package job;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import effect.SoundEffect;
import user.UserDataObject;
import util.UtilChat;
import util.YamlLoader;



public class JobChat extends UtilChat
{
	public void JobTypeChatting(PlayerChatEvent event)
	{
		UserDataObject u = new UserDataObject();
		event.setCancelled(true);
		Player player = event.getPlayer();
		skill.OPboxSkillGui SKGUI = new skill.OPboxSkillGui();
	  	YamlLoader jobYaml = new YamlLoader();
	  	YamlLoader configYaml = new YamlLoader();

		jobYaml.getConfig("Skill/JobList.yml");
		configYaml.getConfig("config.yml");
		
		String Message = ChatColor.stripColor(event.getMessage());
		job.JobGUI JGUI = new job.JobGUI();

		switch(u.getString(player, (byte)1))
		{
		case "CC"://CreateCategory
			if(jobYaml.getConfigurationSection("Mabinogi").getKeys(false).toString().contains(Message) == false)
			{
				jobYaml.createSection("Mabinogi."+Message);
				jobYaml.saveConfig();
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 0.5F);
				JGUI.Mabinogi_ChooseCategory(player,(short) 0);

				configYaml.set("Time.LastSkillChanged", new util.UtilNumber().RandomNum(0, 100000)-new util.UtilNumber().RandomNum(0, 100000));
				configYaml.saveConfig();
				
				new job.JobMain().AllPlayerFixAllSkillAndJobYML();
				u.clearAll(player);
			}
			else
			{
				player.sendMessage("��c[ī�װ�] : �̹� �����ϴ� ī�װ� �̸��Դϴ�!");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			return;
		case "CJ"://CreateJob
			jobYaml.set("MapleStory."+Message+"."+Message+".NeedLV",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".NeedSTR",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".NeedDEX",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".NeedINT",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".NeedWILL",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".NeedLUK",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".PrevJob","null");
			jobYaml.set("MapleStory."+Message+"."+Message+".IconID",267);
			jobYaml.set("MapleStory."+Message+"."+Message+".IconData",0);
			jobYaml.set("MapleStory."+Message+"."+Message+".IconAmount",1);
			jobYaml.createSection("MapleStory."+Message+"."+Message+".Skill");
			jobYaml.saveConfig();
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 0.5F);
			JGUI.MapleStory_JobSetting(player,Message);
			u.clearAll(player);
			return;
		case "CJL"://CreateJobLevel (�±� �����)
			String JobName2 = u.getString(player, (byte)2);
			short NowJobLevel = (short) jobYaml.getConfigurationSection("MapleStory."+JobName2).getKeys(false).size();
			jobYaml.set("MapleStory."+JobName2+"."+Message+".NeedLV",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".NeedSTR",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".NeedDEX",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".NeedINT",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".NeedWILL",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".NeedLUK",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".PrevJob","null");
			jobYaml.set("MapleStory."+JobName2+"."+Message+".IconID",267);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".IconData",0);
			jobYaml.set("MapleStory."+JobName2+"."+Message+".IconAmount",1);
			jobYaml.createSection("MapleStory."+JobName2+"."+Message+".Skill");
			jobYaml.saveConfig();
			SoundEffect.SP(player, org.bukkit.Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
			JGUI.MapleStory_JobSetting(player, JobName2);
			u.clearAll(player);
			return;
		case "JNL"://JobNeedLevel (�±��� ���� �ʿ� ����)
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName3 = u.getString(player, (byte)2);
				String JobNick2 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".NeedLV",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNS");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick2+"��d�� �±� �ʿ� "+main.MainServerOption.statSTR+"�� �����ϼ���.");
			}
			return;
		case "JNS" : 
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName3 = u.getString(player, (byte)2);
				String JobNick2 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".NeedSTR",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JND");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick2+"��d�� �±� �ʿ� "+main.MainServerOption.statDEX+"�� �����ϼ���.");
			}
			return;
		case "JND" : 
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName3 = u.getString(player, (byte)2);
				String JobNick2 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".NeedDEX",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNI");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick2+"��d�� �±� �ʿ� "+main.MainServerOption.statINT+"�� �����ϼ���.");
			}
			return;
		case "JNI" : 
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName3 = u.getString(player, (byte)2);
				String JobNick2 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".NeedINT",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNW");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick2+"��d�� �±� �ʿ� "+main.MainServerOption.statWILL+"�� �����ϼ���.");
			}
			return;
		case "JNW" : 
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName3 = u.getString(player, (byte)2);
				String JobNick2 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".NeedWILL",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNLU");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick2+"��d�� �±� �ʿ� "+main.MainServerOption.statLUK+"�� �����ϼ���.");
			}
			return;
		case "JNLU" : 
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName3 = u.getString(player, (byte)2);
				String JobNick2 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".NeedLUK",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNPJ");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : � �����̡�e"+JobNick2+"��d �� �±� �����ϰ� �ұ��?");
				
				Object[] Job2 = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray();
				for(int count = 0; count < Job2.length; count++)
				{
					Object[] a = jobYaml.getConfigurationSection("MapleStory."+Job2[count].toString()).getKeys(false).toArray();
					for(int counter=0;counter<a.length;counter++)
					{
						if(!a[counter].toString().equals(JobNick2))
						player.sendMessage("��d "+Job2[count].toString()+" �� ��e"+ a[counter].toString());
					}
				}
				player.sendMessage("��d ���� ������ �����̵� ��� ���� ��� �� ��e����");
			}
			return;
		case "JNPJ" : 
			String JobName3 = u.getString(player, (byte)2);
			String JobNick2 = u.getString(player, (byte)3);
			Object[] Job2 = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray();
			boolean checked = false;
			if(!Message.equals("����"))
			{
				for(int count = 0; count < Job2.length; count++)
				{
					Object[] a = jobYaml.getConfigurationSection("MapleStory."+Job2[count].toString()).getKeys(false).toArray();
					for(int counter=0;counter<a.length;counter++)
					{
						if(a[counter].toString().equals(ChatColor.stripColor(Message)))
						{
							checked = true;
							break;
						}
					}
				}
			}
			else
				checked = true;
			if(checked == true)
			{
				if(!Message.equals("����"))
					jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".PrevJob",ChatColor.stripColor(Message));
				else
					jobYaml.set("MapleStory."+JobName3+"."+JobNick2+".PrevJob","null");
					
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNICONID");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick2+"��d �� ��Ÿ���� ������ ID�� �����ΰ���?");
			}
			else
			{
				Object[] Job1 = jobYaml.getConfigurationSection("MapleStory").getKeys(false).toArray();
				for(int count = 0; count < Job1.length; count++)
				{
					Object[] a = jobYaml.getConfigurationSection("MapleStory."+Job1[count].toString()).getKeys(false).toArray();
					for(int counter=0;counter<a.length;counter++)
					{
						if(!a[counter].toString().equals(JobNick2))
						player.sendMessage("��d "+Job2[count].toString()+" �� ��e"+ a[counter].toString());
					}
				}
				player.sendMessage("��d ���� ������ �����̵� ��� ���� ��� �� ��e����");
			}
			return;
		case "JNICONID" : 
			if(isIntMinMax(Message, player, 1, 2267))
			{
				event.EventInteract I = new event.EventInteract();
				if(I.SetItemDefaultName(Short.parseShort(Message),(byte)0).equals("�������� ���� ������"))
				{
					player.sendMessage("��c[SYSTEM] : �ش� �������� �������� �ʽ��ϴ�!");
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  				return;
				}
				String JobName4 = u.getString(player, (byte)2);
				String JobNick3 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName4+"."+JobNick3+".IconID",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNICONDATA");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick3+"��d �� ��Ÿ���� ������ DATA�� �����ΰ���?");
			}
			return;
		case "JNICONDATA" : 
			if(isIntMinMax(Message, player, 0, Integer.MAX_VALUE))
			{
				String JobName4 = u.getString(player, (byte)2);
				String JobNick3 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName4+"."+JobNick3+".IconData",Integer.parseInt(Message));
				jobYaml.saveConfig();
				u.setType(player, "Job");
				u.setString(player, (byte)1, "JNICONAMOUNT");
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				player.sendMessage("��d[����] : ��e"+JobNick3+"��d �� ��Ÿ���� ������ ������ �� ���ΰ���?");
			}
			return;
		case "JNICONAMOUNT" : 
			if(isIntMinMax(Message, player, 1, 127))
			{
				String JobName4 = u.getString(player, (byte)2);
				String JobNick3 = u.getString(player, (byte)3);
				jobYaml.set("MapleStory."+JobName4+"."+JobNick3+".IconAmount",Integer.parseInt(Message));
				jobYaml.saveConfig();
				SoundEffect.SP(player, org.bukkit.Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
				JGUI.MapleStory_JobSetting(player, JobName4);
				u.clearAll(player);
			}
			return;
		}
		return;
	}

}
