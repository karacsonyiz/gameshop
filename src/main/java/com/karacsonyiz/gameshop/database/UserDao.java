package com.karacsonyiz.gameshop.database;
import com.karacsonyiz.gameshop.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");
            User user = new User(id,name,password,email,role);
            return user;
        }
    }

    public List<User> listUsers() {
        return jdbcTemplate.query("select id, name, password, email, enabled, role from users",new UserMapper());
    }

    public Optional<User> findUserByUserName(String name){
        try {
            User user = jdbcTemplate.queryForObject("select id, name, password, email, enabled, role from users where name = ?", new UserMapper(), name);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        }
    }

    public Optional<User> findUserByEmail(String email){
        try {
            User user = jdbcTemplate.queryForObject("select id, name, password, email, enabled, role from users where email = ?", new UserMapper(), email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        }
    }

    public long createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into users(name, password, email, enabled, role) values(?, ?, ?, 1, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getRole());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    }
