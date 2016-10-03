package GBD_RPG.CustomItem;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import GBD_RPG.Admin.OPbox_GUI;
import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_GUI;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;
import net.minecraft.server.v1_10_R1.NBTTagByte;
import net.minecraft.server.v1_10_R1.NBTTagCompound;

public class CustomItem_GUI extends Util_GUI
{
	public void ItemListGUI(Player player, int page)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager ItemList = YC.getNewConfig("Item/ItemList.yml");

		String UniqueCode = "��0��0��3��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ��� : " + (page+1));

		Object[] a= ItemList.getKeys().toArray();

		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			int number = ((page*45)+loc);
			if(count > a.length || loc >= 45) break;
			String ItemName = ItemList.getString(number+".DisplayName");
			int ItemID = ItemList.getInt(number+".ID");
			int ItemData = ItemList.getInt(number+".Data");
			Stack2(ItemName, ItemID, ItemData, 1,Arrays.asList(LoreCreater(number)), loc, inv);
			
			loc++;
		}
		
		if(a.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�� ������", 145,0,1,Arrays.asList(ChatColor.GRAY + "���ο� �������� �����մϴ�."), 49, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void NewItemGUI(Player player, int number)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager ItemList = YC.getNewConfig("Item/ItemList.yml");

		String UniqueCode = "��0��0��3��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ �� ����");
		String ItemName = ItemList.getString(number+".DisplayName");
		short ItemID = (short) ItemList.getInt(number+".ID");
		byte ItemData = (byte) ItemList.getInt(number+".Data");

		String Type = "";
		String Grade = "";
		for(int counter =0;counter < 13 - ItemList.getString(number+".Type").length();counter++ )
			Type = Type +" ";
		Type = Type +ItemList.getString(number+".Type");
		Grade = ItemList.getString(number+".Grade");
		
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 9, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 10, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 11, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 18, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 20, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 27, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 28, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �����    ]", 145,0,1,null, 29, inv);
		Stack2(ItemName, ItemID,ItemData,1,Arrays.asList(LoreCreater(number)), 19, inv);
		
		Stack2(ChatColor.DARK_AQUA + "[    ���� ����    ]", 421,0,1,Arrays.asList(ChatColor.WHITE+"������ ����â��",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� ����    ]","       "+ ItemList.getString(number+".ShowType"),""), 37, inv);
		Stack2(ChatColor.DARK_AQUA + "[    �̸� ����    ]", 421,0,1,Arrays.asList(ChatColor.WHITE+"�������� �̸���",ChatColor.WHITE+"�����մϴ�.",""), 13, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ���� ����    ]", 386,0,1,Arrays.asList(ChatColor.WHITE+"�������� ������",ChatColor.WHITE+"�����մϴ�.",""), 14, inv);
		Stack2(ChatColor.DARK_AQUA + "[    Ÿ�� ����    ]", 61,0,1,Arrays.asList(ChatColor.WHITE+"�������� Ÿ����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� Ÿ��    ]",Type,""), 15, inv);
		Stack2(ChatColor.DARK_AQUA + "[    ��� ����    ]", 266,0,1,Arrays.asList(ChatColor.WHITE+"�������� �����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� ���    ]","       "+Grade,""), 16, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ID        ]", 405,0,1,Arrays.asList(ChatColor.WHITE+"�������� ID����",ChatColor.WHITE+"�����մϴ�.",""), 22, inv);
		Stack2(ChatColor.DARK_AQUA + "[       DATA       ]", 336,0,1,Arrays.asList(ChatColor.WHITE+"�������� DATA����",ChatColor.WHITE+"�����մϴ�.",""), 23, inv);
		Stack2(ChatColor.DARK_AQUA + "[       "+GBD_RPG.Main_Main.Main_ServerOption.Damage+"       ]", 267,0,1,Arrays.asList(ChatColor.WHITE+"�������� "+GBD_RPG.Main_Main.Main_ServerOption.Damage+"��",ChatColor.WHITE+"�����մϴ�.",""), 24, inv);
		Stack2(ChatColor.DARK_AQUA + "[     "+GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+"     ]", 403,0,1,Arrays.asList(ChatColor.WHITE+"�������� "+GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+"��",ChatColor.WHITE+"�����մϴ�.",""), 25, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ���        ]", 307,0,1,Arrays.asList(ChatColor.WHITE+"�������� ������",ChatColor.WHITE+"�����մϴ�.",""), 31, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ��ȣ        ]", 306,0,1,Arrays.asList(ChatColor.WHITE+"�������� ��ȣ��",ChatColor.WHITE+"�����մϴ�.",""), 32, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ���      ]", 311,0,1,Arrays.asList(ChatColor.WHITE+"�������� ���� ��",ChatColor.WHITE+"�����մϴ�.",""), 33, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ��ȣ      ]", 310,0,1,Arrays.asList(ChatColor.WHITE+"�������� ���� ��ȣ��",ChatColor.WHITE+"�����մϴ�.",""), 34, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ����        ]", 399,0,1,Arrays.asList(ChatColor.WHITE+"�������� ���ʽ� ������",ChatColor.WHITE+"�����մϴ�.",""), 40, inv);
		Stack2(ChatColor.DARK_AQUA + "[       ������       ]", 145,2,1,Arrays.asList(ChatColor.WHITE+"�������� ��������",ChatColor.WHITE+"�����մϴ�.","",ChatColor.RED+"[0 ������ �Ϲ� ������ ������ ���]",""), 41, inv);
		Stack2(ChatColor.DARK_AQUA + "[        ����        ]", 145,0,1,Arrays.asList(ChatColor.WHITE+"�������� �ִ� ���� Ƚ����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.RED+"[0 ������ ���� �Ұ���]",""), 42, inv);
		Stack2(ChatColor.DARK_AQUA + "[         ��         ]", 381,0,1,Arrays.asList(ChatColor.WHITE+"�������� �ִ� ������",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"�ִ� "+ItemList.getInt(number+".MaxSocket")+" ��","",ChatColor.RED+"[0 ������ �� ���� �Ұ���]",""), 43, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ����      ]", 166,0,1,Arrays.asList(ChatColor.WHITE+"������ ������ ������",ChatColor.WHITE+"�ɾ�Ӵϴ�.",""), 49, inv);
		Stack2(ChatColor.DARK_AQUA + "[      ���� ����      ]", 397,3,1,Arrays.asList(ChatColor.WHITE+"������ ������ ������",ChatColor.WHITE+"�ɾ�Ӵϴ�.",ChatColor.RED+"[�� Ŭ���� ����]",""), 50, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+number), 53, inv);
		player.openInventory(inv);
	}
	
	public void JobListGUI(Player player, short page, int number)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager JobList  = YC.getNewConfig("Skill/JobList.yml");
		YamlManager Config = YC.getNewConfig("config.yml");

		String UniqueCode = "��0��0��3��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ���� ���� : " + (page+1));

		Object[] a = JobList.getConfigurationSection("MapleStory").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String JobName = a[count].toString();
			Set<String> JobLevel= JobList.getConfigurationSection("MapleStory."+JobName).getKeys(false);
			
			if(count > a.length || loc >= 45) break;
			
			if(JobName.equalsIgnoreCase(Config.getString("Server.DefaultJob")))
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + JobName, 403,0,1,Arrays.asList(ChatColor.DARK_AQUA+"�ִ� �±� : " + ChatColor.WHITE+JobLevel.size()+ChatColor.DARK_AQUA+"�� �±�","",ChatColor.YELLOW+"[��Ŭ���� ���� ����]",ChatColor.YELLOW+""+ChatColor.BOLD+"[���� �⺻ ����]"), loc, inv);
			else
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + JobName, 340,0,1,Arrays.asList(ChatColor.DARK_AQUA+"�ִ� �±� : " + ChatColor.WHITE+JobLevel.size()+ChatColor.DARK_AQUA+"�� �±�","",ChatColor.YELLOW+"[��Ŭ���� ���� ����]"), loc, inv);
			
			loc++;
		}
		
		if(a.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+number), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	public void ItemListInventoryclick(InventoryClickEvent event)
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
			short page = (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player,(byte) 1);
			else if(slot == 48)//���� ������
				ItemListGUI(player,page-1);
			else if(slot == 50)//���� ������
				ItemListGUI(player,page+1);
			else
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager ItemList = YC.getNewConfig("Item/ItemList.yml");
				if(slot == 49)//�� ������
				{
					int ItemCounter = ItemList.getConfigurationSection("").getKeys(false).size();
					ItemList.set(ItemCounter+".ShowType",ChatColor.WHITE+"[���]");
					ItemList.set(ItemCounter+".ID",267);
					ItemList.set(ItemCounter+".Data",0);
					ItemList.set(ItemCounter+".DisplayName",ChatColor.WHITE+"��� ���� ������ ��");
					ItemList.set(ItemCounter+".Lore",ChatColor.WHITE+"��� ������� �����̴�.%enter%"+ChatColor.WHITE+"������ ����������...");
					ItemList.set(ItemCounter+".Type",ChatColor.RED+"[���� ����]");
					ItemList.set(ItemCounter+".Grade",ChatColor.WHITE+"[�Ϲ�]");
					ItemList.set(ItemCounter+".MinDamage",1);
					ItemList.set(ItemCounter+".MaxDamage",7);
					ItemList.set(ItemCounter+".MinMaDamage",0);
					ItemList.set(ItemCounter+".MaxMaDamage",0);
					ItemList.set(ItemCounter+".DEF",0);
					ItemList.set(ItemCounter+".Protect",0);
					ItemList.set(ItemCounter+".MaDEF",0);
					ItemList.set(ItemCounter+".MaProtect",0);
					ItemList.set(ItemCounter+".Durability",256);
					ItemList.set(ItemCounter+".MaxDurability",256);
					ItemList.set(ItemCounter+".STR",0);
					ItemList.set(ItemCounter+".DEX",0);
					ItemList.set(ItemCounter+".INT",0);
					ItemList.set(ItemCounter+".WILL",0);
					ItemList.set(ItemCounter+".LUK",0);
					ItemList.set(ItemCounter+".Balance",10);
					ItemList.set(ItemCounter+".Critical",5);
					ItemList.set(ItemCounter+".MaxUpgrade",5);
					ItemList.set(ItemCounter+".MaxSocket",3);
					ItemList.set(ItemCounter+".HP",0);
					ItemList.set(ItemCounter+".MP",0);
					ItemList.set(ItemCounter+".JOB","����");
					ItemList.set(ItemCounter+".MinLV",0);
					ItemList.set(ItemCounter+".MinRLV",0);
					ItemList.set(ItemCounter+".MinSTR",0);
					ItemList.set(ItemCounter+".MinDEX",0);
					ItemList.set(ItemCounter+".MinINT",0);
					ItemList.set(ItemCounter+".MinWILL",0);
					ItemList.set(ItemCounter+".MinLUK",0);
					ItemList.saveConfig();
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
						short Acount =  (short) (ItemList.getConfigurationSection("").getKeys(false).toArray().length-1);

						for(short counter = (short) number;counter <Acount;counter++)
							ItemList.set(counter+"", ItemList.get((counter+1)+""));
						ItemList.removeKey(Acount+"");
						ItemList.saveConfig();
						ItemListGUI(player, page);
					}
				}
			}
		}
	}
	
	public void NewItemGUIclick(InventoryClickEvent event)
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
			if(slot == 45)//���� ȭ��
				ItemListGUI(player, 0);
			else if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
			{
				int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager ItemList = YC.getNewConfig("Item/ItemList.yml");
				if(ItemList.getString(itemnumber+"")==null)
				{
					player.sendMessage(ChatColor.RED+"[SYSTEM] : �ٸ� OP�� �������� �����Ͽ� �ݿ����� �ʾҽ��ϴ�!");
					s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
					player.closeInventory();
					return;
				}
				if(slot==37)//�� Ÿ��
				{
					if(ItemList.getString(itemnumber+".ShowType").contains("[���]"))
						ItemList.set(itemnumber+".ShowType",ChatColor.YELLOW+"[�÷�]");
					else if(ItemList.getString(itemnumber+".ShowType").contains("[�÷�]"))
						ItemList.set(itemnumber+".ShowType",ChatColor.LIGHT_PURPLE+"[��ȣ]");
					else if(ItemList.getString(itemnumber+".ShowType").contains("[��ȣ]"))
						ItemList.set(itemnumber+".ShowType",ChatColor.BLUE+"[�з�]");
					else if(ItemList.getString(itemnumber+".ShowType").contains("[�з�]"))
						ItemList.set(itemnumber+".ShowType",ChatColor.WHITE+"[���]");
					ItemList.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==15)//Ÿ�� ����
				{
				  	YamlManager Target = YC.getNewConfig("Item/CustomType.yml");
				  	Object[] Type = Target.getKeys().toArray();
					s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				  	if(Type.length == 0)
				  	{
						if(ItemList.getString(itemnumber+".Type").contains("[���� ����]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[�Ѽ� ��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[�Ѽ� ��]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[��� ��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��� ��]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[����]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[���Ÿ� ����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[���Ÿ� ����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[Ȱ]");
						else if(ItemList.getString(itemnumber+".Type").contains("[Ȱ]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[���� ����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[���� ����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[������]");
						else if(ItemList.getString(itemnumber+".Type").contains("[������]"))
							ItemList.set(itemnumber+".Type",ChatColor.WHITE+"[��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��]"))
							ItemList.set(itemnumber+".Type",ChatColor.GRAY+"[��Ÿ]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��Ÿ]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[���� ����]");
				  	}
				  	else
				  	{
						if(ItemList.getString(itemnumber+".Type").contains("[���� ����]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[�Ѽ� ��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[�Ѽ� ��]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[��� ��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��� ��]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[����]"))
							ItemList.set(itemnumber+".Type",ChatColor.RED+"[��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[���Ÿ� ����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[���Ÿ� ����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[Ȱ]");
						else if(ItemList.getString(itemnumber+".Type").contains("[Ȱ]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_GREEN+"[����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[���� ����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[���� ����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[����]");
						else if(ItemList.getString(itemnumber+".Type").contains("[����]"))
							ItemList.set(itemnumber+".Type",ChatColor.DARK_AQUA+"[������]");
						else if(ItemList.getString(itemnumber+".Type").contains("[������]"))
							ItemList.set(itemnumber+".Type",ChatColor.WHITE+"[��]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��]"))
							ItemList.set(itemnumber+".Type",ChatColor.GRAY+"[��Ÿ]");
						else if(ItemList.getString(itemnumber+".Type").contains("[��Ÿ]"))
							ItemList.set(itemnumber+".Type",ChatColor.WHITE+Type[0].toString());
						else
						{
							for(short count = 0; count < Type.length; count++)
							{
								if((ItemList.getString(itemnumber+".Type").contains(Type[count].toString())))
								{
									if(count+1 == Type.length)
										ItemList.set(itemnumber+".Type",ChatColor.RED+"[���� ����]");
									else
										ItemList.set(itemnumber+".Type",ChatColor.WHITE+Type[(count+1)].toString());
									break;
								}
							}
						}
				  	}
					ItemList.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==16)//��� ����
				{
					if(ItemList.getString(itemnumber+".Grade").contains("[�Ϲ�]"))
						ItemList.set(itemnumber+".Grade",ChatColor.GREEN+"[���]");
					else if(ItemList.getString(itemnumber+".Grade").contains("[���]"))
						ItemList.set(itemnumber+".Grade",ChatColor.BLUE+"[����]");
					else if(ItemList.getString(itemnumber+".Grade").contains("[����]"))
						ItemList.set(itemnumber+".Grade",ChatColor.YELLOW+"[����]");
					else if(ItemList.getString(itemnumber+".Grade").contains("[����]"))
						ItemList.set(itemnumber+".Grade",ChatColor.DARK_PURPLE+"[����]");
					else if(ItemList.getString(itemnumber+".Grade").contains("[����]"))
						ItemList.set(itemnumber+".Grade",ChatColor.GOLD+"[����]");
					else if(ItemList.getString(itemnumber+".Grade").contains("[����]"))
						ItemList.set(itemnumber+".Grade",ChatColor.DARK_RED+""+ChatColor.BOLD+"["+ChatColor.GOLD+""+ChatColor.BOLD+"��"+ChatColor.DARK_GREEN+""+ChatColor.BOLD+"��"+ChatColor.DARK_BLUE+""+ChatColor.BOLD+"]");
					else if(ItemList.getString(itemnumber+".Grade").contains("��"))
						ItemList.set(itemnumber+".Grade",ChatColor.GRAY+"[�ϱ�]");
					else if(ItemList.getString(itemnumber+".Grade").contains("[�ϱ�]"))
						ItemList.set(itemnumber+".Grade",ChatColor.WHITE+"[�Ϲ�]");
					ItemList.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==43)//�ִ� ���� ����
				{
					if(ItemList.getInt(itemnumber+".MaxSocket") < 5)
						ItemList.set(itemnumber+".MaxSocket",ItemList.getInt(itemnumber+".MaxSocket")+1);
					else if(ItemList.getInt(itemnumber+".MaxSocket") == 5)
							ItemList.set(itemnumber+".MaxSocket", 0);
					ItemList.saveConfig();
					NewItemGUI(player, itemnumber);
				}
				else if(slot==50)//���� ����
				{
					if(event.isLeftClick())
						JobListGUI(player, (short)0, itemnumber);
					else if(event.isRightClick())
					{
						s.SP(player, Sound.ITEM_SHIELD_BREAK, 0.8F, 1.0F);
						ItemList.set(itemnumber+".JOB", "����");
						ItemList.saveConfig();
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
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.Damage+"�� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
						u.setString(player, (byte)1, "MinDamage");
					}
					else if(slot == 25)//���� ����� ����
					{
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+"�� �Է��� �ּ���!");
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
			int itemnumber = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			if(slot == 45)//���� ���
				NewItemGUI(player, itemnumber);
			else
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager ItemList = YC.getNewConfig("Item/ItemList.yml");
				ItemList.set(itemnumber+".JOB", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				ItemList.saveConfig();
				s.SP(player, Sound.ITEM_SHIELD_BREAK, 0.8F, 1.0F);
				NewItemGUI(player, itemnumber);
			}
		}
	}
	

	public String[] LoreCreater(int ItemNumber)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager ItemList = YC.getNewConfig("Item/ItemList.yml");
		String lore = "";
		String Type = ChatColor.stripColor(ItemList.getString(ItemNumber+".ShowType"));
		if(Type.compareTo("[�з�]")==0)
		{
			lore = lore+ItemList.getString(ItemNumber+".Type");
			for(int count = 0; count<20-ItemList.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(ItemList.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.GOLD+"��� ���� ���� : " +ChatColor.WHITE + ItemList.getString(ItemNumber+".JOB")+"%enter%%enter%";
				

			if(ItemList.getInt(ItemNumber+".MinDamage") != 0||ItemList.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.Damage+" : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MinDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MinMaDamage") != 0||ItemList.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+" : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MinMaDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.GRAY + " �� ��� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".DEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + " �� ��ȣ : " + ChatColor.WHITE +ItemList.getInt(ItemNumber+".Protect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.BLUE + " �� ���� ��� : " + ChatColor.WHITE +ItemList.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.DARK_BLUE + " �� ���� ��ȣ : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.DARK_GREEN + " �� �뷱�� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Balance")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.YELLOW + " �� ũ��Ƽ�� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Critical")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.GOLD + " �� ������ : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Durability")+" / "+ ItemList.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+" �� ���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+ChatColor.GOLD+"___--------------------___%enter%"+ChatColor.GOLD+"       [������ ����]";
			lore = lore+"%enter%"+ ItemList.getString(ItemNumber+".Lore")+"%enter%";
			lore = lore+ChatColor.GOLD+"---____________________---%enter%";


			if(ItemList.getInt(ItemNumber+".HP")!=0||ItemList.getInt(ItemNumber+".MP")!=0||
					ItemList.getInt(ItemNumber+".STR")!=0||ItemList.getInt(ItemNumber+".DEX")!=0||
					ItemList.getInt(ItemNumber+".INT")!=0||ItemList.getInt(ItemNumber+".WILL")!=0||
					ItemList.getInt(ItemNumber+".LUK")!=0)
			{
				lore = lore+ChatColor.DARK_AQUA+"___--------------------___%enter%"+ChatColor.DARK_AQUA+"       [���ʽ� �ɼ�]%enter%";
				if(ItemList.getInt(ItemNumber+".HP") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� ����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".HP") < 0)
					lore = lore+ChatColor.RED + " �� ����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MP") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� ���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".MP") < 0)
					lore = lore+ChatColor.RED + " �� ���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
				if(ItemList.getInt(ItemNumber+".STR") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".STR") < 0)
					lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
				if(ItemList.getInt(ItemNumber+".DEX") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".DEX") < 0)
					lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
				if(ItemList.getInt(ItemNumber+".INT") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".INT") < 0)
					lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
				if(ItemList.getInt(ItemNumber+".WILL") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".WILL") < 0)
					lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
				if(ItemList.getInt(ItemNumber+".LUK") > 0)
					lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
				else if(ItemList.getInt(ItemNumber+".LUK") < 0)
					lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
				lore = lore+ChatColor.DARK_AQUA+"---____________________---%enter%";
			}

			if(ItemList.getInt(ItemNumber+".MaxSocket")!=0||ItemList.getInt(ItemNumber+".MaxUpgrade")!=0)
			{
				lore = lore+ChatColor.LIGHT_PURPLE+"___--------------------___%enter%"+ChatColor.LIGHT_PURPLE+"       [������ ��ȭ]%enter%";
				if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0 && ItemList.getInt(ItemNumber+".MaxSocket") > 0)
				{
					lore = lore+ChatColor.DARK_PURPLE + " �� ���� : " + "0 / "+ItemList.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
					lore = lore+ChatColor.BLUE + " �� �� : ";
					for(int count = 0; count <ItemList.getInt(ItemNumber+".MaxSocket");count++)
						lore = lore+ChatColor.GRAY + "�� ";
				}
				else if(ItemList.getInt(ItemNumber+".MaxUpgrade") <= 0 && ItemList.getInt(ItemNumber+".MaxSocket") > 0)
				{
					lore = lore+ChatColor.BLUE + " �� �� : ";
					for(int count = 0; count <ItemList.getInt(ItemNumber+".MaxSocket");count++)
						lore = lore+ChatColor.GRAY + "�� ";
				}
				else
					lore = lore+ChatColor.DARK_PURPLE + " �� ���� : " + "0 / "+ItemList.getInt(ItemNumber+".MaxUpgrade");
				lore = lore+"%enter%"+ChatColor.LIGHT_PURPLE+"---____________________---%enter%";
			}
			if(ItemList.getInt(ItemNumber+".MinLV")!=0||ItemList.getInt(ItemNumber+".MinRLV")!=0||
					ItemList.getInt(ItemNumber+".MinSTR")!=0||ItemList.getInt(ItemNumber+".MinDEX")!=0||
					ItemList.getInt(ItemNumber+".MinINT")!=0||ItemList.getInt(ItemNumber+".MinWILL")!=0||
					ItemList.getInt(ItemNumber+".MinLUK")!=0)
			{
				lore = lore+ChatColor.DARK_RED+"___--------------------___%enter%"+ChatColor.DARK_RED+"       [���� ����]%enter%";
				if(ItemList.getInt(ItemNumber+".MinLV") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� ���� : " + ItemList.getInt(ItemNumber+".MinLV")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MinRLV") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� �������� : " + ItemList.getInt(ItemNumber+".MinRLV")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MinSTR") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".MinSTR")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MinDEX") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".MinDEX")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MinINT") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".MinINT")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MinWILL") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".MinWILL")+"%enter%";
				if(ItemList.getInt(ItemNumber+".MinLUK") > 0)
					lore = lore+ChatColor.DARK_RED + " �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".MinLUK")+"%enter%";
				lore = lore+ChatColor.DARK_RED+"---____________________---%enter%";
				
			}
		}
		else if(Type.compareTo("[��ȣ]")==0)
		{
			lore = lore+ItemList.getString(ItemNumber+".Type");
			for(short count = 0; count<20-ItemList.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(ItemList.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.GOLD+"��� ���� ���� : " +ChatColor.WHITE + ItemList.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(ItemList.getInt(ItemNumber+".MinDamage") != 0||ItemList.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.Damage+" : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MinDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MinMaDamage") != 0||ItemList.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+" : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MinMaDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.GRAY + " �� ��� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".DEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + " �� ��ȣ : " + ChatColor.WHITE +ItemList.getInt(ItemNumber+".Protect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.BLUE + " �� ���� ��� : " + ChatColor.WHITE +ItemList.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.DARK_BLUE + " �� ���� ��ȣ : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.DARK_GREEN + " �� �뷱�� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Balance")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.YELLOW + " �� ũ��Ƽ�� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Critical")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.GOLD + " �� ������ : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Durability")+" / "+ ItemList.getInt(ItemNumber+".MaxDurability")+"%enter%";

			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+" �� ���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+"%enter%"+ ItemList.getString(ItemNumber+".Lore")+"%enter%";


			if(ItemList.getInt(ItemNumber+".HP") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� ����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".HP") < 0)
				lore = lore+ChatColor.RED + " �� ����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MP") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� ���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".MP") < 0)
				lore = lore+ChatColor.RED + " �� ���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
			if(ItemList.getInt(ItemNumber+".STR") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".STR") < 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEX") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".DEX") < 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
			if(ItemList.getInt(ItemNumber+".INT") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".INT") < 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
			if(ItemList.getInt(ItemNumber+".WILL") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".WILL") < 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
			if(ItemList.getInt(ItemNumber+".LUK") > 0)
				lore = lore+ChatColor.DARK_AQUA + " �� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".LUK") < 0)
				lore = lore+ChatColor.RED + " �� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_PURPLE + " �� ���� : " + "0 / "+ItemList.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%"+ChatColor.BLUE + " �� �� : ";
				for(byte count = 0; count <ItemList.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+ChatColor.GRAY + "�� ";
			}

			if(ItemList.getInt(ItemNumber+".MinLV")!=0||ItemList.getInt(ItemNumber+".MinRLV")!=0||
					ItemList.getInt(ItemNumber+".MinSTR")!=0||ItemList.getInt(ItemNumber+".MinDEX")!=0||
					ItemList.getInt(ItemNumber+".MinINT")!=0||ItemList.getInt(ItemNumber+".MinWILL")!=0||
					ItemList.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			
			if(ItemList.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� ���� : " + ItemList.getInt(ItemNumber+".MinLV");
			if(ItemList.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� �������� : " + ItemList.getInt(ItemNumber+".MinRLV");
			if(ItemList.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".MinSTR");
			if(ItemList.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".MinDEX");
			if(ItemList.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".MinINT");
			if(ItemList.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".MinWILL");
			if(ItemList.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+ChatColor.DARK_RED + "%enter% �� �ּ� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".MinLUK");
		}
		else if(Type.compareTo("[�÷�]")==0)
		{

			lore = lore+ItemList.getString(ItemNumber+".Type");
			for(int count = 0; count<20-ItemList.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(ItemList.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.GOLD+"��� ���� ���� : " +ChatColor.WHITE + ItemList.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(ItemList.getInt(ItemNumber+".MinDamage") != 0||ItemList.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.RED + GBD_RPG.Main_Main.Main_ServerOption.Damage+" : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MinDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MinMaDamage") != 0||ItemList.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.DARK_AQUA +GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+" : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MinMaDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.GRAY + "��� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".DEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + "��ȣ : " + ChatColor.WHITE +ItemList.getInt(ItemNumber+".Protect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.BLUE + "���� ��� : " + ChatColor.WHITE +ItemList.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.DARK_BLUE + "���� ��ȣ : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.DARK_GREEN + "�뷱�� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Balance")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.YELLOW + "ũ��Ƽ�� : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Critical")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.GOLD + "������ : " +ChatColor.WHITE + ItemList.getInt(ItemNumber+".Durability")+" / "+ ItemList.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+"���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+"%enter%"+ ItemList.getString(ItemNumber+".Lore")+"%enter%";


			if(ItemList.getInt(ItemNumber+".HP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".HP") < 0)
				lore = lore+ChatColor.RED + "����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".MP") < 0)
				lore = lore+ChatColor.RED + "���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
			if(ItemList.getInt(ItemNumber+".STR") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".STR") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEX") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".DEX") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
			if(ItemList.getInt(ItemNumber+".INT") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".INT") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
			if(ItemList.getInt(ItemNumber+".WILL") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".WILL") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
			if(ItemList.getInt(ItemNumber+".LUK") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".LUK") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_PURPLE + "���� : " + "0 / "+ItemList.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%"+ChatColor.BLUE + "�� : ";
				for(byte count = 0; count <ItemList.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+ChatColor.GRAY + "�� ";
			}
			if(ItemList.getInt(ItemNumber+".MinLV")!=0||ItemList.getInt(ItemNumber+".MinRLV")!=0||
					ItemList.getInt(ItemNumber+".MinSTR")!=0||ItemList.getInt(ItemNumber+".MinDEX")!=0||
					ItemList.getInt(ItemNumber+".MinINT")!=0||ItemList.getInt(ItemNumber+".MinWILL")!=0||
					ItemList.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(ItemList.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� ���� : " + ItemList.getInt(ItemNumber+".MinLV");
			if(ItemList.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� �������� : " + ItemList.getInt(ItemNumber+".MinRLV");
			if(ItemList.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".MinSTR");
			if(ItemList.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".MinDEX");
			if(ItemList.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".MinINT");
			if(ItemList.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".MinWILL");
			if(ItemList.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"%enter%"+ChatColor.DARK_RED +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".MinLUK");
		}
		else
		{
			lore = lore+ItemList.getString(ItemNumber+".Type");
			for(int count = 0; count<20-ItemList.getString(ItemNumber+".Type").length();count++)
				lore = lore+" ";
			if(ItemList.getString(ItemNumber+".JOB").compareTo("����")==0)
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%%enter%";
			else
				lore = lore+ItemList.getString(ItemNumber+".Grade")+"%enter%"+ChatColor.WHITE+"��� ���� ���� : " + ItemList.getString(ItemNumber+".JOB")+"%enter%%enter%";
				
			if(ItemList.getInt(ItemNumber+".MinDamage") != 0||ItemList.getInt(ItemNumber+".MaxDamage") != 0)
				lore = lore+ChatColor.WHITE +GBD_RPG.Main_Main.Main_ServerOption.Damage+" : " + ItemList.getInt(ItemNumber+".MinDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MinMaDamage") != 0||ItemList.getInt(ItemNumber+".MaxMaDamage") != 0)
				lore = lore+ChatColor.WHITE +GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+" : " + ItemList.getInt(ItemNumber+".MinMaDamage") + " ~ " + ItemList.getInt(ItemNumber+".MaxMaDamage")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEF") != 0)
				lore = lore+ChatColor.WHITE + "��� : " + ItemList.getInt(ItemNumber+".DEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Protect") != 0)
				lore = lore+ChatColor.WHITE + "��ȣ : " + ItemList.getInt(ItemNumber+".Protect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaDEF") != 0)
				lore = lore+ChatColor.WHITE + "���� ��� : " + ItemList.getInt(ItemNumber+".MaDEF")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaProtect") != 0)
				lore = lore+ChatColor.WHITE + "���� ��ȣ : " + ItemList.getInt(ItemNumber+".MaProtect")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Balance") != 0)
				lore = lore+ChatColor.WHITE + "�뷱�� : " + ItemList.getInt(ItemNumber+".Balance")+"%enter%";
			if(ItemList.getInt(ItemNumber+".Critical") != 0)
				lore = lore+ChatColor.WHITE + "ũ��Ƽ�� : " + ItemList.getInt(ItemNumber+".Critical")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxDurability") > 0)
				lore = lore+ChatColor.WHITE + "������ : " + ItemList.getInt(ItemNumber+".Durability")+" / "+ ItemList.getInt(ItemNumber+".MaxDurability")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore + ChatColor.WHITE+"���õ� : 0.0%"+ChatColor.WHITE+ "%enter%";
			
			lore = lore+"%enter%"+ ItemList.getString(ItemNumber+".Lore")+"%enter%";


			if(ItemList.getInt(ItemNumber+".HP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".HP") < 0)
				lore = lore+ChatColor.RED + "����� : " + ItemList.getInt(ItemNumber+".HP")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MP") > 0)
				lore = lore+ChatColor.DARK_AQUA + "���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".MP") < 0)
				lore = lore+ChatColor.RED + "���� : " + ItemList.getInt(ItemNumber+".MP")+"%enter%";
			if(ItemList.getInt(ItemNumber+".STR") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".STR") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".STR")+"%enter%";
			if(ItemList.getInt(ItemNumber+".DEX") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".DEX") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".DEX")+"%enter%";
			if(ItemList.getInt(ItemNumber+".INT") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".INT") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".INT")+"%enter%";
			if(ItemList.getInt(ItemNumber+".WILL") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".WILL") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".WILL")+"%enter%";
			if(ItemList.getInt(ItemNumber+".LUK") > 0)
				lore = lore+ChatColor.DARK_AQUA + ""+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
			else if(ItemList.getInt(ItemNumber+".LUK") < 0)
				lore = lore+ChatColor.RED + ""+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".LUK")+"%enter%";
			
			if(ItemList.getInt(ItemNumber+".MaxUpgrade") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE + "���� : " + "0 / "+ItemList.getInt(ItemNumber+".MaxUpgrade")+"%enter%";
			if(ItemList.getInt(ItemNumber+".MaxSocket") > 0)
			{
				lore = lore+"%enter%"+ChatColor.WHITE + "�� : ";
				for(byte count = 0; count <ItemList.getInt(ItemNumber+".MaxSocket");count++)
					lore = lore+ChatColor.GRAY + "�� ";
			}
			if(ItemList.getInt(ItemNumber+".MinLV")!=0||ItemList.getInt(ItemNumber+".MinRLV")!=0||
					ItemList.getInt(ItemNumber+".MinSTR")!=0||ItemList.getInt(ItemNumber+".MinDEX")!=0||
					ItemList.getInt(ItemNumber+".MinINT")!=0||ItemList.getInt(ItemNumber+".MinWILL")!=0||
					ItemList.getInt(ItemNumber+".MinLUK")!=0)
				lore = lore+"%enter%";
			if(ItemList.getInt(ItemNumber+".MinLV") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� ���� : " + ItemList.getInt(ItemNumber+".MinLV");
			if(ItemList.getInt(ItemNumber+".MinRLV") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� �������� : " + ItemList.getInt(ItemNumber+".MinRLV");
			if(ItemList.getInt(ItemNumber+".MinSTR") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.STR+" : " + ItemList.getInt(ItemNumber+".MinSTR");
			if(ItemList.getInt(ItemNumber+".MinDEX") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : " + ItemList.getInt(ItemNumber+".MinDEX");
			if(ItemList.getInt(ItemNumber+".MinINT") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.INT+" : " + ItemList.getInt(ItemNumber+".MinINT");
			if(ItemList.getInt(ItemNumber+".MinWILL") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : " + ItemList.getInt(ItemNumber+".MinWILL");
			if(ItemList.getInt(ItemNumber+".MinLUK") > 0)
				lore = lore+"%enter%"+ChatColor.WHITE +"�ּ� "+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : " + ItemList.getInt(ItemNumber+".MinLUK");
		}
		String[] scriptA = lore.split("%enter%");
		for(byte counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  scriptA[counter];
		return scriptA;
	}
}