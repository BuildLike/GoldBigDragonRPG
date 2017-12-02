package user;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import effect.SoundEffect;
import main.MainServerOption;
import util.YamlLoader;

public class UserObject
{
	private String PlayerName;
	private String PlayerUUID;
	private String PlayerRootJob;
	
	private Player player;
	private int Stat_Level;
	private int Stat_RealLevel;
	private int Stat_SkillPoint;
	private int Stat_StatPoint;
	private long Stat_EXP;
	private long Stat_MaxEXP;
	private long Stat_Money;
	private int Stat_HP;
	private int Stat_MaxHP;
	private int Stat_Wond;
	private int Stat_MP;
	private int Stat_MaxMP;
	private int Stat_STR;
	private int Stat_DEX;
	private int Stat_INT;
	private int Stat_WILL;
	private int Stat_LUK;
	private int Stat_Balance;
	private int Stat_Critical;
	private int Stat_DEF;
	private int Stat_DEFcrash;
	private int Stat_Protect;
	private int Stat_Magic_DEF;
	private int Stat_MagicDEFcrash;
	private int Stat_Magic_Protect;
	private long Stat_AttackTime;
	private byte Stat_BowPull;

	private boolean Alert_Damage;
	private boolean Alert_MobHealth;
	private boolean Alert_Critical;
	private boolean Alert_AttackDelay;
	private boolean Alert_ItemPickUp;
	private boolean Alert_EXPget;
	
	private boolean Option_EquipLook;
	private boolean Option_HotBarSound;
	private byte Option_ChattingType;
	private boolean Option_BGM;
	private boolean Option_ClickUse;
	private boolean Option_SeeInventory;

	private long ETC_Party;
	private boolean ETC_Death;
	private String ETC_CurrentArea;
	private String ETC_LastVisited;
	private long ETC_BuffCoolTime;

	private String Dungeon_Enter;
	private long Dungeon_UTC;
	private boolean Dungeon_NormalBGMplaying;
	private boolean Dungeon_BossBGMplaying;

	private boolean Death;
	private Location LastDeathPoint;
	
	public String getPlayerUUID()
	{return PlayerUUID;}
	
	public void setPlayerUUID(String playerUUID)
	{PlayerUUID = playerUUID;}
	
	public int getStat_Level()
	{return Stat_Level;}

	public void setStat_Level(int value)
	{Stat_Level = value;}
	
	public void addStat_Level(int value)
	{
		if(Stat_Level+value <= Integer.MAX_VALUE)
			Stat_Level = Stat_Level+value;
		else
			Stat_Level = Integer.MAX_VALUE;
	}
	
	public int getStat_RealLevel()
	{return Stat_RealLevel;}
	
	public void addStat_RealLevel(int value)
	{
		if(Stat_RealLevel+value <= Integer.MAX_VALUE)
			Stat_RealLevel = Stat_RealLevel+value;
		else
			Stat_RealLevel = Integer.MAX_VALUE;
	}
	
	public int getStat_SkillPoint()
	{return Stat_SkillPoint;}
	
	public void addStat_SkillPoint(int value)
	{
		if(Stat_SkillPoint+value <= Integer.MAX_VALUE)
			Stat_SkillPoint = Stat_SkillPoint+value;
		else
			Stat_SkillPoint = Integer.MAX_VALUE;
	}
	
	public int getStat_StatPoint()
	{return Stat_StatPoint;}
	
	public void setStat_StatPoint(int stat_StatPoint)
	{Stat_StatPoint = stat_StatPoint;}

	public void addStat_StatPoint(int value)
	{
		if(Stat_StatPoint+value <= Integer.MAX_VALUE)
			Stat_StatPoint = Stat_StatPoint+value;
		else
			Stat_StatPoint = Integer.MAX_VALUE;
	}
	
	public long getStat_EXP()
	{return Stat_EXP;}
	
	public void setStat_EXP(long value)
	{Stat_EXP = value;}

