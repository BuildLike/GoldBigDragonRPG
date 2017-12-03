package battle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import effect.SoundEffect;
import main.MainServerOption;
import util.UtilNumber;
import util.YamlLoader;

public class BattleCalculator
{
	//�÷��̾��� �����ּ� ���ݷ��� �� ���� �޼ҵ�//
	public static int getCombatDamage(Entity entity, int defaultDamage, int str, boolean isMin)
	{
		if(entity != null && entity.getType() == EntityType.PLAYER)
		{
			Player player = (Player) entity;
			ItemStack item = player.getInventory().getItemInMainHand();
			if(item != null)
			{
				if(item.hasItemMeta())
				{
					if(item.getItemMeta().hasLore())
						if(item.getItemMeta().getLore().toString().contains(main.MainServerOption.damage+" : "))
						{
							switch(item.getType())
							{
							case WOOD_SPADE :
							case GOLD_SPADE :
							case WOOD_PICKAXE :
							case GOLD_PICKAXE:
								defaultDamage -= 2;
								break;
							case STONE_SPADE:
							case STONE_PICKAXE:
								defaultDamage -= 3;
								break;
							case IRON_SPADE:
							case WOOD_SWORD:
							case GOLD_SWORD:
							case IRON_PICKAXE:
								defaultDamage -= 4;
								break;
							case DIAMOND_SPADE:
							case STONE_SWORD:
							case DIAMOND_PICKAXE:
								defaultDamage -= 5;
								break;
							case IRON_SWORD:
								defaultDamage -= 6;
								break;
							case WOOD_AXE:
							case GOLD_AXE:
							case DIAMOND_AXE:
							case DIAMOND_SWORD:
								defaultDamage -= 7;
								break;
							case STONE_AXE:
							case IRON_AXE:
								defaultDamage -= 9;
								break;
							default:
								break;
							}
					}
				}
			}
			str += getPlayerEquipmentStat((Player)entity, "STR", true, null)[0];
			if(isMin)
				defaultDamage += getPlayerEquipmentStat((Player)entity, "Damage", true, null)[0];
			else
				defaultDamage += getPlayerEquipmentStat((Player)entity, "Damage", true, null)[1];
		}
		return returnCombatValue(str, defaultDamage, isMin);
	}
	
	//���� ���ݷ��� �� ���� �޼ҵ�//
	public static int returnCombatValue(int stat, int defaultDamage, boolean isMin)
	{
		if(isMin)
			defaultDamage += stat/5;
		else
			defaultDamage += stat/3;
		if(defaultDamage <= 0)
			return 0;
		else
			return defaultDamage;
	}

	//���� ���ݷ��� �� ���� �޼ҵ�//
	public static int returnExplosionDamageValue(int stat, int defaultDamage, boolean isMin)
	{
		int dam=0;
		if(isMin)
			dam = (stat/4)+defaultDamage;
		else
			dam = (int) ((stat/2.5)+defaultDamage);
		if(dam <= 0)
			return 1;
		else
			return dam;
	}
	
	//���Ÿ� ���ݷ��� �� ���� �޼ҵ�//
	public static int returnRangeDamageValue(Entity entity, int stat, int defaultDamage, boolean isMin)
	{
		if(entity != null)
			if(entity.getType() == EntityType.PLAYER)
			{
				stat = stat + getPlayerEquipmentStat((Player)entity, "DEX", false, null)[0];
				if(isMin)
					defaultDamage = defaultDamage + getPlayerEquipmentStat((Player)entity, "Damage", false, null)[0];
				else
					defaultDamage = defaultDamage + getPlayerEquipmentStat((Player)entity, "Damage", false, null)[1];
			}
		int dam=0;
		if(isMin)
			dam = ((stat/5) + defaultDamage);
		else
			dam = ((stat/3)+defaultDamage);
		if(dam <= 0)
			return 1;
		else
			return dam;
	}
	
