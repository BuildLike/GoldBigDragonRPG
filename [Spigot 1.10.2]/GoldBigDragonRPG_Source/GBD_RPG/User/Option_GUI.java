package GBD_RPG.User;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import GBD_RPG.Main_Main.Main_ServerOption;
import GBD_RPG.Skill.UserSkill_GUI;
import GBD_RPG.Util.Util_GUI;
import GBD_RPG.Util.YamlController;

public class Option_GUI extends Util_GUI
{
	public void optionGUI(Player player)
	{
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

		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_EXPget()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��, ����ġ ȹ�� �˸�", 384,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� ����ġ ȹ���� �˸��ϴ�."), 2, inv);}
			else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��, ����ġ ȹ�� �˸�", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� ����ġ ȹ���� �˸��� �ʽ��ϴ�."), 2, inv);}
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_ItemPickUp()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ ȹ�� �˸�", 154,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"������ ȹ���� �˸��ϴ�."), 3, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ ȹ�� �˸�", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"������ ȹ���� �˸��� �ʽ��ϴ�."), 3, inv);}
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_MobHealth()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �����", 381,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� ���� ��Ȳ�� ���ϴ�."), 4, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �����", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� ���� ��Ȳ�� ���� �ʽ��ϴ�."), 4, inv);}
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_Damage()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ �˸�", 267,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"������ ���� ���ظ� �˸��ϴ�."), 5, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ �˸�", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"������ ���� ���ظ� �˸��� �ʽ��ϴ�."), 5, inv);}
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "Ŭ���� ���", 438,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"�Һ� �������� Ŭ���� ����մϴ�."), 6, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "Ŭ���� ���", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"�Һ� �������� ����Ű ó�� ����մϴ�."), 6, inv);}
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "BGM ���", 2256,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� BGM�� ���� ��ŵ�ϴ�."), 14, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "BGM ���", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� BGM�� ���� �ʽ��ϴ�."), 14, inv);}

		
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_EquipLook()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ����", 416,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"�ٸ� �÷��̾ �ڽ��� ���",ChatColor.GRAY+"������ �� �ֽ��ϴ�."), 11, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ����", 166,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"�ٸ� �÷��̾ �ڽ��� ���",ChatColor.GRAY+"������ �� �����ϴ�."), 11, inv);}

		switch(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType())
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
		if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ��ȯ ����", 307,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"�ֹٸ� ������ �� ���� �Ҹ��� ���ϴ�."), 13, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ��ȯ ����", 166,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"�ֹٸ� �������� �Ҹ��� ���� �ʽ��ϴ�."), 13, inv);}

		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 26, inv);

		player.openInventory(inv);
	}
	
	public void optionInventoryclick(InventoryClickEvent event)
	{
		YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		Stats_GUI SGUI = new Stats_GUI();
		ETC_GUI EGUI = new ETC_GUI();
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		
		switch (event.getSlot())
		{
			case 0: //����
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				SGUI.StatusGUI(player);
				break;
			case 36://��Ÿ
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				EGUI.ETCGUI_Main(player);
				break;
			case 9://��ų
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				UserSkill_GUI PGUI = new UserSkill_GUI();
				PGUI.MainSkillsListGUI(player, (short) 0);
				break;
			case 18://����Ʈ
				GBD_RPG.Quest.Quest_GUI QGUI = new GBD_RPG.Quest.Quest_GUI();
				QGUI.MyQuestListGUI(player, (short) 0);
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				break;
			case 26://�ݱ�
				s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
				break;
			case 2://����ġ ȹ�� �˸�
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_EXPget()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_EXPget(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_EXPget(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 3://������ ȹ�� �˸�
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_ItemPickUp()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_ItemPickUp(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_ItemPickUp(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 4://���� �����
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_MobHealth()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_MobHealth(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_MobHealth(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 5://������ �˸�
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_Damage()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_Damage(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_Damage(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 6://Ŭ���� ���
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setClickUse(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setClickUse(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 11://��� ����
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_EquipLook()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_EquipLook(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_EquipLook(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 12://ä�� �ɼ�
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType() < 3)
					GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_ChattingType((byte) (GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType()+1));
				else
					GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_ChattingType((byte) (0));
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 13://��� ��ȯ ����
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_HotBarSound(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_HotBarSound(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				optionGUI(player);
				break;
			case 14://BGM ���
				if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn()){GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setBgm(false);}
				else{GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setBgm(true);}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(Main_ServerOption.NoteBlockAPIAble)
					new OtherPlugins.NoteBlockAPIMain().Stop(player);
				optionGUI(player);
				break;
		}
		return;
	}
	
}
