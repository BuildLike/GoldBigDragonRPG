package GBD_RPG.Warp;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_GUI;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Warp_GUI extends Util_GUI
{
	public void WarpListGUI(Player player, int page)
	{
		YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager TelePort  = YC.getNewConfig("Teleport/TeleportList.yml");
		String UniqueCode = "��0��0��c��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));

		Object[] TelePortList= TelePort.getKeys().toArray();

		byte loc=0;
		String worldname[]= new String[Bukkit.getServer().getWorlds().size()];
		for(short count=0;count<Bukkit.getServer().getWorlds().size();count++)
			worldname[count] = Bukkit.getServer().getWorlds().get(count).getName();
		short a = 0;
		for(short count = (short) (page*45); count < TelePortList.length+Bukkit.getServer().getWorlds().size();count++)
		{
			if(loc >= 45) break;
			if(count < TelePortList.length)
			{
				String TelePortTitle =TelePortList[count].toString();
				String world = TelePort.getString(TelePortTitle+".World");
				int x = TelePort.getInt(TelePortTitle+".X");
				short y = (short) TelePort.getInt(TelePortTitle+".Y");
				int z = TelePort.getInt(TelePortTitle+".Z");
				short pitch = (short) TelePort.getInt(TelePortTitle+".Pitch");
				short yaw = (short) TelePort.getInt(TelePortTitle+".Yaw");
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
					loc++;
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
					loc++;
				}
			}
			else
			{
				if(player.isOp() == true)
				{
					String world = worldname[a];
					int x = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getX();
					short y = (short) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getY();
					int z = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getZ();
					short pitch = (short) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getPitch();
					short yaw = (short) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getYaw();
					Stack(ChatColor.WHITE+world, 2, 0, 1,Arrays.asList(ChatColor.DARK_AQUA+"���� : "+ChatColor.WHITE+""+ChatColor.BOLD+world,
							ChatColor.DARK_AQUA+"x ���� ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+x
							,ChatColor.DARK_AQUA+"y ���� ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+y
							,ChatColor.DARK_AQUA+"z ���� ��ǥ : "+ChatColor.WHITE+""+ChatColor.BOLD+z
							,ChatColor.DARK_GRAY+"�ü� : "+ChatColor.GRAY+""+ChatColor.BOLD+pitch
							,ChatColor.DARK_GRAY+"���� : "+ChatColor.GRAY+""+ChatColor.BOLD+yaw
							,""
							,ChatColor.BLUE+"[���� OP�� ��ɾ�� �̵� �����մϴ�.]","",ChatColor.YELLOW+"[�� Ŭ���� �ش� ����� �����մϴ�.]"), loc, inv);
					a++;
					loc++;
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
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		if(slot == 53)//�ݱ�
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)
			{
				if(player.isOp())
					new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_Main(player, (byte) 2);
				else
					new GBD_RPG.User.ETC_GUI().ETCGUI_Main(player);
			}
			else if(slot == 48)//���� ������
				WarpListGUI(player, page-1);
			else if(slot == 50)//���� ������
				WarpListGUI(player, page+1);
			else if(slot == 49 && player.isOp())//���� ����
			{
				player.closeInventory();
				player.sendMessage(ChatColor.DARK_AQUA+"[����] : �� �������� �̸��� ���� �ּ���!");
				UserData_Object u = new UserData_Object();
				u.setType(player, "Teleport");
				u.setString(player, (byte)1, "NW");
			}
			else
			{
				if(event.getCurrentItem().getTypeId()==2)
					new GBD_RPG.Warp.Warp_Main().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else
				{
					if(event.isShiftClick()==false&&event.isLeftClick())
						new GBD_RPG.Warp.Warp_Main().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					else if(event.isShiftClick()&&event.isLeftClick()&&player.isOp())
					{
						new GBD_RPG.Warp.Warp_Main().setTeleportPermission(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						WarpListGUI(player, page);
					}
					else if(event.isShiftClick()&&event.isRightClick()&&player.isOp())
					{
						s.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
						new GBD_RPG.Warp.Warp_Main().RemoveTeleportList(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						WarpListGUI(player, page);
					}
				}
			}
		}
	}
}
