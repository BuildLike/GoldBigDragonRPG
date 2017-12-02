package event;

import java.util.Calendar;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserDataObject;
import util.UtilChat;
import util.YamlLoader;



public class EventPlayerChat extends UtilChat implements Listener
{
	@EventHandler
	public void PlayerChatting(PlayerChatEvent event)
	{
		event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		UserDataObject u = new UserDataObject();
	    Player player = event.getPlayer();
	    String playerUUID = event.getPlayer().getUniqueId().toString();
	    if(u.getTemp(player) != null)
	    {
	    	TEMProuter(event, u.getTemp(player));
	    	return;
	    }
	    if(player.isOp()==true)
		    if(u.getType(player) != null)
			    if(u.getType(player).equals("Quest"))
		    	{new quest.QuestChat().QuestTypeChatting(event); return;}
			    else if(u.getType(player).equals("WorldCreater"))
		    	{new admin.WorldCreateChat().worldCreaterTypeChatting(event); return;}
			    else if(u.getType(player).equals("UseableItem")
			    		||u.getType(player).equals("Upgrade")
			    		||u.getType(player).equals("Item"))
	    		{new customitem.CustomItemChat().itemTypeChatting(event); return;}
			    else if(u.getType(player).equals("Area"))
		    	{new area.AreaChat().areaTypeChatting(event); return;}
			    else if(u.getType(player).equals("NPC"))
		    	{new npc.NpcChat().NPCTypeChatting(event); return;}
			    else if(u.getType(player).equals("NewBie"))
		    	{new admin.NewBieChat().newBieTypeChatting(event); return;}
			    else if(u.getType(player).equals("Skill"))
		    	{new skill.SkillChat().SkillTypeChatting(event); return;}
			    else if(u.getType(player).equals("Job"))
		    	{new job.JobChat().JobTypeChatting(event); return;}
			    else if(u.getType(player).equals("Monster"))
		    	{new monster.MonsterChat().MonsterTypeChatting(event); return;}
			    else if(u.getType(player).equals("Teleport"))
		    	{new warp.WarpChat().TeleportTypeChatting(event);return;}
			    else if(u.getType(player).equals("Event"))
		    	{new admin.EventChat().eventChatting(event); return;}
			    else if(u.getType(player).equals("System"))
		    	{new admin.OPboxChat().systemTypeChatting(event); return;}
			    else if(u.getType(player).equals("Navi"))
		    	{new admin.NavigationChat().naviTypeChatting(event); return;}
			    else if(u.getType(player).equals("Gamble"))
		    	{new admin.GambleChat().gambleChatting(event); return;}
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
	  	String Prefix = "";
	  	if(configYaml.contains("Server.ChatPrefix"))
	  	{
		  	Calendar C = Calendar.getInstance();
	  		Prefix = configYaml.getString("Server.ChatPrefix");
			Prefix=Prefix.replace("%t%",C.get(Calendar.HOUR_OF_DAY)+"��"+C.get(Calendar.MINUTE)+"��");
			if(player.getGameMode()==GameMode.SURVIVAL)
				Prefix=Prefix.replace("%gm%","�����̹�");
			else if(player.getGameMode()==GameMode.ADVENTURE)
				Prefix=Prefix.replace("%gm%","������");
			else if(player.getGameMode()==GameMode.CREATIVE)
				Prefix=Prefix.replace("%gm%","ũ������Ƽ��");
			else if(player.getGameMode()==GameMode.SPECTATOR)
				Prefix=Prefix.replace("%gm%","����");
			
			if(main.MainServerOption.PlayerList.get(playerUUID).getOption_ChattingType()==0)
				Prefix=Prefix.replace("%ct%","�Ϲ�");
			else if(main.MainServerOption.PlayerList.get(playerUUID).getOption_ChattingType()==1)
				Prefix=Prefix.replace("%ct%","��Ƽ");
			else if(main.MainServerOption.PlayerList.get(playerUUID).getOption_ChattingType()==3)
				Prefix=Prefix.replace("%ct%","������");
			Prefix=Prefix.replace("%lv%",main.MainServerOption.PlayerList.get(playerUUID).getStat_Level()+"");
			Prefix=Prefix.replace("%rlv%",main.MainServerOption.PlayerList.get(playerUUID).getStat_RealLevel()+"");
		  	YamlLoader playerSkillYaml = new YamlLoader();
		  	playerSkillYaml.getConfig("Skill/PlayerData/" + player.getUniqueId()+".yml");
			Prefix=Prefix.replace("%job%",playerSkillYaml.getString("Job.Type"));
			Prefix=Prefix.replace("%player%",player.getName());
			Prefix=Prefix.replace("%message%",event.getMessage());
  			event.setCancelled(true);
		  	switch(main.MainServerOption.PlayerList.get(playerUUID).getOption_ChattingType())
		  	{
		  	case 0:
		  		Bukkit.broadcastMessage(Prefix);
		  		return;
		  	case 1: 
		  		if(main.MainServerOption.partyJoiner.containsKey(player) == false)
		  		{
		  			player.sendMessage("��9[��Ƽ] : ��Ƽ�� ���ԵǾ� ���� �ʽ��ϴ�!");
		  			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  		}
		  		else
		  		{
		  			main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).PartyBroadCastMessage("��9[��Ƽ] "+Prefix,null);
			  		Bukkit.getConsoleSender().sendMessage("[��Ƽ] "+Prefix);
		  		}
	  			return;
		  	case 2:
	  			event.setCancelled(true);
		  		return;
		  	case 3:
	  			event.setCancelled(true);
	  			if(player.isOp() == false)
	  			{
		  			player.sendMessage("��d[������] : ����� �����ڰ� �ƴմϴ�!");
		  			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  			}
	  			else
	  			{
	  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
	  		    	Player[] a = new Player[playerlist.size()];
	  		    	playerlist.toArray(a);
	  	  			for(int count = 0; count < a.length;count++)
	  	  			{
	  	  		    	if(a[count].isOp())
	  	  		    		a[count].sendMessage("��d[������] "+Prefix);
	  	  		    }
	  		  		Bukkit.getConsoleSender().sendMessage("[������] "+Prefix);
	  			}
	  			return;
		  	}
	  	}
	  	else
	  	{
		  	switch(main.MainServerOption.PlayerList.get(playerUUID).getOption_ChattingType())
		  	{
		  	case 0:
		  		return;
		  	case 1: 
	  			event.setCancelled(true);
		  		if(main.MainServerOption.partyJoiner.containsKey(player) == false)
		  		{
		  			player.sendMessage("��9[��Ƽ] : ��Ƽ�� ���ԵǾ� ���� �ʽ��ϴ�!");
		  			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  		}
		  		else
		  		{
		  			main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).PartyBroadCastMessage("��9[��Ƽ] "+player.getName()+" : " + event.getMessage(),null);
			  		Bukkit.getConsoleSender().sendMessage("[��Ƽ] "+player.getName()+" : " + event.getMessage());
		  		}
	  			return;
		  	case 2:
	  			event.setCancelled(true);
		  		return;
		  	case 3:
	  			event.setCancelled(true);
	  			if(player.isOp() == false)
	  			{
		  			player.sendMessage("��d[������] : ����� �����ڰ� �ƴմϴ�!");
		  			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  			}
	  			else
	  			{
	  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
	  		    	Player[] a = new Player[playerlist.size()];
	  		    	playerlist.toArray(a);
	  	  			for(int count = 0; count<a.length;count++)
	  	  			{
	  	  		    	if(a[count].isOnline() == true)
	  	  		    	{
	  	  		    		Player send = (Player) Bukkit.getOfflinePlayer(((Player)a[count]).getName());
	  	  		    		send.sendMessage("��d[������] "+player.getName()+" : " + event.getMessage());
	  	  		    	}	
	  	  		    }
	  		  		Bukkit.getConsoleSender().sendMessage("[������] "+player.getName()+" : " + event.getMessage());
	  			}
	  			return;
		  	}
	  	}
	}

	public void TEMProuter(PlayerChatEvent event, String Temp)
	{
		event.setCancelled(true);
		Player player = event.getPlayer();
		
		String Message = ChatColor.stripColor(event.getMessage());
		if(Temp.equals("FA"))
		{
			if(Message.equals(player.getName()))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[ģ��] : �ڱ� �ڽ��� �߰��� �� �����ϴ�!");
			}
			else
			{
				Message.replace(".", "");
				if(Bukkit.getServer().getPlayer(Message) != null)
					new user.EquipGui().SetFriends(player, Bukkit.getServer().getPlayer(Message));
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c[ģ��] : �ش� �÷��̾ ã�� �� �����ϴ�!");
				}
			}
			new user.EtcGui().FriendsGUI(player, (short) 0);
			new UserDataObject().initTemp(player);
		}
		else if(Temp.equals("Structure"))
			new structure.StructureChat().PlayerChatrouter(event);
		else if(Temp.equals("Dungeon"))
			new dungeon.DungeonChat().PlayerChatrouter(event);
		return;
	}
	
}