package job;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import util.YamlLoader;




public class Job_Main
{	
	public void AllPlayerFixAllSkillAndJobYML()
	{
	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
	  	if(configYaml.contains("Time.LastSkillChanged")==false)
	  	{
	  		configYaml.set("Time.LastSkillChanged", -1);
	  		configYaml.saveConfig();
	  	}
	  	
    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
    	Player[] players = new Player[playerlist.size()];
    	playerlist.toArray(players);
		FixJobList();
	  	YamlLoader playerSkillYaml = new YamlLoader();
		for(int count = 0; count < players.length;count++)
		{
			playerSkillYaml.getConfig("Skill/PlayerData/"+players[count].getUniqueId().toString()+".yml");
	  		if(configYaml.getInt("Time.LastSkillChanged")!=playerSkillYaml.getInt("Update") || playerSkillYaml.contains("Update")==false)
	  		{
	  			playerSkillYaml.set("Update", configYaml.getInt("Time.LastSkillChanged"));
	  			playerSkillYaml.saveConfig();
	  			FixJobList();
				FixPlayerJobList(players[count]);
	  		}
		}
		return;
	}

	public void PlayerFixAllSkillAndJobYML(Player player)
	{
	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
	  	if(configYaml.contains("Time.LastSkillChanged")==false)
	  	{
	  		configYaml.set("Time.LastSkillChanged", -1);
	  		configYaml.saveConfig();
	  	}
	  	YamlLoader playerSkillYaml = new YamlLoader();
		playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
  		if(configYaml.getInt("Time.LastSkillChanged")!=playerSkillYaml.getInt("Update") || playerSkillYaml.contains("Update")==false)
  		{
  			playerSkillYaml.set("Update", configYaml.getInt("Time.LastSkillChanged"));
  			playerSkillYaml.saveConfig();
  			FixJobList();
			FixPlayerJobList(player);
  		}
		return;
	}
	
	public void AllPlayerSkillRankFix()
	{
    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
    	Player[] players = new Player[playerlist.size()];
    	playerlist.toArray(players);
		FixJobList();
		for(int count = 0; count < players.length;count++)
			SkillRankFix(players[count]);
		return;
	}
	
	public void FixJobList()
	//���� ��ų���� ��ų ��Ͽ� ��ϵ��� ���� ��ų�� ���� �� �ִ� �޼ҵ�
	{
	  	YamlLoader jobYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");
	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
		
		if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
		{
			String[] Categori = jobYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray(new String[0]);
			for(int counter = 0; counter < Categori.length; counter++)
			{
				if(Categori[counter].compareTo("Added")!=0)
				{
					String[] Skills = jobYaml.getConfigurationSection("Mabinogi."+Categori[counter]).getKeys(false).toArray(new String[0]);
					for(int countta = 0; countta < Skills.length; countta++)
					{
						if(skillYaml.contains(Skills[countta])==false)
							jobYaml.removeKey("Mabinogi."+Categori[counter]+"."+Skills[countta]);
					}
				}
			}
			jobYaml.saveConfig();
		}
		else
		{
			String[] Job = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
			for(int counter = 0; counter < Job.length; counter++)
			{
				String[] SubJob = jobYaml.getConfigurationSection("MapleStory."+Job[counter].toString()).getKeys(false).toArray(new String[0]);
				for(int count = 0; count < SubJob.length; count++)
				{
					String[] SubJobSkills = jobYaml.getConfigurationSection("MapleStory."+Job[counter].toString()+"."+SubJob[count]+".Skill").getKeys(false).toArray(new String[0]);
					for(int countta = 0; countta < SubJobSkills.length; countta++)
					{
						if(skillYaml.contains(SubJobSkills[countta])==false)
						{
							jobYaml.removeKey("MapleStory."+Job[counter]+"."+SubJob[count]+".Skill."+SubJobSkills[countta]);
							jobYaml.saveConfig();
						}
					}
				}
			}
		}
	}
	
