package admin;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;

public class NewBie_GUI extends Util_GUI
{
	public void newBieGuiMain(Player player)
	{
		String uniqueCode = "��0��0��1��1��7��r";
		Inventory inv = Bukkit.createInventory(null, 9, uniqueCode + "��0�ʽ��� �ɼ�");

		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");
	
		Stack("��f��l�⺻ ������", 54,0,1,Arrays.asList("��7ù ���ӽ� �������� �����մϴ�."), 2, inv);
		Stack("��f��l�⺻ ����Ʈ", 386,0,1,Arrays.asList("��7���� ���ڸ��� ����Ʈ�� �ݴϴ�.","��7(������ Ʃ�丮�� �Դϴ�.)","","��3[   �⺻ ����Ʈ   ]","��f��l"+newbieYaml.getString("FirstQuest"),"","��e[Ŭ���� ����Ʈ�� �����մϴ�.]","��c[Shift + �� Ŭ���� ����Ʈ�� �����մϴ�.]"), 3, inv);
		Stack("��f��l�⺻ ���� ��ġ", 368,0,1,Arrays.asList("��7���� ���ڸ��� �̵� ��ŵ�ϴ�.","","��3[   ���� ��ġ   ]","��3 - ���� : ��f��l"+newbieYaml.getString("TelePort.World")
		,"��3 - ��ǥ : ��f��l"+newbieYaml.getInt("TelePort.X")+","+newbieYaml.getInt("TelePort.Y")+","+newbieYaml.getInt("TelePort.Z"),"","��e[Ŭ���� ���� ��ġ�� ��� �˴ϴ�.]"), 4, inv);
		Stack("��f��l���̵�", 340,0,1,Arrays.asList("��7���̵�â�� �����մϴ�.","","��7/��Ÿ","��8��ɾ ���� ������","��8���̵带 Ȯ���Ͻ� �� �ֽ��ϴ�."), 5, inv);
		
		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void newBieSupportItemGui(Player player)
	{
		String uniqueCode = "��1��0��1��1��8��r";
		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");
		
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�ʽ��� ����");

		int newbieSupportItemAmount = newbieYaml.getConfigurationSection("SupportItem").getKeys(false).size();

		int num = 0;
		for(int count = 0; count < newbieSupportItemAmount;count++)
			if(newbieYaml.getItemStack("SupportItem."+count) != null)
			{
				ItemStackStack(newbieYaml.getItemStack("SupportItem."+count), num, inv);
				num++;
			}

		Stack("��f��l-", 166,0,1,null, 46, inv);
		Stack("��f��l-", 166,0,1,null, 47, inv);
		Stack("��f��l-", 166,0,1,null, 48, inv);
		Stack("��f��l�⺻ ������", 266,0,1,Arrays.asList("��7�⺻������ ������ �ִ� ���� �����մϴ�.","","��e[���� ������]","��f��l"+newbieYaml.getInt("SupportMoney")),49, inv);
		Stack("��f��l-", 166,0,1,null, 50, inv);
		Stack("��f��l-", 166,0,1,null, 51, inv);
		Stack("��f��l-", 166,0,1,null, 52, inv);
		
		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		
		player.openInventory(inv);
	}
	
	public void newBieQuestGui(Player player, short page)
	{
		String uniqueCode = "��0��0��1��1��9��r";
		YamlLoader questYaml = new YamlLoader();
		questYaml.getConfig("Quest/QuestList.yml");
		
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�ʽ��� �⺻�� ���� : " + (page+1));

		Object[] questList = questYaml.getKeys().toArray();
		
		byte loc=0;
		for(int count = page*45; count < questList.length;count++)
		{
			String questName = questList[count].toString();
			Set<String> questFlow= questYaml.getConfigurationSection(questName+".FlowChart").getKeys(false);
			if(count > questList.length || loc >= 45) break;
			switch(questYaml.getString(questList[count].toString() + ".Type"))
			{
				case "N" :
					Stack2("��f��l" + questName, 340,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+questFlow.size()+"��","��3����Ʈ Ÿ�� : �Ϲ� ����Ʈ",""), loc, inv);
					break;
				case "R" :
					Stack2("��f��l" + questName, 386,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+questFlow.size()+"��","��3����Ʈ Ÿ�� : �ݺ� ����Ʈ",""), loc, inv);
					break;
				case "D" :
					Stack2("��f��l" + questName, 403,0,1,Arrays.asList("��f����Ʈ ���� ��� : "+questFlow.size()+"��","��3����Ʈ Ÿ�� : ���� ����Ʈ",""), loc, inv);
					break;
				case "W" :
					Stack2("��f��l" + questName, 403,0,7,Arrays.asList("��f����Ʈ ���� ��� : "+questFlow.size()+"��","��3����Ʈ Ÿ�� : ���� ����Ʈ",""), loc, inv);
					break;
				case "M" :
					Stack2("��f��l" + questName, 403,0,31,Arrays.asList("��f����Ʈ ���� ��� : "+questFlow.size()+"��","��3����Ʈ Ÿ�� : �Ѵ� ����Ʈ",""), loc, inv);
					break;
			}
			loc++;
		}
		
