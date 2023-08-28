package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gametect.api.repository.SectionRepository;
import com.gametect.api.model.Section;

@Repository
public class SectionRepositoryImpl implements SectionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Section> findAll() {
		String sql = "SELECT * FROM secao";
		return jdbcTemplate.query(sql, new SectionMapper());
	}

	@Override
	public List<Section> findByFrameworkId(Integer id) {
		String sql = "SELECT * FROM secao WHERE framework_id = ?";
		return jdbcTemplate.query(sql, new SectionMapper(), id);
	}

	@Override
	public Optional<Section> findById(Integer id) {
		String sql = "SELECT * FROM secao WHERE id = ?";
		try {
			return Optional.of(jdbcTemplate.queryForObject(sql, new SectionMapper(), id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Section save(Section section) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO secao (titulo, descricao, framework_id) VALUES (?,?,?)";
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, section.getTitle());
			ps.setString(2, section.getDescription());
			ps.setInt(3, section.getFrameworkId());
			return ps;
		}, keyHolder);
		return this.findById(keyHolder.getKey().intValue()).get();
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM secao";
		jdbcTemplate.update(sql);
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM secao WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	private class SectionMapper implements RowMapper<Section> {
		@Override
		public Section mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Section section = new Section();
			section.setId(resultSet.getInt("id"));
			section.setTitle(resultSet.getString("titulo"));
			section.setDescription(resultSet.getString("descricao"));
			section.setFrameworkId(resultSet.getInt("framework_id"));
			return section;
		}
	}

}
