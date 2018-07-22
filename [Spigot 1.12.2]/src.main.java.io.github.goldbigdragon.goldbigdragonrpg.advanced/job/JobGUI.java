package job;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import effect.SoundEffect;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class JobGUI extends GuiUtil
{
	public void chooseSystemGUI(Player player)
	{
		String uniqueCode = "��0��0��6��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 27, uniqueCode + "��0�ý��� ����");
		
		removeFlagStack("��6��l������", 346,0,1,Arrays.asList("��7������ ���丮�ʹ� �ٸ���","��7�����Ӱ� ��ų�� ���� �� �� �ֽ��ϴ�.","��7���� ������ ���� ������","��7ī�װ����� ��ų�� �����ϴ�.","","��a������ : ��e||||||||||||||||||||","��a�밡�� : ��e||||||||||||||||||||","","��c[���� ������ �������� ��츸 ����]"), 12, inv);
		removeFlagStack("��c��l������ ���丮", 40,0,1,Arrays.asList("��7������ʹ� �ٸ���","��7�������� ��ų�� �����Ǿ� �ֽ��ϴ�.","��7���� ������ �����ϱ� ������","��7������ �� �±޺��� ��ų�� �����ϴ�.","","��a������ : ��e||||||��7||||||||||||||","��a�밡�� : ��e|||||||||||��7|||||||||","","��c[���� ������ ������ ���丮�� ��츸 ����]"), 14, inv);
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7�۾� ������ â�� �ݽ��ϴ�."), 26, inv);
		player.openInventory(inv);
	}

	public void mapleStory_ChooseJob(Player player, int page)
	{
	  	YamlLoader jobYaml = new YamlLoader();
	  	YamlLoader configYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");
		configYaml.getConfig("config.yml");

		String uniqueCode = "��0��0��6��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0[MapleStory] ��ü ���� ��� : " + (page+1));

		String[] jobList = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
		
		int loc=0;
		int jobMaxLevel = 0;
		
		for(int count = page*45; count < jobList.length;count++)
		{
			jobMaxLevel = jobYaml.getConfigurationSection("MapleStory."+jobList[count]).getKeys(false).size();
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
	
	public void mapleStory_JobSetting(Player player, String JobName)
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");

		String uniqueCode = "��0��0��6��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 27, uniqueCode + "��0[MapleStory] ���� ����");

		String[] jobList = jobYaml.getConfigurationSection("MapleStory."+JobName).getKeys(false).toArray(new String[0]);

		int id = 0;
		int data = 0;
		int amount = 0;
		int jobSkillList = 0;
		int needLevel = 0;
		int needSTR = 0;
		int needDEX = 0;
		int needINT = 0;
		int needWILL = 0;
		int needLUK = 0;
		String prevJob = null;
		
		for(int count = 0; count < jobList.length;count++)
		{
			jobSkillList= jobYaml.getConfigurationSection("MapleStory."+JobName+"."+jobList[count]+".Skill").getKeys(false).size();
			
			id = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".IconID");
			data = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".IconData");
			amount = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".IconAmount");

			needLevel = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedLV");
			needSTR = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedSTR");
			needDEX = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedDEX");
			needINT = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedINT");
			needWILL = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedWILL");
			needLUK = jobYaml.getInt("MapleStory."+JobName+"."+jobList[count]+".NeedLUK");
			prevJob = jobYaml.getString("MapleStory."+JobName+"."+jobList[count]+".PrevJob");
			if(prevJob.equalsIgnoreCase("null"))
				prevJob = "����";
			if(count == 0)
				removeFlagStack("��f��l" + jobList[count] , id,data,amount,Arrays.asList("��3��ϵ� ��ų �� : ��f"+jobSkillList+ "��3��","��a�±� �ʿ� ���� : ��f"+needLevel+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statSTR+" : ��f"+needSTR+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statDEX+" : ��f"+needDEX+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statINT+" : ��f"+needINT+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statWILL+" : ��f"+needWILL+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statLUK+" : ��f"+needLUK+"��a �̻�"	,"��a���� �±� �ܰ� : ��f"+prevJob,"","��e[�� Ŭ���� ���� ��ų ���]","��e[�� Ŭ���� ���� ��ų Ȯ��]","��e[Shift + �� Ŭ���� �±� ���� ����]","��e��l[�⺻ Ŭ����]"), count, inv);
			else
				removeFlagStack("��f��l" + jobList[count], id,data,amount,Arrays.asList("��3��ϵ� ��ų �� : ��f"+jobSkillList+ "��3��","��a�±� �ʿ� ���� : ��f"+needLevel+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statSTR+" : ��f"+needSTR+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statDEX+" : ��f"+needDEX+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statINT+" : ��f"+needINT+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statWILL+" : ��f"+needWILL+"��a �̻�","��a�±� �ʿ� "+main.MainServerOption.statLUK+" : ��f"+needLUK+"��a �̻�"	,"��a���� �±� �ܰ� : ��f"+prevJob,"","��e[�� Ŭ���� ���� ��ų ���]","��e[�� Ŭ���� ���� ��ų Ȯ��]","��e[Shift + �� Ŭ���� �±� ���� ����]","��c[Shift + ��Ŭ���� �±� ����]","��c�÷��̾ �������� ���� �������ϴ�."), count, inv);
		}
		
		removeFlagStack("��f��l�� �±�", 386,0,1,Arrays.asList("��7���ο� �±� Ŭ������ ����ϴ�."), 22, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+JobName), 26, inv);
		player.openInventory(inv);
	}
	
	public void mabinogi_ChooseCategory(Player player, int page)
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");

		String uniqueCode = "��0��0��6��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0[Mabinogi] ��ü ī�װ� ��� : " + (page+1));

		String[] jobList = jobYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray(new String[0]);
		
		int loc=0;
		int skillNumber = 0;
		for(int count = page*45; count < jobList.length;count++)
		{
			if(!jobList[count].equals("Added"))
			{
				if(count > jobList.length || loc >= 45) break;
				skillNumber = jobYaml.getConfigurationSection("Mabinogi."+jobList[count]).getKeys(false).size();
				removeFlagStack("��f��l" + jobList[count], 403,0,1,Arrays.asList("��3��ϵ� ��ų : ��f"+skillNumber+"��3 ��","","��e[�� Ŭ���� ��ų ���]","��e[Shift + �� Ŭ���� ��ų ����]","��c[Shift + ��Ŭ���� ī�װ� ����]"), loc, inv);
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
	
	public void mabinogi_SkillSetting(Player player, int page, String CategoriName)
	{
	  	YamlLoader jobYaml = new YamlLoader();
	  	jobYaml.getConfig("Skill/JobList.yml");

		String uniqueCode = "��0��0��6��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0[Mabinogi] ��ų ���� ��� : " + (page+1));

	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");
		String[] mabinogiCategoriList = jobYaml.getConfigurationSection("Mabinogi."+CategoriName).getKeys(false).toArray(new String[0]);
		
		int loc = 0;
		int id = 0;
		int data = 0;
		int amount = 0;
		boolean skillPublic = false;
		for(int count = page*45; count < mabinogiCategoriList.length;count++)
		{
			skillPublic = jobYaml.getBoolean("Mabinogi."+CategoriName+"."+mabinogiCategoriList[count]);
			if(count > mabinogiCategoriList.length || loc >= 45) break;

			id = skillYaml.getInt(mabinogiCategoriList[count]+".ID");
			data = skillYaml.getInt(mabinogiCategoriList[count]+".DATA");
			amount = skillYaml.getInt(mabinogiCategoriList[count]+".Amount");
			
			if(skillPublic)
				removeFlagStack("��f��l" + mabinogiCategoriList[count], id,data,amount,Arrays.asList("","��3[   �⺻ ��ų   ]","��7���� ���ӽ� �⺻������","��7�־����� ��ų�Դϴ�.","","��e[�� Ŭ���� ���� ��ų ��ȯ]","��c[Shift + ��Ŭ���� ��ų ����]","","��c     ��  ����  ��     ","��c���� ��ų���� ��ȯ �ϴ���","��c���� �ش� ��ų�� �˰� �ִ�","��c������ ��ų�� �������� ������,","��c�ű� �������� ��ų �ڵ� ���游","��c�Ұ��� �ϰ� �˴ϴ�."), loc, inv);
			else
				removeFlagStack("��f��l" + mabinogiCategoriList[count], id,data,amount,Arrays.asList("","��5[   ���� ��ų   ]","��7å�� �аų� ����Ʈ ������ ���Ͽ�","��7���� �� �ִ� ��ų�Դϴ�.","","��e[�� Ŭ���� �⺻ ��ų ��ȯ]","��c[Shift + ��Ŭ���� ��ų ����]","","��c     ��  ����  ��     ","��c�⺻ ��ų���� ��ȯ ��ų ���","��c���� ������ ��� �ο�����,","��c�׸��� ��ȯ ���Ŀ� ������ ���","��c�ű� �����鿡�� �ش� ��ų�� �־����ϴ�.","��c[���� ������ ���� ����� �� �߻�]"), loc, inv);
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
	
	public void addedSkillsListGUI(Player player, int page, String JobName, String JobNick)
	{
		YamlLoader jobYaml = new YamlLoader();
		YamlLoader skillYaml = new YamlLoader();
	  	skillYaml.getConfig("Skill/SkillList.yml");
		jobYaml.getConfig("Skill/JobList.yml");

		String uniqueCode = "��0��0��6��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0��ϵ� ��ų ��� : " + (page+1));

		String[] skillList = jobYaml.getConfigurationSection("MapleStory."+JobName+"."+JobNick+".Skill").getKeys(false).toArray(new String[0]);
		
		int loc=0;
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
	
	
	public void chooseSystemGUIClick(InventoryClickEvent event)
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
				mabinogi_ChooseCategory(player,(short) 0);
			else if(slot == 14)//�����ý��丮 Ÿ�� ���� ���
				mapleStory_ChooseJob(player,(short) 0);
		}
	}

	public void mapleStory_ChooseJobClick(InventoryClickEvent event)
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
				chooseSystemGUI(player);
			else if(slot == 48)//���� ������
				mapleStory_ChooseJob(player, Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2);
			else if(slot == 49)//�� ����
			{
				player.closeInventory();
				player.sendMessage("��d[����] : ���ο� ���� �̸��� ������ �ּ���!");
				UserDataObject u = new UserDataObject();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "CJ");
			}
			else if(slot == 50)//���� ������
				mapleStory_ChooseJob(player, Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				String jobName = event.getCurrentItem().getItemMeta().getDisplayName().substring(4);
				YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				if(event.isLeftClick() && ! event.isRightClick())
					mapleStory_JobSetting(player, jobName);
				if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����")&&event.isShiftClick()
				&&event.isLeftClick())
				{
					configYaml.set("Server.DefaultJob", jobName);
					configYaml.saveConfig();
					mapleStory_ChooseJob(player, Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1);
				}
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����")&&event.isShiftClick()
				&&event.isRightClick())
				{
					if(configYaml.getString("Server.DefaultJob").equalsIgnoreCase(jobName))
					{
						SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[����] : ���� �⺻ ������ ������ �� �����ϴ�!");
					}
					else
					{
						YamlLoader jobYaml = new YamlLoader();
						SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
						jobYaml.getConfig("Skill/JobList.yml");
						jobYaml.removeKey("MapleStory."+ChatColor.stripColor(jobName));
						jobYaml.saveConfig();
						mapleStory_ChooseJob(player, (short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1));
						configYaml.set("Time.LastSkillChanged", new util.NumericUtil().RandomNum(0, 100000)-new util.NumericUtil().RandomNum(0, 100000));
						configYaml.saveConfig();
						new job.JobMain().AllPlayerFixAllSkillAndJobYML();
					}
				}
			}
		}
	}

	public void mapleStory_JobSettingClick(InventoryClickEvent event)
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
			String jobName = event.getInventory().getItem(26).getItemMeta().getLore().get(1).substring(2);
			YamlLoader jobYaml = new YamlLoader();
			jobYaml.getConfig("Skill/JobList.yml");
			
			if(slot == 18)//���� ���
				mapleStory_ChooseJob(player, (short) 0);
			else if(slot == 22)//�� �±�
			{
				short NowJobLV = (short) jobYaml.getConfigurationSection("MapleStory."+jobName).getKeys(false).size();
				if(NowJobLV == 18)
				{
					SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c[����] : �ִ� �±��� 18�� ���� �Դϴ�!");
					return;
				}
				else
				{
					player.closeInventory();
					player.sendMessage("��d[����] : ��e"+ jobName+"��d�� �� �±� ���� �̸��� ������ �ּ���!");
					UserDataObject u = new UserDataObject();
					u.setType(player, "Job");
					u.setString(player, (byte)1, "CJL");
					u.setString(player, (byte)2, jobName);
				}
			}
			else
			{
				String jobNick = event.getCurrentItem().getItemMeta().getDisplayName().substring(4);
				if(event.isLeftClick()&& ! event.isShiftClick())
				{
					UserDataObject u = new UserDataObject();
					u.setType(player, "Job");
					u.setString(player, (byte)2, jobNick);
					u.setString(player, (byte)3, jobName);
					new skill.OPboxSkillGui().AllSkillsGUI(player,(short) 0, true,"Maple");
				}
				else if(event.isShiftClick()&&event.isLeftClick())
				{
					player.closeInventory();
					player.sendMessage("��d[����] : ��e"+ jobNick+"��d�� �±� �ʿ� ������ �Է� �ϼ���!");

					UserDataObject u = new UserDataObject();
					u.setType(player, "Job");
					u.setString(player, (byte)1, "JNL");
					u.setString(player, (byte)2, jobName);
					u.setString(player, (byte)3, jobNick);
					
				}
				else if(! event.isShiftClick()&&event.isRightClick())
				{
					SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					addedSkillsListGUI(player, 0, jobName, jobNick);
				}
				else if(event.isShiftClick()&&event.isRightClick())
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
						jobYaml.removeKey("MapleStory."+jobName+"."+jobNick);
						jobYaml.saveConfig();
						mapleStory_JobSetting(player, jobName);
						YamlLoader configYaml = new YamlLoader();
						configYaml.getConfig("config.yml");
						configYaml.set("Time.LastSkillChanged", new util.NumericUtil().RandomNum(0, 100000)-new util.NumericUtil().RandomNum(0, 100000));
						configYaml.saveConfig();
						new job.JobMain().AllPlayerFixAllSkillAndJobYML();
					}
				}
			}
		}
	}

	public void addedSkillsListGUIClick(InventoryClickEvent event)
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
			String jobName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
			String jobNick = event.getInventory().getItem(45).getItemMeta().getLore().get(1).substring(2);
			int page = Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			
			if(slot == 45)//���� ���
				mapleStory_JobSetting(player, jobName);
			else if(slot == 48)//���� ������
				addedSkillsListGUI(player, page-1, jobName, jobNick);
			else if(slot == 50)//���� ������
				addedSkillsListGUI(player, page+1, jobName, jobNick);
			else
			{
				if(event.isShiftClick()&&event.isRightClick())
				{
					YamlLoader jobYaml = new YamlLoader();
					jobYaml.getConfig("Skill/JobList.yml");
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					jobYaml.removeKey("MapleStory."+jobName+"."+jobNick+".Skill."+event.getCurrentItem().getItemMeta().getDisplayName().substring(4));
					jobYaml.saveConfig();
					addedSkillsListGUI(player, page, jobName, jobNick);
					YamlLoader configYaml = new YamlLoader();
					configYaml.getConfig("config.yml");
					configYaml.set("Time.LastSkillChanged", new util.NumericUtil().RandomNum(0, 100000)-new util.NumericUtil().RandomNum(0, 100000));
					configYaml.saveConfig();
					new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				}
			}
		}
	}
	
	public void mabinogi_ChooseCategoryClick(InventoryClickEvent event)
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
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			if(slot == 45)//���� ���
				chooseSystemGUI(player);
			else if(slot == 48)//���� ������
				mabinogi_ChooseCategory(player,(short) (page-1));
			else if(slot == 50)//���� ������
				mabinogi_ChooseCategory(player,(short) (page+1));
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
				String categoriName = event.getCurrentItem().getItemMeta().getDisplayName().substring(4);
				if(event.isLeftClick())
				{
					if( ! event.isShiftClick())
						new skill.OPboxSkillGui().AllSkillsGUI(player, 0, true, categoriName);
					else
						mabinogi_SkillSetting(player, 0, categoriName);
				}
				else if(event.isShiftClick()&&event.isRightClick())
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					YamlLoader jobYaml = new YamlLoader();
					jobYaml.getConfig("Skill/JobList.yml");
					String[] addedSkillList = jobYaml.getConfigurationSection("Mabinogi.Added").getKeys(false).toArray(new String[0]);
					for(int count = 0; count <addedSkillList.length;count++)
					{
						if(jobYaml.getString("Mabinogi.Added."+addedSkillList[count]).equals(categoriName))
							jobYaml.removeKey("Mabinogi.Added."+addedSkillList[count]);
					}
					jobYaml.removeKey("Mabinogi."+categoriName);
					jobYaml.saveConfig();
					mabinogi_ChooseCategory(player,page);
					YamlLoader configYaml = new YamlLoader();
					configYaml.getConfig("config.yml");
					configYaml.set("Time.LastSkillChanged", new util.NumericUtil().RandomNum(0, 100000)-new util.NumericUtil().RandomNum(0, 100000));
					configYaml.saveConfig();
					new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				}
			}
		}
	}
	
	public void mabinogi_SkillSettingClick(InventoryClickEvent event)
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
			String categoriName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			if(slot == 45)//���� ���
				mabinogi_ChooseCategory(player, 0);
			else if(slot == 48)//���� ������
				mabinogi_SkillSetting(player, page,categoriName);
			else if(slot == 50)//���� ������
				mabinogi_SkillSetting(player, page,categoriName);
			else
			{
				String skillName = event.getCurrentItem().getItemMeta().getDisplayName().substring(4);
				YamlLoader jobYaml = new YamlLoader();
				YamlLoader configYaml = new YamlLoader();
				jobYaml.getConfig("Skill/JobList.yml");
				configYaml.getConfig("config.yml");
				if(event.isLeftClick())
				{
					if(jobYaml.getBoolean("Mabinogi."+categoriName+"."+skillName))
					{
						jobYaml.set("Mabinogi."+categoriName+"."+skillName, false);
						jobYaml.saveConfig();
						mabinogi_SkillSetting(player, page,categoriName);
					}
					else
					{
						jobYaml.set("Mabinogi."+categoriName+"."+skillName, true);
						jobYaml.saveConfig();
						mabinogi_SkillSetting(player, page,categoriName);
						configYaml.set("Time.LastSkillChanged", new util.NumericUtil().RandomNum(0, 100000)-new util.NumericUtil().RandomNum(0, 100000));
						configYaml.saveConfig();
						new job.JobMain().AllPlayerFixAllSkillAndJobYML();
					}
				}
				else if(event.isShiftClick()&&event.isRightClick())
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					jobYaml.removeKey("Mabinogi.Added."+skillName);
					jobYaml.removeKey("Mabinogi."+categoriName+"."+skillName);
					jobYaml.saveConfig();
					mabinogi_SkillSetting(player, page, categoriName);
					configYaml.set("Time.LastSkillChanged", new util.NumericUtil().RandomNum(0, 100000)-new util.NumericUtil().RandomNum(0, 100000));
					configYaml.saveConfig();
					new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				}
			}
		}
	}
	
}