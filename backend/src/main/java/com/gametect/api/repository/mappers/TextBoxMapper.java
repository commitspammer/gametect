package com.gametect.api.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gametect.api.model.TextBox;

public class TextBoxMapper implements RowMapper<TextBox>{

    @Override
    @Nullable
    public TextBox mapRow(ResultSet rs, int rowNum) throws SQLException {
        TextBox textBox = new TextBox();
        textBox.setId(rs.getInt("id"));
        textBox.setProjectId(rs.getInt("projeto_id"));
        textBox.setSubsectionId(rs.getInt("subsecao_id"));
        textBox.setText(rs.getString("texto"));

        return textBox;
    }
    
}