	public boolean addStat_MoneyAndEXP(long Money, long EXP, boolean isAlert)
	{
		if(main.MainServerOption.economy!=null)
		{
			if(Money > 0)
				main.MainServerOption.economy.depositPlayer(player.getName(), Money);
			else if(Money < 0)
				main.MainServerOption.economy.withdrawPlayer(player.getName(), -1*Money);
		}
		else
		{
			if(Money != 0)
			{
				if(Stat_Money + Money <= 2000000000)
				{
					if(Stat_Money + Money < 0)
						return false;
					else
						setStat_Money(this.Stat_Money+Money);
				}
				else
				{
					player.sendMessage("��c[System] : "+MainServerOption.money+"��c ��(��) 2000000000(20��)�̻� ���� �� �����ϴ�!");
					setStat_Money(2000000000);
				}
			}
		}
		if(EXP!=0)
		{
			Stat_EXP = LongProcessing(Stat_EXP, EXP);

		    boolean isLevelUp = false;
		    YamlLoader levelYAML = new YamlLoader();
    		levelYAML.getConfig("Level.yml");
		    int LevelUp_PerSkillPoint = MainServerOption.levelUpPerSkillPoint * MainServerOption.eventSkillPoint;
		    int LevelUp_PerStatPoint = MainServerOption.levelUpPerStatPoint * MainServerOption.eventStatPoint;
			for(;;)
			{
				if(Stat_EXP < Stat_MaxEXP)
					break;
				else if(MainServerOption.maxLevel <= Stat_Level || levelYAML.contains((Stat_Level+1)+"")==false)
				{
					Stat_EXP = Stat_MaxEXP;
					break;
				}
				else
				{
					isLevelUp = true;
					Stat_EXP -= Stat_MaxEXP;
					Stat_Level++;
					Stat_RealLevel++;
					Stat_SkillPoint += LevelUp_PerSkillPoint;
					Stat_StatPoint += LevelUp_PerStatPoint;
					Stat_MaxEXP = levelYAML.getLong(Stat_Level+"");
					if(Stat_MaxEXP > Long.MAX_VALUE)
						Stat_MaxEXP = Long.MAX_VALUE;
					else if(Stat_MaxEXP <= 0)
						Stat_MaxEXP = 100;

					if(MainServerOption.maxLevel <= Stat_Level)
						new effect.SendPacket().sendActionBar(Bukkit.getPlayer(PlayerName), "��c��l[�ִ� ������ �����Ͽ� ���̻� ������ �Ͻ� ���� �����ϴ�!]", false);
				}
			}
			if(isLevelUp)
			{
				new effect.SendPacket().sendTitle(Bukkit.getPlayer(PlayerName), "��fLevel Up!", "��f���� ��e"+ Stat_Level+"��f�� �Ǿ����ϴ�!", 1, 3, 1);
				SoundEffect.SP(Bukkit.getPlayer(PlayerName), org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1.5F, 1.8F);
			}
		}
		if(isAlert && Alert_EXPget)
		{
			if(Money!=0&&EXP!=0)
				new effect.SendPacket().sendActionBar(player, "��b��l[����ġ] "+ EXP + " ��e��l[��f"+MainServerOption.money+"��e��l] "+ Money, false);
			else if(Money!=0)
				new effect.SendPacket().sendActionBar(player, "��e��l[��f"+MainServerOption.money+"��e��l] "+ Money, false);
			else
				new effect.SendPacket().sendActionBar(player, "��b��l[����ġ] "+ EXP, false);
		}
			
		return true;
	}
	
	public long getStat_MaxEXP()
	{return Stat_MaxEXP;}

	public void setStat_MaxEXP(long value)
	{Stat_MaxEXP = value;}
	
	public long getStat_Money()
	{
		if(main.MainServerOption.economy!=null)
			return (long) main.MainServerOption.economy.getBalance(player.getName());
		else
			return Stat_Money;
	}

	public void setStat_Money(long value)
	{
		if(main.MainServerOption.economy!=null)
		{
			if(main.MainServerOption.economy.withdrawPlayer(player.getName(), main.MainServerOption.economy.getBalance(player.getName())).transactionSuccess())
				main.MainServerOption.economy.depositPlayer(player.getName(), value);
		}
		else
			Stat_Money = value;
	}
	
	public int getStat_HP()
	{return Stat_HP;}
	
	public void setStat_HP(int stat_HP)
	{Stat_HP = stat_HP;}

	public void addStat_HP(int value)
	{
		if(value < 0)
		{
			if(Stat_HP - value >= -1*Stat_MaxHP)
				Stat_HP = Stat_HP + value;
			else
				Stat_HP = -1 * Stat_MaxHP;
		}
		else
		{
			if(Stat_HP+value <= Stat_MaxHP)
				Stat_HP = Stat_HP + value;
			else
				Stat_HP = Stat_MaxHP;
		}
	}
	
	public int getStat_MaxHP()
	{return Stat_MaxHP;}
	
	public void setStat_MaxHP(int stat_MaxHP)
	{Stat_MaxHP = stat_MaxHP;}
	
	public void addStat_MaxHP(int value)
	{
		if(value < 0)
		{
			if(Stat_MaxHP - value >= 1)
				Stat_MaxHP = Stat_MaxHP + value;
			else
				Stat_MaxHP = 1;
		}
		else
		{
			if(Stat_MaxHP+value <= Integer.MAX_VALUE)
				Stat_MaxHP = Stat_MaxHP + value;
			else
				Stat_MaxHP = Integer.MAX_VALUE;
		}
	}
	
