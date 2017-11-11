package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import main.Main_ServerOption;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;

public class Upgrade_GUI extends Util_GUI
{
	public void upgradeRecipeGui(Player player, int page)
	{
		YamlLoader upgradeYaml = new YamlLoader();
		upgradeYaml.getConfig("Item/Upgrade.yml");

		String uniqueCode = "��0��0��1��1��c��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0������ ��� : " + (page+1));

		String[] upgradeList= upgradeYaml.getKeys().toArray(new String[0]);
		byte loc=0;
		for(int count = page*45; count < upgradeList.length;count++)
		{
			if(count > upgradeList.length || loc >= 45) break;
			String itemName =upgradeList[count];
			String lore=null;
			if(upgradeYaml.getString(itemName+".Only").equals("null"))
				lore = "��f[��� ���]%enter%%enter%";
			else
				lore = upgradeYaml.getString(itemName+".Only")+"%enter%%enter%";

			if(upgradeYaml.getInt(itemName+".MaxDurability") > 0)
				lore = lore+"��3 �� �ִ� ������ : "+upgradeYaml.getInt(itemName+".MaxDurability")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MaxDurability") < 0)
				lore = lore+"��c �� �ִ� ������ : "+upgradeYaml.getInt(itemName+".MaxDurability")+"%enter%";
			if(upgradeYaml.getInt(itemName+".MinDamage") > 0)
				lore = lore+"��3 �� �ּ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(itemName+".MinDamage")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MinDamage") < 0)
				lore = lore+"��c �� �ּ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(itemName+".MinDamage")+"%enter%";
			if(upgradeYaml.getInt(itemName+".MaxDamage") > 0)
				lore = lore+"��3 �� �ִ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(itemName+".MaxDamage")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MaxDamage") < 0)
				lore = lore+"��c �� �ִ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(itemName+".MaxDamage")+"%enter%";
			if(upgradeYaml.getInt(itemName+".MinMaDamage") > 0)
				lore = lore+"��3 �� �ּ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(itemName+".MinMaDamage")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MinMaDamage") < 0)
				lore = lore+"��c �� �ּ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(itemName+".MinMaDamage")+"%enter%";
			if(upgradeYaml.getInt(itemName+".MaxMaDamage") > 0)
				lore = lore+"��3 �� �ִ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(itemName+".MaxMaDamage")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MaxMaDamage") < 0)
				lore = lore+"��c �� �ִ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(itemName+".MaxMaDamage")+"%enter%";
			if(upgradeYaml.getInt(itemName+".DEF") > 0)
				lore = lore+"��3 �� ��� : "+upgradeYaml.getInt(itemName+".DEF")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".DEF") < 0)
				lore = lore+"��c �� ��� : "+upgradeYaml.getInt(itemName+".DEF")+"%enter%";
			if(upgradeYaml.getInt(itemName+".Protect") > 0)
				lore = lore+"��3 �� ��ȣ : "+upgradeYaml.getInt(itemName+".Protect")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".Protect") < 0)
				lore = lore+"��c �� ��ȣ : "+upgradeYaml.getInt(itemName+".Protect")+"%enter%";
			if(upgradeYaml.getInt(itemName+".MaDEF") > 0)
				lore = lore+"��3 �� ���� ��� : "+upgradeYaml.getInt(itemName+".MaDEF")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MaDEF") < 0)
				lore = lore+"��c �� ���� ��� : "+upgradeYaml.getInt(itemName+".MaDEF")+"%enter%";
			if(upgradeYaml.getInt(itemName+".MaProtect") > 0)
				lore = lore+"��3 �� ���� ��ȣ : "+upgradeYaml.getInt(itemName+".MaProtect")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".MaProtect") < 0)
				lore = lore+"��c �� ���� ��ȣ : "+upgradeYaml.getInt(itemName+".MaProtect")+"%enter%";
			if(upgradeYaml.getInt(itemName+".Balance") > 0)
				lore = lore+"��3 �� �뷱�� : "+upgradeYaml.getInt(itemName+".Balance")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".Balance") < 0)
				lore = lore+"��c �� �뷱�� : "+upgradeYaml.getInt(itemName+".Balance")+"%enter%";
			if(upgradeYaml.getInt(itemName+".Critical") > 0)
				lore = lore+"��3 �� ũ��Ƽ�� : "+upgradeYaml.getInt(itemName+".Critical")+"%enter%";
			else if(upgradeYaml.getInt(itemName+".Critical") < 0)
				lore = lore+"��c �� ũ��Ƽ�� : "+upgradeYaml.getInt(itemName+".Critical")+"%enter%";
			
			lore = lore+"%enter%"+upgradeYaml.getString(itemName+".Lore")+"%enter%%enter%";

			lore = lore+"��e �� ���� Ƚ�� : ��f"+upgradeYaml.getInt(itemName+".UpgradeAbleLevel")+"��e ȸ° ���� ����%enter%";
			lore = lore+"��e �� �ʿ� ���õ� : ��f"+upgradeYaml.getInt(itemName+".DecreaseProficiency")+"%enter% ";

			lore = lore+"%enter%��e[�� Ŭ���� ������ ����]%enter%��c[Shift + �� Ŭ���� ������ ����]%enter% ";

			String[] scriptA = lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			
			Stack2("��f"+itemName, 395, 0, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(upgradeList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ������", 339,0,1,Arrays.asList("��7���ο� �������� ����ϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void upgradeRecipeSettingGui(Player player, String recipeName)
	{
		YamlLoader upgradeYaml = new YamlLoader();
		upgradeYaml.getConfig("Item/Upgrade.yml");

		String uniqueCode = "��0��0��1��1��d��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0������ ����");

		String lore=null;
		if(upgradeYaml.getString(recipeName+".Only").equals("null"))
			lore = "��f[��� ���]%enter%%enter%";
		else
			lore = upgradeYaml.getString(recipeName+".Only")+"%enter%%enter%";

		if(upgradeYaml.getInt(recipeName+".MaxDurability") > 0)
			lore = lore+"��3 �� �ִ� ������ : "+upgradeYaml.getInt(recipeName+".MaxDurability")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MaxDurability") < 0)
			lore = lore+"��c �� �ִ� ������ : "+upgradeYaml.getInt(recipeName+".MaxDurability")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".MinDamage") > 0)
			lore = lore+"��3 �� �ּ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(recipeName+".MinDamage")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MinDamage") < 0)
			lore = lore+"��c �� �ּ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(recipeName+".MinDamage")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".MaxDamage") > 0)
			lore = lore+"��3 �� �ִ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(recipeName+".MaxDamage")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MaxDamage") < 0)
			lore = lore+"��c �� �ִ� "+Main_ServerOption.damage+" : "+upgradeYaml.getInt(recipeName+".MaxDamage")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".MinMaDamage") > 0)
			lore = lore+"��3 �� �ּ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(recipeName+".MinMaDamage")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MinMaDamage") < 0)
			lore = lore+"��c �� �ּ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(recipeName+".MinMaDamage")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".MaxMaDamage") > 0)
			lore = lore+"��3 �� �ִ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(recipeName+".MaxMaDamage")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MaxMaDamage") < 0)
			lore = lore+"��c �� �ִ� "+Main_ServerOption.magicDamage+" : "+upgradeYaml.getInt(recipeName+".MaxMaDamage")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".DEF") > 0)
			lore = lore+"��3 �� ��� : "+upgradeYaml.getInt(recipeName+".DEF")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".DEF") < 0)
			lore = lore+"��c �� ��� : "+upgradeYaml.getInt(recipeName+".DEF")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".Protect") > 0)
			lore = lore+"��3 �� ��ȣ : "+upgradeYaml.getInt(recipeName+".Protect")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".Protect") < 0)
			lore = lore+"��c �� ��ȣ : "+upgradeYaml.getInt(recipeName+".Protect")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".MaDEF") > 0)
			lore = lore+"��3 �� ���� ��� : "+upgradeYaml.getInt(recipeName+".MaDEF")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MaDEF") < 0)
			lore = lore+"��c �� ���� ��� : "+upgradeYaml.getInt(recipeName+".MaDEF")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".MaProtect") > 0)
			lore = lore+"��3 �� ���� ��ȣ : "+upgradeYaml.getInt(recipeName+".MaProtect")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".MaProtect") < 0)
			lore = lore+"��c �� ���� ��ȣ : "+upgradeYaml.getInt(recipeName+".MaProtect")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".Balance") > 0)
			lore = lore+"��3 �� �뷱�� : "+upgradeYaml.getInt(recipeName+".Balance")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".Balance") < 0)
			lore = lore+"��c �� �뷱�� : "+upgradeYaml.getInt(recipeName+".Balance")+"%enter%";
		if(upgradeYaml.getInt(recipeName+".Critical") > 0)
			lore = lore+"��3 �� ũ��Ƽ�� : "+upgradeYaml.getInt(recipeName+".Critical")+"%enter%";
		else if(upgradeYaml.getInt(recipeName+".Critical") < 0)
			lore = lore+"��c �� ũ��Ƽ�� : "+upgradeYaml.getInt(recipeName+".Critical")+"%enter%";
		
		lore = lore+"%enter%"+upgradeYaml.getString(recipeName+".Lore")+"%enter%%enter%";

		lore = lore+"��e �� ���� Ƚ�� : ��f"+upgradeYaml.getInt(recipeName+".UpgradeAbleLevel")+"��e ȸ° ���� ����%enter%";
		lore = lore+"��e �� �ʿ� ���õ� : ��f"+upgradeYaml.getInt(recipeName+".DecreaseProficiency")+"%enter% ";

		
		String[] scriptA = lore.split("%enter%");
		for(int counter = 0; counter < scriptA.length; counter++)
			scriptA[counter] =  " "+scriptA[counter];

		Stack2("��3[    �����    ]", 265,0,1,null, 9, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 10, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 11, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 18, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 20, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 27, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 28, inv);
		Stack2("��3[    �����    ]", 265,0,1,null, 29, inv);
		
		Stack2("��f"+recipeName, 395, 0, 1,Arrays.asList(scriptA), 19, inv);
		Stack2("��f[   ���� ����   ]", 421, 0, 1,Arrays.asList("��f�������� ������ �����մϴ�."), 37, inv);
		
		Stack2("��3[    Ÿ�� ����    ]", 61,0,1,Arrays.asList("��f���� ������ Ÿ����","��f�����մϴ�.","","��f[    ���� Ÿ��    ]",upgradeYaml.getString(recipeName+".Only"),""), 13, inv);
		Stack2("��3[       "+Main_ServerOption.damage+"       ]", 267,0,1,Arrays.asList("��f������ "+Main_ServerOption.damage+"��","��f��� ��ŵ�ϴ�.",""), 14, inv);
		Stack2("��3[     "+Main_ServerOption.magicDamage+"     ]", 403,0,1,Arrays.asList("��f������ "+Main_ServerOption.magicDamage+"��","��f��� ��ŵ�ϴ�.",""), 15, inv);
		Stack2("��3[       �뷱��       ]", 262,0,1,Arrays.asList("��f������ �뷱����","��f��� ��ŵ�ϴ�.",""), 16, inv);
		Stack2("��3[        ���        ]", 307,0,1,Arrays.asList("��f������ ������","��f��� ��ŵ�ϴ�.",""), 22, inv);
		Stack2("��3[        ��ȣ        ]", 306,0,1,Arrays.asList("��f������ ��ȣ��","��f��� ��ŵ�ϴ�.",""), 23, inv);
		Stack2("��3[      ���� ���      ]", 311,0,1,Arrays.asList("��f������ ���� ��","��f��� ��ŵ�ϴ�.",""), 24, inv);
		Stack2("��3[      ���� ��ȣ      ]", 310,0,1,Arrays.asList("��f������ ���� ��ȣ��","��f��� ��ŵ�ϴ�.",""), 25, inv);
		Stack2("��3[      ũ��Ƽ��      ]", 377,0,1,Arrays.asList("��f������ ũ��Ƽ�� Ȯ����","��f��� ��ŵ�ϴ�.",""), 31, inv);
		Stack2("��3[       ������       ]", 145,2,1,Arrays.asList("��f������ �ִ� ��������","��f���� �մϴ�.",""), 32, inv);
		Stack2("��3[        ����        ]", 145,0,1,Arrays.asList("��f���� ������ ���� ������","��f���� �մϴ�.",""), 33, inv);
		Stack2("��3[       ���õ�       ]", 416,0,1,Arrays.asList("��f������ �ʿ��� ���õ���","��f���� �մϴ�.",""), 34, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+recipeName), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void upgradeRecipeGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
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
				new OPbox_GUI().opBoxGuiMain(player, (byte) 2);
			else if(slot == 48)//���� ������
				upgradeRecipeGui(player, page-1);
			else if(slot == 49)//�� ������
			{
				player.closeInventory();
				player.sendMessage("��3[����] : ���ο� �������� �̸��� �Է� �ϼ���!");
				UserData_Object u = new UserData_Object();
				u.setType(player, "Upgrade");
				u.setString(player, (byte)1, "NUR");
			}
			else if(slot == 50)//���� ������
				upgradeRecipeGui(player, page+1);
			else
			{
				if(event.isLeftClick() == true&&event.isShiftClick()==false)
					upgradeRecipeSettingGui(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isRightClick()==true&&event.isShiftClick()==true)
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					YamlLoader upgradeYaml = new YamlLoader();
					upgradeYaml.getConfig("Item/Upgrade.yml");
					upgradeYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					upgradeYaml.saveConfig();
					upgradeRecipeGui(player, page);
				}
			}
		}
	}
	
	public void upgradeRecipeSettingGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		if(!((event.getSlot()>=9&&event.getSlot()<=11)||(event.getSlot()>=18&&event.getSlot()<=20)||(event.getSlot()>=27&&event.getSlot()<=29)))
		{
			if(slot == 53)//������
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				String recipeName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
				if(slot == 45)//���� ���
					upgradeRecipeGui(player, 0);
				else if(slot == 13)//Ÿ�� ����
				{
					YamlLoader upgradeYaml = new YamlLoader();
					upgradeYaml.getConfig("Item/Upgrade.yml");
					YamlLoader customTypeYaml = new YamlLoader();
					customTypeYaml.getConfig("Item/CustomType.yml");
				  	String[] Type = customTypeYaml.getKeys().toArray(new String[0]);
				  	if(Type.length == 0)
				  	{
						if(upgradeYaml.getString(recipeName+".Only").contains("[���� ����]"))
							upgradeYaml.set(recipeName+".Only","��c[�Ѽ� ��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[�Ѽ� ��]"))
							upgradeYaml.set(recipeName+".Only","��c[��� ��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��� ��]"))
							upgradeYaml.set(recipeName+".Only","��c[����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[����]"))
							upgradeYaml.set(recipeName+".Only","��c[��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��]"))
							upgradeYaml.set(recipeName+".Only","��2[���Ÿ� ����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[���Ÿ� ����]"))
							upgradeYaml.set(recipeName+".Only","��2[Ȱ]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[Ȱ]"))
							upgradeYaml.set(recipeName+".Only","��2[����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[����]"))
							upgradeYaml.set(recipeName+".Only","��3[���� ����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[���� ����]"))
							upgradeYaml.set(recipeName+".Only","��3[����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[����]"))
							upgradeYaml.set(recipeName+".Only","��3[������]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[������]"))
							upgradeYaml.set(recipeName+".Only","��f[��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��]"))
							upgradeYaml.set(recipeName+".Only","��7[��Ÿ]");
						else
							upgradeYaml.set(recipeName+".Only","��c[���� ����]");
				  	}
				  	else
				  	{
						if(upgradeYaml.getString(recipeName+".Only").contains("[���� ����]"))
							upgradeYaml.set(recipeName+".Only","��c[�Ѽ� ��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[�Ѽ� ��]"))
							upgradeYaml.set(recipeName+".Only","��c[��� ��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��� ��]"))
							upgradeYaml.set(recipeName+".Only","��c[����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[����]"))
							upgradeYaml.set(recipeName+".Only","��c[��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��]"))
							upgradeYaml.set(recipeName+".Only","��2[���Ÿ� ����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[���Ÿ� ����]"))
							upgradeYaml.set(recipeName+".Only","��2[Ȱ]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[Ȱ]"))
							upgradeYaml.set(recipeName+".Only","��2[����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[����]"))
							upgradeYaml.set(recipeName+".Only","��3[���� ����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[���� ����]"))
							upgradeYaml.set(recipeName+".Only","��3[����]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[����]"))
							upgradeYaml.set(recipeName+".Only","��3[������]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[������]"))
							upgradeYaml.set(recipeName+".Only","��f[��]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��]"))
							upgradeYaml.set(recipeName+".Only","��7[��Ÿ]");
						else if(upgradeYaml.getString(recipeName+".Only").contains("[��Ÿ]"))
							upgradeYaml.set(recipeName+".Only","��f"+Type[0].toString());
				  		else
						{
							for(int count = 0; count < Type.length; count++)
							{
								if((upgradeYaml.getString(recipeName+".Only").contains(Type[count])))
								{
									if(count+1 == Type.length)
										upgradeYaml.set(recipeName+".Only","��c[���� ����]");
									else
										upgradeYaml.set(recipeName+".Only","��f"+Type[(count+1)]);
									break;
								}
							}
						}
				  	}
					upgradeYaml.saveConfig();
					upgradeRecipeSettingGui(player,recipeName );
				}
				else
				{
					UserData_Object u = new UserData_Object();
					player.closeInventory();
					u.setType(player, "Upgrade");
					u.setString(player, (byte)6, recipeName);
					if(slot == 14)//������ ����
					{
						player.sendMessage("��3[����] : ��ȭ�� �ּ� ���ݷ� ��ġ�� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UMinD");
					}
					else if(slot == 15)//���� ����� ����
					{
						player.sendMessage("��3[����] : ��ȭ�� �ּ� ���� ���ݷ� ��ġ�� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UMMinD");
					}
					else if(slot == 16)//�뷱�� ����
					{
						player.sendMessage("��3[����] : ��ȭ�� �뷱���� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UB");
					}
					else if(slot == 22)//��� ����
					{
						player.sendMessage("��3[����] : ��ȭ�� ������ �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UDEF");
					}
					else if(slot == 23)//��ȣ ����
					{
						player.sendMessage("��3[����] : ��ȭ�� ��ȣ�� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UP");
					}
					else if(slot == 24)//���� ��� ����
					{
						player.sendMessage("��3[����] : ��ȭ�� ���� ������ �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UMDEF");
					}
					else if(slot == 25)//���� ��ȣ ����
					{
						player.sendMessage("��3[����] : ��ȭ�� ���� ��ȣ�� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UMP");
					}
					else if(slot == 31)//ũ��Ƽ�� ����
					{
						player.sendMessage("��3[����] : ��ȭ�� ũ��Ƽ���� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UC");
					}
					else if(slot == 32)//������ ����
					{
						player.sendMessage("��3[����] : ��ȭ�� �ִ� �������� �Է��ϼ���!");
						player.sendMessage("��a(��e"+ Integer.MIN_VALUE+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UMD");
					}
					else if(slot == 33)//���� ���� ����
					{
						player.sendMessage("��3[����] : �� ������ �ϱ� ���� ���� ������ �Է��ϼ���!");
						player.sendMessage("��a(��e"+0+"��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)1, "UUL");
					}
					else if(slot == 34)//�ʿ� ���õ� ����
					{
						player.sendMessage("��3[����] : ���� ���� ������ ���õ��� �Է��ϼ���!");
						player.sendMessage("��a(��e"+0+"��a ~ ��e100��a)");
						u.setString(player, (byte)1, "UDP");
					}
					else if(slot == 37)//���� ����
					{
						player.sendMessage("��3[������] : �������� ������ �Է��� �ּ���!");
						player.sendMessage("��6%enter%��f - ���� ��� ���� -");
						player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
						"��3&3 ��4&4 ��5&5 " +
								"��6&6 ��7&7 ��8&8 " +
						"��9&9 ��a&a ��b&b ��c&c " +
								"��d&d ��e&e ��f&f");
						u.setString(player, (byte)1, "ULC");
					}
				}
			}
		}
	}
}
