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
	public void eventChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		Player player = event.getPlayer();
		
		effect.SendPacket sp = new effect.SendPacket();
		YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
	    event.setCancelled(true);
	    String message = ChatColor.stripColor(event.getMessage());
	    
		switch(u.getString(player, (byte)1))
		{
		case "SKP"://SkillPoint
			if(isIntMinMax(message, player, 1, 10))
			{
				int value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_Level_Up_SkillPoint") == 1)
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_SkillPoint", value);
						sp.sendTitleAllPlayers("\'��f��ų ����Ʈ ��e"+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[�������� ��� ��ų ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.eventSkillPoint = (byte) value;
					}
				}
				else
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_SkillPoint", value);
						sp.sendTitleAllPlayers("\'��f��ų ����Ʈ ��e"+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[�������� ��� ��ų ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.eventSkillPoint = (byte) value;
					}
					else
					{
						Bukkit.broadcastMessage("��d[Server] : ��ų ����Ʈ  "+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+"�� �̺�Ʈ�� �����մϴ�.");
						sp.sendTitleAllPlayers("\'��f��ų ����Ʈ ��e"+ configYaml.getInt("Event.Multiple_Level_Up_SkillPoint")+"��f�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[�������� ��� ��ų ����Ʈ�� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_Level_Up_SkillPoint",1);
						main.Main_ServerOption.eventSkillPoint = 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().eventGuiMain(player);
				u.clearAll(player);
			}
			return;
		case "STP"://StatPoint
			if(isIntMinMax(message, player, 1, 10))
			{
				int value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_Level_Up_StatPoint") == 1)
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_StatPoint", value);
						sp.sendTitleAllPlayers("\'��f���� ����Ʈ ��e"+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[�������� ��� ���� ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.eventStatPoint = (byte) value;
					}
				}
				else
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_Level_Up_StatPoint", value);
						sp.sendTitleAllPlayers("\'��f���� ����Ʈ ��e"+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[�������� ��� ���� ����Ʈ�� �����˴ϴ�.]");
						main.Main_ServerOption.eventStatPoint = (byte) value;
					}
					else
					{
						Bukkit.broadcastMessage("��d[Server] : ���� ����Ʈ  "+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+"�� �̺�Ʈ�� �����մϴ�.");
						sp.sendTitleAllPlayers("\'��f���� ����Ʈ ��e"+ configYaml.getInt("Event.Multiple_Level_Up_StatPoint")+"��f�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[�������� ��� ���� ����Ʈ�� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_Level_Up_StatPoint",1);
						main.Main_ServerOption.eventStatPoint = 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().eventGuiMain(player);
				u.clearAll(player);
			}
			return;

		case "EXP"://EXP
			if(isIntMinMax(message, player, 1, 10))
			{
				int value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_EXP_Get") == 1)
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_EXP_Get", value);
						sp.sendTitleAllPlayers("\'��f����ġ ��e"+ configYaml.getInt("Event.Multiple_EXP_Get")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[ȹ���ϴ� ����ġ���� �����˴ϴ�.]");
						main.Main_ServerOption.eventExp= (byte) value;
					}
				}
				else
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_EXP_Get", value);
						sp.sendTitleAllPlayers("\'��f����ġ ��e"+ configYaml.getInt("Event.Multiple_EXP_Get")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[ȹ���ϴ� ����ġ���� �����˴ϴ�.]");
						main.Main_ServerOption.eventExp= (byte) value;
					}
					else
					{
						Bukkit.broadcastMessage("��d[Server] : ����ġ  "+ configYaml.getInt("Event.Multiple_EXP_Get")+"�� �̺�Ʈ�� �����մϴ�.");
						sp.sendTitleAllPlayers("\'��f����ġ ��e"+ configYaml.getInt("Event.Multiple_EXP_Get")+"��f�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[ȹ���ϴ� ����ġ���� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_EXP_Get",1);
						main.Main_ServerOption.eventExp= 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().eventGuiMain(player);
				u.clearAll(player);
			}
			return;
		case "DROP"://DropChance
			if(isIntMinMax(message, player, 1, 10))
			{
				int value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.DropChance") == 1)
				{
					if(value != 1)
					{
						configYaml.set("Event.DropChance", value);
						sp.sendTitleAllPlayers("\'��f����� ��e"+ configYaml.getInt("Event.DropChance")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[������ ������� �����˴ϴ�.]");
						main.Main_ServerOption.eventDropChance= (byte) value;
					}
				}
				else
				{
					if(value != 1)
					{
						configYaml.set("Event.DropChance", value);
						sp.sendTitleAllPlayers("\'��f����� ��e"+ configYaml.getInt("Event.DropChance")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[������ ������� �����˴ϴ�.]");
						main.Main_ServerOption.eventDropChance= (byte) value;
					}
					else
					{
						Bukkit.broadcastMessage("��d[Server] : �����  "+ configYaml.getInt("Event.DropChance")+"�� �̺�Ʈ�� �����մϴ�.");
						sp.sendTitleAllPlayers("\'��f����� ��e"+ configYaml.getInt("Event.DropChance")+"��f�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[������ ������� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.DropChance",1);
						main.Main_ServerOption.eventDropChance= 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().eventGuiMain(player);
				u.clearAll(player);
			}
			return;
		case "Proficiency"://Proficiency
			if(isIntMinMax(message, player, 1, 10))
			{
				int value = Integer.parseInt(message);
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(configYaml.getInt("Event.Multiple_Proficiency_Get") == 1)
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_Proficiency_Get", value);
						sp.sendTitleAllPlayers("\'��f���õ� ��e"+ configYaml.getInt("Event.Multiple_Proficiency_Get")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[��� ���õ� ��·��� �����˴ϴ�.]");
						main.Main_ServerOption.eventProficiency= (byte) value;
					}
				}
				else
				{
					if(value != 1)
					{
						configYaml.set("Event.Multiple_Proficiency_Get", value);
						sp.sendTitleAllPlayers("\'��f���õ� ��e"+ configYaml.getInt("Event.Multiple_Proficiency_Get")+"��f�� �̺�Ʈ�� �����մϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[��� ���õ� ��·��� �����˴ϴ�.]");
						main.Main_ServerOption.eventProficiency= (byte) value;
					}
					else
					{
						Bukkit.broadcastMessage("��d[Server] : ���õ�  "+ configYaml.getInt("Event.Multiple_Proficiency_Get")+"�� �̺�Ʈ�� �����մϴ�.");
						sp.sendTitleAllPlayers("\'��f���õ� ��e"+ configYaml.getInt("Event.Multiple_Proficiency_Get")+"��f�� �̺�Ʈ�� ����Ǿ����ϴ�.\'");
						sp.sendActionBarAllPlayers("��l��e[��� ���õ� ��·��� ������� ���ƿɴϴ�.]");
						configYaml.set("Event.Multiple_Proficiency_Get",1);
						main.Main_ServerOption.eventProficiency= 1;
					}
				}
				configYaml.saveConfig();
				new admin.Event_GUI().eventGuiMain(player);
				u.clearAll(player);
			}
			return;
		}
		return;
	}

}
