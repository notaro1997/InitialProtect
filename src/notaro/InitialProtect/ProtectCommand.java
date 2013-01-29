package notaro.InitialProtect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProtectCommand implements CommandExecutor{
	private InitialProtect plugin;
	public ProtectCommand(InitialProtect plugin){
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if (sender instanceof Player == false){
			sender.sendMessage(ChatColor.RED + "This command can only be used in the chat!");
			return true;
		}
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("safe")){
			if(player.hasPermission("notaro.safe")){
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					ProtectFile ProtectFile = plugin.getPlayerInfo();
					ProtectFile.getPlayers().set(target.getName() + ".Safe", true);
					ProtectFile.savePlayers();
					player.sendMessage(ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.DARK_AQUA + " is now verified to be safe, and can build.");
					target.sendMessage(ChatColor.DARK_AQUA + "You are now verified safe, and can build.");
				}else{
					player.sendMessage(ChatColor.RED + "Please specify who to make safe.");
				}
			}else{
				player.sendMessage(ChatColor.RED + "You need the permission: " + ChatColor.DARK_GREEN + "notaro.safe " + ChatColor.RED + "to perform this command.");
			}
		}
		return false;
	}
}
