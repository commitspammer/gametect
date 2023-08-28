package com.gametect.api.repository;

import com.gametect.api.model.Project;
import com.gametect.api.repository.mappers.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Project> findAll() {
        String sql = "SELECT * FROM projeto";
        return jdbcTemplate.query(sql, new ProjectMapper());
    }

    @Override
    public List<Project> findByUser(Integer idUser) {
        String sql = "select p.* from projeto_usuario pu \n" +
                "inner join projeto p on p.id = pu.projeto_id\n" +
                "inner join usuario u on u.id = pu.usuario_id\n" +
                "where u.id = ?";
        return jdbcTemplate.query(sql, new ProjectMapper(), idUser);
    }

    @Override
    public Optional<Project> findById(Integer id) {
        String sql = "SELECT * FROM projeto WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProjectMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Project save(Project project) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO projeto (`titulo`,`descricao`, `banner` ,`framework_id`) VALUES (?,?,?,?)";


        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, project.getTitle());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getBanner());
            ps.setInt(4, project.getFrameworkId());
            return ps;
        }, keyHolder);

        return this.findById(Integer.parseInt(keyHolder.getKey().toString())).get();
    }

    @Override
    public Project update(Project project) {
        String sql = "UPDATE projeto SET `titulo` = ?, `descricao` = ?, " +
                " `banner` = ? " +
                " WHERE id = ?";

        jdbcTemplate.update(sql,
                project.getTitle(),
                project.getDescription(),
                project.getBanner(),
                project.getId());
        return project;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM projeto";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM projeto WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

}
