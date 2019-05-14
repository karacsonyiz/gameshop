package com.karacsonyiz.gameshop.database;
import com.karacsonyiz.gameshop.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            int enabled = resultSet.getInt("enabled");
            String role = resultSet.getString("role");
            User user = new User(id,name,password,enabled,role);
            return user;
        }
    }

    public List<User> listUsers() {
        return jdbcTemplate.query("select id, name, password, enabled, role from users",new UserMapper());
    }

    public Optional<User> findUserByUserName(String name){
        try {
            User user = jdbcTemplate.queryForObject("select id, name, password, enabled, role from users where name = ?", new UserMapper(), name);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        }
    }

}
