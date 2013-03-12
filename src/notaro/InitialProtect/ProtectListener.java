package notaro.InitialProtect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ProtectListener implements Listener{

	private InitialProtect plugin;
	public ProtectListener(InitialProtect plugin){
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBreakEvent(BlockBreakEvent event){
		ProtectFile ProtectFile = plugin.SafePlayers;
		Player player = event.getPlayer();
		String name = player.getName();
		if(!ProtectFile.contains(name)){
			event.setCancelled(true);
			player.setFireTicks(40);
			player.sendMessage(ChatColor.RED + "You cant break blocks until you have been verified safe by an admin!");
		}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlaceEvent(BlockPlaceEvent event){
		ProtectFile ProtectFile = plugin.SafePlayers;
		Player player = event.getPlayer();
		String name = player.getName();
		if(!ProtectFile.contains(name)){
			event.setCancelled(true);
			player.setFireTicks(40);
			player.sendMessage(ChatColor.RED + "You cant place blocks until you have been verified safe by an admin!");
		}
	}
}
