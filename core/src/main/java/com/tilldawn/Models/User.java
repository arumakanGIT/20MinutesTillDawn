package com.tilldawn.Models;

import com.tilldawn.Models.Enums.Gun;
import com.tilldawn.Models.Enums.Move;

public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private int securityQuestionID;
    private String answer;
    private String avatar;
    private Gun weapon;
    private int kill;
    private int health = 100;
    private int time;
    private int score;
    private Move moveState = Move.walk;
    private GameInputSetting gameInputSetting;
    private final CollisionRect rect;
    private boolean autoReload;
    private boolean bw;

    public User(int id, String username, String password, String salt, int securityQuestionID, String answer,
                String avatar, Gun weapon, int kill, int time, int score, boolean autoReload, boolean bw) {
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
        this.autoReload = autoReload;
        this.bw = bw;
        rect = new CollisionRect(0, 0, 0, 0);
    }

    // setter

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setWeapon(Gun weapon) {
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

    public void setMoveState(Move moveState) {
        this.moveState = moveState;
    }

    public void setGameInputSetting(GameInputSetting gameInputSetting) {
        this.gameInputSetting = gameInputSetting;
    }

    // getter

    public String getAvatar() {
        return avatar;
    }

    public Gun getWeapon() {
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

    public Move getMoveState() {
        return moveState;
    }

    public GameInputSetting getGameInputSetting() {
        return gameInputSetting;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isBw() {
        return bw;
    }

    public void setBw(boolean bw) {
        this.bw = bw;
    }

}
