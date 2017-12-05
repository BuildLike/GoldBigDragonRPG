package monster;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
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
import util.YamlLoader;



public class MonsterSpawn
{
	public void EntitySpawn(CreatureSpawnEvent event)
	{
		if(event.getLocation().getWorld().getName().equals("Dungeon"))
		{
			if(event.getSpawnReason()== SpawnReason.NATURAL || event.getSpawnReason() == SpawnReason.CHUNK_GEN
					|| event.getSpawnReason() == SpawnReason.MOUNT|| event.getSpawnReason() == SpawnReason.JOCKEY
					|| event.getSpawnReason() == SpawnReason.SLIME_SPLIT)
			{
				event.setCancelled(true);
				return;
			}
		}
		if(event.getEntity().getType()==EntityType.ARMOR_STAND)
			return;
		if(event.getSpawnReason() == SpawnReason.SLIME_SPLIT)
		{
			if(event.getEntity().getCustomName()!=null)
			{
				if(main.MainServerOption.MonsterNameMatching.containsKey(event.getEntity().getCustomName()))
				{
					event.setCancelled(true);
				}
			}
		}
		area.AreaMain A = new area.AreaMain();
		String[] Area = A.getAreaName(event.getEntity());
		
		YamlLoader areaYaml = new YamlLoader();
		if(Area != null)
		{
			if(A.getAreaOption(Area[0], (char) 3) == false)
			{
				event.setCancelled(true);
				return;
			}
			else if(A.getAreaOption(Area[0], (char) 8) == false)
			{
				areaYaml.getConfig("Area/AreaList.yml");
				String AreaName = A.getAreaName(event.getEntity())[0];
				Object[] MobNameList = areaYaml.getConfigurationSection(AreaName+".Monster").getKeys(false).toArray();
				boolean isExit = false;
				for(int count = 0;count<10;count++)
				{
					if(isExit) break;
					if(MobNameList.length != 0)
					{
						short RandomMob = (short) new util.UtilNumber().RandomNum(0, MobNameList.length-1);
						if(main.MainServerOption.MonsterList.containsKey(MobNameList[RandomMob].toString()))
						{
							new monster.MonsterSpawn().SpawnMob(event.getLocation(), MobNameList[RandomMob].toString(), (byte) -1, null,(char) -1, false);
							isExit = true;
						}
						else
						{
							areaYaml.removeKey(AreaName+".Monster."+MobNameList[RandomMob]);
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
	
	public void CreateMonster(String MonsterName)
	{
		ItemStack Item = new ItemStack(Material.AIR);
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		
		monsterYaml.set(MonsterName+".Name", MonsterName);
		monsterYaml.set(MonsterName+".Type", "����");
		monsterYaml.set(MonsterName+".AI", "�Ϲ� �ൿ");
		monsterYaml.set(MonsterName+".EXP", 15);
		monsterYaml.set(MonsterName+".Biome", "NULL");
		monsterYaml.set(MonsterName+".HP", 20);
		monsterYaml.set(MonsterName+".MIN_Money", 1);
		monsterYaml.set(MonsterName+".MAX_Money", 10);
		monsterYaml.set(MonsterName+".STR", 10);
		monsterYaml.set(MonsterName+".DEX", 10);
		monsterYaml.set(MonsterName+".INT", 10);
		monsterYaml.set(MonsterName+".WILL", 10);
		monsterYaml.set(MonsterName+".LUK", 10);
		monsterYaml.set(MonsterName+".DEF", 0);
		monsterYaml.set(MonsterName+".Protect", 0);
		monsterYaml.set(MonsterName+".Magic_DEF", 0);
		monsterYaml.set(MonsterName+".Magic_Protect", 0);
		monsterYaml.set(MonsterName+".Head.DropChance", 0);
		monsterYaml.set(MonsterName+".Head.Item", Item);
		monsterYaml.set(MonsterName+".Chest.DropChance", 0);
		monsterYaml.set(MonsterName+".Chest.Item", Item);
		monsterYaml.set(MonsterName+".Leggings.DropChance", 0);
		monsterYaml.set(MonsterName+".Leggings.Item", Item);
		monsterYaml.set(MonsterName+".Boots.DropChance", 0);
		monsterYaml.set(MonsterName+".Boots.Item", Item);
		monsterYaml.set(MonsterName+".Hand.DropChance", 0);
		monsterYaml.set(MonsterName+".Hand.Item", Item);
		monsterYaml.set(MonsterName+".OffHand.DropChance", 0);
		monsterYaml.set(MonsterName+".OffHand.Item", Item);
		monsterYaml.saveConfig();
		return;
	}

	public void StayLive(Entity e, boolean isStayLive)
	{
		if(e.getType()!=EntityType.ENDER_CRYSTAL)
			if(!e.isDead())
			{
				LivingEntity LE = (LivingEntity) e;
				LE.setRemoveWhenFarAway(isStayLive);
			}
	}
	
	public void SpawnMob(Location loc, String mob, byte DungeonSpawning, int[] XYZLoc, char Group, boolean isStayLive)
	{
		if(mob.charAt(0)=='��')
		{
			String Type = mob.substring(1);
			mob = null;
			CreateCreature(Type, loc, mob, DungeonSpawning, XYZLoc, Group, isStayLive);
		}
		loc.add(0.5, 1, 0.5);
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		String Type = monsterYaml.getString(mob + ".Type");
		if(monsterYaml.getString(mob+".Name")!=null)
		{
			String aiString = monsterYaml.getString(mob + ".AI");
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
				monsterYaml.set(mob + ".AI", "�Ϲ� �ൿ");
				monsterYaml.saveConfig();
			}
			if(!aiString.equals("�Ϲ� �ൿ"))
			{
				String CustomMobName = "CUSTOM"+mob;
				int CustomMobID = getMonsterID(monsterYaml.getString(mob + ".Type"));
				LivingEntity E = null;
				if(Type.equals("����"))
					E = (CraftZombie) CustomZombie.spawn(loc, ai);
				else if(Type.equals("���̾�Ʈ"))
					E = (CraftGiant) CustomGiant.spawn(loc, ai);
				else if(Type.equals("���̷���"))
					E = (CraftSkeleton) CustomSkeleton.spawn(loc, ai);
				else if(Type.equals("�״����̷���"))
					E = (CraftWitherSkeleton) CustomWitherSkeleton.spawn(loc, ai);
				else if(Type.equals("������"))
					E = (CraftEnderman) CustomEnderman.spawn(loc, ai);
				else if(Type.equals("ũ����")||Type.equals("����ũ����"))
					E = (CraftCreeper) CustomCreeper.spawn(loc, ai);
				else if(Type.equals("�Ź�"))
					E = (CraftSpider) CustomSpider.spawn(loc, ai);
				else if(Type.equals("�����Ź�"))
					E = (CraftCaveSpider) CustomCaveSpider.spawn(loc, ai);
				else if(Type.equals("������"))
					E = (CraftSilverfish) CustomSilverFish.spawn(loc, ai);
				else if(Type.equals("���������"))
					E = (CraftEndermite) CustomEnderMite.spawn(loc, ai);
				else if(Type.equals("������"))
					E = (CraftBlaze) CustomBlaze.spawn(loc, ai);
				else if(Type.equals("�����Ǳ׸�"))
					E = (CraftPigZombie) CustomPigZombie.spawn(loc, ai);
				else if(Type.equals("����"))
					E = (CraftWitch) CustomWitch.spawn(loc, ai);
				else if(Type.equals("����"))
					E = (CraftWither) CustomWither.spawn(loc, ai);
				else if(Type.equals("��ȣ��"))
					E = (CraftGuardian) CustomGuardian.spawn(loc, ai);
				else if(Type.equals("�ϱذ�"))
					E = (CraftPolarBear) CustomPolarBear.spawn(loc, ai);
				else if(Type.equals("����ũ����Ż"))
					CreateCreature(Type, loc, mob, DungeonSpawning, XYZLoc, Group, isStayLive);

				else if (Type.equals("���������"))
					E = (CraftElderGuardian) CustomElderGuardian.spawn(loc, ai);
				else if (Type.equals("��Ʈ����"))
					E = (CraftStray) CustomStray.spawn(loc, ai);
				else if (Type.equals("�㽺ũ"))
					E = (CraftHusk) CustomHusk.spawn(loc, ai);
				else if (Type.equals("�ֹ�����"))
					E = (CraftVillagerZombie) CustomZombieVillager.spawn(loc, ai);
				else if (Type.equals("��ȯ��"))
					E = (CraftEvoker) CustomEvoker.spawn(loc, ai);
				else if (Type.equals("����"))
					E = (CraftVex) CustomVex.spawn(loc, ai);
				else if (Type.equals("������"))
					E = (CraftVindicator) CustomVindicator.spawn(loc, ai);
				else if (Type.equals("��Ŀ"))
					E = (Shulker) CustomShulker.spawn(loc, ai);
				// ���� ������ ��
				else if (Type.equals("���̷��渻"))
					E = (LivingEntity) CustomSkeletonHorse.spawn(loc, ai);
				else if (Type.equals("����"))
					E = (LivingEntity) CustomZombieHorse.spawn(loc, ai);
				else if (Type.equals("�糪��"))
					E = (LivingEntity) CustomDonkey.spawn(loc, ai);
				else if (Type.equals("���"))
					E = (LivingEntity) CustomMule.spawn(loc, ai);
				else if (Type.equals("�ΰ�"))
					E = (LivingEntity) CustomHuman.spawn(loc, ai);
				else if (Type.equals("����"))
					E = (LivingEntity) CustomBat.spawn(loc, ai);
				else if (Type.equals("����"))
					E = (LivingEntity) CustomPig.spawn(loc, ai);
				else if (Type.equals("��"))
					E = (LivingEntity) CustomSheep.spawn(loc, ai);
				else if (Type.equals("��"))
					E = (LivingEntity) CustomCow.spawn(loc, ai);
				else if (Type.equals("��"))
					E = (LivingEntity) CustomChicken.spawn(loc, ai);
				else if (Type.equals("��¡��"))
					E = (LivingEntity) CustomSquid.spawn(loc, ai);
				else if (Type.equals("����"))
					E = (LivingEntity) CustomWolf.spawn(loc, ai);
				else if (Type.equals("������"))
					E = (LivingEntity) CustomMooshroom.spawn(loc, ai);
				else if (Type.equals("�����"))
					E = (LivingEntity) CustomSnowman.spawn(loc, ai);
				else if (Type.equals("������"))
					E = (LivingEntity) CustomOcelot.spawn(loc, ai);
				else if (Type.equals("ö��"))
					E = (LivingEntity) CustomIronGolem.spawn(loc, ai);
				else if (Type.equals("��"))
					E = (LivingEntity) CustomHorse.spawn(loc, ai);
				else if (Type.equals("�䳢"))
					E = (LivingEntity) CustomRabbit.spawn(loc, ai);
				else if (Type.equals("��"))
					E = (LivingEntity) CustomLlama.spawn(loc, ai);
				else if (Type.equals("�ֹ�"))
					E = (LivingEntity) CustomVillager.spawn(loc, ai);

				if(E!=null)
					E = getEntity(E, mob, DungeonSpawning, XYZLoc, Group);
			}
			else
				CreateCreature(Type, loc, mob, DungeonSpawning, XYZLoc, Group, isStayLive);
		}
		return;
	}
	
	private void CreateCreature(String Type, Location loc, String mob, byte DungeonSpawning, int[] XYZLoc, char Group, boolean isStayLive)
	{
		switch(Type)
		{
			case "����":{Zombie zombie = (Zombie) loc.getWorld().spawn(loc, Zombie.class);zombie = (Zombie) getEntity(zombie,mob, DungeonSpawning, XYZLoc, Group);}break;
			case "���̾�Ʈ":{Giant giant = (Giant) loc.getWorld().spawn(loc, Giant.class);giant = (Giant) getEntity(giant,mob, DungeonSpawning, XYZLoc, Group);StayLive(giant, isStayLive);}break;
			case "���̷���":{Skeleton skeleton = (Skeleton) loc.getWorld().spawn(loc, Skeleton.class);skeleton = (Skeleton) getEntity(skeleton,mob, DungeonSpawning, XYZLoc, Group);StayLive(skeleton, isStayLive);}break;
			case "�״����̷���":{WitherSkeleton skeleton = (WitherSkeleton) loc.getWorld().spawn(loc, WitherSkeleton.class);skeleton = (WitherSkeleton) getEntity(skeleton,mob, DungeonSpawning, XYZLoc, Group);StayLive(skeleton, isStayLive);}break;
			case "������":{Enderman enderman = (Enderman) loc.getWorld().spawn(loc, Enderman.class);enderman = (Enderman) getEntity(enderman,mob, DungeonSpawning, XYZLoc, Group);StayLive(enderman, isStayLive);}break;
			case "ũ����":{Creeper creeper = (Creeper) loc.getWorld().spawn(loc, Creeper.class);creeper = (Creeper) getEntity(creeper,mob, DungeonSpawning, XYZLoc, Group);StayLive(creeper, isStayLive);}break;
			case "����ũ����":{Creeper Lcreeper = (Creeper) loc.getWorld().spawn(loc, Creeper.class);Lcreeper = (Creeper) getEntity(Lcreeper,mob, DungeonSpawning, XYZLoc, Group);StayLive(Lcreeper, isStayLive);}break;
			case "�Ź�":{Spider spider = (Spider) loc.getWorld().spawn(loc, Spider.class);spider = (Spider) getEntity(spider,mob, DungeonSpawning, XYZLoc, Group);StayLive(spider, isStayLive);}break;
			case "�����Ź�":{CaveSpider cavespider = (CaveSpider) loc.getWorld().spawn(loc, CaveSpider.class);cavespider = (CaveSpider) getEntity(cavespider,mob, DungeonSpawning, XYZLoc, Group);StayLive(cavespider, isStayLive);}break;
			case "������":{Silverfish silverfish = (Silverfish) loc.getWorld().spawn(loc, Silverfish.class);silverfish = (Silverfish) getEntity(silverfish,mob, DungeonSpawning, XYZLoc, Group);StayLive(silverfish, isStayLive);}break;
			case "���������":{Endermite endermite = (Endermite) loc.getWorld().spawn(loc, Endermite.class);endermite = (Endermite) getEntity(endermite,mob, DungeonSpawning, XYZLoc, Group);StayLive(endermite, isStayLive);}break;
			case "������":case "�ʴ���������": case "Ư�뽽����": case "ū������": case "���뽽����": case "����������":{Slime Sslime = (Slime) loc.getWorld().spawn(loc, Slime.class);Sslime = (Slime) getEntity(Sslime,mob, DungeonSpawning, XYZLoc, Group);StayLive(Sslime, isStayLive);}break;
			case "���׸�ť��": case "�ʴ������׸�ť��":case "Ư�븶�׸�ť��": case "ū���׸�ť��": case "���븶�׸�ť��": case "�������׸�ť��":{MagmaCube Smagmacube = (MagmaCube) loc.getWorld().spawn(loc, MagmaCube.class);Smagmacube = (MagmaCube) getEntity(Smagmacube,mob, DungeonSpawning, XYZLoc, Group);StayLive(Smagmacube, isStayLive);}break;
			case "������":{Blaze blaze = (Blaze) loc.getWorld().spawn(loc, Blaze.class);blaze = (Blaze) getEntity(blaze,mob, DungeonSpawning, XYZLoc, Group);StayLive(blaze, isStayLive);}break;
			case "����Ʈ":{Ghast ghast = (Ghast) loc.getWorld().spawn(loc, Ghast.class);ghast = (Ghast) getEntity(ghast,mob, DungeonSpawning, XYZLoc, Group);StayLive(ghast, isStayLive);}break;
			case "�����Ǳ׸�":{PigZombie pigzombie = (PigZombie) loc.getWorld().spawn(loc, PigZombie.class);pigzombie = (PigZombie) getEntity(pigzombie,mob, DungeonSpawning, XYZLoc, Group);StayLive(pigzombie, isStayLive);}break;
			case "����":{Witch witch = (Witch) loc.getWorld().spawn(loc, Witch.class);witch = (Witch) getEntity(witch,mob, DungeonSpawning, XYZLoc, Group);StayLive(witch, isStayLive);}break;
			case "����":{Wither wither = (Wither) loc.getWorld().spawn(loc, Wither.class);wither = (Wither) getEntity(wither,mob, DungeonSpawning, XYZLoc, Group);StayLive(wither, isStayLive);}break;
			case "�����巡��":{EnderDragon ED = (EnderDragon) loc.getWorld().spawn(loc, EnderDragon.class);ED = (EnderDragon) getEntity(ED,mob, DungeonSpawning, XYZLoc, Group);StayLive(ED, isStayLive);}break;
			case "����ũ����Ż":{EnderCrystal EC = (EnderCrystal) loc.getWorld().spawn(loc, EnderCrystal.class);EC = getEnderCrystal(EC,mob, DungeonSpawning, XYZLoc, Group);StayLive(EC, isStayLive);}break;
			case "��ȣ��":{Guardian guardian = (Guardian) loc.getWorld().spawn(loc, Guardian.class);guardian = (Guardian) getEntity(guardian,mob, DungeonSpawning, XYZLoc, Group);StayLive(guardian, isStayLive);}break;
			case "��":{Sheep sheep = (Sheep) loc.getWorld().spawn(loc, Sheep.class);sheep = (Sheep) getEntity(sheep,mob, DungeonSpawning, XYZLoc, Group);StayLive(sheep, isStayLive);}break;
			case "��":{Cow cow = (Cow) loc.getWorld().spawn(loc, Cow.class);cow = (Cow) getEntity(cow,mob, DungeonSpawning, XYZLoc, Group);StayLive(cow, isStayLive);}break;
			case "����":{Pig pig = (Pig) loc.getWorld().spawn(loc, Pig.class);pig = (Pig) getEntity(pig,mob, DungeonSpawning, XYZLoc, Group);StayLive(pig, isStayLive);}break;
			case "��":{Horse horse = (Horse) loc.getWorld().spawn(loc, Horse.class);horse = (Horse) getEntity(horse,mob, DungeonSpawning, XYZLoc, Group);StayLive(horse, isStayLive);}break;
			case "�䳢":{Rabbit rabbit = (Rabbit) loc.getWorld().spawn(loc, Rabbit.class);rabbit = (Rabbit) getEntity(rabbit,mob, DungeonSpawning, XYZLoc, Group);StayLive(rabbit, isStayLive);}break;
			case "������":{Ocelot oceleot = (Ocelot) loc.getWorld().spawn(loc, Ocelot.class);oceleot = (Ocelot) getEntity(oceleot,mob, DungeonSpawning, XYZLoc, Group);StayLive(oceleot, isStayLive);}break;
			case "����":{Wolf wolf = (Wolf) loc.getWorld().spawn(loc, Wolf.class);wolf = (Wolf) getEntity(wolf,mob, DungeonSpawning, XYZLoc, Group);StayLive(wolf, isStayLive);}break;
			case "��":{Chicken chicken = (Chicken) loc.getWorld().spawn(loc, Chicken.class);chicken = (Chicken) getEntity(chicken,mob, DungeonSpawning, XYZLoc, Group);StayLive(chicken, isStayLive);}break;
			case "������":{MushroomCow Mcow = (MushroomCow) loc.getWorld().spawn(loc, MushroomCow.class);Mcow = (MushroomCow) getEntity(Mcow,mob, DungeonSpawning, XYZLoc, Group);StayLive(Mcow, isStayLive);}break;
			case "����":{Bat bat = (Bat) loc.getWorld().spawn(loc, Bat.class);bat = (Bat) getEntity(bat,mob, DungeonSpawning, XYZLoc, Group);StayLive(bat, isStayLive);}break;
			case "��¡��":{Squid squid = (Squid) loc.getWorld().spawn(loc, Squid.class);squid = (Squid) getEntity(squid,mob, DungeonSpawning, XYZLoc, Group);StayLive(squid, isStayLive);}break;
			case "�ֹ�":{Villager villager = (Villager) loc.getWorld().spawn(loc, Villager.class);villager = (Villager) getEntity(villager,mob, DungeonSpawning, XYZLoc, Group);StayLive(villager, isStayLive);}break;
			case "�����":{Snowman snowman = (Snowman) loc.getWorld().spawn(loc, Snowman.class);snowman = (Snowman) getEntity(snowman,mob, DungeonSpawning, XYZLoc, Group);StayLive(snowman, isStayLive);}break;
			case "ö��":{IronGolem golem = (IronGolem) loc.getWorld().spawn(loc, IronGolem.class);golem = (IronGolem) getEntity(golem,mob, DungeonSpawning, XYZLoc, Group);StayLive(golem, isStayLive);}break;
			case "��Ŀ":{Shulker shulker = (Shulker) loc.getWorld().spawn(loc, Shulker.class);shulker = (Shulker) getEntity(shulker,mob, DungeonSpawning, XYZLoc, Group);StayLive(shulker, isStayLive);}break;
			case "�ϱذ�":{PolarBear polarBear = (PolarBear) loc.getWorld().spawn(loc, PolarBear.class);polarBear = (PolarBear) getEntity(polarBear,mob, DungeonSpawning, XYZLoc, Group);StayLive(polarBear, isStayLive);}break;
			
			case "���������":{ElderGuardian elderGuardian = (ElderGuardian) loc.getWorld().spawn(loc, ElderGuardian.class);elderGuardian = (ElderGuardian) getEntity(elderGuardian,mob, DungeonSpawning, XYZLoc, Group);StayLive(elderGuardian, isStayLive);}break;
			case "��Ʈ����":{Stray stray = (Stray) loc.getWorld().spawn(loc, Stray.class);stray = (Stray) getEntity(stray,mob, DungeonSpawning, XYZLoc, Group);StayLive(stray, isStayLive);}break;
			case "�㽺ũ":{Husk husk = (Husk) loc.getWorld().spawn(loc, Husk.class);husk = (Husk) getEntity(husk,mob, DungeonSpawning, XYZLoc, Group);StayLive(husk, isStayLive);}break;
			case "�ֹ�����":{ZombieVillager zombiVillager = (ZombieVillager) loc.getWorld().spawn(loc, ZombieVillager.class);zombiVillager = (ZombieVillager) getEntity(zombiVillager,mob, DungeonSpawning, XYZLoc, Group);StayLive(zombiVillager, isStayLive);}break;
			case "��ȯ��":{Evoker evoker = (Evoker) loc.getWorld().spawn(loc, Evoker.class);evoker = (Evoker) getEntity(evoker,mob, DungeonSpawning, XYZLoc, Group);StayLive(evoker, isStayLive);}break;
			case "����":{Vex vex = (Vex) loc.getWorld().spawn(loc, Vex.class);vex = (Vex) getEntity(vex,mob, DungeonSpawning, XYZLoc, Group);StayLive(vex, isStayLive);}break;
			case "������":{Vindicator polarBear = (Vindicator) loc.getWorld().spawn(loc, Vindicator.class);polarBear = (Vindicator) getEntity(polarBear,mob, DungeonSpawning, XYZLoc, Group);StayLive(polarBear, isStayLive);}break;
			case "���̷��渻":{SkeletonHorse skeletonHorse = (SkeletonHorse) loc.getWorld().spawn(loc, SkeletonHorse.class);skeletonHorse = (SkeletonHorse) getEntity(skeletonHorse,mob, DungeonSpawning, XYZLoc, Group);StayLive(skeletonHorse, isStayLive);}break;
			case "����":{ZombieHorse zombieHorse = (ZombieHorse) loc.getWorld().spawn(loc, ZombieHorse.class);zombieHorse = (ZombieHorse) getEntity(zombieHorse,mob, DungeonSpawning, XYZLoc, Group);StayLive(zombieHorse, isStayLive);}break;
			case "�糪��":{Donkey donkey = (Donkey) loc.getWorld().spawn(loc, Donkey.class);donkey = (Donkey) getEntity(donkey,mob, DungeonSpawning, XYZLoc, Group);StayLive(donkey, isStayLive);}break;
			case "���":{Mule mule = (Mule) loc.getWorld().spawn(loc, Mule.class);mule = (Mule) getEntity(mule,mob, DungeonSpawning, XYZLoc, Group);StayLive(mule, isStayLive);}break;
			case "�ΰ�":{Player player = (Player) loc.getWorld().spawn(loc, Player.class);player = (Player) getEntity(player,mob, DungeonSpawning, XYZLoc, Group);StayLive(player, isStayLive);}break;
			case "��":{Llama llama = (Llama) loc.getWorld().spawn(loc, Llama.class);llama = (Llama) getEntity(llama,mob, DungeonSpawning, XYZLoc, Group);StayLive(llama, isStayLive);}break;
			//case "�޸�":{HumanEntity human = (HumanEntity) loc.getWorld().spawn(loc, Player.class);human = (HumanEntity) getEntity(human,mob, DungeonSpawning, XYZLoc, Group);StayLive(human, isStayLive);}break;
		}
	}

	private LivingEntity getEntity(LivingEntity Monster, String mob, byte DungeonSpawning, int[] XYZloc, char Group)
	{
		if(mob!=null)
		{
			YamlLoader monsterYaml = new YamlLoader();
			monsterYaml.getConfig("Monster/MonsterList.yml");
			Monster.setCustomName(monsterYaml.getString(mob + ".Name").replace("&", "��"));
			Monster.setCustomNameVisible(true);
			ItemStack Equip = monsterYaml.getItemStack(mob+".Head.Item");
			if(Equip == null)
				Monster.getEquipment().setHelmet(null);
			else
			{
				Monster.getEquipment().setHelmet(Equip);
				if(Equip.hasItemMeta())
					if(Equip.getItemMeta().hasLore())
						if(ChatColor.stripColor(Equip.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���."))
							Monster.getEquipment().setHelmet(null);
			}
			Equip = monsterYaml.getItemStack(mob+".Chest.Item");
			if(Equip == null)
				Monster.getEquipment().setChestplate(null);
			else
			{
				Monster.getEquipment().setChestplate(Equip);
				if(Equip.hasItemMeta())
					if(Equip.getItemMeta().hasLore())
						if(ChatColor.stripColor(Equip.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���."))
							Monster.getEquipment().setChestplate(null);
			}
			Equip = monsterYaml.getItemStack(mob+".Leggings.Item");
			if(Equip == null)
				Monster.getEquipment().setLeggings(null);
			else
			{
				Monster.getEquipment().setLeggings(Equip);
				if(Equip.hasItemMeta())
					if(Equip.getItemMeta().hasLore())
						if(ChatColor.stripColor(Equip.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���."))
							Monster.getEquipment().setLeggings(null);
			}
			Equip = monsterYaml.getItemStack(mob+".Boots.Item");
			if(Equip == null)
				Monster.getEquipment().setBoots(null);
			else
			{
				Monster.getEquipment().setBoots(Equip);
				if(Equip.hasItemMeta())
					if(Equip.getItemMeta().hasLore())
						if(ChatColor.stripColor(Equip.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���."))
							Monster.getEquipment().setBoots(null);
			}
			Equip = monsterYaml.getItemStack(mob+".Hand.Item");
			if(Equip == null)
				Monster.getEquipment().setItemInMainHand(null);
			else
			{
				Monster.getEquipment().setItemInMainHand(Equip);
				if(Equip.hasItemMeta())
					if(Equip.getItemMeta().hasLore())
						if(ChatColor.stripColor(Equip.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���."))
								Monster.getEquipment().setItemInMainHand(null);
			}
			Equip = monsterYaml.getItemStack(mob+".OffHand.Item");
			if(Equip == null)
				Monster.getEquipment().setItemInOffHand(null);
			else
			{
				Monster.getEquipment().setItemInOffHand(Equip);
				if(Equip.hasItemMeta())
					if(Equip.getItemMeta().hasLore())
						if(ChatColor.stripColor(Equip.getItemMeta().getLore().get(0)).equals("�̰��� �������� �־� �ּ���."))
								Monster.getEquipment().setItemInOffHand(null);
			}
			YamlLoader configYaml = new YamlLoader();
		    configYaml.getConfig("config.yml");
			Monster.getEquipment().setHelmetDropChance((float)(monsterYaml.getInt(mob+".Head.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			Monster.getEquipment().setChestplateDropChance((float)(monsterYaml.getInt(mob+".Chest.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			Monster.getEquipment().setLeggingsDropChance((float)(monsterYaml.getInt(mob+".Leggings.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			Monster.getEquipment().setBootsDropChance((float)(monsterYaml.getInt(mob+".Boots.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			Monster.getEquipment().setItemInMainHandDropChance((float)(monsterYaml.getInt(mob+".Hand.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			Monster.getEquipment().setItemInOffHandDropChance((float)(monsterYaml.getInt(mob+".OffHand.DropChance")*configYaml.getInt("Event.DropChance")/1000.0));
			
			if(Monster.getType() == EntityType.CREEPER)
			{
				if(monsterYaml.getString(mob+".Type").equalsIgnoreCase("����ũ����") == true)
				{
					  ((Creeper) Monster).setPowered(true);
				}
			}
			else if(Monster.getType() == EntityType.SLIME)
			{
				switch(monsterYaml.getString(mob + ".Type"))
				{
					case "����������" : ((Slime) Monster).setSize(1);break;
					case "���뽽����" : ((Slime) Monster).setSize(2);break;
					case "ū������" : ((Slime) Monster).setSize(4);break;
					case "Ư�뽽����" : ((Slime) Monster).setSize(16);break;
					case "�ʴ���������" : ((Slime) Monster).setSize(64);break;
					default : ((Slime) Monster).setSize(new util.UtilNumber().RandomNum(1, 4));break;
				}
			}
			else if(Monster.getType() == EntityType.MAGMA_CUBE)
			{
				switch(monsterYaml.getString(mob + ".Type"))
				{
					case "�������׸�ť��" : ((MagmaCube) Monster).setSize(1);break;
					case "���븶�׸�ť��" : ((MagmaCube) Monster).setSize(2);break;
					case "ū���׸�ť��" : ((MagmaCube) Monster).setSize(4);break;
					case "Ư�븶�׸�ť��" : ((MagmaCube) Monster).setSize(16);break;
					case "�ʴ������׸�ť��" : ((MagmaCube) Monster).setSize(64);break;
					default : ((MagmaCube) Monster).setSize(new util.UtilNumber().RandomNum(1, 4));break;
				}
			}
			if(monsterYaml.contains(mob+".Potion"))
			{
				if(monsterYaml.getInt(mob+".Potion.Regenerate")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.REGENERATION, 50000, monsterYaml.getInt(mob+".Potion.Regenerate")));
				if(monsterYaml.getInt(mob+".Potion.Poison")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.POISON, 50000, monsterYaml.getInt(mob+".Potion.Poison")));
				if(monsterYaml.getInt(mob+".Potion.Speed")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.SPEED, 50000, monsterYaml.getInt(mob+".Potion.Speed")));
				if(monsterYaml.getInt(mob+".Potion.Slow")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.SLOW, 50000, monsterYaml.getInt(mob+".Potion.Slow")));
				if(monsterYaml.getInt(mob+".Potion.Strength")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.INCREASE_DAMAGE, 50000, monsterYaml.getInt(mob+".Potion.Strength")));
				if(monsterYaml.getInt(mob+".Potion.Weak")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.WEAKNESS, 50000, monsterYaml.getInt(mob+".Potion.Weak")));
				if(monsterYaml.getInt(mob+".Potion.JumpBoost")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.JUMP, 50000, monsterYaml.getInt(mob+".Potion.JumpBoost")));
				if(monsterYaml.getInt(mob+".Potion.FireRegistance")!=0)
					Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.FIRE_RESISTANCE, 50000, monsterYaml.getInt(mob+".Potion.FireRegistance")));
				if(monsterYaml.getInt(mob+".Potion.WaterBreath")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.WATER_BREATHING, 50000, monsterYaml.getInt(mob+".Potion.WaterBreath")));
				if(monsterYaml.getInt(mob+".Potion.Invisible")!=0)
				Monster.addPotionEffect(PottionBuff.getPotionEffect(PotionEffectType.INVISIBILITY, 50000, monsterYaml.getInt(mob+".Potion.Invisible")));
			}
			Damageable m = Monster;
			m.setMaxHealth(monsterYaml.getInt(mob + ".HP")+0.5);
			m.setHealth(m.getMaxHealth());
		}
		
		
		
		if(DungeonSpawning != -1)
		{
			YamlLoader monsterYaml = new YamlLoader();
			monsterYaml.getConfig("Monster/MonsterList.yml");
			/*
    		���� �ĺ� �ڵ� ���� ���� GroupNumber �ڵ�� ������ ���� �׷��� �����ϱ� ��������,
    		�� ���� �׷� �ڵ尡 ���� ���Ͱ� �ݰ� 20 �̳��� ���� ���, ���� ���̺갡 �����ų�
    		���� ������ �ȴ�. ���� �׷� �ڵ�� 0 ~ f ���� �����Ѵ�.
			 */
			if(mob==null)
			{
				switch(DungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					Monster.setCustomName("��2��0��2��e��"+Group+"��f");
					break;
				case 2://2�� == ���� ���̺� ����
					Monster.setCustomName("��2��0��2��1��"+Group+"��f");
					break;
				case 3://3�� == ���� ���� ��
					Monster.setCustomName("��2��0��2��4��"+Group+"��f");
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					Monster.setCustomName("��2��0��2��2��"+Group+"��f");
					break;
				}
			}
			else
			{
				String a = monsterYaml.getString(mob + ".Name").replace("&", "��");
				switch(DungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					Monster.setCustomName("��2��0��2��e��"+Group+"��f"+a);
					break;
				case 2://2�� == ���� ���̺� ����
					Monster.setCustomName("��2��0��2��1��"+Group+"��f"+a);
					break;
				case 3://3�� == ���� ���� ��
					Monster.setCustomName("��2��0��2��4��"+Group+"��f"+a);
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					Monster.setCustomName("��2��0��2��2��"+Group+"��f"+a);
					break;
				}
			}
			ItemStack Equip = monsterYaml.getItemStack(mob+".Head.Item");
			int itemnumber = 30;
			if(Equip!=null && Equip.getType() != Material.AIR)
			{
				if(Equip.hasItemMeta())
				{
					ItemMeta im = Equip.getItemMeta();
					im.setLore(Arrays.asList("xyz:"+XYZloc[0]+","+XYZloc[1]+","+XYZloc[2]));
					Equip.setItemMeta(im);
					Monster.getEquipment().setHelmet(Equip);
				}
				else
				{
					ItemStack Icon = new MaterialData(267, (byte) 0).toItemStack(1);
					ItemMeta Icon_Meta = Icon.getItemMeta();
					Icon_Meta.setLore(Arrays.asList("xyz:"+XYZloc[0]+","+XYZloc[1]+","+XYZloc[2]));
					Equip.setItemMeta(Icon_Meta);
					Monster.getEquipment().setHelmet(Equip);
				}
			}
			else
			{
				ItemStack i = new ItemStack(itemnumber);
				i.setAmount(1);
				ItemMeta im = i.getItemMeta();
				im.setLore(Arrays.asList("xyz:"+XYZloc[0]+","+XYZloc[1]+","+XYZloc[2]));
				i.setItemMeta(im);
				Monster.getEquipment().setHelmet(i);
			}
			Monster.getEquipment().setHelmetDropChance(0.00000000000000000F);
		}
		return Monster;
	}
	
	private EnderCrystal getEnderCrystal(EnderCrystal Monster, String mob, byte DungeonSpawning, int[] XYZLoc, char Group)
	{
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		if(DungeonSpawning != -1)
		{
			/*
    		���� �ĺ� �ڵ� ���� ���� GroupNumber �ڵ�� ������ ���� �׷��� �����ϱ� ��������,
    		�� ���� �׷� �ڵ尡 ���� ���Ͱ� �ݰ� 20 �̳��� ���� ���, ���� ���̺갡 �����ų�
    		���� ������ �ȴ�. ���� �׷� �ڵ�� 0 ~ f ���� �����Ѵ�.
			 */
			if(mob.charAt(0)=='��')
			{
				switch(DungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					Monster.setCustomName("��2��0��2��e��"+Group+"��f");
					break;
				case 2://2�� == ���� ���̺� ����
					Monster.setCustomName("��2��0��2��1��"+Group+"��f");
					break;
				case 3://3�� == ���� ���� ��
					Monster.setCustomName("��2��0��2��4��"+Group+"��f");
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					Monster.setCustomName("��2��0��2��2��"+Group+"��f");
					break;
				}
			}
			else
			{
				String a = monsterYaml.getString(mob + ".Name").replace("&", "��");
				switch(DungeonSpawning)
				{
				case 1://1�� == �Ϲ�
					Monster.setCustomName("��2��0��2��e��"+Group+"��f"+a);
					break;
				case 2://2�� == ���� ���̺� ����
					Monster.setCustomName("��2��0��2��1��"+Group+"��f"+a);
					break;
				case 3://3�� == ���� ���� ��
					Monster.setCustomName("��2��0��2��4��"+Group+"��f"+a);
					break;
				case 4://4�� == ���� [������ ���� Ž���ϱ� ���ؼ� ����� öâ �߾��� ��ġ�� ���� ���Ǳ׿� ���� ��Ų��.]
					Monster.setCustomName("��2��0��2��2��"+Group+"��f"+a);
					break;
				}
			}
		}
		else
			Monster.setCustomName(monsterYaml.getString(mob + ".Name").replace("&", "��"));
		Monster.setCustomNameVisible(true);
		return Monster;
	}

	
	private static void Stack(String Display, int ID, byte DATA, byte STACK, List<String> Lore, byte Loc, Inventory inventory)
	{
		ItemStack Icon = new MaterialData(ID, (byte) DATA).toItemStack(STACK);
		ItemMeta Icon_Meta = Icon.getItemMeta();
		Icon_Meta.setDisplayName(Display);
		Icon_Meta.setLore(Lore);
		Icon.setItemMeta(Icon_Meta);
		inventory.setItem(Loc, Icon);
		return;
	}
	
	public void SpawnEggGive(Player player, String mob)
	{
		ItemStack Icon = new MaterialData(383, (byte) 0).toItemStack(1);
		ItemMeta Icon_Meta = Icon.getItemMeta();
		Icon_Meta.setDisplayName("��c"+mob);
		Icon_Meta.addEnchant(Enchantment.DURABILITY, 1, true);
		Icon_Meta.setLore(Arrays.asList("��8"+mob+"���� ����"));
		Icon.setItemMeta(Icon_Meta);
		player.getInventory().addItem(Icon);
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
	
	public short getMonsterID(String MonsterType)
	{
		switch(MonsterType)
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

	public String getMonsterCustomName(String MonsterType)
	{
		switch(MonsterType)
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