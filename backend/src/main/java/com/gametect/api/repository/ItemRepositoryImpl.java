package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gametect.api.model.Item;
import com.gametect.api.repository.mappers.ItemMapper;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Item> findAll() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, new ItemMapper());
    }

    @Override
    public List<Item> findBySelectionBoxId(Integer id) {
        String sql = "SELECT * FROM item where caixa_selecao_id = ?";
        return jdbcTemplate.query(sql, new ItemMapper(), id);
        
    }

    @Override
    public Item save(Item item) {
        String sql = "INSERT INTO item (`caixa_selecao_id`,`selecionado`,`descricao`) VALUES (?,?,?)";
        jdbcTemplate.update(sql, item.getSelectionBoxId(), item.getSelected(), item.getDescription());
        return item;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM item";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM item WHERE caixa_selecao_id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }
    
}
