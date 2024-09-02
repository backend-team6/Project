package org.example.model.service;

import org.example.model.dto.ShopDTO;
import org.example.model.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {
    private static final int COUNT_PER_PAGE = 10;
    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDTO> findAll() throws SQLException {
        return shopRepository.findAll();
    }

    public void insert(ShopDTO shopDTO) throws SQLException {
        shopRepository.insert(shopDTO);
    }
}
