package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import servertick.ServerTick_Main;
import user.ETC_GUI;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;



public class Navigation_GUI extends Util_GUI
{
	public void NavigationListGUI(Player player, short page)
	{
		YamlLoader navigationYaml = new YamlLoader();
		navigationYaml.getConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��1��1��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�׺� ��� : " + (page+1));

		String[] Navi= navigationYaml.getConfigurationSection("").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		String NaviName = null;
		String world = null;
		int x = 0;
		int y = 0;
		int z = 0;
		int Time = 0;
		int sensitive = 0;
		boolean Permition = false;
		int ShowArrow = 0;
		
		String TimeS = null;
		String PermitionS = null;
		String sensitiveS = ChatColor.BLUE+"<�ݰ� "+sensitive+"��� �̳��� �������� ����>";
		String ShowArrowS = null;
		for(int count = page*45; count < Navi.length;count++)
		{
			if(count > Navi.length || loc >= 45) break;
			NaviName = navigationYaml.getString(Navi[count]+".Name");
			world = navigationYaml.getString(Navi[count]+".world");
			x = navigationYaml.getInt(Navi[count]+".x");
			y = navigationYaml.getInt(Navi[count]+".y");
			z = navigationYaml.getInt(Navi[count]+".z");
			Time = navigationYaml.getInt(Navi[count]+".time");
			sensitive = navigationYaml.getInt(Navi[count]+".sensitive");
			Permition = navigationYaml.getBoolean(Navi[count]+".onlyOPuse");
			ShowArrow = navigationYaml.getInt(Navi[count]+".ShowArrow");
			
			if(Permition == false)
				PermitionS = ChatColor.DARK_AQUA+"<��� ��� ����>";
			else
				PermitionS = ChatColor.DARK_AQUA+"<OP�� ��� ����>";
			if(Time >= 0)
				TimeS = ChatColor.DARK_AQUA+"<"+Time+"�� ���� ����>";
			else
				TimeS = ChatColor.DARK_AQUA+"<������ �� ���� ����>";
			switch(ShowArrow)
			{
			default:
				ShowArrowS = ChatColor.DARK_AQUA+"<�⺻ ȭ��ǥ ���>";
				break;
			}
			Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + Navi[count], 395,0,1,Arrays.asList(
			ChatColor.YELLOW+""+ChatColor.BOLD+NaviName,"",
			ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,
			ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,sensitiveS,"",
			ChatColor.DARK_AQUA+"[��Ÿ �ɼ�]",TimeS,PermitionS,ShowArrowS,""
			,ChatColor.YELLOW+"[�� Ŭ���� �׺� ����]",ChatColor.RED+"[Shift + ��Ŭ���� �׺� ����]"), loc, inv);
			loc++;
		}
		
