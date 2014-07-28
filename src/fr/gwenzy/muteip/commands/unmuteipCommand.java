package fr.gwenzy.muteip.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gwenzy.muteip.main.Main;

public class unmuteipCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
		if(CommandLabel.equalsIgnoreCase("unmuteip"))
		{
			if(args.length != 2)
			{
				sender.sendMessage(ChatColor.RED+"[MuteIP] Merci de respecter la syntaxe /unmuteip <Joueur/IP> <Valeur>");
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
					@SuppressWarnings("deprecation")
					Player p = Main.s.getPlayer(args[1]);
					String atraiter = p.getAddress().toString();
					ip = atraiter.split(":")[0];
					ip = ip.replace("/", "");
				}
				File f = new File("plugins/MuteIP/players/"+ip);
				if(f.exists())
				{
					f.delete();
					sender.sendMessage(ChatColor.GREEN+"[MuteIP] Le joueur/IP est autorisé à parler désormais");

				}
				else
				{
					
					sender.sendMessage(ChatColor.GREEN+"[MuteIP] Le joueur/IP n'est pas mute");
				}
			}
			
		}
		return true;
	}

}
