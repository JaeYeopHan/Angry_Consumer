package com.ac.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jbee on 2016. 10. 19..
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String grade;
    private int sumOfAgree;

    public User(){}

    public User (ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.email = rs.getString("email");
        this.password = rs.getString("password");
        this.grade = rs.getString("grade_name");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSumOfAgree() {
        return sumOfAgree;
    }

    public void setSumOfAgree(int sumOfAgree) {
        this.sumOfAgree = sumOfAgree;
    }

    public void updateUserInfo(String name) {
        this.name = name;
    }

    public void updateUserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
