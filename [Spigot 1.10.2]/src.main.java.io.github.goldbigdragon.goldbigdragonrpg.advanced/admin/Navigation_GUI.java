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
	public void navigationListGui(Player player, short page)
	{
		YamlLoader navigationYaml = new YamlLoader();
		navigationYaml.getConfig("Navigation/NavigationList.yml");

		String uniqueCode = "��0��0��1��1��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�׺� ��� : " + (page+1));

		String[] navi= navigationYaml.getConfigurationSection("").getKeys(false).toArray(new String[0]);
		
		byte loc=0;
		String naviName = null;
		String world = null;
		int x = 0;
		int y = 0;
		int z = 0;
		int time = 0;
		int sensitive = 0;
		boolean permition = false;
		int showArrow = 0;
		
		String timeS = null;
		String permitionS = null;
		String sensitiveS = "��9<�ݰ� "+sensitive+"��� �̳��� �������� ����>";
		String showArrowS = null;
		for(int count = page*45; count < navi.length;count++)
		{
			if(count > navi.length || loc >= 45) break;
			naviName = navigationYaml.getString(navi[count]+".Name");
			world = navigationYaml.getString(navi[count]+".world");
			x = navigationYaml.getInt(navi[count]+".x");
			y = navigationYaml.getInt(navi[count]+".y");
			z = navigationYaml.getInt(navi[count]+".z");
			time = navigationYaml.getInt(navi[count]+".time");
			sensitive = navigationYaml.getInt(navi[count]+".sensitive");
			permition = navigationYaml.getBoolean(navi[count]+".onlyOPuse");
			showArrow = navigationYaml.getInt(navi[count]+".ShowArrow");
			
			if(permition == false)
				permitionS = "��3<��� ��� ����>";
			else
				permitionS = "��3<OP�� ��� ����>";
			if(time >= 0)
				timeS = "��3<"+time+"�� ���� ����>";
			else
				timeS = "��3<������ �� ���� ����>";
			switch(showArrow)
			{
			default:
				showArrowS = "��3<�⺻ ȭ��ǥ ���>";
				break;
			}
			Stack2("��0��l" + navi[count], 395,0,1,Arrays.asList(
			"��e��l"+naviName,"",
			"��9[���� ����]","��9���� : ��f"+world,
			"��9��ǥ : ��f"+x+","+y+","+z,sensitiveS,"",
			"��3[��Ÿ �ɼ�]",timeS,permitionS,showArrowS,""
			,"��e[�� Ŭ���� �׺� ����]","��c[Shift + ��Ŭ���� �׺� ����]"), loc, inv);
			loc++;
		}
		
		if(navi.length-(page*44)>45)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		Stack2("��f��l�� �׺�", 386,0,1,Arrays.asList("��7�� �׺� �����մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}

	public void navigationOptionGui(Player player, String naviUTC)
	{
		YamlLoader navigationYaml = new YamlLoader();
		navigationYaml.getConfig("Navigation/NavigationList.yml");

		String uniqueCode = "��0��0��1��1��5��r";
		Inventory inv = Bukkit.createInventory(null, 36, uniqueCode + "��0�׺� ����");

		String naviName = navigationYaml.getString(naviUTC+".Name");
		String world = navigationYaml.getString(naviUTC+".world");
		int x = navigationYaml.getInt(naviUTC+".x");
		short y = (short) navigationYaml.getInt(naviUTC+".y");
		int z = navigationYaml.getInt(naviUTC+".z");
		int time = navigationYaml.getInt(naviUTC+".time");
		short sensitive = (short) navigationYaml.getInt(naviUTC+".sensitive");
		boolean permition = navigationYaml.getBoolean(naviUTC+".onlyOPuse");
		byte showArrow = (byte) navigationYaml.getInt(naviUTC+".ShowArrow");

		String timeS = "��9[������ �� ���� ����]";
		String permitionS = "��9[OP�� ��� ����]";
		String sensitiveS = "��9[�ݰ� "+sensitive+"��� �̳��� �������� ����]";
		String showArrowS = "��9[�⺻ ȭ��ǥ ���]";
		if(permition == false)
			permitionS = "��9[��� ��� ����]";
		if(time >= 0)
			timeS = "��9["+time+"�� ���� ����]";
		switch(showArrow)
		{
		default:
			showArrowS = "��9[�⺻ ȭ��ǥ ���]";
			break;
		}
		
		Stack2("��f��l�̸� ����", 386,0,1,Arrays.asList("��7�׺���̼� �̸��� �����մϴ�.","","��9[���� �̸�]","��f"+naviName,""), 10, inv);
		Stack2("��f��l��ǥ ����", 358,0,1,Arrays.asList("��7Ŭ���� ���� ��ġ�� �����մϴ�.","","��9[���� ����]","��9���� : ��f"+world,"��9��ǥ : ��f"+x+","+y+","+z,""), 11, inv);
		Stack2("��f��l���� �ð�", 347,0,1,Arrays.asList("��7�׺���̼� ���� �ð��� �����մϴ�.","",timeS,""), 12, inv);
		Stack2("��f��l���� �ݰ�", 420,0,1,Arrays.asList("��7���� ���� ������ �����մϴ�.","",sensitiveS,""), 13, inv);
		Stack2("��f��l��� ����", 137,0,1,Arrays.asList("��7�׺���̼� ��� ������ �����մϴ�.","",permitionS,""), 14, inv);
		Stack2("��f��l�׺� Ÿ��", 381,0,1,Arrays.asList("��7�׺���̼� Ÿ���� �����մϴ�.","",showArrowS,""), 15, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 27, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+naviUTC), 35, inv);
		player.openInventory(inv);
	}
	
	public void useNavigationGui(Player player, short page)
	{
		YamlLoader navigationYaml = new YamlLoader();
		navigationYaml.getConfig("Navigation/NavigationList.yml");

		String uniqueCode = "��0��0��1��1��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0�׺� ��� : " + (page+1));

		String[] navi= navigationYaml.getConfigurationSection("").getKeys(false).toArray(new String[0]);
		
		int loc=0;
		String naviName = null;
		String world = null;
		String timeS = null;
		for(int count = page*45; count < navi.length;count++)
		{
			if(count > navi.length || loc >= 45) break;
			boolean permition = navigationYaml.getBoolean(navi[count]+".onlyOPuse");
			if(permition)
			{
				if(player.isOp())
				{
					naviName = navigationYaml.getString(navi[count]+".Name");
					world = navigationYaml.getString(navi[count]+".world");
					int x = navigationYaml.getInt(navi[count]+".x");
					short y = (short) navigationYaml.getInt(navi[count]+".y");
					int z = navigationYaml.getInt(navi[count]+".z");

					int time = navigationYaml.getInt(navi[count]+".time");
					if(time >= 0)
						timeS = "��3<"+time+"�� ���� ����>";
					else
						timeS = "��3<������ �� ���� ����>";
					
					Stack2("��0��l" + navi[count], 395,0,1,Arrays.asList(
					"��e��l"+naviName,"",
					"��9[���� ����]","��9���� : ��f"+world,
					"��9��ǥ : ��f"+x+","+y+","+z,"",timeS,"","��e[�� Ŭ���� �׺� ���]"), loc, inv);
					loc++;
				}
			}
			else
			{
				naviName = navigationYaml.getString(navi[count]+".Name");
				world = navigationYaml.getString(navi[count]+".world");
				int x = navigationYaml.getInt(navi[count]+".x");
				short y = (short) navigationYaml.getInt(navi[count]+".y");
				int z = navigationYaml.getInt(navi[count]+".z");

				int time = navigationYaml.getInt(navi[count]+".time");
				if(time >= 0)
					timeS = "��3<"+time+"�� ���� ����>";
				else
					timeS = "��3<������ �� ���� ����>";
				
				Stack2("��0��l" + navi[count], 395,0,1,Arrays.asList(
				"��e��l"+naviName,"",
				"��9[���� ����]","��9���� : ��f"+world,
				"��9��ǥ : ��f"+x+","+y+","+z,"",timeS,"","��e[�� Ŭ���� �׺� ���]"), loc, inv);
				loc++;
			}
		}
		
		if(navi.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void navigationListGuiClick(InventoryClickEvent event)
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
				new OPbox_GUI().opBoxGuiMain(player, (byte) 1);
			else if(slot == 48)//���� ������
				navigationListGui(player, (short) (page-1));
			else if(slot == 49)//�� �׺�
			{
				player.closeInventory();
				player.sendMessage("��a[�׺�] : ���ο� �׺���̼� �̸��� �Է� �� �ּ���!");
				UserData_Object u = new UserData_Object();
				u.setType(player, "Navi");
				u.setString(player, (byte)0, "NN");
			}
			else if(slot == 50)//���� ������
				navigationListGui(player, (short) (page+1));
			else
			{
				if(event.isLeftClick())
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					navigationOptionGui(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
				else if(event.isShiftClick() && event.isRightClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
					YamlLoader navigationYaml = new YamlLoader();
					navigationYaml.getConfig("Navigation/NavigationList.yml");
					navigationYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					navigationYaml.saveConfig();
					navigationListGui(player, page);
				}
			}
		}
	}
	
	public void navigationOptionGuiClick(InventoryClickEvent event)
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
				navigationListGui(player, (short) 0);
			else
			{
				YamlLoader navigationYaml = new YamlLoader();
				navigationYaml.getConfig("Navigation/NavigationList.yml");
				String utc = ChatColor.stripColor(event.getInventory().getItem(35).getItemMeta().getLore().get(1));

				if(slot == 11)//��ǥ ����
				{
					navigationYaml.set(utc+".world", player.getLocation().getWorld().getName());
					navigationYaml.set(utc+".x", (int)player.getLocation().getX());
					navigationYaml.set(utc+".y", (int)player.getLocation().getY());
					navigationYaml.set(utc+".z", (int)player.getLocation().getZ());
					navigationYaml.saveConfig();
					navigationOptionGui(player, utc);
				}
				else if(slot == 14)//��� ����
				{
					if(navigationYaml.getBoolean(utc+".onlyOPuse"))
						navigationYaml.set(utc+".onlyOPuse", false);
					else
						navigationYaml.set(utc+".onlyOPuse", true);
					navigationYaml.saveConfig();
					navigationOptionGui(player, utc);
				}
				else
				{
					player.closeInventory();
					UserData_Object u = new UserData_Object();
					u.setType(player, "Navi");
					u.setString(player, (byte)1, utc);
					if(slot == 10)//�̸� ����
					{
						player.sendMessage("��a[�׺�] : ���ϴ� �׺���̼� �̸��� �Է� �� �ּ���!");
						u.setString(player, (byte)0, "CNN");
					}
					else if(slot == 12)//���� �ð�
					{
						player.sendMessage("��a[�׺�] : �׺���̼� ���� �ð��� �Է� �� �ּ���!");
						player.sendMessage("��e(-1��(ã�� �� ����) ~ 3600��(1�ð�))");
						u.setString(player, (byte)0, "CNT");
					}
					else if(slot == 13)//���� �ݰ�
					{
						player.sendMessage("��a[�׺�] : ���� ���� ������ �Է� �� �ּ���!");
						player.sendMessage("��e(1 ~ 1000)");
						u.setString(player, (byte)0, "CNS");
					}
					else if(slot == 15)//�׺� Ÿ��
					{
						player.sendMessage("��a[�׺�] : �׺���̼� ȭ��ǥ ����� �����ϼ���!");
						player.sendMessage("��e(0 ~ 10)");
						u.setString(player, (byte)0, "CNA");
					}
				}
			}
		}
	}

	public void useNavigationGuiClick(InventoryClickEvent event)
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
				useNavigationGui(player, (short) (page-1));
			else if(slot == 50)//���� ������
				useNavigationGui(player, (short) (page+1));
			else if(event.isLeftClick())
			{
				for(int count = 0; count < ServerTick_Main.NaviUsingList.size();count++)
				{
					if(ServerTick_Main.NaviUsingList.get(count).equals(player.getName()))
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[�׺���̼�] : ����� �̹� �׺���̼��� ��� ���Դϴ�!");
						return;
					}
				}
				ServerTick_Main.NaviUsingList.add(player.getName());
				YamlLoader navigationYaml = new YamlLoader();
				navigationYaml.getConfig("Navigation/NavigationList.yml");
				String utc = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				player.closeInventory();
				SoundEffect.SP(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
				
				servertick.ServerTick_Object tickObject = new servertick.ServerTick_Object(servertick.ServerTick_Main.nowUTC, "NV");
				tickObject.setCount(0);//Ƚ �� �ʱ�ȭ
				tickObject.setMaxCount(navigationYaml.getInt(utc+".time"));//N�ʰ� �׺���̼�
				//-1�� ������, N�ʰ��� �ƴ�, ã�� �� �� ���� �׺���̼� ����
				tickObject.setString((byte)1, navigationYaml.getString(utc+".world"));//������ ���� �̸� ����
				tickObject.setString((byte)2, player.getName());//�÷��̾� �̸� ����
				
				tickObject.setInt((byte)0, navigationYaml.getInt(utc+".x"));//������X ��ġ����
				tickObject.setInt((byte)1, navigationYaml.getInt(utc+".y"));//������Y ��ġ����
				tickObject.setInt((byte)2, navigationYaml.getInt(utc+".z"));//������Z ��ġ����
				tickObject.setInt((byte)3, navigationYaml.getInt(utc+".sensitive"));//���� ���� ����
				tickObject.setInt((byte)4, navigationYaml.getInt(utc+".ShowArrow"));//��ƼŬ ����
				
				servertick.ServerTick_Main.Schedule.put(servertick.ServerTick_Main.nowUTC, tickObject);
				player.sendMessage("��e[�׺���̼�] : ��ã�� �ý����� �����˴ϴ�!");
				player.sendMessage("��e(ȭ��ǥ�� ������ ���� ���, [ESC] �� [����] �� [���� ����] ���� [����]�� [���]�� ������ �ּ���!)");
			}
		}
	}
}
