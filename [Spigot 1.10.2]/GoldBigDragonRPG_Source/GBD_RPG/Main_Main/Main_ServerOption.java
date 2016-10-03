package GBD_RPG.Main_Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import GBD_RPG.Area.Area_Object;
import GBD_RPG.Corpse.Corpse_Main;
import GBD_RPG.Monster.Monster_Object;
import GBD_RPG.Party.Party_DataManager;
import GBD_RPG.Party.Party_Object;
import GBD_RPG.User.User_Object;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Main_ServerOption
{
	public static ArrayList<String> DungeonTheme = new ArrayList<String>();
	
	public static String STR = "ü��";
	public static String DEX = "�ؾ�";
	public static String INT = "����";
	public static String WILL = "����";
	public static String LUK = "���";
	public static String Money = ChatColor.YELLOW+""+ChatColor.BOLD+"Gold";
	public static String Damage = "�����";
	public static String MagicDamage = "���� �����";
	
	public static byte Event_SkillPoint = 1;
	public static byte Event_StatPoint = 1;
	public static byte Event_Exp = 1;
	public static byte Event_DropChance = 1;
	public static byte Event_Proficiency = 1;
	
	public static byte LevelUpPerSkillPoint = 1;
	public static byte LevelUpPerStatPoint = 10;
	
	
	public static int MaxLevel = 100;
	public static int MaxStats = 1500;
	public static int EXPShareDistance = 50;
	public static long MaxDropMoney = 100000;
	
	public static String STR_Lore = "%enter%"+ChatColor.GRAY+" "+Main_ServerOption.STR+"�� �÷��̾���%enter%"+ChatColor.GRAY + " ������ ���ݷ���%enter%"+ChatColor.GRAY + " ��½��� �ݴϴ�.%enter%";
	public static String DEX_Lore = "%enter%"+ChatColor.GRAY+" "+Main_ServerOption.DEX+"�� �÷��̾���%enter%"+ChatColor.GRAY + " ���Ÿ� ���ݷ���%enter%"+ChatColor.GRAY + " ��½��� �ݴϴ�.%enter%";
	public static String INT_Lore = "%enter%"+ChatColor.GRAY+" "+Main_ServerOption.INT+"�� �÷��̾%enter%"+ChatColor.GRAY + " ����ϴ� ��ų ��%enter%"+ChatColor.GRAY+" "+Main_ServerOption.INT+" ������ �޴�%enter%"+ChatColor.GRAY+" ��ų ���ݷ���%enter%"+ChatColor.GRAY + " ��½��� �ݴϴ�.%enter%";
	public static String WILL_Lore = "%enter%"+ChatColor.GRAY+" "+Main_ServerOption.WILL+"�� �÷��̾���%enter%"+ChatColor.GRAY + " ũ��Ƽ�� �� ��ų ��%enter%"+ChatColor.GRAY+" "+Main_ServerOption.WILL+" ������ �޴�%enter%"+ChatColor.GRAY + " ��ų ���ݷ���%enter%"+ChatColor.GRAY+" ��½��� �ݴϴ�.%enter%";
	public static String LUK_Lore = "%enter%"+ChatColor.GRAY+" "+Main_ServerOption.LUK+"�� �÷��̾��%enter%"+ChatColor.GRAY+" ������ ���� ���� �Ͼ%enter%"+ChatColor.GRAY + " Ȯ���� ������ŵ�ϴ�.%enter%";

	public static boolean MoneySystem = false;
	public static short Money1ID = 348;
	public static byte Money1DATA = 0;
	public static short Money2ID = 371;
	public static byte Money2DATA = 0;
	public static short Money3ID = 147;
	public static byte Money3DATA = 0;
	public static short Money4ID = 266;
	public static byte Money4DATA = 0;
	public static short Money5ID = 41;
	public static byte Money5DATA = 0;
	public static short Money6ID = 41;
	public static byte Money6DATA = 0;
	
	public static String serverUpdate = "2016-10-03-21:46";
	public static String serverVersion = "Advanced";
	private static String updateCheckURL = "https://goldbigdragon.github.io/";
	public static String currentServerUpdate = "2016-10-03-21:46";
	public static String currentServerVersion = "Advanced";
	
	public static java.util.Map<Long, Party_Object> Party = new LinkedHashMap<Long, Party_Object>();
	public static java.util.Map<Player, Long> PartyJoiner = new LinkedHashMap<Player, Long>();
	
	public static HashMap<Player, Location> catchedLocation1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> catchedLocation2 = new HashMap<Player, Location>();
	
	public static HashMap<Player, String> PlayerUseSpell = new HashMap<Player, String>();
	public static HashMap<Player, ItemStack> PlayerlastItem = new HashMap<Player, ItemStack>();

	public static HashMap<String, ArrayList<Area_Object>> AreaList = new HashMap<String, ArrayList<Area_Object>>();
	public static HashMap<Player, String> PlayerCurrentArea = new HashMap<Player, String>();
	public static HashMap<String, User_Object> PlayerList = new HashMap<String, User_Object>();
	public static HashMap<String, Monster_Object> MonsterList = new HashMap<String, Monster_Object>();
	public static HashMap<String, String> MonsterNameMatching = new HashMap<String, String>();
		
	public static boolean MagicSpellsCatched = false;
	public static boolean MagicSpellsEnable = false;
	public static boolean CitizensCatched = false;
	public static boolean NoteBlockAPI = false;
	public static boolean NoteBlockAPIAble = false;
	
	public static boolean Mapping = false;
	public static boolean AntiExplode = true;
	public static boolean PVP = true;
	
	public static ItemStack DeathRescue = null;
	public static ItemStack DeathRevive = null;
	
	public void Initialize()
	{
	  	new GBD_RPG.Area.Area_Main().addAreaList();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ���� ���� �ε�");
		Object[] players = Bukkit.getOnlinePlayers().toArray();
		for(short count = 0; count < players.length; count++)
		{
			Player p = ((Player)players[count]);
			PlayerList.put(p.getUniqueId().toString(), new User_Object((Player)players[count]));
			if(new GBD_RPG.Area.Area_Main().SearchAreaName(p.getLocation()) != null)
				PlayerCurrentArea.put(p, new GBD_RPG.Area.Area_Main().SearchAreaName(p.getLocation())[0].toString());
			if(PlayerList.get(p.getUniqueId().toString()).isDeath())
				new Corpse_Main().CreateCorpse(p);
		}
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" �÷��̾� ���� �ε�");
	  	YamlController YC = new YamlController(Main_Main.plugin);
		YamlManager Monster  = YC.getNewConfig("Monster/MonsterList.yml");
		Object[] KeyList = Monster.getConfigurationSection("").getKeys(false).toArray();
		for(int count = 0; count < KeyList.length; count++)
		{
			String RealName = (String)KeyList[count];
			Monster_Object OM = new Monster_Object(RealName, Monster.getString(RealName+".Name"), Monster.getLong(RealName+".EXP"), Monster.getInt(RealName+".HP"), Monster.getInt(RealName+".MIN_Money"), Monster.getInt(RealName+".MAX_Money"), Monster.getInt(RealName+".STR"), Monster.getInt(RealName+".DEX"), Monster.getInt(RealName+".INT"), Monster.getInt(RealName+".WILL"), Monster.getInt(RealName+".LUK"), Monster.getInt(RealName+".DEF"), Monster.getInt(RealName+".Protect"), Monster.getInt(RealName+".Magic_DEF"), Monster.getInt(RealName+".Magic_Protect"));
			MonsterList.put(RealName, OM);
			MonsterNameMatching.put(Monster.getString(RealName+".Name"), RealName);
		}
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ���� ���� �ε�");
		
	  	File MusicFolder = new File(Main_Main.plugin.getDataFolder().getAbsolutePath() + "/NoteBlockSound/");
		if(!MusicFolder.exists())
			MusicFolder.mkdirs();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" NBS ���� �ε�");
	  	GBD_RPG.Main_Main.Main_Config config = new GBD_RPG.Main_Main.Main_Config();
	  	config.CheckConfig(YC);
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ���Ǳ� ���� �ε�");
	  	config.CreateMapImageConfig(YC);
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" �� �̹��� ���� �ε�");
	  	if(YC.isExit("Skill/SkillList.yml") == false)
	  	  new GBD_RPG.Skill.Skill_Config().CreateNewSkillList();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ��ų ���� �ε�");
	  	if(YC.isExit("Skill/JobList.yml") == false)
	  		new GBD_RPG.Skill.Skill_Config().CreateNewJobList();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ���� ���� �ε�");
	  	if(YC.isExit("ETC/NewBie.yml") == false)
	  		new GBD_RPG.Admin.NewBie_Config().CreateNewConfig();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" �ʺ��� ���� �ε�");
	  	
	  	new Party_DataManager().loadParty();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ��Ƽ ���� �ε�");
	  	
		YamlManager WorldConfig = YC.getNewConfig("WorldList.yml");
		Object[] worldname = WorldConfig.getKeys().toArray();
		for(short count = 0; count < WorldConfig.getKeys().size();count++)
			if(Bukkit.getWorld(worldname[count].toString()) == null)
				WorldCreator.name(worldname[count].toString()).createWorld();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ���� ���� �ε�");

		NoteBlockAPICatch();
		
		new GBD_RPG.ServerTick.ServerTick_Main(Main_Main.plugin);
		new GBD_RPG.ServerTick.ServerTick_ScheduleManager().loadCategoriFile();
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ƽ ���� �ε�");
		
		if(Bukkit.getServer().getOnlineMode()==false)
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "�� ��ǰ ���������� �Ϻ� ����� �������� ������ ���� �������� �ֽ��ϴ�.");

		if(Bukkit.getWorld("Dungeon") == null)
		{
			WorldCreator.name("Dungeon").type(WorldType.FLAT).generateStructures(false).createWorld();
			Block block = null;
			for(byte count = 0; count < 21; count++)
			{
				for(byte count2 = 0; count2 < 21; count2++)
				{
					byte id = 1;
					if(count == 1||count2 == 1||count == 7||count2 == 7||count == 14||count2 == 14||count == 20||count2 == 20)
						id=89;
					block = new Location(Bukkit.getWorld("Dungeon"), -100+count, 30, -100+count2).getBlock();
					block.setTypeIdAndData(id, (byte) 0, true);
					block = new Location(Bukkit.getWorld("Dungeon"), -100+count, 42, -100+count2).getBlock();
					block.setTypeIdAndData(id, (byte) 0, true);
				}
			}
			for(byte count = 0; count < 21; count++)
			{
				for(byte count2 = 0; count2 < 12; count2++)
				{
					byte id = 1;
					if(count2 == 1||count2 == 11)
						id=89;
					block = new Location(Bukkit.getWorld("Dungeon"), -100+count, 30+count2, -100).getBlock();
					block.setTypeIdAndData(id, (byte) 0, true);
					block = new Location(Bukkit.getWorld("Dungeon"), -100, 30+count2, -100+count).getBlock();
					block.setTypeIdAndData(id, (byte) 0, true);
					block = new Location(Bukkit.getWorld("Dungeon"), -100+count, 30+count2, -79).getBlock();
					block.setTypeIdAndData(id, (byte) 0, true);
					block = new Location(Bukkit.getWorld("Dungeon"), -79, 30+count2, -100+count).getBlock();
					block.setTypeIdAndData(id, (byte) 0, true);
				}
			}
			block = new Location(Bukkit.getWorld("Dungeon"), -100+10, 31, -100+11).getBlock();
			block.setTypeIdAndData(138, (byte) 0, true);
			block = new Location(Bukkit.getWorld("Dungeon"), -100+11, 31, -100+11).getBlock();
			block.setTypeIdAndData(138, (byte) 0, true);
			block = new Location(Bukkit.getWorld("Dungeon"), -100+10, 31, -100+10).getBlock();
			block.setTypeIdAndData(138, (byte) 0, true);
			block = new Location(Bukkit.getWorld("Dungeon"), -100+11, 31, -100+10).getBlock();
			block.setTypeIdAndData(138, (byte) 0, true);
			

			YamlManager DungeonData = YC.getNewConfig("Dungeon/DungeonData.yml");
			DungeonData.set("StartPoint.X", 1000);
			DungeonData.set("StartPoint.Y", 30);
			DungeonData.set("StartPoint.Z", 1000);
			DungeonData.saveConfig();
		}
		else
		{
			Iterator<Entity> entityList = Bukkit.getWorld("Dungeon").getEntities().iterator();
			boolean isPlayerExist = false;
			while(entityList.hasNext())
			{
				Entity entity = (entityList.next());
				if(entity.getType()==EntityType.PLAYER)
				{
					Player p = (Player) entity;
					if(p.isOnline())
					{
						isPlayerExist = true;
						break;
					}
				}
			}
			if(isPlayerExist==false)
			{
				while(entityList.hasNext())
					entityList.next().remove();
				YamlManager DungeonData = YC.getNewConfig("Dungeon/DungeonData.yml");
				if(DungeonData.getLong("StartPoint.X")>=50000)
				{
					DungeonData.set("StartPoint.X", 1000);
					DungeonData.set("StartPoint.Y", 30);
					DungeonData.set("StartPoint.Z", 1000);
					DungeonData.saveConfig();
				}
			}
		}
		File directory = new File(Main_Main.plugin.getDataFolder() + "\\Dungeon\\Schematic"); 
		if(directory.exists()==false)
			directory.mkdir();
		File[] fileList = directory.listFiles(); 
		try
		{
			for(int count = 0 ; count < fileList.length ; count++)
				if(fileList[count].isFile()==false)
				{
					File InnerDirectory = new File(Main_Main.plugin.getDataFolder() + "\\Dungeon\\Schematic\\"+fileList[count].getName()); 
					File[] schematicList = InnerDirectory.listFiles();
					if(schematicList.length != 25)
					{
						ArrayList<String> DungeonFile = new ArrayList<String>();
						for(byte count2 = 0 ; count2 < schematicList.length ; count2++)
							DungeonFile.add(schematicList[count2].getName());
						Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Fail] " + fileList[count].getName()+" ���� �׸��� ����Ϸ��� �Ʒ� ���������� �� �ʿ��մϴ�!");
						if(DungeonFile.contains("Boss.schematic")==false)
							Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� Boss.schematic");
						for(byte count2 = 0 ; count2 < 4 ; count2++)
							if(DungeonFile.contains("Closed_Door"+count2+".schematic")==false)
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� Closed_Door"+count2+".schematic");
						if(DungeonFile.contains("CrossRoad.schematic")==false)
							Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� CrossRoad.schematic");
						for(byte count2 = 0 ; count2 < 4 ; count2++)
							if(DungeonFile.contains("Door"+count2+".schematic")==false)
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� Door"+count2+".schematic");
						for(byte count2 = 0 ; count2 < 4 ; count2++)
							if(DungeonFile.contains("LRoad"+count2+".schematic")==false)
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� LRoad"+count2+".schematic");
						for(byte count2 = 0 ; count2 < 2 ; count2++)
							if(DungeonFile.contains("Road"+count2+".schematic")==false)
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� Road"+count2+".schematic");
						if(DungeonFile.contains("Room.schematic")==false)
							Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� Room.schematic");
						for(byte count2 = 0 ; count2 < 4 ; count2++)
							if(DungeonFile.contains("TRoad"+count2+".schematic")==false)
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� TRoad"+count2+".schematic");
						for(byte count2 = 0 ; count2 < 4 ; count2++)
							if(DungeonFile.contains("Wall"+count2+".schematic")==false)
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"�� Wall"+count2+".schematic");
					}
					else
					{
						DungeonTheme.add(fileList[count].getName());
						Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE+"[���� �׸� �߰�] " + fileList[count].getName());
					}
				}
		}
		catch(Exception e)
		{}
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN +""+ChatColor.BOLD+ "[OK]"+ChatColor.DARK_GRAY+" ���� ���� �ε�");

	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "��������������������������������������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "I changed My Symbol!");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Like this Oriental Dragon...");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Because some peoples claimed");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "my original Dragon symbol...");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "(They said my original symbol");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "looks like the 'Nazi''s Hakenkreuz)");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "��");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "����������"); 
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�������ߡ���");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�����ߡ�������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "���������ߡ�������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�������ߡ������ߡ���");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�����ߡ������ߡ�������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "���ߡ������ߡ�����������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�����ߡ��ߡ���������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�������ߡ�������������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "��GoldBigDragon Advanced");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "http://cafe.naver.com/goldbigdragon");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "��������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "��������������������������������������������");
	  	
	  	VersionCheck();
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
				Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "[ ! ] ���� GoldBigDragonRPG�� �ֽ� �����Դϴ�!");
			else
			{
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ ! ] GoldBigDragonRPG�� �ֽ� ������ �ƴմϴ�! �Ʒ� �ּҿ��� �ٿ�ε� ��������!");
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ ! ] http://cafe.naver.com/goldbigdragon/57885");
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[ ! ] ��ġ�� �ʿ��� ���� : " + Parsed[3].split(": ")[1]);
			}
			
		}
		catch (IOException ioe)
		{
		  	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[���� üũ ����] ���ͳ� ������ Ȯ�� �� �ּ���!");
		}
	}
	
	public void MagicSpellCatch()
	{
		if(MagicSpellsCatched == false)
		{
			MagicSpellsCatched = true;
			if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == false)
			{
				ErrorMessage();
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "MagicSpells �÷������� ã�� �� �����ϴ�!");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "MagicSpells �ٿ�ε� URL");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "http://nisovin.com/magicspells/Start");
			}
			else
			{
				MagicSpellsEnable = true;
				new OtherPlugins.SpellMain(Main_Main.plugin);
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
				ErrorMessage();
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Citizens �÷������� ã�� �� �����ϴ�!");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Citizens �ٿ�ε� URL");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "http://www.curse.com/bukkit-plugins/minecraft/citizens#t1:other-downloads");
			}
			else
				new OtherPlugins.CitizensMain(Main_Main.plugin);
		}
		return;
	}
	
	public void NoteBlockAPICatch()
	{
		if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI"))
			new OtherPlugins.NoteBlockAPIMain(Main_Main.plugin);
		return;
	}
	
	public void NoteBlockAPICatchPJ()
	{
		if(NoteBlockAPI == false)
		{
			NoteBlockAPI = true;
			if(Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") == false)
			{
				ErrorMessage();
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "��Ʈ���� ��� �÷������� ã�� �� �����ϴ�!");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "NoteBlockAPI �ٿ�ε� URL");
			  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "http://www.curse.com/bukkit-plugins/minecraft/noteblockapi");
			}
			else
			{
				new OtherPlugins.NoteBlockAPIMain(Main_Main.plugin);
			}
		}
		return;
	}
	
	public void ErrorMessage()
	{
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�������۰桡����");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������"); 
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "��������ᡡ���");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�������ᡡ����");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�������ᡡ����");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������ᡡ�����"); 
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "������������");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[�÷��̿� ������ �����ϴ�]");
	  	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "");
	}
}