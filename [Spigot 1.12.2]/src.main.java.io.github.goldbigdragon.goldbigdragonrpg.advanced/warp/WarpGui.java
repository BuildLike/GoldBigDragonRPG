package warp;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;

public class WarpGui extends UtilGui
{
	public void warpListGUI(Player player, int page)
	{
		YamlLoader telePort = new YamlLoader();
		telePort.getConfig("Teleport/TeleportList.yml");
		String uniqueCode = "��0��0��c��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��� : " + (page+1));

		Object[] telePortList= telePort.getKeys().toArray();

		byte loc=0;
		String[] worldname= new String[Bukkit.getServer().getWorlds().size()];
		for(int count=0;count<Bukkit.getServer().getWorlds().size();count++)
			worldname[count] = Bukkit.getServer().getWorlds().get(count).getName();
		short a = 0;
		for(int count = (short) (page*45); count < telePortList.length+Bukkit.getServer().getWorlds().size();count++)
		{
			if(loc >= 45) break;
			if(count < telePortList.length)
			{
				String telePortTitle =telePortList[count].toString();
				String world = telePort.getString(telePortTitle+".World");
				int x = telePort.getInt(telePortTitle+".X");
				short y = (short) telePort.getInt(telePortTitle+".Y");
				int z = telePort.getInt(telePortTitle+".Z");
				short pitch = (short) telePort.getInt(telePortTitle+".Pitch");
				short yaw = (short) telePort.getInt(telePortTitle+".Yaw");
				boolean onlyOpUse = telePort.getBoolean(telePortTitle+".OnlyOpUse");

				if(player.isOp())
				{
					if(onlyOpUse)
						stack("��f"+telePortTitle, 345, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
							"��3x ��ǥ : ��f��l"+x
							,"��3y ��ǥ : ��f��l"+y
							,"��3z ��ǥ : ��f��l"+z
							,"��8�ü� : ��7��l"+pitch
							,"��8���� : ��7��l"+yaw
							,""
							,"��9[���� OP�� ��ɾ�� �̵� �����մϴ�.]","","��e[�� Ŭ���� �ش� ��ġ�� �����մϴ�.]","��e[Shift + �� Ŭ���� ������ �����մϴ�.]","��c[Shift + �� Ŭ���� �ش� ������ �����մϴ�.]"), loc, inv);
					else
						stack("��f"+telePortTitle, 345, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
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
					if(!onlyOpUse)
						stack("��f"+telePortTitle, 345, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
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
				if(player.isOp())
				{
					String world = worldname[a];
					int x = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getX();
					short y = (short) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getY();
					int z = (int) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getZ();
					short pitch = (short) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getPitch();
					short yaw = (short) Bukkit.getServer().getWorld(worldname[a]).getSpawnLocation().getYaw();
					stack("��f"+world, 2, 0, 1,Arrays.asList("��3���� : ��f��l"+world,
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
		
		if(telePortList.length-(page*44)>45)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		if(player.isOp())
			stack("��f��l�� ����", 339,0,1,Arrays.asList("��7���ο� ���� ������ �����մϴ�."), 49, inv);
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void warpListGUIInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//�ݱ�
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.0F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)
			{
				if(player.isOp())
					new admin.OPboxGui().opBoxGuiMain(player, (byte) 2);
				else
					new user.EtcGui().ETCGUI_Main(player);
			}
			else if(slot == 48)//���� ������
				warpListGUI(player, page-1);
			else if(slot == 50)//���� ������
				warpListGUI(player, page+1);
			else if(slot == 49 && player.isOp())//���� ����
			{
				player.closeInventory();
				player.sendMessage("��3[����] : �� �������� �̸��� ���� �ּ���!");
				UserDataObject u = new UserDataObject();
				u.setType(player, "Teleport");
				u.setString(player, (byte)1, "NW");
			}
			else
			{
				if(event.getCurrentItem().getTypeId()==2)
					new warp.WarpMain().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else
				{
					if(!event.isShiftClick()&&event.isLeftClick())
						new warp.WarpMain().TeleportUser(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					else if(event.isShiftClick()&&event.isLeftClick()&&player.isOp())
					{
						new warp.WarpMain().setTeleportPermission(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						warpListGUI(player, page);
					}
					else if(event.isShiftClick()&&event.isRightClick()&&player.isOp())
					{
						SoundEffect.playSound(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
						new warp.WarpMain().RemoveTeleportList(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						warpListGUI(player, page);
					}
				}
			}
		}
	}
}
