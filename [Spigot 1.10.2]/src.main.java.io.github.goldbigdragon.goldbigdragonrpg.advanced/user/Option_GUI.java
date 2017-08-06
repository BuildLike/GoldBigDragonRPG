package user;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import skill.UserSkill_GUI;
import util.Util_GUI;

public class Option_GUI extends Util_GUI
{
	public void optionGUI(Player player)
	{
		String UniqueCode = "��0��0��0��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode+"��0�ɼ�");

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

		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_EXPget()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��, ����ġ ȹ�� �˸�", 384,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� ����ġ ȹ���� �˸��ϴ�."), 2, inv);}
			else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��, ����ġ ȹ�� �˸�", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� ����ġ ȹ���� �˸��� �ʽ��ϴ�."), 2, inv);}
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_ItemPickUp()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ ȹ�� �˸�", 154,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"������ ȹ���� �˸��ϴ�."), 3, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ ȹ�� �˸�", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"������ ȹ���� �˸��� �ʽ��ϴ�."), 3, inv);}
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_MobHealth()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �����", 381,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� ���� ��Ȳ�� ���ϴ�."), 4, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �����", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� ���� ��Ȳ�� ���� �ʽ��ϴ�."), 4, inv);}
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_Damage()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ �˸�", 267,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"������ ���� ���ظ� �˸��ϴ�."), 5, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "������ �˸�", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"������ ���� ���ظ� �˸��� �ʽ��ϴ�."), 5, inv);}
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "Ŭ���� ���", 438,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"�Һ� �������� Ŭ���� ����մϴ�."), 6, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "Ŭ���� ���", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"�Һ� �������� ����Ű ó�� ����մϴ�."), 6, inv);}
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "BGM ���", 2256,0,1,Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY+"���� BGM�� ���� ��ŵ�ϴ�."), 14, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "BGM ���", 166,0,1,Arrays.asList(ChatColor.RED + "[��Ȱ��ȭ]",ChatColor.GRAY+"���� BGM�� ���� �ʽ��ϴ�."), 14, inv);}

		
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_EquipLook()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ����", 416,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"�ٸ� �÷��̾ �ڽ��� ���",ChatColor.GRAY+"������ �� �ֽ��ϴ�."), 11, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ����", 166,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"�ٸ� �÷��̾ �ڽ��� ���",ChatColor.GRAY+"������ �� �����ϴ�."), 11, inv);}

		switch(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType())
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
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ��ȯ ����", 307,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"�ֹٸ� ������ �� ���� �Ҹ��� ���ϴ�."), 13, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "��� ��ȯ ����", 166,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"�ֹٸ� �������� �Ҹ��� ���� �ʽ��ϴ�."), 13, inv);}

		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_SeeInventory()){Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "�� Ŭ���� ���� ���� ����", 307,0,1,Arrays.asList(ChatColor.GREEN + "[���]",ChatColor.GRAY+"����� �� Ŭ���ϸ� ����â�� ��ϴ�."), 15, inv);}
		else{Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "�� Ŭ���� ���� ���� ����", 166,0,1,Arrays.asList(ChatColor.RED + "[�����]",ChatColor.GRAY+"����� �� Ŭ���Ͽ��� ����â�� ���� �ʽ��ϴ�."), 15, inv);}

		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 26, inv);

		player.openInventory(inv);
	}
	
	public void optionInventoryclick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		int slot = event.getSlot();

		if(event.getSlot()==26)
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)
				new user.Stats_GUI().StatusGUI(player);
			else if(slot == 9)
				new UserSkill_GUI().MainSkillsListGUI(player, (short) 0);
			else if(slot == 18)
				new quest.Quest_GUI().MyQuestListGUI(player, (short) 0);
			else if(slot == 36)
				new ETC_GUI().ETCGUI_Main(player);
			else
			{
				if(slot == 2)//����ġ ȹ�� �˸�
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_EXPget(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_EXPget()==false);
				else if(slot == 3)//������ ȹ�� �˸�
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_ItemPickUp(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_ItemPickUp()==false);
				else if(slot == 4)//���� �����
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_MobHealth(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_MobHealth()==false);
				else if(slot == 5)//������ �˸�
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_Damage(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_Damage()==false);
				else if(slot == 6)//Ŭ���� ���
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setClickUse(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()==false);
				else if(slot == 11)//��� ����
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_EquipLook(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_EquipLook()==false);
				else if(slot == 12)//ä�� �ɼ�
				{
					if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType() < 3)
						main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_ChattingType((byte) (main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType()+1));
					else
						main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_ChattingType((byte) (0));
				}
				else if(slot == 13)//��� ��ȯ ����
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_HotBarSound(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound()==false);
				else if(slot == 14)//BGM ���
				{
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setBgm(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn()==false);
					new otherplugins.NoteBlockAPIMain().Stop(player);
				}
				else if(slot == 15)//��Ŭ���� ���� ���� ����
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_SeeInventory(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_SeeInventory()==false);
				
				optionGUI(player);
			}
		}
		return;
	}
	
}
