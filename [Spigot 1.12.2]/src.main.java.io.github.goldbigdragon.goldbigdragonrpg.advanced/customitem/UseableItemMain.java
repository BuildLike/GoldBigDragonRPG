package customitem;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import effect.SoundEffect;
import main.MainServerOption;
import servertick.ServerTickMain;
import util.YamlLoader;



public class UseableItemMain
{
	public void useAbleItemUse(Player player, String type)
	{
		ItemStack item = player.getInventory().getItemInMainHand();
		if(type.equals("��ȯ��"))
		{
			if(ServerTickMain.PlayerTaskList.containsKey(player.getName())==true)
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				new effect.SendPacket().sendActionBar(player, "��c��l[���� �ڷ���Ʈ�� �� �� ���� �����Դϴ�!]", false);
				return;
			}
			util.ETC etc = new util.ETC();
			if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_AttackTime() >= etc.getSec())
			{
				player.sendMessage("��c[�̵� �Ұ�] : ��e"+((main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_AttackTime()+15000 - etc.getSec())/1000)+"��c �� �Ŀ� �̵� �����մϴ�!");
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				return;
			}
			String world = "";
			int x = 0;
			short y = 0;
			int z = 0;
			for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
			{
				String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
				if(nowlore.contains(" : "))
				{
					if(nowlore.contains("����"))
						world = nowlore.split(" : ")[1];
					else if(nowlore.contains("X ��ǥ"))
						x = Integer.parseInt(nowlore.split(" : ")[1]);
					else if(nowlore.contains("Y ��ǥ"))
						y = Short.parseShort(nowlore.split(" : ")[1]);
					else if(nowlore.contains("Z ��ǥ"))
						z = Integer.parseInt(nowlore.split(" : ")[1]);
				}
			}
			if(item.getAmount() != 1)
			{
				item.setAmount(item.getAmount()-1);
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
			}
			else
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
			long utc= servertick.ServerTickMain.nowUTC-1;
			servertick.ServerTickObject STSO = new servertick.ServerTickObject(utc, "P_UTS");
			Location loc = player.getLocation();
			STSO.setTick(utc);//�ڷ���Ʈ ���� �ð�
			STSO.setCount(5);//�ڷ���Ʈ �ð�
			STSO.setString((byte)0, world+","+x+","+y+","+z);//�̵� ��ġ ����
			STSO.setString((byte)1, loc.getWorld().getName()+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ());//���� ��ġ ����
			STSO.setString((byte)2, player.getName());//�÷��̾� �̸� ����
			servertick.ServerTickMain.Schedule.put(utc, STSO);
			ServerTickMain.PlayerTaskList.put(player.getName(), ""+utc);
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
				if(!configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
				{
					if(item.getAmount() != 1)
					{
						item.setAmount(item.getAmount()-1);
						player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
					}
					else
						player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
					int totalStatPoint = configYaml.getInt("DefaultStat.StatPoint")+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_StatPoint();
					totalStatPoint += main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR() - configYaml.getInt("DefaultStat.STR");
					totalStatPoint += main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX() - configYaml.getInt("DefaultStat.DEX");
					totalStatPoint += main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT() - configYaml.getInt("DefaultStat.INT");
					totalStatPoint += main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL() - configYaml.getInt("DefaultStat.WILL");
					totalStatPoint += main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK() - configYaml.getInt("DefaultStat.LUK");
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_STR(configYaml.getInt("DefaultStat.STR"));
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_DEX(configYaml.getInt("DefaultStat.DEX"));
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_INT(configYaml.getInt("DefaultStat.INT"));
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_WILL(configYaml.getInt("DefaultStat.WILL"));
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_LUK(configYaml.getInt("DefaultStat.LUK"));
					main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_StatPoint(totalStatPoint);
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
			
			int statPoint = 0;
			int skillPoint = 0;
			int def = 0;
			int protect = 0;
			int magicDef = 0;
			int magicProtect  = 0;
			int balance = 0;
			int critical  = 0;
			int hp  = 0;
			int mp  = 0;
			int str  = 0;
			int dex  = 0;
			int INT  = 0;
			int will  = 0;
			int luk  = 0;
			
			for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
			{
				String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
				if(nowlore.contains(" : "))
				{
					if(nowlore.contains("����Ʈ"))
					{
						if(nowlore.contains("����"))
							statPoint = Integer.parseInt(nowlore.split(" : ")[1]);
						if(nowlore.contains("��ų"))
							skillPoint = Integer.parseInt(nowlore.split(" : ")[1]);
					}
					if(nowlore.contains("���"))
						if(nowlore.contains("����"))
							magicDef = Integer.parseInt(nowlore.split(" : ")[1]);
						else
							def = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("��ȣ"))
						if(nowlore.contains("����"))
							magicProtect = Integer.parseInt(nowlore.split(" : ")[1]);
						else
							protect = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("�뷱��"))
						balance = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("ũ��Ƽ��"))
						critical = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("�����"))
						hp = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains("����"))
						mp = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(MainServerOption.statSTR))
						str = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(MainServerOption.statDEX))
						dex = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(MainServerOption.statINT))
						INT = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(MainServerOption.statWILL))
						will = Integer.parseInt(nowlore.split(" : ")[1]);
					if(nowlore.contains(MainServerOption.statLUK))
						luk = Integer.parseInt(nowlore.split(" : ")[1]);
				}
			}
			if(skillPoint!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_SkillPoint(skillPoint);
			if(statPoint!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_StatPoint(statPoint);
			if(def!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEF(def);
			if(protect!=0)
			main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Protect(protect);
			if(magicDef!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_DEF(magicDef);
			if(magicProtect!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_Protect(magicProtect);
			if(balance!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Balance(balance);
			if(critical!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Critical(critical);
			if(hp!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxHP(hp);
			if(mp!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxMP(mp);
			if(str!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_STR(str);
			if(dex!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEX(dex);
			if(INT!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_INT(INT);
			if(will!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_WILL(will);
			if(luk!=0)
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_LUK(luk);

			if(item.getAmount() != 1)
			{
				item.setAmount(item.getAmount()-1);
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
			}
			else
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
			
			if(skillPoint>=0&&statPoint>=0&&def>=0&&protect>=0&&magicDef>=0&&magicProtect>=0&&balance>=0&&critical>=0&&hp>0
			&&mp>=0&&str>=0&&dex>=0&&INT>=0&&will>=0&&luk>0)
			{
				SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 0.5F);
				player.sendMessage("��a��l[      �ɷ�ġ�� ��� �Ͽ����ϴ�!      ]");
			}
			else if(skillPoint<0&&statPoint<0&&def<0&&protect<0&&magicDef<0&&magicProtect<0&&balance<0&&critical<0&&hp<0
					&&mp<0&&str<0&&dex<0&&INT<0&&will<0&&luk<0)
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
			if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
			{
				String skillName = null;
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
										skillName = nowlore.replace(" + ", "");
										break;
									}
					}
				}
				if(skillName == null)
					return;
			  	YamlLoader skillYaml = new YamlLoader();
				skillYaml.getConfig("Skill/SkillList.yml");
				if(skillYaml.contains(skillName))
				{
					skillYaml.getConfig("Skill/JobList.yml");
					if(skillYaml.contains("Mabinogi.Added."+skillName))
					{
					  	YamlLoader playerSkillYaml = new YamlLoader();
						playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
						if(!playerSkillYaml.contains("Mabinogi."+skillYaml.getString("Mabinogi.Added."+skillName)+"."+skillName))
						{
							playerSkillYaml.set("Mabinogi."+skillYaml.getString("Mabinogi.Added."+skillName)+"."+skillName, 1);
							playerSkillYaml.saveConfig();
							if(item.getAmount() != 1)
							{
								item.setAmount(item.getAmount()-1);
								player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
							}
							else
								player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
							SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
							player.sendMessage("��d��l[���ο� ��ų�� ȹ�� �Ͽ����ϴ�!] ��e��l"+ChatColor.UNDERLINE+skillName);
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
			int health = 0;
			int mana = 0;
			int food = 0;
			for(int counter = 0; counter < item.getItemMeta().getLore().size();counter++)
			{
				String nowlore=ChatColor.stripColor(item.getItemMeta().getLore().get(counter));
				if(nowlore.contains(" : "))
				{
					if(nowlore.contains("�����"))
						health = Integer.parseInt(nowlore.split(" : ")[1]);
					else if(nowlore.contains("����"))
						mana = Integer.parseInt(nowlore.split(" : ")[1]);
					else if(nowlore.contains("������"))
						food = Integer.parseInt(nowlore.split(" : ")[1]);
				}
			}
			if(item.getAmount() != 1)
			{
				item.setAmount(item.getAmount()-1);
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
			}
			else
				player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
			
			if(health > 0)
			{
				SoundEffect.SL(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 2.0F, 0.8F);
				Damageable damageable = player;
				if(damageable.getMaxHealth() < damageable.getHealth()+health)
					damageable.setHealth(damageable.getMaxHealth());
				else
					damageable.setHealth(damageable.getHealth() + health);
			}
			if(mana >0)
			{
				if(main.MainServerOption.MagicSpellsCatched)
				{
					otherplugins.SpellMain spellMain = new otherplugins.SpellMain();
					spellMain.DrinkManaPotion(player, mana);
					SoundEffect.SL(player.getLocation(), Sound.BLOCK_WATER_AMBIENT, 2.0F, 1.9F);
				}
			}
			if(food > 0)
			{
				SoundEffect.SL(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 2.0F, 1.2F);
				if(player.getFoodLevel()+food > 20)
					player.setFoodLevel(20);
				player.setFoodLevel(player.getFoodLevel()+food);
			}
		}
		else if(type.equals("��"))
		{
			int money=Integer.parseInt(ChatColor.stripColor(item.getItemMeta().getLore().get(1).split(" ")[0]));
			if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + money <= 2000000000)
			{
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(money, 0, false);
				if(item.getAmount() != 1)
				{
					item.setAmount(item.getAmount()-1);
					player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
				}
				else
					player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(0));
				SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.8F);
				player.sendMessage("��a[SYSTEM] : ��f��l"+money+" "+MainServerOption.money+"��a �Ա� �Ϸ�!");
				player.sendMessage("��7(���� "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()+ChatColor.stripColor(MainServerOption.money)+" ������)");
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[System] : "+MainServerOption.money+"��c ��(��) 2000000000(20��)�̻� ���� �� �����ϴ�!");
			}
		}
		return;
	}
}
