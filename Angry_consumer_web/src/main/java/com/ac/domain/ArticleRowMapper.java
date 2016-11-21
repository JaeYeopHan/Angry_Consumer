package com.ac.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 11. 21..
 */
public final class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setTitle(rs.getString("title"));
        article.setContents(rs.getString("contents"));
        article.setClassify(rs.getString("classify"));
        return article;
    }
}
