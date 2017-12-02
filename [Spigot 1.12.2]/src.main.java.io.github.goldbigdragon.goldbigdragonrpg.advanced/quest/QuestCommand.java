package quest;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import effect.SoundEffect;
import util.YamlLoader;

public class QuestCommand
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    
		Player player = (Player) talker;
		if(args.length==0)
		{
			SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 0.8F);
			new quest.QuestGui().MyQuestListGUI(player, (short) 0);
			return;
		}
		if(talker.isOp() == true)
		{
			if(player.isOp() == true)
			{
			    YamlLoader questListYaml = new YamlLoader();
			    questListYaml.getConfig("Quest/QuestList.yml");
		    	if(questListYaml.isExit("Quest/QuestList.yml")==false)
		    	{
		    		questListYaml.set("Do_not_Touch_This", true);
		    		questListYaml.saveConfig();
		    	}

			    
				switch(ChatColor.stripColor(args[0]))
				{
		  			case "����" :
		  			{
		  					SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 0.8F);
		  					new quest.QuestGui().AllOfQuestListGUI(player, (short) 0,false);
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
							    for(int i =2; i<args.length-1;i++)
						    	{
							    	QN.append(args[i]+" ");
						    	}
							    QN.append(args[args.length-1]);
							    String QuestName = QN.toString().replace(".", "");
							    String QuestNameString = ChatColor.stripColor(QuestName);
								if(questListYaml.contains(QuestNameString))
						    	{
								  	SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
							    	player.sendMessage("��c[SYSTEM] : �ش� �̸��� ����Ʈ�� �̹� �����մϴ�!");
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
								questListYaml.set(QuestNameString + ".QuestMaker", player.getName());
								questListYaml.set(QuestNameString + ".Type", QuestType);
								questListYaml.set(QuestNameString + ".Server.Limit", 0);
								questListYaml.set(QuestNameString + ".Need.LV", 0);
								questListYaml.set(QuestNameString + ".Need.Love", 0);
								questListYaml.createSection(QuestNameString + ".Need.Skill");
								questListYaml.set(QuestNameString + ".Need.STR", 0);
								questListYaml.set(QuestNameString + ".Need.DEX", 0);
								questListYaml.set(QuestNameString + ".Need.INT", 0);
								questListYaml.set(QuestNameString + ".Need.WILL", 0);
								questListYaml.set(QuestNameString + ".Need.LUK", 0);
								questListYaml.set(QuestNameString + ".Need.PrevQuest", "null");
								questListYaml.createSection(QuestNameString + ".FlowChart");
								questListYaml.saveConfig();
							    player.sendMessage("��a[SYSTEM] : ��e"+QuestNameString+"��3 ����Ʈ�� �����Ǿ����ϴ�!");
			  					SoundEffect.SP(player, Sound.ENTITY_HORSE_ARMOR, 1.0F, 0.8F);
			  					new quest.QuestGui().FixQuestGUI(player, (short) 0, QuestNameString);
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
				talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
				SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
	  			return;
			}
		}
	}

	private void HelpMessage(Player player)
	{
		player.sendMessage("��e������������������������[����Ʈ ��ɾ�]������������������������");
		player.sendMessage("��6/����Ʈ��e - ���� �ڽ��� �������� ����Ʈ ����� �����մϴ�.");
		player.sendMessage("��6/����Ʈ ������e - ���ο� ����Ʈ�� ����ų�, ������ ����Ʈ�� �����մϴ�.");
		player.sendMessage("��6/����Ʈ ���� [Ÿ��] [�̸�]��e - ���ο� ����Ʈ�� �����ϸ�, ����â���� �ٷ� �Ѿ�ϴ�.");
		player.sendMessage("��a[�Ϲ� / �ݺ� / ���� / ���� / �Ѵ�]");
		player.sendMessage("��e����������������������������������������������������������������");
	}
}