package customitem;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import effect.SoundEffect;
import main.Main_ServerOption;
import servertick.ServerTick_Main;
import util.YamlLoader;



public class UseableItem_Main
{
	public void UseAbleItemUse(Player player, String type)
	{
		ItemStack item = player.getInventory().getItemInMainHand();
		if(type.equals("��ȯ��"))
		{
			if(ServerTick_Main.PlayerTaskList.containsKey(player.getName())==true)
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				new effect.SendPacket().sendActionBar(player, "��c��l[���� �ڷ���Ʈ�� �� �� ���� �����Դϴ�!]");
				return;
			}
			util.ETC ETC = new util.ETC();
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_AttackTime() >= ETC.getSec())
			{
				player.sendMessage("��c[�̵� �Ұ�] : ��e"+((main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_AttackTime()+15000 - ETC.getSec())/1000)+"��c �� �Ŀ� �̵� �����մϴ�!");
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				return;
			}
			String world = "";
			int X = 0;
			short Y = 0;
			int Z = 0;
			for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
			{
				String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
				if(nowlore.contains(" : "))
				{
					if(nowlore.contains("����"))
						world = nowlore.split(" : ")[1];
					else if(nowlore.contains("X ��ǥ"))
						X = Integer.parseInt(nowlore.split(" : ")[1]);
					else if(nowlore.contains("Y ��ǥ"))
						Y = Short.parseShort(nowlore.split(" : ")[1]);
					else if(nowlore.contains("Z ��ǥ"))
						Z = Integer.parseInt(nowlore.split(" : ")[1]);
				}
			}
			if(item.getAmount() != 1)
			{
				item.setAmount(item.getAmount()-1);
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
			}
			else
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
			long UTC= servertick.ServerTick_Main.nowUTC-1;
			servertick.ServerTick_Object STSO = new servertick.ServerTick_Object(UTC, "P_UTS");
			Location loc = player.getLocation();
			STSO.setTick(UTC);//�ڷ���Ʈ ���� �ð�
			STSO.setCount(5);//�ڷ���Ʈ �ð�
			STSO.setString((byte)0, world+","+X+","+Y+","+Z);//�̵� ��ġ ����
			STSO.setString((byte)1, loc.getWorld().getName()+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ());//���� ��ġ ����
			STSO.setString((byte)2, player.getName());//�÷��̾� �̸� ����
			servertick.ServerTick_Main.Schedule.put(UTC, STSO);
			ServerTick_Main.PlayerTaskList.put(player.getName(), ""+UTC);
			new effect.PottionBuff().givePotionEffect(player, PotionEffectType.CONFUSION, 8, 255);
			SoundEffect.SP(player, Sound.BLOCK_CLOTH_BREAK, 0.7F, 0.5F);
			SoundEffect.SP(player, Sound.BLOCK_PORTAL_TRAVEL, 0.6F, 1.4F);
		}
		else if(type.equals("�ֹ���"))
		{
			if(item.getItemMeta().getDisplayName().equals("��2��3��4��3��3��l[���� �ʱ�ȭ �ֹ���]"))
			{
			  	YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System")==false)
				{
					if(item.getAmount() != 1)
					{
						item.setAmount(item.getAmount()-1);
						player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
					}
					else
						player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
					int TotalStatPoint = configYaml.getInt("DefaultStat.StatPoint")+main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_StatPoint();
					TotalStatPoint += main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR() - configYaml.getInt("DefaultStat.STR");
					TotalStatPoint += main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX() - configYaml.getInt("DefaultStat.DEX");
					TotalStatPoint += main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT() - configYaml.getInt("DefaultStat.INT");
					TotalStatPoint += main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL() - configYaml.getInt("DefaultStat.WILL");
					TotalStatPoint += main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK() - configYaml.getInt("DefaultStat.LUK");
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_STR(configYaml.getInt("DefaultStat.STR"));
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_DEX(configYaml.getInt("DefaultStat.DEX"));
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_INT(configYaml.getInt("DefaultStat.INT"));
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_WILL(configYaml.getInt("DefaultStat.WILL"));
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_LUK(configYaml.getInt("DefaultStat.LUK"));
					main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_StatPoint(TotalStatPoint);
					SoundEffect.SP(player, Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, 1.2F, 0.5F);
					player.sendMessage("��e��l[SYSTEM] : ������ �ʱ�ȭ�Ǿ����ϴ�!");
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c[System] : ������ ���丮 �ý����� ��츸 ��� �����մϴ�!");
				}
				return;
			}
			
			int StatPoint = 0;
			int SkillPoint = 0;
			int DEF = 0;
			int Protect = 0;
			int MaDEF = 0;
			int MaProtect  = 0;
			int Balance = 0;
			int Critical  = 0;
			int HP  = 0;
			int MP  = 0;
			int STR  = 0;
			int DEX  = 0;
			int INT  = 0;
			int WILL  = 0;
			int LUK  = 0;
			
			for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
			{
				String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
				if(nowlore.contains(" : "))
				{
					if(nowlore.contains("����Ʈ"))
					{
						if(nowlore.contains("����"))
							StatPoint = Integer.parseInt(nowlore.split(" : ")[1]);
						if(nowlore.contains("��ų"))
							SkillPoint = Integer.parseInt(nowlore.split(" : ")[1]);
					}
					if(nowlore.contains("���"))
						if(nowlore.contains("����"))
							MaDEF = Integer.parseInt(nowlore.split(" : ")[1]);
						else
							DEF = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("��ȣ"))
						if(nowlore.contains("����"))
							MaProtect = Integer.parseInt(nowlore.split(" : ")[1]);
						else
							Protect = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("�뷱��"))
						Balance = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("ũ��Ƽ��"))
						Critical = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("�����"))
						HP = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("����"))
						MP = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(Main_ServerOption.statSTR))
						STR = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(Main_ServerOption.statDEX))
						DEX = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(Main_ServerOption.statINT))
						INT = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(Main_ServerOption.statWILL))
						WILL = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(Main_ServerOption.statLUK))
						LUK = Integer.parseInt(nowlore.split(" : ")[1]);
				}
			}
			if(SkillPoint!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_SkillPoint(SkillPoint);
			if(StatPoint!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_StatPoint(StatPoint);
			if(DEF!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEF(DEF);
			if(Protect!=0)
			main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Protect(Protect);
			if(MaDEF!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_DEF(MaDEF);
			if(MaProtect!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_Protect(MaProtect);
			if(Balance!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Balance(Balance);
			if(Critical!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Critical(Critical);
			if(HP!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxHP(HP);
			if(MP!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxMP(MP);
			if(STR!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_STR(STR);
			if(DEX!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEX(DEX);
			if(INT!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_INT(INT);
			if(WILL!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_WILL(WILL);
			if(LUK!=0)
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_LUK(LUK);

			if(item.getAmount() != 1)
			{
				item.setAmount(item.getAmount()-1);
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
			}
			else
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
			
			if(SkillPoint>=0&&StatPoint>=0&&DEF>=0&&Protect>=0&&MaDEF>=0&&MaProtect>=0&&Balance>=0&&Critical>=0&&HP>0
			&&MP>=0&&STR>=0&&DEX>=0&&INT>=0&&WILL>=0&&LUK>0)
			{
				SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 0.5F);
				player.sendMessage("��a��l[      �ɷ�ġ�� ��� �Ͽ����ϴ�!      ]");
			}
			else if(SkillPoint<0&&StatPoint<0&&DEF<0&&Protect<0&&MaDEF<0&&MaProtect<0&&Balance<0&&Critical<0&&HP<0
					&&MP<0&&STR<0&&DEX<0&&INT<0&&WILL<0&&LUK<0)
			{
				SoundEffect.SP(player, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 0.8F, 0.5F);
				player.sendMessage("��c��l[      �ɷ�ġ�� ���� �Ͽ����ϴ�!      ]");
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.5F);
				player.sendMessage("��e��l[      �ɷ�ġ�� ��ȭ�� ������ϴ�!      ]");
			}
		}
		else if(type.equals("��ų��"))
		{
		  	YamlLoader configYaml = new YamlLoader();
			configYaml.getConfig("config.yml");
			if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System")==true)
			{
				String Skillname = null;
				for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
				{
					String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
					if(nowlore.contains("[��"))
					{
						if(nowlore.contains("Ŭ����"))
							if(nowlore.contains("�Ʒ�"))
								if(nowlore.contains("��ų"))
									if(nowlore.contains("ȹ��]"))
									{
										nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter+1));
										Skillname = nowlore.replace(" + ", "");
										break;
									}
					}
				}
				if(Skillname == null)
					return;
			  	YamlLoader skillYaml = new YamlLoader();
				skillYaml.getConfig("Skill/SkillList.yml");
				if(skillYaml.contains(Skillname))
				{
					skillYaml.getConfig("Skill/JobList.yml");
					if(skillYaml.contains("Mabinogi.Added."+Skillname)==true)
					{
					  	YamlLoader playerSkillYaml = new YamlLoader();
						playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
						if(playerSkillYaml.contains("Mabinogi."+skillYaml.getString("Mabinogi.Added."+Skillname)+"."+Skillname) == false)
						{
							playerSkillYaml.set("Mabinogi."+skillYaml.getString("Mabinogi.Added."+Skillname)+"."+Skillname, 1);
							playerSkillYaml.saveConfig();
							if(item.getAmount() != 1)
							{
								item.setAmount(item.getAmount()-1);
								player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
							}
							else
								player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
							SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
							player.sendMessage("��d��l[���ο� ��ų�� ȹ�� �Ͽ����ϴ�!] ��e��l"+ChatColor.UNDERLINE+Skillname);
							return;
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c��l[         ����� �̹� �ش� ��ų�� �˰� �ֽ��ϴ�!         ]");
							return;
						}
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c��l[�ش� ��ų�� ��� ī�װ����� �������� �ʽ��ϴ�! �����ڿ��� �����ϼ���!]");
						return;
					}
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c��l[������ �ش� ��ų�� �������� �ʽ��ϴ�! �����ڿ��� �����ϼ���!]");
					return;
				}
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c��l[   ���� �ý����� '������'�� ��츸 ��� �����մϴ�!   ]");
				return;
			}
		}
		else if(type.equals("�Һ�"))
		{
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
			}
			if(item.getAmount() != 1)
			{
				item.setAmount(item.getAmount()-1);
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
			}
			else
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
			
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
				if(main.Main_ServerOption.MagicSpellsCatched == true)
				{
					otherplugins.SpellMain MG = new otherplugins.SpellMain();
					MG.DrinkManaPotion(player, Mana);
					SoundEffect.SL(player.getLocation(), Sound.BLOCK_WATER_AMBIENT, 2.0F, 1.9F);
				}
			}
			if(Food > 0)
			{
				SoundEffect.SL(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 2.0F, 1.2F);
				if(player.getFoodLevel()+Food > 20)
					player.setFoodLevel(20);
				player.setFoodLevel(player.getFoodLevel()+Food);
			}
		}
		else if(type.equals("��"))
		{
			int money=Integer.parseInt(ChatColor.stripColor(item.getItemMeta().getLore().get(1).split(" ")[0]));
			if(main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + money <= 2000000000)
			{
				main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(money, 0, false);
				if(item.getAmount() != 1)
				{
					item.setAmount(item.getAmount()-1);
					player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
				}
				else
					player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
				SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.8F);
				player.sendMessage("��a[SYSTEM] : ��f��l"+money+" "+Main_ServerOption.money+"��a �Ա� �Ϸ�!");
				player.sendMessage("��7(���� "+main.Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()+ChatColor.stripColor(Main_ServerOption.money)+" ������)");
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[System] : "+Main_ServerOption.money+"��c ��(��) 2000000000(20��)�̻� ���� �� �����ϴ�!");
			}
		}
		return;
	}
}
