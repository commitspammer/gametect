package com.gametect.api.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gametect.api.model.Image;

public class ImageMapper implements RowMapper<Image>{

    @Override
    @Nullable
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt("id"));
        image.setProjectId(rs.getInt("projeto_id"));
        image.setSubsectionId(rs.getInt("subsecao_id"));
        image.setUrl(rs.getString("url"));
        image.setHeight(rs.getDouble("altura"));
        image.setWidth(rs.getDouble("largura"));

        return image;
    }
    
}
