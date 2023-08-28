package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gametect.api.model.Framework;

public interface FrameworkRepository {

	List<Framework> findAll();

	Optional<Framework> findById(Integer id);

	Framework save(Framework framework);

	void deleteAll();

	void deleteById(Integer id);

}
