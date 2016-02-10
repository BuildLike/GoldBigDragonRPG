package GoldBigDragon_RPG.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import GoldBigDragon_RPG.Util.YamlController;
import GoldBigDragon_RPG.Util.YamlManager;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;

public class Main extends JavaPlugin implements Listener
{
	public static YamlController YC_1,YC_2,YC_3;

	public static String serverUpdate = "2016-02-11-00:52";
	public static String serverVersion = "Advanced";
	private static String updateCheckURL = "https://goldbigdragon.github.io/";
	public static String currentServerUpdate = "2016-02-11-00:52";
	public static String currentServerVersion = "Advanced";
	
	public static String SpawnMobName;

	public static java.util.Map<Long, PartyDataObject> Party = new LinkedHashMap<Long, PartyDataObject>();
	public static java.util.Map<Player, Long> PartyJoiner = new LinkedHashMap<Player, Long>();
	
	public static HashMap<Player, Location> catchedLocation1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> catchedLocation2 = new HashMap<Player, Location>();
	
	public static HashMap<Player, String> PlayerUseSpell = new HashMap<Player, String>();
	public static HashMap<Player, ItemStack> PlayerlastItem = new HashMap<Player, ItemStack>();
	public static HashMap<Player, String> PlayerCurrentArea = new HashMap<Player, String>();
		
	public static boolean MagicSpellsCatched = false;
	public static boolean MagicSpellsEnable = false;
	public static boolean CitizensCatched = false;
	public static boolean NoteBlockAPI = false;
	public static boolean NoteBlockAPIAble = false;
	
	public static boolean spawntime = true;
	public static boolean Mapping = false;
	
