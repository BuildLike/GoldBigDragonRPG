package admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_Chat;
import util.YamlLoader;

public class Event_Chat extends Util_Chat
{
	public void EventChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		Player player = event.getPlayer();
		
		effect.SendPacket PS = new effect.SendPacket();
		YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
	    event.setCancelled(true);
	    String message = ChatColor.stripColor(event.getMessage());
	    
		switch(u.getString(player, (byte)1))
		{
		case "SKP"://SkillPoint
			if(isIntMinMax(message, player, 1, 10))
			{
				int Value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_Level_Up_SkillPoint") == 1)
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_SkillPoint", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "��ų ����Ʈ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[�������� ��� ��ų ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.Event_SkillPoint = (byte) Value;
					}
				}
				else
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_SkillPoint", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "��ų ����Ʈ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[�������� ��� ��ų ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.Event_SkillPoint = (byte) Value;
					}
					else
					{
						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"[Server] : ��ų ����Ʈ  "+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+"�� �̺�Ʈ�� �����մϴ�.");
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "��ų ����Ʈ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+ChatColor.WHITE +"�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[�������� ��� ��ų ����Ʈ�� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_Level_Up_SkillPoint",1);
						main.Main_ServerOption.Event_SkillPoint = 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().EventGUI_Main(player);
				u.clearAll(player);
			}
			return;
		case "STP"://StatPoint
			if(isIntMinMax(message, player, 1, 10))
			{
				int Value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_Level_Up_StatPoint") == 1)
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_StatPoint", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "���� ����Ʈ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[�������� ��� ���� ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.Event_StatPoint = (byte) Value;
					}
				}
				else
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_StatPoint", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "���� ����Ʈ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[�������� ��� ���� ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.Event_StatPoint = (byte) Value;
					}
					else
					{
						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"[Server] : ���� ����Ʈ  "+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+"�� �̺�Ʈ�� �����մϴ�.");
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "���� ����Ʈ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+ChatColor.WHITE +"�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[�������� ��� ���� ����Ʈ�� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_Level_Up_StatPoint",1);
						main.Main_ServerOption.Event_StatPoint = 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().EventGUI_Main(player);
				u.clearAll(player);
			}
			return;

		case "EXP"://EXP
			if(isIntMinMax(message, player, 1, 10))
			{
				int Value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_EXP_Get") == 1)
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_EXP_Get", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "����ġ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_EXP_Get")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[ȹ���ϴ� ����ġ���� �����˴ϴ�.]");
						main.Main_ServerOption.Event_Exp= (byte) Value;
					}
				}
				else
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_EXP_Get", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "����ġ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_EXP_Get")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[ȹ���ϴ� ����ġ���� �����˴ϴ�.]");
						main.Main_ServerOption.Event_Exp= (byte) Value;
					}
					else
					{
						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"[Server] : ����ġ  "+ configYaml.getInt("Event.Multiple_EXP_Get")+"�� �̺�Ʈ�� �����մϴ�.");
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "����ġ "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_EXP_Get")+ChatColor.WHITE +"�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[ȹ���ϴ� ����ġ���� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_EXP_Get",1);
						main.Main_ServerOption.Event_Exp= 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().EventGUI_Main(player);
				u.clearAll(player);
			}
			return;
		case "DROP"://DropChance
			if(isIntMinMax(message, player, 1, 10))
			{
				int Value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.DropChance") == 1)
				{
					if(Value != 1)
					{
						configYaml.set("Event.DropChance", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "����� "+ChatColor.YELLOW +""+ configYaml.getInt("Event.DropChance")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[������ ������� �����˴ϴ�.]");
						main.Main_ServerOption.Event_DropChance= (byte) Value;
					}
				}
				else
				{
					if(Value != 1)
					{
						configYaml.set("Event.DropChance", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "����� "+ChatColor.YELLOW +""+ configYaml.getInt("Event.DropChance")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[������ ������� �����˴ϴ�.]");
						main.Main_ServerOption.Event_DropChance= (byte) Value;
					}
					else
					{
						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"[Server] : �����  "+ configYaml.getInt("Event.DropChance")+"�� �̺�Ʈ�� �����մϴ�.");
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "����� "+ChatColor.YELLOW +""+ configYaml.getInt("Event.DropChance")+ChatColor.WHITE +"�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[������ ������� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.DropChance",1);
						main.Main_ServerOption.Event_DropChance= 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().EventGUI_Main(player);
				u.clearAll(player);
			}
			return;
		case "Proficiency"://Proficiency
			if(isIntMinMax(message, player, 1, 10))
			{
				int Value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_Proficiency_Get") == 1)
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_Proficiency_Get", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "���õ� "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Proficiency_Get")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[��� ���õ� ��·��� �����˴ϴ�.]");
						main.Main_ServerOption.Event_Proficiency= (byte) Value;
					}
				}
				else
				{
					if(Value != 1)
					{
						configYaml.set("Event.Multiple_Proficiency_Get", Value);
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "���õ� "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Proficiency_Get")+ChatColor.WHITE +"�� �̺�Ʈ�� �����մϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[��� ���õ� ��·��� �����˴ϴ�.]");
						main.Main_ServerOption.Event_Proficiency= (byte) Value;
					}
					else
					{
						Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE+"[Server] : ���õ�  "+ configYaml.getInt("Event.Multiple_Proficiency_Get")+"�� �̺�Ʈ�� �����մϴ�.");
						PS.sendTitleAllPlayers("\'"+ChatColor.WHITE + "���õ� "+ChatColor.YELLOW +""+ configYaml.getInt("Event.Multiple_Proficiency_Get")+ChatColor.WHITE +"�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						PS.sendActionBarAllPlayers(ChatColor.BOLD+""+ChatColor.YELLOW +"[��� ���õ� ��·��� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_Proficiency_Get",1);
						main.Main_ServerOption.Event_Proficiency= 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().EventGUI_Main(player);
				u.clearAll(player);
			}
			return;
		}
		return;
	}

}
