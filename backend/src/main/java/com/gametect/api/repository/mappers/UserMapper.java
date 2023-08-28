package com.gametect.api.repository.mappers;

import com.gametect.api.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(Integer.parseInt(resultSet.getString("id")));
        user.setName(resultSet.getString("nome"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("senha"));
        user.setAvatar(resultSet.getString("foto"));

        return user;
    }
}