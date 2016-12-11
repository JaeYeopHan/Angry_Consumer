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
        String query = "SELECT * FROM comment WHERE Article_idArticle = "+ id +" ORDER BY idComment ASC";
        return jdbcTemplate.query(query, new CommentRowMapper());
    }

    public int insertComment(Comment comment, int writerId, int articleId) {
        String query = "INSERT INTO comment(content, Article_idArticle, User_id) VALUES (?, (SELECT idArticle from Article WHERE idArticle = ?), (SELECT id FROM user WHERE id = ?))";
        return jdbcTemplate.update(query, comment.getCommentContent(), articleId, writerId);
    }
}
