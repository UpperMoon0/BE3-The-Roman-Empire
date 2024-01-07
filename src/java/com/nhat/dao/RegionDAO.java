package com.nhat.dao;

import com.nhat.util.DatabaseUtil;
import model.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {

    /**
     * Retrieves all regions from the Region table and returns a list of Region objects.
     *
     * @return List of Region objects
     */
    public List<Region> getAllRegions() {
        List<Region> regions = new ArrayList<>();

        // Get a connection to the database
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Region");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and create Region objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                long marketValue = resultSet.getLong("market_value");
                short growth = resultSet.getShort("growth");
                short revoltRate = resultSet.getShort("revolt_rate");
                int foodSupply = resultSet.getInt("food_supply");

                // Create a new Region object and add it to the list
                Region region = new Region(id, name, marketValue, growth, revoltRate, foodSupply);
                regions.add(region);
            }

        } catch (SQLException e) {
            // If any exception occurs, print the stack trace
            e.printStackTrace();
        }

        // Return the list of regions
        return regions;
    }
    
    /**
    * Retrieves a region from the Region table based on the given ID.
    *
    * @param regionId the ID of the region to retrieve
    * @return the Region object if found, or null if not found
    */
   public Region getRegionById(int regionId) {
       // Initialize the region to null
       Region region = null;

       // Get a connection to the database
       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Region WHERE id = ?")) {

           // Set the ID parameter in the prepared statement
           preparedStatement.setInt(1, regionId);

           // Execute the query
           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               // Check if a region was found
               if (resultSet.next()) {
                   // Retrieve data from the result set
                   String name = resultSet.getString("name");
                   long marketValue = resultSet.getLong("market_value");
                   short growth = resultSet.getShort("growth");
                   short revoltRate = resultSet.getShort("revolt_rate");
                   int foodSupply = resultSet.getInt("food_supply");

                   // Create a new Region object
                   region = new Region(regionId, name, marketValue, growth, revoltRate, foodSupply);
               }
           }

       } catch (SQLException e) {
           // If any exception occurs, print the stack trace
           e.printStackTrace();
       }

       // Return the region (null if not found)
       return region;
   }
}
