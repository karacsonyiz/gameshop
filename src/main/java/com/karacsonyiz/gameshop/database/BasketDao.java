package com.karacsonyiz.gameshop.database;

import com.karacsonyiz.gameshop.model.BasketItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BasketDao {

    private JdbcTemplate jdbcTemplate;

    public BasketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class BasketItemMapper implements RowMapper<BasketItem> {
        @Override
        public BasketItem mapRow(ResultSet resultSet, int i) throws SQLException {
            long userId = resultSet.getLong("user_id");
            String productId = resultSet.getString("product_id");
            return new BasketItem(userId,productId);
        }
    }

    public void deleteFromBasketByProductIdAndUserId(long userId, String productId) {
        jdbcTemplate.update("delete from basket where user_id = ? AND product_id = ?", userId, productId);
    }

    public List<BasketItem> findBasketItemsByUserIdAndProductId(BasketItem basketItem){
        return jdbcTemplate.query("SELECT id, user_id, product_id FROM basket WHERE user_id = ? AND product_id = ?",
                new BasketItemMapper(),basketItem.getUserId(),basketItem.getProductId());
    }

    public void addToBasket(BasketItem basketItem){
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into basket(user_id,product_id) values(?,?)");
            ps.setLong(1,basketItem.getUserId());
            ps.setString(2,basketItem.getProductId());
            return ps;
        });
    }

    public void deleteFromBasketByUserId(long userId) {
        jdbcTemplate.update("delete from basket where user_id = ?",userId);
    }


}