		if(Navi.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		Stack2("��f��l�� �׺�", 386,0,1,Arrays.asList(ChatColor.GRAY + "�� �׺� �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void NavigationOptionGUI(Player player, String NaviUTC)
	{
		YamlLoader navigationYaml = new YamlLoader();
		navigationYaml.getConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��1��1��5��r";
		Inventory inv = Bukkit.createInventory(null, 36, UniqueCode + "��0�׺� ����");

		String NaviName = navigationYaml.getString(NaviUTC+".Name");
		String world = navigationYaml.getString(NaviUTC+".world");
		int x = navigationYaml.getInt(NaviUTC+".x");
		short y = (short) navigationYaml.getInt(NaviUTC+".y");
		int z = navigationYaml.getInt(NaviUTC+".z");
		int Time = navigationYaml.getInt(NaviUTC+".time");
		short sensitive = (short) navigationYaml.getInt(NaviUTC+".sensitive");
		boolean Permition = navigationYaml.getBoolean(NaviUTC+".onlyOPuse");
		byte ShowArrow = (byte) navigationYaml.getInt(NaviUTC+".ShowArrow");

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
		
		Stack2("��f��l�̸� ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� �̸��� �����մϴ�.","",ChatColor.BLUE+"[���� �̸�]",ChatColor.WHITE+NaviName,""), 10, inv);
		Stack2("��f��l��ǥ ����", 358,0,1,Arrays.asList(ChatColor.GRAY + "Ŭ���� ���� ��ġ�� �����մϴ�.","",ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,""), 11, inv);
		Stack2("��f��l���� �ð�", 347,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� ���� �ð��� �����մϴ�.","",TimeS,""), 12, inv);
		Stack2("��f��l���� �ݰ�", 420,0,1,Arrays.asList(ChatColor.GRAY + "���� ���� ������ �����մϴ�.","",sensitiveS,""), 13, inv);
		Stack2("��f��l��� ����", 137,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� ��� ������ �����մϴ�.","",PermitionS,""), 14, inv);
		Stack2("��f��l�׺� Ÿ��", 381,0,1,Arrays.asList(ChatColor.GRAY + "�׺���̼� Ÿ���� �����մϴ�.","",ShowArrowS,""), 15, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 27, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+NaviUTC), 35, inv);
		player.openInventory(inv);
	}
	
	public void UseNavigationGUI(Player player, short page)
	{
		YamlLoader navigationYaml = new YamlLoader();
		navigationYaml.getConfig("Navigation/NavigationList.yml");

		String UniqueCode = "��0��0��1��1��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0�׺� ��� : " + (page+1));

		String[] Navi= navigationYaml.getConfigurationSection("").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		String NaviName = null;
		String world = null;
		String TimeS = null;
		for(int count = page*45; count < Navi.length;count++)
		{
			if(count > Navi.length || loc >= 45) break;
			boolean Permition = navigationYaml.getBoolean(Navi[count]+".onlyOPuse");
			if(Permition)
			{
				if(player.isOp())
				{
					NaviName = navigationYaml.getString(Navi[count]+".Name");
					world = navigationYaml.getString(Navi[count]+".world");
					int x = navigationYaml.getInt(Navi[count]+".x");
					short y = (short) navigationYaml.getInt(Navi[count]+".y");
					int z = navigationYaml.getInt(Navi[count]+".z");

					int Time = navigationYaml.getInt(Navi[count]+".time");
					if(Time >= 0)
						TimeS = ChatColor.DARK_AQUA+"<"+Time+"�� ���� ����>";
					else
						TimeS = ChatColor.DARK_AQUA+"<������ �� ���� ����>";
					
					Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + Navi[count], 395,0,1,Arrays.asList(
					ChatColor.YELLOW+""+ChatColor.BOLD+NaviName,"",
					ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,
					ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,"",TimeS,"",ChatColor.YELLOW+"[�� Ŭ���� �׺� ���]"), loc, inv);
					loc++;
				}
			}
			else
			{
				NaviName = navigationYaml.getString(Navi[count]+".Name");
				world = navigationYaml.getString(Navi[count]+".world");
				int x = navigationYaml.getInt(Navi[count]+".x");
				short y = (short) navigationYaml.getInt(Navi[count]+".y");
				int z = navigationYaml.getInt(Navi[count]+".z");

				int Time = navigationYaml.getInt(Navi[count]+".time");
				if(Time >= 0)
					TimeS = ChatColor.DARK_AQUA+"<"+Time+"�� ���� ����>";
				else
					TimeS = ChatColor.DARK_AQUA+"<������ �� ���� ����>";
				
				Stack2(ChatColor.BLACK + "" + ChatColor.BOLD + Navi[count], 395,0,1,Arrays.asList(
				ChatColor.YELLOW+""+ChatColor.BOLD+NaviName,"",
				ChatColor.BLUE+"[���� ����]",ChatColor.BLUE+"���� : "+ChatColor.WHITE+world,
				ChatColor.BLUE+"��ǥ : " + ChatColor.WHITE+x+","+y+","+z,"",TimeS,"",ChatColor.YELLOW+"[�� Ŭ���� �׺� ���]"), loc, inv);
				loc++;
			}
		}
		
		if(Navi.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void NavigationListGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		int slot = event.getSlot();

		
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					NavigationOptionGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
				else if(event.isShiftClick() == true && event.isRightClick() == true)
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					YamlLoader navigationYaml = new YamlLoader();
					navigationYaml.getConfig("Navigation/NavigationList.yml");
					navigationYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					navigationYaml.saveConfig();
					NavigationListGUI(player, page);
				}
			}
		}
	}
	
	public void NavigationOptionGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		
		
		if(slot == 35)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 27)//���� ���
				NavigationListGUI(player, (short) 0);
			else
			{
				YamlLoader navigationYaml = new YamlLoader();
				navigationYaml.getConfig("Navigation/NavigationList.yml");
				String UTC = ChatColor.stripColor(event.getInventory().getItem(35).getItemMeta().getLore().get(1));

				if(slot == 11)//��ǥ ����
				{
					navigationYaml.set(UTC+".world", player.getLocation().getWorld().getName());
					navigationYaml.set(UTC+".x", (int)player.getLocation().getX());
					navigationYaml.set(UTC+".y", (int)player.getLocation().getY());
					navigationYaml.set(UTC+".z", (int)player.getLocation().getZ());
					navigationYaml.saveConfig();
					NavigationOptionGUI(player, UTC);
				}
				else if(slot == 14)//��� ����
				{
					if(navigationYaml.getBoolean(UTC+".onlyOPuse"))
						navigationYaml.set(UTC+".onlyOPuse", false);
					else
						navigationYaml.set(UTC+".onlyOPuse", true);
					navigationYaml.saveConfig();
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
		
		
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
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
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage(ChatColor.RED+"[�׺���̼�] : ����� �̹� �׺���̼��� ��� ���Դϴ�!");
						return;
					}
				}
				ServerTick_Main.NaviUsingList.add(player.getName());
				YamlLoader navigationYaml = new YamlLoader();
				navigationYaml.getConfig("Navigation/NavigationList.yml");
				String UTC = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				player.closeInventory();
				SoundEffect.SP(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
				
				servertick.ServerTick_Object STSO = new servertick.ServerTick_Object(servertick.ServerTick_Main.nowUTC, "NV");
				STSO.setCount(0);//Ƚ �� �ʱ�ȭ
				STSO.setMaxCount(navigationYaml.getInt(UTC+".time"));//N�ʰ� �׺���̼�
				//-1�� ������, N�ʰ��� �ƴ�, ã�� �� �� ���� �׺���̼� ����
				STSO.setString((byte)1, navigationYaml.getString(UTC+".world"));//������ ���� �̸� ����
				STSO.setString((byte)2, player.getName());//�÷��̾� �̸� ����
				
				STSO.setInt((byte)0, navigationYaml.getInt(UTC+".x"));//������X ��ġ����
				STSO.setInt((byte)1, navigationYaml.getInt(UTC+".y"));//������Y ��ġ����
				STSO.setInt((byte)2, navigationYaml.getInt(UTC+".z"));//������Z ��ġ����
				STSO.setInt((byte)3, navigationYaml.getInt(UTC+".sensitive"));//���� ���� ����
				STSO.setInt((byte)4, navigationYaml.getInt(UTC+".ShowArrow"));//��ƼŬ ����
				
				servertick.ServerTick_Main.Schedule.put(servertick.ServerTick_Main.nowUTC, STSO);
				player.sendMessage(ChatColor.YELLOW+"[�׺���̼�] : ��ã�� �ý����� �����˴ϴ�!");
				player.sendMessage(ChatColor.YELLOW+"(ȭ��ǥ�� ������ ���� ���, [ESC] �� [����] �� [���� ����] ���� [����]�� [���]�� ������ �ּ���!)");
			}
		}
	}
}
