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
        article.setId(rs.getInt("idArticle"));
        article.setTitle(rs.getString("title"));
        article.setContents(rs.getString("contents"));
        article.setClassify(rs.getString("classify"));
        article.setHit(rs.getInt("hit"));
        article.setAgree(rs.getInt("agree"));
        article.setCountOfComment(rs.getInt("countOfComment"));
        article.setWriterId(rs.getInt("user_id"));
        article.setCreateDate(rs.getDate("create_date"));
        article.setIdImage(rs.getInt("idImage"));
        return article;
    }
}
