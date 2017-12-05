package otherplugins;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.citizensnpcs.api.event.NPCCreateEvent;
import net.citizensnpcs.api.event.NPCRemoveEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import user.UserDataObject;
import util.YamlLoader;

public class CitizensMain implements Listener
{
	public CitizensMain(JavaPlugin plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public CitizensMain()
	{}
	
	public void NPCquest(NPCRightClickEvent event)
	{
		effect.SoundEffect s = new effect.SoundEffect();

	    
	    Player player = event.getClicker();
	    net.citizensnpcs.api.npc.NPC target = event.getNPC();
		YamlLoader QuestList = new YamlLoader();
	    QuestList.getConfig("Quest/QuestList.yml");
		YamlLoader PlayerQuestList = new YamlLoader();
		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
		
		if(PlayerQuestList.contains("Started"))
		{
			Object[] a = PlayerQuestList.getConfigurationSection("Started").getKeys(false).toArray();
				
			for(short count = 0; count < a.length; count++)
			{
				String QuestName = a[count].toString();
				short QuestFlow = (short) PlayerQuestList.getInt("Started."+QuestName+".Flow");
				quest.QuestGui QGUI = new quest.QuestGui();
				boolean isThatTarget = false;
				if(QuestList.contains(QuestName+".FlowChart."+QuestFlow+".Type"))
					switch(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".Type"))
					{
					case "Script":
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".NPCuuid")).equalsIgnoreCase(ChatColor.stripColor(target.getUniqueId().toString())))
							isThatTarget = true;
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".NPCname")).equalsIgnoreCase(ChatColor.stripColor(target.getName())))
							isThatTarget = true;
						if(isThatTarget == true)
						{
							event.setCancelled(true);
							QGUI.QuestRouter(player, QuestName);
							return;
						}
						break;
					case "Choice":
						QGUI.Quest_UserChoice(player, QuestName, (short) PlayerQuestList.getInt("Started."+QuestName+".Flow"));
						return;
					case "PScript":
						event.setCancelled(true);
						QGUI.QuestRouter(player, QuestName);
						return;
					case "Talk":
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".TargetNPCuuid")).equalsIgnoreCase(ChatColor.stripColor(target.getUniqueId().toString())))
							isThatTarget = true;
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".TargetNPCname")).equalsIgnoreCase(ChatColor.stripColor(target.getName())))
							isThatTarget = true;
						if(isThatTarget == true)
						{
							event.setCancelled(true);
							PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
							PlayerQuestList.saveConfig();
							QGUI.QuestRouter(player, QuestName);
							return;
						}
						break;
					case "Give":
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".TargetNPCuuid")).equalsIgnoreCase(ChatColor.stripColor(target.getUniqueId().toString())))
							isThatTarget = true;
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".TargetNPCname")).equalsIgnoreCase(ChatColor.stripColor(target.getName())))
							isThatTarget = true;
						if(isThatTarget == true)
						{
							if(QuestList.contains(QuestName+".FlowChart."+QuestFlow+".Item") == false)
							{
								if(PlayerQuestList.getInt("Started."+QuestName+".Flow") == 0)
								{
									QGUI.QuestRouter(player, QuestName);
								}
								else
								{
									PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
									PlayerQuestList.saveConfig();
									QGUI.QuestRouter(player, QuestName);
								}
								return;
							}
							Object[] p = QuestList.getConfigurationSection(QuestName+".FlowChart."+QuestFlow+".Item").getKeys(false).toArray();
							ItemStack item[] = new ItemStack[p.length];
							
							for(byte counter = 0; counter < p.length; counter++)
								item[counter] = QuestList.getItemStack(QuestName+".FlowChart."+QuestFlow+".Item."+counter);
							
							int getfinished = 0;
							for(byte eight = 0; eight < 8; eight++)
							{
								for(byte counter = 0; counter <player.getInventory().getSize(); counter++)
								{
									if(player.getInventory().getItem(counter) != null)
										if(player.getInventory().getItem(counter).isSimilar(item[getfinished]))
										{
											if(player.getInventory().getItem(counter).getAmount() >= item[getfinished].getAmount())
											{
												getfinished = getfinished+1;
													break;
											}
										}
								}
								if(getfinished == item.length)
									break;
							}
							if(getfinished == item.length)
							{
								getfinished = 0;
								ItemStack Pitem = null;
	
								for(byte eight = 0; eight < 8; eight++)
								{
									for(byte counter = 0; counter <player.getInventory().getSize(); counter++)
									{
										if(player.getInventory().getItem(counter) != null)
										if(player.getInventory().getItem(counter).isSimilar(item[getfinished]))
										{
											if(player.getInventory().getItem(counter).getAmount() >= item[getfinished].getAmount())
											{
												Pitem = player.getInventory().getItem(counter);
												Pitem.setAmount(Pitem.getAmount()-item[getfinished].getAmount());
												if(Pitem.getAmount() - item[getfinished].getAmount() == 0)
													player.getInventory().remove(counter);
												else
													player.getInventory().setItem(counter, Pitem);
												
												getfinished = getfinished+1;
												if(getfinished == item.length)
												{
													event.setCancelled(true);
													PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
													PlayerQuestList.saveConfig();
													QGUI.QuestRouter(player, QuestName);
													return;
												}
											}
										}
									}
								}
							}
						}
						break;
						
					case "Present":
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".TargetNPCuuid")).equalsIgnoreCase(ChatColor.stripColor(target.getUniqueId().toString())))
							isThatTarget = true;
						if(ChatColor.stripColor(QuestList.getString(QuestName+".FlowChart."+QuestFlow+".TargetNPCname")).equalsIgnoreCase(ChatColor.stripColor(target.getName())))
							isThatTarget = true;
						if(isThatTarget == true)
						{
							event.setCancelled(true);
							if(QuestList.contains(QuestName+".FlowChart."+QuestFlow+".Item") == true)
							{
								Object[] p = QuestList.getConfigurationSection(QuestName+".FlowChart."+QuestFlow+".Item").getKeys(false).toArray();
								byte emptySlot = 0;
								ItemStack item[] = new ItemStack[p.length];
								
								for(byte counter = 0; counter < p.length; counter++)
									item[counter] = QuestList.getItemStack(QuestName+".FlowChart."+QuestFlow+".Item."+counter);
								
								for(byte counter = 0; counter < player.getInventory().getSize(); counter++)
								{
									if(player.getInventory().getItem(counter) == null)
										emptySlot++;
								}
								
								if(emptySlot >= item.length)
								{
									for(byte counter = 0;counter < p.length; counter++)
										player.getInventory().addItem(item[counter]);
	
									PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
									PlayerQuestList.saveConfig();

									main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(QuestList.getLong(QuestName + ".FlowChart."+QuestFlow+".Money"), 0, false);

									YamlLoader YM = new YamlLoader();
							    	if(YM.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
							    	{
							    		YM.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
							    		YM.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".love", QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".Love"));
							    		YM.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".Career", 0);
							    		YM.saveConfig();
							    	}
							    	else
							    	{
							    		YM.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
							    		int ownlove = YM.getInt(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".love");
							    		int owncareer = YM.getInt(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".Career");
							    		YM.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".love", ownlove +QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".Love"));
							    		YM.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".Career", owncareer +QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".Career"));
							    		YM.saveConfig();
							    	}
						    		if(QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".EXP") != 0)
						    			new util.UtilPlayer().addMoneyAndEXP(player, 0, QuestList.getLong(QuestName + ".FlowChart."+QuestFlow+".EXP"), null, false, false);
									
									event.setCancelled(true);
									QGUI.QuestRouter(player, QuestName);
									return;
								}
								else
								{
									s.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
									player.sendMessage(ChatColor.YELLOW + "[����Ʈ] : ���� �÷��̾��� �κ��丮 ������ ������� �ʾ� ������ ���� �� �����ϴ�!");
									return;
								}
							}
						}
						break;
					}//switch ��
			}
		}
	}
	
	@EventHandler
	public void NPCRightClick(NPCRightClickEvent event)
	{
		Player player = event.getClicker();
		UserDataObject u = new UserDataObject();
		u.setNPCuuid(player, event.getNPC().getUniqueId().toString());
		YamlLoader DNPC = new YamlLoader();
		DNPC.getConfig("NPC/DistrictNPC.yml");
		if(player.isOp()==true)
		{
			if(new UserDataObject().getInt(player, (byte)4)==114)
			{
				DNPC.removeKey(event.getNPC().getUniqueId().toString());
				DNPC.saveConfig();
				player.sendMessage(ChatColor.GREEN+"[NPC] : �ش� NPC�� GUIâ�� Ȱ��ȭ �Ǿ����ϴ�!");
				new effect.SoundEffect().playSound(player, Sound.ENTITY_VILLAGER_YES, 1.0F, 1.0F);
				new UserDataObject().setInt(player, (byte)4, -1);
			}
		}
		
		if(DNPC.contains(event.getNPC().getUniqueId().toString())==false)
		{
			npc.NpcGui NPGUI = new npc.NpcGui();
			NPGUI.MainGUI(event.getClicker(), event.getNPC().getName(), event.getClicker().isOp());
		}

		NPCquest(event);
		return;
	}
	@EventHandler
	public void NPCCreating(NPCCreateEvent event)
	{
		npc.NpcConfig NPCC = new npc.NpcConfig();
		NPCC.NPCNPCconfig(event.getNPC().getUniqueId().toString());
	}
	@EventHandler
	public void NPCRemove(NPCRemoveEvent event)
	{
		File file = new File("plugins/GoldBigDragonRPG/NPC/NPCData/" + event.getNPC().getUniqueId().toString()+".yml");
		file.delete();
		return;
	}

}
