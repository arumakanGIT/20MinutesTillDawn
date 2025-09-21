package com.tilldawn.Models;

import java.sql.Connection;
import java.sql.Statement;

public class UserTable {
    public static void createTable() {
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
                "score INTEGER NOT NULL DEFAULT 0," +
                "kill INTEGER NOT NULL DEFAULT 0," +
                "time INTEGER NOT NULL DEFAULT 0)";

            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
