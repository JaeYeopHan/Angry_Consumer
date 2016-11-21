package com.ac.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 10. 22..
 */

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int userInsert(User user) {
        String query = "INSERT INTO user(name, email, password) VALUES(?,?,?)";
        return jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getPassword());
    }

    public int userInfoUpdate(User user) {
        String query = "UPDATE user SET name = ?, password=? WHERE email = ?";
        return jdbcTemplate.update(query, user.getName(), user.getPassword(), user.getEmail());
    }

    public String getUserGrade(User user) {
        String query = "SELECT g.grade_name FROM USER AS u INNER JOIN grade AS g ON u.Grade_idGrade = g.idGrade WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{user.getId()}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("g.grade_name");
            }
        });
    }

    public User findUserByEmail(String email) {
        String query = "SELECT * FROM user WHERE email=?";
        User resultUser;
        try {
            resultUser = jdbcTemplate.queryForObject(query, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return resultUser;
    }

    public User findUserByName(String name) {
        String query = "SELECT * FROM user WHERE name=?";
        User resultUser;
        try {
            resultUser = jdbcTemplate.queryForObject(query, new Object[]{name}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return resultUser;
    }

    public User findUserById(int id) {
        String query = "SELECT * FROM user WHERE id=?";
        User resultUser;
        try {
            resultUser = jdbcTemplate.queryForObject(query, new Object[]{id}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return resultUser;
    }
}