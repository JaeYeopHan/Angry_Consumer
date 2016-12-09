package com.ac.domain;

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
import java.sql.Statement;
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

    public List<Article> getArticleListCountOfSix() {
        String query = "SELECT * FROM article ORDER BY idArticle DESC LIMIT 6";
        return jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public List<Article> getArticleListByQuery(String keyword) {
        String query = "SELECT * FROM article WHERE title LIKE '%" + keyword + "%' OR contents LIKE '%" + keyword + "%' ORDER BY idArticle DESC";
        return  jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public List<Article> getArticleListByQueryOfRange(String keyword, String range) {
        String query = "SELECT * FROM article WHERE " + range + " LIKE '%" + keyword + "%' ORDER BY idArticle DESC";
        return  jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public int articleInsert(Article article, User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO article(title, classify, contents, user_id, idImage) VALUES(?,?,?,(SELECT id FROM USER WHERE id = ?),?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, article.getClassify());
                pstmt.setString(3, article.getContents());
                pstmt.setInt(4, user.getId());
                pstmt.setInt(5, article.getIdImage());
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Article getArticleByArticleId(int idArticle) {
        String query = "SELECT * FROM article WHERE idArticle = ?";
        Article resultArticle;
        try {
            resultArticle = jdbcTemplate.queryForObject(query, new Object[]{idArticle}, new ArticleRowMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return resultArticle;
    }

    public void deleteArticle(int idArticle) {
        jdbcTemplate.update("DELETE FROM article WHERE idArticle = ?", Integer.valueOf(idArticle));
    }

    public void updateArticle(Article article, int id) {
        String query = "UPDATE article SET title = ?, classify = ?, contents = ? WHERE idArticle = ?";
        jdbcTemplate.update(query, article.getTitle(), article.getClassify(), article.getContents(), id);
    }

    public void updateAgreeOfArticle(int id) {
        String query = "UPDATE article SET agree = agree + 1 WHERE idArticle = ?";
        jdbcTemplate.update(query, id);
    }

    public void updateHitOfArticle(int id) {
        String query = "update article SET hit = hit + 1 WHERE idArticle = ?";
        jdbcTemplate.update(query, id);
    }

}