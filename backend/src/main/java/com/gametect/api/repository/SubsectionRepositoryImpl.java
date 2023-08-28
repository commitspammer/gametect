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

import com.gametect.api.repository.SubsectionRepository;
import com.gametect.api.model.Subsection;

@Repository
public class SubsectionRepositoryImpl implements SubsectionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Subsection> findAll() {
		String sql = "SELECT * FROM subsecao";
		return jdbcTemplate.query(sql, new SubsectionMapper());
	}

	@Override
	public List<Subsection> findBySectionId(Integer id) {
		String sql = "SELECT * FROM subsecao WHERE secao_id = ?";
		return jdbcTemplate.query(sql, new SubsectionMapper(), id);
	}

	@Override
	public Optional<Subsection> findById(Integer id) {
		String sql = "SELECT * FROM subsecao WHERE id = ?";
		try {
			return Optional.of(jdbcTemplate.queryForObject(sql, new SubsectionMapper(), id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Subsection save(Subsection subsection) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO subsecao (titulo, decricao, secao_id) VALUES (?,?,?)";
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, subsection.getTitle());
			ps.setString(2, subsection.getDescription());
			ps.setInt(3, subsection.getSectionId());
			return ps;
		}, keyHolder);
		return this.findById(keyHolder.getKey().intValue()).get();
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM subsecao";
		jdbcTemplate.update(sql);
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM subsecao WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	private class SubsectionMapper implements RowMapper<Subsection> {
		@Override
		public Subsection mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Subsection subsection = new Subsection();
			subsection.setId(resultSet.getInt("id"));
			subsection.setTitle(resultSet.getString("titulo"));
			subsection.setDescription(resultSet.getString("decricao"));
			subsection.setSectionId(resultSet.getInt("secao_id"));
			return subsection;
		}
	}

}
