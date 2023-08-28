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

import com.gametect.api.repository.FrameworkRepository;
import com.gametect.api.model.Framework;

@Repository
public class FrameworkRepositoryImpl implements FrameworkRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Framework> findAll() {
		String sql = "SELECT * FROM framework";
		return jdbcTemplate.query(sql, new FrameworkMapper());
	}

	@Override
	public Optional<Framework> findById(Integer id) {
		String sql = "SELECT * FROM framework WHERE id = ?";
		try {
			return Optional.of(jdbcTemplate.queryForObject(sql, new FrameworkMapper(), id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Framework save(Framework framework) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO framework (nome, tutorial) VALUES (?,?)";
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, framework.getName());
			ps.setString(2, framework.getTutorial());
			return ps;
		}, keyHolder);
		return this.findById(keyHolder.getKey().intValue()).get();
	}

	@Override
	public void deleteAll() {
		String sql = "DELETE FROM framework";
		jdbcTemplate.update(sql);
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM framework WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	private class FrameworkMapper implements RowMapper<Framework> {
		@Override
		public Framework mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Framework framework = new Framework();
			framework.setId(resultSet.getInt("id"));
			framework.setName(resultSet.getString("nome"));
			framework.setTutorial(resultSet.getString("tutorial"));
			return framework;
		}
	}

}
