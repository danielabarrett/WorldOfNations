package me.bambam250.worldofnations.objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

import static me.bambam250.worldofnations.Utility.consolePrefix;

public class Nation {
    private UUID uuid;
    private String name;
    private String displayname;
    private String color;
    private String flagBase;
    private ArrayList<String> flagPatterns;
    private ItemStack flag;
    private boolean inviteOnly;
    private UUID owner;
    private ArrayList<UUID> executives;
    private ArrayList<UUID> citizens;
    private final boolean DEBUG = true;

    /**
     * Creates a nation object using a query result from the nations table in nations.db
     * **to be used in Database.java
     * @param rs ResultSet type from SQL query
     */
    public Nation(UUID uuid, ResultSet rs) {
        try {
            this.uuid = uuid;
            name            = rs.getString("name");
            displayname     = rs.getString("displayname");
            color           = rs.getString("color");
//            flagBase        = rs.getString("");
//            flagPatterns    = rs.getString("");
            inviteOnly      = rs.getBoolean("invite_only");
            owner = UUID.fromString(rs.getString("owner").replace('_', '-'));
            Bukkit.getConsoleSender().sendMessage(consolePrefix + "Nation " + ChatColor.valueOf(color) + displayname + ChatColor.RESET + "created successfully");
            executives = new ArrayList<>();
            // swap _ and - for correct uuid format ; remove the first and last characters ('[', ']') ; split by commas
            for (String entry : rs.getString("executives").replace('_', '-').substring(1, rs.getString("executives").length()-1).split(",")) {
                if (DEBUG) Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + entry);
                executives.add(UUID.fromString(entry));
            }
            citizens = new ArrayList<>();
            // swap _ and - for correct uuid format ; remove the first and last characters ('[', ']') ; split by commas
            for (String entry : rs.getString("citizens").replace('_', '-').substring(1, rs.getString("citizens").length()-1).split(",")) {
                if (DEBUG) Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + entry);
                citizens.add(UUID.fromString(entry));
            }

        } catch (SQLException ex) {
            Bukkit.getLogger().log(Level.SEVERE,"SQLite exception on nation create", ex);
        }
    }

    /**
     * Creates a nation object using individual parameters
     * **to be used in NationList.java
     * @param name snake-case name for the nation
     * @param owner player to be the owner
     */
    public Nation(String name, Player owner) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.displayname = name;
        this.color = "WHITE";
        this.flagBase = "WHITE";
        this.flagPatterns = new ArrayList<>();
        this.flag = new ItemStack(Material.WHITE_BANNER, 1);
        this.inviteOnly = true;
        this.owner = owner.getUniqueId();
        this.executives = new ArrayList<>();
        this.citizens = new ArrayList<>();
        executives.add(owner.getUniqueId());
        citizens.add(owner.getUniqueId());



    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFlagBase() {
        return flagBase;
    }

    public void setFlagBase(String flagBase) {
        this.flagBase = flagBase;
    }

    public ArrayList<String> getFlagPatterns() {
        return flagPatterns;
    }

    public void setFlagPatterns(ArrayList<String> flagPatterns) {
        this.flagPatterns = flagPatterns;
    }

    public ItemStack getFlag() {
        return flag;
    }

    public void setFlag(ItemStack flag) {
        this.flag = flag;
    }

    public boolean isInviteOnly() {
        return inviteOnly;
    }

    public void setInviteOnly(boolean inviteOnly) {
        this.inviteOnly = inviteOnly;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public ArrayList<UUID> getExecutives() {
        return executives;
    }

    public void setExecutives(ArrayList<UUID> executives) {
        this.executives = executives;
    }

    public ArrayList<UUID> getCitizens() {
        return citizens;
    }

    public void setCitizens(ArrayList<UUID> citizens) {
        this.citizens = citizens;
    }

    /**
     * Uses string values from the database to create a flag item to be used by the nation
     * @param base String color (banner colors only)
     * @param patterns CSV formatted list of banner patterns and their colors
     * @return Banner item containing
     */
    private ItemStack parseFlag(String base, ArrayList<String> patterns) {




        return null;
    }


}
