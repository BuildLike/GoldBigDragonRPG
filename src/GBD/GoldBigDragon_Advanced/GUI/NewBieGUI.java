package GBD.GoldBigDragon_Advanced.GUI;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import GBD.GoldBigDragon_Advanced.Main.Main;
import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;

public class NewBieGUI extends GUIutil
{
	public void NewBieGUIMain(Player player)
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLACK + "�ʽ��� �ɼ�");

		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager NewBieYM = GUI_YC.getNewConfig("ETC/NewBie.yml");
		
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�⺻ ������", 54,0,1,Arrays.asList(ChatColor.GRAY + "ù ���ӽ� �������� �����մϴ�."), 2, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�⺻ ����Ʈ", 386,0,1,Arrays.asList(ChatColor.GRAY + "���� ���ڸ��� ����Ʈ�� �ݴϴ�.",ChatColor.GRAY+"(������ Ʃ�丮�� �Դϴ�.)","",ChatColor.DARK_AQUA+"[   �⺻ ����Ʈ   ]",ChatColor.WHITE+""+ChatColor.BOLD+NewBieYM.getString("FirstQuest"),"",ChatColor.YELLOW+"[Ŭ���� ����Ʈ�� �����մϴ�.]"), 3, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�⺻ ���� ��ġ", 368,0,1,Arrays.asList(ChatColor.GRAY + "���� ���ڸ��� �̵� ��ŵ�ϴ�.","",ChatColor.DARK_AQUA+"[   ���� ��ġ   ]",ChatColor.DARK_AQUA+" - ���� : "+ChatColor.WHITE+""+ChatColor.BOLD+NewBieYM.getString("TelePort.World")
		,ChatColor.DARK_AQUA+" - ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+NewBieYM.getInt("TelePort.X")+","+NewBieYM.getInt("TelePort.Y")+","+NewBieYM.getInt("TelePort.Z"),"",ChatColor.YELLOW+"[Ŭ���� ���� ��ġ�� ��� �˴ϴ�.]"), 4, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���̵�", 340,0,1,Arrays.asList(ChatColor.GRAY + "���̵�â�� �����մϴ�.","",ChatColor.GRAY+"/��Ÿ",ChatColor.DARK_GRAY+"��ɾ ���� ������",ChatColor.DARK_GRAY+"���̵带 Ȯ���Ͻ� �� �ֽ��ϴ�."), 5, inv);
		
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void NewBieSupportItemGUI(Player player)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager NewBieYM = GUI_YC.getNewConfig("ETC/NewBie.yml");
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLACK + "�ʽ��� ����");

		Object[] a= NewBieYM.getConfigurationSection("SupportItem").getKeys(false).toArray();

		int num = 0;
		for(int count = 0; count < a.length;count++)
			if(NewBieYM.getItemStack("SupportItem."+count) != null)
			{
				ItemStackStack(NewBieYM.getItemStack("SupportItem."+count), num, inv);
				num = num+1;
			}

		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 46, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 47, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 48, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�⺻ ������", 266,0,1,Arrays.asList(ChatColor.GRAY + "�⺻������ ������ �ִ� ���� �����մϴ�.","",ChatColor.YELLOW+"[���� ������]",ChatColor.WHITE+""+ChatColor.BOLD+""+NewBieYM.getInt("SupportMoney")),49, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 50, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 51, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 52, inv);
		
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		
		player.openInventory(inv);
	}
	
	public void NewBieQuestGUI(Player player, int page)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager QuestList  = GUI_YC.getNewConfig("Quest/QuestList.yml");
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLACK + "�ʽ��� �⺻�� ���� : " + (page+1));

		Set<String> b= QuestList.getKeys();
		Object[] a = b.toArray();
		
		int loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String QuestName = a[count].toString();
			Set<String> QuestFlow= QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false);
			if(count > a.length || loc >= 45) break;
			switch(QuestList.getString(a[count].toString() + ".Type"))
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
			loc=loc+1;
		}
		
		if(a.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void NewBieGuideGUI(Player player)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager NewBieYM = GUI_YC.getNewConfig("ETC/NewBie.yml");

		if(NewBieYM.contains("Guide")==false)
		{
			NewBieYM.set("Guide.1", null);
			NewBieYM.saveConfig();
		}
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLACK + "�ʽ��� ���̵�");

		Object[] a= NewBieYM.getConfigurationSection("Guide").getKeys(false).toArray();

		int num = 0;
		for(int count = 0; count < a.length;count++)
			if(NewBieYM.getItemStack("Guide."+count) != null)
			{
				ItemStackStack(NewBieYM.getItemStack("Guide."+count), num, inv);
				num = num+1;
			}

		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 46, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 47, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 48, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 49, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 50, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 51, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "-", 166,0,1,null, 52, inv);
		
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		
		player.openInventory(inv);
	}
	
	
	public void NewBieGUIMainInventoryclick(InventoryClickEvent event)
	{
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		switch (event.getSlot())
		{
		case 0:
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			GBD.GoldBigDragon_Advanced.GUI.OPBoxGUI OGUI = new GBD.GoldBigDragon_Advanced.GUI.OPBoxGUI();
			OGUI.OPBoxGUI_Main(player, 2);
			return;
		case 2://�⺻ ������
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieSupportItemGUI(player);
			return;
		case 3: //�⺻ ����Ʈ
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieQuestGUI(player, 0);
			return;
		case 4: //�⺻ ���� ��ġ
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
			YamlManager NewBieYM = GUI_YC.getNewConfig("ETC/NewBie.yml");
			NewBieYM.set("TelePort.World", player.getLocation().getWorld().getName());
			NewBieYM.set("TelePort.X", player.getLocation().getX());
			NewBieYM.set("TelePort.Y", player.getLocation().getY());
			NewBieYM.set("TelePort.Z", player.getLocation().getZ());
			NewBieYM.set("TelePort.Pitch", player.getLocation().getPitch());
			NewBieYM.set("TelePort.Yaw", player.getLocation().getYaw());
			NewBieYM.saveConfig();
			player.sendMessage(ChatColor.GREEN+"[���� �ڷ���Ʈ����] : ���ӽ� �̵� ��ġ ���� �Ϸ�!");
			NewBieGUIMain(player);
			return;
		case 5://���̵�
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieGuideGUI(player);
			return;
		case 8:
			s.SP(player, Sound.PISTON_RETRACT, 1.0F, 1.0F);
			player.closeInventory();
			return;
		}
	}
	
	public void NewBieSupportItemGUIInventoryclick(InventoryClickEvent event)
	{
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();
		Player player = (Player) event.getWhoClicked();
		switch (event.getSlot())
		{
		case 45:
			event.setCancelled(true);
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieGUIMain(player);
			return;
		case 49://�⺻ ����
			event.setCancelled(true);
			if(!ChatColor.stripColor(event.getInventory().getTitle()).equals("�ʽ��� ���̵�"))
			{
				s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
				player.closeInventory();
				player.sendMessage(ChatColor.DARK_AQUA+"[���� ������] : �󸶸� ������ �����ϵ��� �Ͻðڽ��ϱ�?");
				player.sendMessage(ChatColor.DARK_AQUA+"(0 ~ "+Integer.MAX_VALUE+")");
				Main.UserData.get(player).setType("NewBie");
				Main.UserData.get(player).setString((byte)1, "NSM");
			}
			return;
		case 46:
		case 47:
		case 48:
		case 50:
		case 51:
		case 52:
			event.setCancelled(true);
			return;
		case 53:
			event.setCancelled(true);
			s.SP(player, Sound.PISTON_RETRACT, 1.0F, 1.0F);
			player.closeInventory();
			return;
		}
	}
	
	public void NewBieQuestGUIInventoryclick(InventoryClickEvent event)
	{
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();

		int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		switch (event.getSlot())
		{
		case 45:
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieGUIMain(player);
			return;
		case 48://���� ������
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieQuestGUI(player, page-1);
			return;
		case 50://���� ������
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			NewBieQuestGUI(player, page+1);
			return;
		case 54:
			s.SP(player, Sound.PISTON_RETRACT, 1.0F, 1.0F);
			player.closeInventory();
			return;
		default:
			String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
			YamlManager NewBieYM = GUI_YC.getNewConfig("ETC/NewBie.yml");
			YamlManager QuestList=GUI_YC.getNewConfig("Quest/QuestList.yml");
			
			if(QuestList.contains(QuestName)==true)
			{
				if(QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false).toArray().length != 0)
				{
					s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
					NewBieYM.set("FirstQuest", QuestName);
					NewBieYM.saveConfig();
					NewBieGUIMain(player);
				}
				else
				{
					s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[���� ����Ʈ] : �ش� ����Ʈ�� ����Ʈ ������Ʈ�� �������� �ʽ��ϴ�!");
				}
			}
			else
			{
				s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage(ChatColor.RED+"[���� ����Ʈ] : �ش� ����Ʈ�� �������� �ʽ��ϴ�!");
			}
			
		}
	}

	public void InventoryClose_NewBie(InventoryCloseEvent event)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager NewBieYM = GUI_YC.getNewConfig("ETC/NewBie.yml");
		int num = 0;
		for(int count = 0; count < 45;count++)
		{
			if(event.getInventory().getItem(count) != null)
			{
				if(event.getInventory().getTitle().contains("���̵�"))
					NewBieYM.set("Guide."+num, event.getInventory().getItem(count));
				else
					NewBieYM.set("SupportItem."+num, event.getInventory().getItem(count));
				num = num+1;
			}
			else
				if(event.getInventory().getTitle().contains("���̵�"))
					NewBieYM.removeKey("Guide."+count);
				else
					NewBieYM.removeKey("SupportItem."+count);
		}
		NewBieYM.saveConfig();

		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();

		if(event.getInventory().getTitle().contains("���̵�"))
			event.getPlayer().sendMessage(ChatColor.GREEN + "[���� ���̵�] : ���������� ���� �Ǿ����ϴ�!");
		else
			event.getPlayer().sendMessage(ChatColor.GREEN + "[���� ������] : ���������� ���� �Ǿ����ϴ�!");
		s.SP((Player) event.getPlayer(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
	}
}
