package me.bambam250.worldofnations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldOfNations extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WoN] " + ChatColor.GRAY + "Starting plugin");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[WON] " + ChatColor.GRAY + "Started");
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
