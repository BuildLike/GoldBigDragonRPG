package GBD_RPG.Area;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_Chat;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Area_Chat extends Util_Chat
{
	public void AreaTypeChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = event.getPlayer();
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager AreaConfig =YC.getNewConfig("Area/AreaList.yml");
		event.setCancelled(true);
		GBD_RPG.Area.Area_GUI AGUI = new GBD_RPG.Area.Area_GUI();
		String Message = ChatColor.stripColor(event.getMessage());
		switch(u.getString(player, (byte)2))
		{
			case "ARR"://AreaRegenBlock
				if(isIntMinMax(Message, player, 1, 3600))
				{
					AreaConfig.set(u.getString(player, (byte)3)+".RegenBlock", Integer.parseInt(Message));
	    			AreaConfig.saveConfig();
	    			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					AGUI.AreaGUI_Main(player, u.getString(player, (byte)3));
	    			u.clearAll(player);
				}
				return;
			case "AMSC"://AreaMonsterSpawnCount
				if(isIntMinMax(Message, player, 1, 100))
				{
					AreaConfig.set(u.getString(player, (byte)3)+".MonsterSpawnRule."+u.getString(player, (byte)1)+".count", Integer.parseInt(Message));
	    			AreaConfig.saveConfig();
	    			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					u.setString(player, (byte)2, "AMSMC");
					player.sendMessage(ChatColor.GREEN+"[����] : �ݰ� 20��� �̳� ��ƼƼ�� �� ���� �̸��� ���� ���� �ұ��?");
					player.sendMessage(ChatColor.YELLOW+"(�ּ� 1���� ~ �ִ� 300����)");
				}
				return;
			case "AMSMC"://AreaMonsterSpawnMaximumCount
				if(isIntMinMax(Message, player, 1, 300))
				{
					AreaConfig.set(u.getString(player, (byte)3)+".MonsterSpawnRule."+u.getString(player, (byte)1)+".max", Integer.parseInt(Message));
	    			AreaConfig.saveConfig();
	    			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					u.setString(player, (byte)2, "AMST");
					player.sendMessage(ChatColor.GREEN+"[����] : �� �ʸ��� �����ǰ� �ұ��?");
					player.sendMessage(ChatColor.YELLOW+"(�ּ� 1�� ~ �ִ� 7200��(2�ð�))");
				}
				return;
			case "AMST"://AreaMonsterSpawnTimer
				if(isIntMinMax(Message, player, 1, 7200))
				{
					AreaConfig.set(u.getString(player, (byte)3)+".MonsterSpawnRule."+u.getString(player, (byte)1)+".timer", Integer.parseInt(Message));
	    			AreaConfig.saveConfig();
	    			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
	    			/*
					u.setString(player, (byte)2, "AMSR");
					player.sendMessage(ChatColor.GREEN+"[����] : ���� ���� '����'�� �÷��̾ �ݰ� �� ��� �̳��� ��� �� �� ���� ������ ���� �ұ��?");
					player.sendMessage(ChatColor.YELLOW+"(�ּ� 1��� ~ �ִ� 1000���)");
					*/
	    			u.setString(player, (byte)2, "AMSM");
					player.sendMessage(ChatColor.GREEN+"[����] : Ư���� ���� �ϰ� ���� ���Ͱ� �ֳ���?");
					player.sendMessage(ChatColor.YELLOW+"(O Ȥ�� X�� ����ϼ���!)");
				}
				return;
				/*
			case "AMSR"://AreaMonsterSpawnRange
				if(isIntMinMax(Message, player, 1, 1000))
				{
					AreaConfig.set(u.getString(player, (byte)3)+".MonsterSpawnRule."+u.getString(player, (byte)1)+".range", Integer.parseInt(Message));
	    			AreaConfig.saveConfig();
	    			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					u.setString(player, (byte)2, "AMSM");
					player.sendMessage(ChatColor.GREEN+"[����] : Ư���� ���� �ϰ� ���� ���Ͱ� �ֳ���?");
					player.sendMessage(ChatColor.YELLOW+"(O Ȥ�� X�� ����ϼ���!)");
				}
				return;
				*/
			case "AMSM"://AreaMonsterSpawnMonster
				byte answer = askOX(Message, player);
				if(answer!=-1)
				{
					if(answer==0)
					{
		    			s.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
		    			AGUI.AreaMonsterSpawnSettingGUI(player, (short) 0, u.getString(player, (byte)3));
		    			String AreaName =u.getString(player, (byte)3);
		    			new GBD_RPG.Area.Area_Main().AreaMonsterSpawnAdd(AreaName, u.getString(player, (byte)1));
					}
					else
					{
						s.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.7F);
						AGUI.AreaSpawnSpecialMonsterListGUI(player, (short) 0, u.getString(player, (byte)3),u.getString(player, (byte)1));
					}
	    			u.clearAll(player);
				}
				return;
			case "Priority"://���� �켱���� ����
				if(isIntMinMax(Message, player, 0, 100))
				{
	    			AreaConfig.set(u.getString(player, (byte)3)+".Priority", Integer.parseInt(Message));
	    			AreaConfig.saveConfig();
	    			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
	    			AGUI.AreaGUI_Main(player, u.getString(player, (byte)3));
	    			u.clearAll(player);
				}
				return;
		}
		return;
	}

}
