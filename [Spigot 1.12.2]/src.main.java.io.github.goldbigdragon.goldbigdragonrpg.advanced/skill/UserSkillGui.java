package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import effect.SoundEffect;
import user.StatsGui;
import util.UtilGui;
import util.YamlLoader;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UserSkillGui extends UtilGui
{
	public void mainSkillsListGUI(Player player, short page)
	{
		YamlLoader config = new YamlLoader();
		config.getConfig("config.yml");
		YamlLoader playerSkillList = new YamlLoader();
		playerSkillList.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
		Inventory inv = null;
		if( ! config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
		{
			String uniqueCode = "��0��0��b��0��3��r";
			inv = Bukkit.createInventory(null, 54, uniqueCode + "��0������ ���� : " + (page+1));
			String[] playerSkills= playerSkillList.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);

			byte loc=0;
			for(int count = page*45; count < playerSkills.length;count++)
			{
				String jobName = playerSkills[count];
				int skillAmount= playerSkillList.getConfigurationSection("MapleStory."+playerSkills[count]+".Skill").getKeys(false).size();
				if(count > playerSkills.length || loc >= 45) break;
			
				removeFlagStack("��f��l" + jobName,  340,0,1,Arrays.asList("��3��ų ���� : ��f"+skillAmount +"��3 ��"), loc, inv);
				loc++;
			}
			if(playerSkills.length-(page*44)>45)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		}
		else
		{
			String uniqueCode = "��0��0��b��0��4��r";
			inv = Bukkit.createInventory(null, 54, uniqueCode + "��0ī�װ� ���� : " + (page+1));
			Object[] categori= playerSkillList.getConfigurationSection("Mabinogi").getKeys(false).toArray();

			byte loc=0;
			int skillAmount = 0;
			for(int count = (page*45); count < categori.length;count++)
			{
				String categoriName = categori[count].toString();
				skillAmount = playerSkillList.getConfigurationSection("Mabinogi."+categori[count].toString()).getKeys(false).size();
				if(count > categori.length || loc >= 45) break;
			
				removeFlagStack("��f��l" + categoriName,  340,0,1,Arrays.asList("��3��ų ���� : ��f"+skillAmount +"��3 ��"), loc, inv);
				loc++;
			}
			if(categori.length-(page*44)>45)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		}

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void skillListGUI(Player player, short page,boolean isMabinogi, String categoriName)
	{
		YamlLoader config = new YamlLoader();
		config.getConfig("config.yml");
		YamlLoader playerSkillList = new YamlLoader();
		playerSkillList.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
		YamlLoader allSkillList = new YamlLoader();
		allSkillList.getConfig("Skill/SkillList.yml");
		Inventory inv = null;
		String uniqueCode = "��0��0��b��0��5��r";
		if(! config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
		{
			inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��ų ��� : " + (page+1));
			Object[] a= playerSkillList.getConfigurationSection("MapleStory."+categoriName+".Skill").getKeys(false).toArray();

			byte loc=0;
			for(int count = page*45; count < a.length;count++)
			{
				int skillRank= playerSkillList.getInt("MapleStory."+categoriName+".Skill."+a[count]);
				int skillMaxRank = allSkillList.getConfigurationSection(a[count]+".SkillRank").getKeys(false).size();
				int id = allSkillList.getInt(a[count]+".ID");
				int data = allSkillList.getInt(a[count]+".DATA");
				int amount = allSkillList.getInt(a[count]+".Amount");
				if(count > a.length || loc >= 45) break;
			
				String skilllore = "��3��ų ��ũ : " +skillRank +" / "+skillMaxRank+"%enter%"+allSkillList.getString(a[count]+".SkillRank."+skillRank+".Lore");
				skilllore = skilllore +"%enter%%enter%��e[�� Ŭ���� ����Ű ���]";

				if(skillRank<skillMaxRank)
				{
					skilllore = skilllore +"%enter%��e[Shift + �� Ŭ���� ��ũ ��]%enter%";
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint() < allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".SkillPoint"))
					{
						skilllore = skilllore +"%enter%��c�ʿ� ��ų ����Ʈ : "+allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".SkillPoint");
						skilllore = skilllore +"%enter%��c���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					else
					{
						skilllore = skilllore +"%enter%��a�ʿ� ��ų ����Ʈ : "+allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".SkillPoint");
						skilllore = skilllore +"%enter%��a���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					if(allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() < allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedLevel"))
						{
							skilllore = skilllore +"%enter%��c�ּ� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedLevel");
							skilllore = skilllore +"%enter%��c���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
						else
						{
							skilllore = skilllore +"%enter%��a�ּ� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedLevel");
							skilllore = skilllore +"%enter%��a���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
					if(allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedRealLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() < allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedRealLevel"))
						{
							skilllore = skilllore +"%enter%��c�ּ� ���� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedRealLevel");
							skilllore = skilllore +"%enter%��c���� ���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
						}
						else
						{
							skilllore = skilllore +"%enter%��a�ּ� ���� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(skillRank+1)+".NeedRealLevel");
							skilllore = skilllore +"%enter%��a���� ���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
						}
				}
				
				String[] scriptA = skilllore.split("%enter%");
				for(int counter = 0; counter < scriptA.length; counter++)
					scriptA[counter] =  scriptA[counter];
				removeFlagStack("��f��l" + a[count],  id,data,amount,Arrays.asList(scriptA), loc, inv);
				
				loc++;
			}
			if(a.length-(page*44)>45)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		}
		else
		{
			inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��ų ��� : " + (page+1));
			Object[] a= playerSkillList.getConfigurationSection("Mabinogi."+categoriName).getKeys(false).toArray();

			byte loc=0;
			for(int count = page*45; count < a.length;count++)
			{
				short SkillRank= (short) playerSkillList.getInt("Mabinogi."+categoriName+"."+a[count]);
				short SkillMaxRank = (short) allSkillList.getConfigurationSection(a[count]+".SkillRank").getKeys(false).size();
				int IConID = allSkillList.getInt(a[count]+".ID");
				byte IConDATA = (byte) allSkillList.getInt(a[count]+".DATA");
				byte IConAmount = (byte) allSkillList.getInt(a[count]+".Amount");
				if(count > a.length || loc >= 45) break;
			
				String Skilllore = "��3��ų ��ũ : " +SkillRank +" / "+SkillMaxRank+"%enter%"+allSkillList.getString(a[count]+".SkillRank."+SkillRank+".Lore");
				Skilllore = Skilllore +"%enter%%enter%��e[�� Ŭ���� ����Ű ���]";

				if(SkillRank<SkillMaxRank)
				{
					Skilllore = Skilllore +"%enter%��e[Shift + �� Ŭ���� ��ũ ��]%enter%";
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint() < allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint"))
					{
						Skilllore = Skilllore +"%enter%��c�ʿ� ��ų ����Ʈ : "+allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint");
						Skilllore = Skilllore +"%enter%��c���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					else
					{
						Skilllore = Skilllore +"%enter%��a�ʿ� ��ų ����Ʈ : "+allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint");
						Skilllore = Skilllore +"%enter%��a���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					if(allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() < allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel"))
						{
							Skilllore = Skilllore +"%enter%��c�ּ� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel");
							Skilllore = Skilllore +"%enter%��c���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
						else
						{
							Skilllore = Skilllore +"%enter%��a�ּ� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel");
							Skilllore = Skilllore +"%enter%��a���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
					if(allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() < allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel"))
						{
							Skilllore = Skilllore +"%enter%��c�ּ� ���� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel");
							Skilllore = Skilllore +"%enter%��c���� ���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
						}
						else
						{
							Skilllore = Skilllore +"%enter%��a�ּ� ���� ���� : "+allSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel");
							Skilllore = Skilllore +"%enter%��a���� ���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
						}
				}
				String[] scriptA = Skilllore.split("%enter%");
				for(int counter = 0; counter < scriptA.length; counter++)
					scriptA[counter] =  scriptA[counter];
				removeFlagStack("��f��l" + a[count],  IConID,IConDATA,IConAmount,Arrays.asList(scriptA), loc, inv);
				
				loc++;
			}
			if(a.length-(page*44)>45)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		}
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+ categoriName), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+isMabinogi), 53, inv);
		player.openInventory(inv);
	}
	
	public void addQuickBarGUI(Player player,boolean isMabinogi,  String categoriName, String skillname)
	{
		String uniqueCode = "��0��0��b��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 18, uniqueCode + "��0������ ���");

		for(int count = 0;count < 9;count++)
		{
			if(player.getInventory().getItem(count) == null)
				removeFlagStack("��3��l|||||||||||||||||||||[�� ����]|||||||||||||||||||||", 160,9,1,Arrays.asList("","��e��l[Ŭ���� �ֹٿ� ��� �˴ϴ�]"),count, inv);
			else
				removeFlagStack("��c��l||||||||||||||||||||||||||[�����]||||||||||||||||||||||||||", 160,14,1,Arrays.asList("","��c��l[�� ������ ����� �� �����ϴ�]"),count, inv);
		}
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+isMabinogi), 9, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+skillname,"��0"+categoriName), 17, inv);
		player.openInventory(inv);
	}
	
	
	
	public void MapleStory_MainSkillsListGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new StatsGui().StatusGUI(player);
			else if(slot == 48)//���� ������
				mainSkillsListGUI(player, (short) (page-1));
			else if(slot == 50)//���� ������
				mainSkillsListGUI(player, (short) (page+1));
			else
				skillListGUI(player, (short) 0, false, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
		}
	}
	
	public void mabinogiMainSkillsListGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		
		Player player = (Player) event.getWhoClicked();
		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new StatsGui().StatusGUI(player);
			else if(slot == 48)//���� ������
				mainSkillsListGUI(player, (short) (page-1));
			else if(slot == 50)//���� ������
				mainSkillsListGUI(player, (short) (page+1));
			else
				skillListGUI(player, page, true, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
		}
	}
	
	
	public void SkillListGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		
		if(slot == 53)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			boolean isMabinogi = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			String categoriName = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				mainSkillsListGUI(player, (short) 0);
			else if(slot == 48)//���� ������
				skillListGUI(player, (short) (page-1), isMabinogi, categoriName);
			else if(slot == 50)//���� ������
				skillListGUI(player, (short) (page+1), isMabinogi, categoriName);
			else
			{
				if(event.isLeftClick() && ! event.isShiftClick())
					addQuickBarGUI(player, isMabinogi,categoriName,ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isLeftClick() && event.isShiftClick())
				{
					String skillName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					YamlLoader playerSkillList = new YamlLoader();
					playerSkillList.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
					YamlLoader allSkillList = new YamlLoader();
					allSkillList.getConfig("Skill/SkillList.yml");
					int skillRank = 1;
					int skillMaxRank = allSkillList.getConfigurationSection(skillName+".SkillRank").getKeys(false).size();
					if(isMabinogi)
						skillRank= playerSkillList.getInt("Mabinogi."+categoriName+"."+skillName);
					else
						skillRank= playerSkillList.getInt("MapleStory."+categoriName+".Skill."+skillName);
					if(skillRank<skillMaxRank)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() < allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".NeedLevel"))
						{
							SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[��ų] : ������ �����մϴ�!");
							return;
						}
						else if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() < allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".NeedRealLevel"))
						{
							SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[��ų] : ���� ������ �����մϴ�!");
							return;
						}
						else if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint() >= allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".SkillPoint"))
						{
							if(isMabinogi)
								playerSkillList.set("Mabinogi."+categoriName+"."+skillName, skillRank+1);
							else
								playerSkillList.set("MapleStory."+categoriName+".Skill."+skillName, skillRank+1);
							
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_SkillPoint(-1 * allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".SkillPoint"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxHP(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusHP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_HP(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusHP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxMP(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusMP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MP(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusMP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Balance(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusBAL"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Critical(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusCRI"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_STR(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusSTR"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEX(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusDEX"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_INT(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusINT"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_WILL(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusWILL"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_LUK(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusLUK"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEF(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusDEF"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Protect(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusPRO"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_DEF(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusMDEF"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_Protect(allSkillList.getInt(skillName+".SkillRank."+(skillRank+1)+".BonusMPRO"));
							playerSkillList.saveConfig();
							SoundEffect.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 1.7F);

							if(main.MainServerOption.MagicSpellsCatched)
							{
								otherplugins.SpellMain magicspellMain = new otherplugins.SpellMain();
								magicspellMain.setPlayerMaxAndNowMana(player);
							}
							Damageable p = player;
							p.setMaxHealth(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_MaxHP());
							p.setHealth(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_HP());
							
							if(skillRank!=skillMaxRank-1)
								player.sendMessage("��a[��ų] : ��e"+skillName+"��a ��ų�� ��e��ũ�� ��¡�a�Ͽ����ϴ�!");
							else
								player.sendMessage("��5[��ų] : ��e"+skillName+"��5 ��ų�� ��e�����͡�5�Ͽ����ϴ�!");
							
							skillListGUI(player, page, isMabinogi, categoriName);
						}
						else
						{
							SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[��ų] : ���� ����Ʈ�� �����մϴ�!");
						}
					}
					else
						SoundEffect.playSound(player, Sound.BLOCK_ANVIL_LAND, 0.8F, 1.7F);
				}
			}
		}
	}
	
	public void addQuickBarGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 17)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			boolean isMabinogi = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(9).getItemMeta().getLore().get(1)));
			String categoriName = ChatColor.stripColor(event.getInventory().getItem(17).getItemMeta().getLore().get(2));
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 9)//���� ���
				skillListGUI(player, (short) 0, isMabinogi, categoriName);
			else
			{
				String skillname = ChatColor.stripColor(event.getInventory().getItem(17).getItemMeta().getLore().get(1));
				if(player.getInventory().getItem(event.getSlot()) == null)
				{
					YamlLoader allSkillList = new YamlLoader();
					allSkillList.getConfig("Skill/SkillList.yml");
					int id = allSkillList.getInt(skillname+".ID");
					int data = allSkillList.getInt(skillname+".DATA");
					int amount = allSkillList.getInt(skillname+".Amount");
					
					List<String> lore = new ArrayList<>();
					lore.add("��f"+categoriName);
					lore.add("��f"+skillname);
					lore.add("");
					lore.add("��e[Ŭ���� �����Կ��� ����]");
					lore.add("");
					
					ItemStack item = new ItemStack(id);
					item.setAmount(amount);
					item.setDurability((short) data);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName("��a     [��ų ����Ű]     ");
					meta.setLore(lore);
					item.setItemMeta(meta);
					player.getInventory().setItem(event.getSlot(), item);
					skillListGUI(player, (short) 0, isMabinogi, categoriName);
				}
				else
					addQuickBarGUI(player, isMabinogi,categoriName,skillname);
			}
		}
	}
}
