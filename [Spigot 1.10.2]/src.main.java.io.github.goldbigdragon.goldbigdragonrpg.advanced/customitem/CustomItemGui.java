package customitem;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import effect.SoundEffect;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;



public class CustomItemGui extends UtilGui
{
	public void itemListGui(Player player, int page)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/ItemList.yml");

		String uniqueCode = "��0��0��3��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0������ ��� : " + (page+1));

		int itemAmounts = itemYaml.getKeys().size();

		byte loc=0;
		for(int count = page*45; count < itemAmounts;count++)
		{
			int number = ((page*45)+loc);
			if(count > itemAmounts || loc >= 45) break;
			String itemName = itemYaml.getString(number+".DisplayName");
			int itemId = itemYaml.getInt(number+".ID");
			int itemData = itemYaml.getInt(number+".Data");
			Stack2(itemName, itemId, itemData, 1,Arrays.asList(loreCreater(number)), loc, inv);
			
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

	public void newItemGui(Player player, int number)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/ItemList.yml");

		String uniqueCode = "��0��0��3��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0������ �� ����");
		String itemName = itemYaml.getString(number+".DisplayName");
		short itemID = (short) itemYaml.getInt(number+".ID");
		byte itemData = (byte) itemYaml.getInt(number+".Data");

		String type = "";
		String grade = "";
		for(int counter =0;counter < 13 - itemYaml.getString(number+".Type").length();counter++ )
			type = type +" ";
		type = type +itemYaml.getString(number+".Type");
		grade = itemYaml.getString(number+".Grade");
		
		Stack2("��3[    �����    ]", 145,0,1,null, 9, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 10, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 11, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 18, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 20, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 27, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 28, inv);
		Stack2("��3[    �����    ]", 145,0,1,null, 29, inv);
		Stack2(itemName, itemID,itemData,1,Arrays.asList(loreCreater(number)), 19, inv);
		
		Stack2("��3[    ���� ����    ]", 421,0,1,Arrays.asList("��f������ ����â��","��f�����մϴ�.","","��f[    ���� ����    ]","       "+ itemYaml.getString(number+".ShowType"),""), 37, inv);
		Stack2("��3[    �̸� ����    ]", 421,0,1,Arrays.asList("��f�������� �̸���","��f�����մϴ�.",""), 13, inv);
		Stack2("��3[    ���� ����    ]", 386,0,1,Arrays.asList("��f�������� ������","��f�����մϴ�.",""), 14, inv);
		Stack2("��3[    Ÿ�� ����    ]", 61,0,1,Arrays.asList("��f�������� Ÿ����","��f�����մϴ�.","","��f[    ���� Ÿ��    ]",type,""), 15, inv);
		Stack2("��3[    ��� ����    ]", 266,0,1,Arrays.asList("��f�������� �����","��f�����մϴ�.","","��f[    ���� ���    ]","       "+grade,""), 16, inv);
		Stack2("��3[        ID        ]", 405,0,1,Arrays.asList("��f�������� ID����","��f�����մϴ�.",""), 22, inv);
		Stack2("��3[       DATA       ]", 336,0,1,Arrays.asList("��f�������� DATA����","��f�����մϴ�.",""), 23, inv);
		Stack2("��3[       "+main.MainServerOption.damage+"       ]", 267,0,1,Arrays.asList("��f�������� "+main.MainServerOption.damage+"��","��f�����մϴ�.",""), 24, inv);
		Stack2("��3[     "+main.MainServerOption.magicDamage+"     ]", 403,0,1,Arrays.asList("��f�������� "+main.MainServerOption.magicDamage+"��","��f�����մϴ�.",""), 25, inv);
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
	
	public void jobListGui(Player player, short page, int number)
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");
	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");

		String uniqueCode = "��0��0��3��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0������ ���� ���� : " + (page+1));

		String[] jobList = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < jobList.length;count++)
		{
			int jobLevel= jobYaml.getConfigurationSection("MapleStory."+jobList[count]).getKeys(false).size();
			
			if(count > jobList.length || loc >= 45) break;
			
			if(jobList[count].equalsIgnoreCase(configYaml.getString("Server.DefaultJob")))
				Stack2("��f��l" + jobList[count], 403,0,1,Arrays.asList("��3�ִ� �±� : ��f"+jobLevel+"��3�� �±�","","��e[��Ŭ���� ���� ����]","��e��l[���� �⺻ ����]"), loc, inv);
			else
				Stack2("��f��l" + jobList[count], 340,0,1,Arrays.asList("��3�ִ� �±� : ��f"+jobLevel+"��3�� �±�","","��e[��Ŭ���� ���� ����]"), loc, inv);
			
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
	
	
	
	public void itemListInventoryclick(InventoryClickEvent event)
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
				new OPboxGui().opBoxGuiMain(player,(byte) 1);
			else if(slot == 48)//���� ������
				itemListGui(player,page-1);
			else if(slot == 50)//���� ������
				itemListGui(player,page+1);
			else
			{
			  	YamlLoader itemYaml = new YamlLoader();
				itemYaml.getConfig("Item/ItemList.yml");
				if(slot == 49)//�� ������
				{
					int itemCounter = itemYaml.getKeys().size();
					itemYaml.set(itemCounter+".ShowType","��f[���]");
					itemYaml.set(itemCounter+".ID",267);
					itemYaml.set(itemCounter+".Data",0);
					itemYaml.set(itemCounter+".DisplayName","��f��� ���� ������ ��");
					itemYaml.set(itemCounter+".Lore","��f��� ������� �����̴�.%enter%��f������ ����������...");
					itemYaml.set(itemCounter+".Type","��c[���� ����]");
					itemYaml.set(itemCounter+".Grade","��f[�Ϲ�]");
					itemYaml.set(itemCounter+".MinDamage",1);
					itemYaml.set(itemCounter+".MaxDamage",7);
					itemYaml.set(itemCounter+".MinMaDamage",0);
					itemYaml.set(itemCounter+".MaxMaDamage",0);
					itemYaml.set(itemCounter+".DEF",0);
					itemYaml.set(itemCounter+".Protect",0);
					itemYaml.set(itemCounter+".MaDEF",0);
					itemYaml.set(itemCounter+".MaProtect",0);
					itemYaml.set(itemCounter+".Durability",256);
					itemYaml.set(itemCounter+".MaxDurability",256);
					itemYaml.set(itemCounter+".STR",0);
					itemYaml.set(itemCounter+".DEX",0);
					itemYaml.set(itemCounter+".INT",0);
					itemYaml.set(itemCounter+".WILL",0);
					itemYaml.set(itemCounter+".LUK",0);
					itemYaml.set(itemCounter+".Balance",10);
					itemYaml.set(itemCounter+".Critical",5);
					itemYaml.set(itemCounter+".MaxUpgrade",5);
					itemYaml.set(itemCounter+".MaxSocket",3);
					itemYaml.set(itemCounter+".HP",0);
					itemYaml.set(itemCounter+".MP",0);
					itemYaml.set(itemCounter+".JOB","����");
					itemYaml.set(itemCounter+".MinLV",0);
					itemYaml.set(itemCounter+".MinRLV",0);
					itemYaml.set(itemCounter+".MinSTR",0);
					itemYaml.set(itemCounter+".MinDEX",0);
					itemYaml.set(itemCounter+".MinINT",0);
					itemYaml.set(itemCounter+".MinWILL",0);
					itemYaml.set(itemCounter+".MinLUK",0);
					itemYaml.saveConfig();
					newItemGui(player, itemCounter);
				}
				else
				{
					int number = ((page*45)+event.getSlot());
					if(event.isLeftClick()&&!event.isShiftClick())
					{
						player.sendMessage("��a[SYSTEM] : Ŭ���� �������� ���� �޾ҽ��ϴ�!");
						player.getInventory().addItem(event.getCurrentItem());
					}
					if(event.isLeftClick()&&event.isShiftClick())
						newItemGui(player, number);
					else if(event.isRightClick()&&event.isShiftClick())
					{
						short acount =  (short) (itemYaml.getKeys().toArray().length-1);
						for(int counter = (short) number;counter <acount;counter++)
							itemYaml.set(counter+"", itemYaml.get((counter+1)+""));
						itemYaml.removeKey(acount+"");
						itemYaml.saveConfig();
						itemListGui(player, page);
					}
				}
			}
		}
	}
	
	public void newItemGuiClick(InventoryClickEvent event)
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
				itemListGui(player, 0);
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
					newItemGui(player, itemnumber);
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
					newItemGui(player, itemnumber);
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
					newItemGui(player, itemnumber);
				}
				else if(slot==43)//�ִ� ���� ����
				{
					if(itemYaml.getInt(itemnumber+".MaxSocket") < 5)
						itemYaml.set(itemnumber+".MaxSocket",itemYaml.getInt(itemnumber+".MaxSocket")+1);
					else if(itemYaml.getInt(itemnumber+".MaxSocket") == 5)
							itemYaml.set(itemnumber+".MaxSocket", 0);
					itemYaml.saveConfig();
					newItemGui(player, itemnumber);
				}
				else if(slot==50)//���� ����
				{
					if(event.isLeftClick())
						jobListGui(player, (short)0, itemnumber);
					else if(event.isRightClick())
					{
						SoundEffect.SP(player, Sound.ITEM_SHIELD_BREAK, 0.8F, 1.0F);
						itemYaml.set(itemnumber+".JOB", "����");
						itemYaml.saveConfig();
						newItemGui(player, itemnumber);
					}
				}
				else
				{
					UserDataObject u = new UserDataObject();
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
						player.sendMessage("��3[������] : �������� �ּ� "+main.MainServerOption.damage+"�� �Է��� �ּ���!");
						player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinDamage");
					}
					else if(slot == 25)//���� ����� ����
					{
						player.sendMessage("��3[������] : �������� �ּ� "+main.MainServerOption.magicDamage+"�� �Է��� �ּ���!");
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
				newItemGui(player, itemnumber);
			else
			{
			  	YamlLoader temYaml = new YamlLoader();
				temYaml.getConfig("Item/ItemList.yml");
				temYaml.set(itemnumber+".JOB", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				temYaml.saveConfig();
				SoundEffect.SP(player, Sound.ITEM_SHIELD_BREAK, 0.8F, 1.0F);
				newItemGui(player, itemnumber);
			}
		}
	}
	

	public String[] loreCreater(int itemNumbe)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/ItemList.yml");
		String lore = "";
		String type = ChatColor.stripColor(itemYaml.getString(itemNumbe+".ShowType"));
		if(type.equals("[�з�]"))
		{
			lore = lore+itemYaml.getString(itemNumbe+".Type");
			for(int count = 0; count<20-itemYaml.getString(itemNumbe+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(itemNumbe+".JOB").equals("����"))
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%��6��� ���� ���� : ��f" + itemYaml.getString(itemNumbe+".JOB")+"%enter%%enter%";
				

			if(itemYaml.getInt(itemNumbe+".MinDamage") != 0||itemYaml.getInt(itemNumbe+".MaxDamage") != 0)
				lore = lore+"��c �� "+main.MainServerOption.damage+" : ��f" + itemYaml.getInt(itemNumbe+".MinDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MinMaDamage") != 0||itemYaml.getInt(itemNumbe+".MaxMaDamage") != 0)
				lore = lore+"��3 �� "+main.MainServerOption.magicDamage+" : ��f" + itemYaml.getInt(itemNumbe+".MinMaDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEF") != 0)
				lore = lore+"��7 �� ��� : ��f" + itemYaml.getInt(itemNumbe+".DEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Protect") != 0)
				lore = lore+"��f �� ��ȣ : ��f" +itemYaml.getInt(itemNumbe+".Protect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaDEF") != 0)
				lore = lore+"��9 �� ���� ��� : ��f" +itemYaml.getInt(itemNumbe+".MaDEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaProtect") != 0)
				lore = lore+"��1 �� ���� ��ȣ : ��f" + itemYaml.getInt(itemNumbe+".MaProtect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Balance") != 0)
				lore = lore+"��2 �� �뷱�� : ��f" + itemYaml.getInt(itemNumbe+".Balance")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Critical") != 0)
				lore = lore+"��e �� ũ��Ƽ�� : ��f" + itemYaml.getInt(itemNumbe+".Critical")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxDurability") > 0)
				lore = lore+"��6 �� ������ : ��f" + itemYaml.getInt(itemNumbe+".Durability")+" / "+ itemYaml.getInt(itemNumbe+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore + "��f �� ���õ� : 0.0%��f%enter%";
			
			lore = lore+"��6___--------------------___%enter%��6       [������ ����]";
			lore = lore+"%enter%"+ itemYaml.getString(itemNumbe+".Lore")+"%enter%";
			lore = lore+"��6---____________________---%enter%";


			if(itemYaml.getInt(itemNumbe+".HP")!=0||itemYaml.getInt(itemNumbe+".MP")!=0||
					itemYaml.getInt(itemNumbe+".STR")!=0||itemYaml.getInt(itemNumbe+".DEX")!=0||
					itemYaml.getInt(itemNumbe+".INT")!=0||itemYaml.getInt(itemNumbe+".WILL")!=0||
					itemYaml.getInt(itemNumbe+".LUK")!=0)
			{
				lore = lore+"��3___--------------------___%enter%��3       [���ʽ� �ɼ�]%enter%";
				if(itemYaml.getInt(itemNumbe+".HP") > 0)
					lore = lore+"��3 �� ����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".HP") < 0)
					lore = lore+"��c �� ����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MP") > 0)
					lore = lore+"��3 �� ���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".MP") < 0)
					lore = lore+"��c �� ���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".STR") > 0)
					lore = lore+"��3 �� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".STR") < 0)
					lore = lore+"��c �� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".DEX") > 0)
					lore = lore+"��3 �� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".DEX") < 0)
					lore = lore+"��c �� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".INT") > 0)
					lore = lore+"��3 �� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".INT") < 0)
					lore = lore+"��c �� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".WILL") > 0)
					lore = lore+"��3 �� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".WILL") < 0)
					lore = lore+"��c �� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".LUK") > 0)
					lore = lore+"��3 �� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
				else if(itemYaml.getInt(itemNumbe+".LUK") < 0)
					lore = lore+"��c �� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
				lore = lore+"��3---____________________---%enter%";
			}

			if(itemYaml.getInt(itemNumbe+".MaxSocket")!=0||itemYaml.getInt(itemNumbe+".MaxUpgrade")!=0)
			{
				lore = lore+"��d___--------------------___%enter%��d       [������ ��ȭ]%enter%";
				if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0 && itemYaml.getInt(itemNumbe+".MaxSocket") > 0)
				{
					lore = lore+"��5 �� ���� : 0 / "+itemYaml.getInt(itemNumbe+".MaxUpgrade")+"%enter%";
					lore = lore+"��9 �� �� : ";
					for(int count = 0; count <itemYaml.getInt(itemNumbe+".MaxSocket");count++)
						lore = lore+"��7�� ";
				}
				else if(itemYaml.getInt(itemNumbe+".MaxUpgrade") <= 0 && itemYaml.getInt(itemNumbe+".MaxSocket") > 0)
				{
					lore = lore+"��9 �� �� : ";
					for(int count = 0; count <itemYaml.getInt(itemNumbe+".MaxSocket");count++)
						lore = lore+"��7�� ";
				}
				else
					lore = lore+"��5 �� ���� : 0 / "+itemYaml.getInt(itemNumbe+".MaxUpgrade");
				lore = lore+"%enter%��d---____________________---%enter%";
			}
			if(itemYaml.getInt(itemNumbe+".MinLV")!=0||itemYaml.getInt(itemNumbe+".MinRLV")!=0||
					itemYaml.getInt(itemNumbe+".MinSTR")!=0||itemYaml.getInt(itemNumbe+".MinDEX")!=0||
					itemYaml.getInt(itemNumbe+".MinINT")!=0||itemYaml.getInt(itemNumbe+".MinWILL")!=0||
					itemYaml.getInt(itemNumbe+".MinLUK")!=0)
			{
				lore = lore+"��4___--------------------___%enter%��4       [���� ����]%enter%";
				if(itemYaml.getInt(itemNumbe+".MinLV") > 0)
					lore = lore+"��4 �� �ּ� ���� : " + itemYaml.getInt(itemNumbe+".MinLV")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MinRLV") > 0)
					lore = lore+"��4 �� �ּ� �������� : " + itemYaml.getInt(itemNumbe+".MinRLV")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MinSTR") > 0)
					lore = lore+"��4 �� �ּ� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".MinSTR")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MinDEX") > 0)
					lore = lore+"��4 �� �ּ� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".MinDEX")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MinINT") > 0)
					lore = lore+"��4 �� �ּ� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".MinINT")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MinWILL") > 0)
					lore = lore+"��4 �� �ּ� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".MinWILL")+"%enter%";
				if(itemYaml.getInt(itemNumbe+".MinLUK") > 0)
					lore = lore+"��4 �� �ּ� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".MinLUK")+"%enter%";
				lore = lore+"��4---____________________---%enter%";
				
			}
		}
		else if(type.equals("[��ȣ]"))
		{
			lore = lore+itemYaml.getString(itemNumbe+".Type");
			for(int count = 0; count<20-itemYaml.getString(itemNumbe+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(itemNumbe+".JOB").equals("����"))
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%��6��� ���� ���� : ��f" + itemYaml.getString(itemNumbe+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(itemNumbe+".MinDamage") != 0||itemYaml.getInt(itemNumbe+".MaxDamage") != 0)
				lore = lore+"��c �� "+main.MainServerOption.damage+" : ��f" + itemYaml.getInt(itemNumbe+".MinDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MinMaDamage") != 0||itemYaml.getInt(itemNumbe+".MaxMaDamage") != 0)
				lore = lore+"��3 �� "+main.MainServerOption.magicDamage+" : ��f" + itemYaml.getInt(itemNumbe+".MinMaDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEF") != 0)
				lore = lore+"��7 �� ��� : ��f" + itemYaml.getInt(itemNumbe+".DEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Protect") != 0)
				lore = lore+"��f �� ��ȣ : ��f" +itemYaml.getInt(itemNumbe+".Protect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaDEF") != 0)
				lore = lore+"��9 �� ���� ��� : ��f" +itemYaml.getInt(itemNumbe+".MaDEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaProtect") != 0)
				lore = lore+"��1 �� ���� ��ȣ : ��f" + itemYaml.getInt(itemNumbe+".MaProtect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Balance") != 0)
				lore = lore+"��2 �� �뷱�� : ��f" + itemYaml.getInt(itemNumbe+".Balance")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Critical") != 0)
				lore = lore+"��e �� ũ��Ƽ�� : ��f" + itemYaml.getInt(itemNumbe+".Critical")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxDurability") > 0)
				lore = lore+"��6 �� ������ : ��f" + itemYaml.getInt(itemNumbe+".Durability")+" / "+ itemYaml.getInt(itemNumbe+".MaxDurability")+"%enter%";

			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore + "��f �� ���õ� : 0.0%��f%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(itemNumbe+".Lore")+"%enter%";


			if(itemYaml.getInt(itemNumbe+".HP") > 0)
				lore = lore+"��3 �� ����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".HP") < 0)
				lore = lore+"��c �� ����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MP") > 0)
				lore = lore+"��3 �� ���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".MP") < 0)
				lore = lore+"��c �� ���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".STR") > 0)
				lore = lore+"��3 �� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".STR") < 0)
				lore = lore+"��c �� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEX") > 0)
				lore = lore+"��3 �� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".DEX") < 0)
				lore = lore+"��c �� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".INT") > 0)
				lore = lore+"��3 �� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".INT") < 0)
				lore = lore+"��c �� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".WILL") > 0)
				lore = lore+"��3 �� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".WILL") < 0)
				lore = lore+"��c �� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".LUK") > 0)
				lore = lore+"��3 �� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".LUK") < 0)
				lore = lore+"��c �� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
			
			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore+"%enter%��5 �� ���� : 0 / "+itemYaml.getInt(itemNumbe+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxSocket") > 0)
			{
				lore = lore+"%enter%��9 �� �� : ";
				for(int count = 0; count <itemYaml.getInt(itemNumbe+".MaxSocket");count++)
					lore = lore+"��7�� ";
			}

			if(itemYaml.getInt(itemNumbe+".MinLV")!=0||itemYaml.getInt(itemNumbe+".MinRLV")!=0||
					itemYaml.getInt(itemNumbe+".MinSTR")!=0||itemYaml.getInt(itemNumbe+".MinDEX")!=0||
					itemYaml.getInt(itemNumbe+".MinINT")!=0||itemYaml.getInt(itemNumbe+".MinWILL")!=0||
					itemYaml.getInt(itemNumbe+".MinLUK")!=0)
				lore = lore+"%enter%";
			
			if(itemYaml.getInt(itemNumbe+".MinLV") > 0)
				lore = lore+"��4%enter% �� �ּ� ���� : " + itemYaml.getInt(itemNumbe+".MinLV");
			if(itemYaml.getInt(itemNumbe+".MinRLV") > 0)
				lore = lore+"��4%enter% �� �ּ� �������� : " + itemYaml.getInt(itemNumbe+".MinRLV");
			if(itemYaml.getInt(itemNumbe+".MinSTR") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".MinSTR");
			if(itemYaml.getInt(itemNumbe+".MinDEX") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".MinDEX");
			if(itemYaml.getInt(itemNumbe+".MinINT") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".MinINT");
			if(itemYaml.getInt(itemNumbe+".MinWILL") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".MinWILL");
			if(itemYaml.getInt(itemNumbe+".MinLUK") > 0)
				lore = lore+"��4%enter% �� �ּ� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".MinLUK");
		}
		else if(type.equals("[�÷�]"))
		{

			lore = lore+itemYaml.getString(itemNumbe+".Type");
			for(int count = 0; count<20-itemYaml.getString(itemNumbe+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(itemNumbe+".JOB").equals("����"))
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%��6��� ���� ���� : ��f" + itemYaml.getString(itemNumbe+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(itemNumbe+".MinDamage") != 0||itemYaml.getInt(itemNumbe+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + main.MainServerOption.damage+" : ��f" + itemYaml.getInt(itemNumbe+".MinDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MinMaDamage") != 0||itemYaml.getInt(itemNumbe+".MaxMaDamage") != 0)
				lore = lore+"��3"+main.MainServerOption.magicDamage+" : ��f" + itemYaml.getInt(itemNumbe+".MinMaDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEF") != 0)
				lore = lore+"��7��� : ��f" + itemYaml.getInt(itemNumbe+".DEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Protect") != 0)
				lore = lore+"��f��ȣ : ��f" +itemYaml.getInt(itemNumbe+".Protect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaDEF") != 0)
				lore = lore+"��9���� ��� : ��f" +itemYaml.getInt(itemNumbe+".MaDEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaProtect") != 0)
				lore = lore+"��1���� ��ȣ : ��f" + itemYaml.getInt(itemNumbe+".MaProtect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Balance") != 0)
				lore = lore+"��2�뷱�� : ��f" + itemYaml.getInt(itemNumbe+".Balance")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Critical") != 0)
				lore = lore+"��eũ��Ƽ�� : ��f" + itemYaml.getInt(itemNumbe+".Critical")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxDurability") > 0)
				lore = lore+"��6������ : ��f" + itemYaml.getInt(itemNumbe+".Durability")+" / "+ itemYaml.getInt(itemNumbe+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore + "��f���õ� : 0.0%��f%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(itemNumbe+".Lore")+"%enter%";


			if(itemYaml.getInt(itemNumbe+".HP") > 0)
				lore = lore+"��3����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".HP") < 0)
				lore = lore+"��c����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MP") > 0)
				lore = lore+"��3���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".MP") < 0)
				lore = lore+"��c���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".STR") > 0)
				lore = lore+"��3"+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".STR") < 0)
				lore = lore+"��c"+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEX") > 0)
				lore = lore+"��3"+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".DEX") < 0)
				lore = lore+"��c"+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".INT") > 0)
				lore = lore+"��3"+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".INT") < 0)
				lore = lore+"��c"+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".WILL") > 0)
				lore = lore+"��3"+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".WILL") < 0)
				lore = lore+"��c"+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".LUK") > 0)
				lore = lore+"��3"+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".LUK") < 0)
				lore = lore+"��c"+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
			
			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore+"%enter%��5���� : 0 / "+itemYaml.getInt(itemNumbe+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxSocket") > 0)
			{
				lore = lore+"%enter%��9�� : ";
				for(int count = 0; count <itemYaml.getInt(itemNumbe+".MaxSocket");count++)
					lore = lore+"��7�� ";
			}
			if(itemYaml.getInt(itemNumbe+".MinLV")!=0||itemYaml.getInt(itemNumbe+".MinRLV")!=0||
					itemYaml.getInt(itemNumbe+".MinSTR")!=0||itemYaml.getInt(itemNumbe+".MinDEX")!=0||
					itemYaml.getInt(itemNumbe+".MinINT")!=0||itemYaml.getInt(itemNumbe+".MinWILL")!=0||
					itemYaml.getInt(itemNumbe+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MinLV") > 0)
				lore = lore+"%enter%��4�ּ� ���� : " + itemYaml.getInt(itemNumbe+".MinLV");
			if(itemYaml.getInt(itemNumbe+".MinRLV") > 0)
				lore = lore+"%enter%��4�ּ� �������� : " + itemYaml.getInt(itemNumbe+".MinRLV");
			if(itemYaml.getInt(itemNumbe+".MinSTR") > 0)
				lore = lore+"%enter%��4�ּ� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".MinSTR");
			if(itemYaml.getInt(itemNumbe+".MinDEX") > 0)
				lore = lore+"%enter%��4�ּ� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".MinDEX");
			if(itemYaml.getInt(itemNumbe+".MinINT") > 0)
				lore = lore+"%enter%��4�ּ� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".MinINT");
			if(itemYaml.getInt(itemNumbe+".MinWILL") > 0)
				lore = lore+"%enter%��4�ּ� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".MinWILL");
			if(itemYaml.getInt(itemNumbe+".MinLUK") > 0)
				lore = lore+"%enter%��4�ּ� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".MinLUK");
		}
		else
		{
			lore = lore+itemYaml.getString(itemNumbe+".Type");
			for(int count = 0; count<20-itemYaml.getString(itemNumbe+".Type").length();count++)
				lore = lore+" ";
			if(itemYaml.getString(itemNumbe+".JOB").equals("����"))
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%%enter%";
			else
				lore = lore+itemYaml.getString(itemNumbe+".Grade")+"%enter%��f��� ���� ���� : " + itemYaml.getString(itemNumbe+".JOB")+"%enter%%enter%";
				
			if(itemYaml.getInt(itemNumbe+".MinDamage") != 0||itemYaml.getInt(itemNumbe+".MaxDamage") != 0)
				lore = lore+"��f"+main.MainServerOption.damage+" : " + itemYaml.getInt(itemNumbe+".MinDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MinMaDamage") != 0||itemYaml.getInt(itemNumbe+".MaxMaDamage") != 0)
				lore = lore+"��f"+main.MainServerOption.magicDamage+" : " + itemYaml.getInt(itemNumbe+".MinMaDamage") + " ~ " + itemYaml.getInt(itemNumbe+".MaxMaDamage")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEF") != 0)
				lore = lore+"��f��� : " + itemYaml.getInt(itemNumbe+".DEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Protect") != 0)
				lore = lore+"��f��ȣ : " + itemYaml.getInt(itemNumbe+".Protect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaDEF") != 0)
				lore = lore+"��f���� ��� : " + itemYaml.getInt(itemNumbe+".MaDEF")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaProtect") != 0)
				lore = lore+"��f���� ��ȣ : " + itemYaml.getInt(itemNumbe+".MaProtect")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Balance") != 0)
				lore = lore+"��f�뷱�� : " + itemYaml.getInt(itemNumbe+".Balance")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".Critical") != 0)
				lore = lore+"��fũ��Ƽ�� : " + itemYaml.getInt(itemNumbe+".Critical")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxDurability") > 0)
				lore = lore+"��f������ : " + itemYaml.getInt(itemNumbe+".Durability")+" / "+ itemYaml.getInt(itemNumbe+".MaxDurability")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore + "��f���õ� : 0.0%��f%enter%";
			
			lore = lore+"%enter%"+ itemYaml.getString(itemNumbe+".Lore")+"%enter%";


			if(itemYaml.getInt(itemNumbe+".HP") > 0)
				lore = lore+"��3����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".HP") < 0)
				lore = lore+"��c����� : " + itemYaml.getInt(itemNumbe+".HP")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MP") > 0)
				lore = lore+"��3���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".MP") < 0)
				lore = lore+"��c���� : " + itemYaml.getInt(itemNumbe+".MP")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".STR") > 0)
				lore = lore+"��3"+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".STR") < 0)
				lore = lore+"��c"+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".STR")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".DEX") > 0)
				lore = lore+"��3"+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".DEX") < 0)
				lore = lore+"��c"+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".DEX")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".INT") > 0)
				lore = lore+"��3"+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".INT") < 0)
				lore = lore+"��c"+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".INT")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".WILL") > 0)
				lore = lore+"��3"+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".WILL") < 0)
				lore = lore+"��c"+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".WILL")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".LUK") > 0)
				lore = lore+"��3"+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
			else if(itemYaml.getInt(itemNumbe+".LUK") < 0)
				lore = lore+"��c"+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".LUK")+"%enter%";
			
			if(itemYaml.getInt(itemNumbe+".MaxUpgrade") > 0)
				lore = lore+"%enter%��f���� : 0 / "+itemYaml.getInt(itemNumbe+".MaxUpgrade")+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MaxSocket") > 0)
			{
				lore = lore+"%enter%��f�� : ";
				for(int count = 0; count <itemYaml.getInt(itemNumbe+".MaxSocket");count++)
					lore = lore+"��7�� ";
			}
			if(itemYaml.getInt(itemNumbe+".MinLV")!=0||itemYaml.getInt(itemNumbe+".MinRLV")!=0||
					itemYaml.getInt(itemNumbe+".MinSTR")!=0||itemYaml.getInt(itemNumbe+".MinDEX")!=0||
					itemYaml.getInt(itemNumbe+".MinINT")!=0||itemYaml.getInt(itemNumbe+".MinWILL")!=0||
					itemYaml.getInt(itemNumbe+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(itemYaml.getInt(itemNumbe+".MinLV") > 0)
				lore = lore+"%enter%��f�ּ� ���� : " + itemYaml.getInt(itemNumbe+".MinLV");
			if(itemYaml.getInt(itemNumbe+".MinRLV") > 0)
				lore = lore+"%enter%��f�ּ� �������� : " + itemYaml.getInt(itemNumbe+".MinRLV");
			if(itemYaml.getInt(itemNumbe+".MinSTR") > 0)
				lore = lore+"%enter%��f�ּ� "+main.MainServerOption.statSTR+" : " + itemYaml.getInt(itemNumbe+".MinSTR");
			if(itemYaml.getInt(itemNumbe+".MinDEX") > 0)
				lore = lore+"%enter%��f�ּ� "+main.MainServerOption.statDEX+" : " + itemYaml.getInt(itemNumbe+".MinDEX");
			if(itemYaml.getInt(itemNumbe+".MinINT") > 0)
				lore = lore+"%enter%��f�ּ� "+main.MainServerOption.statINT+" : " + itemYaml.getInt(itemNumbe+".MinINT");
			if(itemYaml.getInt(itemNumbe+".MinWILL") > 0)
				lore = lore+"%enter%��f�ּ� "+main.MainServerOption.statWILL+" : " + itemYaml.getInt(itemNumbe+".MinWILL");
			if(itemYaml.getInt(itemNumbe+".MinLUK") > 0)
				lore = lore+"%enter%��f�ּ� "+main.MainServerOption.statLUK+" : " + itemYaml.getInt(itemNumbe+".MinLUK");
		}
		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  scriptA[counter];
		return scriptA;
	}
}