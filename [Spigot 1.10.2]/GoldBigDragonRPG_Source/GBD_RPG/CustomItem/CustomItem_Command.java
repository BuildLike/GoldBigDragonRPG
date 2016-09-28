package GBD_RPG.CustomItem;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class CustomItem_Command
{
	public void onCommand1(CommandSender talker, Command command, String string, String[] args)
	{
		Player player = (Player) talker;
		  if(talker.isOp() == true)
		  {
			  if(args.length == 0 || args.length >= 4)
			  {
					HelpMessage(player);
			  		return;
			  }
			  switch(ChatColor.stripColor(args[0]))
			  {
		  			case "���" :
		  			{
		  				GBD_RPG.CustomItem.CustomItem_GUI IGUI = new GBD_RPG.CustomItem.CustomItem_GUI();
		  				IGUI.ItemListGUI(player,0);
		  			}
		  			return;
			  }
		  }
		  else
		  {
			  talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			  new GBD_RPG.Effect.Effect_Sound().SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			  return;
		  }
	}

	public void onCommand2(CommandSender talker, Command command, String string, String[] args)
	{
		Player player = (Player) talker;
		if(talker.isOp() == true)
		{
			String s="";
			switch(ChatColor.stripColor(args[0]))
			{
				case "�±׻���" :
				{
					ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
					itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
					itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
					itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
					itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
					player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
				}
				return;
				case "ID" :
				{
			  		if(args.length != 2)
					{
						HelpMessage(player);
						return;
					}
				  	SettingItemMeta(player, (byte) 0, Integer.parseInt(args[1]));
				}
				return;
				case "DATA" :
				{
			  		if(args.length != 2)
					{
						HelpMessage(player);
						return;
					}
				  	SettingItemMeta(player, (byte) 1, Integer.parseInt(args[1]));
				}
				return;
				case "����" :
				{
			  		if(args.length != 2)
					{
						HelpMessage(player);
						return;
					}
				  	SettingItemMeta(player, (byte) 2, Integer.parseInt(args[1]));
				}
				return;
				case "�̸�" :
				{
			  		if(args.length < 2)
					{
						HelpMessage(player);
						return;
					}
	  				if(args.length >= 2)
	  				{
	  					s = args[1];
	  					for(byte a = 2; a<= ((args.length)-1);a++)
	  						s = s+" "+args[a];
	  				}
				  	SettingItemMeta(player, (byte) 0, s);
				}
				return;
				case "�����߰�" :
				{
			  		if(args.length < 2)
					{
						HelpMessage(player);
						return;
					}
	  				if(args.length >= 2)
	  				{
	  					s = args[1];
	  					for(byte a = 2; a<= ((args.length)-1);a++)
	  						s = s+" "+args[a];
	  				}
				  	SettingItemMeta(player, (byte) 1, s);
				}
				return;
				case "��������" :
				{
					SettingItemMeta(player, (byte) 2, null);
				}
				return;
				case "����" :
				{
					if(player.getInventory().getItemInMainHand().getType() == Material.AIR)
					{
						GBD_RPG.Effect.Effect_Sound sound = new GBD_RPG.Effect.Effect_Sound();
						sound.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
						player.sendMessage(ChatColor.RED + "[SYSTEM] : �տ� ������ �������� ��� �־�� �մϴ�!");
						return;
					}
					player.getInventory().getItemInMainHand().setDurability((short)-player.getInventory().getItemInMainHand().getType().getMaxDurability());
					ItemStack item = player.getInventory().getItemInMainHand();
					if(item.hasItemMeta() == true)
					{
						if(item.getItemMeta().hasLore() == true)
						{
							for(short count = 0; count < item.getItemMeta().getLore().size(); count++)
							{
								ItemMeta Meta = item.getItemMeta();
								if(Meta.getLore().get(count).contains("������") == true)
								{
									String[] Lore = Meta.getLore().get(count).split(" : ");
									String[] SubLore = Lore[1].split(" / ");
									List<String> PLore = Meta.getLore();
									PLore.set(count, Lore[0] + " : "+SubLore[1]+" / "+SubLore[1]);
									Meta.setLore(PLore);
									item.setItemMeta(Meta);
								}
							}
						}
					}
				}
				return;
				case "����" :
					return;
				case "��æƮ" :
					return;
				default:
				{
					HelpMessage(player);
				}
			  	return;
			}
		}
		else
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			new GBD_RPG.Effect.Effect_Sound().SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
	}
	
	public void SettingItemMeta(Player player, byte type, int value)
	{
		if(player.isOp() == false)
		{
			new GBD_RPG.Effect.Effect_Sound().SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;
		}
		if(player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getTypeId() == 0 )
		{
			new GBD_RPG.Effect.Effect_Sound().SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage(ChatColor.RED + "[SYSTEM] : �տ� �������� ��� �־�� �մϴ�!");
			return;
		}
		else
		{
			ItemStack item = player.getInventory().getItemInMainHand();
			switch(type)
			{
			case 0:
				{
					item.setTypeId(value);
					player.getInventory().setItemInMainHand(item);
				}
				break;
			case 1:
				{
					ItemStack it = new MaterialData(item.getTypeId(), (byte) value).toItemStack();
					it.setAmount(item.getAmount());
					if(item.hasItemMeta() == true)
						it.setItemMeta(item.getItemMeta());
					player.getInventory().setItemInMainHand(it);
				}
				break;
			case 2:
				{
					if(value >= 127) value = 127;
					if(value <= 0) value = 1;
					item.setAmount(value);
				}
				break;
			}
		}
		return;
	}
	
	public void SettingItemMeta(Player player, byte type, String value)
	{
		if(player.isOp() == false)
		{
			new GBD_RPG.Effect.Effect_Sound().SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;
		}
		if(player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getTypeId() == 0 )
		{
			new GBD_RPG.Effect.Effect_Sound().SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage(ChatColor.RED + "[SYSTEM] : �տ� �������� ��� �־�� �մϴ�!");
			return;
		}
		else
		{
			ItemStack item = player.getInventory().getItemInMainHand();
			ItemMeta itemMeta = item.getItemMeta();
			List<String> Lore = Arrays.asList();
			switch(type)
			{
			case 0:
				{
					value = ChatColor.WHITE + value;
					itemMeta.setDisplayName(value);
					item.setItemMeta(itemMeta);
					player.getInventory().setItemInMainHand(item);
				}
				break;
			case 1:
				{
					value = ChatColor.WHITE + value;
					if(itemMeta.hasLore() == false)
						itemMeta.setLore(Arrays.asList(value));
					else
					{
						Lore = itemMeta.getLore();
						Lore.add(Lore.size(), value);
						itemMeta.setLore(Lore);
					}
					item.setItemMeta(itemMeta);
					player.getInventory().setItemInMainHand(item);
				}
				break;
			case 2:
				{
					if(itemMeta.hasLore() == false)
					{
						return;
					}
					else
					{
						Lore = itemMeta.getLore();
						for(byte repeat = 0; repeat < 5; repeat++)
							for(byte count=0;count<Lore.size();count++)
								Lore.remove(count);
						itemMeta.setLore(Lore);
					}
					item.setItemMeta(itemMeta);
					player.getInventory().setItemInMainHand(item);
				}
				break;
			}
		}
		return;
	}

	public void HelpMessage(Player player)
	{
		player.sendMessage(ChatColor.YELLOW+"������������������������[������ ��ɾ�]������������������������");
		player.sendMessage(ChatColor.GOLD + "/������ ����" + ChatColor.YELLOW + " - �տ� ����ִ� �������� �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ �̸� <���ڿ�>" + ChatColor.YELLOW + " - �ش� �������� ������ �̸��� �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ ID <����>" + ChatColor.YELLOW + " - �ش� �������� ID���� �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ DATA <����>" + ChatColor.YELLOW + " - �ش� �������� DATA���� �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ ���� <����>" + ChatColor.YELLOW + " - �ش� �������� ������ �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ �±׻���" + ChatColor.YELLOW + " - [1.8.0�� �ȵ�] ���̾Ƹ�� ���� +7 �������ؿ� ���� ������ �±׸� �����մϴ�.");
		//player.sendMessage(ChatColor.GOLD + "/������ ���� [ȿ��] [����]" + ChatColor.YELLOW + " - �����ۿ� ���� ȿ���� ���մϴ�.");
		//player.sendMessage(ChatColor.GOLD + "/������ ��æƮ [��æƮ] [����]" + ChatColor.YELLOW + " - �����ۿ� ��æƮ ȿ���� ���մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ �����߰� <���ڿ�>" + ChatColor.YELLOW + " - �ش� �������� �ξ� �� ���� �߰��մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/������ ��������" + ChatColor.YELLOW + " - �ش� �������� ��� �ξ �����մϴ�.");
		player.sendMessage(ChatColor.GREEN + "[�Ʒ��� ���� ������ �߰��� ��, �����ۿ� ȿ���� ����ϴ�.]");
		player.sendMessage(ChatColor.AQUA + "/������ �����߰� "+GBD_RPG.Main_Main.Main_ServerOption.Damage+" : 3 ~ 6" + ChatColor.RED +" (������ ������ "+GBD_RPG.Main_Main.Main_ServerOption.Damage+" 3 ~ 6 ���)");
		player.sendMessage(ChatColor.AQUA + "/������ �����߰� ��� : 3" + ChatColor.RED +" (������ ������ ��� 3���)");
		player.sendMessage(ChatColor.GREEN + "[�߰� ������ �ɼ� �±�]");
		player.sendMessage(ChatColor.AQUA + "["+GBD_RPG.Main_Main.Main_ServerOption.Damage+" : <����> ~ <����>] [��� : <����>] [��ȣ : <����>]\n"
				+ "["+GBD_RPG.Main_Main.Main_ServerOption.MagicDamage+" : <����> ~ <����>] [���� ��� : <����>] [���� ��ȣ : <����>]\n"
				+ "["+GBD_RPG.Main_Main.Main_ServerOption.STR+" : <����>] ["+GBD_RPG.Main_Main.Main_ServerOption.DEX+" : <����>] ["+GBD_RPG.Main_Main.Main_ServerOption.INT+" : <����>] ["+GBD_RPG.Main_Main.Main_ServerOption.WILL+" : <����>] ["+GBD_RPG.Main_Main.Main_ServerOption.LUK+" : <����>]\n"
				+ "[ũ��Ƽ�� : <����>] [�뷱�� : <����>] [������ : <����> / <����>] \n"
				+ "[���׷��̵� : <����> / <����>] [����� : <����>] [���� : <����>]\n"+ChatColor.GREEN+"[������ Ÿ�� �±�] - ������ ��ϵ� ��ų�� ���� ���� �ɼǿ� ����"+ChatColor.AQUA+"\n[�Һ�] [���� ����] [�Ѽ� ��] [��� ��] [���Ÿ� ����] [Ȱ] [����] [����] [��] [���� ����] [����] [������]");
		player.sendMessage(ChatColor.YELLOW+"����������������������������������������������������������������");
	}
}
