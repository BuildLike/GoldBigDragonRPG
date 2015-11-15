package GBD.GoldBigDragon_Advanced.GUI;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import GBD.GoldBigDragon_Advanced.Event.Damage;
import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;


public class StatsGUI extends GUIutil
{
	//���� GUIâ�� 1 �������� ������ �ִ� �޼ҵ�//
	public void StatusGUI(Player player)
	{
		Damage dam = new Damage();
	    YamlManager YM,Config;
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
	  	if(GUI_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		new GBD.GoldBigDragon_Advanced.Config.StatConfig().CreateNewStats(player);
		YM = GUI_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		Config = GUI_YC.getNewConfig("config.yml");
		
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.BLACK + "����");

		Stack2(ChatColor.WHITE + "����", 160,4,1,Arrays.asList(ChatColor.GRAY + "������ Ȯ���մϴ�."), 0, inv);
		Stack2(ChatColor.WHITE + "��ų", 403,0,1,Arrays.asList(ChatColor.GRAY + "��ų�� Ȯ���մϴ�."), 9, inv);
		Stack2(ChatColor.WHITE + "����Ʈ", 358,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� ����Ʈ�� Ȯ���մϴ�."), 18, inv);
		Stack2(ChatColor.WHITE + "�ɼ�", 145,0,1,Arrays.asList(ChatColor.GRAY + "��Ÿ ������ �մϴ�."), 27, inv);
		Stack2(ChatColor.WHITE + "��Ÿ", 354,0,1,Arrays.asList(ChatColor.GRAY + "��Ÿ ������ Ȯ���մϴ�."), 36, inv);
		
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 1, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 7, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 10, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 16, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 19, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 25, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 28, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 34, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 37, inv);
		Stack2(ChatColor.RED + " ", 66,0,1,Arrays.asList(""), 43, inv);
		
		ItemStack EXIT = new ItemStack(Material.WOOD_DOOR, 1);
		ItemMeta EXIT_BUTTON = EXIT.getItemMeta();
		EXIT_BUTTON.setDisplayName(ChatColor.WHITE  + "" + ChatColor.BOLD + "�ݱ�");
		EXIT_BUTTON.setLore(Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."));
		EXIT.setItemMeta(EXIT_BUTTON);
		inv.setItem(26, EXIT);

		if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == true)
		{
			Stack2(ChatColor.GREEN + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.GREEN + "]", 397,3,1,
					Arrays.asList(ChatColor.WHITE + "[����] : " +ChatColor.BOLD + YM.getInt("Stat.Level"),
							ChatColor.WHITE + "[���� ����] : " +ChatColor.BOLD + YM.getInt("Stat.RealLevel"),
							ChatColor.WHITE + "[����ġ] : " + ChatColor.BOLD + YM.getInt("Stat.EXP") + " / " + YM.getInt("Stat.MaxEXP"),
							ChatColor.AQUA + "[��ų ����Ʈ] : " + ChatColor.WHITE + ChatColor.BOLD + YM.getInt("Stat.SkillPoint")), 13, inv);
		}
		else
		{
			YamlManager PlayerSkillYML = GUI_YC.getNewConfig("Skill/PlayerData/"+player.getUniqueId()+".yml");
			Stack2(ChatColor.GREEN + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.GREEN + "]", 397,3,1,
					Arrays.asList(ChatColor.WHITE + "[����] : " +ChatColor.BOLD + YM.getInt("Stat.Level"),
							ChatColor.WHITE + "[����] : " +ChatColor.BOLD + PlayerSkillYML.getString("Job.Type"),
							ChatColor.WHITE + "[����ġ] : " + ChatColor.BOLD + YM.getInt("Stat.EXP") + " / " + YM.getInt("Stat.MaxEXP"),
							ChatColor.GREEN + "[���� ����Ʈ] : " + ChatColor.WHITE + ChatColor.BOLD + YM.getInt("Stat.StatPoint"),
							ChatColor.AQUA + "[��ų ����Ʈ] : " + ChatColor.WHITE + ChatColor.BOLD + YM.getInt("Stat.SkillPoint")), 13, inv);
		}
		
		int DefaultDamage = 0;
		if(player.getItemInHand().hasItemMeta() == true)
		{
			if(player.getItemInHand().getItemMeta().hasLore() == true)
			{
				if(player.getItemInHand().getItemMeta().getLore().toString().contains("����� : ") == true)
				{
					switch(player.getItemInHand().getType())
					{
					case WOOD_SPADE :
					case GOLD_SPADE :
						DefaultDamage = 1;
						break;
					case WOOD_PICKAXE :
					case GOLD_PICKAXE:
					case STONE_SPADE:
						DefaultDamage = 2;
						break;
					case WOOD_AXE:
					case GOLD_AXE:
					case STONE_PICKAXE:
					case IRON_SPADE:
						DefaultDamage = 3;
						break;
					case WOOD_SWORD:
					case GOLD_SWORD:
					case STONE_AXE:
					case IRON_PICKAXE:
					case DIAMOND_SPADE:
						DefaultDamage = 4;
						break;
					case STONE_SWORD:
					case IRON_AXE:
					case DIAMOND_PICKAXE:
						DefaultDamage = 5;
						break;
					case IRON_SWORD:
					case DIAMOND_AXE:
						DefaultDamage = 6;
						break;
					case DIAMOND_SWORD:
						DefaultDamage = 7;
						break;
					}
				}
			}
		}
		int stat=dam.getPlayerEquipmentStat(player, "STR")[0];
		if(stat == 0)
		{
			Stack2(ChatColor.DARK_RED + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "ü��"+ChatColor.DARK_RED + "]", 267,0,1,
					Arrays.asList(ChatColor.WHITE +""+ChatColor.BOLD +"      "+ YM.getInt("Stat.STR"),
							ChatColor.GRAY + " ü���� �÷��̾���",ChatColor.GRAY + " ������ ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ���� ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + (dam.CombatMinDamageGet(player,DefaultDamage,YM.getInt("Stat.STR"))) + " ~ " + (dam.CombatMaxDamageGet(player,DefaultDamage, YM.getInt("Stat.STR")))), 20, inv);
		}
		else if(stat > 0)
		{
			Stack2(ChatColor.DARK_RED + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "ü��"+ChatColor.DARK_RED + "]", 267,0,1,
					Arrays.asList(ChatColor.YELLOW +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.STR") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.STR")+")",
							ChatColor.GRAY + " ü���� �÷��̾���",ChatColor.GRAY + " ������ ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ���� ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + (dam.CombatMinDamageGet(player,DefaultDamage,YM.getInt("Stat.STR"))) + " ~ " + (dam.CombatMaxDamageGet(player,DefaultDamage, YM.getInt("Stat.STR")))), 20, inv);
		}
		else
		{
			Stack2(ChatColor.DARK_RED + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "ü��"+ChatColor.DARK_RED + "]", 267,0,1,
					Arrays.asList(ChatColor.RED +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.STR") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.STR")+")",
							ChatColor.GRAY + " ü���� �÷��̾���",ChatColor.GRAY + " ������ ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ���� ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + (dam.CombatMinDamageGet(player,DefaultDamage,YM.getInt("Stat.STR"))) + " ~ " + (dam.CombatMaxDamageGet(player,DefaultDamage, YM.getInt("Stat.STR")))), 20, inv);
		}
		
		stat=dam.getPlayerEquipmentStat(player, "DEX")[0];
		if(stat == 0)
		{
			Stack2(ChatColor.GREEN + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "�ؾ�"+ChatColor.GREEN + "]", 261,0,1,
					Arrays.asList(ChatColor.WHITE +""+ChatColor.BOLD +"      "+ YM.getInt("Stat.DEX"),
							ChatColor.GRAY + " �ؾ��� �÷��̾���",ChatColor.GRAY + " ���Ÿ� ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ���Ÿ� ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + dam.RangeMinDamageGet(player,0,YM.getInt("Stat.DEX")) + " ~ " + dam.RangeMaxDamageGet(player,0, YM.getInt("Stat.DEX"))), 21, inv);
		}
		else if(stat > 0)
		{
			Stack2(ChatColor.GREEN + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "�ؾ�"+ChatColor.GREEN + "]", 261,0,1,
					Arrays.asList(ChatColor.YELLOW +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.DEX") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.DEX")+")",
							ChatColor.GRAY + " �ؾ��� �÷��̾���",ChatColor.GRAY + " ���Ÿ� ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ���Ÿ� ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + dam.RangeMinDamageGet(player,0,YM.getInt("Stat.DEX")) + " ~ " + dam.RangeMaxDamageGet(player,0, YM.getInt("Stat.DEX"))), 21, inv);
		}
		else
		{
			Stack2(ChatColor.GREEN + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "�ؾ�"+ChatColor.GREEN + "]", 261,0,1,
					Arrays.asList(ChatColor.RED +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.DEX") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.DEX")+")",
							ChatColor.GRAY + " �ؾ��� �÷��̾���",ChatColor.GRAY + " ���Ÿ� ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ���Ÿ� ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + dam.RangeMinDamageGet(player,0,YM.getInt("Stat.DEX")) + " ~ " + dam.RangeMaxDamageGet(player,0, YM.getInt("Stat.DEX"))), 21, inv);
		}
		
		stat=dam.getPlayerEquipmentStat(player, "INT")[0];
		if(stat == 0)
		{
			Stack2(ChatColor.AQUA + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.AQUA + "]", 369,0,1,
					Arrays.asList(ChatColor.WHITE +""+ChatColor.BOLD +"      "+ YM.getInt("Stat.INT"),
							ChatColor.GRAY + " ������ �÷��̾",ChatColor.GRAY + " ����ϴ� ��ų ��",ChatColor.GRAY + " ���� ������ �޴�",ChatColor.GRAY + " ��ų ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ��ų ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + ((YM.getInt("Stat.INT")+dam.getPlayerEquipmentStat(player, "INT")[0])*0.6+100) + " %"), 22, inv);
		}
		else if(stat > 0)
		{
			Stack2(ChatColor.AQUA + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.AQUA + "]", 369,0,1,
					Arrays.asList(ChatColor.YELLOW +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.INT") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.INT")+")",
							ChatColor.GRAY + " ������ �÷��̾",ChatColor.GRAY + " ����ϴ� ��ų ��",ChatColor.GRAY + " ���� ������ �޴�",ChatColor.GRAY + " ��ų ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ��ų ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + ((YM.getInt("Stat.INT")+dam.getPlayerEquipmentStat(player, "INT")[0])*0.6+100) + " %"), 22, inv);
		}
		else
		{
			Stack2(ChatColor.AQUA + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.AQUA + "]", 369,0,1,
					Arrays.asList(ChatColor.RED +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.INT") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.INT")+")",
							ChatColor.GRAY + " ������ �÷��̾",ChatColor.GRAY + " ����ϴ� ��ų ��",ChatColor.GRAY + " ���� ������ �޴�",ChatColor.GRAY + " ��ų ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ��ų ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + ((YM.getInt("Stat.INT")+dam.getPlayerEquipmentStat(player, "INT")[0])*0.6+100) + " %"), 22, inv);
		}

		stat=dam.getPlayerEquipmentStat(player, "WILL")[0];
		if(stat == 0)
		{
			Stack2(ChatColor.GRAY + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.GRAY + "]", 370,0,1,
					Arrays.asList(ChatColor.WHITE +""+ChatColor.BOLD +"      "+ YM.getInt("Stat.WILL"),
							ChatColor.GRAY + " ������ �÷��̾���",ChatColor.GRAY + " ũ��Ƽ�� �� ��ų ��",ChatColor.GRAY + " ���� ������ �޴�",ChatColor.GRAY + " ��ų ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ��ų ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + ((YM.getInt("Stat.WILL")+dam.getPlayerEquipmentStat(player, "WILL")[0])*0.6+100) + " %"), 23, inv);
		}
		else if(stat > 0)
		{
			Stack2(ChatColor.GRAY + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.GRAY + "]", 370,0,1,
					Arrays.asList(ChatColor.YELLOW +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.WILL") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.INT")+")",
							ChatColor.GRAY + " ������ �÷��̾���",ChatColor.GRAY + " ũ��Ƽ�� �� ��ų ��",ChatColor.GRAY + " ���� ������ �޴�",ChatColor.GRAY + " ��ų ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ��ų ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + ((YM.getInt("Stat.WILL")+dam.getPlayerEquipmentStat(player, "WILL")[0])*0.6+100) + " %"), 23, inv);
		}
		else
		{
			Stack2(ChatColor.GRAY + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.GRAY + "]", 370,0,1,
					Arrays.asList(ChatColor.RED +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.WILL") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.INT")+")",
							ChatColor.GRAY + " ������ �÷��̾���",ChatColor.GRAY + " ũ��Ƽ�� �� ��ų ��",ChatColor.GRAY + " ���� ������ �޴�",ChatColor.GRAY + " ��ų ���ݷ���",
							ChatColor.GRAY + " ��½��� �ݴϴ�.","",
							ChatColor.AQUA + "" + ChatColor.BOLD +"[�߰� ��ų ���ݷ�]",
							ChatColor.RED + "       " + ChatColor.BOLD + "" + ((YM.getInt("Stat.WILL")+dam.getPlayerEquipmentStat(player, "WILL")[0])*0.6+100) + " %"), 23, inv);
		}

		stat=dam.getPlayerEquipmentStat(player, "LUK")[0];
		if(stat == 0)
		{

			Stack2(ChatColor.YELLOW + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "���"+ChatColor.YELLOW + "]", 322,0,1,
					Arrays.asList(ChatColor.WHITE +""+ChatColor.BOLD +"      "+ YM.getInt("Stat.LUK"),
							ChatColor.GRAY + " ����� �÷��̾��",ChatColor.GRAY + " ������ ���� ���� �Ͼ",
							ChatColor.GRAY + " Ȯ���� ������ŵ�ϴ�.",""), 24, inv);
		}
		else if(stat > 0)
		{

			Stack2(ChatColor.YELLOW + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "���"+ChatColor.YELLOW + "]", 322,0,1,
					Arrays.asList(ChatColor.YELLOW +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.LUK") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.LUK")+")",
							ChatColor.GRAY + " ����� �÷��̾��",ChatColor.GRAY + " ������ ���� ���� �Ͼ",
							ChatColor.GRAY + " Ȯ���� ������ŵ�ϴ�.",""), 24, inv);
		}
		else
		{

			Stack2(ChatColor.YELLOW + "       [" + ChatColor.WHITE +""+ChatColor.BOLD + "���"+ChatColor.YELLOW + "]", 322,0,1,
					Arrays.asList(ChatColor.RED +""+ChatColor.BOLD +"    "+ (YM.getInt("Stat.LUK") + stat) +ChatColor.WHITE + "("+ YM.getInt("Stat.LUK")+")",
							ChatColor.GRAY + " ����� �÷��̾��",ChatColor.GRAY + " ������ ���� ���� �Ͼ",
							ChatColor.GRAY + " Ȯ���� ������ŵ�ϴ�.",""), 24, inv);
		}

		if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
		{
			Stack2(ChatColor.GOLD + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "ü�� ���"+ChatColor.GOLD + "]", 399,0,1,
					Arrays.asList(ChatColor.GRAY + "ü�� ������ �Ѵܰ� ��� ��ŵ�ϴ�.",ChatColor.GRAY + "���� ���� ����Ʈ : "+YM.getInt("Stat.StatPoint")), 29, inv);
			Stack2(ChatColor.GOLD + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "�ؾ� ���"+ChatColor.GOLD + "]", 399,0,1,
					Arrays.asList(ChatColor.GRAY + "�ؾ� ������ �Ѵܰ� ��� ��ŵ�ϴ�.",ChatColor.GRAY + "���� ���� ����Ʈ : "+YM.getInt("Stat.StatPoint")), 30, inv);
			Stack2(ChatColor.GOLD + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "���� ���"+ChatColor.GOLD + "]", 399,0,1,
					Arrays.asList(ChatColor.GRAY + "���� ������ �Ѵܰ� ��� ��ŵ�ϴ�.",ChatColor.GRAY + "���� ���� ����Ʈ : "+YM.getInt("Stat.StatPoint")), 31, inv);
			Stack2(ChatColor.GOLD + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "���� ���"+ChatColor.GOLD + "]", 399,0,1,
					Arrays.asList(ChatColor.GRAY + "���� ������ �Ѵܰ� ��� ��ŵ�ϴ�.",ChatColor.GRAY + "���� ���� ����Ʈ : "+YM.getInt("Stat.StatPoint")), 32, inv);
			Stack2(ChatColor.GOLD + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "��� ���"+ChatColor.GOLD + "]", 399,0,1,
					Arrays.asList(ChatColor.GRAY + "��� ������ �Ѵܰ� ��� ��ŵ�ϴ�.",ChatColor.GRAY + "���� ���� ����Ʈ : "+YM.getInt("Stat.StatPoint")), 33, inv);
		}
		GBD.GoldBigDragon_Advanced.Event.Damage d = new GBD.GoldBigDragon_Advanced.Event.Damage();
		Stack2(ChatColor.GRAY + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "���"+ChatColor.GRAY + "]", 307,0,1,
				Arrays.asList(ChatColor.WHITE + "���� ��� : "+ChatColor.WHITE +(YM.getInt("Stat.DEF")+d.getPlayerEquipmentStat(player, "���")[0]),
						ChatColor.GRAY + "�߰� ���� ��ȣ : "+ChatColor.WHITE + (YM.getInt("Stat.Protect")+d.getPlayerEquipmentStat(player, "��ȣ")[0]),
						ChatColor.AQUA + "�߰� ���� ��� : "+ChatColor.WHITE + (YM.getInt("Stat.Magic_DEF")+d.getMagicDEF(player,YM.getInt("Stat.INT"))),
						ChatColor.DARK_AQUA + "�߰� ���� ��ȣ : "+ChatColor.WHITE + (YM.getInt("Stat.Magic_Protect")+d.getMagicProtect(player, YM.getInt("Stat.INT")))), 38, inv);

		Stack2(ChatColor.RED + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "����"+ChatColor.RED + "]", 409,0,1,
				Arrays.asList(ChatColor.RED + "�߰� ���� ��� ���� : "+ChatColor.WHITE + (YM.getInt("Stat.DEFcrash")+d.getDEFcrash(player, YM.getInt("Stat.DEX"))),
						ChatColor.BLUE + "�߰� ���� ��� ���� : "+ChatColor.WHITE + (YM.getInt("Stat.MagicDEFcrash")+d.getMagicDEFcrash(player, YM.getInt("Stat.INT")))), 39, inv);
		
		Stack2(ChatColor.GREEN + "    [" + ChatColor.WHITE +""+ChatColor.BOLD + "��ȸ"+ChatColor.GREEN + "]", 377,0,1,
				Arrays.asList(ChatColor.GREEN + "�߰� �뷱�� : "+ChatColor.WHITE + d.getBalance(player, YM.getInt("Stat.DEX"), YM.getInt("Stat.Balance"))+"%",
						ChatColor.YELLOW + "�߰� ũ��Ƽ�� : "+ChatColor.WHITE + d.getCritical(YM.getInt("Stat.LUK"), YM.getInt("Stat.WILL"), YM.getInt("Stat.Protect"))+"%"), 42, inv);
		
		player.openInventory(inv);
	}
	
	//���� GUIâ ���� �������� ������ ��, �ش� �����ܿ� ����� �ִ� �޼ҵ�1   -���� GUI, ���ǹڽ�, Ŀ���� ����GUI-//
	public void StatusInventoryclick(InventoryClickEvent event)
	{
	    YamlManager YM,Config;
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();
	  	if(GUI_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		new GBD.GoldBigDragon_Advanced.Config.StatConfig().CreateNewStats(player);
		YM = GUI_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		
		switch (event.getSlot())
		{
		case 99:
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			break;
		case 98:
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			break;
		case 36:
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			ETCGUI EGUI = new ETCGUI();
			EGUI.ETCGUI_Main(player);
			break;
		case 9:
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			PlayerSkillGUI PGUI = new PlayerSkillGUI();
			PGUI.MainSkillsListGUI(player, 0);
			break;
		case 18:
			GBD.GoldBigDragon_Advanced.GUI.QuestGUI QGUI = new GBD.GoldBigDragon_Advanced.GUI.QuestGUI();
			QGUI.MyQuestListGUI(player, 0);
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			break;
		case 27:
			GBD.GoldBigDragon_Advanced.GUI.OptionGUI oGUI = new GBD.GoldBigDragon_Advanced.GUI.OptionGUI();
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			oGUI.optionGUI(player);
			break;
		case 29:
			Config = GUI_YC.getNewConfig("config.yml");
			if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
				if(YM.getInt("Stat.StatPoint") >= 1)
				{
					YM.set("Stat.StatPoint", YM.getInt("Stat.StatPoint")-1);
					YM.set("Stat.STR", YM.getInt("Stat.STR")+1);
					YM.saveConfig();
					s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				}
			StatusGUI(player);
			break;
		case 30:
			Config = GUI_YC.getNewConfig("config.yml");
			if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
				if(YM.getInt("Stat.StatPoint") >= 1)
				{
					YM.set("Stat.StatPoint", YM.getInt("Stat.StatPoint")-1);
					YM.set("Stat.DEX", YM.getInt("Stat.DEX")+1);
					YM.saveConfig();
					s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				}
			StatusGUI(player);
			break;
		case 31:
			Config = GUI_YC.getNewConfig("config.yml");
			if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
				if(YM.getInt("Stat.StatPoint") >= 1)
				{
					YM.set("Stat.StatPoint", YM.getInt("Stat.StatPoint")-1);
					YM.set("Stat.INT", YM.getInt("Stat.INT")+1);
					YM.saveConfig();
					s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				}
			StatusGUI(player);
			break;
		case 32:
			Config = GUI_YC.getNewConfig("config.yml");
			if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
				if(YM.getInt("Stat.StatPoint") >= 1)
				{
					YM.set("Stat.StatPoint", YM.getInt("Stat.StatPoint")-1);
					YM.set("Stat.WILL", YM.getInt("Stat.WILL")+1);
					YM.saveConfig();
					s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				}
			StatusGUI(player);
			break;
		case 33:
			Config = GUI_YC.getNewConfig("config.yml");
			if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
				if(YM.getInt("Stat.StatPoint") >= 1)
				{
					YM.set("Stat.StatPoint", YM.getInt("Stat.StatPoint")-1);
					YM.set("Stat.LUK", YM.getInt("Stat.LUK")+1);
					YM.saveConfig();
					s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				}
			StatusGUI(player);
			break;
		case 26:
			s.SP(player, Sound.PISTON_RETRACT, 0.8F, 1.8F);
			player.closeInventory();
			break;
		}
		return;
	}
	
}
