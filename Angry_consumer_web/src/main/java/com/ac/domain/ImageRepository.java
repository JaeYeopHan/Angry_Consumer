package com.ac.domain;

import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 11. 30..
 */
@Repository
public class ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertArticleImage(String fileName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO image(path) VALUES(?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, fileName);
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
