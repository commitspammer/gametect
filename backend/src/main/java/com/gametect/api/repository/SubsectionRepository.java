package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gametect.api.model.Subsection;

public interface SubsectionRepository {

	List<Subsection> findAll();

	List<Subsection> findBySectionId(Integer id);

	Optional<Subsection> findById(Integer id);

	Subsection save(Subsection subsection);

	void deleteAll();

	void deleteById(Integer id);

}
