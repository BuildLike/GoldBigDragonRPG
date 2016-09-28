package GBD_RPG.Quest;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Quest_Command
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) talker;
		if(args.length==0)
		{
			s.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 0.8F);
			new GBD_RPG.Quest.Quest_GUI().MyQuestListGUI(player, (short) 0);
			return;
		}
		if(talker.isOp() == true)
		{
			if(player.isOp() == true)
			{
			    YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
			    YamlManager QuestConfig=YC.getNewConfig("Quest/QuestList.yml");
		    	if(YC.isExit("Quest/QuestList.yml")==false)
		    	{
		    		QuestConfig.set("Do_not_Touch_This", true);
		    		QuestConfig.saveConfig();
		    	}

			    
				switch(ChatColor.stripColor(args[0]))
				{
		  			case "����" :
		  			{
		  					s.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 0.8F);
		  					new GBD_RPG.Quest.Quest_GUI().AllOfQuestListGUI(player, (short) 0,false);
		  			}
		  			break;
			  		case "����" :
				  		{
				  			if(args.length <= 2)
				  			{
								HelpMessage(player);
							  	return;
				  			}
				  			if(args[1].equalsIgnoreCase("�Ϲ�")||args[1].equalsIgnoreCase("�ݺ�")||args[1].equalsIgnoreCase("����")
				  			||args[1].equalsIgnoreCase("����")||args[1].equalsIgnoreCase("�Ѵ�"))
				  			{
							  	StringBuffer QN = new StringBuffer();
							    for(byte i =2; i<args.length-1;i++)
						    	{
							    	QN.append(args[i]+" ");
						    	}
							    QN.append(args[args.length-1]);
							    String QuestName = QN.toString().replace(".", "");
							    String QuestNameString = ChatColor.stripColor(QuestName);
								if(QuestConfig.contains(QuestNameString))
						    	{
								  	s.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
							    	player.sendMessage(ChatColor.RED+"[SYSTEM] : �ش� �̸��� ����Ʈ�� �̹� �����մϴ�!");
								    return;
						    	}
								String QuestType = null;
								switch(args[1])
								{
									case "�Ϲ�" : QuestType = "N"; break;
									case "�ݺ�" : QuestType = "R"; break;
									case "����" : QuestType = "D"; break;
									case "����" : QuestType = "W"; break;
									case "�Ѵ�" : QuestType = "M"; break;
								}
								QuestConfig.set(QuestNameString + ".QuestMaker", player.getName());
								QuestConfig.set(QuestNameString + ".Type", QuestType);
								QuestConfig.set(QuestNameString + ".Server.Limit", 0);
								QuestConfig.set(QuestNameString + ".Need.LV", 0);
								QuestConfig.set(QuestNameString + ".Need.Love", 0);
								QuestConfig.createSection(QuestNameString + ".Need.Skill");
								QuestConfig.set(QuestNameString + ".Need.STR", 0);
								QuestConfig.set(QuestNameString + ".Need.DEX", 0);
								QuestConfig.set(QuestNameString + ".Need.INT", 0);
								QuestConfig.set(QuestNameString + ".Need.WILL", 0);
								QuestConfig.set(QuestNameString + ".Need.LUK", 0);
								QuestConfig.set(QuestNameString + ".Need.PrevQuest", "null");
								QuestConfig.createSection(QuestNameString + ".FlowChart");
								QuestConfig.saveConfig();
							    player.sendMessage(ChatColor.GREEN+"[SYSTEM] : "+ChatColor.YELLOW+QuestNameString+ChatColor.DARK_AQUA+" ����Ʈ�� �����Ǿ����ϴ�!");
			  					s.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 0.8F);
			  					new GBD_RPG.Quest.Quest_GUI().FixQuestGUI(player, (short) 0, QuestNameString);
				  			}
				  			else
							HelpMessage(player);
				  		}
				  		return;
				    default:
						{
							HelpMessage(player);
						}
				  		return;
				}
			}
			else
			{
				talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
				s.SP((Player)talker, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
	  			return;
			}
		}
	}

	private void HelpMessage(Player player)
	{
		player.sendMessage(ChatColor.YELLOW+"������������������������[����Ʈ ��ɾ�]������������������������");
		player.sendMessage(ChatColor.GOLD + "/����Ʈ" + ChatColor.YELLOW + " - ���� �ڽ��� �������� ����Ʈ ����� �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/����Ʈ ����" + ChatColor.YELLOW + " - ���ο� ����Ʈ�� ����ų�, ������ ����Ʈ�� �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/����Ʈ ���� [Ÿ��] [�̸�]" + ChatColor.YELLOW + " - ���ο� ����Ʈ�� �����ϸ�, ����â���� �ٷ� �Ѿ�ϴ�.");
		player.sendMessage(ChatColor.GREEN + "[�Ϲ� / �ݺ� / ���� / ���� / �Ѵ�]");
		player.sendMessage(ChatColor.YELLOW+"����������������������������������������������������������������");
	}
}