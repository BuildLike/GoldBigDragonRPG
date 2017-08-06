package dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import admin.OPbox_GUI;
import battle.Battle_Calculator;
import effect.SoundEffect;
import servertick.ServerTick_Main;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;



public final class Dungeon_GUI extends Util_GUI
{
	/*
	���� ����.
	������ ����.
	���� ����.
	�� ������ ���� ������ ����.
	���ܸ��� ������ ����.
	�Ϲ� �������� ���� ����� ���� ����.
	
	��� ���� ���� ���� ����.
	���� ���� ����.
	���� ���� ����.
	
	��� ����/����/�������� ��� ���� �ٸ�.
	 */
	
	//DungeonGUI//
	public void DungeonListMainGUI(Player player, int page, int Type)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/DungeonList.yml");

		String UniqueCode = "��0��0��a��0��0��r";
		Inventory inv = null;
		if(Type==52)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));
		else if(Type==358)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ��� : " + (page+1));
		else if(Type==120)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));
		String[] dungeonList = null;
		if(Type==52)//����
		{
			dungeonList = dungeonYaml.getKeys().toArray(new String[0]);
			
			int loc=0;
			for(int count = page*45; count < dungeonList.length;count++)
			{
			  	YamlLoader DungeonOptionYML = new YamlLoader();
				DungeonOptionYML.getConfig("Dungeon/Dungeon/"+dungeonList[count]+"/Option.yml");
				
				Stack2("��f��l" + dungeonList[count], 52,0,1,Arrays.asList(
				"",ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getString("Type.Name")
				,ChatColor.BLUE+"���� ũ�� : "+ChatColor.WHITE+DungeonOptionYML.getInt("Size")
				,ChatColor.BLUE+"���� ������ : "+ChatColor.WHITE+DungeonOptionYML.getInt("Maze_Level")
				,ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.Level")+" �̻�"
				,ChatColor.BLUE+"���� ���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.RealLevel")+" �̻�"
				,"",ChatColor.BLUE+"[�⺻ Ŭ���� ����]"
				,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.Money")+" "+main.Main_ServerOption.Money
				,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.EXP")+"��b��lEXP"
				,"",ChatColor.YELLOW+"[�� Ŭ���� ���� ����]",ChatColor.RED+"[Shift + ��Ŭ���� ���� ����]"), loc, inv);
				
				loc=loc+1;
			}
		}
		else if(Type==358)//������
		{
			dungeonYaml.getConfig("Dungeon/EnterCardList.yml");
			dungeonList = dungeonYaml.getKeys().toArray(new String[0]);
			int loc=0;
			String Time = null;
			for(int count = page*45; count < dungeonList.length;count++)
			{
				if(dungeonYaml.getInt(dungeonList[count]+".Hour")!=-1)
				{
					if(dungeonYaml.getInt(dungeonList[count]+".Hour")!=0)
						Time = dungeonYaml.getInt(dungeonList[count]+".Hour")+"�ð� ";
					if(dungeonYaml.getInt(dungeonList[count]+".Min")!=0)
						Time = Time+dungeonYaml.getInt(dungeonList[count]+".Min")+"�� ";
					Time = Time+dungeonYaml.getInt(dungeonList[count]+".Sec")+"��";
				}
				else
					Time = "������";
				if(dungeonYaml.getString(dungeonList[count]+".Dungeon")==null)
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + dungeonList[count], dungeonYaml.getInt(dungeonList[count]+".ID"),dungeonYaml.getInt(dungeonList[count]+".DATA"),1,Arrays.asList(
					"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
					ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+dungeonYaml.getInt(dungeonList[count]+".Capacity"),
					ChatColor.BLUE+"��ȿ �ð� : "+ChatColor.WHITE+""+Time,
					"",ChatColor.YELLOW+"[�� Ŭ���� ������ ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �߱�]",ChatColor.RED+"[Shift + ��Ŭ���� ������ ����]"), loc, inv);
				else
				{
				  	YamlLoader Dungeon = new YamlLoader();
					Dungeon.getConfig("Dungeon/DungeonList.yml");
					if(Dungeon.contains(dungeonYaml.getString(dungeonList[count]+".Dungeon")))
					{
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + dungeonList[count], dungeonYaml.getInt(dungeonList[count]+".ID"),dungeonYaml.getInt(dungeonList[count]+".DATA"),1,Arrays.asList(
						"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+""+dungeonYaml.getString(dungeonList[count]+".Dungeon"),
						ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+dungeonYaml.getInt(dungeonList[count]+".Capacity"),
						ChatColor.BLUE+"��ȿ �ð� : "+ChatColor.WHITE+""+Time,
						"",ChatColor.YELLOW+"[�� Ŭ���� ������ ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �߱�]",ChatColor.RED+"[Shift + ��Ŭ���� ������ ����]"), loc, inv);
					}
					else
					{
						dungeonYaml.set(dungeonList[count]+".Dungeon",null);
						dungeonYaml.saveConfig();
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + dungeonList[count], dungeonYaml.getInt(dungeonList[count]+".ID"),dungeonYaml.getInt(dungeonList[count]+".DATA"),1,Arrays.asList(
						"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
						ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+dungeonYaml.getInt(dungeonList[count]+".Capacity"),
						ChatColor.BLUE+"��ȿ �ð� : "+ChatColor.WHITE+""+Time,
						"",ChatColor.YELLOW+"[�� Ŭ���� ������ ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �߱�]",ChatColor.RED+"[Shift + ��Ŭ���� ������ ����]"), loc, inv);
					}
						
				}
				
				loc=loc+1;
			}
		}
		else if(Type==120)//����
		{
			dungeonYaml.getConfig("Dungeon/AltarList.yml");
			dungeonList = dungeonYaml.getKeys().toArray(new String[0]);
			int loc=0;
			String AltarCode = null;
			for(int count = page*45; count < dungeonList.length;count++)
			{
				AltarCode = dungeonList[count];
				Stack2(ChatColor.WHITE+dungeonYaml.getString(AltarCode+".Name"), dungeonYaml.getInt(AltarCode+".ID"),dungeonYaml.getInt(AltarCode+".DATA"),1,Arrays.asList(
				"",ChatColor.BLUE+"[���� ��ġ]",
				ChatColor.WHITE+" "+dungeonYaml.getString(AltarCode+".World"),
				ChatColor.WHITE+" "+dungeonYaml.getInt(AltarCode+".X")+","+dungeonYaml.getInt(AltarCode+".Y")+","+dungeonYaml.getInt(AltarCode+".Z"),
				"",ChatColor.YELLOW+"[�� Ŭ���� ���� ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ���� �̵�]",ChatColor.RED+"[Shift + ��Ŭ���� ���� ö��]","",AltarCode), loc, inv);
				
				loc=loc+1;
			}
		}
		if(Type==52)
			Stack2("��9��l[���� �׸�]", 52,0,1,Arrays.asList(ChatColor.BLUE + "���� �׸� : "+ChatColor.WHITE+"����","",ChatColor.YELLOW+"������ �����ϱ� ���ؼ���",ChatColor.YELLOW+"�Ʒ��� 3���� �������� ���� �ؾ� �մϴ�.",ChatColor.YELLOW+"[����, ������, ����]"), 47, inv);
		else if(Type==358)
			Stack2("��9��l[���� �׸�]", 358,0,1,Arrays.asList(ChatColor.BLUE + "���� �׸� : "+ChatColor.WHITE+"������","",ChatColor.YELLOW+"������ �����ϱ� ���ؼ���",ChatColor.YELLOW+"�Ʒ��� 3���� �������� ���� �ؾ� �մϴ�.",ChatColor.YELLOW+"[����, ������, ����]"), 47, inv);
		else if(Type==120)
			Stack2("��9��l[���� �׸�]", 120,0,1,Arrays.asList(ChatColor.BLUE + "���� �׸� : "+ChatColor.WHITE+"����","",ChatColor.YELLOW+"������ �����ϱ� ���ؼ���",ChatColor.YELLOW+"�Ʒ��� 3���� �������� ���� �ؾ� �մϴ�.",ChatColor.YELLOW+"[����, ������, ����]"), 47, inv);
		
		
		if(dungeonList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		if(Type==52)
			Stack2("��f��l���� ����", 383,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ������ �����մϴ�."), 49, inv);
		if(Type==358)
			Stack2("��f��l������ ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "���ο� �������� �����մϴ�."), 49, inv);
		if(Type==120)
			Stack2("��f��l���� �Ǽ�", 381,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ������ �����մϴ�.","","��c��l[������ ������ ������ �ٶ󺾴ϴ�.]"), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void DungeonSetUpGUI(Player player, String DungeonName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");

		String UniqueCode = "��0��0��a��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0���� ���� : " +DungeonName);
		Stack2("��f��l���� Ÿ��", dungeonYaml.getInt("Type.ID"),dungeonYaml.getInt("Type.DATA"),1,Arrays.asList(ChatColor.GRAY + "���� ���� Ÿ�� : "+dungeonYaml.getString("Type.Name")), 11, inv);
		
		Stack2("��f��l���� ũ��", 395,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� ũ�� : "+dungeonYaml.getInt("Size"),ChatColor.DARK_GRAY + "�ּ� : 5",ChatColor.DARK_GRAY + "�ִ� : 30"), 13, inv);
		Stack2("��f��l���� ����", 53,0,1,Arrays.asList(ChatColor.GRAY + "���� �̷� ���� : "+dungeonYaml.getInt("Maze_Level"),"",ChatColor.YELLOW+"[���� �޴� �׸�]",ChatColor.YELLOW+" - ������ ���� ��",ChatColor.YELLOW+" - ���� ������ ����",ChatColor.YELLOW+" - ���� ������"), 15, inv);
		
		Stack2("��f��l���� ����", 101,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� ������ �����մϴ�.",ChatColor.RED+"���� ���� : " + ChatColor.GRAY+dungeonYaml.getInt("District.Level"),ChatColor.RED+"���� ���� ���� : " + ChatColor.GRAY+ dungeonYaml.getInt("District.RealLevel")), 20, inv);
		Stack2("��f��l���� ����", 266,0,1,Arrays.asList(ChatColor.GRAY + "���� �⺻ ������ �����մϴ�.",ChatColor.YELLOW+"���� �ݾ� : " + ChatColor.GRAY+dungeonYaml.getInt("Reward.Money"),ChatColor.AQUA+"���� ����ġ : " + ChatColor.GRAY+dungeonYaml.getInt("Reward.EXP")), 22, inv);
		Stack2("��f��l���� ���� ����", 54,0,1,Arrays.asList(ChatColor.GRAY + "���� �߰� ������ �����մϴ�."), 24, inv);
		Stack2("��f��l���� ����", 383,0,1,Arrays.asList(ChatColor.GRAY + "���� ���͸� �����մϴ�."), 29, inv);
		
		String lore = "";
		otherplugins.NoteBlockAPIMain NBAPI = new otherplugins.NoteBlockAPIMain();
		int tracknumber = dungeonYaml.getInt("BGM.Normal");
		lore = " %enter%"+ChatColor.GRAY + "���� BGM�� �����մϴ�.%enter% %enter%"+ChatColor.BLUE + "[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ tracknumber+"%enter%"
		+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ NBAPI.getTitle(tracknumber)+"%enter%"
		+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+NBAPI.getAuthor(tracknumber)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
		
		String Description = NBAPI.getDescription(tracknumber);
		String lore2="";
		int a = 0;
		for(int count = 0; count <Description.toCharArray().length; count++)
		{
			lore2 = lore2+ChatColor.BLUE+Description.toCharArray()[count];
			a=a+1;
			if(a >= 15)
			{
				a = 0;
				lore2 = lore2+"%enter%      ";
			}
		}
		lore = lore + lore2;
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� �����]", 2263,0,1,Arrays.asList(lore.split("%enter%")), 31, inv);

		lore = "";
		tracknumber = dungeonYaml.getInt("BGM.BOSS");
		lore = " %enter%"+ChatColor.GRAY + "���� BGM�� �����մϴ�.%enter% %enter%"+ChatColor.BLUE + "[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ tracknumber+"%enter%"
		+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ NBAPI.getTitle(tracknumber)+"%enter%"
		+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+NBAPI.getAuthor(tracknumber)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
		
		Description = NBAPI.getDescription(tracknumber);
		lore2="";
		a = 0;
		for(int count = 0; count <Description.toCharArray().length; count++)
		{
			lore2 = lore2+ChatColor.BLUE+Description.toCharArray()[count];
			a=a+1;
			if(a >= 15)
			{
				a = 0;
				lore2 = lore2+"%enter%      ";
			}
		}
		lore = lore + lore2;
		Stack2(ChatColor.WHITE + ""+ChatColor.BOLD+"[���� �����]", 2259,0,1,Arrays.asList(lore.split("%enter%")), 33, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 44, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 36, inv);

		player.openInventory(inv);
	}

	public void DungeonChestReward(Player player, String DungeonName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Reward.yml");

		String UniqueCode = "��1��0��a��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� : " +DungeonName);
	
		Stack2("��9��l[100% Ȯ��]", 160,11,1,Arrays.asList("",ChatColor.WHITE+"100% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"100% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 0, inv);
		Stack2("��a��l[90% Ȯ��]", 160,5,1,Arrays.asList("",ChatColor.WHITE+"90% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"90% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 9, inv);
		Stack2("��e��l[50% Ȯ��]", 160,4,1,Arrays.asList("",ChatColor.WHITE+"50% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"50% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 18, inv);
		Stack2("��6��l[10% Ȯ��]", 160,1,1,Arrays.asList("",ChatColor.WHITE+"10% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"10% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 27, inv);
		Stack2("��4��l[1% Ȯ��]", 160,14,1,Arrays.asList("",ChatColor.WHITE+"1% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"1% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 36, inv);
		Stack2("��8��l[0.1% Ȯ��]", 160,10,1,Arrays.asList("",ChatColor.WHITE+"0.1% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"0.1% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 45, inv);

		for(int count = 0; count < 8; count++)
		{
			if(dungeonYaml.getItemStack("100."+count)!=null)
				ItemStackStack(dungeonYaml.getItemStack("100."+count), count+1, inv);
			if(dungeonYaml.getItemStack("90."+count)!=null)
				ItemStackStack(dungeonYaml.getItemStack("90."+count), count+10, inv);
			if(dungeonYaml.getItemStack("50."+count)!=null)
				ItemStackStack(dungeonYaml.getItemStack("50."+count), count+19, inv);
			if(dungeonYaml.getItemStack("10."+count)!=null)
				ItemStackStack(dungeonYaml.getItemStack("10."+count), count+28, inv);
			if(dungeonYaml.getItemStack("1."+count)!=null)
				ItemStackStack(dungeonYaml.getItemStack("1."+count), count+37, inv);
			if(dungeonYaml.getItemStack("0."+count)!=null)
				ItemStackStack(dungeonYaml.getItemStack("0."+count), count+46, inv);
		}
		player.openInventory(inv);
	}
	
	public void DungeonMonsterGUIMain(Player player, String DungeonName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");

		String UniqueCode = "��0��0��a��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� : " +DungeonName);

		Stack2("��4��l[BOSS]", 160,14,1,Arrays.asList("",ChatColor.WHITE+"�����濡�� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 0, inv);
		Stack2("��6��l[�� ����]", 160,5,1,Arrays.asList("",ChatColor.WHITE+"������ �տ��� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 9, inv);
		Stack2("��e��l[��� ����]", 160,4,1,Arrays.asList("",ChatColor.WHITE+"�Ϲ� �濡�� ���� �ſ� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 18, inv);
		Stack2("��a��l[�߱� ����]", 160,5,1,Arrays.asList("",ChatColor.WHITE+"�Ϲ� �濡�� ���� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 27, inv);
		Stack2("��9��l[�ϱ� ����]", 160,11,1,Arrays.asList("",ChatColor.WHITE+"�Ϲ� �濡�� ���� �Ϲ� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 36, inv);

		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);

		String Type = "Boss";
		for(int count2 = 0; count2 < 5; count2 ++)
		{
			switch(count2)
			{
			case 0:
				Type = "Boss";	break;
			case 1:
				Type = "SubBoss";	break;
			case 2:
				Type = "High";	break;
			case 3:
				Type = "Middle";	break;
			case 4:
				Type = "Normal";	break;
			}
			
			for(int count = 0; count < 8; count ++)
			{
				if(dungeonYaml.getString(Type+"."+count)==null)
					Stack2("��f��l[����]", 383, 0, 1,Arrays.asList("",ChatColor.YELLOW + "[�� Ŭ���� ���]"), count+1+(count2*9), inv);
				else
				{
					switch(dungeonYaml.getString(Type+"."+count))
					{
					case "������":
						Stack2("��2��l[�Ϲ� ����]", 397, 2, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ｺ�̷���":
						Stack2("��7��l[�Ϲ� ���̷���]", 397, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "��ũ����":
						Stack2("��a��l[�Ϲ� ũ����]", 397, 4, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "��Ź�":
						Stack2("��7��l[�Ϲ� �Ź�]", 287, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ﵿ���Ź�":
						Stack2("��7��l[�Ϲ� ���� �Ź�]", 375, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�￣����":
						Stack2("��8��l[�Ϲ� ������]", 368, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ｽ����":
						Stack2("��a��l[�Ϲ� ������]", 341, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�︶�׸�ť��":
						Stack2("��e��l[�Ϲ� ���׸� ť��]", 378, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�︶��":
						Stack2("��8��l[�Ϲ� ����]", 438, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������Ǳ׸�":
						Stack2("��d��l[�Ϲ� ���� �Ǳ׸�]", 283, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������":
						Stack2("��e��l[�Ϲ� ������]", 369, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ﰡ��Ʈ":
						Stack2("��f��l[�Ϲ� ����Ʈ]", 370, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "�ﰡ���":
						Stack2("��3��l[�Ϲ� ��ȣ��]", 410, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "�����":
						Stack2("��7��l[�Ϲ� ����]", 1, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "�����":
						Stack2("��d��l[�Ϲ� ����]", 319, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���":
						Stack2("��f��l[�Ϲ� ��]", 423, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���":
						Stack2("��7��l[�Ϲ� ��]", 363, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���":
						Stack2("��f��l[�Ϲ� ��]", 365, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���¡��":
						Stack2("��7��l[�Ϲ� ��¡��]", 351, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�����":
						Stack2("��f��l[�Ϲ� ����]", 352, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������":
						Stack2("��c��l[�Ϲ� ������]", 40, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������":
						Stack2("��e��l[�Ϲ� ������]", 349, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�︻":
						Stack2("��6��l[�Ϲ� ��]", 418, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "���䳢":
						Stack2("��6��l[�Ϲ� �䳢]", 411, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "���ֹ�":
						Stack2("��6��l[�Ϲ� �ֹ�]", 388, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "��ϱذ�":
						Stack2("��f��l[�Ϲ� �ϱذ�]", 80, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					default:
						YamlLoader monsterYaml = new YamlLoader();
						monsterYaml.getConfig("Monster/MonsterList.yml");
						String mobName = dungeonYaml.getString(Type+"."+count);
						boolean isExit = false;

						List<String> lore = new ArrayList<String>();
						String[] monsterList = monsterYaml.getKeys().toArray(new String[0]);
						for(int count3=0;count3 < monsterList.length;count3++)
						{
							if(monsterList[count3].compareTo(mobName)==0)
							{
								lore.clear();
								lore.add("");
								lore.add("��f��l �̸� : "+ChatColor.WHITE+monsterYaml.getString(mobName+".Name"));
								lore.add("��f��l Ÿ�� : "+ChatColor.WHITE+monsterYaml.getString(mobName+".Type"));
								lore.add("��f��l ���� ���̿� : "+ChatColor.WHITE+monsterYaml.getString(mobName+".Biome"));
								lore.add("��c��l ����� : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".HP"));
								lore.add("��b��l ����ġ : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".EXP"));
								lore.add("��e��l ��� �ݾ� : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".MIN_Money")+" ~ "+monsterYaml.getInt(mobName+".MAX_Money"));
								lore.add("��c��l "+main.Main_ServerOption.STR+" : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".STR")+ChatColor.GRAY+ " [���� : " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(mobName+".STR"), true) + " ~ " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(mobName+".STR"), false) + "]");
								lore.add("��a��l "+main.Main_ServerOption.DEX+" : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".DEX")+ChatColor.GRAY+ " [Ȱ�� : " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(mobName+".DEX"), 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(mobName+".DEX"), 0, false) + "]");
								lore.add("��3��l "+main.Main_ServerOption.INT+" : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".INT")+ChatColor.GRAY+ " [���� : " + (monsterYaml.getInt(mobName+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(mobName+".INT")/2.5)+"]");
								lore.add("��7��l "+main.Main_ServerOption.WILL+" : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".WILL")+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(mobName+".LUK"), (int)monsterYaml.getInt(mobName+".WILL"),0) + " %]");
								lore.add("��e��l "+main.Main_ServerOption.LUK+" : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".LUK")+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(mobName+".LUK"), (int)monsterYaml.getInt(mobName+".WILL"),0) + " %]");
								lore.add("��7��l ��� : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".DEF"));
								lore.add("��b��l ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".Protect"));
								lore.add("��9��l ���� ��� : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".Magic_DEF"));
								lore.add("��1��l ���� ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(mobName+".Magic_Protect"));
								lore.add("");
								lore.add("��e��l[�� Ŭ���� ����]");
								
								int id = 383;
								int data = 0;
								switch(monsterYaml.getString(mobName+".Type"))
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
								Stack(ChatColor.WHITE+mobName, id, data, 1, lore, count+1+(count2*9), inv);
								ItemMeta a = inv.getItem(count+1+(count2*9)).getItemMeta();
								a.addEnchant(Enchantment.SILK_TOUCH, 3, true);
								inv.getItem(count+1+(count2*9)).setItemMeta(a);
								isExit = true;
								break;
							}
						}
						if(isExit==false)
						{
							dungeonYaml.set(Type+"."+count, null);
							dungeonYaml.saveConfig();
							Stack2("��f��l[����]", 383, 0, 1,Arrays.asList("",ChatColor.YELLOW + "[�� Ŭ���� ���]"), count+1+(count2*9), inv);
						}
					}
				}
			}
		}
		player.openInventory(inv);
	}
	
	public void DungeonMonsterChooseMain(Player player, String DungeonName, int slot)
	{
		String UniqueCode = "��0��0��a��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ���� : " +DungeonName);
		Stack2("��c��l[����]", 166,0,1,Arrays.asList(ChatColor.GRAY + "���� ������ ���� �ʽ��ϴ�."), 2, inv);
		Stack2("��f��l[�Ϲ�]", 383,0,1,Arrays.asList(ChatColor.GRAY + "�Ϲ����� ���� �� �ϳ��� ���ϴ�."), 4, inv);
		Stack2("��b��l[Ŀ����]", 52,0,1,Arrays.asList(ChatColor.GRAY + "Ŀ���� ���� �� �ϳ��� ���ϴ�.","",ChatColor.RED+"[���� ũ����Ż ������ ���͸�",ChatColor.RED+"������ ���, ������ ������ �˴ϴ�.]"), 6, inv);
		
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+slot), 8, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);
		player.openInventory(inv);
	}
	
	public void DungeonSelectNormalMonsterChoose(Player player, String DungeonName, String Type, int slot)
	{
		String UniqueCode = "��0��0��a��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�Ϲ� ���� : " +DungeonName);

		Stack2("��2��l[����]",397,2,1,null, 0, inv);
		Stack2("��7��l[���̷���]",397,0,1,null, 1, inv);
		Stack2("��a��l[ũ����]",397,4,1,null, 2, inv);
		Stack2("��7��l[�Ź�]",287,0,1,null, 3, inv);
		Stack2("��7��l[���� �Ź�]",375,0,1,null, 4, inv);
		Stack2("��8��l[������]",368,0,1,null, 5, inv);
		Stack2("��a��l[������]",341,0,1,null, 6, inv);
		Stack2("��e��l[���׸� ť��]",378,0,1,null, 7, inv);
		Stack2("��8��l[����]",438,0,1,null, 8, inv);
		Stack2("��d��l[���� �Ǳ׸�]",283,0,1,null, 9, inv);
		Stack2("��e��l[������]",369,0,1,null, 10, inv);
		Stack2("��f��l[����Ʈ]",370,0,1,null, 11, inv);
		Stack2("��3��l[��ȣ��]",410,0,1,null, 12, inv);
		Stack2("��7��l[����]",1,0,1,null, 13, inv);
		Stack2("��d��l[����]",319,0,1,null, 14, inv);
		Stack2("��f��l[��]",423,0,1,null, 15, inv);
		Stack2("��7��l[��]",363,0,1,null, 16, inv);
		Stack2("��f��l[��]",365,0,1,null, 17, inv);
		Stack2("��7��l[��¡��]",351,0,1,null, 18, inv);
		Stack2("��f��l[����]",352,0,1,null, 19, inv);
		Stack2("��f��l[���� ��]",40,0,1,null, 20, inv);
		Stack2("��f��l[������]",349,0,1,null, 21, inv);
		Stack2("��f��l[��]",418,0,1,null, 22, inv);
		Stack2("��f��l[�䳢]",411,0,1,null, 23, inv);
		Stack2("��f��l[�ֹ�]",388,0,1,null, 24, inv);
		Stack2("��f��l[�ϱذ�]",80,0,1,null, 25, inv);

		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+slot), 53, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+Type), 45, inv);
		player.openInventory(inv);
	}
	
	public void DungeonSelectCustomMonsterChoose(Player player, String DungeonName, String Type, int slot, int page)
	{
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		String UniqueCode = "��0��0��a��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0Ŀ���� ���� : " + (page+1));

		String[] monsterList= monsterYaml.getKeys().toArray(new String[0]);

		int loc=0;
		List<String> lore = new ArrayList<String>();
		for(int count = page*45; count < monsterList.length;count++)
		{
			if(count > monsterList.length || loc >= 45) break;
			lore.clear();
			lore.add("");
			lore.add("��f��l �̸� : "+ChatColor.WHITE+monsterYaml.getString(monsterList[count]+".Name"));
			lore.add("��f��l Ÿ�� : "+ChatColor.WHITE+monsterYaml.getString(monsterList[count]+".Type"));
			lore.add("��f��l ���� ���̿� : "+ChatColor.WHITE+monsterYaml.getString(monsterList[count]+".Biome"));
			lore.add("��c��l ����� : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".HP"));
			lore.add("��b��l ����ġ : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".EXP"));
			lore.add("��e��l ��� �ݾ� : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".MIN_Money")+" ~ "+monsterYaml.getInt(monsterList[count]+".MAX_Money"));
			lore.add("��c��l "+main.Main_ServerOption.STR+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".STR")+ChatColor.GRAY+ " [���� : " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(monsterList[count]+".STR"), true) + " ~ " + Battle_Calculator.CombatDamageGet(null, 0, monsterYaml.getInt(monsterList[count]+".STR"), false) + "]");
			lore.add("��a��l "+main.Main_ServerOption.DEX+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".DEX")+ChatColor.GRAY+ " [Ȱ�� : " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterList[count]+".DEX"), 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterList[count]+".DEX"), 0, false) + "]");
			lore.add("��3��l "+main.Main_ServerOption.INT+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".INT")+ChatColor.GRAY+ " [���� : " + (monsterYaml.getInt(monsterList[count]+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(monsterList[count]+".INT")/2.5)+"]");
			lore.add("��7��l "+main.Main_ServerOption.WILL+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".WILL")+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(monsterList[count]+".LUK"), (int)monsterYaml.getInt(monsterList[count]+".WILL"),0) + " %]");
			lore.add("��e��l "+main.Main_ServerOption.LUK+" : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".LUK")+ChatColor.GRAY+ " [ũ�� : " + Battle_Calculator.getCritical(null,monsterYaml.getInt(monsterList[count]+".LUK"), (int)monsterYaml.getInt(monsterList[count]+".WILL"),0) + " %]");
			lore.add("��7��l ��� : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".DEF"));
			lore.add("��b��l ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".Protect"));
			lore.add("��9��l ���� ��� : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".Magic_DEF"));
			lore.add("��1��l ���� ��ȣ : "+ChatColor.WHITE+monsterYaml.getInt(monsterList[count]+".Magic_Protect"));

			int id = 383;
			int data = 0;
			switch(monsterYaml.getString(monsterList[count]+".Type"))
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
			
			Stack(ChatColor.WHITE+monsterList[count], id, data, 1,lore, loc, inv);
			loc=loc+1;
		}
		
		if(monsterList.length-(page*44)>45)
		Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+Type), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+slot,ChatColor.BLACK+DungeonName), 53, inv);
		player.openInventory(inv);
	}

	public void DungeonMusicSettingGUI(Player player, int page,String DungeonName, boolean isBOSS)
	{
		String UniqueCode = "��0��0��a��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����� : " + (page+1));
		int loc=0;
		int model = new util.Util_Number().RandomNum(0, 11);
		for(int count = page*45; count < otherplugins.NoteBlockAPIMain.Musics.size();count++)
		{
			if(model<11)
				model=model+1;
			else
				model=0;
			otherplugins.NoteBlockAPIMain NBAPI = new otherplugins.NoteBlockAPIMain();
			String lore = " %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ count+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ NBAPI.getTitle(count)+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+NBAPI.getAuthor(count)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
			String Description = NBAPI.getDescription(count);
			String lore2="";
			int a = 0;
			for(int counter = 0; counter <Description.toCharArray().length; counter++)
			{
				lore2 = lore2+ChatColor.BLUE+Description.toCharArray()[counter];
				a=a+1;
				if(a >= 15)
				{
					a = 0;
					lore2 = lore2+"%enter%      ";
				}
			}
			lore = lore + lore2+"%enter% %enter%"+ChatColor.YELLOW+"[�� Ŭ���� ����� ����]";
			if(count > otherplugins.NoteBlockAPIMain.Musics.size() || loc >= 45) break;
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + count, 2256+model,0,1,Arrays.asList(lore.split("%enter%")), loc, inv);
			
			loc=loc+1;
		}
		
		if(otherplugins.NoteBlockAPIMain.Musics.size()-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+""+isBOSS), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+DungeonName), 53, inv);
		player.openInventory(inv);
	}
	//DungeonGUI//
	
	
	//EnterCardGUI//
	public void EnterCardSetUpGUI(Player player, String EnterCardName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/EnterCardList.yml");

		String UniqueCode = "��0��0��a��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0������ ����");
		if(dungeonYaml.getString(EnterCardName+".Dungeon")!= null)
			Stack2("��f��l[������ ���� ����]", 52,0,1,Arrays.asList("",ChatColor.BLUE + "���� �̾��� ���� : "+ChatColor.WHITE+dungeonYaml.getString(EnterCardName+".Dungeon")), 2, inv);
		else
			Stack2("��f��l[������ ���� ����]", 166,0,1,Arrays.asList("",ChatColor.BLUE + "���� �̾��� ���� : "+ChatColor.WHITE+"����"), 2, inv);
			
		Stack2("��f��l[������ ���� ����]", dungeonYaml.getInt(EnterCardName+".ID"),dungeonYaml.getInt(EnterCardName+".DATA"),1,Arrays.asList("",ChatColor.BLUE + "���� ������ Ÿ�� : "+ChatColor.WHITE+dungeonYaml.getInt(EnterCardName+".ID")+":"+ dungeonYaml.getInt(EnterCardName+".DATA"),"",ChatColor.YELLOW+"[F3 + H �Է½� Ÿ�� Ȯ�� ����]"), 3, inv);
		Stack2("��f��l[������ ���� �ʱ�ȭ]", 325,0,1,Arrays.asList("",ChatColor.WHITE + "������ ���°� ��Ÿ���� ���� ��",ChatColor.WHITE+"������ �ʱ�ȭ �� �ݴϴ�."), 4, inv);
		Stack2("��f��l[������ ���� �ο�]", 397,3,1,Arrays.asList("",ChatColor.BLUE + "���� ���� �ο� : "+ChatColor.WHITE+dungeonYaml.getInt(EnterCardName+".Capacity")+" ��"), 5, inv);

		String Time = null;
		if(dungeonYaml.getInt(EnterCardName.toString()+".Hour")!=-1)
		{
			if(dungeonYaml.getInt(EnterCardName.toString()+".Hour")!=0)
				Time = dungeonYaml.getInt(EnterCardName.toString()+".Hour")+"�ð� ";
			if(dungeonYaml.getInt(EnterCardName.toString()+".Min")!=0)
				Time = Time+dungeonYaml.getInt(EnterCardName.toString()+".Min")+"�� ";
			Time = Time+dungeonYaml.getInt(EnterCardName.toString()+".Sec")+"��";
		}
		else
			Time = "������";
		Stack2("��f��l[������ ��ȿ �ð�]", 347,0,1,Arrays.asList("",ChatColor.WHITE+Time), 6, inv);

		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+EnterCardName), 8, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);

		player.openInventory(inv);
	}

	public void EnterCardDungeonSettingGUI(Player player, int page, String EnterCardName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/DungeonList.yml");

		String UniqueCode = "��0��0��a��0��9��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ���� : " + (page+1));
		String[] DungeonList = dungeonYaml.getKeys().toArray(new String[0]);
		
		int loc=0;
		for(int count = page*45; count < DungeonList.length;count++)
		{
			YamlLoader DungeonOptionYML = new YamlLoader();
			DungeonOptionYML.getConfig("Dungeon/Dungeon/"+DungeonList[count]+"/Option.yml");
			
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count], 52,0,1,Arrays.asList(
			"",ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getString("Type.Name")
			,ChatColor.BLUE+"���� ũ�� : "+ChatColor.WHITE+DungeonOptionYML.getInt("Size")
			,ChatColor.BLUE+"���� ������ : "+ChatColor.WHITE+DungeonOptionYML.getInt("Maze_Level")
			,ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.Level")+" �̻�"
			,ChatColor.BLUE+"���� ���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.RealLevel")+" �̻�"
			,"",ChatColor.BLUE+"[�⺻ Ŭ���� ����]"
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.Money")+" "+main.Main_ServerOption.Money
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.EXP")+"��b��lEXP"
			,"",ChatColor.YELLOW+"[�� Ŭ���� ����]"), loc, inv);
			
			loc=loc+1;
		}
		
		if(DungeonList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+EnterCardName), 53, inv);
		player.openInventory(inv);
		return;
	}
	//EnterCardGUI//

	//AltarGUI//
	public void AltarShapeListGUI(Player player)
	{
		String UniqueCode = "��0��0��a��0��a��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����");
		Stack2("��2��l[�̳� �� ����]", 48,0,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"13��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"GoldBigDragon [���]"), 0, inv);
		Stack2("��e��l[����ף]", 41,0,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"15��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"ComputerFairy [����]",ChatColor.WHITE+"GoldBigDragon [��]"), 1, inv);
		Stack2("��7��l[���� ����]", 1,0,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"1�� 5��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"GoldBigDragon [���]"), 2, inv);
		Stack2("��7��l[�غδ�]", 1,5,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"8��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"GoldBigDragon [���]"), 3, inv);
		Stack2("��7��l[�׽�Ʈ�� ����]", 48,0,1,null, 44, inv);
		
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}

	public void AltarSettingGUI(Player player, String AltarName)
	{
		String UniqueCode = "��0��0��a��0��b��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ����");

	  	YamlLoader AltarList = new YamlLoader();
		AltarList.getConfig("Dungeon/AltarList.yml");
	  	YamlLoader AltarConfig = new YamlLoader();
		AltarConfig.getConfig("Dungeon/Altar/"+AltarName+".yml");
		Stack2("��f��l[�̸� ����]", 421,0,1,Arrays.asList(ChatColor.GRAY+"������ �̸��� �����մϴ�.","",ChatColor.BLUE+"[���� ���� �̸�]",ChatColor.WHITE+AltarList.getString(AltarName+".Name")), 2, inv);
		if(AltarConfig.getString("NormalDungeon")==null)
			Stack2("��f��l[�Ϲ� ����]", 166,0,1,Arrays.asList(ChatColor.GRAY+"�� ���ܿ� �������� ������",ChatColor.GRAY+"�������� ������ ��� �����Ǵ�",ChatColor.GRAY+"�Ϲ� ������ �����մϴ�.","",ChatColor.BLUE+"[���� ������ �Ϲ� ����]",ChatColor.WHITE+"�������� ����"), 4, inv);
		else
		{
		  	YamlLoader DungeonList = new YamlLoader();
			DungeonList.getConfig("Dungeon/DungeonList.yml");
			if(DungeonList.contains(AltarConfig.getString("NormalDungeon")))
				Stack2("��f��l[�Ϲ� ����]", 52,0,1,Arrays.asList(ChatColor.GRAY+"���ܿ� �������� ������",ChatColor.GRAY+"�������� ������ ��� �����Ǵ�",ChatColor.GRAY+"�Ϲ� ������ �����մϴ�.","",ChatColor.BLUE+"[���� ������ �Ϲ� ����]",ChatColor.WHITE+AltarConfig.getString("NormalDungeon")), 4, inv);
			else
			{
				AltarConfig.set("NormalDungeon", null);
				AltarConfig.saveConfig();
				Stack2("��f��l[�Ϲ� ����]", 166,0,1,Arrays.asList(ChatColor.GRAY+"���ܿ� �������� ������",ChatColor.GRAY+"�������� ������ ��� �����Ǵ�",ChatColor.GRAY+"�Ϲ� ������ �����մϴ�.","",ChatColor.BLUE+"[���� ������ �Ϲ� ����]",ChatColor.WHITE+"�������� ����"), 4, inv);
			}
		}
		Stack2("��f��l[������ ���]", 358,0,1,Arrays.asList(ChatColor.GRAY+"���ܿ��� ��� ������",ChatColor.GRAY+"�������� ����մϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), 6, inv);

		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+AltarName), 8, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);

		player.openInventory(inv);
		return;
	}
	
	public void AltarDungeonSettingGUI(Player player, int page, String AltarName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/DungeonList.yml");

		String UniqueCode = "��0��0��a��0��c��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� : " + (page+1));
		String[] DungeonList = dungeonYaml.getKeys().toArray(new String[0]);
		
		int loc=0;
		YamlLoader DungeonOptionYML = new YamlLoader();
		for(int count = page*45; count < DungeonList.length;count++)
		{
			DungeonOptionYML.getConfig("Dungeon/Dungeon/"+DungeonList[count]+"/Option.yml");
			
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count], 52,0,1,Arrays.asList(
			"",ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getString("Type.Name")
			,ChatColor.BLUE+"���� ũ�� : "+ChatColor.WHITE+DungeonOptionYML.getInt("Size")
			,ChatColor.BLUE+"���� ������ : "+ChatColor.WHITE+DungeonOptionYML.getInt("Maze_Level")
			,ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.Level")+" �̻�"
			,ChatColor.BLUE+"���� ���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.RealLevel")+" �̻�"
			,"",ChatColor.BLUE+"[�⺻ Ŭ���� ����]"
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.Money")+" "+main.Main_ServerOption.Money
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.EXP")+"��b��lEXP"
			,"",ChatColor.YELLOW+"[�� Ŭ���� ����]"), loc, inv);
			
			loc=loc+1;
		}
		
		if(DungeonList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",AltarName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AltarEnterCardSettingGUI(Player player, int page, String AltarName)
	{
	  	YamlLoader AlterConfig = new YamlLoader();
		AlterConfig.getConfig("Dungeon/Altar/"+AltarName+".yml");

		String UniqueCode = "��0��0��a��0��d��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��ϵ� ������ : " + (page+1));

		if(AlterConfig.getConfigurationSection("EnterCard").getKeys(false).size()!=0)
		{
			String[] EnterCardList = AlterConfig.getConfigurationSection("EnterCard").getKeys(false).toArray(new String[0]);

			int loc=0;
		  	YamlLoader EnterCard = new YamlLoader();
		  	YamlLoader Dungeon = new YamlLoader();
			for(int count = page*45; count < EnterCardList.length;count++)
			{
				EnterCard.getConfig("Dungeon/EnterCardList.yml");
				if(EnterCard.contains(EnterCardList[count]))
				{
					if(EnterCard.getString(EnterCardList[count]+".Dungeon")==null)
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + EnterCardList[count], EnterCard.getInt(EnterCardList[count]+".ID"),EnterCard.getInt(EnterCardList[count]+".DATA"),1,Arrays.asList(
								"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
								ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+EnterCard.getInt(EnterCardList[count]+".Capacity"),
								"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
					else
					{
						Dungeon.getConfig("Dungeon/DungeonList.yml");
						if(Dungeon.contains(EnterCard.getString(EnterCardList[count]+".Dungeon")))
						{
							Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + EnterCardList[count], EnterCard.getInt(EnterCardList[count]+".ID"),EnterCard.getInt(EnterCardList[count]+".DATA"),1,Arrays.asList(
							"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+""+EnterCard.getString(EnterCardList[count]+".Dungeon"),
							ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+EnterCard.getInt(EnterCardList[count]+".Capacity"),
							"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
						}
						else
						{
							EnterCard.set(EnterCardList[count]+".Dungeon",null);
							EnterCard.saveConfig();
							Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + EnterCardList[count], EnterCard.getInt(EnterCardList[count]+".ID"),EnterCard.getInt(EnterCardList[count]+".DATA"),1,Arrays.asList(
									"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
									ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+EnterCard.getInt(EnterCardList[count]+".Capacity"),
									"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
						}
							
					}
					loc=loc+1;
				}
				else
				{
					AlterConfig.removeKey("EnterCard."+EnterCardList[count]);
					AlterConfig.saveConfig();
				}
			}
			if(EnterCardList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		}
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l������ ���", 386,0,1,Arrays.asList(ChatColor.GRAY + "���ܿ� �������� ����մϴ�."), 49, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",AltarName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AltarEnterCardListGUI(Player player, int page, String AltarName)
	{
	  	YamlLoader dungeonYaml = new YamlLoader();

		String UniqueCode = "��0��0��a��0��e��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ������ ��� : " + (page+1));
		
		dungeonYaml.getConfig("Dungeon/EnterCardList.yml");
		String[] DungeonList = dungeonYaml.getKeys().toArray(new String[0]);
		int loc=0;
	  	YamlLoader Dungeon = new YamlLoader();
		for(int count = page*45; count < DungeonList.length;count++)
		{
			if(dungeonYaml.getString(DungeonList[count]+".Dungeon")==null)
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count], dungeonYaml.getInt(DungeonList[count]+".ID"),dungeonYaml.getInt(DungeonList[count]+".DATA"),1,Arrays.asList(
				"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
				ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+dungeonYaml.getInt(DungeonList[count]+".Capacity"),
				"",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), loc, inv);
			else
			{
				Dungeon.getConfig("Dungeon/DungeonList.yml");
				if(Dungeon.contains(dungeonYaml.getString(DungeonList[count]+".Dungeon")))
				{
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count], dungeonYaml.getInt(DungeonList[count]+".ID"),dungeonYaml.getInt(DungeonList[count]+".DATA"),1,Arrays.asList(
					"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+""+dungeonYaml.getString(DungeonList[count]+".Dungeon"),
					ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+dungeonYaml.getInt(DungeonList[count]+".Capacity"),
					"",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), loc, inv);
				}
				else
				{
					dungeonYaml.set(DungeonList[count]+".Dungeon",null);
					dungeonYaml.saveConfig();
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count], dungeonYaml.getInt(DungeonList[count]+".ID"),dungeonYaml.getInt(DungeonList[count]+".DATA"),1,Arrays.asList(
					"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
					ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+dungeonYaml.getInt(DungeonList[count]+".Capacity"),
					"",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), loc, inv);
				}
			}
			loc=loc+1;
		}
		
		if(DungeonList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",AltarName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AltarUseGUI(Player player, String AltarName)
	{
		String UniqueCode = "��1��0��a��0��f��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���ܿ� ������ ��ġ�� �������� �̵��մϴ�");

		Stack2(AltarName, 160,0,1,null, 0, inv);
		Stack2(AltarName, 160,0,1,null, 1, inv);
		Stack2(AltarName, 160,0,1,null, 2, inv);
		Stack2(AltarName, 160,0,1,null, 3, inv);
		Stack2(AltarName, 160,0,1,null, 5, inv);
		Stack2(AltarName, 160,0,1,null, 6, inv);
		Stack2(AltarName, 160,0,1,null, 7, inv);
		Stack2(AltarName, 160,0,1,null, 8, inv);
		player.openInventory(inv);
		return;
	}
	//AltarGUI//
	
	public void DungeonEXIT(Player player)
	{
		String UniqueCode = "��0��0��a��1��0��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0�������� �����ðڽ��ϱ�?");

		Stack2("��c��l[���� �ܷ�]", 166,0,1,null, 3, inv);
		Stack2("��9��l[���� ����]", 138,0,1,null, 5, inv);
		player.openInventory(inv);
		return;
	}
	
	
	

	//DungeonGUI Click//
	public void DungeonListMainGUIClick(InventoryClickEvent event)
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
			int Type = event.getInventory().getItem(47).getTypeId();
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			
			if(slot == 45)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player, (byte) 3);
			else if(slot == 47)//Ÿ�� ����
			{
				if(event.isLeftClick())
				{
					if(Type == 52)
						DungeonListMainGUI(player, 0,358);
					else if(Type == 358)
						DungeonListMainGUI(player, 0,120);
					else if(Type == 120)
						DungeonListMainGUI(player, 0,52);
				}
				else
				{
					if(Type == 52)
						DungeonListMainGUI(player, 0,120);
					else if(Type == 358)
						DungeonListMainGUI(player, 0,52);
					else if(Type == 120)
						DungeonListMainGUI(player, 0,358);
				}
			}
			else if(slot == 48)//���� ������
				DungeonListMainGUI(player, page-1,Type);
			else if(slot == 49)//�� ����
			{
				if(main.Main_ServerOption.DungeonTheme.isEmpty())
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[SYSTEM] : ���� ������ ���� �׸��� ã�� �� �����ϴ�!");
					player.sendMessage(ChatColor.YELLOW+"(���� �׸� �ٿ�ε� : "+ChatColor.GOLD+"http://cafe.naver.com/goldbigdragon/56713"+ChatColor.YELLOW+")");
					return;
				}
				UserData_Object u = new UserData_Object();
				u.setTemp(player, "Dungeon");
				player.closeInventory();
				if(Type == 52)
				{
					u.setType(player, "DungeonMain");
					u.setString(player, (byte)0, "ND");//NewDungeon
					player.sendMessage(ChatColor.GREEN+"[����] : ���ο� ���� �̸��� �Է� �� �ּ���!");
				}
				else if(Type == 358)
				{
					u.setType(player, "EnterCard");
					u.setString(player, (byte)0, "NEC");//NewEnterCard
					player.sendMessage(ChatColor.GREEN+"[����] : ���ο� ������ �̸��� �Է� �� �ּ���!");
				}
				else if(Type == 120)
				{
					u.setType(player, "Altar");
					u.setString(player, (byte)0, "NA_Q");//NewAlter_Question
					player.sendMessage(ChatColor.GREEN+"[����] : ���� �� �ִ� ��ġ�� ������ ����ðڽ��ϱ�? (��/�ƴϿ�)");
				}
			}
			else if(slot == 50)//���� ������
				DungeonListMainGUI(player, page+1,Type);
			else
			{
				if(event.getCurrentItem().hasItemMeta()==false)
					return;
				if(Type == 52)
				{
					String DungeonName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				  	YamlLoader dungeonYaml = new YamlLoader();
					if(event.isLeftClick() == true)
						DungeonSetUpGUI(player, DungeonName);
					else if(event.isShiftClick() == true && event.isRightClick() == true)
					{
						SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
						dungeonYaml.getConfig("Dungeon/DungeonList.yml");
						dungeonYaml.removeKey(DungeonName);
						dungeonYaml.saveConfig();
						File file = new File("plugins/GoldBigDragonRPG/Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
						file.delete();
						file = new File("plugins/GoldBigDragonRPG/Dungeon/Dungeon/"+DungeonName+"/Option.yml");
						file.delete();
						file = new File("plugins/GoldBigDragonRPG/Dungeon/Dungeon/"+DungeonName+"/Reward.yml");
						file.delete();
						file = new File("plugins/GoldBigDragonRPG/Dungeon/Dungeon/"+DungeonName);
						file.delete();
						DungeonListMainGUI(player, page,Type);
					}
					else if(event.isShiftClick()==false&&event.isRightClick())
					{
						dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
						new dungeon.Dungeon_Creater().CreateTestSeed(player, dungeonYaml.getInt("Size"), dungeonYaml.getInt("Maze_Level"), dungeonYaml.getString("Type.Name"));
					}
				}
				if(Type == 358)
				{
					String DungeonName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					if(event.isLeftClick() == true&&event.isShiftClick()==false)
						EnterCardSetUpGUI(player, DungeonName);
					else if(event.isShiftClick()&&event.isRightClick())
					{
						SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					  	YamlLoader dungeonYaml = new YamlLoader();
						dungeonYaml.getConfig("Dungeon/EnterCardList.yml");
						dungeonYaml.removeKey(DungeonName);
						dungeonYaml.saveConfig();
						DungeonListMainGUI(player, page,Type);
					}
					else if(event.isShiftClick()&& event.isLeftClick())
					{
					  	YamlLoader dungeonYaml = new YamlLoader();
						dungeonYaml.getConfig("Dungeon/EnterCardList.yml");
						ItemStack Icon = new MaterialData(dungeonYaml.getInt(DungeonName+".ID"), (byte) dungeonYaml.getInt(DungeonName+".DATA")).toItemStack(1);
						ItemMeta Icon_Meta = Icon.getItemMeta();
						Icon_Meta.setDisplayName("��c��l[���� ������]");
						Calendar Today = Calendar.getInstance();
						String UseableTime = "[���� �ð� ����]";
						if(dungeonYaml.getInt(DungeonName+".Hour")!=-1)
						{
							Today.add(Calendar.MONTH, 1);
							Today.add(Calendar.HOUR, dungeonYaml.getInt(DungeonName+".Hour"));
							Today.add(Calendar.MINUTE, dungeonYaml.getInt(DungeonName+".Min"));
							Today.add(Calendar.SECOND, dungeonYaml.getInt(DungeonName+".Sec"));
							
							UseableTime = Today.get(Calendar.YEAR)+"�� "+Today.get(Calendar.MONTH)+"�� "+Today.get(Calendar.DATE)+"�� "+Today.get(Calendar.HOUR)+"�� "+Today.get(Calendar.MINUTE)+"�� "+Today.get(Calendar.SECOND)+"�� ����";
						}
						Icon_Meta.setLore(Arrays.asList("",ChatColor.RED+DungeonName,"",ChatColor.RED+"�ο� : "+ChatColor.WHITE+dungeonYaml.getInt(DungeonName+".Capacity"),"",ChatColor.WHITE+""+UseableTime));
						Icon.setItemMeta(Icon_Meta);
						player.getInventory().addItem(Icon);
					}
				}
				if(Type == 120)
				{
					String DungeonName = event.getCurrentItem().getItemMeta().getLore().get(event.getCurrentItem().getItemMeta().getLore().size()-1);
					if(event.isLeftClick() == true&&event.isShiftClick()==false)
						AltarSettingGUI(player, DungeonName);
					else if(event.isShiftClick() == true && event.isRightClick() == true)
					{
						SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					  	YamlLoader dungeonYaml = new YamlLoader();
						dungeonYaml.getConfig("Dungeon/AltarList.yml");
						Location loc = new Location(Bukkit.getServer().getWorld(dungeonYaml.getString(DungeonName+".World")), dungeonYaml.getInt(DungeonName+".X"), dungeonYaml.getInt(DungeonName+".Y"), dungeonYaml.getInt(DungeonName+".Z"));
						int radius = dungeonYaml.getInt(DungeonName+".radius");
						Object[] EntitiList = Bukkit.getServer().getWorld(dungeonYaml.getString(DungeonName+".World")).getNearbyEntities(loc, radius, radius, radius).toArray();
						for(int count=0; count<EntitiList.length;count++)
							if(((Entity)EntitiList[count]).getCustomName()!=null)
								if(((Entity)EntitiList[count]).getCustomName().compareTo(DungeonName)==0)
									((Entity)EntitiList[count]).remove();
						dungeonYaml.removeKey(DungeonName);
						dungeonYaml.saveConfig();
						File file = new File("plugins/GoldBigDragonRPG/Dungeon/Altar/"+DungeonName+".yml");
						file.delete();
						DungeonListMainGUI(player, page,Type);
					}
					else if(event.isShiftClick() == true && event.isLeftClick() == true)
					{
						SoundEffect.SP(player, Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
					  	YamlLoader dungeonYaml = new YamlLoader();
						dungeonYaml.getConfig("Dungeon/AltarList.yml");
						Location loc = new Location(Bukkit.getServer().getWorld(dungeonYaml.getString(DungeonName+".World")), dungeonYaml.getInt(DungeonName+".X"), dungeonYaml.getInt(DungeonName+".Y"), dungeonYaml.getInt(DungeonName+".Z"));
						player.teleport(loc);
						SoundEffect.SP(player, Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
					}
				}
			}
		}
	}
	
	public void DungeonSetUpGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 44)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);
			if(slot == 36)//���� ���
				DungeonListMainGUI(player, 0, 52);
			else if(slot == 11)//���� Ÿ��
			{
				if(main.Main_ServerOption.DungeonTheme.isEmpty())
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[SYSTEM] : ���� �׸��� ã�� �� �����ϴ�!");
					player.sendMessage(ChatColor.YELLOW+"(���� �׸� �ٿ�ε� : "+ChatColor.GOLD+"http://cafe.naver.com/goldbigdragon/56713"+ChatColor.YELLOW+")");
					return;
				}
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
				String DungeonTheme = dungeonYaml.getString("Type.Name");
				if(main.Main_ServerOption.DungeonTheme.contains(DungeonTheme)==false)
					dungeonYaml.set("Type.Name", main.Main_ServerOption.DungeonTheme.get(0));
				else
				{
					for(int count = 0; count < main.Main_ServerOption.DungeonTheme.size(); count++)
						if(main.Main_ServerOption.DungeonTheme.get(count).compareTo(DungeonTheme)==0)
						{
							if(count+1 >= main.Main_ServerOption.DungeonTheme.size())
								dungeonYaml.set("Type.Name", main.Main_ServerOption.DungeonTheme.get(0));
							else
								dungeonYaml.set("Type.Name", main.Main_ServerOption.DungeonTheme.get(count+1));
						}
				}
				dungeonYaml.saveConfig();
				DungeonSetUpGUI(player, DungeonName);
			}
			else if(slot == 24)//���� ����
			{
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				DungeonChestReward(player, DungeonName);
			}
			else if(slot == 29)//����
			{
				SoundEffect.SP(player, Sound.ENTITY_WOLF_AMBIENT, 0.8F, 1.0F);
				DungeonMonsterGUIMain(player, DungeonName);
			}
			else if(slot == 31)//����BGM ����
				DungeonMusicSettingGUI(player, 0, DungeonName, false);
			else if(slot == 33)//����BGM ����
				DungeonMusicSettingGUI(player, 0, DungeonName, true);
			else
			{
				UserData_Object u = new UserData_Object();
				u.setTemp(player, "Dungeon");
				u.setType(player, "DungeonMain");
				u.setString(player, (byte)1, DungeonName);
				player.closeInventory();
				if(slot == 13)//���� ũ��
				{
					u.setString(player, (byte)0, "DS");//DungeonSize
					player.sendMessage(ChatColor.GREEN+"[����] : ���� ũ�⸦ �Է� �� �ּ���! (�ּ� 5 �ִ� 50)");
				}
				else if(slot == 15)//�̷� ����
				{
					u.setString(player, (byte)0, "DML");//DungeonMazeLevel
					player.sendMessage(ChatColor.GREEN+"[����] : ���� �̷� ������ �Է� �� �ּ���! (�ּ� 0 �ִ� 10)");
					player.sendMessage(ChatColor.YELLOW+"[����] �̷� ������ �������� �������� ��ĥ���� ����!");
				}
				else if(slot == 20)//���� ����
				{
					u.setString(player, (byte)0, "DDL");//DungeonDistrictLevel
					player.sendMessage(ChatColor.GREEN+"[����] : ���� ���� ���� ������ �Է� �� �ּ���!");
				}
				else if(slot == 22)//��, ����ġ ���� ����
				{
					u.setString(player, (byte)0, "DRM");//DungeonRewardMoney
					player.sendMessage(ChatColor.GREEN+"[����] : ���� Ŭ���� ������� �Է� �� �ּ���!");
				}
			}
		}
	}

	public void DungeonChestRewardClick(InventoryClickEvent event)
	{
		if(event.getSlot()%9==0)
			if(event.getClickedInventory().getName().contains("����"))
				event.setCancelled(true);
	}
	
	public void DungeonMonsterGUIMainClick(InventoryClickEvent event)
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
			String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);
			if(slot == 45)
				DungeonSetUpGUI(player, DungeonName);
			else if(slot%9 != 0 && slot <= 44)
				DungeonMonsterChooseMain(player, DungeonName, slot);
		}
		return;
	}
	
	public void DungeonMonsterChooseMainClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);
			
			if(slot == 0)//���� ���
				DungeonMonsterGUIMain(player, DungeonName);
			else
			{
				int Slot = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getLore().get(1)));
				String Type = null;
				if(Slot< 9)
				{
					Type="Boss";
					Slot = Slot-1;
				}
				else if(Slot < 18)
				{
					Type="SubBoss";
					Slot = Slot-10;
				}
				else if(Slot < 27)
				{
					Type="High";
					Slot = Slot-19;
				}
				else if(Slot < 36)
				{
					Type="Middle";
					Slot = Slot-28;
				}
				else if(Slot < 45)
				{
					Type="Normal";
					Slot = Slot-37;
				}
				if(slot == 2)//����
				{
				  	YamlLoader dungeonYaml = new YamlLoader();
					dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
					dungeonYaml.removeKey(Type+"."+Slot);
					dungeonYaml.saveConfig();
					DungeonMonsterGUIMain(player, DungeonName);
				}
				else if(slot == 4)//�Ϲ� ����
					DungeonSelectNormalMonsterChoose(player, DungeonName, Type, Slot);
				else if(slot == 6)//Ŀ���� ����
					DungeonSelectCustomMonsterChoose(player, DungeonName, Type, Slot, 0);
			}
		}
	}
	
	public void DungeonSelectNormalMonsterChooseClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		int slot = event.getSlot();
		
		if(slot==53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);
			int Slot = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot==45)
				DungeonMonsterChooseMain(player, DungeonName, Slot);
			else
			{
				String Type = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
				if(slot==0)
					dungeonYaml.set(Type+"."+Slot, "������");
				else if(slot==1)
					dungeonYaml.set(Type+"."+Slot, "�ｺ�̷���");
				else if(slot==2)
					dungeonYaml.set(Type+"."+Slot, "��ũ����");
				else if(slot==3)
					dungeonYaml.set(Type+"."+Slot, "��Ź�");
				else if(slot==4)
					dungeonYaml.set(Type+"."+Slot, "�ﵿ���Ź�");
				else if(slot==5)
					dungeonYaml.set(Type+"."+Slot, "�￣����");
				else if(slot==6)
					dungeonYaml.set(Type+"."+Slot, "�ｽ����");
				else if(slot==7)
					dungeonYaml.set(Type+"."+Slot, "�︶�׸�ť��");
				else if(slot==8)
					dungeonYaml.set(Type+"."+Slot, "�︶��");
				else if(slot==9)
					dungeonYaml.set(Type+"."+Slot, "�������Ǳ׸�");
				else if(slot==10)
					dungeonYaml.set(Type+"."+Slot, "�������");
				else if(slot==11)
					dungeonYaml.set(Type+"."+Slot, "�ﰡ��Ʈ");
				else if(slot==12)
					dungeonYaml.set(Type+"."+Slot, "���ȣ��");
				else if(slot==13)
					dungeonYaml.set(Type+"."+Slot, "�����");
				else if(slot==14)
					dungeonYaml.set(Type+"."+Slot, "�����");
				else if(slot==15)
					dungeonYaml.set(Type+"."+Slot, "���");
				else if(slot==16)
					dungeonYaml.set(Type+"."+Slot, "���");
				else if(slot==17)
					dungeonYaml.set(Type+"."+Slot, "���");
				else if(slot==18)
					dungeonYaml.set(Type+"."+Slot, "���¡��");
				else if(slot==19)
					dungeonYaml.set(Type+"."+Slot, "�����");
				else if(slot==20)
					dungeonYaml.set(Type+"."+Slot, "�������");
				else if(slot==21)
					dungeonYaml.set(Type+"."+Slot, "�������");
				else if(slot==22)
					dungeonYaml.set(Type+"."+Slot, "�︻");
				else if(slot==23)
					dungeonYaml.set(Type+"."+Slot, "���䳢");
				else if(slot==24)
					dungeonYaml.set(Type+"."+Slot, "���ֹ�");
				else if(slot==25)
					dungeonYaml.set(Type+"."+Slot, "��ϱذ�");
				dungeonYaml.saveConfig();
				DungeonMonsterGUIMain(player, DungeonName);
			}
		}
	}
	
	public void DungeonSelectCustomMonsterChooseClick(InventoryClickEvent event)
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
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			int Slot = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			String Type = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			String DungeonName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(2));
			
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
			if(slot == 45)//���� ���
				DungeonMonsterChooseMain(player, DungeonName, Slot);
			else if(slot == 48)//���� ������
				DungeonSelectCustomMonsterChoose(player, DungeonName, Type, Slot, page-1);
			else if(slot == 50)//���� ������
				DungeonSelectCustomMonsterChoose(player, DungeonName, Type, Slot, page+1);
			else if(event.getCurrentItem().getTypeId()==383)
			{
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
				dungeonYaml.set(Type+"."+Slot, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				dungeonYaml.saveConfig();
				DungeonMonsterGUIMain(player, DungeonName);
			}
		}
	}
	
	public void DungeonMusicSettingGUIClick(InventoryClickEvent event)
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
			String DungeonName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			boolean isBoss = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1)));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				DungeonSetUpGUI(player, DungeonName);
			else if(slot == 48)//���� ������
				DungeonMusicSettingGUI(player, page-1,DungeonName,isBoss);
			else if(slot == 50)//���� ������
				DungeonMusicSettingGUI(player, page+1,DungeonName,isBoss);
			else
			{
				if(event.getCurrentItem().hasItemMeta())
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				  	YamlLoader dungeonYaml = new YamlLoader();
					dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
					if(isBoss)
						dungeonYaml.set("BGM.BOSS", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
					else
						dungeonYaml.set("BGM.Normal", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
					dungeonYaml.saveConfig();
					DungeonSetUpGUI(player, DungeonName);
				}
			}
		}
			
		
		switch (event.getSlot())
		{
		default :
			return;
		}
	}
	//DungeonGUI Click//

	
	//EnterCardGUI Click//
	public void EnterCardSetUpGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String EnterCardName = ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getLore().get(1));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)//���� ���
				DungeonListMainGUI(player, 0, 358);
			else if(slot == 2)//���� ����
			{
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/DungeonList.yml");
				if(dungeonYaml.getKeys().size()==0)
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[����] : ������ ������ �����ϴ�! ������ ���� ����� ������!");
				}
				else
					EnterCardDungeonSettingGUI(player, 0, EnterCardName);
			}
			else if(slot == 4)//������ ���� �ʱ�ȭ
			{
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.8F);
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/EnterCardList.yml");
				dungeonYaml.set(EnterCardName+".ID",358);
				dungeonYaml.set(EnterCardName+".DATA",0);
				dungeonYaml.saveConfig();
				EnterCardSetUpGUI(player, EnterCardName);
			}
			else
			{
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setTemp(player, "Dungeon");
				u.setType(player, "EnterCard");
				u.setString(player, (byte)1, EnterCardName);
				if(slot == 3)//������ ���� ����
				{
					u.setString(player, (byte)0, "ECID");//EnterCardID
					player.sendMessage(ChatColor.GREEN+"[������] : ������ ������ Ÿ�� ID�� �Է� �� �ּ���.");
				}
				else if(slot == 5)//���� �ο� ����
				{
					u.setString(player, (byte)0, "ECC");//EnterCardCapacity
					player.sendMessage(ChatColor.GREEN+"[������] : �ʿ� ���� �ο� ���� �Է� �� �ּ���.");
				}
				else if(slot == 6)//��ȿ�ð� ����
				{
					u.setString(player, (byte)0, "ECUH");//EnterCardUseableHour
					player.sendMessage(ChatColor.GREEN+"[������] : ��ȿ �ð��� �Է� �� �ּ���. (�ִ� 24�ð�, -1�Է½� ������)");
				}
			}
		}
	}

	public void EnterCardDungeonSettingGUIClick(InventoryClickEvent event)
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
			String EnterCardName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				EnterCardSetUpGUI(player, EnterCardName);
			else
			{
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/EnterCardList.yml");
				dungeonYaml.set(EnterCardName+".Dungeon", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				dungeonYaml.saveConfig();
				EnterCardSetUpGUI(player, EnterCardName);
			}
		}
	}
	//EnterCardGUI Click//

	
	//AltarGUI Click//
	public void AltarShapeListGUIClick(InventoryClickEvent event)
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
			if(slot == 45)//���� ���
				DungeonListMainGUI(player, 0, 120);
			else
			{
				if(ServerTick_Main.ServerTask.compareTo("null")!=0)
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[Server] : ���� ������ "+ChatColor.YELLOW+ServerTick_Main.ServerTask+ChatColor.RED+" �۾� ���Դϴ�.");
					return;
				}
				player.closeInventory();
			  	YamlLoader AltarList = new YamlLoader();
				AltarList.getConfig("Dungeon/AltarList.yml");
				String Code = ChatColor.BLACK+""+ChatColor.BOLD;
				Code = Code+ChatColor.WHITE+"[����]";
				String Salt = Code;
				int ID = 1;
				int DATA = 0;
				String Type = null;
				int radius = 5;
				if(slot == 0)
				{
					Type = "MossyAltar";
					ID = 48;
					radius = 3;
				}
				else if(slot == 1)
				{
					Type = "GoldBigDragon";
					ID = 41;
					radius = 20;
				}
				else if(slot == 2)
				{
					Type = "StoneHenge";
					ID = 1;
					radius = 8;
				}
				else if(slot == 3)
				{
					Type = "AnatomicalBoard";
					ID = 1;
					DATA = 5;
					radius = 3;
				}
				
				for(;;)
				{
					for(int count=0;count < 6; count++)
						Salt = Salt+getRandomCode();
					if(AltarList.contains(Salt)==false)
						break;
					Salt = Code;
				}
				AltarList.set(Salt+".Name", "��� ������ ����");
				AltarList.set(Salt+".Type", Type);
				AltarList.set(Salt+".radius", radius);
				AltarList.set(Salt+".ID", ID);
				AltarList.set(Salt+".DATA", DATA);
				AltarList.set(Salt+".World", player.getLocation().getWorld().getName());
				AltarList.set(Salt+".X", (int)player.getLocation().getX());
				AltarList.set(Salt+".Y", (int)player.getLocation().getY());
				AltarList.set(Salt+".Z", (int)player.getLocation().getZ());
				AltarList.saveConfig();
				AltarList.getConfig("Dungeon/Altar/"+Salt+".yml");
				AltarList.createSection("EnterCard");
				AltarList.saveConfig();
				new structure.Structure_Main().CreateSturcture(player, Salt, (short) (101+event.getSlot()), 4);
			}
		}
	}

	public void AltarSettingGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		String AltarName = event.getInventory().getItem(8).getItemMeta().getLore().get(1);
		
		
		if(slot == 8)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)//���� ���
				DungeonListMainGUI(player, 0, 120);
			else if(slot == 2)//�̸� ����
			{
				UserData_Object u = new UserData_Object();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				u.setTemp(player, "Dungeon");
				player.closeInventory();
				u.setType(player, "Altar");
				u.setString(player, (byte)0, "EAN");//EditAltarName
				u.setString(player, (byte)1, AltarName);
				player.sendMessage(ChatColor.GREEN+"[����] : ���� �̸��� �Է� �� �ּ���.");
			}
			else if(slot == 4)//�Ϲ� ���� ����
				AltarDungeonSettingGUI(player, 0, AltarName);
			else if(slot == 6)//������ ����
				AltarEnterCardSettingGUI(player, 0, AltarName.substring(2, AltarName.length()));
		}
	}
	
	public void AltarUseGUIClick(InventoryClickEvent event)
	{
		if(event.getSlot()!=4)
			if(ChatColor.stripColor(event.getClickedInventory().getName()).compareTo("���ܿ� ������ ��ġ�� �������� �̵��մϴ�")==0)
				event.setCancelled(true);
	}
	
	public void AltarDungeonSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		String AltarName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2, event.getInventory().getItem(53).getItemMeta().getLore().get(1).length());
		int slot = event.getSlot();
		
		if(slot == 53)
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)
				AltarSettingGUI(player, AltarName);
			else
			{
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/Altar/"+AltarName+".yml");
				dungeonYaml.set("NormalDungeon", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				dungeonYaml.saveConfig();
				AltarSettingGUI(player, AltarName);
			}
		}
	}
	
	public void AltarEnterCardSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		String AltarName = event.getInventory().getItem(53).getItemMeta().getLore().get(1);
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
			if(slot == 45)//���� ���
				AltarSettingGUI(player, AltarName);
			else if(slot == 48)//���� ������
				AltarEnterCardSettingGUI(player, page-1, AltarName);
			else if(slot == 49)//������ ���
				AltarEnterCardListGUI(player, page, AltarName);
			else if(slot == 50)//���� ������
				AltarEnterCardSettingGUI(player, page+1, AltarName);
			else if(event.isShiftClick()&&event.isRightClick())
			{
				SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/Altar/"+AltarName+".yml");
				dungeonYaml.removeKey("EnterCard."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				dungeonYaml.saveConfig();
				AltarEnterCardSettingGUI(player, page, AltarName);
				return;
			}
		}
	}
	
	public void AltarEnterCardListGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		String AltarName = event.getInventory().getItem(53).getItemMeta().getLore().get(1);
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
			if(slot == 45)//���� ���
				AltarEnterCardSettingGUI(player, 0, AltarName);
			else if(slot == 48)//���� ������
				AltarEnterCardListGUI(player, page-1, AltarName);
			else if(slot == 50)//���� ������
				AltarEnterCardListGUI(player, page+1, AltarName);
			else
			{
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/Altar/"+AltarName+".yml");
				dungeonYaml.createSection("EnterCard."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				dungeonYaml.saveConfig();
				AltarEnterCardSettingGUI(player, page, AltarName);
			}
		}
	}
	
	public String getRandomCode()
	{
		int randomNum = new util.Util_Number().RandomNum(0, 15);
		switch(randomNum)
		{
			case 0:
				return ChatColor.BLACK+"";
			case 1:
				return ChatColor.DARK_BLUE+"";
			case 2:
				return ChatColor.DARK_GREEN+"";
			case 3:
				return ChatColor.DARK_AQUA+"";
			case 4:
				return ChatColor.DARK_RED+"";
			case 5:
				return ChatColor.DARK_PURPLE+"";
			case 6:
				return ChatColor.GOLD+"";
			case 7:
				return ChatColor.GRAY+"";
			case 8:
				return ChatColor.DARK_GRAY+"";
			case 9:
				return ChatColor.BLUE+"";
			case 10:
				return ChatColor.GREEN+"";
			case 11:
				return ChatColor.AQUA+"";
			case 12:
				return ChatColor.RED+"";
			case 13:
				return ChatColor.LIGHT_PURPLE+"";
			case 14:
				return ChatColor.YELLOW+"";
			case 15:
				return ChatColor.WHITE+"";
		}
		return ChatColor.BLACK+"";
	}
	//AltarGUI Click//
	
	public void DungeonEXITClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		int slot = event.getSlot();
		player.closeInventory();
		if(slot == 3)
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
		else if(slot == 5)
		{
			new dungeon.Dungeon_Main().EraseAllDungeonKey(player, true);
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		  	YamlLoader playerYaml = new YamlLoader();
			String DungeonName = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter();
			long UTC = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_UTC();
			playerYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Entered/"+UTC+".yml");
			if(playerYaml.contains("EnteredAlter"))
			{
				DungeonName = playerYaml.getString("EnteredAlter");
				playerYaml.getConfig("Dungeon/AltarList.yml");
				if(playerYaml.contains(DungeonName))
				{
					Location loc = new Location(Bukkit.getServer().getWorld(playerYaml.getString(DungeonName+".World")), playerYaml.getLong(DungeonName+".X"), playerYaml.getLong(DungeonName+".Y")+1, playerYaml.getLong(DungeonName+".Z"));
					player.teleport(loc);
					return;
				}
			}
			new util.Util_Player().teleportToCurrentArea(player, true);
			return;
		}
	}
	
	//DungeonGUI Close//
	public void DungeonChestRewardClose(InventoryCloseEvent event)
	{
		String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);

	  	YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Reward.yml");
		
		for(int count = 0; count < 8; count++)
		{
			if(event.getInventory().getItem(count+1)!=null)
				dungeonYaml.set("100."+count, event.getInventory().getItem(count+1));
			else
				dungeonYaml.removeKey("100."+count);
			if(event.getInventory().getItem(count+10)!=null)
				dungeonYaml.set("90."+count, event.getInventory().getItem(count+10));	
			else
				dungeonYaml.removeKey("90."+count);
			if(event.getInventory().getItem(count+19)!=null)
				dungeonYaml.set("50."+count, event.getInventory().getItem(count+19));	
			else
				dungeonYaml.removeKey("50."+count);
			if(event.getInventory().getItem(count+28)!=null)
				dungeonYaml.set("10."+count, event.getInventory().getItem(count+28));
			else
				dungeonYaml.removeKey("10."+count);
			if(event.getInventory().getItem(count+37)!=null)
				dungeonYaml.set("1."+count, event.getInventory().getItem(count+37));
			else
				dungeonYaml.removeKey("1."+count);	
			if(event.getInventory().getItem(count+46)!=null)
				dungeonYaml.set("0."+count, event.getInventory().getItem(count+46));
			else
				dungeonYaml.removeKey("0."+count);
		}
		dungeonYaml.saveConfig();

		SoundEffect.SP((Player) event.getPlayer(), Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
		event.getPlayer().sendMessage(ChatColor.GREEN+"[����] : ���� ���� �Ϸ�!");
	}
	
	public void AltarUSEGuiClose(InventoryCloseEvent event)
	{
		ItemStack item = event.getInventory().getItem(4);
		if(item!=null)
		{
			String AltarName = event.getInventory().getItem(0).getItemMeta().getDisplayName();
			Player player = (Player) event.getPlayer();
		  	YamlLoader altarYaml = new YamlLoader();
			altarYaml.getConfig("Dungeon/Altar/"+AltarName+".yml");
			event.getInventory().setItem(4, null);
			
			int LvDistrict = -1;
			int RealLvDistrict = -1;
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter() != null)
			{
				if(new util.Util_Player().giveItem(player, item)==false)
					new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.WHITE+"(�̹� ������ ����� ���� �ִ�...)");
				return;
			}
			if(item.hasItemMeta())
			{
				if(item.getItemMeta().hasDisplayName())
				{
					if(item.getItemMeta().getDisplayName().compareTo("��c��l[���� ������]")==0)
					{
						if(altarYaml.contains("EnterCard."+ChatColor.stripColor(item.getItemMeta().getLore().get(1))))
						{
							int capacity = Integer.parseInt(ChatColor.stripColor(item.getItemMeta().getLore().get(3)).split(" : ")[1]);
							String time = ChatColor.stripColor(item.getItemMeta().getLore().get(item.getItemMeta().getLore().size()-1));
							boolean canUse = false;
							if(time.compareTo("[���� �ð� ����]")==0)
								canUse = true;
							else
							{
								int year = Integer.parseInt(time.split(" ")[0].substring(0, time.split(" ")[0].length()-1));
								int month = Integer.parseInt(time.split(" ")[1].substring(0, time.split(" ")[1].length()-1));
								int day = Integer.parseInt(time.split(" ")[2].substring(0, time.split(" ")[2].length()-1));
								int hour = Integer.parseInt(time.split(" ")[3].substring(0, time.split(" ")[3].length()-1));
								int min = Integer.parseInt(time.split(" ")[4].substring(0, time.split(" ")[4].length()-1));
								int sec = Integer.parseInt(time.split(" ")[5].substring(0, time.split(" ")[5].length()-1));

								Calendar Today = Calendar.getInstance();
								Today.add(Calendar.MONTH, 1);
								
								if(year > Today.get(Calendar.YEAR))
									canUse = true;
								else if(year == Today.get(Calendar.YEAR))
									if(month > Today.get(Calendar.MONTH))
										canUse = true;
									else if(month == Today.get(Calendar.MONTH))
										if(day > Today.get(Calendar.DATE))
											canUse = true;
										else if(day == Today.get(Calendar.DATE))
											if(hour > Today.get(Calendar.HOUR))
												canUse = true;
											else if(hour == Today.get(Calendar.HOUR))
												if(min > Today.get(Calendar.MINUTE))
													canUse = true;
												else if(min == Today.get(Calendar.MINUTE))
													if(sec > Today.get(Calendar.SECOND))
														canUse = true;
							}
							if(canUse)
							{
							  	YamlLoader enterCardYaml = new YamlLoader();
								enterCardYaml.getConfig("Dungeon/EnterCardList.yml");
							  	YamlLoader levelYaml = new YamlLoader();
							  	levelYaml.getConfig("Dungeon/Dungeon/"+enterCardYaml.getString(ChatColor.stripColor(item.getItemMeta().getLore().get(1))+".Dungeon")+"/Option.yml");
							  	int levelDistrict = levelYaml.getInt("District.Level");
							  	int realLevelDistrict = levelYaml.getInt("District.RealLevel");
							  	
							  	
								if(enterCardYaml.contains(ChatColor.stripColor(item.getItemMeta().getLore().get(1))))
								{
									PartyEnterDungeon(player, item, AltarName, capacity, enterCardYaml.getString(ChatColor.stripColor(item.getItemMeta().getLore().get(1))+".Dungeon"), levelDistrict, realLevelDistrict);
								}
								else
								{
									if(new util.Util_Player().giveItem(player, item)==false)
										new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
									SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
									player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
									return;
								}
							}
							else
							{
								if(new util.Util_Player().giveItem(player, item)==false)
									new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
								SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
								player.sendMessage(ChatColor.WHITE+"(���� �������� ��ȿ�ð��� ������...)");
								return;
							}
						}
						else
						{
							if(new util.Util_Player().giveItem(player, item)==false)
								new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
							SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
							return;
						}
					}
					else
					{
						if(altarYaml.getString("NormalDungeon")!=null)
						{
						  	YamlLoader dungeonYaml = new YamlLoader();
							dungeonYaml.getConfig("Dungeon/DungeonList.yml");
							if(dungeonYaml.contains(altarYaml.getString("NormalDungeon")))
								PartyEnterDungeon(player, item, AltarName, -1, altarYaml.getString("NormalDungeon"), LvDistrict, RealLvDistrict);
							else
							{
								altarYaml.set("NormalDungeon", null);
								altarYaml.saveConfig();
								if(new util.Util_Player().giveItem(player, item)==false)
									new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
								SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
								player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
								return;
							}
						}
						else
						{
							if(new util.Util_Player().giveItem(player, item)==false)
								new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
							SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
							return;
						}
					}
				}
				return;
			}
			if(altarYaml.getString("NormalDungeon")!=null)
			{
			  	YamlLoader dungeonYaml = new YamlLoader();
				dungeonYaml.getConfig("Dungeon/DungeonList.yml");
				if(dungeonYaml.contains(altarYaml.getString("NormalDungeon")))
					PartyEnterDungeon(player, item, AltarName, -1, altarYaml.getString("NormalDungeon"), LvDistrict, RealLvDistrict);
				else
				{
					altarYaml.set("NormalDungeon", null);
					altarYaml.saveConfig();
					if(new util.Util_Player().giveItem(player, item)==false)
						new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
					SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
					return;
				}
			}
			else
			{
				if(new util.Util_Player().giveItem(player, item)==false)
					new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
				return;
			}
		}
	}

	private void PartyEnterDungeon(Player player, ItemStack item, String AltarName, int capacity, String DungeonName, int LvDistrict, int RealLvDistrict)
	{
		if(main.Main_ServerOption.PartyJoiner.containsKey(player))
		{
			
			if(capacity!=-1)
				if(main.Main_ServerOption.Party.get(main.Main_ServerOption.PartyJoiner.get(player)).getPartyMembers()!=capacity)
				{
					if(new util.Util_Player().giveItem(player, item)==false)
						new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
					SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.RED + "[����] : ���� ���� �ο��� ���� �ʽ��ϴ�! ("+capacity+"��)");
					return;
				}
		  	YamlLoader AltarConfig = new YamlLoader();
		  	YamlLoader dungeonYaml = new YamlLoader();
			AltarConfig.getConfig("Dungeon/Altar/"+AltarName+".yml");
			dungeonYaml.getConfig("Dungeon/Dungeon/"+AltarConfig.getString("NormalDungeon")+"/Option.yml");
			if(LvDistrict==-1)
				LvDistrict = dungeonYaml.getInt("District.Level");
			if(RealLvDistrict==-1)
				RealLvDistrict = dungeonYaml.getInt("District.RealLevel");
			if(DungeonName!=null)
				dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
			long UTC = main.Main_ServerOption.PartyJoiner.get(player);
			if(main.Main_ServerOption.Party.get(UTC).getLeader().compareTo(player.getName())==0)
			{
				//��Ƽ�� �߰��ϱ�//
				ArrayList<Player> NearPartyMember = new ArrayList<Player>();
				main.Main_ServerOption.Party.get(UTC).getMember();
				for(int count = 0; count < main.Main_ServerOption.Party.get(UTC).getPartyMembers(); count++)
				{
					if(player.getWorld().getName().compareTo(main.Main_ServerOption.Party.get(UTC).getMember()[count].getWorld().getName())==0)
						if(player.getLocation().distance(main.Main_ServerOption.Party.get(UTC).getMember()[count].getLocation()) < 11)
							NearPartyMember.add(main.Main_ServerOption.Party.get(UTC).getMember()[count]);
				}
				for(int count = 0; count < NearPartyMember.size(); count++)
				{
					if(main.Main_ServerOption.PlayerList.get(NearPartyMember.get(count).getUniqueId().toString()).getStat_Level()< LvDistrict)
					{
						if(new util.Util_Player().giveItem(player, item)==false)
							new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
						SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED + "[����] : ��Ƽ�� "+NearPartyMember.get(count).getName()+"���� ������ ���� ������ ������ �� �����ϴ�!");
						player.sendMessage(ChatColor.RED + "(���� ���� : "+dungeonYaml.getInt("District.Level")+")");
						return;
					}
					if(main.Main_ServerOption.PlayerList.get(NearPartyMember.get(count).getUniqueId().toString()).getStat_RealLevel()<RealLvDistrict)
					{
						if(new util.Util_Player().giveItem(player, item)==false)
							new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
						SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED + "[����] : ��Ƽ�� "+NearPartyMember.get(count).getName()+"���� ���� ������ ���� ������ ������ �� �����ϴ�!");
						player.sendMessage(ChatColor.RED + "(���� ���� ���� : "+dungeonYaml.getInt("District.RealLevel")+")");
						return;
					}
				}
				if(new dungeon.Dungeon_Creater().CreateDungeon(player, dungeonYaml.getInt("Size"), dungeonYaml.getInt("Maze_Level"), dungeonYaml.getString("Type.Name"),DungeonName,NearPartyMember, AltarName, item)==false)
				{
					if(new util.Util_Player().giveItem(player, item)==false)
						new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
					SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
					return;
				}
			}
			else
			{
				if(new util.Util_Player().giveItem(player, item)==false)
					new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED+"[��Ƽ] : ��Ƽ�� ������ ���ܿ� ������ ��ĥ �� �ֽ��ϴ�!");
				return;
			}
		}
		else
			SoloEnterDungeon(player, item, AltarName, capacity, DungeonName, LvDistrict, RealLvDistrict);
	}
	
	private void SoloEnterDungeon(Player player, ItemStack item, String AltarName, int capacity, String DungeonName, int LvDistrict, int RealLvDistrict)
	{
		
		if(capacity==-1||capacity==1)
		{
		  	YamlLoader AltarConfig = new YamlLoader();
		  	YamlLoader dungeonYaml = new YamlLoader();
			AltarConfig.getConfig("Dungeon/Altar/"+AltarName+".yml");
			dungeonYaml.getConfig("Dungeon/Dungeon/"+AltarConfig.getString("NormalDungeon")+"/Option.yml");
			if(DungeonName!=null)
				dungeonYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
			if(LvDistrict==-1)
				LvDistrict = dungeonYaml.getInt("District.Level");
			if(RealLvDistrict==-1)
				RealLvDistrict = dungeonYaml.getInt("District.RealLevel");
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level()< LvDistrict)
			{
				if(new util.Util_Player().giveItem(player, item)==false)
					new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED + "[����] : ����� ������ ���� ������ ������ �� �����ϴ�!");
				player.sendMessage(ChatColor.RED + "(���� ���� : "+dungeonYaml.getInt("District.Level")+")");
				return;
			}
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel()<RealLvDistrict)
			{
				if(new util.Util_Player().giveItem(player, item)==false)
					new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED + "[����] : ����� ���� ������ ���� ������ ������ �� �����ϴ�!");
				player.sendMessage(ChatColor.RED + "(���� ���� ���� : "+dungeonYaml.getInt("District.RealLevel")+")");
				return;
			}
			if(new dungeon.Dungeon_Creater().CreateDungeon(player, dungeonYaml.getInt("Size"), dungeonYaml.getInt("Maze_Level"), dungeonYaml.getString("Type.Name"),DungeonName,null, AltarName, item)==false)
			{
				if(new util.Util_Player().giveItem(player, item)==false)
					new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				return;
			}
		}
		else
		{
			if(new util.Util_Player().giveItem(player, item)==false)
				new event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
			SoundEffect.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
			player.sendMessage(ChatColor.RED + "[����] : ���� ���� �ο��� ���� �ʽ��ϴ�! ("+capacity+"��)");
			return;
		}
	}
	//DungeonGUI Close//

}
