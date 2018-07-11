package monster;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffectType;

import effect.PottionBuff;
import effect.SoundEffect;
import monster.ai.*;
import util.NumericUtil;
import util.YamlLoader;

public class MonsterSpawn
{
	public void entitySpawn(CreatureSpawnEvent event)
	{
		if(event.getLocation().getWorld().getName().equals("Dungeon"))
		{
			if(event.getSpawnReason() == SpawnReason.NATURAL || event.getSpawnReason() == SpawnReason.CHUNK_GEN
					|| event.getSpawnReason() == SpawnReason.MOUNT|| event.getSpawnReason() == SpawnReason.JOCKEY
					|| event.getSpawnReason() == SpawnReason.SLIME_SPLIT)
			{
				event.setCancelled(true);
				return;
			}
		}
		
		if(event.getEntity().getType()==EntityType.ARMOR_STAND)
			return;
		
		if(event.getSpawnReason() == SpawnReason.SLIME_SPLIT &&
				event.getEntity().getCustomName() != null &&
				main.MainServerOption.MonsterNameMatching.containsKey(event.getEntity().getCustomName()))
			event.setCancelled(true);
		
		area.AreaAPI areaApi = new area.AreaAPI();
		String[] areaNameArrays = areaApi.getAreaName(event.getEntity());
		
		YamlLoader areaYaml = new YamlLoader();
		if(areaNameArrays != null)
		{
			if( ! areaApi.getAreaOption(areaNameArrays[0], (char) 3))
			{
				event.setCancelled(true);
				return;
			}
			else if( ! areaApi.getAreaOption(areaNameArrays[0], (char) 8))
			{
				areaYaml.getConfig("Area/AreaList.yml");
				String areaName = areaApi.getAreaName(event.getEntity())[0];
				String[] mobNameArrays = areaYaml.getConfigurationSection(areaName+".Monster").getKeys(false).toArray(new String[0]);
				int randomMob = 0;
				NumericUtil nu = new NumericUtil();
				for(int count = 0; count < 10; count++)
				{
					if(mobNameArrays.length != 0)
					{
						randomMob = nu.RandomNum(0, mobNameArrays.length-1);
						if(main.MainServerOption.MonsterList.containsKey(mobNameArrays[randomMob]))
						{
							new monster.MonsterSpawn().spawnMonster(event.getLocation(), mobNameArrays[randomMob], (byte) -1, null,(char) -1, false);
							break;
						}
						else
						{
							areaYaml.removeKey(areaName+".Monster."+mobNameArrays[randomMob]);
							areaYaml.saveConfig();
						}
					}
					else
						break;
				}
			}
		}
		YamlLoader configYaml = new YamlLoader();
	    configYaml.getConfig("config.yml");
		new monster.MonsterSpawn().SpawnEffect(event.getEntity(),event.getLocation(), (byte) configYaml.getInt("Server.MonsterSpawnEffect"));
		return;
	}
	
	public void createMonster(String monsterName)
	{
		ItemStack item = new ItemStack(Material.AIR);
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		
		monsterYaml.set(monsterName+".Name", monsterName);
		monsterYaml.set(monsterName+".Type", "����");
		monsterYaml.set(monsterName+".AI", "�Ϲ� �ൿ");
		monsterYaml.set(monsterName+".EXP", 15);
		monsterYaml.set(monsterName+".Biome", "NULL");
		monsterYaml.set(monsterName+".HP", 20);
		monsterYaml.set(monsterName+".MIN_Money", 1);
		monsterYaml.set(monsterName+".MAX_Money", 10);
		monsterYaml.set(monsterName+".STR", 10);
		monsterYaml.set(monsterName+".DEX", 10);
		monsterYaml.set(monsterName+".INT", 10);
		monsterYaml.set(monsterName+".WILL", 10);
		monsterYaml.set(monsterName+".LUK", 10);
		monsterYaml.set(monsterName+".DEF", 0);
		monsterYaml.set(monsterName+".Protect", 0);
		monsterYaml.set(monsterName+".Magic_DEF", 0);
		monsterYaml.set(monsterName+".Magic_Protect", 0);
		monsterYaml.set(monsterName+".Head.DropChance", 0);
		monsterYaml.set(monsterName+".Head.Item", item);
		monsterYaml.set(monsterName+".Chest.DropChance", 0);
		monsterYaml.set(monsterName+".Chest.Item", item);
		monsterYaml.set(monsterName+".Leggings.DropChance", 0);
		monsterYaml.set(monsterName+".Leggings.Item", item);
		monsterYaml.set(monsterName+".Boots.DropChance", 0);
		monsterYaml.set(monsterName+".Boots.Item", item);
		monsterYaml.set(monsterName+".Hand.DropChance", 0);
		monsterYaml.set(monsterName+".Hand.Item", item);
		monsterYaml.set(monsterName+".OffHand.DropChance", 0);
		monsterYaml.set(monsterName+".OffHand.Item", item);
		monsterYaml.saveConfig();
		return;
	}

	public void stayLive(Entity e, boolean isStayLive)
	{
		if(e.getType()!=EntityType.ENDER_CRYSTAL && !e.isDead())
		{
			LivingEntity le = (LivingEntity) e;
			le.setRemoveWhenFarAway(isStayLive);
		}
	}
	