	//�������� MP/HP ���ݿ� ���� ����� ���ʽ�
	public static int magicSpellsDamageBonus(int stat)
	{
		int dam=stat/25;
		if(dam <= 0)
			return 0;
		else
			return dam;
	}
	
    //�÷��̾��� �뷱���� ���ϰ�, �����ϰ� �������� ���� �� �ִ� �޼ҵ�//
	public static int damagerand(Entity entity, int min, int max, int playerBalance)
	{
		UtilNumber num = new UtilNumber();
		if(min > max)
		{
			int temp = max;
			max = min;
			min = temp;
		}
		if (num.RandomNum(1, 100) <= playerBalance)
			return num.RandomNum(num.RandomNum(min, max), max);
		else
		{
			max = (max/2);
			if(max <= min)
				max=min;
			return num.RandomNum(min, max);
		}
	}

	//�÷��̾��� ũ��Ƽ�� Ȯ���� ����ϰ�, ũ��Ƽ�� ���θ� �����ϴ� �޼ҵ�//
	public static int criticalrend(Entity entity, int attackerLuk, int attackerWill, int attackerDamage, int defenserProtect, int attackerCritical)
	{
		UtilNumber num = new UtilNumber();
		int critical;
		if(defenserProtect/2 <= 1) 
			critical= getCritical(entity, attackerLuk, attackerWill,attackerCritical);
		else
			critical= (getCritical(entity, attackerLuk, attackerWill,attackerCritical)/100)*(100-(defenserProtect/2));
		if (critical > 90)
			critical = 90;
		if (critical < 2)
			critical = 2;
		int getcritical = num.RandomNum(0, 100);
		if (getcritical <= critical)
			return (attackerDamage/2);
		else
			return 0;
	}

	//�뷱�� ����//
	public static int getBalance(Entity entity, int dex, int playerBalance)
	{
		int balance = playerBalance;
		if(entity!=null)
		if(entity.getType() == EntityType.PLAYER)
		{
			dex = dex + getPlayerEquipmentStat((Player)entity, "DEX", false, null)[0];
			balance = balance + getPlayerEquipmentStat((Player)entity, "Balance", false, null)[0];
		}
		balance = balance + (int)dex/20;
		if (balance > 80) balance = 80;
		if (balance < 0) balance = 1;
		return balance;
	}
	
	//ũ��Ƽ���� ����//
	public static int getCritical(Entity entity, int playerLuk, int playerWill, int defaultCritical)
	{
		int critical = defaultCritical;
		if(entity!=null)
		if(entity.getType() == EntityType.PLAYER)
			critical = critical + getPlayerEquipmentStat((Player)entity, "Critical", false, null)[0];
		critical = critical + (playerLuk/5 + playerWill/10);
		return critical;
	}
	
	//���� ����//
	public static int getMagicDEF(Entity entity, int playerInt)
	{
		int magicDef = 0;
		if(entity.getType() == EntityType.PLAYER)
		{
			playerInt = playerInt + getPlayerEquipmentStat((Player)entity, "INT", false, null)[0];
			magicDef = magicDef + getPlayerEquipmentStat((Player)entity, "Magic_DEF", false, null)[0];
		}
		magicDef = magicDef + (playerInt/20);
		return magicDef;
	}

	//���� ����//
	public static int getMagicProtect(Entity entity, int playerInt)
	{
		int magicProtect = 0;
		if(entity.getType() == EntityType.PLAYER)
		{
			playerInt = playerInt + getPlayerEquipmentStat((Player)entity, "INT", false, null)[0];
			magicProtect = magicProtect + getPlayerEquipmentStat((Player)entity, "Magic_Protect", false, null)[0];
		}
		magicProtect = magicProtect + (playerInt/100);
		return magicProtect;
	}

