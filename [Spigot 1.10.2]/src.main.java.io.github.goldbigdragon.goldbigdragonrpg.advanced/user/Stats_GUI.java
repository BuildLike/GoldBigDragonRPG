package user;

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

import battle.Battle_Calculator;
import effect.SoundEffect;
import skill.UserSkill_GUI;
import util.Util_GUI;
import util.YamlLoader;

public class Stats_GUI extends Util_GUI
{
	//���� GUIâ�� 1 �������� ������ �ִ� �޼ҵ�//
	public void StatusGUI(Player player)
	{
		String UniqueCode = "��0��0��0��0��0��r";
	    YamlLoader Config = new YamlLoader();
	    Config.getConfig("config.yml");
		
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0����");

		Stack2("��f����", 160,4,1,Arrays.asList("��7������ Ȯ���մϴ�."), 0, inv);
		Stack2("��f��ų", 403,0,1,Arrays.asList("��7��ų�� Ȯ���մϴ�."), 9, inv);
		Stack2("��f����Ʈ", 358,0,1,Arrays.asList("��7���� �������� ����Ʈ�� Ȯ���մϴ�."), 18, inv);
		Stack2("��f�ɼ�", 145,0,1,Arrays.asList("��7��Ÿ ������ �մϴ�."), 27, inv);
		Stack2("��f��Ÿ", 354,0,1,Arrays.asList("��7��Ÿ ������ Ȯ���մϴ�."), 36, inv);
		
		Stack2("��c ", 66,0,1,Arrays.asList(""), 1, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 7, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 10, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 16, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 19, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 25, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 28, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 34, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 37, inv);
		Stack2("��c ", 66,0,1,Arrays.asList(""), 43, inv);
		
		ItemStack EXIT = new ItemStack(Material.WOOD_DOOR, 1);
		ItemMeta EXIT_BUTTON = EXIT.getItemMeta();
		EXIT_BUTTON.setDisplayName("��f��l�ݱ�");
		EXIT_BUTTON.setLore(Arrays.asList("��7â�� �ݽ��ϴ�."));
		EXIT.setItemMeta(EXIT_BUTTON);
		inv.setItem(26, EXIT);

		int StatPoint = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_StatPoint();
		if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == true)
		{
			Stack2("��a    [��f��l���¡�a]", 397,3,1,
					Arrays.asList("��f[����] : ��l" + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level(),
							"��f[���� ����] : ��l" + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel(),
							"��f[����ġ] : ��l" + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_EXP() + " / " + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_MaxEXP(),
							"��b[��ų ����Ʈ] : ��f" + ChatColor.BOLD + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint()), 13, inv);
		}
		else
		{
		    YamlLoader PlayerSkillYML = new YamlLoader();
			PlayerSkillYML.getConfig("Skill/PlayerData/"+player.getUniqueId()+".yml");
			Stack2("��a       [��f��l���¡�a]", 397,3,1,
					Arrays.asList("��f[����] : ��l" + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level(),
							"��f[����] : ��l" + PlayerSkillYML.getString("Job.Type"),
							"��f[����ġ] : ��l" + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_EXP() + " / " + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_MaxEXP(),
							"��a[���� ����Ʈ] : ��f" + ChatColor.BOLD + StatPoint,
							"��b[��ų ����Ʈ] : ��f" + ChatColor.BOLD + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint()), 13, inv);
		}
		
