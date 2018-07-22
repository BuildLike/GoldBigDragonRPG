package area.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import battle.BattleCalculator;
import effect.SoundEffect;
import main.MainServerOption;
import util.GuiUtil;
import util.YamlLoader;

public class AreaMonsterListGui extends GuiUtil{

	private String uniqueCode = "��0��0��2��0��7��r";
	
	public void areaMonsterListGui(Player player, int page, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ���� ���� : " + (page+1));

		String[] monsterList= monsterYaml.getKeys().toArray(new String[0]);
		String[] monsterNameList= areaYaml.getConfigurationSection(areaName+".Monster").getKeys(false).toArray(new String[0]);

		String lore = null;
		byte loc=0;
		String monsterName = null;
		for(int count = page*45; count < monsterList.length;count++)
		{
			if(count > monsterList.length || loc >= 45) break;
			monsterName = monsterList[count];
			boolean isExit = false;
			for(int count2 = 0; count2 < monsterNameList.length; count2++)
			{
				if(monsterNameList[count2].equals(monsterName))
				{
					isExit=true;
					break;
				}
			}
			
			if(!isExit)
			{
				lore = null;
				lore = "%enter%��f��l �̸� : ��f"+monsterYaml.getString(monsterName+".Name")+"%enter%";
				lore = lore+"��f��l Ÿ�� : ��f"+monsterYaml.getString(monsterName+".Type")+"%enter%";
				lore = lore+"��f��l ���� ���̿� : ��f"+monsterYaml.getString(monsterName+".Biome")+"%enter%";
				lore = lore+"��c��l ����� : ��f"+monsterYaml.getInt(monsterName+".HP")+"%enter%";
				lore = lore+"��b��l ����ġ : ��f"+monsterYaml.getInt(monsterName+".EXP")+"%enter%";
				lore = lore+"��e��l ��� �ݾ� : ��f"+monsterYaml.getInt(monsterName+".MIN_Money")+" ~ "+monsterYaml.getInt(monsterName+".MAX_Money")+"%enter%";
				lore = lore+"��c��l "+MainServerOption.statSTR+" : ��f"+monsterYaml.getInt(monsterName+".STR")
				+"��7 [���� : " + BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), true) + " ~ " + BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), false) + "]%enter%";
				lore = lore+"��a��l "+MainServerOption.statDEX+" : ��f"+monsterYaml.getInt(monsterName+".DEX")
				+"��7 [Ȱ�� : " + BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, true) + " ~ " + BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, false) + "]%enter%";
				lore = lore+"��9��l "+MainServerOption.statINT+" : ��f"+monsterYaml.getInt(monsterName+".INT")
				+"��7 [���� : " + (monsterYaml.getInt(monsterName+".INT")/4)+ " ~ "+(int)(monsterYaml.getInt(monsterName+".INT")/2.5)+"]%enter%";
				lore = lore+"��7��l "+MainServerOption.statWILL+" : ��f"+monsterYaml.getInt(monsterName+".WILL")
				+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
				lore = lore+"��e��l "+MainServerOption.statLUK+" : ��f"+monsterYaml.getInt(monsterName+".LUK")
				+"��7 [ũ�� : " + BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0) + " %]%enter%";
				lore = lore+"��7��l ��� : ��f"+monsterYaml.getInt(monsterName+".DEF")+"%enter%";
				lore = lore+"��b��l ��ȣ : ��f"+monsterYaml.getInt(monsterName+".Protect")+"%enter%";
				lore = lore+"��9��l ���� ��� : ��f"+monsterYaml.getInt(monsterName+".Magic_DEF")+"%enter%";
				lore = lore+"��1��l ���� ��ȣ : ��f"+monsterYaml.getInt(monsterName+".Magic_Protect")+"%enter%";
				lore = lore+"%enter%��e��l[�� Ŭ���� ���� ���]";

				String[] scriptA = lore.split("%enter%");
				for(int counter = 0; counter < scriptA.length; counter++)
					scriptA[counter] =  " "+scriptA[counter];
				short id = 383;
				byte data = 0;
				switch(monsterYaml.getString(monsterName+".Type"))
				{
					case "����ũ����" : case "ũ����" : data=50; break;
					case "�״����̷���" : case "���̷���" : data=51; break;
					case "�Ź�" : data=52; break;
					case "����" :case "���̾�Ʈ" : data=54; break;
					case "�ʴ���������" :case "Ư�뽽����" : case "ū������" :case "���뽽����" : case "����������" : data=55; break;
					case "����Ʈ" : data=56; break;
					case "�����Ǳ׸�" : data=57; break;
					case "������" : data=58; break;
					case "�����Ź�" : data=59; break;
					case "������" : data=60; break;
					case "������" : data=61; break;
					case "ū���׸�ť��" :case "Ư�븶�׸�ť��" : case "���븶�׸�ť��": case "���׸�ť��" : case "�������׸�ť��" : data=62; break;
					case "����" : data=65; break;
					case "����" : data=66; break;
					case "���������" : data=67; break;
					case "��ȣ��" : data=68; break;
					case "����" : data=90; break;
					case "��" : data=91; break;
					case "��" : data=92; break;
					case "��" : data=93; break;
					case "��¡��" : data=94; break;
					case "����" : data=95; break;
					case "������" : data=96; break;
					case "������" : data=98; break;
					case "��" : data=100; break;
					case "�䳢" : data=101; break;
					case "�ֹ�" : data=120; break;
					case "����" : id=399; break;
					case "�����巡��" : id=122; break;
					case "����ũ����Ż" : id=46; break;
				}
				
				stack("��f"+monsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
				loc++;
			}
		}
		
		if(monsterList.length-(page*44)>45)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+areaName), 53, inv);
		player.openInventory(inv);
	}
	

	public void areaMonsterListGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		String areaName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
		int page = Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				new AreaMonsterSettingGui().areaMonsterSettingGui(player, 0, areaName);
			else if(slot == 48)//���� ������
				areaMonsterListGui(player, page-1, areaName);
			else if(slot == 50)//���� ������
				areaMonsterListGui(player, page+1, areaName);
			else
			{
				String mobName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				SoundEffect.playSound(player, Sound.ENTITY_WOLF_AMBIENT, 0.8F, 1.0F);
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				areaYaml.createSection(areaName+".Monster."+mobName);
				areaYaml.saveConfig();
				areaMonsterListGui(player, page, areaName);
			}
		}
	}

}
