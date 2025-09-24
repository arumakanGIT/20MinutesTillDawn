package com.tilldawn.Models;

import java.sql.Connection;
import java.sql.Statement;

public class UserTable {
        public static void createUserTable() {
            try (Connection conn = Database.connect();
                 Statement stmt = conn.createStatement()) {

                String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL," +
                    "salt TEXT NOT NULL," +
                    "securityQuestionID INTEGER NOT NULL," +
                    "answer TEXT NOT NULL," +
                    "rememberToken TEXT," +
                    "avatar TEXT NOT NULL DEFAULT 'Abby.png'," +
                    "weapon TEXT NOT NULL DEFAULT 'Revolver'," +
                    "minutes INTEGER NOT NULL DEFAULT 20," +
                    "seconds INTEGER NOT NULL DEFAULT 0," +
                    "score INTEGER NOT NULL DEFAULT 0," +
                    "kill INTEGER NOT NULL DEFAULT 0," +
                    "time INTEGER NOT NULL DEFAULT 0)";

                stmt.execute(sql);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

    public static void createGameInputTable() {
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS game_inputs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userID INTEGER NOT NULL UNIQUE," +
                "up INTEGER NOT NULL," +
                "down INTEGER NOT NULL," +
                "left INTEGER NOT NULL," +
                "right INTEGER NOT NULL," +
                "reload INTEGER NOT NULL," +
                "speed INTEGER NOT NULL," +
                "autoAim INTEGER NOT NULL," +
                "FOREIGN KEY(userID) REFERENCES users(id) ON DELETE CASCADE)";

            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }


    public static void createSettingTable() {
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS settings (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "userID INTEGER NOT NULL," +
                    "song TEXT NOT NULL," +
                    "music FLOAT NOT NULL DEFAULT 0.5," +
                    "SFX FLOAT NOT NULL DEFAULT 1," +
                    "foot FLOAT NOT NULL DEFAULT 0.5," +
                    "ambient FLOAT NOT NULL DEFAULT 0.5," +
                    "auto_reload INTEGER NOT NULL DEFAULT 0," +
                    "bw INTEGER NOT NULL DEFAULT 0," +
                    "FOREIGN KEY(userID) REFERENCES users(id) ON DELETE CASCADE)";

            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
