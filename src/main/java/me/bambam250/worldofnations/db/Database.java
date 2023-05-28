package me.bambam250.worldofnations.db;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String HOST;
    private final String PORT;
    private final String USER;
    private final String PASSWORD;
    private final String DATABASE_NAME;

    private Connection connection;

    /**
     * Creates the database
     * @param HOST default: localhost
     * @param PORT default: 3306
     * @param USER default: root
     * @param PASSWORD default: ""
     * @param DATABASE_NAME default: nations
     */
    public Database(String HOST, String PORT, String USER, String PASSWORD, String DATABASE_NAME) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.DATABASE_NAME = DATABASE_NAME;
    }

    public Connection getConnection() throws SQLException {

        if (connection != null) return connection;

        String url = "jdbc:mysql://" + this.HOST + "/" + DATABASE_NAME;

        connection = DriverManager.getConnection(url, this.USER, this.PASSWORD);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WoN] " + ChatColor.GRAY + "Database connected");
        return connection;
    }
}
