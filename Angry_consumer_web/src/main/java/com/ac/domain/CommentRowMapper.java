package com.ac.domain;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 12. 11..
 */
public class CommentRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("idComment"));
        comment.setArticleId(rs.getInt("Article_idArticle"));
        comment.setWriterId(rs.getInt("User_id"));
        comment.setCommentContent(rs.getString("content"));
        return comment;
    }
}
