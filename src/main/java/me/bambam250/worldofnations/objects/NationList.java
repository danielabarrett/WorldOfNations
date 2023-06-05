package me.bambam250.worldofnations.objects;

import me.bambam250.worldofnations.Main;
import me.bambam250.worldofnations.db.Database;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class NationList {
    public ArrayList<Nation> nations;
    private Database db;
    private Main plugin;

    public NationList(Database db, Main plugin) {
        this.db = db;
        this.plugin = plugin;
        this.nations = new ArrayList<>();

        ArrayList<UUID> idList = db.getNationIds();

        for (UUID uuid : idList) {
            nations.add(db.getNation(uuid));
        }
    }

    public void addNation(Nation nation) {
        nations.add(nation);
    }

    public boolean createNation(String name, Player owner) {
        for (Nation nation : nations) {
            if (nation.getName().equalsIgnoreCase(name)) return false;
        }
        nations.add(new Nation(name, owner));
        db.save();
        return true;
    }

    public boolean contains(String name) {
        for (Nation nation : nations) {
            if (nation.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public Nation getNation(UUID uuid) {
        for (Nation nation : nations) {
            if (nation.getUuid().equals(uuid)) return nation;
        }
        return null;
    }

    public Nation getNation(String name) {
        for (Nation nation : nations) {
            if (nation.getName().equalsIgnoreCase(name)) return nation;
        }
        return null;
    }


}
