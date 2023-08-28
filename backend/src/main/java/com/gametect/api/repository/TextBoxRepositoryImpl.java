package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gametect.api.model.TextBox;
import com.gametect.api.repository.mappers.TextBoxMapper;

@Repository
public class TextBoxRepositoryImpl implements TextBoxRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TextBox> findAll() {
        String sql = "SELECT * FROM caixa_texto";
        return jdbcTemplate.query(sql, new TextBoxMapper());
    }

    @Override
    public Optional<TextBox> findById(Integer id) {
        String sql = "SELECT * FROM caixa_texto where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new TextBoxMapper(), id));
        }
        catch(EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<TextBox> findByProjectId(Integer id) {
        String sql = "SELECT * FROM caixa_texto where projeto_id = ?";
        return jdbcTemplate.query(sql, new TextBoxMapper(), id);
    }

    @Override
    public TextBox save(TextBox textBox) {
        String sql = "INSERT INTO caixa_texto (`projeto_id`, `subsecao_id`,`texto`) VALUES (?,?,?)";
        jdbcTemplate.update(sql, textBox.getProjectId(), textBox.getSubsectionId(), textBox.getText());
        return textBox;
    }

    @Override
    public TextBox update(TextBox textBox) {
        String sql = "UPDATE caixa_texto SET texto = ?, projeto_id = ?, subsecao_id = ? where id = ? ";
        jdbcTemplate.update(sql, textBox.getText(), textBox.getProjectId(), textBox.getSubsectionId(),textBox.getId());
        return textBox;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM caixa_texto";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM caixa_texto WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }
    
}
