package corpse;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import effect.PottionBuff;
import effect.SoundEffect;
import main.Main_ServerOption;
import util.Util_GUI;
import util.YamlLoader;



public class Corpse_GUI extends Util_GUI
{
	public void SetNormal(String path, String normalValue)
	{
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
	  	if(configYaml.getString(path)==null)
	  	{
  			configYaml.set(path, normalValue);
  			configYaml.saveConfig();
  			return;
	  	}
	  	if(configYaml.getString(path).contains("%"))
	  	{
	  		try
	  		{
	  			byte value = Byte.parseByte(configYaml.getString(path).replace("%", ""));
	  			if(value > 100||value<0)
	  			{
		  			configYaml.set(path, normalValue);
		  			configYaml.saveConfig();
	  			}
	  		}
	  		catch(Exception e)
	  		{
	  			configYaml.set(path, normalValue);
	  			configYaml.saveConfig();
	  		}
	  	}
	}
	
	
	public void OpenReviveSelectGUI(Player player)
	{
		if(player.getOpenInventory().getTitle().compareTo("��0��0��9��0��0��r��0�ൿ�Ҵ� ���� -��Ȱ ��� ����-")!=0)
		{
			String UniqueCode = "��0��0��9��0��0��r";
			Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0�ൿ�Ҵ� ���� -��Ȱ ��� ����-");

		  	YamlLoader configYaml = new YamlLoader();
		  	configYaml.getConfig("config.yml");

		  	SetNormal("Death.Spawn_Home.SetHealth", "100%");
		  	SetNormal("Death.Spawn_Home.PenaltyEXP", "10%");
		  	SetNormal("Death.Spawn_Home.PenaltyMoney", "0%");

		  	SetNormal("Death.Spawn_Here.SetHealth", "1%");
		  	SetNormal("Death.Spawn_Here.PenaltyEXP", "15%");
		  	SetNormal("Death.Spawn_Here.PenaltyMoney", "10%");
		  	
		  	SetNormal("Death.Spawn_Help.SetHealth", "1%");
		  	SetNormal("Death.Spawn_Help.PenaltyEXP", "5%");
		  	SetNormal("Death.Spawn_Help.PenaltyMoney", "0%");
		  	
		  	SetNormal("Death.Spawn_Item.SetHealth", "100%");
		  	SetNormal("Death.Spawn_Item.PenaltyEXP", "0%");
		  	SetNormal("Death.Spawn_Item.PenaltyMoney", "0%");
		  	
		  	try
		  	{
				if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_LastVisited()==null||main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_LastVisited().compareTo("null")==0)
					Stack2("��6��l[����� �������� ��Ȱ]", 345,0,1,Arrays.asList(ChatColor.GRAY + "�ֱ� �湮�� ������ �����ϴ�.",ChatColor.GRAY + "�� ����� ������ ���,",ChatColor.GRAY + player.getLocation().getWorld().getName()+"���忡 ������",ChatColor.GRAY + "�⺻ ���� �������� ��Ȱ�մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Home.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Home.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Home.PenaltyMoney")+" ����"), 10, inv);
				else
					Stack2("��6��l[����� �������� ��Ȱ]", 345,0,1,Arrays.asList(ChatColor.YELLOW + main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_LastVisited()+ChatColor.GRAY +"���� ��Ȱ�մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Home.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Home.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Home.PenaltyMoney")+" ����"), 10, inv);
		  	}
		  	catch(NullPointerException e)
		  	{
		  		Stack2("��6��l[����� �������� ��Ȱ]", 345,0,1,Arrays.asList(ChatColor.GRAY + "�ֱ� �湮�� ������ �����ϴ�.",ChatColor.GRAY + "�� ����� ������ ���,",ChatColor.GRAY + player.getLocation().getWorld().getName()+"���忡 ������",ChatColor.GRAY + "�⺻ ���� �������� ��Ȱ�մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Home.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Home.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Home.PenaltyMoney")+" ����"), 10, inv);
		  	}
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_EXP()<0)
				Stack2("��c��l[�ٽ� �Ͼ��]", 166,0,1,Arrays.asList(ChatColor.GRAY + "����ġ�� �����Ͽ� ���ڸ�",ChatColor.GRAY + "��Ȱ�� �Ұ��� �մϴ�."), 12, inv);
			else if(configYaml.getBoolean("Death.DistrictDirectRevive"))
				Stack2("��c��l[�ٽ� �Ͼ��]", 166,0,1,Arrays.asList(ChatColor.GRAY + "���ڸ� ��Ȱ�� �Ұ����մϴ�."), 12, inv);
			else
				Stack2("��c��l[�ٽ� �Ͼ��]", 2266,0,1,Arrays.asList(ChatColor.GRAY + "������ ���� �ٽ� �Ͼ�ϴ�.",ChatColor.GRAY + "���� ���´� ����ϱ� ����ϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Here.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Here.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Here.PenaltyMoney")+" ����"), 12, inv);
			
			ItemStack item = configYaml.getItemStack("Death.RescueItem");
			
			if(item == null)
				Stack2("��c��l[������ ��ٸ���]", 397,3,1,Arrays.asList(ChatColor.GRAY + "�ٸ� ����� ������ ��û�մϴ�.",ChatColor.GRAY + "������ ����� �ִ��� ���캸����.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Help.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Help.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Help.PenaltyMoney")+" ����"), 14, inv);
			else
				Stack2("��c��l[������ ��ٸ���]", item.getTypeId(),item.getData().getData(),item.getAmount(),Arrays.asList(ChatColor.GRAY + "�ٸ� ����� ������ ��û�մϴ�.",ChatColor.GRAY + "������ ����� �ִ��� ���캸����.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Help.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Help.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Help.PenaltyMoney")+" ����"), 14, inv);
			
			item = configYaml.getItemStack("Death.ReviveItem");
			if(item == null)
				Stack2("��3��l[��Ȱ�� ���]", 399,0,1,Arrays.asList(ChatColor.GRAY + "���ڸ� ��Ȱ �������� ����մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Item.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Item.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Item.PenaltyMoney")+" ����"), 16, inv);
			else if(item.hasItemMeta()==false)
				Stack2("��3��l[��Ȱ�� ���]", item.getTypeId(),item.getData().getData(),item.getAmount(),Arrays.asList(ChatColor.GRAY + "���ڸ� ��Ȱ �������� ����մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Item.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Item.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Item.PenaltyMoney")+" ����"), 16, inv);
			else if(item.getItemMeta().hasDisplayName()==false)
				Stack2("��3��l[��Ȱ�� ���]", item.getTypeId(),item.getData().getData(),item.getAmount(),Arrays.asList(ChatColor.GRAY + "���ڸ� ��Ȱ �������� ����մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Item.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Item.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Item.PenaltyMoney")+" ����"), 16, inv);
			else
				Stack2("��3��l["+item.getItemMeta().getDisplayName()+ChatColor.DARK_AQUA + "" + ChatColor.BOLD +" ���]", item.getTypeId(),item.getData().getData(),item.getAmount(),Arrays.asList(ChatColor.GRAY + "���ڸ� ��Ȱ �������� ����մϴ�.","",ChatColor.GREEN+" + "+configYaml.getString("Death.Spawn_Item.SetHealth")+" �����",ChatColor.RED+" - ����ġ "+configYaml.getString("Death.Spawn_Item.PenaltyEXP")+" ����",ChatColor.RED+" - ������ "+configYaml.getString("Death.Spawn_Item.PenaltyMoney")+" ����"), 16, inv);
			player.openInventory(inv);	
		}
	}
	
	public void ReviveSelectClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		if(slot == 10)//�������� ��Ȱ
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			ReviveAtLastVisitedArea(player);
			new Corpse_Main().RemoveCorpse(player.getName());
    		new otherplugins.NoteBlockAPIMain().Stop(player);
		}
		else if(slot == 12)//���ڸ����� ��Ȱ
		{
			if(event.getCurrentItem().getTypeId()==166)
			{
				SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
				return;
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new Corpse_Main().RemoveCorpse(player.getName());
				ReviveAtDeadPoint(player);
	    		new otherplugins.NoteBlockAPIMain().Stop(player);
			}
		}
		else if(slot == 14)//���� ��û
		{
			if(Main_ServerOption.PartyJoiner.containsKey(player))
			{
				Player[] partyMember = Main_ServerOption.Party.get(Main_ServerOption.PartyJoiner.get(player)).getMember();
				for(int count = 0; count < partyMember.length; count++)
					if(player != partyMember[count])
					{
						SoundEffect.SP(partyMember[count], Sound.ENTITY_VILLAGER_DEATH, 0.4F, 0.5F);
						partyMember[count].sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : "+ChatColor.YELLOW+player.getName()+ChatColor.LIGHT_PURPLE+"������ ���� ���� ��û�� ���Խ��ϴ�! (���� : "+player.getLocation().getWorld().getName() + ", XYZ : " + (int)(player.getLocation().getX())+","+(int)(player.getLocation().getY())+","+(int)(player.getLocation().getZ())+")");
					}
				SoundEffect.SP(player, Sound.ENTITY_WITHER_SKELETON_STEP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : ��Ƽ ����鿡�� ���� ��û ��ȣ�� ���½��ϴ�!");
			}
			else
			{
			  	YamlLoader friendYaml = new YamlLoader();
				friendYaml.getConfig("Friend/"+player.getUniqueId().toString()+".yml");
				if(friendYaml.contains("Name")==false)
				{
					SoundEffect.SP(player, Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
					player.sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : ���� ��û ��ȣ�� ���� �� �ִ� ģ���� �����ϴ�!");
				}
				else
				{
					String[] friendsList = friendYaml.getConfigurationSection("Friends").getKeys(false).toArray(new String[0]);
					if(friendsList.length == 0)
					{
						SoundEffect.SP(player, Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
						player.sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : ���� ��û ��ȣ�� ���� �� �ִ� ģ���� �����ϴ�!");
					}
					else
					{
						boolean exitFriend = false;
						for(int count = 0; count < friendsList.length; count++)
						{
							Player friend = Bukkit.getPlayer(friendsList[count]);
							if(friend!=null)
								if(friend.isOnline())
								{
									SoundEffect.SP(friend, Sound.ENTITY_VILLAGER_DEATH, 0.4F, 0.5F);
									friend.sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : "+ChatColor.YELLOW+player.getName()+ChatColor.LIGHT_PURPLE+"������ ���� ���� ��û�� ���Խ��ϴ�! (���� : "+player.getLocation().getWorld().getName() + ", XYZ : " + (int)(player.getLocation().getX())+","+(int)(player.getLocation().getY())+","+(int)(player.getLocation().getZ())+")");
									exitFriend = true;
								}
						}
						if(exitFriend)
						{
							SoundEffect.SP(player, Sound.ENTITY_SKELETON_STEP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : ������ ģ���鿡�� ���� ��û ��ȣ�� ���½��ϴ�!");
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
							player.sendMessage(ChatColor.LIGHT_PURPLE+"[���� ��û] : ���� ��û ��ȣ�� ���� �� �ִ� ģ���� �����ϴ�!");
						}
					}
				}
			}
		}
		else if(slot == 16)//��Ȱ�� ���
		{
		  	YamlLoader configYaml = new YamlLoader();
			configYaml.getConfig("config.yml");
			ItemStack item = configYaml.getItemStack("Death.ReviveItem");
			if(item == null)
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage(ChatColor.RED+"[SYSTEM] : ��Ȱ �������� ���� ��Ȱ�� �� �����ϴ�!");
				OpenReviveSelectGUI(player);
			}
			else
			{
				if(new util.Util_Player().deleteItem(player, item, item.getAmount()))
				{
					new Corpse_Main().RemoveCorpse(player.getName());
					player.setGameMode(GameMode.SURVIVAL);
					player.closeInventory();
					Location l = player.getLocation();
					l.add(0, 1, 0);
					player.teleport(l);
					for(int countta=0;countta<210;countta++)
						new effect.ParticleEffect().PL(player.getLocation(), org.bukkit.Effect.SMOKE, new util.Util_Number().RandomNum(0, 14));
					SoundEffect.SL(player.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, 0.5F, 1.8F);
		    		new otherplugins.NoteBlockAPIMain().Stop(player);
					Penalty(player, configYaml.getString("Death.Spawn_Item.SetHealth"), configYaml.getString("Death.Spawn_Item.PenaltyEXP"), configYaml.getString("Death.Spawn_Item.PenaltyMoney"));
					return;
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[SYSTEM] : ��Ȱ �������� �����Ͽ� ��Ȱ�� �� �����ϴ�!");
					return;
				}
			}
		}
		player.closeInventory();
		return;
	}
	
	
	
	public void ReviveAtLastVisitedArea(Player player)
	{
		player.setGameMode(GameMode.SURVIVAL);
	  	YamlLoader areaYaml = new YamlLoader();
		YamlLoader configYaml = new YamlLoader();
		new util.ETC().UpdatePlayerHPMP(player);

	  	configYaml.getConfig("config.yml");
		areaYaml.getConfig("Area/AreaList.yml");
		if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_LastVisited() != null)
		{
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_LastVisited().compareTo("null")==0)
				player.teleport(player.getWorld().getSpawnLocation());
			else
			{
				String respawnCity = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_LastVisited();
				String[] arealist = areaYaml.getKeys().toArray(new String[0]);
				for(int count =0; count <arealist.length;count++)
				{
					if(arealist[count].compareTo(respawnCity) == 0)
					{
						if(areaYaml.getBoolean(arealist[count]+".SpawnPoint") == true)
						{
							String world = areaYaml.getString(arealist[count]+".World");
							double X = areaYaml.getDouble(arealist[count]+".SpawnLocation.X");
							float Y = (float) areaYaml.getDouble(arealist[count]+".SpawnLocation.Y");
							double Z = areaYaml.getDouble(arealist[count]+".SpawnLocation.Z");
							double Pitch = areaYaml.getDouble(arealist[count]+".SpawnLocation.Pitch");
							double Yaw = areaYaml.getDouble(arealist[count]+".SpawnLocation.Yaw");

							Penalty(player, configYaml.getString("Death.Spawn_Home.SetHealth"), configYaml.getString("Death.Spawn_Home.PenaltyEXP"), configYaml.getString("Death.Spawn_Home.PenaltyMoney"));

							Location loc = new Location(Bukkit.getServer().getWorld(world), X, Y,Z, (float)Yaw, (float)Pitch);
							player.teleport(loc);
							PottionBuff.givePotionEffect(player, PotionEffectType.BLINDNESS, 1, 15);
							SoundEffect.SL(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
							return;
						}
					}
				}
				player.teleport(player.getWorld().getSpawnLocation());
			}
		}
		else
			player.teleport(player.getWorld().getSpawnLocation());
		Penalty(player, configYaml.getString("Death.Spawn_Home.SetHealth"), configYaml.getString("Death.Spawn_Home.PenaltyEXP"), configYaml.getString("Death.Spawn_Home.PenaltyMoney"));
	}
	
	public void ReviveAtDeadPoint(Player player)
	{
		player.setGameMode(GameMode.SURVIVAL);
	  	YamlLoader configYaml = new YamlLoader();
		new util.ETC().UpdatePlayerHPMP(player);
	  	configYaml.getConfig("config.yml");
		Penalty(player, configYaml.getString("Death.Spawn_Here.SetHealth"), configYaml.getString("Death.Spawn_Here.PenaltyEXP"), configYaml.getString("Death.Spawn_Here.PenaltyMoney"));
	}
	
	public void Penalty(Player player, String Health, String EXP, String Money)
	{
		byte HealthPercent = Byte.parseByte(Health.replace("%", ""));
		byte EXPPercent = Byte.parseByte(EXP.replace("%", ""));
		byte MoneyPercent = Byte.parseByte(Money.replace("%", ""));
		if(HealthPercent < 0)
			HealthPercent = 1;
		if(HealthPercent > 100)
			HealthPercent = 100;
		if(EXPPercent < 0)
			EXPPercent = 0;
		if(EXPPercent > 100)
			EXPPercent = 100;
		if(MoneyPercent < 0)
			MoneyPercent = 0;
		if(MoneyPercent > 100)
			MoneyPercent = 100;
		player.setHealth((player.getMaxHealth()/100)*HealthPercent);
		long pEXP = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_EXP();
		long pMaxEXP = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_MaxEXP();
	  	if(pEXP - ((pMaxEXP/100)*EXPPercent)<pMaxEXP*-1)
	  		main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_EXP(-1 * pMaxEXP);
	  	else
	  		main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_EXP(pEXP - ((pMaxEXP/100)*EXPPercent));
	  	long pMoney = main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money();
  		main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP((((pMoney/100)*MoneyPercent) * -1), 0, false);
  		main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setDeath(false);
	}
}