	public void onEnable()
	{
		new GoldBigDragon_RPG.Effect.Corpse().setJavaPlugin(this);
		getServer().getPluginManager().registerEvents(this, this);
		YC_1 = new YamlController(this);
		YC_2 = new YamlController(this);
		YC_3 = new YamlController(this);
	  	File MusicFolder = new File(this.getDataFolder().getAbsolutePath() + "/NoteBlockSound/");
		if(!MusicFolder.exists())
			MusicFolder.mkdirs();
		new GoldBigDragon_RPG.Util.SendString().SendForBukkit(0);
	  	GoldBigDragon_RPG.Config.configConfig config = new GoldBigDragon_RPG.Config.configConfig();
	  	config.CreateNewConfig(YC_1);
	  	config.CreateMapImageConfig(YC_1);
	  	if(YC_1.isExit("Skill/SkillList.yml") == false)
	  	  new GoldBigDragon_RPG.Config.SkillConfig().CreateNewSkillList();
	  	if(YC_1.isExit("Skill/JobList.yml") == false)
	  		new GoldBigDragon_RPG.Config.SkillConfig().CreateNewJobList();
	  	if(YC_1.isExit("ETC/NewBie.yml") == false)
	  		new GoldBigDragon_RPG.Config.NewBieConfig().CreateNewConfig();
	  	new GoldBigDragon_RPG.Config.NPCconfig().NPCscriptExample();
	  	
	  	new PartyDataManager().loadParty();
	  	
		YamlManager WorldConfig = YC_2.getNewConfig("WorldList.yml");
		Object[] worldname = WorldConfig.getKeys().toArray();
		for(int count = 0; count < WorldConfig.getKeys().size();count++)
			if(Bukkit.getWorld(worldname[count].toString()) == null)
				WorldCreator.name(worldname[count].toString()).createWorld();
		
		VersionCheck();
		UpdateConfig();
		NoteBlockAPICatch();
		
		new GoldBigDragon_RPG.ServerTick.ServerTickMain(this);
		new GoldBigDragon_RPG.ServerTick.ServerTickScheduleManager().loadCategoriFile();
		new GoldBigDragon_RPG.Main.ServerOption().Initialize();
	  	return;
	}
	public void onDisable()
	{
		new GoldBigDragon_RPG.Effect.Corpse().RemoveAllCorpse();
		if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
		{
	    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
	    	Player[] a = new Player[playerlist.size()];
	    	playerlist.toArray(a);
	    	for(int count = 0; count <a.length;count++)
	    		new OtherPlugins.NoteBlockAPIMain().Stop(a[count]);
		}
	  	new PartyDataManager().saveParty();

		new GoldBigDragon_RPG.ServerTick.ServerTickScheduleManager().saveCategoriFile();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Clossing GoldBigDragon Advanced...]");
	  	return;
	}
	
	public void VersionCheck()
	{
		BufferedInputStream in = null;
		StringBuffer sb = new StringBuffer();
		try
		{
			URL url = new URL(updateCheckURL);
			URLConnection urlConnection = url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream());
			byte[] bufRead = new byte[10];
			int lenRead = 0;
			
			while ((lenRead = in.read(bufRead)) >0)
				sb.append(new String(bufRead, 0, lenRead));
			String[] Parsed = sb.toString().split("<br>");
			
			String Version = Parsed[1].split(": ")[1];
			String Update = Parsed[2].split(": ")[1];
			
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "�ֽ� ���� : "+Version);
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "���� ���� : "+serverVersion);
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "�ֽ� ��ġ : "+Update);
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "���� ��ġ : "+serverUpdate);

			currentServerUpdate = Update;
			currentServerVersion = Version;
			if(serverVersion.compareTo(Version)==0&&serverUpdate.compareTo(Update)==0)
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "���� GoldBigDragonRPG�� �ֽ� �����Դϴ�!");
			else
			{
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "�ֽ� ������ �ƴմϴ�! �Ʒ� �ּҿ��� �ٿ�ε� ��������!");
		  		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "http://cafe.naver.com/goldbigdragon/40109");
			}
			
		}
		catch (IOException ioe)
		{
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[���� üũ ����] ���ͳ� ������ Ȯ�� �� �ּ���!");
		}
	}
	
	public void UpdateConfig()
	{
		YamlManager Config =YC_2.getNewConfig("config.yml");
		if(Config.contains("Server.CustomWeaponBreak")==false)
		{
			Config.set("Server.CustomWeaponBreak", true);
			Config.saveConfig();
		}
	}
	
	@EventHandler
	private void PlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(new GoldBigDragon_RPG.Effect.Corpse().DeathCapture(player,true))
			new GoldBigDragon_RPG.Effect.Corpse().CreateCorpse(player);
		new GoldBigDragon_RPG.Effect.Corpse().ShowCorpse(player);
		MagicSpellCatch();
		CitizensCatch();

		new UserDataObject().UserDataInit(player);
		
		if(player.isOp() == true)
			new GoldBigDragon_RPG.Effect.PacketSender().sendTitleSubTitle(player,"\'��e/���ǹڽ�\'", "\'��eGoldBigDragonAdvanced ���̵� �� ���� ������ �����մϴ�.\'", 1,10, 1);
		else
		{
			YamlManager Config = YC_2.getNewConfig("config.yml");
			if(Config.getInt("Event.DropChance")>=2||Config.getInt("Event.Multiple_EXP_Get")>=2||Config.getInt("Event.Multiple_Level_Up_StatPoint")>=2||Config.getInt("Event.Multiple_Level_Up_SkillPoint")>=2)
			{
				String alert = "[";
				if(Config.getInt("Event.DropChance")>=2)
					alert =alert+ "��ӷ� ���� "+Config.getInt("Event.DropChance")+"��";
				if(Config.getInt("Event.DropChance")>=2)
					alert = alert+", ";
				if(Config.getInt("Event.Multiple_EXP_Get")>=2)
					alert = alert + "����ġ " + Config.getInt("Event.Multiple_EXP_Get")+"�� ȹ��";
				if(Config.getInt("Event.Multiple_EXP_Get")>=2)
					alert = alert+", ";
				if(Config.getInt("Event.Multiple_Level_Up_StatPoint")>=2)
					alert = alert +"���� ����Ʈ "+Config.getInt("Event.Multiple_Level_Up_StatPoint")+"�� ȹ��";
				if(Config.getInt("Event.Multiple_Level_Up_StatPoint")>=2)
					alert = alert+", ";
				if(Config.getInt("Event.Multiple_Level_Up_SkillPoint")>=2)
					alert = alert +"��ų ����Ʈ " +Config.getInt("Event.Multiple_Level_Up_SkillPoint")+"�� ȹ��";
				alert = alert+"]";
				new GoldBigDragon_RPG.Effect.PacketSender().sendTitleSubTitle(player, "\'���� �̺�Ʈ�� �������Դϴ�.\'", "\'"+alert+"\'", 1, 10, 1);
			}
		}
	  	if(YC_1.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  	    new GoldBigDragon_RPG.Config.StatConfig().CreateNewStats(player);
	  	if(YC_1.isExit("Quest/PlayerData/" + player.getUniqueId()+".yml") == false)
	  	{
	  	    new GoldBigDragon_RPG.Config.QuestConfig().CreateNewPlayerConfig(player);

			YamlManager NewBieYM = YC_1.getNewConfig("ETC/NewBie.yml");
			for(int count = 0; count < YC_1.getNewConfig("ETC/NewBie.yml").getConfigurationSection("SupportItem").getKeys(false).toArray().length;count++)
				if(NewBieYM.getItemStack("SupportItem."+count) != null)
					player.getInventory().addItem(NewBieYM.getItemStack("SupportItem."+count));
			player.teleport(new Location(Bukkit.getWorld(NewBieYM.getString("TelePort.World")), NewBieYM.getInt("TelePort.X"), NewBieYM.getInt("TelePort.Y"), NewBieYM.getInt("TelePort.Z"), NewBieYM.getInt("TelePort.Yaw"), NewBieYM.getInt("TelePort.Pitch")));
	  	}
	  	if(YC_1.isExit("Skill/PlayerData/" + player.getUniqueId()+".yml") == false)
	  		new GoldBigDragon_RPG.Config.SkillConfig().CreateNewPlayerSkill(player);
	  	
	  	new GoldBigDragon_RPG.ETC.Job().PlayerFixAllSkillAndJobYML(player);
		new GoldBigDragon_RPG.Util.ETC().UpdatePlayerHPMP(event.getPlayer());
    	new GoldBigDragon_RPG.GUI.EquipGUI().FriendJoinQuitMessage(player, true);

		if(YC_2.getNewConfig("config.yml").getString("Server.JoinMessage") != null)
			event.setJoinMessage(YC_2.getNewConfig("config.yml").getString("Server.JoinMessage").replace("%player%",event.getPlayer().getName()));
		else
			event.setJoinMessage(null);
	}
	
	@EventHandler
	private void PlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		if(new GoldBigDragon_RPG.Effect.Corpse().DeathCapture(player,false))
			new GoldBigDragon_RPG.Effect.Corpse().RemoveCorpse(player.getName());
		
		if(PartyJoiner.containsKey(player))
			Party.get(PartyJoiner.get(player)).QuitParty(player);
		
		if(NoteBlockAPIAble == true)
			new OtherPlugins.NoteBlockAPIMain().Stop(event.getPlayer());
		
		YamlManager UserData = YC_1.getNewConfig("UserData/"+ player.getUniqueId()+".yml");
		UserData.removeKey("Data");
		UserData.saveConfig();
		PlayerCurrentArea.remove(player);
    	new GoldBigDragon_RPG.GUI.EquipGUI().FriendJoinQuitMessage(player, false);

		YamlManager Config = YC_1.getNewConfig("config.yml");
		if(Config.getString("Server.QuitMessage") != null)
		{
			String message = Config.getString("Server.QuitMessage").replace("%player%",event.getPlayer().getName());
			event.setQuitMessage(message);
		}
		else
			event.setQuitMessage(null);
	}
	
	@EventHandler
	private void PlayerRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
	  	YamlManager YM = YC_1.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		double X = YM.getDouble("LastDeathPoint.X");
		double Y = YM.getDouble("LastDeathPoint.Y");
		double Z = YM.getDouble("LastDeathPoint.Z");
		double Pitch = YM.getDouble("LastDeathPoint.Pitch");
		double Yaw = YM.getDouble("LastDeathPoint.Yaw");
    	event.setRespawnLocation(new Location(Bukkit.getServer().getWorld(YM.getString("LastDeathPoint.World")), X, Y, Z, (float)Yaw, (float)Pitch));
    	player.setGameMode(GameMode.SPECTATOR);
    	if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") == true)
    	{
    		new OtherPlugins.NoteBlockAPIMain().Stop(player);
			YamlManager Config = YC_1.getNewConfig("config.yml");
			if(Config.contains("Death.Track"))
				if(Config.getInt("Death.Track")!=-1)
					new OtherPlugins.NoteBlockAPIMain().Play(player, Config.getInt("Death.Track"));
    	}
    	new GoldBigDragon_RPG.GUI.DeathGUI().OpenReviveSelectGUI(player);
		return;
	}
	
	@EventHandler
	private void Move(PlayerMoveEvent event){GoldBigDragon_RPG.Event.PlayerAction PA = new GoldBigDragon_RPG.Event.PlayerAction();PA.PlayerMove(event);return;}

	@EventHandler
	private void HotBarMove(PlayerItemHeldEvent event)
	{
		//if(MagicSpellsCatched == false)
		//	MagicSpellCatch();
		new GoldBigDragon_RPG.Event.ChangeHotBar().HotBarMove(event);
		return;
	}
	
	@EventHandler
	private void PlayerItemDrop(PlayerDropItemEvent event)
	{
		ItemStack IT = event.getItemDrop().getItemStack();
		if(IT.hasItemMeta() == true)
			if(IT.getItemMeta().hasLore() == true)
				if(IT.getItemMeta().getLore().size() == 4)
					if(IT.getItemMeta().getLore().get(3).equals(ChatColor.YELLOW+"[Ŭ���� �����Կ��� ����]")==true)
						event.setCancelled(true);
		return;
	}
	
	@EventHandler
	private void PlayerChatting(PlayerChatEvent event)
	{
		if(event.getMessage().contains("&") == true)
		{
			event.setMessage(event.getMessage().replaceAll("&l", ChatColor.BOLD+""));
			event.setMessage(event.getMessage().replaceAll("&0", ChatColor.BLACK+""));
			event.setMessage(event.getMessage().replaceAll("&1", ChatColor.DARK_BLUE+""));
			event.setMessage(event.getMessage().replaceAll("&2", ChatColor.DARK_GREEN+""));
			event.setMessage(event.getMessage().replaceAll("&3", ChatColor.DARK_AQUA+""));
			event.setMessage(event.getMessage().replaceAll("&4", ChatColor.DARK_RED+""));
			event.setMessage(event.getMessage().replaceAll("&5", ChatColor.DARK_PURPLE+""));
			event.setMessage(event.getMessage().replaceAll("&6", ChatColor.GOLD+""));
			event.setMessage(event.getMessage().replaceAll("&7", ChatColor.GRAY+""));
			event.setMessage(event.getMessage().replaceAll("&8", ChatColor.DARK_GRAY+""));
			event.setMessage(event.getMessage().replaceAll("&9", ChatColor.BLUE+""));
			event.setMessage(event.getMessage().replaceAll("&a", ChatColor.GREEN+""));
			event.setMessage(event.getMessage().replaceAll("&b", ChatColor.AQUA+""));
			event.setMessage(event.getMessage().replaceAll("&c", ChatColor.RED+""));
			event.setMessage(event.getMessage().replaceAll("&d", ChatColor.LIGHT_PURPLE+""));
			event.setMessage(event.getMessage().replaceAll("&e", ChatColor.YELLOW+""));
			event.setMessage(event.getMessage().replaceAll("&f", ChatColor.WHITE+""));
		}
		GoldBigDragon_RPG.Event.PlayerAction PA = new GoldBigDragon_RPG.Event.PlayerAction();
		PA.PlayerChatting(event);
		return;
	}

	@EventHandler
	private void PlayerFishing(PlayerFishEvent event)
	{
		GoldBigDragon_RPG.Event.Fishing F = new GoldBigDragon_RPG.Event.Fishing();
		F.PlayerFishing(event);
		return;
	}
	
	@EventHandler
	private void Map(MapInitializeEvent event)
	{
		new GoldBigDragon_RPG.ETC.Map().onMap(event);
		return;
	}
	
	
	@EventHandler
	private void PlayerDeath(PlayerDeathEvent event)
	{
		final Player player = event.getEntity();
	  	YamlManager YM = YC_1.getNewConfig("Stats/" + player.getUniqueId()+".yml");
	  	YM.set("Death",true);
	  	YM.set("LastDeathPoint.World",event.getEntity().getLocation().getWorld().getName());
	  	YM.set("LastDeathPoint.X",event.getEntity().getLocation().getX());
	  	YM.set("LastDeathPoint.Y",event.getEntity().getLocation().getY());
	  	YM.set("LastDeathPoint.Z",event.getEntity().getLocation().getZ());
	  	YM.set("LastDeathPoint.Pitch",event.getEntity().getLocation().getPitch());
	  	YM.set("LastDeathPoint.Yaw",event.getEntity().getLocation().getYaw());
	  	YM.saveConfig();
		//��ų ����Ű ������ ��� ����//
		List<ItemStack> Ilist = event.getDrops();
		for(int count = 0; count < Ilist.size(); count++)
		{
			ItemStack IT = Ilist.get(count);
			if(IT.hasItemMeta() == true)
			{
				if(IT.getItemMeta().hasLore() == true)
				{
					if(IT.getItemMeta().getLore().size() >= 4)
					{
						if(IT.getItemMeta().getLore().get(3).equals(ChatColor.YELLOW+"[Ŭ���� �����Կ��� ����]")==true)
						{
							Ilist.set(count,new ItemStack(0));
						}
					}
				}
			}
		}
	    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
	    {
	      public void run()
	      {
	        PacketPlayInClientCommand packet = new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN);
	        ((CraftPlayer)player).getHandle().playerConnection.a(packet);
	      }
	    }, 1L);
	  	
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
	
	public void MagicSpellCatch()
	{
		if(MagicSpellsCatched == false)
		{
			MagicSpellsCatched = true;
			if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == false)
			{
				new GoldBigDragon_RPG.Util.SendString().SendForBukkit(1);
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "MagicSpells �÷������� ã�� �� �����ϴ�!");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "MagicSpells �ٿ�ε� URL");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "http://nisovin.com/magicspells/Start");
			}
			else
			{
				MagicSpellsEnable = true;
				OtherPlugins.SpellMain MS = new OtherPlugins.SpellMain(this);
			}
		}
		return;
	}
	
	public void CitizensCatch()
	{
		if(CitizensCatched == false)
		{
			CitizensCatched = true;
			if(Bukkit.getPluginManager().isPluginEnabled("Citizens") == false)
			{
				new GoldBigDragon_RPG.Util.SendString().SendForBukkit(1);
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Citizens �÷������� ã�� �� �����ϴ�!");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Citizens �ٿ�ε� URL");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "http://www.curse.com/bukkit-plugins/minecraft/citizens#t1:other-downloads");
			}
			else
			{
				OtherPlugins.CitizensMain CZ = new OtherPlugins.CitizensMain(this);
			}
		}
		return;
	}
	
	public void NoteBlockAPICatch()
	{
		if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
			new OtherPlugins.NoteBlockAPIMain(this);
		return;
	}
	
	public void NoteBlockAPICatchPJ()
	{
		if(NoteBlockAPI == false)
		{
			NoteBlockAPI = true;
			if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") == false)
			{
				new GoldBigDragon_RPG.Util.SendString().SendForBukkit(1);
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "��Ʈ��� ��� �÷������� ã�� �� �����ϴ�!");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "NoteBlockAPI �ٿ�ε� URL");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "http://www.curse.com/bukkit-plugins/minecraft/noteblockapi");
			}
			else
			{
				new OtherPlugins.NoteBlockAPIMain(this);
			}
		}
		return;
	}
	
	@EventHandler
	private void RA(EntityShootBowEvent event) {new GoldBigDragon_RPG.Event.Attack().RangeAttack(event);return;}
    @EventHandler
    private void Attack(EntityDamageByEntityEvent event) {new GoldBigDragon_RPG.Event.Attack().AttackRouter(event);return;}
    @EventHandler
	private void EntitySpawn(CreatureSpawnEvent event) {new GoldBigDragon_RPG.Event.MonsterSpawn().EntitySpawn(event);return;}
    @EventHandler
    private void ITBlock(PlayerInteractEvent event)
    {
    	GoldBigDragon_RPG.Util.ETC ETC = new GoldBigDragon_RPG.Util.ETC();
    	ETC.UpdatePlayerHPMP(event.getPlayer());
    	new GoldBigDragon_RPG.Event.Interact().PlayerInteract(event);
    	return;
    }
    @EventHandler
    private void ITEnity(PlayerInteractEntityEvent event)
    {
    	GoldBigDragon_RPG.Util.ETC ETC = new GoldBigDragon_RPG.Util.ETC();
    	ETC.UpdatePlayerHPMP(event.getPlayer());
    	CitizensCatch();
    	new GoldBigDragon_RPG.Event.Interact().PlayerInteractEntity(event);
    	return;
    }
    @EventHandler
    private void ItemGetMessage(PlayerPickupItemEvent event) {new GoldBigDragon_RPG.Event.Interact().PlayerGetItem(event);}
	@EventHandler
	private void MonsterKill(EntityDeathEvent event)	{new GoldBigDragon_RPG.Event.MonsterKill().MonsterKill(event);return;}
	@EventHandler
	private void BBreak(BlockBreakEvent event)
	{
		new GoldBigDragon_RPG.Event.BlockBreak().BlockBreaking(event);return;
	}
	@EventHandler
	private void BlockPlace(BlockPlaceEvent event){GoldBigDragon_RPG.Event.BlockPlace BP = new GoldBigDragon_RPG.Event.BlockPlace();BP.BlockPlace(event);return;}
	
	@EventHandler
	private void applyHealthRegen(EntityRegainHealthEvent event)
	{
		if (event.isCancelled())
			return;
		if (((event.getEntity() instanceof Player)) &&(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED))
	    {
	    	GoldBigDragon_RPG.Util.ETC ETC = new GoldBigDragon_RPG.Util.ETC();
	    	ETC.UpdatePlayerHPMP((Player)event.getEntity());
	    }
		return;
	}
	
	@EventHandler
	private void InventoryClick(InventoryClickEvent event)
	{
		if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == true
		&&MagicSpellsCatched==true)
		{
			GoldBigDragon_RPG.Util.ETC ETC = new GoldBigDragon_RPG.Util.ETC();
			ETC.UpdatePlayerHPMP((Player)event.getWhoClicked());
		}
		if(event.getClickedInventory() == null)
			return;
		if(event.getCurrentItem().hasItemMeta() == true)
		{
			if(event.getCurrentItem().getItemMeta().hasLore() == true)
			{
				if(event.getCurrentItem().getItemMeta().getLore().size() == 4)
				{
					if(event.getCurrentItem().getItemMeta().getLore().get(3).equals((ChatColor.YELLOW+"[Ŭ���� �����Կ��� ����]")))
					{
						event.setCancelled(true);
						event.getWhoClicked().getInventory().setItem(event.getSlot(), new ItemStack(0));
						GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
						s.SP((Player)event.getWhoClicked(), Sound.ANVIL_LAND, 1.0F, 1.9F);
					}
				}
			}
		}
		if(ChatColor.stripColor(event.getWhoClicked().getOpenInventory().getTitle()).compareTo("��ȯ")==0)
			new GoldBigDragon_RPG.GUI.EquipGUI().ExchangeInventoryclick(event);
		
		if(event.getInventory().getName().length() >= 3)
		{
			if(event.getInventory().getName().charAt(0)=='��'
			&&event.getInventory().getName().charAt(1)=='0')
			{
				String InventoryName = ChatColor.stripColor(event.getInventory().getName().toString());
				if(event.getInventory().getType()==InventoryType.CHEST)
				{
					if(!(InventoryName.equals("���� ��� ����")||InventoryName.equals("��ƾ� �� ������ ���")
					||InventoryName.equals("���� ������ ���")||InventoryName.equals("�ʽ��� ����")
					||InventoryName.equals("�ش� ����� ĳ�� ���� ������")||InventoryName.equals("���� �߰� ���")
					||InventoryName.equals("NPC �� ����")||InventoryName.equals("container.chest")
					||InventoryName.equals("container.chestDouble")||InventoryName.equals("�ʽ��� ���̵�")
					||InventoryName.equals("container.minecart")||InventoryName.equals("�̺�Ʈ ��ü ����")
					||InventoryName.equals("�̺�Ʈ ���� ����")||InventoryName.equals("��ȯ")||InventoryName.equals("��Ȱ ������")
					||InventoryName.equals("���� ������")||InventoryName.equals("���� ��ǰ ����")||InventoryName.equals("���� ��� ����")
					))
					{
						event.setCancelled(true);
					}
				}
				
				if(InventoryName.contains("����")||InventoryName.contains("�ɼ�")||InventoryName.contains("������")||
				   InventoryName.equals("��Ÿ")||InventoryName.contains("���̵�")||
				   InventoryName.contains("��Ƽ")||InventoryName.contains("NPC")
				   ||InventoryName.contains("����")||InventoryName.equals("�̺�Ʈ ����")
				   ||InventoryName.contains("���")||InventoryName.contains("����Ʈ")||
				   InventoryName.contains("������")||InventoryName.contains("���")||InventoryName.equals("������Ʈ �߰�")
				   ||InventoryName.contains("���")||InventoryName.contains("����")||InventoryName.contains("������")
				    ||InventoryName.contains("[MapleStory]")||InventoryName.contains("[Mabinogi]")
				    ||InventoryName.contains("��ų")||InventoryName.contains("��ũ")||InventoryName.contains("����")
				    ||InventoryName.contains("��ϵ�")||InventoryName.contains("������")||InventoryName.contains("�ʽ���")
				    ||InventoryName.contains("ī�װ�")||InventoryName.equals("�ش� ����� ĳ�� ���� ������")||InventoryName.contains("����")
				    ||InventoryName.contains("����")||InventoryName.contains("����")||InventoryName.contains("��������")
				    ||InventoryName.contains("�̺�Ʈ")||InventoryName.contains("ģ��")||InventoryName.contains("�׺�")
				    ||InventoryName.equals("��ȯ")||InventoryName.contains("��Ȱ")||InventoryName.contains("����")
				    ||InventoryName.compareTo("���� �ӽ�")==0
				    )
				{
					new GoldBigDragon_RPG.Event.InventoryClick().InventoryClickRouter(event, InventoryName);
				}
			}
		}
		return;
	}
	
	@EventHandler
	private void InventoryClose(InventoryCloseEvent event)
	{
		if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == true
		&&MagicSpellsCatched==true)
		{
			GoldBigDragon_RPG.Util.ETC ETC = new GoldBigDragon_RPG.Util.ETC();
			ETC.UpdatePlayerHPMP((Player)event.getPlayer());
		}
		new GoldBigDragon_RPG.Event.InventoryClose().InventoryCloseRouter(event);
		return;
	}

	public boolean onCommand(CommandSender talker, Command command, String string, String[] args)
    {
		MagicSpellCatch();
    	CitizensCatch();
		for(int count = 0; count <args.length; count++)
		{
			if(args[count].contains("&"))
			{
				args[count]=args[count].replaceAll("&l", ChatColor.BOLD+"");
				args[count]=args[count].replaceAll("&0", ChatColor.BLACK+"");
				args[count]=args[count].replaceAll("&1", ChatColor.DARK_BLUE+"");
				args[count]=args[count].replaceAll("&2", ChatColor.DARK_GREEN+"");
				args[count]=args[count].replaceAll("&3", ChatColor.DARK_AQUA+"");
				args[count]=args[count].replaceAll("&4", ChatColor.DARK_RED+"");
				args[count]=args[count].replaceAll("&5", ChatColor.DARK_PURPLE+"");
				args[count]=args[count].replaceAll("&6", ChatColor.GOLD+"");
				args[count]=args[count].replaceAll("&7", ChatColor.GRAY+"");
				args[count]=args[count].replaceAll("&8", ChatColor.DARK_GRAY+"");
				args[count]=args[count].replaceAll("&9", ChatColor.BLUE+"");
				args[count]=args[count].replaceAll("&a", ChatColor.GREEN+"");
				args[count]=args[count].replaceAll("&b", ChatColor.AQUA+"");
				args[count]=args[count].replaceAll("&c", ChatColor.RED+"");
				args[count]=args[count].replaceAll("&d", ChatColor.LIGHT_PURPLE+"");
				args[count]=args[count].replaceAll("&e", ChatColor.YELLOW+"");
				args[count]=args[count].replaceAll("&f", ChatColor.WHITE+"");
			}
		}
		
		if(talker instanceof Player)
		{
			Player player = (Player) talker;
			GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
			
			switch(string)
			{
				case"gui���":
					if(player.isOp() == true)
					{
					 	s.SP((Player)talker, org.bukkit.Sound.VILLAGER_HAGGLE, 1.0F, 1.8F);
					    player.sendMessage(ChatColor.GREEN+"[NPC] : GUI�� Ȱ��ȭ ��ų NPC�� ��Ŭ�� �ϼ���!");
					    new UserDataObject().setInt(player, (byte) 4, 114);
					}
					else
					{
						talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
						s.SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					}
					return true;
				case"ģ��":
				 	s.SP((Player)talker, org.bukkit.Sound.HORSE_SADDLE, 1.0F, 1.8F);
				 	new GoldBigDragon_RPG.GUI.ETCGUI().FriendsGUI(player, 0);
					return true;
				case"��ų":
				 	s.SP((Player)talker, org.bukkit.Sound.HORSE_SADDLE, 1.0F, 1.8F);
				    GoldBigDragon_RPG.GUI.PlayerSkillGUI PSKGUI = new GoldBigDragon_RPG.GUI.PlayerSkillGUI();
					PSKGUI.MainSkillsListGUI(player, 0);
					return true;
		  		case "������" :
		  			GoldBigDragon_RPG.Command.ItemsCommand ItemC = new GoldBigDragon_RPG.Command.ItemsCommand();
		  			if(args.length <= 0)
		  			{
		  				if(args.length <=0)
		  				{
		  					GoldBigDragon_RPG.Command.HelpMessage HM = new GoldBigDragon_RPG.Command.HelpMessage();
			  				HM.HelpMessager(player, 1);
			  				return true;	
		  				}
		  				if(ChatColor.stripColor(args[0]).equalsIgnoreCase("��������") ==true)
		  				 ItemC.onCommand2(talker, command, string, args);
		  				else
		  				{
		  					GoldBigDragon_RPG.Command.HelpMessage HM = new GoldBigDragon_RPG.Command.HelpMessage();
			  				HM.HelpMessager(player, 1);
			  				return true;	
		  				}
		  			}
		  			 if(ChatColor.stripColor(args[0]).equalsIgnoreCase("���") == false&& ChatColor.stripColor(args[0]).equalsIgnoreCase("���") == false&& ChatColor.stripColor(args[0]).equalsIgnoreCase("����") == false&& ChatColor.stripColor(args[0]).equalsIgnoreCase("�ޱ�") == false&&ChatColor.stripColor(args[0]).equalsIgnoreCase("�ֱ�") == false)
		  				 ItemC.onCommand2(talker, command, string, args);
		  			 else
				  		ItemC.onCommand1(talker, command, string, args);
		  			break;
		  		case "��Ƽ":
		  			GoldBigDragon_RPG.Command.PartyCommand PartyC = new GoldBigDragon_RPG.Command.PartyCommand();
		  			PartyC.onCommand(talker, command, string, args);
		  			return true;
				case "�׽�Ʈ":
				case "�׽�Ʈ2":
				case "��ƼƼ����":
				case "����������":
				case "����":
				case "����":
		  		case "��":
		  			new GoldBigDragon_RPG.Command.SystemCommand().onCommand(player, args, string);
		  			return true;
		  		case "����":
				 	s.SP((Player)talker, org.bukkit.Sound.HORSE_ARMOR, 0.8F, 1.8F);
				 	new GoldBigDragon_RPG.GUI.StatsGUI().StatusGUI((Player)talker);
					return true;
		  		case "�ɼ�":
				 	s.SP((Player)talker, org.bukkit.Sound.HORSE_ARMOR, 0.8F, 1.8F);
				 	new GoldBigDragon_RPG.GUI.OptionGUI().optionGUI((Player)talker);
					return true;
		  		case "��Ÿ":
				 	s.SP((Player)talker, org.bukkit.Sound.HORSE_ARMOR, 0.8F, 1.8F);
				 	new GoldBigDragon_RPG.GUI.ETCGUI().ETCGUI_Main((Player) talker);
					return true;
		  		case "���ǹڽ�":
					  if(talker.isOp() == true)
					  {
						  new UserDataObject().clearAll(player);
				  		  GoldBigDragon_RPG.GUI.OPBoxGUI opgui = new GoldBigDragon_RPG.GUI.OPBoxGUI();
						  s.SP((Player)talker, org.bukkit.Sound.HORSE_ARMOR, 0.8F, 1.8F);
						  opgui.OPBoxGUI_Main((Player)talker,1);
						  return true;
					  }
					  else
					  {
						  talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
						  s.SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
						  return true;
					  }
		  		case "����" :
				  if(talker.isOp() == true)
				  {

					  s.SP((Player)talker, org.bukkit.Sound.HORSE_ARMOR, 0.8F, 1.8F);
		  			new GoldBigDragon_RPG.GUI.MonsterGUI().MonsterListGUI(player, 0);
				  }
				  else
				  {
					  talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
					  s.SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
				  }
		  			return true;
		  		case "����":
		  			GoldBigDragon_RPG.Command.WarpCommand WarpC = new GoldBigDragon_RPG.Command.WarpCommand();
		  			WarpC.onCommand(talker, command, string, args);
		  			return true;
		  		case "����":
		  			GoldBigDragon_RPG.Command.AreaCommand AreaC = new GoldBigDragon_RPG.Command.AreaCommand();
		  			AreaC.onCommand(talker, command, string, args);
		  			return true;
		  		case "����":
		  			GoldBigDragon_RPG.Command.NPCcommand NPCC = new GoldBigDragon_RPG.Command.NPCcommand();
		  			NPCC.onCommand(talker, command, string, args);
		  			return true;
		  		case "����Ʈ":
		  			GoldBigDragon_RPG.Command.QuestCommand QC = new GoldBigDragon_RPG.Command.QuestCommand();
		  			QC.onCommand(talker, command, string, args);
		  			return true;
		  		case "Ŀ�ǵ�":
		  			if(player.isOp() == true)
		  			{
		  				UserDataObject u = new UserDataObject();
						if(u.getType(player).equals("Skill"))
						{
							if(u.getString(player, (byte)1).equalsIgnoreCase("SKC"))
							{
								String CommandString = "";
								for(int count = 0; count <args.length-1; count ++)
									CommandString = CommandString+args[count]+" ";
								CommandString = CommandString+args[args.length-1];
								YamlController GUI_YC = GoldBigDragon_RPG.Main.Main.YC_1;
								YamlManager SkillList  = GUI_YC.getNewConfig("Skill/SkillList.yml");
								if(CommandString.contains("/")==false)
									CommandString = "/"+CommandString;
								if(CommandString.equalsIgnoreCase("/����"))
									SkillList.set(u.getString(player, (byte)2)+".SkillRank."+u.getInt(player, (byte)4)+".Command","null");
								else
									SkillList.set(u.getString(player, (byte)2)+".SkillRank."+u.getInt(player, (byte)4)+".Command",CommandString);
								SkillList.saveConfig();
								GoldBigDragon_RPG.GUI.OPBoxSkillGUI SKGUI = new GoldBigDragon_RPG.GUI.OPBoxSkillGUI();
								SKGUI.SkillRankOptionGUI(player, u.getString(player, (byte)2), u.getInt(player, (byte)4));
								u.clearAll(player);
							}
						}
						else
						{
		  					player.sendMessage(ChatColor.RED+"[��ų ����] : ��ų ������ ���� Ŀ�ǵ� �Դϴ�!");
							s.SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
		  					return true;
						}
		  			}
					else
					{
						talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
						s.SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					}
					return true;
			}
			return false;
		}
		else
		{
			
		}
		return false;
    }

}