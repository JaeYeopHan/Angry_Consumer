package com.ac.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jbee on 2016. 11. 30..
 */
@Repository
public class CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> getListOfComments(int id) {
        String query = "SELECT c.idComment, c.Article_idArticle, c.User_id, c.content, u.name, g.grade_name\n" +
                "FROM comment AS c\n" +
                "INNER JOIN user AS u\n" +
                "INNER JOIN grade AS g\n" +
                "ON u.id = c.user_id AND u.Grade_idGrade = g.idGrade\n" +
                "WHERE c.Article_idArticle = " + id + "\n" +
                "ORDER BY idComment ASC";
        return jdbcTemplate.query(query, new CommentRowMapper());
    }

    public int insertComment(Comment comment, int writerId, int articleId) {
        String query = "INSERT INTO comment(content, Article_idArticle, User_id) VALUES (?, (SELECT idArticle from Article WHERE idArticle = ?), (SELECT id FROM user WHERE id = ?))";
        return jdbcTemplate.update(query, comment.getCommentContent(), articleId, writerId);
    }
}
