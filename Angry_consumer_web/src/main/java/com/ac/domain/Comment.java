package com.ac.domain;

/**
 * Created by Jbee on 2016. 10. 19..
 */
public class Comment {
    private int id;
    private int articleId;
    private int writerId;
    private User writer;
    private String commentContent;

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
