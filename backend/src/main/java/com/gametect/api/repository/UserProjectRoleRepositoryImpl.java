package com.gametect.api.repository;

import com.gametect.api.model.UserProjectRole;
import com.gametect.api.repository.mappers.UserProjectMapper;
import com.gametect.api.repository.mappers.UserProjectRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
@Repository
public class UserProjectRoleRepositoryImpl implements UserProjectRoleRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public UserProjectRole save(UserProjectRole userProjectRole) {
        String sql = "INSERT INTO papel (`projeto_usuario_projeto_id`,`projeto_usuario_usuario_id`, `descricao`) VALUES (?,?, ?)";


        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userProjectRole.getProjetoId());
            ps.setInt(2, userProjectRole.getUserId());
            ps.setString(3, userProjectRole.getDescription().getCode());
            return ps;
        });

        return this.findById(userProjectRole.getProjetoId(), userProjectRole.getUserId(), userProjectRole.getDescription().getCode()).get();
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM papel";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer userId, Integer projetoId, String description) {
        String sql = "DELETE FROM papel WHERE projeto_usuario_projeto_id = ? AND projeto_usuario_usuario_id = ?" +
                " AND descricao = ?";
        jdbcTemplate.update(sql, new Object[] { projetoId, userId, description });
    }

    @Override
    public Optional<UserProjectRole> findById(Integer projetoId, Integer userId, String description) {
        String sql = "SELECT * FROM papel WHERE projeto_usuario_projeto_id = ? AND projeto_usuario_usuario_id = ?" +
                " AND descricao = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserProjectRoleMapper(), projetoId, userId, description));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserProjectRole> findByProjetoUser(Integer projetoId, Integer userId) {
        String sql = "SELECT * FROM papel WHERE projeto_usuario_projeto_id = ? AND projeto_usuario_usuario_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserProjectRoleMapper(), projetoId, userId));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }
}
