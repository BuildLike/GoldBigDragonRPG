package GBD_RPG.NPC;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class NPC_Command
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) talker;
		if(player.isOp() == false)
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			s.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		if(args.length < 1)
		{
			HelpMessage(player);
			return;
		}
		if(player.getInventory().getItemInMainHand().getType() == Material.AIR || player.getInventory().getItemInMainHand().getTypeId() == 0 || player.getInventory().getItemInMainHand().getAmount() == 0)
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : ������ ����� �������� ��� �־�� �մϴ�!");
			s.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		List<Entity> NearbyEntity = player.getNearbyEntities(3.0, 3.0, 3.0);
		
		for(short count = 0; count <NearbyEntity.size(); count++)
		{
			if(CitizensAPI.getNPCRegistry().isNPC(NearbyEntity.get(count))==true)
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				NPC npc = CitizensAPI.getNPCRegistry().getNPC(NearbyEntity.get(count));
				if(YC.isExit("NPC/NPCData/"+ npc.getUniqueId().toString() +".yml") == true)
				{
					YamlManager NPCscript = YC.getNewConfig("NPC/NPCData/"+ npc.getUniqueId().toString() +".yml");
					int directory = 0;
					switch(ChatColor.stripColor(args[0]))
					{
					case "�Ǹ�":
						{
							if(isIntMinMax(args[1], player, 0, Integer.MAX_VALUE))
							{
								directory = NPCscript.getConfigurationSection("Shop.Sell").getKeys(false).toArray().length;
								NPCscript.set("Shop.Sell."+directory+".item", player.getInventory().getItemInMainHand());
								NPCscript.set("Shop.Sell."+directory+".price", Integer.parseInt(args[1]));
								NPCscript.saveConfig();
								talker.sendMessage(ChatColor.GREEN +"["+ NearbyEntity.get(count).getCustomName()+"] : ������ ��ǰ�� ����Ͽ����ϴ�.");
								s.SP((Player)talker, org.bukkit.Sound.BLOCK_CHEST_OPEN, 2.0F, 0.8F);
							}
						}
						return;
					case "����":
						{
							if(isIntMinMax(args[1], player, 0, Integer.MAX_VALUE))
							{
								directory = NPCscript.getConfigurationSection("Shop.Buy").getKeys(false).toArray().length;
								NPCscript.set("Shop.Buy."+directory+".item", player.getInventory().getItemInMainHand());
								NPCscript.set("Shop.Buy."+directory+".price", Integer.parseInt(args[1]));
								NPCscript.saveConfig();
								talker.sendMessage(ChatColor.GREEN +"["+ NearbyEntity.get(count).getCustomName()+"] : �����ֽ� ��ǰ�� "+args[1]+GBD_RPG.Main_Main.Main_ServerOption.Money+ChatColor.GREEN+"�� �� ���̰ڽ��ϴ�.");
								s.SP((Player)talker, org.bukkit.Sound.BLOCK_CHEST_OPEN, 2.0F, 0.8F);
							}
						}
						return;
					}
				}	
			}
		}
		player.sendMessage(ChatColor.RED + "[SYSTEM] : NPC�� ã�� �� �����ϴ�!");
		s.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		return;
	}
	
	private boolean isIntMinMax(String message,Player player, int Min, int Max)
	{
		GBD_RPG.Effect.Effect_Sound sound = new GBD_RPG.Effect.Effect_Sound();
		try
		{
			if(message.split(" ").length <= 1 && Integer.parseInt(message) >= Min&& Integer.parseInt(message) <= Max)
				return true;
			else
			{
				player.sendMessage(ChatColor.RED + "[SYSTEM] : �ּ� "+ChatColor.YELLOW +""+Min+ChatColor.RED+", �ִ� "+ChatColor.YELLOW+""+Max+ChatColor.RED+" ������ ���ڸ� �Է��ϼ���!");
				sound.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
		}
		catch(NumberFormatException e)
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���. ("+ChatColor.YELLOW +""+Min+ChatColor.RED+" ~ "+ChatColor.YELLOW+""+Max+ChatColor.RED+")");
				sound.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
		return false;
	}
	
	private void HelpMessage(Player player)
	{
		player.sendMessage(ChatColor.YELLOW+"������������������������[���� ���� ��ɾ�]������������������������");
		player.sendMessage(ChatColor.GOLD + "/���� �Ǹ� [����]" + ChatColor.YELLOW + " - ��ó�� �ִ� NPC���� ����� �տ��� ������ �ش� ���ݿ� �Ǹ��ϵ��� �մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� ���� [����]" + ChatColor.YELLOW + " - ��ó�� �ִ� NPC���� ����� �տ��� ������ �ش� ���ݿ� �����ϵ��� �մϴ�.");
		player.sendMessage(ChatColor.YELLOW+"����������������������������������������������������������������");
	}
}