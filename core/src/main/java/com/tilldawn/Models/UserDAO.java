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

    public static Result changePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String newHash = SHA_256.hashPassword(newPassword, UserDAO.getStringField(username, "salt"));

            pstmt.setString(1, newHash);
            pstmt.setString(2, username);

            int rows = pstmt.executeUpdate();
            if (rows > 0) return new Result(true, "Password changed successfully");
            else return new Result(false, "User not found");

        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return new Result(false, "Database error: " + e.getMessage());
        }
    }

    public static int getSecurityQuestionID(String username) {
        String sql = "SELECT securityQuestionID FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("securityQuestionID");
            } else return -1;
        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
        return -1;
    }

    public static void saveRememberToken(String username, String token) {
        String sql = "UPDATE users SET rememberToken = ? WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
    }

    public static String getUserByToken(String token) {
        String sql = "SELECT username FROM users WHERE rememberToken = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
        return null;
    }

    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("salt"),
                    rs.getInt("securityQuestionID"),
                    rs.getString("answer"),
                    rs.getString("avatar"),
                    rs.getString("weapon"),
                    rs.getInt("kill"),
                    rs.getInt("time"),
                    rs.getInt("score")
                );
            }
        } catch (Exception e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    public static String getStringField(String username, String field) {
        String sql = "SELECT " + field + " FROM users WHERE username = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(field);
            } else return null;
        } catch (Exception e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
        return null;
    }
}
