package warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import effect.PottionBuff;
import effect.SoundEffect;
import util.YamlLoader;

public class WarpMain
{
	public void CreateNewTeleportSpot(Player player, String TeleportName)
	{
		if(player.isOp() == false)
		{
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;
		}
		else
		{
		  	YamlLoader TeleportList = new YamlLoader();
			TeleportList.getConfig("Teleport/TeleportList.yml");

			if(TeleportList.contains(TeleportName))
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				player.sendMessage("��c[SYSTEM] : �ش� ���� ������ �̹� ��ϵǾ� �ֽ��ϴ�!");
				return;
			}
			
			TeleportList.set(TeleportName+".World", player.getLocation().getWorld().getName());
			TeleportList.set(TeleportName+".X", player.getLocation().getX());
			TeleportList.set(TeleportName+".Y", player.getLocation().getY());
			TeleportList.set(TeleportName+".Z", player.getLocation().getZ());
			TeleportList.set(TeleportName+".Pitch", player.getLocation().getPitch());
			TeleportList.set(TeleportName+".Yaw", player.getLocation().getYaw());
			TeleportList.set(TeleportName+".OnlyOpUse", true);
			TeleportList.saveConfig();
			
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_CHICKEN_EGG, 2.0F, 1.7F);
			player.sendMessage("��a[SYSTEM] : ���� ��ġ�� ���� ������ ��ϵǾ����ϴ�!");
			
			return;
	      }
	}
	
	public void setTeleportPermission(Player player, String TeleportSpotName)
	{
		if(player.isOp() == true)
		{
		  	YamlLoader TeleportList = new YamlLoader();
			TeleportList.getConfig("Teleport/TeleportList.yml");
			if(TeleportList.contains(TeleportSpotName))
			{
				if(TeleportList.getBoolean(TeleportSpotName+".OnlyOpUse") == true)
				{
					TeleportList.set(TeleportSpotName+".OnlyOpUse", false);
					player.sendMessage("��a[SYSTEM] : �ش� ������ �Ϲ� ������ ������ �� �ְ� �Ǿ����ϴ�!");
				}
				else
				{
					TeleportList.set(TeleportSpotName+".OnlyOpUse", true);
					player.sendMessage("��c[SYSTEM] : �ش� ������ �Ϲ� ������ ������ �� ���� �Ǿ����ϴ�!");
					
				}
				TeleportList.saveConfig();
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_CHICKEN_EGG, 2.0F, 1.7F);
			}
			else
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  		player.sendMessage("��c[SYSTEM] : �ش� �̸����� ��ϵ� ���� ������ �����ϴ�!");
			}
	  		return;
		}
		else
		{
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;	
		}
	}
	
	
	public void RemoveTeleportList(Player player, String TeleportName)
	{
		if(player.isOp() == false)
		{
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			return;
		}
		else
		{
		  	YamlLoader TeleportList = new YamlLoader();
			TeleportList.getConfig("Teleport/TeleportList.yml");

			if(TeleportList.contains(TeleportName))
			{
				TeleportList.removeKey(TeleportName+".World");
				TeleportList.removeKey(TeleportName+".X");
				TeleportList.removeKey(TeleportName+".Y");
				TeleportList.removeKey(TeleportName+".Z");
				TeleportList.removeKey(TeleportName+".Pitch");
				TeleportList.removeKey(TeleportName+".Yaw");
				TeleportList.removeKey(TeleportName+".OnlyOpUse");
				TeleportList.removeKey(TeleportName+"");
				TeleportList.saveConfig();

				SoundEffect.playSound(player,org.bukkit.Sound.ENTITY_ITEM_PICKUP,0.7F,1.0F);
	    		player.sendMessage("��a[SYSTEM] : ��e"+TeleportName+"��a ���� ������ ���������� �����Ͽ����ϴ�!");
			}
			else
			{
				SoundEffect.playSound(player,org.bukkit.Sound.ENTITY_ITEM_BREAK,0.7F,1.0F);
		  		player.sendMessage("��c[SYSTEM] : �ش� �̸����� ��ϵ� ���� ������ �����ϴ�!");
			}
		}
		return;
	}
	
	public void TeleportUser(Player player, String TeleportSpotName)
	{

	  	YamlLoader TeleportList = new YamlLoader();
		TeleportList.getConfig("Teleport/TeleportList.yml");

		if(TeleportList.contains(TeleportSpotName))
		{
			if(TeleportList.getBoolean(TeleportSpotName+".OnlyOpUse")== true  && player.isOp() == false)
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				player.sendMessage("��c[SYSTEM] : �ش� ������ ���ѵ� �����Դϴ�!");
				return;
			}
			else
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
				Location loc = new Location(Bukkit.getWorld(TeleportList.getString(TeleportSpotName+".World")), TeleportList.getInt(TeleportSpotName+".X")+0.5,  TeleportList.getInt(TeleportSpotName+".Y")+0.5,  TeleportList.getInt(TeleportSpotName+".Z")+0.5,  TeleportList.getInt(TeleportSpotName+".Yaw"),  TeleportList.getInt(TeleportSpotName+".Pitch"));
				player.teleport(loc);
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
				PottionBuff.givePotionEffect(player, PotionEffectType.BLINDNESS, 1, 15);
				return;
			}
		}
		for(int counter=0;counter<Bukkit.getServer().getWorlds().size();counter++)
		{
			if(Bukkit.getServer().getWorlds().get(counter).getName().equalsIgnoreCase(TeleportSpotName))
			{
				if(player.isOp()==true)
				{
					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
					player.teleport(Bukkit.getServer().getWorld(TeleportSpotName).getSpawnLocation());
					PottionBuff.givePotionEffect(player, PotionEffectType.BLINDNESS, 1, 15);
					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
				}
				else
				{
					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					player.sendMessage("��c[SYSTEM] : ���尣 �̵��� �����ڸ� ���˴ϴ�!");
				}
				return;
			}
		}
		SoundEffect.playSound(player,org.bukkit.Sound.ENTITY_ITEM_BREAK,0.7F,1.0F);
  		player.sendMessage("��c[SYSTEM] : �ش� �̸����� ��ϵ� ���� ������ �����ϴ�!");
		return;
	}
}
