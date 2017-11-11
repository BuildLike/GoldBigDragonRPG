package skill;

import org.bukkit.entity.Player;

import util.YamlLoader;

public class Skill_Config
{
    public void CreateNewPlayerSkill(Player player)
	{
    	YamlLoader PlayerSkillYML = new YamlLoader();
		PlayerSkillYML.getConfig("Skill/PlayerData/"+player.getUniqueId()+".yml");
    	YamlLoader Config = new YamlLoader();
		Config.getConfig("config.yml");
    	YamlLoader JobList = new YamlLoader();
		JobList.getConfig("Skill/JobList.yml");

		PlayerSkillYML.set("Job.Root",Config.getString("Server.DefaultJob"));
		PlayerSkillYML.set("Job.Type",Config.getString("Server.DefaultJob"));
		PlayerSkillYML.set("Job.LV",1);
		PlayerSkillYML.createSection("MapleStory");
		PlayerSkillYML.createSection("MapleStory."+Config.getString("Server.DefaultJob")+".Skill");
		Object[] DefaultSkills = null;
		if(JobList.contains(("MapleStory."+Config.getString("Server.DefaultJob")+"."+Config.getString("Server.DefaultJob")+".Skill"))==true)
			DefaultSkills = JobList.getConfigurationSection("MapleStory."+Config.getString("Server.DefaultJob")+"."+Config.getString("Server.DefaultJob")+".Skill").getKeys(false).toArray();
		if(DefaultSkills!=null)
			for(int count = 0; count < DefaultSkills.length;count++)
				PlayerSkillYML.set("MapleStory."+Config.getString("Server.DefaultJob")+".Skill."+DefaultSkills[count],1);
		PlayerSkillYML.createSection("Mabinogi");
		PlayerSkillYML.saveConfig();
	}
    
    public void CreateNewJobList()
	{
    	YamlLoader SkillList = new YamlLoader();
		SkillList.getConfig("Skill/JobList.yml");
		
		SkillList.createSection("Mabinogi.Added");
		SkillList.set("MapleStory.�ʺ���.�ʺ���.NeedLV",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.NeedSTR",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.NeedDEX",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.NeedINT",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.NeedWILL",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.NeedLUK",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.PrevJob","null");
		SkillList.set("MapleStory.�ʺ���.�ʺ���.IconID",268);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.IconData",0);
		SkillList.set("MapleStory.�ʺ���.�ʺ���.IconAmount",1);
		SkillList.createSection("MapleStory.�ʺ���.�ʺ���.Skill");
		SkillList.saveConfig();
	}
    
    public void CreateNewSkillList()
	{
    	YamlLoader SkillList = new YamlLoader();
		SkillList.getConfig("Skill/SkillList.yml");
		SkillList.set("CloudKill.ID",151);
		SkillList.set("CloudKill.DATA",0);
		SkillList.set("CloudKill.Amount",1);
		SkillList.set("CloudKill.SkillRank."+(int)1+".Command","/weather clear 9999");
		SkillList.set("CloudKill.SkillRank."+(int)1+".BukkitPermission",true);
		SkillList.set("CloudKill.SkillRank."+(int)1+".MagicSpells","null");
		SkillList.set("CloudKill.SkillRank."+(int)1+".Lore","��7     [���� ����]     ");
		SkillList.set("CloudKill.SkillRank."+(int)1+".AffectStat","����");
		SkillList.set("CloudKill.SkillRank."+(int)1+".DistrictWeapon","����");
		SkillList.saveConfig();
	}
}
