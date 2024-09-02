package org.example.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.dto.ShopDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ShopRepository {
    void insert(ShopDTO shopDTO) throws SQLException;
    ShopDTO selectAll() throws SQLException;
    List<ShopDTO> findAll() throws SQLException;
}
