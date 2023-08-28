package com.gametect.api.repository;

import com.gametect.api.model.UserProject;
import com.gametect.api.repository.mappers.UserProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class UserProjectRepositoryImpl implements UserProjectRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserProject save(UserProject userProject) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO projeto_usuario (`projeto_id`,`usuario_id`) VALUES (?,?)";


        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userProject.getProjetoId());
            ps.setInt(2, userProject.getUserId());

            return ps;
        }, keyHolder);

        return this.findById(userProject.getProjetoId(), userProject.getUserId()).get();
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM projeto_usuario";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer userId, Integer projetoId) {
        String sql = "DELETE FROM projeto_usuario WHERE projeto_id = ? AND usuario_id = ?";
        jdbcTemplate.update(sql, new Object[]{projetoId, userId});
    }

    @Override
    public Optional<UserProject> findById(Integer projetoId, Integer userId) {
        String sql = "SELECT * FROM projeto_usuario WHERE projeto_id = ? and usuario_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserProjectMapper(), projetoId, userId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }
}
