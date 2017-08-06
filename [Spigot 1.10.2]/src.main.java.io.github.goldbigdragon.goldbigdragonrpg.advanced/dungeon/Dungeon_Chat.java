package dungeon;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import net.md_5.bungee.api.ChatColor;
import user.UserData_Object;
import util.YamlLoader;



public class Dungeon_Chat
{
	public void PlayerChatrouter(PlayerChatEvent event)
	{
		Player player = event.getPlayer();
		UserData_Object u = new UserData_Object();
		if(u.getType(player).compareTo("DungeonMain")==0)
			DungeonMainChatting(event);
		else if(u.getType(player).compareTo("EnterCard")==0)
			EnterCardChatting(event);
		else if(u.getType(player).compareTo("Altar")==0)
			AltarChatting(event);
	}
	
	
	private void DungeonMainChatting(PlayerChatEvent event)
	{
		
		Player player = event.getPlayer();
		UserData_Object u = new UserData_Object();
		String Message = ChatColor.stripColor(event.getMessage());
	  	YamlLoader dungeonListYaml = new YamlLoader();
		if(u.getString(player,(byte)0).compareTo("ND")==0)
		{
			dungeonListYaml.getConfig("Dungeon/DungeonList.yml");
			if(Message.length()>=11)
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage(ChatColor.RED+"[����] : �̸��� �ʹ� ��ϴ�! (10�� �̳�)");
				return;
			}
			if(dungeonListYaml.contains(Message))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage(ChatColor.RED+"[����] : �ش� �̸��� ������ �̹� �����մϴ�!");
				return;
			}
			else
			{
				dungeonListYaml.set(Message, servertick.ServerTick_Main.nowUTC);
				dungeonListYaml.saveConfig();
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+Message+"/Option.yml");
				dungeonListYaml.set("Type.ID", 1);
				dungeonListYaml.set("Type.DATA", 0);
				dungeonListYaml.set("Type.Name", main.Main_ServerOption.DungeonTheme.get(0));
				dungeonListYaml.set("Size", 5);
				dungeonListYaml.set("Maze_Level", 1);
				dungeonListYaml.set("District.Level", 0);
				dungeonListYaml.set("District.RealLevel", 0);
				dungeonListYaml.set("Reward.Money", 1000);
				dungeonListYaml.set("Reward.EXP", 10000);
				dungeonListYaml.saveConfig();
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+Message+"/Monster.yml");
				for(int count = 0; count < 8; count ++)
				{
					dungeonListYaml.createSection("Boss."+count);
					dungeonListYaml.createSection("SubBoss."+count);
					dungeonListYaml.createSection("High."+count);
					dungeonListYaml.createSection("Middle."+count);
					dungeonListYaml.createSection("Normal."+count);
				}
				dungeonListYaml.saveConfig();
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+Message+"/Reward.yml");
				for(int count = 0; count < 8; count++)
				{
					dungeonListYaml.createSection("100."+count);
					dungeonListYaml.createSection("90."+count);
					dungeonListYaml.createSection("50."+count);
					dungeonListYaml.createSection("10."+count);
					dungeonListYaml.createSection("1."+count);
					dungeonListYaml.createSection("0."+count);
				}
				dungeonListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[����] : ���� �߰� �Ϸ�!");
			}
			u.clearAll(player);
			new dungeon.Dungeon_GUI().DungeonListMainGUI(player, 0, 52);
			return;
		}
		else if(u.getString(player,(byte)0).compareTo("DS")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 5, 50))
			{
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+u.getString(player, (byte)1)+"/Option.yml");
				dungeonListYaml.set("Size", Integer.parseInt(event.getMessage()));
				dungeonListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().DungeonSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
		else if(u.getString(player,(byte)0).compareTo("DML")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 10))
			{
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+u.getString(player, (byte)1)+"/Option.yml");
				dungeonListYaml.set("Maze_Level", Integer.parseInt(event.getMessage()));
				dungeonListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().DungeonSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
		else if(u.getString(player,(byte)0).compareTo("DDL")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 2000000))
			{
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+u.getString(player, (byte)1)+"/Option.yml");
				dungeonListYaml.set("District.Level", Integer.parseInt(event.getMessage()));
				dungeonListYaml.saveConfig();
				u.setString(player, (byte)0, "DDRL");//DungeonDistrictRealLevel
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[����] : ���� ���� ���� ���� ������ �Է� �� �ּ���!");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("DDRL")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 2000000))
			{
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+u.getString(player, (byte)1)+"/Option.yml");
				dungeonListYaml.set("District.RealLevel", Integer.parseInt(event.getMessage()));
				dungeonListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().DungeonSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
		else if(u.getString(player,(byte)0).compareTo("DRM")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 20000000))
			{
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+u.getString(player, (byte)1)+"/Option.yml");
				dungeonListYaml.set("Reward.Money", Integer.parseInt(event.getMessage()));
				dungeonListYaml.saveConfig();
				u.setString(player, (byte)0, "DRE");//DungeonRewardEXP
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[����] : ���� Ŭ���� ���� ����ġ�� �Է� �� �ּ���!");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("DRE")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 100000000))
			{
				dungeonListYaml.getConfig("Dungeon/Dungeon/"+u.getString(player, (byte)1)+"/Option.yml");
				dungeonListYaml.set("Reward.EXP", Integer.parseInt(event.getMessage()));
				dungeonListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().DungeonSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
	}
	
	private void EnterCardChatting(PlayerChatEvent event)
	{
		
		Player player = event.getPlayer();
		UserData_Object u = new UserData_Object();
		String Message = ChatColor.stripColor(event.getMessage());
	  	YamlLoader enterCardListYaml = new YamlLoader();
		enterCardListYaml.getConfig("Dungeon/EnterCardList.yml");
		if(u.getString(player,(byte)0).compareTo("NEC")==0)
		{
			if(Message.length()>=16)
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage(ChatColor.RED+"[����] : �̸��� �ʹ� ��ϴ�! (15�� �̳�)");
				return;
			}
			if(enterCardListYaml.contains(Message))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage(ChatColor.RED+"[����] : �ش� �̸��� �������� �̹� �����մϴ�!");
				return;
			}
			else
			{
				enterCardListYaml.set(Message+".ID", 358);
				enterCardListYaml.set(Message+".DATA", 0);
				enterCardListYaml.createSection(Message+".Dungeon");
				enterCardListYaml.set(Message+".Capacity", 8);
				enterCardListYaml.set(Message+".Hour", -1);
				enterCardListYaml.set(Message+".Min", 0);
				enterCardListYaml.set(Message+".Sec", 0);
				enterCardListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[����] : ������ �߰� �Ϸ�!");
			}
			u.clearAll(player);
			new dungeon.Dungeon_GUI().DungeonListMainGUI(player, 0, 358);
			return;
		}
		else if(u.getString(player,(byte)0).compareTo("ECID")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 1, 2267))
			{
				enterCardListYaml.set(u.getString(player, (byte)1)+".ID", Integer.parseInt(event.getMessage()));
				enterCardListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				u.setString(player, (byte)0, "ECDATA");
				player.sendMessage(ChatColor.GREEN+"[����] : ������ ������ Ÿ�� DATA�� �Է� �� �ּ���.");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("ECDATA")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 20))
			{
				enterCardListYaml.set(u.getString(player, (byte)1)+".DATA", Integer.parseInt(event.getMessage()));
				enterCardListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().EnterCardSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
		else if(u.getString(player,(byte)0).compareTo("ECC")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 1, 32))
			{
				enterCardListYaml.set(u.getString(player, (byte)1)+".Capacity", Integer.parseInt(event.getMessage()));
				enterCardListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().EnterCardSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
		else if(u.getString(player,(byte)0).compareTo("ECUH")==0)
		{
			if(isIntMinMax(event.getMessage(), player, -1, 24))
			{
				if(Integer.parseInt(event.getMessage())==-1)
				{
					enterCardListYaml.set(u.getString(player, (byte)1)+".Hour", -1);
					enterCardListYaml.saveConfig();
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					new dungeon.Dungeon_GUI().EnterCardSetUpGUI(player, u.getString(player, (byte)1));
					u.clearAll(player);
				}
				else
				{
					enterCardListYaml.set(u.getString(player, (byte)1)+".Hour", Integer.parseInt(event.getMessage()));
					enterCardListYaml.saveConfig();
					u.setString(player, (byte)0, "ECUM");//EnterCardUseableMinute 
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.GREEN+"[������] : ��ȿ ���� �Է� �� �ּ���. (�ִ� 59��)");
				}
			}
		}
		else if(u.getString(player,(byte)0).compareTo("ECUM")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 59))
			{
				enterCardListYaml.set(u.getString(player, (byte)1)+".Min",Integer.parseInt(event.getMessage()));
				enterCardListYaml.saveConfig();
				u.setString(player, (byte)0, "ECUS");//EnterCardUseableSecond
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[������] : ��ȿ �ʸ� �Է� �� �ּ���. (�ִ� 59��)");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("ECUS")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 59))
			{
				enterCardListYaml.set(u.getString(player, (byte)1)+".Sec",Integer.parseInt(event.getMessage()));
				enterCardListYaml.saveConfig();
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				new dungeon.Dungeon_GUI().EnterCardSetUpGUI(player, u.getString(player, (byte)1));
				u.clearAll(player);
			}
		}
	}
	
	private void AltarChatting(PlayerChatEvent event)
	{
		
		Player player = event.getPlayer();
		UserData_Object u = new UserData_Object();
		String Message = ChatColor.stripColor(event.getMessage());
		if(u.getString(player,(byte)0).compareTo("NA_Q")==0)
		{
			if(askOX(Message, player)==1)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.2F);
				u.clearAll(player);
				new dungeon.Dungeon_GUI().AltarShapeListGUI(player);
			}
			else if(Message.compareTo("�ƴϿ�")==0||Message.compareTo("x")==0
				||Message.compareTo("X")==0)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[����] : ���� ��ġ�� ��ҵǾ����ϴ�.");
				u.clearAll(player);
				new dungeon.Dungeon_GUI().DungeonListMainGUI(player, 0, 120);
			}
			return;
		}
		else if(u.getString(player,(byte)0).compareTo("EAN")==0)
		{
		  	YamlLoader alterListYaml = new YamlLoader();
			alterListYaml.getConfig("Dungeon/AltarList.yml");
			String AltarName = u.getString(player, (byte)1).substring(2, u.getString(player, (byte)1).length());
			alterListYaml.set(AltarName+".Name", event.getMessage());
			alterListYaml.saveConfig();
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			new dungeon.Dungeon_GUI().AltarSettingGUI(player, AltarName);
			u.clearAll(player);
		}
	}
	
	
	
	
	
	private byte askOX(String message,Player player)
	{
		if(message.split(" ").length <= 1)
		{
			if(message.compareTo("x")==0||message.compareTo("X")==0||message.compareTo("�ƴϿ�")==0)
				return 0;
			else if(message.compareTo("o")==0||message.compareTo("O")==0||message.compareTo("��")==0)
				return 1;
			else
			{
				player.sendMessage(ChatColor.RED + "[SYSTEM] : [��/O] Ȥ�� [�ƴϿ�/X]�� �Է� �� �ּ���!");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			
		}
		else
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : [��/O] Ȥ�� [�ƴϿ�/X]�� �Է� �� �ּ���!");
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
		return -1;
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
}
