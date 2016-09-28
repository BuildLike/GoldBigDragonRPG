package GBD_RPG.Quest;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_Chat;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Quest_Chat extends Util_Chat
{
	
	public void QuestTypeChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		GBD_RPG.Effect.Effect_Sound sound = new GBD_RPG.Effect.Effect_Sound();
	    Player player = event.getPlayer();
    	GBD_RPG.Quest.Quest_GUI QGUI = new GBD_RPG.Quest.Quest_GUI();
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager QuestConfig=YC.getNewConfig("Quest/QuestList.yml");

    	event.setCancelled(true);
    	String message = ChatColor.stripColor(event.getMessage());
    	switch(u.getString(player, (byte)1))
    	{
	    	case "Cal"://Caluclate
	    	{
				if(isIntMinMax(message, player, 1, 5))
				{
					sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			    	u.setString(player, (byte)1, "CVS");
			    	u.setInt(player, (byte)1, Integer.parseInt(message));
			    	String Example=null;
			    	switch(Integer.parseInt(message))
			    	{
			    	case 1:
			    		Example = "�÷��̾� ���� �� B";
			    		break;
			    	case 2:
			    		Example = "�÷��̾� ���� �� B";
			    		break;
			    	case 3:
			    		Example = "�÷��̾� ���� �� B";
			    		break;
			    	case 4:
			    		Example = "�÷��̾� ���� �� B";
			    		break;
			    	case 5:
			    		Example = "�÷��̾� ���� �� B";
			    		break;
			    	}
					player.sendMessage(ChatColor.GREEN + "[����Ʈ] : "+ChatColor.YELLOW+Example+ChatColor.GREEN+" ���� "+ChatColor.YELLOW+"B"+ChatColor.GREEN+" ���� �� ���� �� �ΰ���?");
			    	if(Integer.parseInt(message) <= 2)
						player.sendMessage(ChatColor.GRAY + "(�ּ� -1000 ~ �ִ� 20000)");
			    	else
						player.sendMessage(ChatColor.GRAY + "(�ּ� 1 ~ �ִ� 100)");
					player.sendMessage(ChatColor.GRAY + "(��� ��� -2000 ���ϰų� 40000 �̻��� ��� ���� -2000�� 40000���� ����)");
					player.sendMessage(ChatColor.GRAY + "(���� Ÿ����  Integer�̹Ƿ�, ��� ���� �ʹ� ũ�ų� ������ �̻��� ���� ���ü��� ����)");
				}
	    	}
	    	return;
	    	case "CVS"://Calculate Value Set
	    	{
	    		if(u.getInt(player, (byte)1)<=2)
	    		{
	    			if(isIntMinMax(message, player, -1000, 20000))
					{
		    			short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
						sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
						QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", "Cal");
		        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Comparison", u.getInt(player, (byte)1));
			    		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Value", Integer.parseInt(message));
		    	    	QuestConfig.saveConfig();
		    			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : ��� ���� ������ �Ϸ�Ǿ����ϴ�!");
		    			QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
		    	    	u.clearAll(player);
					}
	    		}
	    		else
	    		{
	    			if(isIntMinMax(message, player, 1, 100))
					{
		    			short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
						sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
						QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", "Cal");
		        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Comparison", u.getInt(player, (byte)1));
			    		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Value", Integer.parseInt(message));
		    	    	QuestConfig.saveConfig();
		    			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : ��� ���� ������ �Ϸ�Ǿ����ϴ�!");
		    			QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
		    	    	u.clearAll(player);
					}
	    		}
	    	}
	    	return;
    	case "IFMVS"://IF Max Value Set
    	{
			if(isIntMinMax(message, player, u.getInt(player, (byte)2), 40000))
			{
    			short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", "IF");
        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Comparison", u.getInt(player, (byte)1));
	    		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Min", u.getInt(player, (byte)2));
	    		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Max", Integer.parseInt(message));
    	    	QuestConfig.saveConfig();
    			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : IF�� ������ �Ϸ�Ǿ����ϴ�!");
    			QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
    	    	u.clearAll(player);
			}
    	}
    	return;
    	case "IFVS"://IF Value Set
    	{
			if(isIntMinMax(message, player, -2000, 40000))
			{
    			short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
		    	if(u.getInt(player, (byte)1)!=7)
		    	{
	        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", "IF");
	        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Comparison", u.getInt(player, (byte)1));
		    		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Value", Integer.parseInt(message));
		    	}
		    	else
		    	{
		    		u.setInt(player, (byte)2, Integer.parseInt(message));
		    		QuestConfig.saveConfig();
			    	u.setString(player, (byte)1, "IFMVS");
					player.sendMessage(ChatColor.GREEN + "[����Ʈ] : "+ChatColor.YELLOW+Integer.parseInt(message)+" <= �÷��̾� ���� <= B"+ChatColor.GREEN+" ���� "+ChatColor.YELLOW+"C"+ChatColor.GREEN+" ���� �� ���� �� �ΰ���?");
					player.sendMessage(ChatColor.GRAY + "(�ּ� "+Integer.parseInt(message)+" ~ �ִ� 40000)");
		    		return;
		    	}
    	    	QuestConfig.saveConfig();
    			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : IF�� ������ �Ϸ�Ǿ����ϴ�!");
    			QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
    	    	u.clearAll(player);
			}
    	}
    	return;
    	case "IFTS"://IF Type Select
    	{
			if(isIntMinMax(message, player, 1, 7))
			{
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
		    	u.setString(player, (byte)1, "IFVS");
		    	u.setInt(player, (byte)1, Integer.parseInt(message));
		    	String Example=null;
		    	switch(Integer.parseInt(message))
		    	{
		    	case 1:
		    		Example = "�÷��̾� ���� == B";
		    		break;
		    	case 2:
		    		Example = "�÷��̾� ���� != B";
		    		break;
		    	case 3:
		    		Example = "�÷��̾� ���� > B";
		    		break;
		    	case 4:
		    		Example = "�÷��̾� ���� < B";
		    		break;
		    	case 5:
		    		Example = "�÷��̾� ���� >= B";
		    		break;
		    	case 6:
		    		Example = "�÷��̾� ���� <= B";
		    		break;
		    	case 7:
		    		Example = "C <= �÷��̾� ���� <= B";
					player.sendMessage(ChatColor.GREEN + "[����Ʈ] : "+ChatColor.YELLOW+Example+ChatColor.GREEN+" ���� "+ChatColor.YELLOW+"C"+ChatColor.GREEN+" ���� �� ���� �� �ΰ���?");
					player.sendMessage(ChatColor.GRAY + "(�ּ� -2000 ~ �ִ� 40000)");
		    		return;
		    	}
				player.sendMessage(ChatColor.GREEN + "[����Ʈ] : "+ChatColor.YELLOW+Example+ChatColor.GREEN+" ���� "+ChatColor.YELLOW+"B"+ChatColor.GREEN+" ���� �� ���� �� �ΰ���?");
				player.sendMessage(ChatColor.GRAY + "(�ּ� -2000 ~ �ִ� 40000)");
			}
    	}
    	return;
	    	case "CV"://ChangeVariable
	    	{
				if(isIntMinMax(message, player, -1000, 30000))
				{
					sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
	    			short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
	        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", "VarChange");
	        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Value", Integer.parseInt(message));
	    	    	QuestConfig.saveConfig();
	    			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : ���� ���� ������ �Ϸ�Ǿ����ϴ�!");
	    			QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
	    	    	u.clearAll(player);
				}
	    	}
	    	return;
    	case "SCV"://SetChoiceVariable
    	{
			if(isIntMinMax(message, player, -1000, 30000))
			{
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				u.setInt(player, (byte)(u.getInt(player, (byte)1)+2),Integer.parseInt(message));
				u.setInt(player, (byte)1,u.getInt(player, (byte)1)+1);
	    		if(u.getInt(player, (byte)0)==u.getInt(player, (byte)1))
	    		{
	    			short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
	        		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", "Choice");
	        		for(int count = 0; count <u.getInt(player, (byte)0);count++)
	        		{
	        			QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Choice."+count+".Lore", u.getString(player, (byte)(count+4)));
	        			QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Choice."+count+".Var", u.getInt(player, (byte)(count+2)));
	        		}
	    	    	QuestConfig.saveConfig();
	    			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : �������� ���������� ��ϵǾ����ϴ�!");
	    			QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
	    	    	u.clearAll(player);
	    		}
	    		else
	    		{
			    	u.setString(player, (byte)1, "SCL");
			    	player.sendMessage(ChatColor.GREEN + "[����Ʈ] : "+(u.getInt(player, (byte)1)+1)+"��° ���ÿ� ���� ���� �Է� �ϼ���!");
					player.sendMessage(ChatColor.GOLD + "%enter%"+ChatColor.WHITE + " - ���� ��� ���� -");
					player.sendMessage(ChatColor.GOLD + "%player%"+ChatColor.WHITE + " - �÷��̾� ��Ī�ϱ� -");
					player.sendMessage(ChatColor.WHITE + ""+ChatColor.BOLD + "&l " + ChatColor.BLACK + "&0 "+ChatColor.DARK_BLUE+"&1 "+ChatColor.DARK_GREEN+"&2 "+
					ChatColor.DARK_AQUA + "&3 " +ChatColor.DARK_RED + "&4 " + ChatColor.DARK_PURPLE + "&5 " +
							ChatColor.GOLD + "&6 " + ChatColor.GRAY + "&7 " + ChatColor.DARK_GRAY + "&8 " +
					ChatColor.BLUE + "&9 " + ChatColor.GREEN + "&a " + ChatColor.AQUA + "&b " + ChatColor.RED + "&c" +
							ChatColor.LIGHT_PURPLE + "&d " + ChatColor.YELLOW + "&e "+ChatColor.WHITE + "&f");
	    		}
			}
			
    	}
    	return;
    	case "SCL"://SetChoiceLore
    	{
			sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)(u.getInt(player, (byte)1)+4), event.getMessage());
	    	u.setString(player, (byte)1, "SCV");
			player.sendMessage(ChatColor.GREEN + "[����Ʈ] : "+(u.getInt(player, (byte)1)+1)+"�� �������� �� ���, �÷��̾� ������ ������ ��ȯ��ų���?");
			player.sendMessage(ChatColor.GRAY + "(�ּ� -1000 ~ �ִ� 30000)");
    	}
    	return;
    	case "CS"://ChoiceSize
			if(isIntMinMax(message, player, 1, 4))
			{
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				u.setInt(player, (byte)0, Integer.parseInt(message));
				u.setInt(player, (byte)1, 0);
		    	u.setString(player, (byte)1, "SCL");
		    	player.sendMessage(ChatColor.GREEN + "[����Ʈ] : 1��° ���ÿ� ���� ���� �Է� �ϼ���!");
				player.sendMessage(ChatColor.GOLD + "%enter%"+ChatColor.WHITE + " - ���� ��� ���� -");
				player.sendMessage(ChatColor.GOLD + "%player%"+ChatColor.WHITE + " - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage(ChatColor.WHITE + ""+ChatColor.BOLD + "&l " + ChatColor.BLACK + "&0 "+ChatColor.DARK_BLUE+"&1 "+ChatColor.DARK_GREEN+"&2 "+
				ChatColor.DARK_AQUA + "&3 " +ChatColor.DARK_RED + "&4 " + ChatColor.DARK_PURPLE + "&5 " +
						ChatColor.GOLD + "&6 " + ChatColor.GRAY + "&7 " + ChatColor.DARK_GRAY + "&8 " +
				ChatColor.BLUE + "&9 " + ChatColor.GREEN + "&a " + ChatColor.AQUA + "&b " + ChatColor.RED + "&c" +
						ChatColor.LIGHT_PURPLE + "&d " + ChatColor.YELLOW + "&e "+ChatColor.WHITE + "&f");
			}
	    	return;
    	case "Whisper":
    	case "BroadCast":
    	case "PScript":
	    	{
	    		short size = (short) QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false).size();
	    		QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Type", u.getString(player, (byte)1));
		    	QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+size+".Message", event.getMessage());
		    	QuestConfig.saveConfig();
				player.sendMessage(ChatColor.GREEN+"[����Ʈ] : ��簡 ���������� ��ϵǾ����ϴ�!");
				QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
		    	u.clearAll(player);
	    	}
	    	return;
    	case "BPID"://BlockPlaceID
			if(isIntMinMax(message, player, 1, Integer.MAX_VALUE))
			{
				GBD_RPG.Main_Event.Main_Interact I = new GBD_RPG.Main_Event.Main_Interact();
				if(I.SetItemDefaultName(Short.parseShort(message),(byte)0).compareTo("�������� ���� ������")==0)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �������� �������� �ʽ��ϴ�!");
	  				sound.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  				return;
				}
				String QuestName = u.getString(player, (byte)2);
				int size = u.getInt(player, (byte)1);
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				QuestConfig.set(QuestName+".FlowChart."+size+".ID", Integer.parseInt(message));
				QuestConfig.saveConfig();
		    	u.setString(player, (byte)1, "BPDATA");
		    	player.sendMessage(ChatColor.GREEN + "[����Ʈ] : ��ġ �� ��� DATA�� �Է� �� �ּ���!");
			}
	    	return;
    	case "BPDATA"://BlockPlaceDATA
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				String QuestName = u.getString(player, (byte)2);
				int size = u.getInt(player, (byte)1);
				sound.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				QuestConfig.set(QuestName+".FlowChart."+size+".DATA", Integer.parseInt(message));
				QuestConfig.saveConfig();
		    	u.setString(player, (byte)1, "BPDATA");
		    	u.clearAll(player);
		    	QGUI.FixQuestGUI(player, (short) 0, QuestName);
			}
	    	return;
    	case "Script":
	    	u.setString(player, (byte)3,ChatColor.WHITE + event.getMessage());
			player.sendMessage(ChatColor.GREEN+"[����Ʈ] : �ش� ��縦 ���� NPC�� ��Ŭ�� �ϼ���.");
	    	return;
    	case "Visit":
			YamlManager AreaList = YC.getNewConfig("Area/AreaList.yml");
			Object[] arealist = AreaList.getConfigurationSection("").getKeys(false).toArray();

			if(arealist.length <= 0)
			{
				GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
				s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				player.sendMessage(ChatColor.RED + "[SYSTEM] : ������ ������ �����ϴ�!");
				u.clearAll(player);
				return;
			}
			for(short count =0; count <arealist.length;count++)
			{
				if(event.getMessage().compareTo(arealist[count].toString())==0)
				{
					player.sendMessage(ChatColor.GREEN+"[����Ʈ] : "+ChatColor.YELLOW + arealist[count] + ChatColor.GREEN+" ������ �湮�ϵ��� ��� �Ǿ����ϴ�!");

					Set<String> b4 = QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false);
					
			    	QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+b4.size()+".Type", "Visit");
			    	QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+b4.size()+".AreaName", (String) arealist[count]);
			    	QuestConfig.saveConfig();

					QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
					u.clearAll(player);
					return;
				}
				player.sendMessage(ChatColor.GREEN +"  "+ arealist[count].toString());
			}
			player.sendMessage(ChatColor.GREEN + "���������������������� ��Ϧ�����������������");
			for(short count =0; count <arealist.length;count++)
				player.sendMessage(ChatColor.GREEN +"  "+ arealist[count].toString());
			player.sendMessage(ChatColor.GREEN + "���������������������� ��Ϧ�����������������");
			player.sendMessage(ChatColor.DARK_AQUA + "[����Ʈ] : �湮�ؾ� �� ���� �̸��� ���� �ּ���!");
	    	return;
    	case "Hunt":
			if(isIntMinMax(event.getMessage(), player, 1, Integer.MAX_VALUE))
			{
				short Flownumber=0;
				short Monsternumber =0;
				Set<String> b = QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false);
				if(u.getInt(player, (byte)3) != -1)
					Flownumber = (short) (b.size()-1);
				else
					Flownumber = (short) b.size();
				if(QuestConfig.contains(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Monster")==false)
				{
					QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Type","Hunt");
					QuestConfig.createSection(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Monster");
					QuestConfig.saveConfig();
				}
				Set<String> c = QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Monster").getKeys(false);
				if(u.getInt(player, (byte)2) != -1)
					Monsternumber = (short) u.getInt(player, (byte)2);
				else
					Monsternumber = (short) c.size();
				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Monster."+Monsternumber+".MonsterName", u.getString(player, (byte)3));
				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Monster."+Monsternumber+".Amount", Integer.parseInt(event.getMessage()));
				QuestConfig.saveConfig();
				player.sendMessage(ChatColor.GREEN + "[����Ʈ] : " + ChatColor.YELLOW + QGUI.SkullType(u.getString(player, (byte)3)) + ChatColor.GREEN + " (��)�� " + ChatColor.YELLOW + Integer.parseInt(event.getMessage())+ ChatColor.GREEN +" ���� ����ϵ��� �����Ǿ����ϴ�!");

				if(u.getInt(player, (byte)2) < 17)
					QGUI.KeepGoing(player, u.getString(player, (byte)2), (short) Flownumber, (short) Monsternumber,false);
				else
					QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
				u.clearAll(player);
			}
			return;
    	case "Harvest":
	    	if(u.getString(player, (byte)3)!=null)
	    	{
	    		if(ChatColor.stripColor(event.getMessage()).compareTo("x")==0 ||ChatColor.stripColor(event.getMessage()).compareTo("X")==0 ||
	    				ChatColor.stripColor(event.getMessage()).compareTo("o")==0 ||ChatColor.stripColor(event.getMessage()).compareTo("O")==0)
	    		{
		    		if(ChatColor.stripColor(event.getMessage()).compareTo("x")==0 ||ChatColor.stripColor(event.getMessage()).compareTo("X")==0)
		    			u.setBoolean(player, (byte)1, false);
		    		else
		    			u.setBoolean(player, (byte)1, true);
					u.setString(player, (byte)3,null);
			    	player.sendMessage(ChatColor.GREEN + "[SYSTEM] : ����� �󸶳� ä���ؾ� ���� �����ϼ���! ("+ChatColor.YELLOW + "1"+ChatColor.GREEN+" ~ "+ChatColor.YELLOW+""+Integer.MAX_VALUE+ChatColor.GREEN+"��)");
	    		}
	    		else
	    		{
    				player.sendMessage(ChatColor.RED + "[SYSTEM] : xȤ�� o�� �Է� ��  �ּ���.");
      				sound.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	    		}
  				return;
	    	}
	    	else
	    	{
				if(isIntMinMax(event.getMessage(), player, 1, Integer.MAX_VALUE))
				{
    				short Flownumber=0;
    				short BlockNumber =0;
    				Set<String> b = QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart").getKeys(false);
    				if(u.getInt(player, (byte)3) != -1)
    					Flownumber = (short) (b.size()-1);
    				else
    					Flownumber = (short) b.size();
    				if(QuestConfig.contains(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block")==false)
    				{
        				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Type","Harvest");
        				QuestConfig.createSection(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block");
        				QuestConfig.saveConfig();
    				}
    				Set<String> c = QuestConfig.getConfigurationSection(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block").getKeys(false);
    				if(u.getInt(player, (byte)4) != -1)
    					BlockNumber = (short) u.getInt(player, (byte)4);
    				else
    					BlockNumber = 0;
    				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block."+BlockNumber+".BlockID", u.getInt(player, (byte)1));
    				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block."+BlockNumber+".BlockData", u.getInt(player, (byte)2));
    				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block."+BlockNumber+".Amount", Integer.parseInt(event.getMessage()));
    				QuestConfig.set(u.getString(player, (byte)2)+".FlowChart."+Flownumber+".Block."+BlockNumber+".DataEquals", u.getBoolean(player, (byte)1));
    				QuestConfig.saveConfig();
    				
    				if(u.getBoolean(player, (byte)1) == false)
    					player.sendMessage(ChatColor.GREEN + "[����Ʈ] : ������ ID�� " + ChatColor.YELLOW + u.getInt(player, (byte)1) +ChatColor.GREEN + " �� ��� ����� " + ChatColor.YELLOW + Integer.parseInt(event.getMessage())+ ChatColor.GREEN +" �� ä���ϵ��� �����Ǿ����ϴ�!");
    				else
    					player.sendMessage(ChatColor.GREEN + "[����Ʈ] : ������ �ڵ� " + ChatColor.YELLOW + u.getInt(player, (byte)1) +  ":"+ u.getInt(player, (byte)2) + ChatColor.GREEN + " �� ����� " + ChatColor.YELLOW + Integer.parseInt(event.getMessage())+ ChatColor.GREEN +" �� ä���ϵ��� �����Ǿ����ϴ�!");

    				if(u.getInt(player, (byte)2) < 17)
    					QGUI.KeepGoing(player, u.getString(player, (byte)2), (short) Flownumber, (short) BlockNumber,true);
    				else
    					QGUI.FixQuestGUI(player, (short) 0, u.getString(player, (byte)2));
    				u.clearAll(player);
				}
    			return;
	    	}
    	}
    	
    	if(u.getString(player, (byte)2)!=null)
    	{
    		if(u.getString(player, (byte)1).contains("District") == true)
    		{
				if(isIntMinMax(event.getMessage(), player, 0, Integer.MAX_VALUE))
				{
    				String QuestName = u.getString(player, (byte)2);
    				int value = Integer.parseInt(event.getMessage());
    				YamlManager QuestList  = YC.getNewConfig("Quest/QuestList.yml");
    				switch(u.getString(player, (byte)1))
    				{
    				case "Level District":
    					QuestList.set(QuestName+".Need.LV", value);
    					break;
    				case "Love District":
    					QuestList.set(QuestName+".Need.Love", value);
    					break;
    				case "STR District":
    					QuestList.set(QuestName+".Need.STR", value);
    					break;
    				case "DEX District":
    					QuestList.set(QuestName+".Need.DEX", value);
    					break;
    				case "INT District":
    					QuestList.set(QuestName+".Need.INT", value);
    					break;
    				case "WILL District":
    					QuestList.set(QuestName+".Need.WILL", value);
    					break;
    				case "LUK District":
    					QuestList.set(QuestName+".Need.LUK", value);
    					break;
    				case "Accept District":
    					QuestList.set(QuestName+".Server.Limit", value);
    					break;
    				}
    				QuestList.saveConfig();
    				u.clearAll(player);
    				QGUI.QuestOptionGUI(player, QuestName);
				}
    			return;
    		}
    	}
    	
		if(u.getString(player, (byte)4)!=null)
		{
			if(isIntMinMax(event.getMessage(), player, 0, Integer.MAX_VALUE))
			{
    			switch(u.getString(player, (byte)4))
    			{
	    			case "M":
	    		    	event.setCancelled(true);
	    				u.setString(player, (byte)4,null);
	    				u.setInt(player, (byte)1, Integer.parseInt(ChatColor.stripColor(event.getMessage())));
			    		QGUI.GetPresentGUI(player, u.getString(player, (byte)3));
	    				break;
	    			case "E":
	    		    	event.setCancelled(true);
	    				u.setString(player, (byte)4,null);
	    				u.setInt(player, (byte)2, Integer.parseInt(ChatColor.stripColor(event.getMessage())));
			    		QGUI.GetPresentGUI(player, u.getString(player, (byte)3));
	    				break;
	    			case "L":
	    		    	event.setCancelled(true);
	    				u.setString(player, (byte)4,null);
	    				u.setInt(player, (byte)3, Integer.parseInt(ChatColor.stripColor(event.getMessage())));
			    		QGUI.GetPresentGUI(player, u.getString(player, (byte)3));
	    				break;
    				default :
    					break;
    			}
			}
			return;
    	}
		return;
	}
	
}
