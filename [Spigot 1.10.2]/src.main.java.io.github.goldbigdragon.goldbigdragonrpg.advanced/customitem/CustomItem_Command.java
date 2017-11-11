package customitem;

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

import effect.SoundEffect;

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
		  				customitem.CustomItem_GUI IGUI = new customitem.CustomItem_GUI();
		  				IGUI.ItemListGUI(player,0);
		  			}
		  			return;
			  }
		  }
		  else
		  {
			  talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			  SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
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
	  					for(int a = 2; a<= ((args.length)-1);a++)
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
	  					for(int a = 2; a<= ((args.length)-1);a++)
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
						SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
						player.sendMessage("��c[SYSTEM] : �տ� ������ �������� ��� �־�� �մϴ�!");
						return;
					}
					player.getInventory().getItemInMainHand().setDurability((short)-player.getInventory().getItemInMainHand().getType().getMaxDurability());
					ItemStack item = player.getInventory().getItemInMainHand();
					if(item.hasItemMeta() == true)
					{
						if(item.getItemMeta().hasLore() == true)
						{
							for(int count = 0; count < item.getItemMeta().getLore().size(); count++)
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
			talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
	}
	
	public void SettingItemMeta(Player player, byte type, int value)
	{
		if(player.isOp() == false)
		{
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;
		}
		if(player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getTypeId() == 0 )
		{
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �տ� �������� ��� �־�� �մϴ�!");
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
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;
		}
		if(player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getTypeId() == 0 )
		{
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �տ� �������� ��� �־�� �մϴ�!");
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
					value = "��f"+ value;
					itemMeta.setDisplayName(value);
					item.setItemMeta(itemMeta);
					player.getInventory().setItemInMainHand(item);
				}
				break;
			case 1:
				{
					value = "��f"+ value;
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
						for(int repeat = 0; repeat < 5; repeat++)
							for(int count=0;count<Lore.size();count++)
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
		player.sendMessage("��e������������������������[������ ��ɾ�]������������������������");
		player.sendMessage("��6/������ ������e - �տ� ����ִ� �������� �����մϴ�.");
		player.sendMessage("��6/������ �̸� <���ڿ�>��e - �ش� �������� ������ �̸��� �����մϴ�.");
		player.sendMessage("��6/������ ID <����>��e - �ش� �������� ID���� �����մϴ�.");
		player.sendMessage("��6/������ DATA <����>��e - �ش� �������� DATA���� �����մϴ�.");
		player.sendMessage("��6/������ ���� <����>��e - �ش� �������� ������ �����մϴ�.");
		player.sendMessage("��6/������ �±׻�����e - [1.8.0�� �ȵ�] ���̾Ƹ�� ���� +7 �������ؿ� ���� ������ �±׸� �����մϴ�.");
		//player.sendMessage("��6/������ ���� [ȿ��] [����]��e - �����ۿ� ���� ȿ���� ���մϴ�.");
		//player.sendMessage("��6/������ ��æƮ [��æƮ] [����]��e - �����ۿ� ��æƮ ȿ���� ���մϴ�.");
		player.sendMessage("��6/������ �����߰� <���ڿ�>��e - �ش� �������� �ξ� �� ���� �߰��մϴ�.");
		player.sendMessage("��6/������ �������š�e - �ش� �������� ��� �ξ �����մϴ�.");
		player.sendMessage("��a[�Ʒ��� ���� ������ �߰��� ��, �����ۿ� ȿ���� ����ϴ�.]");
		player.sendMessage("��b/������ �����߰� "+main.Main_ServerOption.damage+" : 3 ~ 6��c (������ ������ "+main.Main_ServerOption.damage+" 3 ~ 6 ���)");
		player.sendMessage("��b/������ �����߰� ��� : 3��c (������ ������ ��� 3���)");
		player.sendMessage("��a[�߰� ������ �ɼ� �±�]");
		player.sendMessage("��b["+main.Main_ServerOption.damage+" : <����> ~ <����>] [��� : <����>] [��ȣ : <����>]\n"
				+ "["+main.Main_ServerOption.magicDamage+" : <����> ~ <����>] [���� ��� : <����>] [���� ��ȣ : <����>]\n"
				+ "["+main.Main_ServerOption.statSTR+" : <����>] ["+main.Main_ServerOption.statDEX+" : <����>] ["+main.Main_ServerOption.statINT+" : <����>] ["+main.Main_ServerOption.statWILL+" : <����>] ["+main.Main_ServerOption.statLUK+" : <����>]\n"
				+ "[ũ��Ƽ�� : <����>] [�뷱�� : <����>] [������ : <����> / <����>] \n"
				+ "[���׷��̵� : <����> / <����>] [����� : <����>] [���� : <����>]\n��a[������ Ÿ�� �±�] - ������ ��ϵ� ��ų�� ���� ���� �ɼǿ� ������b\n[�Һ�] [���� ����] [�Ѽ� ��] [��� ��] [���Ÿ� ����] [Ȱ] [����] [����] [��] [���� ����] [����] [������]");
		player.sendMessage("��e����������������������������������������������������������������");
	}
}
