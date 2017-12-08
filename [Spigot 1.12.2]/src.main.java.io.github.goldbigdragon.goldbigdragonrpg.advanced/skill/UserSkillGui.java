package skill;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.material.*;

import effect.SoundEffect;
import user.StatsGui;
import util.UtilGui;
import util.YamlLoader;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UserSkillGui extends UtilGui
{
	public void MainSkillsListGUI(Player player, short page)
	{
		YamlLoader Config = new YamlLoader();
		Config.getConfig("config.yml");
		YamlLoader PlayerSkillList = new YamlLoader();
		PlayerSkillList.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
		Inventory inv = null;
		if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
		{
			String UniqueCode = "��0��0��b��0��3��r";
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������ ���� : " + (page+1));
			Object[] a= PlayerSkillList.getConfigurationSection("MapleStory").getKeys(false).toArray();

			byte loc=0;
			for(int count = page*45; count < a.length;count++)
			{
				String JobName = a[count].toString();
				short SkillAmount= (short) PlayerSkillList.getConfigurationSection("MapleStory."+a[count].toString()+".Skill").getKeys(false).size();
				if(count > a.length || loc >= 45) break;
			
				removeFlagStack("��f��l" + JobName,  340,0,1,Arrays.asList("��3��ų ���� : ��f"+SkillAmount +"��3 ��"), loc, inv);
				loc++;
			}
			if(a.length-(page*44)>45)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		}
		else
		{
			String UniqueCode = "��0��0��b��0��4��r";
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0ī�װ� ���� : " + (page+1));
			Object[] Categori= PlayerSkillList.getConfigurationSection("Mabinogi").getKeys(false).toArray();

			byte loc=0;
			for(int count = (short) (page*45); count < Categori.length;count++)
			{
				String CategoriName = Categori[count].toString();
				short SkillAmount= (short) PlayerSkillList.getConfigurationSection("Mabinogi."+Categori[count].toString()).getKeys(false).size();
				if(count > Categori.length || loc >= 45) break;
			
				removeFlagStack("��f��l" + CategoriName,  340,0,1,Arrays.asList("��3��ų ���� : ��f"+SkillAmount +"��3 ��"), loc, inv);
				loc++;
			}
			if(Categori.length-(page*44)>45)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
			if(page!=0)
				removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		}

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void SkillListGUI(Player player, short page,boolean isMabinogi, String CategoriName)
	{
		YamlLoader Config = new YamlLoader();
		Config.getConfig("config.yml");
		YamlLoader PlayerSkillList = new YamlLoader();
		PlayerSkillList.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
		YamlLoader AllSkillList = new YamlLoader();
		AllSkillList.getConfig("Skill/SkillList.yml");
		Inventory inv = null;
		String UniqueCode = "��0��0��b��0��5��r";
		if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
		{
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��ų ��� : " + (page+1));
			Object[] a= PlayerSkillList.getConfigurationSection("MapleStory."+CategoriName+".Skill").getKeys(false).toArray();

			byte loc=0;
			for(int count = page*45; count < a.length;count++)
			{
				short SkillRank= (short) PlayerSkillList.getInt("MapleStory."+CategoriName+".Skill."+a[count]);
				short SkillMaxRank = (short) AllSkillList.getConfigurationSection(a[count]+".SkillRank").getKeys(false).size();
				int IConID = AllSkillList.getInt(a[count]+".ID");
				byte IConDATA = (byte) AllSkillList.getInt(a[count]+".DATA");
				byte IConAmount = (byte) AllSkillList.getInt(a[count]+".Amount");
				if(count > a.length || loc >= 45) break;
			
				String Skilllore = "��3��ų ��ũ : " +SkillRank +" / "+SkillMaxRank+"%enter%"+AllSkillList.getString(a[count]+".SkillRank."+SkillRank+".Lore");
				Skilllore = Skilllore +"%enter%%enter%��e[�� Ŭ���� ����Ű ���]";

				if(SkillRank<SkillMaxRank)
				{
					Skilllore = Skilllore +"%enter%��e[Shift + �� Ŭ���� ��ũ ��]%enter%";
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint() < AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint"))
					{
						Skilllore = Skilllore +"%enter%��c�ʿ� ��ų ����Ʈ : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint");
						Skilllore = Skilllore +"%enter%��c���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					else
					{
						Skilllore = Skilllore +"%enter%��a�ʿ� ��ų ����Ʈ : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint");
						Skilllore = Skilllore +"%enter%��a���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					if(AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() < AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel"))
						{
							Skilllore = Skilllore +"%enter%��c�ּ� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel");
							Skilllore = Skilllore +"%enter%��c���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
						else
						{
							Skilllore = Skilllore +"%enter%��a�ּ� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel");
							Skilllore = Skilllore +"%enter%��a���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
					if(AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() < AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel"))
						{
							Skilllore = Skilllore +"%enter%��c�ּ� ���� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel");
							Skilllore = Skilllore +"%enter%��c���� ���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
						}
						else
						{
							Skilllore = Skilllore +"%enter%��a�ּ� ���� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel");
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
		else
		{
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��ų ��� : " + (page+1));
			Object[] a= PlayerSkillList.getConfigurationSection("Mabinogi."+CategoriName).getKeys(false).toArray();

			byte loc=0;
			for(int count = page*45; count < a.length;count++)
			{
				short SkillRank= (short) PlayerSkillList.getInt("Mabinogi."+CategoriName+"."+a[count]);
				short SkillMaxRank = (short) AllSkillList.getConfigurationSection(a[count]+".SkillRank").getKeys(false).size();
				int IConID = AllSkillList.getInt(a[count]+".ID");
				byte IConDATA = (byte) AllSkillList.getInt(a[count]+".DATA");
				byte IConAmount = (byte) AllSkillList.getInt(a[count]+".Amount");
				if(count > a.length || loc >= 45) break;
			
				String Skilllore = "��3��ų ��ũ : " +SkillRank +" / "+SkillMaxRank+"%enter%"+AllSkillList.getString(a[count]+".SkillRank."+SkillRank+".Lore");
				Skilllore = Skilllore +"%enter%%enter%��e[�� Ŭ���� ����Ű ���]";

				if(SkillRank<SkillMaxRank)
				{
					Skilllore = Skilllore +"%enter%��e[Shift + �� Ŭ���� ��ũ ��]%enter%";
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint() < AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint"))
					{
						Skilllore = Skilllore +"%enter%��c�ʿ� ��ų ����Ʈ : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint");
						Skilllore = Skilllore +"%enter%��c���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					else
					{
						Skilllore = Skilllore +"%enter%��a�ʿ� ��ų ����Ʈ : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".SkillPoint");
						Skilllore = Skilllore +"%enter%��a���� ��ų ����Ʈ : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint();
					}
					if(AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() < AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel"))
						{
							Skilllore = Skilllore +"%enter%��c�ּ� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel");
							Skilllore = Skilllore +"%enter%��c���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
						else
						{
							Skilllore = Skilllore +"%enter%��a�ּ� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedLevel");
							Skilllore = Skilllore +"%enter%��a���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
						}
					if(AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel") > 0)
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() < AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel"))
						{
							Skilllore = Skilllore +"%enter%��c�ּ� ���� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel");
							Skilllore = Skilllore +"%enter%��c���� ���� ���� : "+main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
						}
						else
						{
							Skilllore = Skilllore +"%enter%��a�ּ� ���� ���� : "+AllSkillList.getInt(a[count]+".SkillRank."+(SkillRank+1)+".NeedRealLevel");
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
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+ CategoriName), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+isMabinogi), 53, inv);
		player.openInventory(inv);
	}
	
	public void AddQuickBarGUI(Player player,boolean isMabinogi,  String CategoriName, String Skillname)
	{
		String UniqueCode = "��0��0��b��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 18, UniqueCode + "��0������ ���");

		for(int count = 0;count < 9;count++)
		{
			if(player.getInventory().getItem(count) == null)
				removeFlagStack("��3��l|||||||||||||||||||||[�� ����]|||||||||||||||||||||", 160,9,1,Arrays.asList("","��e��l[Ŭ���� �ֹٿ� ��� �˴ϴ�]"),count, inv);
			else
				removeFlagStack("��c��l||||||||||||||||||||||||||[�����]||||||||||||||||||||||||||", 160,14,1,Arrays.asList("","��c��l[�� ������ ����� �� �����ϴ�]"),count, inv);
		}
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+isMabinogi), 9, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+Skillname,"��0"+CategoriName), 17, inv);
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
				MainSkillsListGUI(player, (short) (page-1));
			else if(slot == 50)//���� ������
				MainSkillsListGUI(player, (short) (page+1));
			else
				SkillListGUI(player, (short) 0, false, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
		}
	}
	
	public void Mabinogi_MainSkillsListGUIClick(InventoryClickEvent event)
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
				MainSkillsListGUI(player, (short) (page-1));
			else if(slot == 50)//���� ������
				MainSkillsListGUI(player, (short) (page+1));
			else
				SkillListGUI(player, page, true, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
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
			String CategoriName = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				MainSkillsListGUI(player, (short) 0);
			else if(slot == 48)//���� ������
				SkillListGUI(player, (short) (page-1), isMabinogi, CategoriName);
			else if(slot == 50)//���� ������
				SkillListGUI(player, (short) (page+1), isMabinogi, CategoriName);
			else
			{
				if(event.isLeftClick() == true && event.isShiftClick() == false)
					AddQuickBarGUI(player, isMabinogi,CategoriName,ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isLeftClick() == true && event.isShiftClick() == true)
				{
					String SkillName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					YamlLoader PlayerSkillList = new YamlLoader();
					PlayerSkillList.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
					YamlLoader AllSkillList = new YamlLoader();
					AllSkillList.getConfig("Skill/SkillList.yml");
					short SkillRank = 1;
					short SkillMaxRank = (short) AllSkillList.getConfigurationSection(SkillName+".SkillRank").getKeys(false).size();
					if(isMabinogi==false)
						SkillRank= (short) PlayerSkillList.getInt("MapleStory."+CategoriName+".Skill."+SkillName);
					else
						SkillRank= (short) PlayerSkillList.getInt("Mabinogi."+CategoriName+"."+SkillName);
					if(SkillRank<SkillMaxRank)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() < AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".NeedLevel"))
						{
							SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[��ų] : ������ �����մϴ�!");
							return;
						}
						else if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() < AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".NeedRealLevel"))
						{
							SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[��ų] : ���� ������ �����մϴ�!");
							return;
						}
						else if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_SkillPoint() >= AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".SkillPoint"))
						{
							if(isMabinogi == false)
								PlayerSkillList.set("MapleStory."+CategoriName+".Skill."+SkillName, SkillRank+1);
							else
								PlayerSkillList.set("Mabinogi."+CategoriName+"."+SkillName, SkillRank+1);
							
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_SkillPoint(-1 * AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".SkillPoint"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxHP(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusHP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_HP(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusHP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MaxMP(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusMP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MP(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusMP"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Balance(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusBAL"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Critical(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusCRI"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_STR(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusSTR"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEX(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusDEX"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_INT(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusINT"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_WILL(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusWILL"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_LUK(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusLUK"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_DEF(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusDEF"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Protect(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusPRO"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_DEF(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusMDEF"));
							main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_Magic_Protect(AllSkillList.getInt(SkillName+".SkillRank."+(SkillRank+1)+".BonusMPRO"));
							PlayerSkillList.saveConfig();
							SoundEffect.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 1.7F);

							if(main.MainServerOption.MagicSpellsCatched == true)
							{
								otherplugins.SpellMain MS = new otherplugins.SpellMain();
								MS.setPlayerMaxAndNowMana(player);
							}
							Damageable p = player;
							p.setMaxHealth(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_MaxHP());
							p.setHealth(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_HP());
							
							if(SkillRank!=SkillMaxRank-1)
								player.sendMessage("��a[��ų] : ��e"+SkillName+"��a ��ų�� ��e��ũ�� ��¡�a�Ͽ����ϴ�!");
							else
								player.sendMessage("��5[��ų] : ��e"+SkillName+"��5 ��ų�� ��e�����͡�5�Ͽ����ϴ�!");
							
							SkillListGUI(player, page, isMabinogi, CategoriName);
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
	
	public void AddQuickBarGUIClick(InventoryClickEvent event)
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
			String CategoriName = ChatColor.stripColor(event.getInventory().getItem(17).getItemMeta().getLore().get(2));
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 9)//���� ���
				SkillListGUI(player, (short) 0, isMabinogi, CategoriName);
			else
			{
				String Skillname = ChatColor.stripColor(event.getInventory().getItem(17).getItemMeta().getLore().get(1));
				if(player.getInventory().getItem(event.getSlot()) == null)
				{
					YamlLoader AllSkillList = new YamlLoader();
					AllSkillList.getConfig("Skill/SkillList.yml");
					int IconID = AllSkillList.getInt(Skillname+".ID");
					byte IconDATA = (byte) AllSkillList.getInt(Skillname+".DATA");
					byte IconAmount = (byte) AllSkillList.getInt(Skillname+".Amount");
					String lore = "��f"+CategoriName+"%enter%��f"+Skillname+"%enter%%enter%��e[Ŭ���� �����Կ��� ����]%enter%";
					String[] scriptA = lore.split("%enter%");
					for(int counter = 0; counter < scriptA.length; counter++)
						scriptA[counter] =  scriptA[counter];
					
					ItemStack Icon = new MaterialData(IconID, (byte) IconDATA).toItemStack(IconAmount);
					ItemMeta Icon_Meta = Icon.getItemMeta();
					Icon_Meta.setDisplayName("��a     [��ų ����Ű]     ");
					Icon_Meta.setLore(Arrays.asList(scriptA));
					Icon.setItemMeta(Icon_Meta);
					player.getInventory().setItem(event.getSlot(), Icon);
					SkillListGUI(player, (short) 0, isMabinogi, CategoriName);
				}
				else
					AddQuickBarGUI(player, isMabinogi,CategoriName,Skillname);
			}
		}
	}
}
