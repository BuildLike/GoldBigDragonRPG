package customitem;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UseableItemGui extends UtilGui
{
	public void useableItemListGui(Player player, int page)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/Consume.yml");

		String uniqueCode = "��0��0��3��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�Ҹ� ������ ��� : " + (page+1));

		String[] consumeItemList = itemYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < consumeItemList.length;count++)
		{
			String ItemName = itemYaml.getString(consumeItemList[count]+".DisplayName");
			short ID = (short) itemYaml.getInt(consumeItemList[count]+".ID");
			byte Data = (byte) itemYaml.getInt(consumeItemList[count]+".Data");
			
			if(count > consumeItemList.length || loc >= 45) break;
			removeFlagStack(ItemName, ID,Data,1,Arrays.asList(loreCreater(Integer.parseInt(consumeItemList[count]))), loc, inv);
			loc++;
		}
		
		if(consumeItemList.length-(page*44)>45)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ������", 386,0,1,Arrays.asList("��7���ο� �������� �����մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void chooseUseableItemTypeGui(Player player, int number)
	{
		String uniqueCode = "��0��0��3��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 9, uniqueCode + "��0�Ҹ� ������ Ÿ��");

		removeFlagStack("��f��l[��ȯ��]", 340,0,1,Arrays.asList("��7Ư�� ��ġ�� �ż��� �̵��� �� �ִ�","��7��ȯ���� �����մϴ�."), 2, inv);
		removeFlagStack("��f��l[�ֹ���]", 339,0,1,Arrays.asList("��7Ư���� ����� ���","��7�ֹ����� �����մϴ�."), 3, inv);
		removeFlagStack("��f��l[��ų ��]", 403,0,1,Arrays.asList("��7Ư�� ��ų�� ��� �� �ִ�","��7��ų ���� �����մϴ�.","","��c[���� �ý����� '������'���� �մϴ�.]"), 4, inv);
		removeFlagStack("��f��l[����, ����]", 297,0,1,Arrays.asList("��7���������� ����� ������","��7���� �� ���� ���� �����մϴ�."), 5, inv);
		removeFlagStack("��f��l[��]", 381,0,1,Arrays.asList("��7������ �ɷ��� �÷��ִ�","��7�ź��� ���� �����մϴ�."), 6, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 0, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ number), 8, inv);
		player.openInventory(inv);
	}
	
	public void newUseableItemGui(Player player, int number)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/Consume.yml");

		String uniqueCode = "��0��0��3��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�Ҹ� ������ �� ����");
		String itemName = itemYaml.getString(number+".DisplayName");
		short itemId = (short) itemYaml.getInt(number+".ID");
		byte itemData = (byte) itemYaml.getInt(number+".Data");

		String type = "";
		String grade = "";
		for(int counter =0;counter < 13 - itemYaml.getString(number+".Type").length();counter++ )
			type = type +" ";
		type = type +itemYaml.getString(number+".Type");
		grade = itemYaml.getString(number+".Grade");
		
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 9, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 10, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 11, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 18, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 20, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 27, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 28, inv);
		removeFlagStack("��3[    �����    ]", 58,0,1,null, 29, inv);
		
		removeFlagStack(itemName, itemId,itemData,1,Arrays.asList(loreCreater(number)), 19, inv);
		
		removeFlagStack("��3[    ���� ����    ]", 421,0,1,Arrays.asList("��f������ ����â��","��f�����մϴ�.","","��f[    ���� ����    ]","       "+ itemYaml.getString(number+".ShowType"),""), 37, inv);
		removeFlagStack("��3[    �̸� ����    ]", 421,0,1,Arrays.asList("��f�������� �̸���","��f�����մϴ�.",""), 13, inv);
		removeFlagStack("��3[    ���� ����    ]", 386,0,1,Arrays.asList("��f�������� ������","��f�����մϴ�.",""), 14, inv);
		removeFlagStack("��3[    Ÿ�� ����    ]", 166,0,1,Arrays.asList("","��c[Ÿ�� ������ �Ұ��� �մϴ�]",""), 15, inv);
		removeFlagStack("��3[    ��� ����    ]", 266,0,1,Arrays.asList("��f�������� �����","��f�����մϴ�.","","��f[    ���� ���    ]","       "+grade,""), 16, inv);
		removeFlagStack("��3[        ID        ]", 405,0,1,Arrays.asList("��f�������� ID����","��f�����մϴ�.",""), 22, inv);
		removeFlagStack("��3[       DATA       ]", 336,0,1,Arrays.asList("��f�������� DATA����","��f�����մϴ�.",""), 23, inv);

		switch(ChatColor.stripColor(itemYaml.getString(number+".Type")))
		{
		case "[��ȯ��]":
			stack("��3[    ��ġ ����    ]", 386,0,1,Arrays.asList("��f���� �� �ִ� ��Ҹ�","��f���� �������� ��� �մϴ�.","","��9[���� ��ϵ� ��ġ]","��9���� : "+itemYaml.getString(number+".World"),"��9��ǥ : "+itemYaml.getInt(number+".X")+","+itemYaml.getInt(number+".Y")+","+itemYaml.getInt(number+".Z"),"","��e[�� Ŭ���� ���� ���� ���]",""), 25, inv);
			break;
		case "[�ֹ���]":
			stack("��3[     ��ų ����Ʈ     ]", 403,0,1,Arrays.asList("��f�ֹ��� ���� ���","��f��ų ����Ʈ�� ����ϴ�.",""), 24, inv);
			stack("��3[     ���� ����Ʈ     ]", 403,0,1,Arrays.asList("��f�ֹ��� ���� ���","��f���� ����Ʈ�� ����ϴ�.",""), 25, inv);
			stack("��3[        ���        ]", 307,0,1,Arrays.asList("��f�ֹ��� ���� ������","��f��� ���� �ݴϴ�.",""), 31, inv);
			stack("��3[        ��ȣ        ]", 306,0,1,Arrays.asList("��f�ֹ��� ���� ��ȣ��","��f��� ���� �ݴϴ�.",""), 32, inv);
			stack("��3[      ���� ���      ]", 311,0,1,Arrays.asList("��f�ֹ��� ���� ���� ��","��f��� ���� �ݴϴ�.",""), 33, inv);
			stack("��3[      ���� ��ȣ      ]", 310,0,1,Arrays.asList("��f�ֹ��� ���� ���� ��ȣ��","��f��� ���� �ݴϴ�.",""), 34, inv);
			stack("��3[        ����        ]", 399,0,1,Arrays.asList("��f�ֹ��� ���� ������ ����������","��f��� ���� �ݴϴ�.",""), 40, inv);
			break;
		case "[��ų��]":
			if(itemYaml.getString(number+".Skill").equals("null"))
				stack("��3[        ��ų        ]", 340,0,1,Arrays.asList("��f��ų �� ����","��f�Ʒ� ��ų�� �����մϴ�.","","��9[���� ��ϵ� ��ų]","��f      ����"), 25, inv);
			else
				stack("��3[        ��ų        ]", 403,0,1,Arrays.asList("��f��ų �� ����","��f�Ʒ� ��ų�� �����մϴ�.","","��9[���� ��ϵ� ��ų]","��f"+itemYaml.getString(number+".Skill")), 25, inv);
			break;
		case "[�Һ�]":
			stack("��3[       ������       ]", 364,0,1,Arrays.asList("��f������ ���� ��⸦","��f���� ���� �ݴϴ�.",""), 31, inv);
			stack("��3[       �����       ]", 373,8261,1,Arrays.asList("��f������ ���� �������","��f��� ���� �ݴϴ�.",""), 32, inv);
			stack("��3[        ����        ]", 373,8230,1,Arrays.asList("��f������ ���� ������","��f��� ���� �ݴϴ�.",""), 33, inv);
			stack("��3[        ȯ��        ]", 399,0,1,Arrays.asList("��f������ ���� �÷��̾���","��f������ �ʱ�ȭ ���� �ݴϴ�.","","��c[���� �ý����� �������� ��츸 ��� �����մϴ�.]",""), 34, inv);
			break;
		case "[��]":
			stack("��3[       �����       ]", 267,0,1,Arrays.asList("��f�� ������ "+MainServerOption.damage+"��","��f���� ���� �ݴϴ�.",""), 24, inv);
			stack("��3[     ���� �����     ]", 403,0,1,Arrays.asList("��f�� ������ "+MainServerOption.magicDamage+"��","��f���� ���� �ݴϴ�.",""), 25, inv);
			stack("��3[        ���        ]", 307,0,1,Arrays.asList("��f�� ������ ������","��f���� ���� �ݴϴ�.",""), 31, inv);
			stack("��3[        ��ȣ        ]", 306,0,1,Arrays.asList("��f�� ������ ��ȣ��","��f���� ���� �ݴϴ�.",""), 32, inv);
			stack("��3[      ���� ���      ]", 311,0,1,Arrays.asList("��f�� ������ ���� ��","��f���� ���� �ݴϴ�.",""), 33, inv);
			stack("��3[      ���� ��ȣ      ]", 310,0,1,Arrays.asList("��f�� ������ ���� ��ȣ��","��f���� ���� �ݴϴ�.",""), 34, inv);
			stack("��3[        ����        ]", 399,0,1,Arrays.asList("��f�� ������ ������ ����������","��f���� ���� �ݴϴ�.",""), 40, inv);
			stack("��3[       ������       ]", 145,2,1,Arrays.asList("��f�� ������ �������� ��������","��f���� ���� �ݴϴ�.","","��c[�Ϲ� ������ �Ұ���]",""), 41, inv);
			//Stack("��3[        ����        ]", 145,0,1,Arrays.asList("��f�� ������ �ִ� ���� Ƚ����","��f���� ���� �ݴϴ�.",""), 42, inv);
			break;
		}
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+type), 45, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+number), 53, inv);
		player.openInventory(inv);
	}
	
	public void selectSkillGui(Player player, short page, int itemNumber)
	{
	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");

		String uniqueCode = "��0��0��3��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0��� ���� ��ų ��� : " + (page+1));

		String[] skillList= skillYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < skillList.length;count++)
		{
			short jobLevel= (short) skillYaml.getConfigurationSection(skillList[count].toString()+".SkillRank").getKeys(false).size();
			if(count > skillList.length || loc >= 45) break;

		  	YamlLoader jobYaml = new YamlLoader();
			jobYaml.getConfig("Skill/JobList.yml");
			if(jobYaml.contains("Mabinogi.Added."+skillList[count])==true)
			{
				removeFlagStack("��f��l" + skillList[count],  skillYaml.getInt(skillList[count].toString()+".ID"),skillYaml.getInt(skillList[count].toString()+".DATA"),skillYaml.getInt(skillList[count].toString()+".Amount"),Arrays.asList("��3�ִ� ��ų ���� : ��f"+jobLevel,"","��e[�� Ŭ���� ��ų ���]"), loc, inv);
				loc++;	
			}
		}
		
		if(skillList.length-(page*44)>45)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+itemNumber), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	public void useableItemListGuiClick(InventoryClickEvent event)
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
			if(slot==45)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 2);
			else if(slot == 48)//���� ������
				useableItemListGui(player, page-1);
			else if(slot == 49)//�� ������
				chooseUseableItemTypeGui(player, ((page*45)+event.getSlot()));
			else if(slot == 50)//���� ������
				useableItemListGui(player, page+1);
			else
			{
				int number = ((page*45)+event.getSlot());
				if(event.isLeftClick()&&!event.isShiftClick())
				{
					player.sendMessage("��a[SYSTEM] : Ŭ���� �������� ���� �޾ҽ��ϴ�!");
					player.getInventory().addItem(event.getCurrentItem());
				}
				if(event.isLeftClick()&&event.isShiftClick())
					newUseableItemGui(player, number);
				else if(event.isRightClick()&&event.isShiftClick())
				{
				  	YamlLoader itemYaml = new YamlLoader();
					itemYaml.getConfig("Item/Consume.yml");
					short acount =  (short) (itemYaml.getKeys().toArray().length-1);

					for(int counter = number;counter <acount;counter++)
						itemYaml.set(counter+"", itemYaml.get((counter+1)+""));
					itemYaml.removeKey(acount+"");
					itemYaml.saveConfig();
					useableItemListGui(player, page);
				}
			}
		}
	}
	
	public void chooseUseableItemTypeGuiClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)
				useableItemListGui(player, 0);
			else
			{
				String type = "";
				String lore = "";
				String displayName = "";
				short id = 267;
				short data = 0;
				
				if(slot == 2)
				{
					type = "��f[��ȯ��]";
					lore = "��f��ü�� �ս� ���� ������ ������%enter%��f������ �̵��� �� �ִ� �ź���%enter%��f��ȯ �ֹ����̴�.";
					displayName = "��f0�� ��ȯ �ֹ���";
					id = 340;
				}
				else if(slot == 3)
				{
					type = "��6[�ֹ���]";
					lore = "��f���� ��ų ����Ʈ��%enter%��f5��ŭ ��½��� �ش�.";
					displayName ="��f��ų ����Ʈ 5 ���� �ֹ���";
					id = 339;
				}
				else if(slot == 4)
				{
					type = "��5[��ų��]";
					lore = "��f���� �ƹ��͵� �������� ����%enter%��f�� ������ ��ų ���̴�.%enter% %enter%��f(�ƹ��͵� ���� �� ���� �� ����.)";
					displayName = "��f�� ��ų ��";
					id = 403;
				}
				else if(slot == 5)
				{
					type = "��d[�Һ�]";
					lore = "��f�� ���Կ� ����, ���޽�%enter%��f����� ���, �������%enter%��f10 ġ���� �ִ� �����̴�.";
					displayName = "��f�û��� ����";
					id = 373;
					data = 8261;
				}
				else if(slot == 6)
				{
					type = "��9[��]";
					lore = "��f������ ����� ���̴�.%enter%��f������ �뷱���� �÷��ִ� �� �ϴ�.";
					displayName ="��f��� ��";
					id = 351;
					data = 10;
				}

			  	YamlLoader useableItemYaml = new YamlLoader();
				useableItemYaml.getConfig("Item/Consume.yml");
				
				int itemCounter = useableItemYaml.getKeys().size();
				useableItemYaml.set(itemCounter+".ShowType","��f[���]");
				useableItemYaml.set(itemCounter+".ID",id);
				useableItemYaml.set(itemCounter+".Data",data);
				useableItemYaml.set(itemCounter+".DisplayName",displayName);
				useableItemYaml.set(itemCounter+".Lore",lore);
				useableItemYaml.set(itemCounter+".Type",type);
				useableItemYaml.set(itemCounter+".Grade","��f[�Ϲ�]");
				
				if(slot == 2)
				{
					useableItemYaml.set(itemCounter+".World",player.getLocation().getWorld().getName().toString());
					useableItemYaml.set(itemCounter+".X",0);
					useableItemYaml.set(itemCounter+".Y",0);
					useableItemYaml.set(itemCounter+".Z",0);
					useableItemYaml.set(itemCounter+".Pitch",0);
					useableItemYaml.set(itemCounter+".Yaw",0);
				}
				else if(slot ==3)
				{
					useableItemYaml.set(itemCounter+".DEF",0);
					useableItemYaml.set(itemCounter+".Protect",0);
					useableItemYaml.set(itemCounter+".MaDEF",0);
					useableItemYaml.set(itemCounter+".MaProtect",0);
					useableItemYaml.set(itemCounter+".STR",0);
					useableItemYaml.set(itemCounter+".DEX",0);
					useableItemYaml.set(itemCounter+".INT",0);
					useableItemYaml.set(itemCounter+".WILL",0);
					useableItemYaml.set(itemCounter+".LUK",0);
					useableItemYaml.set(itemCounter+".Balance",0);
					useableItemYaml.set(itemCounter+".Critical",0);
					useableItemYaml.set(itemCounter+".HP",0);
					useableItemYaml.set(itemCounter+".MP",0);
					useableItemYaml.set(itemCounter+".SkillPoint",5);
					useableItemYaml.set(itemCounter+".StatPoint",0);
				}
				else if(slot ==4)
					useableItemYaml.set(itemCounter+".Skill","null");
				else if(slot ==5)
				{
					useableItemYaml.set(itemCounter+".HP",10);
					useableItemYaml.set(itemCounter+".MP",0);
					useableItemYaml.set(itemCounter+".Saturation",0);
					useableItemYaml.set(itemCounter+".Rebirth",false);
				}
				else if(slot ==6)
				{
					useableItemYaml.set(itemCounter+".MinDamage",0);
					useableItemYaml.set(itemCounter+".MaxDamage",0);
					useableItemYaml.set(itemCounter+".MinMaDamage",0);
					useableItemYaml.set(itemCounter+".MaxMaDamage",0);
					useableItemYaml.set(itemCounter+".DEF",0);
					useableItemYaml.set(itemCounter+".Protect",0);
					useableItemYaml.set(itemCounter+".MaDEF",0);
					useableItemYaml.set(itemCounter+".MaProtect",0);
					useableItemYaml.set(itemCounter+".Durability",0);
					useableItemYaml.set(itemCounter+".MaxDurability",0);
					useableItemYaml.set(itemCounter+".STR",0);
					useableItemYaml.set(itemCounter+".DEX",0);
					useableItemYaml.set(itemCounter+".INT",0);
					useableItemYaml.set(itemCounter+".WILL",0);
					useableItemYaml.set(itemCounter+".LUK",0);
					useableItemYaml.set(itemCounter+".Balance",10);
					useableItemYaml.set(itemCounter+".Critical",0);
					useableItemYaml.set(itemCounter+".HP",0);
					useableItemYaml.set(itemCounter+".MP",0);
				}
				useableItemYaml.saveConfig();
				newUseableItemGui(player,itemCounter);
			}
		}
	}

	public void newUseableItemGuiClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		String iconName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
		
		int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
		if(iconName.equals("[        ��ų        ]"))
		{
		  	YamlLoader configYaml = new YamlLoader();
			configYaml.getConfig("config.yml");
			
			if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System")==true)
			{
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				selectSkillGui(player, (short) 0, itemnumber);
			}
			else
			{
				SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[��ų �� ����] : ���� ���� �ý����� ��e'������'��c�� �ƴմϴ�!");
			}
		}
		else if(iconName.equals("[    ��ġ ����    ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			useableItemYaml.set(itemnumber+".World", player.getLocation().getWorld().getName().toString());
			useableItemYaml.set(itemnumber+".X", player.getLocation().getX());
			useableItemYaml.set(itemnumber+".Y", player.getLocation().getY());
			useableItemYaml.set(itemnumber+".Z", player.getLocation().getZ());
			useableItemYaml.set(itemnumber+".Pitch", player.getLocation().getPitch());
			useableItemYaml.set(itemnumber+".Yaw", player.getLocation().getYaw());
			useableItemYaml.saveConfig();
			newUseableItemGui(player, itemnumber);
		}
		else if(iconName.equals("[        ȯ��        ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(useableItemYaml.getBoolean(itemnumber+".Rebirth") == false)
				useableItemYaml.set(itemnumber+".Rebirth", true);
			else
				useableItemYaml.set(itemnumber+".Rebirth", false);
			useableItemYaml.saveConfig();
			newUseableItemGui(player, itemnumber);
		}
		else if(iconName.equals("[    ���� ����    ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(useableItemYaml.getString(itemnumber+".ShowType").contains("[���]"))
				useableItemYaml.set(itemnumber+".ShowType","��e[�÷�]");
			else if(useableItemYaml.getString(itemnumber+".ShowType").contains("[�÷�]"))
				useableItemYaml.set(itemnumber+".ShowType","��d[��ȣ]");
			else if(useableItemYaml.getString(itemnumber+".ShowType").contains("[��ȣ]"))
				useableItemYaml.set(itemnumber+".ShowType","��9[�з�]");
			else if(useableItemYaml.getString(itemnumber+".ShowType").contains("[�з�]"))
				useableItemYaml.set(itemnumber+".ShowType","��f[���]");
			useableItemYaml.saveConfig();
			newUseableItemGui(player, itemnumber);
		}
		else if(iconName.equals("[    Ÿ�� ����    ]"))
			SoundEffect.playSound(player, Sound.BLOCK_ANVIL_LAND, 0.8F, 1.8F);
		else if(iconName.equals("[    ��� ����    ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(useableItemYaml.getString(itemnumber+".Grade").contains("[�Ϲ�]"))
				useableItemYaml.set(itemnumber+".Grade","��a[���]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("[���]"))
				useableItemYaml.set(itemnumber+".Grade","��9[����]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("[����]"))
				useableItemYaml.set(itemnumber+".Grade","��e[����]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("[����]"))
				useableItemYaml.set(itemnumber+".Grade","��5[����]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("[����]"))
				useableItemYaml.set(itemnumber+".Grade","��6[����]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("[����]"))
				useableItemYaml.set(itemnumber+".Grade","��4��l[��6��l�ʡ�2��l����1��l]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("��"))
				useableItemYaml.set(itemnumber+".Grade","��7[�ϱ�]");
			else if(useableItemYaml.getString(itemnumber+".Grade").contains("[�ϱ�]"))
				useableItemYaml.set(itemnumber+".Grade","��f[�Ϲ�]");
			useableItemYaml.saveConfig();
			newUseableItemGui(player, itemnumber);
		}
		else if(iconName.equals("���� ���"))
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			useableItemListGui(player, 0);
		}
		else if(iconName.equals("�ݱ�"))
		{
				SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
		}
		else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
		{
			UserDataObject u = new UserDataObject();
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			u.setType(player, "UseableItem");
			u.setInt(player, (byte)3, itemnumber);
			u.setInt(player, (byte)4, -1);
			player.closeInventory();
			if(iconName.equals("[       ������       ]"))
			{
				player.sendMessage("��3[������] : ȸ���� �������� �Է��� �ּ���!");
				u.setString(player, (byte)1, "Saturation");
			}
			else if(iconName.equals("[       �����       ]"))
			{
				player.sendMessage("��3[������] : ȸ���� ������� �Է��� �ּ���!");
				u.setString(player, (byte)1, "HP");
				u.setInt(player, (byte)4, -8);
			}
			else if(iconName.equals("[        ����        ]"))
			{
				player.sendMessage("��3[������] : ȸ���� ������ �Է��� �ּ���!");
				u.setString(player, (byte)1, "MP");
				u.setInt(player, (byte)4, -8);
			}
			else if(iconName.equals("[     ��ų ����Ʈ     ]"))
			{
				player.sendMessage("��3[������] : �ְ��� �ϴ� ��ų ����Ʈ�� ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "SkillPoint");
			}
			else if(iconName.equals("[     ���� ����Ʈ     ]"))
			{
				player.sendMessage("��3[������] : �ְ��� �ϴ� ���� ����Ʈ�� ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "StatPoint");
			}
			else if(iconName.equals("[        ID        ]"))
			{
				player.sendMessage("��3[������] : �������� ID ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "ID");
			}
			else if(iconName.equals("[       DATA       ]"))
			{
				player.sendMessage("��3[������] : �������� DATA ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "Data");
			}
			else if(iconName.equals("[       �����       ]"))
			{
				player.sendMessage("��3[������] : �������� �ּ� "+MainServerOption.damage+"�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MinDamage");
			}
			else if(iconName.equals("[     ���� �����     ]"))
			{
				player.sendMessage("��3[������] : �������� �ּ� "+MainServerOption.magicDamage+"�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MinMaDamage");
			}
			else if(iconName.equals("[        ���        ]"))
			{
				player.sendMessage("��3[������] : �������� ������ �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "DEF");
			}
			else if(iconName.equals("[        ��ȣ        ]"))
			{
				player.sendMessage("��3[������] : �������� ��ȣ�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "Protect");
			}
			else if(iconName.equals("[      ���� ���      ]"))
			{
				player.sendMessage("��3[������] : �������� ���� ������ �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MaDEF");
			}
			else if(iconName.equals("[      ���� ��ȣ      ]"))
			{
				player.sendMessage("��3[������] : �������� ���� ��ȣ�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MaProtect");
			}
			else if(iconName.equals("[        ����        ]"))
			{
				player.sendMessage("��3[������] : �������� ����� ���ʽ��� �Է��� �ּ���!");
				player.sendMessage("��3(-127 ~ 127)");
				u.setString(player, (byte)1, "HP");
			}
			else if(iconName.equals("[       ������       ]"))
			{
				player.sendMessage("��3[������] : �������� �ִ� �������� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MaxDurability");
			}
			else
			{
				if(iconName.equals("[    �̸� ����    ]"))
				{
					player.sendMessage("��3[������] : �������� �̸��� �Է��� �ּ���!");
					u.setString(player, (byte)1, "DisplayName");
				}
				else if(iconName.equals("[    ���� ����    ]"))
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
			
		}
	}
	
	public void selectSkillGuiClick(InventoryClickEvent event)
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
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				newUseableItemGui(player, itemnumber);
			else if(slot == 48)//���� ������
				selectSkillGui(player, (short) (page-1), itemnumber);
			else if(slot == 50)//���� ������
				selectSkillGui(player, (short) (page-1), itemnumber);
			else
			{
			  	YamlLoader useableItemYaml = new YamlLoader();
				useableItemYaml.getConfig("Item/Consume.yml");
				useableItemYaml.set(itemnumber+".Skill", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				useableItemYaml.saveConfig();
				newUseableItemGui(player, itemnumber);
			}
		}
	}
	
	
	public String[] loreCreater(int itemNumber)
	{
	  	YamlLoader useableItemYaml = new YamlLoader();
		useableItemYaml.getConfig("Item/Consume.yml");
		String lore = "";
		String type = ChatColor.stripColor(useableItemYaml.getString(itemNumber+".ShowType"));

		lore = lore+useableItemYaml.getString(itemNumber+".Type");
		for(int count = 0; count<20-useableItemYaml.getString(itemNumber+".Type").length();count++)
			lore = lore+" ";
		lore = lore+useableItemYaml.getString(itemNumber+".Grade")+"%enter% %enter%";
		
		switch(type)
		{
		case "[�з�]":
		{
			switch(ChatColor.stripColor(useableItemYaml.getString(itemNumber+".Type")))
			{
			case "[��ȯ��]":
				lore = lore+"��9 �� ���� : ��f" + useableItemYaml.getString(itemNumber+".World")+"%enter%";
				lore = lore+"��9 �� X ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".X")+"%enter%";
				lore = lore+"��9 �� Y ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Y")+"%enter%";
				lore = lore+"��9 �� Z ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Z")+"%enter%";
				break;
			case "[�ֹ���]":
				if(useableItemYaml.getInt(itemNumber+".StatPoint")>0)
					lore = lore+"��3 + ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".StatPoint")<0)
					lore = lore+"��c - ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".SkillPoint")>0)
					lore = lore+"��3 + ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".SkillPoint")<0)
					lore = lore+"��c - ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				
					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".STR") > 0)
						lore = lore+"��3 + "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
						lore = lore+"��c - "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
						lore = lore+"��3 + "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
						lore = lore+"��c - "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".INT") > 0)
						lore = lore+"��3 + "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
						lore = lore+"��c - "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
						lore = lore+"��3 + "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
						lore = lore+"��c - "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
						lore = lore+"��3 + "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
						lore = lore+"��c - "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
				break;
			case "[��ų��]":
				if(useableItemYaml.getString(itemNumber+".Skill").equals("null"))
					lore = lore+"%enter%��c [�ƹ��͵� ���� �� å]%enter%";
				else
					lore = lore+"%enter%"+ChatColor.DARK_AQUA  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��3 + ��f"+useableItemYaml.getString(itemNumber+".Skill")+"%enter%";
				break;
			case "[�Һ�]":
				if(useableItemYaml.getInt(itemNumber+".HP") > 0)
					lore = lore+"��3 + ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
					lore = lore+"��c - ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MP") > 0)
					lore = lore+"��3 + ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
					lore = lore+"��c - ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Saturation") > 0)
					lore = lore+"��3 + ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".Saturation") < 0)
					lore = lore+"��c - ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
				if(useableItemYaml.getBoolean(itemNumber+".Rebirth") == true)
					lore = lore+"��6��l + ȯ��%enter%";
				break;
			case "[��]":
				if(useableItemYaml.getInt(itemNumber+".MinDamage")>0)
					lore = lore+"��3 + �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MinDamage")<0)
					lore = lore+"��c - �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxDamage")>0)
					lore = lore+"��3 + �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxDamage")<0)
					lore = lore+"��c - �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MinMaDamage")>0)
					lore = lore+"��3 + �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MinMaDamage")<0)
					lore = lore+"��c - �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")>0)
					lore = lore+"��3 + �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")<0)
					lore = lore+"��c - �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";

				if(useableItemYaml.getInt(itemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";

				if(useableItemYaml.getInt(itemNumber+".Durability")>0)
					lore = lore+"��3 + ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".Durability")<0)
					lore = lore+"��c - ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxDurability")>0)
					lore = lore+"��3 + �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxDurability")<0)
					lore = lore+"��c - �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";


				if(useableItemYaml.getInt(itemNumber+".HP")!=0||useableItemYaml.getInt(itemNumber+".MP")!=0||
				useableItemYaml.getInt(itemNumber+".STR")!=0||useableItemYaml.getInt(itemNumber+".DEX")!=0||
				useableItemYaml.getInt(itemNumber+".INT")!=0||useableItemYaml.getInt(itemNumber+".WILL")!=0||
				useableItemYaml.getInt(itemNumber+".LUK")!=0)
				{
					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".STR") > 0)
						lore = lore+"��3 + "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
						lore = lore+"��c - "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
						lore = lore+"��3 + "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
						lore = lore+"��c - "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".INT") > 0)
						lore = lore+"��3 + "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
						lore = lore+"��c - "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
						lore = lore+"��3 + "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
						lore = lore+"��c - "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
						lore = lore+"��3 + "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
						lore = lore+"��c - "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") > 0)
						lore = lore+"��5 + ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") < 0)
						lore = lore+"��c - ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
				}
				break;
			}

			lore = lore+"%enter%��6___--------------------___%enter%��6       [������ ����]";
			lore = lore+"%enter%"+ useableItemYaml.getString(itemNumber+".Lore")+"%enter%";
			lore = lore+"��6---____________________---%enter%";
		}
		break;
		case "[��ȣ]":
		{
			switch(ChatColor.stripColor(useableItemYaml.getString(itemNumber+".Type")))
			{
			case "[��ȯ��]":
				lore = lore+"��9 �� ���� : ��f" + useableItemYaml.getString(itemNumber+".World")+"%enter%";
				lore = lore+"��9 �� X ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".X")+"%enter%";
				lore = lore+"��9 �� Y ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Y")+"%enter%";
				lore = lore+"��9 �� Z ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Z")+"%enter%";
				break;
			case "[�ֹ���]":
				if(useableItemYaml.getInt(itemNumber+".StatPoint")>0)
					lore = lore+"��3 + ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".StatPoint")<0)
					lore = lore+"��c - ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".SkillPoint")>0)
					lore = lore+"��3 + ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".SkillPoint")<0)
					lore = lore+"��c - ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				
					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".STR") > 0)
						lore = lore+"��3 + "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
						lore = lore+"��c - "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
						lore = lore+"��3 + "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
						lore = lore+"��c - "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".INT") > 0)
						lore = lore+"��3 + "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
						lore = lore+"��c - "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
						lore = lore+"��3 + "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
						lore = lore+"��c - "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
						lore = lore+"��3 + "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
						lore = lore+"��c - "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
				break;
			case "[��ų��]":
				if(useableItemYaml.getString(itemNumber+".Skill").equals("null"))
					lore = lore+"%enter%��c [�ƹ��͵� ���� �� å]%enter%";
				else
					lore = lore+"%enter%"+ChatColor.DARK_AQUA  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��3 + ��f"+useableItemYaml.getString(itemNumber+".Skill")+"%enter%";
				break;
			case "[�Һ�]":
				if(useableItemYaml.getInt(itemNumber+".HP") > 0)
					lore = lore+"��3 + ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
					lore = lore+"��c - ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MP") > 0)
					lore = lore+"��3 + ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
					lore = lore+"��c - ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Saturation") > 0)
					lore = lore+"��3 + ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".Saturation") < 0)
					lore = lore+"��c - ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
				if(useableItemYaml.getBoolean(itemNumber+".Rebirth") == true)
					lore = lore+"��6��l + ȯ��%enter%";
				break;
			case "[��]":
				if(useableItemYaml.getInt(itemNumber+".MinDamage")>0)
					lore = lore+"��3 + �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MinDamage")<0)
					lore = lore+"��c - �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxDamage")>0)
					lore = lore+"��3 + �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxDamage")<0)
					lore = lore+"��c - �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MinMaDamage")>0)
					lore = lore+"��3 + �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MinMaDamage")<0)
					lore = lore+"��c - �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")>0)
					lore = lore+"��3 + �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")<0)
					lore = lore+"��c - �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";

				if(useableItemYaml.getInt(itemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Durability")>0)
					lore = lore+"��3 + ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".Durability")<0)
					lore = lore+"��c - ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxDurability")>0)
					lore = lore+"��3 + �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxDurability")<0)
					lore = lore+"��c - �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";

				if(useableItemYaml.getInt(itemNumber+".HP")!=0||useableItemYaml.getInt(itemNumber+".MP")!=0||
				useableItemYaml.getInt(itemNumber+".STR")!=0||useableItemYaml.getInt(itemNumber+".DEX")!=0||
				useableItemYaml.getInt(itemNumber+".INT")!=0||useableItemYaml.getInt(itemNumber+".WILL")!=0||
				useableItemYaml.getInt(itemNumber+".LUK")!=0)
				{
					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".STR") > 0)
						lore = lore+"��3 + "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
						lore = lore+"��c - "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
						lore = lore+"��3 + "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
						lore = lore+"��c - "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".INT") > 0)
						lore = lore+"��3 + "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
						lore = lore+"��c - "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
						lore = lore+"��3 + "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
						lore = lore+"��c - "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
						lore = lore+"��3 + "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
						lore = lore+"��c - "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") > 0)
						lore = lore+"��5 + ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") < 0)
						lore = lore+"��c - ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
				}
				break;
			}			
			
			lore = lore+"%enter%"+ useableItemYaml.getString(itemNumber+".Lore")+"%enter%%enter%";

		}
		break;
		case "[�÷�]":
		{
			switch(ChatColor.stripColor(useableItemYaml.getString(itemNumber+".Type")))
			{
			case "[��ȯ��]":
				lore = lore+"��9 ���� : ��f" + useableItemYaml.getString(itemNumber+".World")+"%enter%";
				lore = lore+"��9 X ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".X")+"%enter%";
				lore = lore+"��9 Y ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Y")+"%enter%";
				lore = lore+"��9 Z ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Z")+"%enter%";
				break;
			case "[�ֹ���]":
				if(useableItemYaml.getInt(itemNumber+".StatPoint")>0)
					lore = lore+"��3 ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".StatPoint")<0)
					lore = lore+"��c ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".SkillPoint")>0)
					lore = lore+"��3 ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".SkillPoint")<0)
					lore = lore+"��c ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".DEF")>0)
					lore = lore+"��3 ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
					lore = lore+"��c ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")>0)
					lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")<0)
					lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
					lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
					lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
					lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
					lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")>0)
					lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")<0)
					lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")>0)
					lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")<0)
					lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				
					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".STR") > 0)
						lore = lore+"��3 "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
						lore = lore+"��c "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
						lore = lore+"��3 "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
						lore = lore+"��c "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".INT") > 0)
						lore = lore+"��3 "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
						lore = lore+"��c "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
						lore = lore+"��3 "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
						lore = lore+"��c "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
						lore = lore+"��3 "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
						lore = lore+"��c "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
				break;
			case "[��ų��]":
				if(useableItemYaml.getString(itemNumber+".Skill").equals("null"))
					lore = lore+"%enter%��c [�ƹ��͵� ���� �� å]%enter%";
				else
					lore = lore+"%enter%"+ChatColor.DARK_AQUA  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��3 + ��f"+useableItemYaml.getString(itemNumber+".Skill")+"%enter%";
				break;
			case "[�Һ�]":
				if(useableItemYaml.getInt(itemNumber+".HP") > 0)
					lore = lore+"��3 ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
					lore = lore+"��c ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MP") > 0)
					lore = lore+"��3 ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
					lore = lore+"��c ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Saturation") > 0)
					lore = lore+"��3 ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".Saturation") < 0)
					lore = lore+"��c ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
				if(useableItemYaml.getBoolean(itemNumber+".Rebirth") == true)
					lore = lore+"��6��l + ȯ��%enter%";
				break;
			case "[��]":
				if(useableItemYaml.getInt(itemNumber+".MinDamage")>0)
					lore = lore+"��3 �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MinDamage")<0)
					lore = lore+"��c �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxDamage")>0)
					lore = lore+"��3 �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxDamage")<0)
					lore = lore+"��c �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MinMaDamage")>0)
					lore = lore+"��3 �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MinMaDamage")<0)
					lore = lore+"��c �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")>0)
					lore = lore+"��3 �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")<0)
					lore = lore+"��c �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";

				if(useableItemYaml.getInt(itemNumber+".DEF")>0)
					lore = lore+"��3 ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
					lore = lore+"��c ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")>0)
					lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Protect")<0)
					lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
					lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
					lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
					lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
					lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")>0)
					lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Balance")<0)
					lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")>0)
					lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".Critical")<0)
					lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";

				if(useableItemYaml.getInt(itemNumber+".Durability")>0)
					lore = lore+"��3 ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".Durability")<0)
					lore = lore+"��c ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
				if(useableItemYaml.getInt(itemNumber+".MaxDurability")>0)
					lore = lore+"��3 �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";
				else if(useableItemYaml.getInt(itemNumber+".MaxDurability")<0)
					lore = lore+"��c �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";


					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".STR") > 0)
						lore = lore+"��3 "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
						lore = lore+"��c "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
						lore = lore+"��3 "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
						lore = lore+"��c "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".INT") > 0)
						lore = lore+"��3 "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
						lore = lore+"��c "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
						lore = lore+"��3 "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
						lore = lore+"��c "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
						lore = lore+"��3 "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
						lore = lore+"��c "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") > 0)
						lore = lore+"��5 ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") < 0)
						lore = lore+"��c ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
				break;
			}
			
			lore = lore+"%enter%"+ useableItemYaml.getString(itemNumber+".Lore")+"%enter%%enter%";


		}
		break;
		
			case "[���]":
			{
				switch(ChatColor.stripColor(useableItemYaml.getString(itemNumber+".Type")))
				{
				case "[��ȯ��]":
					lore = lore+"��f ���� : ��f" + useableItemYaml.getString(itemNumber+".World")+"%enter%";
					lore = lore+"��f X ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".X")+"%enter%";
					lore = lore+"��f Y ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Y")+"%enter%";
					lore = lore+"��f Z ��ǥ : ��f" + useableItemYaml.getInt(itemNumber+".Z")+"%enter%";
					break;
				case "[�ֹ���]":
					if(useableItemYaml.getInt(itemNumber+".StatPoint")>0)
						lore = lore+"��3 ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".StatPoint")<0)
						lore = lore+"��c ���� ����Ʈ : " + useableItemYaml.getInt(itemNumber+".StatPoint")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".SkillPoint")>0)
						lore = lore+"��3 ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".SkillPoint")<0)
						lore = lore+"��c ��ų ����Ʈ : " + useableItemYaml.getInt(itemNumber+".SkillPoint")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".DEF")>0)
						lore = lore+"��3 ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
						lore = lore+"��c ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Protect")>0)
						lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Protect")<0)
						lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
						lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
						lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
						lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
						lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Balance")>0)
						lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Balance")<0)
						lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Critical")>0)
						lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Critical")<0)
						lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
					
						if(useableItemYaml.getInt(itemNumber+".HP") > 0)
							lore = lore+"��3 ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
							lore = lore+"��c ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".MP") > 0)
							lore = lore+"��3 ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
							lore = lore+"��c ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".STR") > 0)
							lore = lore+"��3 "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
							lore = lore+"��c "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
							lore = lore+"��3 "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
							lore = lore+"��c "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".INT") > 0)
							lore = lore+"��3 "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
							lore = lore+"��c "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
							lore = lore+"��3 "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
							lore = lore+"��c "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
							lore = lore+"��3 "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
							lore = lore+"��c "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
					break;
				case "[��ų��]":
					if(useableItemYaml.getString(itemNumber+".Skill").equals("null"))
						lore = lore+"%enter%��f [�ƹ��͵� ���� �� å]%enter%";
					else
						lore = lore+"%enter%��f"  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��f + ��f"+useableItemYaml.getString(itemNumber+".Skill")+"%enter%";
					break;
				case "[�Һ�]":
					if(useableItemYaml.getInt(itemNumber+".HP") > 0)
						lore = lore+"��3 ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
						lore = lore+"��c ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MP") > 0)
						lore = lore+"��3 ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
						lore = lore+"��c ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Saturation") > 0)
						lore = lore+"��3 ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".Saturation") < 0)
						lore = lore+"��c ������ : " + useableItemYaml.getInt(itemNumber+".Saturation")+"%enter%";
					if(useableItemYaml.getBoolean(itemNumber+".Rebirth") == true)
						lore = lore+"��6��l + ȯ��%enter%";
					break;
				case "[��]":
					if(useableItemYaml.getInt(itemNumber+".MinDamage")>0)
						lore = lore+"��3 �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MinDamage")<0)
						lore = lore+"��c �ּ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinDamage")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxDamage")>0)
						lore = lore+"��3 �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MaxDamage")<0)
						lore = lore+"��c �ִ� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxDamage")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MinMaDamage")>0)
						lore = lore+"��3 �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MinMaDamage")<0)
						lore = lore+"��c �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MinMaDamage")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")>0)
						lore = lore+"��3 �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MaxMaDamage")<0)
						lore = lore+"��c �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(itemNumber+".MaxMaDamage")+"%enter%";
					
					
						
					if(useableItemYaml.getInt(itemNumber+".DEF")>0)
						lore = lore+"��3 ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".DEF")<0)
						lore = lore+"��c ��� : " + useableItemYaml.getInt(itemNumber+".DEF")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Protect")>0)
						lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Protect")<0)
						lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(itemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaDEF")>0)
						lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaDEF")<0)
						lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(itemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaProtect")>0)
						lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaProtect")<0)
						lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(itemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Balance")>0)
						lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Balance")<0)
						lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(itemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Critical")>0)
						lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Critical")<0)
						lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(itemNumber+".Critical")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".Durability")>0)
						lore = lore+"��3 ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".Durability")<0)
						lore = lore+"��c ���� ������ ���� : " + useableItemYaml.getInt(itemNumber+".Durability")+"%enter%";
					if(useableItemYaml.getInt(itemNumber+".MaxDurability")>0)
						lore = lore+"��3 �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";
					else if(useableItemYaml.getInt(itemNumber+".MaxDurability")<0)
						lore = lore+"��c �ִ� ������ ���� : " + useableItemYaml.getInt(itemNumber+".MaxDurability")+"%enter%";
					
						if(useableItemYaml.getInt(itemNumber+".HP") > 0)
							lore = lore+"��3 ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".HP") < 0)
							lore = lore+"��c ����� : " + useableItemYaml.getInt(itemNumber+".HP")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".MP") > 0)
							lore = lore+"��3 ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".MP") < 0)
							lore = lore+"��c ���� : " + useableItemYaml.getInt(itemNumber+".MP")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".STR") > 0)
							lore = lore+"��3 "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".STR") < 0)
							lore = lore+"��c "+MainServerOption.statSTR+" : " + useableItemYaml.getInt(itemNumber+".STR")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".DEX") > 0)
							lore = lore+"��3 "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".DEX") < 0)
							lore = lore+"��c "+MainServerOption.statDEX+" : " + useableItemYaml.getInt(itemNumber+".DEX")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".INT") > 0)
							lore = lore+"��3 "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".INT") < 0)
							lore = lore+"��c "+MainServerOption.statINT+" : " + useableItemYaml.getInt(itemNumber+".INT")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".WILL") > 0)
							lore = lore+"��3 "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".WILL") < 0)
							lore = lore+"��c "+MainServerOption.statWILL+" : " + useableItemYaml.getInt(itemNumber+".WILL")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".LUK") > 0)
							lore = lore+"��3 "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
						else if(useableItemYaml.getInt(itemNumber+".LUK") < 0)
							lore = lore+"��c "+MainServerOption.statLUK+" : " + useableItemYaml.getInt(itemNumber+".LUK")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") > 0)
							lore = lore+"��5 ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
						if(useableItemYaml.getInt(itemNumber+".MaxUpgrade") < 0)
							lore = lore+"��3 ���� : " +useableItemYaml.getInt(itemNumber+".MaxUpgrade")+"%enter%";
					break;
				}
				
				lore = lore+"%enter%"+ useableItemYaml.getString(itemNumber+".Lore")+"%enter%%enter%";

			}
			break;
		}

		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  scriptA[counter];
		return scriptA;
	}
}
