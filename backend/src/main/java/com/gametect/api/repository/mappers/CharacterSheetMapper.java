package com.gametect.api.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gametect.api.model.CharacterSheet;

public class CharacterSheetMapper implements RowMapper<CharacterSheet>{

    @Override
    @Nullable
    public CharacterSheet mapRow(ResultSet rs, int rowNum) throws SQLException {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setId(rs.getInt("id"));
        characterSheet.setProjectId(rs.getInt("projeto_id"));
        characterSheet.setSubsectionId(rs.getInt("subsecao_id"));
        characterSheet.setName(rs.getString("nome"));
        characterSheet.setHistory(rs.getString("passado"));
        characterSheet.setMotivation(rs.getString("motivacao"));
        characterSheet.setReferenceSheet(rs.getString("folha_referencia"));
        characterSheet.setHeight(rs.getDouble("altura"));
        characterSheet.setWeight(rs.getDouble("peso"));

        return characterSheet;
    }
    
}
