package GBD_RPG.Admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import GBD_RPG.ServerTick.ServerTick_Main;
import GBD_RPG.User.ETC_GUI;
import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_GUI;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Navigation_GUI extends Util_GUI
{
	public void NavigationListGUI(Player player, short page)
	{
		YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager NavigationConfig =YC.getNewConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��1��1��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�׺� ��� : " + (page+1));

		Object[] Navi= NavigationConfig.getConfigurationSection("").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < Navi.length;count++)
		{
			if(count > Navi.length || loc >= 45) break;
			String NaviName = NavigationConfig.getString(Navi[count]+".Name");
			String world = NavigationConfig.getString(Navi[count]+".world");
			int x = NavigationConfig.getInt(Navi[count]+".x");
			int y = NavigationConfig.getInt(Navi[count]+".y");
			int z = NavigationConfig.getInt(Navi[count]+".z");
			int Time = NavigationConfig.getInt(Navi[count]+".time");
			int sensitive = NavigationConfig.getInt(Navi[count]+".sensitive");
			boolean Permition = NavigationConfig.getBoolean(Navi[count]+".onlyOPuse");
			int ShowArrow = NavigationConfig.getInt(Navi[count]+".ShowArrow");
			
			
			String TimeS = ChatColor.DARK_AQUA+"<������ �� ���� ����>";
			String PermitionS = ChatColor.DARK_AQUA+"<OP�� ��� ����>";
			String sensitiveS = ChatColor.BLUE+"<�ݰ� "+sensitive+"��� �̳��� �������� ����>";
			String ShowArrowS = ChatColor.DARK_AQUA+"<�⺻ ȭ��ǥ ���>";
			if(Permition == false)
				PermitionS = ChatColor.DARK_AQUA+"<��� ��� ����>";
			if(Time >= 0)
				TimeS = ChatColor.DARK_AQUA+"<"+Time+"�� ���� ����>";
			switch(ShowArrow)
			{
			default:
				ShowArrowS = ChatColor.DARK_AQUA+"<�⺻ ȭ��ǥ ���>";
				break;
			}
			Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + Navi[count].toString(), 395,0,1,Arrays.asList(
			ChatColor.YELLOW+""+ChatColor.BOLD+NaviName,"",
			ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,
			ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,sensitiveS,"",
			ChatColor.DARK_AQUA+"[��Ÿ �ɼ�]",TimeS,PermitionS,ShowArrowS,""
			,ChatColor.YELLOW+"[�� Ŭ���� �׺� ����]",ChatColor.RED+"[Shift + ��Ŭ���� �׺� ����]"), loc, inv);
			loc++;
		}
		
		if(Navi.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�� �׺�", 386,0,1,Arrays.asList(ChatColor.GRAY + "�� �׺� �����մϴ�."), 49, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void NavigationOptionGUI(Player player, String NaviUTC)
	{
		YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager NavigationConfig =YC.getNewConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��1��1��5��r";
		Inventory inv = Bukkit.createInventory(null, 36, UniqueCode + "��0�׺� ����");

		String NaviName = NavigationConfig.getString(NaviUTC+".Name");
		String world = NavigationConfig.getString(NaviUTC+".world");
		int x = NavigationConfig.getInt(NaviUTC+".x");
		short y = (short) NavigationConfig.getInt(NaviUTC+".y");
		int z = NavigationConfig.getInt(NaviUTC+".z");
		int Time = NavigationConfig.getInt(NaviUTC+".time");
		short sensitive = (short) NavigationConfig.getInt(NaviUTC+".sensitive");
		boolean Permition = NavigationConfig.getBoolean(NaviUTC+".onlyOPuse");
		byte ShowArrow = (byte) NavigationConfig.getInt(NaviUTC+".ShowArrow");

		String TimeS = ChatColor.BLUE+"[������ �� ���� ����]";
		String PermitionS = ChatColor.BLUE+"[OP�� ��� ����]";
		String sensitiveS = ChatColor.BLUE+"[�ݰ� "+sensitive+"��� �̳��� �������� ����]";
		String ShowArrowS = ChatColor.BLUE+"[�⺻ ȭ��ǥ ���]";
		if(Permition == false)
			PermitionS = ChatColor.BLUE+"[��� ��� ����]";
		if(Time >= 0)
			TimeS = ChatColor.BLUE+"["+Time+"�� ���� ����]";
		switch(ShowArrow)
		{
		default:
			ShowArrowS = ChatColor.BLUE+"[�⺻ ȭ��ǥ ���]";
			break;
		}
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�̸� ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� �̸��� �����մϴ�.","",ChatColor.BLUE+"[���� �̸�]",ChatColor.WHITE+NaviName,""), 10, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "��ǥ ����", 358,0,1,Arrays.asList(ChatColor.GRAY + "Ŭ���� ���� ��ġ�� �����մϴ�.","",ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,""), 11, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� �ð�", 347,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� ���� �ð��� �����մϴ�.","",TimeS,""), 12, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� �ݰ�", 420,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� ������ �����մϴ�.","",sensitiveS,""), 13, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "��� ����", 137,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� ��� ������ �����մϴ�.","",PermitionS,""), 14, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�׺� Ÿ��", 381,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� Ÿ���� �����մϴ�.","",ShowArrowS,""), 15, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 27, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+NaviUTC), 35, inv);
		player.openInventory(inv);
	}
	
	public void UseNavigationGUI(Player player, short page)
	{
		YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager NavigationConfig =YC.getNewConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��1��1��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�׺� ��� : " + (page+1));

		Object[] Navi= NavigationConfig.getConfigurationSection("").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < Navi.length;count++)
		{
			if(count > Navi.length || loc >= 45) break;
			boolean Permition = NavigationConfig.getBoolean(Navi[count]+".onlyOPuse");
			if(Permition)
			{
				if(player.isOp())
				{
					String NaviName = NavigationConfig.getString(Navi[count]+".Name");
					String world = NavigationConfig.getString(Navi[count]+".world");
					int x = NavigationConfig.getInt(Navi[count]+".x");
					short y = (short) NavigationConfig.getInt(Navi[count]+".y");
					int z = NavigationConfig.getInt(Navi[count]+".z");

					int Time = NavigationConfig.getInt(Navi[count]+".time");
					String TimeS = ChatColor.DARK_AQUA+"<������ �� ���� ����>";
					if(Time >= 0)
						TimeS = ChatColor.DARK_AQUA+"<"+Time+"�� ���� ����>";
					
					Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + Navi[count].toString(), 395,0,1,Arrays.asList(
					ChatColor.YELLOW+""+ChatColor.BOLD+NaviName,"",
					ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,
					ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,"",TimeS,"",ChatColor.YELLOW+"[�� Ŭ���� �׺� ���]"), loc, inv);
					loc++;
				}
			}
			else
			{
				String NaviName = NavigationConfig.getString(Navi[count]+".Name");
				String world = NavigationConfig.getString(Navi[count]+".world");
				int x = NavigationConfig.getInt(Navi[count]+".x");
				short y = (short) NavigationConfig.getInt(Navi[count]+".y");
				int z = NavigationConfig.getInt(Navi[count]+".z");

				int Time = NavigationConfig.getInt(Navi[count]+".time");
				String TimeS = ChatColor.DARK_AQUA+"<������ �� ���� ����>";
				if(Time >= 0)
					TimeS = ChatColor.DARK_AQUA+"<"+Time+"�� ���� ����>";
				
				Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + Navi[count].toString(), 395,0,1,Arrays.asList(
				ChatColor.YELLOW+""+ChatColor.BOLD+NaviName,"",
				ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,
				ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,"",TimeS,"",ChatColor.YELLOW+"[�� Ŭ���� �׺� ���]"), loc, inv);
				loc++;
			}
		}
		
		if(Navi.length-(page*44)>45)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void NavigationListGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		int slot = event.getSlot();

		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player, (byte) 1);
			else if(slot == 48)//���� ������
				NavigationListGUI(player, (short) (page-1));
			else if(slot == 49)//�� �׺�
			{
				player.closeInventory();
				player.sendMessage(ChatColor.GREEN+"[�׺�] : ���ο� �׺���̼� �̸��� �Է� �� �ּ���!");
				UserData_Object u = new UserData_Object();
				u.setType(player, "Navi");
				u.setString(player, (byte)0, "NN");
			}
			else if(slot == 50)//���� ������
				NavigationListGUI(player, (short) (page+1));
			else
			{
				if(event.isLeftClick() == true)
				{
					s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					NavigationOptionGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
				else if(event.isShiftClick() == true && event.isRightClick() == true)
				{
					s.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager NavigationConfig =YC.getNewConfig("Navigation/NavigationList.yml");
					NavigationConfig.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					NavigationConfig.saveConfig();
					NavigationListGUI(player, page);
				}
			}
		}
	}
	
	public void NavigationOptionGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 35)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 27)//���� ���
				NavigationListGUI(player, (short) 0);
			else
			{
				YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager NavigationConfig =YC.getNewConfig("Navigation/NavigationList.yml");
				String UTC = ChatColor.stripColor(event.getInventory().getItem(35).getItemMeta().getLore().get(1));

				if(slot == 11)//��ǥ ����
				{
					NavigationConfig.set(UTC+".world", player.getLocation().getWorld().getName());
					NavigationConfig.set(UTC+".x", (int)player.getLocation().getX());
					NavigationConfig.set(UTC+".y", (int)player.getLocation().getY());
					NavigationConfig.set(UTC+".z", (int)player.getLocation().getZ());
					NavigationConfig.saveConfig();
					NavigationOptionGUI(player, UTC);
				}
				else if(slot == 14)//��� ����
				{
					if(NavigationConfig.getBoolean(UTC+".onlyOPuse"))
						NavigationConfig.set(UTC+".onlyOPuse", false);
					else
						NavigationConfig.set(UTC+".onlyOPuse", true);
					NavigationConfig.saveConfig();
					NavigationOptionGUI(player, UTC);
				}
				else
				{
					player.closeInventory();
					UserData_Object u = new UserData_Object();
					u.setType(player, "Navi");
					u.setString(player, (byte)1, UTC);
					if(slot == 10)//�̸� ����
					{
						player.sendMessage(ChatColor.GREEN+"[�׺�] : ���ϴ� �׺���̼� �̸��� �Է� �� �ּ���!");
						u.setString(player, (byte)0, "CNN");
					}
					else if(slot == 12)//���� �ð�
					{
						player.sendMessage(ChatColor.GREEN+"[�׺�] : �׺���̼� ���� �ð��� �Է� �� �ּ���!");
						player.sendMessage(ChatColor.YELLOW+"(-1��(ã�� �� ����) ~ 3600��(1�ð�))");
						u.setString(player, (byte)0, "CNT");
					}
					else if(slot == 13)//���� �ݰ�
					{
						player.sendMessage(ChatColor.GREEN+"[�׺�] : ���� ���� ������ �Է� �� �ּ���!");
						player.sendMessage(ChatColor.YELLOW+"(1 ~ 1000)");
						u.setString(player, (byte)0, "CNS");
					}
					else if(slot == 15)//�׺� Ÿ��
					{
						player.sendMessage(ChatColor.GREEN+"[�׺�] : �׺���̼� ȭ��ǥ ����� �����ϼ���!");
						player.sendMessage(ChatColor.YELLOW+"(0 ~ 10)");
						u.setString(player, (byte)0, "CNA");
					}
				}
			}
		}
	}

	public void UseNavigationGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		int slot = event.getSlot();
		
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				new ETC_GUI().ETCGUI_Main(player);
			else if(slot == 48)//���� ������
				UseNavigationGUI(player, (short) (page-1));
			else if(slot == 50)//���� ������
				UseNavigationGUI(player, (short) (page+1));
			else if(event.isLeftClick() == true)
			{
				for(int count = 0; count < ServerTick_Main.NaviUsingList.size();count++)
				{
					if(ServerTick_Main.NaviUsingList.get(count).equals(player.getName()))
					{
						s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage(ChatColor.RED+"[�׺���̼�] : ����� �̹� �׺���̼��� ��� ���Դϴ�!");
						return;
					}
				}
				ServerTick_Main.NaviUsingList.add(player.getName());
				YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager NavigationConfig =YC.getNewConfig("Navigation/NavigationList.yml");
				String UTC = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				player.closeInventory();
				s.SP(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
				
				GBD_RPG.ServerTick.ServerTick_Object STSO = new GBD_RPG.ServerTick.ServerTick_Object(GBD_RPG.ServerTick.ServerTick_Main.nowUTC, "NV");
				STSO.setCount(0);//Ƚ �� �ʱ�ȭ
				STSO.setMaxCount(NavigationConfig.getInt(UTC+".time"));//N�ʰ� �׺���̼�
				//-1�� ������, N�ʰ��� �ƴ�, ã�� �� �� ���� �׺���̼� ����
				STSO.setString((byte)1, NavigationConfig.getString(UTC+".world"));//������ ���� �̸� ����
				STSO.setString((byte)2, player.getName());//�÷��̾� �̸� ����
				
				STSO.setInt((byte)0, NavigationConfig.getInt(UTC+".x"));//������X ��ġ����
				STSO.setInt((byte)1, NavigationConfig.getInt(UTC+".y"));//������Y ��ġ����
				STSO.setInt((byte)2, NavigationConfig.getInt(UTC+".z"));//������Z ��ġ����
				STSO.setInt((byte)3, NavigationConfig.getInt(UTC+".sensitive"));//���� ���� ����
				STSO.setInt((byte)4, NavigationConfig.getInt(UTC+".ShowArrow"));//��ƼŬ ����
				
				GBD_RPG.ServerTick.ServerTick_Main.Schedule.put(GBD_RPG.ServerTick.ServerTick_Main.nowUTC, STSO);
				player.sendMessage(ChatColor.YELLOW+"[�׺���̼�] : ��ã�� �ý����� �����˴ϴ�!");
				player.sendMessage(ChatColor.YELLOW+"(ȭ��ǥ�� ������ ���� ���, [ESC] �� [����] �� [���� ����] ���� [����]�� [���]�� ������ �ּ���!)");
			}
		}
	}
}
