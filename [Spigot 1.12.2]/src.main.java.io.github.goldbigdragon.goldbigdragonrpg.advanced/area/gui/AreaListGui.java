package area.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import effect.SoundEffect;
import main.MainServerOption;
import util.GuiUtil;
import util.YamlLoader;

public class AreaListGui extends GuiUtil {

	private String uniqueCode = "��0��0��2��0��0��r";
	
	public void areaListGui(Player player, short page)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0��ü ���� ��� : " + (page+1));

		String[] areaList= areaYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		String areaName = null;
		String world = null;
		for(int count = page*45; count < areaList.length;count++)
		{
			areaName = areaList[count];
			
			if(count > areaList.length || loc >= 45) break;
			world = areaYaml.getString(areaName+".World");
			int minXLoc = areaYaml.getInt(areaName+".X.Min");
			int minYLoc = areaYaml.getInt(areaName+".Y.Min");
			int minZLoc = areaYaml.getInt(areaName+".Z.Min");
			int maxXLoc = areaYaml.getInt(areaName+".X.Max");
			int maxYLoc = areaYaml.getInt(areaName+".Y.Max");
			int maxZLoc = areaYaml.getInt(areaName+".Z.Max");
			
			byte priority = (byte) areaYaml.getInt(areaName+".Priority");
			removeFlagStack("��f��l" + areaName, 395,0,1,Arrays.asList(
					"��3���� : "+world,"��3X ���� : "+minXLoc+" ~ " + maxXLoc
					,"��3Y ���� : "+minYLoc+" ~ " + maxYLoc
					,"��3Z ���� : "+minZLoc+" ~ " + maxZLoc
					,"��3�켱 ���� : "+ priority
					,"��8�������� ���� ��ĥ ���",
					"��8�켱 ������ �� ���� ������",
					"��8����˴ϴ�."
					,"","��e[�� Ŭ���� ���� ����]","��c[Shift + ��Ŭ���� ���� ����]"), loc, inv);
			
			loc++;
		}
		
		if(areaList.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ����", 386,0,1,Arrays.asList("��7���ο� ������ �����մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
		return;
	}

	public void areaListGuiClick(InventoryClickEvent event)
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
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			String areaName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			
			if(slot == 45)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 2);
			else if(slot == 48)//���� ������
				areaListGui(player, (short) (page-1));
			else if(slot == 49)//���� �߰�
			{
			  	YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				player.closeInventory();
				event.EventInteract IT = new event.EventInteract();
				player.sendMessage("��3[����] : " + IT.setItemDefaultName(configYaml.getInt("Server.AreaSettingWand"), 0) +"��3 ���������� ������ ������ �� ��,");
				player.sendMessage("��6��l /���� <�����̸�> ���� ��3��ɾ �Է��� �ּ���!");
				SoundEffect.playSound((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			else if(slot == 50)//���� ������
				areaListGui(player, (short) (page+1));
			else
			{
				if(event.isLeftClick())
					new AreaSettingGui().areaSettingGui(player, areaName);
				else if(event.isShiftClick() && event.isRightClick())
				{
					SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					for(int count = 0; count < MainServerOption.AreaList.get(areaYaml.getString(areaName+".World")).size(); count++)
						if(MainServerOption.AreaList.get(areaYaml.getString(areaName+".World")).get(count).areaName.equals(areaName))
							MainServerOption.AreaList.get(areaYaml.getString(areaName+".World")).remove(count);
					areaYaml.removeKey(areaName);
					areaYaml.saveConfig();
					areaListGui(player, page);
				}
			}
		}
	}
}
