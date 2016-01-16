package OtherPlugins;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.Spell;
import com.nisovin.magicspells.Spell.SpellCastResult;
import com.nisovin.magicspells.events.ManaChangeEvent;
import com.nisovin.magicspells.events.SpellTargetEvent;
import com.nisovin.magicspells.events.SpellTargetLocationEvent;
import com.nisovin.magicspells.mana.ManaChangeReason;
import com.nisovin.magicspells.spells.passive.KillListener;

import GoldBigDragon_RPG.Main.Main;
import GoldBigDragon_RPG.Util.YamlController;
import GoldBigDragon_RPG.Util.YamlManager;
import GoldBigDragon_RPG.Event.Damage;

public class SpellMain implements Listener
{
	public SpellMain(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public SpellMain()
	{}

	@EventHandler
	public void MagicSpellsTargeted (SpellTargetEvent event)
	{
		event.setPower(event.getPower()+BonusPowerCalculator(event.getCaster(),event.getTarget()));
		//Bukkit.broadcastMessage(event.getCaster().getName()+"��"+event.getPower()+"������ ������ "+event.getTarget().getName()+"�� ����!");
	}
	@EventHandler
	public void MagicSpellsTargeted (SpellTargetLocationEvent event)
	{
		event.setPower(event.getPower()+BonusPowerCalculator(event.getCaster(),null));
		//Bukkit.broadcastMessage(event.getCaster().getName()+"��"+event.getPower()+"������ ���� ������ ����!");
	}
	
	public float BonusPowerCalculator (Player player, LivingEntity target)
	{
	    Damage damage = new Damage();
		GoldBigDragon_RPG.Config.StatConfig stat = new GoldBigDragon_RPG.Config.StatConfig();
		YamlController GUI_YC = GoldBigDragon_RPG.Main.Main.YC_2;
		YamlManager PlayerStat;
	  	if(GUI_YC.isExit("Stats/" + player.getUniqueId()+".yml") == false)
	  		stat.CreateNewStats(player);
	  	PlayerStat = GUI_YC.getNewConfig("Stats/" + player.getUniqueId()+".yml");
		Damage dam = new Damage();
		int bonuspower = 0;
		if(Main.PlayerUseSpell.containsKey(player) == true)
		{
			Damageable p = player;
			String switchCheck = Main.PlayerUseSpell.get(player);
			if(switchCheck.compareTo("�����")==0)
				bonuspower = dam.MagicSpellsDamageBonus((int) p.getHealth())+damage.getPlayerEquipmentStat(player, "HP")[0];
			else if(switchCheck.compareTo("����")==0)
				bonuspower = dam.MagicSpellsDamageBonus(getPlayerMana(player))+damage.getPlayerEquipmentStat(player, "MP")[0];
			else if(switchCheck.compareTo(GoldBigDragon_RPG.Main.ServerOption.STR)==0)
				bonuspower = PlayerStat.getInt("Stat.STR")+damage.getPlayerEquipmentStat(player, "STR")[0];
			else if(switchCheck.compareTo(GoldBigDragon_RPG.Main.ServerOption.DEX)==0)
				bonuspower = PlayerStat.getInt("Stat.DEX")+damage.getPlayerEquipmentStat(player, "DEX")[0];
			else if(switchCheck.compareTo(GoldBigDragon_RPG.Main.ServerOption.INT)==0)
				bonuspower = PlayerStat.getInt("Stat.INT")+damage.getPlayerEquipmentStat(player, "INT")[0];
			else if(switchCheck.compareTo(GoldBigDragon_RPG.Main.ServerOption.WILL)==0)
				bonuspower = PlayerStat.getInt("Stat.WILL")+damage.getPlayerEquipmentStat(player, "WILL")[0];
			else if(switchCheck.compareTo(GoldBigDragon_RPG.Main.ServerOption.LUK)==0)
				bonuspower = PlayerStat.getInt("Stat.LUK")+damage.getPlayerEquipmentStat(player, "LUK")[0];
			else
				bonuspower = 0;
		}
		int[] WeaponPower = dam.getSlotChangedPlayerEquipmentStat(player, "MagicDamage", Main.PlayerlastItem.get(player));
		int WeaponPowerFixed = new GoldBigDragon_RPG.Util.Number().RandomNum(WeaponPower[0], WeaponPower[1]);
		bonuspower = bonuspower+WeaponPowerFixed;
		int negativeBonus = 0;
		if(target!=null)
		{
			PlayerStat = GUI_YC.getNewConfig("Monster/MonsterList.yml");
			if(target.getType()==EntityType.PLAYER)
			{
				Player t = (Player) target;
				if(t.isOnline())
				{
				  	if(GUI_YC.isExit("Stats/" + t.getUniqueId()+".yml") == false)
				  		stat.CreateNewStats(t);
				  	PlayerStat = GUI_YC.getNewConfig("Stats/" + t.getUniqueId()+".yml");
				  	negativeBonus = dam.getMagicProtect(t, PlayerStat.getInt("Stat.INT"));
				}
				else
				{
					if(t.isCustomNameVisible() == true)
					{
						String name = ChatColor.stripColor(t.getCustomName().toString());
						Object[] monsterlist = PlayerStat.getConfigurationSection("").getKeys(false).toArray();
						
						for(int count = 0; count < monsterlist.length; count ++)
						{
							if(PlayerStat.getString(monsterlist[count].toString()+".Name").equals(name) == true)
							{
								negativeBonus = PlayerStat.getInt(monsterlist[count].toString()+".Magic_Protect");
							    break;
							}
						}
					}
				}
			}
			else
			{
				if(target.isCustomNameVisible() == true)
				{
					String name = ChatColor.stripColor(target.getCustomName().toString());
					Object[] monsterlist = PlayerStat.getConfigurationSection("").getKeys(false).toArray();
					
					for(int count = 0; count < monsterlist.length; count ++)
					{
						if(PlayerStat.getString(monsterlist[count].toString()+".Name").equals(name) == true)
						{
							negativeBonus = PlayerStat.getInt(monsterlist[count].toString()+".Magic_Protect");
						    break;
						}
					}
				}
			}
		}
		Main.PlayerUseSpell.put(player, "us");
		return (float) (bonuspower*0.006)-negativeBonus;
	}
	
	
	
	public void getSpellsList (Player player)
	{
		Collection<Spell> SC = MagicSpells.spells();
		Object[] SO = SC.toArray();
		for(int count = 0; count < SO.length; count++)
			player.sendMessage(SO[count].toString());
	}

	public void ShowAllMaigcGUI(Player player, int page,String SkillName, int SkillLevel,int sort)
	{
		Object[] spells = new Object[46];
		int temp = 0;
		int counter=0;
		switch(sort)
		{
		case 0:
			for(counter = 0;counter<46;counter++)
				if(counter+(page*45) < MagicSpells.spells().toArray().length)
					spells[counter] = MagicSpells.spells().toArray()[counter+(page*45)];
				else
					break;
			break;
		case 1:
			for(counter = 0;counter<46;)
				if(counter+(page*45) < MagicSpells.spells().toArray().length&&
					temp+(page*45) < MagicSpells.spells().toArray().length)
				{
					if(MagicSpells.spells().toArray()[temp+(page*45)].getClass().getSimpleName().equals("DummySpell"))
					{
						spells[counter] = MagicSpells.spells().toArray()[temp+(page*45)];
						counter = counter+1;
					}
					temp=temp+1;
				}
				else
					break;
			break;
		case 2:
			for(counter = 0;counter<46;)
				if(counter+(page*45) < MagicSpells.spells().toArray().length&&
					temp+(page*45) < MagicSpells.spells().toArray().length)
				{
					if(MagicSpells.spells().toArray()[temp+(page*45)].getClass().getSimpleName().equals("AreaEffectSpell"))
					{
						spells[counter] = MagicSpells.spells().toArray()[temp+(page*45)];
						counter = counter+1;
					}
					temp=temp+1;
				}
				else
					break;
			break;
		case 3:
			for(counter = 0;counter<46;)
				if(counter+(page*45) < MagicSpells.spells().toArray().length&&
					temp+(page*45) < MagicSpells.spells().toArray().length)
				{
					if(MagicSpells.spells().toArray()[temp+(page*45)].getClass().getSimpleName().equals("MultiSpell"))
					{
						spells[counter] = MagicSpells.spells().toArray()[temp+(page*45)];
						counter = counter+1;
					}
					temp=temp+1;
				}
				else
					break;
			break;
		case 4:
			for(counter = 0;counter<46;)
				if(counter+(page*45) < MagicSpells.spells().toArray().length&&
					temp+(page*45) < MagicSpells.spells().toArray().length)
				{
					if(!MagicSpells.spells().toArray()[temp+(page*45)].getClass().getSimpleName().equals("ParticleProjectileSpell"))
					{
						spells[counter] = MagicSpells.spells().toArray()[temp+(page*45)];
						counter = counter+1;
					}
					temp=temp+1;
				}
				else
					break;
			break;
		case 5:
			for(counter = 0;counter<46;)
				if(counter+(page*45) < MagicSpells.spells().toArray().length&&
					temp+(page*45) < MagicSpells.spells().toArray().length)
				{
					if(MagicSpells.spells().toArray()[temp+(page*45)].getClass().getSimpleName().equals("PainSpell"))
					{
						spells[counter] = MagicSpells.spells().toArray()[temp+(page*45)];
						counter = counter+1;
					}
					temp=temp+1;
				}
				else
					break;
			break;
		}
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLACK + "�������� ��� : " + (page+1));

		int loc=0;
		Spell spell;
		for(int count = 0; count < spells.length;count++)
		{
			spell = (Spell) spells[count];
			if(spell!=null)
			{
				String SpellName = spell.getName();
				Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + SpellName, 403,0,1,Arrays.asList(ChatColor.WHITE+spell.getClass().getSimpleName()+"",ChatColor.YELLOW+"[�� Ŭ���� ��ų ���]"), count, inv);
			}
		}
		