		if(questList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void newBieGuideGui(Player player)
	{
		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");

		if(!newbieYaml.contains("Guide"))
		{
			newbieYaml.createSection("Guide");
			newbieYaml.saveConfig();
		}

		String uniqueCode = "��1��0��1��1��a��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�ʽ��� ���̵�");

		int newbieGuideItemSize= newbieYaml.getConfigurationSection("Guide").getKeys(false).size();

		byte num = 0;
		for(int count = 0; count < newbieGuideItemSize; count++)
			if(newbieYaml.getItemStack("Guide."+count) != null)
			{
				ItemStackStack(newbieYaml.getItemStack("Guide."+count), num, inv);
				num++;
			}

		Stack("��f��l-", 166,0,1,null, 46, inv);
		Stack("��f��l-", 166,0,1,null, 47, inv);
		Stack("��f��l-", 166,0,1,null, 48, inv);
		Stack("��f��l-", 166,0,1,null, 49, inv);
		Stack("��f��l-", 166,0,1,null, 50, inv);
		Stack("��f��l-", 166,0,1,null, 51, inv);
		Stack("��f��l-", 166,0,1,null, 52, inv);
		
		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		
		player.openInventory(inv);
	}
	
	
	public void newBieGuiMainInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		
		if(slot == 8)//�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
			player.closeInventory();
		}
		else
		{
			if(slot == 3)//�⺻ ����Ʈ
			{
				if(event.isLeftClick())
					newBieQuestGui(player, (short) 0);
				else if(event.isRightClick()&&event.isShiftClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
					YamlLoader newbieYaml = new YamlLoader();
					newbieYaml.getConfig("ETC/NewBie.yml");
					newbieYaml.set("FirstQuest", "null");
					newbieYaml.saveConfig();
					newBieGuiMain(player);
				}
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				if(slot == 0)//���� ���
					new admin.OPbox_GUI().opBoxGuiMain(player, (byte) 2);
				else if(slot == 2)//�⺻ ������
					newBieSupportItemGui(player);
				else if(slot == 4)//�⺻ ���� ��ġ
				{
					YamlLoader newbieYaml = new YamlLoader();
					newbieYaml.getConfig("ETC/NewBie.yml");
					newbieYaml.set("TelePort.World", player.getLocation().getWorld().getName());
					newbieYaml.set("TelePort.X", player.getLocation().getX());
					newbieYaml.set("TelePort.Y", player.getLocation().getY());
					newbieYaml.set("TelePort.Z", player.getLocation().getZ());
					newbieYaml.set("TelePort.Pitch", player.getLocation().getPitch());
					newbieYaml.set("TelePort.Yaw", player.getLocation().getYaw());
					newbieYaml.saveConfig();
					player.sendMessage("��a[���� �ڷ���Ʈ����] : ���ӽ� �̵� ��ġ ���� �Ϸ�!");
					newBieGuiMain(player);
				}
				else if(slot == 5)//���̵�
					newBieGuideGui(player);
			}
		}
	}
	
	public void newbieSupportItemGuiInventoryClick(InventoryClickEvent event, String subjectCode)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		if(slot >= 45)
			event.setCancelled(true);
		if(slot == 53)//�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if(slot == 45)//���� ���
				newBieGuiMain(player);
			else if(slot == 49)//�⺻ ����
			{
				if(!subjectCode.equals("1a"))//�ʽ��� ���̵� ����â�� �ƴ� ���
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					player.closeInventory();
					player.sendMessage("��3[���� ������] : �󸶸� ������ �����ϵ��� �Ͻðڽ��ϱ�?");
					player.sendMessage("��3(0 ~ "+Integer.MAX_VALUE+")");
					UserData_Object u = new UserData_Object();
					u.setType(player, "NewBie");
					u.setString(player, (byte)1, "NSM");
				}
			}
		}
	}
	
	public void newbieQuestGuiInventoryClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		if(slot == 53)
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if(slot == 45)//���� ���
				newBieGuiMain(player);
			else if(slot == 48)//���� ������
				newBieQuestGui(player, (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				newBieQuestGui(player, (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])));
			else
			{
				String questName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				YamlLoader newbieYaml = new YamlLoader();
				newbieYaml.getConfig("ETC/NewBie.yml");
				YamlLoader questYaml = new YamlLoader();
				questYaml.getConfig("Quest/QuestList.yml");
				
				if(questYaml.contains(questName)==true)
				{
					if(questYaml.getConfigurationSection(questName+".FlowChart").getKeys(false).toArray().length != 0)
					{
						newbieYaml.set("FirstQuest", questName);
						newbieYaml.saveConfig();
						newBieGuiMain(player);
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[���� ����Ʈ] : �ش� ����Ʈ�� ����Ʈ ������Ʈ�� �������� �ʽ��ϴ�!");
					}
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c[���� ����Ʈ] : �ش� ����Ʈ�� �������� �ʽ��ϴ�!");
				}
			}
		}
	}

	public void newbieInventoryClose(InventoryCloseEvent event, String subjectCode)
	{
		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");
		short num = 0;
		for(int count = 0; count < 45;count++)
		{
			if(event.getInventory().getItem(count) != null)
			{
				if(subjectCode.equals("1a"))//���̵�
					newbieYaml.set("Guide."+num, event.getInventory().getItem(count));
				else//if(SubjectCode.equals("18"))//���� ������
					newbieYaml.set("SupportItem."+num, event.getInventory().getItem(count));
				num++;
			}
			else
				if(subjectCode.equals("1a"))//���̵�
					newbieYaml.removeKey("Guide."+count);
				else//if(SubjectCode.equals("18"))//���� ������
					newbieYaml.removeKey("SupportItem."+count);
		}
		newbieYaml.saveConfig();

		if(subjectCode.equals("1a"))//���̵�
			event.getPlayer().sendMessage("��a[���� ���̵�] : ���������� ���� �Ǿ����ϴ�!");
		else//if(SubjectCode.equals("18"))//���� ������
			event.getPlayer().sendMessage("��a[���� ������] : ���������� ���� �Ǿ����ϴ�!");
		SoundEffect.SP((Player) event.getPlayer(), Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
	}
}
