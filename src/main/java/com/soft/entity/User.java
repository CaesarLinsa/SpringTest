package com.soft.entity;

import org.springframework.stereotype.Service;

@Service
public class User {

    private Integer id;

    private String  userName;

    private String passWord;

    private String gender;

    public User() {

    }

    public User(Integer id, String userName, String passWord, String gender) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
