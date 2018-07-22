package monster.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import battle.BattleCalculator;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class MonsterListGui extends GuiUtil{

	private String uniqueCode = "��0��0��8��0��0��r";
	
	public void monsterListGUI(Player player, int page)
	{
		YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��� : " + (page+1));

		String[] monsterNameArrays= monsterYaml.getKeys().toArray(new String[0]);

		byte loc=0;
		StringBuilder sb;
		String monsterName = null;
		for(int count = page*45; count < monsterNameArrays.length || loc >= 45; count++)
		{
			monsterName = monsterNameArrays[count];
			sb = new StringBuilder();
			sb.append("%enter%��f��l �̸� : ��f");
			sb.append(monsterYaml.getString(monsterName+".Name"));
			sb.append("%enter%");
			sb.append("��f��l Ÿ�� : ��f");
			sb.append(monsterYaml.getString(monsterName+".Type"));
			sb.append("%enter%");
			sb.append("��f��l ���� ���̿� : ��f");
			sb.append(monsterYaml.getString(monsterName+".Biome"));
			sb.append("%enter%");
			sb.append("��c��l ����� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".HP"));
			sb.append("%enter%");
			sb.append("��b��l ����ġ : ��f");
			sb.append(monsterYaml.getInt(monsterName+".EXP"));
			sb.append("%enter%");
			sb.append("��e��l ��� �ݾ� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".MIN_Money"));
			sb.append(" ~ ");
			sb.append(monsterYaml.getInt(monsterName+".MAX_Money"));
			sb.append("%enter%");
			sb.append("��c��l ");
			sb.append(MainServerOption.statSTR);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".STR"));
			sb.append("��7 [���� : ");
			sb.append(BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), true));
			sb.append(" ~ ");
			sb.append(BattleCalculator.getCombatDamage(null, 0, monsterYaml.getInt(monsterName+".STR"), false));
			sb.append("]%enter%");
			sb.append("��a��l ");
			sb.append(MainServerOption.statDEX);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".DEX"));
			sb.append("��7 [Ȱ�� : ");
			sb.append(BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, true));
			sb.append(" ~ ");
			sb.append(BattleCalculator.returnRangeDamageValue(null, monsterYaml.getInt(monsterName+".DEX"), 0, false));
			sb.append("]%enter%");
			sb.append("��9��l ");
			sb.append(MainServerOption.statINT);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".INT"));
			sb.append("��7 [���� : ");
			sb.append(monsterYaml.getInt(monsterName+".INT")/4);
			sb.append(" ~ ");
			sb.append((int)(monsterYaml.getInt(monsterName+".INT")/2.5));
			sb.append("]%enter%");
			sb.append("��7��l ");
			sb.append(MainServerOption.statWILL);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".WILL"));
			sb.append("��7 [ũ�� : ");
			sb.append(BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0));
			sb.append(" %]%enter%");
			sb.append("��e��l ");
			sb.append(MainServerOption.statLUK);
			sb.append(" : ��f");
			sb.append(monsterYaml.getInt(monsterName+".LUK"));
			sb.append("��7 [ũ�� : ");
			sb.append(BattleCalculator.getCritical(null,monsterYaml.getInt(monsterName+".LUK"), (int)monsterYaml.getInt(monsterName+".WILL"),0));
			sb.append(" %]%enter%");

			sb.append("��7��l ��� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".DEF"));
			sb.append("%enter%");
			sb.append("��b��l ��ȣ : ��f");
			sb.append(monsterYaml.getInt(monsterName+".Protect"));
			sb.append("%enter%");
			sb.append("��9��l ���� ��� : ��f");
			sb.append(monsterYaml.getInt(monsterName+".Magic_DEF"));
			sb.append("%enter%");
			sb.append("��1��l ���� ��ȣ : ��f");
			sb.append(monsterYaml.getInt(monsterName+".Magic_Protect"));
			sb.append("%enter%");
			sb.append("%enter%��e��l[Shift + �� Ŭ���� ������ ����]%enter%��c��l[Shift + �� Ŭ���� ���� ����]%enter%");

			String[] scriptA = sb.toString().split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			int id = 383;
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
				//case "�޸�" : id=379; data = 3; break;
			}
			
			stack("��f"+monsterName, id, data, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(monsterNameArrays.length-(page*44)>45)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		stack("��f��l�� ����", 339,0,1,Arrays.asList("��7���ο� ���͸� �����մϴ�."), 49, inv);
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void monsterListClick(InventoryClickEvent event)
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
			int page =  (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 1);
			else if(slot == 48)//���� ������
				monsterListGUI(player, page-1);
			else if(slot == 49)//�� ����
			{
				player.closeInventory();
				player.sendMessage("��a[����] : ���ο� ���� �̸��� ���� �ּ���!");
				UserDataObject u = new UserDataObject();
				u.setType(player, "Monster");
				u.setString(player, (byte)1, "NM");
			}
			else if(slot == 50)//���� ������
				monsterListGUI(player, page+1);
			else
			{
				String monsterName = event.getCurrentItem().getItemMeta().getDisplayName().substring(2);
				if(event.isLeftClick() && ! event.isShiftClick())
					new MonsterOptionSettingGui().monsterOptionSettingGui(player, monsterName);
				else if(event.isLeftClick() && event.isShiftClick())
					new monster.MonsterSpawn().spawnEggGive(player, monsterName);
				else if(event.isRightClick() && event.isShiftClick())
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
					YamlLoader monsterListYaml = new YamlLoader();
					monsterListYaml.getConfig("Monster/MonsterList.yml");
					monsterListYaml.removeKey(monsterName);
					monsterListYaml.saveConfig();
					monsterListGUI(player, page);
				}
			}
		}
	}

}
