package com.gametect.api.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gametect.api.model.Item;

public class ItemMapper implements RowMapper<Item>{

    @Override
    @Nullable
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setSelectionBoxId(rs.getInt("caixa_selecao_id"));
        item.setDescription(rs.getString("descricao"));
        item.setSelected(rs.getBoolean("selecionado"));

        return item;
    }
    
}
