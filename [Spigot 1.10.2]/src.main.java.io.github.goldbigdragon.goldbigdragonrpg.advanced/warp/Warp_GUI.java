package warp;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;

public class Warp_GUI extends Util_GUI
{
	public void WarpListGUI(Player player, int page)
	{
		YamlLoader TelePort = new YamlLoader();
		TelePort.getConfig("Teleport/TeleportList.yml");
		String UniqueCode = "��0��0��c��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� : " + (page+1));

		Object[] TelePortList= TelePort.getKeys().toArray();

		byte loc=0;
		String worldname[]= new String[Bukkit.getServer().getWorlds().size()];
		for(int count=0;count<Bukkit.getServer().getWorlds().size();count++)
			worldname[count] = Bukkit.getServer().getWorlds().get(count).getName();
		short a = 0;
		for(int count = (short) (page*45); count < TelePortList.length+Bukkit.getServer().getWorlds().size();count++)
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
						Stack("��f"+TelePortTitle, 345, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
							"��3x ��ǥ : ��f��l"+x
							,"��3y ��ǥ : ��f��l"+y
							,"��3z ��ǥ : ��f��l"+z
							,"��8�ü� : ��7��l"+pitch
							,"��8���� : ��7��l"+yaw
							,""
							,"��9[���� OP�� ��ɾ�� �̵� �����մϴ�.]","","��e[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]","��e[Shift + �� Ŭ���� ������ �����մϴ�.]","��c[Shift + �� Ŭ���� �ش� ������ �����մϴ�.]"), loc, inv);
					else
						Stack("��f"+TelePortTitle, 345, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
							"��3x ��ǥ : ��f��l"+x
							,"��3y ��ǥ : ��f��l"+y
							,"��3z ��ǥ : ��f��l"+z
							,"��8�ü� : ��7��l"+pitch
							,"��8���� : ��7��l"+yaw
							,""
							,"��a[�Ϲ� ������ ��ɾ�� �̵� �����մϴ�.]","","��e[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]","��e[Shift + �� Ŭ���� ������ �����մϴ�.]","��c[Shift + �� Ŭ���� �ش� ������ �����մϴ�.]"), loc, inv);
					loc++;
				}
				else
				{
					if(OnlyOpUse == false)
						Stack("��f"+TelePortTitle, 345, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
							"��3x ��ǥ : ��f��l"+x
							,"��3y ��ǥ : ��f��l"+y
							,"��3z ��ǥ : ��f��l"+z
							,"��8�ü� : ��7��l"+pitch
							,"��8���� : ��7��l"+yaw
							,"","��e[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]"), loc, inv);
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
					Stack("��f"+world, 2, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
							"��3x ���� ��ǥ : ��f��l"+x
							,"��3y ���� ��ǥ : ��f��l"+y
							,"��3z ���� ��ǥ : ��f��l"+z
							,"��8�ü� : ��7��l"+pitch
							,"��8���� : ��7��l"+yaw
							,""
							,"��9[���� OP�� ��ɾ�� �̵� �����մϴ�.]","","��e[�� Ŭ���� �ش� ����� �����մϴ�.]"), loc, inv);
					a++;
					loc++;
				}
			}

		}
		
		if(TelePortList.length-(page*44)>45)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		if(player.isOp() == true)
			Stack("��f��l�� ����", 339,0,1,Arrays.asList("��7���ο� ���� ������ �����մϴ�."), 49, inv);
		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void WarpListGUIInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)
			{
				if(player.isOp())
					new admin.OPbox_GUI().opBoxGuiMain(player, (byte) 2);
				else
					new user.ETC_GUI().ETCGUI_Main(player);
			}
			else if(slot == 48)//���� ������
				WarpListGUI(player, page-1);
			else if(slot == 50)//���� ������
				WarpListGUI(player, page+1);
			else if(slot == 49 && player.isOp())//���� ����
			{
				player.closeInventory();
				player.sendMessage("��3[����] : �� �������� �̸��� ���� �ּ���!");
				UserData_Object u = new UserData_Object();
				u.setType(player, "Teleport");
				u.setString(player, (byte)1, "NW");
			}
			else
			{
				if(event.getCurrentItem().getTypeId()==2)
					new warp.Warp_Main().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else
				{
					if(event.isShiftClick()==false&&event.isLeftClick())
						new warp.Warp_Main().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					else if(event.isShiftClick()&&event.isLeftClick()&&player.isOp())
					{
						new warp.Warp_Main().setTeleportPermission(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						WarpListGUI(player, page);
					}
					else if(event.isShiftClick()&&event.isRightClick()&&player.isOp())
					{
						SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
						new warp.Warp_Main().RemoveTeleportList(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						WarpListGUI(player, page);
					}
				}
			}
		}
	}
}
