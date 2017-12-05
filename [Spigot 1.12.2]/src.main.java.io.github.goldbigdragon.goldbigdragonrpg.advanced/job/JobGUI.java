package job;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import effect.SoundEffect;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class JobGUI extends UtilGui
{
	public void ChooseSystemGUI(Player player)
	{
		String UniqueCode = "��0��0��6��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0�ý��� ����");
		
		removeFlagStack("��6��l������", 346,0,1,Arrays.asList("��7������ ���丮�ʹ� �ٸ���","��7�����Ӱ� ��ų�� ���� �� �� �ֽ��ϴ�.","��7���� ������ ���� ������","��7ī�װ����� ��ų�� �����ϴ�.","","��a������ : ��e||||||||||||||||||||","��a�밡�� : ��e||||||||||||||||||||","","��c[���� ������ �������� ��츸 ����]"), 12, inv);
		removeFlagStack("��c��l������ ���丮", 40,0,1,Arrays.asList("��7������ʹ� �ٸ���","��7�������� ��ų�� �����Ǿ� �ֽ��ϴ�.","��7���� ������ �����ϱ� ������","��7������ �� �±޺��� ��ų�� �����ϴ�.","","��a������ : ��e||||||��7||||||||||||||","��a�밡�� : ��e|||||||||||��7|||||||||","","��c[���� ������ ������ ���丮�� ��츸 ����]"), 14, inv);
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7�۾� ������ â�� �ݽ��ϴ�."), 26, inv);
		player.openInventory(inv);
	}

	public void MapleStory_ChooseJob(Player player, short page)
	{
	  	YamlLoader jobYaml = new YamlLoader();
	  	YamlLoader configYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");
		configYaml.getConfig("config.yml");

		String UniqueCode = "��0��0��6��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0[MapleStory] ��ü ���� ��� : " + (page+1));

		String[] jobList = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < jobList.length;count++)
		{
			int jobMaxLevel = jobYaml.getConfigurationSection("MapleStory."+jobList[count]).getKeys(false).size();
			
			if(count > jobList.length || loc >= 45) break;
			
			if(jobList[count].equalsIgnoreCase(configYaml.getString("Server.DefaultJob")))
				removeFlagStack("��f��l" + jobList[count], 403,0,1,Arrays.asList("��3�ִ� �±� : ��f"+jobMaxLevel+"��3�� �±�","","��e[��Ŭ���� �±� ����]","��e��l[���� �⺻ ����]"), loc, inv);
			else
				removeFlagStack("��f��l" + jobList[count], 340,0,1,Arrays.asList("��3�ִ� �±� : ��f"+jobMaxLevel+"��3�� �±�","","��e[��Ŭ���� �±� ����]","��e[Shift + ��Ŭ���� ���� �⺻ ���� ����]","��c[Shift + ��Ŭ���� ���� ����]","��c�÷��̾� ���� �������� ���� �������ϴ�."), loc, inv);
			
			loc++;
		}
		
		if(jobList.length-(page*44)>45)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ����", 386,0,1,Arrays.asList("��7���ο� ������ �����մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void MapleStory_JobSetting(Player player, String JobName)
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");

		String UniqueCode = "��0��0��6��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0[MapleStory] ���� ����");

		String[] jobList = jobYaml.getConfigurationSection("MapleStory."+JobName).getKeys(false).toArray(new String[0]);

		for(int count = 0; count < jobList.length;count++)
		{
			short JobSkillList= (short) jobYaml.getConfigurationSection("MapleStory."+JobName+"."+jobList[count]+".Skill").getKeys(false).size();
			
			short IconID = (short) jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".IconID");
			byte IconData = (byte) jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".IconData");
			byte IconAmount = (byte) jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".IconAmount");

			int NeedLevel = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedLV");
			int NeedSTR = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedSTR");
			int NeedDEX = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedDEX");
			int NeedINT = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedINT");
			int NeedWILL = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedWILL");
			int NeedLUK = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedLUK");
			String PrevJob = jobYaml.getString("MapleStory."+JobName+"."+jobList[count]+".PrevJob");
			if(PrevJob.equalsIgnoreCase("null") == true)
				PrevJob = "����";
			if(count == 0)
				removeFlagStack("��f��l" + jobList[count] , IconID,IconData,IconAmount,Arrays.asList("��3��ϵ� ��ų �� : ��f"+JobSkillList+ "��3��","��a�±� �ʿ� ���� : ��f"+NeedLevel+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statSTR+" : ��f"+NeedSTR+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statDEX+" : ��f"+NeedDEX+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statINT+" : ��f"+NeedINT+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statWILL+" : ��f"+NeedWILL+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statLUK+" : ��f"+NeedLUK+"��a �̻�"	,"��a���� �±� �ܰ� : ��f"+PrevJob,"","��e[�� Ŭ���� ���� ��ų ���]","��e[�� Ŭ���� ���� ��ų Ȯ��]","��e[Shift + �� Ŭ���� �±� ���� ����]","��e��l[�⺻ Ŭ����]"), count, inv);
			else
				removeFlagStack("��f��l" + jobList[count], IconID,IconData,IconAmount,Arrays.asList("��3��ϵ� ��ų �� : ��f"+JobSkillList+ "��3��","��a�±� �ʿ� ���� : ��f"+NeedLevel+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statSTR+" : ��f"+NeedSTR+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statDEX+" : ��f"+NeedDEX+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statINT+" : ��f"+NeedINT+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statWILL+" : ��f"+NeedWILL+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statLUK+" : ��f"+NeedLUK+"��a �̻�"	,"��a���� �±� �ܰ� : ��f"+PrevJob,"","��e[�� Ŭ���� ���� ��ų ���]","��e[�� Ŭ���� ���� ��ų Ȯ��]","��e[Shift + �� Ŭ���� �±� ���� ����]","��c[Shift + ��Ŭ���� �±� ����]","��c�÷��̾ �������� ���� �������ϴ�."), count, inv);
		}
		
		removeFlagStack("��f��l�� �±�", 386,0,1,Arrays.asList("��7���ο� �±� Ŭ������ ����ϴ�."), 22, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+JobName), 26, inv);
		player.openInventory(inv);
	}
	
	public void Mabinogi_ChooseCategory(Player player, short page)
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");

		String UniqueCode = "��0��0��6��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0[Mabinogi] ��ü ī�װ� ��� : " + (page+1));

		String[] jobList = jobYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < jobList.length;count++)
		{
			if(!jobList[count].equals("Added"))
			{
				if(count > jobList.length || loc >= 45) break;
				short SkillNumber = (short) jobYaml.getConfigurationSection("Mabinogi."+jobList[count]).getKeys(false).size();
				removeFlagStack("��f��l" + jobList[count], 403,0,1,Arrays.asList("��3��ϵ� ��ų : ��f"+SkillNumber+"��3 ��","","��e[�� Ŭ���� ��ų ���]","��e[Shift + �� Ŭ���� ��ų ����]","��c[Shift + ��Ŭ���� ī�װ� ����]"), loc, inv);
				loc++;
			}
		}
		
		if(jobList.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ī�װ�", 386,0,1,Arrays.asList("��7���ο� ī�װ��� �����մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void Mabinogi_SkillSetting(Player player, short page, String CategoriName)
	{
	  	YamlLoader jobYaml = new YamlLoader();
	  	jobYaml.getConfig("Skill/JobList.yml");

		String UniqueCode = "��0��0��6��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0[Mabinogi] ��ų ���� ��� : " + (page+1));

	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");
		String[] mabinogiCategoriList = jobYaml.getConfigurationSection("Mabinogi."+CategoriName).getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < mabinogiCategoriList.length;count++)
		{
			boolean SkillPublic = jobYaml.getBoolean("Mabinogi."+CategoriName+"."+mabinogiCategoriList[count]);
			if(count > mabinogiCategoriList.length || loc >= 45) break;

			short IconID = (short) skillYaml.getInt(mabinogiCategoriList[count]+".ID");
			byte IconData = (byte) skillYaml.getInt(mabinogiCategoriList[count]+".DATA");
			byte IconAmount = (byte) skillYaml.getInt(mabinogiCategoriList[count]+".Amount");
			
			if(SkillPublic == true)
				removeFlagStack("��f��l" + mabinogiCategoriList[count], IconID,IconData,IconAmount,Arrays.asList("","��3[   �⺻ ��ų   ]","��7���� ���ӽ� �⺻������","��7�־����� ��ų�Դϴ�.","","��e[�� Ŭ���� ���� ��ų ��ȯ]","��c[Shift + ��Ŭ���� ��ų ����]","","��c     ��  ����  ��     ","��c���� ��ų���� ��ȯ �ϴ���","��c���� �ش� ��ų�� �˰� �ִ�","��c������ ��ų�� �������� ������,","��c�ű� �������� ��ų �ڵ� ���游","��c�Ұ��� �ϰ� �˴ϴ�."), loc, inv);
			else
				removeFlagStack("��f��l" + mabinogiCategoriList[count], IconID,IconData,IconAmount,Arrays.asList("","��5[   ���� ��ų   ]","��7å�� �аų� ����Ʈ ������ ���Ͽ�","��7���� �� �ִ� ��ų�Դϴ�.","","��e[�� Ŭ���� �⺻ ��ų ��ȯ]","��c[Shift + ��Ŭ���� ��ų ����]","","��c     ��  ����  ��     ","��c�⺻ ��ų���� ��ȯ ��ų ���","��c���� ������ ��� �ο�����,","��c�׸��� ��ȯ ���Ŀ� ������ ���","��c�ű� �����鿡�� �ش� ��ų�� �־����ϴ�.","��c[���� ������ ���� ����� �� �߻�]"), loc, inv);
			loc++;
		}
		
		if(mabinogiCategoriList.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ CategoriName), 53, inv);
		player.openInventory(inv);
	}
	
	public void AddedSkillsListGUI(Player player, int page, String JobName, String JobNick)
	{
		YamlLoader jobYaml = new YamlLoader();
		YamlLoader skillYaml = new YamlLoader();
	  	skillYaml.getConfig("Skill/SkillList.yml");
		jobYaml.getConfig("Skill/JobList.yml");

		String UniqueCode = "��0��0��6��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��ϵ� ��ų ��� : " + (page+1));

		String[] skillList = jobYaml.getConfigurationSection("MapleStory."+JobName+"."+JobNick+".Skill").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < skillList.length;count++)
		{
			if(count > skillList.length || loc >= 45) break;
			removeFlagStack("��f��l" + skillList[count].toString(),  skillYaml.getInt(skillList[count]+".ID"),skillYaml.getInt(skillList[count]+".DATA"),skillYaml.getInt(skillList[count]+".Amount"),Arrays.asList("","��c[Shift + �� Ŭ���� ��ų ����]"), loc, inv);

			loc++;
		}
		
		if(skillList.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+JobNick), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+JobName), 53, inv);
		player.openInventory(inv);
	}
	
	
	
	
	public void ChooseSystemGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 26)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 18)//���� ���
				new OPboxGui().opBoxGuiMain(player,(byte) 2);
			else if(slot == 12)//������ Ÿ�� ī�װ� ���
				Mabinogi_ChooseCategory(player,(short) 0);
			else if(slot == 14)//�����ý��丮 Ÿ�� ���� ���
				MapleStory_ChooseJob(player,(short) 0);
		}
	}

	public void MapleStory_ChooseJobClick(InventoryClickEvent event)
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
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				ChooseSystemGUI(player);
			else if(slot == 48)//���� ������
				MapleStory_ChooseJob(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 49)//�� ����
			{
				player.closeInventory();
				player.sendMessage("��d[����] : ���ο� ���� �̸��� ������ �ּ���!");
				UserDataObject u = new UserDataObject();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "CJ");
			}
			else if(slot == 50)//���� ������
				MapleStory_ChooseJob(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				if(event.isLeftClick()==true&&event.isRightClick()==false)
					MapleStory_JobSetting(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����")==true&&event.isShiftClick()==true
				&&event.isLeftClick()==true)
				{
					configYaml.set("Server.DefaultJob", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					configYaml.saveConfig();
					MapleStory_ChooseJob(player, (short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1));
				}
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����")==true&&event.isShiftClick()==true
				&&event.isRightClick()==true)
				{
					if(configYaml.getString("Server.DefaultJob").equalsIgnoreCase(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())))
					{
						SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[����] : ���� �⺻ ������ ������ �� �����ϴ�!");
					}
					else
					{
						YamlLoader jobYaml = new YamlLoader();
						SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
						jobYaml.getConfig("Skill/JobList.yml");
						jobYaml.removeKey("MapleStory."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						jobYaml.saveConfig();
						MapleStory_ChooseJob(player, (short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1));
						configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
						configYaml.saveConfig();
						new job.JobMain().AllPlayerFixAllSkillAndJobYML();
					}
				}
			}
		}
	}

	public void MapleStory_JobSettingClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 26)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			String JobName = ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(1));
			YamlLoader jobYaml = new YamlLoader();
			jobYaml.getConfig("Skill/JobList.yml");
			
			if(slot == 18)//���� ���
				MapleStory_ChooseJob(player, (short) 0);
			else if(slot == 22)//�� �±�
			{
				short NowJobLV = (short) jobYaml.getConfigurationSection("MapleStory."+JobName).getKeys(false).size();
				if(NowJobLV == 18)
				{
					SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c[����] : �ִ� �±��� 18�� ���� �Դϴ�!");
					return;
				}
				else
				{
					player.closeInventory();
					player.sendMessage("��d[����] : ��e"+ JobName+"��d�� �� �±� ���� �̸��� ������ �ּ���!");
					UserDataObject u = new UserDataObject();
					u.setType(player, "Job");
					u.setString(player, (byte)1, "CJL");
					u.setString(player, (byte)2, JobName);
				}
			}
			else
			{
				String JobNick = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				if(event.isLeftClick()==true&&event.isShiftClick()==false)
				{
					UserDataObject u = new UserDataObject();
					u.setType(player, "Job");
					u.setString(player, (byte)2, JobNick);
					u.setString(player, (byte)3, JobName);
					new skill.OPboxSkillGui().AllSkillsGUI(player,(short) 0, true,"Maple");
				}
				else if(event.isShiftClick()==true&&event.isLeftClick()==true)
				{
					player.closeInventory();
					player.sendMessage("��d[����] : ��e"+ JobNick+"��d�� �±� �ʿ� ������ �Է� �ϼ���!");

					UserDataObject u = new UserDataObject();
					u.setType(player, "Job");
					u.setString(player, (byte)1, "JNL");
					u.setString(player, (byte)2, JobName);
					u.setString(player, (byte)3, JobNick);
					
				}
				else if(event.isShiftClick()==false&&event.isRightClick()==true)
				{
					SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					AddedSkillsListGUI(player, 0, JobName, JobNick);
				}
				else if(event.isShiftClick()==true&&event.isRightClick()==true)
				{
					if(event.getSlot() == 0)
					{
						SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[����] : �⺻ Ŭ������ ������ �� �����ϴ�!");
						return;
					}
					else
					{
						SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
						jobYaml.removeKey("MapleStory."+JobName+"."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						jobYaml.saveConfig();
						MapleStory_JobSetting(player, JobName);
						YamlLoader configYaml = new YamlLoader();
						configYaml.getConfig("config.yml");
						configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
						configYaml.saveConfig();
						new job.JobMain().AllPlayerFixAllSkillAndJobYML();
					}
				}
			}
		}
	}

	public void AddedSkillsListGUIClick(InventoryClickEvent event)
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
			String JobName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			String JobNick = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			
			if(slot == 45)//���� ���
				MapleStory_JobSetting(player, JobName);
			else if(slot == 48)//���� ������
				AddedSkillsListGUI(player, page-1, JobName, JobNick);
			else if(slot == 50)//���� ������
				AddedSkillsListGUI(player, page+1, JobName, JobNick);
			else
			{
				if(event.isShiftClick()==true&&event.isRightClick()==true)
				{
					YamlLoader jobYaml = new YamlLoader();
					jobYaml.getConfig("Skill/JobList.yml");
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					jobYaml.removeKey("MapleStory."+JobName+"."+JobNick+".Skill."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					jobYaml.saveConfig();
					AddedSkillsListGUI(player, page, JobName, JobNick);
					YamlLoader configYaml = new YamlLoader();
					configYaml.getConfig("config.yml");
					configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
					configYaml.saveConfig();
					new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				}
			}
		}
	}
	
	public void Mabinogi_ChooseCategoryClick(InventoryClickEvent event)
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
				ChooseSystemGUI(player);
			else if(slot == 48)//���� ������
				Mabinogi_ChooseCategory(player,(short) (page-1));
			else if(slot == 50)//���� ������
				Mabinogi_ChooseCategory(player,(short) (page+1));
			else if(slot == 49)//�� ī�װ�
			{
				player.sendMessage("��d[ī�װ�] : �� ī�װ��� �̸��� ������ �ּ���!");
				UserDataObject u = new UserDataObject();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "CC");
				player.closeInventory();
			}
			else
			{
				String CategoriName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				if(event.isLeftClick()==true)
				{
					if(event.isShiftClick() == false)
					{
						skill.OPboxSkillGui OGUI = new skill.OPboxSkillGui();
						OGUI.AllSkillsGUI(player, (short) 0, true, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					}
					else
						Mabinogi_SkillSetting(player, (short) 0, CategoriName);
				}
				else if(event.isShiftClick()==true&&event.isRightClick()==true)
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					YamlLoader jobYaml = new YamlLoader();
					jobYaml.getConfig("Skill/JobList.yml");
					String[] addedSkillList = jobYaml.getConfigurationSection("Mabinogi.Added").getKeys(false).toArray(new String[0]);
					for(int count = 0; count <addedSkillList.length;count++)
					{
						if(jobYaml.getString("Mabinogi.Added."+addedSkillList[count]).equals(CategoriName))
							jobYaml.removeKey("Mabinogi.Added."+addedSkillList[count]);
					}
					jobYaml.removeKey("Mabinogi."+CategoriName);
					jobYaml.saveConfig();
					Mabinogi_ChooseCategory(player,page);
					YamlLoader configYaml = new YamlLoader();
					configYaml.getConfig("config.yml");
					configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
					configYaml.saveConfig();
					new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				}
			}
		}
	}
	
	public void Mabinogi_SkillSettingClick(InventoryClickEvent event)
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
			String CategoriName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			if(slot == 45)//���� ���
				Mabinogi_ChooseCategory(player,(short) 0);
			else if(slot == 48)//���� ������
				Mabinogi_SkillSetting(player,(short) page,CategoriName);
			else if(slot == 50)//���� ������
				Mabinogi_SkillSetting(player,(short) page,CategoriName);
			else
			{
				String SkillName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				YamlLoader jobYaml = new YamlLoader();
				YamlLoader configYaml = new YamlLoader();
				jobYaml.getConfig("Skill/JobList.yml");
				configYaml.getConfig("config.yml");
				if(event.isLeftClick()==true)
				{
					if(jobYaml.getBoolean("Mabinogi."+CategoriName+"."+SkillName) == true)
					{
						jobYaml.set("Mabinogi."+CategoriName+"."+SkillName, false);
						jobYaml.saveConfig();
						Mabinogi_SkillSetting(player,(short) page,CategoriName);
					}
					else
					{
						jobYaml.set("Mabinogi."+CategoriName+"."+SkillName, true);
						jobYaml.saveConfig();
						Mabinogi_SkillSetting(player,(short) page,CategoriName);
						configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
						configYaml.saveConfig();
						new job.JobMain().AllPlayerFixAllSkillAndJobYML();
					}
				}
				else if(event.isShiftClick()==true&&event.isRightClick()==true)
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					jobYaml.removeKey("Mabinogi.Added."+SkillName);
					jobYaml.removeKey("Mabinogi."+CategoriName+"."+SkillName);
					jobYaml.saveConfig();
					Mabinogi_SkillSetting(player, (short) page, CategoriName);
					configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
					configYaml.saveConfig();
					new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				}
			}
		}
	}
	
}