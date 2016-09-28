package GBD_RPG.Quest;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Quest_Config
{
    public void CreateNewQuestConfig()
	{
	    YamlManager QuestConfig;
	    YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		QuestConfig=YC.getNewConfig("Quest/QuestList.yml");

    	if(YC.isExit("Quest/QuestList.yml")==false)
    	{
    		QuestConfig.set("Do_not_Touch_This", true);
    		QuestConfig.saveConfig();
    	}
	  	return;
	}
    public void CreateNewPlayerConfig(Player player)
	{
	    YamlManager QuestConfig;
	    YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		QuestConfig = YC.getNewConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");

		QuestConfig.set("PlayerName", player.getName());
		QuestConfig.set("PlayerUUID", player.getUniqueId().toString());
		QuestConfig.createSection("Started");
		QuestConfig.createSection("Ended");
		QuestConfig.saveConfig();
		
		String QuestName = YC.getNewConfig("ETC/NewBie.yml").getString("FirstQuest");
		if(QuestName.compareTo("null") != 0)
		{
			YamlManager QuestList= YC.getNewConfig("Quest/QuestList.yml");
			if(QuestList.contains(QuestName))
			{
				if(QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false).toArray().length != 0)
				{
					String QuestType = QuestList.getString(QuestName+".FlowChart.0.Type");
					QuestConfig.set("Started."+QuestName+".Flow", 0);
					QuestConfig.set("Started."+QuestName+".Type", QuestType);
					if(QuestType.compareTo("Visit")==0)
						QuestConfig.set("Started."+QuestName+".AreaName", QuestList.getString(QuestName+".FlowChart.0.AreaName"));
					else if(QuestType.compareTo("Hunt")==0)
					{
						Object[] MobList = QuestList.getConfigurationSection(QuestName+".FlowChart.0.Monster").getKeys(false).toArray();
						for(short counter = 0; counter < MobList.length; counter++)
							QuestConfig.set("Started."+QuestName+".Hunt."+counter,0);
					}
					else if(QuestType.compareTo("Harvest")==0)
					{
						Object[] BlockList = QuestList.getConfigurationSection(QuestName+".FlowChart.0.Block").getKeys(false).toArray();
						for(short counter = 0; counter < BlockList.length; counter++)
							QuestConfig.set("Started."+QuestName+".Block."+counter,0);
					}
					QuestConfig.saveConfig();
					player.sendMessage(ChatColor.YELLOW+"[����Ʈ] : ���ο� ����Ʈ�� �����߽��ϴ�! " +ChatColor.GOLD+""+ChatColor.BOLD+"/����Ʈ");
					if(QuestType.compareTo("Nevigation")==0||QuestType.compareTo("Whisper")==0||
					QuestType.compareTo("BroadCast")==0||QuestType.compareTo("BlockPlace")==0||
					QuestType.compareTo("VarChange")==0||QuestType.compareTo("TelePort")==0)
						new GBD_RPG.Quest.Quest_GUI().QuestTypeRouter(player, QuestName);
				}
			}
		}
		QuestConfig.saveConfig();
	  	return;
	}
	
}
