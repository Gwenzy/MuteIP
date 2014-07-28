package fr.gwenzy.muteip.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gwenzy.muteip.main.Main;

public class muteipCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
		if(CommandLabel.equalsIgnoreCase("muteip"))
		{
			if(args.length < 2)
			{
				sender.sendMessage(ChatColor.RED+"[MuteIP] Merci de respecter la syntaxe /muteip <Joueur/IP> <Valeur> [Time]");
			}
			else
			{
				
				String pToMute = args[1];
				String ip = "";
				if(args[0].equalsIgnoreCase("IP"))
				{
					ip = pToMute;
				}
				else
				{
					Player p = Main.s.getPlayer(args[1]);
					String atraiter = p.getAddress().toString();
					ip = atraiter.split(":")[0];
					ip = ip.replace("/", "");
				}
				File f = new File("plugins/MuteIP/players/"+ip);
				if(f.exists())
				{
					sender.sendMessage(ChatColor.RED+"[MuteIP] Le joueur/IP est déjà mute");
				}
				else
				{
					
					Calendar time = Calendar.getInstance();
					for(int i=2; i<args.length; i++)
					{
						
						
						if(args[i].endsWith("s"))
						{
							int nb = Integer.valueOf(args[i].replaceAll("s", ""));
							time.add(Calendar.SECOND, nb);
						}
						if(args[i].endsWith("mi"))
						{
							int nb = Integer.valueOf(args[i].replaceAll("mi", ""));
							time.add(Calendar.MINUTE, nb);
						}
						if(args[i].endsWith("h"))
						{
							int nb = Integer.valueOf(args[i].replaceAll("h", ""));
							time.add(Calendar.HOUR, nb);
						}
						if(args[i].endsWith("d"))
						{
							int nb = Integer.valueOf(args[i].replaceAll("d", ""));
							time.add(Calendar.DAY_OF_YEAR, nb);
						}
						if(args[i].endsWith("mo"))
						{
							int nb = Integer.valueOf(args[i].replaceAll("mo", ""));
							time.add(Calendar.MONTH, nb);
						}
						if(args[i].endsWith("y"))
						{
							int nb = Integer.valueOf(args[i].replaceAll("y", ""));
							time.add(Calendar.YEAR, nb);
						}
					}
					
					try {
						f.createNewFile();
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
						oos.writeObject(time);
						oos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					sender.sendMessage(ChatColor.GREEN+"[MuteIP] Joueur/IP mute.");
					
				}
			}
			
		}
		return true;
	}

}
