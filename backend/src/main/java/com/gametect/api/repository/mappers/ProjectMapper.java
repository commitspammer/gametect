package com.gametect.api.repository.mappers;
import com.gametect.api.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Project project = new Project();
        project.setId(Integer.parseInt(resultSet.getString("id")));
        project.setTitle(resultSet.getString("titulo"));
        project.setDescription(resultSet.getString("descricao"));
        project.setBanner(resultSet.getString("banner"));
        project.setFrameworkId(Integer.parseInt(resultSet.getString("framework_id")));
        return project;
    }
}