	public void spawnMonster(Location loc, String monsterName, byte dungeonSpawning, int[] xyzLoc, char group, boolean isStayLive)
	{
		if(monsterName.charAt(0)=='��')
		{
			String type = monsterName.substring(1);
			monsterName = null;
			createCreature(type, loc, monsterName, dungeonSpawning, xyzLoc, group, isStayLive);
		}
		loc.add(0.5, 1, 0.5);
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		String type = monsterYaml.getString(monsterName + ".Type");
		if(monsterYaml.getString(monsterName+".Name")!=null)
		{
			String aiString = monsterYaml.getString(monsterName + ".AI");
			int ai = 0;
			
			if(aiString.equals("������"))
				ai = 3;
			else if(aiString.equals("����"))
				ai = 4;
			else if(aiString.equals("����"))
				ai = 1;
			else if(aiString.equals("�񼱰�"))
				ai = 2;
			
			if(!(aiString.equals("����")||aiString.equals("�񼱰�")||aiString.equals("������")||aiString.equals("����")||aiString.equals("�Ϲ� �ൿ")))
			{
				monsterYaml.set(monsterName + ".AI", "�Ϲ� �ൿ");
				monsterYaml.saveConfig();
			}
			if(!aiString.equals("�Ϲ� �ൿ"))
			{
				LivingEntity entity = null;
				if(type.equals("����"))
					entity = (CraftZombie) CustomZombie.spawn(loc, ai);
				else if(type.equals("���̾�Ʈ"))
					entity = (CraftGiant) CustomGiant.spawn(loc, ai);
				else if(type.equals("���̷���"))
					entity = (CraftSkeleton) CustomSkeleton.spawn(loc, ai);
				else if(type.equals("�״����̷���"))
					entity = (CraftWitherSkeleton) CustomWitherSkeleton.spawn(loc, ai);
				else if(type.equals("������"))
					entity = (CraftEnderman) CustomEnderman.spawn(loc, ai);
				else if(type.equals("ũ����")||type.equals("����ũ����"))
					entity = (CraftCreeper) CustomCreeper.spawn(loc, ai);
				else if(type.equals("�Ź�"))
					entity = (CraftSpider) CustomSpider.spawn(loc, ai);
				else if(type.equals("�����Ź�"))
					entity = (CraftCaveSpider) CustomCaveSpider.spawn(loc, ai);
				else if(type.equals("������"))
					entity = (CraftSilverfish) CustomSilverFish.spawn(loc, ai);
				else if(type.equals("���������"))
					entity = (CraftEndermite) CustomEnderMite.spawn(loc, ai);
				else if(type.equals("������"))
					entity = (CraftBlaze) CustomBlaze.spawn(loc, ai);
				else if(type.equals("�����Ǳ׸�"))
					entity = (CraftPigZombie) CustomPigZombie.spawn(loc, ai);
				else if(type.equals("����"))
					entity = (CraftWitch) CustomWitch.spawn(loc, ai);
				else if(type.equals("����"))
					entity = (CraftWither) CustomWither.spawn(loc, ai);
				else if(type.equals("��ȣ��"))
					entity = (CraftGuardian) CustomGuardian.spawn(loc, ai);
				else if(type.equals("�ϱذ�"))
					entity = (CraftPolarBear) CustomPolarBear.spawn(loc, ai);
				else if(type.equals("����ũ����Ż"))
					createCreature(type, loc, monsterName, dungeonSpawning, xyzLoc, group, isStayLive);

				else if (type.equals("���������"))
					entity = (CraftElderGuardian) CustomElderGuardian.spawn(loc, ai);
				else if (type.equals("��Ʈ����"))
					entity = (CraftStray) CustomStray.spawn(loc, ai);
				else if (type.equals("�㽺ũ"))
					entity = (CraftHusk) CustomHusk.spawn(loc, ai);
				else if (type.equals("�ֹ�����"))
					entity = (CraftVillagerZombie) CustomZombieVillager.spawn(loc, ai);
				else if (type.equals("��ȯ��"))
					entity = (CraftEvoker) CustomEvoker.spawn(loc, ai);
				else if (type.equals("����"))
					entity = (CraftVex) CustomVex.spawn(loc, ai);
				else if (type.equals("������"))
					entity = (CraftVindicator) CustomVindicator.spawn(loc, ai);
				else if (type.equals("��Ŀ"))
					entity = (Shulker) CustomShulker.spawn(loc, ai);
				// ���� ������ ��
				else if (type.equals("���̷��渻"))
					entity = (LivingEntity) CustomSkeletonHorse.spawn(loc, ai);
				else if (type.equals("����"))
					entity = (LivingEntity) CustomZombieHorse.spawn(loc, ai);
				else if (type.equals("�糪��"))
					entity = (LivingEntity) CustomDonkey.spawn(loc, ai);
				else if (type.equals("���"))
					entity = (LivingEntity) CustomMule.spawn(loc, ai);
				else if (type.equals("�ΰ�"))
					entity = (LivingEntity) CustomHuman.spawn(loc, ai);
				else if (type.equals("����"))
					entity = (LivingEntity) CustomBat.spawn(loc, ai);
				else if (type.equals("����"))
					entity = (LivingEntity) CustomPig.spawn(loc, ai);
				else if (type.equals("��"))
					entity = (LivingEntity) CustomSheep.spawn(loc, ai);
				else if (type.equals("��"))
					entity = (LivingEntity) CustomCow.spawn(loc, ai);
				else if (type.equals("��"))
					entity = (LivingEntity) CustomChicken.spawn(loc, ai);
				else if (type.equals("��¡��"))
					entity = (LivingEntity) CustomSquid.spawn(loc, ai);
				else if (type.equals("����"))
					entity = (LivingEntity) CustomWolf.spawn(loc, ai);
				else if (type.equals("������"))
					entity = (LivingEntity) CustomMooshroom.spawn(loc, ai);
				else if (type.equals("�����"))
					entity = (LivingEntity) CustomSnowman.spawn(loc, ai);
				else if (type.equals("������"))
					entity = (LivingEntity) CustomOcelot.spawn(loc, ai);
				else if (type.equals("ö��"))
					entity = (LivingEntity) CustomIronGolem.spawn(loc, ai);
				else if (type.equals("��"))
					entity = (LivingEntity) CustomHorse.spawn(loc, ai);
				else if (type.equals("�䳢"))
					entity = (LivingEntity) CustomRabbit.spawn(loc, ai);
				else if (type.equals("��"))
					entity = (LivingEntity) CustomLlama.spawn(loc, ai);
				else if (type.equals("�ֹ�"))
					entity = (LivingEntity) CustomVillager.spawn(loc, ai);

				if(entity!=null)
					entity = getEntity(entity, monsterName, dungeonSpawning, xyzLoc, group);
			}
			else
				createCreature(type, loc, monsterName, dungeonSpawning, xyzLoc, group, isStayLive);
		}
		return;
	}
	
