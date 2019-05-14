package com.karacsonyiz.gameshop.database;

import com.karacsonyiz.gameshop.model.Order;
import com.karacsonyiz.gameshop.model.OrderItem;
import com.karacsonyiz.gameshop.model.OrderStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createOrderAndOrderItems(long userId) {
        KeyHolder keyHolder  = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into orders(user_id, date, status) values(?, ?, 'ACTIVE')", Statement.RETURN_GENERATED_KEYS
            );
            ps.setLong(1,userId);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);
        long orderId = keyHolder.getKey().longValue();

        jdbcTemplate.update("insert into orderitem (order_id, product_id, product_price) " +
                "select ?, products.product_id, products.price from products " +
                "inner join basket on products.product_id = basket.product_id " +
                "where basket.user_id = ?",orderId,userId);

        jdbcTemplate.update("delete from basket where user_id = ?", userId);
    }

    public List<Order> listOrders() {
        return jdbcTemplate.query("SELECT orders.id, orders.user_id, orders.date, orders.status, " +
                "COUNT(orderitem.id) AS quantity, " +
                "SUM(orderitem.product_price) AS price FROM orders " +
                "LEFT JOIN orderitem ON orders.id = orderitem.order_id " +
                "GROUP BY orders.id ORDER BY orders.date DESC",
                (ResultSet resultSet, int i) -> new Order(
                        resultSet.getLong("orders.id"),
                        resultSet.getInt("orders.user_id"),
                        resultSet.getTimestamp("orders.date").toLocalDateTime(),
                        OrderStatus.valueOf(resultSet.getString("orders.status")),
                        resultSet.getLong("quantity"),
                        resultSet.getLong("price")
                ));
    }

    public List<Order> listOrdersByUserId(long userId) {
        return jdbcTemplate.query("SELECT id, user_id, date, status " +
                "FROM orders where user_id = ? ORDER BY date DESC",new OrderMapper(),userId);
    }

    public List<OrderItem> listOrderItems(long orderId,long userId) {
        return jdbcTemplate.query(
                "SELECT orderitem.id, orderitem.order_id, orderitem.product_id, " +
                    "orderitem.product_price, products.name, products.producer " +
                    "FROM orderitem " +
                    "JOIN orders ON orderitem.order_id = orders.id " +
                    "JOIN products ON orderitem.product_id = products.product_id " +
                    "WHERE orders.id = ? AND orders.user_id = ?",
                (ResultSet resultSet, int i) -> new OrderItem(
                        resultSet.getLong("orderitem.id"),
                        resultSet.getLong("orderitem.order_id"),
                        resultSet.getString("orderitem.product_id"),
                        resultSet.getString("products.name"),
                        resultSet.getString("products.producer"),
                        resultSet.getLong("orderitem.product_price")),
                userId,orderId);
    }

    private static class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            int userId = resultSet.getInt("user_id");
            LocalDateTime localDateTime = resultSet.getTimestamp("date").toLocalDateTime();
            OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
            return new Order(id,userId,localDateTime,status);
        }
    }
}
