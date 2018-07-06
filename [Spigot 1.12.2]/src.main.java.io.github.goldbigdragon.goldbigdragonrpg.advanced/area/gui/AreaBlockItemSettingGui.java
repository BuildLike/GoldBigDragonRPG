package area.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import effect.SoundEffect;
import util.GuiUtil;
import util.YamlLoader;

public class AreaBlockItemSettingGui extends GuiUtil {

	private String uniqueCode = "��1��0��2��0��6��r";
	
	public void areaBlockItemSettingGui(Player player,String areaName,String itemData)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�ش� ����� ĳ�� ���� ������");

		ItemStack item = areaYaml.getItemStack(areaName+".Mining."+itemData+".100");
		
		stackItem(item, 4, inv);

		removeFlagStack("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 0, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 1, inv);	
		removeFlagStack("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 2, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 3, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 5, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 6, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 7, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,11,1,Arrays.asList("��7[100% Ȯ���� ���� ������]"), 8, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".90");
		stackItem(item, 13, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 9, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 10, inv);	
		removeFlagStack("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 11, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 12, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 14, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 15, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 16, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,9,1,Arrays.asList("��7[90% Ȯ���� ���� ������]"), 17, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".50");
		stackItem(item, 22, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 18, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 19, inv);	
		removeFlagStack("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 20, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 21, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 23, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 24, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 25, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,4,1,Arrays.asList("��7[50% Ȯ���� ���� ������]"), 26, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".10");
		stackItem(item, 31, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 27, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 28, inv);	
		removeFlagStack("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 29, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 30, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 32, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 33, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 34, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,1,1,Arrays.asList("��7[10% Ȯ���� ���� ������]"), 35, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".1");
		stackItem(item, 40, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 36, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 37, inv);	
		removeFlagStack("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 38, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 39, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 41, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 42, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 43, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,14,1,Arrays.asList("��7[1% Ȯ���� ���� ������]"), 44, inv);

		item = areaYaml.getItemStack(areaName+".Mining."+itemData+".0");
		stackItem(item, 49, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 46, inv);	
		removeFlagStack("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 47, inv);
		removeFlagStack("��c��l[������ �ֱ�>", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 48, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 50, inv);
		removeFlagStack("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 51, inv);	
		removeFlagStack("��c��l<������ �ֱ�]", 160,15,1,Arrays.asList("��7[0.1% Ȯ���� ���� ������]"), 52, inv);
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+itemData), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ areaName), 53, inv);
		player.openInventory(inv);
		return;
	}
	

	public void areaBlockItemSettingClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		if(!event.getClickedInventory().getTitle().equals("container.inventory"))
		{
			if(slot==4||slot==13||slot==22||slot==31||slot==40||slot==49)
				event.setCancelled(false);
			else if(slot == 53)//������
			{
				SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
			else if(slot == 45)//���� ���
			{
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				new AreaBlockSettingGui().areaBlockSettingGui(player, 0, areaName);
			}
		}
	}

	public void areaBlockItemSettingClose(InventoryCloseEvent event)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		String itemData = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
		String areaName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

		if(event.getInventory().getItem(4) != null)
			areaYaml.set(areaName+".Mining."+itemData+".100", event.getInventory().getItem(4));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".100");
			areaYaml.set(areaName+".Mining."+itemData,"0:0");
		}
		if(event.getInventory().getItem(13) != null)
			areaYaml.set(areaName+".Mining."+itemData+".90", event.getInventory().getItem(13));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".90");
			areaYaml.set(areaName+".Mining."+itemData+".90","0:0");
		}
		if(event.getInventory().getItem(22) != null)
			areaYaml.set(areaName+".Mining."+itemData+".50", event.getInventory().getItem(22));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".50");
			areaYaml.set(areaName+".Mining."+itemData+".50","0:0");
		}
		if(event.getInventory().getItem(31) != null)
			areaYaml.set(areaName+".Mining."+itemData+".10", event.getInventory().getItem(31));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".10");
			areaYaml.set(areaName+".Mining."+itemData+".10","0:0");
		}
		if(event.getInventory().getItem(40) != null)
			areaYaml.set(areaName+".Mining."+itemData+".1", event.getInventory().getItem(40));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".1");
			areaYaml.set(areaName+".Mining."+itemData+".1","0:0");
		}
		if(event.getInventory().getItem(49) != null)
			areaYaml.set(areaName+".Mining."+itemData+".0", event.getInventory().getItem(49));
		else
		{
			areaYaml.removeKey(areaName+".Mining."+itemData+".0");
			areaYaml.set(areaName+".Mining."+itemData+".0","0:0");
		}
		areaYaml.saveConfig();
		return;
	}
}
