package monster;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import admin.OPbox_GUI;
import battle.Battle_Calculator;
import effect.SoundEffect;
import main.Main_ServerOption;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;



public class Monster_GUI extends Util_GUI
{
	public void MonsterListGUI(Player player, int page)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");
		String UniqueCode = "��0��0��8��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));

		Object[] a= monsterListYaml.getKeys().toArray();

		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			if(count > a.length || loc >= 45) break;
			String MonsterName =a[count].toString();
			String Lore=null;
			
			Lore = "%enter%"+"��f��l �̸� : "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Name")+"%enter%";
			Lore = Lore+"��f��l Ÿ�� : "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Type")+"%enter%";
			Lore = Lore+"��f��l ���� ���̿� : "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Biome")+"%enter%";
			Lore = Lore+"��c��l ����� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".HP")+"%enter%";
			Lore = Lore+"��b��l ����ġ : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".EXP")+"%enter%";
			Lore = Lore+"��e��l ��� �ݾ� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(MonsterName+".MAX_Money")+"%enter%";
			Lore = Lore+"��c��l "+Main_ServerOption.STR+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".STR")
			+ChatColor.GRAY+ " [���� : " + Battle_Calculator.CombatDamageGet(null, 0, monsterListYaml.getInt(MonsterName+".STR"), true) + " ~ " + Battle_Calculator.CombatDamageGet(null, 0, monsterListYaml.getInt(MonsterName+".STR"), false) + "]%enter%";
			Lore = Lore+"��a��l "+Main_ServerOption.DEX+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".DEX")
			+ChatColor.GRAY+ " [Ȱ�� : " + Battle_Calculator.returnRangeDamageValue(null, monsterListYaml.getInt(MonsterName+".DEX"), 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(null, monsterListYaml.getInt(MonsterName+".DEX"), 0, false) + "]%enter%";
			Lore = Lore+"��9��l "+Main_ServerOption.INT+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".INT")
			+ChatColor.GRAY+ " [���� : " + (monsterListYaml.getInt(MonsterName+".INT")/4)+ " ~ "+(int)(monsterListYaml.getInt(MonsterName+".INT")/2.5)+"]%enter%";
			Lore = Lore+"��7��l "+Main_ServerOption.WILL+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".WILL")
			+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterListYaml.getInt(MonsterName+".LUK"), (int)monsterListYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
			Lore = Lore+"��e��l "+Main_ServerOption.LUK+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".LUK")
			+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterListYaml.getInt(MonsterName+".LUK"), (int)monsterListYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
			Lore = Lore+"��7��l ��� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".DEF")+"%enter%";
			Lore = Lore+"��b��l ��ȣ : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".Protect")+"%enter%";
			Lore = Lore+"��9��l ���� ��� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".Magic_DEF")+"%enter%";
			Lore = Lore+"��1��l ���� ��ȣ : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".Magic_Protect")+"%enter%";
			Lore = Lore+"%enter%"+"��e��l[Shift + �� Ŭ���� ������ ����]"+"%enter%"+"��c��l[Shift + �� Ŭ���� ���� ����]";

			String[] scriptA = Lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			int id = 383;
			byte data = 0;
			switch(monsterListYaml.getString(MonsterName+".Type"))
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
				case "����" : id=399; break;
				case "�����巡��" : id=122; break;
				case "����ũ����Ż" : id=46; break;
				//case "�޸�" : id=379; data = 3; break;
			}
			
			Stack(ChatColor.WHITE+MonsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(a.length-(page*44)>45)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l�� ����", 339,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ���͸� �����մϴ�."), 49, inv);
		Stack("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void MonsterOptionSettingGUI(Player player,String MonsterName)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");

		String UniqueCode = "��0��0��8��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����");

		String Lore=null;			
		Lore = "%enter%"+"��f��l �̸� : "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Name")+"%enter%";
		Lore = Lore+"��f��l Ÿ�� : "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Type")+"%enter%";
		Lore = Lore+"��f��l ���� ���̿� : "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Biome")+"%enter%";
		Lore = Lore+"��c��l ����� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".HP")+"%enter%";
		Lore = Lore+"��b��l ����ġ : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".EXP")+"%enter%";
		Lore = Lore+"��e��l ��� �ݾ� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(MonsterName+".MAX_Money")+"%enter%";
		Lore = Lore+"��c��l "+Main_ServerOption.STR+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".STR")
		+ChatColor.GRAY+ " [���� : " + Battle_Calculator.CombatDamageGet(null, 0, monsterListYaml.getInt(MonsterName+".STR"), true) + " ~ " + Battle_Calculator.CombatDamageGet(null, 0, monsterListYaml.getInt(MonsterName+".STR"), false) + "]%enter%";
		Lore = Lore+"��a��l "+Main_ServerOption.DEX+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".DEX")
		+ChatColor.GRAY+ " [Ȱ�� : " + Battle_Calculator.returnRangeDamageValue(null, monsterListYaml.getInt(MonsterName+".DEX"), 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(null, monsterListYaml.getInt(MonsterName+".DEX"), 0, false) + "]%enter%";
		Lore = Lore+"��9��l "+Main_ServerOption.INT+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".INT")
		+ChatColor.GRAY+ " [���� : " + (monsterListYaml.getInt(MonsterName+".INT")/4)+ " ~ "+(int)(monsterListYaml.getInt(MonsterName+".INT")/2.5)+"]%enter%";
		Lore = Lore+"��7��l "+Main_ServerOption.WILL+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".WILL")
		+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterListYaml.getInt(MonsterName+".LUK"), (int)monsterListYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
		Lore = Lore+"��e��l "+Main_ServerOption.LUK+" : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".LUK")
		+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterListYaml.getInt(MonsterName+".LUK"), (int)monsterListYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
		Lore = Lore+"��7��l ��� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".DEF")+"%enter%";
		Lore = Lore+"��b��l ��ȣ : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".Protect")+"%enter%";
		Lore = Lore+"��9��l ���� ��� : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".Magic_DEF")+"%enter%";
		Lore = Lore+"��1��l ���� ��ȣ : "+ChatColor.WHITE+monsterListYaml.getInt(MonsterName+".Magic_Protect")+"%enter%";

		
		String[] scriptA = Lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  " "+scriptA[counter];

		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 9, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 10, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 11, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 18, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 20, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 27, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 28, inv);
		Stack2(ChatColor.RED + "[    ����    ]", 52,0,1,null, 29, inv);
		int id = 383;
		byte data = 0;
		String Type = monsterListYaml.getString(MonsterName+".Type");
		switch(Type)
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

		Stack2(ChatColor.WHITE + MonsterName, id,data,1,Arrays.asList(scriptA), 19, inv);
		
		
		Stack2(ChatColor.DARK_AQUA + "[    �̸� ����    ]", 421,0,1,Arrays.asList(ChatColor.WHITE+"������ �̸���",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� �̸�    ]"," "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Name"),""), 13, inv);
		Stack2(ChatColor.DARK_AQUA + "[    Ÿ�� ����    ]", 383,0,1,Arrays.asList(ChatColor.WHITE+"������ Ÿ����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� Ÿ��    ]"," "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Type"),""), 14, inv);

		data = 0;
		switch(monsterListYaml.getString(MonsterName+".Biome"))
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
		
		Stack2(ChatColor.DARK_AQUA + "[ ���� ���̿� ���� ]", id,data,1,Arrays.asList(ChatColor.WHITE+"���Ͱ� �����ϴ�",ChatColor.WHITE+"���̿��� �����մϴ�.","",ChatColor.WHITE+"[    ���� ���̿�    ]"," "+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".Biome"),""), 15, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ����� ����    ]", 351,1,1,Arrays.asList(ChatColor.WHITE+"������ �������",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� �����    ]"," "+ChatColor.WHITE+""+monsterListYaml.getInt(MonsterName+".HP"),""), 16, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ����ġ ����    ]", 384,0,1,Arrays.asList(ChatColor.WHITE+"���� ��ɽ� ���",ChatColor.WHITE+"����ġ ���� �����մϴ�.","",ChatColor.WHITE+"[    ���� ����ġ    ]"," "+ChatColor.WHITE+""+monsterListYaml.getInt(MonsterName+".EXP"),""), 22, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ��� �ݾ� ����  ]", 266,0,1,Arrays.asList(ChatColor.WHITE+"���� ��ɽ� ���",ChatColor.WHITE+"�ݾ��� �����մϴ�.","",ChatColor.WHITE+"[    ���� �ݾ�    ]"," "+ChatColor.WHITE+""+monsterListYaml.getInt(MonsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(MonsterName+".MAX_Money"),""), 23, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ��� ����    ]", 307,0,1,Arrays.asList(ChatColor.WHITE+"������ ���",ChatColor.WHITE+"���� �մϴ�.","",ChatColor.YELLOW+"[    ��Ŭ���� ����    ]",""), 24, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ��� ����� ����  ]", 54,0,1,Arrays.asList(ChatColor.WHITE+"���� ��ɽ� ����Ǵ�",ChatColor.WHITE+"��� Ȯ���� �����մϴ�.","",ChatColor.WHITE+"[    ���� �����    ]"," "+ChatColor.WHITE+"�Ӹ� : "+monsterListYaml.getInt(MonsterName+".Head.DropChance")/10.0+"%"
				," "+ChatColor.WHITE+"���� : "+monsterListYaml.getInt(MonsterName+".Chest.DropChance")/10.0+"%"
				," "+ChatColor.WHITE+"���� : "+monsterListYaml.getInt(MonsterName+".Leggings.DropChance")/10.0+"%"
				," "+ChatColor.WHITE+"�Ź� : "+monsterListYaml.getInt(MonsterName+".Boots.DropChance")/10.0+"%"
				," "+ChatColor.WHITE+"���� : "+monsterListYaml.getInt(MonsterName+".Hand.DropChance")/10.0+"%","",ChatColor.YELLOW+"[    ��Ŭ���� ����   ]",""), 25, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ���� ���� ����  ]", 399,0,1,Arrays.asList(ChatColor.WHITE+"������ �⺻ ������",ChatColor.WHITE+"�����մϴ�.",""), 31, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ���� ��� ����  ]", 310,0,1,Arrays.asList(ChatColor.WHITE+"������ ��� �� ��ȣ��",ChatColor.WHITE+"�����մϴ�.",""), 32, inv);
		
		Lore = ChatColor.WHITE+"������ AI�� �����մϴ�.%enter%%enter%"+ChatColor.WHITE+"[    ���� AI    ]%enter%"+ChatColor.WHITE+monsterListYaml.getString(MonsterName+".AI")+"%enter%%enter%";
		if(Type.compareTo("�ʴ���������")==0||Type.compareTo("Ư�뽽����")==0||Type.compareTo("ū������")==0||
		Type.compareTo("���뽽����")==0||Type.compareTo("����������")==0||Type.compareTo("ū���׸�ť��")==0||Type.compareTo("Ư�븶�׸�ť��")==0||Type.compareTo("���븶�׸�ť��")==0||
		Type.compareTo("���׸�ť��")==0||Type.compareTo("�������׸�ť��")==0||Type.compareTo("����Ʈ")==0||Type.compareTo("����")==0
		||Type.compareTo("�����巡��")==0||Type.compareTo("��Ŀ")==0||Type.compareTo("��")==0||Type.compareTo("��")==0
		||Type.compareTo("����")==0||Type.compareTo("��")==0||Type.compareTo("����")==0||Type.compareTo("�䳢")==0
		||Type.compareTo("������")==0||Type.compareTo("����")==0||Type.compareTo("��")==0||Type.compareTo("������")==0
		||Type.compareTo("��¡��")==0||Type.compareTo("�ֹ�")==0||Type.compareTo("�����")==0||Type.compareTo("��")==0
		)
		Lore = Lore + ChatColor.RED + "[���� ���� �� ���� Ÿ����%enter%"+ChatColor.RED+"������ ���� AI���� ����մϴ�.]";
		else
		{
			switch(monsterListYaml.getString(MonsterName+".AI"))
			{
			case "�Ϲ� �ൿ" :
				Lore = Lore+ChatColor.WHITE+"�Ϲ����� �ൿ�� �մϴ�.%enter%";
				break;
			case "����" :
				Lore = Lore+ChatColor.WHITE+"������ ���� �������մϴ�.%enter%";break;
			case "�񼱰�" :
				Lore = Lore+ChatColor.WHITE+"���ݹޱ� ������ �������� �ʽ��ϴ�.%enter%";break;
			case "������" :
				Lore = Lore+ChatColor.WHITE+"���ݹ� �̵��� ���� �ʽ��ϴ�.%enter%";break;
			case "����" :
				Lore = Lore+ChatColor.WHITE+"���ݹ��� ��� ����ġ�� �ٻڸ�,%enter%"+ChatColor.WHITE+"����� �������� �ʽ��ϴ�.%enter%";break;
			}
		}
		
		scriptA = Lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  " "+scriptA[counter];
		
		
		Stack2(ChatColor.DARK_AQUA + "[  ���� AI ����  ]", 137,0,1,Arrays.asList(scriptA), 33, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ���� ȿ��    ]", 373,0,1,Arrays.asList(ChatColor.WHITE+"���Ϳ��� ���� ȿ����",ChatColor.WHITE+"�ο��մϴ�.",""), 34, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+MonsterName), 53, inv);
		player.openInventory(inv);
	}
	
	public void MonsterPotionGUI(Player player, String MonsterName)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");
		String UniqueCode = "��0��0��8��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����");
		
		Stack2(ChatColor.DARK_AQUA + "[  ���  ]", 373,8193,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.Regenerate")), 10, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ��  ]", 373,8196,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.Poison")), 11, inv);
		Stack2(ChatColor.DARK_AQUA + "[  �ż�  ]", 373,8194,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.Speed")), 12, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ����  ]", 373,8234,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.Slow")), 13, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ��  ]", 373,8201,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.Strength")), 14, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ������  ]", 373,8232,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.Weak")), 15, inv);
		Stack2(ChatColor.DARK_AQUA + "[  ����  ]", 373,8267,1,Arrays.asList(ChatColor.WHITE+"[  ���� ��  ]",ChatColor.YELLOW+" "+monsterListYaml.getInt(MonsterName+".Potion.JumpBoost")), 16, inv);

		if(monsterListYaml.getInt(MonsterName+".Potion.FireRegistance")!=0)
			Stack2(ChatColor.DARK_AQUA + "[  ȭ�� ����  ]", 373,8227,1,Arrays.asList(ChatColor.GREEN+"[  ���� ����  ]"), 19, inv);
		else
			Stack2(ChatColor.DARK_AQUA + "[  ȭ�� ����  ]", 166,0,1,Arrays.asList(ChatColor.RED+"[  ���� ������  ]"), 19, inv);
		if(monsterListYaml.getInt(MonsterName+".Potion.WaterBreath")!=0)
			Stack2(ChatColor.DARK_AQUA + "[  ���� ȣȩ  ]", 373,8237,1,Arrays.asList(ChatColor.GREEN+"[  ���� ����  ]"), 20, inv);
		else
			Stack2(ChatColor.DARK_AQUA + "[  ���� ȣȩ  ]", 166,0,1,Arrays.asList(ChatColor.RED+"[  ���� ������  ]"), 20, inv);
		if(monsterListYaml.getInt(MonsterName+".Potion.Invisible")!=0)
			Stack2(ChatColor.DARK_AQUA + "[  ����  ]", 373,8238,1,Arrays.asList(ChatColor.GREEN+"[  ���� ����  ]"), 21, inv);
		else
			Stack2(ChatColor.DARK_AQUA + "[  ����  ]", 166,0,1,Arrays.asList(ChatColor.RED+"[  ���� ������  ]"), 21, inv);
			

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+MonsterName), 53, inv);
		player.openInventory(inv);
	}

	public void ArmorGUI(Player player, String mob)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		String UniqueCode = "��1��0��8��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ��� ����");
		monsterListYaml.getConfig("Monster/MonsterList.yml");

		if(monsterListYaml.contains(mob + ".Head.Item")==true&&
			monsterListYaml.getItemStack(mob + ".Head.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(0, monsterListYaml.getItemStack(mob + ".Head.Item"));
		else
			Stack(ChatColor.WHITE + "�Ӹ�", 302,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."), (byte)0, inv);

		if(monsterListYaml.contains(mob + ".Chest.Item")==true&&
				monsterListYaml.getItemStack(mob + ".Chest.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(1, monsterListYaml.getItemStack(mob + ".Chest.Item"));
		else
			Stack(ChatColor.WHITE + "����", 303,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."), (byte)1, inv);

		if(monsterListYaml.contains(mob + ".Leggings.Item")==true&&
				monsterListYaml.getItemStack(mob + ".Leggings.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(2, monsterListYaml.getItemStack(mob + ".Leggings.Item"));
		else
			Stack(ChatColor.WHITE + "����", 304,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."), (byte)2, inv);

		if(monsterListYaml.contains(mob + ".Boots.Item")==true&&
		monsterListYaml.getItemStack(mob + ".Boots.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(3, monsterListYaml.getItemStack(mob + ".Boots.Item"));
		else
			Stack(ChatColor.WHITE + "����", 305,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."), (byte)3, inv);

		if(monsterListYaml.contains(mob + ".Hand.Item")==true&&
		monsterListYaml.getItemStack(mob + ".Hand.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(4, monsterListYaml.getItemStack(mob + ".Hand.Item"));
		else
			Stack(ChatColor.WHITE + "������", 267,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."), (byte)4, inv);

		if(monsterListYaml.contains(mob + ".OffHand.Item")==true&&
		monsterListYaml.getItemStack(mob + ".OffHand.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(5, monsterListYaml.getItemStack(mob + ".OffHand.Item"));
		else
			Stack(ChatColor.WHITE + "�޼�", 267,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."), (byte)5, inv);
		
		Stack(ChatColor.WHITE + mob, 416,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY + mob+"�� ���� ����Դϴ�." ), (byte)8, inv);
		Stack(ChatColor.WHITE + "", 30,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY +"�̰����� ��������",ChatColor.GRAY +"�÷����� ������."), (byte)7, inv);
		Stack(ChatColor.WHITE + "", 30,(byte)0,(byte)1,Arrays.asList(ChatColor.GRAY +"�̰����� ��������",ChatColor.GRAY +"�÷����� ������."), (byte)6, inv);
		
		player.openInventory(inv);
		return;
	}

	public void MonsterTypeGUI(Player player, String MonsterName)
	{
		String UniqueCode = "��1��0��8��0��b��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� Ÿ�� ����");

		Stack2("��2��l[����]", 367,0,1,null, 0, inv);
		Stack2("��2��l[���̾�Ʈ]", 367,0,2,null, 1, inv);
		Stack2("��d��l[���� �Ǳ׸�]", 283,0,1,null, 2, inv);
		Stack2("��a��l[ũ����]", 289,0,1,null, 3, inv);
		Stack2("��a��l[���� ũ����]", 289,0,1,null, 4, inv);
		Stack2("��f��l[���̷���]", 352,0,1,null, 5, inv);
		Stack2("��8��l[���� ���̷���]", 263,0,1,null, 6, inv);
		Stack2("��8��l[����]", 399,0,1,null, 7, inv);
		Stack2("��7��l[�Ź�]", 287,0,1,null, 8, inv);
		Stack2("��7��l[�����Ź�]", 287,0,2,null, 9, inv);
		Stack2("��a��l[���� ������]", 341,0,1,null, 10, inv);
		Stack2("��a��l[���� ������]", 341,0,2,null, 11, inv);
		Stack2("��a��l[ū ������]", 341,0,4,null, 12, inv);
		Stack2("��a��l[Ư�� ������]", 341,0,8,null, 13, inv);
		Stack2("��a��l[�ʴ��� ������]", 341,0,16,null, 14, inv);
		Stack2("��7��l[���� ���׸�ť��]", 378,0,1,null, 15, inv);
		Stack2("��7��l[���� ���׸�ť��]", 378,0,1,null, 16, inv);
		Stack2("��7��l[ū ���׸�ť��]", 378,0,1,null, 17, inv);
		Stack2("��7��l[Ư�� ���׸�ť��]", 378,0,1,null, 18, inv);
		Stack2("��7��l[�ʴ��� ���׸�ť��]", 378,0,1,null, 19, inv);
		Stack2("��8��l[����]", 362,0,1,null, 20, inv);
		Stack2("��f��l[����Ʈ]", 370,0,1,null, 21, inv);
		Stack2("��e��l[������]", 369,0,1,null, 22, inv);
		Stack2("��7��l[������]", 1,0,1,null, 23, inv);
		Stack2("��5��l[���� �����]", 432,0,1,null, 24, inv);
		Stack2("��a��l[�ֹ�]", 388,0,1,null, 25, inv);
		Stack2("��5��l[����]", 438,0,1,null, 26, inv);
		Stack2("��3��l[�����]", 409,0,1,null, 27, inv);
		Stack2("��8��l[������]", 368,0,1,null, 28, inv);
		Stack2("��5��l[��Ŀ]", 443,0,1,null, 29, inv);
		Stack2("��8��l[�����巡��]", 122,0,1,null, 30, inv);
		Stack2("��d��l[����]", 319,0,1,null, 31, inv);
		Stack2("��f��l[��]", 423,0,1,null, 32, inv);
		Stack2("��7��l[��]", 363,0,1,null, 33, inv);
		Stack2("��c��l[���� ��]", 40,0,1,null, 34, inv);
		Stack2("��f��l[��]", 365,0,1,null, 35, inv);
		Stack2("��8��l[��¡��]", 351,0,1,null, 36, inv);
		Stack2("��7��l[����]", 280,0,1,null, 37, inv);
		Stack2("��e��l[������]", 349,0,1,null, 38, inv);
		Stack2("��f��l[�����]", 332,0,1,null, 39, inv);
		Stack2("��f��l[ö��]", 265,0,1,null, 40, inv);
		Stack2("��f��l[�䳢]", 411,0,1,null, 41, inv);
		Stack2("��f��l[�ϱذ�]", 349,1,1,null, 42, inv);
		Stack2("��6��l[��]", 417,0,1,null, 43, inv);
		Stack2("��5��l[���� ũ����Ż]", 426,0,1,null, 44, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+MonsterName), 53, inv);
		player.openInventory(inv);
	}
	

	public void MonsterListGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player, (byte) 1);
			else if(slot == 48)//���� ������
				MonsterListGUI(player, page-1);
			else if(slot == 49)//�� ����
			{
				player.closeInventory();
				player.sendMessage(ChatColor.GREEN+"[����] : ���ο� ���� �̸��� ���� �ּ���!");
				UserData_Object u = new UserData_Object();
				u.setType(player, "Monster");
				u.setString(player, (byte)1, "NM");
			}
			else if(slot == 50)//���� ������
				MonsterListGUI(player, page+1);
			else
			{
				if(event.isLeftClick() == true&&event.isShiftClick()==false)
					MonsterOptionSettingGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isLeftClick() == true&&event.isShiftClick())
					new monster.Monster_Spawn().SpawnEggGive(player,ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isRightClick()&&event.isShiftClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
					YamlLoader monsterListYaml = new YamlLoader();
					monsterListYaml.getConfig("Monster/MonsterList.yml");
					monsterListYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					monsterListYaml.saveConfig();
					MonsterListGUI(player, page);
				}
			}
		}
	}

	public void MonsterOptionSettingGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String MonsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				MonsterListGUI(player, 0);
			else if(slot == 14)//�� Ÿ�� ����
				MonsterTypeGUI(player, MonsterName);
			else if(slot == 15)//���� ���̿� ����
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String Type = monsterListYaml.getString(MonsterName+".Biome");
				if(Type.compareTo("NULL")==0)
					monsterListYaml.set(MonsterName+".Biome", "BEACH");
				else if(Type.compareTo("BEACH")==0)
					monsterListYaml.set(MonsterName+".Biome", "DESERT");
				else if(Type.compareTo("DESERT")==0)
					monsterListYaml.set(MonsterName+".Biome", "EXTREME_HILLS");
				else if(Type.compareTo("EXTREME_HILLS")==0)
					monsterListYaml.set(MonsterName+".Biome", "FOREST");
				else if(Type.compareTo("FOREST")==0)
					monsterListYaml.set(MonsterName+".Biome", "HELL");
				else if(Type.compareTo("HELL")==0)
					monsterListYaml.set(MonsterName+".Biome", "JUNGLE");
				else if(Type.compareTo("JUNGLE")==0)
					monsterListYaml.set(MonsterName+".Biome", "MESA");
				else if(Type.compareTo("MESA")==0)
					monsterListYaml.set(MonsterName+".Biome", "OCEAN");
				else if(Type.compareTo("OCEAN")==0)
					monsterListYaml.set(MonsterName+".Biome", "PLAINS");
				else if(Type.compareTo("PLAINS")==0)
					monsterListYaml.set(MonsterName+".Biome", "RIVER");
				else if(Type.compareTo("RIVER")==0)
					monsterListYaml.set(MonsterName+".Biome", "SAVANNA");
				else if(Type.compareTo("SAVANNA")==0)
					monsterListYaml.set(MonsterName+".Biome", "SKY");
				else if(Type.compareTo("SKY")==0)
					monsterListYaml.set(MonsterName+".Biome", "SMALL_MOUNTAINS");
				else if(Type.compareTo("SMALL_MOUNTAINS")==0)
					monsterListYaml.set(MonsterName+".Biome", "SWAMPLAND");
				else if(Type.compareTo("SWAMPLAND")==0)
					monsterListYaml.set(MonsterName+".Biome", "TAIGA");
				else if(Type.compareTo("TAIGA")==0)
					monsterListYaml.set(MonsterName+".Biome", "NULL");
				else
					monsterListYaml.set(MonsterName+".Biome", "NULL");
				monsterListYaml.saveConfig();
				MonsterOptionSettingGUI(player, MonsterName);
			}
			else if(slot == 33)
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String Type = monsterListYaml.getString(MonsterName+".AI");
				if(Type.compareTo("�Ϲ� �ൿ")==0)
					monsterListYaml.set(MonsterName+".AI", "����");
				else if(Type.compareTo("����")==0)
					monsterListYaml.set(MonsterName+".AI", "�񼱰�");
				else if(Type.compareTo("�񼱰�")==0)
					monsterListYaml.set(MonsterName+".AI", "����");
				else if(Type.compareTo("����")==0)
					monsterListYaml.set(MonsterName+".AI", "������");
				else if(Type.compareTo("������")==0)
					monsterListYaml.set(MonsterName+".AI", "�Ϲ� �ൿ");
				else
					monsterListYaml.set(MonsterName+".AI", "�Ϲ� �ൿ");
				monsterListYaml.saveConfig();
				MonsterOptionSettingGUI(player, MonsterName);
			}
			else if(slot == 24)//��� ����
			{
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
				ArmorGUI(player, MonsterName);
			}
			else if(slot == 34)//���� ���� ȿ��
				MonsterPotionGUI(player, MonsterName);
			else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
			{
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Monster");
				u.setString(player, (byte)2, ChatColor.stripColor(event.getInventory().getItem(19).getItemMeta().getDisplayName()));
				if(slot==13)//�� �̸� ����
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ �����ִ� �̸��� �����ϼ���!");
					player.sendMessage(ChatColor.WHITE + ""+ChatColor.BOLD + "&l " + ChatColor.BLACK + "&0 "+ChatColor.DARK_BLUE+"&1 "+ChatColor.DARK_GREEN+"&2 "+
					ChatColor.DARK_AQUA + "&3 " +ChatColor.DARK_RED + "&4 " + ChatColor.DARK_PURPLE + "&5 " +
							ChatColor.GOLD + "&6 " + ChatColor.GRAY + "&7 " + ChatColor.DARK_GRAY + "&8 " +
					ChatColor.BLUE + "&9 " + ChatColor.GREEN + "&a " + ChatColor.AQUA + "&b " + ChatColor.RED + "&c " +
							ChatColor.LIGHT_PURPLE + "&d " + ChatColor.YELLOW + "&e "+ChatColor.WHITE + "&f");
					u.setString(player, (byte)1, "CN");
				}
				else if(slot == 16)//����� ����
				{
					player.sendMessage(ChatColor.GREEN+"[����] : �ش� ������ ������� ���� �� �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "HP");
				}
				else if(slot == 22)//����ġ ����
				{
					player.sendMessage(ChatColor.GREEN+"[����] : �ش� ������ ����ġ�� ���� �� �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "EXP");
				}
				else if(slot == 23)//��� �ݾ� ����
				{
					player.sendMessage(ChatColor.GREEN+"[����] : �ش� ���Ͱ� ����ϴ� �ּ� ��差�� ������ �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "MIN_Money");
				}
				else if(slot == 25)//��� ����� ����
				{
					player.sendMessage(ChatColor.GRAY+"(Ȯ�� ��� : 1000 = 100%, 1 = 0.1%)");
					player.sendMessage(ChatColor.GREEN+"[����] : ������ ���� ������� ������ �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ 1000)");
					u.setString(player, (byte)1, "Head.DropChance");
				}
				else if(slot == 31)//���� ���� ����
				{
					player.sendMessage(ChatColor.GRAY+"("+Main_ServerOption.STR+"�� ������ ���� ���ݷ��� ��½��� �ݴϴ�.)");
					player.sendMessage(ChatColor.GREEN+"[����] : ������ "+Main_ServerOption.STR+"�� ������ �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "STR");
				}
				else if(slot == 32)//���� ��� ����
				{
					player.sendMessage(ChatColor.GRAY+"(�������� ������ �������� ������ ��½��� �ݴϴ�.)");
					player.sendMessage(ChatColor.GREEN+"[����] : ������ ���� ������ ������ �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(1 ~ "+Integer.MAX_VALUE+")");
					u.setString(player, (byte)1, "DEF");
				}
			}
		}
	}

	public void MonsterPotionGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String MonsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				MonsterOptionSettingGUI(player, MonsterName);
			else if(slot >= 10 && slot <= 16)
			{
				UserData_Object u = new UserData_Object();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				player.closeInventory();
				u.setType(player, "Monster");
				u.setString(player, (byte)1, "Potion");
				u.setString(player, (byte)3, MonsterName);
				if(slot == 10)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ ��� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Regenerate");
				}
				else if(slot == 11)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ �� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Poision");
				}
				else if(slot == 12)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ �ż� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Speed");
				}
				else if(slot == 13)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ ���� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Slow");
				}
				else if(slot == 14)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ �� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Strength");
				}
				else if(slot == 15)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ ������ ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Weak");
				}
				else if(slot == 16)
				{
					player.sendMessage(ChatColor.GREEN+"[����] : ������ ���� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage(ChatColor.YELLOW+"(0 ~ 100)");
					u.setString(player, (byte)2, "Jump");
				}
			}
			else if(slot >= 19)
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				if(slot == 19)//ȭ�� ����
				{
					if(monsterListYaml.getInt(MonsterName+".Potion.FireRegistance")==0)
						monsterListYaml.set(MonsterName+".Potion.FireRegistance", 1);
					else
						monsterListYaml.set(MonsterName+".Potion.FireRegistance", 0);
				}
				else if(slot == 20)//���� ȣȩ
				{
					if(monsterListYaml.getInt(MonsterName+".Potion.WaterBreath")==0)
						monsterListYaml.set(MonsterName+".Potion.WaterBreath", 1);
					else
						monsterListYaml.set(MonsterName+".Potion.WaterBreath", 0);
				}
				else if(slot == 21)//����
				{
					if(monsterListYaml.getInt(MonsterName+".Potion.Invisible")==0)
						monsterListYaml.set(MonsterName+".Potion.Invisible", 1);
					else
						monsterListYaml.set(MonsterName+".Potion.Invisible", 0);
				}
				monsterListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
				MonsterPotionGUI(player, MonsterName);
			}
		}
	}

	public void ArmorGUIClick(InventoryClickEvent event)
	{
		if(event.getSlot() >=6 && event.getSlot() <= 8)
			event.setCancelled(true);
		else if(event.getCurrentItem().hasItemMeta())
			if(event.getCurrentItem().getItemMeta().hasLore())
				if(event.getCurrentItem().getItemMeta().getLore().get(0).equals(ChatColor.GRAY + "�̰��� �������� �־� �ּ���."))
					event.getInventory().remove(event.getCurrentItem());
		return;
	}
	
	public void ArmorGUIClose(InventoryCloseEvent event)
	{
		YamlLoader monsterListYaml = new YamlLoader();

		monsterListYaml.getConfig("Monster/MonsterList.yml");
		String MonsterName = ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getDisplayName().toString());
		if(event.getInventory().getItem(0)==new util.Util_GUI().getItemStack(ChatColor.WHITE + "�Ӹ�", 302,0,1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Head.Item", null);
		else
			monsterListYaml.set(MonsterName+".Head.Item", event.getInventory().getItem(0));
		
		if(event.getInventory().getItem(1)==new util.Util_GUI().getItemStack(ChatColor.WHITE + "����", 303,0,1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���.")))
					monsterListYaml.set(MonsterName+".Chest.Item", null);
		else
			monsterListYaml.set(MonsterName+".Chest.Item", event.getInventory().getItem(1));
		if(event.getInventory().getItem(2)==new util.Util_GUI().getItemStack(ChatColor.WHITE + "����", 304,0,1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Leggings.Item", null);
		else
			monsterListYaml.set(MonsterName+".Leggings.Item", event.getInventory().getItem(2));
		if(event.getInventory().getItem(1)==new util.Util_GUI().getItemStack(ChatColor.WHITE + "����", 305,0,1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Boots.Item", null);
		else
			monsterListYaml.set(MonsterName+".Boots.Item", event.getInventory().getItem(3));
		if(event.getInventory().getItem(4)==new util.Util_GUI().getItemStack(ChatColor.WHITE + "����", 267,0,1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Hand.Item", null);
		else
			monsterListYaml.set(MonsterName+".Hand.Item", event.getInventory().getItem(4));
		if(event.getInventory().getItem(5)==new util.Util_GUI().getItemStack(ChatColor.WHITE + "����", 267,0,1,Arrays.asList(ChatColor.GRAY + "�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".OffHand.Item", null);
		else
			monsterListYaml.set(MonsterName+".OffHand.Item", event.getInventory().getItem(5));
		monsterListYaml.saveConfig();
		event.getPlayer().sendMessage(ChatColor.GREEN + "[SYSTEM] : ������ ������ ����Ǿ����ϴ�.");
		return;
	}

	public void MonsterTypeGUIClick(InventoryClickEvent event)
	{
		event.setCancelled(true);
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String MonsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				MonsterOptionSettingGUI(player, MonsterName);
			else
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				if(slot == 0)
					monsterListYaml.set(MonsterName+".Type", "����");
				else if(slot == 1)
					monsterListYaml.set(MonsterName+".Type", "���̾�Ʈ");
				else if(slot == 2)
					monsterListYaml.set(MonsterName+".Type", "�����Ǳ׸�");
				else if(slot == 3)
					monsterListYaml.set(MonsterName+".Type", "ũ����");
				else if(slot == 4)
					monsterListYaml.set(MonsterName+".Type", "����ũ����");
				else if(slot == 5)
					monsterListYaml.set(MonsterName+".Type", "���̷���");
				else if(slot == 6)
					monsterListYaml.set(MonsterName+".Type", "�״����̷���");
				else if(slot == 7)
					monsterListYaml.set(MonsterName+".Type", "����");
				else if(slot == 8)
					monsterListYaml.set(MonsterName+".Type", "�Ź�");
				else if(slot == 9)
					monsterListYaml.set(MonsterName+".Type", "�����Ź�");
				else if(slot == 10)
					monsterListYaml.set(MonsterName+".Type", "����������");
				else if(slot == 11)
					monsterListYaml.set(MonsterName+".Type", "���뽽����");
				else if(slot == 12)
					monsterListYaml.set(MonsterName+".Type", "ū������");
				else if(slot == 13)
					monsterListYaml.set(MonsterName+".Type", "Ư�뽽����");
				else if(slot == 14)
					monsterListYaml.set(MonsterName+".Type", "�ʴ���������");
				else if(slot == 15)
					monsterListYaml.set(MonsterName+".Type", "�������׸�ť��");
				else if(slot == 16)
					monsterListYaml.set(MonsterName+".Type", "���븶�׸�ť��");
				else if(slot == 17)
					monsterListYaml.set(MonsterName+".Type", "ū���׸�ť��");
				else if(slot == 18)
					monsterListYaml.set(MonsterName+".Type", "Ư�븶�׸�ť��");
				else if(slot == 19)
					monsterListYaml.set(MonsterName+".Type", "�ʴ������׸�ť��");
				else if(slot == 20)
					monsterListYaml.set(MonsterName+".Type", "����");
				else if(slot == 21)
					monsterListYaml.set(MonsterName+".Type", "����Ʈ");
				else if(slot == 22)
					monsterListYaml.set(MonsterName+".Type", "������");
				else if(slot == 23)
					monsterListYaml.set(MonsterName+".Type", "������");
				else if(slot == 24)
					monsterListYaml.set(MonsterName+".Type", "���������");
				else if(slot == 25)
					monsterListYaml.set(MonsterName+".Type", "�ֹ�");
				else if(slot == 26)
					monsterListYaml.set(MonsterName+".Type", "����");
				else if(slot == 27)
					monsterListYaml.set(MonsterName+".Type", "��ȣ��");
				else if(slot == 28)
					monsterListYaml.set(MonsterName+".Type", "������");
				else if(slot == 29)
					monsterListYaml.set(MonsterName+".Type", "��Ŀ");
				else if(slot == 30)
					monsterListYaml.set(MonsterName+".Type", "�����巡��");
				else if(slot == 31)
					monsterListYaml.set(MonsterName+".Type", "����");
				else if(slot == 32)
					monsterListYaml.set(MonsterName+".Type", "��");
				else if(slot == 33)
					monsterListYaml.set(MonsterName+".Type", "��");
				else if(slot == 34)
					monsterListYaml.set(MonsterName+".Type", "������");
				else if(slot == 35)
					monsterListYaml.set(MonsterName+".Type", "��");
				else if(slot == 36)
					monsterListYaml.set(MonsterName+".Type", "��¡��");
				else if(slot == 37)
					monsterListYaml.set(MonsterName+".Type", "����");
				else if(slot == 38)
					monsterListYaml.set(MonsterName+".Type", "������");
				else if(slot == 39)
					monsterListYaml.set(MonsterName+".Type", "�����");
				else if(slot == 40)
					monsterListYaml.set(MonsterName+".Type", "��");
				else if(slot == 41)
					monsterListYaml.set(MonsterName+".Type", "�䳢");
				else if(slot == 42)
					monsterListYaml.set(MonsterName+".Type", "�ϱذ�");
				else if(slot == 43)
					monsterListYaml.set(MonsterName+".Type", "��");
				else if(slot == 44)
					monsterListYaml.set(MonsterName+".Type", "����ũ����Ż");
				monsterListYaml.saveConfig();
				MonsterOptionSettingGUI(player, MonsterName);
			}
		}
	}
}
