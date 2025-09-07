package com.tilldawn.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static Result register(String username, String password, int securityQuestionID, String answer) {
        if (username.isEmpty() || password.isEmpty() || answer.isEmpty())
            return new Result(false, "Please fill all the fields");
        String sql = "INSERT INTO users(username, password, salt, securityQuestionID, answer) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String salt = SHA_256.generateSalt();
            String passwordHash = SHA_256.hashPassword(password, salt);
            String answerHash = SHA_256.hashPassword(answer, salt);

            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, salt);
            pstmt.setInt(4, securityQuestionID);
            pstmt.setString(5, answerHash);
            pstmt.executeUpdate();

            return new Result(true, "User registered successfully");
        } catch (Exception e) {
            System.out.println("User already exists or DB error");
            return new Result(false, "User already exists or DB error");
        }
    }

    public static Result login(String username, String password) {
        String sql = "SELECT password, salt FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String salt = rs.getString("salt");

                String hash = SHA_256.hashPassword(password, salt);
                return new Result(hash.equals(storedHash), "");
            }
            return new Result(false, "username not found!");
        } catch (Exception e) {
            System.out.println("Error while trying to login" + e.getMessage());
            return new Result(false, "Error while trying to login");
        }
    }

    public static Result userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            return new Result(rs.next(), "");
        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return new Result(false, "Error checking user existence");
        }
    }
}
