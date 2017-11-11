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
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ������", 145,0,1,Arrays.asList("��7���ο� �������� �����մϴ�."), 49, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
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
		
		Stack2("��3[    �����    ]", 145,0,1,null, 9, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 10, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 11, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 18, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 20, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 27, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 28, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 29, inv);
		Stack2(ItemName, ItemID,ItemData,1,Arrays.asList(LoreCreater(number)), 19, inv);
		
		Stack2("��3[    ���� ����    ]", 421,0,1,Arrays.asList("��f������ ����â��","��f�����մϴ�.","","��f[    ���� ����    ]","       "+ itemYaml.getString(number+".ShowType"),""), 37, inv);
		Stack2("��3[    �̸� ����    ]", 421,0,1,Arrays.asList("��f�������� �̸���","��f�����մϴ�.",""), 13, inv);
		Stack2("��3[    ���� ����    ]", 386,0,1,Arrays.asList("��f�������� ������","��f�����մϴ�.",""), 14, inv);
		Stack2("��3[    Ÿ�� ����    ]", 61,0,1,Arrays.asList("��f�������� Ÿ����","��f�����մϴ�.","","��f[    ���� Ÿ��    ]",Type,""), 15, inv);
		Stack2("��3[    ��� ����    ]", 266,0,1,Arrays.asList("��f�������� �����","��f�����մϴ�.","","��f[    ���� ���    ]","       "+Grade,""), 16, inv);
		Stack2("��3[        ID        ]", 405,0,1,Arrays.asList("��f�������� ID����","��f�����մϴ�.",""), 22, inv);
		Stack2("��3[       DATA       ]", 336,0,1,Arrays.asList("��f�������� DATA����","��f�����մϴ�.",""), 23, inv);
		Stack2("��3[       "+main.Main_ServerOption.damage+"       ]", 267,0,1,Arrays.asList("��f�������� "+main.Main_ServerOption.damage+"��","��f�����մϴ�.",""), 24, inv);
		Stack2("��3[     "+main.Main_ServerOption.magicDamage+"     ]", 403,0,1,Arrays.asList("��f�������� "+main.Main_ServerOption.magicDamage+"��","��f�����մϴ�.",""), 25, inv);
		Stack2("��3[        ���        ]", 307,0,1,Arrays.asList("��f�������� ������","��f�����մϴ�.",""), 31, inv);
		Stack2("��3[        ��ȣ        ]", 306,0,1,Arrays.asList("��f�������� ��ȣ��","��f�����մϴ�.",""), 32, inv);
		Stack2("��3[      ���� ���      ]", 311,0,1,Arrays.asList("��f�������� ���� ��","��f�����մϴ�.",""), 33, inv);
		Stack2("��3[      ���� ��ȣ      ]", 310,0,1,Arrays.asList("��f�������� ���� ��ȣ��","��f�����մϴ�.",""), 34, inv);
		Stack2("��3[        ����        ]", 399,0,1,Arrays.asList("��f�������� ���ʽ� ������","��f�����մϴ�.",""), 40, inv);
		Stack2("��3[       ������       ]", 145,2,1,Arrays.asList("��f�������� ��������","��f�����մϴ�.","","��c[0 ������ �Ϲ� ������ ������ ���]",""), 41, inv);
		Stack2("��3[        ����        ]", 145,0,1,Arrays.asList("��f�������� �ִ� ���� Ƚ����","��f�����մϴ�.","","��c[0 ������ ���� �Ұ���]",""), 42, inv);
		Stack2("��3[         ��         ]", 381,0,1,Arrays.asList("��f�������� �ִ� ������","��f�����մϴ�.","","��f�ִ� "+itemYaml.getInt(number+".MaxSocket")+" ��","","��c[0 ������ �� ���� �Ұ���]",""), 43, inv);
		Stack2("��3[      ���� ����      ]", 166,0,1,Arrays.asList("��f������ ������ ������","��f�ɾ�Ӵϴ�.",""), 49, inv);
		Stack2("��3[      ���� ����      ]", 397,3,1,Arrays.asList("��f������ ������ ������","��f�ɾ�Ӵϴ�.","��c[�� Ŭ���� ����]",""), 50, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+number), 53, inv);
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
				Stack2("��f��l" + jobList[count], 403,0,1,Arrays.asList("��3�ִ� �±� : ��f"+JobLevel+"��3�� �±�","","��e[��Ŭ���� ���� ����]","��e��l[���� �⺻ ����]"), loc, inv);
			else
				Stack2("��f��l" + jobList[count], 340,0,1,Arrays.asList("��3�ִ� �±� : ��f"+JobLevel+"��3�� �±�","","��e[��Ŭ���� ���� ����]"), loc, inv);
			
			loc++;
		}
		
		if(jobList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+number), 53, inv);
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
				new OPbox_GUI().opBoxGuiMain(player,(byte) 1);
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
					itemYaml.set(ItemCounter+".ShowType","��f[���]");
					itemYaml.set(ItemCounter+".ID",267);
					itemYaml.set(ItemCounter+".Data",0);
					itemYaml.set(ItemCounter+".DisplayName","��f��� ���� ������ ��");
					itemYaml.set(ItemCounter+".Lore","��f��� ������� �����̴�.%enter%��f������ ����������...");
					itemYaml.set(ItemCounter+".Type","��c[���� ����]");
					itemYaml.set(ItemCounter+".Grade","��f[�Ϲ�]");
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
						player.sendMessage("��a[SYSTEM] : Ŭ���� �������� ���� �޾ҽ��ϴ�!");
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
					player.sendMessage("��c[SYSTEM] : �ٸ� OP�� �������� �����Ͽ� �ݿ����� �ʾҽ��ϴ�!");
					SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
					player.closeInventory();
					return;
				}
				if(slot==37)//�� Ÿ��
				{
					if(itemYaml.getString(itemnumber+".ShowType").contains("[���]"))
						itemYaml.set(itemnumber+".ShowType","��e[�÷�]");
					else if(itemYaml.getString(itemnumber+".ShowType").contains("[�÷�]"))
						itemYaml.set(itemnumber+".ShowType","��d[��ȣ]");
					else if(itemYaml.getString(itemnumber+".ShowType").contains("[��ȣ]"))
						itemYaml.set(itemnumber+".ShowType","��9[�з�]");
					else if(itemYaml.getString(itemnumber+".ShowType").contains("[�з�]"))
						itemYaml.set(itemnumber+".ShowType","��f[���]");
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
							itemYaml.set(itemnumber+".Type","��c[�Ѽ� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[�Ѽ� ��]"))
							itemYaml.set(itemnumber+".Type","��c[��� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��� ��]"))
							itemYaml.set(itemnumber+".Type","��c[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type","��c[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type","��2[���Ÿ� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���Ÿ� ����]"))
							itemYaml.set(itemnumber+".Type","��2[Ȱ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[Ȱ]"))
							itemYaml.set(itemnumber+".Type","��2[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type","��3[���� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type","��3[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type","��3[������]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[������]"))
							itemYaml.set(itemnumber+".Type","��f[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type","��7[��Ÿ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��Ÿ]"))
							itemYaml.set(itemnumber+".Type","��c[���� ����]");
				  	}
				  	else
				  	{
						if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type","��c[�Ѽ� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[�Ѽ� ��]"))
							itemYaml.set(itemnumber+".Type","��c[��� ��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��� ��]"))
							itemYaml.set(itemnumber+".Type","��c[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type","��c[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type","��2[���Ÿ� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���Ÿ� ����]"))
							itemYaml.set(itemnumber+".Type","��2[Ȱ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[Ȱ]"))
							itemYaml.set(itemnumber+".Type","��2[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type","��3[���� ����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[���� ����]"))
							itemYaml.set(itemnumber+".Type","��3[����]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[����]"))
							itemYaml.set(itemnumber+".Type","��3[������]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[������]"))
							itemYaml.set(itemnumber+".Type","��f[��]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��]"))
							itemYaml.set(itemnumber+".Type","��7[��Ÿ]");
						else if(itemYaml.getString(itemnumber+".Type").contains("[��Ÿ]"))
							itemYaml.set(itemnumber+".Type","��f"+weaponCustomType[0]);
						else
						{
							for(int count = 0; count < weaponCustomType.length; count++)
							{
								if((itemYaml.getString(itemnumber+".Type").contains(weaponCustomType[count])))
								{
									if(count+1 == weaponCustomType.length)
										itemYaml.set(itemnumber+".Type","��c[���� ����]");
									else
										itemYaml.set(itemnumber+".Type","��f"+weaponCustomType[(count+1)]);
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
						itemYaml.set(itemnumber+".Grade","��a[���]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[���]"))
						itemYaml.set(itemnumber+".Grade","��9[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade","��e[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade","��5[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade","��6[����]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[����]"))
						itemYaml.set(itemnumber+".Grade","��4��l[��6��l�ʡ�2��l����1��l]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("��"))
						itemYaml.set(itemnumber+".Grade","��7[�ϱ�]");
					else if(itemYaml.getString(itemnumber+".Grade").contains("[�ϱ�]"))
						itemYaml.set(itemnumber+".Grade","��f[�Ϲ�]");
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
							player.sendMessage("��3[������] : �������� �̸��� �Է��� �ּ���!");
							u.setString(player, (byte)1, "DisplayName");
						}
						else
						{
							player.sendMessage("��3[������] : �������� ������ �Է��� �ּ���!");
							player.sendMessage("��6%enter%��f - ���� ��� ���� -");
							u.setString(player, (byte)1, "Lore");
						}
						player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
						"��3&3 ��4&4 ��5&5 " +
								"��6&6 ��7&7 ��8&8 " +
						"��9&9 ��a&a ��b&b ��c&c " +
								"��d&d ��e&e ��f&f");
					}
					else if(slot == 22)//ID����
					{
						player.sendMessage("��3[������] : �������� ID ���� �Է��� �ּ���!");
						u.setString(player, (byte)1, "ID");
					}
					else if(slot == 23)//DATA����
					{
						player.sendMessage("��3[������] : �������� DATA ���� �Է��� �ּ���!");
						u.setString(player, (byte)1, "Data");
					}
					else if(slot == 24)//����� ����
					{
						player.sendMessage("��3[������] : �������� �ּ� "+main.Main_ServerOption.damage+"�� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinDamage");
					}
					else if(slot == 25)//���� ����� ����
					{
						player.sendMessage("��3[������] : �������� �ּ� "+main.Main_ServerOption.magicDamage+"�� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinMaDamage");
					}
					else if(slot == 31)//����
					{
						player.sendMessage("��3[������] : �������� ������ �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "DEF");
					}
					else if(slot == 32)//��ȣ����
					{
						player.sendMessage("��3[������] : �������� ��ȣ�� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "Protect");
					}
					else if(slot == 33)//���� ��� ����
					{
						player.sendMessage("��3[������] : �������� ���� ������ �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MaDEF");
					}
					else if(slot == 34)//���� ��ȣ ����
					{
						player.sendMessage("��3[������] : �������� ���� ��ȣ�� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MaProtect");
					}
					else if(slot == 40)//���� ���ʽ�
					{
						player.sendMessage("��3[������] : �������� ����� ���ʽ��� �Է��� �ּ���!");
						player.sendMessage("��3(-127 ~ 127)");
						u.setString(player, (byte)1, "HP");
					}
					else if(slot == 41)//������ ����
					{
						player.sendMessage("��3[������] : �������� �ִ� �������� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MaxDurability");
					}
					else if(slot == 42)//���� Ƚ�� ����
					{
						player.sendMessage("��3[������] : �������� �ִ� ���� Ƚ���� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ 127)");
						u.setString(player, (byte)1, "MaxUpgrade");
					}
					else if(slot == 49)//���� ����
					{
						player.sendMessage("��3[������] : �������� ���� ������ �Է� �� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
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
		if(Type.equals("[�з�]"))
		{
			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").equals("����"))
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%��6��� ���� ���� : ��f" + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				

			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+"��c �� "+main.Main_ServerOption.damage+" : ��f" + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.magicDamage+" : ��f" + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+"��7 �� ��� : ��f" + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+"��f �� ��ȣ : ��f" +itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+"��9 �� ���� ��� : ��f" +itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+"��1 �� ���� ��ȣ : ��f" + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+"��2 �� �뷱�� : ��f" + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+"��e �� ũ��Ƽ�� : ��f" + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+"��6 �� ������ : ��f" + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + "��f �� ���õ� : 0.0%��f%enter%";
			
			lore = lore+"��6___--------------------___%enter%��6       [������ ����]";
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";
			lore = lore+"��6---____________________---%enter%";


			if(itemYaml.getInt(ItemNumber+".HP")!=0||itemYaml.getInt(ItemNumber+".MP")!=0||
					itemYaml.getInt(ItemNumber+".STR")!=0||itemYaml.getInt(ItemNumber+".DEX")!=0||
					itemYaml.getInt(ItemNumber+".INT")!=0||itemYaml.getInt(ItemNumber+".WILL")!=0||
					itemYaml.getInt(ItemNumber+".LUK")!=0)
			{
				lore = lore+"��3___--------------------___%enter%��3       [���ʽ� �ɼ�]%enter%";
				if(itemYaml.getInt(ItemNumber+".HP") > 0)
					lore = lore+"��3 �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".HP") < 0)
					lore = lore+"��c �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MP") > 0)
					lore = lore+"��3 �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".MP") < 0)
					lore = lore+"��c �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".STR") > 0)
					lore = lore+"��3 �� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".STR") < 0)
					lore = lore+"��c �� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".DEX") > 0)
					lore = lore+"��3 �� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
					lore = lore+"��c �� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".INT") > 0)
					lore = lore+"��3 �� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".INT") < 0)
					lore = lore+"��c �� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".WILL") > 0)
					lore = lore+"��3 �� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
					lore = lore+"��c �� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".LUK") > 0)
					lore = lore+"��3 �� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
					lore = lore+"��c �� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				lore = lore+"��3---____________________---%enter%";
			}

			if(itemYaml.getInt(ItemNumber+".MaxSocket")!=0||itemYaml.getInt(ItemNumber+".MaxUpgrade")!=0)
			{
				lore = lore+"��d___--------------------___%enter%��d       [������ ��ȭ]%enter%";
				if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0 && itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
				{
					lore = lore+"��5 �� ���� : 0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					lore = lore+"��9 �� �� : ";
					for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
						lore = lore+"��7�� ";
				}
				else if(itemYaml.getInt(ItemNumber+".MaxUpgrade") <= 0 && itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
				{
					lore = lore+"��9 �� �� : ";
					for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
						lore = lore+"��7�� ";
				}
				else
					lore = lore+"��5 �� ���� : 0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade");
				lore = lore+"%enter%��d---____________________---%enter%";
			}
			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
			{
				lore = lore+"��4___--------------------___%enter%��4       [���� ����]%enter%";
				if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
					lore = lore+"��4 �� �ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
					lore = lore+"��4 �� �ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
					lore = lore+"��4 �� �ּ� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".MinSTR")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
					lore = lore+"��4 �� �ּ� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
					lore = lore+"��4 �� �ּ� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".MinINT")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
					lore = lore+"��4 �� �ּ� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL")+"%enter%";
				if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
					lore = lore+"��4 �� �ּ� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK")+"%enter%";
				lore = lore+"��4---____________________---%enter%";
				
			}
		}
		else if(Type.equals("[��ȣ]"))
		{
			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").equals("����"))
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%��6��� ���� ���� : ��f" + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+"��c �� "+main.Main_ServerOption.damage+" : ��f" + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.magicDamage+" : ��f" + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+"��7 �� ��� : ��f" + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+"��f �� ��ȣ : ��f" +itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+"��9 �� ���� ��� : ��f" +itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+"��1 �� ���� ��ȣ : ��f" + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+"��2 �� �뷱�� : ��f" + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+"��e �� ũ��Ƽ�� : ��f" + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+"��6 �� ������ : ��f" + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";

			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + "��f �� ���õ� : 0.0%��f%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";


			if(itemYaml.getInt(ItemNumber+".HP") > 0)
				lore = lore+"��3 �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".HP") < 0)
				lore = lore+"��c �� ����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MP") > 0)
				lore = lore+"��3 �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".MP") < 0)
				lore = lore+"��c �� ���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".STR") > 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".STR") < 0)
				lore = lore+"��c �� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEX") > 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
				lore = lore+"��c �� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".INT") > 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".INT") < 0)
				lore = lore+"��c �� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".WILL") > 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
				lore = lore+"��c �� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".LUK") > 0)
				lore = lore+"��3 �� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
				lore = lore+"��c �� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%��5 �� ���� : 0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%��9 �� �� : ";
				for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+"��7�� ";
			}

			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"��4%enter% �� �ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV");
			if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"��4%enter% �� �ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV");
			if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".MinSTR");
			if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX");
			if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".MinINT");
			if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL");
			if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK");
		}
		else if(Type.equals("[�÷�]"))
		{

			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").equals("����"))
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%��6��� ���� ���� : ��f" + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + main.Main_ServerOption.damage+" : ��f" + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+"��3"+main.Main_ServerOption.magicDamage+" : ��f" + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+"��7��� : ��f" + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+"��f��ȣ : ��f" +itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+"��9���� ��� : ��f" +itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+"��1���� ��ȣ : ��f" + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+"��2�뷱�� : ��f" + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+"��eũ��Ƽ�� : ��f" + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+"��6������ : ��f" + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + "��f���õ� : 0.0%��f%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";


			if(itemYaml.getInt(ItemNumber+".HP") > 0)
				lore = lore+"��3����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".HP") < 0)
				lore = lore+"��c����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MP") > 0)
				lore = lore+"��3���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".MP") < 0)
				lore = lore+"��c���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".STR") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".STR") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEX") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".INT") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".INT") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".WILL") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".LUK") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%��5���� : 0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%��9�� : ";
				for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+"��7�� ";
			}
			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"%enter%��4�ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV");
			if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"%enter%��4�ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV");
			if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"%enter%��4�ּ� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".MinSTR");
			if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"%enter%��4�ּ� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX");
			if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"%enter%��4�ּ� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".MinINT");
			if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"%enter%��4�ּ� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL");
			if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"%enter%��4�ּ� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK");
		}
		else
		{
			lore = lore+itemYaml.getString(ItemNumber+".Type");
			for(int count = 0; count<20-itemYaml.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(ItemNumber+".JOB").equals("����"))
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(ItemNumber+".Grade")+"%enter%��f��� ���� ���� : " + itemYaml.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(ItemNumber+".MinDamage") != 0||itemYaml.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+"��f"+main.Main_ServerOption.damage+" : " + itemYaml.getInt(ItemNumber+".MinDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinMaDamage") != 0||itemYaml.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+"��f"+main.Main_ServerOption.magicDamage+" : " + itemYaml.getInt(ItemNumber+".MinMaDamage") + " ~ " + itemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEF") != 0)
				lore = lore+"��f��� : " + itemYaml.getInt(ItemNumber+".DEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Protect") != 0)
				lore = lore+"��f��ȣ : " + itemYaml.getInt(ItemNumber+".Protect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+"��f���� ��� : " + itemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+"��f���� ��ȣ : " + itemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Balance") != 0)
				lore = lore+"��f�뷱�� : " + itemYaml.getInt(ItemNumber+".Balance")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".Critical") != 0)
				lore = lore+"��fũ��Ƽ�� : " + itemYaml.getInt(ItemNumber+".Critical")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+"��f������ : " + itemYaml.getInt(ItemNumber+".Durability")+" / "+ itemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + "��f���õ� : 0.0%��f%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(ItemNumber+".Lore")+"%enter%";


			if(itemYaml.getInt(ItemNumber+".HP") > 0)
				lore = lore+"��3����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".HP") < 0)
				lore = lore+"��c����� : " + itemYaml.getInt(ItemNumber+".HP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MP") > 0)
				lore = lore+"��3���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".MP") < 0)
				lore = lore+"��c���� : " + itemYaml.getInt(ItemNumber+".MP")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".STR") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".STR") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".STR")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".DEX") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".DEX") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".DEX")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".INT") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".INT") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".INT")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".WILL") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".WILL") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".WILL")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".LUK") > 0)
				lore = lore+"��3"+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			else if(itemYaml.getInt(ItemNumber+".LUK") < 0)
				lore = lore+"��c"+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(itemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%��f���� : 0 / "+itemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%��f�� : ";
				for(int count = 0; count <itemYaml.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+"��7�� ";
			}
			if(itemYaml.getInt(ItemNumber+".MinLV")!=0||itemYaml.getInt(ItemNumber+".MinRLV")!=0||
					itemYaml.getInt(ItemNumber+".MinSTR")!=0||itemYaml.getInt(ItemNumber+".MinDEX")!=0||
					itemYaml.getInt(ItemNumber+".MinINT")!=0||itemYaml.getInt(ItemNumber+".MinWILL")!=0||
					itemYaml.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(itemYaml.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"%enter%��f�ּ� ���� : " + itemYaml.getInt(ItemNumber+".MinLV");
			if(itemYaml.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"%enter%��f�ּ� �������� : " + itemYaml.getInt(ItemNumber+".MinRLV");
			if(itemYaml.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"%enter%��f�ּ� "+main.Main_ServerOption.statSTR+" : " + itemYaml.getInt(ItemNumber+".MinSTR");
			if(itemYaml.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"%enter%��f�ּ� "+main.Main_ServerOption.statDEX+" : " + itemYaml.getInt(ItemNumber+".MinDEX");
			if(itemYaml.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"%enter%��f�ּ� "+main.Main_ServerOption.statINT+" : " + itemYaml.getInt(ItemNumber+".MinINT");
			if(itemYaml.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"%enter%��f�ּ� "+main.Main_ServerOption.statWILL+" : " + itemYaml.getInt(ItemNumber+".MinWILL");
			if(itemYaml.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"%enter%��f�ּ� "+main.Main_ServerOption.statLUK+" : " + itemYaml.getInt(ItemNumber+".MinLUK");
		}
		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  scriptA[counter];
		return scriptA;
	}
}