package com.tilldawn.Models;

public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private int securityQuestionID;
    private String answer;
    private String avatar;
    private String weapon;
    private int kill;
    private int health = 100;
    private int time;
    private int score;
    private int playerX;
    private int playerY;
    private int speed = 5;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public User(int id, String username, String password, String salt, int securityQuestionID, String answer,
                String avatar, String weapon, int kill, int time, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.securityQuestionID = securityQuestionID;
        this.answer = answer;
        this.avatar = avatar;
        this.weapon = weapon;
        this.kill = kill;
        this.time = time;
        this.score = score;
    }

    // setter

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getter

    public String getAvatar() {
        return avatar;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getKill() {
        return kill;
    }

    public int getHealth() {
        return health;
    }

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
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

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }
}
