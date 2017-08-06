package npc;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import effect.SoundEffect;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import util.YamlLoader;



public class NPC_Command
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    
		Player player = (Player) talker;
		if(player.isOp() == false)
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
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
			SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		List<Entity> NearbyEntity = player.getNearbyEntities(3.0, 3.0, 3.0);
		
		for(int count = 0; count <NearbyEntity.size(); count++)
		{
			if(CitizensAPI.getNPCRegistry().isNPC(NearbyEntity.get(count))==true)
			{
			  	YamlLoader npcScriptYaml = new YamlLoader();
				NPC npc = CitizensAPI.getNPCRegistry().getNPC(NearbyEntity.get(count));
				if(npcScriptYaml.isExit("NPC/NPCData/"+ npc.getUniqueId().toString() +".yml") == true)
				{
					npcScriptYaml.getConfig("NPC/NPCData/"+ npc.getUniqueId().toString() +".yml");
					int directory = 0;
					switch(ChatColor.stripColor(args[0]))
					{
					case "�Ǹ�":
						{
							if(isIntMinMax(args[1], player, 0, Integer.MAX_VALUE))
							{
								directory = npcScriptYaml.getConfigurationSection("Shop.Sell").getKeys(false).toArray().length;
								npcScriptYaml.set("Shop.Sell."+directory+".item", player.getInventory().getItemInMainHand());
								npcScriptYaml.set("Shop.Sell."+directory+".price", Integer.parseInt(args[1]));
								npcScriptYaml.saveConfig();
								talker.sendMessage(ChatColor.GREEN +"["+ NearbyEntity.get(count).getCustomName()+"] : ������ ��ǰ�� ����Ͽ����ϴ�.");
								SoundEffect.SP((Player)talker, org.bukkit.Sound.BLOCK_CHEST_OPEN, 2.0F, 0.8F);
							}
						}
						return;
					case "����":
						{
							if(isIntMinMax(args[1], player, 0, Integer.MAX_VALUE))
							{
								directory = npcScriptYaml.getConfigurationSection("Shop.Buy").getKeys(false).toArray().length;
								npcScriptYaml.set("Shop.Buy."+directory+".item", player.getInventory().getItemInMainHand());
								npcScriptYaml.set("Shop.Buy."+directory+".price", Integer.parseInt(args[1]));
								npcScriptYaml.saveConfig();
								talker.sendMessage(ChatColor.GREEN +"["+ NearbyEntity.get(count).getCustomName()+"] : �����ֽ� ��ǰ�� "+args[1]+main.Main_ServerOption.Money+ChatColor.GREEN+"�� �� ���̰ڽ��ϴ�.");
								SoundEffect.SP((Player)talker, org.bukkit.Sound.BLOCK_CHEST_OPEN, 2.0F, 0.8F);
							}
						}
						return;
					}
				}	
			}
		}
		player.sendMessage(ChatColor.RED + "[SYSTEM] : NPC�� ã�� �� �����ϴ�!");
		SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		return;
	}
	
	private boolean isIntMinMax(String message,Player player, int Min, int Max)
	{
		try
		{
			if(message.split(" ").length <= 1 && Integer.parseInt(message) >= Min&& Integer.parseInt(message) <= Max)
				return true;
			else
			{
				player.sendMessage(ChatColor.RED + "[SYSTEM] : �ּ� "+ChatColor.YELLOW +""+Min+ChatColor.RED+", �ִ� "+ChatColor.YELLOW+""+Max+ChatColor.RED+" ������ ���ڸ� �Է��ϼ���!");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
		}
		catch(NumberFormatException e)
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���. ("+ChatColor.YELLOW +""+Min+ChatColor.RED+" ~ "+ChatColor.YELLOW+""+Max+ChatColor.RED+")");
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
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