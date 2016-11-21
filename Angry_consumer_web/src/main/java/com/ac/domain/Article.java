package com.ac.domain;

import java.time.LocalDateTime;

/**
 * Created by Jbee on 2016. 10. 19..
 */
public class Article {
    private int id;
    private String classify;
    private String title;
    private String contents;
    private String writerId;
    private int hit;
    private int countOfComment;
    private int agree;
    private LocalDateTime createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getClassify() {
        return classify;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", classify='" + classify + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", hit=" + hit +
                ", agree=" + agree +
                ", createDate=" + createDate +
                '}';
    }
}
