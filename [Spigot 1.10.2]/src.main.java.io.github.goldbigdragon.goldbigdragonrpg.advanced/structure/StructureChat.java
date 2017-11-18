package structure;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import effect.SoundEffect;
import user.UserDataObject;
import util.YamlLoader;

public class StructureChat
{
	public void PlayerChatrouter(PlayerChatEvent event)
	{
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		if(u.getType(player).equals("Post"))
			PostChatting(event);
		else if(u.getType(player).equals("Board"))
			BoardChatting(event);
		else if(u.getType(player).equals("TradeBoard"))
			TradeBoardChatting(event);
		else if(u.getType(player).equals("CampFire"))
			CampFireChatting(event);
		
	}
	
	private void PostChatting(PlayerChatEvent event)
	{
		
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		//Reciever NickName
		if(u.getString(player,(byte)0).equals("RN"))
		{
			String lower = Message.toLowerCase();
			if(lower.equals(ChatColor.stripColor(player.getName().toLowerCase())))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				u.clearAll(player);
				player.sendMessage("��c[����] : �ڱ� �ڽſ��Դ� ���� �� �����ϴ�!");
				return;
			}
				
			if(Bukkit.getPlayer(Message) != null)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				u.setString(player, (byte)0, "Title");
				u.setString(player, (byte)1, Message);
				u.setTemp(player,"Structure");
				player.sendMessage("��a[����] : ���� ������ �Է� �ϼ���.");
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				u.clearAll(player);
				player.sendMessage("��c[����] : �ش� �г����� ���� �÷��̾ �����ϴ�!");
			}
		}
		else if(u.getString(player,(byte)0).equals("Title"))
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)0, "Memo");
			u.setString(player, (byte)2, event.getMessage());
			player.sendMessage("��a[����] : ���� ������ �Է� �ϼ���.");
			u.setTemp(player,"Structure");
		}
		else if(u.getString(player,(byte)0).equals("Memo"))
		{
			SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.5F);
			u.setString(player, (byte)3,"��f"+event.getMessage());
			u.setItemStack(player, null);
			new StructPostBox().ItemPutterGUI(player);
		}
		else if(u.getString(player,(byte)0).equals("Value"))
		{
			if(isIntMinMax(event.getMessage(), player, 0, 2000000))
			{
				u.setInt(player, (byte)0, Integer.parseInt(event.getMessage()));
				new structure.StructPostBox().SendPost(player);
			}
			else
			{
				player.sendMessage("��a[����] : ���� ������ ���� ����� �Է� �ϼ���.");
				u.setTemp(player,"Structure");
			}
		}
	}

	private void BoardChatting(PlayerChatEvent event)
	{
		
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		
		//Board_PostTitle
		if(u.getString(player,(byte)0).equals("Title"))
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)0, "Memo");
			u.setString(player, (byte)1, "��f"+event.getMessage());
			player.sendMessage("��a[�Խ���] : �Խñ� ������ �Է� �ϼ���.");
			u.setTemp(player,"Structure");
		}
		else if(u.getString(player,(byte)0).equals("Memo"))
		{
			SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 1.5F);
			u.setString(player, (byte)2,"��f"+event.getMessage());
			YamlLoader Board = new YamlLoader();
			Board.getConfig("Structure/"+u.getString(player, (byte)3)+".yml");
			Board.set("User."+Board.getInt("Post_Number")+".User", player.getName());
			Board.set("User."+Board.getInt("Post_Number")+".Title", u.getString(player, (byte)1));
			Board.set("User."+Board.getInt("Post_Number")+".Memo", u.getString(player, (byte)2));
			Board.set("User."+Board.getInt("Post_Number")+".UTC", new servertick.ServerTickMain().nowUTC);
			Board.set("Post_Number", Board.getInt("Post_Number")+1);
			Board.saveConfig();
			new StructBoard().BoardMainGUI(player, u.getString(player, (byte)3), (byte) 0);
			u.clearAll(player);
		}
		else if(u.getString(player,(byte)0).equals("Notice"))
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			u.setString(player, (byte)2,"��f"+event.getMessage());
			YamlLoader Board = new YamlLoader();
			Board.getConfig("Structure/"+u.getString(player, (byte)1)+".yml");
			Board.set("Notice", event.getMessage());
			Board.saveConfig();
			new StructBoard().BoardSettingGUI(player, u.getString(player, (byte)1));
			u.clearAll(player);
		}
	}
	
	private void TradeBoardChatting(PlayerChatEvent event)
	{
		
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		
		if(u.getString(player,(byte)0).equals("Notice"))
		{
			if(askOX(Message, player)==1)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				if(u.getInt(player, (byte)0)==1)//�ŷ� Ÿ���� �Ǹ��� ���
				{
					new StructTradeBoard().SelectSellItemGUI(player);
					player.sendMessage("��a[�ŷ� �Խ���] : �Ǹ��� �������� �����ϼ���.");
				}
				else if(u.getInt(player, (byte)0)==3)//�ŷ� Ÿ���� ������ ���
				{
					new StructTradeBoard().SelectBuyItemGUI(player);
					player.sendMessage("��a[�ŷ� �Խ���] : ������ �������� �����ϼ���.");
				}
				else //�ŷ� Ÿ���� ��ȯ�� ���
				{
					new StructTradeBoard().SelectExchangeItem_YouGUI(player);
					player.sendMessage("��a[�ŷ� �Խ���] : ������ ������ ����ϴ� �������� �����ϼ���.");
				}
				u.setString(player, (byte)0, "SelectItem");
			}
			else if(Message.equals("�ƴϿ�")||Message.equals("x")
				||Message.equals("X"))
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��a[�ŷ� �Խ���] : ��ǰ ����� ��ҵǾ����ϴ�.");
				u.clearAll(player);
				return;
			}
		}
		else if(u.getString(player,(byte)0).equals("SetNeedAmount"))
		{
			if(isIntMinMax(event.getMessage(), player, 1, 1000))
			{
				if(u.getInt(player, (byte)0)==5)
				{
					u.setInt(player, (byte) 2, Integer.parseInt(event.getMessage()));
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage("��a[�ŷ� �Խ���] : ����� ������ �ֽǰǰ���?");
					u.setString(player, (byte)0, "SetMyItem");
					new StructTradeBoard().SelectExchangeItem_MyGUI(player);
					return;
				}
				u.setInt(player, (byte) 2, Integer.parseInt(event.getMessage()));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��a[�ŷ� �Խ���] : 1���� �󸶿� ���� �Ͻǰǰ���? (0 ~ 2�鸸)");
				u.setString(player, (byte)0, "SetPrice");
			}
		}
		else if(u.getString(player,(byte)0).equals("SetPrice"))
		{
			if(isIntMinMax(event.getMessage(), player, 0, 2000000))
			{
				u.setInt(player, (byte)1, Integer.parseInt(event.getMessage()));

				//�Ǹ��� ���
				if(u.getInt(player, (byte)0)==3)
				{
					YamlLoader Board = new YamlLoader();
					Board.getConfig("Structure/UserShopBoard.yml");
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()<Board.getInt("RegisterCommission"))
					{
						u.clearAll(player);
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage("��c[�ŷ� �Խ���] : ��� �����ᰡ �����մϴ�! �� ��� �� �ּ���!");
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
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage("��c[�ŷ� �Խ���] : ���� ��ǰ�� �̹� ����ϼ̽��ϴ�!");
						return;
					}
					if(Board.contains("Sell."+ItemName)==false)
						Board.set("Sell."+ItemName+".Item", itemAmountOne);
					Board.set("SellRegistered", Board.getInt("SellRegistered")+1);
					Board.set("Sell."+ItemName+"."+player.getUniqueId().toString()+".Name", player.getName());
					Board.set("Sell."+ItemName+"."+player.getUniqueId().toString()+".Price", u.getInt(player, (byte)1));
					Board.set("Sell."+ItemName+"."+player.getUniqueId().toString()+".Amount", u.getInt(player,(byte)2));
					Board.saveConfig();
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * Board.getInt("RegisterCommission"), 0, false);
					YamlLoader USRL = new YamlLoader();
					USRL.getConfig("Structure/UserShopRegisterList.yml");
					USRL.set(player.getUniqueId().toString(), USRL.getInt(player.getUniqueId().toString())+1);
					USRL.saveConfig();
					u.clearAll(player);
					SoundEffect.SP(player, Sound.BLOCK_CHEST_OPEN, 1.0F, 1.8F);
					player.sendMessage("��a[�ŷ� �Խ���] : ����� �Ϸ�Ǿ����ϴ�!");
					return;
				}
				for(int count = 0; count < player.getInventory().getSize(); count++)
				{
					if(player.getInventory().getItem(count)!=null)
						if(player.getInventory().getItem(count).equals(u.getItemStack(player)))
						{
							YamlLoader Board = new YamlLoader();
							Board.getConfig("Structure/UserShopBoard.yml");
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()<Board.getInt("RegisterCommission"))
							{
								u.clearAll(player);
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
								player.sendMessage("��c[�ŷ� �Խ���] : ��� �����ᰡ �����մϴ�! �� ��� �� �ּ���!");
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
									SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
									player.sendMessage("��c[�ŷ� �Խ���] : ���� ��ǰ�� �̹� ����ϼ̽��ϴ�!");
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
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * Board.getInt("RegisterCommission"), 0, false);

							YamlLoader USRL = new YamlLoader();
							USRL.getConfig("Structure/UserShopRegisterList.yml");
							USRL.set(player.getUniqueId().toString(), USRL.getInt(player.getUniqueId().toString())+1);
							USRL.saveConfig();
							player.getInventory().setItem(count, null);
							u.clearAll(player);
							SoundEffect.SP(player, Sound.BLOCK_CHEST_OPEN, 1.0F, 1.8F);
							player.sendMessage("��a[�ŷ� �Խ���] : ����� �Ϸ�Ǿ����ϴ�!");
							return;
						}
				}
				u.clearAll(player);
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��c[�ŷ� �Խ���] : ����� �������� �����ϴ�! �� ��� �� �ּ���!");
			}
		}
		else if(u.getString(player,(byte)0).equals("TradeBoardSetting"))
		{
			String SettingType = u.getString(player, (byte)1);
			YamlLoader Board = new YamlLoader();
			Board.getConfig("Structure/UserShopBoard.yml");
			if(SettingType.equals("SellCommission"))
			{
				if(isIntMinMax(event.getMessage(), player, 0, 100))
					Board.set(SettingType, Integer.parseInt(event.getMessage()));
			}
			else if(SettingType.equals("RegisterCommission"))
			{
				if(isIntMinMax(event.getMessage(), player, 0, 2000000))
					Board.set(SettingType, Integer.parseInt(event.getMessage()));
			}
			else if(SettingType.equals("LimitPerPlayer"))
			{
				if(isIntMinMax(event.getMessage(), player, 1, 100))
					Board.set(SettingType, Integer.parseInt(event.getMessage()));
			}
			Board.saveConfig();
			new structure.StructTradeBoard().TradeBoardSettingGUI(player);
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			u.clearAll(player);
		}
		else if(u.getString(player,(byte)0).equals("BuyTrade"))
		{
			if(isIntMinMax(event.getMessage(), player, 0, u.getInt(player, (byte)0)))
			{
				int needAmount = Integer.parseInt(ChatColor.stripColor(event.getMessage()));
				if(needAmount==0)
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage("��a[�ŷ� �Խ���] : �ŷ��� ��ҵǾ����ϴ�.");
					u.clearAll(player);
					return;
				}
				else
				{
					YamlLoader Board = new YamlLoader();
					Board.getConfig("Structure/UserShopBoard.yml");
					short ExitAmount = (byte) Board.getInt("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount");
					if(Board.contains("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2))==false)
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage("��c[�ŷ� �Խ���] : �ŷ� ������ �ٲ�����ϴ�! �� �õ� �� �ֽñ� �ٶ��ϴ�!");
						u.clearAll(player);
						return;
					}
					else if(ExitAmount<needAmount)
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
						player.sendMessage("��c[�ŷ� �Խ���] : �ŷ� ������ �ٲ�����ϴ�! �� �õ� �� �ֽñ� �ٶ��ϴ�!");
						u.clearAll(player);
						return;
					}
					else
					{
						int Price = Board.getInt("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Price");
						if(Price*needAmount <= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money())
						{
							Price = Price*needAmount;
							ItemStack item = Board.getItemStack("Buy."+u.getString(player, (byte)1)+".Item");
							int SellCommission = Board.getInt("SellCommission");
							int MinusSellCommission = (int)((Price/100.0) * SellCommission);
							
							if(Bukkit.getPlayer(Board.getString("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Name"))!=null)
							{
								Player Target = Bukkit.getPlayer(Board.getString("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Name"));
								if(Target.isOnline())
								{
									if(main.MainServerOption.PlayerList.get(u.getString(player, (byte)2)).getStat_Money()+Price > 2000000000)
									{
										SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
										player.sendMessage("��c[�ŷ� �Խ���] : �Ǹ����� ���� �ܰ� �ʰ��Ǿ� �ŷ��� ������ �� �����ϴ�!");
										u.clearAll(player);
										return;
									}
									SoundEffect.SP(Target, Sound.ENTITY_VILLAGER_YES, 1.0F, 1.0F);
									new effect.SendPacket().sendTitleSubTitle(Target, "\'��3[�ŷ� ����]\'","\'��3�ŷ� �Խ��ǿ� ����� ��ǰ�� �ǸŵǾ����ϴ�.\'", (byte)1, (byte)3, (byte)1);
									main.MainServerOption.PlayerList.get(u.getString(player, (byte)2)).addStat_MoneyAndEXP(Price-MinusSellCommission, 0, false);
								}
								else
								{
									YamlLoader TargetYML = new YamlLoader();
									TargetYML.getConfig("Stats/"+u.getString(player, (byte)2)+".yml");
									if(TargetYML.getLong("Stat.Money") + Price-MinusSellCommission > 2000000000)
									{
										SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
										player.sendMessage("��c[�ŷ� �Խ���] : �Ǹ����� ���� �ܰ� �ʰ��Ǿ� �ŷ��� ������ �� �����ϴ�!");
										u.clearAll(player);
										return;
									}
									TargetYML.set("Stat.Money", TargetYML.getLong("Stat.Money") + Price-MinusSellCommission);
									TargetYML.saveConfig();
								}
							}
							else
							{
								YamlLoader TargetYML = new YamlLoader();
								TargetYML.getConfig("Stats/"+u.getString(player, (byte)2)+".yml");
								TargetYML.set("Stat.Money", TargetYML.getLong("Stat.Money") + Price-MinusSellCommission);
								TargetYML.saveConfig();
							}
							if(ExitAmount-needAmount ==0)
							{
								if(Board.getConfigurationSection("Buy."+u.getString(player, (byte)1)).getKeys(false).size()==2)
									Board.removeKey("Buy."+u.getString(player, (byte)1));
								else
									Board.removeKey("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2));
								Board.set("BuyRegistered", Board.getInt("BuyRegistered")-1);
								YamlLoader USRL = new YamlLoader();
								USRL.getConfig("Structure/UserShopRegisterList.yml");
								if(USRL.contains(u.getString(player, (byte)2))==true)
								{
									USRL.set(u.getString(player, (byte)2), USRL.getInt(u.getString(player, (byte)2))-1);
									USRL.saveConfig();
								}
							}
							else
								Board.set("Buy."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount", ExitAmount-needAmount);
							Board.saveConfig();
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1*Price, 0, false);
							new structure.StructPostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ �Ǹ� ������]", 
							player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� "+needAmount+"�� �����Ͽ� "+Price+" ��f"+ChatColor.stripColor(main.MainServerOption.money)+"��f�� ������ ������, "
							+MinusSellCommission+" ��f"+ChatColor.stripColor(main.MainServerOption.money)+" ��ŭ�� �����Ḧ �����Ͽ����ϴ�.", null);

							if(needAmount!=1)
								item.setAmount(needAmount);
							new util.UtilPlayer().giveItemForce(player, item);
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
							player.sendMessage("��c[�ŷ� �Խ���] : �������� �����մϴ�!");
							player.sendMessage("��c[�ʿ� �ݾ� : "+(Price*needAmount)+" "+main.MainServerOption.money+"��c]");
							player.sendMessage("��c[���� �ݾ� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()+" "+main.MainServerOption.money+"��c]");
						}
						u.clearAll(player);
						return;
					}
				}
			}
		}
		else if(u.getString(player,(byte)0).equals("SellTrade"))
		{
			if(isIntMinMax(event.getMessage(), player, 0, u.getInt(player, (byte)0)))
			{
				short needAmount = (short) Integer.parseInt(ChatColor.stripColor(event.getMessage()));
				if(needAmount==0)
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage("��a[�ŷ� �Խ���] : �ŷ��� ��ҵǾ����ϴ�.");
					u.clearAll(player);
					return;
				}
				else
				{
					YamlLoader Board = new YamlLoader();
					Board.getConfig("Structure/UserShopBoard.yml");
					Player Target = Bukkit.getPlayer(Board.getString("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Name"));
					short itemcount = 0;
					ItemStack BoardAddedItem = Board.getItemStack("Sell."+u.getString(player, (byte)1)+".Item");
					for(int count=0; count<player.getInventory().getSize();count++)
					{
						ItemStack PlayerHave = player.getInventory().getItem(count);
						if(PlayerHave!=null)
						{
							int amount = PlayerHave.getAmount();
							PlayerHave.setAmount(1);
							if(PlayerHave.equals(BoardAddedItem))
								itemcount= (short) (itemcount+amount);
							PlayerHave.setAmount(amount);
						}
					}
					if(itemcount<needAmount)
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[�ŷ� �Խ���] : ������ ����� ������ ���� �ʽ��ϴ�!");
						u.clearAll(player);
						return;
					}
					if(needAmount > Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount"))
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[�ŷ� �Խ���] : �ŷ� ������ �ٲ�����ϴ�! �� �õ� �� �ֽñ� �ٶ��ϴ�!");
						u.clearAll(player);
						return;
					}
					int rawprice = Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Price");
					long price = rawprice*needAmount;
					
					if(Target!=null)
					{
						if(Target.isOnline())
						{
							if(main.MainServerOption.PlayerList.get(u.getString(player, (byte)2)).getStat_Money()<price)
							{
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[�ŷ� �Խ���] : �Ǹ����� ���� �ܰ� �����Ͽ� �ŷ��� ��ҵǾ����ϴ�!");
								u.clearAll(player);
								return;
							}
							else
							{
								SoundEffect.SP(Target, Sound.ENTITY_VILLAGER_YES, 1.0F, 1.0F);
								main.MainServerOption.PlayerList.get(u.getString(player, (byte)2)).addStat_MoneyAndEXP(-1*price, 0, false);
								new effect.SendPacket().sendTitleSubTitle(Target, "\'��3[�ŷ� ����]\'","\'��3�ŷ� �Խ��ǿ� �Ƿ��� ��ǰ�� �����Ͽ����ϴ�.\'", (byte)1, (byte)3, (byte)1);
							}
						}
						else
						{
							YamlLoader TargetYML = new YamlLoader();
							TargetYML.getConfig("Stats/"+u.getString(player, (byte)2)+".yml");
							if(TargetYML.getLong("Stat.Money") < price)
							{
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[�ŷ� �Խ���] : �Ǹ����� ���� �ܰ� �����Ͽ� �ŷ��� ��ҵǾ����ϴ�!");
								u.clearAll(player);
								return;
							}
							else
							{
								TargetYML.set("Stat.Money", TargetYML.getLong("Stat.Money") - price);
								TargetYML.saveConfig();
							}
						}
					}
					else
					{
						YamlLoader TargetYML = new YamlLoader();
						TargetYML.getConfig("Stats/"+u.getString(player, (byte)2)+".yml");
						if(TargetYML.getLong("Stat.Money") < price)
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[�ŷ� �Խ���] : �Ǹ����� ���� �ܰ� �����Ͽ� �ŷ��� ��ҵǾ����ϴ�!");
							u.clearAll(player);
							return;
						}
						else
						{
							TargetYML.set("Stat.Money", TargetYML.getLong("Stat.Money") - price);
							TargetYML.saveConfig();
						}
					}
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(price, 0, false);
					if(Board.getInt("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2)+".Amount")-needAmount ==0)
					{
						if(Board.getConfigurationSection("Sell."+u.getString(player, (byte)1)).getKeys(false).size()==2)
							Board.removeKey("Sell."+u.getString(player, (byte)1));
						else
							Board.removeKey("Sell."+u.getString(player, (byte)1)+"."+u.getString(player, (byte)2));
						Board.set("SellRegistered", Board.getInt("SellRegistered")-1);
						YamlLoader USRL = new YamlLoader();
						USRL.getConfig("Structure/UserShopRegisterList.yml");
						if(USRL.contains(Target.getUniqueId().toString())==true)
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
						short pack = (short) (needAmount/64);
						short trash = (short) (needAmount-(pack*64));
						BoardAddedItem.setAmount(64);
						for(int count=0;count<pack;count++)
							new structure.StructPostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ ���� ������]",
									player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� 64�� �Ǹ��Ͽ� "+(rawprice*64)+" "+ChatColor.stripColor(main.MainServerOption.money)+"��f�� ������ �߽��ϴ�.", BoardAddedItem);
						if(trash!=0)
						{
							BoardAddedItem.setAmount(trash);
							new structure.StructPostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ ���� ������]",
									player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� "+trash+"�� �Ǹ��Ͽ� "+(rawprice*trash)+" "+ChatColor.stripColor(main.MainServerOption.money)+"��f�� ������ �߽��ϴ�.", BoardAddedItem);
						}
					}
					else
					{
						BoardAddedItem.setAmount(needAmount);
						new structure.StructPostBox().SendPost_Server(u.getString(player, (byte)2), "[�ŷ� �Խ���]", "[��ǰ ���� ������]",
								player.getName()+"�Բ��� ["+u.getString(player, (byte)3)+"] �������� "+needAmount+"�� �Ǹ��Ͽ� "+price+" "+ChatColor.stripColor(main.MainServerOption.money)+"��f�� ������ �߽��ϴ�.", BoardAddedItem);
					}
					BoardAddedItem.setAmount(1);
					for(int count=0; count<player.getInventory().getSize();count++)
					{
						ItemStack PlayerHave = player.getInventory().getItem(count);
						if(PlayerHave!=null)
						{
							byte amount = (byte) PlayerHave.getAmount();
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
								needAmount = (short) (needAmount - amount);
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
		
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		String Message = ChatColor.stripColor(event.getMessage());
		//Reciever NickName
		if(u.getString(player,(byte)0).equals("RN"))
		{
			if(Message.equals(ChatColor.stripColor(player.getName())))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				u.clearAll(player);
				player.sendMessage("��c[����] : �ڱ� �ڽſ��Դ� ���� �� �����ϴ�!");
				return;
			}
		}
	}
	
	private boolean isIntMinMax(String message,Player player, int Min, int Max)
	{
		try
		{
			if(message.split(" ").length <= 1 && Integer.parseInt(message) >= Min&& Integer.parseInt(message) <= Max)
				return true;
			else
			{
				player.sendMessage("��c[SYSTEM] : �ּ� ��e"+Min+"��c, �ִ� ��e"+Max+"��c ������ ���ڸ� �Է��ϼ���!");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
		}
		catch(NumberFormatException e)
		{
			player.sendMessage("��c[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���. (��e"+Min+"��c ~ ��e"+Max+"��c)");
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
		return false;
	}

	private byte askOX(String message,Player player)
	{
		if(message.split(" ").length <= 1)
		{
			if(message.equals("x")||message.equals("X")||message.equals("�ƴϿ�"))
				return 0;
			else if(message.equals("o")||message.equals("O")||message.equals("��"))
				return 1;
			else
			{
				player.sendMessage("��c[SYSTEM] : [��/O] Ȥ�� [�ƴϿ�/X]�� �Է� �� �ּ���!");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			
		}
		else
		{
			player.sendMessage("��c[SYSTEM] : [��/O] Ȥ�� [�ƴϿ�/X]�� �Է� �� �ּ���!");
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
		return -1;
	}
}
