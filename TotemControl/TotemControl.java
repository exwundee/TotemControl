package TotemControl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.TotemCommand;

public class TotemControl extends JavaPlugin {

	public static Plugin plugin;
	
	@Override
    public void onEnable() {
		
		System.out.println("Enabled 'TotemControl' by x1D.");
		
		plugin = this;
		registerEvents(this);
		getCommand("totemcontrol").setExecutor(new TotemCommand());
		plugin.getConfig().options().copyDefaults(true);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().broadcastMessage("tick");
				for (Player p : Bukkit.getOnlinePlayers()) {
					ItemStack[] content = p.getInventory().getContents();
					for (ItemStack item : content) {
						if ((item != null) && (item.getType() == Material.TOTEM) && (item.getAmount() != 1)) {
							item.setAmount(1);
						}
					}
				}
			}
		}, 1L, (long) plugin.getConfig().getInt("interval") * 20);
		
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	public static Plugin getPlugin() {	
		return plugin;
	}
	
}
