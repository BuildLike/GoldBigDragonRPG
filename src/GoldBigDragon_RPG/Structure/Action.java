package GoldBigDragon_RPG.Structure;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import GoldBigDragon_RPG.Main.UserDataObject;
import GoldBigDragon_RPG.Util.YamlController;
import GoldBigDragon_RPG.Util.YamlManager;
import net.md_5.bungee.api.ChatColor;

public class Action
{
	public void PlayerChatrouter(PlayerChatEvent event)
	{
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		if(u.getType(player).compareTo("Post")==0)
			PostChatting(event);
		else if(u.getType(player).compareTo("Board")==0)
			BoardChatting(event);
		else if(u.getType(player).compareTo("TradeBoard")==0)
			TradeBoardChatting(event);
		else if(u.getType(player).compareTo("CampFire")==0)
			CampFireChatting(event);
		
	}
	
	
	private void PostChatting(PlayerChatEvent event)
	{
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		//Reciever NickName
		if(u.getString(player,(byte)0).compareTo("RN")==0)
		{
			if(Message.compareTo(ChatColor.stripColor(player.getName()))==0)
			{
				s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
				u.clearAll(player);
				player.sendMessage(ChatColor.RED+"[����] : �ڱ� �ڽſ��Դ� ���� �� �����ϴ�!");
				return;
			}
				
			if(Bukkit.getOfflinePlayer(Message)!=null)
			{
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
				u.setString(player, (byte)0, "Title");
				u.setString(player, (byte)1, Message);
				u.setTemp(player,"Structure");
				player.sendMessage(ChatColor.GREEN+"[����] : ���� ������ �Է� �ϼ���.");
			}
			else
			{
				s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
				u.clearAll(player);
				player.sendMessage(ChatColor.RED+"[����] : �ش� �г����� ���� �÷��̾ �����ϴ�!");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("Title")==0)
		{
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)0, "Memo");
			u.setString(player, (byte)2, event.getMessage());
			player.sendMessage(ChatColor.GREEN+"[����] : ���� ������ �Է� �ϼ���.");
			u.setTemp(player,"Structure");
		}
		else if(u.getString(player,(byte)0).compareTo("Memo")==0)
		{
			s.SP(player, Sound.HORSE_ARMOR, 1.0F, 1.5F);
			u.setString(player, (byte)3,ChatColor.WHITE+event.getMessage());
			u.setItemStack(player, null);
			new Structure_PostBox().ItemPutterGUI(player);
		}
		else if(u.getString(player,(byte)0).compareTo("Value")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 2000000))
			{
				u.setInt(player, (byte)0, Integer.parseInt(event.getMessage()));
				new GoldBigDragon_RPG.Structure.Structure_PostBox().SendPost(player);
			}
			else
			{
				player.sendMessage(ChatColor.GREEN+"[����] : ���� ������ ���� ����� �Է� �ϼ���.");
				u.setTemp(player,"Structure");
			}
		}
	}

	private void BoardChatting(PlayerChatEvent event)
	{
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		
		//Board_PostTitle
		if(u.getString(player,(byte)0).compareTo("Title")==0)
		{
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)0, "Memo");
			u.setString(player, (byte)1, ChatColor.WHITE+event.getMessage());
			player.sendMessage(ChatColor.GREEN+"[�Խ���] : �Խñ� ������ �Է� �ϼ���.");
			u.setTemp(player,"Structure");
		}
		else if(u.getString(player,(byte)0).compareTo("Memo")==0)
		{
			s.SP(player, Sound.HORSE_ARMOR, 1.0F, 1.5F);
			u.setString(player, (byte)2,ChatColor.WHITE+event.getMessage());
			YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
			YamlManager Board =YC_2.getNewConfig("Structure/"+u.getString(player, (byte)3)+".yml");
			Board.set("User."+Board.getInt("Post_Number")+".User", player.getName());
			Board.set("User."+Board.getInt("Post_Number")+".Title", u.getString(player, (byte)1));
			Board.set("User."+Board.getInt("Post_Number")+".Memo", u.getString(player, (byte)2));
			Board.set("User."+Board.getInt("Post_Number")+".UTC", new GoldBigDragon_RPG.ServerTick.ServerTickMain().nowUTC);
			Board.set("Post_Number", Board.getInt("Post_Number")+1);
			Board.saveConfig();
			new Structure_Board().BoardMainGUI(player, u.getString(player, (byte)3), 0);
			u.clearAll(player);
		}
		else if(u.getString(player,(byte)0).compareTo("Notice")==0)
		{
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)2,ChatColor.WHITE+event.getMessage());
			YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
			YamlManager Board =YC_2.getNewConfig("Structure/"+u.getString(player, (byte)1)+".yml");
			Board.set("Notice", event.getMessage());
			Board.saveConfig();
			new Structure_Board().BoardSettingGUI(player, u.getString(player, (byte)1));
			u.clearAll(player);
		}
	}
	
	private void TradeBoardChatting(PlayerChatEvent event)
	{
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		
		if(u.getString(player,(byte)0).compareTo("Notice")==0)
		{
			if(askOX(Message, player)==1)
			{
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
				if(u.getInt(player, (byte)0)==1)//�ŷ� Ÿ���� �Ǹ��� ���
				{
					new Structure_TradeBoard().SelectSellItemGUI(player);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : �Ǹ��� �������� �����ϼ���.");
				}
				else if(u.getInt(player, (byte)0)==3)//�ŷ� Ÿ���� ������ ���
				{
					new Structure_TradeBoard().SelectBuyItemGUI(player);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : ������ �������� �����ϼ���.");
				}
				else //�ŷ� Ÿ���� ��ȯ�� ���
				{
					new Structure_TradeBoard().SelectExchangeItem_YouGUI(player);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : ������ ������ ����ϴ� �������� �����ϼ���.");
				}
				u.setString(player, (byte)0, "SelectItem");
			}
			else if(Message.compareTo("�ƴϿ�")==0||Message.compareTo("x")==0
				||Message.compareTo("X")==0)
			{
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : ��ǰ ����� ��ҵǾ����ϴ�.");
				u.clearAll(player);
				return;
			}
		}
		else if(u.getString(player,(byte)0).compareTo("SetNeedAmount")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 1, 1000))
			{
				if(u.getInt(player, (byte)0)==5)
				{
					u.setInt(player, (byte) 2, Integer.parseInt(event.getMessage()));
					s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : ����� ������ �ֽǰǰ���?");
					u.setString(player, (byte)0, "SetMyItem");
					new Structure_TradeBoard().SelectExchangeItem_MyGUI(player);
					return;
				}
				u.setInt(player, (byte) 2, Integer.parseInt(event.getMessage()));
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : 1���� �󸶿� ���� �Ͻǰǰ���? (0 ~ 2�鸸)");
				u.setString(player, (byte)0, "SetPrice");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("SetPrice")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, 2000000))
			{
				u.setInt(player, (byte)1, Integer.parseInt(event.getMessage()));

				//�Ǹ��� ���
				if(u.getInt(player, (byte)0)==3)
				{
					YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
					YamlManager Board =YC_2.getNewConfig("Structure/UserShopBoard.yml");
					YamlManager Pstat =YC_2.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");
					if(Pstat.getLong("Stat.Money")<Board.getInt("RegisterCommission"))
					{
						u.clearAll(player);
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : ��� �����ᰡ �����մϴ�! �� ��� �� �ּ���!");
						return;
					}
					ItemStack itemAmountOne = u.getItemStack(player);
					String ItemName = u.getItemStack(player).getType().name()+"%d%"+itemAmountOne.getData().getData();
					if(u.getItemStack(player).hasItemMeta())
						if(u.getItemStack(player).getItemMeta().hasDisplayName())
							ItemName = u.getItemStack(player).getItemMeta().getDisplayName()+"%d%"+itemAmountOne.getData().getData();

					ItemName = ItemName.replace(":","");
					ItemName = ItemName.replace(".","");
					ItemName = ItemName.replace("[","");
					ItemName = ItemName.replace("]","");
					if(u.getItemStack(player).hasItemMeta())
					{
						if(u.getItemStack(player).getItemMeta().hasLore())
							ItemName = ItemName+u.getItemStack(player).getItemMeta().getLore().toString().length();
						else
							ItemName = ItemName+0;
					}
					else
						ItemName = ItemName+0;
					if(Board.contains("Sell."+ItemName+"."+player.getUniqueId().toString()))
					{
						u.clearAll(player);
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : ���� ��ǰ�� �̹� ����ϼ̽��ϴ�!");
						return;
					}
					if(Board.contains("Sell."+ItemName)==false)
						Board.set("Sell."+ItemName+".Item", itemAmountOne);
					Board.set("SellRegistered", Board.getInt("SellRegistered")+1);
					Board.set("Sell."+ItemName+"."+player.getUniqueId().toString()+".Name", player.getName());
					Board.set("Sell."+ItemName+"."+player.getUniqueId().toString()+".Price", u.getInt(player, (byte)1));
					Board.set("Sell."+ItemName+"."+player.getUniqueId().toString()+".Amount", u.getInt(player,(byte)2));
					Board.saveConfig();
					

					Pstat.set("Stat.Money", Pstat.getLong("Stat.Money")-Board.getInt("RegisterCommission"));
					Pstat.saveConfig();
					YamlManager USRL =YC_2.getNewConfig("Structure/UserShopRegisterList.yml");
					USRL.set(player.getUniqueId().toString(), USRL.getInt(player.getUniqueId().toString())+1);
					USRL.saveConfig();
					u.clearAll(player);
					s.SP(player, Sound.CHEST_OPEN, 1.0F, 1.8F);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : ����� �Ϸ�Ǿ����ϴ�!");
					return;
				}
				for(int count = 0; count < player.getInventory().getSize(); count++)
				{
					if(player.getInventory().getItem(count)!=null)
						if(player.getInventory().getItem(count).equals(u.getItemStack(player)))
						{
							YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
							YamlManager Board =YC_2.getNewConfig("Structure/UserShopBoard.yml");
							YamlManager Pstat =YC_2.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");
							if(Pstat.getLong("Stat.Money")<Board.getInt("RegisterCommission"))
							{
								u.clearAll(player);
								s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
								player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : ��� �����ᰡ �����մϴ�! �� ��� �� �ּ���!");
								return;
							}
							//������ ���
							if(u.getInt(player, (byte)0)==1)
							{
								ItemStack itemAmountOne = u.getItemStack(player);
								String ItemName = u.getItemStack(player).getType().name()+"%d%"+itemAmountOne.getData().getData();
								if(u.getItemStack(player).hasItemMeta())
									if(u.getItemStack(player).getItemMeta().hasDisplayName())
										ItemName = u.getItemStack(player).getItemMeta().getDisplayName()+"%d%"+itemAmountOne.getData().getData();

								ItemName = ItemName.replace(":","");
								ItemName = ItemName.replace(".","");
								ItemName = ItemName.replace("[","");
								ItemName = ItemName.replace("]","");
								if(u.getItemStack(player).hasItemMeta())
								{
									if(u.getItemStack(player).getItemMeta().hasLore())
										ItemName = ItemName+u.getItemStack(player).getItemMeta().getLore().toString().length();
									else
										ItemName = ItemName+0;
								}
								else
									ItemName = ItemName+0;
								itemAmountOne.setAmount(1);
								if(Board.contains("Buy."+ItemName+"."+player.getUniqueId().toString()))
								{
									u.clearAll(player);
									s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
									player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : ���� ��ǰ�� �̹� ����ϼ̽��ϴ�!");
									return;
								}
								if(Board.contains("Buy."+ItemName)==false)
									Board.set("Buy."+ItemName+".Item", itemAmountOne);
								Board.set("BuyRegistered", Board.getInt("BuyRegistered")+1);
								Board.set("Buy."+ItemName+"."+player.getUniqueId().toString()+".Name", player.getName());
								Board.set("Buy."+ItemName+"."+player.getUniqueId().toString()+".Price", u.getInt(player, (byte)1));
								Board.set("Buy."+ItemName+"."+player.getUniqueId().toString()+".Amount", u.getItemStack(player).getAmount());
								Board.saveConfig();
							}
							Pstat.set("Stat.Money", Pstat.getLong("Stat.Money")-Board.getInt("RegisterCommission"));
							Pstat.saveConfig();
							YamlManager USRL =YC_2.getNewConfig("Structure/UserShopRegisterList.yml");
							USRL.set(player.getUniqueId().toString(), USRL.getInt(player.getUniqueId().toString())+1);
							USRL.saveConfig();
							player.getInventory().setItem(count, null);
							u.clearAll(player);
							s.SP(player, Sound.CHEST_OPEN, 1.0F, 1.8F);
							player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : ����� �Ϸ�Ǿ����ϴ�!");
							return;
						}
				}
				u.clearAll(player);
				s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
				player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : ����� �������� �����ϴ�! �� ��� �� �ּ���!");
			}
		}
		else if(u.getString(player,(byte)0).compareTo("TradeBoardSetting")==0)
		{
			String SettingType = u.getString(player, (byte)1);
			YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
			YamlManager Board =YC_2.getNewConfig("Structure/UserShopBoard.yml");
			if(SettingType.compareTo("SellCommission")==0)
			{
				if(isIntMinMax(event.getMessage(), player, 0, 100))
					Board.set(SettingType, Integer.parseInt(event.getMessage()));
			}
			else if(SettingType.compareTo("RegisterCommission")==0)
			{
				if(isIntMinMax(event.getMessage(), player, 0, 2000000))
					Board.set(SettingType, Integer.parseInt(event.getMessage()));
			}
			else if(SettingType.compareTo("LimitPerPlayer")==0)
			{
				if(isIntMinMax(event.getMessage(), player, 1, 100))
					Board.set(SettingType, Integer.parseInt(event.getMessage()));
			}
			Board.saveConfig();
			new GoldBigDragon_RPG.Structure.Structure_TradeBoard().TradeBoardSettingGUI(player);
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			u.clearAll(player);
		}
		else if(u.getString(player,(byte)0).compareTo("BuyTrade")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, u.getInt(player, (byte)0)))
			{
				int needAmount = Integer.parseInt(ChatColor.stripColor(event.getMessage()));
				if(needAmount==0)
				{
					s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : �ŷ��� ��ҵǾ����ϴ�.");
					u.clearAll(player);
					return;
				}
				else
				{
					YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
					YamlManager PlayerStat =YC_2.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");
					YamlManager Board =YC_2.getNewConfig("Structure/UserShopBoard.yml");
					int ExitAmount = Board.getInt("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount");
					if(Board.contains("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2))==false)
					{
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : �ŷ� ������ �ٲ�����ϴ�! �� �õ� �� �ֽñ� �ٶ��ϴ�!");
						u.clearAll(player);
						return;
					}
					else if(ExitAmount<needAmount)
					{
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : �ŷ� ������ �ٲ�����ϴ�! �� �õ� �� �ֽñ� �ٶ��ϴ�!");
						u.clearAll(player);
						return;
					}
					else
					{
						int Price = Board.getInt("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Price");
						if(Price*needAmount <= PlayerStat.getLong("Stat.Money"))
						{
							Price = Price*needAmount;
							ItemStack item = Board.getItemStack("Buy."+u.getString(player, (byte)1)+".Item");
							int SellCommission = Board.getInt("SellCommission");
							int MinusSellCommission = (int)((Price/100.0) * SellCommission);
							Player Target = Bukkit.getPlayer(Board.getString("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Name"));
							
							if(ExitAmount-needAmount ==0)
							{
								if(Board.getConfigurationSection("Buy."+u.getString(player, (byte)1)).getKeys(false).size()==2)
									Board.removeKey("Buy."+u.getString(player, (byte)1));
								else
									Board.removeKey("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2));
								Board.set("BuyRegistered", Board.getInt("BuyRegistered")-1);
								YamlManager USRL =YC_2.getNewConfig("Structure/UserShopRegisterList.yml");
								if(USRL.contains(player.getUniqueId().toString())==true)
								{
									USRL.set(Target.getUniqueId().toString(), USRL.getInt(Target.getUniqueId().toString())-1);
									USRL.saveConfig();
								}
							}
							else
								Board.set("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount", ExitAmount-needAmount);
							Board.saveConfig();
							PlayerStat.set("Stat.Money",PlayerStat.getLong("Stat.Money")-Price);
							PlayerStat.saveConfig();
							
							YamlManager TargetStat =YC_2.getNewConfig("Stats/"+u.getString(player, (byte)2)+".yml");
							if(TargetStat.getLong("Stat.Money") + Price-MinusSellCommission >= 2000000000)
								TargetStat.set("Stat.Money",2000000000);
							else
								TargetStat.set("Stat.Money",TargetStat.getLong("Stat.Money")+(Price-MinusSellCommission));
							TargetStat.saveConfig();
							new GoldBigDragon_RPG.Structure.Structure_PostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ �Ǹ� ������]", 
							player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� "+needAmount+"�� �����Ͽ� "+Price+" "+ChatColor.WHITE+ChatColor.stripColor(GoldBigDragon_RPG.Main.ServerOption.Money)+ChatColor.WHITE+"�� ������ ������, "
							+MinusSellCommission+" "+ChatColor.WHITE+ChatColor.stripColor(GoldBigDragon_RPG.Main.ServerOption.Money)+" ��ŭ�� �����Ḧ �����Ͽ����ϴ�.", null);
							if(Target!=null)
								if(Target.isOnline())
								{
									s.SP(Target, Sound.VILLAGER_YES, 1.0F, 1.0F);
									new GoldBigDragon_RPG.Effect.PacketSender().sendTitleSubTitle(Target, "\'��3[�ŷ� ����]\'","\'��3�ŷ� �Խ��ǿ� ����� ��ǰ�� �ǸŵǾ����ϴ�.\'", 1, 3, 1);
								}
							if(needAmount!=1)
								item.setAmount(needAmount);
							if(new GoldBigDragon_RPG.ETC.Items().GiveItem(player, item)==false)
							{
								s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage(ChatColor.YELLOW+"[�ŷ� �Խ���] : �κ��丮�� �����Ͽ� ���������� �������� �߼��Ͽ����ϴ�!");
								new GoldBigDragon_RPG.Structure.Structure_PostBox().SendPost_Server(player.getUniqueId().toString(), "[�ŷ� �Խ���]", "[�κ��丮 ����]", "�ŷ� ���� �κ��丮�� �����Ͽ� �������� �������� ��۵Ǿ����ϴ�.", item);
							}
						}
						else
						{
							s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.0F);
							player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : �������� �����մϴ�!");
							player.sendMessage(ChatColor.RED+"[�ʿ� �ݾ� : "+(Price*needAmount)+" "+GoldBigDragon_RPG.Main.ServerOption.Money+ChatColor.RED+"]");
							player.sendMessage(ChatColor.RED+"[���� �ݾ� : "+PlayerStat.getLong("Stat.Money")+" "+GoldBigDragon_RPG.Main.ServerOption.Money+ChatColor.RED+"]");
						}
						u.clearAll(player);
						return;
					}
				}
			}
		}
		else if(u.getString(player,(byte)0).compareTo("SellTrade")==0)
		{
			if(isIntMinMax(event.getMessage(), player, 0, u.getInt(player, (byte)0)))
			{
				int needAmount = Integer.parseInt(ChatColor.stripColor(event.getMessage()));
				if(needAmount==0)
				{
					s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage(ChatColor.GREEN+"[�ŷ� �Խ���] : �ŷ��� ��ҵǾ����ϴ�.");
					u.clearAll(player);
					return;
				}
				else
				{
					YamlController YC_2 = GoldBigDragon_RPG.Main.Main.YC_2;
					YamlManager Board =YC_2.getNewConfig("Structure/UserShopBoard.yml");
					Player Target = Bukkit.getPlayer(Board.getString("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Name"));
					int itemcount = 0;
					ItemStack BoardAddedItem = Board.getItemStack("Sell."+u.getString(player, (byte)1)+".Item");
					for(int count=0; count<player.getInventory().getSize();count++)
					{
						ItemStack PlayerHave = player.getInventory().getItem(count);
						if(PlayerHave!=null)
						{
							int amount = PlayerHave.getAmount();
							PlayerHave.setAmount(1);
							if(PlayerHave.equals(BoardAddedItem))
								itemcount= itemcount+amount;
							PlayerHave.setAmount(amount);
						}
					}
					if(itemcount<needAmount)
					{
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : ������ ����� ������ ���� �ʽ��ϴ�!");
						u.clearAll(player);
						return;
					}
					if(needAmount > Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount"))
					{
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : �ŷ� ������ �ٲ�����ϴ�! �� �õ� �� �ֽñ� �ٶ��ϴ�!");
						u.clearAll(player);
						return;
					}
					int rawprice = Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Price");
					long price = rawprice*needAmount;
					YamlManager TargetStat =YC_2.getNewConfig("Stats/"+u.getString(player, (byte)2)+".yml");
					if(TargetStat.getLong("Stat.Money")<price)
					{
						s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage(ChatColor.RED+"[�ŷ� �Խ���] : �Ǹ����� ���� �ܰ� �����Ͽ� �ŷ��� ��ҵǾ����ϴ�!");
						u.clearAll(player);
						return;
					}
					YamlManager PlayerStat =YC_2.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");
					if(PlayerStat.getLong("Stat.Money")+price>=2000000000)
						PlayerStat.set("Stat.Money",2000000000);
					else
						PlayerStat.set("Stat.Money", PlayerStat.getLong("Stat.Money")+price);
					TargetStat.set("Stat.Money", TargetStat.getLong("Stat.Money")-price);
					PlayerStat.saveConfig();
					TargetStat.saveConfig();
					
					if(Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount")-needAmount ==0)
					{
						if(Board.getConfigurationSection("Sell."+u.getString(player, (byte)1)).getKeys(false).size()==2)
							Board.removeKey("Sell."+u.getString(player, (byte)1));
						else
							Board.removeKey("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2));
						Board.set("SellRegistered", Board.getInt("SellRegistered")-1);
						YamlManager USRL =YC_2.getNewConfig("Structure/UserShopRegisterList.yml");
						if(USRL.contains(player.getUniqueId().toString())==true)
						{
							USRL.set(Target.getUniqueId().toString(), USRL.getInt(Target.getUniqueId().toString())-1);
							USRL.saveConfig();
						}
					}
					else
						Board.set("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount", Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount")-needAmount);
					Board.saveConfig();
					if(needAmount>64)
					{
						int pack = needAmount/64;
						int trash = needAmount-(pack*64);
						BoardAddedItem.setAmount(64);
						for(int count=0;count<pack;count++)
							new GoldBigDragon_RPG.Structure.Structure_PostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ ���� ������]",
									player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� 64�� �Ǹ��Ͽ� "+(rawprice*64)+" "+ChatColor.stripColor(GoldBigDragon_RPG.Main.ServerOption.Money)+ChatColor.WHITE+"�� ������ �߽��ϴ�.", BoardAddedItem);
						if(trash!=0)
						{
							BoardAddedItem.setAmount(trash);
							new GoldBigDragon_RPG.Structure.Structure_PostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ ���� ������]",
									player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� "+trash+"�� �Ǹ��Ͽ� "+(rawprice*trash)+" "+ChatColor.stripColor(GoldBigDragon_RPG.Main.ServerOption.Money)+ChatColor.WHITE+"�� ������ �߽��ϴ�.", BoardAddedItem);
						}
					}
					else
					{
						BoardAddedItem.setAmount(needAmount);
						new GoldBigDragon_RPG.Structure.Structure_PostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ ���� ������]",
								player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� "+needAmount+"�� �Ǹ��Ͽ� "+price+" "+ChatColor.stripColor(GoldBigDragon_RPG.Main.ServerOption.Money)+ChatColor.WHITE+"�� ������ �߽��ϴ�.", BoardAddedItem);
					}
					if(Target!=null)
						if(Target.isOnline())
						{
							s.SP(Target, Sound.VILLAGER_YES, 1.0F, 1.0F);
							new GoldBigDragon_RPG.Effect.PacketSender().sendTitleSubTitle(Target, "\'��3[�ŷ� ����]\'","\'��3�ŷ� �Խ��ǿ� �Ƿ��� ��ǰ�� �����Ͽ����ϴ�.\'", 1, 3, 1);
						}
					
					BoardAddedItem.setAmount(1);
					for(int count=0; count<player.getInventory().getSize();count++)
					{
						ItemStack PlayerHave = player.getInventory().getItem(count);
						if(PlayerHave!=null)
						{
							int amount = PlayerHave.getAmount();
							PlayerHave.setAmount(1);
							if(PlayerHave.equals(BoardAddedItem))
							{
								if(amount > needAmount)
								{
									PlayerHave.setAmount(amount-needAmount);
									player.getInventory().setItem(count, PlayerHave);
								}
								else
									player.getInventory().setItem(count, null);
								needAmount = needAmount - amount;
							}
							if(needAmount<=0)
								break;
						}
					}
					//�÷��̾� �κ��丮���� �Ǹ��� ��ǰ �����ϱ�
				}
			}
		}//SellTrade ��
	}
	
	private void CampFireChatting(PlayerChatEvent event)
	{
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		//Reciever NickName
		if(u.getString(player,(byte)0).compareTo("RN")==0)
		{
			if(Message.compareTo(ChatColor.stripColor(player.getName()))==0)
			{
				s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
				u.clearAll(player);
				player.sendMessage(ChatColor.RED+"[����] : �ڱ� �ڽſ��Դ� ���� �� �����ϴ�!");
				return;
			}
		}
	}
	
	private boolean isIntMinMax(String message,Player player, int Min, int Max)
	{
		GoldBigDragon_RPG.Effect.Sound sound = new GoldBigDragon_RPG.Effect.Sound();
		try
		{
			if(message.split(" ").length <= 1 && Integer.parseInt(message) >= Min&& Integer.parseInt(message) <= Max)
				return true;
			else
			{
				player.sendMessage(ChatColor.RED + "[SYSTEM] : �ּ� "+ChatColor.YELLOW +""+Min+ChatColor.RED+", �ִ� "+ChatColor.YELLOW+""+Max+ChatColor.RED+" ������ ���ڸ� �Է��ϼ���!");
				sound.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
			}
		}
		catch(NumberFormatException e)
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���. ("+ChatColor.YELLOW +""+Min+ChatColor.RED+" ~ "+ChatColor.YELLOW+""+Max+ChatColor.RED+")");
				sound.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
		}
		return false;
	}

	private byte askOX(String message,Player player)
	{
		GoldBigDragon_RPG.Effect.Sound sound = new GoldBigDragon_RPG.Effect.Sound();
		if(message.split(" ").length <= 1)
		{
			if(message.compareTo("x")==0||message.compareTo("X")==0||message.compareTo("�ƴϿ�")==0)
				return 0;
			else if(message.compareTo("o")==0||message.compareTo("O")==0||message.compareTo("��")==0)
				return 1;
			else
			{
				player.sendMessage(ChatColor.RED + "[SYSTEM] : [��/O] Ȥ�� [�ƴϿ�/X]�� �Է� �� �ּ���!");
				sound.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
			}
			
		}
		else
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : [��/O] Ȥ�� [�ƴϿ�/X]�� �Է� �� �ּ���!");
			sound.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
		}
		return -1;
	}
}
