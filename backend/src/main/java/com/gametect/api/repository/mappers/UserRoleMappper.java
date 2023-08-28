package com.gametect.api.repository.mappers;

import com.gametect.api.enumerations.UserProjectRoleEnum;
import com.gametect.api.model.UserRole;
import org.springframework.lang.Nullable;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMappper implements RowMapper<UserRole> {

    @Override
    @Nullable
    public UserRole mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserRole user = new UserRole();
        user.setId(Integer.parseInt(resultSet.getString("id")));
        user.setName(resultSet.getString("nome"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(UserProjectRoleEnum.valueOf(resultSet.getString("descricao")));
        return user;
    }
}
