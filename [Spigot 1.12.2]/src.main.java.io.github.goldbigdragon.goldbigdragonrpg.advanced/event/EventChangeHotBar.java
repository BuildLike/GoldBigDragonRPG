package event;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.Spell;

import effect.SoundEffect;

import main.MainServerOption;
import util.YamlLoader;



public class EventChangeHotBar implements Listener
{
	@EventHandler
	public void HotBarMove(PlayerItemHeldEvent event)
	{
		Player player = event.getPlayer();
		if(new corpse.CorpseMain().deathCapture(player,false))
			return;
			
		byte NewSlot = (byte)event.getNewSlot();
		if(player.getInventory().getItem(NewSlot) != null)
		{
			if(player.getInventory().getItem(NewSlot).hasItemMeta() == true)
			{
				ItemStack item = player.getInventory().getItem(NewSlot);
				if(item.getItemMeta().hasLore() == true)
				{
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isClickUse()==false &&item.getItemMeta().getLore().toString().contains("[�Һ�]"))
					{
						byte PrevSlot = (byte)event.getPreviousSlot();

						int Health = 0;
						int Mana = 0;
						int Food = 0;
						for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
						{
							String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
							if(nowlore.contains(" : "))
							{
								if(nowlore.contains("�����"))
									Health = Integer.parseInt(nowlore.split(" : ")[1]);
								else if(nowlore.contains("����"))
									Mana = Integer.parseInt(nowlore.split(" : ")[1]);
								else if(nowlore.contains("������"))
									Food = Integer.parseInt(nowlore.split(" : ")[1]);
							}
							else if(nowlore.contains("ȯ��"))
							{
								if(nowlore.contains(" + "))
								{
								  	YamlLoader configYaml = new YamlLoader();
								    configYaml.getConfig("config.yml");
									
									if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
									{
										main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_RealLevel(1);
										main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_Level(1);
										main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_EXP(0);
										main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_MaxEXP(100);
										
										SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.5F);
										SoundEffect.SP(player, Sound.ENTITY_FIREWORK_LAUNCH, 1.0F, 1.2F);
										SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
										effect.SendPacket PS = new effect.SendPacket();
										PS.sendTitle(player,"��e�� [ Rebirth ] ��",  "��e[���� �� ����ġ�� �ʱ�ȭ �Ǿ����ϴ�!]", 1, 5, 1);
									}
									else
									{
										player.sendMessage("��c[SYSTEM] : ���� �ý��ۿ� ���� �ʾ� ȯ���� �� �� �����ϴ�!");
										SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
									}
								}
							}
						}
						if(Health > 0)
						{
							SoundEffect.SL(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 2.0F, 0.8F);
							Damageable Dp = player;
							if(Dp.getMaxHealth() < Dp.getHealth()+Health)
								Dp.setHealth(Dp.getMaxHealth());
							else
								Dp.setHealth(Dp.getHealth() + Health);
						}
						if(Mana >0)
						{
							if(MainServerOption.MagicSpellsCatched)
							{
								otherplugins.SpellMain MS = new otherplugins.SpellMain();
								MS.DrinkManaPotion(player, Mana);
								SoundEffect.SL(player.getLocation(), Sound.BLOCK_WATER_AMBIENT, 2.0F, 1.9F);
							}
						}
						if(Food > 0)
						{
							SoundEffect.SL(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 2.0F, 1.2F);
							if(player.getFoodLevel()+Food > 40)
								player.setFoodLevel(40);
							else
								player.setFoodLevel(player.getFoodLevel()+Food);
						}
						if(item.getAmount() != 1)
							item.setAmount(item.getAmount()-1);
						else
							player.getInventory().setItem(NewSlot, new ItemStack(0));

						effect.SendPacket PS = new effect.SendPacket();
						PS.switchHotbarSlot(player, PrevSlot);
					}
					else if(item.getItemMeta().getLore().size() == 4)
					{
						if(item.getItemMeta().getLore().get(3).equals("��e[Ŭ���� �����Կ��� ����]"))
						{
							byte PrevSlot = (byte)event.getPreviousSlot();
							
							String CategoryName = ChatColor.stripColor(item.getItemMeta().getLore().get(0));
							String Skillname = ChatColor.stripColor(item.getItemMeta().getLore().get(1));

						  	YamlLoader configYaml = new YamlLoader();
						  	YamlLoader playerSkillYaml = new YamlLoader();
						  	YamlLoader allSkillYaml = new YamlLoader();
							configYaml.getConfig("config.yml");
							playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
							allSkillYaml.getConfig("Skill/SkillList.yml");
							short PlayerSkillRank =0;

							if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
								PlayerSkillRank= (short) playerSkillYaml.getInt("MapleStory."+CategoryName+".Skill."+Skillname);
							else
								PlayerSkillRank= (short) playerSkillYaml.getInt("Mabinogi."+CategoryName+"."+Skillname);
								
							if(PlayerSkillRank ==0)
							{
								player.sendMessage("��c[��ų] : �ش� ��ų�� ������ ��ų�Դϴ�!");
								player.getInventory().setItem(NewSlot, new ItemStack(0));
								return;
							}
							effect.SendPacket PS = new effect.SendPacket();
							PS.switchHotbarSlot(player, PrevSlot);
							
							String Command = allSkillYaml.getString(Skillname+".SkillRank."+PlayerSkillRank+".Command");
							String Spell = allSkillYaml.getString(Skillname+".SkillRank."+PlayerSkillRank+".MagicSpells");
							boolean isConsole = allSkillYaml.getBoolean(Skillname+".SkillRank."+PlayerSkillRank+".Command");
							String AffectStat = allSkillYaml.getString(Skillname+".SkillRank."+PlayerSkillRank+".AffectStat");
							String DistrictWeapon = allSkillYaml.getString(Skillname+".SkillRank."+PlayerSkillRank+".DistrictWeapon");

							MainServerOption.PlayerUseSpell.put(player, AffectStat);
							MainServerOption.PlayerlastItem.put(player, player.getInventory().getItem(event.getPreviousSlot()));
							
							boolean Useable = false;
							
							if(DistrictWeapon.equalsIgnoreCase("����")==false)
							{
								if(player.getInventory().getItem(PrevSlot) != null)
								{
									ItemStack PrevItem = player.getInventory().getItem(PrevSlot);
									if(player.getInventory().getItem(PrevSlot).hasItemMeta() == false)
									{
										short PlayerItemIDItemStack = (short) PrevItem.getTypeId();
										switch(DistrictWeapon)
										{
										case "���� ����":
										{	switch(PlayerItemIDItemStack)
											{
												case 267:
												case 268:
												case 272:
												case 276:
												case 283:
												case 271:
												case 275:
												case 258:
												case 279:
												case 286:
												case 290:
												case 291:
												case 292:
												case 293:
												case 294:
												Useable = true;
												break;
											}
										}	break;
										case "�Ѽ� ��":
										{	switch(PlayerItemIDItemStack)
											{
												case 267:
												case 268:
												case 272:
												case 276:
												case 283:
												Useable = true;
												break;
											}
										}	break;
										case "����":
										{	switch(PlayerItemIDItemStack)
											{
												case 271:
												case 275:
												case 258:
												case 279:
												case 286:
												Useable = true;
												break;
											}
										}	break;
										case "��":
										{	switch(PlayerItemIDItemStack)
											{
												case 290:
												case 291:
												case 292:
												case 293:
												case 294:
												Useable = true;
												break;
											}
										}	break;
										case "���Ÿ� ����":
										{	switch(PlayerItemIDItemStack)
											{
												case 261:
												case 23:
												Useable = true;
												break;
											}
										}	break;
										case "Ȱ":
										{	if(PlayerItemIDItemStack == 261)
												Useable = true;
										}	break;
										case "����":
										{	if(PlayerItemIDItemStack == 23)
												Useable = true;
										}	break;
										case "���� ����":
										{	switch(PlayerItemIDItemStack)
											{
												case 280:
												case 352:
												case 369:
												Useable = true;
												break;
											}
										}	break;
										case "����":
										{	if(PlayerItemIDItemStack == 280 || PlayerItemIDItemStack==352)
												Useable = true;
										}	break;
										case "������":
										{	if(PlayerItemIDItemStack == 369)
												Useable = true;
										}	break;
										}
									}
									else
									{
										if(PrevItem.getItemMeta().hasLore() == true)
										{
											switch(DistrictWeapon)
											{
											case "���� ����":
												if(PrevItem.getItemMeta().getLore().toString().contains("[�Ѽ� ��]")||PrevItem.getItemMeta().getLore().toString().contains("[��� ��]")
												||PrevItem.getItemMeta().getLore().toString().contains("[����]")||PrevItem.getItemMeta().getLore().toString().contains("[��]")
												||PrevItem.getItemMeta().getLore().toString().contains("[���� ����]"))
													Useable = true;
												break;
											case "���Ÿ� ����":
												if(PrevItem.getItemMeta().getLore().toString().contains("[Ȱ]")||PrevItem.getItemMeta().getLore().toString().contains("[����]")
														||PrevItem.getItemMeta().getLore().toString().contains("[���Ÿ� ����]"))
													Useable = true;
												break;
											case "���� ����":
												if(PrevItem.getItemMeta().getLore().toString().contains("[����]")||PrevItem.getItemMeta().getLore().toString().contains("[������]")
														||PrevItem.getItemMeta().getLore().toString().contains("[���� ����]"))
													Useable = true;
												break;
											default:
												if(PrevItem.getItemMeta().getLore().toString().contains(DistrictWeapon))
													Useable = true;
												break;
											}
										}
									}
								}
							}
							if(Useable == false && DistrictWeapon.equalsIgnoreCase("����")==false)
							{
								player.sendMessage("��c[��ų] : ���� ����δ� ��ų�� ����� �� �����ϴ�!");
								player.sendMessage("��c�ʿ� ���� Ÿ�� : "+DistrictWeapon);
								return;
							}
							if(Command.equalsIgnoreCase("null") == false)
							{
								if(isConsole == true)
									Bukkit.getConsoleSender().sendMessage(Command);
								else
									player.chat(Command);
							}

							if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == true)
							{
								if(Spell.equalsIgnoreCase("null") == false)
								{
									Object[] spells = MagicSpells.spells().toArray();
									Spell spell;
									boolean isExit = false;
									for(int count = 0; count < spells.length;count++)
									{
										spell = (Spell) spells[count];
										if(spell.getName().equals(Spell))
										{
											isExit = true;
											break;
										}
									}
									if(isExit == true)
									{
										otherplugins.SpellMain MS = new otherplugins.SpellMain();
										MS.CastSpell(player, Spell);
									}
									else
									{
										player.sendMessage("��c[��ų] : MagicSpells�÷����ο� �ش� ������ �������� �ʽ��ϴ�! �����ڿ��� �����ϼ���!");
										player.sendMessage("��c�������� �ʴ� ���� �̸� : ��e"+ Spell);
										player.sendMessage("��c�������� �ʴ� ������ ��ϵ� ��ų : ��e"+ Skillname +" "+PlayerSkillRank+"��ũ" );
										
										SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.9F);
									}
								}
							}
						}
						else
						{
							util.ETC ETC = new util.ETC();
							ETC.SlotChangedUpdatePlayerHPMP(player, player.getInventory().getItem(event.getNewSlot()));
							HotBarSound(player, (short) player.getInventory().getItem(event.getNewSlot()).getTypeId());
						}
					}
					else
					{
						util.ETC ETC = new util.ETC();
						ETC.SlotChangedUpdatePlayerHPMP(player, player.getInventory().getItem(event.getNewSlot()));
						HotBarSound(player, (short) player.getInventory().getItem(event.getNewSlot()).getTypeId());
					}
				}
				else
				{
					util.ETC ETC = new util.ETC();
					ETC.SlotChangedUpdatePlayerHPMP(player, player.getInventory().getItem(event.getNewSlot()));
					HotBarSound(player, (short) player.getInventory().getItem(event.getNewSlot()).getTypeId());
				}
			}
			else
			{
				util.ETC ETC = new util.ETC();
				ETC.SlotChangedUpdatePlayerHPMP(player, player.getInventory().getItem(event.getNewSlot()));
				HotBarSound(player, (short) player.getInventory().getItem(event.getNewSlot()).getTypeId());
			}
		}
		else
		{
			util.ETC ETC = new util.ETC();
			ItemStack item = new ItemStack(3);
			item.setItemMeta(null);
			ETC.SlotChangedUpdatePlayerHPMP(player, item);
			HotBarSound(player, (short) -1);
		}
		return;
	}
	
	public void HotBarSound(Player player,short itemID)
	{
		if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_HotBarSound())
		{
			
			if(itemID == -1)
				SoundEffect.SP(player, Sound.BLOCK_WOOD_BUTTON_CLICK_ON,0.8F, 0.5F);
			if(itemID >= 298&&itemID <= 317)
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR,0.9F, 0.5F);
			else if(itemID >= 290&&itemID <= 294)
	  			SoundEffect.SP(player, Sound.ITEM_HOE_TILL, 0.8F, 1.0F);
			else if(itemID == 46)
				SoundEffect.SP(player, Sound.ENTITY_TNT_PRIMED,1.5F, 0.8F);
			else if(itemID == 261)
	  			SoundEffect.SP(player, Sound.ENTITY_ARROW_HIT, 1.0F, 1.0F);
			else if(itemID == 259)
	  			SoundEffect.SP(player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.0F);
			else if(itemID == 256 || itemID == 269 || itemID == 273 || itemID == 277 || itemID == 284)
	  			SoundEffect.SP(player, Sound.ITEM_SHOVEL_FLATTEN, 0.8F, 1.0F);
			else if(itemID == 257 || itemID == 270 || itemID == 274 || itemID == 278 || itemID == 285)
	  			SoundEffect.SP(player, Sound.BLOCK_STONE_BREAK, 0.8F, 1.0F);
			else if(itemID == 258 || itemID == 271 || itemID == 275 || itemID == 279 || itemID == 286)
	  			SoundEffect.SP(player, Sound.BLOCK_WOOD_BREAK, 0.8F, 1.0F);
			else if(itemID == 267 || itemID == 268 || itemID == 272 || itemID == 276 || itemID == 283)
				SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR,1.0F, 2.0F);
			else if(itemID == 346)
				SoundEffect.SP(player, Sound.ENTITY_GENERIC_SWIM,1.5F, 1.0F);
			else if(itemID == 359)
				SoundEffect.SP(player, Sound.ENTITY_SHEEP_SHEAR,1.5F, 1.0F);
			else if(itemID == 368)
				SoundEffect.SP(player, Sound.ENTITY_ENDERMEN_TELEPORT,1.0F, 1.0F);
			else if(itemID == 373)
				SoundEffect.SP(player, Sound.ENTITY_GENERIC_DRINK,1.0F, 1.0F);
			else if(itemID == 374)
				SoundEffect.SP(player, Sound.ITEM_BOTTLE_FILL,1.0F, 1.0F);
			else if(itemID == 437)
				SoundEffect.SP(player, Sound.ITEM_BOTTLE_FILL_DRAGONBREATH,1.0F, 1.0F);
			else if(itemID == 438)
				SoundEffect.SP(player, Sound.ENTITY_SPLASH_POTION_BREAK,1.0F, 1.0F);
			else if(itemID == 441)
				SoundEffect.SP(player, Sound.ENTITY_WITCH_DRINK,1.0F, 1.0F);
			else
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP,0.8F, 1.0F);
		}
		return;
	}
}
