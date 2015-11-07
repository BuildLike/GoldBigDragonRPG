package GBD.GoldBigDragon_Advanced.Event;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import GBD.GoldBigDragon_Advanced.Main.Main;
import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;

public class BlockBreak
{
	private GBD.GoldBigDragon_Advanced.Effect.Sound sound = new GBD.GoldBigDragon_Advanced.Effect.Sound();
	private GBD.GoldBigDragon_Advanced.Util.Number n = new GBD.GoldBigDragon_Advanced.Util.Number();
	private GBD.GoldBigDragon_Advanced.Effect.PacketSender t = new GBD.GoldBigDragon_Advanced.Effect.PacketSender();
	private GBD.GoldBigDragon_Advanced.Config.StatConfig stat = new GBD.GoldBigDragon_Advanced.Config.StatConfig();
	
	public void BlockBreaking(BlockBreakEvent event)
	{
		GBD.GoldBigDragon_Advanced.Event.Level LV = new GBD.GoldBigDragon_Advanced.Event.Level();
		GBD.GoldBigDragon_Advanced.Event.ItemDrop ID = new GBD.GoldBigDragon_Advanced.Event.ItemDrop();
		GBD.GoldBigDragon_Advanced.Event.Damage D = new GBD.GoldBigDragon_Advanced.Event.Damage();
		Player player = (Player) event.getPlayer();
		YamlController Event_YC = GBD.GoldBigDragon_Advanced.Main.Main.Event_YC;
		YamlManager Config = Event_YC.getNewConfig("config.yml");
		
		GBD.GoldBigDragon_Advanced.ETC.Area A = new GBD.GoldBigDragon_Advanced.ETC.Area();
		String[] Area = A.getAreaName(event.getBlock());
		if(Area != null)
		{
			if(A.getAreaOption(Area[0], (char) 1) == false && event.getPlayer().isOp() == false)
			{
				event.setCancelled(true);
				sound.SP(event.getPlayer(), org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
				event.getPlayer().sendMessage(ChatColor.RED + "[SYSTEM] : " + ChatColor.YELLOW + Area[1] + ChatColor.RED + " ���� ������ ��� ä���� �Ұ����մϴ�!");
				return;
			}
			if(player.getGameMode() != GameMode.CREATIVE)
			{
				YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
				YamlManager AreaConfig =GUI_YC.getNewConfig("Area/AreaList.yml");
				String BlockData = event.getBlock().getTypeId()+":"+event.getBlock().getData();
				if(AreaConfig.contains(Area[0]+".Mining."+BlockData) == true)
				{
					event.setCancelled(true);
					event.getBlock().setType(Material.AIR);
					GBD.GoldBigDragon_Advanced.Event.ItemDrop ItemDrop = new GBD.GoldBigDragon_Advanced.Event.ItemDrop();
					Location loc = event.getBlock().getLocation();
					loc.setY(loc.getY()+1);
					loc.setX(loc.getX()+0.5);
					loc.setZ(loc.getZ()+0.5);
					if(AreaConfig.getString(Area[0]+".Mining."+BlockData).equals("0:0") == false)
						ItemDrop.CustomItemDrop(event.getBlock().getLocation(), AreaConfig.getItemStack(Area[0]+".Mining."+BlockData));
				}
			}
		}
		
		Quest(event, player);
		
	  	if(Event_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		stat.CreateNewStats(player);

	  	YamlManager YM = Event_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		D.decreaseDurabilityWeapon(player);
		switch(event.getBlock().getType())
		{
			case COAL_ORE:
				LV.EXPadd(player, Config.getLong("Getting.Coal.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Coal.Money"));
				break;
			case IRON_ORE:
				LV.EXPadd(player, Config.getLong("Getting.Iron.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Iron.Money"));
				break;
			case GOLD_ORE:
				LV.EXPadd(player, Config.getLong("Getting.Gold.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Gold.Money"));
				break;
			case DIAMOND_ORE:
				LV.EXPadd(player, Config.getLong("Getting.Diamond.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Diamond.Money"));
				break;
			case EMERALD_ORE:
				LV.EXPadd(player, Config.getLong("Getting.Emerald.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Emerald.Money"));
				break;
			case REDSTONE_ORE:
				LV.EXPadd(player, Config.getLong("Getting.RedStone.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.RedStone.Money"));
				break;
			case LAPIS_ORE:
				LV.EXPadd(player, Config.getLong("Getting.Lapis.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Lapis.Money"));
				break;
			case LOG:
				LV.EXPadd(player, Config.getLong("Getting.Wood.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Wood.Money"));
				break;
			case LOG_2:
				LV.EXPadd(player, Config.getLong("Getting.Wood.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.Wood.Money"));
				break;
			case QUARTZ_ORE:
				LV.EXPadd(player, Config.getLong("Getting.NetherQuartz.EXP"), player.getLocation());
				ID.MoneyDrop(player.getLocation(), Config.getInt("Getting.NetherQuartz.Money"));
				break;
			default:
				break;	
		}
		if(event.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("Dungeon")==true
				&&player.isOp()==false)
		{
			event.setCancelled(true);
			return;
		}

		int lucky = YM.getInt("Stat.LUK")/30;
		if(lucky >= 150) lucky =150;
		if(lucky <= 0) lucky = 1;
		if(lucky >= n.RandomNum(0, 1000))
		{
			int amount = 0;
			int luckysize = n.RandomNum(0, 100);
			if(luckysize <= 80){t.sendActionBar(player, ChatColor.YELLOW +""+ChatColor.BOLD+ "��Ű ���ʽ�!");amount = 1;	sound.SP(player, Sound.LEVEL_UP, 0.5F, 0.9F);}
			else if(luckysize <= 95){t.sendActionBar(player, ChatColor.YELLOW +""+ChatColor.BOLD+ "�� ��Ű ���ʽ�!");amount = 5;	sound.SP(player, Sound.LEVEL_UP, 0.7F, 1.0F);}
			else{t.sendActionBar(player, ChatColor.YELLOW +""+ChatColor.BOLD+ "���� ��Ű ���ʽ�!");amount = 20;	sound.SP(player, Sound.LEVEL_UP, 1.0F, 1.1F);}
			switch(event.getBlock().getType())
			{
				case COAL_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 263,0,amount);
					break;
				case IRON_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 15,0,amount);
					break;
				case GOLD_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 14,0,amount);
					break;
				case DIAMOND_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 264,0,amount);
					break;
				case EMERALD_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 388,0,amount);
					break;
				case REDSTONE_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 331,0,amount);
					break;
				case LAPIS_ORE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 351,4,amount);
					break;
				case SEA_LANTERN:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), 410,0,amount);
					break;
				case LOG:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), event.getBlock().getTypeId(),event.getBlock().getData(),amount);
					break;
				case LOG_2:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), event.getBlock().getTypeId(),event.getBlock().getData(),amount);
					break;
				case DIRT:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), event.getBlock().getTypeId(),event.getBlock().getData(),amount);
					break;
				case STONE:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), event.getBlock().getTypeId(),event.getBlock().getData(),amount);
					break;
				case SAND:
					new ItemDrop().PureItemNaturalDrop(event.getBlock().getLocation(), event.getBlock().getTypeId(),event.getBlock().getData(),amount);
					break;
				default:
					break;	
			}
		}
	}

	public void Quest(BlockBreakEvent event, Player player)
	{
		YamlController Party_YC = GBD.GoldBigDragon_Advanced.Main.Main.Party_YC;
	  	if(Party_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		stat.CreateNewStats(player);

	  	YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager QuestList  = GUI_YC.getNewConfig("Quest/QuestList.yml");
	  	if(Party_YC.isExit("Quest/PlayerData/"+player.getUniqueId()+".yml") == false)
	  		new GBD.GoldBigDragon_Advanced.Config.QuestConfig().CreateNewPlayerConfig(player);
		YamlManager PlayerQuestList  = GUI_YC.getNewConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");

		if(Main.PartyJoiner.containsKey(player)==false)
		{
			if(PlayerQuestList.getConfigurationSection("Started").getKeys(false).toArray().length >= 1)
			{
				Object[] a = PlayerQuestList.getConfigurationSection("Started").getKeys(false).toArray();
				for(int count = 0; count < a.length; count++)
				{
					String QuestName = (String) a[count];
					int Flow = PlayerQuestList.getInt("Started."+QuestName+".Flow");
					if(PlayerQuestList.getString("Started."+QuestName+".Type").equalsIgnoreCase("Harvest"))
					{
						Object[] MobList = QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Block").getKeys(false).toArray();
						int Finish = 0;
						for(int counter = 0; counter < MobList.length; counter++)
						{
							int BlockID = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".BlockID");
							int BlockData = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".BlockData");
							int MAX = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".Amount");
							boolean DataEquals = QuestList.getBoolean(QuestName+".FlowChart."+Flow+".Block."+counter+".DataEquals");
							if(BlockID == event.getBlock().getTypeId() && MAX > PlayerQuestList.getInt("Started."+QuestName+".Block."+counter))
							{
								if(DataEquals == false)
								{
									PlayerQuestList.set("Started."+QuestName+".Block."+counter, PlayerQuestList.getInt("Started."+QuestName+".Block."+counter)+1);
									PlayerQuestList.saveConfig();
								}
								else
								{
									if(BlockData == event.getBlock().getData())
									{
										PlayerQuestList.set("Started."+QuestName+".Block."+counter, PlayerQuestList.getInt("Started."+QuestName+".Block."+counter)+1);
										PlayerQuestList.saveConfig();
									}
								}
							}
							if(MAX == PlayerQuestList.getInt("Started."+QuestName+".Block."+counter))
							{
								Finish = Finish+1;
							}
							if(Finish == MobList.length)
							{
								PlayerQuestList.set("Started."+QuestName+".Type",QuestList.getString(QuestName+".FlowChart."+(PlayerQuestList.getInt("Started."+QuestName+".Flow")+1)+".Type"));
								PlayerQuestList.set("Started."+QuestName+".Flow",PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
								PlayerQuestList.removeKey("Started."+QuestName+".Harvest");
								PlayerQuestList.saveConfig();
								GBD.GoldBigDragon_Advanced.GUI.QuestGUI QGUI = new GBD.GoldBigDragon_Advanced.GUI.QuestGUI();
								QGUI.QuestTypeRouter(player, QuestName);
								//����Ʈ �Ϸ� �޽���//
								break;
							}
						}
					}
				}
			}
		}
		else
		{
			Player[] PartyMember = Main.Party.get(Main.PartyJoiner.get(player)).getMember();
			for(int counta = 0; counta < PartyMember.length; counta++)
			{
				player = PartyMember[counta];
				if(event.getBlock().getLocation().getWorld() == player.getLocation().getWorld())
				{
					YamlManager Config = GUI_YC.getNewConfig("config.yml");
					if(event.getBlock().getLocation().distance(player.getLocation()) <= Config.getInt("Party.EXPShareDistance"))
					{
						PlayerQuestList  = GUI_YC.getNewConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
						Object[] a = PlayerQuestList.getConfigurationSection("Started.").getKeys(false).toArray();
						for(int count = 0; count < a.length; count++)
						{
							String QuestName = (String) a[count];
							int Flow = PlayerQuestList.getInt("Started."+QuestName+".Flow");
							if(PlayerQuestList.getString("Started."+QuestName+".Type").equalsIgnoreCase("Harvest"))
							{
								Object[] MobList = QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Block").getKeys(false).toArray();
								int Finish = 0;
								for(int counter = 0; counter < MobList.length; counter++)
								{
									int BlockID = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".BlockID");
									int BlockData = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".BlockData");
									int MAX = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".Amount");
									boolean DataEquals = QuestList.getBoolean(QuestName+".FlowChart."+Flow+".Block."+counter+".DataEquals");
									if(BlockID == event.getBlock().getTypeId() && MAX > PlayerQuestList.getInt("Started."+QuestName+".Block."+counter))
									{
										if(DataEquals == false)
										{
											PlayerQuestList.set("Started."+QuestName+".Block."+counter, PlayerQuestList.getInt("Started."+QuestName+".Block."+counter)+1);
											PlayerQuestList.saveConfig();
										}
										else
										{
											if(BlockData == event.getBlock().getData())
											{
												PlayerQuestList.set("Started."+QuestName+".Block."+counter, PlayerQuestList.getInt("Started."+QuestName+".Block."+counter)+1);
												PlayerQuestList.saveConfig();
											}
										}
									}
									if(MAX == PlayerQuestList.getInt("Started."+QuestName+".Block."+counter))
									{
										Finish = Finish+1;
									}
									if(Finish == MobList.length)
									{
										PlayerQuestList.set("Started."+QuestName+".Type",QuestList.getString(QuestName+".FlowChart."+(PlayerQuestList.getInt("Started."+QuestName+".Flow")+1)+".Type"));
										PlayerQuestList.set("Started."+QuestName+".Flow",PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
										PlayerQuestList.removeKey("Started."+QuestName+".Harvest");
										PlayerQuestList.saveConfig();
										GBD.GoldBigDragon_Advanced.GUI.QuestGUI QGUI = new GBD.GoldBigDragon_Advanced.GUI.QuestGUI();
										QGUI.QuestTypeRouter(player, QuestName);
										//����Ʈ �Ϸ� �޽���//
										break;
									}
								}
							}
						}
						
					}
				}
			}
		}
	}
}