		int DefaultDamage = 0;
		if(player.getInventory().getItemInMainHand().hasItemMeta() == true)
		{
			if(player.getInventory().getItemInMainHand().getItemMeta().hasLore() == true)
			{
				if(player.getInventory().getItemInMainHand().getItemMeta().getLore().toString().contains(main.Main_ServerOption.damage+" : ") == true)
				{
					switch(player.getInventory().getItemInMainHand().getType())
					{
					case WOOD_SPADE :
					case GOLD_SPADE :
					case WOOD_PICKAXE :
					case GOLD_PICKAXE:
						DefaultDamage += 2;
						break;
					case STONE_SPADE:
					case STONE_PICKAXE:
						DefaultDamage += 3;
						break;
					case IRON_SPADE:
					case WOOD_SWORD:
					case GOLD_SWORD:
					case IRON_PICKAXE:
						DefaultDamage += 4;
						break;
					case DIAMOND_SPADE:
					case STONE_SWORD:
					case DIAMOND_PICKAXE:
						DefaultDamage += 5;
						break;
					case IRON_SWORD:
						DefaultDamage += 6;
						break;
					case WOOD_AXE:
					case GOLD_AXE:
					case DIAMOND_AXE:
					case DIAMOND_SWORD:
						DefaultDamage += 7;
						break;
					case STONE_AXE:
					case IRON_AXE:
						DefaultDamage += 9;
						break;
					}
				}
			}
		}
		int EquipmentStat = Battle_Calculator.getPlayerEquipmentStat(player, "STR", false, null)[0];
		int PlayerStat = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR();
		if(PlayerStat > main.Main_ServerOption.maxSTR)
			PlayerStat = main.Main_ServerOption.maxSTR;
		String Additional = "��c��l"+(Battle_Calculator.CombatDamageGet(player,DefaultDamage,PlayerStat, true)) + " ~ " + (Battle_Calculator.CombatDamageGet(player,DefaultDamage, PlayerStat, false));
		String CurrentStat;
		if(EquipmentStat == 0)
			CurrentStat = "��f��l"+ PlayerStat;
		else if(EquipmentStat > 0)
			CurrentStat = "��e��l"+ (PlayerStat + EquipmentStat) +"��f("+ PlayerStat +")";
		else
			CurrentStat = "��c��l"+(PlayerStat + EquipmentStat) +"��f("+ PlayerStat+")";
		String lore = main.Main_ServerOption.STR_Lore;
		lore = LineUp(CurrentStat, (byte) (main.Main_ServerOption.statSTR.length()+20))+"%enter%"+lore.replace("%stat%", main.Main_ServerOption.statSTR)
				+"%enter%��b��l[�߰� ���� ���ݷ�]%enter%"+LineUp(Additional, (byte) 24);
		
		Stack2("��4"+ LineUp("��c[��f��l"+main.Main_ServerOption.statSTR+"��4]", (byte) 24), 267,0,1,
				Arrays.asList(lore.split("%enter%")), 20, inv);

		int DEX = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX();
		EquipmentStat=Battle_Calculator.getPlayerEquipmentStat(player, "DEX", false, null)[0];
		if(DEX > main.Main_ServerOption.maxDEX)
			DEX = main.Main_ServerOption.maxDEX;
		Additional = "��c��l" + Battle_Calculator.returnRangeDamageValue(player, DEX, 0, true) + " ~ " + Battle_Calculator.returnRangeDamageValue(player, DEX, 0, false);
		if(EquipmentStat == 0)
			CurrentStat = "��f��l"+ DEX;
		else if(EquipmentStat > 0)
			CurrentStat = "��e��l"+ (DEX + EquipmentStat) +"��f("+ DEX+")";
		else
			CurrentStat = "��c��l"+(DEX + EquipmentStat) +"��f("+ DEX+")";

		lore = main.Main_ServerOption.DEX_Lore;
		lore = LineUp(CurrentStat, (byte) (main.Main_ServerOption.statDEX.length()+20))+"%enter%"+lore.replace("%stat%", main.Main_ServerOption.statDEX)
					+"%enter%��b��l[�߰� ���Ÿ� ���ݷ�]%enter%"+LineUp(Additional, (byte) 24);
			
		Stack2(LineUp("��a[��f��l"+main.Main_ServerOption.statDEX+"��a]", (byte) 24), 261,0,1,
				Arrays.asList(lore.split("%enter%")), 21, inv);
		
		int INT = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT();
		if(INT > main.Main_ServerOption.maxINT)
			INT = main.Main_ServerOption.maxINT;
		EquipmentStat=Battle_Calculator.getPlayerEquipmentStat(player, "INT", false, null)[0];
		Additional = "��c��l" + ((INT+Battle_Calculator.getPlayerEquipmentStat(player, "INT", false, null)[0])*0.6+100) + " %";
		if(EquipmentStat == 0)
			CurrentStat = "��f��l"+ INT;
		else if(EquipmentStat > 0)
			CurrentStat = "��e��l"+ (INT + EquipmentStat) +"��f("+ INT+")";
		else
			CurrentStat = "��c��l"+(INT + EquipmentStat) +"��f("+ INT +")";

