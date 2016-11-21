package com.ac.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Jbee on 2016. 10. 19..
 */
public class Article {
    private int id;
    private String classify;
    private String title;
    private String contents;
    private int writerId;
    private int hit;
    private int countOfComment;
    private int agree;
    private User writer;
    private Date createDate;
    private MultipartFile uploadFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getCountOfComment() {
        return countOfComment;
    }

    public void setCountOfComment(int countOfComment) {
        this.countOfComment = countOfComment;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", classify='" + classify + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", writerId=" + writerId +
                ", hit=" + hit +
                ", countOfComment=" + countOfComment +
                ", agree=" + agree +
                ", createDate=" + createDate +
                ", uploadFile=" + uploadFile +
                '}';
    }
}