	public void FixPlayerJobList(Player player)
	//���� �߿��� ���� ��Ͽ� ��ϵ��� ���� ������ ���� �÷��̾ ���� �� �ִ� �޼ҵ�
	//������ ���������� ������ ī�װ��� �÷��̾� ��ų ��Ͽ��� ������ �ָ�, ���� ���� ī�װ��� �÷��̾�� ����� �ش�.
	{
	  	YamlLoader playerSkillYaml = new YamlLoader();
	  	if(playerSkillYaml.isExit("Skill/PlayerData/"+player.getUniqueId().toString()+".yml") == false)
	  		new skill.Skill_Config().CreateNewPlayerSkill(player);

		playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
	  	YamlLoader jobYaml = new YamlLoader();
	  	YamlLoader skillYaml = new YamlLoader();
		jobYaml.getConfig("Skill/JobList.yml");
		skillYaml.getConfig("Skill/SkillList.yml");
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
		
		if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System"))
		{
			ArrayList<String> Categori = new ArrayList<String>();
			ArrayList<String> PlayerCategori = new ArrayList<String>();
			for(int count=0;count<playerSkillYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray().length;count++)
				PlayerCategori.add(playerSkillYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray()[count].toString());
			for(int count=0;count<jobYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray().length;count++)
				Categori.add(jobYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray()[count].toString());

			for(int count = 0; count < PlayerCategori.size(); count++)
				if(Categori.contains(PlayerCategori.get(count)) == false)
					playerSkillYaml.removeKey("Mabinogi."+PlayerCategori.get(count).toString());

			for(int count = 0; count < Categori.size(); count++)
				if(Categori.get(count).compareTo("Added") != 0)
					if(PlayerCategori.contains(Categori.get(count)) == false)
						playerSkillYaml.createSection("Mabinogi."+Categori.get(count));
			

			//���� ��ų�� �÷��̾� ��ų�� ���Ͽ� ������ ���� ��ų ����
			//���� ��ų�� �÷��̾� ��ų�� ���Ͽ� �÷��̾�� ���� ��ų ���
			//������ ī�װ��� ��ϵ� ��� ��ų�� ���ڿ��� ������ ��, �÷��̾ ���� ��ų�� �����Ͽ�
			//���� ī�װ����� ������ �÷��̾�� ��ų�� �ִٸ� ������ �ִ� ����.
			for(int count = 0; count < Categori.size(); count ++)
			{
				if(Categori.get(count).compareTo("Added") != 0)
				{
					ArrayList<String> JobSkillList = new ArrayList<String>();
					ArrayList<String> PlayerSkillList = new ArrayList<String>();
					for(int countta = 0; countta < jobYaml.getConfigurationSection("Mabinogi."+Categori.get(count)).getKeys(false).toArray().length; countta ++)
						JobSkillList.add(jobYaml.getConfigurationSection("Mabinogi."+Categori.get(count)).getKeys(false).toArray()[countta].toString());
					for(int countta = 0; countta < playerSkillYaml.getConfigurationSection("Mabinogi."+Categori.get(count)).getKeys(false).toArray().length; countta ++)
						PlayerSkillList.add(playerSkillYaml.getConfigurationSection("Mabinogi."+Categori.get(count)).getKeys(false).toArray()[countta].toString());
					for(int countta = 0; countta < PlayerSkillList.size(); countta++)
						if(JobSkillList.contains(PlayerSkillList.get(countta))==false)
							playerSkillYaml.removeKey("Mabinogi."+Categori.get(count)+"."+PlayerSkillList.get(countta));
					
					//���� ��ų ���� �Ϲ� ��ų���� �߷�����, �Ϲ� ��ų�� ���� �÷��̾��
					//��ų�� ������ �ִ� ����.
					for(int countta = 0; countta < JobSkillList.size(); countta++)
					{
						if(jobYaml.getBoolean("Mabinogi."+Categori.get(count) + "."+JobSkillList.get(countta)) == true)
							if(PlayerSkillList.contains(JobSkillList.get(countta))==false)
								playerSkillYaml.set("Mabinogi."+Categori.get(count)+"."+JobSkillList.get(countta), 1);
					}
					
					//��ų �ִ� ���� �ѱ�͵��� �ִ� ������ ���� �� �ֱ�.
					for(int countta = 0; countta < PlayerSkillList.size(); countta++)
					{
						short SkillMaxRank = (short) skillYaml.getConfigurationSection(PlayerSkillList.get(countta)+".SkillRank").getKeys(false).size();
						if(playerSkillYaml.getInt("Mabinogi."+Categori.get(count)+"."+PlayerSkillList.get(countta)) >  SkillMaxRank)
							playerSkillYaml.set("Mabinogi."+Categori.get(count)+"."+PlayerSkillList.get(countta), SkillMaxRank);
					}
				}
			}
			playerSkillYaml.saveConfig();
			
		}
		else//������ ���丮
		{
			String[] Jobs = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
			boolean isJobExit = false;
			for(int counter = 0; counter < Jobs.length; counter++)
				for(int count =0; count < jobYaml.getConfigurationSection("MapleStory."+Jobs[counter]).getKeys(false).size(); count++)
					if(jobYaml.getConfigurationSection("MapleStory."+Jobs[counter]).getKeys(false).toArray()[count].toString().compareTo(playerSkillYaml.getString("Job.Type"))==0)
					{
						isJobExit = true;
						break;
					}
			if(isJobExit==false)
			{
				String ServerDefaultJob = configYaml.getString("Server.DefaultJob");
				playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
				playerSkillYaml.set("Job.Type", ServerDefaultJob);
				playerSkillYaml.set("Job.LV", 1);
				String[] Skills = jobYaml.getConfigurationSection("MapleStory."+ServerDefaultJob+"."+ServerDefaultJob+".Skill").getKeys(false).toArray(new String[0]);
				for(int count = 0; count < Skills.length;count++)
					if(playerSkillYaml.contains("MapleStory."+ServerDefaultJob+".Skill."+Skills[count])==false)
						playerSkillYaml.set("MapleStory."+ServerDefaultJob+".Skill."+Skills[count],1);
				playerSkillYaml.saveConfig();
			}

			//������ ��ų �����ְ�, ����� ��ų �����ִ� ����
			String[] PlayerJob = playerSkillYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
			for(int counter = 0; counter < Jobs.length; counter++)
			{
				for(int count = 0; count < PlayerJob.length; count++)
				{
					String[] SubJobs = jobYaml.getConfigurationSection("MapleStory."+Jobs[counter]).getKeys(false).toArray(new String[0]);
					if(playerSkillYaml.contains("MapleStory."+PlayerJob[count]+".Skill")==false)
					{
						playerSkillYaml.createSection("MapleStory."+PlayerJob[count]+".Skill");
						playerSkillYaml.saveConfig();
					}
					for(int countta = 0; countta < SubJobs.length; countta++)
					{
						if(SubJobs[countta].compareTo(PlayerJob[count])==0)
						{
							ArrayList<String> SubJobSkills = new ArrayList<String>();
							ArrayList<String> PlayerJobSkills = new ArrayList<String>();
							
							for(int count1 = 0; count1 < jobYaml.getConfigurationSection("MapleStory."+Jobs[counter]+"."+SubJobs[countta]+".Skill").getKeys(false).toArray().length; count1 ++)
								SubJobSkills.add(jobYaml.getConfigurationSection("MapleStory."+Jobs[counter]+"."+SubJobs[countta]+".Skill").getKeys(false).toArray()[count1].toString());
							for(int count1 = 0; count1 < playerSkillYaml.getConfigurationSection("MapleStory."+PlayerJob[count]+".Skill").getKeys(false).toArray().length; count1 ++)
								PlayerJobSkills.add(playerSkillYaml.getConfigurationSection("MapleStory."+PlayerJob[count]+".Skill").getKeys(false).toArray()[count1].toString());
							
							for(int cc=0;cc<PlayerJobSkills.size();cc++)
								if(SubJobSkills.contains(PlayerJobSkills.get(cc))==false)
									playerSkillYaml.removeKey("MapleStory."+PlayerJob[count]+".Skill."+PlayerJobSkills.get(cc));

							for(int cc=0;cc<SubJobSkills.size();cc++)
								if(PlayerJobSkills.contains(SubJobSkills.get(cc))==false)
									playerSkillYaml.set("MapleStory."+SubJobs[countta]+".Skill."+SubJobSkills.get(cc),1);

							//��ų �ִ� ���� �ѱ�͵��� �ִ� ������ ���� �� �ֱ�.
							for(int cc = 0; cc < PlayerJobSkills.size();cc++)
							{
								if(skillYaml.contains(PlayerJobSkills.get(cc)+".SkillRank"))
								{
									short SkillMaxRank = (short) skillYaml.getConfigurationSection(PlayerJobSkills.get(cc)+".SkillRank").getKeys(false).size();
									if(playerSkillYaml.getInt("MapleStory."+PlayerJob[counter]+".Skill."+PlayerJobSkills.get(cc)) >  SkillMaxRank)
										playerSkillYaml.set("MapleStory."+PlayerJob[counter]+".Skill."+PlayerJobSkills.get(cc), SkillMaxRank);
								}
							}
							
							playerSkillYaml.saveConfig();
							
						}
					}
				}
			}
		}
		return;
	}
	
