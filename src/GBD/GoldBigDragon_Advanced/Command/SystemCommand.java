package GBD.GoldBigDragon_Advanced.Command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import GBD.GoldBigDragon_Advanced.Main.ServerOption;
import GBD.GoldBigDragon_Advanced.ServerTick.ServerTickMain;
import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;

public class SystemCommand
{
	public void onCommand(Player player, String[] args, String string)
	{
		YamlController Main_YC = GBD.GoldBigDragon_Advanced.Main.Main.Main_YC;
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();
		switch(string)
		{
			case "����":
			{
				if(ServerTickMain.PlayerTaskList.containsKey(player.getName()))
				{
					long UTC = Long.parseLong(ServerTickMain.PlayerTaskList.get(player.getName()));
					GBD.GoldBigDragon_Advanced.ServerTick.ServerTickScheduleObject STSO = ServerTickMain.Schedule.get(UTC);
					String taskType = STSO.getType();
					
					switch(taskType)
					{
						case "P_EC"://Player Exchange
						{
							if(STSO.getString((byte)1).compareTo(player.getName())==0)
							{
								s.SP(player, Sound.HORSE_ARMOR, 1.0F, 1.7F);
								s.SP(Bukkit.getServer().getPlayer(STSO.getString((byte)1)), Sound.HORSE_ARMOR, 1.0F, 1.7F);
								new GBD.GoldBigDragon_Advanced.GUI.EquipGUI().ExChangeGUI(Bukkit.getServer().getPlayer(STSO.getString((byte)0)), Bukkit.getServer().getPlayer(STSO.getString((byte)1)),null,false,false);
								new GBD.GoldBigDragon_Advanced.GUI.EquipGUI().ExChangeGUI(Bukkit.getServer().getPlayer(STSO.getString((byte)1)), Bukkit.getServer().getPlayer(STSO.getString((byte)0)),null,false,false);
								ServerTickMain.PlayerTaskList.remove(ServerTickMain.Schedule.get(UTC).getString((byte)0));
								ServerTickMain.PlayerTaskList.remove(ServerTickMain.Schedule.get(UTC).getString((byte)1));
								ServerTickMain.Schedule.remove(UTC);
							}
						}
						break;
					}
				}
			}
			return;
			case "����":
			{
				if(ServerTickMain.PlayerTaskList.containsKey(player.getName()))
				{
					GBD.GoldBigDragon_Advanced.ServerTick.ServerTickScheduleObject STSO = ServerTickMain.Schedule.get(Long.parseLong(ServerTickMain.PlayerTaskList.get(player.getName())));
					String taskType = STSO.getType();
					switch(taskType)
					{
						case "P_EC"://Player Exchange
						{
							if(STSO.getString((byte)1).compareTo(player.getName())==0)
							{
								GBD.GoldBigDragon_Advanced.ServerTick.ServerTask_Player SP = new GBD.GoldBigDragon_Advanced.ServerTick.ServerTask_Player();
								SP.Cancel(STSO.getTick(), 0);
							}
						}
						break;
					}
				}
			}
			return;
			case"�׽�Ʈ":
			if(player.isOp() == true)
			{
			}
			else
			{
				player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ���ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
				s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
			}
			return;
			case "��":
	  		{
	  			if(args.length == 0)
	  			{
				 	s.SP(player, org.bukkit.Sound.LAVA_POP, 0.8F, 1.8F);
				 	YamlManager YM = Main_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
				 	player.sendMessage(ChatColor.YELLOW + "[���� ���� �ݾ�] " + ChatColor.WHITE+ChatColor.BOLD +"" +YM.getInt("Stat.Money") + " "+ServerOption.Money);
				 	player.sendMessage(ChatColor.GOLD + "/�� ������ [�ݾ�]"+ChatColor.WHITE+" �ش� �ݾ� ��ŭ ���� ���������� �����ϴ�.");
	  			}
	  			else if(args.length == 2&&args[0].compareTo("������")==0)
	  			{
					try
					{
						if(args[1].length() >= 1 && Integer.parseInt(args[1]) >= 1&& Integer.parseInt(args[1]) <= 100000000)
						{
						 	YamlManager YM = Main_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
							if(YM.getInt("Stat.Money") >= Integer.parseInt(args[1]))
							{
								for(int count = 0; count < 36;count++)
								{
									if(player.getInventory().getItem(count)==null)
									{
										int money = Integer.parseInt(args[1]);
										YM.set("Stat.Money", YM.getInt("Stat.Money") - money);
										YM.saveConfig();
										ItemStack Icon;
										if(money <= 50)
											Icon = new MaterialData(ServerOption.Money1ID, (byte) ServerOption.Money1DATA).toItemStack();
										else if(money <= 100)
											Icon = new MaterialData(ServerOption.Money2ID, (byte) ServerOption.Money2DATA).toItemStack();
										else if(money <= 1000)
											Icon = new MaterialData(ServerOption.Money3ID, (byte) ServerOption.Money3DATA).toItemStack();
										else if(money <= 10000)
											Icon = new MaterialData(ServerOption.Money4ID, (byte) ServerOption.Money4DATA).toItemStack();
										else if(money <= 50000)
											Icon = new MaterialData(ServerOption.Money5ID, (byte) ServerOption.Money5DATA).toItemStack();
										else
											Icon = new MaterialData(ServerOption.Money6ID, (byte) ServerOption.Money6DATA).toItemStack();
										Icon.setAmount(1);
										ItemMeta Icon_Meta = Icon.getItemMeta();
										Icon_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
										Icon_Meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
										Icon_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
										Icon_Meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
										Icon_Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
										Icon_Meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
										Icon_Meta.setDisplayName(ServerOption.Money);
										StringBuffer MoneyString = new StringBuffer();
										int Mok = 0;
										if(money==100000000||money==10000000||money==1000000||
											money==100000||money==10000||money==1000||money==100||
											money==10)
										{
											switch(money)
											{
												case 100000000:
													MoneyString.append("1��");
													break;
												case 10000000:
													MoneyString.append("1õ��");
													break;
												case 1000000:
													MoneyString.append("1�鸸");
													break;
												case 100000:
													MoneyString.append("1�ʸ�");
													break;
												case 10000:
													MoneyString.append("1��");
													break;
												case 1000:
													MoneyString.append("1õ");
													break;
												case 100:
													MoneyString.append("1��");
													break;
												case 10:
													MoneyString.append("1��");
													break;
											}
										}
										else
										{
											if(money >= 10000000)
											{
												Mok = money / 10000000;
												MoneyString.append(Mok+"õ");
												money = money-(Mok*10000000);
											}
											if(money >= 1000000)
											{
												Mok = money / 1000000;
												MoneyString.append(Mok+"��");
												money = money-(Mok*1000000);
											}
											if(money >= 100000)
											{
												Mok = money / 100000;
												MoneyString.append(Mok+"��");
												money = money-(Mok*100000);
											}
											if(money >= 10000)
											{
												Mok = money / 10000;
												MoneyString.append(Mok+"�� ");
												money = money-(Mok*10000);
											}
											else if(Integer.parseInt(args[1]) >= 10000)
											{
												MoneyString.append("�� ");
											}
											if(money >= 1000)
											{
												Mok = money / 1000;
												MoneyString.append(Mok+"õ");
												money = money-(Mok*1000);
											}
											if(money >= 100)
											{
												Mok = money / 100;
												MoneyString.append(Mok+"��");
												money = money-(Mok*100);
											}
											if(money >= 10)
											{
												Mok = money / 10;
												MoneyString.append(Mok+"��");
												money = money-(Mok*10);
											}
											if(money >= 1)
											{
												Mok = money / 1;
												MoneyString.append(Mok);
											}
										}
										Icon_Meta.setLore(Arrays.asList(ChatColor.YELLOW+"[��]             "+ChatColor.WHITE+"[�Ϲ�]",ChatColor.WHITE+""+ChatColor.BOLD+args[1]+" "+ServerOption.Money,ChatColor.GRAY+"("+MoneyString.toString()+" "+ChatColor.stripColor(ServerOption.Money)+")","",ChatColor.GRAY+"�� Ŭ���� �� ���·�",ChatColor.GRAY+"�Աݵ˴ϴ�."));
										Icon.setItemMeta(Icon_Meta);
										player.getInventory().addItem(Icon);
										s.SP(player, org.bukkit.Sound.LAVA_POP, 2.0F, 1.7F);
										player.sendMessage(ChatColor.GREEN + "[System] : "+ChatColor.WHITE+""+ChatColor.BOLD+args[1]+" "+ServerOption.Money+ChatColor.GREEN+" ��(��) ���½��ϴ�!");
										return;
									}
								}
								s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
								player.sendMessage(ChatColor.RED + "[System] : �κ��丮 ������ �����մϴ�!");
							}
							else
							{
								player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� ���� �ݾ��� �ʰ��ϴ� ���Դϴ�!");
								s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
							}
							
						}
						else
						{
							player.sendMessage(ChatColor.RED + "[SYSTEM] : �ּ� "+ChatColor.YELLOW +""+1+ChatColor.RED+", �ִ� "+ChatColor.YELLOW+""+100000000+ChatColor.RED+" ������ ���ڸ� �Է��ϼ���!");
							s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
						}
					}
					catch(NumberFormatException e)
					{
						player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���. ("+ChatColor.YELLOW +""+1+ChatColor.RED+" ~ "+ChatColor.YELLOW+""+100000000+ChatColor.RED+")");
						s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					}
	  			}
	  			else
	  			{
	  				s.SP(player, org.bukkit.Sound.ORB_PICKUP, 0.8F, 1.8F);
	  				player.sendMessage(ChatColor.GOLD + "/��"+ChatColor.WHITE+" ���� �ڽ��� ������ �ݾ��� Ȯ���մϴ�.");
	  				player.sendMessage(ChatColor.GOLD + "/�� ������ [�ݾ�]"+ChatColor.WHITE+" �ش� �ݾ� ��ŭ ���� ���������� �����ϴ�.");
	  			}
	  		}
	  		return;
			case "��ƼƼ����":
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /��ƼƼ���� [1~10000]");
					s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
				if(player.isOp() == true)
				{
				    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
				    int amount = 0;
				    for(int count = 0; count < entities.size(); count++)
				    {
				    	if(entities.get(count).getType() != EntityType.PLAYER &&entities.get(count).getType() != EntityType.ITEM_FRAME)
				    	{
				    		entities.get(count).remove();
				    		amount = amount+1;
				    	}
				    }
				    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"���� �̳��� �ִ� "+amount+"������ ��ƼƼ�� �����Ͽ����ϴ�!");
				}
				else
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ���ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
					s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
				return;

			case "����������":
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /���������� [1~10000]");
					s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
				if(player.isOp() == true)
				{
				    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
				    int amount = 0;
				    for(int count = 0; count < entities.size(); count++)
				    {
				    	if(entities.get(count).getType() == EntityType.DROPPED_ITEM)
				    	{
				    		entities.get(count).remove();
				    		amount = amount+1;
				    	}
				    }
				    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"���� �̳��� �ִ� "+amount+"���� �������� �����Ͽ����ϴ�!");
				}
				else
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ���ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
					s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
				return;
		}
		return;
	}
}