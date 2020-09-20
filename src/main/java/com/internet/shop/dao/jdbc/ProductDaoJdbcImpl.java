package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product item) {
        Connection connection = ConnectionUtil.getConnection();
        
        return null;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product update(Product item) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
