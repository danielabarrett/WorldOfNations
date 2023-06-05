package me.bambam250.worldofnations;

import me.bambam250.worldofnations.commands.NationMaster;
import me.bambam250.worldofnations.db.Database;
import me.bambam250.worldofnations.db.SQLite;
import me.bambam250.worldofnations.objects.Nation;
import me.bambam250.worldofnations.objects.NationList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private Database db;
    private NationList nationList;

    /**
     * Plugin start method
     */
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WoN] " + ChatColor.GRAY + "Starting plugin");

        saveDefaultConfig();
        this.db = new SQLite(this);
        this.db.load();

        this.nationList = new NationList(db, this);

        this.getCommand("nation").setExecutor(new NationMaster(this));

        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WON] " + ChatColor.GRAY + "Started");
        // Plugin startup logic

    }

    /**
     * Plugin shutdown method
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        db.save();
    }

    public Database getDatabase() {
        return this.db;
    }
    public NationList getNationList() {
        return this.nationList;
    }
    public ArrayList<Nation> getNations() {
        return nationList.nations;
    }

}