	public int getStat_Wond()
	{return Stat_Wond;}

	public void addStat_Wond(int value)
	{
		if(value < 0)
		{
			if(Stat_Wond - value >= 0)
				Stat_Wond = Stat_Wond + value;
			else
				Stat_Wond = 0;
		}
		else
		{
			if(Stat_Wond+value < Stat_MaxHP)
				Stat_Wond = Stat_Wond + value;
			else
				Stat_Wond = Stat_MaxHP-1;
		}
	}
	
	public int getStat_MP()
	{return Stat_MP;}
	
	public void setStat_MP(int stat_MP)
	{Stat_MP = stat_MP;}
	
	public void addStat_MP(int value)
	{
		if(value < 0)
		{
			if(Stat_MP - value >= 0)
				Stat_MP = Stat_MP + value;
			else
				Stat_MP = 0;
		}
		else
		{
			if(Stat_MP+value <= Stat_MaxMP)
				Stat_MP = Stat_MP + value;
			else
				Stat_MP = Stat_MaxMP;
		}
	}
	
	public int getStat_MaxMP()
	{return Stat_MaxMP;}
	
	public void setStat_MaxMP(int stat_MaxMP)
	{Stat_MaxMP = stat_MaxMP;}

	public void addStat_MaxMP(int value)
	{
		if(value < 0)
		{
			if(Stat_MaxMP - value >= 1)
				Stat_MaxMP = Stat_MaxMP + value;
			else
				Stat_MaxMP = 1;
		}
		else
		{
			if(Stat_MaxMP+value <= Integer.MAX_VALUE)
				Stat_MaxMP = Stat_MaxMP + value;
			else
				Stat_MaxMP = Integer.MAX_VALUE;
		}
	}
	
	public int getStat_STR()
	{
		if(Stat_STR > MainServerOption.maxSTR)
			return MainServerOption.maxSTR;
		else
			return Stat_STR;
	}
	
	public void setStat_STR(int stat_STR)
	{Stat_STR = stat_STR;}

	public void addStat_STR(int value)
	{Stat_STR = IntegerProcessing(Stat_STR, value);}
	
	public int getStat_DEX()
	{
		if(Stat_DEX > MainServerOption.maxDEX)
			return MainServerOption.maxDEX;
		else
			return Stat_DEX;
	}
	
	public void setStat_DEX(int stat_DEX)
	{Stat_DEX = stat_DEX;}

	public void addStat_DEX(int value)
	{Stat_DEX = IntegerProcessing(Stat_DEX, value);}
	
	public int getStat_INT()
	{
		if(Stat_INT > MainServerOption.maxINT)
			return MainServerOption.maxINT;
		else
			return Stat_INT;
	}
	
	public void setStat_INT(int stat_INT)
	{Stat_INT = stat_INT;}

	public void addStat_INT(int value)
	{Stat_INT = IntegerProcessing(Stat_INT, value);}
	
	public int getStat_WILL()
	{
		if(Stat_WILL > MainServerOption.maxWILL)
			return MainServerOption.maxWILL;
		else
			return Stat_WILL;
	}
	
	public void setStat_WILL(int stat_WILL)
	{Stat_WILL = stat_WILL;}

	public void addStat_WILL(int value)
	{Stat_WILL = IntegerProcessing(Stat_WILL, value);}
	
	public int getStat_LUK()
	{
		if(Stat_LUK > MainServerOption.maxLUK)
			return MainServerOption.maxLUK;
		else
			return Stat_LUK;
	}
	
	public void setStat_LUK(int stat_LUK)
	{Stat_LUK = stat_LUK;}
	
	public void addStat_LUK(int value)
	{Stat_LUK = IntegerProcessing(Stat_LUK, value);}
	
	public int getStat_Balance()
	{return Stat_Balance;}
	
	public void setStat_Balance(int stat_Balance)
	{Stat_Balance = stat_Balance;}

	public void addStat_Balance(int value)
	{Stat_Balance = IntegerProcessing(Stat_Balance, value);}
	
	public int getStat_Critical()
	{return Stat_Critical;}
	
	public void setStat_Critical(int stat_Critical)
	{Stat_Critical = stat_Critical;}

	public void addStat_Critical(int value)
	{Stat_Critical = IntegerProcessing(Stat_Critical, value);}
	
	public int getStat_DEF()
	{return Stat_DEF;}
	
	public void setStat_DEF(int stat_DEF)
	{Stat_DEF = stat_DEF;}

	public void addStat_DEF(int value)
	{Stat_DEF = IntegerProcessing(Stat_DEF, value);}
	
	public int getStat_DEFcrash()
	{return Stat_DEFcrash;}
	
