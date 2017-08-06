package area;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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



public class Area_GUI extends Util_GUI
{
	public void AreaListGUI(Player player, short page)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String UniqueCode = "��0��0��2��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��ü ���� ��� : " + (page+1));

		String[] AreaList= areaYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		String AreaName = null;
		String world = null;
		for(int count = page*45; count < AreaList.length;count++)
		{
			AreaName = AreaList[count];
			
			if(count > AreaList.length || loc >= 45) break;
			world = areaYaml.getString(AreaName+".World");
			int MinXLoc = areaYaml.getInt(AreaName+".X.Min");
			byte MinYLoc = (byte) areaYaml.getInt(AreaName+".Y.Min");
			int MinZLoc = areaYaml.getInt(AreaName+".Z.Min");
			int MaxXLoc = areaYaml.getInt(AreaName+".X.Max");
			byte MaxYLoc = (byte) areaYaml.getInt(AreaName+".Y.Max");
			int MaxZLoc = areaYaml.getInt(AreaName+".Z.Max");
			
			byte Priority = (byte) areaYaml.getInt(AreaName+".Priority");
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + AreaName, 395,0,1,Arrays.asList(
					ChatColor.DARK_AQUA+"���� : "+world,ChatColor.DARK_AQUA+"X ���� : "+MinXLoc+" ~ " + MaxXLoc
					,ChatColor.DARK_AQUA+"Y ���� : "+MinYLoc+" ~ " + MaxYLoc
					,ChatColor.DARK_AQUA+"Z ���� : "+MinZLoc+" ~ " + MaxZLoc
					,ChatColor.DARK_AQUA+"�켱 ���� : "+ Priority
					,ChatColor.DARK_GRAY+"�������� ���� ��ĥ ���",
					ChatColor.DARK_GRAY+"�켱 ������ �� ���� ������",
					ChatColor.DARK_GRAY+"����˴ϴ�."
					,"",ChatColor.YELLOW+"[�� Ŭ���� ���� ����]",ChatColor.RED+"[Shift + ��Ŭ���� ���� ����]"), loc, inv);
			
			loc++;
		}
		
		if(AreaList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ������ �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AreaSettingGUI (Player player, String AreaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String UniqueCode = "��0��0��2��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0���� ����");

		if(areaYaml.getBoolean(AreaName+".BlockUse") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� ���]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   �ź�   ]",""), 9, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� ���]", 116,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ���   ]",""), 9, inv);

		if(areaYaml.getBoolean(AreaName+".BlockPlace") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� ��ġ]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   �ź�   ]",""), 10, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� ��ġ]", 2,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ���   ]",""), 10, inv);

		if(areaYaml.getBoolean(AreaName+".BlockBreak") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� �ı�]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   �ź�   ]",""), 11, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� �ı�]", 278,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ���   ]",""), 11, inv);

		if(areaYaml.getBoolean(AreaName+".PVP") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[   PVP   ]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   �ź�   ]",""), 12, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[   PVP   ]", 267,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ���   ]",""), 12, inv);

		if(areaYaml.getBoolean(AreaName+".MobSpawn") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� ����]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   �ź�   ]",""), 13, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� ����]", 52,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ���   ]",""), 13, inv);

		if(areaYaml.getBoolean(AreaName+".Alert") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� �޽���]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   ����   ]",""), 14, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� �޽���]", 340,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ����   ]",""), 14, inv);

		if(areaYaml.getBoolean(AreaName+".SpawnPoint") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[������ ���]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   �Ұ�   ]",""), 15, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[������ ���]", 397,3,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ����   ]",""), 15, inv);

		if(areaYaml.getBoolean(AreaName+".Music") == false)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[����� ���]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   ����   ]",""), 16, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[����� ���]", 84,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   ���   ]",""), 16, inv);

		if(areaYaml.getInt(AreaName+".RegenBlock") == 0)
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� ����]", 166,0,1,Arrays.asList("",ChatColor.RED + ""+ChatColor.BOLD+"[   ����   ]",""), 28, inv);
		else
			Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[��� ����]", 103,0,1,Arrays.asList("",ChatColor.GREEN + ""+ChatColor.BOLD+ "[   Ȱ��   ]","",ChatColor.DARK_AQUA+""+areaYaml.getInt(AreaName+".RegenBlock")+" �� ���� ����","",ChatColor.RED+"[�÷��̾ ���� ĵ ��ϸ� ���� �˴ϴ�]",""), 28, inv);

		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[Ư��ǰ ����]", 15,0,1,Arrays.asList("",ChatColor.GRAY + "���� �������� ����� ĳ��",ChatColor.GRAY+"������ �������� ������",ChatColor.GRAY+"���� �մϴ�.","",ChatColor.YELLOW + "[Ŭ���� Ư��ǰ ����]"), 19, inv);
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� ������]", 346,0,1,Arrays.asList("",ChatColor.GRAY + "���� �������� ���ø� �Ͽ�",ChatColor.GRAY+"���� �� �ִ� ������ Ȯ������",ChatColor.GRAY+"�����մϴ�.",ChatColor.YELLOW + "[Ŭ���� ���� ������ ����]"), 20, inv);
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[�켱���� ����]", 384,0,1,Arrays.asList("",ChatColor.GRAY+"�������� ���� ��ĥ ���",ChatColor.GRAY+"�켱 ������ �� ���� ������",ChatColor.GRAY+"����˴ϴ�.",ChatColor.GRAY+"�� �Ӽ��� �̿��Ͽ� ������ �����,",ChatColor.GRAY+"���� ������ ���� ���� ��",ChatColor.GRAY+"������ ���� �� �ֽ��ϴ�.",ChatColor.BLUE+"[   ���� �켱 ����   ]",ChatColor.WHITE +" "+areaYaml.getInt(AreaName+".Priority"),"",ChatColor.YELLOW + "[Ŭ���� �켱 ���� ����]"), 21, inv);
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� ����]", 383,0,1,Arrays.asList("",ChatColor.GRAY + "���� �������� �ڿ�������",ChatColor.GRAY+"�����Ǵ� ���� ��ſ�",ChatColor.GRAY+"Ŀ���� ���ͷ� �����մϴ�.","",ChatColor.YELLOW + "[Ŭ���� Ŀ���� ���� ����]",ChatColor.RED+"[���� ���� ������ ��Ȱ��]"), 22, inv);
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� ���� ����]", 52,0,1,Arrays.asList("",ChatColor.GRAY + "���� ������ Ư�� ������",ChatColor.GRAY+"Ư�� �ð����� ���͸�",ChatColor.GRAY+"���� �մϴ�.","",ChatColor.YELLOW + "[Ŭ���� ���� ���� ����]"), 31, inv);
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[�޽��� ����]", 386,0,1,Arrays.asList("",ChatColor.GRAY + "���� ���� �޽����� �����մϴ�.","",ChatColor.YELLOW + "[Ŭ���� ���� �޽��� ����]"), 23, inv);
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[�߽��� ����]", 138,0,1,Arrays.asList("",ChatColor.GRAY + "���� ��ȯ, �ֱ� �湮 ��ġ����",ChatColor.GRAY+"������ ���� ���� ��������",ChatColor.GRAY+"�ڷ���Ʈ �Ǵ� �̺�Ʈ�� �߻��� ���",ChatColor.GRAY+"���� ��ġ�� �߽����� �˴ϴ�.","",ChatColor.DARK_AQUA+"[  ���� �߽���  ]",ChatColor.DARK_AQUA+""+areaYaml.getString(AreaName+".World")+" : "+areaYaml.getInt(AreaName+".SpawnLocation.X")+","+areaYaml.getInt(AreaName+".SpawnLocation.Y")+","+areaYaml.getInt(AreaName+".SpawnLocation.Z"),"",ChatColor.YELLOW+ "[Ŭ���� ���� ��ġ�� ����]"), 24, inv);
		
		if(areaYaml.getInt(AreaName+".Restrict.MinNowLevel")+areaYaml.getInt(AreaName+".Restrict.MinNowLevel")+
			areaYaml.getInt(AreaName+".Restrict.MinRealLevel")+areaYaml.getInt(AreaName+".Restrict.MaxRealLevel")==0)
			Stack2(ChatColor.GREEN + ""+ChatColor.BOLD+"[���� ���� ���� ����]", 166,0,1,Arrays.asList("",ChatColor.GRAY + "������ ���� ���� ������ �����ϴ�.",""), 34, inv);
		else
			Stack2(ChatColor.RED + ""+ChatColor.BOLD+"[���� ���� ����]", 399,0,1,Arrays.asList("",ChatColor.GRAY + "������ ���� ���� ������ �ֽ��ϴ�.",""
			,ChatColor.DARK_AQUA+"[  �ּ� ���� ����  ]", "  "+ChatColor.DARK_AQUA+""+areaYaml.getInt(AreaName+".Restrict.MinNowLevel")
			,ChatColor.DARK_AQUA+"[  �ִ� ���� ����  ]", "  "+ChatColor.DARK_AQUA+""+areaYaml.getInt(AreaName+".Restrict.MaxNowLevel")
			,ChatColor.GRAY+" �� ������ �ý����� ��� �߰� ���� ��"
			,ChatColor.DARK_AQUA+"[  �ּ� ���� ����  ]", "  "+ChatColor.DARK_AQUA+""+areaYaml.getInt(AreaName+".Restrict.MinRealLevel")
			,ChatColor.DARK_AQUA+"[  �ִ� ���� ����  ]", "  "+ChatColor.DARK_AQUA+""+areaYaml.getInt(AreaName+".Restrict.MaxRealLevel"),""), 34, inv);
		String lore = "";
		short tracknumber = (short) areaYaml.getInt(AreaName+".BGM");
		lore = " %enter%"+ChatColor.GRAY + "���� ����� �׸� ����%enter%"+ChatColor.GRAY+"��� ��ų �� �ֽ��ϴ�.%enter% %enter%"+ChatColor.BLUE + "[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ tracknumber+"%enter%"
		+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ new otherplugins.NoteBlockAPIMain().getTitle(tracknumber)+"%enter%"
		+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+new otherplugins.NoteBlockAPIMain().getAuthor(tracknumber)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
		
		String Description = new otherplugins.NoteBlockAPIMain().getDescription(areaYaml.getInt(AreaName+".BGM"));
		String lore2="";
		short a = 0;
		for(int count = 0; count <Description.toCharArray().length; count++)
		{
			lore2 = lore2+ChatColor.BLUE+Description.toCharArray()[count];
			a++;
			if(a >= 15)
			{
				a = 0;
				lore2 = lore2+"%enter%      ";
			}
		}
		lore = lore + lore2;
		
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� �����]", 2263,0,1,Arrays.asList(lore.split("%enter%")), 25, inv);
		
		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� �̵�", 368,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� ������ �̵��մϴ�."), 40, inv);
		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ������� ���ư��ϴ�."), 36, inv);
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "���� â�� �ݽ��ϴ�.",ChatColor.BLACK+AreaName), 44, inv);
		
		player.openInventory(inv);
		return;
	}
	
	public void AreaMonsterSpawnSettingGUI(Player player, short page, String AreaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		String UniqueCode = "��0��0��2��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� ���� �� : " + (page+1));
		if(areaYaml.contains(AreaName+".MonsterSpawnRule")==false)
		{
			areaYaml.createSection(AreaName+".MonsterSpawnRule");
			areaYaml.saveConfig();
		}
		String[] RuleList= areaYaml.getConfigurationSection(AreaName+".MonsterSpawnRule").getKeys(false).toArray(new String[0]);
		byte loc=0;
		for(int count = page*45; count <RuleList.length ;count++)
		{
			if(count > RuleList.length || loc >= 45) break;
			String RuleNumber = RuleList[count];
			if(areaYaml.contains(AreaName+".MonsterSpawnRule."+RuleNumber+".Monster"))
				Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + (RuleNumber), 383,0,1,Arrays.asList(
						ChatColor.GOLD+"[     ���� �ɼ�     ]",ChatColor.RED+"-������ ������ ���� ���� �۵� -",ChatColor.GOLD+"���� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.world"),
						ChatColor.GOLD+"��ǥ : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.x")+","+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.y")+","+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.z"),
						ChatColor.GOLD+"�ν� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".range")+"���",
						ChatColor.GOLD+"�ð� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".timer")+"�ʸ��� "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".count")+"���� ����",
						ChatColor.GOLD+"�ִ� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".max")+"����",
						ChatColor.GOLD+"���� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".Monster")
						,"",ChatColor.RED+"[Shift + ��Ŭ���� �� ����]"), loc, inv);
			else
				Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + (RuleNumber), 52,0,1,Arrays.asList(
					ChatColor.GOLD+"[     ���� �ɼ�     ]",ChatColor.RED+"-������ ������ ���� ���� �۵� -",ChatColor.GOLD+"���� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.world"),
					ChatColor.GOLD+"��ǥ : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.x")+","+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.y")+","+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".loc.z"),
					ChatColor.GOLD+"�ν� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".range")+"���",
					ChatColor.GOLD+"�ð� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".timer")+"�ʸ��� "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".count")+"���� ����",
					ChatColor.GOLD+"�ִ� : "+areaYaml.getString(AreaName+".MonsterSpawnRule."+RuleNumber+".max")+"����"
					,"",ChatColor.RED+"[Shift + ��Ŭ���� �� ����]"), loc, inv);
			loc++;
		}

		if(RuleList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ��Ģ �߰�", 52,0,1,Arrays.asList(ChatColor.GRAY + "�� ���� ��Ģ�� �߰��մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK + AreaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AreaMonsterSettingGUI(Player player, short page, String AreaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");

		String UniqueCode = "��0��0��2��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��ü ���� : " + (page+1));

		String[] MonsterNameList= areaYaml.getConfigurationSection(AreaName+".Monster").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		short MobNameListLength = (short) MonsterNameList.length;
		String MonsterName = null;
		String Name = null;
		for(int count = page*45; count <MobNameListLength ;count++)
		{
			MonsterName = MonsterNameList[count];
			if(monsterYaml.contains(MonsterName) == true)
			{
				Name = monsterYaml.getString(MonsterName+".Name");
				if(count > MobNameListLength || loc >= 45) break;
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + MonsterName, 383,0,1,Arrays.asList(
						ChatColor.WHITE+"�̸� : " + Name,ChatColor.WHITE+"Ÿ�� : " + monsterYaml.getString(MonsterName+".Type"),
						ChatColor.WHITE+"����� : " + monsterYaml.getInt(MonsterName+".HP"),ChatColor.WHITE+"����ġ : " + monsterYaml.getInt(MonsterName+".EXP"),
						ChatColor.WHITE+"��� : " + monsterYaml.getInt(MonsterName+".MIN_Money")+" ~ " +monsterYaml.getInt(MonsterName+".MAX_Money"),
						"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
				loc++;
			}
			else
			{
				areaYaml.removeKey(AreaName+".Monster."+MonsterName);
				areaYaml.saveConfig();
			}
		}
		
		if(MobNameListLength-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� �߰�", 52,0,1,Arrays.asList(ChatColor.GRAY + "�� Ŀ���� ���͸� �߰��մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK + AreaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AreaFishSettingGUI(Player player, String AreaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		String UniqueCode = "��1��0��2��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� �߰� ���");
		
		Stack2("��a��l[     54%     ]", 160,5,1,Arrays.asList(ChatColor.GRAY + "�� �ٿ��� 54% Ȯ���� ���� �������� �ø�����."), 0, inv);
		Stack2("��e��l[     30%     ]", 160,4,1,Arrays.asList(ChatColor.GRAY + "�� �ٿ��� 30% Ȯ���� ���� �������� �ø�����."), 9, inv);
		Stack2("��6��l[     10%     ]", 160,1,1,Arrays.asList(ChatColor.GRAY + "�� �ٿ��� 10% Ȯ���� ���� �������� �ø�����."), 18, inv);
		Stack2("��c��l[      5%      ]", 160,14,1,Arrays.asList(ChatColor.GRAY + "�� �ٿ��� 5% Ȯ���� ���� �������� �ø�����."), 27, inv);
		Stack2("��8��l[      1%      ]", 160,10,1,Arrays.asList(ChatColor.GRAY + "�� �ٿ��� 1% Ȯ���� ���� �������� �ø�����."), 36, inv);

		String[] FishingItemList = areaYaml.getConfigurationSection(AreaName+".Fishing.54").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < FishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(AreaName+".Fishing.54."+FishingItemList[count]), count+1, inv);
		FishingItemList = areaYaml.getConfigurationSection(AreaName+".Fishing.30").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < FishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(AreaName+".Fishing.30."+FishingItemList[count]), count+10, inv);
		FishingItemList = areaYaml.getConfigurationSection(AreaName+".Fishing.10").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < FishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(AreaName+".Fishing.10."+FishingItemList[count]), count+19, inv);
		FishingItemList = areaYaml.getConfigurationSection(AreaName+".Fishing.5").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < FishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(AreaName+".Fishing.5."+FishingItemList[count]), count+28, inv);
		FishingItemList = areaYaml.getConfigurationSection(AreaName+".Fishing.1").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < FishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(AreaName+".Fishing.1."+FishingItemList[count]), count+37, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK + AreaName), 53, inv);
		player.openInventory(inv);
	}
	
	public void AreaBlockSettingGUI(Player player, short page, String AreaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		event.Main_Interact I = new event.Main_Interact();

		String UniqueCode = "��0��0��2��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� Ư��ǰ : " + (page+1));

		String[] BlockIdDataList= areaYaml.getConfigurationSection(AreaName+".Mining").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count <BlockIdDataList.length ;count++)
		{
			short ID = Short.parseShort(BlockIdDataList[count].split(":")[0]);
			byte Data = Byte.parseByte(BlockIdDataList[count].split(":")[1]);

			Stack2(I.SetItemDefaultName(ID, (byte) Data), ID,Data,1,Arrays.asList(
					"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
				loc++;
		}
		
		if(BlockIdDataList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��lƯ�깰 �߰�", 52,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ����� �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK + AreaName), 53, inv);
		player.openInventory(inv);
	}
	
	public void AreaBlockItemSettingGUI(Player player,String AreaName,String ItemData)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		String UniqueCode = "��1��0��2��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�ش� ����� ĳ�� ���� ������");

		ItemStack item = areaYaml.getItemStack(AreaName+".Mining."+ItemData+".100");
		
		ItemStackStack(item, 4, inv);

		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 0, inv);
		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 1, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 2, inv);
		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 3, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 5, inv);
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 6, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 7, inv);
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList(ChatColor.GRAY + "[100% Ȯ���� ���� ������]"), 8, inv);

		item = areaYaml.getItemStack(AreaName+".Mining."+ItemData+".90");
		ItemStackStack(item, 13, inv);
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 9, inv);
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 10, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 11, inv);
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 12, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 14, inv);
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 15, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 16, inv);
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList(ChatColor.GRAY + "[90% Ȯ���� ���� ������]"), 17, inv);

		item = areaYaml.getItemStack(AreaName+".Mining."+ItemData+".50");
		ItemStackStack(item, 22, inv);
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 18, inv);
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 19, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 20, inv);
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 21, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 23, inv);
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 24, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 25, inv);
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList(ChatColor.GRAY + "[50% Ȯ���� ���� ������]"), 26, inv);

		item = areaYaml.getItemStack(AreaName+".Mining."+ItemData+".10");
		ItemStackStack(item, 31, inv);
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 27, inv);
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 28, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 29, inv);
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 30, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 32, inv);
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 33, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 34, inv);
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList(ChatColor.GRAY + "[10% Ȯ���� ���� ������]"), 35, inv);

		item = areaYaml.getItemStack(AreaName+".Mining."+ItemData+".1");
		ItemStackStack(item, 40, inv);
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 36, inv);
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 37, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 38, inv);
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 39, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 41, inv);
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 42, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 43, inv);
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList(ChatColor.GRAY + "[1% Ȯ���� ���� ������]"), 44, inv);

		item = areaYaml.getItemStack(AreaName+".Mining."+ItemData+".0");
		ItemStackStack(item, 49, inv);
		Stack2("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList(ChatColor.GRAY + "[0.1% Ȯ���� ���� ������]"), 46, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList(ChatColor.GRAY + "[0.1% Ȯ���� ���� ������]"), 47, inv);
		Stack2("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList(ChatColor.GRAY + "[0.1% Ȯ���� ���� ������]"), 48, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList(ChatColor.GRAY + "[0.1% Ȯ���� ���� ������]"), 50, inv);
		Stack2("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList(ChatColor.GRAY + "[0.1% Ȯ���� ���� ������]"), 51, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList(ChatColor.GRAY + "[0.1% Ȯ���� ���� ������]"), 52, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+ItemData), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK + AreaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AreaAddMonsterListGUI(Player player, short page,String AreaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");

		String UniqueCode = "��0��0��2��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� ���� : " + (page+1));

		String[] monsterList= monsterYaml.getKeys().toArray(new String[0]);
		String[] monsterNameList= areaYaml.getConfigurationSection(AreaName+".Monster").getKeys(false).toArray(new String[0]);

		String lore = null;
		byte loc=0;
		String monsterName = null;
		for(int count = page*45; count < monsterList.length;count++)
		{
			if(count > monsterList.length || loc >= 45) break;
			monsterName = monsterList[count];
			boolean isExit = false;
			for(int count2 = 0; count2 < monsterNameList.length; count2++)
			{
				if(monsterNameList[count2].compareTo(monsterName)==0)
				{
					isExit=true;
					break;
				}
			}
			
			if(isExit == false)
			{
				lore = null;
				lore = "%enter%"+"��f��l �̸� : "+ChatColor.WHITE+monsterYaml.getString(monsterName+".Name")+"%enter%";
				lore = lore+"��f��l Ÿ�� : "+ChatColor.WHITE+monsterYaml.getString(monsterName+".Type")+"%enter%";
				lore = lore+"��f��l ���� ���̿� : "+ChatColor.WHITE+monsterYaml.getString(monsterName+".Biome")+"%enter%";
				lore = lore+"��c��l ����� : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".HP")+"%enter%";
				lore = lore+"��b��l ����ġ : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".EXP")+"%enter%";
				lore = lore+"��e��l ��� �ݾ� : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".MIN_Money")+" ~ "+monsterYaml.getInt(monsterName+".MAX_Money")+"%enter%";
				lore = lore+"��c��l "+Main_ServerOption.STR+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".STR")
				+ChatColor.GRAY+ " [���� : " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(monsterName+".STR"), true) + " ~ " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(monsterName+".STR"), false) + "]%enter%";
				lore = lore+"��a��l "+Main_ServerOption.DEX+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".DEX")
				+ChatColor.GRAY+ " [Ȱ�� : " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, false) + "]%enter%";
				lore = lore+"��9��l "+Main_ServerOption.INT+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".INT")
				+ChatColor.GRAY+ " [���� : " + (monsterYaml.getInt(monsterName+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(monsterName+".INT")/2.5)+"]%enter%";
				lore = lore+"��7��l "+Main_ServerOption.WILL+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".WILL")
				+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
				lore = lore+"��e��l "+Main_ServerOption.LUK+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".LUK")
				+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
				lore = lore+"��7��l ��� : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".DEF")+"%enter%";
				lore = lore+"��b��l ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".Protect")+"%enter%";
				lore = lore+"��9��l ���� ��� : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".Magic_DEF")+"%enter%";
				lore = lore+"��1��l ���� ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(monsterName+".Magic_Protect")+"%enter%";
				lore = lore+"%enter%"+"��e��l[�� Ŭ���� ���� ���]";

				String[] scriptA = lore.split("%enter%");
				for(int counter = 0; counter < scriptA.length; counter++)
					scriptA[counter] =  " "+scriptA[counter];
				short id = 383;
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
				}
				
				Stack(ChatColor.WHITE+monsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
				loc++;
			}
		}
		
		if(monsterList.length-(page*44)>45)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+AreaName), 53, inv);
		player.openInventory(inv);
	}
	
	public void AreaSpawnSpecialMonsterListGUI(Player player, short page,String AreaName,String RuleCount)
	{
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		String UniqueCode = "��0��0��2��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� Ư�� ����  : " + (page+1));

		String[] monsterList= monsterYaml.getKeys().toArray(new String[0]);

		byte loc=0;
		String MonsterName = null;
		String lore = null;
		for(int count = page*45; count < monsterList.length;count++)
		{
			if(count > monsterList.length || loc >= 45) break;
			MonsterName = monsterList[count];
			lore = null;
			lore = "%enter%"+"��f��l �̸� : "+ChatColor.WHITE+monsterYaml.getString(MonsterName+".Name")+"%enter%";
			lore = lore+"��f��l Ÿ�� : "+ChatColor.WHITE+monsterYaml.getString(MonsterName+".Type")+"%enter%";
			lore = lore+"��f��l ���� ���̿� : "+ChatColor.WHITE+monsterYaml.getString(MonsterName+".Biome")+"%enter%";
			lore = lore+"��c��l ����� : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".HP")+"%enter%";
			lore = lore+"��b��l ����ġ : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".EXP")+"%enter%";
			lore = lore+"��e��l ��� �ݾ� : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".MIN_Money")+" ~ "+monsterYaml.getInt(MonsterName+".MAX_Money")+"%enter%";
			lore = lore+"��c��l "+Main_ServerOption.STR+" : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".STR")
			+ChatColor.GRAY+ " [���� : " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(MonsterName+".STR"), true) + " ~ " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(MonsterName+".STR"), false) + "]%enter%";
			lore = lore+"��a��l "+Main_ServerOption.DEX+" : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".DEX")
			+ChatColor.GRAY+ " [Ȱ�� : " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(MonsterName+".DEX"), 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(MonsterName+".DEX"), 0, false) + "]%enter%";
			lore = lore+"��9��l "+Main_ServerOption.INT+" : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".INT")
			+ChatColor.GRAY+ " [���� : " + (monsterYaml.getInt(MonsterName+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(MonsterName+".INT")/2.5)+"]%enter%";
			lore = lore+"��7��l "+Main_ServerOption.WILL+" : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".WILL")
			+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(MonsterName+".LUK"), (int)monsterYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
			lore = lore+"��e��l "+Main_ServerOption.LUK+" : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".LUK")
			+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(MonsterName+".LUK"), (int)monsterYaml.getInt(MonsterName+".WILL"),0) + " %]%enter%";
			lore = lore+"��7��l ��� : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".DEF")+"%enter%";
			lore = lore+"��b��l ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".Protect")+"%enter%";
			lore = lore+"��9��l ���� ��� : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".Magic_DEF")+"%enter%";
			lore = lore+"��1��l ���� ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(MonsterName+".Magic_Protect")+"%enter%";
			lore = lore+"%enter%"+"��e��l[�� Ŭ���� ���� ���]";

			String[] scriptA = lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			short id = 383;
			byte data = 0;
			switch(monsterYaml.getString(MonsterName+".Type"))
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
			}
			
			Stack(ChatColor.WHITE+MonsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(monsterList.length-(page*44)>45)
		Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l���", 166,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� �������",ChatColor.GRAY+"������ ��� �� ���͸�",ChatColor.GRAY+"�����ϰ� ���� �մϴ�.",ChatColor.BLACK+AreaName,ChatColor.BLACK+""+RuleCount), 49, inv);
		player.openInventory(inv);
	}

	public void AreaMusicSettingGUI(Player player, int page,String AreaName)
	{
		String UniqueCode = "��0��0��2��0��9��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����� : " + (page+1));
		byte loc=0;
		byte model = (byte) new util.Util_Number().RandomNum(0, 11);
		for(int count = page*45; count < new otherplugins.NoteBlockAPIMain().Musics.size();count++)
		{
			if(model<11)
				model++;
			else
				model=0;
			String lore = " %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ count+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ new otherplugins.NoteBlockAPIMain().getTitle(count)+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+new otherplugins.NoteBlockAPIMain().getAuthor(count)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
			String Description = new otherplugins.NoteBlockAPIMain().getDescription(count);
			String lore2="";
			short a = 0;
			for(int counter = 0; counter <Description.toCharArray().length; counter++)
			{
				lore2 = lore2+ChatColor.BLUE+Description.toCharArray()[counter];
				a++;
				if(a >= 15)
				{
					a = 0;
					lore2 = lore2+"%enter%      ";
				}
			}
			lore = lore + lore2+"%enter% %enter%"+ChatColor.YELLOW+"[�� Ŭ���� ����� ����]";
			if(count > new otherplugins.NoteBlockAPIMain().Musics.size() || loc >= 45) break;
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + count, 2256+model,0,1,Arrays.asList(lore.split("%enter%")), loc, inv);
			
			loc++;
		}
		
		if(new otherplugins.NoteBlockAPIMain().Musics.size()-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+AreaName), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	public void AreaListGUIClick(InventoryClickEvent event)
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
			String AreaName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			
			if(slot == 45)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player, (byte) 2);
			else if(slot == 48)//���� ������
				AreaListGUI(player, (short) (page-1));
			else if(slot == 49)//���� �߰�
			{
			  	YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				player.closeInventory();
				event.Main_Interact IT = new event.Main_Interact();
				player.sendMessage(ChatColor.DARK_AQUA + "[����] : " + IT.SetItemDefaultName((short) configYaml.getInt("Server.AreaSettingWand"),(byte)0) +ChatColor.DARK_AQUA+" ���������� ������ ������ �� ��,");
				player.sendMessage(ChatColor.GOLD +""+ChatColor.BOLD+ " /���� <�����̸�> ���� "+ChatColor.DARK_AQUA+"��ɾ �Է��� �ּ���!");
				SoundEffect.SP((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			else if(slot == 50)//���� ������
				AreaListGUI(player, (short) (page+1));
			else
			{
				if(event.isLeftClick() == true)
					AreaSettingGUI(player, AreaName);
				else if(event.isShiftClick() == true && event.isRightClick() == true)
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					for(int count = 0; count < Main_ServerOption.AreaList.get(areaYaml.getString(AreaName+".World")).size(); count++)
						if(Main_ServerOption.AreaList.get(areaYaml.getString(AreaName+".World")).get(count).AreaName.compareTo(AreaName)==0)
							Main_ServerOption.AreaList.get(areaYaml.getString(AreaName+".World")).remove(count);
					areaYaml.removeKey(AreaName);
					areaYaml.saveConfig();
					AreaListGUI(player, page);
				}
			}
		}
	}
	
	public void AreaSettingGUIInventoryclick(InventoryClickEvent event)
	{
		
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		if(slot == 44)//â�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
		  	YamlLoader areaYaml = new YamlLoader();
			areaYaml.getConfig("Area/AreaList.yml");
			String AreaName = ChatColor.stripColor(event.getInventory().getItem(44).getItemMeta().getLore().get(1));
			if(slot == 36)//���� ȭ��
				AreaListGUI(player,(short) 0);
			else if(slot >= 9 && slot <= 16)
			{
				if(slot == 9)//��� ���
				{
					if(areaYaml.getBoolean(AreaName+".BlockUse") == false)
						areaYaml.set(AreaName+".BlockUse", true);
					else
						areaYaml.set(AreaName+".BlockUse", false);
				}
				else if(slot == 10)//��� ��ġ
				{
					if(areaYaml.getBoolean(AreaName+".BlockPlace") == false)
						areaYaml.set(AreaName+".BlockPlace", true);
					else
						areaYaml.set(AreaName+".BlockPlace", false);
				}
				else if(slot == 11)//��� �ı�
				{
					if(areaYaml.getBoolean(AreaName+".BlockBreak") == false)
						areaYaml.set(AreaName+".BlockBreak", true);
					else
						areaYaml.set(AreaName+".BlockBreak", false);
				}
				else if(slot == 12)//PVP
				{
					if(areaYaml.getBoolean(AreaName+".PVP") == false)
						areaYaml.set(AreaName+".PVP", true);
					else
						areaYaml.set(AreaName+".PVP", false);
				}
				else if(slot == 13)//���� ����
				{
					if(areaYaml.getBoolean(AreaName+".MobSpawn") == false)
						areaYaml.set(AreaName+".MobSpawn", true);
					else
						areaYaml.set(AreaName+".MobSpawn", false);
				}
				else if(slot == 14)//���� �޽���
				{
					if(areaYaml.getBoolean(AreaName+".Alert") == false)
						areaYaml.set(AreaName+".Alert", true);
					else
						areaYaml.set(AreaName+".Alert", false);
				}
				else if(slot == 15)//������ ���
				{
					if(areaYaml.getBoolean(AreaName+".SpawnPoint") == false)
						areaYaml.set(AreaName+".SpawnPoint", true);
					else
						areaYaml.set(AreaName+".SpawnPoint", false);
				}
				else if(slot == 16)//����� ���
				{
					if(areaYaml.getBoolean(AreaName+".Music") == false)
						areaYaml.set(AreaName+".Music", true);
					else
						areaYaml.set(AreaName+".Music", false);
				}
				areaYaml.saveConfig();
				AreaSettingGUI(player, AreaName);
			}
			else if(slot == 21)//�켱 ���� ����
			{
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Area");
				u.setString(player, (byte)2, "Priority");
				u.setString(player, (byte)3, AreaName);
				player.sendMessage(ChatColor.GREEN + "[����] : "+ChatColor.YELLOW+AreaName+ChatColor.GREEN+" ������ �켱 ������ �Է��ϼ���!");
				player.sendMessage(ChatColor.GRAY + "(�ּ� 0 ~ �ִ� 100)");
			}
			else if(slot == 23)//�޽��� ����
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.sendMessage(ChatColor.GOLD + "/���� "+AreaName+" �̸� <���ڿ�>" + ChatColor.YELLOW + "\n - "+AreaName+" ������ �˸� �޽����� ���� �̸��� ���մϴ�.");
				player.sendMessage(ChatColor.GOLD + "/���� "+AreaName+" ���� <���ڿ�>" + ChatColor.YELLOW + "\n - "+AreaName+" ������ �˸� �޽����� ���� �ΰ� ������ ���մϴ�.");
				player.sendMessage(ChatColor.GOLD + "%player%"+ChatColor.WHITE + " - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage(ChatColor.WHITE + ""+ChatColor.BOLD + "&l " + ChatColor.BLACK + "&0 "+ChatColor.DARK_BLUE+"&1 "+ChatColor.DARK_GREEN+"&2 "+
				ChatColor.DARK_AQUA + "&3 " +ChatColor.DARK_RED + "&4 " + ChatColor.DARK_PURPLE + "&5 " +
						ChatColor.GOLD + "&6 " + ChatColor.GRAY + "&7 " + ChatColor.DARK_GRAY + "&8 " +
				ChatColor.BLUE + "&9 " + ChatColor.GREEN + "&a " + ChatColor.AQUA + "&b " + ChatColor.RED + "&c " +
						ChatColor.LIGHT_PURPLE + "&d " + ChatColor.YELLOW + "&e "+ChatColor.WHITE + "&f");
				player.closeInventory();
			}
			else if(slot == 24)//�߽��� ����
			{
				areaYaml.set(AreaName+".World", player.getLocation().getWorld().getName());
				areaYaml.set(AreaName+".SpawnLocation.X", player.getLocation().getX());
				areaYaml.set(AreaName+".SpawnLocation.Y", player.getLocation().getY());
				areaYaml.set(AreaName+".SpawnLocation.Z", player.getLocation().getZ());
				areaYaml.set(AreaName+".SpawnLocation.Pitch", player.getLocation().getPitch());
				areaYaml.set(AreaName+".SpawnLocation.Yaw", player.getLocation().getYaw());
				areaYaml.saveConfig();
				AreaSettingGUI(player, AreaName);
			}
			else if(slot == 25)//���� ����� ����
			{
				if(new otherplugins.NoteBlockAPIMain().SoundList(player,true))
					AreaMusicSettingGUI(player, 0, AreaName);
				else
					SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.9F);
			}
			else if(slot == 28)//��� ����
			{
				if(areaYaml.getInt(AreaName+".RegenBlock") == 0)
				{
					player.closeInventory();
					UserData_Object u = new UserData_Object();
					areaYaml.set(AreaName+".RegenBlock", 1);
					areaYaml.saveConfig();
					u.setType(player, "Area");
					u.setString(player, (byte)2, "ARR");
					u.setString(player, (byte)3, AreaName);
					player.sendMessage(ChatColor.GREEN + "[����] : "+ChatColor.YELLOW+AreaName+ChatColor.GREEN+" ������ ��� ���� �ӵ��� �����ϼ���!");
					player.sendMessage(ChatColor.GRAY + "(�ּ� 1�� ~ �ִ� 3600��(1�ð�))");
				}
				else
				{
					areaYaml.set(AreaName+".RegenBlock", 0);
					areaYaml.saveConfig();
					AreaSettingGUI(player, AreaName);
				}
			}
			else if(slot == 19)//Ư��ǰ ����
				AreaBlockSettingGUI(player, (short) 0, AreaName);
			else if(slot == 20)//���� ������
				AreaFishSettingGUI(player, AreaName);
			else if(slot == 22)//���� ����
				AreaMonsterSettingGUI(player,(short) 0, AreaName);
			else if(slot == 31)//���� ���� ��
				AreaMonsterSpawnSettingGUI(player, (short) 0, AreaName);
			else if(slot == 34)//���� ����
			{
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Area");
				u.setString(player, (byte)2, "MinNLR");
				u.setString(player, (byte)3, AreaName);
				player.sendMessage(ChatColor.GREEN + "[����] : "+ChatColor.YELLOW+AreaName+ChatColor.GREEN+" ������ ���忡 �ʿ��� �ּ� ������ �Է� �ϼ���!"+ChatColor.GRAY + " (0 �Է½� ���� ����)");
			}
			else if(slot == 40)//���� �̵�
			{
				player.closeInventory();
				player.teleport(new Location(Bukkit.getWorld(areaYaml.getString(AreaName+".World")),areaYaml.getInt(AreaName+".SpawnLocation.X"), areaYaml.getInt(AreaName+".SpawnLocation.Y"),areaYaml.getInt(AreaName+".SpawnLocation.Z"),areaYaml.getInt(AreaName+".SpawnLocation.Yaw"),areaYaml.getInt(AreaName+".SpawnLocation.Pitch")));
			}
		}
		return;
	}

	public void AreaMonsterSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		if(slot == 53)//â�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ȭ��
				AreaSettingGUI(player, AreaName);
			else if(slot == 48)//���� ������
				AreaMonsterSettingGUI(player, (short) (page-1), AreaName);
			else if(slot == 49)//���� �߰�
			{
			  	YamlLoader monsterYaml = new YamlLoader();
				monsterYaml.getConfig("Monster/MonsterList.yml");
				if(monsterYaml.getKeys().size() == 0)
				{
					SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0F, 1.8F);
					player.sendMessage(ChatColor.RED + "[����] : ���� ��ϵ� Ŀ���� ���Ͱ� �������� �ʽ��ϴ�!");
					player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD + "/���� <�̸�> ���� " + ChatColor.YELLOW +"�ش� �̸��� ���� ���͸� �����մϴ�.");
				}
				else
					AreaAddMonsterListGUI(player, page, AreaName);
			}
			else if(slot == 50)//���� ������
				AreaMonsterSettingGUI(player, (short) (page+1),AreaName);
			else
			{
				if(event.isShiftClick() == true && event.isRightClick() == true)
				{
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					String MonsterName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					areaYaml.removeKey(AreaName+".Monster."+MonsterName);
					areaYaml.saveConfig();
					AreaMonsterSettingGUI(player, page,AreaName);
				}
			}
		}
	}

	public void AreaFishSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		if(slot == 0 || slot == 9 || slot == 18 || slot == 27 || slot == 36 || slot >= 45)
			event.setCancelled(true);
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else if(slot == 45)//���� ���
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			AreaSettingGUI(player, AreaName);
		}
	}

	public void AreaBlockSettingGUIClick(InventoryClickEvent event)
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
			String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				AreaSettingGUI(player, AreaName);
			else if(slot == 48)//���� ������
				AreaBlockSettingGUI(player, (short) (page-1), AreaName);
			else if(slot == 49)//Ư�깰 �߰�
			{
				player.closeInventory();
				player.sendMessage(ChatColor.DARK_AQUA + "[����] : ������ ����� ��Ŭ�� �ϼ���!");

				UserData_Object u = new UserData_Object();
				u.setType(player, "Area");
				u.setString(player, (byte)2, AreaName);
				u.setString(player, (byte)3, "ANBI");
			}
			else if(slot == 50)//���� ������
				AreaBlockSettingGUI(player, (short) (page+1), AreaName);
			else
			{
				String BlockName = event.getCurrentItem().getTypeId()+":"+event.getCurrentItem().getData().getData();
				if(event.isShiftClick()==false&&event.isLeftClick()==true)
					AreaBlockItemSettingGUI(player, AreaName, BlockName);
				else if(event.isShiftClick() == true && event.isRightClick() == true)
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					areaYaml.removeKey(AreaName+".Mining."+BlockName);
					areaYaml.saveConfig();
					AreaBlockSettingGUI(player, page, AreaName);
				}
			}
		}
	}

	public void AreaBlockItemSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		if(event.getClickedInventory().getTitle().compareTo("container.inventory") != 0)
		{
			if(slot==4||slot==13||slot==22||slot==31||slot==40||slot==49)
				event.setCancelled(false);
			else if(slot == 53)//������
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
			else if(slot == 45)//���� ���
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				AreaBlockSettingGUI(player, (short) 0, AreaName);
			}
		}
	}

	public void AreaAddMonsterSpawnRuleGUIClick(InventoryClickEvent event)
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
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			if(slot == 45)//���� ���
				AreaSettingGUI(player, AreaName);
			else if(slot == 48)//���� ������
				AreaMonsterSpawnSettingGUI(player, (short) (page-1), AreaName);
			else if(slot == 49)//�� �߰�
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				long count = new util.ETC().getNowUTC();
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".range", 1);
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".count", 4);
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".timer", 10);
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".max", 10);
				UserData_Object u = new UserData_Object();
				u.setType(player, "Area");
				u.setString(player, (byte)1, count+"");
				u.setString(player, (byte)2, AreaName);
				u.setString(player, (byte)3, "MLS");
				areaYaml.saveConfig();
				player.sendMessage(ChatColor.GREEN+"[����] : ���Ͱ� ���� �� ��ġ�� ���콺 �� Ŭ�� �ϼ���!");
				player.closeInventory();
			}
			else if(slot == 50)//���� ������
				AreaMonsterSpawnSettingGUI(player, (short) (page+1), AreaName);
			else if(event.isRightClick()&&event.isShiftClick())
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
				areaYaml.removeKey(AreaName+".MonsterSpawnRule."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				areaYaml.saveConfig();
				AreaMonsterSpawnSettingGUI(player, (short) page, AreaName);
			}
		}
	}
	
	public void AreaAddMonsterListGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				AreaMonsterSettingGUI(player, (short) 0, AreaName);
			else if(slot == 45)//���� ������
				AreaAddMonsterListGUI(player, (short) (page-1), AreaName);
			else if(slot == 50)//���� ������
				AreaAddMonsterListGUI(player, (short) (page+1), AreaName);
			else
			{
				String MobName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				SoundEffect.SP(player, Sound.ENTITY_WOLF_AMBIENT, 0.8F, 1.0F);
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				areaYaml.createSection(AreaName+".Monster."+MobName);
				areaYaml.saveConfig();
				AreaAddMonsterListGUI(player, page, AreaName);
			}
		}
	}

	public void AreaSpawnSpecialMonsterListGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		String AreaName = ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(3));
		String RuleCounter = ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(4));
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		if(slot == 49)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			AreaMonsterSpawnSettingGUI(player, (short) 0, AreaName);
			new area.Area_Main().AreaMonsterSpawnAdd(AreaName, RuleCounter);
		}
		else if(slot == 48)//���� ������
			AreaAddMonsterListGUI(player, (short) (page-1), AreaName);
		else if(slot == 50)//���� ������
			AreaAddMonsterListGUI(player, (short) (page+1), AreaName);
		else
		{
			String MobName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 0.8F, 1.0F);
		  	YamlLoader areaYaml = new YamlLoader();
			areaYaml.getConfig("Area/AreaList.yml");
			areaYaml.set(AreaName+".MonsterSpawnRule."+RuleCounter+".Monster", MobName);
			areaYaml.saveConfig();
			AreaMonsterSpawnSettingGUI(player, (short) 0, AreaName);
			
			new area.Area_Main().AreaMonsterSpawnAdd(AreaName, RuleCounter);
		}
	}

	public void AreaMusicSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		
		String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		int slot = event.getSlot();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);

		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else if(AreaName.compareTo("DeathBGM��")==0)
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)
				new admin.OPbox_GUI().OPBoxGUI_Death(player);
			else if(slot == 48)
				AreaMusicSettingGUI(player, page-1,AreaName);
			else if(slot == 50)
				AreaMusicSettingGUI(player, page+1,AreaName);
			else
			{
			  	YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				configYaml.set("Death.Track", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
				configYaml.saveConfig();
				new admin.OPbox_GUI().OPBoxGUI_Death(player);
			}
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				AreaSettingGUI(player, AreaName);
			else if(slot == 48)//���� ������
				AreaMusicSettingGUI(player, page-1,AreaName);
			else if(slot == 50)//���� ������
				AreaMusicSettingGUI(player, page+1,AreaName);
			else
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				areaYaml.set(AreaName+".BGM", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
				areaYaml.saveConfig();
				AreaSettingGUI(player, AreaName);
			}
		}
	}

	
	
	public void FishingSettingInventoryClose(InventoryCloseEvent event)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		byte loc = 0;
		for(int count = 1; count < 9; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(AreaName + ".Fishing.54."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(AreaName+".Fishing.54."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 10; count < 18; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(AreaName + ".Fishing.30."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(AreaName+".Fishing.30."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 19; count < 27; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(AreaName + ".Fishing.10."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(AreaName+".Fishing.10."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 28; count < 36; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(AreaName + ".Fishing.5."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(AreaName+".Fishing.5."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 37; count < 45; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(AreaName + ".Fishing.1."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(AreaName+".Fishing.1."+loc);
			areaYaml.saveConfig();
		}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(AreaName+".Fishing.54."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(AreaName+".Fishing.54."+(counter), areaYaml.getItemStack(AreaName+".Fishing.54."+(counter+1)));
					areaYaml.removeKey(AreaName+".Fishing.54."+(counter+1));
				}
				areaYaml.saveConfig();
			}
	
		byte line1 = 0;
		byte line2 = 0;
		byte line3 = 0;
		byte line4 = 0;
		byte line5 = 0;
		for(int count = 0; count < 45; count++)
		{
			if(event.getInventory().getItem(count) != null)
			{
				if(count>0&&count<9 )
					line1 = (byte) (line1+1);
				else if(count>9&&count<18 )
					line2 = (byte)(line2 +1);
				else if(count>18&&count<27 )
					line3 = (byte)(line3 +1);
				else if(count>27&&count<36 )
					line4 = (byte)(line4 +1);
				else if(count>36&&count<45 )
					line5 =(byte) (line5 +1);
			}
		}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(AreaName+".Fishing.54."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(AreaName+".Fishing.54."+(counter), areaYaml.getItemStack(AreaName+".Fishing.54."+(counter+1)));
					areaYaml.removeKey(AreaName+".Fishing.54."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(AreaName+".Fishing.30."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(AreaName+".Fishing.30."+(counter), areaYaml.getItemStack(AreaName+".Fishing.30."+(counter+1)));
					areaYaml.removeKey(AreaName+".Fishing.30."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(AreaName+".Fishing.10."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(AreaName+".Fishing.10."+(counter), areaYaml.getItemStack(AreaName+".Fishing.10."+(counter+1)));
					areaYaml.removeKey(AreaName+".Fishing.10."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(AreaName+".Fishing.5."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(AreaName+".Fishing.5."+(counter), areaYaml.getItemStack(AreaName+".Fishing.5."+(counter+1)));
					areaYaml.removeKey(AreaName+".Fishing.5."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(AreaName+".Fishing.1."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(AreaName+".Fishing.1."+(counter), areaYaml.getItemStack(AreaName+".Fishing.1."+(counter+1)));
					areaYaml.removeKey(AreaName+".Fishing.1."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = line1; count <7;count++)
			if(areaYaml.contains(AreaName+".Fishing.54."+count))
				areaYaml.removeKey(AreaName+".Fishing.54."+count);
		for(int count = line2; count <7;count++)
			if(areaYaml.contains(AreaName+".Fishing.30."+count))
				areaYaml.removeKey(AreaName+".Fishing.30."+count);
		for(int count = line3; count <7;count++)
			if(areaYaml.contains(AreaName+".Fishing.10."+count))
				areaYaml.removeKey(AreaName+".Fishing.10."+count);
		for(int count = line4; count <7;count++)
			if(areaYaml.contains(AreaName+".Fishing.5."+count))
				areaYaml.removeKey(AreaName+".Fishing.5."+count);
		for(int count = line5; count <7;count++)
			if(areaYaml.contains(AreaName+".Fishing.1."+count))
				areaYaml.removeKey(AreaName+".Fishing.1."+count);
		areaYaml.saveConfig();
		return;
	}
	
	public void BlockItemSettingInventoryClose(InventoryCloseEvent event)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String ItemData = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
		String AreaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		if(event.getInventory().getItem(4) != null)
			areaYaml.set(AreaName+".Mining."+ItemData+".100", event.getInventory().getItem(4));
		else
		{
			areaYaml.removeKey(AreaName+".Mining."+ItemData+".100");
			areaYaml.set(AreaName+".Mining."+ItemData,"0:0");
		}
		if(event.getInventory().getItem(13) != null)
			areaYaml.set(AreaName+".Mining."+ItemData+".90", event.getInventory().getItem(13));
		else
		{
			areaYaml.removeKey(AreaName+".Mining."+ItemData+".90");
			areaYaml.set(AreaName+".Mining."+ItemData+".90","0:0");
		}
		if(event.getInventory().getItem(22) != null)
			areaYaml.set(AreaName+".Mining."+ItemData+".50", event.getInventory().getItem(22));
		else
		{
			areaYaml.removeKey(AreaName+".Mining."+ItemData+".50");
			areaYaml.set(AreaName+".Mining."+ItemData+".50","0:0");
		}
		if(event.getInventory().getItem(31) != null)
			areaYaml.set(AreaName+".Mining."+ItemData+".10", event.getInventory().getItem(31));
		else
		{
			areaYaml.removeKey(AreaName+".Mining."+ItemData+".10");
			areaYaml.set(AreaName+".Mining."+ItemData+".10","0:0");
		}
		if(event.getInventory().getItem(40) != null)
			areaYaml.set(AreaName+".Mining."+ItemData+".1", event.getInventory().getItem(40));
		else
		{
			areaYaml.removeKey(AreaName+".Mining."+ItemData+".1");
			areaYaml.set(AreaName+".Mining."+ItemData+".1","0:0");
		}
		if(event.getInventory().getItem(49) != null)
			areaYaml.set(AreaName+".Mining."+ItemData+".0", event.getInventory().getItem(49));
		else
		{
			areaYaml.removeKey(AreaName+".Mining."+ItemData+".0");
			areaYaml.set(AreaName+".Mining."+ItemData+".0","0:0");
		}
		areaYaml.saveConfig();
		return;
	}

}