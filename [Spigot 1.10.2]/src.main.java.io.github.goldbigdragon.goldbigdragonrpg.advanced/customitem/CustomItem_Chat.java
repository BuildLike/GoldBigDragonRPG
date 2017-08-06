package customitem;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_Chat;
import util.YamlLoader;



public class CustomItem_Chat extends Util_Chat
{
	public void ItemTypeChatting(PlayerChatEvent event)
	{
		UserData_Object u = new UserData_Object();
		Player player = event.getPlayer();
		customitem.CustomItem_GUI IGUI = new customitem.CustomItem_GUI();
		admin.Upgrade_GUI UpGUI = new admin.Upgrade_GUI();
		

	  	YamlLoader upgradeYaml = new YamlLoader();
		upgradeYaml.getConfig("Item/Upgrade.yml");
	  	YamlLoader itemYaml = new YamlLoader();
	  	itemYaml.getConfig("Item/ItemList.yml");
		if(u.getType(player).compareTo("UseableItem")==0||u.getType(player).compareTo("UseableItem")==0)
			itemYaml.getConfig("Item/Consume.yml");
		event.setCancelled(true);
		int number = 0;
		String Message = ChatColor.stripColor(event.getMessage());
		if(u.getInt(player, (byte)3)!=-1)
			number = u.getInt(player, (byte)3);
		if(itemYaml.getString(number+"")==null&&u.getType(player).compareTo("Upgrade")!=0)
		{
			player.sendMessage(ChatColor.RED+"[SYSTEM] : �ٸ� OP�� �������� �����Ͽ� �ݿ����� �ʾҽ��ϴ�!");
			return;
		}
		String SayType = u.getString(player, (byte)1);
		if(SayType.compareTo("DisplayName")==0 || SayType.compareTo("Lore")==0)
			itemYaml.set(number+"."+SayType,event.getMessage());
		else if(SayType.compareTo("ID")==0)
		{
			if(isIntMinMax(Message, player, 1, Integer.MAX_VALUE))
			{
				event.Main_Interact I = new event.Main_Interact();
				if(I.SetItemDefaultName(Short.parseShort(Message),(byte)0).compareTo("�������� ���� ������")==0)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �������� �������� �ʽ��ϴ�!");
	  				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  				return;
				}
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
			}
		}
		else if(SayType.compareTo("MinDamage")==0)
		{
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				u.setType(player, u.getType(player));
				u.setString(player, (byte)1, "MaxDamage");
				player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ִ� "+main.Main_ServerOption.Damage+"�� �Է��� �ּ���!");
				player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
			}
			return;
		}
		else if(SayType.compareTo("MinMaDamage")==0)
		{
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				u.setType(player, u.getType(player));
				u.setString(player, (byte)1, "MaxMaDamage");
				player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �ִ� "+main.Main_ServerOption.MagicDamage+"�� �Է��� �ּ���!");
				player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
			}
			return;
		}
		else if(SayType.compareTo("MaxDurability")==0)
		{
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				u.setType(player, u.getType(player));
				u.setString(player, (byte)1, "Durability");
				player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���� �������� �Է��� �ּ���!");
				player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+itemYaml.getInt(number+".MaxDurability")+")");
			}
			return;
		}
		else if(SayType.compareTo("HP")==0)
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				if(u.getInt(player, (byte)4) != -1)
				{
					if(u.getInt(player, (byte)4) == -8)
					{
						customitem.UseableItem_GUI UGUI = new customitem.UseableItem_GUI();
						UGUI.NewUseableItemGUI(player, number);
						u.clearAll(player);
						return;
					}
					else
					{
						u.setType(player, u.getType(player));
						u.setString(player, (byte)1, "MP");
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� ������ �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(-127 ~ 127)");
						return;
					}
				}
				else
				{
					u.setType(player, u.getType(player));
					u.setString(player, (byte)1, "MP");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� ������ �Է��� �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(-127 ~ 127)");
				}
			}
			return;
		}
		else if(SayType.compareTo("MP")==0)
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				if(u.getInt(player, (byte)4) != -1)
				{
					if(u.getInt(player, (byte)4) == -8)
					{
						customitem.UseableItem_GUI UGUI = new customitem.UseableItem_GUI();
						UGUI.NewUseableItemGUI(player, number);
						u.clearAll(player);
						return;
					}
					else
					{
						u.setType(player, u.getType(player));
						u.setString(player, (byte)1, "STR");
						player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� "+main.Main_ServerOption.STR+"�� �Է��� �ּ���!");
						player.sendMessage(ChatColor.DARK_AQUA+"(-127 ~ 127)");
						return;
					}
				}
				else
				{
					u.setType(player, u.getType(player));
					u.setString(player, (byte)1, "STR");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� "+main.Main_ServerOption.STR+"�� �Է��� �ּ���!");
					player.sendMessage(ChatColor.DARK_AQUA+"(-127 ~ 127)");
					return;
				}
			}
			return;
		}
		else if(SayType.compareTo("STR")==0||SayType.compareTo("DEX")==0||SayType.compareTo("INT")==0||SayType.compareTo("WILL")==0||
				SayType.compareTo("LUK")==0||SayType.compareTo("Balance")==0)
		{
			if(isIntMinMax(Message, player, -127, 127))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				u.setType(player, u.getType(player));
				if(SayType.compareTo("STR")==0)
				{
					u.setString(player, (byte)1, "DEX");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� "+main.Main_ServerOption.DEX+"�� �Է��� �ּ���!");
				}
				else if(SayType.compareTo("DEX")==0)
				{
					u.setString(player, (byte)1, "INT");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� "+main.Main_ServerOption.INT+"�� �Է��� �ּ���!");
				}
				else if(SayType.compareTo("INT")==0)
				{
					u.setString(player, (byte)1, "WILL");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� "+main.Main_ServerOption.WILL+"�� �Է��� �ּ���!");
				}
				else if(SayType.compareTo("WILL")==0)
				{
					u.setString(player, (byte)1, "LUK");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ���ʽ� "+main.Main_ServerOption.LUK+"�� �Է��� �ּ���!");
				}
				else if(SayType.compareTo("LUK")==0)
				{
					u.setString(player, (byte)1, "Balance");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �뷱���� �Է��� �ּ���!");
				}
				else if(SayType.compareTo("Balance")==0)
				{
					u.setString(player, (byte)1, "Critical");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� ũ��Ƽ���� �Է��� �ּ���!");
				}
				player.sendMessage(ChatColor.DARK_AQUA+"(-127 ~ 127)");
			}
			return;
		}
		else if(SayType.compareTo("Critical")==0)
		{
			if(isIntMinMax(Message, player, -127, 127))
			{
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
				itemYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				if(u.getType(player).compareTo("UseableItem")==0)
				{
					customitem.UseableItem_GUI UGUI = new customitem.UseableItem_GUI();
					UGUI.NewUseableItemGUI(player, number);
				}
				else
					IGUI.NewItemGUI(player, number);
				u.clearAll(player);
			}
			return;
		}
		else if(SayType.compareTo("Saturation")==0 ||SayType.compareTo("SkillPoint")==0 ||SayType.compareTo("StatPoint")==0 ||
				SayType.compareTo("Data")==0 ||SayType.compareTo("DEF")==0 ||SayType.compareTo("Protect")==0 ||SayType.compareTo("MaDEF")==0 ||
				SayType.compareTo("MaProtect")==0 ||SayType.compareTo("MaxUpgrade")==0 ||SayType.compareTo("MaxDamage")==0 ||SayType.compareTo("MaxMaDamage")==0 ||
				SayType.compareTo("Durability")==0)
		{
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
				itemYaml.set(number+"."+SayType,Integer.parseInt(Message));
		}
		else if(SayType.compareTo("NUR")==0)//NewUpgradeRecipe
		{
			Message = Message.replace(".", "");
			if(upgradeYaml.contains(Message)==true)
			{
				player.sendMessage(ChatColor.RED+"[����] : �ش� �̸��� �������� �̹� �����մϴ�!");
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				return;
			}
			upgradeYaml.set(Message+".Lore", ChatColor.WHITE+"������ ���� �ٵ�� �������̴�.%enter%"+ChatColor.WHITE+"���� �ٵ��� ����� ��������%enter%"+ChatColor.WHITE+"����������, �������̴�.");
			upgradeYaml.set(Message+".Only",ChatColor.RED+ "[���� ����]");
			upgradeYaml.set(Message+".MaxDurability", -50);
			upgradeYaml.set(Message+".MinDamage", 1);
			upgradeYaml.set(Message+".MaxDamage", 8);
			upgradeYaml.set(Message+".MinMaDamage", 0);
			upgradeYaml.set(Message+".MaxMaDamage", 0);
			upgradeYaml.set(Message+".DEF", 0);
			upgradeYaml.set(Message+".Protect", 0);
			upgradeYaml.set(Message+".MaDEF", 0);
			upgradeYaml.set(Message+".MaProtect", 0);
			upgradeYaml.set(Message+".Critical", 2);
			upgradeYaml.set(Message+".Balance", 0);
			upgradeYaml.set(Message+".UpgradeAbleLevel", 0);
			upgradeYaml.set(Message+".DecreaseProficiency",30);
			upgradeYaml.saveConfig();
			SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.8F);
			UpGUI.UpgradeRecipeSettingGUI(player, Message);
			u.clearAll(player);
			return;
		}
		else if(SayType.compareTo("UMinD")==0)//UpgradeMinDamage
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MinDamage", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				u.setType(player, u.getType(player));
				u.setString(player, (byte)1, "UMaxD");
				player.sendMessage(ChatColor.DARK_AQUA+"[����] : ��ȭ�� �ִ� ���ݷ� ��ġ�� �Է��ϼ���!");
				player.sendMessage(ChatColor.GREEN + "("+ChatColor.YELLOW + Integer.MIN_VALUE+ChatColor.GREEN+" ~ "+ChatColor.YELLOW+""+Integer.MAX_VALUE+ChatColor.GREEN+")");
			}
			return;
		}
		else if(SayType.compareTo("UMaxD")==0)//UpgradeMaxDamage
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MaxDamage", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UMMinD")==0)//UpgradeMagicMinDamage
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MinMaDamage", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				u.setType(player, u.getType(player));
				u.setString(player, (byte)1, "UMMaxD");
				player.sendMessage(ChatColor.DARK_AQUA+"[����] : ��ȭ�� �ִ� ���� ���ݷ� ��ġ�� �Է��ϼ���!");
				player.sendMessage(ChatColor.GREEN + "("+ChatColor.YELLOW + Integer.MIN_VALUE+ChatColor.GREEN+" ~ "+ChatColor.YELLOW+""+Integer.MAX_VALUE+ChatColor.GREEN+")");
			}
			return;
		}
		else if(SayType.compareTo("UMMaxD")==0)//UpgradeMagicMaxDamage
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MaxMaDamage", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UB")==0)//UpgradeBalance
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".Balance", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UDEF")==0)//UpgradeDefense
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".DEF", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UP")==0)//UpgradeProtect
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".Protect", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UMDEF")==0)//UpgradeMagicDefense
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MaDEF", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UMP")==0)//UpgradeMagicProtect
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MaProtect", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UC")==0)//UpgradeCritical
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".Critical", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UMD")==0)//UpgradeMaxDurability
		{
			if(isIntMinMax(Message, player, Integer.MIN_VALUE, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".MaxDurability", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UUL")==0)//UpgradeUpgradeLevel
		{
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".UpgradeAbleLevel", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("UDP")==0)//UpgradeDecreaseProficiency
		{
			if(isIntMinMax(Message, player, 0, 100))
			{
				upgradeYaml.set(u.getString(player, (byte)6)+".DecreaseProficiency", Integer.parseInt(Message));
				upgradeYaml.saveConfig();
				UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			}
			return;
		}
		else if(SayType.compareTo("ULC")==0)//Upgrade Lore Change
		{
			upgradeYaml.set(u.getString(player, (byte)6)+".Lore", event.getMessage());
			upgradeYaml.saveConfig();
			UpGUI.UpgradeRecipeSettingGUI(player, u.getString(player, (byte)6));
			u.clearAll(player);
			SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
			return;
		}
		else if(SayType.compareTo("MinSTR")==0||SayType.compareTo("MinDEX")==0||SayType.compareTo("MinINT")==0||SayType.compareTo("MinWILL")==0||
				SayType.compareTo("MinLV")==0||SayType.compareTo("MinRLV")==0||SayType.compareTo("MinLUK")==0)
		{
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				itemYaml.set(number+"."+SayType, Integer.parseInt(Message));
				itemYaml.saveConfig();
				u.setType(player, u.getType(player));
				if(SayType.compareTo("MinLV")==0)
				{
					u.setString(player, (byte)1, "MinRLV");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� �������� ������ �Է� �� �ּ���!");
				}
				else if(SayType.compareTo("MinRLV")==0)
				{
					u.setString(player, (byte)1, "MinSTR");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� "+main.Main_ServerOption.STR+" ������ �Է� �� �ּ���!");
				}
				else if(SayType.compareTo("MinSTR")==0)
				{
					u.setString(player, (byte)1, "MinDEX");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� "+main.Main_ServerOption.DEX+" ������ �Է� �� �ּ���!");
				}
				else if(SayType.compareTo("MinDEX")==0)
				{
					u.setString(player, (byte)1, "MinINT");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� "+main.Main_ServerOption.INT+" ������ �Է� �� �ּ���!");
				}
				else if(SayType.compareTo("MinINT")==0)
				{
					u.setString(player, (byte)1, "MinWILL");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� "+main.Main_ServerOption.WILL+" ������ �Է� �� �ּ���!");
				}
				else if(SayType.compareTo("MinWILL")==0)
				{
					u.setString(player, (byte)1, "MinLUK");
					player.sendMessage(ChatColor.DARK_AQUA+"[������] : �������� "+main.Main_ServerOption.LUK+" ������ �Է� �� �ּ���!");
				}
				if(SayType.compareTo("MinLUK")==0)
				{
					SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
					IGUI.NewItemGUI(player, number);
					u.clearAll(player);
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
				}
			}
			return;
		}
		itemYaml.saveConfig();
		SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
		if(u.getType(player).compareTo("UseableItem")==0)
			new customitem.UseableItem_GUI().NewUseableItemGUI(player, number);
		else
			IGUI.NewItemGUI(player, number);
		u.clearAll(player);
		return;
	}

}
