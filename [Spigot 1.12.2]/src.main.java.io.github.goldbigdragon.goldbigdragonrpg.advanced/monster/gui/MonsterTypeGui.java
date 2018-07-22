package monster.gui;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import util.GuiUtil;
import util.YamlLoader;

public class MonsterTypeGui extends GuiUtil{

	String uniqueCode = "��1��0��8��0��b��r";
	
	public void monsterTypeGui(Player player, String monsterName, int page)
	{
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� Ÿ�� : " + (page+1) + " / 2");

		if(page==0)
		{
			monsterStack(inv, 0, 54,  0, "��2��l[����]", Arrays.asList("��0"+54));
			monsterStack(inv, 1, 27,  0, "��2��l[�ֹ�����]", Arrays.asList("��0"+27));
			monsterStack(inv, 2, 53,  0, "��2��l[���̾�Ʈ]", Arrays.asList("��0"+53));
			monsterStack(inv, 3, 29,  0, "��2��l[����]", Arrays.asList("��0"+29));
			monsterStack(inv, 4, 57,  0, "��d��l[�����Ǳ׸�]", Arrays.asList("��0"+57));
			monsterStack(inv, 5, 23,  0, "��e��l[�㽺ũ]", Arrays.asList("��0"+23));
			monsterStack(inv, 6, 50,  0, "��a��l[ũ����]", Arrays.asList("��0"+50));
			monsterStack(inv, 7, 50,  1, "��a��l[����ũ����]", Arrays.asList("��0"+50));
			monsterStack(inv, 8, 51,  0, "��f��l[���̷���]", Arrays.asList("��0"+51));
			monsterStack(inv, 9, 5,  0, "��8��l[�״����̷���]", Arrays.asList("��0"+5));
			monsterStack(inv, 10, 28,  0, "��f��l[���̷��渻]", Arrays.asList("��0"+28));
			monsterStack(inv, 11, 6,  0, "��b��l[��Ʈ����]", Arrays.asList("��0"+6));
			monsterStack(inv, 12, 64,  0, "��8��l[����]", Arrays.asList("��0"+64));
			monsterStack(inv, 13, 52,  0, "��7��l[�Ź�]", Arrays.asList("��0"+52));
			monsterStack(inv, 14, 59,  1, "��7��l[�����Ź�]", Arrays.asList("��0"+59));
			monsterStack(inv, 13, 55,  0, "��a��l[����������]", Arrays.asList("��0"+55));
			monsterStack(inv, 14, 55,  1, "��a��l[���뽽����]", Arrays.asList("��0"+55));
			monsterStack(inv, 15, 55,  2, "��a��l[ū������]", Arrays.asList("��0"+55));
			monsterStack(inv, 16, 55,  3, "��a��l[Ư�뽽����]", Arrays.asList("��0"+55));
			monsterStack(inv, 17, 55,  4, "��a��l[�ʴ���������]", Arrays.asList("��0"+55));
			monsterStack(inv, 18, 62,  0, "��7��l[�������׸�ť��]", Arrays.asList("��0"+62));
			monsterStack(inv, 19, 62,  1, "��7��l[���븶�׸�ť��]", Arrays.asList("��0"+62));
			monsterStack(inv, 20, 62,  2, "��7��l[ū���׸�ť��]", Arrays.asList("��0"+62));
			monsterStack(inv, 21, 62,  3, "��7��l[Ư�븶�׸�ť��]", Arrays.asList("��0"+62));
			monsterStack(inv, 22, 62,  4, "��7��l[�ʴ������׸�ť��]", Arrays.asList("��0"+62));
			monsterStack(inv, 23, 65,  0, "��8��l[����]", Arrays.asList("��0"+65));
			monsterStack(inv, 24, 56,  0, "��f��l[����Ʈ]", Arrays.asList("��0"+56));
			monsterStack(inv, 25, 61,  0, "��e��l[������]", Arrays.asList("��0"+61));
			monsterStack(inv, 26, 60,  0, "��7��l[������]", Arrays.asList("��0"+60));
			monsterStack(inv, 27, 67,  0, "��5��l[���������]", Arrays.asList("��0"+67));
			monsterStack(inv, 28, 120,  0, "��a��l[�ֹ�]", Arrays.asList("��0"+120));
			monsterStack(inv, 29, 66,  0, "��5��l[����]", Arrays.asList("��0"+66));
			monsterStack(inv, 30, 36,  0, "��7��l[������]", Arrays.asList("��0"+36));
			monsterStack(inv, 31, 34,  0, "��5��l[��ȯ��]", Arrays.asList("��0"+34));
			monsterStack(inv, 32, 35,  0, "��9��l[����]", Arrays.asList("��0"+35));
			monsterStack(inv, 33, 68,  0, "��3��l[�����]", Arrays.asList("��0"+68));
			monsterStack(inv, 34, 4,  0, "��3��l[���������]", Arrays.asList("��0"+4));
			monsterStack(inv, 35, 58,  0, "��8��l[������]", Arrays.asList("��0"+58));
			monsterStack(inv, 36, 69,  0, "��5��l[��Ŀ]", Arrays.asList("��0"+69));
			monsterStack(inv, 37, 63,  0, "��8��l[�����巡��]", Arrays.asList("��0"+63));
			monsterStack(inv, 39, 90,  0, "��d��l[����]", Arrays.asList("��0"+90));
			monsterStack(inv, 40, 91,  0, "��f��l[��]", Arrays.asList("��0"+91));
			monsterStack(inv, 41, 92,  0, "��7��l[��]", Arrays.asList("��0"+92));
			monsterStack(inv, 42, 96,  0, "��c��l[������]", Arrays.asList("��0"+96));
			monsterStack(inv, 43, 93,  0, "��f��l[��]", Arrays.asList("��0"+93));
			monsterStack(inv, 44, 94,  0, "��8��l[��¡��]", Arrays.asList("��0"+94));
		  	removeFlagStack("��f��l���� ������", 323, 0, 1, null, 50, inv);
		}
		else
		{
			monsterStack(inv, 0, 95,  0, "��f��l[����]", Arrays.asList("��0"+95));
			monsterStack(inv, 1, 98,  0, "��e��l[������]", Arrays.asList("��0"+98));
			monsterStack(inv, 2, 97,  0, "��f��l[�����]", Arrays.asList("��0"+97));
			monsterStack(inv, 3, 99,  0, "��f��l[ö��]", Arrays.asList("��0"+99));
			monsterStack(inv, 4, 101,  0, "��f��l[�䳢]", Arrays.asList("��0"+101));
			monsterStack(inv, 5, 102,  0, "��f��l[�ϱذ�]", Arrays.asList("��0"+102));
			monsterStack(inv, 6, 31,  0, "��6��l[�糪��]", Arrays.asList("��0"+31));
			monsterStack(inv, 7, 32,  0, "��6��l[���]", Arrays.asList("��0"+32));
			monsterStack(inv, 8, 103,  0, "��6��l[��]", Arrays.asList("��0"+103));
			monsterStack(inv, 9, 100,  0, "��6��l[��]", Arrays.asList("��0"+100));
		  	removeFlagStack("��f��l���� ������", 323, 0, 1, null, 48, inv);
		}

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+monsterName), 53, inv);
		player.openInventory(inv);
	}

	private void monsterStack(Inventory inv, int loc, int monsterID, int option, String displayName, List<String> lore)
	{
		int id = 383;
		int data = 0;
		int amount = 1;

		if(monsterID==4)//���� �����
		{
			id=409;
			amount = 2;
		}
		else if(monsterID==5)//���� ���̷���
			id=263;
		else if(monsterID==6)//��Ʈ����
			id=440;
		else if(monsterID==23)//�㽺ũ
		{
			id=24;
			data = 1;
		}
		else if(monsterID==27)//�ֹ� ����
		{
			id=367;
			amount = 2;
		}
		else if(monsterID==28)//���̷��� ��
		{
			id=352;
			amount = 2;
		}
		else if(monsterID==29)//���� ��
		{
			id=367;
			amount = 4;
		}
		else if(monsterID==31)//�糪��
			id=54;
		else if(monsterID==32)//�¿�
		{
			id=54;
			amount = 1;
		}
		else if(monsterID==34)//����Ŀ
			id=449;
		else if(monsterID==35)//����
			id=452;
		else if(monsterID==36)//���������
			id=258;
		else if(monsterID==49)//�ΰ�
		{
			id=397;
			data = 3;
		}
		else if(monsterID==50)//ũ����
		{
			id=289;
			if(option==1)//���� ũ����
				amount = 2;
		}
		else if(monsterID==51)//���̷���
			id=352;
		else if(monsterID==52)//�Ź�
			id=287;
		else if(monsterID==53)//���̾�Ʈ
		{
			id=367;
			amount = 3;
		}
		else if(monsterID==54)//����
			id=367;
		else if(monsterID==55)//������
		{
			id=341;
			amount =  (option+amount);
		}
		else if(monsterID==56)//����Ʈ
			id=370;
		else if(monsterID==57)//���� �Ǳ׸�
			id=283;
		else if(monsterID==58)//������
			id=368;
		else if(monsterID==59)//�����Ź�
		{
			id=287;
			amount = 2;
		}
		else if(monsterID==60)//������
			id=1;
		else if(monsterID==61)//������
			id=369;
		else if(monsterID==62)//���׸� ť��
		{
			id=378;
			amount =  (option+amount);
		}
		else if(monsterID==63)//���� �巡��
			id=122;
		else if(monsterID==64)//����
			id=399;
		else if(monsterID==65)//����
			id=362;
		else if(monsterID==66)//����
			id=438;
		else if(monsterID==67)//���� ����Ʈ
			id=432;
		else if(monsterID==68)//�����
			id=409;
		else if(monsterID==69)//��Ŀ
			id=450;
		else if(monsterID==90)//����
			id=319;
		else if(monsterID==91)//��
			id=423;
		else if(monsterID==92)//��
			id=363;
		else if(monsterID==93)//��
			id=365;
		else if(monsterID==94)//��¡��
			id=351;
		else if(monsterID==95)//����
			id=280;
		else if(monsterID==96)//������
			id=40;
		else if(monsterID==97)//�����
			id=332;
		else if(monsterID==98)//������
			id=349;
		else if(monsterID==99)//ö��
			id=265;
		else if(monsterID==100)//��
			id=417;
		else if(monsterID==101)//�䳢
			id=411;
		else if(monsterID==102)//�ϱذ�
		{
			id=349;
			data = 1;
		}
		else if(monsterID==103)//��
		{
			id=54;
			amount = 2;
		}
		else if(monsterID==120)//�ֹ�
			id=388;
		else if(monsterID==200)//���� ũ����Ż
			id=426;
		
		removeFlagStack(displayName, id, data, amount, lore, loc, inv);
		return;
	}

	public void monsterTypeClick(InventoryClickEvent event)
	{
		event.setCancelled(true);
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			int page = (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1].split(" / ")[0]));
			String monsterName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
			
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				new MonsterOptionSettingGui().monsterOptionSettingGui(player, monsterName);
			else if(slot == 48)//���� ������
				monsterTypeGui(player, monsterName, page-2);
			else if(slot == 50)//���� ������
				monsterTypeGui(player, monsterName, page);
			else
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String type = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				type = type.substring(1, type.length()-1);
				monsterListYaml.set(monsterName+".Type", type);
				monsterListYaml.saveConfig();
				new MonsterOptionSettingGui().monsterOptionSettingGui(player, monsterName);
			}
		}
	}

}
