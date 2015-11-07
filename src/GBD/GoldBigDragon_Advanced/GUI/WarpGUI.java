package GBD.GoldBigDragon_Advanced.GUI;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import GBD.GoldBigDragon_Advanced.Main.Main;
import GBD.GoldBigDragon_Advanced.Util.YamlController;
import GBD.GoldBigDragon_Advanced.Util.YamlManager;

public class WarpGUI extends GUIutil
{
	public void WarpListGUI(Player player, int page)
	{
		YamlController GUI_YC = GBD.GoldBigDragon_Advanced.Main.Main.GUI_YC;
		YamlManager TelePort  = GUI_YC.getNewConfig("Teleport/TeleportList.yml");
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.BLACK + "���� ��� : " + (page+1));

		Object[] TelePortList= TelePort.getKeys().toArray();

		int loc=0;
		String worldname[]= new String[Bukkit.getServer().getWorlds().size()];
		for(int count=0;count<Bukkit.getServer().getWorlds().size();count++)
			worldname[count] = Bukkit.getServer().getWorlds().get(count).getName();
		int a = 0;
		for(int count = page*45; count < TelePortList.length+Bukkit.getServer().getWorlds().size();count++)
		{
			if(loc >= 45) break;
			if(count < TelePortList.length)
			{
				String TelePortTitle =TelePortList[count].toString();
				String world = TelePort.getString(TelePortTitle+".World");
				int x = TelePort.getInt(TelePortTitle+".X");
				int y = TelePort.getInt(TelePortTitle+".Y");
				int z = TelePort.getInt(TelePortTitle+".Z");
				int pitch = TelePort.getInt(TelePortTitle+".Pitch");
				int yaw = TelePort.getInt(TelePortTitle+".Yaw");
				boolean OnlyOpUse = TelePort.getBoolean(TelePortTitle+".OnlyOpUse");

				if(player.isOp() == true)
				{
					if(OnlyOpUse == true)
						Stack(ChatColor.WHITE+TelePortTitle, 345, 0, 1,Arrays.asList(ChatColor.DARK_AQUA+"���� : "+ChatColor.WHITE+""+ChatColor.BOLD+world,
							ChatColor.DARK_AQUA+"x ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+x
							,ChatColor.DARK_AQUA+"y ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+y
							,ChatColor.DARK_AQUA+"z ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+z
							,ChatColor.DARK_GRAY+"�ü� : "+ChatColor.GRAY+""+ChatColor.BOLD+pitch
							,ChatColor.DARK_GRAY+"���� : "+ChatColor.GRAY+""+ChatColor.BOLD+yaw
							,""
							,ChatColor.BLUE+"[���� OP�� ��ɾ�� �̵� �����մϴ�.]","",ChatColor.YELLOW+"[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �����մϴ�.]",ChatColor.RED+"[Shift + �� Ŭ���� �ش� ������ �����մϴ�.]"), loc, inv);
					else
						Stack(ChatColor.WHITE+TelePortTitle, 345, 0, 1,Arrays.asList(ChatColor.DARK_AQUA+"���� : "+ChatColor.WHITE+""+ChatColor.BOLD+world,
							ChatColor.DARK_AQUA+"x ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+x
							,ChatColor.DARK_AQUA+"y ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+y
							,ChatColor.DARK_AQUA+"z ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+z
							,ChatColor.DARK_GRAY+"�ü� : "+ChatColor.GRAY+""+ChatColor.BOLD+pitch
							,ChatColor.DARK_GRAY+"���� : "+ChatColor.GRAY+""+ChatColor.BOLD+yaw
							,""
							,ChatColor.GREEN+"[�Ϲ� ������ ��ɾ�� �̵� �����մϴ�.]","",ChatColor.YELLOW+"[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]",ChatColor.YELLOW+"[Shift + �� Ŭ���� ������ �����մϴ�.]",ChatColor.RED+"[Shift + �� Ŭ���� �ش� ������ �����մϴ�.]"), loc, inv);
					loc=loc+1;
				}
				else
				{
					if(OnlyOpUse == false)
						Stack(ChatColor.WHITE+TelePortTitle, 345, 0, 1,Arrays.asList(ChatColor.DARK_AQUA+"���� : "+ChatColor.WHITE+""+ChatColor.BOLD+world,
							ChatColor.DARK_AQUA+"x ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+x
							,ChatColor.DARK_AQUA+"y ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+y
							,ChatColor.DARK_AQUA+"z ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+z
							,ChatColor.DARK_GRAY+"�ü� : "+ChatColor.GRAY+""+ChatColor.BOLD+pitch
							,ChatColor.DARK_GRAY+"���� : "+ChatColor.GRAY+""+ChatColor.BOLD+yaw
							,"",ChatColor.YELLOW+"[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]"), loc, inv);
					loc=loc+1;
				}
			}
			else
			{
				if(player.isOp() == true)
				{
					String world = worldname[a];
					int x = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getX();
					int y = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getY();
					int z = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getZ();
					int pitch = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getPitch();
					int yaw = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getYaw();
					Stack(ChatColor.WHITE+world, 2, 0, 1,Arrays.asList(ChatColor.DARK_AQUA+"���� : "+ChatColor.WHITE+""+ChatColor.BOLD+world,
							ChatColor.DARK_AQUA+"x ���� ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+x
							,ChatColor.DARK_AQUA+"y ���� ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+y
							,ChatColor.DARK_AQUA+"z ���� ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+z
							,ChatColor.DARK_GRAY+"�ü� : "+ChatColor.GRAY+""+ChatColor.BOLD+pitch
							,ChatColor.DARK_GRAY+"���� : "+ChatColor.GRAY+""+ChatColor.BOLD+yaw
							,""
							,ChatColor.BLUE+"[���� OP�� ��ɾ�� �̵� �����մϴ�.]","",ChatColor.YELLOW+"[�� Ŭ���� �ش� ����� �����մϴ�.]"), loc, inv);
					a = a+1;
					loc=loc+1;
				}
			}

		}
		