	//��� ���� ����//
	public static int getDEFcrash(Entity entity, int playerDex)
	{
		int defCrash = 0;
		if(entity.getType() == EntityType.PLAYER)
		{
			playerDex = playerDex + getPlayerEquipmentStat((Player)entity, "DEX", false, null)[0];
			defCrash = defCrash + getPlayerEquipmentStat((Player)entity, "DEFcrash", false, null)[0];
		}
		defCrash = defCrash + (playerDex/40);
		return defCrash;
	}

	//���� ���� ����//
	public static int getMagicDEFcrash(Entity entity, int playeInt)
	{
		int magicDefCrash = 0;
		if(entity.getType() == EntityType.PLAYER)
		{
			playeInt = playeInt + getPlayerEquipmentStat((Player)entity, "INT", false, null)[0];
			magicDefCrash = magicDefCrash + getPlayerEquipmentStat((Player)entity, "MagicDEFcrash", false, null)[0];
		}
		magicDefCrash = magicDefCrash + (playeInt/40);
		return magicDefCrash;
	}

	public static int[] getPlayerEquipmentStat(Player player, String type, boolean isCombat, ItemStack newSlot)
	{
		int[] bonus = new int[2];
		String[] lore;
		switch(type)
		{
			case "Damage":type = main.MainServerOption.damage;break;
			case "DEF":type = "���";break;
			case "DEFcrash":type = "������";break;
			case "Protect":type = "��ȣ";break;
			case "MagicDamage":type = main.MainServerOption.magicDamage;break;
			case "Magic_DEF":type = "���� ���";break;
			case "MagicDEFcrash":type = "���� ������";break;
			case "Magic_Protect":type = "���� ��ȣ";break;
			case "STR":type = main.MainServerOption.statSTR;break;
			case "DEX":type = main.MainServerOption.statDEX;break;
			case "INT":type = main.MainServerOption.statINT;break;
			case "WILL":type = main.MainServerOption.statWILL;break;
			case "LUK":type = main.MainServerOption.statLUK;break;
			case "HP":type = "�����";break;
			case "MP":type = "����";break;
			case "Critical":type = "ũ��Ƽ��";break;
			case "Balance":type = "�뷱��";break;
			case "Upgrade":type = "���׷��̵�";break;
			default : break;
		}
		ArrayList<ItemStack> item = new ArrayList<>();
		item.add(player.getInventory().getHelmet());
		item.add(player.getInventory().getChestplate());
		item.add(player.getInventory().getLeggings());
		item.add(player.getInventory().getBoots());
		if(newSlot==null)
			item.add(player.getInventory().getItemInMainHand());
		else
		{
			if(!newSlot.hasItemMeta())
				newSlot=null;
			else
				item.add(newSlot);
		}
		if(main.MainServerOption.dualWeapon)
			item.add(player.getInventory().getItemInOffHand());
		boolean totaluseable = true;
		for(int counter = 0; counter < item.size(); counter++)
		{
			boolean isCancel = false;
			boolean exitDurability = true;
			if(item.get(counter) != null)
			{
				if(counter >= 4 && newSlot==null)
				{
					if(counter == 4)
					{
						if(!isCombat && !(item.get(4).getTypeId() == 261 ||item.get(4).getTypeId() == 262 || item.get(4).getTypeId() == 439|| item.get(4).getTypeId() == 440) && type.equals(main.MainServerOption.damage))
							isCancel = true;
					}
					else
					{
						if(!isCombat && !(item.get(5).getTypeId() == 261 ||item.get(5).getTypeId() == 262 || item.get(5).getTypeId() == 439|| item.get(5).getTypeId() == 440) && type.equals(main.MainServerOption.damage))
							isCancel = true;
						if(isCombat && (item.get(counter).getTypeId()==261) && type.equals(main.MainServerOption.damage))
							break;
					}
				}
				if(!isCancel)
				{
					if(item.get(counter).hasItemMeta())
					{
						if(item.get(counter).getItemMeta().hasLore())
						{
							if(item.get(counter).getItemMeta().getLore().toString().contains(type))
							{
								if(!(item.get(counter).getItemMeta().getLore().toString().contains("[�ֹ���]")||item.get(counter).getItemMeta().getLore().toString().contains("[��]")||item.get(counter).getItemMeta().getLore().toString().contains("[�Һ�]")))
								{
									boolean useable = true;
									for(int count = 0; count < item.get(counter).getItemMeta().getLore().size(); count++)
									{
										String nowlore=ChatColor.stripColor(item.get(counter).getItemMeta().getLore().get(count));
										if(nowlore.contains(" : "))
										{
											if(nowlore.contains("����"))
												if(!nowlore.split(" : ")[1].equals(MainServerOption.PlayerList.get(player.getUniqueId().toString()).getPlayerRootJob()))
													useable = false;
											if(nowlore.contains("�ּ�"))
											{
												String[] resist = nowlore.split(" ");
												if(resist[resist.length-3].equals("����"))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
												else if(resist[resist.length-3].equals("��������"))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
												else if(resist[resist.length-3].equals(main.MainServerOption.statSTR))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR();
												else if(resist[resist.length-3].equals(main.MainServerOption.statDEX))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX();
												else if(resist[resist.length-3].equals(main.MainServerOption.statINT))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT();
												else if(resist[resist.length-3].equals(main.MainServerOption.statWILL))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL();
												else if(resist[resist.length-3].equals(main.MainServerOption.statLUK))
													useable = Integer.parseInt(resist[resist.length-1]) <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK();
											}
											if(!useable)
											{
												totaluseable = false;
												break;
											}
											if(nowlore.contains("������"))
											{
												String[] Lore2 = nowlore.split(" : ");
												String[] SubLore = Lore2[1].split(" / ");
												if(Integer.parseInt(SubLore[0]) <= 0)
												{
													exitDurability = false;
													break;
												}
											}
										}
									}
									if(useable)
									{
										for(int count = 0; count < item.get(counter).getItemMeta().getLore().size(); count++)
										{
											if(item.get(counter).getItemMeta().getLore().get(count).contains(type))
											{
												if(item.get(counter).getItemMeta().getLore().get(count).contains(" : ")||item.get(counter).getItemMeta().getLore().get(count).contains("/"))
												{
													if(exitDurability)
													{
														lore = ChatColor.stripColor(item.get(counter).getItemMeta().getLore().get(count)).split(" : ");
														if(lore[0].contains(type))
														{
															if(type.equals(main.MainServerOption.statSTR)||type.equals(main.MainServerOption.statDEX)||
																type.equals(main.MainServerOption.statINT)||type.equals(main.MainServerOption.statWILL)||
																type.equals(main.MainServerOption.statLUK))
															{
																if(!item.get(counter).getItemMeta().getLore().get(count).contains("�ּ�"))
																	bonus[0] = bonus[0] + Integer.parseInt(lore[1]);
															}
															else if(type.equals(main.MainServerOption.damage)||type.equals(main.MainServerOption.magicDamage)||type.equals("���׷��̵�"))
															{
																if(type.equals(main.MainServerOption.damage))
																{
																	String[] subLore = lore[1].split(" ~ ");
																	bonus[0] = bonus[0] + Integer.parseInt(subLore[0]);
																	bonus[1] = bonus[1] + Integer.parseInt(subLore[1]);
																}
																else if(type.equals(main.MainServerOption.magicDamage)||type.equals("���׷��̵�"))
																{
																	String[] subLore = lore[1].split(" ~ ");
																	bonus[0] = bonus[0] + Integer.parseInt(subLore[0]);
																	bonus[1] = bonus[1] + Integer.parseInt(subLore[1]);
																}
															}
															else
																bonus[0] = bonus[0] + Integer.parseInt(lore[1]);
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
				}
			}
		}
		if(!totaluseable)
			new effect.SendPacket().sendTitle(player, "��e", "��c(��� �� ������ ���� ���ϰ� �ִ�!)", 1, 1, 1);
		return bonus;
	}
	
	public static void decreaseDurabilityArmor(Player player)
	{
		boolean durabilityExit = false;
		ArrayList<ItemStack> item = new ArrayList<>();
		item.add(player.getInventory().getHelmet());
		item.add(player.getInventory().getChestplate());
		item.add(player.getInventory().getLeggings());
		item.add(player.getInventory().getBoots());
		byte added = 0;
		if(player.getInventory().getItemInMainHand().getTypeId()==442)
		{
			item.add(player.getInventory().getItemInMainHand());
			added = (byte) (added + 1);
		}
		if(main.MainServerOption.dualWeapon)
			if(player.getInventory().getItemInOffHand().getTypeId()==442)
			{
				item.add(player.getInventory().getItemInOffHand());
				added = (byte) (added + 2);
			}
		for(int counter = 0; counter < item.size(); counter++)
		{
			if(item.get(counter) != null)
			if(item.get(counter).hasItemMeta())
			{
				if(item.get(counter).getItemMeta().hasLore())
				{
					if(item.get(counter).getItemMeta().getLore().toString().contains("������"))
					{
						if(!(item.get(counter).getItemMeta().getLore().toString().contains("[�ֹ���]")||item.get(counter).getItemMeta().getLore().toString().contains("[��]")||item.get(counter).getItemMeta().getLore().toString().contains("[�Һ�]")))
						{
							for(int count = 0; count < item.get(counter).getItemMeta().getLore().size(); count++)
							{
								String nowlore=ChatColor.stripColor(item.get(counter).getItemMeta().getLore().get(count));
								if(nowlore.contains(" : "))
								{
									ItemMeta meta = item.get(counter).getItemMeta();
									if(nowlore.contains(" / "))
									{
										if(meta.getLore().get(count).contains("������"))
										{
											String[] lore = ChatColor.stripColor(meta.getLore().get(count)).split(" : ");
											String[] subLore = lore[1].split(" / ");
											List<String> pLore = meta.getLore();
											if((Integer.parseInt(subLore[0])-1) <= 0)
											{
											  	YamlLoader configYaml = new YamlLoader();
												configYaml.getConfig("config.yml");
												if(configYaml.getBoolean("Server.CustomWeaponBreak"))
												{
													SoundEffect.SP(player, Sound.ENTITY_ITEM_BREAK, 1.2F, 1.0F);
													if(item.get(counter).getItemMeta().hasDisplayName())
														player.sendMessage("��c[��� �ı�] : ��e"+item.get(counter).getItemMeta().getDisplayName()+"��c ��� �ı��Ǿ����ϴ�!");
													else
														player.sendMessage("��c[��� �ı�] : ��� �ı��Ǿ����ϴ�!");
													if(counter==0)
														player.getInventory().setHelmet(new ItemStack(0));
													else if(counter==1)
														player.getInventory().setChestplate(new ItemStack(0));
													else if(counter==2)
														player.getInventory().setLeggings(new ItemStack(0));
													else if(counter==3)
														player.getInventory().setBoots(new ItemStack(0));
													else if(added==1 && counter==4)
														player.getInventory().setItemInMainHand(new ItemStack(0));
													else if(added==2 && counter==4)
														player.getInventory().setItemInOffHand(new ItemStack(0));
													else if(added==3)
													{
														if(counter==4)
															player.getInventory().setItemInMainHand(new ItemStack(0));
														else
															player.getInventory().setItemInOffHand(new ItemStack(0));
													}
													else
														item.set(counter, new ItemStack(0));
													break;
												}
												else
													pLore.set(count,"��f"+  lore[0] + " : "+ 0 +" / "+subLore[1]);
											}
											else
											{
												if((Integer.parseInt(subLore[0])-1) == 20)
												{
													SoundEffect.SP(player, Sound.BLOCK_ANVIL_USE, 0.8F, 0.5F);
													if(counter==0)
														player.sendMessage("��e[��� �ı�] : ������ �������� �� ��� ���ϴ�!");
													else if(counter==1)
														player.sendMessage("��e[��� �ı�] : �䰩�� �������� �� ��� ���ϴ�!");
													else if(counter==2)
														player.sendMessage("��e[��� �ı�] : ������ �������� �� ��� ���ϴ�!");
													else if(counter==3)
														player.sendMessage("��e[��� �ı�] : �Ź��� �������� �� ��� ���ϴ�!");
													else if(added==1 && counter==4)
														player.sendMessage("��e[��� �ı�] : �� ������ �������� �� ��� ���ϴ�!");
													else if(added==2 && counter==4)
														player.sendMessage("��e[��� �ı�] : ���� ������ �������� �� ��� ���ϴ�!");
													else if(added==3)
													{
														if(counter==4)
															player.sendMessage("��e[��� �ı�] : �� ������ �������� �� ��� ���ϴ�!");
														else
															player.sendMessage("��e[��� �ı�] : ���� ������ �������� �� ��� ���ϴ�!");
													}
													else
														player.sendMessage("��e[��� �ı�] : ����� �������� �� ��� ���ϴ�!");
												}
												pLore.set(count,"��f"+  lore[0] + " : "+(Integer.parseInt(subLore[0])-1) +" / "+subLore[1]);
												durabilityExit = true;
											}
											meta.setLore(pLore);
											item.get(counter).setItemMeta(meta);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(durabilityExit)
		{
			for(int counter = 0; counter < item.size(); counter++)
			{
				if(item.get(counter)!=null)
				if(item.get(counter).hasItemMeta())
				{
					if(item.get(counter).getItemMeta().hasLore())
					{
						if(item.get(counter).getItemMeta().getLore().toString().contains("���õ�"))
						{
							for(int count = 0; count < item.get(counter).getItemMeta().getLore().size(); count++)
							{
								String nowlore=ChatColor.stripColor(item.get(counter).getItemMeta().getLore().get(count));
								if(nowlore.contains(" : "))
								{
									ItemMeta meta = item.get(counter).getItemMeta();
									if(meta.getLore().get(count).contains("���õ�"))
									{
										float proficiency = 0.07F * main.MainServerOption.eventProficiency;
										String[] lore = ChatColor.stripColor(meta.getLore().get(count)).split(" : ");
										String[] subLore = lore[1].split("%");
										List<String> pLore = meta.getLore();
										DecimalFormat format = new DecimalFormat(".##");
										String str = format.format((Float.parseFloat(subLore[0])+proficiency));
										if(str.charAt(0)=='.')
											str = "0"+str;
										if((Float.parseFloat(subLore[0])+0.07F) >= 100.0F)
											pLore.set(count,"��f"+  lore[0] + " : "+ 100.0 +"%��f");
										else
											pLore.set(count,"��f"+  lore[0] + " : "+ str +"%��f");
										meta.setLore(pLore);
										item.get(counter).setItemMeta(meta);
									}
								}
							}
						}
					}
				}
			}
		}
		return;
	}
	
	public static void decreaseDurabilityWeapon(Player player)
	{
		boolean durabilityExit = false;
		ArrayList<ItemStack> item = new ArrayList<>();
		item.add(player.getInventory().getItemInMainHand());
		if(main.MainServerOption.dualWeapon)
			item.add(player.getInventory().getItemInOffHand());
		for(int counter = 0; counter < item.size(); counter++)
		{
			if(item.get(counter)!=null)
			{
				if(counter==1 && (item.get(counter).getTypeId()==442||item.get(counter).getTypeId()==261))
					return;
				if(item.get(counter).hasItemMeta())
				{
					if(item.get(counter).getItemMeta().hasLore())
					{
						if(item.get(counter).getItemMeta().getLore().toString().contains("������"))
						{
							if(!(item.get(counter).getItemMeta().getLore().toString().contains("[�ֹ���]")||item.get(counter).getItemMeta().getLore().toString().contains("[��]")||item.get(counter).getItemMeta().getLore().toString().contains("[�Һ�]")))
							{
								for(int count = 0; count < item.get(counter).getItemMeta().getLore().size(); count++)
								{
									String nowlore=ChatColor.stripColor(item.get(counter).getItemMeta().getLore().get(count));
									if(nowlore.contains(" : ") && nowlore.contains(" / "))
									{
										ItemMeta meta = item.get(counter).getItemMeta();
										if(meta.getLore().get(count).contains("������"))
										{
											String[] lore = ChatColor.stripColor(meta.getLore().get(count)).split(" : ");
											String[] subLore = lore[1].split(" / ");
											List<String> pLore = meta.getLore();
											if((Integer.parseInt(subLore[0])-1) <= 0)
											{
											  	YamlLoader configYaml = new YamlLoader();
												configYaml.getConfig("config.yml");
												if(configYaml.getBoolean("Server.CustomWeaponBreak"))
												{
													SoundEffect.SP(player, Sound.ENTITY_ITEM_BREAK, 1.2F, 1.0F);
													if(item.get(counter).getItemMeta().hasDisplayName())
														player.sendMessage("��c[��� �ı�] : ��e"+item.get(counter).getItemMeta().getDisplayName()+"��c ��� �ı��Ǿ����ϴ�!");
													else
														player.sendMessage("��c[��� �ı�] : ��� �ı��Ǿ����ϴ�!");
													if(counter==0)
														player.getInventory().setItemInMainHand(new ItemStack(0));
													else
														player.getInventory().setItemInOffHand(new ItemStack(0));
													break;
												}
												else
													pLore.set(count, "��f"+ lore[0] + " : "+ 0 +" / "+subLore[1]);
											}
											else
											{
												pLore.set(count, "��f"+ lore[0] + " : "+(Integer.parseInt(subLore[0])-1) +" / "+subLore[1]);
												durabilityExit = true;
											}
											meta.setLore(pLore);
											item.get(counter).setItemMeta(meta);
										}
									}
								}
							}
						}
					}
				}
				if(durabilityExit)
				{
					if(item.get(counter).hasItemMeta())
					{
						if(item.get(counter).getItemMeta().hasLore())
						{
							if(item.get(counter).getItemMeta().getLore().toString().contains("���õ�"))
							{
								for(int count = 0; count < item.get(counter).getItemMeta().getLore().size(); count++)
								{
									String nowlore=ChatColor.stripColor(item.get(counter).getItemMeta().getLore().get(count));
									if(nowlore.contains(" : "))
									{
										ItemMeta meta = item.get(counter).getItemMeta();
										if(meta.getLore().get(count).contains("���õ�"))
										{
											float proficiency = 0.07F * main.MainServerOption.eventProficiency;
											String[] lore = ChatColor.stripColor(meta.getLore().get(count)).split(" : ");
											String[] subLore = lore[1].split("%");
											List<String> pLore = meta.getLore();
											DecimalFormat format = new DecimalFormat(".##");
											String str = format.format((Float.parseFloat(subLore[0])+proficiency));
											if(str.charAt(0)=='.')
												str = "0"+str;
											if((Float.parseFloat(subLore[0])+0.07F) >= 100.0F)
												pLore.set(count,"��f"+  lore[0] + " : "+ 100.0 +"%��f");
											else
												pLore.set(count,"��f"+  lore[0] + " : "+ str +"%��f");
											meta.setLore(pLore);
											item.get(counter).setItemMeta(meta);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return;
	}
}
