package me.bambam250.worldofnations;

import me.bambam250.worldofnations.db.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldOfNations extends JavaPlugin {

    private Database database;

    /**
     * Plugin start method
     */
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WoN] " + ChatColor.GRAY + "Starting plugin");

        saveDefaultConfig();
        this.database = new Database(
                getConfig().getString("database.host"),
                getConfig().getString("database.port"),
                getConfig().getString("database.user"),
                getConfig().getString("database.password"),
                getConfig().getString("database.database_name"));

        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WON] " + ChatColor.GRAY + "Started");
        // Plugin startup logic

    }

    /**
     * Plugin shutdown method
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
