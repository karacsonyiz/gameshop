package com.karacsonyiz.gameshop.database;

import com.karacsonyiz.gameshop.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            String productId = resultSet.getString("product_id");
            String name = resultSet.getString("name");
            String producer = resultSet.getString("producer");
            long price = resultSet.getLong("price");
            long quantity = resultSet.getLong("quantity");
            Product product = new Product(productId,name,producer,price,quantity);
            return product;
        }
    }

    public List<Product> listProducts(){
        return jdbcTemplate.query("select product_id, name, producer, price, quantity from products order by name,producer asc",new ProductMapper());
    }

    public List<Product> listBasketItemsForUser(long userId){
        return jdbcTemplate.query("SELECT products.product_id, name, producer, price, quantity from products" +
                " LEFT JOIN basket on products.product_id = basket.product_id where basket.user_id = ?", new ProductMapper(), userId);
    }

    public Optional<Product> findGameById(String id){
        try {
            Product product = jdbcTemplate.queryForObject("select product_id, name, producer, price, quantity from products where product_id = ?", new ProductMapper(), id);
            return Optional.of(product);
        } catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        }
    }

    public void createProduct(Product product) {
        String uuid = UUID.randomUUID().toString();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into products(product_id, name, producer, price, quantity) values(?,?,?,?,?)");
            ps.setString(1, uuid);
            ps.setString(2, product.getName());
            ps.setString(3, product.getProducer());
            ps.setLong(4, product.getPrice());
            ps.setLong(5, product.getQuantity());
            return ps;
        });
    }
}

