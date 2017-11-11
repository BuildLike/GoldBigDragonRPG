package main;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import effect.SoundEffect;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_10_R1.PacketPlayInClientCommand;
import party.Party_DataManager;
import user.UserData_Object;
import util.YamlLoader;



public class Main extends JavaPlugin implements Listener
{
	public static JavaPlugin plugin = null;

	@EventHandler
	public void SongEndEvent(com.xxmicloxx.noteblockapi.SongEndEvent event)
	{
		event.getSongPlayer().setPlaying(false);
		Player player = null;
		for(int count = 0; count < event.getSongPlayer().getPlayerList().size(); count++)
		{
			player = Bukkit.getPlayer(event.getSongPlayer().getPlayerList().get(count));
			new otherplugins.NoteBlockAPIMain().SongPlay(player, event.getSongPlayer().getSong());
		}
	}

    @EventHandler
    public void craftItem(PrepareItemCraftEvent event)
    {
    	Inventory inv = event.getInventory();
    	ItemStack item = null;
    	boolean cantCraft = false;
    	for(int count = 0; count < inv.getSize(); count++)
    	{
    		item = inv.getItem(count);
    		if(item != null && item.getType() != Material.AIR)
    		{
    			if(item.hasItemMeta()&&item.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)&&item.getItemMeta().hasLore()&&item.getItemMeta().hasDisplayName()&&
    			item.getItemMeta().getLore().get(0).contains("[��]"))
    			{
    				cantCraft = true;
    				break;
    			}
    				
    		}
    	}
    	if(cantCraft)
    		inv.setItem(0, new ItemStack(Material.AIR));
    }
	
	public void onEnable()
	{
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new battle.Battle_Main(), this);
		getServer().getPluginManager().registerEvents(new event.Main_BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new map.Map(), this);
		getServer().getPluginManager().registerEvents(new event.Main_BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new event.Main_Fishing(), this);
		getServer().getPluginManager().registerEvents(new event.Main_PlayerChat(), this);
		getServer().getPluginManager().registerEvents(new event.Main_ChangeHotBar(), this);
		getServer().getPluginManager().registerEvents(new event.Main_PlayerJoin(), this);
		new otherplugins.NoteBlockAPIMain(Main.plugin);
		new Main_ServerOption().initialize();
		
		if(getServer().getPluginManager().getPlugin("Vault")!=null)
		{
			RegisteredServiceProvider<Economy> rspE = getServer().getServicesManager().getRegistration(Economy.class);
			if(rspE != null)
				main.Main_ServerOption.economy = rspE.getProvider();
		}
	  	return;
	}
	
	public void onDisable()
	{
		new corpse.Corpse_Main().RemoveAllCorpse();
    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
    	Player[] a = new Player[playerlist.size()];
    	playerlist.toArray(a);
    	for(int count = 0; count <a.length;count++)
    		new otherplugins.NoteBlockAPIMain().Stop(a[count]);
	  	new Party_DataManager().saveParty();

		new servertick.ServerTick_ScheduleManager().saveCategoriFile();
		Object[] players = Bukkit.getOnlinePlayers().toArray();
		for(int count = 0; count < players.length; count++)
			Main_ServerOption.PlayerList.get(((Player)players[count]).getUniqueId().toString()).saveAll();
	  	Bukkit.getConsoleSender().sendMessage("��c[Clossing GoldBigDragon Advanced...]");
	  	return;
	}
	
	@EventHandler
	private void PlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
	  	YamlLoader userYaml = new YamlLoader();
		if(player.getLocation().getWorld().getName().equals("Dungeon"))
			new dungeon.Dungeon_Main().EraseAllDungeonKey(player, true);
		
		if(new corpse.Corpse_Main().DeathCapture(player,false))
			new corpse.Corpse_Main().RemoveCorpse(player.getName());
		
		if(Main_ServerOption.partyJoiner.containsKey(player))
			Main_ServerOption.party.get(Main_ServerOption.partyJoiner.get(player)).QuitParty(player);
		
		new otherplugins.NoteBlockAPIMain().Stop(event.getPlayer());

		userYaml.getConfig("UserData/"+ player.getUniqueId()+".yml");
		userYaml.removeKey("Data");
		userYaml.saveConfig();
		Main_ServerOption.PlayerCurrentArea.remove(player);
    	new user.Equip_GUI().FriendJoinQuitMessage(player, false);

	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
		if(configYaml.getString("Server.QuitMessage") != null)
		{
			String message = configYaml.getString("Server.QuitMessage").replace("%player%",event.getPlayer().getName());
			event.setQuitMessage(message);
		}
		else
			event.setQuitMessage(null);
	  main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).saveAll();
	  main.Main_ServerOption.PlayerList.remove(player.getUniqueId().toString());
	}
	
	@EventHandler
	private void PlayerRespawn(PlayerRespawnEvent event)
	{
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
		if(configYaml.getBoolean("Death.SystemOn"))
		{
			Player player = event.getPlayer();
	    	event.setRespawnLocation(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getLastDeathPoint());
	    	player.setGameMode(GameMode.SPECTATOR);
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).isBgmOn())
			{
	    		new otherplugins.NoteBlockAPIMain().Stop(player);
				if(configYaml.contains("Death.Track"))
					if(configYaml.getInt("Death.Track")!=-1)
						new otherplugins.NoteBlockAPIMain().Play(player, configYaml.getInt("Death.Track"));
			}
	    	new corpse.Corpse_GUI().OpenReviveSelectGUI(player);
		}
		return;
	}

	
	@EventHandler
	private void PlayerItemDrop(PlayerDropItemEvent event)
	{
		ItemStack IT = event.getItemDrop().getItemStack();
		if(IT.hasItemMeta() == true)
			if(IT.getItemMeta().hasLore() == true)
				if(IT.getItemMeta().getLore().size() == 4)
					if(IT.getItemMeta().getLore().get(3).equals("��e[Ŭ���� �����Կ��� ����]")==true)
						event.setCancelled(true);
		return;
	}
	
	@EventHandler
	private void AmorStand(PlayerArmorStandManipulateEvent event)
	{
		Player player = event.getPlayer();
		ArmorStand AS = event.getRightClicked();
		String name = AS.getCustomName();
		if(name!=null)
		{
			if(name.charAt(0)=='��'&&name.charAt(1)=='0'&&name.charAt(2)=='��'&&name.charAt(3)=='l')
			{
				event.setCancelled(true);
				new structure.Structure_Main().StructureUse(player,AS.getCustomName());
				return;
			}
			else if(name.charAt(0)=='��'&&name.charAt(1)=='c'&&name.charAt(2)=='��'&&name.charAt(3)=='0')
			{
				event.setCancelled(true);
				if(player.getInventory().getItemInMainHand() != null)
				{
					if(Main_ServerOption.DeathRescue != null)
					{
						if(Main_ServerOption.DeathRescue.getTypeId()==player.getInventory().getItemInMainHand().getTypeId())
						{
							ItemStack Pitem = player.getInventory().getItemInMainHand();
							if(Main_ServerOption.DeathRescue.getAmount()<=Pitem.getAmount())
							{
								
								String Name = null;
								if(AS.getItemInHand().getType() != Material.AIR)
									Name = AS.getItemInHand().getItemMeta().getDisplayName();
								else if(AS.getHelmet().getType() != Material.AIR)
									Name = AS.getHelmet().getItemMeta().getDisplayName();
								if(Name != null)
								{
									Player target = Bukkit.getPlayer(Name);
									if(target != null)
									{
									  	if(main.Main_ServerOption.PlayerList.get(target.getUniqueId().toString()).isDeath())
									  	{
											if(new util.Util_Player().deleteItem(player, Main_ServerOption.DeathRescue, Main_ServerOption.DeathRescue.getAmount()))
											{
												new corpse.Corpse_Main().RemoveCorpse(Name);
												player.updateInventory();
												player.sendMessage("��d[����] : ��e"+target.getName()+"��d���� ��Ȱ���׽��ϴ�!");
												target.sendMessage("��d[��Ȱ] : ��e"+player.getName()+"��d�Կ� ���� ��Ȱ�Ͽ����ϴ�!");
												target.setGameMode(GameMode.SURVIVAL);
												target.closeInventory();
												Location l = target.getLocation();
												l.add(0, 1, 0);
												target.teleport(l);
												for(int count2=0;count2<210;count2++)
													new effect.ParticleEffect().PL(target.getLocation(), org.bukkit.Effect.SMOKE, new util.Util_Number().RandomNum(0, 14));
												SoundEffect.SL(target.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, 0.5F, 1.8F);
									    		new otherplugins.NoteBlockAPIMain().Stop(target);
											  	YamlLoader configYaml = new YamlLoader();
										    	configYaml.getConfig("config.yml");
										    	new corpse.Corpse_GUI().Penalty(target, configYaml.getString("Death.Spawn_Help.SetHealth"), configYaml.getString("Death.Spawn_Help.PenaltyEXP"), configYaml.getString("Death.Spawn_Help.PenaltyMoney"));
												return;
											}
											else
											{
												SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
												player.sendMessage("��c[SYSTEM] : ��Ȱ �������� �����Ͽ� ��Ȱ��ų �� �����ϴ�!");
												return;
											}
									  	}
									}
									else
									{
										Collection<Entity> aa = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 3, 3, 3);
										for(int count = 0; count < aa.size(); count++)
										{
											Entity now = ((Entity)aa.toArray()[count]);
											if(now.getType()==EntityType.ARMOR_STAND)
											{
												String CustomName = now.getCustomName();
												if(CustomName != null)
												{
													if(CustomName.charAt(0)=='��'&&CustomName.charAt(1)=='c'&&CustomName.charAt(2)=='��'&&CustomName.charAt(3)=='0')
													{
														String Name2 = null;
														if(AS.getItemInHand().getType() != Material.AIR)
															Name2 = AS.getItemInHand().getItemMeta().getDisplayName();
														else if(AS.getHelmet().getType() != Material.AIR)
															Name2 = AS.getHelmet().getItemMeta().getDisplayName();
														if(Name.equals(Name2))
															now.remove();
													}
												}
											}
										}
										return;
									}
								}
								else
								{
									AS.remove();
									return;
								}
							}
						}
					}
				}
			}
			else
			{
				if(event.getPlayer().isOp()==false)
				{
					String TargetArea = null;
					area.Area_Main A = new area.Area_Main();
					if(A.getAreaName((Entity)AS) != null)
						TargetArea = A.getAreaName((Entity)AS)[0];
					if(TargetArea != null && A.getAreaOption(TargetArea, (char) 7) == false)
					{
						event.setCancelled(true);
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	private void BlockBurnEvent(BlockBurnEvent event)
	{
		if(Main_ServerOption.AntiExplode || event.getBlock().getLocation().getWorld().getName().equals("Dungeon"))
			event.setCancelled(true);
	}
	@EventHandler
	private void BlockIgniteEvent(BlockIgniteEvent event)
	{
		if((Main_ServerOption.AntiExplode || event.getBlock().getLocation().getWorld().getName().equals("Dungeon"))&&event.getIgnitingEntity()==null)
			event.setCancelled(true);
	}
	
	
	@EventHandler
	private void PlayerDeath(PlayerDeathEvent event)
	{
		final Player player = event.getEntity();
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
		if(configYaml.getBoolean("Death.SystemOn"))
		{
			main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setDeath(true);
		    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
		    {
		      public void run()
		      {
		        PacketPlayInClientCommand packet = new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN);
		        ((CraftPlayer)player).getHandle().playerConnection.a(packet);
		      }
		    }, 1L);
		}
		main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setLastDeathPoint(new Location(event.getEntity().getLocation().getWorld(), event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), event.getEntity().getLocation().getYaw(), event.getEntity().getLocation().getPitch()));
		if(event.getKeepInventory()==false)
		{
			List<ItemStack> Ilist = event.getDrops();
			event.setKeepInventory(true);
			event.getEntity().getInventory().clear();
			for(int count = 0; count < Ilist.size(); count++)
			{
				ItemStack IT = Ilist.get(count);
				if(IT.isSimilar(Main_ServerOption.DeathRevive))
				{
					Ilist.remove(count);
					event.getEntity().getInventory().addItem(IT);
				}
				
				/*
				else if(IT.hasItemMeta() == true)
					if(IT.getItemMeta().hasLore() == true)
						if(IT.getItemMeta().getLore().size() >= 4)
							if(IT.getItemMeta().getLore().get(3).equals("��e[Ŭ���� �����Կ��� ����]")==true)
								Ilist.remove(count);
				*/
			}
			for(int count = 0; count < Ilist.size(); count++)
				new event.Main_ItemDrop().CustomItemDrop(event.getEntity().getLocation(), Ilist.get(count));
		}
		return;
	}
	
	@EventHandler
	private void KeepItemDurability(PlayerItemDamageEvent event)
	{
		ItemStack item = event.getItem();
		if(item.hasItemMeta())
			if(item.getItemMeta().hasLore() == true)
				if(item.getItemMeta().getLore().toString().contains("������"))
					event.setCancelled(true);
		return;
	}
	
    @EventHandler
	private void EntitySpawn(CreatureSpawnEvent event) {new monster.Monster_Spawn().EntitySpawn(event);return;}
    @EventHandler
    private void ITBlock(PlayerInteractEvent event)
    {
		if (event.getAction() == Action.PHYSICAL)
		{
			Block block = event.getClickedBlock();
			if (block != null)
			{
				if (block.getTypeId() == 60) 
				{
					area.Area_Main A = new area.Area_Main();
					String[] Area = A.getAreaName(event.getClickedBlock());
					if(Area != null)
					{
						if(A.getAreaOption(Area[0], (char) 7) == false)
						{
							event.setCancelled(true);
							if(event.getPlayer().isOp() == false)
							{
								SoundEffect.SP(event.getPlayer(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
								event.getPlayer().sendMessage("��c[SYSTEM] : ��e"+ Area[1] + "��c ������ �ִ� �۹��� �� �� �������ϴ�!");
							}
							return;
						}
					}
				}
			}
		}
    	new util.ETC().UpdatePlayerHPMP(event.getPlayer());
    	new event.Main_Interact().PlayerInteract(event);
    	return;
    }
    @EventHandler
    private void ITEnity(PlayerInteractEntityEvent event)
    {
    	new util.ETC().UpdatePlayerHPMP(event.getPlayer());
    	new main.Main_ServerOption().CitizensCatch();
    	new event.Main_Interact().PlayerInteractEntity(event);
    	return;
    }
    
    @EventHandler
    private void ItemGetMessage(PlayerPickupItemEvent event) {new event.Main_Interact().PlayerGetItem(event);}
	@EventHandler
	private void MonsterKill(EntityDeathEvent event)	{new monster.Monster_Kill().MonsterKilling(event);return;}

	@EventHandler
	private void EntityExplode(EntityExplodeEvent event)
	{
		if(Main_ServerOption.AntiExplode || event.getEntity().getLocation().getWorld().getName().equals("Dungeon"))
			event.blockList().clear();
	}
	
	@EventHandler
	private void ExplosionPrime(ExplosionPrimeEvent event)
	{
		if(event.getEntity().getLocation().getWorld().getName().equals("Dungeon"))
		{
			if(event.getEntityType()==EntityType.ENDER_CRYSTAL || event.getEntityType()==EntityType.DRAGON_FIREBALL
					|| event.getEntityType()==EntityType.FIREBALL || event.getEntityType()==EntityType.SMALL_FIREBALL)
				return;
			else
			{
				event.setCancelled(true);
				new monster.Monster_Kill().Boomb(event.getEntity());
				new monster.Monster_Kill().DungeonKilled((LivingEntity)event.getEntity(), true);
			}
			event.getEntity().remove();
		}
	}
	
	@EventHandler
	private void onArrowHitBlock(ProjectileHitEvent event)
	{
		if(event.getEntity().getLocation().getWorld().getName().equals("Dungeon"))
		{
			if(event.getEntity().getType()==EntityType.ARROW)
			{
				Arrow a = (Arrow)event.getEntity();
				if(a.getShooter() instanceof Player)
				{
					Player player = (Player)a.getShooter();
					if(player.isOnline())
					{
						Location down = new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ());
						Block b = null;
						int yaw = (int) event.getEntity().getLocation().getYaw();
						for(int count = 0; count < 2; count++)
						{
							if(yaw >= -46 && yaw <=45)
								down.add(0, 0, 1);
							else if(yaw >= 46 && yaw <= 135)
								down.add(1, 0, 0);
							else if(yaw >= -136 && yaw <= -45)
								down.add(-1, 0, 0);
							else
								down.add(0, 0, -1);
							b = down.getBlock();
							if(b.getTypeId()!=0)
								break;
						}
						if(b.getTypeId() == 146)
							new dungeon.Dungeon_Main().TrapChestOpen(b);
						else if(b.getTypeId() == 95)
							new dungeon.Dungeon_Main().TrapGlassTouch(b, player);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void applyHealthRegen(EntityRegainHealthEvent event)
	{
		if (event.isCancelled())
			return;
		if (((event.getEntity() instanceof Player)) &&(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED))
	    {
	    	util.ETC ETC = new util.ETC();
	    	ETC.UpdatePlayerHPMP((Player)event.getEntity());
	    }
		return;
	}
	
	@EventHandler
	private void InventoryClick(InventoryClickEvent event)
	{
		if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == true &&Main_ServerOption.MagicSpellsCatched==true)
		{
			util.ETC ETC = new util.ETC();
			ETC.UpdatePlayerHPMP((Player)event.getWhoClicked());
		}
		if(event.getClickedInventory() == null || event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR)
			return;
		if(event.getCurrentItem().hasItemMeta())
		{
			if(event.getCurrentItem().getItemMeta().hasLore())
			{
				if(event.getCurrentItem().getItemMeta().getLore().size() == 4)
				{
					if(event.getCurrentItem().getItemMeta().getLore().get(3).equals(("��e[Ŭ���� �����Կ��� ����]")))
					{
						event.setCancelled(true);
						event.getWhoClicked().getInventory().setItem(event.getSlot(), null);
						SoundEffect.SP((Player)event.getWhoClicked(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.9F);
						return;
					}
				}
			}
		}
		if(event.getInventory().getName().charAt(0)=='��')
		{
			String InventoryCode = event.getInventory().getName().split("��r")[0].replaceAll("��", "");
			//[��0] [��0��0] [��0��0] [��r]
			//1��° �� �ڵ� ǥ = Ŭ���� �̺�Ʈ ĵ�� ����
			//2,3��° �� �ڵ� ǥ = �ش� GUIȭ�� Ÿ��
			//4,5��° �� �ڵ� ǥ = �ش� GUIȭ�� Ÿ�� ��, �� ��° GUI����
			//6��° �ǵ��� �� �ڵ� ǥ = split�� ���� �ڵ�
			
			//1��° �� �ڵ尡 0�̸�, Ŭ���� ������ ��ҵǴ� ��
			if(event.getInventory().getName().charAt(1)=='0')
				event.setCancelled(true);
			new event.Main_InventoryClick().InventoryClickRouter(event, InventoryCode);
		}
		return;
	}
	
	@EventHandler
	private void InventoryClose(InventoryCloseEvent event)
	{
		if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == true
		&&Main_ServerOption.MagicSpellsCatched==true)
		{
			util.ETC ETC = new util.ETC();
			ETC.UpdatePlayerHPMP((Player)event.getPlayer());
		}

		if(event.getInventory().getName().charAt(0)=='��')
		{
			String InventoryCode = event.getInventory().getName().split("��r")[0].replaceAll("��", "");
			//[��0] [��0��0] [��0��0] [��r]
			//1��° �� �ڵ� ǥ = Ŭ���� �̺�Ʈ ĵ�� ����
			//2,3��° �� �ڵ� ǥ = �ش� GUIȭ�� Ÿ��
			//4,5��° �� �ڵ� ǥ = �ش� GUIȭ�� Ÿ�� ��, �� ��° GUI����
			//6��° �ǵ��� �� �ڵ� ǥ = split�� ���� �ڵ�
			new event.Main_InventoryClose().InventoryCloseRouter(event, InventoryCode);
		}
		return;
	}

	public boolean onCommand(CommandSender talker, org.bukkit.command.Command command, String string, String[] args)
    {
		new main.Main_ServerOption().MagicSpellCatch();
		new main.Main_ServerOption().CitizensCatch();
		for(int count = 0; count <args.length; count++)
			args[count] = ChatColor.translateAlternateColorCodes('&', args[count]);
		
		if(talker instanceof Player)
		{
			Player player = (Player) talker;
			
			
			switch(string)
			{
				case"gui���":
				case"gbdenablegui":
					if(player.isOp() == true)
					{
					 	SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_VILLAGER_YES, 1.0F, 1.8F);
					    player.sendMessage("��a[NPC] : GUI�� Ȱ��ȭ ��ų NPC�� ��Ŭ�� �ϼ���!");
					    new UserData_Object().setInt(player, (byte) 4, 114);
					}
					else
					{
						talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
						SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					}
					return true;
				case"ģ��":
				case"gbdfriend":
				 	SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				 	new user.ETC_GUI().FriendsGUI(player, (short) 0);
					return true;
				case"��ų":
				case"gbdskill":
				 	SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				    skill.UserSkill_GUI PSKGUI = new skill.UserSkill_GUI();
					PSKGUI.MainSkillsListGUI(player, (short) 0);
					return true;
		  		case "������" :
		  		case "gbditem" :
		  			customitem.CustomItem_Command ItemC = new customitem.CustomItem_Command();
		  			if(args.length <= 0)
		  			{
		  				if(args.length <=0)
		  				{
		  					ItemC.HelpMessage(player);
			  				return true;	
		  				}
		  				if(ChatColor.stripColor(args[0]).equalsIgnoreCase("��������") ==true)
		  				 ItemC.onCommand2(talker, command, string, args);
		  				else
		  				{
		  					ItemC.HelpMessage(player);
			  				return true;	
		  				}
		  			}
		  			 if(ChatColor.stripColor(args[0]).equalsIgnoreCase("���") == false&& ChatColor.stripColor(args[0]).equalsIgnoreCase("���") == false&& ChatColor.stripColor(args[0]).equalsIgnoreCase("����") == false&& ChatColor.stripColor(args[0]).equalsIgnoreCase("�ޱ�") == false&&ChatColor.stripColor(args[0]).equalsIgnoreCase("�ֱ�") == false)
		  				 ItemC.onCommand2(talker, command, string, args);
		  			 else
				  		ItemC.onCommand1(talker, command, string, args);
		  			break;
		  		case "��Ƽ":
		  		case "gbdparty":
		  			party.Party_Command PartyC = new party.Party_Command();
		  			PartyC.onCommand(talker, command, string, args);
		  			return true;
				case "�׽�Ʈ":
				case "�׽�Ʈ2":
		  		case "Ÿ���߰�":
				case "��ƼƼ����":
				case "����������":
		  		case "����ö��":
		  		case "���ǹڽ�":
		  		case "�����ʱ�ȭ��":
		  		case "����":
		  		case "����ġ�ֱ�":
				case "gbdtest":
				case "gbdtest2":
				case "gbdaddtype":
				case "gbdremoveentity":
				case "gbdremoveitem":
				case "gbdforceremove":
				case "opbox":
				case "gbdbacktothenewbie":
				case "giveexp":
		  			new admin.Admin_Command().onCommand(player, args, string);
		  			return true;
				case "����":
				case "����":
		  		case "��":
				case "gbdaccept":
				case "gbddeny":
		  		case "gbdmoney":
		  			new user.User_Command().onCommand(player, args, string);
		  			return true;
		  		case "����":
		  		case "gbdstat":
				 	SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				 	new user.Stats_GUI().StatusGUI((Player)talker);
					return true;
		  		case "�ɼ�":
		  		case "gbdoption":
				 	SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				 	new user.Option_GUI().optionGUI((Player)talker);
					return true;
		  		case "��Ÿ":
		  		case "gbdetc":
				 	SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				 	new user.ETC_GUI().ETCGUI_Main((Player) talker);
					return true;
		  		case "����" :
		  		case "gbdmobs" :
				  if(talker.isOp() == true)
				  {
					  SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
		  			new monster.Monster_GUI().MonsterListGUI(player, 0);
				  }
				  else
				  {
					  talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
					  SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				  }
		  			return true;
		  		case "����":
		  		case "gbdwarp":
		  			warp.Warp_Command WarpC = new warp.Warp_Command();
		  			WarpC.onCommand(talker, command, string, args);
		  			return true;
		  		case "����":
		  		case "gbdarea":
		  			area.Area_Command AreaC = new area.Area_Command();
		  			AreaC.onCommand(talker, command, string, args);
		  			return true;
		  		case "����":
		  		case "gbdshop":
		  			npc.NPC_Command NPCC = new npc.NPC_Command();
		  			NPCC.onCommand(talker, command, string, args);
		  			return true;
		  		case "����Ʈ":
		  		case "gbdquest":
		  			quest.Quest_Command QC = new quest.Quest_Command();
		  			QC.onCommand(talker, command, string, args);
		  			return true;
		  		case "Ŀ�ǵ�":
		  		case "gbdcommand":
		  			if(player.isOp() == true)
		  			{
		  				UserData_Object u = new UserData_Object();
						if(u.getType(player)!=null&&u.getType(player).equals("Skill"))
						{
							if(u.getString(player, (byte)1).equalsIgnoreCase("SKC"))
							{
								String CommandString = "";
								for(int count = 0; count <args.length-1; count ++)
									CommandString = CommandString+args[count]+" ";
								CommandString = CommandString+args[args.length-1];
							  	YamlLoader skillYaml = new YamlLoader();
								skillYaml.getConfig("Skill/SkillList.yml");
								if(CommandString.contains("/")==false)
									CommandString = "/"+CommandString;
								if(CommandString.equalsIgnoreCase("/����"))
									skillYaml.set(u.getString(player, (byte)2)+".SkillRank."+u.getInt(player, (byte)4)+".Command","null");
								else
									skillYaml.set(u.getString(player, (byte)2)+".SkillRank."+u.getInt(player, (byte)4)+".Command",CommandString);
								skillYaml.saveConfig();
								skill.OPboxSkill_GUI SKGUI = new skill.OPboxSkill_GUI();
								SKGUI.SkillRankOptionGUI(player, u.getString(player, (byte)2), (short) u.getInt(player, (byte)4));
								u.clearAll(player);
							}
						}
						else
						{
		  					player.sendMessage("��c[��ų ����] : �� ��ɾ�� ��ų ������ ���˴ϴ�!");
							SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  					return true;
						}
		  			}
					else
					{
						talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
						SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					}
					return true;
			}
			return false;
		}
		else
		{
			if(string.equals("����")||string.equals("giveexp")||string.equals("����ġ�ֱ�"))
			{
				if(args.length==2)
				{
  					Player target = Bukkit.getServer().getPlayer(args[0]);
	  				if(target.isOnline())
	  				{
	  					int EXP = 0;
	  					try
	  					{
	  						EXP = Integer.parseInt(args[1]);
	  					}
	  					catch(NumberFormatException e)
	  					{
	  					  	Bukkit.getConsoleSender().sendMessage("��c[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���!");
		  					return true;
	  					}
	  					main.Main_ServerOption.PlayerList.get(target.getUniqueId().toString()).addStat_MoneyAndEXP(0, EXP, true);
  					  	Bukkit.getConsoleSender().sendMessage("��a[SYSTEM] : " + args[0] + "�Կ��� ����ġ " + EXP + "�� �����Ͽ����ϴ�!");
	  				}
	  				else
	  				{
					  	Bukkit.getConsoleSender().sendMessage("��c[SYSTEM] : �ش� �÷��̾�� �������� �ƴմϴ�!");
	  				}
				}
				else
				{
					  	Bukkit.getConsoleSender().sendMessage("��c[SYSTEM] : /���� [�г���] [����ġ]");
				}
			}
		}
		return false;
    }

	@EventHandler
	public void Sign(SignChangeEvent event)
	{
		for (int i = 0; i <= 3; i++)
	    {
			String line = event.getLine(i);
	        line = ChatColor.translateAlternateColorCodes('&', line);
	        event.setLine(i, line);
	    }
	}
}