	public void setStat_DEFcrash(int stat_DEFcrash)
	{Stat_DEFcrash = stat_DEFcrash;}

	public void addStat_DEFcrash(int value)
	{Stat_DEFcrash = IntegerProcessing(Stat_DEFcrash, value);}
	
	public int getStat_Protect()
	{return Stat_Protect;}
	
	public void setStat_Protect(int stat_Protect)
	{Stat_Protect = stat_Protect;}

	public void addStat_Protect(int value)
	{Stat_Protect = IntegerProcessing(Stat_Protect, value);}
	
	public int getStat_Magic_DEF()
	{return Stat_Magic_DEF;}
	
	public void setStat_Magic_DEF(int stat_Magic_DEF)
	{Stat_Magic_DEF = stat_Magic_DEF;}

	public void addStat_Magic_DEF(int value)
	{Stat_Magic_DEF = IntegerProcessing(Stat_Magic_DEF, value);}
	
	public int getStat_MagicDEFcrash()
	{return Stat_MagicDEFcrash;}
	
	public void setStat_MagicDEFcrash(int stat_MagicDEFcrash)
	{Stat_MagicDEFcrash = stat_MagicDEFcrash;}

	public void addStat_MagicDEFcrash(int value)
	{Stat_MagicDEFcrash = IntegerProcessing(Stat_MagicDEFcrash, value);}
	
	public int getStat_Magic_Protect()
	{return Stat_Magic_Protect;}
	
	public void setStat_Magic_Protect(int stat_Magic_Protect)
	{Stat_Magic_Protect = stat_Magic_Protect;}

	public void addStat_Magic_Protect(int value)
	{Stat_Magic_Protect = IntegerProcessing(Stat_Magic_Protect, value);}
	
	public long getStat_AttackTime()
	{return Stat_AttackTime;}
	
	public void setStat_AttackTime(long stat_AttackTime)
	{Stat_AttackTime = stat_AttackTime;}
	
	public void addStat_AttackTime(long value)
	{Stat_AttackTime = LongProcessing(Stat_AttackTime, value);}
	
	public byte getStat_BowPull()
	{return Stat_BowPull;}
	
	public void setStat_BowPull(byte stat_BowPull)
	{Stat_BowPull = stat_BowPull;}
	
	public boolean isAlert_Damage()
	{return Alert_Damage;}
	
	public void setAlert_Damage(boolean alert_Damage)
	{Alert_Damage = alert_Damage;}
	
	public boolean isAlert_MobHealth()
	{return Alert_MobHealth;}
	
	public void setAlert_MobHealth(boolean alert_MobHealth)
	{Alert_MobHealth = alert_MobHealth;}
	
	public boolean isAlert_Critical()
	{return Alert_Critical;}
	
	public void setAlert_Critical(boolean alert_Critical)
	{Alert_Critical = alert_Critical;}
	
	public boolean isAlert_AttackDelay()
	{return Alert_AttackDelay;}
	
	public void setAlert_AttackDelay(boolean alert_AttackDelay)
	{Alert_AttackDelay = alert_AttackDelay;}
	
	public boolean isAlert_ItemPickUp()
	{return Alert_ItemPickUp;}
	
	public void setAlert_ItemPickUp(boolean alert_ItemPickUp)
	{Alert_ItemPickUp = alert_ItemPickUp;}
	
	public boolean isAlert_EXPget()
	{return Alert_EXPget;}
	
	public void setAlert_EXPget(boolean alert_EXPget)
	{Alert_EXPget = alert_EXPget;}
	
	public boolean isOption_EquipLook()
	{return Option_EquipLook;}
	
	public void setOption_EquipLook(boolean option_EquipLook)
	{Option_EquipLook = option_EquipLook;}
	
	public boolean isOption_HotBarSound()
	{return Option_HotBarSound;}
	
	public void setOption_HotBarSound(boolean option_HotBarSound)
	{Option_HotBarSound = option_HotBarSound;}
	
	public byte getOption_ChattingType()
	{return Option_ChattingType;}
	
	public void setOption_ChattingType(byte option_ChattingType)
	{Option_ChattingType = option_ChattingType;}
	
	public long getETC_Party()
	{return ETC_Party;}
	
	public void setETC_Party(long eTC_Party)
	{ETC_Party = eTC_Party;}
	
	public boolean isETC_Death()
	{return ETC_Death;}
	
	public void setETC_Death(boolean eTC_Death)
	{ETC_Death = eTC_Death;}
	
	public String getETC_CurrentArea()
	{return ETC_CurrentArea;}
	
	public void setETC_CurrentArea(String eTC_CurrentArea)
	{ETC_CurrentArea = eTC_CurrentArea;}
	
	public String getETC_LastVisited()
	{return ETC_LastVisited;}
	
