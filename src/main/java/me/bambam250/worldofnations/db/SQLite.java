package me.bambam250.worldofnations.db;

import me.bambam250.worldofnations.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

//import com.CraftedPlugins.cyberkm.Database.Database; // import the database class.
//import com.CraftedPlugins.cyberkm.Main.Main; // import your main class


public class SQLite extends Database{
    String dbname;
    public SQLite(Main instance){
        super(instance);
        dbname = plugin.getConfig().getString("Databases.Nations", "nations"); // Set the table name here e.g. player_kills

    }

    public String SQLiteCreateNationsTable = "CREATE TABLE IF NOT EXISTS nations (" + // make sure to put your table name in here too.
            "'uuid' varchar(32) NOT NULL," +
            "`name` varchar(32) NOT NULL," + // This creates the different columns you will save data too. varchar(32) Is a string, int = integer
            "'displayname' varchar(32) NOT NULL," +
            "`color` varchar(32) NOT NULL," +
            "'flag_base' varchar(32) NOT NULL," +
            "'flag_patterns' text," +
            "`invite_only` bool NOT NULL," +
            "'owner' varchar(32) NOT NULL," +
            "'executives' TEXT," +
            "'citizens' TEXT," +
            "PRIMARY KEY (`uuid`)" +  // This is creating 3 columns Player, Kills, Total. Primary key is what you are going to use as your indexer. Here we want to use player so
            ");"; // we can search by player, and get kills and total. If you somehow were searching kills it would provide total and player.
    public String SQLiteCreateCitiesTable = "CREATE TABLE IF NOT EXISTS cities (" +
            "'uuid' varchar(32) NOT NULL," +
            "'name' varchar(32) NOT NULL," +
            "PRIMARY KEY ('uuid')" +
            ");";
    public String SQLiteCreatePlayersTable = "CREATE TABLE IF NOT EXISTS players (" +
            "'uuid' varchar(32) NOT NULL," +
            "'username' varchar(32) NOT NULL," +
            "PRIMARY KEY ('uuid')" +
            ");";


    // SQL creation stuff, You can leave the below stuff untouched.
    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname +".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+ dbname +".db");
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateNationsTable);
            s.executeUpdate(SQLiteCreateCitiesTable);
            s.executeUpdate(SQLiteCreatePlayersTable);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "checkpoint");
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}