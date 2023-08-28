package com.gametect.api.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gametect.api.model.SelectionBox;

public class SelectionBoxMapper implements RowMapper<SelectionBox>{

    @Override
    @Nullable
    public SelectionBox mapRow(ResultSet rs, int rowNum) throws SQLException {
        SelectionBox selectionBox = new SelectionBox();
        selectionBox.setId(rs.getInt("id"));
        selectionBox.setProjectId(rs.getInt("projeto_id"));
        selectionBox.setSubsectionId(rs.getInt("subsecao_id"));
        selectionBox.setQuestion(rs.getString("questao"));

        return selectionBox;
    }
    
}