	public void setETC_LastVisited(String eTC_LastVisited)
	{ETC_LastVisited = eTC_LastVisited;}
	
	public long getETC_BuffCoolTime()
	{return ETC_BuffCoolTime;}
	
	public void setETC_BuffCoolTime(long eTC_BuffCoolTime)
	{ETC_BuffCoolTime = eTC_BuffCoolTime;}
	
	public boolean isDungeon_NormalBGMplaying()
	{return Dungeon_NormalBGMplaying;}
	
	public void setDungeon_NormalBGMplaying(boolean dungeon_NormalBGMplaying)
	{Dungeon_NormalBGMplaying = dungeon_NormalBGMplaying;}
	
	public boolean isDungeon_BossBGMplaying()
	{return Dungeon_BossBGMplaying;}
	
	public void setDungeon_BossBGMplaying(boolean dungeon_BossBGMplaying)
	{Dungeon_BossBGMplaying = dungeon_BossBGMplaying;}
	
	public long getDungeon_UTC()
	{return Dungeon_UTC;}

	public void setDungeon_UTC(long dungeon_UTC) {
		Dungeon_UTC = dungeon_UTC;
	}

	public String getDungeon_Enter() {
		return Dungeon_Enter;
	}

	public void setDungeon_Enter(String dungeon_Enter) {
		Dungeon_Enter = dungeon_Enter;
	}

	public boolean isDeath()
	{return Death;}
	
	public void setDeath(boolean death)
	{Death = death;}

	public boolean isBgmOn()
	{return Option_BGM;}
	
	public void setBgm(boolean death)
	{Option_BGM = death;}

	public boolean isClickUse()
	{return Option_ClickUse;}
	
	public void setClickUse(boolean death)
	{Option_ClickUse = death;}
	
	public Location getLastDeathPoint()
	{return LastDeathPoint;}
	
	public void setLastDeathPoint(Location lastDeathPoint)
	{LastDeathPoint = lastDeathPoint;	}
	
	public UserObject()
	{}
	
