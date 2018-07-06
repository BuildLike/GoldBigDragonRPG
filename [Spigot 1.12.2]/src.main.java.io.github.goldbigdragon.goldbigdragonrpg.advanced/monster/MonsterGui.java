package monster;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import admin.OPboxGui;
import battle.BattleCalculator;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class MonsterGui extends GuiUtil
{
	public void monsterListGUI(Player player, int page)
	{
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		String uniqueCode = "��0��0��8��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��� : " + (page+1));

		Object[] a= monsterYaml.getKeys().toArray();

		byte loc=0;
		StringBuilder sb = new StringBuilder();
		String monsterName = null;
		for(int count = page*45; count < a.length;count++)
		{
			if(loc >= 45) break;
			monsterName = a[count].toString();
			sb = new StringBuilder();
			sb.append("%enter%��f��l �̸� : ��f");
			sb.append(monsterYaml.getString(monsterName+".Name"));
			sb.append("%enter%");
			sb.append("��f��l Ÿ�� : ��f");
			sb.append(monsterYaml.getString(monsterName+".Type"));
			sb.append("%enter%");
			sb.append("��f��l ���� ���̿� : ��f");
			sb.append(monsterYaml.getString(monsterName+".Biome"));
			sb.append("%enter%");
			sb.append("��c��l ����� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".HP"));
			sb.append("%enter%");
			sb.append("��b��l ����ġ : ��f");
			sb.append(monsterYaml.getInt(monsterName+".EXP"));
			sb.append("%enter%");
			sb.append("��e��l ��� �ݾ� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".MIN_Money"));
			sb.append(" ~ ");
			sb.append(monsterYaml.getInt(monsterName+".MAX_Money"));
			sb.append("%enter%");
			sb.append("��c��l ");
			sb.append(MainServerOption.statSTR);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".STR"));
			sb.append("��7 [���� : ");
			sb.append(BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), true));
			sb.append(" ~ ");
			sb.append(BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), false));
			sb.append("]%enter%");
			sb.append("��a��l ");
			sb.append(MainServerOption.statDEX);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".DEX"));
			sb.append("��7 [Ȱ�� : ");
			sb.append(BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, true));
			sb.append(" ~ ");
			sb.append(BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, false));
			sb.append("]%enter%");
			sb.append("��9��l ");
			sb.append(MainServerOption.statINT);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".INT"));
			sb.append("��7 [���� : ");
			sb.append(monsterYaml.getInt(monsterName+".INT")/4);
			sb.append(" ~ ");
			sb.append((int)(monsterYaml.getInt(monsterName+".INT")/2.5));
			sb.append("]%enter%");
			sb.append("��7��l ");
			sb.append(MainServerOption.statWILL);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".WILL"));
			sb.append("��7 [ũ�� : ");
			sb.append(BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0));
			sb.append(" %]%enter%");
			sb.append("��e��l ");
			sb.append(MainServerOption.statLUK);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".LUK"));
			sb.append("��7 [ũ�� : ");
			sb.append(BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0));
			sb.append(" %]%enter%");

			sb.append("��7��l ��� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".DEF"));
			sb.append("%enter%");
			sb.append("��b��l ��ȣ : ��f");
			sb.append(monsterYaml.getInt(monsterName+".Protect"));
			sb.append("%enter%");
			sb.append("��9��l ���� ��� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".Magic_DEF"));
			sb.append("%enter%");
			sb.append("��1��l ���� ��ȣ : ��f");
			sb.append(monsterYaml.getInt(monsterName+".Magic_Protect"));
			sb.append("%enter%");
			sb.append("%enter%��e��l[Shift + �� Ŭ���� ������ ����]%enter%��c��l[Shift + �� Ŭ���� ���� ����]%enter%");

			String[] scriptA = sb.toString().split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			int id = 383;
			byte data = 0;
			switch(monsterYaml.getString(monsterName+".Type"))
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
			
			stack("��f"+monsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(a.length-(page*44)>45)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		stack("��f��l�� ����", 339,0,1,Arrays.asList("��7���ο� ���͸� �����մϴ�."), 49, inv);
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void MonsterOptionSettingGUI(Player player,String MonsterName)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");

		String UniqueCode = "��0��0��8��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����");

		String Lore=null;			
		Lore = "%enter%��f��l �̸� : ��f"+monsterListYaml.getString(MonsterName+".Name")+"%enter%";
		Lore = Lore+"��f��l Ÿ�� : ��f"+monsterListYaml.getString(MonsterName+".Type")+"%enter%";
		Lore = Lore+"��f��l ���� ���̿� : ��f"+monsterListYaml.getString(MonsterName+".Biome")+"%enter%";
		Lore = Lore+"��c��l ����� : ��f"+monsterListYaml.getInt(MonsterName+".HP")+"%enter%";
		Lore = Lore+"��b��l ����ġ : ��f"+monsterListYaml.getInt(MonsterName+".EXP")+"%enter%";
		Lore = Lore+"��e��l ��� �ݾ� : ��f"+monsterListYaml.getInt(MonsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(MonsterName+".MAX_Money")+"%enter%";
		Lore = Lore+"��c��l "+MainServerOption.statSTR+" : ��f"+monsterListYaml.getInt(MonsterName+".STR")
		+"��7 [���� : " + BattleCalculator.getCombatDamage(null, 0, monsterListYaml.getInt(MonsterName+".STR"), true) + " ~ " + BattleCalculator.getCombatDamage(null, 0, monsterListYaml.getInt(MonsterName+".STR"), false) + "]%enter%";
		Lore = Lore+"��a��l "+MainServerOption.statDEX+" : ��f"+monsterListYaml.getInt(MonsterName+".DEX")
		+"��7 [Ȱ�� : " + BattleCalculator.returnRangeDamageValue(null, monsterListYaml.getInt(MonsterName+".DEX"), 0, true) + " ~ " + BattleCalculator.returnRangeDamageValue(null, monsterListYaml.getInt(MonsterName+".DEX"), 0, false) + "]%enter%";
		Lore = Lore+"��9��l "+MainServerOption.statINT+" : ��f"+monsterListYaml.getInt(MonsterName+".INT")
		+"��7 [���� : " + (monsterListYaml.getInt(MonsterName+".INT")/4)+ " ~ "+(int)(monsterListYaml.getInt(MonsterName+".INT")/2.5)+"]%enter%";
		Lore = Lore+"��7��l "+MainServerOption.statWILL+" : ��f"+monsterListYaml.getInt(MonsterName+".WILL")
		+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterListYaml.getInt(MonsterName+".LUK"), (int)monsterListYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
		Lore = Lore+"��e��l "+MainServerOption.statLUK+" : ��f"+monsterListYaml.getInt(MonsterName+".LUK")
		+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterListYaml.getInt(MonsterName+".LUK"), (int)monsterListYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
		Lore = Lore+"��7��l ��� : ��f"+monsterListYaml.getInt(MonsterName+".DEF")+"%enter%";
		Lore = Lore+"��b��l ��ȣ : ��f"+monsterListYaml.getInt(MonsterName+".Protect")+"%enter%";
		Lore = Lore+"��9��l ���� ��� : ��f"+monsterListYaml.getInt(MonsterName+".Magic_DEF")+"%enter%";
		Lore = Lore+"��1��l ���� ��ȣ : ��f"+monsterListYaml.getInt(MonsterName+".Magic_Protect")+"%enter%";

		
		String[] scriptA = Lore.split("%enter%");
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

		removeFlagStack("��f"+ MonsterName, id,data,1,Arrays.asList(scriptA), 19, inv);
		
		
		removeFlagStack("��3[    �̸� ����    ]", 421,0,1,Arrays.asList("��f������ �̸���","��f�����մϴ�.","","��f[    ���� �̸�    ]"," ��f"+monsterListYaml.getString(MonsterName+".Name"),""), 13, inv);
		removeFlagStack("��3[    Ÿ�� ����    ]", 383,0,1,Arrays.asList("��f������ Ÿ����","��f�����մϴ�.","","��f[    ���� Ÿ��    ]"," ��f"+monsterListYaml.getString(MonsterName+".Type"),""), 14, inv);

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
		
		removeFlagStack("��3[ ���� ���̿� ���� ]", id,data,1,Arrays.asList("��f���Ͱ� �����ϴ�","��f���̿��� �����մϴ�.","","��f[    ���� ���̿�    ]"," ��f"+monsterListYaml.getString(MonsterName+".Biome"),""), 15, inv);
		removeFlagStack("��3[    ����� ����    ]", 351,1,1,Arrays.asList("��f������ �������","��f�����մϴ�.","","��f[    ���� �����    ]"," ��f"+monsterListYaml.getInt(MonsterName+".HP"),""), 16, inv);
		removeFlagStack("��3[    ����ġ ����    ]", 384,0,1,Arrays.asList("��f���� ��ɽ� ���","��f����ġ ���� �����մϴ�.","","��f[    ���� ����ġ    ]"," ��f"+monsterListYaml.getInt(MonsterName+".EXP"),""), 22, inv);
		removeFlagStack("��3[  ��� �ݾ� ����  ]", 266,0,1,Arrays.asList("��f���� ��ɽ� ���","��f�ݾ��� �����մϴ�.","","��f[    ���� �ݾ�    ]"," ��f"+monsterListYaml.getInt(MonsterName+".MIN_Money")+" ~ "+monsterListYaml.getInt(MonsterName+".MAX_Money"),""), 23, inv);
		removeFlagStack("��3[    ��� ����    ]", 307,0,1,Arrays.asList("��f������ ���","��f���� �մϴ�.","","��e[    ��Ŭ���� ����    ]",""), 24, inv);
		removeFlagStack("��3[  ��� ����� ����  ]", 54,0,1,Arrays.asList("��f���� ��ɽ� ����Ǵ�","��f��� Ȯ���� �����մϴ�.","","��f[    ���� �����    ]"," ��f�Ӹ� : "+monsterListYaml.getInt(MonsterName+".Head.DropChance")/10.0+"%"
				," ��f���� : "+monsterListYaml.getInt(MonsterName+".Chest.DropChance")/10.0+"%"
				," ��f���� : "+monsterListYaml.getInt(MonsterName+".Leggings.DropChance")/10.0+"%"
				," ��f�Ź� : "+monsterListYaml.getInt(MonsterName+".Boots.DropChance")/10.0+"%"
				," ��f���� : "+monsterListYaml.getInt(MonsterName+".Hand.DropChance")/10.0+"%","","��e[    ��Ŭ���� ����   ]",""), 25, inv);
		removeFlagStack("��3[  ���� ���� ����  ]", 399,0,1,Arrays.asList("��f������ �⺻ ������","��f�����մϴ�.",""), 31, inv);
		removeFlagStack("��3[  ���� ��� ����  ]", 310,0,1,Arrays.asList("��f������ ��� �� ��ȣ��","��f�����մϴ�.",""), 32, inv);
		
		Lore = "��f������ AI�� �����մϴ�.%enter%%enter%��f[    ���� AI    ]%enter%��f"+monsterListYaml.getString(MonsterName+".AI")+"%enter%%enter%";
		if(Type.equals("�ʴ���������")||Type.equals("Ư�뽽����")||Type.equals("ū������")||
		Type.equals("���뽽����")||Type.equals("����������")||Type.equals("ū���׸�ť��")||Type.equals("Ư�븶�׸�ť��")||Type.equals("���븶�׸�ť��")||
		Type.equals("���׸�ť��")||Type.equals("�������׸�ť��")||Type.equals("����Ʈ")||Type.equals("����")
		||Type.equals("�����巡��")||Type.equals("��Ŀ")||Type.equals("��")||Type.equals("��")
		||Type.equals("����")||Type.equals("��")||Type.equals("����")||Type.equals("�䳢")
		||Type.equals("������")||Type.equals("����")||Type.equals("��")||Type.equals("������")
		||Type.equals("��¡��")||Type.equals("�ֹ�")||Type.equals("�����")||Type.equals("��")
		)
		Lore = Lore + "��c[���� ���� �� ���� Ÿ����%enter%��c������ ���� AI���� ����մϴ�.]";
		else
		{
			switch(monsterListYaml.getString(MonsterName+".AI"))
			{
			case "�Ϲ� �ൿ" :
				Lore = Lore+"��f�Ϲ����� �ൿ�� �մϴ�.%enter%";
				break;
			case "����" :
				Lore = Lore+"��f������ ���� �������մϴ�.%enter%";break;
			case "�񼱰�" :
				Lore = Lore+"��f���ݹޱ� ������ �������� �ʽ��ϴ�.%enter%";break;
			case "������" :
				Lore = Lore+"��f���ݹ� �̵��� ���� �ʽ��ϴ�.%enter%";break;
			case "����" :
				Lore = Lore+"��f���ݹ��� ��� ����ġ�� �ٻڸ�,%enter%��f����� �������� �ʽ��ϴ�.%enter%";break;
			}
		}
		
		scriptA = Lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  " "+scriptA[counter];
		
		
		removeFlagStack("��3[  ���� AI ����  ]", 137,0,1,Arrays.asList(scriptA), 33, inv);
		removeFlagStack("��3[    ���� ȿ��    ]", 373,0,1,Arrays.asList("��f���Ϳ��� ���� ȿ����","��f�ο��մϴ�.",""), 34, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+MonsterName), 53, inv);
		player.openInventory(inv);
	}
	
	public void MonsterPotionGUI(Player player, String MonsterName)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");
		String UniqueCode = "��0��0��8��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����");
		
		removeFlagStack("��3[  ���  ]", 373,8193,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.Regenerate")), 10, inv);
		removeFlagStack("��3[  ��  ]", 373,8196,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.Poison")), 11, inv);
		removeFlagStack("��3[  �ż�  ]", 373,8194,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.Speed")), 12, inv);
		removeFlagStack("��3[  ����  ]", 373,8234,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.Slow")), 13, inv);
		removeFlagStack("��3[  ��  ]", 373,8201,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.Strength")), 14, inv);
		removeFlagStack("��3[  ������  ]", 373,8232,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.Weak")), 15, inv);
		removeFlagStack("��3[  ����  ]", 373,8267,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(MonsterName+".Potion.JumpBoost")), 16, inv);

		if(monsterListYaml.getInt(MonsterName+".Potion.FireRegistance")!=0)
			removeFlagStack("��3[  ȭ�� ����  ]", 373,8227,1,Arrays.asList("��a[  ���� ����  ]"), 19, inv);
		else
			removeFlagStack("��3[  ȭ�� ����  ]", 166,0,1,Arrays.asList("��c[  ���� ������  ]"), 19, inv);
		if(monsterListYaml.getInt(MonsterName+".Potion.WaterBreath")!=0)
			removeFlagStack("��3[  ���� ȣȩ  ]", 373,8237,1,Arrays.asList("��a[  ���� ����  ]"), 20, inv);
		else
			removeFlagStack("��3[  ���� ȣȩ  ]", 166,0,1,Arrays.asList("��c[  ���� ������  ]"), 20, inv);
		if(monsterListYaml.getInt(MonsterName+".Potion.Invisible")!=0)
			removeFlagStack("��3[  ����  ]", 373,8238,1,Arrays.asList("��a[  ���� ����  ]"), 21, inv);
		else
			removeFlagStack("��3[  ����  ]", 166,0,1,Arrays.asList("��c[  ���� ������  ]"), 21, inv);
			

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+MonsterName), 53, inv);
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
			stack("��f�Ӹ�", 302,(byte)0,(byte)1,Arrays.asList("��7�̰��� �������� �־� �ּ���."), (byte)0, inv);

		if(monsterListYaml.contains(mob + ".Chest.Item")==true&&
				monsterListYaml.getItemStack(mob + ".Chest.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(1, monsterListYaml.getItemStack(mob + ".Chest.Item"));
		else
			stack("��f����", 303,(byte)0,(byte)1,Arrays.asList("��7�̰��� �������� �־� �ּ���."), (byte)1, inv);

		if(monsterListYaml.contains(mob + ".Leggings.Item")==true&&
				monsterListYaml.getItemStack(mob + ".Leggings.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(2, monsterListYaml.getItemStack(mob + ".Leggings.Item"));
		else
			stack("��f����", 304,(byte)0,(byte)1,Arrays.asList("��7�̰��� �������� �־� �ּ���."), (byte)2, inv);

		if(monsterListYaml.contains(mob + ".Boots.Item")==true&&
		monsterListYaml.getItemStack(mob + ".Boots.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(3, monsterListYaml.getItemStack(mob + ".Boots.Item"));
		else
			stack("��f����", 305,(byte)0,(byte)1,Arrays.asList("��7�̰��� �������� �־� �ּ���."), (byte)3, inv);

		if(monsterListYaml.contains(mob + ".Hand.Item")==true&&
		monsterListYaml.getItemStack(mob + ".Hand.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(4, monsterListYaml.getItemStack(mob + ".Hand.Item"));
		else
			stack("��f������", 267,(byte)0,(byte)1,Arrays.asList("��7�̰��� �������� �־� �ּ���."), (byte)4, inv);

		if(monsterListYaml.contains(mob + ".OffHand.Item")==true&&
		monsterListYaml.getItemStack(mob + ".OffHand.Item").equals(new ItemStack(Material.AIR))==false)
			inv.setItem(5, monsterListYaml.getItemStack(mob + ".OffHand.Item"));
		else
			stack("��f�޼�", 267,(byte)0,(byte)1,Arrays.asList("��7�̰��� �������� �־� �ּ���."), (byte)5, inv);
		
		stack("��f"+ mob, 416,(byte)0,(byte)1,Arrays.asList("��8"+ mob+"�� ���� ����Դϴ�." ), (byte)8, inv);
		stack("��f", 30,(byte)0,(byte)1,Arrays.asList("��7�̰����� ��������","��7�÷����� ������."), (byte)7, inv);
		stack("��f", 30,(byte)0,(byte)1,Arrays.asList("��7�̰����� ��������","��7�÷����� ������."), (byte)6, inv);
		
		player.openInventory(inv);
		return;
	}

	public void MonsterTypeGUI(Player player, String MonsterName, int page)
	{
		String UniqueCode = "��1��0��8��0��b��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� Ÿ�� : " + (page+1) + " / 2");

		if(page==0)
		{
			MonsterStack(inv, 0, 54,  0, "��2��l[����]", Arrays.asList("��0"+54));
			MonsterStack(inv, 1, 27,  0, "��2��l[�ֹ�����]", Arrays.asList("��0"+27));
			MonsterStack(inv, 2, 53,  0, "��2��l[���̾�Ʈ]", Arrays.asList("��0"+53));
			MonsterStack(inv, 3, 29,  0, "��2��l[����]", Arrays.asList("��0"+29));
			MonsterStack(inv, 4, 57,  0, "��d��l[�����Ǳ׸�]", Arrays.asList("��0"+57));
			MonsterStack(inv, 5, 23,  0, "��e��l[�㽺ũ]", Arrays.asList("��0"+23));
			MonsterStack(inv, 6, 50,  0, "��a��l[ũ����]", Arrays.asList("��0"+50));
			MonsterStack(inv, 7, 50,  1, "��a��l[����ũ����]", Arrays.asList("��0"+50));
			MonsterStack(inv, 8, 51,  0, "��f��l[���̷���]", Arrays.asList("��0"+51));
			MonsterStack(inv, 9, 5,  0, "��8��l[�״����̷���]", Arrays.asList("��0"+5));
			MonsterStack(inv, 10, 28,  0, "��f��l[���̷��渻]", Arrays.asList("��0"+28));
			MonsterStack(inv, 11, 6,  0, "��b��l[��Ʈ����]", Arrays.asList("��0"+6));
			MonsterStack(inv, 12, 64,  0, "��8��l[����]", Arrays.asList("��0"+64));
			MonsterStack(inv, 13, 52,  0, "��7��l[�Ź�]", Arrays.asList("��0"+52));
			MonsterStack(inv, 14, 59,  1, "��7��l[�����Ź�]", Arrays.asList("��0"+59));
			MonsterStack(inv, 13, 55,  0, "��a��l[����������]", Arrays.asList("��0"+55));
			MonsterStack(inv, 14, 55,  1, "��a��l[���뽽����]", Arrays.asList("��0"+55));
			MonsterStack(inv, 15, 55,  2, "��a��l[ū������]", Arrays.asList("��0"+55));
			MonsterStack(inv, 16, 55,  3, "��a��l[Ư�뽽����]", Arrays.asList("��0"+55));
			MonsterStack(inv, 17, 55,  4, "��a��l[�ʴ���������]", Arrays.asList("��0"+55));
			MonsterStack(inv, 18, 62,  0, "��7��l[�������׸�ť��]", Arrays.asList("��0"+62));
			MonsterStack(inv, 19, 62,  1, "��7��l[���븶�׸�ť��]", Arrays.asList("��0"+62));
			MonsterStack(inv, 20, 62,  2, "��7��l[ū���׸�ť��]", Arrays.asList("��0"+62));
			MonsterStack(inv, 21, 62,  3, "��7��l[Ư�븶�׸�ť��]", Arrays.asList("��0"+62));
			MonsterStack(inv, 22, 62,  4, "��7��l[�ʴ������׸�ť��]", Arrays.asList("��0"+62));
			MonsterStack(inv, 23, 65,  0, "��8��l[����]", Arrays.asList("��0"+65));
			MonsterStack(inv, 24, 56,  0, "��f��l[����Ʈ]", Arrays.asList("��0"+56));
			MonsterStack(inv, 25, 61,  0, "��e��l[������]", Arrays.asList("��0"+61));
			MonsterStack(inv, 26, 60,  0, "��7��l[������]", Arrays.asList("��0"+60));
			MonsterStack(inv, 27, 67,  0, "��5��l[���������]", Arrays.asList("��0"+67));
			MonsterStack(inv, 28, 120,  0, "��a��l[�ֹ�]", Arrays.asList("��0"+120));
			MonsterStack(inv, 29, 66,  0, "��5��l[����]", Arrays.asList("��0"+66));
			MonsterStack(inv, 30, 36,  0, "��7��l[������]", Arrays.asList("��0"+36));
			MonsterStack(inv, 31, 34,  0, "��5��l[��ȯ��]", Arrays.asList("��0"+34));
			MonsterStack(inv, 32, 35,  0, "��9��l[����]", Arrays.asList("��0"+35));
			MonsterStack(inv, 33, 68,  0, "��3��l[�����]", Arrays.asList("��0"+68));
			MonsterStack(inv, 34, 4,  0, "��3��l[���������]", Arrays.asList("��0"+4));
			MonsterStack(inv, 35, 58,  0, "��8��l[������]", Arrays.asList("��0"+58));
			MonsterStack(inv, 36, 69,  0, "��5��l[��Ŀ]", Arrays.asList("��0"+69));
			MonsterStack(inv, 37, 63,  0, "��8��l[�����巡��]", Arrays.asList("��0"+63));
			MonsterStack(inv, 39, 90,  0, "��d��l[����]", Arrays.asList("��0"+90));
			MonsterStack(inv, 40, 91,  0, "��f��l[��]", Arrays.asList("��0"+91));
			MonsterStack(inv, 41, 92,  0, "��7��l[��]", Arrays.asList("��0"+92));
			MonsterStack(inv, 42, 96,  0, "��c��l[������]", Arrays.asList("��0"+96));
			MonsterStack(inv, 43, 93,  0, "��f��l[��]", Arrays.asList("��0"+93));
			MonsterStack(inv, 44, 94,  0, "��8��l[��¡��]", Arrays.asList("��0"+94));
		  	removeFlagStack("��f��l���� ������", 323, 0, 1, null, 50, inv);
		}
		else
		{
			MonsterStack(inv, 0, 95,  0, "��f��l[����]", Arrays.asList("��0"+95));
			MonsterStack(inv, 1, 98,  0, "��e��l[������]", Arrays.asList("��0"+98));
			MonsterStack(inv, 2, 97,  0, "��f��l[�����]", Arrays.asList("��0"+97));
			MonsterStack(inv, 3, 99,  0, "��f��l[ö��]", Arrays.asList("��0"+99));
			MonsterStack(inv, 4, 101,  0, "��f��l[�䳢]", Arrays.asList("��0"+101));
			MonsterStack(inv, 5, 102,  0, "��f��l[�ϱذ�]", Arrays.asList("��0"+102));
			MonsterStack(inv, 6, 31,  0, "��6��l[�糪��]", Arrays.asList("��0"+31));
			MonsterStack(inv, 7, 32,  0, "��6��l[���]", Arrays.asList("��0"+32));
			MonsterStack(inv, 8, 103,  0, "��6��l[��]", Arrays.asList("��0"+103));
			MonsterStack(inv, 9, 100,  0, "��6��l[��]", Arrays.asList("��0"+100));
		  	removeFlagStack("��f��l���� ������", 323, 0, 1, null, 48, inv);
		}

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+MonsterName), 53, inv);
		player.openInventory(inv);
	}

	public void MonsterStack(Inventory inv, int loc, int monsterID, int option, String displayName, List<String> lore)
	{
		int ID = 383;
		int Data = 0;
		int Amount = 1;

		if(monsterID==4)//���� �����
		{
			ID=409;
			Amount = 2;
		}
		else if(monsterID==5)//���� ���̷���
			ID=263;
		else if(monsterID==6)//��Ʈ����
			ID=440;
		else if(monsterID==23)//�㽺ũ
		{
			ID=24;
			Data = 1;
		}
		else if(monsterID==27)//�ֹ� ����
		{
			ID=367;
			Amount = 2;
		}
		else if(monsterID==28)//���̷��� ��
		{
			ID=352;
			Amount = 2;
		}
		else if(monsterID==29)//���� ��
		{
			ID=367;
			Amount = 4;
		}
		else if(monsterID==31)//�糪��
			ID=54;
		else if(monsterID==32)//�¿�
		{
			ID=54;
			Amount = 1;
		}
		else if(monsterID==34)//����Ŀ
			ID=449;
		else if(monsterID==35)//����
			ID=452;
		else if(monsterID==36)//���������
			ID=258;
		else if(monsterID==49)//�ΰ�
		{
			ID=397;
			Data = 3;
		}
		else if(monsterID==50)//ũ����
		{
			ID=289;
			if(option==1)//���� ũ����
				Amount = 2;
		}
		else if(monsterID==51)//���̷���
			ID=352;
		else if(monsterID==52)//�Ź�
			ID=287;
		else if(monsterID==53)//���̾�Ʈ
		{
			ID=367;
			Amount = 3;
		}
		else if(monsterID==54)//����
			ID=367;
		else if(monsterID==55)//������
		{
			ID=341;
			Amount =  (option+Amount);
		}
		else if(monsterID==56)//����Ʈ
			ID=370;
		else if(monsterID==57)//���� �Ǳ׸�
			ID=283;
		else if(monsterID==58)//������
			ID=368;
		else if(monsterID==59)//�����Ź�
		{
			ID=287;
			Amount = 2;
		}
		else if(monsterID==60)//������
			ID=1;
		else if(monsterID==61)//������
			ID=369;
		else if(monsterID==62)//���׸� ť��
		{
			ID=378;
			Amount =  (option+Amount);
		}
		else if(monsterID==63)//���� �巡��
			ID=122;
		else if(monsterID==64)//����
			ID=399;
		else if(monsterID==65)//����
			ID=362;
		else if(monsterID==66)//����
			ID=438;
		else if(monsterID==67)//���� ����Ʈ
			ID=432;
		else if(monsterID==68)//�����
			ID=409;
		else if(monsterID==69)//��Ŀ
			ID=450;
		else if(monsterID==90)//����
			ID=319;
		else if(monsterID==91)//��
			ID=423;
		else if(monsterID==92)//��
			ID=363;
		else if(monsterID==93)//��
			ID=365;
		else if(monsterID==94)//��¡��
			ID=351;
		else if(monsterID==95)//����
			ID=280;
		else if(monsterID==96)//������
			ID=40;
		else if(monsterID==97)//�����
			ID=332;
		else if(monsterID==98)//������
			ID=349;
		else if(monsterID==99)//ö��
			ID=265;
		else if(monsterID==100)//��
			ID=417;
		else if(monsterID==101)//�䳢
			ID=411;
		else if(monsterID==102)//�ϱذ�
		{
			ID=349;
			Data = 1;
		}
		else if(monsterID==103)//��
		{
			ID=54;
			Amount = 2;
		}
		else if(monsterID==120)//�ֹ�
			ID=388;
		else if(monsterID==200)//���� ũ����Ż
			ID=426;
		
		removeFlagStack(displayName, ID, Data, Amount, lore, loc, inv);
		return;
	}

	public void MonsterListGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 1);
			else if(slot == 48)//���� ������
				monsterListGUI(player, page-1);
			else if(slot == 49)//�� ����
			{
				player.closeInventory();
				player.sendMessage("��a[����] : ���ο� ���� �̸��� ���� �ּ���!");
				UserDataObject u = new UserDataObject();
				u.setType(player, "Monster");
				u.setString(player, (byte)1, "NM");
			}
			else if(slot == 50)//���� ������
				monsterListGUI(player, page+1);
			else
			{
				if(event.isLeftClick() == true&&event.isShiftClick()==false)
					MonsterOptionSettingGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isLeftClick() == true&&event.isShiftClick())
					new monster.MonsterSpawn().SpawnEggGive(player,ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isRightClick()&&event.isShiftClick())
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
					YamlLoader monsterListYaml = new YamlLoader();
					monsterListYaml.getConfig("Monster/MonsterList.yml");
					monsterListYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					monsterListYaml.saveConfig();
					monsterListGUI(player, page);
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
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String MonsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				monsterListGUI(player, 0);
			else if(slot == 14)//�� Ÿ�� ����
				MonsterTypeGUI(player, MonsterName, 0);
			else if(slot == 15)//���� ���̿� ����
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String Type = monsterListYaml.getString(MonsterName+".Biome");
				if(Type.equals("NULL"))
					monsterListYaml.set(MonsterName+".Biome", "BEACH");
				else if(Type.equals("BEACH"))
					monsterListYaml.set(MonsterName+".Biome", "DESERT");
				else if(Type.equals("DESERT"))
					monsterListYaml.set(MonsterName+".Biome", "EXTREME_HILLS");
				else if(Type.equals("EXTREME_HILLS"))
					monsterListYaml.set(MonsterName+".Biome", "FOREST");
				else if(Type.equals("FOREST"))
					monsterListYaml.set(MonsterName+".Biome", "HELL");
				else if(Type.equals("HELL"))
					monsterListYaml.set(MonsterName+".Biome", "JUNGLE");
				else if(Type.equals("JUNGLE"))
					monsterListYaml.set(MonsterName+".Biome", "MESA");
				else if(Type.equals("MESA"))
					monsterListYaml.set(MonsterName+".Biome", "OCEAN");
				else if(Type.equals("OCEAN"))
					monsterListYaml.set(MonsterName+".Biome", "PLAINS");
				else if(Type.equals("PLAINS"))
					monsterListYaml.set(MonsterName+".Biome", "RIVER");
				else if(Type.equals("RIVER"))
					monsterListYaml.set(MonsterName+".Biome", "SAVANNA");
				else if(Type.equals("SAVANNA"))
					monsterListYaml.set(MonsterName+".Biome", "SKY");
				else if(Type.equals("SKY"))
					monsterListYaml.set(MonsterName+".Biome", "SMALL_MOUNTAINS");
				else if(Type.equals("SMALL_MOUNTAINS"))
					monsterListYaml.set(MonsterName+".Biome", "SWAMPLAND");
				else if(Type.equals("SWAMPLAND"))
					monsterListYaml.set(MonsterName+".Biome", "TAIGA");
				else if(Type.equals("TAIGA"))
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
				if(Type.equals("�Ϲ� �ൿ"))
					monsterListYaml.set(MonsterName+".AI", "����");
				else if(Type.equals("����"))
					monsterListYaml.set(MonsterName+".AI", "�񼱰�");
				else if(Type.equals("�񼱰�"))
					monsterListYaml.set(MonsterName+".AI", "����");
				else if(Type.equals("����"))
					monsterListYaml.set(MonsterName+".AI", "������");
				else if(Type.equals("������"))
					monsterListYaml.set(MonsterName+".AI", "�Ϲ� �ൿ");
				else
					monsterListYaml.set(MonsterName+".AI", "�Ϲ� �ൿ");
				monsterListYaml.saveConfig();
				MonsterOptionSettingGUI(player, MonsterName);
			}
			else if(slot == 24)//��� ����
			{
				SoundEffect.playSound(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.0F);
				ArmorGUI(player, MonsterName);
			}
			else if(slot == 34)//���� ���� ȿ��
				MonsterPotionGUI(player, MonsterName);
			else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
			{
				UserDataObject u = new UserDataObject();
				player.closeInventory();
				u.setType(player, "Monster");
				u.setString(player, (byte)2, ChatColor.stripColor(event.getInventory().getItem(19).getItemMeta().getDisplayName()));
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

	public void MonsterPotionGUIClick(InventoryClickEvent event)
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
			String MonsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				MonsterOptionSettingGUI(player, MonsterName);
			else if(slot >= 10 && slot <= 16)
			{
				UserDataObject u = new UserDataObject();
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				player.closeInventory();
				u.setType(player, "Monster");
				u.setString(player, (byte)1, "Potion");
				u.setString(player, (byte)3, MonsterName);
				if(slot == 10)
				{
					player.sendMessage("��a[����] : ������ ��� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Regenerate");
				}
				else if(slot == 11)
				{
					player.sendMessage("��a[����] : ������ �� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Poision");
				}
				else if(slot == 12)
				{
					player.sendMessage("��a[����] : ������ �ż� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Speed");
				}
				else if(slot == 13)
				{
					player.sendMessage("��a[����] : ������ ���� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Slow");
				}
				else if(slot == 14)
				{
					player.sendMessage("��a[����] : ������ �� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Strength");
				}
				else if(slot == 15)
				{
					player.sendMessage("��a[����] : ������ ������ ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Weak");
				}
				else if(slot == 16)
				{
					player.sendMessage("��a[����] : ������ ���� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
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
				SoundEffect.playSound(player, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
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
				if(event.getCurrentItem().getItemMeta().getLore().get(0).equals("��7�̰��� �������� �־� �ּ���."))
					event.getInventory().remove(event.getCurrentItem());
		return;
	}
	
	public void ArmorGUIClose(InventoryCloseEvent event)
	{
		YamlLoader monsterListYaml = new YamlLoader();

		monsterListYaml.getConfig("Monster/MonsterList.yml");
		String MonsterName = ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getDisplayName().toString());
		if(event.getInventory().getItem(0)==new util.GuiUtil().getItemStack("��f�Ӹ�", 302,0,1,Arrays.asList("��7�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Head.Item", null);
		else
			monsterListYaml.set(MonsterName+".Head.Item", event.getInventory().getItem(0));
		
		if(event.getInventory().getItem(1)==new util.GuiUtil().getItemStack("��f����", 303,0,1,Arrays.asList("��7�̰��� �������� �־� �ּ���.")))
					monsterListYaml.set(MonsterName+".Chest.Item", null);
		else
			monsterListYaml.set(MonsterName+".Chest.Item", event.getInventory().getItem(1));
		if(event.getInventory().getItem(2)==new util.GuiUtil().getItemStack("��f����", 304,0,1,Arrays.asList("��7�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Leggings.Item", null);
		else
			monsterListYaml.set(MonsterName+".Leggings.Item", event.getInventory().getItem(2));
		if(event.getInventory().getItem(1)==new util.GuiUtil().getItemStack("��f����", 305,0,1,Arrays.asList("��7�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Boots.Item", null);
		else
			monsterListYaml.set(MonsterName+".Boots.Item", event.getInventory().getItem(3));
		if(event.getInventory().getItem(4)==new util.GuiUtil().getItemStack("��f����", 267,0,1,Arrays.asList("��7�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".Hand.Item", null);
		else
			monsterListYaml.set(MonsterName+".Hand.Item", event.getInventory().getItem(4));
		if(event.getInventory().getItem(5)==new util.GuiUtil().getItemStack("��f����", 267,0,1,Arrays.asList("��7�̰��� �������� �־� �ּ���.")))
			monsterListYaml.set(MonsterName+".OffHand.Item", null);
		else
			monsterListYaml.set(MonsterName+".OffHand.Item", event.getInventory().getItem(5));
		monsterListYaml.saveConfig();
		event.getPlayer().sendMessage("��a[SYSTEM] : ������ ������ ����Ǿ����ϴ�.");
		return;
	}

	public void MonsterTypeGUIClick(InventoryClickEvent event)
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
			String MonsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				MonsterOptionSettingGUI(player, MonsterName);
			else if(slot == 48)//���� ������
				MonsterTypeGUI(player, MonsterName, page-2);
			else if(slot == 50)//���� ������
				MonsterTypeGUI(player, MonsterName, page);
			else
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				String type = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				type = type.substring(1, type.length()-1);
				monsterListYaml.set(MonsterName+".Type", type);
				monsterListYaml.saveConfig();
				MonsterOptionSettingGUI(player, MonsterName);
			}
		}
	}
}
