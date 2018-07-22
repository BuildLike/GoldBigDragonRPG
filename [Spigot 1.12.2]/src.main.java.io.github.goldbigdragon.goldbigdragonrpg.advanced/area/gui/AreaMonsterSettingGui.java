package area.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import util.GuiUtil;
import util.YamlLoader;

public class AreaMonsterSettingGui extends GuiUtil{

	private String uniqueCode = "��0��0��2��0��3��r";
	
	public void areaMonsterSettingGui(Player player, int page, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
	  	YamlLoader monsterYaml = new YamlLoader();
		monsterYaml.getConfig("Monster/MonsterList.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��ü ���� : " + (page+1));

		String[] monsterNameList= areaYaml.getConfigurationSection(areaName+".Monster").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		short mobNameListLength = (short) monsterNameList.length;
		String monsterName = null;
		String name = null;
		for(int count = page*45; count <mobNameListLength ;count++)
		{
			monsterName = monsterNameList[count];
			if(monsterYaml.contains(monsterName) == true)
			{
				name = monsterYaml.getString(monsterName+".Name");
				if(count > mobNameListLength || loc >= 45) break;
				removeFlagStack("��f��l" + monsterName, 383,0,1,Arrays.asList(
						"��f�̸� : " + name,"��fŸ�� : " + monsterYaml.getString(monsterName+".Type"),
						"��f����� : " + monsterYaml.getInt(monsterName+".HP"),"��f����ġ : " + monsterYaml.getInt(monsterName+".EXP"),
						"��f��� : " + monsterYaml.getInt(monsterName+".MIN_Money")+" ~ " +monsterYaml.getInt(monsterName+".MAX_Money"),
						"","��c[Shift + ��Ŭ���� ��� ����]"), loc, inv);
				loc++;
			}
			else
			{
				areaYaml.removeKey(areaName+".Monster."+monsterName);
				areaYaml.saveConfig();
			}
		}
		if(mobNameListLength-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l���� �߰�", 52,0,1,Arrays.asList("��7�� Ŀ���� ���͸� �߰��մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
		return;
	}

	public void areaMonsterSettingGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		if(slot == 53)//â�ݱ�
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			String areaName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ȭ��
				new AreaSettingGui().areaSettingGui(player, areaName);
			else if(slot == 48)//���� ������
				areaMonsterSettingGui(player, (short) (page-1), areaName);
			else if(slot == 49)//���� �߰�
			{
			  	YamlLoader monsterYaml = new YamlLoader();
				monsterYaml.getConfig("Monster/MonsterList.yml");
				if(monsterYaml.getKeys().size() == 0)
				{
					SoundEffect.playSound(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0F, 1.8F);
					player.sendMessage("��c[����] : ���� ��ϵ� Ŀ���� ���Ͱ� �������� �ʽ��ϴ�!");
					player.sendMessage("��6��l/���� <�̸�> ���� ��e�ش� �̸��� ���� ���͸� �����մϴ�.");
				}
				else
					new AreaMonsterListGui().areaMonsterListGui(player, page, areaName);
			}
			else if(slot == 50)//���� ������
				areaMonsterSettingGui(player, (short) (page+1),areaName);
			else
			{
				if(event.isShiftClick() && event.isRightClick())
				{
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					String monsterName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					areaYaml.removeKey(areaName+".Monster."+monsterName);
					areaYaml.saveConfig();
					areaMonsterSettingGui(player, page,areaName);
				}
			}
		}
	}
	
}