	public UserObject(Player player)
	{
		this.player = player;
		PlayerName = player.getName();
		PlayerUUID = player.getUniqueId().toString();
	  	YamlLoader PlayerConfig = new YamlLoader();
		PlayerConfig.getConfig("Stats/"+PlayerUUID+".yml");
		if(PlayerConfig.contains("Stat.STR")==false)
		{
			PlayerConfig.getConfig("Level.yml");
			Stat_Level = 1;
			Stat_RealLevel = 1;
			Stat_MaxEXP = PlayerConfig.getLong(1+"");
			PlayerConfig.getConfig("ETC/NewBie.yml");

			if(main.MainServerOption.economy!=null)
				main.MainServerOption.economy.depositPlayer(player.getName(), PlayerConfig.getInt("SupportMoney"));
			else
				Stat_Money = PlayerConfig.getInt("SupportMoney");
			
			PlayerConfig.getConfig("config.yml");
			Stat_SkillPoint = PlayerConfig.getInt("DefaultStat.SkillPoint");
			Stat_StatPoint = PlayerConfig.getInt("DefaultStat.StatPoint");
			Stat_EXP = 0;
			Stat_HP = PlayerConfig.getInt("DefaultStat.HP");
			Stat_MaxHP = PlayerConfig.getInt("DefaultStat.HP");
			Stat_Wond = PlayerConfig.getInt("DefaultStat.Wond");
			Stat_MP = PlayerConfig.getInt("DefaultStat.MP");
			Stat_MaxMP = PlayerConfig.getInt("DefaultStat.MP");
			Stat_STR = PlayerConfig.getInt("DefaultStat.STR");
			Stat_DEX = PlayerConfig.getInt("DefaultStat.DEX");
			Stat_INT = PlayerConfig.getInt("DefaultStat.INT");
			Stat_WILL = PlayerConfig.getInt("DefaultStat.WILL");
			Stat_LUK = PlayerConfig.getInt("DefaultStat.LUK");
			Stat_Balance = PlayerConfig.getInt("DefaultStat.Balance");
			Stat_Critical = PlayerConfig.getInt("DefaultStat.Critical");
			Stat_DEF = PlayerConfig.getInt("DefaultStat.DEF");
			Stat_DEFcrash = PlayerConfig.getInt("DefaultStat.DEFcrash");
			Stat_Protect = PlayerConfig.getInt("DefaultStat.Protect");
			Stat_Magic_DEF = PlayerConfig.getInt("DefaultStat.Magic_DEF");
			Stat_MagicDEFcrash = PlayerConfig.getInt("DefaultStat.MagicDEFcrash");
			Stat_Magic_Protect = PlayerConfig.getInt("DefaultStat.Magic_Protect");
			Stat_AttackTime = -1;
			Stat_BowPull = 0;

			Alert_Damage = true;
			Alert_MobHealth = true;
			Alert_Critical = true;
			Alert_AttackDelay = true;
			Alert_ItemPickUp = true;
			Alert_EXPget = true;

			Option_BGM = true;
			Option_ClickUse = true;
			Option_EquipLook = true;
			Option_HotBarSound = true;
			Option_ChattingType = 0;
			Option_SeeInventory = true;

			ETC_Party = -1;
			ETC_Death = false;
			ETC_CurrentArea = "null";
			ETC_LastVisited = "null";
			ETC_BuffCoolTime = 0;
		  	
			Dungeon_Enter = null;
			Dungeon_UTC = -1;
			Dungeon_NormalBGMplaying = false;
			Dungeon_BossBGMplaying = false;

			Death = false;
			LastDeathPoint = null;
			saveAll();
		}
		else
		{

			Stat_Level = PlayerConfig.getInt("Stat.Level");
			Stat_RealLevel = PlayerConfig.getInt("Stat.RealLevel");
			Stat_SkillPoint = PlayerConfig.getInt("Stat.SkillPoint");
			Stat_StatPoint = PlayerConfig.getInt("Stat.StatPoint");
			Stat_EXP = PlayerConfig.getLong("Stat.EXP");
			Stat_MaxEXP = PlayerConfig.getLong("Stat.MaxEXP");
			if(main.MainServerOption.economy!=null)
				Stat_Money = (long) main.MainServerOption.economy.getBalance(player.getName());
			else
				Stat_Money = PlayerConfig.getLong("Stat.Money");
			Stat_HP = PlayerConfig.getInt("Stat.HP");
			Stat_MaxHP = PlayerConfig.getInt("Stat.MAXHP");
			Stat_Wond = PlayerConfig.getInt("Stat.Wond");
			Stat_MP = PlayerConfig.getInt("Stat.MP");
			Stat_MaxMP = PlayerConfig.getInt("Stat.MAXMP");
			Stat_STR = PlayerConfig.getInt("Stat.STR");
			Stat_DEX = PlayerConfig.getInt("Stat.DEX");
			Stat_INT = PlayerConfig.getInt("Stat.INT");
			Stat_WILL = PlayerConfig.getInt("Stat.WILL");
			Stat_LUK = PlayerConfig.getInt("Stat.LUK");
			Stat_Balance = PlayerConfig.getInt("Stat.Balance");
			Stat_Critical = PlayerConfig.getInt("Stat.Critical");
			Stat_DEF = PlayerConfig.getInt("Stat.DEF");
			Stat_DEFcrash = PlayerConfig.getInt("Stat.DEFcrash");
			Stat_Protect = PlayerConfig.getInt("Stat.Protect");
			Stat_Magic_DEF = PlayerConfig.getInt("Stat.Magic_DEF");
			Stat_MagicDEFcrash = PlayerConfig.getInt("Stat.MagicDEFcrash");
			Stat_Magic_Protect = PlayerConfig.getInt("Stat.Magic_Protect");
			Stat_AttackTime = PlayerConfig.getLong("Stat.AttackTime");
			Stat_BowPull = (byte) PlayerConfig.getInt("Stat.BowPull");

			Alert_Damage = PlayerConfig.getBoolean("Alert.Damage");
			Alert_MobHealth = PlayerConfig.getBoolean("Alert.MobHealth");
			Alert_Critical = PlayerConfig.getBoolean("Alert.Critical");
			Alert_AttackDelay = PlayerConfig.getBoolean("Alert.AttackDelay");
			Alert_ItemPickUp = PlayerConfig.getBoolean("Alert.ItemPickUp");
			Alert_EXPget = PlayerConfig.getBoolean("Alert.EXPget");
			
			Option_EquipLook = PlayerConfig.getBoolean("Option.EquipLook");
			Option_HotBarSound = PlayerConfig.getBoolean("Option.HotBarSound");
			Option_ChattingType = (byte) PlayerConfig.getInt("Option.ChattingType");
			Option_BGM = PlayerConfig.getBoolean("Option.BGM");
			Option_ClickUse = PlayerConfig.getBoolean("Option.ClickUse");
			Option_SeeInventory = PlayerConfig.getBoolean("Option.SeeInventory");

			ETC_Party = PlayerConfig.getLong("ETC.Party");
			ETC_Death = PlayerConfig.getBoolean("ETC.Death");
			ETC_CurrentArea = PlayerConfig.getString("ETC.CurrentArea");
			ETC_LastVisited = PlayerConfig.getString("ETC.LastVisited");
			ETC_BuffCoolTime = PlayerConfig.getInt("ETC.BuffCoolTime");
		  	
			Dungeon_Enter = PlayerConfig.getString("Dungeon.Enter");
			Dungeon_UTC = PlayerConfig.getLong("Dungeon.UTC");
			Dungeon_NormalBGMplaying = PlayerConfig.getBoolean("Dungeon.NormalBGMplaying");
			Dungeon_BossBGMplaying = PlayerConfig.getBoolean("Dungeon.BossBGMplaying");

			Death = PlayerConfig.getBoolean("Death");
			if(PlayerConfig.getString("LastDeathPoint.World") != null)
			{
				Location loc = new Location(Bukkit.getWorld(PlayerConfig.getString("LastDeathPoint.World")), PlayerConfig.getInt("LastDeathPoint.X"), PlayerConfig.getInt("LastDeathPoint.Y"), PlayerConfig.getInt("LastDeathPoint.Z"), (float)PlayerConfig.getDouble("LastDeathPoint.Yaw"), (float)PlayerConfig.getDouble("LastDeathPoint.Pitch"));
				LastDeathPoint = loc;
			}
			else
				LastDeathPoint = null;

		  	YamlLoader PlayerJob = new YamlLoader();
			PlayerJob.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
			if(PlayerJob.contains("Job.Root"))
				PlayerRootJob =  PlayerJob.getString("Job.Root");
			else
			{
			  	YamlLoader Config = new YamlLoader();
		    	Config.getConfig("config.yml");
				if(PlayerJob.getString("Job.Type").equals(Config.getString("Server.DefaultJob")))
					PlayerJob.set("Job.Root", Config.getString("Server.DefaultJob"));
				else
				{
					boolean getIt = false;
				  	YamlLoader JobList = new YamlLoader();
					JobList.getConfig("Skill/JobList.yml");
					Object[] Job = JobList.getConfigurationSection("MapleStory").getKeys(false).toArray();
					for(int count = 0; count < Job.length; count++)
					{
						Object[] q = JobList.getConfigurationSection("MapleStory."+Job[count].toString()).getKeys(false).toArray();
						for(int counter=0;counter<q.length;counter++)
						{
							if(q[counter].toString().equals(PlayerJob.getString("Job.Type")))
							{
								PlayerJob.set("Job.Root", Job[count].toString());
								PlayerRootJob =  Job[count].toString();
								getIt = true;
								break;
							}
						}
						if(getIt)
							break;
					}
					if(getIt==false)
					{
						PlayerJob.set("Job.Type", Config.getString("Server.DefaultJob"));
						PlayerJob.set("Job.Root", Config.getString("Server.DefaultJob"));
						PlayerRootJob = Config.getString("Server.DefaultJob");
					}
				}
				PlayerJob.saveConfig();
			}
		}
		MainServerOption.PlayerList.put(player.getUniqueId().toString(), this);
		return;
	}
	
