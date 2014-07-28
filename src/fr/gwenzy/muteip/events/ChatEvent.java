package fr.gwenzy.muteip.events;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

	@EventHandler
	public void onChatEvent (AsyncPlayerChatEvent event) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		Player p = event.getPlayer();
		String atraiter = p.getAddress().toString();
		String ip = atraiter.split(":")[0];
		ip = ip.replace("/", "");
		File f = new File("plugins/MuteIP/players/"+ip);
		if(f.exists())
		{
			Calendar now = Calendar.getInstance();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Calendar cible = (Calendar) ois.readObject();
			ois.close();
			if(cible.compareTo(now)!=-1)
			{
			event.setCancelled(true);
			p.sendMessage(ChatColor.RED+"Vous n'êtes pas autorisé à parler. Namého !");
			}
			else
			{
				f.delete();
			}
		}
	}
}
