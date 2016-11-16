package com.ac.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jbee on 2016. 10. 22..
 */

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public List<User> getUserList() {
//        String query = "SELECT * FROM user";
//        return jdbcTemplate.query(query, new UserRowMapper());
//    }

    //insert query
    public int insert(User user) {
        String query = "INSERT INTO user(name, email, password) VALUES(?,?,?)";
        return jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getPassword());
    }

    public int update(User user) {
        String query = "UPDATE user SET name = ?, password=? WHERE email = ?";
        return jdbcTemplate.update(query, user.getName(), user.getPassword(), user.getEmail());
    }

    //confirm exist email
    public User existEmail(String email) {
        String query = "SELECT * FROM user WHERE email=?";
        User resultUser;
        try {
            resultUser = jdbcTemplate.queryForObject(query, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return resultUser;
    }

    //confirm exist name
    public User existName(String name) {
        String query = "SELECT * FROM user WHERE name=?";
        User resultUser;
        try {
            resultUser = jdbcTemplate.queryForObject(query, new Object[]{name}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return resultUser;
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}