	public void SkillRankFix(Player player)
	{
	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");
	  	YamlLoader playerSkillYaml = new YamlLoader();
		playerSkillYaml.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");

		//��ų �ִ� �������� ������ ���� ��ų�� �ִ� ����ġ�� ������ �ִ� ����
		if(configYaml.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == true)
		{
			String[] CategoriList = playerSkillYaml.getConfigurationSection("Mabinogi").getKeys(false).toArray(new String[0]);
			playerSkillYaml.saveConfig();
			for(int count = 0; count < CategoriList.length; count ++)
			{
				String[] PlayerSkills = playerSkillYaml.getConfigurationSection("Mabinogi."+CategoriList[count]).getKeys(false).toArray(new String[0]);
				for(int countta = 0; countta < PlayerSkills.length; countta++)
				{
					short SkillMaxRank = (short) skillYaml.getConfigurationSection(PlayerSkills[countta]+".SkillRank").getKeys(false).size();
					if(playerSkillYaml.getInt("Mabinogi."+CategoriList[count]+"."+PlayerSkills[countta]) >  SkillMaxRank)
					{
						playerSkillYaml.set("Mabinogi."+CategoriList[count]+"."+PlayerSkills[countta], SkillMaxRank);
					}
				}
			}
		}
		else
		{
			if(playerSkillYaml.contains("MapleStory"))
			{
				String[] PlayerJob = playerSkillYaml.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
				for(int counter = 0; counter < PlayerJob.length; counter++)
				{
					String[] PlayerSkills = playerSkillYaml.getConfigurationSection("MapleStory."+PlayerJob[counter]+".Skill").getKeys(false).toArray(new String[0]);
					for(int countta = 0; countta < PlayerSkills.length;countta++)
					{
						short SkillMaxRank = (short) skillYaml.getConfigurationSection(PlayerSkills[countta]+".SkillRank").getKeys(false).size();
						if(playerSkillYaml.getInt("MapleStory."+PlayerJob[counter]+".Skill."+PlayerSkills[countta]) >  SkillMaxRank)
						{
							playerSkillYaml.set("MapleStory."+PlayerJob[counter]+".Skill."+PlayerSkills[countta], SkillMaxRank);
						}
					}
				}
			}
		}
		playerSkillYaml.saveConfig();
	}
}
