package notaro.InitialProtect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtectFile {

	public static InitialProtect plugin;
	public ProtectFile(InitialProtect instance){
		plugin = instance;
	}

	private FileConfiguration players = null;
	private File SafePlayersFile = null;

	public void loadPlayers() {
		this.getPlayers().options().copyDefaults(true);
		savePlayers();
	}

	public void reloadPlayers(	) {
		if (SafePlayersFile == null) {
			SafePlayersFile = new File(plugin.getDataFolder(), "SafePlayers.yml");
		}
		players = YamlConfiguration.loadConfiguration(SafePlayersFile);

		InputStream Config = plugin.getResource("SafePlayers.yml");
		if (Config != null) {
			YamlConfiguration YamlConfig = YamlConfiguration.loadConfiguration(Config);
			players.setDefaults(YamlConfig);
		}
	}

	public FileConfiguration getPlayers() {
		if (players == null) {
			reloadPlayers();
		}
		return players;
	}

	public void savePlayers() {
		if (players == null || SafePlayersFile == null) {
			return;
		}
		try {
			players.save(SafePlayersFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,"Error saving players to " + SafePlayersFile, ex);
			ex.printStackTrace();
		}
	}
}
