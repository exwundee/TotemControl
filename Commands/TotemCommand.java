package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class TotemCommand implements CommandExecutor{

	public static Plugin plugin = TotemControl.TotemControl.getPlugin();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (command.getName().equalsIgnoreCase("totemcontrol")) {
			if (player.isOp() != true) return true;
			else {
				if (args.length != 1) {
					player.sendMessage(ChatColor.RED + "Invalid amount of arguments.\n/tc <int>");
					return true;
				} else {
					int i = Integer.parseInt(args[0]);
					plugin.getConfig().set("interval", i); 
					plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN + "Successfully set interval to " + Integer.toString(i));
				}
			}
		}
		return false;
	}

}
