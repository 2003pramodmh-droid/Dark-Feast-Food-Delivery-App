package com.tap.dao;
import com.tap.Util.*;
import com.tap.model.User;
import java.sql.*;

/**
 * UserDAO - Data Access Object for User operations
 * Handles all database operations related to users
 */
public class UserDAO {

    /**
     * Register a new user
     * 
     * @param user User object containing registration details
     * @return true if registration successful, false otherwise
     */
    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password, email, address) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // In production, hash this!
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Authenticate user login
     * 
     * @param username Username
     * @param password Password
     * @return User object if login successful, null otherwise
     */
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check if username already exists
     * 
     * @param username Username to check
     * @return true if exists, false otherwise
     */
    public boolean usernameExists(String username) {
        String sql = "SELECT id FROM users WHERE username=?";
        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
