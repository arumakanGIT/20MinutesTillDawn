package com.tilldawn.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static boolean register(String username, String password) {
        String sql = "INSERT INTO users(username, password, salt) VALUES(?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String salt = SHA_256.generateSalt();
            String passwordHash = SHA_256.hashPassword(password, salt);

            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, salt);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("User already exists or DB error");
            return false;
        }
    }

    public static boolean login(String username, String password) {
        String sql = "SELECT password, salt FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String salt = rs.getString("salt");

                String hash = SHA_256.hashPassword(password, salt);
                return (hash.equals(storedHash));
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error while trying to login" + e.getMessage());
            return false;
        }
    }

    public static boolean userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }
}
