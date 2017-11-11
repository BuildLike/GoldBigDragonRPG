package customitem;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;

import admin.OPbox_GUI;
import effect.SoundEffect;
import main.Main_ServerOption;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UseableItem_GUI extends Util_GUI
{
	public void UseableItemListGUI(Player player, int page)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/Consume.yml");

		String UniqueCode = "��0��0��3��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�Ҹ� ������ ��� : " + (page+1));

		String[] consumeItemList = itemYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < consumeItemList.length;count++)
		{
			String ItemName = itemYaml.getString(consumeItemList[count]+".DisplayName");
			short ID = (short) itemYaml.getInt(consumeItemList[count]+".ID");
			byte Data = (byte) itemYaml.getInt(consumeItemList[count]+".Data");
			
			if(count > consumeItemList.length || loc >= 45) break;
			Stack2(ItemName, ID,Data,1,Arrays.asList(LoreCreater(Integer.parseInt(consumeItemList[count]))), loc, inv);
			loc++;
		}
		
		if(consumeItemList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ������", 386,0,1,Arrays.asList("��7���ο� �������� �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void ChooseUseableItemTypeGUI(Player player, int number)
	{
		String UniqueCode = "��0��0��3��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0�Ҹ� ������ Ÿ��");

		Stack2("��f��l[��ȯ��]", 340,0,1,Arrays.asList("��7Ư�� ��ġ�� �ż��� �̵��� �� �ִ�","��7��ȯ���� �����մϴ�."), 2, inv);
		Stack2("��f��l[�ֹ���]", 339,0,1,Arrays.asList("��7Ư���� ����� ���","��7�ֹ����� �����մϴ�."), 3, inv);
		Stack2("��f��l[��ų ��]", 403,0,1,Arrays.asList("��7Ư�� ��ų�� ��� �� �ִ�","��7��ų ���� �����մϴ�.","","��c[���� �ý����� '������'���� �մϴ�.]"), 4, inv);
		Stack2("��f��l[����, ����]", 297,0,1,Arrays.asList("��7���������� ����� ������","��7���� �� ���� ���� �����մϴ�."), 5, inv);
		Stack2("��f��l[��]", 381,0,1,Arrays.asList("��7������ �ɷ��� �÷��ִ�","��7�ź��� ���� �����մϴ�."), 6, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ number), 8, inv);
		player.openInventory(inv);
	}
	
	public void NewUseableItemGUI(Player player, int number)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/Consume.yml");

		String UniqueCode = "��0��0��3��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�Ҹ� ������ �� ����");
		String ItemName = itemYaml.getString(number+".DisplayName");
		short ItemID = (short) itemYaml.getInt(number+".ID");
		byte ItemData = (byte) itemYaml.getInt(number+".Data");

		String Type = "";
		String Grade = "";
		for(int counter =0;counter < 13 - itemYaml.getString(number+".Type").length();counter++ )
			Type = Type +" ";
		Type = Type +itemYaml.getString(number+".Type");
		Grade = itemYaml.getString(number+".Grade");
		
		Stack2("��3[    �����    ]", 58,0,1,null, 9, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 10, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 11, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 18, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 20, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 27, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 28, inv);
		Stack2("��3[    �����    ]", 58,0,1,null, 29, inv);
		
		Stack2(ItemName, ItemID,ItemData,1,Arrays.asList(LoreCreater(number)), 19, inv);
		
		Stack2("��3[    ���� ����    ]", 421,0,1,Arrays.asList("��f������ ����â��","��f�����մϴ�.","","��f[    ���� ����    ]","       "+ itemYaml.getString(number+".ShowType"),""), 37, inv);
		Stack2("��3[    �̸� ����    ]", 421,0,1,Arrays.asList("��f�������� �̸���","��f�����մϴ�.",""), 13, inv);
		Stack2("��3[    ���� ����    ]", 386,0,1,Arrays.asList("��f�������� ������","��f�����մϴ�.",""), 14, inv);
		Stack2("��3[    Ÿ�� ����    ]", 166,0,1,Arrays.asList("","��c[Ÿ�� ������ �Ұ��� �մϴ�]",""), 15, inv);
		Stack2("��3[    ��� ����    ]", 266,0,1,Arrays.asList("��f�������� �����","��f�����մϴ�.","","��f[    ���� ���    ]","       "+Grade,""), 16, inv);
		Stack2("��3[        ID        ]", 405,0,1,Arrays.asList("��f�������� ID����","��f�����մϴ�.",""), 22, inv);
		Stack2("��3[       DATA       ]", 336,0,1,Arrays.asList("��f�������� DATA����","��f�����մϴ�.",""), 23, inv);

		switch(ChatColor.stripColor(itemYaml.getString(number+".Type")))
		{
		case "[��ȯ��]":
			Stack("��3[    ��ġ ����    ]", 386,0,1,Arrays.asList("��f���� �� �ִ� ��Ҹ�","��f���� �������� ��� �մϴ�.","","��9[���� ��ϵ� ��ġ]","��9���� : "+itemYaml.getString(number+".World"),"��9��ǥ : "+itemYaml.getInt(number+".X")+","+itemYaml.getInt(number+".Y")+","+itemYaml.getInt(number+".Z"),"","��e[�� Ŭ���� ���� ���� ���]",""), 25, inv);
			break;
		case "[�ֹ���]":
			Stack("��3[     ��ų ����Ʈ     ]", 403,0,1,Arrays.asList("��f�ֹ��� ���� ���","��f��ų ����Ʈ�� ����ϴ�.",""), 24, inv);
			Stack("��3[     ���� ����Ʈ     ]", 403,0,1,Arrays.asList("��f�ֹ��� ���� ���","��f���� ����Ʈ�� ����ϴ�.",""), 25, inv);
			Stack("��3[        ���        ]", 307,0,1,Arrays.asList("��f�ֹ��� ���� ������","��f��� ���� �ݴϴ�.",""), 31, inv);
			Stack("��3[        ��ȣ        ]", 306,0,1,Arrays.asList("��f�ֹ��� ���� ��ȣ��","��f��� ���� �ݴϴ�.",""), 32, inv);
			Stack("��3[      ���� ���      ]", 311,0,1,Arrays.asList("��f�ֹ��� ���� ���� ��","��f��� ���� �ݴϴ�.",""), 33, inv);
			Stack("��3[      ���� ��ȣ      ]", 310,0,1,Arrays.asList("��f�ֹ��� ���� ���� ��ȣ��","��f��� ���� �ݴϴ�.",""), 34, inv);
			Stack("��3[        ����        ]", 399,0,1,Arrays.asList("��f�ֹ��� ���� ������ ����������","��f��� ���� �ݴϴ�.",""), 40, inv);
			break;
		case "[��ų��]":
			if(itemYaml.getString(number+".Skill").equals("null"))
				Stack("��3[        ��ų        ]", 340,0,1,Arrays.asList("��f��ų �� ����","��f�Ʒ� ��ų�� �����մϴ�.","","��9[���� ��ϵ� ��ų]","��f      ����"), 25, inv);
			else
				Stack("��3[        ��ų        ]", 403,0,1,Arrays.asList("��f��ų �� ����","��f�Ʒ� ��ų�� �����մϴ�.","","��9[���� ��ϵ� ��ų]","��f"+itemYaml.getString(number+".Skill")), 25, inv);
			break;
		case "[�Һ�]":
			Stack("��3[       ������       ]", 364,0,1,Arrays.asList("��f������ ���� ��⸦","��f���� ���� �ݴϴ�.",""), 31, inv);
			Stack("��3[       �����       ]", 373,8261,1,Arrays.asList("��f������ ���� �������","��f��� ���� �ݴϴ�.",""), 32, inv);
			Stack("��3[        ����        ]", 373,8230,1,Arrays.asList("��f������ ���� ������","��f��� ���� �ݴϴ�.",""), 33, inv);
			Stack("��3[        ȯ��        ]", 399,0,1,Arrays.asList("��f������ ���� �÷��̾���","��f������ �ʱ�ȭ ���� �ݴϴ�.","","��c[���� �ý����� �������� ��츸 ��� �����մϴ�.]",""), 34, inv);
			break;
		case "[��]":
			Stack("��3[       �����       ]", 267,0,1,Arrays.asList("��f�� ������ "+Main_ServerOption.damage+"��","��f���� ���� �ݴϴ�.",""), 24, inv);
			Stack("��3[     ���� �����     ]", 403,0,1,Arrays.asList("��f�� ������ "+Main_ServerOption.magicDamage+"��","��f���� ���� �ݴϴ�.",""), 25, inv);
			Stack("��3[        ���        ]", 307,0,1,Arrays.asList("��f�� ������ ������","��f���� ���� �ݴϴ�.",""), 31, inv);
			Stack("��3[        ��ȣ        ]", 306,0,1,Arrays.asList("��f�� ������ ��ȣ��","��f���� ���� �ݴϴ�.",""), 32, inv);
			Stack("��3[      ���� ���      ]", 311,0,1,Arrays.asList("��f�� ������ ���� ��","��f���� ���� �ݴϴ�.",""), 33, inv);
			Stack("��3[      ���� ��ȣ      ]", 310,0,1,Arrays.asList("��f�� ������ ���� ��ȣ��","��f���� ���� �ݴϴ�.",""), 34, inv);
			Stack("��3[        ����        ]", 399,0,1,Arrays.asList("��f�� ������ ������ ����������","��f���� ���� �ݴϴ�.",""), 40, inv);
			Stack("��3[       ������       ]", 145,2,1,Arrays.asList("��f�� ������ �������� ��������","��f���� ���� �ݴϴ�.","","��c[�Ϲ� ������ �Ұ���]",""), 41, inv);
			//Stack("��3[        ����        ]", 145,0,1,Arrays.asList("��f�� ������ �ִ� ���� Ƚ����","��f���� ���� �ݴϴ�.",""), 42, inv);
			break;
		}
		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+Type), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+number), 53, inv);
		player.openInventory(inv);
	}
	
	public void SelectSkillGUI(Player player, short page, int ItemNumber)
	{
	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");

		String UniqueCode = "��0��0��3��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��� ���� ��ų ��� : " + (page+1));

		String[] skillList= skillYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < skillList.length;count++)
		{
			short JobLevel= (short) skillYaml.getConfigurationSection(skillList[count].toString()+".SkillRank").getKeys(false).size();
			if(count > skillList.length || loc >= 45) break;

		  	YamlLoader jobYaml = new YamlLoader();
			jobYaml.getConfig("Skill/JobList.yml");
			if(jobYaml.contains("Mabinogi.Added."+skillList[count])==true)
			{
				Stack2("��f��l" + skillList[count],  skillYaml.getInt(skillList[count].toString()+".ID"),skillYaml.getInt(skillList[count].toString()+".DATA"),skillYaml.getInt(skillList[count].toString()+".Amount"),Arrays.asList("��3�ִ� ��ų ���� : ��f"+JobLevel,"","��e[�� Ŭ���� ��ų ���]"), loc, inv);
				loc++;	
			}
		}
		
		if(skillList.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ItemNumber), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	public void UseableItemListGUIClick(InventoryClickEvent event)
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
			if(slot==45)//���� ���
				new OPbox_GUI().opBoxGuiMain(player, (byte) 2);
			else if(slot == 48)//���� ������
				UseableItemListGUI(player, page-1);
			else if(slot == 49)//�� ������
				ChooseUseableItemTypeGUI(player, ((page*45)+event.getSlot()));
			else if(slot == 50)//���� ������
				UseableItemListGUI(player, page+1);
			else
			{
				int number = ((page*45)+event.getSlot());
				if(event.isLeftClick() == true&&event.isShiftClick()==false)
				{
					player.sendMessage("��a[SYSTEM] : Ŭ���� �������� ���� �޾ҽ��ϴ�!");
					player.getInventory().addItem(event.getCurrentItem());
				}
				if(event.isLeftClick() == true&&event.isShiftClick()==true)
					NewUseableItemGUI(player, number);
				else if(event.isRightClick()==true&&event.isShiftClick()==true)
				{
				  	YamlLoader itemYaml = new YamlLoader();
					itemYaml.getConfig("Item/Consume.yml");
					short Acount =  (short) (itemYaml.getKeys().toArray().length-1);

					for(int counter = number;counter <Acount;counter++)
						itemYaml.set(counter+"", itemYaml.get((counter+1)+""));
					itemYaml.removeKey(Acount+"");
					itemYaml.saveConfig();
					UseableItemListGUI(player, page);
				}
			}
		}
	}
	
	public void ChooseUseableItemTypeGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)
				UseableItemListGUI(player, 0);
			else
			{
				String Type = "";
				String Lore = "";
				String DisplayName = "";
				short ID = 267;
				short Data = 0;
				
				if(slot == 2)
				{
					Type = "��f[��ȯ��]";
					Lore = "��f��ü�� �ս� ���� ������ ������%enter%��f������ �̵��� �� �ִ� �ź���%enter%��f��ȯ �ֹ����̴�.";
					DisplayName = "��f0�� ��ȯ �ֹ���";
					ID = 340;
				}
				else if(slot == 3)
				{
					Type = "��6[�ֹ���]";
					Lore = "��f���� ��ų ����Ʈ��%enter%��f5��ŭ ��½��� �ش�.";
					DisplayName ="��f��ų ����Ʈ 5 ���� �ֹ���";
					ID = 339;
				}
				else if(slot == 4)
				{
					Type = "��5[��ų��]";
					Lore = "��f���� �ƹ��͵� �������� ����%enter%��f�� ������ ��ų ���̴�.%enter% %enter%��f(�ƹ��͵� ���� �� ���� �� ����.)";
					DisplayName = "��f�� ��ų ��";
					ID = 403;
				}
				else if(slot == 5)
				{
					Type = "��d[�Һ�]";
					Lore = "��f�� ���Կ� ����, ���޽�%enter%��f����� ���, �������%enter%��f10 ġ���� �ִ� �����̴�.";
					DisplayName = "��f�û��� ����";
					ID = 373;
					Data = 8261;
				}
				else if(slot == 6)
				{
					Type = "��9[��]";
					Lore = "��f������ ����� ���̴�.%enter%��f������ �뷱���� �÷��ִ� �� �ϴ�.";
					DisplayName ="��f��� ��";
					ID = 351;
					Data = 10;
				}

			  	YamlLoader useableItemYaml = new YamlLoader();
				useableItemYaml.getConfig("Item/Consume.yml");
				
				int ItemCounter = useableItemYaml.getKeys().size();
				useableItemYaml.set(ItemCounter+".ShowType","��f[���]");
				useableItemYaml.set(ItemCounter+".ID",ID);
				useableItemYaml.set(ItemCounter+".Data",Data);
				useableItemYaml.set(ItemCounter+".DisplayName",DisplayName);
				useableItemYaml.set(ItemCounter+".Lore",Lore);
				useableItemYaml.set(ItemCounter+".Type",Type);
				useableItemYaml.set(ItemCounter+".Grade","��f[�Ϲ�]");
				
				if(slot == 2)
				{
					useableItemYaml.set(ItemCounter+".World",player.getLocation().getWorld().getName().toString());
					useableItemYaml.set(ItemCounter+".X",0);
					useableItemYaml.set(ItemCounter+".Y",0);
					useableItemYaml.set(ItemCounter+".Z",0);
					useableItemYaml.set(ItemCounter+".Pitch",0);
					useableItemYaml.set(ItemCounter+".Yaw",0);
				}
				else if(slot ==3)
				{
					useableItemYaml.set(ItemCounter+".DEF",0);
					useableItemYaml.set(ItemCounter+".Protect",0);
					useableItemYaml.set(ItemCounter+".MaDEF",0);
					useableItemYaml.set(ItemCounter+".MaProtect",0);
					useableItemYaml.set(ItemCounter+".STR",0);
					useableItemYaml.set(ItemCounter+".DEX",0);
					useableItemYaml.set(ItemCounter+".INT",0);
					useableItemYaml.set(ItemCounter+".WILL",0);
					useableItemYaml.set(ItemCounter+".LUK",0);
					useableItemYaml.set(ItemCounter+".Balance",0);
					useableItemYaml.set(ItemCounter+".Critical",0);
					useableItemYaml.set(ItemCounter+".HP",0);
					useableItemYaml.set(ItemCounter+".MP",0);
					useableItemYaml.set(ItemCounter+".SkillPoint",5);
					useableItemYaml.set(ItemCounter+".StatPoint",0);
				}
				else if(slot ==4)
					useableItemYaml.set(ItemCounter+".Skill","null");
				else if(slot ==5)
				{
					useableItemYaml.set(ItemCounter+".HP",10);
					useableItemYaml.set(ItemCounter+".MP",0);
					useableItemYaml.set(ItemCounter+".Saturation",0);
					useableItemYaml.set(ItemCounter+".Rebirth",false);
				}
				else if(slot ==6)
				{
					useableItemYaml.set(ItemCounter+".MinDamage",0);
					useableItemYaml.set(ItemCounter+".MaxDamage",0);
					useableItemYaml.set(ItemCounter+".MinMaDamage",0);
					useableItemYaml.set(ItemCounter+".MaxMaDamage",0);
					useableItemYaml.set(ItemCounter+".DEF",0);
					useableItemYaml.set(ItemCounter+".Protect",0);
					useableItemYaml.set(ItemCounter+".MaDEF",0);
					useableItemYaml.set(ItemCounter+".MaProtect",0);
					useableItemYaml.set(ItemCounter+".Durability",0);
					useableItemYaml.set(ItemCounter+".MaxDurability",0);
					useableItemYaml.set(ItemCounter+".STR",0);
					useableItemYaml.set(ItemCounter+".DEX",0);
					useableItemYaml.set(ItemCounter+".INT",0);
					useableItemYaml.set(ItemCounter+".WILL",0);
					useableItemYaml.set(ItemCounter+".LUK",0);
					useableItemYaml.set(ItemCounter+".Balance",10);
					useableItemYaml.set(ItemCounter+".Critical",0);
					useableItemYaml.set(ItemCounter+".HP",0);
					useableItemYaml.set(ItemCounter+".MP",0);
				}
				useableItemYaml.saveConfig();
				NewUseableItemGUI(player,ItemCounter);
			}
		}
	}

	public void NewUseableItemGUIclick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		String IconName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
		
		int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
		if(IconName.equals("[        ��ų        ]"))
		{
		  	YamlLoader configYaml = new YamlLoader();
			configYaml.getConfig("config.yml");
			
			if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System")==true)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				SelectSkillGUI(player, (short) 0, itemnumber);
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[��ų �� ����] : ���� ���� �ý����� ��e'������'��c�� �ƴմϴ�!");
			}
		}
		else if(IconName.equals("[    ��ġ ����    ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			useableItemYaml.set(itemnumber+".World", player.getLocation().getWorld().getName().toString());
			useableItemYaml.set(itemnumber+".X", player.getLocation().getX());
			useableItemYaml.set(itemnumber+".Y", player.getLocation().getY());
			useableItemYaml.set(itemnumber+".Z", player.getLocation().getZ());
			useableItemYaml.set(itemnumber+".Pitch", player.getLocation().getPitch());
			useableItemYaml.set(itemnumber+".Yaw", player.getLocation().getYaw());
			useableItemYaml.saveConfig();
			NewUseableItemGUI(player, itemnumber);
		}
		else if(IconName.equals("[        ȯ��        ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(useableItemYaml.getBoolean(itemnumber+".Rebirth") == false)
				useableItemYaml.set(itemnumber+".Rebirth", true);
			else
				useableItemYaml.set(itemnumber+".Rebirth", false);
			useableItemYaml.saveConfig();
			NewUseableItemGUI(player, itemnumber);
		}
		else if(IconName.equals("[    ���� ����    ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(useableItemYaml.getString(itemnumber+".ShowType").contains("[���]"))
				useableItemYaml.set(itemnumber+".ShowType","��e[�÷�]");
			else if(useableItemYaml.getString(itemnumber+".ShowType").contains("[�÷�]"))
				useableItemYaml.set(itemnumber+".ShowType","��d[��ȣ]");
			else if(useableItemYaml.getString(itemnumber+".ShowType").contains("[��ȣ]"))
				useableItemYaml.set(itemnumber+".ShowType","��9[�з�]");
			else if(useableItemYaml.getString(itemnumber+".ShowType").contains("[�з�]"))
				useableItemYaml.set(itemnumber+".ShowType","��f[���]");
			useableItemYaml.saveConfig();
			NewUseableItemGUI(player, itemnumber);
		}
		else if(IconName.equals("[    Ÿ�� ����    ]"))
			SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 0.8F, 1.8F);
		else if(IconName.equals("[    ��� ����    ]"))
		{
		  	YamlLoader useableItemYaml = new YamlLoader();
			useableItemYaml.getConfig("Item/Consume.yml");
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
			NewUseableItemGUI(player, itemnumber);
		}
		else if(IconName.equals("���� ���"))
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			UseableItemListGUI(player, 0);
		}
		else if(IconName.equals("�ݱ�"))
		{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
		}
		else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
		{
			UserData_Object u = new UserData_Object();
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			u.setType(player, "UseableItem");
			u.setInt(player, (byte)3, itemnumber);
			u.setInt(player, (byte)4, -1);
			player.closeInventory();
			if(IconName.equals("[       ������       ]"))
			{
				player.sendMessage("��3[������] : ȸ���� �������� �Է��� �ּ���!");
				u.setString(player, (byte)1, "Saturation");
			}
			else if(IconName.equals("[       �����       ]"))
			{
				player.sendMessage("��3[������] : ȸ���� ������� �Է��� �ּ���!");
				u.setString(player, (byte)1, "HP");
				u.setInt(player, (byte)4, -8);
			}
			else if(IconName.equals("[        ����        ]"))
			{
				player.sendMessage("��3[������] : ȸ���� ������ �Է��� �ּ���!");
				u.setString(player, (byte)1, "MP");
				u.setInt(player, (byte)4, -8);
			}
			else if(IconName.equals("[     ��ų ����Ʈ     ]"))
			{
				player.sendMessage("��3[������] : �ְ��� �ϴ� ��ų ����Ʈ�� ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "SkillPoint");
			}
			else if(IconName.equals("[     ���� ����Ʈ     ]"))
			{
				player.sendMessage("��3[������] : �ְ��� �ϴ� ���� ����Ʈ�� ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "StatPoint");
			}
			else if(IconName.equals("[        ID        ]"))
			{
				player.sendMessage("��3[������] : �������� ID ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "ID");
			}
			else if(IconName.equals("[       DATA       ]"))
			{
				player.sendMessage("��3[������] : �������� DATA ���� �Է��� �ּ���!");
				u.setString(player, (byte)1, "Data");
			}
			else if(IconName.equals("[       �����       ]"))
			{
				player.sendMessage("��3[������] : �������� �ּ� "+Main_ServerOption.damage+"�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MinDamage");
			}
			else if(IconName.equals("[     ���� �����     ]"))
			{
				player.sendMessage("��3[������] : �������� �ּ� "+Main_ServerOption.magicDamage+"�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MinMaDamage");
			}
			else if(IconName.equals("[        ���        ]"))
			{
				player.sendMessage("��3[������] : �������� ������ �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "DEF");
			}
			else if(IconName.equals("[        ��ȣ        ]"))
			{
				player.sendMessage("��3[������] : �������� ��ȣ�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "Protect");
			}
			else if(IconName.equals("[      ���� ���      ]"))
			{
				player.sendMessage("��3[������] : �������� ���� ������ �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MaDEF");
			}
			else if(IconName.equals("[      ���� ��ȣ      ]"))
			{
				player.sendMessage("��3[������] : �������� ���� ��ȣ�� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MaProtect");
			}
			else if(IconName.equals("[        ����        ]"))
			{
				player.sendMessage("��3[������] : �������� ����� ���ʽ��� �Է��� �ּ���!");
				player.sendMessage("��3(-127 ~ 127)");
				u.setString(player, (byte)1, "HP");
			}
			else if(IconName.equals("[       ������       ]"))
			{
				player.sendMessage("��3[������] : �������� �ִ� �������� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
				u.setString(player, (byte)1, "MaxDurability");
			}
			else
			{
				if(IconName.equals("[    �̸� ����    ]"))
				{
					player.sendMessage("��3[������] : �������� �̸��� �Է��� �ּ���!");
					u.setString(player, (byte)1, "DisplayName");
				}
				else if(IconName.equals("[    ���� ����    ]"))
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
	
	public void SelectSkillGUIClick(InventoryClickEvent event)
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
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				NewUseableItemGUI(player, itemnumber);
			else if(slot == 48)//���� ������
				SelectSkillGUI(player, (short) (page-1), itemnumber);
			else if(slot == 50)//���� ������
				SelectSkillGUI(player, (short) (page-1), itemnumber);
			else
			{
			  	YamlLoader useableItemYaml = new YamlLoader();
				useableItemYaml.getConfig("Item/Consume.yml");
				useableItemYaml.set(itemnumber+".Skill", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				useableItemYaml.saveConfig();
				NewUseableItemGUI(player, itemnumber);
			}
		}
	}
	
	
	public String[] LoreCreater(int ItemNumber)
	{
	  	YamlLoader useableItemYaml = new YamlLoader();
		useableItemYaml.getConfig("Item/Consume.yml");
		String lore = "";
		String Type = ChatColor.stripColor(useableItemYaml.getString(ItemNumber+".ShowType"));

		lore = lore+useableItemYaml.getString(ItemNumber+".Type");
		for(int count = 0; count<20-useableItemYaml.getString(ItemNumber+".Type").length();count++)
			lore = lore+" ";
		lore = lore+useableItemYaml.getString(ItemNumber+".Grade")+"%enter% %enter%";
		
		switch(Type)
		{
		case "[�з�]":
		{
			switch(ChatColor.stripColor(useableItemYaml.getString(ItemNumber+".Type")))
			{
			case "[��ȯ��]":
				lore = lore+"��9 �� ���� : ��f" + useableItemYaml.getString(ItemNumber+".World")+"%enter%";
				lore = lore+"��9 �� X ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".X")+"%enter%";
				lore = lore+"��9 �� Y ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Y")+"%enter%";
				lore = lore+"��9 �� Z ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Z")+"%enter%";
				break;
			case "[�ֹ���]":
				if(useableItemYaml.getInt(ItemNumber+".StatPoint")>0)
					lore = lore+"��3 + ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".StatPoint")<0)
					lore = lore+"��c - ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".SkillPoint")>0)
					lore = lore+"��3 + ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".SkillPoint")<0)
					lore = lore+"��c - ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				
					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
						lore = lore+"��c - "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
						lore = lore+"��c - "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
						lore = lore+"��c - "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
						lore = lore+"��c - "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
						lore = lore+"��c - "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				break;
			case "[��ų��]":
				if(useableItemYaml.getString(ItemNumber+".Skill").equals("null"))
					lore = lore+"%enter%��c [�ƹ��͵� ���� �� å]%enter%";
				else
					lore = lore+"%enter%"+ChatColor.DARK_AQUA  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��3 + ��f"+useableItemYaml.getString(ItemNumber+".Skill")+"%enter%";
				break;
			case "[�Һ�]":
				if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
					lore = lore+"��3 + ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
					lore = lore+"��c - ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
					lore = lore+"��3 + ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
					lore = lore+"��c - ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Saturation") > 0)
					lore = lore+"��3 + ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".Saturation") < 0)
					lore = lore+"��c - ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
				if(useableItemYaml.getBoolean(ItemNumber+".Rebirth") == true)
					lore = lore+"��6��l + ȯ��%enter%";
				break;
			case "[��]":
				if(useableItemYaml.getInt(ItemNumber+".MinDamage")>0)
					lore = lore+"��3 + �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MinDamage")<0)
					lore = lore+"��c - �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxDamage")>0)
					lore = lore+"��3 + �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxDamage")<0)
					lore = lore+"��c - �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")>0)
					lore = lore+"��3 + �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")<0)
					lore = lore+"��c - �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")>0)
					lore = lore+"��3 + �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")<0)
					lore = lore+"��c - �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";

				if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";

				if(useableItemYaml.getInt(ItemNumber+".Durability")>0)
					lore = lore+"��3 + ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".Durability")<0)
					lore = lore+"��c - ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxDurability")>0)
					lore = lore+"��3 + �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxDurability")<0)
					lore = lore+"��c - �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";


				if(useableItemYaml.getInt(ItemNumber+".HP")!=0||useableItemYaml.getInt(ItemNumber+".MP")!=0||
				useableItemYaml.getInt(ItemNumber+".STR")!=0||useableItemYaml.getInt(ItemNumber+".DEX")!=0||
				useableItemYaml.getInt(ItemNumber+".INT")!=0||useableItemYaml.getInt(ItemNumber+".WILL")!=0||
				useableItemYaml.getInt(ItemNumber+".LUK")!=0)
				{
					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
						lore = lore+"��c - "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
						lore = lore+"��c - "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
						lore = lore+"��c - "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
						lore = lore+"��c - "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
						lore = lore+"��c - "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
						lore = lore+"��5 + ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") < 0)
						lore = lore+"��c - ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
				}
				break;
			}

			lore = lore+"%enter%��6___--------------------___%enter%��6       [������ ����]";
			lore = lore+"%enter%"+ useableItemYaml.getString(ItemNumber+".Lore")+"%enter%";
			lore = lore+"��6---____________________---%enter%";
		}
		break;
		case "[��ȣ]":
		{
			switch(ChatColor.stripColor(useableItemYaml.getString(ItemNumber+".Type")))
			{
			case "[��ȯ��]":
				lore = lore+"��9 �� ���� : ��f" + useableItemYaml.getString(ItemNumber+".World")+"%enter%";
				lore = lore+"��9 �� X ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".X")+"%enter%";
				lore = lore+"��9 �� Y ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Y")+"%enter%";
				lore = lore+"��9 �� Z ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Z")+"%enter%";
				break;
			case "[�ֹ���]":
				if(useableItemYaml.getInt(ItemNumber+".StatPoint")>0)
					lore = lore+"��3 + ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".StatPoint")<0)
					lore = lore+"��c - ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".SkillPoint")>0)
					lore = lore+"��3 + ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".SkillPoint")<0)
					lore = lore+"��c - ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				
					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
						lore = lore+"��c - "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
						lore = lore+"��c - "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
						lore = lore+"��c - "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
						lore = lore+"��c - "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
						lore = lore+"��c - "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				break;
			case "[��ų��]":
				if(useableItemYaml.getString(ItemNumber+".Skill").equals("null"))
					lore = lore+"%enter%��c [�ƹ��͵� ���� �� å]%enter%";
				else
					lore = lore+"%enter%"+ChatColor.DARK_AQUA  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��3 + ��f"+useableItemYaml.getString(ItemNumber+".Skill")+"%enter%";
				break;
			case "[�Һ�]":
				if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
					lore = lore+"��3 + ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
					lore = lore+"��c - ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
					lore = lore+"��3 + ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
					lore = lore+"��c - ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Saturation") > 0)
					lore = lore+"��3 + ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".Saturation") < 0)
					lore = lore+"��c - ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
				if(useableItemYaml.getBoolean(ItemNumber+".Rebirth") == true)
					lore = lore+"��6��l + ȯ��%enter%";
				break;
			case "[��]":
				if(useableItemYaml.getInt(ItemNumber+".MinDamage")>0)
					lore = lore+"��3 + �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MinDamage")<0)
					lore = lore+"��c - �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxDamage")>0)
					lore = lore+"��3 + �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxDamage")<0)
					lore = lore+"��c - �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")>0)
					lore = lore+"��3 + �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")<0)
					lore = lore+"��c - �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")>0)
					lore = lore+"��3 + �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")<0)
					lore = lore+"��c - �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";

				if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
					lore = lore+"��3 + ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
					lore = lore+"��c - ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
					lore = lore+"��3 + ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
					lore = lore+"��c - ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
					lore = lore+"��3 + ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
					lore = lore+"��c - ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
					lore = lore+"��3 + ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
					lore = lore+"��c - ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
					lore = lore+"��3 + �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
					lore = lore+"��c - �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
					lore = lore+"��3 + ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
					lore = lore+"��c - ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Durability")>0)
					lore = lore+"��3 + ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".Durability")<0)
					lore = lore+"��c - ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxDurability")>0)
					lore = lore+"��3 + �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxDurability")<0)
					lore = lore+"��c - �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";

				if(useableItemYaml.getInt(ItemNumber+".HP")!=0||useableItemYaml.getInt(ItemNumber+".MP")!=0||
				useableItemYaml.getInt(ItemNumber+".STR")!=0||useableItemYaml.getInt(ItemNumber+".DEX")!=0||
				useableItemYaml.getInt(ItemNumber+".INT")!=0||useableItemYaml.getInt(ItemNumber+".WILL")!=0||
				useableItemYaml.getInt(ItemNumber+".LUK")!=0)
				{
					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 + ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c - ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 + ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c - ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
						lore = lore+"��c - "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
						lore = lore+"��c - "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
						lore = lore+"��c - "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
						lore = lore+"��c - "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
						lore = lore+"��3 + "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
						lore = lore+"��c - "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
						lore = lore+"��5 + ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") < 0)
						lore = lore+"��c - ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
				}
				break;
			}			
			
			lore = lore+"%enter%"+ useableItemYaml.getString(ItemNumber+".Lore")+"%enter%%enter%";

		}
		break;
		case "[�÷�]":
		{
			switch(ChatColor.stripColor(useableItemYaml.getString(ItemNumber+".Type")))
			{
			case "[��ȯ��]":
				lore = lore+"��9 ���� : ��f" + useableItemYaml.getString(ItemNumber+".World")+"%enter%";
				lore = lore+"��9 X ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".X")+"%enter%";
				lore = lore+"��9 Y ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Y")+"%enter%";
				lore = lore+"��9 Z ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Z")+"%enter%";
				break;
			case "[�ֹ���]":
				if(useableItemYaml.getInt(ItemNumber+".StatPoint")>0)
					lore = lore+"��3 ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".StatPoint")<0)
					lore = lore+"��c ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".SkillPoint")>0)
					lore = lore+"��3 ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".SkillPoint")<0)
					lore = lore+"��c ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
					lore = lore+"��3 ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
					lore = lore+"��c ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
					lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
					lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
					lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
					lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
					lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
					lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
					lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
					lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
					lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
					lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				
					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
						lore = lore+"��3 "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
						lore = lore+"��c "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
						lore = lore+"��3 "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
						lore = lore+"��c "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
						lore = lore+"��3 "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
						lore = lore+"��c "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
						lore = lore+"��3 "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
						lore = lore+"��c "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
						lore = lore+"��3 "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
						lore = lore+"��c "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
				break;
			case "[��ų��]":
				if(useableItemYaml.getString(ItemNumber+".Skill").equals("null"))
					lore = lore+"%enter%��c [�ƹ��͵� ���� �� å]%enter%";
				else
					lore = lore+"%enter%"+ChatColor.DARK_AQUA  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��3 + ��f"+useableItemYaml.getString(ItemNumber+".Skill")+"%enter%";
				break;
			case "[�Һ�]":
				if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
					lore = lore+"��3 ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
					lore = lore+"��c ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
					lore = lore+"��3 ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
					lore = lore+"��c ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Saturation") > 0)
					lore = lore+"��3 ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".Saturation") < 0)
					lore = lore+"��c ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
				if(useableItemYaml.getBoolean(ItemNumber+".Rebirth") == true)
					lore = lore+"��6��l + ȯ��%enter%";
				break;
			case "[��]":
				if(useableItemYaml.getInt(ItemNumber+".MinDamage")>0)
					lore = lore+"��3 �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MinDamage")<0)
					lore = lore+"��c �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxDamage")>0)
					lore = lore+"��3 �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxDamage")<0)
					lore = lore+"��c �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")>0)
					lore = lore+"��3 �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")<0)
					lore = lore+"��c �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")>0)
					lore = lore+"��3 �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")<0)
					lore = lore+"��c �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";

				if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
					lore = lore+"��3 ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
					lore = lore+"��c ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
					lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
					lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
					lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
					lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
					lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
					lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
					lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
					lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
					lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
					lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";

				if(useableItemYaml.getInt(ItemNumber+".Durability")>0)
					lore = lore+"��3 ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".Durability")<0)
					lore = lore+"��c ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
				if(useableItemYaml.getInt(ItemNumber+".MaxDurability")>0)
					lore = lore+"��3 �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
				else if(useableItemYaml.getInt(ItemNumber+".MaxDurability")<0)
					lore = lore+"��c �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";


					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
						lore = lore+"��3 "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
						lore = lore+"��c "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
						lore = lore+"��3 "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
						lore = lore+"��c "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
						lore = lore+"��3 "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
						lore = lore+"��c "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
						lore = lore+"��3 "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
						lore = lore+"��c "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
						lore = lore+"��3 "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
						lore = lore+"��c "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
						lore = lore+"��5 ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") < 0)
						lore = lore+"��c ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
				break;
			}
			
			lore = lore+"%enter%"+ useableItemYaml.getString(ItemNumber+".Lore")+"%enter%%enter%";


		}
		break;
		
			case "[���]":
			{
				switch(ChatColor.stripColor(useableItemYaml.getString(ItemNumber+".Type")))
				{
				case "[��ȯ��]":
					lore = lore+"��f ���� : ��f" + useableItemYaml.getString(ItemNumber+".World")+"%enter%";
					lore = lore+"��f X ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".X")+"%enter%";
					lore = lore+"��f Y ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Y")+"%enter%";
					lore = lore+"��f Z ��ǥ : ��f" + useableItemYaml.getInt(ItemNumber+".Z")+"%enter%";
					break;
				case "[�ֹ���]":
					if(useableItemYaml.getInt(ItemNumber+".StatPoint")>0)
						lore = lore+"��3 ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".StatPoint")<0)
						lore = lore+"��c ���� ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".StatPoint")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".SkillPoint")>0)
						lore = lore+"��3 ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".SkillPoint")<0)
						lore = lore+"��c ��ų ����Ʈ : " + useableItemYaml.getInt(ItemNumber+".SkillPoint")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
						lore = lore+"��3 ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
						lore = lore+"��c ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
						lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
						lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
						lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
						lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
						lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
						lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
						lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
						lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
						lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
						lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
					
						if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
							lore = lore+"��3 ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
							lore = lore+"��c ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
							lore = lore+"��3 ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
							lore = lore+"��c ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
							lore = lore+"��3 "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
							lore = lore+"��c "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
							lore = lore+"��3 "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
							lore = lore+"��c "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
							lore = lore+"��3 "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
							lore = lore+"��c "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
							lore = lore+"��3 "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
							lore = lore+"��c "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
							lore = lore+"��3 "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
							lore = lore+"��c "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
					break;
				case "[��ų��]":
					if(useableItemYaml.getString(ItemNumber+".Skill").equals("null"))
						lore = lore+"%enter%��f [�ƹ��͵� ���� �� å]%enter%";
					else
						lore = lore+"%enter%��f"  +" [�� Ŭ���� �Ʒ� ��ų ȹ��]%enter%��f + ��f"+useableItemYaml.getString(ItemNumber+".Skill")+"%enter%";
					break;
				case "[�Һ�]":
					if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
						lore = lore+"��3 ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
						lore = lore+"��c ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
						lore = lore+"��3 ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
						lore = lore+"��c ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Saturation") > 0)
						lore = lore+"��3 ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".Saturation") < 0)
						lore = lore+"��c ������ : " + useableItemYaml.getInt(ItemNumber+".Saturation")+"%enter%";
					if(useableItemYaml.getBoolean(ItemNumber+".Rebirth") == true)
						lore = lore+"��6��l + ȯ��%enter%";
					break;
				case "[��]":
					if(useableItemYaml.getInt(ItemNumber+".MinDamage")>0)
						lore = lore+"��3 �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MinDamage")<0)
						lore = lore+"��c �ּ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinDamage")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxDamage")>0)
						lore = lore+"��3 �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MaxDamage")<0)
						lore = lore+"��c �ִ� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxDamage")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")>0)
						lore = lore+"��3 �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MinMaDamage")<0)
						lore = lore+"��c �ּ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MinMaDamage")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")>0)
						lore = lore+"��3 �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MaxMaDamage")<0)
						lore = lore+"��c �ִ� ���� ���ݷ� : " + useableItemYaml.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
					
					
						
					if(useableItemYaml.getInt(ItemNumber+".DEF")>0)
						lore = lore+"��3 ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".DEF")<0)
						lore = lore+"��c ��� : " + useableItemYaml.getInt(ItemNumber+".DEF")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Protect")>0)
						lore = lore+"��3 ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Protect")<0)
						lore = lore+"��c ��ȣ : " + useableItemYaml.getInt(ItemNumber+".Protect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaDEF")>0)
						lore = lore+"��3 ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaDEF")<0)
						lore = lore+"��c ���� ��� : " + useableItemYaml.getInt(ItemNumber+".MaDEF")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaProtect")>0)
						lore = lore+"��3 ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaProtect")<0)
						lore = lore+"��c ���� ��ȣ : " + useableItemYaml.getInt(ItemNumber+".MaProtect")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Balance")>0)
						lore = lore+"��3 �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Balance")<0)
						lore = lore+"��c �뷱�� : " + useableItemYaml.getInt(ItemNumber+".Balance")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Critical")>0)
						lore = lore+"��3 ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Critical")<0)
						lore = lore+"��c ũ��Ƽ�� : " + useableItemYaml.getInt(ItemNumber+".Critical")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".Durability")>0)
						lore = lore+"��3 ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".Durability")<0)
						lore = lore+"��c ���� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".Durability")+"%enter%";
					if(useableItemYaml.getInt(ItemNumber+".MaxDurability")>0)
						lore = lore+"��3 �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
					else if(useableItemYaml.getInt(ItemNumber+".MaxDurability")<0)
						lore = lore+"��c �ִ� ������ ���� : " + useableItemYaml.getInt(ItemNumber+".MaxDurability")+"%enter%";
					
						if(useableItemYaml.getInt(ItemNumber+".HP") > 0)
							lore = lore+"��3 ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".HP") < 0)
							lore = lore+"��c ����� : " + useableItemYaml.getInt(ItemNumber+".HP")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".MP") > 0)
							lore = lore+"��3 ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".MP") < 0)
							lore = lore+"��c ���� : " + useableItemYaml.getInt(ItemNumber+".MP")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".STR") > 0)
							lore = lore+"��3 "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".STR") < 0)
							lore = lore+"��c "+Main_ServerOption.statSTR+" : " + useableItemYaml.getInt(ItemNumber+".STR")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".DEX") > 0)
							lore = lore+"��3 "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".DEX") < 0)
							lore = lore+"��c "+Main_ServerOption.statDEX+" : " + useableItemYaml.getInt(ItemNumber+".DEX")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".INT") > 0)
							lore = lore+"��3 "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".INT") < 0)
							lore = lore+"��c "+Main_ServerOption.statINT+" : " + useableItemYaml.getInt(ItemNumber+".INT")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".WILL") > 0)
							lore = lore+"��3 "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".WILL") < 0)
							lore = lore+"��c "+Main_ServerOption.statWILL+" : " + useableItemYaml.getInt(ItemNumber+".WILL")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".LUK") > 0)
							lore = lore+"��3 "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
						else if(useableItemYaml.getInt(ItemNumber+".LUK") < 0)
							lore = lore+"��c "+Main_ServerOption.statLUK+" : " + useableItemYaml.getInt(ItemNumber+".LUK")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") > 0)
							lore = lore+"��5 ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
						if(useableItemYaml.getInt(ItemNumber+".MaxUpgrade") < 0)
							lore = lore+"��3 ���� : " +useableItemYaml.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					break;
				}
				
				lore = lore+"%enter%"+ useableItemYaml.getString(ItemNumber+".Lore")+"%enter%%enter%";

			}
			break;
		}

		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  scriptA[counter];
		return scriptA;
	}
	
}
