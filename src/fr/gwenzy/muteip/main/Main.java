package fr.gwenzy.muteip.main;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gwenzy.muteip.commands.muteipCommand;
import fr.gwenzy.muteip.commands.unmuteipCommand;
import fr.gwenzy.muteip.events.ChatEvent;

public class Main extends JavaPlugin
{
	public static Server s  = Bukkit.getServer();
	public static Logger log = s.getLogger();
	@Override
	public void onEnable()
	{
		log.info("MuteIP Enabled");
		//Check des dossier
		File f1 = new File("plugins/MuteIP");
		File f2 = new File("plugins/MuteIP/players/");
		if(!f1.exists()){f1.mkdir();}
		if(!f2.exists()){f2.mkdir();}
		
		getCommand("muteip").setExecutor(new muteipCommand());
		getCommand("unmuteip").setExecutor(new unmuteipCommand());
	
		s.getPluginManager().registerEvents(new ChatEvent(), this);
	}
	
	@Override
	public void onDisable()
	{
		log.info("MuteIP Disabled");
	}
}
