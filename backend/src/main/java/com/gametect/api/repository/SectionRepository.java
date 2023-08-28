package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gametect.api.model.Section;

public interface SectionRepository {

	List<Section> findAll();

	List<Section> findByFrameworkId(Integer id);

	Optional<Section> findById(Integer id);

	Section save(Section section);

	void deleteAll();

	void deleteById(Integer id);

}
