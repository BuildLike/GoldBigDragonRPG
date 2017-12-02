package dungeon;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import effect.SoundEffect;
import main.MainServerOption;
import util.YamlLoader;



public class DungeonMain
{

	public void IronDoorOpening(Location loc)
	{
		servertick.ServerTickObject STSO = new servertick.ServerTickObject(0, "Sound");
		STSO.setType("Sound");
		STSO.setString((byte)1, loc.getWorld().getName());
		STSO.setInt((byte)0, (int)loc.getX());
		STSO.setInt((byte)1, (int)loc.getY());
		STSO.setInt((byte)2, (int)loc.getZ());
		STSO.setString((byte)0, "0000001");//�Ҹ� ����
		STSO.setInt((byte)3, 20);//�Ҹ� ũ��
		STSO.setInt((byte)4, 5);//�Ҹ� �ӵ�
		
		STSO.setInt((byte)5, 1);//ƽ ����
		STSO.setMaxCount(STSO.getString((byte)0).length());
		STSO.setTick(servertick.ServerTickMain.nowUTC);
		servertick.ServerTickMain.Schedule.put(servertick.ServerTickMain.nowUTC, STSO);
	}
	public void DungeonClear(Player player, Location BossLoc)
	{
		YamlLoader dungeonYaml = new YamlLoader();
		dungeonYaml.getConfig("Dungeon/Dungeon/"+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter() +"/Option.yml");
		
		int Reward_M = dungeonYaml.getInt("Reward.Money");
		int Reward_E = dungeonYaml.getInt("Reward.EXP");
		
		ItemStack item = new ItemStack(292);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("��a��0��a��f��l[���� ���� ����]");
		im.setLore(Arrays.asList("","��f���� ���ڸ� �� �� �ִ�","��f���� �����̴�."));
		im.addEnchant(Enchantment.DURABILITY, 6000, true);
		item.setItemMeta(im);

		if(MainServerOption.partyJoiner.containsKey(player))
		{
			Player[] partyMember = MainServerOption.party.get(MainServerOption.partyJoiner.get(player)).getMember();
			for(int count = 0; count < partyMember.length; count++)
			{
				Long target = MainServerOption.PlayerList.get(partyMember[count].getUniqueId().toString()).getDungeon_UTC();
				if(target.equals(MainServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_UTC()))
				{
					{
						SoundEffect.SP(partyMember[count], Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
						new util.UtilPlayer().giveItemDrop(partyMember[count], item, partyMember[count].getLocation());
						new util.UtilPlayer().DungeonClear(partyMember[count], Reward_M, Reward_E);
					}
				}
			}
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
			new util.UtilPlayer().giveItemDrop(player, item, player.getLocation());
			new util.UtilPlayer().DungeonClear(player, Reward_M, Reward_E);
		}
		
		BossLoc.add(3, -1, -30);
		IronDoorOpening(BossLoc);
		Block block = null;
    	for(int count = 0; count < 7; count++)
    	{
    		for(int count2 = 0; count2 < 5; count2++)
    		{
    			block = BossLoc.add(-1, 0, 0).getBlock();
				block.setType(Material.AIR,true);
    		}
			block = BossLoc.add(5, 1, 0).getBlock();
    	}
		BossLoc.add(0, -7, -1);
    	for(int count = 0; count < 7; count++)
    	{
    		for(int count2 = 0; count2 < 5; count2++)
    		{
    			block = BossLoc.add(-1, 0, 0).getBlock();
				block.setType(Material.AIR,true);
    		}
			block = BossLoc.add(5, 1, 0).getBlock();
    	}
	}
	
	public void BossRoomOpen(Player player, Location BossLoc, String DungeonName)
	{
		YamlLoader dungeonOptionYaml = new YamlLoader();
		dungeonOptionYaml.getConfig("Dungeon/Dungeon/"+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter() +"/Option.yml");
		int SoundTrack = dungeonOptionYaml.getInt("BGM.BOSS");
		
		otherplugins.NoteBlockApiMain NBAPIM = new otherplugins.NoteBlockApiMain();
		if(MainServerOption.partyJoiner.containsKey(player))
		{
			Player[] partyMember = MainServerOption.party.get(MainServerOption.partyJoiner.get(player)).getMember();
			for(int count = 0; count < partyMember.length; count++)
			{
				if(MainServerOption.PlayerList.get(partyMember[count].getUniqueId().toString()).isBgmOn())
				{
					Long target = MainServerOption.PlayerList.get(partyMember[count].getUniqueId().toString()).getDungeon_UTC();
					if(target.equals(MainServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_UTC()))
					{
						NBAPIM.Stop(partyMember[count]);
						NBAPIM.Play(partyMember[count], SoundTrack);	
					}
				}
			}
		}
		else
		{
			NBAPIM.Stop(player);
			NBAPIM.Play(player, SoundTrack);	
		}

		YamlLoader dungeonMonsterYaml = new YamlLoader();
		dungeonMonsterYaml.getConfig("Dungeon/Dungeon/"+DungeonName +"/Monster.yml");
    	String[] MobList = null;
		if(dungeonMonsterYaml.getConfigurationSection("Boss").getKeys(false).size()!=0)
			MobList = dungeonMonsterYaml.getConfigurationSection("Boss").getKeys(false).toArray(new String[0]);

		int XYZloc[] = new int[3];
		XYZloc[0] = (int) BossLoc.getX();
		XYZloc[1] = (int) BossLoc.getY();
		XYZloc[2] = (int) BossLoc.getZ();
		byte GroupNumber = (byte) new util.UtilNumber().RandomNum(0, 15);
		char Group = '0';
		switch(GroupNumber)
		{
			case 0 : Group = '0'; break;
			case 1 : Group = '1'; break;
			case 2 : Group = '2'; break;
			case 3 : Group = '3'; break;
			case 4 : Group = '4'; break;
			case 5 : Group = '5'; break;
			case 6 : Group = '6'; break;
			case 7 : Group = '7'; break;
			case 8 : Group = '8'; break;
			case 9 : Group = '9'; break;
			case 10 : Group = 'a'; break;
			case 11 : Group = 'b'; break;
			case 12 : Group = 'c'; break;
			case 13 : Group = 'd'; break;
			case 14 : Group = 'e'; break;
			case 15 : Group = 'f'; break;
		}
		
    	if(MobList ==null || MobList.length == 0)
    		DungeonClear(player, BossLoc);
    	else
    	{
    		monster.MonsterSpawn MC = new monster.MonsterSpawn();
    		
    		for(int count = 0; count < MobList.length; count++)
    		{
    			BossLoc.add(0, 0.2, 0);
    			SoundEffect.SL(BossLoc, Sound.ENTITY_WITHER_DEATH, 1.3F, 1.8F);
    			MC.SpawnMob(BossLoc, dungeonMonsterYaml.getString("Boss."+MobList[count]),(byte) 4, XYZloc, Group, true);
    		}
		}
	}
	
	public void EraseAllDungeonKey(Player player, boolean isDrop)
	{
		ItemStack item = new ItemStack(292);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("��a��0��a��f��l[���� �� ����]");
		im.setLore(Arrays.asList("","��f���� ���� �� �� �ִ�","��f���� �����̴�."));
		im.addEnchant(Enchantment.DURABILITY, 6000, true);
		item.setItemMeta(im);
		if(isDrop)
			new util.UtilPlayer().dropItem(player, item, true);
		else
			new util.UtilPlayer().deleteItem(player, item, -1);
		
		item = new ItemStack(292);
		im = item.getItemMeta();
		im.setDisplayName("��a��0��a��f��l[���� ���� ����]");
		im.setLore(Arrays.asList("","��f���� ���ڸ� �� �� �ִ�","��f���� �����̴�."));
		im.addEnchant(Enchantment.DURABILITY, 6000, true);
		item.setItemMeta(im);
		new util.UtilPlayer().dropItem(player, item, true);
		
		if(isDrop)
			new util.UtilPlayer().dropItem(player, item, true);
		else
			new util.UtilPlayer().deleteItem(player, item, -1);
	}

	public void MonsterSpawn(Location loc)
	{
		Block SB = new Location(loc.getWorld(),loc.getX(),loc.getY()+12,loc.getZ()).getBlock();
        Sign SignBlock = (Sign) SB.getState();
        String DungeonName = SignBlock.getLine(2);

	  	YamlLoader dungeonMonsterYaml = new YamlLoader();
		dungeonMonsterYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
    	String[] MobList = null;
    	String ListName = "Middle";
    	byte randomLevel = (byte) new util.UtilNumber().RandomNum(0, 3);
    	if(randomLevel<=2)
    	{
    		if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
    			if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
	        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
	        			MobList = null;
		        	else
		        	{
		        		ListName = "Normal";
		        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
		        	}
    			else
    			{
	        		ListName = "High";
    				MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
    			}
    		else
    		{
        		ListName = "Middle";
    			MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
    		}
    	}
    	else
    	{
    		if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
    			if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
	        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
	        			MobList = null;
		        	else
		        	{
		        		ListName = "Normal";
		        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
		        	}
    			else
    			{
	        		ListName = "Middle";
    				MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
    			}
    		else
    		{
        		ListName = "High";
    			MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
    		}
    	}
    	if(MobList!=null)
    	{
        	String Mob = MobList[new util.UtilNumber().RandomNum(0, MobList.length-1)];
    		if(Mob != null)
    		{
        		randomLevel = (byte) new util.UtilNumber().RandomNum(0, 5);
        		switch(randomLevel)
        		{
        		case 0://2ȸ ����
        			break;
        		default://1ȸ ����
        			break;
        		}
        		
    			int[] XYZLoc = new int[3];
    			XYZLoc[0] = (int) loc.getX();
    			XYZLoc[1] = (int) loc.getY();
    			XYZLoc[2] = (int) loc.getZ();
    			byte GroupNumber = (byte) new util.UtilNumber().RandomNum(0, 15);
    			char Group = '0';
    			switch(GroupNumber)
    			{
    				case 0 : Group = '0'; break;
    				case 1 : Group = '1'; break;
    				case 2 : Group = '2'; break;
    				case 3 : Group = '3'; break;
    				case 4 : Group = '4'; break;
    				case 5 : Group = '5'; break;
    				case 6 : Group = '6'; break;
    				case 7 : Group = '7'; break;
    				case 8 : Group = '8'; break;
    				case 9 : Group = '9'; break;
    				case 10 : Group = 'a'; break;
    				case 11 : Group = 'b'; break;
    				case 12 : Group = 'c'; break;
    				case 13 : Group = 'd'; break;
    				case 14 : Group = 'e'; break;
    				case 15 : Group = 'f'; break;
    			}
    			monster.MonsterSpawn MC = new monster.MonsterSpawn();
    			
    			loc.add(0,1,0);
        		for(int count = 0; count < 7; count++)
        		{
        			SoundEffect.SL(loc, Sound.ENTITY_WITHER_DEATH, 1.3F, 1.8F);
        			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+Mob),(byte) 1, XYZLoc, Group, true);
        			loc.add(0, 0.2, 0);
        		}
    			SoundEffect.SL(loc, Sound.ENTITY_WITHER_DEATH, 1.3F, 1.8F);
    			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+Mob),(byte) 3, XYZLoc, Group, true);	
    		}
    		else
    		{
    			Location blockLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ());
				ItemStack item = new ItemStack(292);
				ItemMeta im = item.getItemMeta();
				im.setDisplayName("��a��0��a��f��l[���� �� ����]");
				im.setLore(Arrays.asList("","��f���� ���� �� �� �ִ�","��f���� �����̴�."));
				im.addEnchant(Enchantment.DURABILITY, 6000, true);
				item.setItemMeta(im);
				new event.EventItemDrop().CustomItemDrop(blockLoc, item);
				new dungeon.DungeonMain().DungeonTrapDoorWorker(loc, false);
    		}
    	}
        return;
	}
	
	public void DungeonInteract(PlayerInteractEvent event)
	{
		//�絿�� ��� ���ϰ� �ϱ�
		if(event.getPlayer().getInventory().getItemInMainHand().getTypeId()>=325&&
			event.getPlayer().getInventory().getItemInMainHand().getTypeId()<=327)
		{
			event.setCancelled(true);
			SoundEffect.SP(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
			new effect.SendPacket().sendActionBar(event.getPlayer(), "��c��l[���������� �絿�� ����� �Ұ����մϴ�!]", false);
			return;
		}
		else if(event.getPlayer().getInventory().getItemInMainHand().getTypeId()==432)
		{
			event.setCancelled(true);
			SoundEffect.SP(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
			new effect.SendPacket().sendActionBar(event.getPlayer(), "��c��l[���������� �ķŰ� ����� �Ұ����մϴ�!]", false);
			return;
		}
		Block block = event.getClickedBlock();
		if(block==null)
			return;
		if(block.getType()==Material.AIR)
			return;
		
		
		if(block.getType().getId()==146)//�� ����
		{
			if(TrapChestOpen(block))
			{
				event.setCancelled(true);
				byte howMuch = (byte) new util.UtilNumber().RandomNum(0, 8);
				for(int count = 0; count < howMuch; count++)
					new util.UtilPlayer().addMoneyAndEXP(event.getPlayer() , new util.UtilNumber().RandomNum(0, 500), 0, block.getLocation(), true, false);
			}
		}
		
		else if(block.getType().getId()==95) //������ ����
			TrapGlassTouch(block, event.getPlayer());
		else if(block.getType().getId()==138)//���� Ż��� ��ȣ��
		{
			event.setCancelled(true);
			new dungeon.DungeonGui().DungeonEXIT(event.getPlayer());
		}
		
		else if(block.getType().getId()==23) //���� �� ���豸��
		{
			event.setCancelled(true);
			Block SB = new Location(block.getWorld(),block.getX(),block.getY()+10,block.getZ()).getBlock();
			if(SB.getType()!=Material.SIGN_POST)
				return;
			if(event.getClickedBlock().getLocation().add(0, 10, 0).getBlock() !=null)
			{
				if(event.getClickedBlock().getLocation().add(0,10,0).getBlock().getType() == Material.SIGN_POST)
				{
			        Sign SignBlock = (Sign) event.getClickedBlock().getLocation().add(0, 10, 0).getBlock().getState();
			        String GridImage = SignBlock.getLine(1);
					if(GridImage.equals("��")||GridImage.equals("��")||GridImage.equals("��")||GridImage.equals("��")||GridImage.equals("��"))
					{
						ItemStack item = new ItemStack(292);
						ItemMeta im = item.getItemMeta();
						im.setDisplayName("��a��0��a��f��l[���� �� ����]");
						im.setLore(Arrays.asList("","��f���� ���� �� �� �ִ�","��f���� �����̴�."));
						im.addEnchant(Enchantment.DURABILITY, 6000, true);
						item.setItemMeta(im);
						if(new util.UtilPlayer().deleteItem(event.getPlayer(), item, 1))
						{
				        	Location loc = event.getClickedBlock().getLocation();
							String Title = "��9";
							String SubTitle  = "��f���� �� ���踦 ����Ͽ� ���� ������.";
				        	new effect.SendPacket().sendTitle(event.getPlayer(), Title, SubTitle, 1, 2, 1);
							IronDoorOpening(loc);
					        switch(GridImage)
					        {
					        case "��":
					        case "��":
					        	loc.add(-2, -2, 0);
				    			block = loc.getBlock();
				    			block.setType(Material.AIR,true);
						    	for(int count = 0; count < 6; count++)
						    	{
						    		for(int count2 = 0; count2 < 5; count2++)
						    		{
						    			block.setType(Material.AIR,true);
						    			block = loc.add(1, 0, 0).getBlock();
						    		}
									block = loc.add(-5, 1, 0).getBlock();
						    	}
					        	break;
					        case "��":
					        	loc.add(-2, -2, 0);
				    			block = loc.getBlock();
				    			block.setType(Material.AIR,true);
						    	for(int count = 0; count < 6; count++)
						    	{
						    		for(int count2 = 0; count2 < 5; count2++)
						    		{
						    			block.setType(Material.AIR,true);
						    			block = loc.add(1, 0, 0).getBlock();
						    		}
									block = loc.add(-5, 1, 0).getBlock();
						    	}
						    	loc = event.getClickedBlock().getLocation().add(-2, -2, -1);
				    			block = loc.getBlock();
				    			block.setType(Material.AIR,true);
						    	for(int count = 0; count < 6; count++)
						    	{
						    		for(int count2 = 0; count2 < 5; count2++)
						    		{
						    			block.setType(Material.AIR,true);
						    			block = loc.add(1, 0, 0).getBlock();
						    		}
									block = loc.add(-5, 1, 0).getBlock();
						    	}
						    	loc = event.getClickedBlock().getLocation().add(0, -1 ,-33);
								BossRoomOpen(event.getPlayer(), loc, SignBlock.getLine(2));
					        	break;
					        case "��":
					        case "��":
					        	loc.add(0, -2, -2);
				    			block = loc.getBlock();
				    			block.setType(Material.AIR,true);
						    	for(int count = 0; count < 6; count++)
						    	{
						    		for(int count2 = 0; count2 < 5; count2++)
						    		{
						    			block.setType(Material.AIR,true);
						    			block = loc.add(0, 0, 1).getBlock();
						    		}
									block = loc.add(0, 1, -5).getBlock();
						    	}
					        	break;
					        }
					        return;
						}
						else
						{
							String Title = "��9";
							String SubTitle  = "��f���� ���� ���ؼ��� ���谡 �ʿ��� �� ����...";
				        	new effect.SendPacket().sendTitle(event.getPlayer(), Title, SubTitle, 1, 2, 1);
						}
					}
					else
					{
						String Title = "��9";
						String SubTitle  = "��f����� �� �� �ִ� ���� �ƴ� �� ����...";
			        	new effect.SendPacket().sendTitle(event.getPlayer(), Title, SubTitle, 1, 2, 1);
			        	return;
					}
				}
			}
		}
		else if(block.getType().getId() == 54) //�̹� �� �Ϲ� ����
		{
			SoundEffect.SL(block.getLocation().add(0,2,0), Sound.BLOCK_CHEST_OPEN, 0.5F, 1.8F);
			event.setCancelled(true);
			block.setType(Material.AIR);
			ItemStack item = new ItemStack(292);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("��a��0��a��f��l[���� �� ����]");
			im.setLore(Arrays.asList("","��f���� ���� �� �� �ִ�","��f���� �����̴�."));
			im.addEnchant(Enchantment.DURABILITY, 6000, true);
			item.setItemMeta(im);
			SoundEffect.SL(block.getLocation().add(0,2,0), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.5F, 1.8F);
			new event.EventItemDrop().CustomItemDrop(block.getLocation().add(0.5,1,0.5), item);
			byte howMuch = (byte) new util.UtilNumber().RandomNum(0, 4);
			for(int count = 0; count < howMuch; count++)
				new util.UtilPlayer().addMoneyAndEXP(event.getPlayer() , new util.UtilNumber().RandomNum(0, 500), 0, block.getLocation(), true, false);
		}
		else if(block.getType().getId() == 130) //���� ����
		{
			Block SB = new Location(block.getWorld(),block.getX(),block.getY()+12,block.getZ()).getBlock();
			if(SB.getType()!=Material.SIGN_POST)
				return;
			event.setCancelled(true);
	        Player player = event.getPlayer();
			ItemStack item = new ItemStack(292);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("��a��0��a��f��l[���� ���� ����]");
			im.setLore(Arrays.asList("","��f���� ���ڸ� �� �� �ִ�","��f���� �����̴�."));
			im.addEnchant(Enchantment.DURABILITY, 6000, true);
			item.setItemMeta(im);
			if(new util.UtilPlayer().deleteItem(player, item, 1))
			{
				event.getClickedBlock().setType(Material.AIR, true);
				SoundEffect.SL(event.getClickedBlock().getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 1.0F);
		        Sign SignBlock = (Sign) SB.getState();
		        String DungeonName = SignBlock.getLine(2);
			  	YamlLoader dungeonRewardYaml = new YamlLoader();
				dungeonRewardYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Reward.yml");
				
				boolean treasureGet = false;
				int luck = new util.UtilNumber().RandomNum(0, 7);
				item = dungeonRewardYaml.getItemStack("100."+luck);
				if(item!=null)
				{
					treasureGet = true;
					new util.UtilPlayer().giveItemDrop(player, item, event.getClickedBlock().getLocation());
				}
				luck = new util.UtilNumber().RandomNum(1, 10);
				if(luck != 10)
				{
					int count = new util.UtilNumber().RandomNum(0, 7);
					item = dungeonRewardYaml.getItemStack("90."+count);
					if(item!=null)
					{
						treasureGet = true;
						new util.UtilPlayer().giveItemDrop(player, item, event.getClickedBlock().getLocation());
					}
				}
				luck = new util.UtilNumber().RandomNum(1, 10);
				if(luck <= 5)
				{
					int count = new util.UtilNumber().RandomNum(0, 7);
					item = dungeonRewardYaml.getItemStack("50."+count);
					if(item!=null)
					{
						treasureGet = true;
						new util.UtilPlayer().giveItemDrop(player, item, event.getClickedBlock().getLocation());
					}
				}
				luck = new util.UtilNumber().RandomNum(1, 10);
				if(luck == 1)
				{
					int count = new util.UtilNumber().RandomNum(0, 7);
					item = dungeonRewardYaml.getItemStack("10."+count);
					if(item!=null)
					{
						treasureGet = true;
						new util.UtilPlayer().giveItemDrop(player, item, event.getClickedBlock().getLocation());
					}
				}
				luck = new util.UtilNumber().RandomNum(1, 100);
				if(luck == 1)
				{
					int count = new util.UtilNumber().RandomNum(0, 7);
					item = dungeonRewardYaml.getItemStack("1."+count);
					if(item!=null)
					{
						treasureGet = true;
						new util.UtilPlayer().giveItemDrop(player, item, event.getClickedBlock().getLocation());
					}
				}
				luck = new util.UtilNumber().RandomNum(1, 1000);
				if(luck == 1)
				{
					int count = new util.UtilNumber().RandomNum(0, 7);
					item = dungeonRewardYaml.getItemStack("0."+count);
					if(item!=null)
					{
						treasureGet = true;
						new util.UtilPlayer().giveItemDrop(player, item, event.getClickedBlock().getLocation());
					}
				}
				
				if(treasureGet==false)
					new effect.SendPacket().sendActionBar(player, "��c��l[��! ���� ��ȸ��...]", false);
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1.0F, 0.5F);
				new effect.SendPacket().sendActionBar(player, "��f��l[���ڿ� �´� ���谡 �����ϴ�!]", false);
			}
		}
		return;
	}
	
	public boolean TrapChestOpen(Block block)
	{
		Block SB = new Location(block.getWorld(),block.getX(),block.getY()+12,block.getZ()).getBlock();
		if(SB.getType()!=Material.SIGN_POST)
			return false;
		monster.MonsterSpawn MC = new monster.MonsterSpawn();
		
		
        Sign SignBlock = (Sign) SB.getState();
        String GridImage = SignBlock.getLine(1);
        String DungeonName = SignBlock.getLine(2);
        Location loc = new Location(block.getWorld(),block.getX(),block.getY(),block.getZ());

	  	YamlLoader dungeonMonsterYaml = new YamlLoader();
		dungeonMonsterYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
    	String[] MobList = null;
    	String ListName = "Normal";
    	byte randomLevel = (byte) new util.UtilNumber().RandomNum(0, 3);
        if(dungeonMonsterYaml.contains("Normal")==false)
        	dungeonMonsterYaml.createSection("Normal");
        if(dungeonMonsterYaml.contains("Middle")==false)
        	dungeonMonsterYaml.createSection("Middle");
        if(dungeonMonsterYaml.contains("High")==false)
        	dungeonMonsterYaml.createSection("High");
    	if(randomLevel <= 1)
    	{
    		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
        		if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
        			MobList = null;
	        	else
	        	{
	        		ListName = "Middle";
	        		MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
	        	}
        	else
        	{
        		ListName = "Normal";
        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
        	}
    	}
    	else if(randomLevel==2)
    	{
    		if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
    			if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
	        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
	        			MobList = null;
		        	else
		        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
    			else
    			{
	        		ListName = "High";
    				MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
    			}
    		else
    		{
        		ListName = "Middle";
    			MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
    		}
    	}
    	else
    	{
    		if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
    			if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
	        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
	        			MobList = null;
		        	else
		        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
    			else
    			{
	        		ListName = "Middle";
    				MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
    			}
    		else
    		{
        		ListName = "High";
    			MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
    		}
    	}
    	if(MobList!=null && MobList.length != 0)
    	{
    		ArrayList<String> Mob = new ArrayList<String>();
    		for(int count = 0; count < 8; count++)
        		Mob.add(MobList[new util.UtilNumber().RandomNum(0, MobList.length-1)]);
    		if(MobList.length > 0)
    		{
            	if(GridImage.equals("��"))
            	{
        			SoundEffect.SL(loc, Sound.BLOCK_CHEST_OPEN, 1.3F, 1.8F);
            		MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+Mob.get(0)),(byte)-1,null, (char) -1, false);
            	}
            	else
            	{
            		randomLevel = (byte) new util.UtilNumber().RandomNum(0, 5);
            		switch(randomLevel)
            		{
            		case 0://2ȸ ����
            			break;
            		default://1ȸ ����
            			break;
            		}
            		
    				DungeonTrapDoorWorker(loc, true);
    				int[] XYZLoc = new int[3];
    				XYZLoc[0] = block.getX();
    				XYZLoc[1] = block.getY();
    				XYZLoc[2] = block.getZ();
    				byte RoomChallenge = (byte) new util.UtilNumber().RandomNum(1, 5);
    				byte GroupNumber = (byte) new util.UtilNumber().RandomNum(0, 15);
    				char Group = '0';
    				switch(GroupNumber)
    				{
        				case 0 : Group = '0'; break;
        				case 1 : Group = '1'; break;
        				case 2 : Group = '2'; break;
        				case 3 : Group = '3'; break;
        				case 4 : Group = '4'; break;
        				case 5 : Group = '5'; break;
        				case 6 : Group = '6'; break;
        				case 7 : Group = '7'; break;
        				case 8 : Group = '8'; break;
        				case 9 : Group = '9'; break;
        				case 10 : Group = 'a'; break;
        				case 11 : Group = 'b'; break;
        				case 12 : Group = 'c'; break;
        				case 13 : Group = 'd'; break;
        				case 14 : Group = 'e'; break;
        				case 15 : Group = 'f'; break;
    				}
        			loc.add(0, 1, 0);
    				if(RoomChallenge <= 2)
    				{
                		for(int count = 0; count < 8; count++)
                		{
                			SoundEffect.SL(loc, Sound.ENTITY_WITHER_DEATH, 1.3F, 1.8F);
                			loc.add(0, 0.2, 0);
                			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+Mob.get(count)),(byte) 2, XYZLoc, Group, true);
                		}
    				}
    				else
    				{
                		for(int count = 0; count < 7; count++)
                		{
                			loc.add(0, 0.2, 0);
                			SoundEffect.SL(loc, Sound.ENTITY_WITHER_DEATH, 1.3F, 1.8F);
                			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+Mob.get(count)),(byte) 1, XYZLoc, Group, true);
                		}
            			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+Mob.get(7)),(byte) 3, XYZLoc, Group, true);
    				}
            		/*
            		�Ϲ� �� ���� �� �����ǰ� �� ������ �ϱ�
            		���� ������ �ִ� ���Ϳ��Դ� �̸� �տ� Į�� �ڵ带 �ٿ� ǥ���Ѵ�.
            		Į���ڵ� ���� �迭�� ��-��-�� ���� �����Ѵ�.
            		��-��-��-�� = ���� ���� �༮
            		��-��-��-�� = �Ϲ� �༮
            		��-��-��-�� = ������ ���ϴ� ������, �ݰ� 25��� �̳��� ��˳���� ���� ���, ������ ���� ������.
            		������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.
            		��-��-��-�� = ���� ���̺� ����
            		���� ���� & �ڵ�� ������ ���� �׷��� �����ϱ� ��������,
            		�� ���� �׷� �ڵ尡 ���� ���Ͱ� �ݰ� 20 �̳��� ���� ���, ���� ���̺갡 �����ų�
            		���� ������ �ȴ�. ���� �׷� �ڵ�� 0 ~ f ���� �����Ѵ�.
            		
            		���� ������ ���ؼ��� ���ʿ� ������ ���� ��ġ�� �˰� �־�� �ϴµ�,
            		X, Y, Z ��ǥ�� ���� �� �ξ�� �Ѵ�.
            		�����ϱ� ���� ���� ���Ͱ� ���� ���� �������ε�
            		�װ� ��ٷӴ�.
            		�տ� �������� ���ٸ� ���� ���� �ְ�, �ִٸ� �� �Ʒ��� ��ǥ�� ������ ��,
            		���� �� ����ϰ� �ٽ� ��ǥ�� ���ֹ����� ������, ������, ��, ���� ���� ���͵��� ������ �������� ����.
            		
            		
            		EntityDeath �̺�Ʈ����, ��ƼƼ�� ���� ���尡 Dungeon�� ��츸
            		���� ���� ������ ó���ϸ�, Ŀ���� ���� ���� �����͸� ã�� ����
            		���� 8ĳ���� ����Ʈ Ŀ���� ������ �����ϰ� �ѱ⵵�� �Ѵ�.
            		��� Entity Damage By Entity �̺�Ʈ������ �������� ���� ��츦 ��� �־�� �Ѵ�.
            		*/
            	}
    		}
    		else if(!GridImage.equals("��"))
    		{
    			Location blockLoc = new Location(block.getWorld(), block.getLocation().getX(), block.getLocation().getY()+1, block.getLocation().getZ());
				ItemStack item = new ItemStack(292);
				ItemMeta im = item.getItemMeta();
				im.setDisplayName("��a��0��a��f��l[���� �� ����]");
				im.setLore(Arrays.asList("","��f���� ���� �� �� �ִ�","��f���� �����̴�."));
				im.addEnchant(Enchantment.DURABILITY, 6000, true);
				item.setItemMeta(im);
				new event.EventItemDrop().CustomItemDrop(blockLoc, item);
    		}
    	}
		else if(!GridImage.equals("��"))
		{
			Location blockLoc = new Location(block.getWorld(), block.getLocation().getX(), block.getLocation().getY()+1, block.getLocation().getZ());
			ItemStack item = new ItemStack(292);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName("��a��0��a��f��l[���� �� ����]");
			im.setLore(Arrays.asList("","��f���� ���� �� �� �ִ�","��f���� �����̴�."));
			im.addEnchant(Enchantment.DURABILITY, 6000, true);
			item.setItemMeta(im);
			new event.EventItemDrop().CustomItemDrop(blockLoc, item);
		}
		SoundEffect.SL(block.getLocation(), org.bukkit.Sound.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
        block.setTypeIdAndData(0, (byte)0, true);
        return true;
	}
	
	public void TrapGlassTouch(Block block, Player player)
	{
		Block SB = new Location(block.getWorld(),block.getX(),block.getY()+11,block.getZ()).getBlock();
		if(SB.getType()!=Material.SIGN_POST)
			return;
		monster.MonsterSpawn MC = new monster.MonsterSpawn();
		
		effect.ParticleEffect p = new effect.ParticleEffect();
		if(block.getData()==15||block.getData()==14||block.getData()==13||
			block.getData()==11||block.getData()==8)
		{
			switch(block.getData())
			{
    			case 15: block.setTypeIdAndData(95, (byte)7, true);break;
    			case 14: block.setTypeIdAndData(95, (byte)6, true);break;
    			case 13: block.setTypeIdAndData(95, (byte) 5, true);break;
    			case 11: block.setTypeIdAndData(95, (byte)3, true);break;
    			case 8: block.setTypeIdAndData(95, (byte)0, true);break;
			}
			for(int counter=0;counter<50;counter++)
				p.PL(block.getLocation(), org.bukkit.Effect.MAGIC_CRIT, 0);
			SoundEffect.SL(block.getLocation(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.5F);
		}
		else if(block.getData()==0||block.getData()==3||block.getData()==5||block.getData()==6||block.getData()==7)
		{
			for(int counter=0;counter<31;counter++)
				p.PL(block.getLocation(), org.bukkit.Effect.CRIT, 0);
			SoundEffect.SL(block.getLocation(), org.bukkit.Sound.ENTITY_GENERIC_HURT, 0.5F, 0.5F);
			return;
		}
		block = new Location(block.getWorld(),block.getX(),block.getY()-2,block.getZ()).getBlock();
		if(block.getType()==Material.STONE)
		{
			block = new Location(block.getWorld(),block.getX(),block.getY()+13,block.getZ()).getBlock();
	        Sign SignBlock = (Sign) block.getState();
	        String GridImage = SignBlock.getLine(1);
	        Location loc = new Location(block.getWorld(),block.getX(),block.getY(),block.getZ());
	        int Direction = Integer.parseInt(SignBlock.getLine(3));
			DungeonDoorRemover(player, GridImage.charAt(0), Direction, loc);
		}
		else
		{
			block = new Location(block.getWorld(),block.getX(),block.getY()+13,block.getZ()).getBlock();
	        Sign SignBlock = (Sign) block.getState();
	        String GridImage = SignBlock.getLine(1);
	        String DungeonName = SignBlock.getLine(2);
	        /*
	        Direction : 0 = ��
	        Direction : 1 = �ϵ�
	        Direction : 2 = ��
	        Direction : 3 = ����
	        Direction : 4 = ��
	        Direction : 5 = ����
	        Direction : 6 = ��
	        Direction : 7 = �ϼ�
	        Direction : 8 = �߾�
	         */
	        Location loc = new Location(block.getWorld(),block.getX(),block.getY(),block.getZ());
	        int Direction = Integer.parseInt(SignBlock.getLine(3));
	        int NowLevel = Integer.parseInt(SignBlock.getLine(0).split("/")[0]);
        	if(NowLevel<=0)
        		NowLevel = 1;
	        int MaxLevel = Integer.parseInt(SignBlock.getLine(0).split("/")[1]);
	        switch(Direction)
	        {
	        case 1:
	        	loc.add(-6, -12, 6);
	        	break;
	        case 3:
	        	loc.add(-6, -12, -6);
	        	break;
	        case 5:
	        	loc.add(6, -12, -6);
	        	break;
	        case 7:
	        	loc.add(6, -12, 6);
	        	break;
	        }
		  	YamlLoader dungeonMonsterYaml = new YamlLoader();
			dungeonMonsterYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Monster.yml");
        	String[] MobList = null;
        	String ListName = "Normal";
	        if(GridImage.equals("��"))
	        {
	        	if(dungeonMonsterYaml.getConfigurationSection("SubBoss").getKeys(false).size()==0)
	        		if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
		        		if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
			        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
			        			MobList = null;
			        		else
			        			MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
		        		else
		        		{
		        			ListName = "Middle";
		        			MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
		        		}
	        		else
	        		{
	        			ListName = "High";
	        			MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
	        		}
	        	else
        		{
        			ListName = "SubBoss";
	        		MobList = dungeonMonsterYaml.getConfigurationSection("SubBoss").getKeys(false).toArray(new String[0]);
        		}
	        	if(MobList!=null)
	        	{
	        		loc.add(0,1,0);
	        		for(int count = 0; count < 4; count++)
	        		{
	        			loc.add(new util.UtilNumber().RandomNum(-2, 2), 0.1*count, new util.UtilNumber().RandomNum(-2, 2));
	        			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+MobList[new util.UtilNumber().RandomNum(0, MobList.length-1)]), (byte)-1, null, (char) -1, false);
	        		}
	        	}
	        }
	        else
	        {
	        	if(MaxLevel < 5)
	        		MaxLevel = MaxLevel*20;
	        	else if(MaxLevel < 10)
	        		MaxLevel = MaxLevel*10;
	        	else if(MaxLevel < 20)
	        		MaxLevel = MaxLevel*5;
	        	else if(MaxLevel >= 100)
	        		MaxLevel = 100;
	        	if(MaxLevel/NowLevel>20)
	        	{
	        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
		        		if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
		        			MobList = null;
			        	else
			        	{
			        		ListName = "Middle";
			        		MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
			        	}
		        	else
		        	{
		        		ListName = "Normal";
		        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
		        	}
	        	}  	
	        	else if(MaxLevel/NowLevel>10)
	        	{
	        		if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
	        			if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
			        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
			        			MobList = null;
				        	else
				        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
	        			else
	        			{
			        		ListName = "High";
	        				MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
	        			}
	        		else
	        		{
		        		ListName = "Middle";
	        			MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
	        		}
	        	}
	        	else
	        	{
	        		if(dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).size()==0)
	        			if(dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).size()==0)
			        		if(dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).size()==0)
			        			MobList = null;
				        	else
				        		MobList = dungeonMonsterYaml.getConfigurationSection("Normal").getKeys(false).toArray(new String[0]);
	        			else
	        			{
			        		ListName = "Middle";
	        				MobList = dungeonMonsterYaml.getConfigurationSection("Middle").getKeys(false).toArray(new String[0]);
	        			}
	        		else
	        		{
		        		ListName = "High";
	        			MobList = dungeonMonsterYaml.getConfigurationSection("High").getKeys(false).toArray(new String[0]);
	        		}
	        	}
	        }
	        
        	if(MobList!=null)
        	{
        		loc.add(0,1,0);
        		for(int count = 0; count < 4; count++)
        		{
        			loc.add(new util.UtilNumber().RandomNum(-2, 2),0.1*count, new util.UtilNumber().RandomNum(-2, 2));
        			SoundEffect.SL(loc, Sound.ENTITY_WITHER_DEATH, 1.3F, 1.8F);
        			MC.SpawnMob(loc, dungeonMonsterYaml.getString(ListName+"."+MobList[new util.UtilNumber().RandomNum(0, MobList.length-1)]), (byte)-1, null, (char) -1, false);
        		}
        	}
		}
	}
	
	
	
	private void DungeonDoorRemover(Player player, char GridImage, int Direction, Location loc)
	{
		
		Location Original = loc.add(0,-12,0);
		Original.setX(loc.getX());
		Original.setY(loc.getY());
		Original.setZ(loc.getZ());
		Block block = null;
		switch(GridImage)
        {
        case '��':
			switch(Direction)
	        {
		        case 1:
		        	loc.add(-3, 0, -4).getBlock();
		        	break;
		        case 3:
		        	loc.add(-3, 0, -16).getBlock();
		        	break;
		        case 5:
		        	loc.add(9, 0, -16).getBlock();
		        	break;
		        case 7:
		        	loc.add(9, 0, -4).getBlock();
		        	break;
	        }
			IronDoorOpening(loc);
        	for(int count = 0; count < 5; count++)
        	{
        		for(int count2 = 0; count2 < 5; count2++)
        		{
        			block = loc.add(-1, 0, 0).getBlock();
					block.setType(Material.AIR,true);
        		}
    			block = loc.add(5, 1, 0).getBlock();
        	}
        	break;
        case '��':
        	Location BossLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        	Location SignLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        	SignLoc.add(0, 12, 0);
	        Sign SignBlock = (Sign) SignLoc.getBlock().getState();
			switch(Direction)
	        {
		        case 1:
		        	loc = loc.add(-3, 0, -4);
		        	BossLoc = BossLoc.add(-6, 1, -37);
		        	break;
		        case 3:
		        	loc.add(-3, 0, -16).getBlock();
		        	BossLoc = BossLoc.add(-6, 1, -49);
		        	break;
		        case 5:
		        	loc.add(9, 0, -16).getBlock();
		        	BossLoc = BossLoc.add(6, 1, -49);
		        	break;
		        case 7:
		        	loc.add(9, 0, -4).getBlock();
		        	BossLoc = BossLoc.add(6, 1, -37);
		        	break;
	        }
			IronDoorOpening(loc);
			for(int c=0;c<2;c++)
			{
	        	for(int count = 0; count < 5; count++)
	        	{
	        		for(int count2 = 0; count2 < 5; count2++)
	        		{
	        			block = loc.add(-1, 0, 0).getBlock();
    					block.setType(Material.AIR,true);
	        		}
        			block = loc.add(5, 1, 0).getBlock();
	        	}
    			block = loc.add(0, -5, -1).getBlock();
			}
			BossRoomOpen(player, BossLoc, SignBlock.getLine(2));
        	break;
        case '��':
			switch(Direction)
	        {
	        case 1:
	        	loc.add(-3, 0, 16).getBlock();
	        	break;
	        case 3:
	        	loc.add(-3, 0, 4).getBlock();
	        	break;
	        case 5:
	        	loc.add(9, 0, 4).getBlock();
	        	break;
	        case 7:
	        	loc.add(9, 0, 16).getBlock();
	        	break;
	        }
			IronDoorOpening(loc);
        	for(int count = 0; count < 5; count++)
        	{
        		for(int count2 = 0; count2 < 5; count2++)
        		{
        			block = loc.add(-1, 0, 0).getBlock();
					block.setType(Material.AIR,true);
        		}
    			block = loc.add(5, 1, 0).getBlock();
        	}
        	break;
        case '��':
			switch(Direction)
	        {
		        case 1:
		        	loc.add(-16, 0, 9).getBlock();
		        	break;
		        case 3:
		        	loc.add(-16, 0, -3).getBlock();
		        	break;
		        case 5:
		        	loc.add(-4, 0, -3).getBlock();
		        	break;
		        case 7:
		        	loc.add(-4, 0, 9).getBlock();
		        	break;
	        }
			IronDoorOpening(loc);
        	for(int count = 0; count < 5; count++)
        	{
        		for(int count2 = 0; count2 < 5; count2++)
        		{
        			block = loc.add(0, 0, -1).getBlock();
					block.setType(Material.AIR,true);
        		}
    			block = loc.add(0, 1, 5).getBlock();
        	}
        	break;
        case '��':
			switch(Direction)
	        {
		        case 1:
		        	loc.add(4, 0, 9).getBlock();
		        	break;
		        case 3:
		        	loc.add(4, 0, -3).getBlock();
		        	break;
		        case 5:
		        	loc.add(16, 0, -3).getBlock();
		        	break;
		        case 7:
		        	loc.add(16, 0, 9).getBlock();
		        	break;
	        }
			IronDoorOpening(loc);
        	for(int count = 0; count < 5; count++)
        	{
        		for(int count2 = 0; count2 < 5; count2++)
        		{
        			block = loc.add(0, 0, -1).getBlock();
					block.setType(Material.AIR,true);
        		}
    			block = loc.add(0, 1, 5).getBlock();
        	}
        	break;
        }
	}
	
	public void DungeonTrapDoorWorker(Location loc, boolean isCreate)
	{
		Block block = null;
		Material M = Material.STAINED_GLASS_PANE;
		byte blockData = (byte)14;

		IronDoorOpening(loc);
		Location loc2;
		byte signX = 0;
		byte signZ = 0;
		byte addingX;
		byte addingZ;
		byte uppingX;
		byte uppingZ;
		for(int directionLoop = 0; directionLoop < 4; directionLoop++)
		{
			signX = 0;
			signZ = 0;
			addingX = 0;
			addingZ = 0;
			uppingX = 0;
			uppingZ = 0;
			loc2 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
			if(directionLoop == 0)
			{
				loc2.add(-10,0,2);
				addingZ = -1;
				uppingZ = 5;
				signX = 0;
				signZ = -2;
			}
			else if(directionLoop == 1)
			{
				loc2.add(2,0,-10);
				addingX = -1;
				uppingX = 5;
				signZ = 0;
				signX = -2;
			}
			else if(directionLoop ==2)
			{
				loc2.add(10,0,2);
				addingZ = -1;
				uppingZ = 5;
				signX = 0;
				signZ = -2;
			}
			else
			{
				loc2.add(2,0,10);
				addingX = -1;
				uppingX = 5;
				signZ = 0;
				signX = -2;
			}
			block = loc2.getBlock();
	    	for(int count = 0; count < 6; count++)
	    	{
	    		for(int count2 = 0; count2 < 5; count2++)
	    		{
	    			if(isCreate)
	    			{
		    			if(block.getType()==Material.AIR||block == null)
		    			{
		    				block.setType(M,true);
		    				block.setData(blockData);
		    			}
	    			}
	    			else if(block.getType()==M||block == null)
		    			block.setType(Material.AIR,true);
	    			block = loc2.add(addingX, 0, addingZ).getBlock();
	    		}
				block = loc2.add(uppingX, 1, uppingZ).getBlock();
	    	}
	    	block = loc2.add(signX,6,signZ).getBlock();
	    	
	    	block.setType(Material.SIGN_POST);

	    	if(block.getType()==Material.SIGN_POST)
	    	{
		        Sign s = (Sign) block.getState();
		        s.setLine(0, ""+(int)loc.getX());//�� �� X
		        s.setLine(1, ""+(int)loc.getY()+1);//�� �� Y
		        s.setLine(2, ""+(int)loc.getZ());//�� �� Z
		        s.setLine(3, "");//�� ����
		        s.update();
	    	}
	    	else
	    	{
		    	block.setType(Material.SIGN_POST);
		    	if(block.getType()==Material.SIGN_POST)
		    	{
			        Sign s = (Sign) block.getState();
			        s.setLine(0, ""+(int)loc.getX());//�� �� X
			        s.setLine(1, ""+(int)loc.getY()+1);//�� �� Y
			        s.setLine(2, ""+(int)loc.getZ());//�� �� Z
			        s.setLine(3, "");//�� ����
			        s.update();
		    	}
	    	}
		}
	}
	
}