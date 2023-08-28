package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gametect.api.model.SelectionBox;
import com.gametect.api.repository.mappers.SelectionBoxMapper;

@Repository
public class SelectionBoxRepositoryImpl implements SelectionBoxRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SelectionBox> findAll() {
        String sql = "SELECT * FROM caixa_selecao";
        return jdbcTemplate.query(sql, new SelectionBoxMapper());
    }

    @Override
    public Optional<SelectionBox> findById(Integer id) {
        String sql = "SELECT * FROM caixa_selecao where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new SelectionBoxMapper(), id));
        }
        catch(EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<SelectionBox> findByProjectId(Integer id) {
        String sql = "SELECT * FROM caixa_selecao where projeto_id = ?";
        return jdbcTemplate.query(sql, new SelectionBoxMapper(), id);
    }

    @Override
    public SelectionBox save(SelectionBox selectionBox) {
        String sql = "INSERT INTO caixa_selecao (`projeto_id`, `subsecao_id`, `questao`) VALUES (?,?,?)";
        jdbcTemplate.update(sql, selectionBox.getProjectId(), selectionBox.getSubsectionId(), selectionBox.getQuestion());
        return selectionBox;
    }

    @Override
    public SelectionBox update(SelectionBox selectionBox) {
        String sql = "UPDATE caixa_selecao SET questao = ?, projeto_id = ?, subsecao_id = ? where id = ? ";
        jdbcTemplate.update(sql, selectionBox.getQuestion(), selectionBox.getProjectId(), selectionBox.getSubsectionId(), selectionBox.getId());
        return selectionBox;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM caixa_selecao";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM caixa_selecao WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }
    
}
