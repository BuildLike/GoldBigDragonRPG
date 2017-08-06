package admin;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_Chat;
import util.YamlLoader;



public class OPbox_Chat extends Util_Chat
{

	public void SystemTypeChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		Player player = event.getPlayer();

	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
		
	    
	    event.setCancelled(true);
	    String message = ChatColor.stripColor(event.getMessage());
		switch(u.getString(player, (byte)1))
		{
		case "RO_S_H"://RespawnOption_SpawnPoint_Health
		case "RO_T_H"://RespawnOption_There_Health
		case "RO_H_H"://RespawnOption_Help_Health
		case "RO_I_H"://RespawnOption_Item_Health
			if(isIntMinMax(message, player, 1, 100))
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				switch(u.getString(player, (byte)1))
				{
					case "RO_S_H":
						configYaml.set("Death.Spawn_Home.SetHealth", message+"%");
						u.setString(player, (byte)1, "RO_S_E");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : ������ �������� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"����ġ"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
					case "RO_T_H":
						configYaml.set("Death.Spawn_Here.SetHealth", message+"%");
						u.setString(player, (byte)1, "RO_T_E");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : ���ڸ����� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"����ġ"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
					case "RO_H_H":
						configYaml.set("Death.Spawn_Help.SetHealth", message+"%");
						u.setString(player, (byte)1, "RO_H_E");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : ������ �޾� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"����ġ"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
					case "RO_I_H":
						configYaml.set("Death.Spawn_Item.SetHealth", message+"%");
						u.setString(player, (byte)1, "RO_I_E");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : �������� ����Ͽ� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"����ġ"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
				}
				configYaml.saveConfig();
			}
			return;
		case "RO_S_E"://RespawnOption_SpawnPoint_EXP
		case "RO_T_E"://RespawnOption_There_EXP
		case "RO_H_E"://RespawnOption_Help_EXP
		case "RO_I_E"://RespawnOption_Item_EXP
			if(isIntMinMax(message, player, 0, 100))
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				switch(u.getString(player, (byte)1))
				{
					case "RO_S_E":
						configYaml.set("Death.Spawn_Home.PenaltyEXP", message+"%");
						u.setString(player, (byte)1, "RO_S_M");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : ������ �������� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"��"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
					case "RO_T_E":
						configYaml.set("Death.Spawn_Here.PenaltyEXP", message+"%");
						u.setString(player, (byte)1, "RO_T_M");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : ���ڸ����� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"��"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
					case "RO_H_E":
						configYaml.set("Death.Spawn_Help.PenaltyEXP", message+"%");
						u.setString(player, (byte)1, "RO_H_M");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : ������ �޾� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"��"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
					case "RO_I_E":
						configYaml.set("Death.Spawn_Item.PenaltyEXP", message+"%");
						u.setString(player, (byte)1, "RO_I_M");
						player.sendMessage(ChatColor.GREEN+"[��Ȱ] : �������� ����Ͽ� ��Ȱ�� ���, �� %�� "+ChatColor.YELLOW+"��"+ChatColor.GREEN+"�� �Ҿ�������� �ϰڽ��ϱ�?");
						player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
						break;
				}
				configYaml.saveConfig();
			}
			return;
		case "RO_S_M"://RespawnOption_SpawnPoint_Money
		case "RO_T_M"://RespawnOption_There_Money
		case "RO_H_M"://RespawnOption_Help_Money
		case "RO_I_M"://RespawnOption_Item_Money
			if(isIntMinMax(message, player, 0, 100))
			{
				SoundEffect.SP(player, Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
				switch(u.getString(player, (byte)1))
				{
					case "RO_S_M":
						configYaml.set("Death.Spawn_Home.PenaltyMoney", message+"%");
						break;
					case "RO_T_M":
						configYaml.set("Death.Spawn_Here.PenaltyMoney", message+"%");
						break;
					case "RO_H_M":
						configYaml.set("Death.Spawn_Help.PenaltyMoney", message+"%");
						break;
					case "RO_I_M":
						configYaml.set("Death.Spawn_Item.PenaltyMoney", message+"%");
						break;
				}
				configYaml.saveConfig();
				u.clearAll(player);
				new admin.OPbox_GUI().OPBoxGUI_Death(player);
			}
			return;
		case "CCP"://ChangeChatPrefix
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			configYaml.set("Server.ChatPrefix", event.getMessage());
			configYaml.saveConfig();
			u.clearAll(player);
			new admin.OPbox_GUI().OPBoxGUI_Setting(player);
			return;
		case "BMT"://BroadcastMessageTick
			if(isIntMinMax(message, player, 1, 3600))
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				configYaml.set("Server.BroadCastSecond", Integer.parseInt(message));
				configYaml.saveConfig();
				new admin.OPbox_GUI().OPBoxGUI_BroadCast(player, (byte) 0);
				u.clearAll(player);
			}
			return;
		case "NBM"://NewBroadcastMessage
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			YamlLoader broadcastYaml = new YamlLoader();
			broadcastYaml.getConfig("BroadCast.yml");
			broadcastYaml.set(u.getInt(player, (byte)0)+"", ChatColor.WHITE+event.getMessage());
			broadcastYaml.saveConfig();
			u.clearAll(player);
			new admin.OPbox_GUI().OPBoxGUI_BroadCast(player, (byte) 0);
			return;
		case "JM"://JoinMessage
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if(message.compareTo("����")==0)
				configYaml.set("Server.JoinMessage", null);
			else
				configYaml.set("Server.JoinMessage", ChatColor.WHITE+event.getMessage());
			configYaml.saveConfig();
			u.clearAll(player);
			new admin.OPbox_GUI().OPBoxGUI_Setting(player);
			return;
		case "QM"://QuitMessage
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if(message.compareTo("����")==0)
				configYaml.set("Server.QuitMessage", null);
			else
				configYaml.set("Server.QuitMessage", ChatColor.WHITE+event.getMessage());
			configYaml.saveConfig();
			u.clearAll(player);
			new admin.OPbox_GUI().OPBoxGUI_Setting(player);
			return;
		case "CSN"://ChangeStatName
			{
				message.replace(".", "");
				message.replace(":", "");
				message.replace(" ", "");
				String Message = event.getMessage();
				Message.replace(".", "");
				Message.replace(":", "");
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				switch(u.getString(player, (byte)2))
				{
				case "ü��":
					configYaml.set("Server.STR", message);
					break;
				case "ü�� ����":
					configYaml.set("Server.STR_Lore", Message);
					break;
				case "�ؾ�":
					configYaml.set("Server.DEX", message);
					break;
				case "�ؾ� ����":
					configYaml.set("Server.DEX_Lore", Message);
					break;
				case "����":
					configYaml.set("Server.INT", message);
					break;
				case "���� ����":
					configYaml.set("Server.INT_Lore", Message);
					break;
				case "����":
					configYaml.set("Server.WILL", message);
					break;
				case "���� ����":
					configYaml.set("Server.WILL_Lore", Message);
					break;
				case "���":
					configYaml.set("Server.LUK", message);
					break;
				case "��� ����":
					configYaml.set("Server.LUK_Lore", Message);
					break;
				case "�����":
					configYaml.set("Server.Damage", message);
					break;
				case "���� �����":
					configYaml.set("Server.MagicDamage", message);
					break;
				case "ȭ��":
					String Pa = event.getMessage();
					Pa.replace(".", "");
					Pa.replace(":", "");
					Pa.replace(" ", "");
					configYaml.set("Server.MoneyName", Pa);
					configYaml.saveConfig();
					u.clearAll(player);
					main.Main_ServerOption.Money = Pa;
					new admin.OPbox_GUI().OPBoxGUI_Money(player);
					return;
				}
				configYaml.saveConfig();
				u.clearAll(player);
				player.sendMessage(ChatColor.GREEN + "[System] : ����� ������ ���� ������ ����, ���� ���ε� ���� �ϰ� ����˴ϴ�.");
				new admin.OPbox_GUI().OPBoxGUI_StatChange(player);
			}
			return;
	
			case "CDML": //Change Drop Money Limit
			{
				if(isIntMinMax(message, player, 1000, 100000000))
				{
					int value = Integer.parseInt(message);
					main.Main_ServerOption.MaxDropMoney = value;
					configYaml.set("Server.Max_Drop_Money",value);
					configYaml.saveConfig();
					u.clearAll(player);
					new admin.OPbox_GUI().OPBoxGUI_Money(player);
				}
			}
			return;
			case "CMID": //Change Money ID
			{
				if(isIntMinMax(message, player, 1, Integer.MAX_VALUE))
				{
					if(new event.Main_Interact().SetItemDefaultName(Short.parseShort(message),(byte)0).compareTo("�������� ���� ������")==0)
					{
						player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �������� �������� �ʽ��ϴ�!");
		  				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  				return;
					}
					int value = Integer.parseInt(message);
					switch(u.getInt(player, (byte)1))
					{
					case 50:
						configYaml.set("Server.Money.1.ID",value);
						main.Main_ServerOption.Money1ID = (short) value;
						break;
					case 100:
						configYaml.set("Server.Money.2.ID",value);
						main.Main_ServerOption.Money2ID = (short) value;
						break;
					case 1000:
						configYaml.set("Server.Money.3.ID",value);
						main.Main_ServerOption.Money3ID = (short) value;
						break;
					case 10000:
						configYaml.set("Server.Money.4.ID",value);
						main.Main_ServerOption.Money4ID = (short) value;
						break;
					case 50000:
						configYaml.set("Server.Money.5.ID",value);
						main.Main_ServerOption.Money5ID = (short) value;
						break;
					case -1:
						configYaml.set("Server.Money.6.ID",value);
						main.Main_ServerOption.Money6ID = (short) value;
						break;
					}
					configYaml.saveConfig();
					player.sendMessage(ChatColor.DARK_AQUA+"[System] : ȭ�� ������� ������ ������ DATA�� �Է� �� �ּ���!");
					u.setString(player, (byte)1, "CMDATA");
				}
			}
			return;
			case "CMDATA": //Change Money DATA
			{
				if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
				{
					int value = Integer.parseInt(message);
					switch(u.getInt(player, (byte)1))
					{
					case 50:
						configYaml.set("Server.Money.1.DATA",value);
						main.Main_ServerOption.Money1DATA = (byte) value;
						break;
					case 100:
						configYaml.set("Server.Money.2.DATA",value);
						main.Main_ServerOption.Money2DATA = (byte) value;
						break;
					case 1000:
						configYaml.set("Server.Money.3.DATA",value);
						main.Main_ServerOption.Money3DATA = (byte) value;
						break;
					case 10000:
						configYaml.set("Server.Money.4.DATA",value);
						main.Main_ServerOption.Money4DATA = (byte) value;
						break;
					case 50000:
						configYaml.set("Server.Money.5.DATA",value);
						main.Main_ServerOption.Money5DATA = (byte) value;
						break;
					case -1:
						configYaml.set("Server.Money.6.DATA",value);
						main.Main_ServerOption.Money6DATA = (byte) value;
						break;
					}
					configYaml.saveConfig();
					u.clearAll(player);
					new admin.OPbox_GUI().OPBoxGUI_Money(player);
				}
			}
			return;
		}
		return;
	}

}
