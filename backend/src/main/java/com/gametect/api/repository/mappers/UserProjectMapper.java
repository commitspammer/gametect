package com.gametect.api.repository.mappers;

import com.gametect.api.model.UserProject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProjectMapper implements RowMapper<UserProject> {
    @Override
    public UserProject mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        UserProject userProject = new UserProject();
        userProject.setUserId(Integer.parseInt(resultSet.getString("projeto_id")));
        userProject.setProjetoId(Integer.parseInt(resultSet.getString("usuario_id")));

        return userProject;
    }
}
