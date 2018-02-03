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

import admin.OPboxGui;
import battle.BattleCalculator;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;



public class AreaGui extends UtilGui
{
	public void areaListGui(Player player, short page)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String uniqueCode = "��0��0��2��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0��ü ���� ��� : " + (page+1));

		String[] areaList= areaYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		String areaName = null;
		String world = null;
		for(int count = page*45; count < areaList.length;count++)
		{
			areaName = areaList[count];
			
			if(count > areaList.length || loc >= 45) break;
			world = areaYaml.getString(areaName+".World");
			int minXLoc = areaYaml.getInt(areaName+".X.Min");
			int minYLoc = areaYaml.getInt(areaName+".Y.Min");
			int minZLoc = areaYaml.getInt(areaName+".Z.Min");
			int maxXLoc = areaYaml.getInt(areaName+".X.Max");
			int maxYLoc = areaYaml.getInt(areaName+".Y.Max");
			int maxZLoc = areaYaml.getInt(areaName+".Z.Max");
			
			byte priority = (byte) areaYaml.getInt(areaName+".Priority");
			Stack2("��f��l" + areaName, 395,0,1,Arrays.asList(
					"��3���� : "+world,"��3X ���� : "+minXLoc+" ~ " + maxXLoc
					,"��3Y ���� : "+minYLoc+" ~ " + maxYLoc
					,"��3Z ���� : "+minZLoc+" ~ " + maxZLoc
					,"��3�켱 ���� : "+ priority
					,"��8�������� ���� ��ĥ ���",
					"��8�켱 ������ �� ���� ������",
					"��8����˴ϴ�."
					,"","��e[�� Ŭ���� ���� ����]","��c[Shift + ��Ŭ���� ���� ����]"), loc, inv);
			
			loc++;
		}
		
		if(areaList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ����", 386,0,1,Arrays.asList("��7���ο� ������ �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void areaSettingGui (Player player, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String uniqueCode = "��0��0��2��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 45, uniqueCode + "��0���� ����");

		if(!areaYaml.getBoolean(areaName+".BlockUse"))
			Stack2("��f��l[��� ���]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 9, inv);
		else
			Stack2("��f��l[��� ���]", 116,0,1,Arrays.asList("","��a��l[   ���   ]",""), 9, inv);

		if(!areaYaml.getBoolean(areaName+".BlockPlace"))
			Stack2("��f��l[��� ��ġ]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 10, inv);
		else
			Stack2("��f��l[��� ��ġ]", 2,0,1,Arrays.asList("","��a��l[   ���   ]",""), 10, inv);

		if(!areaYaml.getBoolean(areaName+".BlockBreak"))
			Stack2("��f��l[��� �ı�]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 11, inv);
		else
			Stack2("��f��l[��� �ı�]", 278,0,1,Arrays.asList("","��a��l[   ���   ]",""), 11, inv);

		if(!areaYaml.getBoolean(areaName+".PVP"))
			Stack2("��f��l[   PVP   ]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 12, inv);
		else
			Stack2("��f��l[   PVP   ]", 267,0,1,Arrays.asList("","��a��l[   ���   ]",""), 12, inv);

		if(!areaYaml.getBoolean(areaName+".MobSpawn"))
			Stack2("��f��l[���� ����]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 13, inv);
		else
			Stack2("��f��l[���� ����]", 52,0,1,Arrays.asList("","��a��l[   ���   ]",""), 13, inv);

		if(!areaYaml.getBoolean(areaName+".Alert"))
			Stack2("��f��l[���� �޽���]", 166,0,1,Arrays.asList("","��c��l[   ����   ]",""), 14, inv);
		else
			Stack2("��f��l[���� �޽���]", 340,0,1,Arrays.asList("","��a��l[   ����   ]",""), 14, inv);

		if(!areaYaml.getBoolean(areaName+".SpawnPoint"))
			Stack2("��f��l[������ ���]", 166,0,1,Arrays.asList("","��c��l[   �Ұ�   ]",""), 15, inv);
		else
			Stack2("��f��l[������ ���]", 397,3,1,Arrays.asList("","��a��l[   ����   ]",""), 15, inv);

		if(!areaYaml.getBoolean(areaName+".Music"))
			Stack2("��f��l[����� ���]", 166,0,1,Arrays.asList("","��c��l[   ����   ]",""), 16, inv);
		else
			Stack2("��f��l[����� ���]", 84,0,1,Arrays.asList("","��a��l[   ���   ]",""), 16, inv);

		if(areaYaml.getInt(areaName+".RegenBlock")==0)
			Stack2("��f��l[��� ����]", 166,0,1,Arrays.asList("","��c��l[   ����   ]",""), 28, inv);
		else
			Stack2("��f��l[��� ����]", 103,0,1,Arrays.asList("","��a��l[   Ȱ��   ]","","��3"+areaYaml.getInt(areaName+".RegenBlock")+" �� ���� ����","","��c[�÷��̾ ���� ĵ ��ϸ� ���� �˴ϴ�]",""), 28, inv);

		Stack2("��f��l[Ư��ǰ ����]", 15,0,1,Arrays.asList("","��7���� �������� ����� ĳ��","��7������ �������� ������","��7���� �մϴ�.","","��e[Ŭ���� Ư��ǰ ����]"), 19, inv);
		Stack2("��f��l[���� ������]", 346,0,1,Arrays.asList("","��7���� �������� ���ø� �Ͽ�","��7���� �� �ִ� ������ Ȯ������","��7�����մϴ�.","��e[Ŭ���� ���� ������ ����]"), 20, inv);
		Stack2("��f��l[�켱���� ����]", 384,0,1,Arrays.asList("","��7�������� ���� ��ĥ ���","��7�켱 ������ �� ���� ������","��7����˴ϴ�.","��7�� �Ӽ��� �̿��Ͽ� ������ �����,","��7���� ������ ���� ���� ��","��7������ ���� �� �ֽ��ϴ�.","��9[   ���� �켱 ����   ]","��f "+areaYaml.getInt(areaName+".Priority"),"","��e[Ŭ���� �켱 ���� ����]"), 21, inv);
		Stack2("��f��l[���� ����]", 383,0,1,Arrays.asList("","��7���� �������� �ڿ�������","��7�����Ǵ� ���� ��ſ�","��7Ŀ���� ���ͷ� �����մϴ�.","","��e[Ŭ���� Ŀ���� ���� ����]","��c[���� ���� ������ ��Ȱ��]"), 22, inv);
		Stack2("��f��l[���� ���� ����]", 52,0,1,Arrays.asList("","��7���� ������ Ư�� ������","��7Ư�� �ð����� ���͸�","��7���� �մϴ�.","","��e[Ŭ���� ���� ���� ����]"), 31, inv);
		Stack2("��f��l[�޽��� ����]", 386,0,1,Arrays.asList("","��7���� ���� �޽����� �����մϴ�.","","��e[Ŭ���� ���� �޽��� ����]"), 23, inv);
		Stack2("��f��l[�߽��� ����]", 138,0,1,Arrays.asList("","��7���� ��ȯ, �ֱ� �湮 ��ġ����","��7������ ���� ���� ��������","��7�ڷ���Ʈ �Ǵ� �̺�Ʈ�� �߻��� ���","��7���� ��ġ�� �߽����� �˴ϴ�.","","��3[  ���� �߽���  ]","��3"+areaYaml.getString(areaName+".World")+" : "+areaYaml.getInt(areaName+".SpawnLocation.X")+","+areaYaml.getInt(areaName+".SpawnLocation.Y")+","+areaYaml.getInt(areaName+".SpawnLocation.Z"),"","��e[Ŭ���� ���� ��ġ�� ����]"), 24, inv);
		
		if(areaYaml.getInt(areaName+".Restrict.MinNowLevel")+areaYaml.getInt(areaName+".Restrict.MinNowLevel")+
			areaYaml.getInt(areaName+".Restrict.MinRealLevel")+areaYaml.getInt(areaName+".Restrict.MaxRealLevel")==0)
			Stack2("��a��l[���� ���� ���� ����]", 166,0,1,Arrays.asList("","��7������ ���� ���� ������ �����ϴ�.",""), 34, inv);
		else
			Stack2("��c��l[���� ���� ����]", 399,0,1,Arrays.asList("","��7������ ���� ���� ������ �ֽ��ϴ�.",""
			,"��3[  �ּ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MinNowLevel")
			,"��3[  �ִ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MaxNowLevel")
			,"��7 �� ������ �ý����� ��� �߰� ���� ��"
			,"��3[  �ּ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MinRealLevel")
			,"��3[  �ִ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MaxRealLevel"),""), 34, inv);
		String lore = "";
		short tracknumber = (short) areaYaml.getInt(areaName+".BGM");
		lore = " %enter%��7���� ����� �׸� ����%enter%��7��� ��ų �� �ֽ��ϴ�.%enter% %enter%��9[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%��3[Ʈ��] ��9"+ tracknumber+"%enter%"
		+"��3[����] ��9"+ new otherplugins.NoteBlockApiMain().getTitle(tracknumber)+"%enter%"
		+"��3[����] ��9"+new otherplugins.NoteBlockApiMain().getAuthor(tracknumber)+"%enter%��3[����] ";
		
		String description = new otherplugins.NoteBlockApiMain().getDescription(areaYaml.getInt(areaName+".BGM"));
		String lore2="";
		short a = 0;
		for(int count = 0; count <description.toCharArray().length; count++)
		{
			lore2 = lore2+"��9"+description.toCharArray()[count];
			a++;
			if(a >= 15)
			{
				a = 0;
				lore2 = lore2+"%enter%      ";
			}
		}
		lore = lore + lore2;
		
		Stack2("��f��l[���� �����]", 2263,0,1,Arrays.asList(lore.split("%enter%")), 25, inv);
		
		Stack2("��f��l���� �̵�", 368,0,1,Arrays.asList("��7���� �������� ������ �̵��մϴ�."), 40, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ������� ���ư��ϴ�."), 36, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7���� â�� �ݽ��ϴ�.","��0"+areaName), 44, inv);
		
		player.openInventory(inv);
		return;
	}
	
	public void areaMonsterSpawnSettingGui(Player player, short page, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		String uniqueCode = "��0��0��2��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ���� ���� �� : " + (page+1));
		if(!areaYaml.contains(areaName+".MonsterSpawnRule"))
		{
			areaYaml.createSection(areaName+".MonsterSpawnRule");
			areaYaml.saveConfig();
		}
		String[] ruleList= areaYaml.getConfigurationSection(areaName+".MonsterSpawnRule").getKeys(false).toArray(new String[0]);
		byte loc=0;
		for(int count = page*45; count <ruleList.length ;count++)
		{
			if(count > ruleList.length || loc >= 45) break;
			String ruleNumber = ruleList[count];
			if(areaYaml.contains(areaName+".MonsterSpawnRule."+ruleNumber+".Monster"))
				Stack2("��0��l" + (ruleNumber), 383,0,1,Arrays.asList(
						"��6[     ���� �ɼ�     ]","��c-������ ������ ���� ���� �۵� -","��6���� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.world"),
						"��6��ǥ : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.x")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.y")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.z"),
						"��6�ν� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".range")+"���",
						"��6�ð� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".timer")+"�ʸ��� "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".count")+"���� ����",
						"��6�ִ� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".max")+"����",
						"��6���� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".Monster")
						,"","��c[Shift + ��Ŭ���� �� ����]"), loc, inv);
			else
				Stack2("��0��l" + (ruleNumber), 52,0,1,Arrays.asList(
					"��6[     ���� �ɼ�     ]","��c-������ ������ ���� ���� �۵� -","��6���� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.world"),
					"��6��ǥ : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.x")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.y")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.z"),
					"��6�ν� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".range")+"���",
					"��6�ð� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".timer")+"�ʸ��� "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".count")+"���� ����",
					"��6�ִ� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".max")+"����"
					,"","��c[Shift + ��Ŭ���� �� ����]"), loc, inv);
			loc++;
		}

		if(ruleList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ��Ģ �߰�", 52,0,1,Arrays.asList("��7�� ���� ��Ģ�� �߰��մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void areaMonsterSettingGui(Player player, short page, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");

		String uniqueCode = "��0��0��2��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��ü ���� : " + (page+1));

		String[] monsterNameList= areaYaml.getConfigurationSection(areaName+".Monster").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		short mobNameListLength = (short) monsterNameList.length;
		String monsterName = null;
		String name = null;
		for(int count = page*45; count <mobNameListLength ;count++)
		{
			monsterName = monsterNameList[count];
			if(monsterYaml.contains(monsterName) == true)
			{
				name = monsterYaml.getString(monsterName+".Name");
				if(count > mobNameListLength || loc >= 45) break;
				Stack2("��f��l" + monsterName, 383,0,1,Arrays.asList(
						"��f�̸� : " + name,"��fŸ�� : " + monsterYaml.getString(monsterName+".Type"),
						"��f����� : " + monsterYaml.getInt(monsterName+".HP"),"��f����ġ : " + monsterYaml.getInt(monsterName+".EXP"),
						"��f��� : " + monsterYaml.getInt(monsterName+".MIN_Money")+" ~ " +monsterYaml.getInt(monsterName+".MAX_Money"),
						"","��c[Shift + ��Ŭ���� ��� ����]"), loc, inv);
				loc++;
			}
			else
			{
				areaYaml.removeKey(areaName+".Monster."+monsterName);
				areaYaml.saveConfig();
			}
		}
		if(mobNameListLength-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� �߰�", 52,0,1,Arrays.asList("��7�� Ŀ���� ���͸� �߰��մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void areaFishSettingGui(Player player, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		String uniqueCode = "��1��0��2��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� �߰� ���");
		
		Stack2("��a��l[     54%     ]", 160,5,1,Arrays.asList("��7�� �ٿ��� 54% Ȯ���� ���� �������� �ø�����."), 0, inv);
		Stack2("��e��l[     30%     ]", 160,4,1,Arrays.asList("��7�� �ٿ��� 30% Ȯ���� ���� �������� �ø�����."), 9, inv);
		Stack2("��6��l[     10%     ]", 160,1,1,Arrays.asList("��7�� �ٿ��� 10% Ȯ���� ���� �������� �ø�����."), 18, inv);
		Stack2("��c��l[      5%      ]", 160,14,1,Arrays.asList("��7�� �ٿ��� 5% Ȯ���� ���� �������� �ø�����."), 27, inv);
		Stack2("��7��l[      1%      ]", 160,10,1,Arrays.asList("��7�� �ٿ��� 1% Ȯ���� ���� �������� �ø�����."), 36, inv);

		String[] fishingItemList = areaYaml.getConfigurationSection(areaName+".Fishing.54").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < fishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(areaName+".Fishing.54."+fishingItemList[count]), count+1, inv);
		fishingItemList = areaYaml.getConfigurationSection(areaName+".Fishing.30").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < fishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(areaName+".Fishing.30."+fishingItemList[count]), count+10, inv);
		fishingItemList = areaYaml.getConfigurationSection(areaName+".Fishing.10").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < fishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(areaName+".Fishing.10."+fishingItemList[count]), count+19, inv);
		fishingItemList = areaYaml.getConfigurationSection(areaName+".Fishing.5").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < fishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(areaName+".Fishing.5."+fishingItemList[count]), count+28, inv);
		fishingItemList = areaYaml.getConfigurationSection(areaName+".Fishing.1").getKeys(false).toArray(new String[0]);
		for(int count = 0; count < fishingItemList.length; count++)
			ItemStackStack(areaYaml.getItemStack(areaName+".Fishing.1."+fishingItemList[count]), count+37, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
	}
	
	public void areaBlockSettingGui(Player player, short page, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		event.EventInteract I = new event.EventInteract();

		String uniqueCode = "��0��0��2��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� Ư��ǰ : " + (page+1));

		String[] blockIdDataList= areaYaml.getConfigurationSection(areaName+".Mining").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count <blockIdDataList.length ;count++)
		{
			short id = Short.parseShort(blockIdDataList[count].split(":")[0]);
			byte data = Byte.parseByte(blockIdDataList[count].split(":")[1]);

			Stack2(I.SetItemDefaultName(id, (byte) data), id,data,1,Arrays.asList(
					"","��c[Shift + ��Ŭ���� ��� ����]"), loc, inv);
				loc++;
		}
		
		if(blockIdDataList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��lƯ�깰 �߰�", 52,0,1,Arrays.asList("��7���ο� ����� �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
	}
	
	public void areaBlockItemSettingGui(Player player,String areaName,String itemData)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		String uniqueCode = "��1��0��2��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�ش� ����� ĳ�� ���� ������");

		ItemStack item = areaYaml.getItemStack(areaName+".Mining."+itemData+".100");
		
		ItemStackStack(item, 4, inv);

		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 0, inv);
		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 1, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 2, inv);
		Stack2("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 3, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 5, inv);
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 6, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 7, inv);
		Stack2("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 8, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".90");
		ItemStackStack(item, 13, inv);
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 9, inv);
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 10, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 11, inv);
		Stack2("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 12, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 14, inv);
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 15, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 16, inv);
		Stack2("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 17, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".50");
		ItemStackStack(item, 22, inv);
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 18, inv);
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 19, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 20, inv);
		Stack2("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 21, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 23, inv);
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 24, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 25, inv);
		Stack2("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 26, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".10");
		ItemStackStack(item, 31, inv);
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 27, inv);
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 28, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 29, inv);
		Stack2("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 30, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 32, inv);
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 33, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 34, inv);
		Stack2("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 35, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".1");
		ItemStackStack(item, 40, inv);
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 36, inv);
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 37, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 38, inv);
		Stack2("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 39, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 41, inv);
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 42, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 43, inv);
		Stack2("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 44, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".0");
		ItemStackStack(item, 49, inv);
		Stack2("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 46, inv);	
		Stack2("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 47, inv);
		Stack2("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 48, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 50, inv);
		Stack2("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 51, inv);	
		Stack2("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 52, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+itemData), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void areaAddMonsterListGui(Player player, short page,String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");

		String uniqueCode = "��0��0��2��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ���� ���� : " + (page+1));

		String[] monsterList= monsterYaml.getKeys().toArray(new String[0]);
		String[] monsterNameList= areaYaml.getConfigurationSection(areaName+".Monster").getKeys(false).toArray(new String[0]);

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
				if(monsterNameList[count2].equals(monsterName))
				{
					isExit=true;
					break;
				}
			}
			
			if(!isExit)
			{
				lore = null;
				lore = "%enter%��f��l �̸� : ��f"+monsterYaml.getString(monsterName+".Name")+"%enter%";
				lore = lore+"��f��l Ÿ�� : ��f"+monsterYaml.getString(monsterName+".Type")+"%enter%";
				lore = lore+"��f��l ���� ���̿� : ��f"+monsterYaml.getString(monsterName+".Biome")+"%enter%";
				lore = lore+"��c��l ����� : ��f"+monsterYaml.getInt(monsterName+".HP")+"%enter%";
				lore = lore+"��b��l ����ġ : ��f"+monsterYaml.getInt(monsterName+".EXP")+"%enter%";
				lore = lore+"��e��l ��� �ݾ� : ��f"+monsterYaml.getInt(monsterName+".MIN_Money")+" ~ "+monsterYaml.getInt(monsterName+".MAX_Money")+"%enter%";
				lore = lore+"��c��l "+MainServerOption.statSTR+" : ��f"+monsterYaml.getInt(monsterName+".STR")
				+"��7 [���� : " + BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), true) + " ~ " + BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), false) + "]%enter%";
				lore = lore+"��a��l "+MainServerOption.statDEX+" : ��f"+monsterYaml.getInt(monsterName+".DEX")
				+"��7 [Ȱ�� : " + BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, true) + " ~ " + BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, false) + "]%enter%";
				lore = lore+"��9��l "+MainServerOption.statINT+" : ��f"+monsterYaml.getInt(monsterName+".INT")
				+"��7 [���� : " + (monsterYaml.getInt(monsterName+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(monsterName+".INT")/2.5)+"]%enter%";
				lore = lore+"��7��l "+MainServerOption.statWILL+" : ��f"+monsterYaml.getInt(monsterName+".WILL")
				+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
				lore = lore+"��e��l "+MainServerOption.statLUK+" : ��f"+monsterYaml.getInt(monsterName+".LUK")
				+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
				lore = lore+"��7��l ��� : ��f"+monsterYaml.getInt(monsterName+".DEF")+"%enter%";
				lore = lore+"��b��l ��ȣ : ��f"+monsterYaml.getInt(monsterName+".Protect")+"%enter%";
				lore = lore+"��9��l ���� ��� : ��f"+monsterYaml.getInt(monsterName+".Magic_DEF")+"%enter%";
				lore = lore+"��1��l ���� ��ȣ : ��f"+monsterYaml.getInt(monsterName+".Magic_Protect")+"%enter%";
				lore = lore+"%enter%��e��l[�� Ŭ���� ���� ���]";

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
				
				Stack("��f"+monsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
				loc++;
			}
		}
		
		if(monsterList.length-(page*44)>45)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+areaName), 53, inv);
		player.openInventory(inv);
	}
	
	public void areaSpawnSpecialMonsterListGui(Player player, short page,String areaName,String ruleCount)
	{
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		String uniqueCode = "��0��0��2��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� Ư�� ����  : " + (page+1));

		String[] monsterList= monsterYaml.getKeys().toArray(new String[0]);

		byte loc=0;
		String monsterName = null;
		String lore = null;
		for(int count = page*45; count < monsterList.length;count++)
		{
			if(count > monsterList.length || loc >= 45) break;
			monsterName = monsterList[count];
			lore = null;
			lore = "%enter%��f��l �̸� : ��f"+monsterYaml.getString(monsterName+".Name")+"%enter%";
			lore = lore+"��f��l Ÿ�� : ��f"+monsterYaml.getString(monsterName+".Type")+"%enter%";
			lore = lore+"��f��l ���� ���̿� : ��f"+monsterYaml.getString(monsterName+".Biome")+"%enter%";
			lore = lore+"��c��l ����� : ��f"+monsterYaml.getInt(monsterName+".HP")+"%enter%";
			lore = lore+"��b��l ����ġ : ��f"+monsterYaml.getInt(monsterName+".EXP")+"%enter%";
			lore = lore+"��e��l ��� �ݾ� : ��f"+monsterYaml.getInt(monsterName+".MIN_Money")+" ~ "+monsterYaml.getInt(monsterName+".MAX_Money")+"%enter%";
			lore = lore+"��c��l "+MainServerOption.statSTR+" : ��f"+monsterYaml.getInt(monsterName+".STR")
			+"��7 [���� : " + BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), true) + " ~ " + BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), false) + "]%enter%";
			lore = lore+"��a��l "+MainServerOption.statDEX+" : ��f"+monsterYaml.getInt(monsterName+".DEX")
			+"��7 [Ȱ�� : " + BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, true) + " ~ " + BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, false) + "]%enter%";
			lore = lore+"��9��l "+MainServerOption.statINT+" : ��f"+monsterYaml.getInt(monsterName+".INT")
			+"��7 [���� : " + (monsterYaml.getInt(monsterName+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(monsterName+".INT")/2.5)+"]%enter%";
			lore = lore+"��7��l "+MainServerOption.statWILL+" : ��f"+monsterYaml.getInt(monsterName+".WILL")
			+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
			lore = lore+"��e��l "+MainServerOption.statLUK+" : ��f"+monsterYaml.getInt(monsterName+".LUK")
			+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
			lore = lore+"��7��l ��� : ��f"+monsterYaml.getInt(monsterName+".DEF")+"%enter%";
			lore = lore+"��b��l ��ȣ : ��f"+monsterYaml.getInt(monsterName+".Protect")+"%enter%";
			lore = lore+"��9��l ���� ��� : ��f"+monsterYaml.getInt(monsterName+".Magic_DEF")+"%enter%";
			lore = lore+"��1��l ���� ��ȣ : ��f"+monsterYaml.getInt(monsterName+".Magic_Protect")+"%enter%";
			lore = lore+"%enter%��e��l[�� Ŭ���� ���� ���]";

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
			
			Stack("��f"+monsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(monsterList.length-(page*44)>45)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l���", 166,0,1,Arrays.asList("��7���� ���� �������","��7������ ��� �� ���͸�","��7�����ϰ� ���� �մϴ�.","��0"+areaName,"��0"+ruleCount), 49, inv);
		player.openInventory(inv);
	}

	public void areaMusicSettingGui(Player player, int page,String areaName)
	{
		String uniqueCode = "��0��0��2��0��9��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ����� : " + (page+1));
		byte loc=0;
		byte model = (byte) new util.UtilNumber().RandomNum(0, 11);
		for(int count = page*45; count < new otherplugins.NoteBlockApiMain().Musics.size();count++)
		{
			if(model<11)
				model++;
			else
				model=0;
			String lore = " %enter%��3[Ʈ��] ��9"+ count+"%enter%"
			+"��3[����] ��9"+ new otherplugins.NoteBlockApiMain().getTitle(count)+"%enter%"
			+"��3[����] ��9"+new otherplugins.NoteBlockApiMain().getAuthor(count)+"%enter%��3[����] ";
			String description = new otherplugins.NoteBlockApiMain().getDescription(count);
			String lore2="";
			short a = 0;
			for(int counter = 0; counter <description.toCharArray().length; counter++)
			{
				lore2 = lore2+"��9"+description.toCharArray()[counter];
				a++;
				if(a >= 15)
				{
					a = 0;
					lore2 = lore2+"%enter%      ";
				}
			}
			lore = lore + lore2+"%enter% %enter%��e[�� Ŭ���� ����� ����]";
			if(count > new otherplugins.NoteBlockApiMain().Musics.size() || loc >= 45) break;
				Stack2("��f��l" + count, 2256+model,0,1,Arrays.asList(lore.split("%enter%")), loc, inv);
			
			loc++;
		}
		
		if(new otherplugins.NoteBlockApiMain().Musics.size()-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+areaName), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	public void areaListGuiClick(InventoryClickEvent event)
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
			String areaName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			
			if(slot == 45)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 2);
			else if(slot == 48)//���� ������
				areaListGui(player, (short) (page-1));
			else if(slot == 49)//���� �߰�
			{
			  	YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				player.closeInventory();
				event.EventInteract IT = new event.EventInteract();
				player.sendMessage("��3[����] : " + IT.SetItemDefaultName((short) configYaml.getInt("Server.AreaSettingWand"),(byte)0) +"��3 ���������� ������ ������ �� ��,");
				player.sendMessage("��6��l /���� <�����̸�> ���� ��3��ɾ �Է��� �ּ���!");
				SoundEffect.SP((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			else if(slot == 50)//���� ������
				areaListGui(player, (short) (page+1));
			else
			{
				if(event.isLeftClick())
					areaSettingGui(player, areaName);
				else if(event.isShiftClick() && event.isRightClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					for(int count = 0; count < MainServerOption.AreaList.get(areaYaml.getString(areaName+".World")).size(); count++)
						if(MainServerOption.AreaList.get(areaYaml.getString(areaName+".World")).get(count).areaName.equals(areaName))
							MainServerOption.AreaList.get(areaYaml.getString(areaName+".World")).remove(count);
					areaYaml.removeKey(areaName);
					areaYaml.saveConfig();
					areaListGui(player, page);
				}
			}
		}
	}
	
	public void areaSettingGuiInventoryClick(InventoryClickEvent event)
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
			String areaName = ChatColor.stripColor(event.getInventory().getItem(44).getItemMeta().getLore().get(1));
			if(slot == 36)//���� ȭ��
				areaListGui(player,(short) 0);
			else if(slot >= 9 && slot <= 16)
			{
				if(slot == 9)//��� ���
				{
					if(areaYaml.getBoolean(areaName+".BlockUse") == false)
						areaYaml.set(areaName+".BlockUse", true);
					else
						areaYaml.set(areaName+".BlockUse", false);
				}
				else if(slot == 10)//��� ��ġ
				{
					if(areaYaml.getBoolean(areaName+".BlockPlace") == false)
						areaYaml.set(areaName+".BlockPlace", true);
					else
						areaYaml.set(areaName+".BlockPlace", false);
				}
				else if(slot == 11)//��� �ı�
				{
					if(areaYaml.getBoolean(areaName+".BlockBreak") == false)
						areaYaml.set(areaName+".BlockBreak", true);
					else
						areaYaml.set(areaName+".BlockBreak", false);
				}
				else if(slot == 12)//PVP
				{
					if(areaYaml.getBoolean(areaName+".PVP") == false)
						areaYaml.set(areaName+".PVP", true);
					else
						areaYaml.set(areaName+".PVP", false);
				}
				else if(slot == 13)//���� ����
				{
					if(areaYaml.getBoolean(areaName+".MobSpawn") == false)
						areaYaml.set(areaName+".MobSpawn", true);
					else
						areaYaml.set(areaName+".MobSpawn", false);
				}
				else if(slot == 14)//���� �޽���
				{
					if(areaYaml.getBoolean(areaName+".Alert") == false)
						areaYaml.set(areaName+".Alert", true);
					else
						areaYaml.set(areaName+".Alert", false);
				}
				else if(slot == 15)//������ ���
				{
					if(areaYaml.getBoolean(areaName+".SpawnPoint") == false)
						areaYaml.set(areaName+".SpawnPoint", true);
					else
						areaYaml.set(areaName+".SpawnPoint", false);
				}
				else if(slot == 16)//����� ���
				{
					if(areaYaml.getBoolean(areaName+".Music") == false)
						areaYaml.set(areaName+".Music", true);
					else
						areaYaml.set(areaName+".Music", false);
				}
				areaYaml.saveConfig();
				areaSettingGui(player, areaName);
			}
			else if(slot == 21)//�켱 ���� ����
			{
				UserDataObject u = new UserDataObject();
				player.closeInventory();
				u.setType(player, "Area");
				u.setString(player, (byte)2, "Priority");
				u.setString(player, (byte)3, areaName);
				player.sendMessage("��a[����] : ��e"+areaName+"��a ������ �켱 ������ �Է��ϼ���!");
				player.sendMessage("��7(�ּ� 0 ~ �ִ� 100)");
			}
			else if(slot == 23)//�޽��� ����
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.sendMessage("��6/���� "+areaName+" �̸� <���ڿ�>��e\n - "+areaName+" ������ �˸� �޽����� ���� �̸��� ���մϴ�.");
				player.sendMessage("��6/���� "+areaName+" ���� <���ڿ�>��e\n - "+areaName+" ������ �˸� �޽����� ���� �ΰ� ������ ���մϴ�.");
				player.sendMessage("��6%player%��f - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
				"��3&3 ��4&4 ��5&5 " +
						"��6&6 ��7&7 ��8&8 " +
				"��9&9 ��a&a ��b&b ��c&c " +
						"��d&d ��e&e ��f&f");
				player.closeInventory();
			}
			else if(slot == 24)//�߽��� ����
			{
				areaYaml.set(areaName+".World", player.getLocation().getWorld().getName());
				areaYaml.set(areaName+".SpawnLocation.X", player.getLocation().getX());
				areaYaml.set(areaName+".SpawnLocation.Y", player.getLocation().getY());
				areaYaml.set(areaName+".SpawnLocation.Z", player.getLocation().getZ());
				areaYaml.set(areaName+".SpawnLocation.Pitch", player.getLocation().getPitch());
				areaYaml.set(areaName+".SpawnLocation.Yaw", player.getLocation().getYaw());
				areaYaml.saveConfig();
				areaSettingGui(player, areaName);
			}
			else if(slot == 25)//���� ����� ����
			{
				if(new otherplugins.NoteBlockApiMain().SoundList(player,true))
					areaMusicSettingGui(player, 0, areaName);
				else
					SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.9F);
			}
			else if(slot == 28)//��� ����
			{
				if(areaYaml.getInt(areaName+".RegenBlock")==0)
				{
					player.closeInventory();
					UserDataObject u = new UserDataObject();
					areaYaml.set(areaName+".RegenBlock", 1);
					areaYaml.saveConfig();
					u.setType(player, "Area");
					u.setString(player, (byte)2, "ARR");
					u.setString(player, (byte)3, areaName);
					player.sendMessage("��a[����] : ��e"+areaName+"��a ������ ��� ���� �ӵ��� �����ϼ���!");
					player.sendMessage("��7(�ּ� 1�� ~ �ִ� 3600��(1�ð�))");
				}
				else
				{
					areaYaml.set(areaName+".RegenBlock", 0);
					areaYaml.saveConfig();
					areaSettingGui(player, areaName);
				}
			}
			else if(slot == 19)//Ư��ǰ ����
				areaBlockSettingGui(player, (short) 0, areaName);
			else if(slot == 20)//���� ������
				areaFishSettingGui(player, areaName);
			else if(slot == 22)//���� ����
				areaMonsterSettingGui(player,(short) 0, areaName);
			else if(slot == 31)//���� ���� ��
				areaMonsterSpawnSettingGui(player, (short) 0, areaName);
			else if(slot == 34)//���� ����
			{
				UserDataObject u = new UserDataObject();
				player.closeInventory();
				u.setType(player, "Area");
				u.setString(player, (byte)2, "MinNLR");
				u.setString(player, (byte)3, areaName);
				player.sendMessage("��a[����] : ��e"+areaName+"��a ������ ���忡 �ʿ��� �ּ� ������ �Է� �ϼ���!��7 (0 �Է½� ���� ����)");
			}
			else if(slot == 40)//���� �̵�
			{
				player.closeInventory();
				player.teleport(new Location(Bukkit.getWorld(areaYaml.getString(areaName+".World")),areaYaml.getInt(areaName+".SpawnLocation.X"), areaYaml.getInt(areaName+".SpawnLocation.Y"),areaYaml.getInt(areaName+".SpawnLocation.Z"),areaYaml.getInt(areaName+".SpawnLocation.Yaw"),areaYaml.getInt(areaName+".SpawnLocation.Pitch")));
			}
		}
		return;
	}

	public void areaMonsterSettingGuiClick(InventoryClickEvent event)
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
			String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ȭ��
				areaSettingGui(player, areaName);
			else if(slot == 48)//���� ������
				areaMonsterSettingGui(player, (short) (page-1), areaName);
			else if(slot == 49)//���� �߰�
			{
			  	YamlLoader monsterYaml = new YamlLoader();
				monsterYaml.getConfig("Monster/MonsterList.yml");
				if(monsterYaml.getKeys().size() == 0)
				{
					SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0F, 1.8F);
					player.sendMessage("��c[����] : ���� ��ϵ� Ŀ���� ���Ͱ� �������� �ʽ��ϴ�!");
					player.sendMessage("��6��l/���� <�̸�> ���� ��e�ش� �̸��� ���� ���͸� �����մϴ�.");
				}
				else
					areaAddMonsterListGui(player, page, areaName);
			}
			else if(slot == 50)//���� ������
				areaMonsterSettingGui(player, (short) (page+1),areaName);
			else
			{
				if(event.isShiftClick() && event.isRightClick())
				{
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					String monsterName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					areaYaml.removeKey(areaName+".Monster."+monsterName);
					areaYaml.saveConfig();
					areaMonsterSettingGui(player, page,areaName);
				}
			}
		}
	}

	public void areaFishSettingGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
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
			areaSettingGui(player, areaName);
		}
	}

	public void areaBlockSettingGuiClick(InventoryClickEvent event)
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
			String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				areaSettingGui(player, areaName);
			else if(slot == 48)//���� ������
				areaBlockSettingGui(player, (short) (page-1), areaName);
			else if(slot == 49)//Ư�깰 �߰�
			{
				player.closeInventory();
				player.sendMessage("��3[����] : ������ ����� ��Ŭ�� �ϼ���!");

				UserDataObject u = new UserDataObject();
				u.setType(player, "Area");
				u.setString(player, (byte)2, areaName);
				u.setString(player, (byte)3, "ANBI");
			}
			else if(slot == 50)//���� ������
				areaBlockSettingGui(player, (short) (page+1), areaName);
			else
			{
				String blockName = event.getCurrentItem().getTypeId()+":"+event.getCurrentItem().getData().getData();
				if(!event.isShiftClick()&&event.isLeftClick())
					areaBlockItemSettingGui(player, areaName, blockName);
				else if(event.isShiftClick() && event.isRightClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					areaYaml.removeKey(areaName+".Mining."+blockName);
					areaYaml.saveConfig();
					areaBlockSettingGui(player, page, areaName);
				}
			}
		}
	}

	public void areaBlockItemSettingGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		if(!event.getClickedInventory().getTitle().equals("container.inventory"))
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
				areaBlockSettingGui(player, (short) 0, areaName);
			}
		}
	}

	public void areaAddMonsterSpawnRuleGuiClick(InventoryClickEvent event)
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
			String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			if(slot == 45)//���� ���
				areaSettingGui(player, areaName);
			else if(slot == 48)//���� ������
				areaMonsterSpawnSettingGui(player, (short) (page-1), areaName);
			else if(slot == 49)//�� �߰�
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				long count = new util.ETC().getNowUTC();
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".range", 1);
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".count", 4);
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".timer", 10);
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".max", 10);
				UserDataObject u = new UserDataObject();
				u.setType(player, "Area");
				u.setString(player, (byte)1, count+"");
				u.setString(player, (byte)2, areaName);
				u.setString(player, (byte)3, "MLS");
				areaYaml.saveConfig();
				player.sendMessage("��a[����] : ���Ͱ� ���� �� ��ġ�� ���콺 �� Ŭ�� �ϼ���!");
				player.closeInventory();
			}
			else if(slot == 50)//���� ������
				areaMonsterSpawnSettingGui(player, (short) (page+1), areaName);
			else if(event.isRightClick()&&event.isShiftClick())
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
				areaYaml.removeKey(areaName+".MonsterSpawnRule."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				areaYaml.saveConfig();
				areaMonsterSpawnSettingGui(player, (short) page, areaName);
			}
		}
	}
	
	public void areaAddMonsterListGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
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
				areaMonsterSettingGui(player, (short) 0, areaName);
			else if(slot == 45)//���� ������
				areaAddMonsterListGui(player, (short) (page-1), areaName);
			else if(slot == 50)//���� ������
				areaAddMonsterListGui(player, (short) (page+1), areaName);
			else
			{
				String mobName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				SoundEffect.SP(player, Sound.ENTITY_WOLF_AMBIENT, 0.8F, 1.0F);
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				areaYaml.createSection(areaName+".Monster."+mobName);
				areaYaml.saveConfig();
				areaAddMonsterListGui(player, page, areaName);
			}
		}
	}

	public void areaSpawnSpecialMonsterListGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		String areaName = ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(3));
		String ruleCounter = ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(4));
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		if(slot == 49)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			areaMonsterSpawnSettingGui(player, (short) 0, areaName);
			new area.AreaMain().AreaMonsterSpawnAdd(areaName, ruleCounter);
		}
		else if(slot == 48)//���� ������
			areaSpawnSpecialMonsterListGui(player, (short) (page-1), areaName, ruleCounter);
		else if(slot == 50)//���� ������
			areaSpawnSpecialMonsterListGui(player, (short) (page+1), areaName, ruleCounter);
		else
		{
			String mobName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 0.8F, 1.0F);
		  	YamlLoader areaYaml = new YamlLoader();
			areaYaml.getConfig("Area/AreaList.yml");
			areaYaml.set(areaName+".MonsterSpawnRule."+ruleCounter+".Monster", mobName);
			areaYaml.saveConfig();
			areaMonsterSpawnSettingGui(player, (short) 0, areaName);
			
			new area.AreaMain().AreaMonsterSpawnAdd(areaName, ruleCounter);
		}
	}

	public void areaMusicSettingGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		int slot = event.getSlot();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);

		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else if(areaName.equals("DeathBGM��"))
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)
				new admin.OPboxGui().opBoxGuiDeath(player);
			else if(slot == 48)
				areaMusicSettingGui(player, page-1,areaName);
			else if(slot == 50)
				areaMusicSettingGui(player, page+1,areaName);
			else
			{
			  	YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				configYaml.set("Death.Track", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
				configYaml.saveConfig();
				new admin.OPboxGui().opBoxGuiDeath(player);
			}
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				areaSettingGui(player, areaName);
			else if(slot == 48)//���� ������
				areaMusicSettingGui(player, page-1,areaName);
			else if(slot == 50)//���� ������
				areaMusicSettingGui(player, page+1,areaName);
			else
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				areaYaml.set(areaName+".BGM", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
				areaYaml.saveConfig();
				areaSettingGui(player, areaName);
			}
		}
	}

	
	
	public void fishingSettingInventoryClose(InventoryCloseEvent event)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		byte loc = 0;
		for(int count = 1; count < 9; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(areaName + ".Fishing.54."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(areaName+".Fishing.54."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 10; count < 18; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(areaName + ".Fishing.30."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(areaName+".Fishing.30."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 19; count < 27; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(areaName + ".Fishing.10."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(areaName+".Fishing.10."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 28; count < 36; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(areaName + ".Fishing.5."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(areaName+".Fishing.5."+loc);
			areaYaml.saveConfig();
		}
		loc = 0;
		for(int count = 37; count < 45; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				areaYaml.set(areaName + ".Fishing.1."+loc, event.getInventory().getItem(count));
				loc++;
			}
			else
				areaYaml.removeKey(areaName+".Fishing.1."+loc);
			areaYaml.saveConfig();
		}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(areaName+".Fishing.54."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(areaName+".Fishing.54."+(counter), areaYaml.getItemStack(areaName+".Fishing.54."+(counter+1)));
					areaYaml.removeKey(areaName+".Fishing.54."+(counter+1));
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
			if(areaYaml.getItemStack(areaName+".Fishing.54."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(areaName+".Fishing.54."+(counter), areaYaml.getItemStack(areaName+".Fishing.54."+(counter+1)));
					areaYaml.removeKey(areaName+".Fishing.54."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(areaName+".Fishing.30."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(areaName+".Fishing.30."+(counter), areaYaml.getItemStack(areaName+".Fishing.30."+(counter+1)));
					areaYaml.removeKey(areaName+".Fishing.30."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(areaName+".Fishing.10."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(areaName+".Fishing.10."+(counter), areaYaml.getItemStack(areaName+".Fishing.10."+(counter+1)));
					areaYaml.removeKey(areaName+".Fishing.10."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(areaName+".Fishing.5."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(areaName+".Fishing.5."+(counter), areaYaml.getItemStack(areaName+".Fishing.5."+(counter+1)));
					areaYaml.removeKey(areaName+".Fishing.5."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = 0; count <7;count++)
			if(areaYaml.getItemStack(areaName+".Fishing.1."+count) == null)
			{
				for(int counter = count; counter <7; counter++)
				{
					areaYaml.set(areaName+".Fishing.1."+(counter), areaYaml.getItemStack(areaName+".Fishing.1."+(counter+1)));
					areaYaml.removeKey(areaName+".Fishing.1."+(counter+1));
				}
				areaYaml.saveConfig();
			}
		for(int count = line1; count <7;count++)
			if(areaYaml.contains(areaName+".Fishing.54."+count))
				areaYaml.removeKey(areaName+".Fishing.54."+count);
		for(int count = line2; count <7;count++)
			if(areaYaml.contains(areaName+".Fishing.30."+count))
				areaYaml.removeKey(areaName+".Fishing.30."+count);
		for(int count = line3; count <7;count++)
			if(areaYaml.contains(areaName+".Fishing.10."+count))
				areaYaml.removeKey(areaName+".Fishing.10."+count);
		for(int count = line4; count <7;count++)
			if(areaYaml.contains(areaName+".Fishing.5."+count))
				areaYaml.removeKey(areaName+".Fishing.5."+count);
		for(int count = line5; count <7;count++)
			if(areaYaml.contains(areaName+".Fishing.1."+count))
				areaYaml.removeKey(areaName+".Fishing.1."+count);
		areaYaml.saveConfig();
		return;
	}
	
	public void blockItemSettingInventoryClose(InventoryCloseEvent event)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String itemData = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		if(event.getInventory().getItem(4) != null)
			areaYaml.set(areaName+".Mining."+itemData+".100", event.getInventory().getItem(4));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".100");
			areaYaml.set(areaName+".Mining."+itemData,"0:0");
		}
		if(event.getInventory().getItem(13) != null)
			areaYaml.set(areaName+".Mining."+itemData+".90", event.getInventory().getItem(13));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".90");
			areaYaml.set(areaName+".Mining."+itemData+".90","0:0");
		}
		if(event.getInventory().getItem(22) != null)
			areaYaml.set(areaName+".Mining."+itemData+".50", event.getInventory().getItem(22));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".50");
			areaYaml.set(areaName+".Mining."+itemData+".50","0:0");
		}
		if(event.getInventory().getItem(31) != null)
			areaYaml.set(areaName+".Mining."+itemData+".10", event.getInventory().getItem(31));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".10");
			areaYaml.set(areaName+".Mining."+itemData+".10","0:0");
		}
		if(event.getInventory().getItem(40) != null)
			areaYaml.set(areaName+".Mining."+itemData+".1", event.getInventory().getItem(40));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".1");
			areaYaml.set(areaName+".Mining."+itemData+".1","0:0");
		}
		if(event.getInventory().getItem(49) != null)
			areaYaml.set(areaName+".Mining."+itemData+".0", event.getInventory().getItem(49));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".0");
			areaYaml.set(areaName+".Mining."+itemData+".0","0:0");
		}
		areaYaml.saveConfig();
		return;
	}
}