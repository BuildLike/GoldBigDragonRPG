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
	public void NewBieGUIMain(Player player)
	{
		String UniqueCode = "��0��0��1��1��7��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0�ʽ��� �ɼ�");

		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");
	
		Stack("��f��l�⺻ ������", 54,0,1,Arrays.asList(ChatColor.GRAY + "ù ���ӽ� �������� �����մϴ�."), 2, inv);
		Stack("��f��l�⺻ ����Ʈ", 386,0,1,Arrays.asList(ChatColor.GRAY + "���� ���ڸ��� ����Ʈ�� �ݴϴ�.",ChatColor.GRAY+"(������ Ʃ�丮�� �Դϴ�.)","",ChatColor.DARK_AQUA+"[   �⺻ ����Ʈ   ]",ChatColor.WHITE+""+ChatColor.BOLD+newbieYaml.getString("FirstQuest"),"",ChatColor.YELLOW+"[Ŭ���� ����Ʈ�� �����մϴ�.]",ChatColor.RED+"[Shift + �� Ŭ���� ����Ʈ�� �����մϴ�.]"), 3, inv);
		Stack("��f��l�⺻ ���� ��ġ", 368,0,1,Arrays.asList(ChatColor.GRAY + "���� ���ڸ��� �̵� ��ŵ�ϴ�.","",ChatColor.DARK_AQUA+"[   ���� ��ġ   ]",ChatColor.DARK_AQUA+" - ���� : "+ChatColor.WHITE+""+ChatColor.BOLD+newbieYaml.getString("TelePort.World")
		,ChatColor.DARK_AQUA+" - ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+newbieYaml.getInt("TelePort.X")+","+newbieYaml.getInt("TelePort.Y")+","+newbieYaml.getInt("TelePort.Z"),"",ChatColor.YELLOW+"[Ŭ���� ���� ��ġ�� ��� �˴ϴ�.]"), 4, inv);
		Stack("��f��l���̵�", 340,0,1,Arrays.asList(ChatColor.GRAY + "���̵�â�� �����մϴ�.","",ChatColor.GRAY+"/��Ÿ",ChatColor.DARK_GRAY+"��ɾ ���� ������",ChatColor.DARK_GRAY+"���̵带 Ȯ���Ͻ� �� �ֽ��ϴ�."), 5, inv);
		
		Stack("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void NewBieSupportItemGUI(Player player)
	{
		String UniqueCode = "��1��0��1��1��8��r";
		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");
		
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�ʽ��� ����");

		int newbieSupportItemAmount = newbieYaml.getConfigurationSection("SupportItem").getKeys(false).size();

		byte num = 0;
		for(int count = 0; count < newbieSupportItemAmount;count++)
			if(newbieYaml.getItemStack("SupportItem."+count) != null)
			{
				ItemStackStack(newbieYaml.getItemStack("SupportItem."+count), num, inv);
				num++;
			}

		Stack("��f��l-", 166,0,1,null, 46, inv);
		Stack("��f��l-", 166,0,1,null, 47, inv);
		Stack("��f��l-", 166,0,1,null, 48, inv);
		Stack("��f��l�⺻ ������", 266,0,1,Arrays.asList(ChatColor.GRAY + "�⺻������ ������ �ִ� ���� �����մϴ�.","",ChatColor.YELLOW+"[���� ������]","��f��l"+newbieYaml.getInt("SupportMoney")),49, inv);
		Stack("��f��l-", 166,0,1,null, 50, inv);
		Stack("��f��l-", 166,0,1,null, 51, inv);
		Stack("��f��l-", 166,0,1,null, 52, inv);
		
		Stack("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		
		player.openInventory(inv);
	}
	
	public void NewBieQuestGUI(Player player, short page)
	{
		String UniqueCode = "��0��0��1��1��9��r";
		YamlLoader questYaml = new YamlLoader();
		questYaml.getConfig("Quest/QuestList.yml");
		
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�ʽ��� �⺻�� ���� : " + (page+1));

		Object[] a = questYaml.getKeys().toArray();
		
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String QuestName = a[count].toString();
			Set<String> QuestFlow= questYaml.getConfigurationSection(QuestName+".FlowChart").getKeys(false);
			if(count > a.length || loc >= 45) break;
			switch(questYaml.getString(a[count].toString() + ".Type"))
			{
				case "N" :
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + QuestName, 340,0,1,Arrays.asList(ChatColor.WHITE+"����Ʈ ���� ��� : "+QuestFlow.size()+"��",ChatColor.DARK_AQUA+"����Ʈ Ÿ�� : �Ϲ� ����Ʈ",""), loc, inv);
					break;
				case "R" :
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + QuestName, 386,0,1,Arrays.asList(ChatColor.WHITE+"����Ʈ ���� ��� : "+QuestFlow.size()+"��",ChatColor.DARK_AQUA+"����Ʈ Ÿ�� : �ݺ� ����Ʈ",""), loc, inv);
					break;
				case "D" :
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + QuestName, 403,0,1,Arrays.asList(ChatColor.WHITE+"����Ʈ ���� ��� : "+QuestFlow.size()+"��",ChatColor.DARK_AQUA+"����Ʈ Ÿ�� : ���� ����Ʈ",""), loc, inv);
					break;
				case "W" :
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + QuestName, 403,0,7,Arrays.asList(ChatColor.WHITE+"����Ʈ ���� ��� : "+QuestFlow.size()+"��",ChatColor.DARK_AQUA+"����Ʈ Ÿ�� : ���� ����Ʈ",""), loc, inv);
					break;
				case "M" :
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + QuestName, 403,0,31,Arrays.asList(ChatColor.WHITE+"����Ʈ ���� ��� : "+QuestFlow.size()+"��",ChatColor.DARK_AQUA+"����Ʈ Ÿ�� : �Ѵ� ����Ʈ",""), loc, inv);
					break;
			}
			loc++;
		}
		
		if(a.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void NewBieGuideGUI(Player player)
	{
		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");

		if(newbieYaml.contains("Guide")==false)
		{
			newbieYaml.createSection("Guide");
			newbieYaml.saveConfig();
		}

		String UniqueCode = "��1��0��1��1��a��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�ʽ��� ���̵�");

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
		
		Stack("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		
		player.openInventory(inv);
	}
	
	
	public void NewBieGUIMainInventoryclick(InventoryClickEvent event)
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
					NewBieQuestGUI(player, (short) 0);
				else if(event.isRightClick()&&event.isShiftClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
					YamlLoader newbieYaml = new YamlLoader();
					newbieYaml.getConfig("ETC/NewBie.yml");
					newbieYaml.set("FirstQuest", "null");
					newbieYaml.saveConfig();
					NewBieGUIMain(player);
				}
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
				if(slot == 0)//���� ���
					new admin.OPbox_GUI().OPBoxGUI_Main(player, (byte) 2);
				else if(slot == 2)//�⺻ ������
					NewBieSupportItemGUI(player);
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
					player.sendMessage(ChatColor.GREEN+"[���� �ڷ���Ʈ����] : ���ӽ� �̵� ��ġ ���� �Ϸ�!");
					NewBieGUIMain(player);
				}
				else if(slot == 5)//���̵�
					NewBieGuideGUI(player);
			}
		}
	}
	
	public void NewBieSupportItemGUIInventoryclick(InventoryClickEvent event, String SubjectCode)
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
				NewBieGUIMain(player);
			else if(slot == 49)//�⺻ ����
			{
				if(SubjectCode.compareTo("1a")!=0)//�ʽ��� ���̵� ����â�� �ƴ� ���
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					player.closeInventory();
					player.sendMessage(ChatColor.DARK_AQUA+"[���� ������] : �󸶸� ������ �����ϵ��� �Ͻðڽ��ϱ�?");
					player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
					UserData_Object u = new UserData_Object();
					u.setType(player, "NewBie");
					u.setString(player, (byte)1, "NSM");
				}
			}
		}
	}
	
	public void NewBieQuestGUIInventoryclick(InventoryClickEvent event)
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
				NewBieGUIMain(player);
			else if(slot == 48)//���� ������
				NewBieQuestGUI(player, (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				NewBieQuestGUI(player, (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])));
			else
			{
				String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				YamlLoader newbieYaml = new YamlLoader();
				newbieYaml.getConfig("ETC/NewBie.yml");
				YamlLoader questYaml = new YamlLoader();
				questYaml.getConfig("Quest/QuestList.yml");
				
				if(questYaml.contains(QuestName)==true)
				{
					if(questYaml.getConfigurationSection(QuestName+".FlowChart").getKeys(false).toArray().length != 0)
					{
						newbieYaml.set("FirstQuest", QuestName);
						newbieYaml.saveConfig();
						NewBieGUIMain(player);
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage(ChatColor.RED+"[���� ����Ʈ] : �ش� ����Ʈ�� ����Ʈ ������Ʈ�� �������� �ʽ��ϴ�!");
					}
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[���� ����Ʈ] : �ش� ����Ʈ�� �������� �ʽ��ϴ�!");
				}
			}
		}
	}

	public void InventoryClose_NewBie(InventoryCloseEvent event, String SubjectCode)
	{
		YamlLoader newbieYaml = new YamlLoader();
		newbieYaml.getConfig("ETC/NewBie.yml");
		short num = 0;
		for(int count = 0; count < 45;count++)
		{
			if(event.getInventory().getItem(count) != null)
			{
				if(SubjectCode.compareTo("1a")==0)//���̵�
					newbieYaml.set("Guide."+num, event.getInventory().getItem(count));
				else//if(SubjectCode.compareTo("18")==0)//���� ������
					newbieYaml.set("SupportItem."+num, event.getInventory().getItem(count));
				num++;
			}
			else
				if(SubjectCode.compareTo("1a")==0)//���̵�
					newbieYaml.removeKey("Guide."+count);
				else//if(SubjectCode.compareTo("18")==0)//���� ������
					newbieYaml.removeKey("SupportItem."+count);
		}
		newbieYaml.saveConfig();

		if(SubjectCode.compareTo("1a")==0)//���̵�
			event.getPlayer().sendMessage(ChatColor.GREEN + "[���� ���̵�] : ���������� ���� �Ǿ����ϴ�!");
		else//if(SubjectCode.compareTo("18")==0)//���� ������
			event.getPlayer().sendMessage(ChatColor.GREEN + "[���� ������] : ���������� ���� �Ǿ����ϴ�!");
		SoundEffect.SP((Player) event.getPlayer(), Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
	}
}
