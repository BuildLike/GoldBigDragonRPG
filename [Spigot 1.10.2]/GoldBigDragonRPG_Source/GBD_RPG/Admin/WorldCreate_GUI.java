package GBD_RPG.Admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_GUI;

public class WorldCreate_GUI extends Util_GUI
{
	public void WorldCreateGUIMain(Player player)
	{
		String UniqueCode = "��0��0��1��1��b��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ����");

		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�Ϲ� ����", 6,0,1,Arrays.asList(ChatColor.GRAY + "�Ϲ����� ���带 �����մϴ�."), 2, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "������ ����", 2,0,1,Arrays.asList(ChatColor.GRAY + "������ ������ ���� ���带 �����մϴ�."), 4, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���̿�", 175,4,1,Arrays.asList(ChatColor.GRAY + "���̿��� ���� ���带 �����մϴ�."), 6, inv);
		
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void WorldCreateGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 8)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player, (byte) 2);
			else
			{
				UserData_Object u = new UserData_Object();
				u.setType(player, "WorldCreater");
				u.setString(player, (byte)2, "WorldCreate");
				player.closeInventory();
				if(slot == 2)//�Ϲ� ���� ����
					u.setString(player, (byte)3, "NORMAL");
				else if(slot == 4)//���� ���� ����
					u.setString(player, (byte)3, "FLAT");
				else if(slot == 6)//���� ���̿� ���� ����
					u.setString(player, (byte)3, "LARGE_BIOMES");
				
				player.sendMessage(ChatColor.GOLD+"[���� ����] : ���ο� ���� �̸��� �ۼ� �� �ּ���!");
			}
		}
	}
}