	public void saveAll()
	{
	  	YamlLoader PlayerConfig = new YamlLoader();
		PlayerConfig.getConfig("Stats/"+PlayerUUID+".yml");

	  	PlayerConfig.set("Player.Name", PlayerName);
	  	PlayerConfig.set("Player.UUID", PlayerUUID);
		
	  	PlayerConfig.set("Stat.Level", Stat_Level);
	  	PlayerConfig.set("Stat.RealLevel", Stat_RealLevel);
	  	PlayerConfig.set("Stat.SkillPoint", Stat_SkillPoint);
	  	PlayerConfig.set("Stat.StatPoint", Stat_StatPoint);
	  	PlayerConfig.set("Stat.EXP", Stat_EXP);
	  	PlayerConfig.set("Stat.MaxEXP", Stat_MaxEXP);
		if(main.MainServerOption.economy!=null)
			PlayerConfig.set("Stat.Money", (long) main.MainServerOption.economy.getBalance(player.getName()));
		else
			PlayerConfig.set("Stat.Money", Stat_Money);
	  	PlayerConfig.set("Stat.HP", Stat_HP);
	  	PlayerConfig.set("Stat.MAXHP", Stat_MaxHP);
	  	PlayerConfig.set("Stat.Wond", Stat_Wond);
	  	PlayerConfig.set("Stat.MP", Stat_MP);
	  	PlayerConfig.set("Stat.MAXMP", Stat_MaxMP);
	  	PlayerConfig.set("Stat.STR", Stat_STR);
	  	PlayerConfig.set("Stat.DEX", Stat_DEX);
	  	PlayerConfig.set("Stat.INT", Stat_INT);
	  	PlayerConfig.set("Stat.WILL", Stat_WILL);
	  	PlayerConfig.set("Stat.LUK", Stat_LUK);
	  	PlayerConfig.set("Stat.Balance", Stat_Balance);
	  	PlayerConfig.set("Stat.Critical", Stat_Critical);
	  	PlayerConfig.set("Stat.DEF", Stat_DEF);
	  	PlayerConfig.set("Stat.DEFcrash", Stat_DEFcrash);
	  	PlayerConfig.set("Stat.Protect", Stat_Protect);
	  	PlayerConfig.set("Stat.Magic_DEF", Stat_Magic_DEF);
	  	PlayerConfig.set("Stat.MagicDEFcrash", Stat_MagicDEFcrash);
	  	PlayerConfig.set("Stat.Magic_Protect", Stat_Magic_Protect);
	  	PlayerConfig.set("Stat.AttackTime", Stat_AttackTime);
	  	PlayerConfig.set("Stat.BowPull", Stat_BowPull);

	  	PlayerConfig.set("Alert.Damage", Alert_Damage);
	  	PlayerConfig.set("Alert.MobHealth", Alert_MobHealth);
	  	PlayerConfig.set("Alert.Critical", Alert_Critical);
	  	PlayerConfig.set("Alert.AttackDelay", Alert_AttackDelay);
	  	PlayerConfig.set("Alert.ItemPickUp", Alert_ItemPickUp);
	  	PlayerConfig.set("Alert.EXPget", Alert_EXPget);

	  	PlayerConfig.set("Option.EquipLook", Option_EquipLook);
	  	PlayerConfig.set("Option.ChattingType", Option_ChattingType);
	  	PlayerConfig.set("Option.HotBarSound", Option_HotBarSound);
	  	PlayerConfig.set("Option.BGM", Option_BGM);
	  	PlayerConfig.set("Option.ClickUse", Option_ClickUse);
	  	PlayerConfig.set("Option.SeeInventory", Option_SeeInventory);

	  	PlayerConfig.set("ETC.Party", ETC_Party);
	  	PlayerConfig.set("ETC.Death", ETC_Death);
	  	PlayerConfig.set("ETC.CurrentArea", ETC_CurrentArea);
	  	PlayerConfig.set("ETC.LastVisited", ETC_LastVisited);
	  	PlayerConfig.set("ETC.BuffCoolTime", ETC_BuffCoolTime);
	  	
	  	PlayerConfig.set("Dungeon.Enter", Dungeon_Enter);
	  	PlayerConfig.set("Dungeon.UTC", Dungeon_UTC);
	  	PlayerConfig.set("Dungeon.NormalBGMplaying", Dungeon_NormalBGMplaying);
	  	PlayerConfig.set("Dungeon.BossBGMplaying", Dungeon_BossBGMplaying);
	  	
	  	PlayerConfig.set("Death", Death);
	  	if(LastDeathPoint != null)
	  	{
		  	PlayerConfig.set("LastDeathPoint.World", LastDeathPoint.getWorld().getName());
		  	PlayerConfig.set("LastDeathPoint.X", LastDeathPoint.getX());
		  	PlayerConfig.set("LastDeathPoint.Y", LastDeathPoint.getY());
		  	PlayerConfig.set("LastDeathPoint.Z", LastDeathPoint.getZ());
		  	PlayerConfig.set("LastDeathPoint.Yaw", LastDeathPoint.getYaw());
		  	PlayerConfig.set("LastDeathPoint.Pitch", LastDeathPoint.getPitch());
	  	}
	  	PlayerConfig.saveConfig();
	}
	