		lore = main.Main_ServerOption.INT_Lore;
		lore = LineUp(CurrentStat, (byte) (main.Main_ServerOption.statINT.length()+20))+"%enter%"+lore.replace("%stat%", main.Main_ServerOption.statINT)
					+"%enter%��b��l[�߰� ��ų ���ݷ�]%enter%"+LineUp(Additional, (byte) 24);
			
		Stack2(LineUp("��b[��f��l"+main.Main_ServerOption.statINT+"��b]",(byte) 24), 369,0,1,
				Arrays.asList(lore.split("%enter%")), 22, inv);

		int WILL = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL();
		if(WILL > main.Main_ServerOption.maxWILL)
			WILL = main.Main_ServerOption.maxWILL;
		EquipmentStat=Battle_Calculator.getPlayerEquipmentStat(player, "WILL", false, null)[0];
		Additional = "��c��l" + ((WILL+Battle_Calculator.getPlayerEquipmentStat(player, "WILL", false, null)[0])*0.6+100) + " %";
		if(EquipmentStat == 0)
			CurrentStat = "��f��l"+ WILL;
		else if(EquipmentStat > 0)
			CurrentStat = "��e��l"+ (WILL + EquipmentStat) +"��f("+ WILL+")";
		else
			CurrentStat = "��c��l"+(WILL + EquipmentStat) +"��f("+ WILL+")";

		lore = main.Main_ServerOption.WILL_Lore;
		lore = LineUp(CurrentStat, (byte) (main.Main_ServerOption.statWILL.length()+20))+"%enter%"+lore.replace("%stat%", main.Main_ServerOption.statWILL)
					+"%enter%��b��l[�߰� ��ų ���ݷ�]%enter%"+LineUp(Additional, (byte) 24);
			
		Stack2(LineUp("��7[��f��l"+main.Main_ServerOption.statWILL+"��7]",(byte) 24), 370,0,1,
				Arrays.asList(lore.split("%enter%")), 23, inv);
		
		int LUK = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK();
		if(LUK > main.Main_ServerOption.maxLUK)
			LUK = main.Main_ServerOption.maxLUK;
		EquipmentStat=Battle_Calculator.getPlayerEquipmentStat(player, "LUK", false, null)[0];
		if(EquipmentStat == 0)
			CurrentStat = "��f��l"+ LUK;
		else if(EquipmentStat > 0)
			CurrentStat = "��e��l"+ (LUK + EquipmentStat) +"��f("+ LUK+")";
		else
			CurrentStat = "��c��l"+(LUK + EquipmentStat) +"��f("+ LUK+")";

		lore = main.Main_ServerOption.LUK_Lore;
		lore = LineUp(CurrentStat, (byte) (main.Main_ServerOption.statLUK.length()+20))+"%enter%"+lore.replace("%stat%", main.Main_ServerOption.statLUK)
					+"%enter%";
			
		Stack2(LineUp("��e[��f��l"+main.Main_ServerOption.statLUK+"��e]",(byte) 24), 322,0,1,
				Arrays.asList(lore.split("%enter%")), 24, inv);


		if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
		{
			Stack2("��6    [��f��l"+main.Main_ServerOption.statSTR+" ��¡�6]", 399,0,1,
					Arrays.asList("��7"+main.Main_ServerOption.statSTR+" ������ �Ѵܰ� ��� ��ŵ�ϴ�.","��7���� ���� ����Ʈ : "+StatPoint), 29, inv);
			Stack2("��6    [��f��l"+main.Main_ServerOption.statDEX+" ��¡�6]", 399,0,1,
					Arrays.asList("��7"+main.Main_ServerOption.statDEX+" ������ �Ѵܰ� ��� ��ŵ�ϴ�.","��7���� ���� ����Ʈ : "+StatPoint), 30, inv);
			Stack2("��6    [��f��l"+main.Main_ServerOption.statINT+" ��¡�6]", 399,0,1,
					Arrays.asList("��7"+main.Main_ServerOption.statINT+" ������ �Ѵܰ� ��� ��ŵ�ϴ�.","��7���� ���� ����Ʈ : "+StatPoint), 31, inv);
			Stack2("��6    [��f��l"+main.Main_ServerOption.statWILL+" ��¡�6]", 399,0,1,
					Arrays.asList("��7"+main.Main_ServerOption.statWILL+" ������ �Ѵܰ� ��� ��ŵ�ϴ�.","��7���� ���� ����Ʈ : "+StatPoint), 32, inv);
			Stack2("��6    [��f��l"+main.Main_ServerOption.statLUK+" ��¡�6]", 399,0,1,
					Arrays.asList("��7"+main.Main_ServerOption.statLUK+" ������ �Ѵܰ� ��� ��ŵ�ϴ�.","��7���� ���� ����Ʈ : "+StatPoint), 33, inv);
		}
		Stack2("��7    [��f��l����7]", 307,0,1,
				Arrays.asList("��f���� ��� : ��f" +(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEF()+Battle_Calculator.getPlayerEquipmentStat(player, "���", false, null)[0]),
						"��7�߰� ���� ��ȣ : ��f" + (main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Protect()+Battle_Calculator.getPlayerEquipmentStat(player, "��ȣ", false, null)[0]),
						"��b�߰� ���� ��� : ��f" + (main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Magic_DEF()+Battle_Calculator.getMagicDEF(player,INT)),
						"��3�߰� ���� ��ȣ : ��f" + (main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Magic_Protect()+Battle_Calculator.getMagicProtect(player, INT))), 38, inv);

