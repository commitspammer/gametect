package com.gametect.api.repository.mappers;

import com.gametect.api.enumerations.UserProjectRoleEnum;
import com.gametect.api.model.UserProjectRole;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProjectRoleMapper implements RowMapper<UserProjectRole> {
    @Override
    public UserProjectRole mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        UserProjectRole userProjectRole = new UserProjectRole();
        userProjectRole.setProjetoId(Integer.parseInt(resultSet.getString("projeto_usuario_projeto_id")));
        userProjectRole.setUserId(Integer.parseInt(resultSet.getString("projeto_usuario_usuario_id")));
        userProjectRole.setDescription(UserProjectRoleEnum.valueOf(resultSet.getString("descricao")));

        return userProjectRole;
    }


}
