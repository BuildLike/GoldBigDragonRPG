package monster;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_Chat;
import util.YamlLoader;



public class Monster_Chat extends Util_Chat
{
	public void MonsterTypeChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		Player player = event.getPlayer();
		monster.Monster_GUI MGUI = new monster.Monster_GUI();
		monster.Monster_Spawn MC = new monster.Monster_Spawn();
	  	YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");
		String[] monsterlist = monsterListYaml.getKeys().toArray(new String[0]);
	    
	    event.setCancelled(true);
	    String message = ChatColor.stripColor(event.getMessage());
		switch(u.getString(player, (byte)1))
		{
		case "Potion":
			if(isIntMinMax(message, player, 0, 100))
			{
				String MonsterName = u.getString(player, (byte)3);
				switch(u.getString(player, (byte)2))
				{
				case "Regenerate":
					monsterListYaml.set(MonsterName+".Potion.Regenerate", Integer.parseInt(message));break;
				case "Poision":
					monsterListYaml.set(MonsterName+".Potion.Poison", Integer.parseInt(message));break;
				case "Speed":
					monsterListYaml.set(MonsterName+".Potion.Speed", Integer.parseInt(message));break;
				case "Slow":
					monsterListYaml.set(MonsterName+".Potion.Slow", Integer.parseInt(message));break;
				case "Strength":
					monsterListYaml.set(MonsterName+".Potion.Strength", Integer.parseInt(message));break;
				case "Weak":
					monsterListYaml.set(MonsterName+".Potion.Weak", Integer.parseInt(message));break;
				case "Jump":
					monsterListYaml.set(MonsterName+".Potion.JumpBoost", Integer.parseInt(message));break;
				}
				monsterListYaml.saveConfig();
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
				MGUI.MonsterPotionGUI(player, MonsterName);
			}
		return;
		case "NM"://NewMonster
			message = message.replace(".", "");
			for(int count = 0; count < monsterlist.length;count++)
	    	{
	    		if(monsterlist[count].equals(message))
	    		{
				  	SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			    	player.sendMessage("��c[SYSTEM] : �ش� �̸��� ���ʹ� �̹� �����մϴ�!");
			    	return;
	    		}
	    	}
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_WOLF_AMBIENT, 1.0F, 1.0F);
	    	MC.CreateMonster(message);

		  	main.Main_ServerOption.MonsterNameMatching.put(message, message);
		  	main.Main_ServerOption.MonsterList.put(message, new monster.Monster_Object(message, message, 15, 20, 1, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0));
		  	
	    	player.sendMessage("��a[SYSTEM] : ��e"+message+"��a ���� ���� �Ϸ�! (�߰� ������ �� �ּ���)");
			SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
			MGUI.MonsterOptionSettingGUI(player, message);
			u.clearAll(player);
	    	return;
		case "CN"://ChangeName
			message= event.getMessage().replace(".", "");
			main.Main_ServerOption.MonsterNameMatching.remove(u.getString(player, (byte)2)+monsterListYaml.getString(".Name"));
			monsterListYaml.set(u.getString(player, (byte)2)+".Name", message);
			monsterListYaml.saveConfig();
			
			main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setCustomName(message);
			main.Main_ServerOption.MonsterNameMatching.put(message, u.getString(player, (byte)2));
			
			SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
			MGUI.MonsterOptionSettingGUI(player, u.getString(player, (byte)2));
			u.clearAll(player);
	    	return;
		case "HP"://HealthPoint
			if(isIntMinMax(message, player, 1, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setHP(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
				MGUI.MonsterOptionSettingGUI(player, u.getString(player, (byte)2));
				u.clearAll(player);
			}
			return;
		case "EXP":
		case "LUK":
		case "Magic_Protect" : //MagicProtect
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				if(u.getString(player, (byte)1).equals("EXP"))
					main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setEXP(Integer.parseInt(message));
				else if(u.getString(player, (byte)1).equals("LUK"))
					main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setLUK(Integer.parseInt(message));
				else
					main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setMPRO(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
				MGUI.MonsterOptionSettingGUI(player, u.getString(player, (byte)2));
				u.clearAll(player);
			}
			return;
		case "MAX_Money"://Maximum Drop Money
			if(isIntMinMax(message, player, u.getInt(player, (byte)1), Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setMaxMoney(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
				MGUI.MonsterOptionSettingGUI(player, u.getString(player, (byte)2));
				u.clearAll(player);
			}
			return;
		case "MIN_Money"://Minimum Drop Money
			if(isIntMinMax(message, player, 1, Integer.MAX_VALUE))
			{
				u.setInt(player, (byte)1, Integer.parseInt(message));
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setMinMoney(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��a[����] : �ش� ���Ͱ� ����ϴ� �ִ� ��差�� ������ �ּ���!");
				player.sendMessage("��3("+message+" ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MAX_Money");
			}
			return;
		case "STR"://Strength
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setSTR(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7("+main.Main_ServerOption.statDEX+"�� ������ ���Ÿ� ���ݷ��� ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ "+main.Main_ServerOption.statDEX+"�� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "DEX");
			}
			return;
		case "DEX"://DEX
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setDEX(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7("+main.Main_ServerOption.statINT+"�� ������ ���� ���ݷ��� ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ "+main.Main_ServerOption.statINT+"�� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "INT");
			}
			return;
		case "INT"://INT
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setINT(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7("+main.Main_ServerOption.statWILL+"�� ������ ũ��Ƽ�� Ȯ���� ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ "+main.Main_ServerOption.statWILL+"�� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "WILL");
			}
			return;
		case "WILL"://WILL
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setWILL(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7("+main.Main_ServerOption.statLUK+"�� ������ ũ��Ƽ�� Ȯ���� ũ�� ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ "+main.Main_ServerOption.statLUK+"�� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "LUK");
			}
			return;
		case "DEF"://Defense
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setDEF(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(��ȣ�� ������ ���� ���׷��� ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ ��ȣ�� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "Protect");
			}
			return;
		case "Protect"://Protect
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setPRO(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(���� ���� ������ ���� ������ ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ ���� �� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "Magic_DEF");
			}
			return;
		case "Magic_DEF"://MagicDefense
			if(isIntMinMax(message, player, 0, Integer.MAX_VALUE))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				main.Main_ServerOption.MonsterList.get(u.getString(player, (byte)2)).setMDEF(Integer.parseInt(message));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(���� ��ȣ�� ������ ���� ���׷��� ��½��� �ݴϴ�.)");
				player.sendMessage("��a[����] : ������ ���� ��ȣ�� ������ �ּ���!");
				player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "Magic_Protect");
			}
			return;
		case "Head.DropChance"://DropChance
			if(isIntMinMax(message, player, 0, 1000))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(Ȯ�� ��� : 1000 = 100%, 1 = 0.1%)");
				player.sendMessage("��a[����] : ������ ���� ������� ������ �ּ���!");
				player.sendMessage("��3(0 ~ 1000)");
				u.setString(player, (byte)1, "Chest.DropChance");
			}
			return;
		case "Chest.DropChance"://DropChance
			if(isIntMinMax(message, player, 0, 1000))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(Ȯ�� ��� : 1000 = 100%, 1 = 0.1%)");
				player.sendMessage("��a[����] : ������ ���� ������� ������ �ּ���!");
				player.sendMessage("��3(0 ~ 1000)");
				u.setString(player, (byte)1, "Leggings.DropChance");
			}
			return;
		case "Leggings.DropChance"://DropChance
			if(isIntMinMax(message, player, 0, 1000))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(Ȯ�� ��� : 1000 = 100%, 1 = 0.1%)");
				player.sendMessage("��a[����] : ������ ���� ������� ������ �ּ���!");
				player.sendMessage("��3(0 ~ 1000)");
				u.setString(player, (byte)1, "Boots.DropChance");
			}
			return;
		case "Boots.DropChance"://DropChance
			if(isIntMinMax(message, player, 0, 1000))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��7(Ȯ�� ��� : 1000 = 100%, 1 = 0.1%)");
				player.sendMessage("��a[����] : ������ ���� ������� ������ �ּ���!");
				player.sendMessage("��3(0 ~ 1000)");
				u.setString(player, (byte)1, "Hand.DropChance");
			}
			return;
		case "Hand.DropChance"://DropChance
			if(isIntMinMax(message, player, 0, 1000))
			{
				monsterListYaml.set(u.getString(player, (byte)2)+"."+u.getString(player, (byte)1), Integer.parseInt(message));
				monsterListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
				MGUI.MonsterOptionSettingGUI(player, u.getString(player, (byte)2));
				u.clearAll(player);
			}
			return;
		}
		return;
	}
	
}