	private int IntegerProcessing(int BaseNumber, int AddNumber)
	{
		if(AddNumber < 0)
		{
			if(BaseNumber + AddNumber >= Integer.MIN_VALUE)
				return BaseNumber + AddNumber;
			else
				return Integer.MIN_VALUE;
		}
		else
		{
			if(BaseNumber+AddNumber <= Integer.MAX_VALUE)
				return BaseNumber+AddNumber;
			else
				return Integer.MAX_VALUE;
		}
	}
	
	private long LongProcessing(long BaseNumber, long AddNumber)
	{
		if(AddNumber < 0)
		{
			if(BaseNumber + AddNumber >= Long.MIN_VALUE)
				return BaseNumber + AddNumber;
			else
				return Long.MIN_VALUE;
		}
		else
		{
			if(BaseNumber+AddNumber <= Long.MAX_VALUE)
				return BaseNumber+AddNumber;
			else
				return Long.MAX_VALUE;
		}
	}

	public String getPlayerRootJob()
	{
		return PlayerRootJob;
	}

	public void setPlayerRootJob(String playerRootJob)
	{
		PlayerRootJob = playerRootJob;
	}

	public boolean isOption_SeeInventory() {
		return Option_SeeInventory;
	}

	public void setOption_SeeInventory(boolean option_SeeInventory) {
		Option_SeeInventory = option_SeeInventory;
	}
}