		if(TelePortList.length-(page*44)>45)
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		if(player.isOp() == true)
			Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�� ����", 339,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ���� ������ �����մϴ�."), 49, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void WarpListGUIInventoryclick(InventoryClickEvent event)
	{
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();

		int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		switch (event.getSlot())
		{
		case 45:
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			if(player.isOp() == true)
				new GBD.GoldBigDragon_Advanced.GUI.OPBoxGUI().OPBoxGUI_Main(player, 2);
			else
				new GBD.GoldBigDragon_Advanced.GUI.ETCGUI().ETCGUI_Main(player);
			return;
		case 48://���� ������
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			WarpListGUI(player, page-1);
			return;
		case 49://���� ����
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			player.closeInventory();
			player.sendMessage(ChatColor.DARK_AQUA+"[����] : �� �������� �̸��� ���� �ּ���!");
			Main.UserData.get(player).setType("Teleport");
			Main.UserData.get(player).setString((byte)1, "NW");
			return;
		case 50://���� ������
			s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
			WarpListGUI(player, page+1);
			return;
		case 54:
			s.SP(player, Sound.PISTON_RETRACT, 1.0F, 1.0F);
			player.closeInventory();
			return;
		default:
			if(event.getCurrentItem().getTypeId()==2)
			{
				s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
				new GBD.GoldBigDragon_Advanced.ETC.Teleport().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
			}
			else
			{
				if(event.isShiftClick()==false&&event.isLeftClick())
				{
					s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
					new GBD.GoldBigDragon_Advanced.ETC.Teleport().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
				else if(event.isShiftClick()&&event.isLeftClick()&&player.isOp())
				{
					s.SP(player, Sound.ITEM_PICKUP, 1.0F, 1.0F);
					new GBD.GoldBigDragon_Advanced.ETC.Teleport().setTeleportPermission(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					WarpListGUI(player, page);
				}
				else if(event.isShiftClick()&&event.isRightClick()&&player.isOp())
				{
					s.SP(player, Sound.LAVA_POP, 1.0F, 1.0F);
					new GBD.GoldBigDragon_Advanced.ETC.Teleport().RemoveTeleportList(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					WarpListGUI(player, page);
				}
			}
			return;
		}
	}
}
