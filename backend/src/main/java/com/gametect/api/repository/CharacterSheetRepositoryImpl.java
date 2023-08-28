package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gametect.api.model.CharacterSheet;
import com.gametect.api.repository.mappers.CharacterSheetMapper;

@Repository
public class CharacterSheetRepositoryImpl implements CharacterSheetRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CharacterSheet> findAll() {
        String sql = "SELECT * FROM ficha_personagem";
        return jdbcTemplate.query(sql, new CharacterSheetMapper());
    }

    @Override
    public Optional<CharacterSheet> findById(Integer id) {
        String sql = "SELECT * FROM ficha_personagem where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new CharacterSheetMapper(), id));
        }
        catch(EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<CharacterSheet> findByProjectId(Integer id) {
        String sql = "SELECT * FROM ficha_personagem where projeto_id = ?";
        return jdbcTemplate.query(sql, new CharacterSheetMapper(), id);
    }

    @Override
    public CharacterSheet save(CharacterSheet characterSheet) {
        String sql = "INSERT INTO ficha_personagem (`projeto_id`, `subsecao_id`,`folha_referencia`, `nome`, `altura`, `peso`, `passado`, `motivacao`) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, characterSheet.getProjectId(), characterSheet.getSubsectionId(), characterSheet.getReferenceSheet(), characterSheet.getName(), characterSheet.getHeight(), characterSheet.getWeight(), characterSheet.getHistory(), characterSheet.getMotivation());
        return characterSheet;
    }

    @Override
    public CharacterSheet update(CharacterSheet characterSheet) {
        String sql = "UPDATE ficha_personagem SET altura = ?, peso = ?, nome = ?, passado = ?, motivacao = ?, folha_referencia = ?, projeto_id = ?, subsecao_id = ?  where id = ? ";
        jdbcTemplate.update(sql, characterSheet.getHeight(), 
            characterSheet.getWeight(), characterSheet.getName(), 
            characterSheet.getHistory(), characterSheet.getMotivation(), 
            characterSheet.getReferenceSheet(), characterSheet.getProjectId(), characterSheet.getSubsectionId(), characterSheet.getId()
        );
        return characterSheet;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM ficha_personagem";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM ficha_personagem WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }
    

    
}
