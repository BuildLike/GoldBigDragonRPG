package customitem.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import admin.OPboxGui;
import customitem.CustomItemAPI;
import effect.SoundEffect;
import util.GuiUtil;
import util.YamlLoader;

public class UseableItemListGui extends GuiUtil{

	private String uniqueCode = "��0��0��3��0��3��r";
	
	public void useableItemListGui(Player player, int page)
	{
	  	YamlLoader itemYaml = new YamlLoader();
		itemYaml.getConfig("Item/Consume.yml");

		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�Ҹ� ������ ��� : " + (page+1));

		String[] consumeItemList = itemYaml.getKeys().toArray(new String[0]);
		
		int loc = 0;
		String itemName = null;
		int id = 0;
		int data = 0;
		CustomItemAPI ciapi = new CustomItemAPI();
		for(int count = page*45; count < consumeItemList.length;count++)
		{
			itemName = itemYaml.getString(consumeItemList[count]+".DisplayName");
			id = itemYaml.getInt(consumeItemList[count]+".ID");
			data = itemYaml.getInt(consumeItemList[count]+".Data");
			if(count > consumeItemList.length || loc >= 45) break;
			removeFlagStack(itemName, id, data, 1, Arrays.asList(ciapi.useableLoreCreater(Integer.parseInt(consumeItemList[count]))), loc, inv);
			loc++;
		}
		
		if(consumeItemList.length-(page*44)>45)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l�� ������", 386,0,1,Arrays.asList("��7���ο� �������� �����մϴ�."), 49, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void useableItemListClick(InventoryClickEvent event)
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
			if(slot==45)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 2);
			else if(slot == 48)//���� ������
				useableItemListGui(player, page-1);
			else if(slot == 49)//�� ������
				new UseableItemTypeSelectGui().useableItemTypeSelectGui(player);
			else if(slot == 50)//���� ������
				useableItemListGui(player, page+1);
			else
			{
				int number = ((page*45)+event.getSlot());
				if(event.isLeftClick()&&!event.isShiftClick())
				{
					player.sendMessage("��a[SYSTEM] : Ŭ���� �������� ���� �޾ҽ��ϴ�!");
					player.getInventory().addItem(event.getCurrentItem());
				}
				if(event.isLeftClick()&&event.isShiftClick())
					new UseableItemMakingGui().useableItemMakingGui(player, number);
				else if(event.isRightClick()&&event.isShiftClick())
				{
				  	YamlLoader itemYaml = new YamlLoader();
					itemYaml.getConfig("Item/Consume.yml");
					short acount =  (short) (itemYaml.getKeys().toArray().length-1);

					for(int counter = number;counter <acount;counter++)
						itemYaml.set(counter+"", itemYaml.get((counter+1)+""));
					itemYaml.removeKey(acount+"");
					itemYaml.saveConfig();
					useableItemListGui(player, page);
				}
			}
		}
	}
}
