package com.ac.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 10. 19..
 */
public class Comment {
    private int id;
    private int articleId;
    private int writerId;
    private User writer;
    private String commentContent;
    private String userName;
    private String userGrade;

    public Comment(){}
    
    public Comment (ResultSet rs) throws SQLException {
        this.id = rs.getInt("idComment");
        this.articleId = rs.getInt("Article_idArticle");
        this.writerId = rs.getInt("User_id");
        this.commentContent = rs.getString("content");
        this.userName = rs.getString("name");
        this.userGrade = rs.getString("grade_name");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void settingWriter(User writer) {
        this.writer = writer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", writerId=" + writerId +
                ", writer=" + writer +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
