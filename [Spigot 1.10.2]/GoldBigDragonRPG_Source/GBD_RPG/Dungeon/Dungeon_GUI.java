package GBD_RPG.Dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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

import GBD_RPG.Admin.OPbox_GUI;
import GBD_RPG.ServerTick.ServerTick_Main;
import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_GUI;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

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
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");

		String UniqueCode = "��0��0��a��0��0��r";
		Inventory inv = null;
		if(Type==52)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));
		else if(Type==358)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ��� : " + (page+1));
		else if(Type==120)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));
		Object[] DungeonList = null;
		if(Type==52)//����
		{
			DungeonList = DungeonConfig.getConfigurationSection("").getKeys(false).toArray();
			
			int loc=0;
			for(int count = page*45; count < DungeonList.length;count++)
			{
				YamlManager DungeonOptionYML = YC.getNewConfig("Dungeon/Dungeon/"+DungeonList[count].toString()+"/Option.yml");
				
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), 52,0,1,Arrays.asList(
				"",ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getString("Type.Name")
				,ChatColor.BLUE+"���� ũ�� : "+ChatColor.WHITE+DungeonOptionYML.getInt("Size")
				,ChatColor.BLUE+"���� ������ : "+ChatColor.WHITE+DungeonOptionYML.getInt("Maze_Level")
				,ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.Level")+" �̻�"
				,ChatColor.BLUE+"���� ���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.RealLevel")+" �̻�"
				,"",ChatColor.BLUE+"[�⺻ Ŭ���� ����]"
				,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.Money")+" "+GBD_RPG.Main_Main.Main_ServerOption.Money
				,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.EXP")+ChatColor.AQUA+" "+ChatColor.BOLD+"EXP"
				,"",ChatColor.YELLOW+"[�� Ŭ���� ���� ����]",ChatColor.RED+"[Shift + ��Ŭ���� ���� ����]"), loc, inv);
				
				loc=loc+1;
			}
		}
		else if(Type==358)//������
		{
			DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
			DungeonList = DungeonConfig.getConfigurationSection("").getKeys(false).toArray();
			int loc=0;
			for(int count = page*45; count < DungeonList.length;count++)
			{
				String Time = null;
				if(DungeonConfig.getInt(DungeonList[count].toString()+".Hour")!=-1)
				{
					if(DungeonConfig.getInt(DungeonList[count].toString()+".Hour")!=0)
						Time = DungeonConfig.getInt(DungeonList[count].toString()+".Hour")+"�ð� ";
					if(DungeonConfig.getInt(DungeonList[count].toString()+".Min")!=0)
						Time = Time+DungeonConfig.getInt(DungeonList[count].toString()+".Min")+"�� ";
					Time = Time+DungeonConfig.getInt(DungeonList[count].toString()+".Sec")+"��";
				}
				else
					Time = "������";
				if(DungeonConfig.getString(DungeonList[count].toString()+".Dungeon")==null)
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), DungeonConfig.getInt(DungeonList[count].toString()+".ID"),DungeonConfig.getInt(DungeonList[count].toString()+".DATA"),1,Arrays.asList(
					"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
					ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+DungeonConfig.getInt(DungeonList[count].toString()+".Capacity"),
					ChatColor.BLUE+"��ȿ �ð� : "+ChatColor.WHITE+""+Time,
					"",ChatColor.YELLOW+"[�� Ŭ���� ������ ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �߱�]",ChatColor.RED+"[Shift + ��Ŭ���� ������ ����]"), loc, inv);
				else
				{
					YamlManager Dungeon = YC.getNewConfig("Dungeon/DungeonList.yml");
					if(Dungeon.contains(DungeonConfig.getString(DungeonList[count].toString()+".Dungeon")))
					{
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), DungeonConfig.getInt(DungeonList[count].toString()+".ID"),DungeonConfig.getInt(DungeonList[count].toString()+".DATA"),1,Arrays.asList(
						"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+""+DungeonConfig.getString(DungeonList[count].toString()+".Dungeon"),
						ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+DungeonConfig.getInt(DungeonList[count].toString()+".Capacity"),
						ChatColor.BLUE+"��ȿ �ð� : "+ChatColor.WHITE+""+Time,
						"",ChatColor.YELLOW+"[�� Ŭ���� ������ ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �߱�]",ChatColor.RED+"[Shift + ��Ŭ���� ������ ����]"), loc, inv);
					}
					else
					{
						DungeonConfig.set(DungeonList[count].toString()+".Dungeon",null);
						DungeonConfig.saveConfig();
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), DungeonConfig.getInt(DungeonList[count].toString()+".ID"),DungeonConfig.getInt(DungeonList[count].toString()+".DATA"),1,Arrays.asList(
						"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
						ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+DungeonConfig.getInt(DungeonList[count].toString()+".Capacity"),
						ChatColor.BLUE+"��ȿ �ð� : "+ChatColor.WHITE+""+Time,
						"",ChatColor.YELLOW+"[�� Ŭ���� ������ ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �߱�]",ChatColor.RED+"[Shift + ��Ŭ���� ������ ����]"), loc, inv);
					}
						
				}
				
				loc=loc+1;
			}
		}
		else if(Type==120)//����
		{
			DungeonConfig = YC.getNewConfig("Dungeon/AltarList.yml");
			DungeonList = DungeonConfig.getKeys().toArray();
			int loc=0;
			for(int count = page*45; count < DungeonList.length;count++)
			{
				String AltarCode = DungeonList[count].toString();
				Stack2(ChatColor.WHITE+DungeonConfig.getString(AltarCode+".Name"), DungeonConfig.getInt(AltarCode+".ID"),DungeonConfig.getInt(AltarCode+".DATA"),1,Arrays.asList(
				"",ChatColor.BLUE+"[���� ��ġ]",
				ChatColor.WHITE+" "+DungeonConfig.getString(AltarCode+".World"),
				ChatColor.WHITE+" "+DungeonConfig.getInt(AltarCode+".X")+","+DungeonConfig.getInt(AltarCode+".Y")+","+DungeonConfig.getInt(AltarCode+".Z"),
				"",ChatColor.YELLOW+"[�� Ŭ���� ���� ����]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ���� �̵�]",ChatColor.RED+"[Shift + ��Ŭ���� ���� ö��]","",AltarCode), loc, inv);
				
				loc=loc+1;
			}
		}
		if(Type==52)
			Stack2(ChatColor.BLUE + "" + ChatColor.BOLD + "[���� �׸�]", 52,0,1,Arrays.asList(ChatColor.BLUE + "���� �׸� : "+ChatColor.WHITE+"����","",ChatColor.YELLOW+"������ �����ϱ� ���ؼ���",ChatColor.YELLOW+"�Ʒ��� 3���� �������� ���� �ؾ� �մϴ�.",ChatColor.YELLOW+"[����, ������, ����]"), 47, inv);
		else if(Type==358)
			Stack2(ChatColor.BLUE + "" + ChatColor.BOLD + "[���� �׸�]", 358,0,1,Arrays.asList(ChatColor.BLUE + "���� �׸� : "+ChatColor.WHITE+"������","",ChatColor.YELLOW+"������ �����ϱ� ���ؼ���",ChatColor.YELLOW+"�Ʒ��� 3���� �������� ���� �ؾ� �մϴ�.",ChatColor.YELLOW+"[����, ������, ����]"), 47, inv);
		else if(Type==120)
			Stack2(ChatColor.BLUE + "" + ChatColor.BOLD + "[���� �׸�]", 120,0,1,Arrays.asList(ChatColor.BLUE + "���� �׸� : "+ChatColor.WHITE+"����","",ChatColor.YELLOW+"������ �����ϱ� ���ؼ���",ChatColor.YELLOW+"�Ʒ��� 3���� �������� ���� �ؾ� �մϴ�.",ChatColor.YELLOW+"[����, ������, ����]"), 47, inv);
		
		
		if(DungeonList.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		if(Type==52)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ����", 383,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ������ �����մϴ�."), 49, inv);
		if(Type==358)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "������ ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "���ο� �������� �����մϴ�."), 49, inv);
		if(Type==120)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� �Ǽ�", 381,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ������ �����մϴ�.","",ChatColor.RED+""+ChatColor.BOLD+"[������ ������ ������ �ٶ󺾴ϴ�.]"), 49, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void DungeonSetUpGUI(Player player, String DungeonName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");

		String UniqueCode = "��0��0��a��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0���� ���� : " +DungeonName);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� Ÿ��", DungeonConfig.getInt("Type.ID"),DungeonConfig.getInt("Type.DATA"),1,Arrays.asList(ChatColor.GRAY + "���� ���� Ÿ�� : "+DungeonConfig.getString("Type.Name")), 11, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ũ��", 395,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� ũ�� : "+DungeonConfig.getInt("Size"),ChatColor.DARK_GRAY + "�ּ� : 5",ChatColor.DARK_GRAY + "�ִ� : 30"), 13, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ����", 53,0,1,Arrays.asList(ChatColor.GRAY + "���� �̷� ���� : "+DungeonConfig.getInt("Maze_Level"),"",ChatColor.YELLOW+"[���� �޴� �׸�]",ChatColor.YELLOW+" - ������ ���� ��",ChatColor.YELLOW+" - ���� ������ ����",ChatColor.YELLOW+" - ���� ������"), 15, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ����", 101,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� ������ �����մϴ�.",ChatColor.RED+"���� ���� : " + ChatColor.GRAY+DungeonConfig.getInt("District.Level"),ChatColor.RED+"���� ���� ���� : " + ChatColor.GRAY+ DungeonConfig.getInt("District.RealLevel")), 20, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ����", 266,0,1,Arrays.asList(ChatColor.GRAY + "���� �⺻ ������ �����մϴ�.",ChatColor.YELLOW+"���� �ݾ� : " + ChatColor.GRAY+DungeonConfig.getInt("Reward.Money"),ChatColor.AQUA+"���� ����ġ : " + ChatColor.GRAY+DungeonConfig.getInt("Reward.EXP")), 22, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���� ����", 54,0,1,Arrays.asList(ChatColor.GRAY + "���� �߰� ������ �����մϴ�."), 24, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ����", 383,0,1,Arrays.asList(ChatColor.GRAY + "���� ���͸� �����մϴ�."), 29, inv);
		
		if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") == true)
		{
			String lore = "";
			int tracknumber = DungeonConfig.getInt("BGM.Normal");
			lore = " %enter%"+ChatColor.GRAY + "���� BGM�� �����մϴ�.%enter% %enter%"+ChatColor.BLUE + "[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ tracknumber+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ new OtherPlugins.NoteBlockAPIMain().getTitle(tracknumber)+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+new OtherPlugins.NoteBlockAPIMain().getAuthor(tracknumber)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
			
			String Description = new OtherPlugins.NoteBlockAPIMain().getDescription(tracknumber);
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
			tracknumber = DungeonConfig.getInt("BGM.BOSS");
			lore = " %enter%"+ChatColor.GRAY + "���� BGM�� �����մϴ�.%enter% %enter%"+ChatColor.BLUE + "[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ tracknumber+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ new OtherPlugins.NoteBlockAPIMain().getTitle(tracknumber)+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+new OtherPlugins.NoteBlockAPIMain().getAuthor(tracknumber)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
			
			Description = new OtherPlugins.NoteBlockAPIMain().getDescription(tracknumber);
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
		}
		else
		{
			Stack2(ChatColor.RED + ""+ChatColor.BOLD+"[���� �����]", 2266,0,1,Arrays.asList("",ChatColor.GRAY + "���� ����� �׸� ����",ChatColor.GRAY+"��� ��ų �� �ֽ��ϴ�.","",ChatColor.RED + "[     �ʿ� �÷�����     ]",ChatColor.RED+" - NoteBlockAPI"), 31, inv);
			Stack2(ChatColor.RED + ""+ChatColor.BOLD+"[���� �����]", 2266,0,1,Arrays.asList("",ChatColor.GRAY + "������ ����� �׸� ����",ChatColor.GRAY+"��� ��ų �� �ֽ��ϴ�.","",ChatColor.RED + "[     �ʿ� �÷�����     ]",ChatColor.RED+" - NoteBlockAPI"), 33, inv);
		}
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 44, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 36, inv);

		player.openInventory(inv);
	}

	public void DungeonChestReward(Player player, String DungeonName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Reward.yml");

		String UniqueCode = "��1��0��a��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� : " +DungeonName);
	
		Stack2(ChatColor.BLUE + "" + ChatColor.BOLD + "[100% Ȯ��]", 160,11,1,Arrays.asList("",ChatColor.WHITE+"100% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"100% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 0, inv);
		Stack2(ChatColor.GREEN + "" + ChatColor.BOLD + "[90% Ȯ��]", 160,5,1,Arrays.asList("",ChatColor.WHITE+"90% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"90% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 9, inv);
		Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[50% Ȯ��]", 160,4,1,Arrays.asList("",ChatColor.WHITE+"50% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"50% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 18, inv);
		Stack2(ChatColor.GOLD + "" + ChatColor.BOLD + "[10% Ȯ��]", 160,1,1,Arrays.asList("",ChatColor.WHITE+"10% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"10% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 27, inv);
		Stack2(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[1% Ȯ��]", 160,14,1,Arrays.asList("",ChatColor.WHITE+"1% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"1% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 36, inv);
		Stack2(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[0.1% Ȯ��]", 160,10,1,Arrays.asList("",ChatColor.WHITE+"0.1% Ȯ���� ���� ��������",ChatColor.WHITE+"�� �ٿ� �����ø� �˴ϴ�.",ChatColor.WHITE+"0.1% Ȯ���� �� �ٿ� �ִ�",ChatColor.WHITE+"������ ��, �ϳ��� ���ɴϴ�.",""), 45, inv);

		for(int count = 0; count < 8; count++)
		{
			if(DungeonConfig.getItemStack("100."+count)!=null)
				ItemStackStack(DungeonConfig.getItemStack("100."+count), count+1, inv);
			if(DungeonConfig.getItemStack("90."+count)!=null)
				ItemStackStack(DungeonConfig.getItemStack("90."+count), count+10, inv);
			if(DungeonConfig.getItemStack("50."+count)!=null)
				ItemStackStack(DungeonConfig.getItemStack("50."+count), count+19, inv);
			if(DungeonConfig.getItemStack("10."+count)!=null)
				ItemStackStack(DungeonConfig.getItemStack("10."+count), count+28, inv);
			if(DungeonConfig.getItemStack("1."+count)!=null)
				ItemStackStack(DungeonConfig.getItemStack("1."+count), count+37, inv);
			if(DungeonConfig.getItemStack("0."+count)!=null)
				ItemStackStack(DungeonConfig.getItemStack("0."+count), count+46, inv);
		}
		player.openInventory(inv);
	}
	
	public void DungeonMonsterGUIMain(Player player, String DungeonName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");

		String UniqueCode = "��0��0��a��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� : " +DungeonName);

		Stack2(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[BOSS]", 160,14,1,Arrays.asList("",ChatColor.WHITE+"�����濡�� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 0, inv);
		Stack2(ChatColor.GOLD + "" + ChatColor.BOLD + "[�� ����]", 160,5,1,Arrays.asList("",ChatColor.WHITE+"������ �տ��� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 9, inv);
		Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[��� ����]", 160,4,1,Arrays.asList("",ChatColor.WHITE+"�Ϲ� �濡�� ���� �ſ� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 18, inv);
		Stack2(ChatColor.GREEN + "" + ChatColor.BOLD + "[�߱� ����]", 160,5,1,Arrays.asList("",ChatColor.WHITE+"�Ϲ� �濡�� ���� ���� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 27, inv);
		Stack2(ChatColor.BLUE + "" + ChatColor.BOLD + "[�ϱ� ����]", 160,11,1,Arrays.asList("",ChatColor.WHITE+"�Ϲ� �濡�� ���� �Ϲ� ���ʹ�",ChatColor.WHITE+"�� �ٿ��� �����մϴ�.",""), 36, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);

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
				if(DungeonConfig.getString(Type+"."+count)==null)
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[����]", 383, 0, 1,Arrays.asList("",ChatColor.YELLOW + "[�� Ŭ���� ���]"), count+1+(count2*9), inv);
				else
				{
					switch(DungeonConfig.getString(Type+"."+count))
					{
					case "������":
						Stack2(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[�Ϲ� ����]", 397, 2, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ｺ�̷���":
						Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[�Ϲ� ���̷���]", 397, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "��ũ����":
						Stack2(ChatColor.GREEN + "" + ChatColor.BOLD + "[�Ϲ� ũ����]", 397, 4, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "��Ź�":
						Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[�Ϲ� �Ź�]", 287, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ﵿ���Ź�":
						Stack2(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[�Ϲ� ���� �Ź�]", 375, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�￣����":
						Stack2(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[�Ϲ� ������]", 368, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ｽ����":
						Stack2(ChatColor.GREEN + "" + ChatColor.BOLD + "[�Ϲ� ������]", 341, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�︶�׸�ť��":
						Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[�Ϲ� ���׸� ť��]", 378, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�︶��":
						Stack2(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[�Ϲ� ����]", 438, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������Ǳ׸�":
						Stack2(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[�Ϲ� ���� �Ǳ׸�]", 283, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������":
						Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[�Ϲ� ������]", 369, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�ﰡ��Ʈ":
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�Ϲ� ����Ʈ]", 370, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "�ﰡ���":
						Stack2(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "[�Ϲ� ��ȣ��]", 410, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "�����":
						Stack2(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[�Ϲ� ����]", 1, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "�����":
						Stack2(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[�Ϲ� ����]", 319, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���":
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�Ϲ� ��]", 423, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���":
						Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[�Ϲ� ��]", 363, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���":
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�Ϲ� ��]", 365, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;	
					case "���¡��":
						Stack2(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[�Ϲ� ��¡��]", 351, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�����":
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�Ϲ� ����]", 352, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������":
						Stack2(ChatColor.RED + "" + ChatColor.BOLD + "[�Ϲ� ������]", 40, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�������":
						Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[�Ϲ� ������]", 349, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "�︻":
						Stack2(ChatColor.GOLD + "" + ChatColor.BOLD + "[�Ϲ� ��]", 418, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "���䳢":
						Stack2(ChatColor.GOLD + "" + ChatColor.BOLD + "[�Ϲ� �䳢]", 411, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "���ֹ�":
						Stack2(ChatColor.GOLD + "" + ChatColor.BOLD + "[�Ϲ� �ֹ�]", 388, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					case "��ϱذ�":
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�Ϲ� �ϱذ�]", 80, 0, 1,Arrays.asList(ChatColor.GRAY+"Ŀ���� ���Ͱ� �ƴ�",ChatColor.GRAY+"�Ϲ� ���� �����Դϴ�.","",ChatColor.YELLOW + "[�� Ŭ���� ����]"), count+1+(count2*9), inv);break;
					default:
						YamlManager MonsterList = YC.getNewConfig("Monster/MonsterList.yml");
						String MobName = DungeonConfig.getString(Type+"."+count);
						boolean isExit = false;
						for(int count3=0;count3<MonsterList.getKeys().size();count3++)
						{
							if(MonsterList.getKeys().toArray()[count3].toString().compareTo(MobName)==0)
							{
								String MonsterName = MobName;
								String Lore=null;
								GBD_RPG.Battle.Battle_Calculator d = new GBD_RPG.Battle.Battle_Calculator();
								Lore = "%enter%"+ChatColor.WHITE+""+ChatColor.BOLD+" �̸� : "+ChatColor.WHITE+MonsterList.getString(MonsterName+".Name")+"%enter%";
								Lore = Lore+ChatColor.WHITE+""+ChatColor.BOLD+" Ÿ�� : "+ChatColor.WHITE+MonsterList.getString(MonsterName+".Type")+"%enter%";
								Lore = Lore+ChatColor.WHITE+""+ChatColor.BOLD+" ���� ���̿� : "+ChatColor.WHITE+MonsterList.getString(MonsterName+".Biome")+"%enter%";
								Lore = Lore+ChatColor.RED+""+ChatColor.BOLD+" ����� : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".HP")+"%enter%";
								Lore = Lore+ChatColor.AQUA+""+ChatColor.BOLD+" ����ġ : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".EXP")+"%enter%";
								Lore = Lore+ChatColor.YELLOW+""+ChatColor.BOLD+" ��� �ݾ� : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".MIN_Money")+" ~ "+MonsterList.getInt(MonsterName+".MAX_Money")+"%enter%";
								Lore = Lore+ChatColor.RED+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".STR")
								+ChatColor.GRAY+ " [���� : " + d.CombatDamageGet(null, 0, MonsterList.getInt(MonsterName+".STR"), true) + " ~ " + d.CombatDamageGet(null, 0, MonsterList.getInt(MonsterName+".STR"), false) + "]%enter%";
								Lore = Lore+ChatColor.GREEN+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".DEX")
								+ChatColor.GRAY+ " [Ȱ�� : " + d.returnRangeDamageValue(null, MonsterList.getInt(MonsterName+".DEX"), 0, true) + " ~ " + d.returnRangeDamageValue(null, MonsterList.getInt(MonsterName+".DEX"), 0, false) + "]%enter%";
								Lore = Lore+ChatColor.DARK_AQUA+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".INT")
								+ChatColor.GRAY+ " [���� : " + (MonsterList.getInt(MonsterName+".INT")/4)+ " ~ "+(int)(MonsterList.getInt(MonsterName+".INT")/2.5)+"]%enter%";
								Lore = Lore+ChatColor.GRAY+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".WILL")
								+ChatColor.GRAY+ " [ũ�� : " + d.getCritical(null,MonsterList.getInt(MonsterName+".LUK"), (int)MonsterList.getInt(MonsterName+".WILL"),0) + " %]%enter%";
								Lore = Lore+ChatColor.YELLOW+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".LUK")
								+ChatColor.GRAY+ " [ũ�� : " + d.getCritical(null,MonsterList.getInt(MonsterName+".LUK"), (int)MonsterList.getInt(MonsterName+".WILL"),0) + " %]%enter%";
								Lore = Lore+ChatColor.GRAY+""+ChatColor.BOLD+" ��� : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".DEF")+"%enter%";
								Lore = Lore+ChatColor.AQUA+""+ChatColor.BOLD+" ��ȣ : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".Protect")+"%enter%";
								Lore = Lore+ChatColor.BLUE+""+ChatColor.BOLD+" ���� ��� : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".Magic_DEF")+"%enter%";
								Lore = Lore+ChatColor.DARK_BLUE+""+ChatColor.BOLD+" ���� ��ȣ : "+ChatColor.WHITE+MonsterList.getInt(MonsterName+".Magic_Protect")+"%enter%";
								Lore = Lore+"%enter%"+ChatColor.YELLOW+""+ChatColor.BOLD+"[�� Ŭ���� ����]";

								String[] scriptA = Lore.split("%enter%");
								for(int counter = 0; counter < scriptA.length; counter++)
									scriptA[counter] =  " "+scriptA[counter];
								int id = 383;
								int data = 0;
								switch(MonsterList.getString(MonsterName+".Type"))
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
								Stack(ChatColor.WHITE+MonsterName, id, data, 1,Arrays.asList(scriptA), count+1+(count2*9), inv);
								ItemMeta a = inv.getItem(count+1+(count2*9)).getItemMeta();
								a.addEnchant(Enchantment.SILK_TOUCH, 3, true);
								inv.getItem(count+1+(count2*9)).setItemMeta(a);
								isExit = true;
								break;
							}
						}
						if(isExit==false)
						{
							DungeonConfig.set(Type+"."+count, null);
							DungeonConfig.saveConfig();
							Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[����]", 383, 0, 1,Arrays.asList("",ChatColor.YELLOW + "[�� Ŭ���� ���]"), count+1+(count2*9), inv);
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
		Stack2(ChatColor.RED + "" + ChatColor.BOLD + "[����]", 166,0,1,Arrays.asList(ChatColor.GRAY + "���� ������ ���� �ʽ��ϴ�."), 2, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�Ϲ�]", 383,0,1,Arrays.asList(ChatColor.GRAY + "�Ϲ����� ���� �� �ϳ��� ���ϴ�."), 4, inv);
		Stack2(ChatColor.AQUA + "" + ChatColor.BOLD + "[Ŀ����]", 52,0,1,Arrays.asList(ChatColor.GRAY + "Ŀ���� ���� �� �ϳ��� ���ϴ�.","",ChatColor.RED+"[���� ũ����Ż ������ ���͸�",ChatColor.RED+"������ ���, ������ ������ �˴ϴ�.]"), 6, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+slot), 8, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);
		player.openInventory(inv);
	}
	
	public void DungeonSelectNormalMonsterChoose(Player player, String DungeonName, String Type, int slot)
	{
		String UniqueCode = "��0��0��a��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�Ϲ� ���� : " +DungeonName);

		Stack2(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[����]",397,2,1,null, 0, inv);
		Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[���̷���]",397,0,1,null, 1, inv);
		Stack2(ChatColor.GREEN + "" + ChatColor.BOLD + "[ũ����]",397,4,1,null, 2, inv);
		Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[�Ź�]",287,0,1,null, 3, inv);
		Stack2(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[���� �Ź�]",375,0,1,null, 4, inv);
		Stack2(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[������]",368,0,1,null, 5, inv);
		Stack2(ChatColor.GREEN + "" + ChatColor.BOLD + "[������]",341,0,1,null, 6, inv);
		Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[���׸� ť��]",378,0,1,null, 7, inv);
		Stack2(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "[����]",438,0,1,null, 8, inv);
		Stack2(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[���� �Ǳ׸�]",283,0,1,null, 9, inv);
		Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[������]",369,0,1,null, 10, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[����Ʈ]",370,0,1,null, 11, inv);
		Stack2(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "[��ȣ��]",410,0,1,null, 12, inv);
		Stack2(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[����]",1,0,1,null, 13, inv);
		Stack2(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "[����]",319,0,1,null, 14, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[��]",423,0,1,null, 15, inv);
		Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[��]",363,0,1,null, 16, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[��]",365,0,1,null, 17, inv);
		Stack2(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "[��¡��]",351,0,1,null, 18, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[����]",352,0,1,null, 19, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[���� ��]",40,0,1,null, 20, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������]",349,0,1,null, 21, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[��]",418,0,1,null, 22, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�䳢]",411,0,1,null, 23, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�ֹ�]",388,0,1,null, 24, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[�ϱذ�]",80,0,1,null, 25, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+slot), 53, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+Type), 45, inv);
		player.openInventory(inv);
	}
	
	public void DungeonSelectCustomMonsterChoose(Player player, String DungeonName, String Type, int slot, int page)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager MobList = YC.getNewConfig("Monster/MonsterList.yml");
		GBD_RPG.Battle.Battle_Calculator d = new GBD_RPG.Battle.Battle_Calculator();
		String UniqueCode = "��0��0��a��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0Ŀ���� ���� : " + (page+1));

		Object[] a= MobList.getKeys().toArray();

		int loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			if(count > a.length || loc >= 45) break;
			String MonsterName =a[count].toString();
			String Lore=null;
			
			Lore = "%enter%"+ChatColor.WHITE+""+ChatColor.BOLD+" �̸� : "+ChatColor.WHITE+MobList.getString(MonsterName+".Name")+"%enter%";
			Lore = Lore+ChatColor.WHITE+""+ChatColor.BOLD+" Ÿ�� : "+ChatColor.WHITE+MobList.getString(MonsterName+".Type")+"%enter%";
			Lore = Lore+ChatColor.WHITE+""+ChatColor.BOLD+" ���� ���̿� : "+ChatColor.WHITE+MobList.getString(MonsterName+".Biome")+"%enter%";
			Lore = Lore+ChatColor.RED+""+ChatColor.BOLD+" ����� : "+ChatColor.WHITE+MobList.getInt(MonsterName+".HP")+"%enter%";
			Lore = Lore+ChatColor.AQUA+""+ChatColor.BOLD+" ����ġ : "+ChatColor.WHITE+MobList.getInt(MonsterName+".EXP")+"%enter%";
			Lore = Lore+ChatColor.YELLOW+""+ChatColor.BOLD+" ��� �ݾ� : "+ChatColor.WHITE+MobList.getInt(MonsterName+".MIN_Money")+" ~ "+MobList.getInt(MonsterName+".MAX_Money")+"%enter%";
			Lore = Lore+ChatColor.RED+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : "+ChatColor.WHITE+MobList.getInt(MonsterName+".STR")
			+ChatColor.GRAY+ " [���� : " + d.CombatDamageGet(null, 0, MobList.getInt(MonsterName+".STR"), true) + " ~ " + d.CombatDamageGet(null, 0, MobList.getInt(MonsterName+".STR"), false) + "]%enter%";
			Lore = Lore+ChatColor.GREEN+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : "+ChatColor.WHITE+MobList.getInt(MonsterName+".DEX")
			+ChatColor.GRAY+ " [Ȱ�� : " + d.returnRangeDamageValue(null, MobList.getInt(MonsterName+".DEX"), 0, true) + " ~ " + d.returnRangeDamageValue(null, MobList.getInt(MonsterName+".DEX"), 0, false) + "]%enter%";
			Lore = Lore+ChatColor.DARK_AQUA+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : "+ChatColor.WHITE+MobList.getInt(MonsterName+".INT")
			+ChatColor.GRAY+ " [���� : " + (MobList.getInt(MonsterName+".INT")/4)+ " ~ "+(int)(MobList.getInt(MonsterName+".INT")/2.5)+"]%enter%";
			Lore = Lore+ChatColor.GRAY+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : "+ChatColor.WHITE+MobList.getInt(MonsterName+".WILL")
			+ChatColor.GRAY+ " [ũ�� : " + d.getCritical(null,MobList.getInt(MonsterName+".LUK"), (int)MobList.getInt(MonsterName+".WILL"),0) + " %]%enter%";
			Lore = Lore+ChatColor.YELLOW+""+ChatColor.BOLD+" "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : "+ChatColor.WHITE+MobList.getInt(MonsterName+".LUK")
			+ChatColor.GRAY+ " [ũ�� : " + d.getCritical(null,MobList.getInt(MonsterName+".LUK"), (int)MobList.getInt(MonsterName+".WILL"),0) + " %]%enter%";
			Lore = Lore+ChatColor.GRAY+""+ChatColor.BOLD+" ��� : "+ChatColor.WHITE+MobList.getInt(MonsterName+".DEF")+"%enter%";
			Lore = Lore+ChatColor.AQUA+""+ChatColor.BOLD+" ��ȣ : "+ChatColor.WHITE+MobList.getInt(MonsterName+".Protect")+"%enter%";
			Lore = Lore+ChatColor.BLUE+""+ChatColor.BOLD+" ���� ��� : "+ChatColor.WHITE+MobList.getInt(MonsterName+".Magic_DEF")+"%enter%";
			Lore = Lore+ChatColor.DARK_BLUE+""+ChatColor.BOLD+" ���� ��ȣ : "+ChatColor.WHITE+MobList.getInt(MonsterName+".Magic_Protect")+"%enter%";

			String[] scriptA = Lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			int id = 383;
			int data = 0;
			switch(MobList.getString(MonsterName+".Type"))
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
			loc=loc+1;
		}
		
		if(a.length-(page*44)>45)
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+Type), 45, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+slot,ChatColor.BLACK+DungeonName), 53, inv);
		player.openInventory(inv);
	}

	public void DungeonMusicSettingGUI(Player player, int page,String DungeonName, boolean isBOSS)
	{
		String UniqueCode = "��0��0��a��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����� : " + (page+1));
		int loc=0;
		int model = new GBD_RPG.Util.Util_Number().RandomNum(0, 11);
		for(int count = page*45; count < OtherPlugins.NoteBlockAPIMain.Musics.size();count++)
		{
			if(model<11)
				model=model+1;
			else
				model=0;
			String lore = " %enter%"+ChatColor.DARK_AQUA+"[Ʈ��] "+ChatColor.BLUE +""+ count+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE +""+ new OtherPlugins.NoteBlockAPIMain().getTitle(count)+"%enter%"
			+ChatColor.DARK_AQUA+"[����] "+ChatColor.BLUE+new OtherPlugins.NoteBlockAPIMain().getAuthor(count)+"%enter%"+ChatColor.DARK_AQUA+"[����] ";
			String Description = new OtherPlugins.NoteBlockAPIMain().getDescription(count);
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
			if(count > OtherPlugins.NoteBlockAPIMain.Musics.size() || loc >= 45) break;
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + count, 2256+model,0,1,Arrays.asList(lore.split("%enter%")), loc, inv);
			
			loc=loc+1;
		}
		
		if(OtherPlugins.NoteBlockAPIMain.Musics.size()-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+""+isBOSS), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+DungeonName), 53, inv);
		player.openInventory(inv);
	}
	//DungeonGUI//
	
	
	//EnterCardGUI//
	public void EnterCardSetUpGUI(Player player, String EnterCardName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");

		String UniqueCode = "��0��0��a��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0������ ����");
		if(DungeonConfig.getString(EnterCardName+".Dungeon")!= null)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������ ���� ����]", 52,0,1,Arrays.asList("",ChatColor.BLUE + "���� �̾��� ���� : "+ChatColor.WHITE+DungeonConfig.getString(EnterCardName+".Dungeon")), 2, inv);
		else
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������ ���� ����]", 166,0,1,Arrays.asList("",ChatColor.BLUE + "���� �̾��� ���� : "+ChatColor.WHITE+"����"), 2, inv);
			
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������ ���� ����]", DungeonConfig.getInt(EnterCardName+".ID"),DungeonConfig.getInt(EnterCardName+".DATA"),1,Arrays.asList("",ChatColor.BLUE + "���� ������ Ÿ�� : "+ChatColor.WHITE+DungeonConfig.getInt(EnterCardName+".ID")+":"+ DungeonConfig.getInt(EnterCardName+".DATA"),"",ChatColor.YELLOW+"[F3 + H �Է½� Ÿ�� Ȯ�� ����]"), 3, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������ ���� �ʱ�ȭ]", 325,0,1,Arrays.asList("",ChatColor.WHITE + "������ ���°� ��Ÿ���� ���� ��",ChatColor.WHITE+"������ �ʱ�ȭ �� �ݴϴ�."), 4, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������ ���� �ο�]", 397,3,1,Arrays.asList("",ChatColor.BLUE + "���� ���� �ο� : "+ChatColor.WHITE+DungeonConfig.getInt(EnterCardName+".Capacity")+" ��"), 5, inv);

		String Time = null;
		if(DungeonConfig.getInt(EnterCardName.toString()+".Hour")!=-1)
		{
			if(DungeonConfig.getInt(EnterCardName.toString()+".Hour")!=0)
				Time = DungeonConfig.getInt(EnterCardName.toString()+".Hour")+"�ð� ";
			if(DungeonConfig.getInt(EnterCardName.toString()+".Min")!=0)
				Time = Time+DungeonConfig.getInt(EnterCardName.toString()+".Min")+"�� ";
			Time = Time+DungeonConfig.getInt(EnterCardName.toString()+".Sec")+"��";
		}
		else
			Time = "������";
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "[������ ��ȿ �ð�]", 347,0,1,Arrays.asList("",ChatColor.WHITE+Time), 6, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+EnterCardName), 8, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);

		player.openInventory(inv);
	}

	public void EnterCardDungeonSettingGUI(Player player, int page, String EnterCardName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");

		String UniqueCode = "��0��0��a��0��9��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ���� : " + (page+1));
		Object[] DungeonList = DungeonConfig.getConfigurationSection("").getKeys(false).toArray();
		
		int loc=0;
		for(int count = page*45; count < DungeonList.length;count++)
		{
			YamlManager DungeonOptionYML = YC.getNewConfig("Dungeon/Dungeon/"+DungeonList[count].toString()+"/Option.yml");
			
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), 52,0,1,Arrays.asList(
			"",ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getString("Type.Name")
			,ChatColor.BLUE+"���� ũ�� : "+ChatColor.WHITE+DungeonOptionYML.getInt("Size")
			,ChatColor.BLUE+"���� ������ : "+ChatColor.WHITE+DungeonOptionYML.getInt("Maze_Level")
			,ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.Level")+" �̻�"
			,ChatColor.BLUE+"���� ���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.RealLevel")+" �̻�"
			,"",ChatColor.BLUE+"[�⺻ Ŭ���� ����]"
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.Money")+" "+GBD_RPG.Main_Main.Main_ServerOption.Money
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.EXP")+ChatColor.AQUA+" "+ChatColor.BOLD+"EXP"
			,"",ChatColor.YELLOW+"[�� Ŭ���� ����]"), loc, inv);
			
			loc=loc+1;
		}
		
		if(DungeonList.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+EnterCardName), 53, inv);
		player.openInventory(inv);
		return;
	}
	//EnterCardGUI//

	//AltarGUI//
	public void AltarShapeListGUI(Player player)
	{
		String UniqueCode = "��0��0��a��0��a��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ����");
		Stack2(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[�̳� �� ����]", 48,0,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"13��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"GoldBigDragon [���]"), 0, inv);
		Stack2(ChatColor.YELLOW + "" + ChatColor.BOLD + "[����ף]", 41,0,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"15��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"ComputerFairy [����]",ChatColor.WHITE+"GoldBigDragon [��]"), 1, inv);
		Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[���� ����]", 1,0,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"1�� 5��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"GoldBigDragon [���]"), 2, inv);
		Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[�غδ�]", 1,5,1,Arrays.asList("",ChatColor.BLUE + "ũ�� : "+ChatColor.WHITE+"����",ChatColor.BLUE+"���� ���� �ð� : "+ChatColor.WHITE+"8��","",ChatColor.GOLD+""+ChatColor.BOLD+"[     ���డ     ]",ChatColor.WHITE+"GoldBigDragon [���]"), 3, inv);
		Stack2(ChatColor.GRAY + "" + ChatColor.BOLD + "[�׽�Ʈ�� ����]", 48,0,1,null, 44, inv);
		
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}

	public void AltarSettingGUI(Player player, String AltarName)
	{
		String UniqueCode = "��0��0��a��0��b��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ����");

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager AltarList = YC.getNewConfig("Dungeon/AltarList.yml");
		YamlManager AltarConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
		Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[�̸� ����]", 421,0,1,Arrays.asList(ChatColor.GRAY+"������ �̸��� �����մϴ�.","",ChatColor.BLUE+"[���� ���� �̸�]",ChatColor.WHITE+AltarList.getString(AltarName+".Name")), 2, inv);
		if(AltarConfig.getString("NormalDungeon")==null)
			Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[�Ϲ� ����]", 166,0,1,Arrays.asList(ChatColor.GRAY+"�� ���ܿ� �������� ������",ChatColor.GRAY+"�������� ������ ��� �����Ǵ�",ChatColor.GRAY+"�Ϲ� ������ �����մϴ�.","",ChatColor.BLUE+"[���� ������ �Ϲ� ����]",ChatColor.WHITE+"�������� ����"), 4, inv);
		else
		{
			YamlManager DungeonList = YC.getNewConfig("Dungeon/DungeonList.yml");
			if(DungeonList.contains(AltarConfig.getString("NormalDungeon")))
				Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[�Ϲ� ����]", 52,0,1,Arrays.asList(ChatColor.GRAY+"���ܿ� �������� ������",ChatColor.GRAY+"�������� ������ ��� �����Ǵ�",ChatColor.GRAY+"�Ϲ� ������ �����մϴ�.","",ChatColor.BLUE+"[���� ������ �Ϲ� ����]",ChatColor.WHITE+AltarConfig.getString("NormalDungeon")), 4, inv);
			else
			{
				AltarConfig.set("NormalDungeon", null);
				AltarConfig.saveConfig();
				Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[�Ϲ� ����]", 166,0,1,Arrays.asList(ChatColor.GRAY+"���ܿ� �������� ������",ChatColor.GRAY+"�������� ������ ��� �����Ǵ�",ChatColor.GRAY+"�Ϲ� ������ �����մϴ�.","",ChatColor.BLUE+"[���� ������ �Ϲ� ����]",ChatColor.WHITE+"�������� ����"), 4, inv);
			}
		}
		Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[������ ���]", 358,0,1,Arrays.asList(ChatColor.GRAY+"���ܿ��� ��� ������",ChatColor.GRAY+"�������� ����մϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), 6, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+AltarName), 8, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);

		player.openInventory(inv);
		return;
	}
	
	public void AltarDungeonSettingGUI(Player player, int page, String AltarName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");

		String UniqueCode = "��0��0��a��0��c��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ���� : " + (page+1));
		Object[] DungeonList = DungeonConfig.getConfigurationSection("").getKeys(false).toArray();
		
		int loc=0;
		for(int count = page*45; count < DungeonList.length;count++)
		{
			YamlManager DungeonOptionYML = YC.getNewConfig("Dungeon/Dungeon/"+DungeonList[count].toString()+"/Option.yml");
			
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), 52,0,1,Arrays.asList(
			"",ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getString("Type.Name")
			,ChatColor.BLUE+"���� ũ�� : "+ChatColor.WHITE+DungeonOptionYML.getInt("Size")
			,ChatColor.BLUE+"���� ������ : "+ChatColor.WHITE+DungeonOptionYML.getInt("Maze_Level")
			,ChatColor.BLUE+"���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.Level")+" �̻�"
			,ChatColor.BLUE+"���� ���� ���� : "+ChatColor.WHITE+DungeonOptionYML.getInt("District.RealLevel")+" �̻�"
			,"",ChatColor.BLUE+"[�⺻ Ŭ���� ����]"
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.Money")+" "+GBD_RPG.Main_Main.Main_ServerOption.Money
			,ChatColor.BLUE+" - "+ChatColor.WHITE+DungeonOptionYML.getInt("Reward.EXP")+ChatColor.AQUA+" "+ChatColor.BOLD+"EXP"
			,"",ChatColor.YELLOW+"[�� Ŭ���� ����]"), loc, inv);
			
			loc=loc+1;
		}
		
		if(DungeonList.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",AltarName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AltarEnterCardSettingGUI(Player player, int page, String AltarName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager AlterConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");

		String UniqueCode = "��0��0��a��0��d��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��ϵ� ������ : " + (page+1));

		if(AlterConfig.getConfigurationSection("EnterCard").getKeys(false).size()!=0)
		{
			Object[] EnterCardList = AlterConfig.getConfigurationSection("EnterCard").getKeys(false).toArray();

			int loc=0;
			for(int count = page*45; count < EnterCardList.length;count++)
			{
				YamlManager EnterCard = YC.getNewConfig("Dungeon/EnterCardList.yml");
				if(EnterCard.contains(EnterCardList[count].toString()))
				{
					if(EnterCard.getString(EnterCardList[count].toString()+".Dungeon")==null)
						Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + EnterCardList[count].toString(), EnterCard.getInt(EnterCardList[count].toString()+".ID"),EnterCard.getInt(EnterCardList[count].toString()+".DATA"),1,Arrays.asList(
								"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
								ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+EnterCard.getInt(EnterCardList[count].toString()+".Capacity"),
								"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
					else
					{
						YamlManager Dungeon = YC.getNewConfig("Dungeon/DungeonList.yml");
						if(Dungeon.contains(EnterCard.getString(EnterCardList[count].toString()+".Dungeon")))
						{
							Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + EnterCardList[count].toString(), EnterCard.getInt(EnterCardList[count].toString()+".ID"),EnterCard.getInt(EnterCardList[count].toString()+".DATA"),1,Arrays.asList(
							"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+""+EnterCard.getString(EnterCardList[count].toString()+".Dungeon"),
							ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+EnterCard.getInt(EnterCardList[count].toString()+".Capacity"),
							"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
						}
						else
						{
							EnterCard.set(EnterCardList[count].toString()+".Dungeon",null);
							EnterCard.saveConfig();
							Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + EnterCardList[count].toString(), EnterCard.getInt(EnterCardList[count].toString()+".ID"),EnterCard.getInt(EnterCardList[count].toString()+".DATA"),1,Arrays.asList(
									"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
									ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+EnterCard.getInt(EnterCardList[count].toString()+".Capacity"),
									"",ChatColor.RED+"[Shift + ��Ŭ���� ��� ����]"), loc, inv);
						}
							
					}
					loc=loc+1;
				}
				else
				{
					AlterConfig.removeKey("EnterCard."+EnterCardList[count].toString());
					AlterConfig.saveConfig();
				}
			}
			if(EnterCardList.length-(page*44)>45)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		}
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "������ ���", 386,0,1,Arrays.asList(ChatColor.GRAY + "���ܿ� �������� ����մϴ�."), 49, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",AltarName), 53, inv);
		player.openInventory(inv);
		return;
	}
	
	public void AltarEnterCardListGUI(Player player, int page, String AltarName)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);

		String UniqueCode = "��0��0��a��0��e��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ������ ��� : " + (page+1));
		
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
		Object[] DungeonList = DungeonConfig.getConfigurationSection("").getKeys(false).toArray();
		int loc=0;
		for(int count = page*45; count < DungeonList.length;count++)
		{
			if(DungeonConfig.getString(DungeonList[count].toString()+".Dungeon")==null)
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), DungeonConfig.getInt(DungeonList[count].toString()+".ID"),DungeonConfig.getInt(DungeonList[count].toString()+".DATA"),1,Arrays.asList(
				"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
				ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+DungeonConfig.getInt(DungeonList[count].toString()+".Capacity"),
				"",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), loc, inv);
			else
			{
				YamlManager Dungeon = YC.getNewConfig("Dungeon/DungeonList.yml");
				if(Dungeon.contains(DungeonConfig.getString(DungeonList[count].toString()+".Dungeon")))
				{
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), DungeonConfig.getInt(DungeonList[count].toString()+".ID"),DungeonConfig.getInt(DungeonList[count].toString()+".DATA"),1,Arrays.asList(
					"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+""+DungeonConfig.getString(DungeonList[count].toString()+".Dungeon"),
					ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+DungeonConfig.getInt(DungeonList[count].toString()+".Capacity"),
					"",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), loc, inv);
				}
				else
				{
					DungeonConfig.set(DungeonList[count].toString()+".Dungeon",null);
					DungeonConfig.saveConfig();
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + DungeonList[count].toString(), DungeonConfig.getInt(DungeonList[count].toString()+".ID"),DungeonConfig.getInt(DungeonList[count].toString()+".DATA"),1,Arrays.asList(
					"",ChatColor.BLUE+"����� ���� : "+ChatColor.WHITE+"����",
					ChatColor.BLUE+"���� ���� �ο� : "+ChatColor.WHITE+""+DungeonConfig.getInt(DungeonList[count].toString()+".Capacity"),
					"",ChatColor.YELLOW+"[�� Ŭ���� ������ ���]"), loc, inv);
				}
			}
			loc=loc+1;
		}
		
		if(DungeonList.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",AltarName), 53, inv);
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

		Stack2(ChatColor.RED+""+ChatColor.BOLD+"[���� �ܷ�]", 166,0,1,null, 3, inv);
		Stack2(ChatColor.BLUE+""+ChatColor.BOLD+"[���� ����]", 138,0,1,null, 5, inv);
		player.openInventory(inv);
		return;
	}
	
	
	

	//DungeonGUI Click//
	public void DungeonListMainGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
				if(GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.isEmpty())
				{
					s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
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
					if(event.isLeftClick() == true)
						DungeonSetUpGUI(player, DungeonName);
					else if(event.isShiftClick() == true && event.isRightClick() == true)
					{
						s.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");
						DungeonConfig.removeKey(DungeonName);
						DungeonConfig.saveConfig();
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
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
						new GBD_RPG.Dungeon.Dungeon_Creater().CreateTestSeed(player, DungeonConfig.getInt("Size"), DungeonConfig.getInt("Maze_Level"), DungeonConfig.getString("Type.Name"));
					}
				}
				if(Type == 358)
				{
					String DungeonName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					if(event.isLeftClick() == true&&event.isShiftClick()==false)
						EnterCardSetUpGUI(player, DungeonName);
					else if(event.isShiftClick()&&event.isRightClick())
					{
						s.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
						DungeonConfig.removeKey(DungeonName);
						DungeonConfig.saveConfig();
						DungeonListMainGUI(player, page,Type);
					}
					else if(event.isShiftClick()&& event.isLeftClick())
					{
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
						ItemStack Icon = new MaterialData(DungeonConfig.getInt(DungeonName+".ID"), (byte) DungeonConfig.getInt(DungeonName+".DATA")).toItemStack(1);
						ItemMeta Icon_Meta = Icon.getItemMeta();
						Icon_Meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"[���� ������]");
						Calendar Today = Calendar.getInstance();
						String UseableTime = "[���� �ð� ����]";
						if(DungeonConfig.getInt(DungeonName+".Hour")!=-1)
						{
							Today.add(Calendar.MONTH, 1);
							Today.add(Calendar.HOUR, DungeonConfig.getInt(DungeonName+".Hour"));
							Today.add(Calendar.MINUTE, DungeonConfig.getInt(DungeonName+".Min"));
							Today.add(Calendar.SECOND, DungeonConfig.getInt(DungeonName+".Sec"));
							
							UseableTime = Today.get(Calendar.YEAR)+"�� "+Today.get(Calendar.MONTH)+"�� "+Today.get(Calendar.DATE)+"�� "+Today.get(Calendar.HOUR)+"�� "+Today.get(Calendar.MINUTE)+"�� "+Today.get(Calendar.SECOND)+"�� ����";
						}
						Icon_Meta.setLore(Arrays.asList("",ChatColor.RED+DungeonName,"",ChatColor.RED+"�ο� : "+ChatColor.WHITE+DungeonConfig.getInt(DungeonName+".Capacity"),"",ChatColor.WHITE+""+UseableTime));
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
						s.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager DungeonConfig = YC.getNewConfig("Dungeon/AltarList.yml");
						Location loc = new Location(Bukkit.getServer().getWorld(DungeonConfig.getString(DungeonName+".World")), DungeonConfig.getInt(DungeonName+".X"), DungeonConfig.getInt(DungeonName+".Y"), DungeonConfig.getInt(DungeonName+".Z"));
						int radius = DungeonConfig.getInt(DungeonName+".radius");
						Object[] EntitiList = Bukkit.getServer().getWorld(DungeonConfig.getString(DungeonName+".World")).getNearbyEntities(loc, radius, radius, radius).toArray();
						for(int count=0; count<EntitiList.length;count++)
							if(((Entity)EntitiList[count]).getCustomName()!=null)
								if(((Entity)EntitiList[count]).getCustomName().compareTo(DungeonName)==0)
									((Entity)EntitiList[count]).remove();
						DungeonConfig.removeKey(DungeonName);
						DungeonConfig.saveConfig();
						File file = new File("plugins/GoldBigDragonRPG/Dungeon/Altar/"+DungeonName+".yml");
						file.delete();
						DungeonListMainGUI(player, page,Type);
					}
					else if(event.isShiftClick() == true && event.isLeftClick() == true)
					{
						s.SP(player, Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager DungeonConfig = YC.getNewConfig("Dungeon/AltarList.yml");
						Location loc = new Location(Bukkit.getServer().getWorld(DungeonConfig.getString(DungeonName+".World")), DungeonConfig.getInt(DungeonName+".X"), DungeonConfig.getInt(DungeonName+".Y"), DungeonConfig.getInt(DungeonName+".Z"));
						player.teleport(loc);
						s.SP(player, Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
					}
				}
			}
		}
	}
	
	public void DungeonSetUpGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 44)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);
			if(slot == 36)//���� ���
				DungeonListMainGUI(player, 0, 52);
			else if(slot == 11)//���� Ÿ��
			{
				if(GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.isEmpty())
				{
					s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[SYSTEM] : ���� �׸��� ã�� �� �����ϴ�!");
					player.sendMessage(ChatColor.YELLOW+"(���� �׸� �ٿ�ε� : "+ChatColor.GOLD+"http://cafe.naver.com/goldbigdragon/56713"+ChatColor.YELLOW+")");
					return;
				}
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
				String DungeonTheme = DungeonConfig.getString("Type.Name");
				if(GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.contains(DungeonTheme)==false)
					DungeonConfig.set("Type.Name", GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.get(0));
				else
				{
					for(int count = 0; count < GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.size(); count++)
						if(GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.get(count).compareTo(DungeonTheme)==0)
						{
							if(count+1 >= GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.size())
								DungeonConfig.set("Type.Name", GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.get(0));
							else
								DungeonConfig.set("Type.Name", GBD_RPG.Main_Main.Main_ServerOption.DungeonTheme.get(count+1));
						}
				}
				DungeonConfig.saveConfig();
				DungeonSetUpGUI(player, DungeonName);
			}
			else if(slot == 24)//���� ����
			{
				s.SP(player, Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				DungeonChestReward(player, DungeonName);
			}
			else if(slot == 29)//����
			{
				s.SP(player, Sound.ENTITY_WOLF_AMBIENT, 0.8F, 1.0F);
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
					DungeonConfig.removeKey(Type+"."+Slot);
					DungeonConfig.saveConfig();
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();

		int slot = event.getSlot();
		
		if(slot==53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);
			int Slot = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot==45)
				DungeonMonsterChooseMain(player, DungeonName, Slot);
			else
			{
				String Type = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
				if(slot==0)
					DungeonConfig.set(Type+"."+Slot, "������");
				else if(slot==1)
					DungeonConfig.set(Type+"."+Slot, "�ｺ�̷���");
				else if(slot==2)
					DungeonConfig.set(Type+"."+Slot, "��ũ����");
				else if(slot==3)
					DungeonConfig.set(Type+"."+Slot, "��Ź�");
				else if(slot==4)
					DungeonConfig.set(Type+"."+Slot, "�ﵿ���Ź�");
				else if(slot==5)
					DungeonConfig.set(Type+"."+Slot, "�￣����");
				else if(slot==6)
					DungeonConfig.set(Type+"."+Slot, "�ｽ����");
				else if(slot==7)
					DungeonConfig.set(Type+"."+Slot, "�︶�׸�ť��");
				else if(slot==8)
					DungeonConfig.set(Type+"."+Slot, "�︶��");
				else if(slot==9)
					DungeonConfig.set(Type+"."+Slot, "�������Ǳ׸�");
				else if(slot==10)
					DungeonConfig.set(Type+"."+Slot, "�������");
				else if(slot==11)
					DungeonConfig.set(Type+"."+Slot, "�ﰡ��Ʈ");
				else if(slot==12)
					DungeonConfig.set(Type+"."+Slot, "���ȣ��");
				else if(slot==13)
					DungeonConfig.set(Type+"."+Slot, "�����");
				else if(slot==14)
					DungeonConfig.set(Type+"."+Slot, "�����");
				else if(slot==15)
					DungeonConfig.set(Type+"."+Slot, "���");
				else if(slot==16)
					DungeonConfig.set(Type+"."+Slot, "���");
				else if(slot==17)
					DungeonConfig.set(Type+"."+Slot, "���");
				else if(slot==18)
					DungeonConfig.set(Type+"."+Slot, "���¡��");
				else if(slot==19)
					DungeonConfig.set(Type+"."+Slot, "�����");
				else if(slot==20)
					DungeonConfig.set(Type+"."+Slot, "�������");
				else if(slot==21)
					DungeonConfig.set(Type+"."+Slot, "�������");
				else if(slot==22)
					DungeonConfig.set(Type+"."+Slot, "�︻");
				else if(slot==23)
					DungeonConfig.set(Type+"."+Slot, "���䳢");
				else if(slot==24)
					DungeonConfig.set(Type+"."+Slot, "���ֹ�");
				else if(slot==25)
					DungeonConfig.set(Type+"."+Slot, "��ϱذ�");
				DungeonConfig.saveConfig();
				DungeonMonsterGUIMain(player, DungeonName);
			}
		}
	}
	
	public void DungeonSelectCustomMonsterChooseClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			int Slot = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			String Type = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			String DungeonName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(2));
			
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
			if(slot == 45)//���� ���
				DungeonMonsterChooseMain(player, DungeonName, Slot);
			else if(slot == 48)//���� ������
				DungeonSelectCustomMonsterChoose(player, DungeonName, Type, Slot, page-1);
			else if(slot == 50)//���� ������
				DungeonSelectCustomMonsterChoose(player, DungeonName, Type, Slot, page+1);
			else if(event.getCurrentItem().getTypeId()==383)
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
				DungeonConfig.set(Type+"."+Slot, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				DungeonConfig.saveConfig();
				DungeonMonsterGUIMain(player, DungeonName);
			}
		}
	}
	
	public void DungeonMusicSettingGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String DungeonName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			boolean isBoss = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1)));
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
					s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
					if(isBoss)
						DungeonConfig.set("BGM.BOSS", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
					else
						DungeonConfig.set("BGM.Normal", Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
					DungeonConfig.saveConfig();
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String EnterCardName = ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getLore().get(1));
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)//���� ���
				DungeonListMainGUI(player, 0, 358);
			else if(slot == 2)//���� ����
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");
				if(DungeonConfig.getKeys().size()==0)
				{
					s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[����] : ������ ������ �����ϴ�! ������ ���� ����� ������!");
				}
				else
					EnterCardDungeonSettingGUI(player, 0, EnterCardName);
			}
			else if(slot == 4)//������ ���� �ʱ�ȭ
			{
				s.SP(player, Sound.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.8F);
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
				DungeonConfig.set(EnterCardName+".ID",358);
				DungeonConfig.set(EnterCardName+".DATA",0);
				DungeonConfig.saveConfig();
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String EnterCardName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				EnterCardSetUpGUI(player, EnterCardName);
			else
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
				DungeonConfig.set(EnterCardName+".Dungeon", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				DungeonConfig.saveConfig();
				EnterCardSetUpGUI(player, EnterCardName);
			}
		}
	}
	//EnterCardGUI Click//

	
	//AltarGUI Click//
	public void AltarShapeListGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				DungeonListMainGUI(player, 0, 120);
			else
			{
				if(ServerTick_Main.ServerTask.compareTo("null")!=0)
				{
					s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[Server] : ���� ������ "+ChatColor.YELLOW+ServerTick_Main.ServerTask+ChatColor.RED+" �۾� ���Դϴ�.");
					return;
				}
				player.closeInventory();
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager AltarList = YC.getNewConfig("Dungeon/AltarList.yml");
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
				AltarList = YC.getNewConfig("Dungeon/Altar/"+Salt+".yml");
				AltarList.createSection("EnterCard");
				AltarList.saveConfig();
				new GBD_RPG.Structure.Structure_Main().CreateSturcture(player, Salt, (short) (101+event.getSlot()), 4);
			}
		}
	}

	public void AltarSettingGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		String AltarName = event.getInventory().getItem(8).getItemMeta().getLore().get(1);
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 8)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)//���� ���
				DungeonListMainGUI(player, 0, 120);
			else if(slot == 2)//�̸� ����
			{
				UserData_Object u = new UserData_Object();
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		String AltarName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2, event.getInventory().getItem(53).getItemMeta().getLore().get(1).length());
		int slot = event.getSlot();
		
		if(slot == 53)
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)
				AltarSettingGUI(player, AltarName);
			else
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
				DungeonConfig.set("NormalDungeon", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				DungeonConfig.saveConfig();
				AltarSettingGUI(player, AltarName);
			}
		}
	}
	
	public void AltarEnterCardSettingGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		String AltarName = event.getInventory().getItem(53).getItemMeta().getLore().get(1);
		int slot = event.getSlot();

		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
				s.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
				DungeonConfig.removeKey("EnterCard."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				DungeonConfig.saveConfig();
				AltarEnterCardSettingGUI(player, page, AltarName);
				return;
			}
		}
	}
	
	public void AltarEnterCardListGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();

		String AltarName = event.getInventory().getItem(53).getItemMeta().getLore().get(1);
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			if(slot == 45)//���� ���
				AltarEnterCardSettingGUI(player, 0, AltarName);
			else if(slot == 48)//���� ������
				AltarEnterCardListGUI(player, page-1, AltarName);
			else if(slot == 50)//���� ������
				AltarEnterCardListGUI(player, page+1, AltarName);
			else
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
				DungeonConfig.createSection("EnterCard."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				DungeonConfig.saveConfig();
				AltarEnterCardSettingGUI(player, page, AltarName);
			}
		}
	}
	
	public String getRandomCode()
	{
		int randomNum = new GBD_RPG.Util.Util_Number().RandomNum(0, 15);
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
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();

		int slot = event.getSlot();
		player.closeInventory();
		if(slot == 3)
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
		else if(slot == 5)
		{
			new GBD_RPG.Dungeon.Dungeon_Main().EraseAllDungeonKey(player, true);
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
			String DungeonName = GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter();
			long UTC = GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_UTC();
			YamlManager PlayerConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Entered/"+UTC+".yml");
			if(PlayerConfig.contains("EnteredAlter"))
			{
				DungeonName = PlayerConfig.getString("EnteredAlter");
				PlayerConfig = YC.getNewConfig("Dungeon/AltarList.yml");
				if(PlayerConfig.contains(DungeonName))
				{
					Location loc = new Location(Bukkit.getServer().getWorld(PlayerConfig.getString(DungeonName+".World")), PlayerConfig.getLong(DungeonName+".X"), PlayerConfig.getLong(DungeonName+".Y")+1, PlayerConfig.getLong(DungeonName+".Z"));
					player.teleport(loc);
					return;
				}
			}
			new GBD_RPG.Util.Util_Player().teleportToCurrentArea(player, true);
			return;
		}
	}
	
	//DungeonGUI Close//
	public void DungeonChestRewardClose(InventoryCloseEvent event)
	{
		String DungeonName = ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]);

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Reward.yml");
		
		for(int count = 0; count < 8; count++)
		{
			if(event.getInventory().getItem(count+1)!=null)
				DungeonConfig.set("100."+count, event.getInventory().getItem(count+1));
			else
				DungeonConfig.removeKey("100."+count);
			if(event.getInventory().getItem(count+10)!=null)
				DungeonConfig.set("90."+count, event.getInventory().getItem(count+10));	
			else
				DungeonConfig.removeKey("90."+count);
			if(event.getInventory().getItem(count+19)!=null)
				DungeonConfig.set("50."+count, event.getInventory().getItem(count+19));	
			else
				DungeonConfig.removeKey("50."+count);
			if(event.getInventory().getItem(count+28)!=null)
				DungeonConfig.set("10."+count, event.getInventory().getItem(count+28));
			else
				DungeonConfig.removeKey("10."+count);
			if(event.getInventory().getItem(count+37)!=null)
				DungeonConfig.set("1."+count, event.getInventory().getItem(count+37));
			else
				DungeonConfig.removeKey("1."+count);	
			if(event.getInventory().getItem(count+46)!=null)
				DungeonConfig.set("0."+count, event.getInventory().getItem(count+46));
			else
				DungeonConfig.removeKey("0."+count);
		}
		DungeonConfig.saveConfig();

		new GBD_RPG.Effect.Effect_Sound().SP((Player) event.getPlayer(), Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
		event.getPlayer().sendMessage(ChatColor.GREEN+"[����] : ���� ���� �Ϸ�!");
	}
	
	public void AltarUSEGuiClose(InventoryCloseEvent event)
	{
		ItemStack item = event.getInventory().getItem(4);
		if(item!=null)
		{
			String AltarName = event.getInventory().getItem(0).getItemMeta().getDisplayName();
			Player player = (Player) event.getPlayer();
		  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
			YamlManager AltarConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
			event.getInventory().setItem(4, null);
			GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
			int LvDistrict = -1;
			int RealLvDistrict = -1;
			if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter() != null)
			{
				if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
					new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.WHITE+"(�̹� ������ ����� ���� �ִ�...)");
				return;
			}
			if(item.hasItemMeta())
			{
				if(item.getItemMeta().hasDisplayName())
				{
					if(item.getItemMeta().getDisplayName().compareTo(ChatColor.RED+""+ChatColor.BOLD+"[���� ������]")==0)
					{
						if(AltarConfig.contains("EnterCard."+ChatColor.stripColor(item.getItemMeta().getLore().get(1))))
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
								YamlManager EnterCardConfig = YC.getNewConfig("Dungeon/EnterCardList.yml");
								LvDistrict = YC.getNewConfig("Dungeon/Dungeon/"+EnterCardConfig.getString(ChatColor.stripColor(item.getItemMeta().getLore().get(1))+".Dungeon")+"/Option.yml").getInt("District.Level");
								RealLvDistrict = YC.getNewConfig("Dungeon/Dungeon/"+EnterCardConfig.getString(ChatColor.stripColor(item.getItemMeta().getLore().get(1))+".Dungeon")+"/Option.yml").getInt("District.RealLevel");
								if(EnterCardConfig.contains(ChatColor.stripColor(item.getItemMeta().getLore().get(1))))
								{
									PartyEnterDungeon(player, item, AltarName, capacity, EnterCardConfig.getString(ChatColor.stripColor(item.getItemMeta().getLore().get(1))+".Dungeon"), LvDistrict, RealLvDistrict);
								}
								else
								{
									if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
										new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
									s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
									player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
									return;
								}
							}
							else
							{
								if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
									new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
								s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
								player.sendMessage(ChatColor.WHITE+"(���� �������� ��ȿ�ð��� ������...)");
								return;
							}
						}
						else
						{
							if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
								new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
							s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
							return;
						}
					}
					else
					{
						if(AltarConfig.getString("NormalDungeon")!=null)
						{
							YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");
							if(DungeonConfig.contains(AltarConfig.getString("NormalDungeon")))
								PartyEnterDungeon(player, item, AltarName, -1, AltarConfig.getString("NormalDungeon"), LvDistrict, RealLvDistrict);
							else
							{
								AltarConfig.set("NormalDungeon", null);
								AltarConfig.saveConfig();
								if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
									new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
								s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
								player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
								return;
							}
						}
						else
						{
							if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
								new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
							s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
							return;
						}
					}
				}
				return;
			}
			if(AltarConfig.getString("NormalDungeon")!=null)
			{
				YamlManager DungeonConfig = YC.getNewConfig("Dungeon/DungeonList.yml");
				if(DungeonConfig.contains(AltarConfig.getString("NormalDungeon")))
					PartyEnterDungeon(player, item, AltarName, -1, AltarConfig.getString("NormalDungeon"), LvDistrict, RealLvDistrict);
				else
				{
					AltarConfig.set("NormalDungeon", null);
					AltarConfig.saveConfig();
					if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
						new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
					s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
					return;
				}
			}
			else
			{
				if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
					new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.WHITE+"(�� ������ ������ ��ĥ �� ���� �� �ϴ�...)");
				return;
			}
		}
	}

	private void PartyEnterDungeon(Player player, ItemStack item, String AltarName, int capacity, String DungeonName, int LvDistrict, int RealLvDistrict)
	{
		if(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.containsKey(player))
		{
			GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
			if(capacity!=-1)
				if(GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).getPartyMembers()!=capacity)
				{
					if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
						new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
					s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.RED + "[����] : ���� ���� �ο��� ���� �ʽ��ϴ�! ("+capacity+"��)");
					return;
				}
		  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
			YamlManager AltarConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
			YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+AltarConfig.getString("NormalDungeon")+"/Option.yml");
			if(LvDistrict==-1)
				LvDistrict = DungeonConfig.getInt("District.Level");
			if(RealLvDistrict==-1)
				RealLvDistrict = DungeonConfig.getInt("District.RealLevel");
			if(DungeonName!=null)
				DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
			long UTC = GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player);
			if(GBD_RPG.Main_Main.Main_ServerOption.Party.get(UTC).getLeader().compareTo(player.getName())==0)
			{
				//��Ƽ�� �߰��ϱ�//
				ArrayList<Player> NearPartyMember = new ArrayList<Player>();
				GBD_RPG.Main_Main.Main_ServerOption.Party.get(UTC).getMember();
				for(int count = 0; count < GBD_RPG.Main_Main.Main_ServerOption.Party.get(UTC).getPartyMembers(); count++)
				{
					if(player.getWorld().getName().compareTo(GBD_RPG.Main_Main.Main_ServerOption.Party.get(UTC).getMember()[count].getWorld().getName())==0)
						if(player.getLocation().distance(GBD_RPG.Main_Main.Main_ServerOption.Party.get(UTC).getMember()[count].getLocation()) < 11)
							NearPartyMember.add(GBD_RPG.Main_Main.Main_ServerOption.Party.get(UTC).getMember()[count]);
				}
				for(int count = 0; count < NearPartyMember.size(); count++)
				{
					if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(NearPartyMember.get(count).getUniqueId().toString()).getStat_Level()< LvDistrict)
					{
						if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
							new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
						s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED + "[����] : ��Ƽ�� "+NearPartyMember.get(count).getName()+"���� ������ ���� ������ ������ �� �����ϴ�!");
						player.sendMessage(ChatColor.RED + "(���� ���� : "+DungeonConfig.getInt("District.Level")+")");
						return;
					}
					if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(NearPartyMember.get(count).getUniqueId().toString()).getStat_RealLevel()<RealLvDistrict)
					{
						if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
							new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
						s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED + "[����] : ��Ƽ�� "+NearPartyMember.get(count).getName()+"���� ���� ������ ���� ������ ������ �� �����ϴ�!");
						player.sendMessage(ChatColor.RED + "(���� ���� ���� : "+DungeonConfig.getInt("District.RealLevel")+")");
						return;
					}
				}
				if(new GBD_RPG.Dungeon.Dungeon_Creater().CreateDungeon(player, DungeonConfig.getInt("Size"), DungeonConfig.getInt("Maze_Level"), DungeonConfig.getString("Type.Name"),DungeonName,NearPartyMember, AltarName, item)==false)
				{
					if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
						new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
					s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
					return;
				}
			}
			else
			{
				if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
					new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED+"[��Ƽ] : ��Ƽ�� ������ ���ܿ� ������ ��ĥ �� �ֽ��ϴ�!");
				return;
			}
		}
		else
			SoloEnterDungeon(player, item, AltarName, capacity, DungeonName, LvDistrict, RealLvDistrict);
	}
	
	private void SoloEnterDungeon(Player player, ItemStack item, String AltarName, int capacity, String DungeonName, int LvDistrict, int RealLvDistrict)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		if(capacity==-1||capacity==1)
		{
		  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
			YamlManager AltarConfig = YC.getNewConfig("Dungeon/Altar/"+AltarName+".yml");
			YamlManager DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+AltarConfig.getString("NormalDungeon")+"/Option.yml");
			if(DungeonName!=null)
				DungeonConfig = YC.getNewConfig("Dungeon/Dungeon/"+DungeonName+"/Option.yml");
			if(LvDistrict==-1)
				LvDistrict = DungeonConfig.getInt("District.Level");
			if(RealLvDistrict==-1)
				RealLvDistrict = DungeonConfig.getInt("District.RealLevel");
			if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level()< LvDistrict)
			{
				if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
					new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED + "[����] : ����� ������ ���� ������ ������ �� �����ϴ�!");
				player.sendMessage(ChatColor.RED + "(���� ���� : "+DungeonConfig.getInt("District.Level")+")");
				return;
			}
			if(GBD_RPG.Main_Main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel()<RealLvDistrict)
			{
				if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
					new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED + "[����] : ����� ���� ������ ���� ������ ������ �� �����ϴ�!");
				player.sendMessage(ChatColor.RED + "(���� ���� ���� : "+DungeonConfig.getInt("District.RealLevel")+")");
				return;
			}
			if(new GBD_RPG.Dungeon.Dungeon_Creater().CreateDungeon(player, DungeonConfig.getInt("Size"), DungeonConfig.getInt("Maze_Level"), DungeonConfig.getString("Type.Name"),DungeonName,null, AltarName, item)==false)
			{
				if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
					new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
				s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
				return;
			}
		}
		else
		{
			if(new GBD_RPG.Util.Util_Player().giveItem(player, item)==false)
				new GBD_RPG.Main_Event.Main_ItemDrop().CustomItemDrop(player.getLocation(), item);
			s.SP(player, Sound.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
			player.sendMessage(ChatColor.RED + "[����] : ���� ���� �ο��� ���� �ʽ��ϴ�! ("+capacity+"��)");
			return;
		}
	}
	//DungeonGUI Close//

}
