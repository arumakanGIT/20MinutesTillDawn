package com.tilldawn.Models;

public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private int securityQuestionID;
    private String answer;

    public User(int id, String username, String password, String salt, int securityQuestionID, String answer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.securityQuestionID = securityQuestionID;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public int getSecurityQuestionID() {
        return securityQuestionID;
    }

    public String getAnswer() {
        return answer;
    }
}
