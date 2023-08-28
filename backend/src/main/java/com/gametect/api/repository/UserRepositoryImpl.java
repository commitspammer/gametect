package com.gametect.api.repository;

import com.gametect.api.model.User;
import com.gametect.api.model.UserRole;
import com.gametect.api.repository.mappers.ProjectMapper;
import com.gametect.api.repository.mappers.UserMapper;
import com.gametect.api.repository.mappers.UserRoleMappper;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO usuario (`nome`,`email`,`senha`, `foto`) VALUES (?,?,?,?)";


        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAvatar());
            return ps;
        }, keyHolder);

        return this.findById(Integer.parseInt(keyHolder.getKey().toString())).get();
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE usuario SET `nome` = ?, `email` = ?, `foto` = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getAvatar(),
                user.getId());
        return user;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM usuario";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new UserMapper(), email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserRole> findByProject(Integer id) {
        String sql = "select u.*, papel.descricao from papel papel\n" +
                "inner join usuario u on u.id = papel.projeto_usuario_usuario_id\n" +
                "inner join projeto p on p.id = papel.projeto_usuario_projeto_id\n" +
                "where p.id = ?";
        return jdbcTemplate.query(sql, new UserRoleMappper(), id);
    }


}
