package user;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import skill.UserSkillGui;
import util.UtilGui;

public class OptionGui extends UtilGui
{
	public void optionGUI(Player player)
	{
		String UniqueCode = "��0��0��0��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode+"��0�ɼ�");

		removeFlagStack("��f����", 397,3,1,Arrays.asList("��7������ Ȯ���մϴ�."), 0, inv);
		removeFlagStack("��f��ų", 403,0,1,Arrays.asList("��7��ų�� Ȯ���մϴ�."), 9, inv);
		removeFlagStack("��f����Ʈ", 358,0,1,Arrays.asList("��7���� �������� ����Ʈ�� Ȯ���մϴ�."), 18, inv);
		removeFlagStack("��f�ɼ�", 160,4,1,Arrays.asList("��7��Ÿ ������ �մϴ�."), 27, inv);
		removeFlagStack("��f��Ÿ", 354,0,1,Arrays.asList("��7��Ÿ ������ Ȯ���մϴ�."), 36, inv);
		
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 1, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 7, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 10, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 16, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 19, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 25, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 28, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 34, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 37, inv);
		removeFlagStack("��c ", 66,0,1,Arrays.asList(""), 43, inv);

		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_EXPget()){removeFlagStack("��f��l��, ����ġ ȹ�� �˸�", 384,0,1,Arrays.asList("��a[Ȱ��ȭ]","��7���� ����ġ ȹ���� �˸��ϴ�."), 2, inv);}
			else{removeFlagStack("��f��l��, ����ġ ȹ�� �˸�", 166,0,1,Arrays.asList("��c[��Ȱ��ȭ]","��7���� ����ġ ȹ���� �˸��� �ʽ��ϴ�."), 2, inv);}
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_ItemPickUp()){removeFlagStack("��f��l������ ȹ�� �˸�", 154,0,1,Arrays.asList("��a[Ȱ��ȭ]","��7������ ȹ���� �˸��ϴ�."), 3, inv);}
		else{removeFlagStack("��f��l������ ȹ�� �˸�", 166,0,1,Arrays.asList("��c[��Ȱ��ȭ]","��7������ ȹ���� �˸��� �ʽ��ϴ�."), 3, inv);}
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_MobHealth()){removeFlagStack("��f��l���� �����", 381,0,1,Arrays.asList("��a[Ȱ��ȭ]","��7���� ���� ��Ȳ�� ���ϴ�."), 4, inv);}
		else{removeFlagStack("��f��l���� �����", 166,0,1,Arrays.asList("��c[��Ȱ��ȭ]","��7���� ���� ��Ȳ�� ���� �ʽ��ϴ�."), 4, inv);}
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_Damage()){removeFlagStack("��f��l������ �˸�", 267,0,1,Arrays.asList("��a[Ȱ��ȭ]","��7������ ���� ���ظ� �˸��ϴ�."), 5, inv);}
		else{removeFlagStack("��f��l������ �˸�", 166,0,1,Arrays.asList("��c[��Ȱ��ȭ]","��7������ ���� ���ظ� �˸��� �ʽ��ϴ�."), 5, inv);}
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()){removeFlagStack("��f��lŬ���� ���", 438,0,1,Arrays.asList("��a[Ȱ��ȭ]","��7�Һ� �������� Ŭ���� ����մϴ�."), 6, inv);}
		else{removeFlagStack("��f��lŬ���� ���", 166,0,1,Arrays.asList("��c[��Ȱ��ȭ]","��7�Һ� �������� ����Ű ó�� ����մϴ�."), 6, inv);}
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn()){removeFlagStack("��f��lBGM ���", 2256,0,1,Arrays.asList("��a[Ȱ��ȭ]","��7���� BGM�� ���� ��ŵ�ϴ�."), 14, inv);}
		else{removeFlagStack("��f��lBGM ���", 166,0,1,Arrays.asList("��c[��Ȱ��ȭ]","��7���� BGM�� ���� �ʽ��ϴ�."), 14, inv);}

		
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_EquipLook()){removeFlagStack("��f��l��� ����", 416,0,1,Arrays.asList("��a[���]","��7�ٸ� �÷��̾ �ڽ��� ���","��7������ �� �ֽ��ϴ�."), 11, inv);}
		else{removeFlagStack("��f��l��� ����", 166,0,1,Arrays.asList("��c[�����]","��7�ٸ� �÷��̾ �ڽ��� ���","��7������ �� �����ϴ�."), 11, inv);}

		switch(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType())
		{
		case 0:
			removeFlagStack("��f��lä�� �ɼ�", 2264,0,1,Arrays.asList("��f[�Ϲ�]","��7�Ϲ����� ä���� �մϴ�."), 12, inv);
			break;
		case 1:
			removeFlagStack("��f��lä�� �ɼ�", 2261,0,1,Arrays.asList("��9[��Ƽ]","��7��Ƽ ä���� �մϴ�."), 12, inv);
			break;
		case 2:
			removeFlagStack("��f��lä�� �ɼ�", 2260,0,1,Arrays.asList("��a[����]","��7�� �ϰ� ������ ��Ⱑ ���� ������...."), 12, inv);
			break;
		case 3:
			removeFlagStack("��f��lä�� �ɼ�", 2258,0,1,Arrays.asList("��d[������]","��7������ ������ ä���� �մϴ�.","��c�� �Ϲ� ������ ����� �� �����ϴ�."), 12, inv);
			break;
		}
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound()){removeFlagStack("��f��l��� ��ȯ ����", 307,0,1,Arrays.asList("��a[���]","��7�ֹٸ� ������ �� ���� �Ҹ��� ���ϴ�."), 13, inv);}
		else{removeFlagStack("��f��l��� ��ȯ ����", 166,0,1,Arrays.asList("��c[�����]","��7�ֹٸ� �������� �Ҹ��� ���� �ʽ��ϴ�."), 13, inv);}

		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_SeeInventory()){removeFlagStack("��f��l�� Ŭ���� ���� ���� ����", 307,0,1,Arrays.asList("��a[���]","��7����� �� Ŭ���ϸ� ����â�� ��ϴ�."), 15, inv);}
		else{removeFlagStack("��f��l�� Ŭ���� ���� ���� ����", 166,0,1,Arrays.asList("��c[�����]","��7����� �� Ŭ���Ͽ��� ����â�� ���� �ʽ��ϴ�."), 15, inv);}

		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 26, inv);

		player.openInventory(inv);
	}
	
	public void optionInventoryclick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		int slot = event.getSlot();

		if(event.getSlot()==26)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)
				new user.StatsGui().StatusGUI(player);
			else if(slot == 9)
				new UserSkillGui().mainSkillsListGUI(player, (short) 0);
			else if(slot == 18)
				new quest.QuestGui().MyQuestListGUI(player, (short) 0);
			else if(slot == 36)
				new EtcGui().ETCGUI_Main(player);
			else
			{
				if(slot == 2)//����ġ ȹ�� �˸�
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_EXPget(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_EXPget()==false);
				else if(slot == 3)//������ ȹ�� �˸�
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_ItemPickUp(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_ItemPickUp()==false);
				else if(slot == 4)//���� �����
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_MobHealth(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_MobHealth()==false);
				else if(slot == 5)//������ �˸�
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setAlert_Damage(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isAlert_Damage()==false);
				else if(slot == 6)//Ŭ���� ���
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setClickUse(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()==false);
				else if(slot == 11)//��� ����
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_EquipLook(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_EquipLook()==false);
				else if(slot == 12)//ä�� �ɼ�
				{
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType() < 3)
						main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_ChattingType((byte) (main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getOption_ChattingType()+1));
					else
						main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_ChattingType((byte) (0));
				}
				else if(slot == 13)//��� ��ȯ ����
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_HotBarSound(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound()==false);
				else if(slot == 14)//BGM ���
				{
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setBgm(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn()==false);
					new otherplugins.NoteBlockApiMain().Stop(player);
				}
				else if(slot == 15)//��Ŭ���� ���� ���� ����
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setOption_SeeInventory(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_SeeInventory()==false);
				
				optionGUI(player);
			}
		}
		return;
	}
}
