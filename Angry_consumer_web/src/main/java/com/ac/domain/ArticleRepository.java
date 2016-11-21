package com.ac.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jbee on 2016. 11. 21..
 */
@Repository
public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getArticleList() {
        String query = "SELECT * FROM article ORDER BY idArticle DESC";
        return jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public int articleInsert(Article article) {
        String query = "INSERT INTO article(title, classify, contents) VALUES(?,?,?)";
        return jdbcTemplate.update(query, article.getTitle(), article.getClassify(), article.getContents());
    }
}
