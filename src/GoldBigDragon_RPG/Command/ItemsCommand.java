package GoldBigDragon_RPG.Command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsCommand extends HelpMessage
{
	public void onCommand1(CommandSender talker, Command command, String string, String[] args)
	{
		Player player = (Player) talker;
		  if(talker.isOp() == true)
		  {
			  if(args.length == 0 || args.length >= 4)
			  {
					HelpMessager((Player)talker,1);
			  		return;
			  }
			  switch(ChatColor.stripColor(args[0]))
			  {
		  			case "���" :
		  			{
		  				GoldBigDragon_RPG.GUI.ItemGUI IGUI = new GoldBigDragon_RPG.GUI.ItemGUI();
		  				IGUI.ItemListGUI(player,0);
		  			}
		  			return;
			  }
		  }
		  else
		  {
			  talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			  new GoldBigDragon_RPG.Effect.Sound().SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
			  return;
		  }
	}

	public void onCommand2(CommandSender talker, Command command, String string, String[] args)
	{
		GoldBigDragon_RPG.ETC.Items I = new GoldBigDragon_RPG.ETC.Items();
		Player player = (Player) talker;
		if(talker.isOp() == true)
		{
			String s="";
			switch(ChatColor.stripColor(args[0]))
			{
				case "�±׻���" :
				{
					ItemMeta itemMeta = player.getItemInHand().getItemMeta();
					itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
					itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
					itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
					itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
					player.getItemInHand().setItemMeta(itemMeta);
				}
				return;
				case "ID" :
				{
			  		if(args.length != 2)
					{
						HelpMessager((Player)talker,1);
						return;
					}
				  	I.SettingItemMeta(player, 0, Integer.parseInt(args[1]));
				}
				return;
				case "DATA" :
				{
			  		if(args.length != 2)
					{
						HelpMessager((Player)talker,1);
						return;
					}
				  	I.SettingItemMeta(player, 1, Integer.parseInt(args[1]));
				}
				return;
				case "����" :
				{
			  		if(args.length != 2)
					{
						HelpMessager((Player)talker,1);
						return;
					}
				  	I.SettingItemMeta(player, 2, Integer.parseInt(args[1]));
				}
				return;
				case "�̸�" :
				{
			  		if(args.length < 2)
					{
						HelpMessager((Player)talker,1);
						return;
					}
	  				if(args.length >= 2)
	  				{
	  					s = args[1];
	  					for(int a = 2; a<= ((args.length)-1);a++)
	  						s = s+" "+args[a];
	  				}
				  	I.SettingItemMeta(player, 0, s);
				}
				return;
				case "�����߰�" :
				{
			  		if(args.length < 2)
					{
						HelpMessager((Player)talker,1);
						return;
					}
	  				if(args.length >= 2)
	  				{
	  					s = args[1];
	  					for(int a = 2; a<= ((args.length)-1);a++)
	  						s = s+" "+args[a];
	  				}
				  	I.SettingItemMeta(player, 1, s);
				}
				return;
				case "��������" :
				{
					I.SettingItemMeta(player, 2, null);
				}
				return;
				case "����" :
				{
					if(player.getItemInHand().getType() == Material.AIR)
					{
						GoldBigDragon_RPG.Effect.Sound sound = new GoldBigDragon_RPG.Effect.Sound();
						sound.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
						player.sendMessage(ChatColor.RED + "[SYSTEM] : �տ� ������ �������� ��� �־�� �մϴ�!");
						return;
					}
					player.getItemInHand().setDurability((short)-player.getItemInHand().getType().getMaxDurability());
					ItemStack item = player.getInventory().getItemInHand();
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
					HelpMessager((Player)talker,1);
				}
			  	return;
			}
		}
		else
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			new GoldBigDragon_RPG.Effect.Sound().SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
	}
	
}
