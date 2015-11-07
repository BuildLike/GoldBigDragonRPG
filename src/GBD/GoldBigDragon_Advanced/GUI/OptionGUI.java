package GBD.GoldBigDragon_Advanced.GUI;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;

public class OptionGUI extends GUIutil
{
	public void optionGUI(Player player)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
	    YamlManager YM;
	  	if(GUI_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		stat.CreateNewStats(player);

		YM = GUI_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.BLACK + "�ɼ�");

		Stack2(ChatColor.WHITE + "����", 397,3,1,Arrays.asList(ChatColor.GRAY + "������ Ȯ���մϴ�."), 0, inv);
		Stack2(ChatColor.WHITE + "��ų", 403,0,1,Arrays.asList(ChatColor.GRAY + "��ų�� Ȯ���մϴ�."), 9, inv);
		Stack2(ChatColor.WHITE + "����Ʈ", 358,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� ����Ʈ�� Ȯ���մϴ�."), 18, inv);
		Stack2(ChatColor.WHITE + "�ɼ�", 160,4,1,Arrays.asList(ChatColor.GRAY + "��Ÿ ������ �մϴ�."), 27, inv);
		Stack2(ChatColor.WHITE + "��Ÿ", 354,0,1,Arrays.asList(ChatColor.GRAY + "��Ÿ ������ Ȯ���մϴ�."), 36, inv);
		
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 1, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 7, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 10, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 16, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 19, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 25, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 28, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 34, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 37, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 43, inv);

		if(YM.getBoolean("Alert.EXPget") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "����ġ ȹ�� �˸�", 384,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"����ġ ȹ���� �˸��ϴ�."), 2, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "����ġ ȹ�� �˸�", 384,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"����ġ ȹ���� �˸��� �ʽ��ϴ�."), 2, inv);}
		if(YM.getBoolean("Alert.ItemPickUp") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ ȹ�� �˸�", 154,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"������ ȹ���� �˸��ϴ�."), 3, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ ȹ�� �˸�", 154,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"������ ȹ���� �˸��� �ʽ��ϴ�."), 3, inv);}
		if(YM.getBoolean("Alert.MobHealth") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �����", 381,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� ���� ��Ȳ�� ���ϴ�."), 4, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �����", 381,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� ���� ��Ȳ�� ���ϴ�."), 4, inv);}
		if(YM.getBoolean("Alert.Damage") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ �˸�", 267,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"������ ���� ���ظ� �˸��ϴ�."), 5, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ �˸�", 267,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"������ ���� ���ظ� �˸��� �ʽ��ϴ�."), 5, inv);}
		if(YM.getBoolean("Alert.AttackDelay") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ������ �˸�", 347,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� �ӵ��� �˸��ϴ�."), 6, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ������ �˸�", 347,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� �ӵ��� �˸��� �ʽ��ϴ�."), 6, inv);}
		if(YM.getBoolean("Option.EquipLook") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ����", 416,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"�ٸ� �÷��̾ �ڽ��� ���",ChatColor.GRAY+"������ �� �ֽ��ϴ�."), 11, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ����", 416,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"�ٸ� �÷��̾ �ڽ��� ���",ChatColor.GRAY+"������ �� �����ϴ�."), 11, inv);}

		switch(YM.getInt("Option.ChattingType"))
		{
		case 0:
			Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "ä�� �ɼ�", 2264,0,1,Arrays.asList(ChatColor.WHITE + "[�Ϲ�]",ChatColor.GRAY+"�Ϲ����� ä���� �մϴ�."), 12, inv);
			break;
		case 1:
			Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "ä�� �ɼ�", 2261,0,1,Arrays.asList(ChatColor.BLUE + "[��Ƽ]",ChatColor.GRAY+"��Ƽ ä���� �մϴ�."), 12, inv);
			break;
		case 2:
			Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "ä�� �ɼ�", 2260,0,1,Arrays.asList(ChatColor.GREEN + "[����]",ChatColor.GRAY+"�� �ϰ� ������ ��Ⱑ ���� ������...."), 12, inv);
			break;
		case 3:
			Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "ä�� �ɼ�", 2258,0,1,Arrays.asList(ChatColor.LIGHT_PURPLE + "[������]",ChatColor.GRAY+"������ ������ ä���� �մϴ�.",ChatColor.RED + "�� �Ϲ� ������ ����� �� �����ϴ�."), 12, inv);
			break;
		}
		if(YM.getBoolean("Option.HotBarSound") == true){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ��ȯ ����", 307,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"�ֹٸ� ������ �� ���� �Ҹ��� ���ϴ�."), 13, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ��ȯ ����", 166,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"�ֹٸ� �������� �Ҹ��� ���� �ʽ��ϴ�."), 13, inv);}

		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 26, inv);

		player.openInventory(inv);
	}
	
	public void optionInventoryclick(InventoryClickEvent event)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
	    YamlManager YM;
		StatsGUI SGUI = new StatsGUI();
		ETCGUI EGUI = new ETCGUI();
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();
		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		
	  	if(GUI_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		stat.CreateNewStats(player);

		YM = GUI_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		
		switch (event.getSlot())
		{
			case 0: //����
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				SGUI.StatusGUI(player);
				break;
			case 36://��Ÿ
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				EGUI.ETCGUI_Main(player);
				break;
			case 9://��ų
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				PlayerSkillGUI PGUI = new PlayerSkillGUI();
				PGUI.MainSkillsListGUI(player, 0);
				break;
			case 18://����Ʈ
				GBD.GoldBigDragon_Advanced.GUI.QuestGUI QGUI = new GBD.GoldBigDragon_Advanced.GUI.QuestGUI();
				QGUI.MyQuestListGUI(player, 0);
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				break;
			case 26://�ݱ�
				s.SP(player, Sound.PISTON_RETRACT, 0.8F, 1.8F);
				player.closeInventory();
				break;
			case 2://����ġ ȹ�� �˸�
				if(YM.getBoolean("Alert.EXPget") == true){YM.set("Alert.EXPget", false);}
				else{YM.set("Alert.EXPget", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 3://������ ȹ�� �˸�
				if(YM.getBoolean("Alert.ItemPickUp") == true){YM.set("Alert.ItemPickUp", false);}
				else{YM.set("Alert.ItemPickUp", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 4://���� �����
				if(YM.getBoolean("Alert.MobHealth") == true){YM.set("Alert.MobHealth", false);}
				else{YM.set("Alert.MobHealth", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 5://������ �˸�
				if(YM.getBoolean("Alert.Damage") == true){YM.set("Alert.Damage", false);}
				else{YM.set("Alert.Damage", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 6://���� ������ �˸�
				if(YM.getBoolean("Alert.AttackDelay") == true){YM.set("Alert.AttackDelay", false);}
				else{YM.set("Alert.AttackDelay", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 11://��� ����
				if(YM.getBoolean("Option.EquipLook") == true){YM.set("Option.EquipLook", false);}
				else{YM.set("Option.EquipLook", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 12://ä�� �ɼ�
				if(YM.getInt("Option.ChattingType") < 3)
				{
					YM.set("Option.ChattingType", (YM.getInt("Option.ChattingType")+1));
				}
				else
				{
					YM.set("Option.ChattingType", 0);
				}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
			case 13://��� ��ȯ ����
				if(YM.getBoolean("Option.HotBarSound") == true){YM.set("Option.HotBarSound", false);}
				else{YM.set("Option.HotBarSound", true);}
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				YM.saveConfig();
				optionGUI(player);
				break;
		}
		return;
	}
	
}
