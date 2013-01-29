package notaro.InitialProtect;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class InitialProtect extends JavaPlugin{

	public void onEnable(){
		this.RegisterCommands(this);
		this.registerEvents(this);
		getPlayerInfo().loadPlayers();
	}

	public void onDisable(){

		getPlayerInfo().savePlayers();
	}

	private void RegisterCommands(InitialProtect plugin){

		getCommand("safe").setExecutor(new ProtectCommand(this));

	}

	private void registerEvents(InitialProtect instance){

		PluginManager manager = this.getServer().getPluginManager();

		manager.registerEvents(new ProtectListener(instance), this);

	}
	public ProtectFile getPlayerInfo(){
		return new ProtectFile(this);
	}
}
