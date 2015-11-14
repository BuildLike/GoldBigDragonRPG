package GBD.GoldBigDragon_Advanced.GUI;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import GBD.GoldBigDragon_Advanced.Main.Main;

public class WorldCreateGUI extends GUIutil
{
	public void WorldCreateGUIMain(Player player)
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLACK + "���� ����");

		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�Ϲ� ����", 6,0,1,Arrays.asList(ChatColor.GRAY + "�Ϲ����� ���带 �����մϴ�."), 2, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "������ ����", 2,0,1,Arrays.asList(ChatColor.GRAY + "������ ������ ���� ���带 �����մϴ�."), 4, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���̿�", 175,4,1,Arrays.asList(ChatColor.GRAY + "���̿��� ���� ���带 �����մϴ�."), 6, inv);
		
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void WorldCreateGUIClick(InventoryClickEvent event)
	{
		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		GBD.GoldBigDragon_Advanced.Effect.Sound s = new GBD.GoldBigDragon_Advanced.Effect.Sound();
		switch (event.getSlot())
		{
		case 0://���� ���
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			OPBoxGUI OPGUI = new OPBoxGUI();
			OPGUI.OPBoxGUI_Main(player, 2);
			return;
		case 8://������
			s.SP(player, Sound.PISTON_RETRACT, 0.8F, 1.8F);
			player.closeInventory();
			return;
		case 2 :
			player.closeInventory();
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			Main.UserData.get(player).setType("WorldCreater");
			Main.UserData.get(player).setString((byte)2, "WorldCreate");
			Main.UserData.get(player).setString((byte)3, "NORMAL");
			player.sendMessage(ChatColor.GOLD+"[���� ����] : ���ο� ���� �̸��� �ۼ� �� �ּ���!");
			break;
		case 4 :
			player.closeInventory();
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			Main.UserData.get(player).setType("WorldCreater");
			Main.UserData.get(player).setString((byte)2, "WorldCreate");
			Main.UserData.get(player).setString((byte)3, "FLAT");
			player.sendMessage(ChatColor.GOLD+"[���� ����] : ���ο� ���� �̸��� �ۼ� �� �ּ���!");
			break;
		case 6 :
			player.closeInventory();
			s.SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
			Main.UserData.get(player).setType("WorldCreater");
			Main.UserData.get(player).setString((byte)2, "WorldCreate");
			Main.UserData.get(player).setString((byte)3, "LARGE_BIOMES");
			player.sendMessage(ChatColor.GOLD+"[���� ����] : ���ο� ���� �̸��� �ۼ� �� �ּ���!");
			break;
		}
	}
}