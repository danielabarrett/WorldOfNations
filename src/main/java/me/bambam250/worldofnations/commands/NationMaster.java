package me.bambam250.worldofnations.commands;

import me.bambam250.worldofnations.Main;
import me.bambam250.worldofnations.objects.Nation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static me.bambam250.worldofnations.Utility.consolePrefix;

public class NationMaster implements CommandExecutor {

    private Main plugin;
    public NationMaster(Main plugin) {
        super();
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (args[0].toLowerCase()) {
            case "create":
                return cmdCreate(sender, args);
            case "info":
                return cmdInfo(sender, args);
            case "list":
                return cmdList(sender, args);
            case "save":
                return cmdSave(sender, args);
            default:
                return false;
        }
    }


    /**
     * Command to create a new nation
     * @param sender Command sender
     * @param args Arguments
     * @return Successful execution
     */
    public boolean cmdCreate(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /nation create <name>");
            return false;
        } else if (!(sender instanceof Player)) {
            errorPlayerOnly(sender);
            return false;
        }
        if (plugin.getNationList().createNation(args[1], (Player) sender)) {
            sender.sendMessage(ChatColor.GREEN + "Created nation " + args[1]);
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Could not create nation " + args[1]);
        return false;
    }

    /**
     * Command to get info about a nation
     * @param sender Command sender
     * @param args Arguments
     * @return Successful execution
     */
    public boolean cmdInfo(CommandSender sender, String[] args) {
        if (plugin.getNationList().contains(args[1])) {
            Nation nation = plugin.getNationList().getNation(args[1]);
            sender.sendMessage(ChatColor.valueOf(nation.getColor()) + nation.getDisplayname());
            sender.sendMessage("Invite only: " + nation.isInviteOnly());
            sender.sendMessage("Owner: " + Bukkit.getOfflinePlayer(nation.getOwner()).getName());
            ArrayList<String> executiveNames = new ArrayList<>();
            for (UUID uuid : nation.getExecutives()) executiveNames.add(Bukkit.getOfflinePlayer(uuid).getName());
            sender.sendMessage(nation.getExecutives().size() + " executives: " +  executiveNames.toString());
            ArrayList<String> citizenNames = new ArrayList<>();
            for (UUID uuid : nation.getCitizens()) citizenNames.add(Bukkit.getOfflinePlayer(uuid).getName());
            sender.sendMessage(nation.getCitizens().size() + " citizens: " + citizenNames.toString());
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Nation not found");
        return false;
    }

    public boolean cmdList(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Nation List:");
        for (Nation nation : plugin.getNations()) {
            sender.sendMessage(ChatColor.valueOf(nation.getColor()) + nation.getDisplayname());
        }
        return true;
    }

    public boolean cmdSave(CommandSender sender, String[] args) {
        plugin.getDatabase().save();
        sender.sendMessage(ChatColor.GREEN + "Database saved");
        return true;
    }

    private void errorPerm(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use that command!");
    }

    private void errorPlayerOnly(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Only a player can use this command!");
    }

    private void errorArgs(CommandSender sender, int numArgs) {

    }
}
