package monster.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import battle.BattleCalculator;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class MonsterOptionSettingGui extends GuiUtil{

	private String uniqueCode = "��0��0��8��0��1��r";
	
	public void monsterOptionSettingGui(Player player,String monsterName)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ����");

		String lore=null;			
		lore = "%enter%��f��l �̸� : ��f"+monsterListYaml.getString(monsterName+".Name")+"%enter%";
		lore = lore+"��f��l Ÿ�� : ��f"+monsterListYaml.getString(monsterName+".Type")+"%enter%";
		lore = lore+"��f��l ���� ���̿� : ��f"+monsterListYaml.getString(monsterName+".Biome")+"%enter%";
		lore = lore+"��c��l ����� : ��f"+monsterListYaml.getInt(monsterName+".HP")+"%enter%";
		lore = lore+"��b��l ����ġ : ��f"+monsterListYaml.getInt(monsterName+".EXP")+"%enter%";
		lore = lore+"��e��l ��� �ݾ� : ��f"+monsterListYaml.getInt(monsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(monsterName+".MAX_Money")+"%enter%";
		lore = lore+"��c��l "+MainServerOption.statSTR+" : ��f"+monsterListYaml.getInt(monsterName+".STR")
		+"��7 [���� : " + BattleCalculator.getCombatDamage(null, 0, monsterListYaml.getInt(monsterName+".STR"), true) + " ~ " + BattleCalculator.getCombatDamage(null, 0, monsterListYaml.getInt(monsterName+".STR"), false) + "]%enter%";
		lore = lore+"��a��l "+MainServerOption.statDEX+" : ��f"+monsterListYaml.getInt(monsterName+".DEX")
		+"��7 [Ȱ�� : " + BattleCalculator.returnRangeDamageValue(null, monsterListYaml.getInt(monsterName+".DEX"), 0, true) + " ~ " + BattleCalculator.returnRangeDamageValue(null, monsterListYaml.getInt(monsterName+".DEX"), 0, false) + "]%enter%";
		lore = lore+"��9��l "+MainServerOption.statINT+" : ��f"+monsterListYaml.getInt(monsterName+".INT")
		+"��7 [���� : " + (monsterListYaml.getInt(monsterName+".INT")/4)+ " ~ "+(int)(monsterListYaml.getInt(monsterName+".INT")/2.5)+"]%enter%";
		lore = lore+"��7��l "+MainServerOption.statWILL+" : ��f"+monsterListYaml.getInt(monsterName+".WILL")
		+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterListYaml.getInt(monsterName+".LUK"), (int)monsterListYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
		lore = lore+"��e��l "+MainServerOption.statLUK+" : ��f"+monsterListYaml.getInt(monsterName+".LUK")
		+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterListYaml.getInt(monsterName+".LUK"), (int)monsterListYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
		lore = lore+"��7��l ��� : ��f"+monsterListYaml.getInt(monsterName+".DEF")+"%enter%";
		lore = lore+"��b��l ��ȣ : ��f"+monsterListYaml.getInt(monsterName+".Protect")+"%enter%";
		lore = lore+"��9��l ���� ��� : ��f"+monsterListYaml.getInt(monsterName+".Magic_DEF")+"%enter%";
		lore = lore+"��1��l ���� ��ȣ : ��f"+monsterListYaml.getInt(monsterName+".Magic_Protect")+"%enter%";
		
		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  " "+scriptA[counter];

		removeFlagStack("��c[    ����    ]", 52,0,1,null, 9, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 10, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 11, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 18, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 20, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 27, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 28, inv);
		removeFlagStack("��c[    ����    ]", 52,0,1,null, 29, inv);
		int id = 383;
		byte data = 0;
		String type = monsterListYaml.getString(monsterName+".Type");
		switch(type)
		{
			case "����ũ����" : case "ũ����" : data=50; break;
			case "�״����̷���" : case "���̷���" : data=51; break;
			case "�Ź�" : data=52; break;
			case "����" :case "���̾�Ʈ" : data=54; break;
			case "�ʴ���������" :case "Ư�뽽����" : case "ū������" :case "���뽽����" : case "����������" : data=55; break;
			case "����Ʈ" : data=56; break;
			case "�����Ǳ׸�" : data=57; break;
			case "������" : data=58; break;
			case "�����Ź�" : data=59; break;
			case "������" : data=60; break;
			case "������" : data=61; break;
			case "ū���׸�ť��" :case "Ư�븶�׸�ť��" : case "���븶�׸�ť��": case "���׸�ť��" : case "�������׸�ť��" : data=62; break;
			case "����" : data=65; break;
			case "����" : data=66; break;
			case "���������" : data=67; break;
			case "��ȣ��" : data=68; break;
			case "����" : data=90; break;
			case "��" : data=91; break;
			case "��" : data=92; break;
			case "��" : data=93; break;
			case "��¡��" : data=94; break;
			case "����" : data=95; break;
			case "������" : data=96; break;
			case "������" : data=98; break;
			case "��" : data=100; break;
			case "�䳢" : data=101; break;
			case "�ֹ�" : data=120; break;
			case "�����" :id = 332; data=0; break;
			case "��" :id = 265; data=0; break;
			case "����" : id=399; break;
			case "�����巡��" : id=122; break;
			case "����ũ����Ż" : id=46; break;
			//case "�޸�" : id=379; data=3;break;
		}
		removeFlagStack("��f"+ monsterName, id,data,1,Arrays.asList(scriptA), 19, inv);
		
		removeFlagStack("��3[    �̸� ����    ]", 421,0,1,Arrays.asList("��f������ �̸���","��f�����մϴ�.","","��f[    ���� �̸�    ]"," ��f"+monsterListYaml.getString(monsterName+".Name"),""), 13, inv);
		removeFlagStack("��3[    Ÿ�� ����    ]", 383,0,1,Arrays.asList("��f������ Ÿ����","��f�����մϴ�.","","��f[    ���� Ÿ��    ]"," ��f"+monsterListYaml.getString(monsterName+".Type"),""), 14, inv);

		data = 0;
		switch(monsterListYaml.getString(monsterName+".Biome"))
		{
			case "BEACH" : id=337;break;
			case "DESERT" : id=12;break;
			case "EXTREME_HILLS" : id=129;break;
			case "FOREST" : id=17;break;
			case "HELL" : id=87;break;
			case "JUNGLE" : id=6;data=3;break;
			case "MESA" : id=159;break;
			case "OCEAN" : id=410;break;
			case "PLAINS" : id=2;break;
			case "RIVER" : id=346;break;
			case "SAVANNA" : id=32;break;
			case "SKY" : id=121;break;
			case "SMALL_MOUNTAINS" : id=6;data=0;break;
			case "SWAMPLAND" : id=111;break;
			case "TAIGA" : id=78;break;
			default : id=166;break;
		}
		
		removeFlagStack("��3[ ���� ���̿� ���� ]", id,data,1,Arrays.asList("��f���Ͱ� �����ϴ�","��f���̿��� �����մϴ�.","","��f[    ���� ���̿�    ]"," ��f"+monsterListYaml.getString(monsterName+".Biome"),""), 15, inv);
		removeFlagStack("��3[    ����� ����    ]", 351,1,1,Arrays.asList("��f������ �������","��f�����մϴ�.","","��f[    ���� �����    ]"," ��f"+monsterListYaml.getInt(monsterName+".HP"),""), 16, inv);
		removeFlagStack("��3[    ����ġ ����    ]", 384,0,1,Arrays.asList("��f���� ��ɽ� ���","��f����ġ ���� �����մϴ�.","","��f[    ���� ����ġ    ]"," ��f"+monsterListYaml.getInt(monsterName+".EXP"),""), 22, inv);
		removeFlagStack("��3[  ��� �ݾ� ����  ]", 266,0,1,Arrays.asList("��f���� ��ɽ� ���","��f�ݾ��� �����մϴ�.","","��f[    ���� �ݾ�    ]"," ��f"+monsterListYaml.getInt(monsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(monsterName+".MAX_Money"),""), 23, inv);
		removeFlagStack("��3[    ��� ����    ]", 307,0,1,Arrays.asList("��f������ ���","��f���� �մϴ�.","","��e[    ��Ŭ���� ����    ]",""), 24, inv);
		removeFlagStack("��3[  ��� ����� ����  ]", 54,0,1,Arrays.asList("��f���� ��ɽ� ����Ǵ�","��f��� Ȯ���� �����մϴ�.","","��f[    ���� �����    ]"," ��f�Ӹ� : "+monsterListYaml.getInt(monsterName+".Head.DropChance")/10.0+"%"
				," ��f���� : "+monsterListYaml.getInt(monsterName+".Chest.DropChance")/10.0+"%"
				," ��f���� : "+monsterListYaml.getInt(monsterName+".Leggings.DropChance")/10.0+"%"
				," ��f�Ź� : "+monsterListYaml.getInt(monsterName+".Boots.DropChance")/10.0+"%"
				," ��f���� : "+monsterListYaml.getInt(monsterName+".Hand.DropChance")/10.0+"%","","��e[    ��Ŭ���� ����   ]",""), 25, inv);
		removeFlagStack("��3[  ���� ���� ����  ]", 399,0,1,Arrays.asList("��f������ �⺻ ������","��f�����մϴ�.",""), 31, inv);
		removeFlagStack("��3[  ���� ��� ����  ]", 310,0,1,Arrays.asList("��f������ ��� �� ��ȣ��","��f�����մϴ�.",""), 32, inv);
		
		lore = "��f������ AI�� �����մϴ�.%enter%%enter%��f[    ���� AI    ]%enter%��f"+monsterListYaml.getString(monsterName+".AI")+"%enter%%enter%";
		if(type.equals("�ʴ���������")||type.equals("Ư�뽽����")||type.equals("ū������")||
		type.equals("���뽽����")||type.equals("����������")||type.equals("ū���׸�ť��")||type.equals("Ư�븶�׸�ť��")||type.equals("���븶�׸�ť��")||
		type.equals("���׸�ť��")||type.equals("�������׸�ť��")||type.equals("����Ʈ")||type.equals("����")
		||type.equals("�����巡��")||type.equals("��Ŀ")||type.equals("��")||type.equals("��")
		||type.equals("����")||type.equals("��")||type.equals("����")||type.equals("�䳢")
		||type.equals("������")||type.equals("����")||type.equals("��")||type.equals("������")
		||type.equals("��¡��")||type.equals("�ֹ�")||type.equals("�����")||type.equals("��")
		)
		lore = lore + "��c[���� ���� �� ���� Ÿ����%enter%��c������ ���� AI���� ����մϴ�.]";
		else
		{
			switch(monsterListYaml.getString(monsterName+".AI"))
			{
			case "�Ϲ� �ൿ" :
				lore = lore+"��f�Ϲ����� �ൿ�� �մϴ�.%enter%";
				break;
			case "����" :
				lore = lore+"��f������ ���� �������մϴ�.%enter%";break;
			case "�񼱰�" :
				lore = lore+"��f���ݹޱ� ������ �������� �ʽ��ϴ�.%enter%";break;
			case "������" :
				lore = lore+"��f���ݹ� �̵��� ���� �ʽ��ϴ�.%enter%";break;
			case "����" :
				lore = lore+"��f���ݹ��� ��� ����ġ�� �ٻڸ�,%enter%��f����� �������� �ʽ��ϴ�.%enter%";break;
			}
		}
		
		scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  " "+scriptA[counter];
		
		
		removeFlagStack("��3[  ���� AI ����  ]", 137,0,1,Arrays.asList(scriptA), 33, inv);
		removeFlagStack("��3[    ���� ȿ��    ]", 373,0,1,Arrays.asList("��f���Ϳ��� ���� ȿ����","��f�ο��մϴ�.",""), 34, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+monsterName), 53, inv);
		player.openInventory(inv);
	}


	public void monsterOptionSettingGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String monsterName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
			
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				new MonsterListGui().monsterListGUI(player, 0);
			else if(slot == 14)//�� Ÿ�� ����
				new MonsterTypeGui().monsterTypeGui(player, monsterName, 0);
			else if(slot == 15)//���� ���̿� ����
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String type = monsterListYaml.getString(monsterName+".Biome");
				if(type.equals("NULL"))
					monsterListYaml.set(monsterName+".Biome", "BEACH");
				else if(type.equals("BEACH"))
					monsterListYaml.set(monsterName+".Biome", "DESERT");
				else if(type.equals("DESERT"))
					monsterListYaml.set(monsterName+".Biome", "EXTREME_HILLS");
				else if(type.equals("EXTREME_HILLS"))
					monsterListYaml.set(monsterName+".Biome", "FOREST");
				else if(type.equals("FOREST"))
					monsterListYaml.set(monsterName+".Biome", "HELL");
				else if(type.equals("HELL"))
					monsterListYaml.set(monsterName+".Biome", "JUNGLE");
				else if(type.equals("JUNGLE"))
					monsterListYaml.set(monsterName+".Biome", "MESA");
				else if(type.equals("MESA"))
					monsterListYaml.set(monsterName+".Biome", "OCEAN");
				else if(type.equals("OCEAN"))
					monsterListYaml.set(monsterName+".Biome", "PLAINS");
				else if(type.equals("PLAINS"))
					monsterListYaml.set(monsterName+".Biome", "RIVER");
				else if(type.equals("RIVER"))
					monsterListYaml.set(monsterName+".Biome", "SAVANNA");
				else if(type.equals("SAVANNA"))
					monsterListYaml.set(monsterName+".Biome", "SKY");
				else if(type.equals("SKY"))
					monsterListYaml.set(monsterName+".Biome", "SMALL_MOUNTAINS");
				else if(type.equals("SMALL_MOUNTAINS"))
					monsterListYaml.set(monsterName+".Biome", "SWAMPLAND");
				else if(type.equals("SWAMPLAND"))
					monsterListYaml.set(monsterName+".Biome", "TAIGA");
				else if(type.equals("TAIGA"))
					monsterListYaml.set(monsterName+".Biome", "NULL");
				else
					monsterListYaml.set(monsterName+".Biome", "NULL");
				monsterListYaml.saveConfig();
				monsterOptionSettingGui(player, monsterName);
			}
			else if(slot == 33)
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String type = monsterListYaml.getString(monsterName+".AI");
				if(type.equals("�Ϲ� �ൿ"))
					monsterListYaml.set(monsterName+".AI", "����");
				else if(type.equals("����"))
					monsterListYaml.set(monsterName+".AI", "�񼱰�");
				else if(type.equals("�񼱰�"))
					monsterListYaml.set(monsterName+".AI", "����");
				else if(type.equals("����"))
					monsterListYaml.set(monsterName+".AI", "������");
				else if(type.equals("������"))
					monsterListYaml.set(monsterName+".AI", "�Ϲ� �ൿ");
				else
					monsterListYaml.set(monsterName+".AI", "�Ϲ� �ൿ");
				monsterListYaml.saveConfig();
				monsterOptionSettingGui(player, monsterName);
			}
			else if(slot == 24)//��� ����
			{
				SoundEffect.playSound(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
				new MonsterEquipGui().monsterEquipGui(player, monsterName);
			}
			else if(slot == 34)//���� ���� ȿ��
				new MonsterPotionGui().monsterPotionGui(player, monsterName);
			else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
			{
				UserDataObject u = new UserDataObject();
				player.closeInventory();
				u.setType(player, "Monster");
				u.setString(player, (byte)2, monsterName);
				if(slot==13)//�� �̸� ����
				{
					player.sendMessage("��a[����] : ������ �����ִ� �̸��� �����ϼ���!");
					player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
					"��3&3 ��4&4 ��5&5 " +
							"��6&6 ��7&7 ��8&8 " +
					"��9&9 ��a&a ��b&b ��c&c " +
							"��d&d ��e&e ��f&f");
					u.setString(player, (byte)1, "CN");
				}
				else if(slot == 16)//����� ����
				{
					player.sendMessage("��a[����] : �ش� ������ ������� ���� �� �ּ���!");
					player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "HP");
				}
				else if(slot == 22)//����ġ ����
				{
					player.sendMessage("��a[����] : �ش� ������ ����ġ�� ���� �� �ּ���!");
					player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "EXP");
				}
				else if(slot == 23)//��� �ݾ� ����
				{
					player.sendMessage("��a[����] : �ش� ���Ͱ� ����ϴ� �ּ� ��差�� ������ �ּ���!");
					player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "MIN_Money");
				}
				else if(slot == 25)//��� ����� ����
				{
					player.sendMessage("��7(Ȯ�� ��� : 1000 = 100%, 1 = 0.1%)");
					player.sendMessage("��a[����] : ������ ���� ������� ������ �ּ���!");
					player.sendMessage("��3(0 ~ 1000)");
					u.setString(player, (byte)1, "Head.DropChance");
				}
				else if(slot == 31)//���� ���� ����
				{
					player.sendMessage("��7("+MainServerOption.statSTR+"�� ������ ���� ���ݷ��� ��½��� �ݴϴ�.)");
					player.sendMessage("��a[����] : ������ "+MainServerOption.statSTR+"�� ������ �ּ���!");
					player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "STR");
				}
				else if(slot == 32)//���� ��� ����
				{
					player.sendMessage("��7(�������� ������ �������� ������ ��½��� �ݴϴ�.)");
					player.sendMessage("��a[����] : ������ ���� ������ ������ �ּ���!");
					player.sendMessage("��3(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "DEF");
				}
			}
		}
	}

}