		Stack2("��c    [��f��l�����c]", 409,0,1,
				Arrays.asList("��c�߰� ���� ��� ���� : ��f" + (main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEFcrash()+Battle_Calculator.getDEFcrash(player, DEX)),
						"��9�߰� ���� ��� ���� : ��f" + (main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_MagicDEFcrash()+Battle_Calculator.getMagicDEFcrash(player, INT))), 39, inv);
		
		Stack2("��a    [��f��l��ȸ��a]", 377,0,1,
				Arrays.asList("��a�߰� �뷱�� : ��f" + Battle_Calculator.getBalance(player, DEX, main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Balance())+"%",
						"��e�߰� ũ��Ƽ�� : ��f" + Battle_Calculator.getCritical(player,LUK, WILL,main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Critical())+"%"), 42, inv);
		
		player.openInventory(inv);
	}
	
	//���� GUIâ ���� �������� ������ ��, �ش� �����ܿ� ����� �ִ� �޼ҵ�1   -���� GUI, ���ǹڽ�, Ŀ���� ����GUI-//
	public void StatusInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		
		
		int slot = event.getSlot();
		
		if(slot == 26)
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot >= 29 && slot <= 33)
			{
			    YamlLoader Config = new YamlLoader();
				Config.getConfig("config.yml");
				if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
					if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_StatPoint() >= 1)
					{
						boolean isOk = false;
						if(slot == 29)
							isOk = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR() < main.Main_ServerOption.maxSTR;
						else if(slot == 30)
							isOk = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX() < main.Main_ServerOption.maxDEX;
						else if(slot == 31)
							isOk = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT() < main.Main_ServerOption.maxINT;
						else if(slot == 32)
							isOk = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL() < main.Main_ServerOption.maxWILL;
						else if(slot == 33)
							isOk = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK() < main.Main_ServerOption.maxLUK;
						
						if(isOk)
						{
							main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_StatPoint(-1);
							if(slot == 29)
								main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_STR(1);
							else if(slot == 30)
								main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEX(1);
							else if(slot == 31)
								main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_INT(1);
							else if(slot == 32)
								main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_WILL(1);
							else if(slot == 33)
								main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_LUK(1);
							SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							new effect.SendPacket().sendActionBar(player, "��c��l[�ش� �ɷ��� �� �̻� ��½�ų �� �����ϴ�!]");
						}
					}
				StatusGUI(player);
			}
			else if(slot == 9)
				new UserSkill_GUI().MainSkillsListGUI(player, (short) 0);
			else if(slot == 18)
				new quest.Quest_GUI().MyQuestListGUI(player, (short) 0);
			else if(slot == 27)
				new user.Option_GUI().optionGUI(player);
			else if(slot == 36)
				new ETC_GUI().ETCGUI_Main(player);
		}
		return;
	}
	
	
	public String LineUp(String RawString,byte size)
	{
		if(RawString.length()>=size)
			return RawString;
		else
		{
			short spaceSize = (short) (size - RawString.length());
			StringBuffer TempString = new StringBuffer();
			for(int count = 0; count < spaceSize/2; count++)
				TempString.append(" ");
			TempString.append(RawString);
			for(int count = 0; count < spaceSize/2; count++)
				TempString.append(" ");
			return TempString.toString();
		}
	}
}
