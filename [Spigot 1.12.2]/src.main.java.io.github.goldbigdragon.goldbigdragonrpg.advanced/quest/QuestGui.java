package quest;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffectType;

import effect.PottionBuff;
import effect.SoundEffect;
import servertick.ServerTickMain;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;

public class QuestGui extends UtilGui
{
	public void MyQuestListGUI(Player player, short page)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");
		YamlLoader PlayerQuestList = new YamlLoader();
		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");

		String UniqueCode = "��0��0��5��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0����Ʈ ��� : " + (page+1));

		String[] a = PlayerQuestList.getConfigurationSection("Started").getKeys(false).toArray(new String[0]);
		String QuestType = "";
		int ItemID = 0;
		byte ItemAmount = 1;
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			if(QuestList.contains(a[count])==false)
			{
				PlayerQuestList.removeKey("Started."+a[count]);
				PlayerQuestList.saveConfig();
			}
			else
			{
				switch(QuestList.getString(a[count] + ".Type"))
				{
				case "N" :
					QuestType = "[�Ϲ�]";
					ItemID = 340;
					break;
				case "R" :
					QuestType = "[�ݺ�]";
					ItemID = 386;
					break;
				case "D" :
					QuestType = "[����]";
					ItemID = 403;
					break;
				case "W" :
					QuestType = "[�ְ�]";
					ItemID = 403;
					ItemAmount = 7;
					break;
				case "M" :
					QuestType = "[����]";
					ItemID = 403;
					ItemAmount = 31;
					break;
				}
				if(count > a.length || loc >= 45) break;

				switch(QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".Type"))
				{
				case "Nevigation":
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��fȭ��ǥ�� ������.",""), loc, inv);
					break;
				case "Choice":
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��f�ϰ���� ���� ��������.","","��e[��Ŭ���� ������ Ȯ��.]"), loc, inv);
					break;
				case "Script" :
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��e"+QuestList.getString(a[count].toString() + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count].toString()+".Flow")+".NPCname")+"��f�� ��ȭ�� �� ����."), loc, inv);
					break;
				case "PScript" :
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��e[��Ŭ���� ���� Ȯ��]"), loc, inv);
					break;
				case "Visit" :
					YamlLoader AreaList = new YamlLoader();
					AreaList.getConfig("Area/AreaList.yml");
					String AreaWorld = AreaList.getString(QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".AreaName")+".World");
					String AreaName = AreaList.getString(QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".AreaName")+".Name");
					int AreaX = AreaList.getInt(QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".AreaName")+".SpawnLocation.X");
					short AreaY = (short) AreaList.getInt(QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".AreaName")+".SpawnLocation.Y");
					int AreaZ = AreaList.getInt(QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".AreaName")+".SpawnLocation.Z");
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��e"+AreaName+"��f ������ �湮����."
							,"��e���� : ��f"+AreaWorld,"��eX ��ǥ : ��f"+AreaX,"��eY ��ǥ : ��f"+AreaY,"��eZ ��ǥ : ��f"+AreaZ), loc, inv);
					break;
				case "Talk" :
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��e"+QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".TargetNPCname")+"��f���� ���� �ɾ� ����."), loc, inv);
					break;
				case "Give" :
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��e"+QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".TargetNPCname")+"��f�� ��Ź��","��f��ǰ�� ��������.","","��e[��Ŭ���� ���� ǰ�� Ȯ��.]"), loc, inv);
					break;
				case "Hunt":
					removeFlagStack("��f��l" +a[count], ItemID,0,ItemAmount,Arrays.asList("��f��ǥ ����� óġ����.","","��e[��Ŭ���� óġ ��� Ȯ��]"), loc, inv);
					break;
				case "Harvest":
					removeFlagStack("��f��l" +a[count], ItemID,0,ItemAmount,Arrays.asList("��f����� ä������.","","��e[��Ŭ���� ä�� ��� Ȯ��]"), loc, inv);
					break;
				case "Present" :
					removeFlagStack("��f��l" + a[count], ItemID,0,ItemAmount,Arrays.asList("��e"+QuestList.getString(a[count] + ".FlowChart."+PlayerQuestList.getInt("Started."+a[count]+".Flow")+".TargetNPCname")+"��f����","��f������ ����.","","��e[��Ŭ���� ���� Ȯ��.]"), loc, inv);
					break;
				}
				loc++;
			}
		}
		
		if(a.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void AllOfQuestListGUI(Player player, short page,boolean ChoosePrevQuest)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

		String UniqueCode = "��0��0��5��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��ü ����Ʈ ��� : " + (page+1));

		String[] a = QuestList.getKeys().toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			int QuestFlowSize = QuestList.getConfigurationSection(a[count]+".FlowChart").getKeys(false).size();
			if(count > a.length || loc >= 45) break;
			if(ChoosePrevQuest == false)
			{
				switch(QuestList.getString(a[count].toString() + ".Type"))
				{
				case "N" :
					removeFlagStack("��f��l" + a[count], 340,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : �Ϲ� ����Ʈ","","��e[��Ŭ���� ���� ������ �մϴ�.]","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case "R" :
					removeFlagStack("��f��l" + a[count], 386,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : �ݺ� ����Ʈ","","��e[��Ŭ���� ���� ������ �մϴ�.]","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case "D" :
					removeFlagStack("��f��l" + a[count], 403,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : ���� ����Ʈ","","��e[��Ŭ���� ���� ������ �մϴ�.]","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case "W" :
					removeFlagStack("��f��l" + a[count], 403,0,7,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : ���� ����Ʈ","","��e[��Ŭ���� ���� ������ �մϴ�.]","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case "M" :
					removeFlagStack("��f��l" + a[count], 403,0,31,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : �Ѵ� ����Ʈ","","��e[��Ŭ���� ���� ������ �մϴ�.]","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				}
			}
			else
			{
				switch(QuestList.getString(a[count].toString() + ".Type"))
				{
				case "N" :
					removeFlagStack("��f��l" + a[count], 340,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : �Ϲ� ����Ʈ",""), loc, inv);
					break;
				case "R" :
					removeFlagStack("��f��l" + a[count], 386,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : �ݺ� ����Ʈ",""), loc, inv);
					break;
				case "D" :
					removeFlagStack("��f��l" + a[count], 403,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : ���� ����Ʈ",""), loc, inv);
					break;
				case "W" :
					removeFlagStack("��f��l" + a[count], 403,0,7,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : ���� ����Ʈ",""), loc, inv);
					break;
				case "M" :
					removeFlagStack("��f��l" + a[count], 403,0,31,Arrays.asList("��f����Ʈ ���� ��� : "+QuestFlowSize+"��","��3����Ʈ Ÿ�� : �Ѵ� ����Ʈ",""), loc, inv);
					break;
				}
			}
			loc++;
		}
		
		if(a.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		if(ChoosePrevQuest == false)
			removeFlagStack("��f��l�� ����Ʈ", 386,0,1,Arrays.asList("��7���ο� ����Ʈ�� �����մϴ�."), 49, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ChoosePrevQuest), 53, inv);
		player.openInventory(inv);
	}
	
	public void FixQuestGUI(Player player, short page, String QuestName)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

		String UniqueCode = "��0��0��5��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode +"��0����Ʈ �帧�� : " + (page+1));
		int flowChartSize = QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false).size();
		
		byte loc=0;
		for(int count = page*45; count < flowChartSize;count++)
		{
			if(count > flowChartSize || loc >= 45) break;
			switch(QuestList.getString(QuestName+".FlowChart."+count+".Type"))
			{
				case "Cal":
				switch(QuestList.getInt(QuestName+".FlowChart."+count+".Comparison"))
				{
				case 1:
					removeFlagStack("��f��l"+count, 137,0,1,Arrays.asList("��fŸ�� : ���","","��3[     ��� ��     ]","��3�÷��̾� ���� �� "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 2:
					removeFlagStack("��f��l"+count, 137,0,1,Arrays.asList("��fŸ�� : ���","","��3[     ��� ��     ]","��3�÷��̾� ���� �� "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 3:
					removeFlagStack("��f��l"+count, 137,0,1,Arrays.asList("��fŸ�� : ���","","��3[     ��� ��     ]","��3�÷��̾� ���� �� "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 4:
					removeFlagStack("��f��l"+count, 137,0,1,Arrays.asList("��fŸ�� : ���","","��3[     ��� ��     ]","��3�÷��̾� ���� �� "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 5:
					removeFlagStack("��f��l"+count, 137,0,1,Arrays.asList("��fŸ�� : ���","","��3[     ��� ��     ]","��3�÷��̾� ���� �� "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				}
				break;
			case "IF":
				switch(QuestList.getInt(QuestName+".FlowChart."+count+".Comparison"))
				{
				case 1:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3�÷��̾� ���� == "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 2:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3�÷��̾� ���� != "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 3:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3�÷��̾� ���� > "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 4:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3�÷��̾� ���� < "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 5:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3�÷��̾� ���� >= "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 6:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3�÷��̾� ���� <= "+QuestList.getInt(QuestName+".FlowChart."+count+".Value"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				case 7:
					removeFlagStack("��f��l"+count, 184,0,1,Arrays.asList("��fŸ�� : IF","","��3[     �� ��     ]","��3"+QuestList.getInt(QuestName+".FlowChart."+count+".Min")+" <= �÷��̾� ���� <= "+QuestList.getInt(QuestName+".FlowChart."+count+".Max"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
					break;
				}
				break;
			case "QuestFail":
				removeFlagStack("��f��l"+count, 166,0,1,Arrays.asList("��fŸ�� : ����Ʈ ����","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "QuestReset":
				removeFlagStack("��f��l"+count, 395,0,1,Arrays.asList("��fŸ�� : ����Ʈ �ʱ�ȭ","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "ELSE":
				removeFlagStack("��f��l"+count, 167,0,1,Arrays.asList("��fŸ�� : ELSE","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "ENDIF":
				removeFlagStack("��f��l"+count, 191,0,1,Arrays.asList("��fŸ�� : ENDIF","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "VarChange":
				removeFlagStack("��f��l"+count, 143,0,1,Arrays.asList("��fŸ�� : ���� ����","��f���� �� : " + QuestList.getInt(QuestName+".FlowChart."+count+".Value") ,"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "Choice":
				int button = QuestList.getConfigurationSection(QuestName+".FlowChart."+count+".Choice").getKeys(false).size();
				removeFlagStack("��f��l"+count, 72,0,button,Arrays.asList("��fŸ�� : ����","��f������ ���� : " +button+"��" ,"","��e[��Ŭ���� ����â Ȯ��]","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "Nevigation":
			{
				String UTC = QuestList.getString(QuestName+".FlowChart."+count+".NeviUTC");
				YamlLoader NavigationConfig = new YamlLoader();
				NavigationConfig.getConfig("Navigation/NavigationList.yml");
				if(NavigationConfig.contains(UTC))
				{
					String NaviName = NavigationConfig.getString(UTC+".Name");
					String world = NavigationConfig.getString(UTC+".world");
					int x = NavigationConfig.getInt(UTC+".x");
					short y = (short) NavigationConfig.getInt(UTC+".y");
					int z = NavigationConfig.getInt(UTC+".z");
					int Time = NavigationConfig.getInt(UTC+".time");
					short sensitive = (short) NavigationConfig.getInt(UTC+".sensitive");
					byte ShowArrow = (byte) NavigationConfig.getInt(UTC+".ShowArrow");
					
					String TimeS = "��3<������ �� ���� ����>";
					String sensitiveS = "��9<�ݰ� "+sensitive+"��� �̳��� �������� ����>";
					String ShowArrowS = "��3<�⺻ ȭ��ǥ ���>";
					if(Time >= 0)
						TimeS = "��3<"+Time+"�� ���� ����>";
					switch(ShowArrow)
					{
					default:
						ShowArrowS = "��3<�⺻ ȭ��ǥ ���>";
						break;
					}
					removeFlagStack("��f��l" + count, 395,0,1,Arrays.asList(
					"��e��l"+NaviName,"",
					"��9[���� ����]","��9���� : ��f"+world,
					"��9��ǥ : ��f"+x+","+y+","+z,sensitiveS,"",
					"��3[��Ÿ �ɼ�]",TimeS,ShowArrowS,""
					,"��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				}
				else
					removeFlagStack("��f��l" + count, 166,0,1,Arrays.asList("��c[�׺���̼��� ã�� �� �����ϴ�!]","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"),loc,inv);
			}
				break;
			case "Whisper":
			{
				String script3 = "��fŸ�� : �Ӹ�%enter%%enter%"+QuestList.getString(QuestName+".FlowChart."+count+".Message")+"%enter% %enter%��c[Shift + ��Ŭ���� �����˴ϴ�.]";
				String[] scriptA3 = script3.split("%enter%");
				for(int counter = 0; counter < scriptA3.length; counter++)
					scriptA3[counter] = "��f"+ scriptA3[counter];
				removeFlagStack("��f��l"+count, 421,0,1,Arrays.asList(scriptA3), loc, inv);
			}
			break;
			case "BroadCast":
			{
				String script3 = "��fŸ�� : ��ü%enter%%enter%"+QuestList.getString(QuestName+".FlowChart."+count+".Message")+"%enter% %enter%��c[Shift + ��Ŭ���� �����˴ϴ�.]";
				String[] scriptA3 = script3.split("%enter%");
				for(int counter = 0; counter < scriptA3.length; counter++)
					scriptA3[counter] = "��f"+ scriptA3[counter];
				removeFlagStack("��f��l"+count, 138,0,1,Arrays.asList(scriptA3), loc, inv);
			}
			break;
			case "Script":
				String script = "��fŸ�� : ���%enter%��f���ϴ� ��ü : "+QuestList.getString(QuestName+".FlowChart."+count+".NPCname")+"%enter%%enter%"+QuestList.getString(QuestName+".FlowChart."+count+".Script")+"%enter% %enter%��c[Shift + ��Ŭ���� �����˴ϴ�.]";
				String[] scriptA = script.split("%enter%");
				for(int counter = 0; counter < scriptA.length; counter++)
					scriptA[counter] = "��f"+ scriptA[counter];
			removeFlagStack("��f��l"+count, 323,0,1,Arrays.asList(scriptA), loc, inv);
			break;
			case "PScript":
				String script3 = "��fŸ�� : ���%enter%��f���ϴ� ��ü : �÷��̾�%enter%%enter%"+QuestList.getString(QuestName+".FlowChart."+count+".Message")+"%enter% %enter%��c[Shift + ��Ŭ���� �����˴ϴ�.]";
				String[] scriptA3 = script3.split("%enter%");
				for(int counter = 0; counter < scriptA3.length; counter++)
					scriptA3[counter] = "��f"+ scriptA3[counter];
			removeFlagStack("��f��l"+count, 323,0,1,Arrays.asList(scriptA3), loc, inv);
			break;
			case "Visit":
			removeFlagStack("��f��l"+count, 345,0,1,Arrays.asList("��fŸ�� : �湮","��f�湮 ���� : "+QuestList.getString(QuestName+".FlowChart."+count+".AreaName"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "Give":
				String script2 = "��fŸ�� : ����%enter%��f���� ��� : ��e"+QuestList.getString(QuestName+".FlowChart."+count+".TargetNPCname")+"%enter%��fNPC�� UUID%enter%��3"+ QuestList.getString(QuestName+".FlowChart."+count+".TargetNPCuuid")+"%enter%%enter%��e[��Ŭ���� ���� ǰ�� Ȯ��]%enter%��c[Shift + ��Ŭ���� �����˴ϴ�.]";
				String[] scriptB = script2.split("%enter%");
				for(int counter = 0; counter < scriptB.length; counter++)
					scriptB[counter] = "��f"+ scriptB[counter];
			removeFlagStack("��f��l"+count, 388,0,1,Arrays.asList(scriptB), loc, inv);
				break;
			case "Hunt":
				removeFlagStack("��f��l"+count, 267,0,1,Arrays.asList("��fŸ�� : ���","","��e[��Ŭ���� óġ ��� Ȯ��]","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "Talk":
				removeFlagStack("��f��l"+count, 397,3,1,Arrays.asList("��fŸ�� : ��ȭ","","��f������ �� NPC �̸�","","��e"+QuestList.getString(QuestName+".FlowChart."+count+".TargetNPCname"),"","��fNPC�� UUID","","��3"+ QuestList.getString(QuestName+".FlowChart."+count+".TargetNPCuuid"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "Present":
				removeFlagStack("��f��l"+count, 54,0,1,Arrays.asList("��fŸ�� : ����","��f���� ��� : ��e"+QuestList.getString(QuestName+".FlowChart."+count+".TargetNPCname"),"��fNPC�� UUID","","��3"+ QuestList.getString(QuestName+".FlowChart."+count+".TargetNPCuuid"),"","","��e[��Ŭ���� ���� Ȯ��]","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "TelePort":
			removeFlagStack("��f��l"+count, 368,0,1,Arrays.asList("��fŸ�� : �̵�","","��f���� : "+QuestList.getString(QuestName+".FlowChart."+count+".World"),"��f��ǥ : " + (int)QuestList.getDouble(QuestName+".FlowChart."+count+".X")+","+ (int)QuestList.getDouble(QuestName+".FlowChart."+count+".Y")+","+ (int)QuestList.getDouble(QuestName+".FlowChart."+count+".Z"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "Harvest":
				removeFlagStack("��f��l"+count, 56,0,1,Arrays.asList("��fŸ�� : ä��","","��e[��Ŭ���� ä�� ��� Ȯ��]","","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			case "BlockPlace":
				removeFlagStack("��f��l"+count, 152,0,1,Arrays.asList("��fŸ�� : ��� ��ġ","��f���� : "+QuestList.getString(QuestName+".FlowChart."+count+".World"),"��f��ǥ : " + (int)QuestList.getDouble(QuestName+".FlowChart."+count+".X")+","+ (int)QuestList.getDouble(QuestName+".FlowChart."+count+".Y")+","+ (int)QuestList.getDouble(QuestName+".FlowChart."+count+".Z"),"��f��� Ÿ�� : " + QuestList.getInt(QuestName+".FlowChart."+count+".ID")+":"+ QuestList.getInt(QuestName+".FlowChart."+count+".DATA"),"","��c[Shift + ��Ŭ���� �����˴ϴ�.]"), loc, inv);
				break;
			}
			loc++;
		}
		
		if(flowChartSize-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ������Ʈ �߰�", 2,0,1,Arrays.asList("��7���ο� ������Ʈ�� �߰��մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 53, inv);
		player.openInventory(inv);
	}

	public void SelectObjectPage(Player player, byte page, String QuestName)
	{
		String UniqueCode = "��0��0��5��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0������Ʈ �߰�");

		removeFlagStack("��f��l���", 323,0,1,Arrays.asList("��7��ȭâ�� ����, �ۼ���","��7��ũ��Ʈ�� �������� ���ϴ�.","��7(ȭ�� : NPC)"), 0, inv);
		removeFlagStack("��f��l����", 323,0,1,Arrays.asList("��7��ȭâ�� ����, �ۼ���","��7��ũ��Ʈ�� �������� ���ϴ�.","��7(ȭ�� : ����)"), 1, inv);
		removeFlagStack("��f��l�湮", 345,0,1,Arrays.asList("��7�÷��̾�� Ư�� ������","��7�湮�ϴ� ����Ʈ�� �ݴϴ�."), 2, inv);
		removeFlagStack("��f��l����", 388,0,1,Arrays.asList("��7�÷��̾ Ư�� ��������","��7NPC���� ����ϴ� ����Ʈ�� �ݴϴ�."), 3, inv);
		removeFlagStack("��f��l���", 267,0,1,Arrays.asList("��7�÷��̾�� Ư�� ���͸�","��7����ϴ� ����Ʈ�� �ݴϴ�."), 4, inv);
		removeFlagStack("��f��l��ȭ", 397,3,1,Arrays.asList("��7�÷��̾�� Ư�� NPC����","��7���� �Ŵ� ����Ʈ�� �ݴϴ�."), 5, inv);
		removeFlagStack("��f��l����", 54,0,1,Arrays.asList("��7�÷��̾�� ������ �ݴϴ�."), 6, inv);
		removeFlagStack("��f��l�̵�", 368,0,1,Arrays.asList("��7�÷��̾ Ư�� ��ġ��","��7�̵� ��ŵ�ϴ�."), 7, inv);
		removeFlagStack("��f��lä��", 56,0,1,Arrays.asList("��7�÷��̾�� Ư�� �����","��7ä���ϴ� ����Ʈ�� �ݴϴ�."), 8, inv);
		removeFlagStack("��f��l���", 152,0,1,Arrays.asList("��7Ư�� ��ġ�� ������","��7����� �����մϴ�."), 9, inv);
		removeFlagStack("��f��l�Ҹ���c[��� �Ұ�]", 84,0,1,Arrays.asList("��7Ư�� ��ġ�� �Ҹ��� ���� �մϴ�."), 10, inv);
		removeFlagStack("��f��l�Ӹ�", 421,0,1,Arrays.asList("��7�÷��̾��� ä��â�� �޽����� ��Ÿ���ϴ�."), 11, inv);
		removeFlagStack("��f��l��ü", 138,0,1,Arrays.asList("��7���� ��ü�� �޽����� ��Ÿ���ϴ�."), 12, inv);
		removeFlagStack("��f��l�׺�", 358,0,1,Arrays.asList("��7�÷��̾�� �׺���̼��� �۵� ��ŵ�ϴ�."), 13, inv);
		

		removeFlagStack("��e��l����", 72,0,1,Arrays.asList("��7�÷��̾ ���ϴ� �����","��7���� �ϵ��� �մϴ�.","��7������ ��信 ����","��7�ٸ� �������� ���� �� �ֽ��ϴ�."), 36, inv);
		removeFlagStack("��e��l����", 143,0,1,Arrays.asList("��7�÷��̾��� ������ ������ �����մϴ�."), 37, inv);
		removeFlagStack("��e��l���", 137,0,1,Arrays.asList("��7�÷��̾��� ������ ������","��7����Ͽ� �����մϴ�."), 38, inv);
		removeFlagStack("��e��lIF", 184,0,1,Arrays.asList("��7�÷��̾��� ���� �������� Ȯ���Ͽ�","��7���� ���� ������ ���","��7IF�� ENDIFȤ�� IF�� ELSE","��7������ ������ �����ϰ� �˴ϴ�.","","��c[�ݵ�� IF�� ���� = ENDIF�� ����]"), 39, inv);
		removeFlagStack("��e��lELSE", 167,0,1,Arrays.asList("��7�÷��̾��� ���� ��������","��7IF ���� ���� ���� ���","��7ELSE�� ENDIF ������ ������","��7�����ϰ� �˴ϴ�.",""), 40, inv);
		removeFlagStack("��e��lENDIF", 191,0,1,Arrays.asList("��7IF�� �� �κ��� ��Ÿ���ϴ�.","","��c[�ݵ�� IF�� ���� = ENDIF�� ����]"), 41, inv);
		
		removeFlagStack("��c��l����Ʈ �ʱ�ȭ", 395,0,1,Arrays.asList("��7����Ʈ�� �߰��� ���� �մϴ�.","��a����Ʈ�� �ٽ� ���� �� �ֽ��ϴ�."), 43, inv);
		removeFlagStack("��c��l����Ʈ ����", 166,0,1,Arrays.asList("��7����Ʈ�� �߰��� ���� �մϴ�.","��7�Ϲ� ����Ʈ�� ��� �÷��̾��","��c����Ʈ�� �ٽ� ���� �� �����ϴ�."), 44, inv);
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 53, inv);
		player.openInventory(inv);
	}

	public void QuestScriptTypeGUI(Player player,String QuestName,String NPCname, short FlowChart, String[] script)
	{
		String UniqueCode = "��0��0��5��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0[����Ʈ]");
		removeFlagStack("��0"+ ChatColor.stripColor(QuestName), 160,8,1,null, 19, inv);
		
		for(int count=0;count < script.length; count++)
			script[count] = script[count].replace("%player%", player.getName());
		if(NPCname.equals(player.getName()))
			stackItem(getPlayerSkull("��e"+NPCname, 1, Arrays.asList(script), player.getName()), 13, inv);
		else
			removeFlagStack("��e"+ NPCname, 386,0,1,Arrays.asList(script), 13, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 26, inv);
		player.openInventory(inv);
	}
	
	public void QuestOptionGUI(Player player, String QuestName)
	{
		String UniqueCode = "��0��0��5��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0����Ʈ �ɼ�");

		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");
		
		switch(QuestList.getString(QuestName + ".Type"))
		{
		case "N" :
			removeFlagStack("��f��l����Ʈ Ÿ��", 340,0,1,Arrays.asList("��f�Ϲ� ����Ʈ"), 4, inv);
			break;
		case "R" :
			removeFlagStack("��f��l����Ʈ Ÿ��", 386,0,1,Arrays.asList("��f�ݺ� ����Ʈ"), 4, inv);
			break;
		case "D" :
			removeFlagStack("��f��l����Ʈ Ÿ��", 403,0,1,Arrays.asList("��f���� ����Ʈ"), 4, inv);
			break;
		case "W" :
			removeFlagStack("��f��l����Ʈ Ÿ��", 403,0,7,Arrays.asList("��f�ְ� ����Ʈ"), 4, inv);
			break;
		case "M" :
			removeFlagStack("��f��l����Ʈ Ÿ��", 403,0,31,Arrays.asList("��f���� ����Ʈ"), 4, inv);
			break;
		}

		removeFlagStack("��f��l���� ����", 384,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ��� ������ �����մϴ�.","��6�������f �ý����� ��� ��e����������f �����̸�,","��c�����ý��丮��f �ý����� ��� ��e������f �����Դϴ�.","","��b[�ʿ� ���� : " + QuestList.getInt(QuestName+".Need.LV")+"]"), 11, inv);
		removeFlagStack("��f��lNPC ȣ���� ����", 38,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��fNPC���� ȣ������ �����մϴ�.","","��b[�ʿ� ȣ���� : " + QuestList.getInt(QuestName+".Need.Love")+"]"), 13, inv);
		removeFlagStack("��f��l��ų ��ũ ����", 403,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��f��ų ��ũ�� �����մϴ�.",""/*,"��b[�ʿ� ��ų : " + QuestList.getString(QuestName+".Need.Skill.Name")+"]","��b[�ʿ� ��ũ : " + QuestList.getInt(QuestName+".Need.Skill.Rank")+"]"*/), 15, inv);
		removeFlagStack("��f��l"+main.MainServerOption.statSTR+" ����", 267,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��f"+main.MainServerOption.statSTR+" ������ �����մϴ�.","","��b[�ʿ� "+main.MainServerOption.statSTR+" : " + QuestList.getInt(QuestName+".Need.STR")+"]"), 20, inv);
		removeFlagStack("��f��l"+main.MainServerOption.statDEX+" ����", 261,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��f"+main.MainServerOption.statDEX+" ������ �����մϴ�.","","��b[�ʿ� "+main.MainServerOption.statDEX+" : " + QuestList.getInt(QuestName+".Need.DEX")+"]"), 21, inv);
		removeFlagStack("��f��l"+main.MainServerOption.statINT+" ����", 369,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��f"+main.MainServerOption.statINT+" ������ �����մϴ�.","","��b[�ʿ� "+main.MainServerOption.statINT+" : " + QuestList.getInt(QuestName+".Need.INT")+"]"), 22, inv);
		removeFlagStack("��f��l"+main.MainServerOption.statWILL+" ����", 370,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��f"+main.MainServerOption.statWILL+" ������ �����մϴ�.","","��b[�ʿ� "+main.MainServerOption.statWILL+" : " + QuestList.getInt(QuestName+".Need.WILL")+"]"), 23, inv);
		removeFlagStack("��f��l"+main.MainServerOption.statLUK+" ����", 322,0,1,Arrays.asList("��f����Ʈ ���࿡ �ʿ���","��f"+main.MainServerOption.statLUK+" ������ �����մϴ�.","","��b[�ʿ� "+main.MainServerOption.statLUK+" : " + QuestList.getInt(QuestName+".Need.LUK")+"]"), 24, inv);
		if(QuestList.getString(QuestName+".Need.PrevQuest").equalsIgnoreCase("null") == true)
			removeFlagStack("��f��l�ʼ� �Ϸ� ����Ʈ", 386,0,1,Arrays.asList("��f���� ����Ʈ�� ������ ��","��f���� ����Ʈ�� ���� �ϵ��� �մϴ�.","","��b[���� ����Ʈ : ����]"),29, inv);
		else
			removeFlagStack("��f��l�ʼ� �Ϸ� ����Ʈ", 386,0,1,Arrays.asList("��f���� ����Ʈ�� ������ ��","��f���� ����Ʈ�� ���� �ϵ��� �մϴ�.","��c[Shift + ��Ŭ���� �����˴ϴ�]","","��b[���� ����Ʈ : " +QuestList.getString(QuestName+".Need.PrevQuest")+"]"),29, inv);
		switch(QuestList.getInt(QuestName+".Server.Limit"))
		{
		case 0:
			removeFlagStack("��f��l����Ʈ ����", 397,3,1,Arrays.asList("��f�������� �� �� ����","��f�� ����Ʈ�� ���� �� �� �ֽ��ϴ�.","��f�÷��̾ ����Ʈ�� ���� �� ���� 1�� ���̸�,","��f-1�� �� ��� ����Ʈ�� ���� �� �����ϴ�.","��3(0������ ������ ���, ������ ������ϴ�.)","","��b[���� ���� �÷��̾� �� : ���� ����]"), 33, inv);
			break;
		case -1:
			removeFlagStack("��f��l����Ʈ ����", 397,3,1,Arrays.asList("��f�������� �� �� ����","��f�� ����Ʈ�� ���� �� �� �ֽ��ϴ�.","��f�÷��̾ ����Ʈ�� ���� �� ���� 1�� ���̸�,","��f-1�� �� ��� ����Ʈ�� ���� �� �����ϴ�.","��3(0������ ������ ���, ������ ������ϴ�.)","","��c[���̻� ���� �� ����]"), 33, inv);
			break;
		default:
			removeFlagStack("��f��l����Ʈ ����", 397,3,1,Arrays.asList("��f�������� �� �� ����","��f�� ����Ʈ�� ���� �� �� �ֽ��ϴ�.","��f�÷��̾ ����Ʈ�� ���� �� ���� 1�� ���̸�,","��f-1�� �� ��� ����Ʈ�� ���� �� �����ϴ�.","��3(0������ ������ ���, ������ ������ϴ�.)","","��b[���� ���� �÷��̾� �� : "+QuestList.getInt(QuestName+".Server.Limit")+"]"), 33, inv);
			break;
		}
	
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 36, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 44, inv);
		player.openInventory(inv);
	}
	
	public void GetterItemSetingGUI(Player player, String QuestName)
	{
		String UniqueCode = "��1��0��5��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0��ƾ� �� ������ ���");
		for(int count = 0;count<8;count++)
			removeFlagStack("��f[�������� �÷� �ּ���.]", 389,0,0,null, count, inv);
		
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 8, inv);
		player.openInventory(inv);
	}

	public void PresentItemSettingGUI(Player player, String QuestName)
	{
		YamlLoader QuestConfig = new YamlLoader();
		QuestConfig.getConfig("Quest/QuestList.yml");

		UserDataObject u = new UserDataObject();

		String UniqueCode = "��1��0��5��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ������ ���");
		if(u.getInt(player, (byte)1) == -1)
			u.setInt(player, (byte)1, 0);
		if(u.getInt(player, (byte)2) == -1)
			u.setInt(player, (byte)2, 0);
		if(u.getInt(player, (byte)3) == -1)
			u.setInt(player, (byte)3, 0);
			
		removeFlagStack("��f[����� �����ϱ�]", 266,0,1,Arrays.asList("��f��l"+u.getInt(player, (byte)1)+" "+main.MainServerOption.money), 0, inv);
		removeFlagStack("��f[����ġ �����ϱ�]", 384,0,1,Arrays.asList("��f��l"+u.getInt(player, (byte)2)+"��b��l EXP"), 1, inv);
		removeFlagStack("��f[NPC ȣ���� �����ϱ�]", 38,0,1,Arrays.asList("��f��l"+u.getInt(player, (byte)3)+"��d��l Love"), 2, inv);
		int ifItemExit = 0;
		for(int count = 3;count<8;count++)
		{
			if(QuestConfig.getItemStack(QuestName + ".FlowChart."+ u.getInt(player, (byte)5) +".Item."+ifItemExit) != null)
			{
				stackItem(QuestConfig.getItemStack(QuestName + ".FlowChart."+ u.getInt(player, (byte)5) +".Item."+ifItemExit), count, inv);
				ifItemExit++;
			}
			else
				removeFlagStack("��f[�������� �÷� �ּ���.]", 389,0,0,null, count, inv);
		}
		
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 8, inv);
		u.setString(player, (byte)4,null);
		player.openInventory(inv);
	}
	
	public void ShowItemGUI(Player player, String QuestName, short Flow, boolean isOP, boolean type)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

		String UniqueCode = "��0��0��5��0��8��r";
		Inventory inv = null;
		
		if(QuestList.contains(QuestName+".FlowChart."+Flow+".Item") == true)
		{
			Object[] a =QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Item").getKeys(false).toArray();
			if(type == false)
			{
				inv = Bukkit.createInventory(null, 27, UniqueCode + "��0��ƾ� �� ������ ���");
				for(int count = 0;count<a.length;count++)
					stackItem(QuestList.getItemStack(QuestName+".FlowChart."+Flow+".Item." + a[count]),count+10,inv);
			}
			else
			{
				inv = Bukkit.createInventory(null, 27, UniqueCode + "��0���� ���");
				removeFlagStack("��6[�����]", 266,0,1,Arrays.asList("","��f��l" + QuestList.getInt(QuestName+".FlowChart."+Flow+".Money") +"��6 "+main.MainServerOption.money), 3, inv);
				removeFlagStack("��b[����ġ]", 384,0,1,Arrays.asList("","��f��l" + QuestList.getInt(QuestName+".FlowChart."+Flow+".EXP") +"��b EXP"), 4, inv);
				removeFlagStack("��d[ȣ����]", 38,0,1,Arrays.asList("","��f��l" + QuestList.getInt(QuestName+".FlowChart."+Flow+".Love") +"��d Love"), 5, inv);

				for(int count = 0;count<a.length;count++)
					stackItem(QuestList.getItemStack(QuestName+".FlowChart."+Flow+".Item." + a[count]),count+11,inv);
				if(player.isOp())
				{
					UserDataObject u = new UserDataObject();
					if(u.getInt(player, (byte)1)!=-9)
					{
						u.clearAll(player);
						removeFlagStack("��f��l���� �ޱ�", 54,0,1,Arrays.asList("��7������ �����մϴ�." ,"��0"+ Flow), 22, inv);
					}
				}
				else
					removeFlagStack("��f��l���� �ޱ�", 54,0,1,Arrays.asList("��7������ �����մϴ�." ,"��0"+ Flow), 22, inv);
			}
		}
		else
		{
			if(type == false)
			{
				inv = Bukkit.createInventory(null, 27, UniqueCode + "��0��ƾ� �� ������ ���");
			}
			else
			{
				inv = Bukkit.createInventory(null, 27, UniqueCode + "��0���� ���");
				removeFlagStack("��6[�����]", 266,0,1,Arrays.asList("","��f��l" + QuestList.getInt(QuestName+".FlowChart."+Flow+".Money") +"��6 "+main.MainServerOption.money), 3, inv);
				removeFlagStack("��b[����ġ]", 384,0,1,Arrays.asList("","��f��l" + QuestList.getInt(QuestName+".FlowChart."+Flow+".EXP") +"��b EXP"), 4, inv);
				removeFlagStack("��d[ȣ����]", 38,0,1,Arrays.asList("","��f��l" + QuestList.getInt(QuestName+".FlowChart."+Flow+".Love") +"��d Love"), 5, inv);
				if(player.isOp())
				{
					UserDataObject u = new UserDataObject();
					if(u.getInt(player, (byte)1)!=-9)
					{
						u.clearAll(player);
						removeFlagStack("��f��l���� �ޱ�", 54,0,1,Arrays.asList("��7������ �����մϴ�." ,"��0"+ Flow), 22, inv);
					}
				}
				else
					removeFlagStack("��f��l���� �ޱ�", 54,0,1,Arrays.asList("��7������ �����մϴ�." ,"��0"+ Flow), 22, inv);
			}
		}
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+ isOP), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 26, inv);
		player.openInventory(inv);
	}
	
	public void KillMonsterGUI(Player player, String QuestName, short Flow, boolean isOP)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");
		YamlLoader PlayerQuestList = new YamlLoader();
		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");

		String UniqueCode = "��0��0��5��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0��� �ؾ� �� ���� ���");
		
		Object[] c = QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Monster").getKeys(false).toArray();
		for(int counter = 0; counter < c.length; counter++)
		{
			String MobName = QuestList.getString(QuestName+".FlowChart."+Flow+".Monster."+counter+".MonsterName");
			int Amount = QuestList.getInt(QuestName+".FlowChart."+Flow+".Monster."+counter+".Amount");
			int PlayerKillAmount = PlayerQuestList.getInt("Started."+QuestName+".Hunt."+counter);
			
	        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1);
	        SkullMeta meta = (SkullMeta) skull.getItemMeta();
	        meta.setOwner(MobName);
	        meta.setDisplayName("��6"+ SkullType(MobName));
	        meta.setLore(Arrays.asList("��f[" +PlayerKillAmount+"/"+ Amount + "]"));
	        skull.setItemMeta(meta);
	        stackItem(skull, counter, inv);
			//Stack2("��6"+ MobName, 266,0,1,Arrays.asList("��f[" +PlayerKillAmount+"/"+ Amount + "]"), counter, inv);
		}
		
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+ isOP), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 26, inv);
		player.openInventory(inv);
	}
	
	public void HarvestGUI(Player player, String QuestName, short Flow, boolean isOP)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");
		YamlLoader PlayerQuestList = new YamlLoader();
		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");

		String UniqueCode = "��0��0��5��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0ä�� �ؾ� �� ��� ���");
		
		Object[] c = QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Block").getKeys(false).toArray();
		for(int counter = 0; counter < c.length; counter++)
		{
			int BlockID = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".BlockID");
			byte BlockData = (byte) QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".BlockData");
			int Amount = QuestList.getInt(QuestName+".FlowChart."+Flow+".Block."+counter+".Amount");
			boolean DataEquals = QuestList.getBoolean(QuestName+".FlowChart."+Flow+".Block."+counter+".DataEquals");
			int PlayerHarvestAmount = PlayerQuestList.getInt("Started."+QuestName+".Block."+counter);
			event.EventInteract IT = new event.EventInteract();
			
			if(DataEquals == true)
				stack("��e"+IT.setItemDefaultName((short) BlockID,(byte)BlockData), BlockID, BlockData, 1, Arrays.asList("��f[" +PlayerHarvestAmount+"/"+ Amount + "]","","��7������ ID : " +BlockID,"��7������ Data : " +BlockData), counter, inv);
			else
				stack("��e�ƹ��� "+IT.setItemDefaultName((short) BlockID,(byte)BlockData)+"��e ����", BlockID, 0, 1, Arrays.asList("��f[" +PlayerHarvestAmount+"/"+ Amount + "]","","��7������ ID : " +BlockID), counter, inv);
		}
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+ isOP), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ ChatColor.stripColor(QuestName)), 26, inv);
		player.openInventory(inv);
	}
	
	public void KeepGoing(Player player, String QuestName, short Flow, short Mob, boolean Harvest)
	{
		String UniqueCode = "��0��0��5��0��9��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0��� ��� �Ͻðڽ��ϱ�?");

		if(Harvest == false)
		{
			removeFlagStack("��a��l��� ����ϱ�", 386,0,1,Arrays.asList("��7��� ����� �߰��� ����մϴ�.","��0"+Flow,"��0"+Mob), 10, inv);
			removeFlagStack("��c��l��� �ߴ��ϱ�", 166,0,1,Arrays.asList("��7��� ��� �߰��� �����մϴ�.","��0"+ ChatColor.stripColor(QuestName)), 16, inv);
		}
		else
		{
			removeFlagStack("��a��l��� ����ϱ�", 386,0,1,Arrays.asList("��7ä�� ����� �߰��� ����մϴ�.","��0"+Flow,"��0"+Mob), 10, inv);
			removeFlagStack("��c��l��� �ߴ��ϱ�", 166,0,1,Arrays.asList("��7ä�� ��� �߰��� �����մϴ�.","��0"+ ChatColor.stripColor(QuestName)), 16, inv);
		}
		player.openInventory(inv);
	}
	
	public void Quest_NavigationListGUI(Player player, short page, String QuestName)
	{
		YamlLoader NavigationConfig = new YamlLoader();
		NavigationConfig.getConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��5��0��a��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0����Ʈ �׺� ���� : " + (page+1));

		Object[] Navi= NavigationConfig.getConfigurationSection("").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < Navi.length;count++)
		{
			if(count > Navi.length || loc >= 45) break;
			String NaviName = NavigationConfig.getString(Navi[count]+".Name");
			String world = NavigationConfig.getString(Navi[count]+".world");
			int x = NavigationConfig.getInt(Navi[count]+".x");
			short y = (short) NavigationConfig.getInt(Navi[count]+".y");
			int z = NavigationConfig.getInt(Navi[count]+".z");
			int Time = NavigationConfig.getInt(Navi[count]+".time");
			short sensitive = (short) NavigationConfig.getInt(Navi[count]+".sensitive");
			boolean Permition = NavigationConfig.getBoolean(Navi[count]+".onlyOPuse");
			byte ShowArrow = (byte) NavigationConfig.getInt(Navi[count]+".ShowArrow");
			
			
			String TimeS = "��3<������ �� ���� ����>";
			String PermitionS = "��3<OP�� ��� ����>";
			String sensitiveS = "��9<�ݰ� "+sensitive+"��� �̳��� �������� ����>";
			String ShowArrowS = "��3<�⺻ ȭ��ǥ ���>";
			if(Permition == false)
				PermitionS = "��3<��� ��� ����>";
			if(Time >= 0)
				TimeS = "��3<"+Time+"�� ���� ����>";
			switch(ShowArrow)
			{
			default:
				ShowArrowS = "��3<�⺻ ȭ��ǥ ���>";
				break;
			}
			removeFlagStack("��0��l" + Navi[count].toString(), 395,0,1,Arrays.asList(
			"��e��l"+NaviName,"",
			"��9[���� ����]","��9���� : ��f"+world,
			"��9��ǥ : ��f"+x+","+y+","+z,sensitiveS,"",
			"��3[��Ÿ �ɼ�]",TimeS,PermitionS,ShowArrowS,""
			,"��e[�� Ŭ���� �׺� ����]"), loc, inv);
			loc++;
		}
		
		if(Navi.length-(page*44)>45)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+QuestName), 53, inv);
		player.openInventory(inv);
	}
	
	public void Quest_OPChoice(Player player,String QuestName, short Flow,short page)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

		String UniqueCode = "��0��0��5��0��b��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0����Ʈ ���� Ȯ��");
		
		String[] script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.0.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.0.Var")).split("%enter%");
		
		switch(QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Choice").getKeys(false).size())
		{
		case 1:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 13, inv);
			break;
		case 2:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 12, inv);
			script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.1.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.1.Var")).split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,2,Arrays.asList(script), 14, inv);
			break;
		case 3:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 11, inv);
			script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.1.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.1.Var")).split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,2,Arrays.asList(script), 13, inv);
			script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.2.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.2.Var")).split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,3,Arrays.asList(script), 15, inv);
			break;
		case 4:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 10, inv);
			script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.1.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.1.Var")).split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,2,Arrays.asList(script), 12, inv);
			script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.2.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.2.Var")).split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,3,Arrays.asList(script), 14, inv);
			script = (QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.3.Lore")+"%enter%%enter%��9��l������ ���� : ��f"+QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.3.Var")).split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,4,Arrays.asList(script), 16, inv);
			break;
		}
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+page), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+QuestName), 26, inv);
		player.openInventory(inv);
	}
	
	public void Quest_UserChoice(Player player,String QuestName, short Flow)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

		String UniqueCode = "��0��0��5��0��c��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0����Ʈ ����");

		String lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.0.Lore").replace("%player%", player.getName());
		String[] script = lore.split("%enter%");
		
		switch(QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Choice").getKeys(false).size())
		{
		case 1:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 13, inv);
			break;
		case 2:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 12, inv);
			lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.1.Lore").replace("%player%", player.getName());
			script = lore.split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,2,Arrays.asList(script), 14, inv);
			break;
		case 3:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 11, inv);
			lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.1.Lore").replace("%player%", player.getName());
			script = lore.split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,2,Arrays.asList(script), 13, inv);
			lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.2.Lore").replace("%player%", player.getName());
			script = lore.split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,3,Arrays.asList(script), 15, inv);
			break;
		case 4:
			removeFlagStack("��f��l[����]", 72,0,1,Arrays.asList(script), 10, inv);
			lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.1.Lore").replace("%player%", player.getName());
			script = lore.split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,2,Arrays.asList(script), 12, inv);
			lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.2.Lore").replace("%player%", player.getName());
			script = lore.split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,3,Arrays.asList(script), 14, inv);
			lore = QuestList.getString(QuestName+".FlowChart."+Flow+".Choice.3.Lore").replace("%player%", player.getName());
			script = lore.split("%enter%");
			removeFlagStack("��f��l[����]", 72,0,4,Arrays.asList(script), 16, inv);
			break;
		}
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+QuestName,"��0"+Flow), 26, inv);
		player.openInventory(inv);
	}
	
	
	public void QuestRouter(Player player,String QuestName)
	{
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");
		YamlLoader PlayerQuestList = new YamlLoader();
		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
		
		String QuestType = QuestList.getString(QuestName+".FlowChart."+PlayerQuestList.getInt("Started."+QuestName+".Flow")+".Type");
		short FlowChart = (short) PlayerQuestList.getInt("Started."+QuestName+".Flow");
		String NPCname = QuestList.getString(QuestName+".FlowChart."+FlowChart+".NPCname");
		if(QuestType == null)
		{
			util.ETC ETC = new util.ETC();
			YamlLoader Config = new YamlLoader();
			Config.getConfig("config.yml");
			String message = Config.getString("Quest.ClearMessage").replace("%QuestName%", QuestName);
			player.sendMessage(message);
			PlayerQuestList.set("Ended."+QuestName+".ClearTime", ETC.getNowUTC());
			PlayerQuestList.removeKey("Started."+QuestName+".Flow");
			PlayerQuestList.removeKey("Started."+QuestName+".Type");
			PlayerQuestList.removeKey("Started."+QuestName);
			PlayerQuestList.saveConfig();
			YamlLoader PlayerVarList = new YamlLoader();
			PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
			PlayerVarList.removeKey(QuestName);
			PlayerVarList.saveConfig();
			player.closeInventory();
			SoundEffect.playSound(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.8F);
			
		}
		else
		{
			PlayerQuestList.set("Started."+QuestName+".Type",QuestList.getString(QuestName+".FlowChart." + FlowChart+".Type") );
			PlayerQuestList.saveConfig();
			switch(QuestType)
			{
				case "Cal":
				{
					YamlLoader PlayerVarList = new YamlLoader();
					PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
					int PlayerValue = PlayerVarList.getInt(QuestName);
					int SideValue = QuestList.getInt(QuestName+".FlowChart."+FlowChart+".Value");
					int total = 0;
					switch(QuestList.getInt(QuestName+".FlowChart."+FlowChart+".Comparison"))
					{
					case 1:
						total = PlayerValue+SideValue;
						break;
					case 2:
						total = PlayerValue-SideValue;
						break;
					case 3:
						total = PlayerValue*SideValue;
						break;
					case 4:
						total = PlayerValue/SideValue;
						break;
					case 5:
						total = PlayerValue%SideValue;
						break;
					}
					if(total > 40000)
						total = 40000;
					if(total < -2000)
						total = -2000;
					PlayerVarList.set(QuestName,total);
					PlayerVarList.saveConfig();
					PlayerQuestList.set("Started."+QuestName+".Flow",PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
					PlayerQuestList.saveConfig();
					QuestRouter(player, QuestName);
					return;
				}
				case "IF":
				{
					YamlLoader PlayerVarList = new YamlLoader();
					PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
					int PlayerValue = PlayerVarList.getInt(QuestName);
					int SideValue = QuestList.getInt(QuestName+".FlowChart."+FlowChart+".Value");
					boolean isMatch = false;
					switch(QuestList.getInt(QuestName+".FlowChart."+FlowChart+".Comparison"))
					{
					case 1:
						if(PlayerValue==SideValue)
							isMatch = true;
						break;
					case 2:
						if(PlayerValue!=SideValue)
							isMatch = true;
						break;
					case 3:
						if(PlayerValue>SideValue)
							isMatch = true;
						break;
					case 4:
						if(PlayerValue<SideValue)
							isMatch = true;
						break;
					case 5:
						if(PlayerValue>=SideValue)
							isMatch = true;
						break;
					case 6:
						if(PlayerValue<=SideValue)
							isMatch = true;
						break;
					case 7:
						SideValue = QuestList.getInt(QuestName+".FlowChart."+FlowChart+".Max");
						if(QuestList.getInt(QuestName+".FlowChart."+PlayerQuestList.getInt("Started."+QuestName+".Flow")+".Min")<=PlayerValue&&PlayerValue<=SideValue)
							isMatch = true;
						break;
					}
					if(isMatch)
					{
						PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
						PlayerQuestList.saveConfig();
						QuestRouter(player, QuestName);
					}
					else
					{
						QuestList.getString(QuestName+".FlowChart."+PlayerQuestList.getInt("Started."+QuestName+".Flow")+".Type");
						int MeetTheIF = 0;
						for(int count = FlowChart+1; count < QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false).size(); count++)
						{
							if(QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("IF"))
								MeetTheIF = MeetTheIF+1;
							else if(QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("ENDIF"))
								if(MeetTheIF!=0)
									MeetTheIF = MeetTheIF-1;
							if(MeetTheIF==0&&(QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("ENDIF")))
							{
								if(PlayerVarList.getInt(QuestName+".MeetELSE")!=0)
								{
									PlayerVarList.set(QuestName+".MeetELSE",PlayerVarList.getInt(QuestName+".MeetELSE")-1);
									PlayerVarList.saveConfig();
								}
								PlayerQuestList.set("Started."+QuestName+".Flow",count);
								PlayerQuestList.saveConfig();
								QuestRouter(player, QuestName);
								return;
							}
							if(MeetTheIF==0&&(QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("ELSE")))
							{
								PlayerVarList.set(QuestName+".MeetELSE",PlayerVarList.getInt(QuestName+".MeetELSE")+1);
								PlayerVarList.saveConfig();
								PlayerQuestList.set("Started."+QuestName+".Flow",count);
								PlayerQuestList.saveConfig();
								QuestRouter(player, QuestName);
								return;
							}
						}
						//������ ENDIF�� ELSE�� IF�� ã�� ���ϸ� ����Ʈ �Ϸ�� �Ѿ
						util.ETC ETC = new util.ETC();
						YamlLoader Config = new YamlLoader();
						Config.getConfig("config.yml");
						String message = Config.getString("Quest.ClearMessage").replace("%QuestName%", QuestName);
						player.sendMessage(message);
						PlayerQuestList.set("Ended."+QuestName+".ClearTime", ETC.getNowUTC());
						PlayerQuestList.removeKey("Started."+QuestName+".Flow");
						PlayerQuestList.removeKey("Started."+QuestName+".Type");
						PlayerQuestList.removeKey("Started."+QuestName);
						PlayerQuestList.saveConfig();
						PlayerVarList.removeKey(QuestName);
						PlayerVarList.saveConfig();
						player.closeInventory();
						SoundEffect.playSound(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.8F);
					}
				}
				break;
				case "ELSE":
				{
					YamlLoader PlayerVarList = new YamlLoader();
					PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
					if(PlayerVarList.getInt(QuestName+".MeetELSE")==0)
					{
						QuestList.getString(QuestName+".FlowChart."+PlayerQuestList.getInt("Started."+QuestName+".Flow")+".Type");
						short MeetTheIF = 0;
						for(int count = FlowChart+1; count < QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false).size(); count++)
						{
							if(QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("IF"))
								MeetTheIF++;
							else if(MeetTheIF!=0&&QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("ENDIF"))
								if(MeetTheIF!=0)
									MeetTheIF--;
							if(MeetTheIF==0&&(QuestList.getString(QuestName+".FlowChart."+count+".Type").equals("ENDIF")))
							{
								PlayerVarList.set(QuestName+".MeetElse",0);
								PlayerVarList.saveConfig();
								PlayerQuestList.set("Started."+QuestName+".Flow",count);
								PlayerQuestList.saveConfig();
								QuestRouter(player, QuestName);
								return;
							}
						}
						//������ ENDIF�� ã�� ���ϸ� ����Ʈ �Ϸ�� �Ѿ
						util.ETC ETC = new util.ETC();
						YamlLoader Config = new YamlLoader();
						Config.getConfig("config.yml");
						String message = Config.getString("Quest.ClearMessage").replace("%QuestName%", QuestName);
						player.sendMessage(message);
						PlayerQuestList.set("Ended."+QuestName+".ClearTime", ETC.getNowUTC());
						PlayerQuestList.removeKey("Started."+QuestName+".Flow");
						PlayerQuestList.removeKey("Started."+QuestName+".Type");
						PlayerQuestList.removeKey("Started."+QuestName);
						PlayerQuestList.saveConfig();
						PlayerVarList.removeKey(QuestName);
						PlayerVarList.saveConfig();
						player.closeInventory();
						SoundEffect.playSound(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.8F);
					}
					else
					{
						PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
						PlayerQuestList.saveConfig();
						QuestRouter(player, QuestName);
					}
				}
				break;
				case "ENDIF":
				{
					YamlLoader PlayerVarList = new YamlLoader();
					PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
					if(PlayerVarList.getInt(QuestName+".MeetELSE")!=0)
					{
						PlayerVarList.set(QuestName+".MeetELSE",PlayerVarList.getInt(QuestName+".MeetELSE")-1);
						PlayerVarList.saveConfig();
					}
					PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
					PlayerQuestList.saveConfig();
					QuestRouter(player, QuestName);
				}
				break;
				case "QuestFail":
				{
					player.sendMessage("��c[����Ʈ] : ����Ʈ�� �ϼ����� ���Ͽ����ϴ�!");
					PlayerQuestList.set("Ended."+QuestName+".ClearTime", new util.ETC().getNowUTC());
					PlayerQuestList.removeKey("Started."+QuestName);
					PlayerQuestList.saveConfig();
					YamlLoader PlayerVarList = new YamlLoader();
					PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
					PlayerVarList.removeKey(QuestName);
					PlayerVarList.saveConfig();
					player.closeInventory();
					SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
					SoundEffect.playSound(player, Sound.ENTITY_WITHER_DEATH, 0.7F, 0.8F);
				}
				break;
				case "QuestReset":
				{
					player.sendMessage("��e[����Ʈ] : ����Ʈ�� �����Ͽ����ϴ�!");
					PlayerQuestList.removeKey("Started."+QuestName);
					PlayerQuestList.saveConfig();
					YamlLoader PlayerVarList = new YamlLoader();
					PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
					PlayerVarList.removeKey(QuestName);
					PlayerVarList.saveConfig();
					player.closeInventory();
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 1.2F, 0.8F);
				}
			break;
			case "VarChange":
			{
				YamlLoader PlayerVarList = new YamlLoader();
				PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
				PlayerVarList.set(QuestName, QuestList.getInt(QuestName+".FlowChart."+FlowChart+".Value"));
				PlayerVarList.saveConfig();
				PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
				PlayerQuestList.saveConfig();
				QuestRouter(player, QuestName);
			}
			break;
			case "Choice":
				Quest_UserChoice(player, QuestName,FlowChart);
				break;
			case "Nevigation":
			{
				String UTC = QuestList.getString(QuestName+".FlowChart."+FlowChart+".NeviUTC");
				YamlLoader NavigationConfig = new YamlLoader();
				NavigationConfig.getConfig("Navigation/NavigationList.yml");
				if(NavigationConfig.contains(UTC))
				{
					ServerTickMain.NaviUsingList.add(player.getName());
					player.closeInventory();
					SoundEffect.playSound(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
					
					servertick.ServerTickObject STSO = new servertick.ServerTickObject(Long.parseLong(UTC), "NV");
					STSO.setCount(0);//Ƚ �� �ʱ�ȭ
					STSO.setMaxCount(NavigationConfig.getInt(UTC+".time"));//N�ʰ� �׺���̼�
					//-1�� ������, N�ʰ��� �ƴ�, ã�� �� �� ���� �׺���̼� ����
					STSO.setString((byte)1, NavigationConfig.getString(UTC+".world"));//������ ���� �̸� ����
					STSO.setString((byte)2, player.getName());//�÷��̾� �̸� ����
					
					STSO.setInt((byte)0, NavigationConfig.getInt(UTC+".x"));//������X ��ġ����
					STSO.setInt((byte)1, NavigationConfig.getInt(UTC+".y"));//������Y ��ġ����
					STSO.setInt((byte)2, NavigationConfig.getInt(UTC+".z"));//������Z ��ġ����
					STSO.setInt((byte)3, NavigationConfig.getInt(UTC+".sensitive"));//���� ���� ����
					STSO.setInt((byte)4, NavigationConfig.getInt(UTC+".ShowArrow"));//��ƼŬ ����
					
					servertick.ServerTickMain.Schedule.put(Long.parseLong(UTC), STSO);
					player.sendMessage("��e[�׺���̼�] : ��ã�� �ý����� �����˴ϴ�!");
					player.sendMessage("��e(ȭ��ǥ�� ������ ���� ���, [ESC] �� [����] �� [���� ����] ���� [����]�� [���]�� ������ �ּ���!)");
					
				}
				else
				{
					SoundEffect.playSound(player, Sound.BLOCK_NOTE_BASS, 1.0F, 1.0F);
					player.sendMessage("��c[�׺���̼�] : ��ϵ� �׺���̼��� ã�� �� �����ϴ�! �����ڿ��� �����ϼ���!");
				}
				PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
				PlayerQuestList.saveConfig();
				QuestRouter(player, QuestName);
			}
			break;
			case "Whisper":
			{
				String script3 = "��f"+QuestList.getString(QuestName+".FlowChart."+FlowChart+".Message");
				script3 = script3.replace("%player%", player.getName());
				YamlLoader PlayerVarList = new YamlLoader();
				PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
				script3 = script3.replace("%value%", ""+PlayerVarList.getInt(QuestName));
				player.sendMessage(script3);
				PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
				PlayerQuestList.saveConfig();
				QuestRouter(player, QuestName);
			}
			break;
			case "BroadCast":
			{
				String script3 = "��f"+QuestList.getString(QuestName+".FlowChart."+FlowChart+".Message");
				script3 = script3.replace("%player%", player.getName());
				YamlLoader PlayerVarList = new YamlLoader();
				PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
				script3 = script3.replace("%value%", ""+PlayerVarList.getInt(QuestName));
				Bukkit.getServer().broadcastMessage(script3);
				PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
				PlayerQuestList.saveConfig();
				QuestRouter(player, QuestName);
			}
			break;
			case "Script": 
			{
				String script = QuestList.getString(QuestName+".FlowChart."+FlowChart+".Script");
				script = script.replace("%player%", player.getName());
				YamlLoader PlayerVarList = new YamlLoader();
				PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
				script = script.replace("%value%", ""+PlayerVarList.getInt(QuestName));
				String[] scriptA = script.split("%enter%");
				for(int count = 0; count < scriptA.length; count++)
					scriptA[count] = "��f"+ scriptA[count];
				QuestScriptTypeGUI(player, QuestName, NPCname, FlowChart, scriptA);
			}
			break;
			case "PScript": 
			{
				String script2 = QuestList.getString(QuestName+".FlowChart."+FlowChart+".Message");
				script2 = script2.replace("%player%", player.getName());
				YamlLoader PlayerVarList = new YamlLoader();
				PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
				script2 = script2.replace("%value%", ""+PlayerVarList.getInt(QuestName));
				String[] scriptA2 = script2.split("%enter%");
				for(int count = 0; count < scriptA2.length; count++)
					scriptA2[count] = "��f"+ scriptA2[count];
				QuestScriptTypeGUI(player, QuestName, player.getName(), FlowChart, scriptA2);
			}
			break;
			case "Visit":
				PlayerQuestList.set("Started."+QuestName+".AreaName",QuestList.getString(QuestName+".FlowChart."+FlowChart+".AreaName"));
				PlayerQuestList.saveConfig();
				break;
			case "Give":
				ShowItemGUI(player, QuestName, FlowChart, false,false);
				break;
			case "Hunt":
				Object[] MobList = QuestList.getConfigurationSection(QuestName+".FlowChart."+FlowChart+".Monster").getKeys(false).toArray();
				for(int counter = 0; counter < MobList.length; counter++)
					PlayerQuestList.set("Started."+QuestName+".Hunt."+counter,0);
				PlayerQuestList.saveConfig();
				KillMonsterGUI(player, QuestName, FlowChart, false);
				break;
			case "Talk":
				break;
			case "Present":
				ShowItemGUI(player, QuestName, FlowChart, false,true);
				break;
			case "TelePort":
				{
					Location l = new Location(Bukkit.getServer().getWorld(QuestList.getString(QuestName+".FlowChart."+FlowChart+".World")), QuestList.getDouble(QuestName+".FlowChart."+FlowChart+".X"),
						QuestList.getDouble(QuestName+".FlowChart."+FlowChart+".Y")+1, QuestList.getDouble(QuestName+".FlowChart."+FlowChart+".Z"));
					player.teleport(l);
					PottionBuff.givePotionEffect(player, PotionEffectType.BLINDNESS, 1, 15);
					SoundEffect.playSoundLocation(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 0.8F, 1.0F);
					PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
					PlayerQuestList.saveConfig();
					QuestRouter(player, QuestName);
				}
				break;
			case "BlockPlace":
				{
					Location l = new Location(Bukkit.getServer().getWorld(QuestList.getString(QuestName+".FlowChart."+FlowChart+".World")), QuestList.getDouble(QuestName+".FlowChart."+FlowChart+".X"),
						QuestList.getDouble(QuestName+".FlowChart."+FlowChart+".Y"), QuestList.getDouble(QuestName+".FlowChart."+FlowChart+".Z"));
					Block block = Bukkit.getWorld(QuestList.getString(QuestName+".FlowChart."+FlowChart+".World")).getBlockAt(l);
					block.setTypeId(QuestList.getInt(QuestName+".FlowChart."+FlowChart+".ID"));
					block.setData((byte)QuestList.getInt(QuestName+".FlowChart."+FlowChart+".DATA"));
					PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
					PlayerQuestList.saveConfig();
					QuestRouter(player, QuestName);
				}
				break;
			case "Harvest":
				Object[] BlockList = QuestList.getConfigurationSection(QuestName+".FlowChart."+FlowChart+".Block").getKeys(false).toArray();
				for(int counter = 0; counter < BlockList.length; counter++)
					PlayerQuestList.set("Started."+QuestName+".Block."+counter,0);
				PlayerQuestList.saveConfig();
				HarvestGUI(player, QuestName, FlowChart, false);
				break;
			}
		}
	}
	
	
	
	public void AllOfQuestListGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		UserDataObject u = new UserDataObject();
		boolean ChooseQuestGUI = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
		if(slot == 53)//�ݱ�
		{
			if(ChooseQuestGUI == true)
				u.clearAll(player);
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
			{
				if(ChooseQuestGUI == true)
				{
					QuestOptionGUI(player, u.getString(player, (byte)1));
					u.clearAll(player);
				}
				else
					new admin.OPboxGui().opBoxGuiMain(player,(byte) 1);
			}
			else if(slot==48)//���� ������
				AllOfQuestListGUI(player,(short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-2),ChooseQuestGUI);
			else if(slot==50)//���� ������
				AllOfQuestListGUI(player, Short.parseShort(event.getInventory().getTitle().split(" : ")[1]),ChooseQuestGUI);
			else if(slot==49)//�� ����Ʈ
			{
				player.sendMessage("��6/����Ʈ ���� [Ÿ��] [�̸�]" );
				player.sendMessage("��a[Ÿ�� : �Ϲ� / �ݺ� / ���� / ���� / �Ѵ�]");
				player.closeInventory();
			}
			else
			{
				if(ChooseQuestGUI == true)
				{
					String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					YamlLoader YC = new YamlLoader();
					YamlLoader QuestList = new YamlLoader();
					QuestList.getConfig("Quest/QuestList.yml");
					if(QuestName.equalsIgnoreCase(u.getString(player, (byte)1)))
					{
						SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.8F, 1.0F);
						player.sendMessage("��c[����Ʈ] : ���� ����Ʈ�� ����� �� �����ϴ�!");
					}
					else
					{
						QuestList.set(u.getString(player, (byte)1)+".Need.PrevQuest", QuestName);
						QuestList.saveConfig();
						QuestOptionGUI(player, u.getString(player, (byte)1));
						u.clearAll(player);
					}
				}
				else
				{
					if(event.getClick().isLeftClick() == true)
						FixQuestGUI(player, (short) 0, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					else if(event.getClick().isRightClick() == true && event.isShiftClick() == true)
					{
						String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
						YamlLoader YC = new YamlLoader();
						YamlLoader QuestList = new YamlLoader();
						QuestList.getConfig("Quest/QuestList.yml");
						QuestList.removeKey(QuestName);
						QuestList.saveConfig();
				    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
				    	Player[] a = new Player[playerlist.size()];
				    	playerlist.toArray(a);
		  	  			for(int count = 0; count<a.length;count++)
		  	  			{
		  	  		    	if(a[count].isOnline() == true)
		  	  		    	{
		  						SoundEffect.playSound(a[count], Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
		  						a[count].sendMessage("��d[������] : ��e"+ player.getName()+"��d�Բ��� ��e"+ QuestName+"��d����Ʈ�� �����ϼ̽��ϴ�!");
		  	  		    	}	
		  	  		    }
		  	  			Bukkit.getConsoleSender().sendMessage("��e"+ player.getName()+"��d�Բ��� ��e"+ QuestName+"��d����Ʈ�� �����ϼ̽��ϴ�!");
						AllOfQuestListGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1),false);
					}
					else if(event.getClick().isRightClick() == true)
						QuestOptionGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
			}
		}
		
	}

	public void FixQuestGUIClick(InventoryClickEvent event)
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
			String QuestName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			if(slot == 45)//���� ���
				AllOfQuestListGUI(player,(short) 0,false);
			else if(slot == 48)//���� ������
				FixQuestGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2),QuestName);
			else if(slot == 50)//���� ������
				FixQuestGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]),QuestName);
			else if(slot == 49)//�� ������Ʈ �߰�
				SelectObjectPage(player, (byte) 0, QuestName);
			else
			{
				short Flow = Short.parseShort(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				if(event.getClick().isLeftClick() == true)
				{
					if(event.getCurrentItem().getItemMeta().getLore().get(0).contains(" : "))
						switch(event.getCurrentItem().getItemMeta().getLore().get(0).split(" : ")[1])
						{
							case "����":
								ShowItemGUI(player, QuestName, (short) Flow,true,false);
								break;
							case "����":
								{
									new UserDataObject().setInt(player, (byte)1,-9);
									ShowItemGUI(player, QuestName, (short) Flow,true,true);
								}
								break;
							case "���":
								KillMonsterGUI(player, QuestName, (short) Flow, player.isOp());
								break;
							case "ä��" :
								HarvestGUI(player, QuestName, (short) Flow, player.isOp());
								break;
							case "����":
								Quest_OPChoice(player, QuestName, (short) Flow, (short) page);
								break;
						}
				}
				else if(event.getClick().isRightClick() == true && event.isShiftClick() == true)
				{
					YamlLoader YC = new YamlLoader();
					YamlLoader QuestList = new YamlLoader();
					QuestList.getConfig("Quest/QuestList.yml");

					Object[] b = QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false).toArray();

					for(int count = Flow; count <= b.length-1;count++)
						QuestList.set(QuestName+".FlowChart."+count,QuestList.get(QuestName+".FlowChart."+(count+1)));
					QuestList.saveConfig();
					FixQuestGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1),QuestName);
				}
			}
		}
	}
	
	public void MyQuestListGUIClick(InventoryClickEvent event)
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
				new user.StatsGui().StatusGUI(player);
			else if(slot == 48)//���� ������
				MyQuestListGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				MyQuestListGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				YamlLoader PlayerQuestList = new YamlLoader();
				PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
				int Flow = PlayerQuestList.getInt("Started."+QuestName+".Flow");
				if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����") == true)
					ShowItemGUI(player, QuestName, (short) Flow,false,false);
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����") == true)
					ShowItemGUI(player, QuestName, (short) Flow,false,true);
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("óġ") == true)
					KillMonsterGUI(player, QuestName, (short) Flow, player.isOp());
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("����") == true)
					QuestRouter(player, QuestName);
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("ä��") == true)
					HarvestGUI(player, QuestName, (short) Flow, false);
				else if(event.getCurrentItem().getItemMeta().getLore().toString().contains("������") == true)
					Quest_UserChoice(player, QuestName, (short) Flow);
			}
		}
	}

	public void SelectObjectPageClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		UserDataObject u = new UserDataObject();
		
		String QuestName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		YamlLoader QuestConfig = new YamlLoader();
		QuestConfig.getConfig("Quest/QuestList.yml");
		short size = (short) QuestConfig.getConfigurationSection(QuestName+".FlowChart").getKeys(false).size();

		switch ((ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())))
		{
			case "����":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"CV");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[����Ʈ] : �÷����� �������� �� ���� �����ұ��?");
				player.sendMessage("��7(�ּ� -1000 ~ �ִ� 30000)");
				player.closeInventory();
				return;
			case "����":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"CS");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[����Ʈ] : �� ���� �������� ���� �� �ǰ���?");
				player.sendMessage("��7(�ּ� 1�� ~ �ִ� 4��)");
				player.closeInventory();
				return;
			case "�׺�":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				Quest_NavigationListGUI(player, (short) 0, QuestName);
				return;
			case "���":
			case "����":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("���"))
					u.setString(player, (byte)1,"Script");
				else
					u.setString(player, (byte)1,"PScript");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[SYSTEM] : ����� ��縦 ä��â�� �Է��ϼ���!");
				player.sendMessage("��6%enter%��f - ���� ��� ���� -");
				player.sendMessage("��6%player%��f - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage("��6%value%��f - �÷��̾��� ���� ����Ʈ ���� ��Ī�ϱ� -");
				player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
				"��3&3 ��4&4 ��5&5 " +
						"��6&6 ��7&7 ��8&8 " +
				"��9&9 ��a&a ��b&b ��c&c" +
						"��d&d ��e&e ��f&f");
				player.closeInventory();
				return;
			case "�湮":
				YamlLoader AreaList = new YamlLoader();
				AreaList.getConfig("Area/AreaList.yml");
				Object[] arealist =AreaList.getConfigurationSection("").getKeys(false).toArray();

				if(arealist.length <= 0)
				{
					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
					player.sendMessage("��c[����Ʈ] : ������ ������ �����ϴ�!");
					player.sendMessage("��6/���� <�̸�> ������e - ���ο� ������ �����մϴ�. -");
					player.closeInventory();
					return;
				}
				player.sendMessage("��a���������������������� ��Ϧ�����������������");
				for(int count =0; count <arealist.length;count++)
				{
					player.sendMessage("��a  "+ arealist[count].toString());
				}
				player.sendMessage("��a���������������������� ��Ϧ�����������������");
				player.sendMessage("��3[����Ʈ] : �湮�ؾ� �� ���� �̸��� ���� �ּ���!");
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Visit");
				u.setString(player, (byte)2,QuestName);
				player.closeInventory();
				return;
			case "����":
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Give");
				u.setString(player, (byte)3,QuestName);
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				player.sendMessage("��a[SYSTEM] : ��c����ǰ�� ���� �غ��Ͻ� ��,��a ���� NPC�� ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "���":
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Hunt");
				u.setString(player, (byte)2,QuestName);
				u.setString(player, (byte)3,"N");
				u.setInt(player, (byte)2, -1);
				u.setInt(player, (byte)3, -1);
				player.sendMessage("��a[SYSTEM] : ��� �ؾ� �� ���͸� ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "��ȭ":
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Talk");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[SYSTEM] : ��ȭ �ؾ� �� NPC�� ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "����":
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Present");
				u.setString(player, (byte)3,QuestName);
				u.setInt(player, (byte)5, -1);
				player.sendMessage("��a[SYSTEM] : ��c���� �������� ���� �غ��Ͻ� ��,��a ������ �� NPC�� ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "�̵�":
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"TelePort");
				u.setString(player, (byte)3,QuestName);
				player.sendMessage("��a[SYSTEM] : �÷��̾ �̵� ��ų ��ġ�� ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "ä��":
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Harvest");
				u.setString(player, (byte)2,QuestName);
				u.setString(player, (byte)3,"null");

				u.setInt(player, (byte)1, 0);//��� ID
				u.setInt(player, (byte)2, 0);//��� DATA
				u.setInt(player, (byte)3, -1);//������ ���
				u.setInt(player, (byte)4, -1);//������ ���
				player.sendMessage("��a[SYSTEM] : ä���ؾ� �� ����� ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "���":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"BlockPlace");
				u.setString(player, (byte)2,QuestName);
				u.setString(player, (byte)3,"null");
				
				u.setInt(player, (byte)1, 0);//��� ID
				u.setInt(player, (byte)2, 0);//��� DATA
				player.sendMessage("��a[����Ʈ] : ����� ��ġ�� ������ ��Ŭ�� �ϼ���!");
				player.closeInventory();
				return;
			case "�Ҹ�":
				return;
			case "�Ӹ�":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Whisper");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[����Ʈ] : � �޽����� �����ϰ� �����Ű���?");
				player.sendMessage("��6%player%��f - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage("��6%value%��f - �÷��̾��� ���� ����Ʈ ���� ��Ī�ϱ� -");
				player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
				"��3&3 ��4&4 ��5&5 " +
						"��6&6 ��7&7 ��8&8 " +
				"��9&9 ��a&a ��b&b ��c&c" +
						"��d&d ��e&e ��f&f");
				player.closeInventory();
				return;
			case "��ü":
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"BroadCast");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[����Ʈ] : � �޽����� �����ϰ� �����Ű���?");
				player.sendMessage("��6%player%��f - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage("��6%value%��f - �÷��̾��� ���� ����Ʈ ���� ��Ī�ϱ� -");
				player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
				"��3&3 ��4&4 ��5&5 " +
						"��6&6 ��7&7 ��8&8 " +
				"��9&9 ��a&a ��b&b ��c&c" +
						"��d&d ��e&e ��f&f");
				player.closeInventory();
				return;
			case "���� ���":
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				FixQuestGUI(player,(short) 0,QuestName);
				return;
			case "�ݱ�":
				SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
				return;
		}

		if(size != 0)
		{
			switch ((ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())))
			{
			case "���":
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Cal");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[����Ʈ] : � ������ �Ͻð� ��������?");
				player.sendMessage("��6��l1. ��fA �� B (�÷��̾� ������ B�� ���մϴ�.)");
				player.sendMessage("��6��l2. ��fA �� B (�÷��̾� ������ B�� ���ϴ�.)");
				player.sendMessage("��6��l3. ��fA �� B (�÷��̾� ������ B�� ���մϴ�.)");
				player.sendMessage("��6��l4. ��fA �� B (�÷��̾� ������ B�� ���� ���� ���մϴ�.)");
				player.sendMessage("��6��l5. ��fA �� B (�÷��̾� ������ B�� ���� �������� ���մϴ�.)");
				player.sendMessage("��7(�ּ� 1 ~ �ִ� 5 ���� �� �Է�)");
				player.closeInventory();
			}
			return;
			case "����Ʈ �ʱ�ȭ":
			{
				
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				QuestConfig.set(QuestName+".FlowChart."+size+".Type", "QuestReset");
		    	QuestConfig.saveConfig();
				FixQuestGUI(player, (short) 0, QuestName);
			}
			break;
			case "����Ʈ ����":
				{
					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
					QuestConfig.set(QuestName+".FlowChart."+size+".Type", "QuestFail");
	    	    	QuestConfig.saveConfig();
	    			FixQuestGUI(player, (short) 0, QuestName);
				}
			break;
			case "IF":
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"IFTS");
				u.setString(player, (byte)2,QuestName);
				player.sendMessage("��a[����Ʈ] : � �񱳸� �Ͻð� ��������?");
				player.sendMessage("��6��l1. ��fA == B (�÷��̾� ������ B�� ������?)");
				player.sendMessage("��6��l2. ��fA != B (�÷��̾� ������ B�� �ٸ���?)");
				player.sendMessage("��6��l3. ��fA > B (�÷��̾� ������ B���� ū��?)");
				player.sendMessage("��6��l4. ��fA < B (�÷��̾� ������ B���� ������?)");
				player.sendMessage("��6��l5. ��fA >= B (�÷��̾� ������ B���� ũ�ų� ������?)");
				player.sendMessage("��6��l6. ��fA <= B (�÷��̾� ������ B���� �۰ų� ������?)");
				player.sendMessage("��6��l7. ��fC <= A <= B (�÷��̾� ������ �ּ� C ~ �ִ� B �����ΰ�?)");
				player.sendMessage("��7(�ּ� 1 ~ �ִ� 7 ���� �� �Է�)");
				player.closeInventory();
			}
			break;
			case "ELSE":
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				QuestConfig.set(QuestName+".FlowChart."+size+".Type", "ELSE");
    	    	QuestConfig.saveConfig();
    			FixQuestGUI(player, (short) 0, QuestName);
			}
			break;
			case "ENDIF":
			{
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
				QuestConfig.set(QuestName+".FlowChart."+size+".Type", "ENDIF");
    	    	QuestConfig.saveConfig();
    			FixQuestGUI(player, (short) 0, QuestName);
			}
			break;
			}
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
			player.sendMessage("��c[����Ʈ] : �ش� �׸��� ù ��° ���� ��ҷ� �� �� �����ϴ�!");
		}
		return;
	}

	public void QuestScriptTypeGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 26)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else if(slot == 13)
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.8F);
			String QuestName = ChatColor.stripColor(event.getInventory().getItem(19).getItemMeta().getDisplayName());
			YamlLoader PlayerQuestList = new YamlLoader();
			PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
			PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
			PlayerQuestList.saveConfig();
			player.closeInventory();
			QuestRouter(player, QuestName);
		}
	}
	
	public void GetterItemSetingGUIClick(InventoryClickEvent event)
	{
		if(!event.getClickedInventory().getTitle().equals("container.inventory"))
			if(event.getSlot() == 8)
				event.getWhoClicked().closeInventory();
	}
	
	public void ShowNeedGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 26)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.8F);
			if(slot == 18)
			{
				if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(1)).equalsIgnoreCase("false"))
					MyQuestListGUI(player, (short) 0);
				else
					FixQuestGUI(player, (short) 0, ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(1)));
			}
			else if(slot == 22)
			{
				YamlLoader PlayerQuestList = new YamlLoader();
				YamlLoader QuestList = new YamlLoader();
				QuestList.getConfig("Quest/QuestList.yml");
				PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
				
				String QuestName =  ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(1));
				short QuestFlow =  Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(22).getItemMeta().getLore().get(1)));

				if(QuestList.contains(QuestName+".FlowChart."+QuestFlow+".Item") == true)
				{
					Object[] p =QuestList.getConfigurationSection(QuestName+".FlowChart."+QuestFlow+".Item").getKeys(false).toArray();
					short emptySlot = 0;
					ItemStack item[] = new ItemStack[p.length];
					
					for(int counter = 0; counter < p.length; counter++)
						item[counter] = QuestList.getItemStack(QuestName+".FlowChart."+QuestFlow+".Item."+counter);
					
					for(int counter = 0; counter < player.getInventory().getSize(); counter++)
					{
						if(player.getInventory().getItem(counter) == null)
							emptySlot++;
					}
					
					if(emptySlot >= item.length)
					{
						for(int counter = 0;counter < p.length; counter++)
							player.getInventory().addItem(item[counter]);
					}
					else
					{
						SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 2.0F, 1.7F);
						player.sendMessage("��e[����Ʈ] : ���� �÷��̾��� �κ��丮 ������ ������� �ʾ� ������ ���� �� �����ϴ�!");
						return;
					}
				}
				main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(QuestList.getLong(QuestName + ".FlowChart."+QuestFlow+".Money"), 0, false);

				YamlLoader playerDataYaml = new YamlLoader();
		    	if(playerDataYaml.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
		    	{
		    		playerDataYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
		    		playerDataYaml.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".love", QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".Love"));
		    		playerDataYaml.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".Career", 0);
		    		playerDataYaml.saveConfig();
		    	}
		    	else
		    	{
		    		playerDataYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
		    		int ownlove = playerDataYaml.getInt(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".love");
		    		int owncareer = playerDataYaml.getInt(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".Career");
		    		playerDataYaml.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".love", ownlove +QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".Love"));
		    		playerDataYaml.set(QuestList.getString(QuestName + ".FlowChart."+QuestFlow+".TargetNPCuuid")+".Career", owncareer +QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".Career"));
		    		playerDataYaml.saveConfig();
		    	}
	    		if(QuestList.getInt(QuestName + ".FlowChart."+QuestFlow+".EXP") != 0)
	    			new util.UtilPlayer().addMoneyAndEXP(player, 0, QuestList.getLong(QuestName + ".FlowChart."+QuestFlow+".EXP"), null, false, false);
				
				PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
				PlayerQuestList.saveConfig();
				player.closeInventory();
				QuestRouter(player, QuestName);
			}
		}
	}
	
	public void PresentItemSettingGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		if(slot <= 2 || slot == 8)
		{
			if(!event.getClickedInventory().getTitle().equals("container.inventory"))
			{
				event.setCancelled(true);
				if(slot == 8)
				{
					SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
					player.closeInventory();
				}
				else
				{
					SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.8F);
					UserDataObject u = new UserDataObject();
					u.setType(player, "Quest");
					if(slot == 0)
					{
						player.sendMessage("��a[SYSTEM] : ������ ������� �Է� �� �ּ���. (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)4,"M");
					}
					else if(slot == 1)
					{
						u.setInt(player, (byte)2, 0);
						player.sendMessage("��a[SYSTEM] : ��½�ų ����ġ�� �Է� �� �ּ���. (��e1��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)4,"E");
					}
					else if(slot == 2)
					{
						u.setInt(player, (byte)3, 0);
						player.sendMessage("��a[SYSTEM] : ��½�ų NPC�� ȣ������ �Է� �� �ּ���. (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
						u.setString(player, (byte)4,"L");
					}
					player.closeInventory();
				}
			}
		}
	}
	
	public void KeepGoingClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		
		if(slot == 16)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.8F);
			UserDataObject u = new UserDataObject();
			String QuestName = ChatColor.stripColor(event.getInventory().getItem(16).getItemMeta().getLore().get(1));
			short Flow = Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(10).getItemMeta().getLore().get(1)));
			short Mob = Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(10).getItemMeta().getLore().get(2)));
			if(event.getCurrentItem().getItemMeta().getLore().get(0).contains("���"))
			{
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Hunt");
				u.setString(player, (byte)2,QuestName);
				u.setString(player, (byte)3,"N");
				u.setInt(player, (byte)2, Mob+1);
				u.setInt(player, (byte)3, Flow);
				player.sendMessage("��a[SYSTEM] : ��� �ؾ� �� ���͸� ��Ŭ�� �ϼ���!");
			}
			else
			{
				u.setType(player, "Quest");
				u.setString(player, (byte)1,"Harvest");
				u.setString(player, (byte)2,QuestName);
				u.setString(player, (byte)3,"null");
				u.setInt(player, (byte)1, 0);
				u.setInt(player, (byte)2, 0);
				u.setInt(player, (byte)3, Flow);
				u.setInt(player, (byte)4, Mob+1);
				player.sendMessage("��a[SYSTEM] : ä�� �ؾ� �� ����� ��Ŭ�� �ϼ���!");
			}
			player.closeInventory();
		}
	}
	
	public void QuestOptionGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		
		if(slot == 44)
		{

			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.8F);
			YamlLoader QuestList = new YamlLoader();
			QuestList.getConfig("Quest/QuestList.yml");
			String QuestName = ChatColor.stripColor(event.getInventory().getItem(44).getItemMeta().getLore().get(1));
			if(slot == 36)
				AllOfQuestListGUI(player, (short) 0,false);
			else if(slot == 15)//��ų ��ũ ����
			{
				SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.8F);
				//��ų ���� �� ��ų ��ũ �Է��ϴ� â ������Ʈ �ϱ�
			}
			else if(slot == 4)//����Ʈ Ÿ��
			{
				switch(QuestList.getString(QuestName + ".Type"))
				{
				case "N" :
					QuestList.set(QuestName+".Type", "R");
					break;
				case "R" :
					QuestList.set(QuestName+".Type", "D");
					break;
				case "D" :
					QuestList.set(QuestName+".Type", "W");
					break;
				case "W" :
					QuestList.set(QuestName+".Type", "M");
					break;
				case "M" :
					QuestList.set(QuestName+".Type", "N");
					break;
				}
				QuestList.saveConfig();
				QuestOptionGUI(player, QuestName);
			}
			else if(slot == 29)//�ʼ� �Ϸ� ����Ʈ
			{
				if(event.isLeftClick() == true && event.isShiftClick() == false)
				{
					UserDataObject u = new UserDataObject();
					u.setString(player, (byte)1,QuestName);
					AllOfQuestListGUI(player, (short) 0, true);
				}
				else if(event.isRightClick() == true && event.isShiftClick() == true)
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.8F);
					QuestList.set(QuestName+".Need.PrevQuest", "null");
					QuestList.saveConfig();
					QuestOptionGUI(player, QuestName);
				}
			}
			else
			{
				UserDataObject u = new UserDataObject();
				u.setType(player, "Quest");
				u.setString(player, (byte)2,QuestName);
				player.closeInventory();
				if(slot == 11)//���� ����
				{
					u.setString(player, (byte)1,"Level District");
					player.sendMessage("��a[SYSTEM] : �� ���� ���� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a����)");
				}
				else if(slot == 13)//ȣ���� ����
				{
					u.setString(player, (byte)1,"Love District");
					player.sendMessage("��a[SYSTEM] : ȣ���� �� �̻���� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				}
				else if(slot == 20)//STR ����
				{
					u.setString(player, (byte)1,"STR District");
					player.sendMessage("��a[SYSTEM] : "+main.MainServerOption.statSTR+"�� �� �̻� �Ǿ�� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				}
				else if(slot == 21)//DEX ����
				{
					u.setString(player, (byte)1,"DEX District");
					player.sendMessage("��a[SYSTEM] : "+main.MainServerOption.statDEX+"�� �� �̻� �Ǿ�� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				}
				else if(slot == 22)//INT ����
				{
					u.setString(player, (byte)1,"INT District");
					player.sendMessage("��a[SYSTEM] : "+main.MainServerOption.statINT+"�� �� �̻� �Ǿ�� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				}
				else if(slot == 23)//WILL ����
				{
					u.setString(player, (byte)1,"WILL District");
					player.sendMessage("��a[SYSTEM] : "+main.MainServerOption.statWILL+"�� �� �̻� �Ǿ�� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				}
				else if(slot == 24)//LUK ����
				{
					u.setString(player, (byte)1,"LUK District");
					player.sendMessage("��a[SYSTEM] : "+main.MainServerOption.statLUK+"�� �� �̻� �Ǿ�� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				}
				else if(slot == 33)//����Ʈ ����
				{
					u.setString(player, (byte)1,"Accept District");
					player.sendMessage("��a[SYSTEM] : �� �� ���� �����ϰ� �Ͻðڽ��ϱ�? (��e0��a ~ ��e"+Integer.MAX_VALUE+"��a��)");
				}
			}
		}
	}
	
	public void Quest_NavigationListGUIClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		String QuestName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
		switch (event.getSlot())
		{
		case 45://���� ���
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			SelectObjectPage(player, (byte) 0, QuestName);
			return;
		case 53://������
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
			return;
		case 48://���� ������
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			Quest_NavigationListGUI(player, (short) (page-1), QuestName);
			return;
		case 50://���� ������
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			Quest_NavigationListGUI(player, (short) (page+1), QuestName);
			return;
		default :
			if(event.isLeftClick() == true)
			{
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				YamlLoader QuestConfig = new YamlLoader();
				QuestConfig.getConfig("Quest/QuestList.yml");
	    		int size = QuestConfig.getConfigurationSection(QuestName+".FlowChart").getKeys(false).size();
	    		QuestConfig.set(QuestName+".FlowChart."+size+".Type", "Nevigation");
		    	QuestConfig.set(QuestName+".FlowChart."+size+".NeviUTC",ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
		    	QuestConfig.saveConfig();
				player.sendMessage("��a[����Ʈ] : �׺���̼��� ���������� ��ϵǾ����ϴ�!");
				FixQuestGUI(player, (short) 0, QuestName);
			}
			return;
		}
	}
	
	public void Quest_OPChoiceClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		short page = Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(18).getItemMeta().getLore().get(1)));
		String QuestName = ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(1));
		switch (event.getSlot())
		{
		case 18://���� ���
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			FixQuestGUI(player, page, QuestName);
			return;
		case 26://������
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
			return;
		}
	}
	
	public void Quest_UserChoiceClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		if(event.getSlot() == 26)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
			return;
		}
		else
		{
			YamlLoader QuestList = new YamlLoader();
			QuestList.getConfig("Quest/QuestList.yml");
			YamlLoader PlayerQuestList = new YamlLoader();
			PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
			YamlLoader PlayerVarList = new YamlLoader();
			PlayerVarList.getConfig("Quest/PlayerVar/"+player.getUniqueId()+".yml");
			
			short Flow = Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(2)));
			String QuestName = ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(1));
			int ChoiceLevel = QuestList.getConfigurationSection(QuestName+".FlowChart."+Flow+".Choice").getKeys(false).size();
			byte Slot = (byte) event.getSlot();
			
			if(event.getCurrentItem()!= null)
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			
			if((ChoiceLevel==1&&Slot==13)||(ChoiceLevel==2&&Slot==12)||(ChoiceLevel==3&&Slot==11)||(ChoiceLevel==4&&Slot==10))
				PlayerVarList.set(QuestName, QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.0.Var"));
			else if((ChoiceLevel==2&&Slot==14)||(ChoiceLevel==3&&Slot==13)||(ChoiceLevel==4&&Slot==12))
				PlayerVarList.set(QuestName, QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.1.Var"));
			else if((ChoiceLevel==3&&Slot==15)||(ChoiceLevel==4&&Slot==14))
				PlayerVarList.set(QuestName, QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.2.Var"));
			else if(ChoiceLevel==4&&Slot==16)
				PlayerVarList.set(QuestName, QuestList.getInt(QuestName+".FlowChart."+Flow+".Choice.3.Var"));
			PlayerVarList.saveConfig();
			PlayerQuestList.set("Started."+QuestName+".Flow", PlayerQuestList.getInt("Started."+QuestName+".Flow")+1);
			PlayerQuestList.saveConfig();
			player.closeInventory();
			QuestRouter(player, QuestName);
		}
	}
	
	
	public void GetterItemSetingGUIClose(InventoryCloseEvent event)
	{
		Player player = (Player)event.getPlayer();
		UserDataObject u = new UserDataObject();
		u.setBoolean(player, (byte)1, false);
		
		YamlLoader QuestConfig = new YamlLoader();
		QuestConfig.getConfig("Quest/QuestList.yml");
		
		String QuestName = u.getString(player, (byte)3);
		Object[] b = QuestConfig.getConfigurationSection(QuestName+".FlowChart").getKeys(false).toArray();
		QuestConfig.set(QuestName+".FlowChart."+b.length+".Type", "Give");
		QuestConfig.set(QuestName+".FlowChart."+b.length+".TargetNPCname", u.getString(player, (byte)2));
		QuestConfig.set(QuestName+".FlowChart."+b.length+".TargetNPCuuid", u.getString(player, (byte)1));
		byte itemacount = 0;
		for(int count = 0; count <8; count++)
		{
			if(event.getInventory().getItem(count) != null)
			{
				if(event.getInventory().getItem(count).getAmount() > 0)
				{
					QuestConfig.set(QuestName+".FlowChart."+b.length+".Item."+itemacount, event.getInventory().getItem(count));
					itemacount++;
				}
			}
		}
		QuestConfig.saveConfig();
		SoundEffect.playSound((Player) event.getPlayer(), org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
    	event.getPlayer().sendMessage("��a[SYSTEM] : ���������� ��ϵǾ����ϴ�!");
		u.clearAll(player);
		return;
	}

	public void PresentItemSettingGUIClose(InventoryCloseEvent event)
	{
		Player player = (Player)event.getPlayer();
		YamlLoader QuestConfig = new YamlLoader();
		QuestConfig.getConfig("Quest/QuestList.yml");
		UserDataObject u = new UserDataObject();
		String QuestName = u.getString(player, (byte)3);
		
		if(u.getInt(player, (byte)5) == -1)
		{
			Object[] b = QuestConfig.getConfigurationSection(QuestName+".FlowChart").getKeys(false).toArray();
			u.setInt(player, (byte)5, b.length);
			QuestConfig.set(QuestName+".FlowChart."+b.length+".Type", "Present");
			QuestConfig.set(QuestName+".FlowChart."+b.length+".TargetNPCname", u.getString(player, (byte)2));
			QuestConfig.set(QuestName+".FlowChart."+b.length+".TargetNPCuuid", u.getString(player, (byte)1));
			byte itemacount = 0;
			for(int count = 3; count <8; count++)
			{
				if(event.getInventory().getItem(count) != null)
				{
					if(event.getInventory().getItem(count).getAmount() > 0)
					{
						QuestConfig.set(QuestName+".FlowChart."+b.length+".Item."+itemacount, event.getInventory().getItem(count));
						itemacount++;
					}
				}
			}
			QuestConfig.saveConfig();
		}
		else
		{
			if(u.getInt(player, (byte)1) == -1)
				u.setInt(player, (byte)1, 0);
			if(u.getInt(player, (byte)2) == -1)
				u.setInt(player, (byte)2, 0);
			if(u.getInt(player, (byte)3) == -1)
				u.setInt(player, (byte)3, 0);
			
			QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".Type", "Present");
			QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".TargetNPCname", u.getString(player, (byte)2));
			QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".TargetNPCuuid", u.getString(player, (byte)1));
			QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".Money", u.getInt(player, (byte)1));
			QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".EXP", u.getInt(player, (byte)2));
			QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".Love", u.getInt(player, (byte)3));
			byte itemacount = 0;
			for(int count = 3; count <8; count++)
			{
				if(event.getInventory().getItem(count) != null)
				{
					if(event.getInventory().getItem(count).getAmount() > 0)
					{
						QuestConfig.set(QuestName+".FlowChart."+u.getInt(player, (byte)5)+".Item."+itemacount, event.getInventory().getItem(count));
						itemacount++;
					}
				}
			}
			QuestConfig.saveConfig();
		}
		if(u.getString(player, (byte)4)==null)
		{
			QuestConfig.saveConfig();
			SoundEffect.playSound((Player) event.getPlayer(), org.bukkit.Sound.ENTITY_ITEM_PICKUP, 0.5F,1.2F);
	    	event.getPlayer().sendMessage("��a[SYSTEM] : ���������� �����Ǿ����ϴ�!");
	    	u.clearAll(player);
		}
		return;
	}
	
	public String SkullType(String s)
	{
		String re = s;
		switch(s)
		{
			case "Zombie" : re = "��2����"; break;
			case "Skeleton" : re = "��7���̷���"; break;
			case "Giant" : re = "��2���̾�Ʈ"; break;
			case "Spider" : re = "��7�Ź�"; break;
			case "Witch" : re = "��5����"; break;
			case "Creeper" : re = "��aũ����"; break;
			case "Slime" : re = "��a������"; break;
			case "Ghast" : re = "��7����Ʈ"; break;
			case "Enderman" : re = "��5������"; break;
			case "Zombie Pigman" : re = "��d���� �Ǳ׸�"; break;
			case "Cave Spider" : re = "��7���� �Ź�"; break;
			case "Silverfish" : re = "��7������"; break;
			case "Blaze" : re = "��e������"; break;
			case "Magma Cube" : re = "��e���׸� ť��"; break;
			case "Bat" : re = "��7����"; break;
			case "Endermite" : re = "��5���� �����"; break;
			case "Guardian" : re = "��3�����"; break;
			case "Pig" : re = "��d����"; break;
			case "Sheep" : re = "��f��"; break;
			case "Cow" : re = "��7��"; break;
			case "Chicken" : re = "��f��"; break;
			case "Squid" : re = "��7��¡��"; break;
			case "Wolf" : re = "��f����"; break;
			case "Mooshroom" : re = "��c���� ��"; break;
			case "Ocelot" : re = "��e������"; break;
			case "Horse" : re = "��6��"; break;
			case "Rabbit" : re = "��f�䳢"; break;
			case "Villager" : re = "��6�ֹ�"; break;
			case "Snow Golem" : re = "��f�� ���"; break;
			case "Iron Golem" : re = "��fö ��"; break;
			case "Wither" : re = "��7��l����"; break;
			case "unknown" : re = "��5��l���� �巡��"; break;
			default :
				re = s; break;
		}
		return re;
	}
}
