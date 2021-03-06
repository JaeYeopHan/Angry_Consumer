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

    public List<Article> getArticleListByOption(String option) {
        String query = "SELECT a.*, i.path\n" +
                       "FROM article AS a\n" +
                       "INNER JOIN image AS i\n" +
                       "ON i.idImage = a.idImage\n" +
                       "WHERE a.classify = '" + option + "'\n" +
                       "ORDER BY idArticle DESC";
        return jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public List<Article> getArticleListCountOfSix() {
        String query = "SELECT a.*, i.path\n" +
                       "FROM article AS a\n" +
                       "INNER JOIN image AS i\n" +
                       "ON i.idImage = a.idImage\n" +
                       "ORDER BY idArticle DESC LIMIT 6";
        return jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public List<Article> getArticleListByQuery(String keyword) {
        String query = "SELECT a.*, i.path\n" +
                       "FROM article AS a\n" +
                       "INNER JOIN image AS i\n" +
                       "ON i.idImage = a.idImage\n" +
                       "WHERE title LIKE '%" + keyword + "%' OR contents LIKE '%" + keyword + "%'\n" +
                       "ORDER BY idArticle DESC";
        return  jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public List<Article> getArticleListByQueryOfRange(String keyword, String range) {
        String query = "SELECT a.*, i.path\n" +
                       "FROM article AS a\n" +
                       "INNER JOIN image AS i\n" +
                       "ON i.idImage = a.idImage\n" +
                       "WHERE " + range + " LIKE '%" + keyword + "%'\n" +
                       "ORDER BY idArticle DESC";
        return  jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public List<Article> getArticleListOfPage(int pageNum) {
        String query = "SELECT a.*, i.path\n" +
                       "FROM article AS a\n" +
                       "INNER JOIN image AS i\n" +
                       "ON i.idImage = a.idImage\n" +
                       "ORDER BY idArticle DESC LIMIT " + pageNum + ", 10";
        return jdbcTemplate.query(query, new ArticleRowMapper());
    }

    public int getcountOfAllArticle() {
        String query = "SELECT count(*) FROM article";
        return jdbcTemplate.queryForObject(query, new Object[]{}, Integer.class);
    }

    public int insertArticle(Article article, User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO article(title, classify, contents, user_id, idImage) VALUES(?,?,?,(SELECT id FROM user WHERE id = ?),?)";
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
        String query = "SELECT a.*, i.path\n" +
                       "FROM article AS a\n" +
                       "INNER JOIN image AS i\n" +
                       "ON i.idImage = a.idImage\n" +
                       "WHERE idArticle = ?";
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

    public void updateCountOfComment(int id) {
        String query = "UPDATE article SET countOfComment = countOfComment + 1 WHERE idArticle = ?";
        jdbcTemplate.update(query, id);
    }

    public int getSumOfAgreeByUserId(int id) {
        String query = "SELECT sum(agree) FROM article WHERE user_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, Integer.class);
    }
}