package area.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class AreaMonsterSpawnSettingGui extends GuiUtil {

	private String uniqueCode = "��0��0��2��0��2��r";
	
	public void areaMonsterSpawnSettingGui(Player player, int page, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ���� ���� �� : " + (page+1));
		if(!areaYaml.contains(areaName+".MonsterSpawnRule"))
		{
			areaYaml.createSection(areaName+".MonsterSpawnRule");
			areaYaml.saveConfig();
		}
		String[] ruleList= areaYaml.getConfigurationSection(areaName+".MonsterSpawnRule").getKeys(false).toArray(new String[0]);
		byte loc=0;
		for(int count = page*45; count <ruleList.length ;count++)
		{
			if(count > ruleList.length || loc >= 45) break;
			String ruleNumber = ruleList[count];
			if(areaYaml.contains(areaName+".MonsterSpawnRule."+ruleNumber+".Monster"))
				removeFlagStack("��0��l" + (ruleNumber), 383,0,1,Arrays.asList(
						"��6[     ���� �ɼ�     ]","��c-������ ������ ���� ���� �۵� -","��6���� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.world"),
						"��6��ǥ : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.x")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.y")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.z"),
						"��6�ν� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".range")+"���",
						"��6�ð� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".timer")+"�ʸ��� "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".count")+"���� ����",
						"��6�ִ� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".max")+"����",
						"��6���� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".Monster")
						,"","��c[Shift + ��Ŭ���� �� ����]"), loc, inv);
			else
				removeFlagStack("��0��l" + (ruleNumber), 52,0,1,Arrays.asList(
					"��6[     ���� �ɼ�     ]","��c-������ ������ ���� ���� �۵� -","��6���� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.world"),
					"��6��ǥ : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.x")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.y")+","+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".loc.z"),
					"��6�ν� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".range")+"���",
					"��6�ð� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".timer")+"�ʸ��� "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".count")+"���� ����",
					"��6�ִ� : "+areaYaml.getString(areaName+".MonsterSpawnRule."+ruleNumber+".max")+"����"
					,"","��c[Shift + ��Ŭ���� �� ����]"), loc, inv);
			loc++;
		}

		if(ruleList.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ��Ģ �߰�", 52,0,1,Arrays.asList("��7�� ���� ��Ģ�� �߰��մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
		return;
	}

	public void areaMonsterSpawnSettingClick(InventoryClickEvent event)
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
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			String areaName = event.getInventory().getItem(53).getItemMeta().getLore().get(1).substring(2);
			if(slot == 45)//���� ���
				new AreaSettingGui().areaSettingGui(player, areaName);
			else if(slot == 48)//���� ������
				new AreaMonsterSpawnSettingGui().areaMonsterSpawnSettingGui(player, page-1, areaName);
			else if(slot == 49)//�� �߰�
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				long count = new util.ETC().getNowUTC();
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".range", 1);
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".count", 4);
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".timer", 10);
				areaYaml.set(areaName+".MonsterSpawnRule."+count+".max", 10);
				UserDataObject u = new UserDataObject();
				u.setType(player, "Area");
				u.setString(player, (byte)1, count+"");
				u.setString(player, (byte)2, areaName);
				u.setString(player, (byte)3, "MLS");
				areaYaml.saveConfig();
				player.sendMessage("��a[����] : ���Ͱ� ���� �� ��ġ�� ���콺 �� Ŭ�� �ϼ���!");
				player.closeInventory();
			}
			else if(slot == 50)//���� ������
				new AreaMonsterSpawnSettingGui().areaMonsterSpawnSettingGui(player, page+1, areaName);
			else if(event.isRightClick()&&event.isShiftClick())
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
				areaYaml.removeKey(areaName+".MonsterSpawnRule."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				areaYaml.saveConfig();
				new AreaMonsterSpawnSettingGui().areaMonsterSpawnSettingGui(player, page, areaName);
			}
		}
	}
	
}
