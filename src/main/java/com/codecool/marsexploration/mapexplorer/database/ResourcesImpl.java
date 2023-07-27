package com.codecool.marsexploration.mapexplorer.database;

import com.codecool.marsexploration.mapexplorer.logger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class ResourcesImpl implements Resources{
    private final String dbFile;
    private final Logger logger;

    public ResourcesImpl(String dbFile, Logger logger) {
        this.dbFile = dbFile;
        this.logger = logger;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbFile;
            conn = DriverManager.getConnection(url);
            return conn;

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return conn;
    }

    @Override
    public void add( int steps, int amountOfResources, String outcome){
        String sql = "INSERT INTO ResourcesMars( steps, amountOfResources, outcome) VALUES(?,?,?)";
        try (Connection conn = getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, steps);
                pstmt.setInt(2, amountOfResources);
                pstmt.setString(3, outcome);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(String userName, String password) throws SQLException {
        String sql = "INSERT INTO Users(user_name,password) VALUES(?,?)";

        try (Connection conn = getConnection()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userName);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayAllResourcesFromDatabase() {

    }
}