	private void createCreature(String type, Location loc, String mob, byte dungeonSpawning, int[] xyzLoc, char group, boolean isStayLive)
	{
		switch(type)
		{
			case "����":{Zombie zombie = (Zombie) loc.getWorld().spawn(loc, Zombie.class);zombie = (Zombie) getEntity(zombie,mob, dungeonSpawning, xyzLoc, group);}break;
			case "���̾�Ʈ":{Giant giant = (Giant) loc.getWorld().spawn(loc, Giant.class);giant = (Giant) getEntity(giant,mob, dungeonSpawning, xyzLoc, group);stayLive(giant, isStayLive);}break;
			case "���̷���":{Skeleton skeleton = (Skeleton) loc.getWorld().spawn(loc, Skeleton.class);skeleton = (Skeleton) getEntity(skeleton,mob, dungeonSpawning, xyzLoc, group);stayLive(skeleton, isStayLive);}break;
			case "�״����̷���":{WitherSkeleton skeleton = (WitherSkeleton) loc.getWorld().spawn(loc, WitherSkeleton.class);skeleton = (WitherSkeleton) getEntity(skeleton,mob, dungeonSpawning, xyzLoc, group);stayLive(skeleton, isStayLive);}break;
			case "������":{Enderman enderman = (Enderman) loc.getWorld().spawn(loc, Enderman.class);enderman = (Enderman) getEntity(enderman,mob, dungeonSpawning, xyzLoc, group);stayLive(enderman, isStayLive);}break;
			case "ũ����":{Creeper creeper = (Creeper) loc.getWorld().spawn(loc, Creeper.class);creeper = (Creeper) getEntity(creeper,mob, dungeonSpawning, xyzLoc, group);stayLive(creeper, isStayLive);}break;
			case "����ũ����":{Creeper Lcreeper = (Creeper) loc.getWorld().spawn(loc, Creeper.class);Lcreeper = (Creeper) getEntity(Lcreeper,mob, dungeonSpawning, xyzLoc, group);stayLive(Lcreeper, isStayLive);}break;
			case "�Ź�":{Spider spider = (Spider) loc.getWorld().spawn(loc, Spider.class);spider = (Spider) getEntity(spider,mob, dungeonSpawning, xyzLoc, group);stayLive(spider, isStayLive);}break;
			case "�����Ź�":{CaveSpider cavespider = (CaveSpider) loc.getWorld().spawn(loc, CaveSpider.class);cavespider = (CaveSpider) getEntity(cavespider,mob, dungeonSpawning, xyzLoc, group);stayLive(cavespider, isStayLive);}break;
			case "������":{Silverfish silverfish = (Silverfish) loc.getWorld().spawn(loc, Silverfish.class);silverfish = (Silverfish) getEntity(silverfish,mob, dungeonSpawning, xyzLoc, group);stayLive(silverfish, isStayLive);}break;
			case "���������":{Endermite endermite = (Endermite) loc.getWorld().spawn(loc, Endermite.class);endermite = (Endermite) getEntity(endermite,mob, dungeonSpawning, xyzLoc, group);stayLive(endermite, isStayLive);}break;
			case "������":case "�ʴ���������": case "Ư�뽽����": case "ū������": case "���뽽����": case "����������":{Slime Sslime = (Slime) loc.getWorld().spawn(loc, Slime.class);Sslime = (Slime) getEntity(Sslime,mob, dungeonSpawning, xyzLoc, group);stayLive(Sslime, isStayLive);}break;
			case "���׸�ť��": case "�ʴ������׸�ť��":case "Ư�븶�׸�ť��": case "ū���׸�ť��": case "���븶�׸�ť��": case "�������׸�ť��":{MagmaCube Smagmacube = (MagmaCube) loc.getWorld().spawn(loc, MagmaCube.class);Smagmacube = (MagmaCube) getEntity(Smagmacube,mob, dungeonSpawning, xyzLoc, group);stayLive(Smagmacube, isStayLive);}break;
			case "������":{Blaze blaze = (Blaze) loc.getWorld().spawn(loc, Blaze.class);blaze = (Blaze) getEntity(blaze,mob, dungeonSpawning, xyzLoc, group);stayLive(blaze, isStayLive);}break;
			case "����Ʈ":{Ghast ghast = (Ghast) loc.getWorld().spawn(loc, Ghast.class);ghast = (Ghast) getEntity(ghast,mob, dungeonSpawning, xyzLoc, group);stayLive(ghast, isStayLive);}break;
			case "�����Ǳ׸�":{PigZombie pigzombie = (PigZombie) loc.getWorld().spawn(loc, PigZombie.class);pigzombie = (PigZombie) getEntity(pigzombie,mob, dungeonSpawning, xyzLoc, group);stayLive(pigzombie, isStayLive);}break;
			case "����":{Witch witch = (Witch) loc.getWorld().spawn(loc, Witch.class);witch = (Witch) getEntity(witch,mob, dungeonSpawning, xyzLoc, group);stayLive(witch, isStayLive);}break;
			case "����":{Wither wither = (Wither) loc.getWorld().spawn(loc, Wither.class);wither = (Wither) getEntity(wither,mob, dungeonSpawning, xyzLoc, group);stayLive(wither, isStayLive);}break;
			case "�����巡��":{EnderDragon ED = (EnderDragon) loc.getWorld().spawn(loc, EnderDragon.class);ED = (EnderDragon) getEntity(ED,mob, dungeonSpawning, xyzLoc, group);stayLive(ED, isStayLive);}break;
			case "����ũ����Ż":{EnderCrystal EC = (EnderCrystal) loc.getWorld().spawn(loc, EnderCrystal.class);EC = getEnderCrystal(EC,mob, dungeonSpawning, xyzLoc, group);stayLive(EC, isStayLive);}break;
			case "��ȣ��":{Guardian guardian = (Guardian) loc.getWorld().spawn(loc, Guardian.class);guardian = (Guardian) getEntity(guardian,mob, dungeonSpawning, xyzLoc, group);stayLive(guardian, isStayLive);}break;
			case "��":{Sheep sheep = (Sheep) loc.getWorld().spawn(loc, Sheep.class);sheep = (Sheep) getEntity(sheep,mob, dungeonSpawning, xyzLoc, group);stayLive(sheep, isStayLive);}break;
			case "��":{Cow cow = (Cow) loc.getWorld().spawn(loc, Cow.class);cow = (Cow) getEntity(cow,mob, dungeonSpawning, xyzLoc, group);stayLive(cow, isStayLive);}break;
			case "����":{Pig pig = (Pig) loc.getWorld().spawn(loc, Pig.class);pig = (Pig) getEntity(pig,mob, dungeonSpawning, xyzLoc, group);stayLive(pig, isStayLive);}break;
			case "��":{Horse horse = (Horse) loc.getWorld().spawn(loc, Horse.class);horse = (Horse) getEntity(horse,mob, dungeonSpawning, xyzLoc, group);stayLive(horse, isStayLive);}break;
			case "�䳢":{Rabbit rabbit = (Rabbit) loc.getWorld().spawn(loc, Rabbit.class);rabbit = (Rabbit) getEntity(rabbit,mob, dungeonSpawning, xyzLoc, group);stayLive(rabbit, isStayLive);}break;
			case "������":{Ocelot oceleot = (Ocelot) loc.getWorld().spawn(loc, Ocelot.class);oceleot = (Ocelot) getEntity(oceleot,mob, dungeonSpawning, xyzLoc, group);stayLive(oceleot, isStayLive);}break;
			case "����":{Wolf wolf = (Wolf) loc.getWorld().spawn(loc, Wolf.class);wolf = (Wolf) getEntity(wolf,mob, dungeonSpawning, xyzLoc, group);stayLive(wolf, isStayLive);}break;
			case "��":{Chicken chicken = (Chicken) loc.getWorld().spawn(loc, Chicken.class);chicken = (Chicken) getEntity(chicken,mob, dungeonSpawning, xyzLoc, group);stayLive(chicken, isStayLive);}break;
			case "������":{MushroomCow Mcow = (MushroomCow) loc.getWorld().spawn(loc, MushroomCow.class);Mcow = (MushroomCow) getEntity(Mcow,mob, dungeonSpawning, xyzLoc, group);stayLive(Mcow, isStayLive);}break;
			case "����":{Bat bat = (Bat) loc.getWorld().spawn(loc, Bat.class);bat = (Bat) getEntity(bat,mob, dungeonSpawning, xyzLoc, group);stayLive(bat, isStayLive);}break;
			case "��¡��":{Squid squid = (Squid) loc.getWorld().spawn(loc, Squid.class);squid = (Squid) getEntity(squid,mob, dungeonSpawning, xyzLoc, group);stayLive(squid, isStayLive);}break;
			case "�ֹ�":{Villager villager = (Villager) loc.getWorld().spawn(loc, Villager.class);villager = (Villager) getEntity(villager,mob, dungeonSpawning, xyzLoc, group);stayLive(villager, isStayLive);}break;
			case "�����":{Snowman snowman = (Snowman) loc.getWorld().spawn(loc, Snowman.class);snowman = (Snowman) getEntity(snowman,mob, dungeonSpawning, xyzLoc, group);stayLive(snowman, isStayLive);}break;
			case "ö��":{IronGolem golem = (IronGolem) loc.getWorld().spawn(loc, IronGolem.class);golem = (IronGolem) getEntity(golem,mob, dungeonSpawning, xyzLoc, group);stayLive(golem, isStayLive);}break;
			case "��Ŀ":{Shulker shulker = (Shulker) loc.getWorld().spawn(loc, Shulker.class);shulker = (Shulker) getEntity(shulker,mob, dungeonSpawning, xyzLoc, group);stayLive(shulker, isStayLive);}break;
			case "�ϱذ�":{PolarBear polarBear = (PolarBear) loc.getWorld().spawn(loc, PolarBear.class);polarBear = (PolarBear) getEntity(polarBear,mob, dungeonSpawning, xyzLoc, group);stayLive(polarBear, isStayLive);}break;
			
			case "���������":{ElderGuardian elderGuardian = (ElderGuardian) loc.getWorld().spawn(loc, ElderGuardian.class);elderGuardian = (ElderGuardian) getEntity(elderGuardian,mob, dungeonSpawning, xyzLoc, group);stayLive(elderGuardian, isStayLive);}break;
			case "��Ʈ����":{Stray stray = (Stray) loc.getWorld().spawn(loc, Stray.class);stray = (Stray) getEntity(stray,mob, dungeonSpawning, xyzLoc, group);stayLive(stray, isStayLive);}break;
			case "�㽺ũ":{Husk husk = (Husk) loc.getWorld().spawn(loc, Husk.class);husk = (Husk) getEntity(husk,mob, dungeonSpawning, xyzLoc, group);stayLive(husk, isStayLive);}break;
			case "�ֹ�����":{ZombieVillager zombiVillager = (ZombieVillager) loc.getWorld().spawn(loc, ZombieVillager.class);zombiVillager = (ZombieVillager) getEntity(zombiVillager,mob, dungeonSpawning, xyzLoc, group);stayLive(zombiVillager, isStayLive);}break;
			case "��ȯ��":{Evoker evoker = (Evoker) loc.getWorld().spawn(loc, Evoker.class);evoker = (Evoker) getEntity(evoker,mob, dungeonSpawning, xyzLoc, group);stayLive(evoker, isStayLive);}break;
			case "����":{Vex vex = (Vex) loc.getWorld().spawn(loc, Vex.class);vex = (Vex) getEntity(vex,mob, dungeonSpawning, xyzLoc, group);stayLive(vex, isStayLive);}break;
			case "������":{Vindicator polarBear = (Vindicator) loc.getWorld().spawn(loc, Vindicator.class);polarBear = (Vindicator) getEntity(polarBear,mob, dungeonSpawning, xyzLoc, group);stayLive(polarBear, isStayLive);}break;
			case "���̷��渻":{SkeletonHorse skeletonHorse = (SkeletonHorse) loc.getWorld().spawn(loc, SkeletonHorse.class);skeletonHorse = (SkeletonHorse) getEntity(skeletonHorse,mob, dungeonSpawning, xyzLoc, group);stayLive(skeletonHorse, isStayLive);}break;
			case "����":{ZombieHorse zombieHorse = (ZombieHorse) loc.getWorld().spawn(loc, ZombieHorse.class);zombieHorse = (ZombieHorse) getEntity(zombieHorse,mob, dungeonSpawning, xyzLoc, group);stayLive(zombieHorse, isStayLive);}break;
			case "�糪��":{Donkey donkey = (Donkey) loc.getWorld().spawn(loc, Donkey.class);donkey = (Donkey) getEntity(donkey,mob, dungeonSpawning, xyzLoc, group);stayLive(donkey, isStayLive);}break;
			case "���":{Mule mule = (Mule) loc.getWorld().spawn(loc, Mule.class);mule = (Mule) getEntity(mule,mob, dungeonSpawning, xyzLoc, group);stayLive(mule, isStayLive);}break;
			case "�ΰ�":{Player player = (Player) loc.getWorld().spawn(loc, Player.class);player = (Player) getEntity(player,mob, dungeonSpawning, xyzLoc, group);stayLive(player, isStayLive);}break;
			case "��":{Llama llama = (Llama) loc.getWorld().spawn(loc, Llama.class);llama = (Llama) getEntity(llama,mob, dungeonSpawning, xyzLoc, group);stayLive(llama, isStayLive);}break;
			//case "�޸�":{HumanEntity human = (HumanEntity) loc.getWorld().spawn(loc, Player.class);human = (HumanEntity) getEntity(human,mob, DungeonSpawning, XYZLoc, Group);StayLive(human, isStayLive);}break;
		}
	}

