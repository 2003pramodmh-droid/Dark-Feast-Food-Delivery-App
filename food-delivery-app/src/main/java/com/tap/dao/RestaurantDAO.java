package com.tap.dao;

import com.tap.model.Restaurant;
import com.tap.model.MenuItem;
import com.tap.Util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RestaurantDAO - Data Access Object for Restaurant operations
 */
public class RestaurantDAO {

    /**
     * Get all restaurants
     * 
     * @return List of all restaurants
     */
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurants ORDER BY rating DESC";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Restaurant restaurant = new Restaurant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("rating"),
                        rs.getString("description"));
                list.add(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Get restaurant by ID
     * 
     * @param id Restaurant ID
     * @return Restaurant object or null
     */
    public Restaurant getRestaurantById(int id) {
        String sql = "SELECT * FROM restaurants WHERE id=?";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Restaurant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("rating"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all menu items for a specific restaurant
     * 
     * @param restaurantId Restaurant ID
     * @return List of menu items
     */
    public List<MenuItem> getMenuByRestaurantId(int restaurantId) {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE restaurant_id=? ORDER BY rating DESC";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuItem item = new MenuItem(
                        rs.getInt("id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("rating"),
                        rs.getString("description"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Get menu item by ID
     * 
     * @param itemId Menu item ID
     * @return MenuItem object or null
     */
    public MenuItem getMenuItemById(int itemId) {
        String sql = "SELECT * FROM menu_items WHERE id=?";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new MenuItem(
                        rs.getInt("id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getDouble("rating"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
