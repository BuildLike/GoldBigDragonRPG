package dungeon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import effect.SoundEffect;
import servertick.ServerTickMain;
import util.YamlLoader;



public class DungeonScheduleObject
{
	private long UTC = 0;
	private int count = 0;
	private Player leader;
	private String Type = "D_RC";//Dungeon_RoomCreate
  	private List<Character> Grid = new ArrayList<Character>();
  	private List<Integer> GridLoc = new ArrayList<Integer>();
  	private List<Character> KeyGrid = new ArrayList<Character>();
  	private List<Integer> KeyGridLoc = new ArrayList<Integer>();
	private List<String> DungeonMaker = new ArrayList<String>();
	private byte size = 5;
	private String DungeonType = "Stone";
	private String DungeonName = null;
	private long StartX = 0;
	private int StartY = 0;
	private long StartZ = 0;
	private boolean BossRoomAdded = false;
	private long SpawnX = 0;
	private long SpawnZ = 0;
	private ItemStack item;

	public void CancelDungeonCreate()
	{
		count = -99;
		ServerTickMain.DungeonSchedule.remove(this);
		if(leader.isOnline())
		{
			if(leader.getInventory().firstEmpty() == -1)
			{
				SoundEffect.SP(leader, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				leader.sendMessage("§c§l[SYSTEM] : 던전 생성 도중 퇴장하여, 제물로 넣은 아이템이 복구되었습니다!");
				new structure.StructPostBox().SendPost_Server(leader.getUniqueId().toString(), "[시스템]", "[던전 생성 취소]", "던전 생성 도중 퇴장하여, 제물로 넣은 아이템이 복구되었습니다.", item);
			}
			else
				leader.getInventory().addItem(item);
		}
		else
			new structure.StructPostBox().SendPost_Server(leader.getUniqueId().toString(), "[시스템]", "[던전 생성 취소]", "던전 생성 도중 퇴장하여, 제물로 넣은 아이템이 복구되었습니다.", item);

		for(int count = 0; count < getDungeonMakerSize(); count++)
		{
			if(Bukkit.getServer().getPlayer(DungeonMaker.get(count))!=null)
			{
				Player player = Bukkit.getServer().getPlayer(DungeonMaker.get(count));
				if(player != leader)
				{
					if(player.isOnline())
					{
						if(player.getLocation().getWorld().getName().equals("Dungeon"))
						{
							player.sendMessage("§c§l[SYSTEM] : 던전 생성자가 던전을 떠나, 던전 입장이 취소되었습니다!");
						  	YamlLoader dungeonPlayerYaml = new YamlLoader();
							String DungeonName = main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_Enter();
							long UTC = main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getDungeon_UTC();
							dungeonPlayerYaml.getConfig("Dungeon/Dungeon/"+DungeonName+"/Entered/"+UTC+".yml");
							if(dungeonPlayerYaml.contains("EnteredAlter"))
							{
								DungeonName = dungeonPlayerYaml.getString("EnteredAlter");
								dungeonPlayerYaml.getConfig("Dungeon/AltarList.yml");
								if(dungeonPlayerYaml.contains(DungeonName))
								{
									Location loc = new Location(Bukkit.getServer().getWorld(dungeonPlayerYaml.getString(DungeonName+".World")), dungeonPlayerYaml.getLong(DungeonName+".X"), dungeonPlayerYaml.getLong(DungeonName+".Y")+1, dungeonPlayerYaml.getLong(DungeonName+".Z"));
									player.teleport(loc);
									return;
								}
							}
							new util.UtilPlayer().teleportToCurrentArea(player, true);
							return;
						}
					}
				}
			}
		}
	}
	
	public void SendCreatingRate(int index)
	{
		if(leader == null)
		{
			CancelDungeonCreate();
			return;
		}
		if(leader.isOnline())
		{
			if(!leader.getLocation().getWorld().getName().equals("Dungeon"))
			{
				CancelDungeonCreate();
				return;
			}
		}
		else
		{
			CancelDungeonCreate();
			return;
		}
		short total = (short) (Grid.size()+KeyGrid.size()-1);
		for(int count = 0; count < getDungeonMakerSize(); count++)
		{
			if(Bukkit.getServer().getPlayer(DungeonMaker.get(count))!=null)
			{
				Player p = Bukkit.getServer().getPlayer(DungeonMaker.get(count));
				if(p.isOnline())
				{
					if(p.getLocation().getWorld().getName().equals("Dungeon"))
					{
						String Title = "\'§9[던전 생성]\'";
						String SubTitle  = "\'§1[ §9"+  index  +"§1 / §9"+ total+"§1 ]\'";
						new effect.SendPacket().sendTitleSubTitle(Bukkit.getServer().getPlayer(DungeonMaker.get(count)),Title, SubTitle, (byte)0, (byte)0, (byte)1);
					}
				}
			}
		}
		if(index >= total)
			for(int count = 0; count < getDungeonMakerSize(); count++)
			{
				if(Bukkit.getServer().getPlayer(DungeonMaker.get(count))!=null)
				{
					Player p = Bukkit.getServer().getPlayer(DungeonMaker.get(count));
					if(p.isOnline())
					{
						if(p.getLocation().getWorld().getName().equals("Dungeon"))
						{
							String Title  = "\'§f" +DungeonName+ "\'";
							new effect.SendPacket().sendTitle(p,Title, (byte)2, (byte)3, (byte)2);
						}
					}
				}
			}
			
	}

	public DungeonScheduleObject(String type)
	{
		this.UTC = ServerTickMain.nowUTC;
		this.Type = type;
	}

	public boolean containsKeyImage(int loc)
	{
		if(KeyGridLoc.contains(loc))
			return true;
		else
			return false;
	}
	
	public char getUnderKeyImage(int loc)
	{
		for(int count = 0; count < GridLoc.size(); count++)
		{
			if(GridLoc.get(count)==loc)
				return Grid.get(count);
		}
		return '─';	
	}
	
	public void addGridLoc(int loc)
	{GridLoc.add(GridLoc.size(), loc);}

	public void addKeyGridLoc(int loc)
	{KeyGridLoc.add(KeyGridLoc.size(), loc);}
	
	public void addGrid(char grid)
	{Grid.add(Grid.size(), grid);}
	
	public void addKeyGrid(char grid)
	{KeyGrid.add(KeyGrid.size(), grid);}
	
	public int getGridSize()
	{return Grid.size();}

	public int getKeyGridSize()
	{return KeyGrid.size();}
	
	public byte getSize()
	{return size;}
	
	public void setSize(byte size)
	{this.size = size;}
	
	public String getDungeonType()
	{return DungeonType;}
	
	public void setDungeonType(String dungeonType)
	{DungeonType = dungeonType;}
	
	public String getDungeonName()
	{return DungeonName;}
	
	public void setDungeonName(String dungeonName)
	{DungeonName = dungeonName;}
	
	public long getStartX()
	{return StartX;}
	
	public void setStartX(long startX)
	{StartX = startX;}
	
	public int getStartY()
	{return StartY;}
	
	public void setStartY(int startY)
	{StartY = startY;}
	
	public long getStartZ()
	{return StartZ;}
	
	public void setStartZ(long startZ)
	{StartZ = startZ;}

	public char getDirection(int index, char Direction)
	{
		int loc = GridLoc.get(index);
		switch(Direction)
		{
		case 'N':
			if(GridLoc.contains(loc-size)==false)
				return (char) -1;
			loc=loc-size;
			break;
		case 'E':
			if(loc%size==size-1)
				return (char) -1;
			if(GridLoc.contains(loc+1)==false)
				return (char) -1;
			loc=loc+1;
			break;
		case 'S':
			if(GridLoc.contains(loc+size)==false)
				return (char) -1;
			loc=loc+size;
			break;
		case 'W':
			if(loc%size==0)
				return (char) -1;
			if(GridLoc.contains(loc-1)==false)
				return (char) -1;
			loc=loc-1;
			break;
		}
		for(int count = 0; count < Grid.size(); count++)
		{
			if(GridLoc.get(count)==loc)
				return Grid.get(count);
		}
		return (char) -1;
	}
	
	public char getGrid(int index)
	{return Grid.get(index);}

	public void setGrid(List<Character> grid)
	{Grid = grid;}

	public int getGridLoc(int index)
	{return GridLoc.get(index);}

	public void setGridLoc(List<Integer> gridLoc)
	{GridLoc = gridLoc;}

	public char getKeyGrid(int index)
	{return KeyGrid.get(index);}

	public void setKeyGrid(List<Character> keyGrid)
	{KeyGrid = keyGrid;}

	public int getKeyGridLoc(int index)
	{return KeyGridLoc.get(index);}

	public void setKeyGridLoc(List<Integer> keyGridLoc)
	{KeyGridLoc = keyGridLoc;}

	public int getDungeonMakerSize()
	{return DungeonMaker.size();}
	
	public String getDungeonMaker(int index)
	{return DungeonMaker.get(index);}
	
	public void addDungeonMaker(String player)
	{DungeonMaker.add(DungeonMaker.size(), player);}

	public void setDungeonMaker(List<String> player)
	{DungeonMaker = player;}

	public String getType()
	{return Type;}

	public void setType(String type)
	{Type = type;}
	
	public void copyThisScheduleObject(long WillAddTick)
	{servertick.ServerTickMain.DungeonSchedule.put(WillAddTick, this);}

	public long getUTC()
	{return UTC;}

	public void setUTC(long uTC)
	{UTC = uTC;}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isBossRoomAdded() {
		return BossRoomAdded;
	}

	public void setBossRoomAdded(boolean bossRoomAdded) {
		BossRoomAdded = bossRoomAdded;
	}

	public long getSpawnX() {
		return SpawnX;
	}

	public void setSpawnX(long spawnX) {
		SpawnX = spawnX;
	}

	public long getSpawnZ() {
		return SpawnZ;
	}

	public void setSpawnZ(long spawnZ) {
		SpawnZ = spawnZ;
	}
	
	public void setLeader(Player p)
	{
		leader = p;
	}
	
	public Player getLeader()
	{
		return leader;
	}
	
	public void setItem(ItemStack usedItem)
	{
		item = usedItem;
	}
	
	public ItemStack getItem()
	{
		return item;
	}
}
