package GBD.GoldBigDragon_Advanced.Command;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;

public class QuestCommand extends HelpMessage
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
		GBD.GoldBigDragon_Advanced.GUI.QuestGUI QGUI = new GBD.GoldBigDragon_Advanced.GUI.QuestGUI();
		Player player = (Player) talker;
		if(talker.isOp() == true)
		{
			if(args.length==0)
			{
				s.SP(player, Sound.HORSE_ARMOR, 1.0F, 0.8F);
				QGUI.MyQuestListGUI(player, 0);
				return;
			}
			
			if(player.isOp() == true)
			{
			    YamlManager QuestConfig;
				YamlController Config_YC = GBD.GoldBigDragon_Advanced.Main.Main.Config_YC;
				QuestConfig=Config_YC.getNewConfig("Quest/QuestList.yml");
		    	if(Config_YC.isExit("Quest/QuestList.yml")==false)
		    	{
		    		QuestConfig.set("Do_not_Touch_This", true);
		    		QuestConfig.saveConfig();
		    	}

			    
				switch(ChatColor.stripColor(args[0]))
				{
		  			case "����" :
		  					s.SP(player, Sound.HORSE_ARMOR, 1.0F, 0.8F);
		  					QGUI.AllOfQuestListGUI(player, 0,false);
		  					break;
			  		case "����" :
			  			if(args.length <= 2)
			  			{
							HelpMessager((Player)talker,8);
						  	return;
			  			}
			  			if(args[1].equalsIgnoreCase("�Ϲ�")||args[1].equalsIgnoreCase("�ݺ�")||args[1].equalsIgnoreCase("����")
			  			||args[1].equalsIgnoreCase("����")||args[1].equalsIgnoreCase("�Ѵ�"))
			  			{
							Set<String> a = QuestConfig.getConfigurationSection("").getKeys(false);
							Object[] questList =a.toArray();

						  	String QuestName = "";
						  	
						    for(int i =2; i<args.length-1;i++)
						    {
						    	QuestName = QuestName + args[i]+" ";
						    }
						    QuestName = QuestName+args[args.length-1];
						    QuestName = QuestName.replace(".","");
						    QuestName = ChatColor.stripColor(QuestName);
							for(int count = 0; count < questList.length;count++)
						    {
								if(questList[count].toString().equalsIgnoreCase(QuestName) == true)
						    	{
								  	s.SP(player, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
							    	player.sendMessage(ChatColor.RED+"[SYSTEM] : �ش� �̸��� ����Ʈ�� �̹� �����մϴ�!");
								    return;
						    	}
						    }
							String QuestType = "";
							switch(args[1])
							{
							case "�Ϲ�" : QuestType = "N"; break;
							case "�ݺ�" : QuestType = "R"; break;
							case "����" : QuestType = "D"; break;
							case "����" : QuestType = "W"; break;
							case "�Ѵ�" : QuestType = "M"; break;
							}
							QuestConfig.set(QuestName + ".QuestMaker", player.getName());
							QuestConfig.set(QuestName + ".Type", QuestType);
							QuestConfig.set(QuestName + ".Server.Limit", 0);
							QuestConfig.set(QuestName + ".Need.LV", 0);
							QuestConfig.set(QuestName + ".Need.Love", 0);
							QuestConfig.set(QuestName + ".Need.Skill.0", null);
							QuestConfig.set(QuestName + ".Need.STR", 0);
							QuestConfig.set(QuestName + ".Need.DEX", 0);
							QuestConfig.set(QuestName + ".Need.INT", 0);
							QuestConfig.set(QuestName + ".Need.WILL", 0);
							QuestConfig.set(QuestName + ".Need.LUK", 0);
							QuestConfig.set(QuestName + ".Need.PrevQuest", "null");
							QuestConfig.set(QuestName + ".FlowChart.0", null);
							QuestConfig.saveConfig();
						    player.sendMessage(ChatColor.GREEN+"[SYSTEM] : "+ChatColor.YELLOW+QuestName+ChatColor.DARK_AQUA+" ����Ʈ�� �����Ǿ����ϴ�!");
		  					s.SP(player, Sound.HORSE_ARMOR, 1.0F, 0.8F);
						    QGUI.FixQuestGUI(player, 0, QuestName);
			  			}
			  			else
						HelpMessager((Player)talker,8);
		  				return;
				    default:
					HelpMessager((Player)talker,8);
					return;
					    	
				}
			}
			else
			{
				talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
				s.SP((Player)talker, org.bukkit.Sound.ORB_PICKUP, 2.0F, 1.7F);
	  			return;
			}
		  }
	}
}