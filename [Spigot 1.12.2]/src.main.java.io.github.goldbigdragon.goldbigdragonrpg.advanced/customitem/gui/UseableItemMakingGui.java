package customitem.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import customitem.CustomItemAPI;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class UseableItemMakingGui extends GuiUtil{

	private String uniqueCode = "��0��0��3��0��5��r";
	
	public void useableItemMakingGui(Player player, int number)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/Consume.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�Ҹ� ������ �� ����");
		String itemName = itemYaml.getString(number+".DisplayName");
		short itemId = (short) itemYaml.getInt(number+".ID");
		byte itemData = (byte) itemYaml.getInt(number+".Data");

		String type = "";
		String grade = "";
		CustomItemAPI ciapi = new CustomItemAPI();
		
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
		
		removeFlagStack(itemName, itemId,itemData,1,Arrays.asList(ciapi.useableLoreCreater(number)), 19, inv);
		
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
		case "[����]":
			stack("��3[       ������       ]", 145,2,1,Arrays.asList("��f������ �ִ� ��������", "��f���� ���� �ݴϴ�.","","��c[�Ϲ� ������ �Ұ���]",""), 24, inv);
			stack("��3[       ���õ�       ]", 416,0,1,Arrays.asList("��f������ ���õ���","��f���� ���� �ݴϴ�.",""), 25, inv);
			break;
		}
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+type), 45, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+number), 53, inv);
		player.openInventory(inv);
	}

	public void useableItemMakingClick(InventoryClickEvent event)
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
				new SelectSkillGui().selectSkillGui(player, (short) 0, itemnumber);
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
			useableItemMakingGui(player, itemnumber);
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
			useableItemMakingGui(player, itemnumber);
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
			useableItemMakingGui(player, itemnumber);
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
			useableItemMakingGui(player, itemnumber);
		}
		else if(iconName.equals("���� ���"))
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			new UseableItemListGui().useableItemListGui(player, 0);
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
			else if(iconName.equals("[       ���õ�       ]"))
			{
				player.sendMessage("��3[������] : �������� ���õ��� �Է��� �ּ���!");
				player.sendMessage("��3(0 ~ 100)");
				u.setString(player, (byte)1, "Proficiency");
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
}
