package admin;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserData_Object;
import util.YamlLoader;



public class Gamble_Chat
{
	public void GambleChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		Player player = event.getPlayer();

	  	YamlLoader gambleYML = new YamlLoader();
	  	gambleYML.getConfig("Item/GamblePresent.yml");

	    
	    event.setCancelled(true);
	    String message = ChatColor.stripColor(event.getMessage().replace(".",""));
		switch(u.getString(player, (byte)0))
		{
		case "NP"://New Package
			{
				if(gambleYML.contains(message))
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[����] : �ش� �̸��� ��ǰ�� �̹� �����մϴ�!");
					return;
				}
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				gambleYML.set(message+".Grade", ChatColor.WHITE+"[�Ϲ�]");
				gambleYML.createSection(message+".Present");
				gambleYML.saveConfig();
				u.clearAll(player);
				new admin.Gamble_GUI().GamblePresentGUI(player, (short)0, (byte)0, (short)-1, null);
			}
			return;
		}
	}

}
