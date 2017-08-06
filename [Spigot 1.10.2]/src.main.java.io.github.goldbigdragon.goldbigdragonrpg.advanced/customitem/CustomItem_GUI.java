package customitem;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import admin.OPbox_GUI;
import effect.SoundEffect;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;



public class CustomItem_GUI extends Util_GUI
{
	public void ItemListGUI(Player player, int page)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/ItemList.yml");

		String UniqueCode = "��0��0��3��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ��� : " + (page+1));

		int itemAmounts = itemYaml.getKeys().size();

		byte loc=0;
		for(int count = page*45; count < itemAmounts;count++)
		{
			int number = ((page*45)+loc);
			if(count > itemAmounts || loc >= 45) break;
			String ItemName = itemYaml.getString(number+".DisplayName");
			int ItemID = itemYaml.getInt(number+".ID");
			int ItemData = itemYaml.getInt(number+".Data");
			Stack2(ItemName, ItemID, ItemData, 1,Arrays.asList(LoreCreater(number)), loc, inv);
			
			loc++;
		}
		
		if(itemAmounts-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ������", 145,0,1,Arrays.asList(ChatColor.GRAY + "���ο� �������� �����մϴ�."), 49, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void NewItemGUI(Player player, int number)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/ItemList.yml");

		String UniqueCode = "��0��0��3��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ �� ����");
		String ItemName = itemYaml.getString(number+".DisplayName");
		short ItemID = (short) itemYaml.getInt(number+".ID");
		byte ItemData = (byte) itemYaml.getInt(number+".Data");

		String Type = "";
		String Grade = "";
		for(int counter =0;counter < 13 - itemYaml.getString(number+".Type").length();counter++ )
			Type = Type +" ";
		Type = Type +itemYaml.getString(number+".Type");
		Grade = itemYaml.getString(number+".Grade");
		
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 9, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 10, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 11, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 18, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 20, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 27, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 28, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 29, inv);
		Stack2(ItemName, ItemID,ItemData,1,Arrays.asList(LoreCreater(number)), 19, inv);
		
		Stack2(ChatColor.DARK_AQUA + "[    ���� ����    ]", 421,0,1,Arrays.asList(ChatColor.WHITE+"������ ����â��",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� ����    ]","       "+ itemYaml.getString(number+".ShowType"),""), 37, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �̸� ����    ]", 421,0,1,Arrays.asList(ChatColor.WHITE+"�������� �̸���",ChatColor.WHITE+"�����մϴ�.",""), 13, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ���� ����    ]", 386,0,1,Arrays.asList(ChatColor.WHITE+"�������� ������",ChatColor.WHITE+"�����մϴ�.",""), 14, inv);
		Stack2(ChatColor.DARK_AQUA + "[    Ÿ�� ����    ]", 61,0,1,Arrays.asList(ChatColor.WHITE+"�������� Ÿ����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� Ÿ��    ]",Type,""), 15, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ��� ����    ]", 266,0,1,Arrays.asList(ChatColor.WHITE+"�������� �����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� ���    ]","       "+Grade,""), 16, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ID        ]", 405,0,1,Arrays.asList(ChatColor.WHITE+"�������� ID����",ChatColor.WHITE+"�����մϴ�.",""), 22, inv);
		Stack2(ChatColor.DARK_AQUA + "[       DATA       ]", 336,0,1,Arrays.asList(ChatColor.WHITE+"�������� DATA����",ChatColor.WHITE+"�����մϴ�.",""), 23, inv);
		Stack2(ChatColor.DARK_AQUA + "[       "+main.Main_ServerOption.Damage+"       ]", 267,0,1,Arrays.asList(ChatColor.WHITE+"�������� "+main.Main_ServerOption.Damage+"��",ChatColor.WHITE+"�����մϴ�.",""), 24, inv);
		Stack2(ChatColor.DARK_AQUA + "[     "+main.Main_ServerOption.MagicDamage+"     ]", 403,0,1,Arrays.asList(ChatColor.WHITE+"�������� "+main.Main_ServerOption.MagicDamage+"��",ChatColor.WHITE+"�����մϴ�.",""), 25, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ���        ]", 307,0,1,Arrays.asList(ChatColor.WHITE+"�������� ������",ChatColor.WHITE+"�����մϴ�.",""), 31, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ��ȣ        ]", 306,0,1,Arrays.asList(ChatColor.WHITE+"�������� ��ȣ��",ChatColor.WHITE+"�����մϴ�.",""), 32, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ���      ]", 311,0,1,Arrays.asList(ChatColor.WHITE+"�������� ���� ��",ChatColor.WHITE+"�����մϴ�.",""), 33, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ��ȣ      ]", 310,0,1,Arrays.asList(ChatColor.WHITE+"�������� ���� ��ȣ��",ChatColor.WHITE+"�����մϴ�.",""), 34, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ����        ]", 399,0,1,Arrays.asList(ChatColor.WHITE+"�������� ���ʽ� ������",ChatColor.WHITE+"�����մϴ�.",""), 40, inv);
		Stack2(ChatColor.DARK_AQUA + "[       ������       ]", 145,2,1,Arrays.asList(ChatColor.WHITE+"�������� ��������",ChatColor.WHITE+"�����մϴ�.","",ChatColor.RED+"[0 ������ �Ϲ� ������ ������ ���]",""), 41, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ����        ]", 145,0,1,Arrays.asList(ChatColor.WHITE+"�������� �ִ� ���� Ƚ����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.RED+"[0 ������ ���� �Ұ���]",""), 42, inv);
		Stack2(ChatColor.DARK_AQUA + "[         ��         ]", 381,0,1,Arrays.asList(ChatColor.WHITE+"�������� �ִ� ������",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"�ִ� "+itemYaml.getInt(number+".MaxSocket")+" ��","",ChatColor.RED+"[0 ������ �� ���� �Ұ���]",""), 43, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ����      ]", 166,0,1,Arrays.asList(ChatColor.WHITE+"������ ������ ������",ChatColor.WHITE+"�ɾ�Ӵϴ�.",""), 49, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ����      ]", 397,3,1,Arrays.asList(ChatColor.WHITE+"������ ������ ������",ChatColor.WHITE+"�ɾ�Ӵϴ�.",ChatColor.RED+"[�� Ŭ���� ����]",""), 50, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+number), 53, inv);
		player.openInventory(inv);
	}
	
	public void JobListGUI(Player player, short page, int number)
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");
	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");

		String UniqueCode = "��0��0��3��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ���� ���� : " + (page+1));

		String[] jobList = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < jobList.length;count++)
		{
			int JobLevel= jobYaml.getConfigurationSection("MapleStory."+jobList[count]).getKeys(false).size();
			
			if(count > jobList.length || loc >= 45) break;
			
			if(jobList[count].equalsIgnoreCase(configYaml.getString("Server.DefaultJob")))
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + jobList[count], 403,0,1,Arrays.asList(ChatColor.DARK_AQUA+"�ִ� �±� : " + ChatColor.WHITE+JobLevel+ChatColor.DARK_AQUA+"�� �±�","",ChatColor.YELLOW+"[��Ŭ���� ���� ����]","��e��l[���� �⺻ ����]"), loc, inv);
			else
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + jobList[count], 340,0,1,Arrays.asList(ChatColor.DARK_AQUA+"�ִ� �±� : " + ChatColor.WHITE+JobLevel+ChatColor.DARK_AQUA+"�� �±�","",ChatColor.YELLOW+"[��Ŭ���� ���� ����]"), loc, inv);
			
			loc++;
		}
		
		if(jobList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+number), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	public void ItemListInventoryclick(InventoryClickEvent event)
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
			short page = (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player,(byte) 1);
			else if(slot == 48)//���� ������
				ItemListGUI(player,page-1);
			else if(slot == 50)//���� ������
				ItemListGUI(player,page+1);
			else
			{
			  	YamlLoader itemYaml = new YamlLoader();
				itemYaml.getConfig("Item/ItemList.yml");
				if(slot == 49)//�� ������
				{
					int ItemCounter = itemYaml.getKeys().size();
					itemYaml.set(ItemCounter+".ShowType",ChatColor.WHITE+"[���]");
					itemYaml.set(ItemCounter+".ID",267);
					itemYaml.set(ItemCounter+".Data",0);
					itemYaml.set(ItemCounter+".DisplayName",ChatColor.WHITE+"��� ���� ������ ��");
					itemYaml.set(ItemCounter+".Lore",ChatColor.WHITE+"��� ������� �����̴�.%enter%"+ChatColor.WHITE+"������ ����������...");
					itemYaml.set(ItemCounter+".Type",ChatColor.RED+"[���� ����]");
					itemYaml.set(ItemCounter+".Grade",ChatColor.WHITE+"[�Ϲ�]");
					itemYaml.set(ItemCounter+".MinDamage",1);
					itemYaml.set(ItemCounter+".MaxDamage",7);
					itemYaml.set(ItemCounter+".MinMaDamage",0);
					itemYaml.set(ItemCounter+".MaxMaDamage",0);
					itemYaml.set(ItemCounter+".DEF",0);
					itemYaml.set(ItemCounter+".Protect",0);
					itemYaml.set(ItemCounter+".MaDEF",0);
					itemYaml.set(ItemCounter+".MaProtect",0);
					itemYaml.set(ItemCounter+".Durability",256);
					itemYaml.set(ItemCounter+".MaxDurability",256);
					itemYaml.set(ItemCounter+".STR",0);
					itemYaml.set(ItemCounter+".DEX",0);
					itemYaml.set(ItemCounter+".INT",0);
					itemYaml.set(ItemCounter+".WILL",0);
					itemYaml.set(ItemCounter+".LUK",0);
					itemYaml.set(ItemCounter+".Balance",10);
					itemYaml.set(ItemCounter+".Critical",5);
					itemYaml.set(ItemCounter+".MaxUpgrade",5);
					itemYaml.set(ItemCounter+".MaxSocket",3);
					itemYaml.set(ItemCounter+".HP",0);
					itemYaml.set(ItemCounter+".MP",0);
					itemYaml.set(ItemCounter+".JOB","����");
					itemYaml.set(ItemCounter+".MinLV",0);
					itemYaml.set(ItemCounter+".MinRLV",0);
					itemYaml.set(ItemCounter+".MinSTR",0);
					itemYaml.set(ItemCounter+".MinDEX",0);
					itemYaml.set(ItemCounter+".MinINT",0);
					itemYaml.set(ItemCounter+".MinWILL",0);
					itemYaml.set(ItemCounter+".MinLUK",0);
					itemYaml.saveConfig();
					NewItemGUI(player, ItemCounter);
				}
				else
				{
					int number = ((page*45)+event.getSlot());
					if(event.isLeftClick() == true&&event.isShiftClick()==false)
					{
						player.sendMessage(ChatColor.GREEN+"[SYSTEM] : Ŭ���� �������� ���� �޾ҽ��ϴ�!");
						player.getInventory().addItem(event.getCurrentItem());
					}
					if(event.isLeftClick() == true&&event.isShiftClick()==true)
						NewItemGUI(player, number);
					else if(event.isRightClick()==true&&event.isShiftClick()==true)
					{
						short Acount =  (short) (itemYaml.getKeys().toArray().length-1);
						for(int counter = (short) number;counter <Acount;counter++)
							itemYaml.set(counter+"", itemYaml.get((counter+1)+""));
						itemYaml.removeKey(Acount+"");
						itemYaml.saveConfig();
						ItemListGUI(player, page);
					}
				}
			}
		}
	}
	
	public void NewItemGUIclick(InventoryClickEvent event)
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
			if(slot == 45)//���� ȭ��
				ItemListGUI(player, 0);
			else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
			{
				int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			  	YamlLoader itemYaml = new YamlLoader();
				itemYaml.getConfig("Item/ItemList.yml");
				if(itemYaml.getString(itemnumber+"")==null)
				{
					player.sendMessage(ChatColor.RED+"[SYSTEM] : �ٸ� OP�� �������� �����Ͽ� �ݿ����� �ʾҽ��ϴ�!");
					SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
					player.closeInventory();
					return;
				}
				if(slot==37)//�� Ÿ��
				{
					if(itemYaml.getString(itemnumber+".ShowType").contains("[���]"))
						itemYaml.set(itemnumber+".ShowType",ChatColor.YELLOW+"[�÷�]");
					else if(itemYaml.getString(itemnumber+".ShowType").contains("[�÷�]"))
						itemYaml.set(itemnumber+".ShowType",ChatColor.LIGHT_PURPLE+"[��ȣ]");
					else if(itemYaml.getString(itemnumber+".ShowType").contains("[��ȣ]"))
						itemYaml.set(itemnumber+".ShowType",ChatColor.BLUE+"[�з�]");
					else if(itemYaml.getString(itemnumber+".ShowType").contains("[�з�]"))
						itemYaml.set(itemnumber+".ShowType",ChatColor.WHITE+"[���]");
					itemYaml.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==15)//Ÿ�� ����
				{
					YamlLoader customTypeYaml = new YamlLoader();
				  	customTypeYaml.getConfig("Item/CustomType.yml");
				  	String[] weaponCustomType = customTypeYaml.getKeys().toArray(new String[0]);
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				  	if(weaponCustomType.length == 0)
				  	{
						if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[�Ѽ� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[�Ѽ� ��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[��� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��� ��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[���Ÿ� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���Ÿ� ����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[Ȱ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[Ȱ]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[���� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[������]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[������]"))
							itemYaml.set(itemnumber+".Type",ChatColor.WHITE+"[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.GRAY+"[��Ÿ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��Ÿ]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[���� ����]");
				  	}
				  	else
				  	{
						if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[�Ѽ� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[�Ѽ� ��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[��� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��� ��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.RED+"[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[���Ÿ� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���Ÿ� ����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[Ȱ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[Ȱ]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[���� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[������]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[������]"))
							itemYaml.set(itemnumber+".Type",ChatColor.WHITE+"[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type",ChatColor.GRAY+"[��Ÿ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��Ÿ]"))
							itemYaml.set(itemnumber+".Type",ChatColor.WHITE+weaponCustomType[0]);
						else
						{
							for(int count = 0; count < weaponCustomType.length; count++)
							{
								if((itemYaml.getString(itemnumber+".Type").contains(weaponCustomType[count])))
								{
									if(count+1 == weaponCustomType.length)
										itemYaml.set(itemnumber+".Type",ChatColor.RED+"[���� ����]");
									else
										itemYaml.set(itemnumber+".Type",ChatColor.WHITE+weaponCustomType[(count+1)]);
									break;
								}
							}
						}
				  	}
					itemYaml.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==16)//��� ����
				{
					if(itemYaml.getString(itemnumber+".Grade").contains("[�Ϲ�]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.GREEN+"[���]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[���]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.BLUE+"[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.YELLOW+"[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.DARK_PURPLE+"[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.GOLD+"[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.DARK_RED+""+ChatColor.BOLD+"["+ChatColor.GOLD+""+ChatColor.BOLD+"��"+ChatColor.DARK_GREEN+""+ChatColor.BOLD+"��"+"��1��l]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("��"))
						itemYaml.set(itemnumber+".Grade",ChatColor.GRAY+"[�ϱ�]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[�ϱ�]"))
						itemYaml.set(itemnumber+".Grade",ChatColor.WHITE+"[�Ϲ�]");
					itemYaml.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==43)//�ִ� ���� ����
				{
					if(itemYaml.getInt(itemnumber+".MaxSocket") < 5)
						itemYaml.set(itemnumber+".MaxSocket",itemYaml.getInt(itemnumber+".MaxSocket")+1);
					else if(itemYaml.getInt(itemnumber+".MaxSocket") == 5)
							itemYaml.set(itemnumber+".MaxSocket", 0);
					itemYaml.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==50)//���� ����
				{
					if(event.isLeftClick())
						JobListGUI(player, (short)0, itemnumber);
					else if(event.isRightClick())
					{
						SoundEffect.SP(player, Sound.ITEM_SHIELD_BREAK, 0.8F, 1.0F);
						itemYaml.set(itemnumber+".JOB", "����");
						itemYaml.saveConfig();
						NewItemGUI(player, itemnumber);
					}
				}
				else
				{
					UserData_Object u = new UserData_Object();
					player.closeInventory();
					u.setType(player, "Item");
					u.setInt(player, (byte)3, itemnumber);
					if(slot == 13 || slot == 14)
					{
						if(slot == 13)
						{
							player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �̸��� �Է��� �ּ���!");
							u.setString(player, (byte)1, "DisplayName");
						}
						else
						{
							player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ������ �Է��� �ּ���!");
							player.sendMessage(ChatColor.GOLD + "%enter%"+ChatColor.WHITE + " - ���� ��� ���� -");
							u.setString(player, (byte)1, "Lore");
						}
						player.sendMessage(ChatColor.WHITE + ""+ChatColor.BOLD + "&l " + ChatColor.BLACK + "&0 "+ChatColor.DARK_BLUE+"&1 "+ChatColor.DARK_GREEN+"&2 "+
						ChatColor.DARK_AQUA + "&3 " +ChatColor.DARK_RED + "&4 " + ChatColor.DARK_PURPLE + "&5 " +
								ChatColor.GOLD + "&6 " + ChatColor.GRAY + "&7 " + ChatColor.DARK_GRAY + "&8 " +
						ChatColor.BLUE + "&9 " + ChatColor.GREEN + "&a " + ChatColor.AQUA + "&b " + ChatColor.RED + "&c " +
								ChatColor.LIGHT_PURPLE + "&d " + ChatColor.YELLOW + "&e "+ChatColor.WHITE + "&f");
					}
					else if(slot == 22)//ID����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ID ���� �Է��� �ּ���!");
						u.setString(player, (byte)1, "ID");
					}
					else if(slot == 23)//DATA����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� DATA ���� �Է��� �ּ���!");
						u.setString(player, (byte)1, "Data");
					}
					else if(slot == 24)//����� ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ּ� "+main.Main_ServerOption.Damage+"�� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinDamage");
					}
					else if(slot == 25)//���� ����� ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ּ� "+main.Main_ServerOption.MagicDamage+"�� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinMaDamage");
					}
					else if(slot == 31)//����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ������ �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "DEF");
					}
					else if(slot == 32)//��ȣ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ��ȣ�� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "Protect");
					}
					else if(slot == 33)//���� ��� ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���� ������ �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MaDEF");
					}
					else if(slot == 34)//���� ��ȣ ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���� ��ȣ�� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MaProtect");
					}
					else if(slot == 40)//���� ���ʽ�
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ����� ���ʽ��� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(-127 ~ 127)");
						u.setString(player, (byte)1, "HP");
					}
					else if(slot == 41)//������ ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ִ� �������� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MaxDurability");
					}
					else if(slot == 42)//���� Ƚ�� ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ִ� ���� Ƚ���� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ 127)");
						u.setString(player, (byte)1, "MaxUpgrade");
					}
					else if(slot == 49)//���� ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���� ������ �Է� �� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinLV");
					}
				}
			}
		}
	}
	
	public void JobGUIClick(InventoryClickEvent event)
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
			int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			if(slot == 45)//���� ���
				NewItemGUI(player, itemnumber);
			else
			{
			  	YamlLoader temYaml = new YamlLoader();
				temYaml.getConfig("Item/ItemList.yml");
				temYaml.set(itemnumber+".JOB", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				temYaml.saveConfig();
				SoundEffect.SP(player, Sound.ITEM_SHIELD_BREAK, 0.8F, 1.0F);
				NewItemGUI(player, itemnumber);
			}
		}
	}
	

	public String[] LoreCreater(int ItemNumber)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/ItemList.yml");
		String lore = "";
		String Type = ChatColor.stripColor(itemYaml.getString(ItemNumber+".ShowType"));
		if(Type.compareTo("[�з�]")==0)
		{
			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.GOLD+"��� ���� ���� : " +ChatColor.WHITE + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				

			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.Damage+" : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.MagicDamage+" : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.GRAY + " �� ��� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + " �� ��ȣ : " + ChatColor.WHITE +itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.BLUE + " �� ���� ��� : " + ChatColor.WHITE +itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.DARK_BLUE + " �� ���� ��ȣ : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.DARK_GREEN + " �� �뷱�� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.YELLOW + " �� ũ��Ƽ�� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.GOLD + " �� ������ : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+" �� ���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+ChatColor.GOLD+"___--------------------___%enter%"+ChatColor.GOLD+"       [������ ����]";
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";
			lore = lore+ChatColor.GOLD+"---____________________---%enter%";


			if(itemYaml.getInt(ItemNumber+".HP")!=0||itemYaml.getInt(ItemNumber+".MP")!=0||
					itemYaml.getInt(ItemNumber+".STR")!=0||itemYaml.getInt(ItemNumber+".DEX")!=0||
					itemYaml.getInt(ItemNumber+".INT")!=0||itemYaml.getInt(ItemNumber+".WILL")!=0||
					itemYaml.getInt(ItemNumber+".LUK")!=0)
			{
				lore = lore+ChatColor.DARK_AQUA+"___--------------------___%enter%"+ChatColor.DARK_AQUA+"       [���ʽ� �ɼ�]%enter%";
				if(itemYaml.getInt(ItemNumber+".HP") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".HP") < 0)
					lore = lore+ChatColor.RED + " �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MP") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".MP") < 0)
					lore = lore+ChatColor.RED + " �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".STR") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".STR") < 0)
					lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".DEX") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
					lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".INT") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".INT") < 0)
					lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".WILL") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
					lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".LUK") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
					lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				lore = lore+ChatColor.DARK_AQUA+"---____________________---%enter%";
			}

			if(itemYaml.getInt(ItemNumber+".MaxSocket")!=0||itemYaml.getInt(ItemNumber+".MaxUpgrade")!=0)
			{
				lore = lore+ChatColor.LIGHT_PURPLE+"___--------------------___%enter%"+ChatColor.LIGHT_PURPLE+"       [������ ��ȭ]%enter%";
				if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0 && itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
				{
					lore = lore+ChatColor.DARK_PURPLE + " �� ���� : " + "0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					lore = lore+ChatColor.BLUE + " �� �� : ";
					for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
						lore = lore+ChatColor.GRAY + "�� ";
				}
				else if(itemYaml.getInt(ItemNumber+".MaxUpgrade") <= 0 && itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
				{
					lore = lore+ChatColor.BLUE + " �� �� : ";
					for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
						lore = lore+ChatColor.GRAY + "�� ";
				}
				else
					lore = lore+ChatColor.DARK_PURPLE + " �� ���� : " + "0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade");
				lore = lore+"%enter%"+ChatColor.LIGHT_PURPLE+"---____________________---%enter%";
			}
			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
			{
				lore = lore+ChatColor.DARK_RED+"___--------------------___%enter%"+ChatColor.DARK_RED+"       [���� ����]%enter%";
				if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".MinSTR")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".MinINT")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK")+"%enter%";
				lore = lore+ChatColor.DARK_RED+"---____________________---%enter%";
				
			}
		}
		else if(Type.compareTo("[��ȣ]")==0)
		{
			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.GOLD+"��� ���� ���� : " +ChatColor.WHITE + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.Damage+" : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.MagicDamage+" : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.GRAY + " �� ��� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + " �� ��ȣ : " + ChatColor.WHITE +itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.BLUE + " �� ���� ��� : " + ChatColor.WHITE +itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.DARK_BLUE + " �� ���� ��ȣ : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.DARK_GREEN + " �� �뷱�� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.YELLOW + " �� ũ��Ƽ�� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.GOLD + " �� ������ : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";

			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+" �� ���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";


			if(itemYaml.getInt(ItemNumber+".HP") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".HP") < 0)
				lore = lore+ChatColor.RED + " �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MP") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".MP") < 0)
				lore = lore+ChatColor.RED + " �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".STR") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".STR") < 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEX") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".INT") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".INT") < 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".WILL") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".LUK") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
				lore = lore+ChatColor.RED + " �� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_PURPLE + " �� ���� : " + "0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%"+ChatColor.BLUE + " �� �� : ";
				for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+ChatColor.GRAY + "�� ";
			}

			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV");
			if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV");
			if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".MinSTR");
			if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX");
			if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".MinINT");
			if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL");
			if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK");
		}
		else if(Type.compareTo("[�÷�]")==0)
		{

			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.GOLD+"��� ���� ���� : " +ChatColor.WHITE + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + main.Main_ServerOption.Damage+" : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.DARK_AQUA +main.Main_ServerOption.MagicDamage+" : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.GRAY + "��� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + "��ȣ : " + ChatColor.WHITE +itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.BLUE + "���� ��� : " + ChatColor.WHITE +itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.DARK_BLUE + "���� ��ȣ : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.DARK_GREEN + "�뷱�� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.YELLOW + "ũ��Ƽ�� : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.GOLD + "������ : " +ChatColor.WHITE + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+"���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";


			if(itemYaml.getInt(ItemNumber+".HP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".HP") < 0)
				lore = lore+ChatColor.RED + "����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".MP") < 0)
				lore = lore+ChatColor.RED + "���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".STR") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".STR") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEX") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".INT") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".INT") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".WILL") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".LUK") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_PURPLE + "���� : " + "0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%"+ChatColor.BLUE + "�� : ";
				for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+ChatColor.GRAY + "�� ";
			}
			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV");
			if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV");
			if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".MinSTR");
			if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX");
			if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".MinINT");
			if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL");
			if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK");
		}
		else
		{
			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.WHITE+"��� ���� ���� : " + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.WHITE +main.Main_ServerOption.Damage+" : " + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.WHITE +main.Main_ServerOption.MagicDamage+" : " + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.WHITE + "��� : " + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + "��ȣ : " + itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.WHITE + "���� ��� : " + itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.WHITE + "���� ��ȣ : " + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.WHITE + "�뷱�� : " + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.WHITE + "ũ��Ƽ�� : " + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.WHITE + "������ : " + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+"���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";


			if(itemYaml.getInt(ItemNumber+".HP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".HP") < 0)
				lore = lore+ChatColor.RED + "����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".MP") < 0)
				lore = lore+ChatColor.RED + "���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".STR") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".STR") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEX") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".INT") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".INT") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".WILL") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".LUK") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
				lore = lore+ChatColor.RED + ""+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE + "���� : " + "0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%"+ChatColor.WHITE + "�� : ";
				for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+ChatColor.GRAY + "�� ";
			}
			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV");
			if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV");
			if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+main.Main_ServerOption.STR+" : " + itemYaml.getInt(ItemNumber+".MinSTR");
			if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+main.Main_ServerOption.DEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX");
			if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+main.Main_ServerOption.INT+" : " + itemYaml.getInt(ItemNumber+".MinINT");
			if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+main.Main_ServerOption.WILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL");
			if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+main.Main_ServerOption.LUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK");
		}
		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  scriptA[counter];
		return scriptA;
	}
}