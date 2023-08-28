package com.gametect.api.service;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.Optional;

import com.gametect.api.repository.FrameworkRepository;
import com.gametect.api.repository.SectionRepository;
import com.gametect.api.repository.SubsectionRepository;
import com.gametect.api.model.dto.FrameworkDTO;
import com.gametect.api.model.dto.SectionDTO;
import com.gametect.api.model.dto.SubsectionDTO;
import com.gametect.api.model.Framework;
import com.gametect.api.model.Section;
import com.gametect.api.model.Subsection;
import com.gametect.api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrameworkService {

	@Autowired
	private FrameworkRepository frameworkRepository;

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private SubsectionRepository subsectionRepository;

	public List<FrameworkDTO> listAll() {
		return frameworkRepository.findAll()
			.stream()
			.map(f -> {
				FrameworkDTO fdto = new FrameworkDTO();
				fdto.setId(f.getId());
				fdto.setName(f.getName());
				fdto.setTutorial(f.getTutorial());
				fdto.setSections(sectionRepository.findByFrameworkId(f.getId())
					.stream()
					.map(s -> {
						SectionDTO sdto = new SectionDTO();
						sdto.setId(s.getId());
						sdto.setTitle(s.getTitle());
						sdto.setDescription(s.getDescription());
						sdto.setSubsections(subsectionRepository.findBySectionId(s.getId())
							.stream()
							.map(ss -> {
								SubsectionDTO ssdto = new SubsectionDTO();
								ssdto.setId(ss.getId());
								ssdto.setTitle(ss.getTitle());
								ssdto.setDescription(ss.getDescription());
								return ssdto;
							})
							.toList()
						);
						return sdto;
					})
					.toList()
				);
				return fdto;
			})
			.toList();
	}

	public FrameworkDTO findById(Integer id) {
		return frameworkRepository.findById(id)
			.map(f -> {
				FrameworkDTO fdto = new FrameworkDTO();
				fdto.setId(f.getId());
				fdto.setName(f.getName());
				fdto.setTutorial(f.getTutorial());
				fdto.setSections(sectionRepository.findByFrameworkId(f.getId())
					.stream()
					.map(s -> {
						SectionDTO sdto = new SectionDTO();
						sdto.setId(s.getId());
						sdto.setTitle(s.getTitle());
						sdto.setDescription(s.getDescription());
						sdto.setSubsections(subsectionRepository.findBySectionId(s.getId())
							.stream()
							.map(ss -> {
								SubsectionDTO ssdto = new SubsectionDTO();
								ssdto.setId(ss.getId());
								ssdto.setTitle(ss.getTitle());
								ssdto.setDescription(ss.getDescription());
								return ssdto;
							})
							.toList()
						);
						return sdto;
					})
					.toList()
				);
				return fdto;
			})
			.orElseThrow(ResourceNotFoundException::new);
	}

	public FrameworkDTO create(FrameworkDTO fdto) {
		Framework f = new Framework();
		f.setName(fdto.getName());
		f.setTutorial(fdto.getTutorial());
		fdto.setId(frameworkRepository.save(f).getId());
		for (SectionDTO sdto: fdto.getSections()) {
			Section s = new Section();
			s.setTitle(sdto.getTitle());
			s.setDescription(sdto.getDescription());
			s.setFrameworkId(fdto.getId());
			sdto.setId(sectionRepository.save(s).getId());
			for (SubsectionDTO ssdto: sdto.getSubsections()) {
				Subsection ss = new Subsection();
				ss.setTitle(ssdto.getTitle());
				ss.setDescription(ssdto.getDescription());
				ss.setSectionId(sdto.getId());
				ssdto.setId(subsectionRepository.save(ss).getId());
			}
		}
		return fdto;
	}

	public void delete(Integer id) {
		List<Integer> secs = sectionRepository.findAll()
			.stream()
			.filter(s -> s.getFrameworkId() == id)
			.map(s -> s.getId())
			.toList();
		List<Integer> subs = subsectionRepository.findAll()
			.stream()
			.filter(ss -> secs.contains(ss.getSectionId()))
			.map(ss -> ss.getId())
			.toList();
		subs.forEach(subsectionRepository::deleteById);
		secs.forEach(sectionRepository::deleteById);
		frameworkRepository.deleteById(id);
	}

	public void delete(FrameworkDTO f) {
		this.delete(f.getId());
	}

}
