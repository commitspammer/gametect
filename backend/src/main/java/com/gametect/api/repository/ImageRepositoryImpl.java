package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.gametect.api.model.Image;
import com.gametect.api.repository.mappers.ImageMapper;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Image> findAll() {
        String sql = "SELECT * FROM imagem";
        return jdbcTemplate.query(sql, new ImageMapper());
    }

    @Override
    public Optional<Image> findById(Integer id) {
        String sql = "SELECT * FROM imagem where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new ImageMapper(), id));
        }
        catch(EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Image> findByProjectId(Integer id) {
        String sql = "SELECT * FROM imagem where projeto_id = ?";
        return jdbcTemplate.query(sql, new ImageMapper(), id);
    }

    @Override
    public Image save(Image image) {
        String sql = "INSERT INTO imagem (`projeto_id`, `subsecao_id`,`altura`,`largura`,`url`) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, image.getProjectId(), image.getSubsectionId(), image.getHeight(), image.getWidth(), image.getUrl());
        return image;
    }

    @Override
    public Image update(Image image) {
        String sql = "UPDATE imagem SET altura = ?, largura = ?, url = ?, projeto_id = ?, subsecao_id = ? where id = ? ";
        jdbcTemplate.update(sql, image.getHeight(), image.getWidth(), image.getUrl(), image.getProjectId(), image.getSubsectionId(), image.getId());
        return image;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM imagem";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM imagem WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }
    
}