	private LivingEntity getEntity(LivingEntity monster, String monsterName, byte dungeonSpawning, int[] xyzLoc, char group)
	{
		if(monsterName!=null)
		{
			YamlLoader monsterYaml = new YamlLoader();
			monsterYaml.getConfig("Monster/MonsterList.yml");
			monster.setCustomName(monsterYaml.getString(monsterName + ".Name").replace("&", "��"));
			monster.setCustomNameVisible(true);
			String[] parts = {"Head", "Chest", "Leggings", "Boots", "Hand", "OffHand"};
			ItemStack item = null;
			ItemStack[] equipments = new ItemStack[parts.length];
			for(int count = 0; count < parts.length; count++)
			{
				item = monsterYaml.getItemStack(monsterName+"." + parts[count] + ".Item");
				if(item != null && ! (item.hasItemMeta() && item.getItemMeta().hasLore() &&
						ChatColor.stripColor(item.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���.")))
					equipments[count] = item;
				else
					equipments[count] = null;
			}
			monster.getEquipment().setHelmet(equipments[0]);
			monster.getEquipment().setChestplate(equipments[1]);
			monster.getEquipment().setLeggings(equipments[2]);
			monster.getEquipment().setBoots(equipments[3]);
			monster.getEquipment().setItemInMainHand(equipments[4]);
			monster.getEquipment().setItemInOffHand(equipments[5]);
			
			YamlLoader configYaml = new YamlLoader();
		    configYaml.getConfig("config.yml");
			monster.getEquipment().setHelmetDropChance((float)(monsterYaml.getInt(monsterName+".Head.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			monster.getEquipment().setChestplateDropChance((float)(monsterYaml.getInt(monsterName+".Chest.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			monster.getEquipment().setLeggingsDropChance((float)(monsterYaml.getInt(monsterName+".Leggings.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			monster.getEquipment().setBootsDropChance((float)(monsterYaml.getInt(monsterName+".Boots.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			monster.getEquipment().setItemInMainHandDropChance((float)(monsterYaml.getInt(monsterName+".Hand.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			monster.getEquipment().setItemInOffHandDropChance((float)(monsterYaml.getInt(monsterName+".OffHand.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			
			if(monster.getType() == EntityType.CREEPER)
			{
				if(monsterYaml.getString(monsterName+".Type").equalsIgnoreCase("����ũ����"))
					  ((Creeper) monster).setPowered(true);
			}
			else if(monster.getType() == EntityType.SLIME)
			{
				switch(monsterYaml.getString(monsterName + ".Type"))
				{
					case "����������" : ((Slime) monster).setSize(1);break;
					case "���뽽����" : ((Slime) monster).setSize(2);break;
					case "ū������" : ((Slime) monster).setSize(4);break;
					case "Ư�뽽����" : ((Slime) monster).setSize(16);break;
					case "�ʴ���������" : ((Slime) monster).setSize(64);break;
					default : ((Slime) monster).setSize(new util.NumericUtil().RandomNum(1, 4));break;
				}
			}
			else if(monster.getType() == EntityType.MAGMA_CUBE)
			{
				switch(monsterYaml.getString(monsterName + ".Type"))
				{
					case "�������׸�ť��" : ((MagmaCube) monster).setSize(1);break;
					case "���븶�׸�ť��" : ((MagmaCube) monster).setSize(2);break;
					case "ū���׸�ť��" : ((MagmaCube) monster).setSize(4);break;
					case "Ư�븶�׸�ť��" : ((MagmaCube) monster).setSize(16);break;
					case "�ʴ������׸�ť��" : ((MagmaCube) monster).setSize(64);break;
					default : ((MagmaCube) monster).setSize(new util.NumericUtil().RandomNum(1, 4));break;
				}
			}
			if(monsterYaml.contains(monsterName+".Potion"))
			{
				if(monsterYaml.getInt(monsterName+".Potion.Regenerate")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.REGENERATION, 50000, monsterYaml.getInt(monsterName+".Potion.Regenerate")));
				if(monsterYaml.getInt(monsterName+".Potion.Poison")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.POISON, 50000, monsterYaml.getInt(monsterName+".Potion.Poison")));
				if(monsterYaml.getInt(monsterName+".Potion.Speed")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.SPEED, 50000, monsterYaml.getInt(monsterName+".Potion.Speed")));
				if(monsterYaml.getInt(monsterName+".Potion.Slow")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.SLOW, 50000, monsterYaml.getInt(monsterName+".Potion.Slow")));
				if(monsterYaml.getInt(monsterName+".Potion.Strength")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.INCREASE_DAMAGE, 50000, monsterYaml.getInt(monsterName+".Potion.Strength")));
				if(monsterYaml.getInt(monsterName+".Potion.Weak")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.WEAKNESS, 50000, monsterYaml.getInt(monsterName+".Potion.Weak")));
				if(monsterYaml.getInt(monsterName+".Potion.JumpBoost")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.JUMP, 50000, monsterYaml.getInt(monsterName+".Potion.JumpBoost")));
				if(monsterYaml.getInt(monsterName+".Potion.FireRegistance")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.FIRE_RESISTANCE, 50000, monsterYaml.getInt(monsterName+".Potion.FireRegistance")));
				if(monsterYaml.getInt(monsterName+".Potion.WaterBreath")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.WATER_BREATHING, 50000, monsterYaml.getInt(monsterName+".Potion.WaterBreath")));
				if(monsterYaml.getInt(monsterName+".Potion.Invisible")!=0)
					monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.INVISIBILITY, 50000, monsterYaml.getInt(monsterName+".Potion.Invisible")));
			}
			Damageable m = monster;
			m.setMaxHealth(monsterYaml.getInt(monsterName + ".HP")+0.5);
			m.setHealth(m.getMaxHealth());
		}
		
		if(dungeonSpawning != -1)
		{
			YamlLoader monsterYaml = new YamlLoader();
			monsterYaml.getConfig("Monster/MonsterList.yml");
			/*
    		���� �ĺ� �ڵ� ���� ���� GroupNumber �ڵ�� ������ ���� �׷��� �����ϱ� ��������,
    		�� ���� �׷� �ڵ尡 ���� ���Ͱ� �ݰ� 20 �̳��� ���� ���, ���� ���̺갡 �����ų�
    		���� ������ �ȴ�. ���� �׷� �ڵ�� 0 ~ f ���� �����Ѵ�.
			 */
			if(monsterName==null)
			{
				switch(dungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					monster.setCustomName("��2��0��2��e��"+group+"��f");
					break;
				case 2://2�� == ���� ���̺� ����
					monster.setCustomName("��2��0��2��1��"+group+"��f");
					break;
				case 3://3�� == ���� ���� ��
					monster.setCustomName("��2��0��2��4��"+group+"��f");
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					monster.setCustomName("��2��0��2��2��"+group+"��f");
					break;
				}
			}
			else
			{
				String a = monsterYaml.getString(monsterName + ".Name").replace("&", "��");
				switch(dungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					monster.setCustomName("��2��0��2��e��"+group+"��f"+a);
					break;
				case 2://2�� == ���� ���̺� ����
					monster.setCustomName("��2��0��2��1��"+group+"��f"+a);
					break;
				case 3://3�� == ���� ���� ��
					monster.setCustomName("��2��0��2��4��"+group+"��f"+a);
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					monster.setCustomName("��2��0��2��2��"+group+"��f"+a);
					break;
				}
			}
			ItemStack helmet = monsterYaml.getItemStack(monsterName+".Head.Item");
			int itemnumber = 30;
			if(helmet!=null && helmet.getType() != Material.AIR)
			{
				if(helmet.hasItemMeta())
				{
					ItemMeta im = helmet.getItemMeta();
					im.setLore(Arrays.asList("xyz:"+xyzLoc[0]+","+xyzLoc[1]+","+xyzLoc[2]));
					helmet.setItemMeta(im);
					monster.getEquipment().setHelmet(helmet);
				}
				else
				{
					ItemStack item = new MaterialData(267, (byte) 0).toItemStack(1);
					ItemMeta im = item.getItemMeta();
					im.setLore(Arrays.asList("xyz:"+xyzLoc[0]+","+xyzLoc[1]+","+xyzLoc[2]));
					helmet.setItemMeta(im);
					monster.getEquipment().setHelmet(helmet);
				}
			}
			else
			{
				ItemStack item = new ItemStack(itemnumber);
				item.setAmount(1);
				ItemMeta im = item.getItemMeta();
				im.setLore(Arrays.asList("xyz:"+xyzLoc[0]+","+xyzLoc[1]+","+xyzLoc[2]));
				item.setItemMeta(im);
				monster.getEquipment().setHelmet(item);
			}
			monster.getEquipment().setHelmetDropChance(0.00000000000000000F);
		}
		return monster;
	}
	
	private EnderCrystal getEnderCrystal(EnderCrystal monster, String mob, byte dungeonSpawning, int[] xyzLoc, char group)
	{
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		if(dungeonSpawning != -1)
		{
			/*
    		���� �ĺ� �ڵ� ���� ���� GroupNumber �ڵ�� ������ ���� �׷��� �����ϱ� ��������,
    		�� ���� �׷� �ڵ尡 ���� ���Ͱ� �ݰ� 20 �̳��� ���� ���, ���� ���̺갡 �����ų�
    		���� ������ �ȴ�. ���� �׷� �ڵ�� 0 ~ f ���� �����Ѵ�.
			 */
			if(mob.charAt(0)=='��')
			{
				switch(dungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					monster.setCustomName("��2��0��2��e��"+group+"��f");
					break;
				case 2://2�� == ���� ���̺� ����
					monster.setCustomName("��2��0��2��1��"+group+"��f");
					break;
				case 3://3�� == ���� ���� ��
					monster.setCustomName("��2��0��2��4��"+group+"��f");
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					monster.setCustomName("��2��0��2��2��"+group+"��f");
					break;
				}
			}
			else
			{
				String a = monsterYaml.getString(mob + ".Name").replace("&", "��");
				switch(dungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					monster.setCustomName("��2��0��2��e��"+group+"��f"+a);
					break;
				case 2://2�� == ���� ���̺� ����
					monster.setCustomName("��2��0��2��1��"+group+"��f"+a);
					break;
				case 3://3�� == ���� ���� ��
					monster.setCustomName("��2��0��2��4��"+group+"��f"+a);
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					monster.setCustomName("��2��0��2��2��"+group+"��f"+a);
					break;
				}
			}
		}
		else
			monster.setCustomName(monsterYaml.getString(mob + ".Name").replace("&", "��"));
		monster.setCustomNameVisible(true);
		return monster;
	}

	
	private static void stack(String display, int id, int data, int amount, List<String> lore, int loc, Inventory inventory)
	{
		ItemStack item = new ItemStack(id);
		item.setAmount(amount);
		item.setDurability((short) data);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(display);
		im.setLore(lore);
		item.setItemMeta(im);
		inventory.setItem(loc, item);
		return;
	}
	
	public void spawnEggGive(Player player, String mob)
	{
		ItemStack item = new ItemStack(383);
		item.setAmount(1);
		item.setDurability((short)0);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName("��c"+mob);
		im.addEnchant(Enchantment.DURABILITY, 1, true);
		im.setLore(Arrays.asList("��8"+mob+"���� ����"));
		item.setItemMeta(im);
		player.getInventory().addItem(item);
		player.sendMessage("��e[SYSTEM] : "+ChatColor.GREEN+mob +"��e���� ���׸� ������ϴ�!");
		return;
	}
	
	public void SpawnEffect (Entity mob,Location loc, byte type)
	{
		
		effect.ParticleEffect p = new effect.ParticleEffect();
		switch(type)
		{
		case 0: return;
		case 1:
			{
				SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_ENDERDRAGON_GROWL, 1.0F, 0.5F);
				for(int counter=0;counter<400;counter++)
				p.PLC(loc, Effect.SPELL, 4);
				for(int counter=0;counter<300;counter++)
				p.PLC(loc, Effect.FLYING_GLYPH, 0);
				for(int counter=0;counter<200;counter++)
				p.PLC(loc, Effect.SMOKE, 4);
			}
			return;
		case 2:
			{
				loc.setY((double)(loc.getBlockY()+1));
				SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 0.5F);
				p.PLC(loc, Effect.ENDER_SIGNAL, 0);
				for(int counter=0;counter<100;counter++)
				p.PLC(loc, Effect.ENDER_SIGNAL, 0);
				for(int counter=0;counter<50;counter++)
				p.PLC(loc, Effect.MAGIC_CRIT, 4);
			}
			return;
		case 3:
			{
				SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 0.5F);
				p.PLC(loc, Effect.EXPLOSION_LARGE, 0);
				for(int counter=0;counter<3;counter++)
				p.PLC(loc, Effect.EXPLOSION_HUGE, 0);
				for(int counter=0;counter<10;counter++)
				p.PLC(loc, Effect.EXPLOSION, 4);
			}
			return;
		case 4:
			{
				SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_GHAST_SHOOT, 1.0F, 0.5F);
				p.PLC(loc, Effect.FLAME, 0);
				for(int counter=0;counter<200;counter++)
				p.PLC(loc, Effect.SMOKE, 9);
				for(int counter=0;counter<200;counter++)
				p.PLC(loc, Effect.FLAME, 0);
			}
			return;
		case 5:
			{
				SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1.0F, 0.6F);
				p.PLC(loc, Effect.CRIT, 0);
				for(int counter=0;counter<400;counter++)
				p.PLC(loc, Effect.SPELL, 4);
				loc.setY((double)(loc.getBlockY()+1.5));
				for(int counter=0;counter<1;counter++)
				p.PLC(loc, Effect.VILLAGER_THUNDERCLOUD, counter);
			}
			return;
		case 6:
			{
				SoundEffect.playSoundLocation(loc, org.bukkit.Sound.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
				loc.setY((double)(loc.getBlockY()+1.8));
				p.PLC(loc, Effect.HEART, 0);
			}
			return;
		case 7:
			{
				switch(mob.getType())
				{
				case ZOMBIE :
				case GIANT :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_ZOMBIE_AMBIENT, 1.0F, 0.5F);
						loc.setY((double)(loc.getBlockY()+1.8));
						p.PLC(loc, Effect.VILLAGER_THUNDERCLOUD, 0);
						for(int counter=0;counter<50;counter++)
							p.PLC(loc, Effect.MAGIC_CRIT, counter);
					}
					break;
				case SKELETON :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_SKELETON_DEATH, 1.0F, 0.5F);
						loc.setY((double)(loc.getBlockY()+1.8));
						p.PLC(loc, Effect.VILLAGER_THUNDERCLOUD, 0);
						for(int counter=0;counter<50;counter++)
							p.PLC(loc, Effect.MAGIC_CRIT, counter);
					}
					break;
				case ENDERMAN :
				case ENDERMITE :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 0.5F);
						loc.setY((double)(loc.getBlockY()+1));
						for(int counter=0;counter<100;counter++)
							p.PLC(loc, Effect.ENDER_SIGNAL, 0);
					}
					break;
				case CREEPER :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 0.5F);
						loc.setY((double)(loc.getBlockY()+1));
						p.PLC(loc, Effect.EXPLOSION_LARGE, 0);
						for(int counter=0;counter<3;counter++)
						p.PLC(loc, Effect.EXPLOSION_HUGE, counter);
						for(int counter=0;counter<5;counter++)
						p.PLC(loc, Effect.EXPLOSION, counter);
					}
					break;
				case SPIDER :
				case CAVE_SPIDER :
				case SILVERFISH:
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_SPIDER_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<10;counter++)
						p.PLC(loc, Effect.LARGE_SMOKE, counter);
						loc.setY((double)(loc.getBlockY()+1));
						p.PLC(loc, Effect.SMOKE, 0);
					}
					break;
				case SLIME:
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_SLIME_JUMP, 1.0F, 0.5F);
						loc.setY((double)(loc.getBlockY()+1));
						for(int counter=0;counter<20;counter++)
						p.PLC(loc, Effect.SLIME, counter);
					}
					break;
				case MAGMA_CUBE:
					{
						loc.setY((double)(loc.getBlockY()+1));
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_SLIME_JUMP, 1.0F, 0.5F);
						for(int counter=0;counter<40;counter++)
						p.PLC(loc, Effect.MOBSPAWNER_FLAMES, counter);
					}
					break;
				case BLAZE :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_BLAZE_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<200;counter++)
						p.PLC(loc, Effect.SMOKE, 9);
						for(int counter=0;counter<200;counter++)
						p.PLC(loc, Effect.FLAME, 0);
					}
					break;
				case GHAST :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_GHAST_AMBIENT, 1.0F, 0.5F);
						p.PLC(loc, Effect.FLAME, 0);
						for(int counter=0;counter<100;counter++)
							p.PLC(loc, Effect.SMOKE, 4);
						for(int counter=0;counter<40;counter++)
						p.PLC(loc, Effect.MOBSPAWNER_FLAMES, counter);
					}
					break;
				case PIG_ZOMBIE :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_ZOMBIE_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<100;counter++)
							p.PLC(loc, Effect.SMOKE, 4);
						for(int counter=0;counter<40;counter++)
						p.PLC(loc, Effect.MOBSPAWNER_FLAMES, counter);
					}
					break;
				case WITCH:
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_WITCH_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<400;counter++)
						p.PLC(loc, Effect.SPELL, 4);
						for(int counter=0;counter<300;counter++)
						p.PLC(loc, Effect.FLYING_GLYPH, 0);
						for(int counter=0;counter<200;counter++)
						p.PLC(loc, Effect.SMOKE, 4);
					}
					break;
				case GUARDIAN :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_GUARDIAN_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<400;counter++)
						p.PLC(loc, Effect.WATERDRIP, counter);
					}
					break;
				case SNOWMAN :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_SNOWMAN_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<200;counter++)
						p.PLC(loc, Effect.SNOWBALL_BREAK, counter);
					}
					break;
					case SHULKER :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_SHULKER_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<400;counter++)
						p.PLC(loc, Effect.ENDER_SIGNAL, counter);
					}
					break;
					case POLAR_BEAR :
					{
						SoundEffect.playSoundLocation(loc, org.bukkit.Sound.ENTITY_POLAR_BEAR_AMBIENT, 1.0F, 0.5F);
						for(int counter=0;counter<200;counter++)
						p.PLC(loc, Effect.VILLAGER_THUNDERCLOUD, counter);
					}
					break;
				}
			}
			return;
		}
		return;
	}
	
	public short getMonsterID(String monsterType)
	{
		switch(monsterType)
		{
			case "����":
				return 54;
			case "���̾�Ʈ":
				return 53;
			case "�״����̷���":
			case "���̷���":
				return 51;
			case "������":
				return 58;
			case "����ũ����":
			case "ũ����":
				return 50;
			case "�Ź�":
				return 52;
			case "�����Ź�":
				return 59;
			case "������":
				return 60;
			case "���������":
				return 67;
			case "�ʴ���������": case "Ư�뽽����": case "ū������": case "���뽽����": case "����������": 
				return 55;
			case "Ư�븶�׸�ť��": case "ū���׸�ť��": case "���븶�׸�ť��": case "�������׸�ť��": case "���׸�ť��":
				return 62;
			case "������":
				return 61;
			case "����Ʈ":
				return 56;
			case "�����Ǳ׸�":
				return 57;
			case "����":
				return 66;
			case "����":
				return 64;
			case "�����巡��":
				return 63;
			case "����ũ����Ż":
				return -50;
			case "��ȣ��":
				return 68;
			case "��":
				return 91;
			case "��":
				return 92;
			case "����":
				return 90;
			case "��":
				return 100;
			case "�䳢":
				return 101;
			case "������":
				return 98;
			case "����":
				return 95;
			case "��":
				return 93;
			case "������":
				return 96;
			case "��¡��":
				return 94;
			case "�ֹ�":
				return 120;
			case "�����":
				return 97;
			case "��":
				return 99;
			case "����":
				return 65;
			case "��Ŀ":
				return 69;
			case "�ϱذ�":
				return 102;
			case "�޸�":
				return 49;
			default : return -60;
		}
	}

	public String getMonsterCustomName(String monsterType)
	{
		switch(monsterType)
		{
			case "����":
				return "CUSTOMZOMBIE";
			case "���̾�Ʈ":
				return "CUSTOMGIANT";
			case "�״����̷���":
			case "���̷���":
				return "CUSTOMSKELETON";
			case "������":
				return "CUSTOMENDERMAN";
			case "����ũ����":
			case "ũ����":
				return "CUSTOMCREEPER";
			case "�Ź�":
				return "CUSTOMSPIDER";
			case "�����Ź�":
				return "CUSTOMCAVESPIDER";
			case "������":
				return "CUSTOMSLIVERFISH";
			case "���������":
				return "CUSTOMENDERMITE";
			case "�ʴ���������": case "Ư�뽽����": case "ū������": case "���뽽����": case "����������":
				return "CUSTOMSLIME"; 
			case "Ư�븶�׸�ť��": case "ū���׸�ť��": case "���븶�׸�ť��": case "�������׸�ť��": case "���׸�ť��":
				return "CUSTOMMAGMACUBE"; 
			case "������":
				return "CUSTOMBLAZE";
			case "����Ʈ":
				return "CUSTOMGHAST";
			case "�����Ǳ׸�":
				return "CUSTOMPIGZOMBIE";
			case "����":
				return "CUSTOMWITCH";
			case "����":
				return "CUSTOMWITHER";
			case "�����巡��":
				return "CUSTOMENDERDRAGON";
			case "��ȣ��":
				return "CUSTOMGUARDIAN";
			case "��":
				return "CUSTOMSHEEP";
			case "��":
				return "CUSTOMCOW";
			case "����":
				return "CUSTOMPIG";
			case "��":
				return "CUSTOMHORSE";
			case "�䳢":
				return "CUSTOMRABBIT";
			case "������":
				return "CUSTOMOCELOT";
			case "����":
				return "CUSTOMWOLF";
			case "��":
				return "CUSTOMCHICKEN";
			case "������":
				return "CUSTOMMOOSHROOM";
			case "��¡��":
				return "CUSTOMSQUID";
			case "�ֹ�":
				return "CUSTOMVILLAGER";
			case "�����":
				return "CUSTOMSNOWGOLEM";
			case "��":
				return "CUSTOMIRONGOLEM";
			case "����":
				return "CUSTOMBAT";
			case "��Ŀ":
				return "CUSTOMSHULKER";
			case "�ϱذ�":
				return "CUSTOMPOLARBEAR";
			case "�޸�":
				return "CUSTOMHUMAN";
			default:
				return "CUSTOMZOMBIE";
		}
	}
}