		if(counter>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		switch(sort)
		{
		case 0:
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "Ŭ������ ����", 154,0,1,Arrays.asList(ChatColor.GRAY + "[��� ����]",ChatColor.BLACK+""+sort), 49, inv);break;
		case 1:
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "Ŭ������ ����", 154,0,1,Arrays.asList(ChatColor.GRAY + "[���� ���� ����]",ChatColor.BLACK+""+sort), 49, inv);break;
		case 2:
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "Ŭ������ ����", 154,0,1,Arrays.asList(ChatColor.GRAY + "[���� ���� ����]",ChatColor.BLACK+""+sort), 49, inv);break;
		case 3:
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "Ŭ������ ����", 154,0,1,Arrays.asList(ChatColor.GRAY + "[��Ƽ ���� ����]",ChatColor.BLACK+""+sort), 49, inv);break;
		case 4:
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "Ŭ������ ����", 154,0,1,Arrays.asList(ChatColor.GRAY + "[P.P ���� ����]",ChatColor.BLACK+""+sort), 49, inv);break;
		case 5:
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "Ŭ������ ����", 154,0,1,Arrays.asList(ChatColor.GRAY + "[pain ���� ����]",ChatColor.BLACK+""+sort), 49, inv);break;
		}
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+""+SkillLevel), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+SkillName), 53, inv);
		player.openInventory(inv);
	}
	
	public void Stack2(String Display, int ID, int DATA, int Stack, List<String> Lore, int Loc, Inventory inventory)
	{
		ItemStack Icon = new MaterialData(ID, (byte) DATA).toItemStack(Stack);
		ItemMeta Icon_Meta = Icon.getItemMeta();
		Icon_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		Icon_Meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		Icon_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		Icon_Meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		Icon_Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		Icon_Meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		Icon_Meta.setDisplayName(Display);
		Icon_Meta.setLore(Lore);
		Icon.setItemMeta(Icon_Meta);
		inventory.setItem(Loc, Icon);
	}

	public void ShowAllMaigcGUIClick(InventoryClickEvent event)
	{
		int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
		String SkillName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		int SkillLevel = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1)));
		int sort = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(1)));
		
		Player player = (Player) event.getWhoClicked();
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();

		event.setCancelled(true);
		switch (event.getSlot())
		{
			case 45://���� �������
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 0.8F);
				new GoldBigDragon_RPG.GUI.OPBoxSkillGUI().SkillRankOptionGUI(player, SkillName, SkillLevel);
				break;
			case 48://���� ������
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 0.8F);
				ShowAllMaigcGUI(player, page-1, SkillName, SkillLevel, sort);
				break;
			case 49://���� ��� ����
				s.SP(player, Sound.PISTON_RETRACT, 1.0F, 0.8F);
				if(sort < 4)
					sort = sort +1;
				else
					sort = 0;
				ShowAllMaigcGUI(player, 0, SkillName, SkillLevel, sort);
				break;
			case 50://���� ������
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 0.8F);
				ShowAllMaigcGUI(player, page+1, SkillName, SkillLevel, sort);
				break;
			case 53://������
				s.SP(player, Sound.PISTON_RETRACT, 0.8F, 1.8F);
				player.closeInventory();
				return;
			default:
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 0.8F);
				YamlController GUI_YC = GoldBigDragon_RPG.Main.Main.YC_2;
				YamlManager SkillList  = GUI_YC.getNewConfig("Skill/SkillList.yml");
				SkillList.set(SkillName+".SkillRank."+SkillLevel+".MagicSpells", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				SkillList.saveConfig();
				new GoldBigDragon_RPG.GUI.OPBoxSkillGUI().SkillRankOptionGUI(player, SkillName, SkillLevel);
				return;
		}
	}
	
	
	public void CastSpell(Player player, String SpellName)
	{
		Object[] spells = MagicSpells.spells().toArray();
		Spell spell = null;
		for(int count = 0; count < spells.length;count++)
		{
			spell = (Spell) spells[count];
			if(spell.getName().equals(SpellName) == true)
			{
				spell.cast(player);
			}
		}
	}
	
	@EventHandler
	public void manaregen(ManaChangeEvent event)
	{//ManaChangeEvent
		if(event.getReason() == ManaChangeReason.REGEN)
		{
			Player player = event.getPlayer();
			setPlayerMaxAndNowMana(player);
		}
	}
	
	
	
	public int getPlayerMana(Player player)
	{
		return MagicSpells.getManaHandler().getMana(player);
	}

	public void setPlayerMaxAndNowMana(Player player)
	{
		YamlController Config_YC = GoldBigDragon_RPG.Main.Main.YC_2;
		YamlManager PlayerStats  = Config_YC.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");

		GoldBigDragon_RPG.Event.Damage d = new GoldBigDragon_RPG.Event.Damage();
		
		int BonusMana = d.getPlayerEquipmentStat(player, "����")[0];
		int MaxMana = PlayerStats.getInt("Stat.MAXMP")+BonusMana;
		int Mana = PlayerStats.getInt("Stat.MP")+BonusMana;
		if(MaxMana > 0)
		{
			try
			{
				MagicSpells.getManaHandler().setMaxMana(player, MaxMana);
			}
			catch(NullPointerException e)
			{				
			}
		}
		return;
	}

	public void setSlotChangePlayerMaxAndNowMana(Player player, ItemStack newSlot)
	{
		YamlController Config_YC = GoldBigDragon_RPG.Main.Main.YC_2;
		YamlManager PlayerStats  = Config_YC.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");

		GoldBigDragon_RPG.Event.Damage d = new GoldBigDragon_RPG.Event.Damage();
		
		int BonusMana = d.getSlotChangedPlayerEquipmentStat(player, "����",newSlot)[0];
		int MaxMana = PlayerStats.getInt("Stat.MAXMP")+BonusMana;

		if(MaxMana > 0 && Main.MagicSpellsEnable == true)
		{
			try
			{
				MagicSpells.getManaHandler().setMaxMana(player, MaxMana);
			}
			catch(NoClassDefFoundError e)
			{
				System.out.println("���� ������ com/nisovin/magicspells/MagicSpells Ŭ������ ã�� �� �����ϴ�!");
			}
			catch(NullPointerException e)
			{
			}
		}
		return;
	}
	
	public void DrinkManaPotion(Player player, int mana)
	{
		int m = MagicSpells.getManaHandler().getMana(player)+mana;
		if(MagicSpells.getManaHandler().getMana(player)+mana >= MagicSpells.getManaHandler().getMaxMana(player))
			m = MagicSpells.getManaHandler().getMaxMana(player);
		MagicSpells.getManaHandler().setMana(player, m, ManaChangeReason.POTION);
	}
}