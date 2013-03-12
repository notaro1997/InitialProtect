package notaro.InitialProtect;

import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class InitialProtect extends JavaPlugin{

	protected ProtectFile SafePlayers;
	
	public void onEnable(){
		
		(new File(this.getDataFolder().getAbsolutePath())).mkdirs();
		this.SafePlayers = new ProtectFile(new File(this.getDataFolder().getAbsolutePath() + File.separator + "SafePlayers.txt"));
		
		this.RegisterCommands(this);
		this.registerEvents(this);
		this.SafePlayers.loadData();
	}

	public void onDisable(){

		
		this.SafePlayers.saveData();
	}

	private void RegisterCommands(InitialProtect plugin){

		getCommand("safe").setExecutor(new ProtectCommand(this));

	}

	private void registerEvents(InitialProtect instance){

		PluginManager manager = this.getServer().getPluginManager();

		manager.registerEvents(new ProtectListener(instance), this);

	}
}
