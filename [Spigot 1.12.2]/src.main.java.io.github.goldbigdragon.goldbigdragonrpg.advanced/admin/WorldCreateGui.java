package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserDataObject;
import util.UtilGui;

public class WorldCreateGui extends UtilGui
{
	public void worldCreateGuiMain(Player player)
	{
		String uniqueCode = "��0��0��1��1��b��r";
		Inventory inv = Bukkit.createInventory(null, 9, uniqueCode + "��0���� ����");

		stack("��f��l�Ϲ� ����", 6,0,1,Arrays.asList("��7�Ϲ����� ���带 �����մϴ�."), 2, inv);
		stack("��f��l������ ����", 2,0,1,Arrays.asList("��7������ ������ ���� ���带 �����մϴ�."), 4, inv);
		stack("��f��l���� ���̿�", 175,4,1,Arrays.asList("��7���̿��� ���� ���带 �����մϴ�."), 6, inv);
		
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 0, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void worldCreateGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 8)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 0)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 2);
			else
			{
				UserDataObject u = new UserDataObject();
				u.setType(player, "WorldCreater");
				u.setString(player, (byte)2, "WorldCreate");
				player.closeInventory();
				if(slot == 2)//�Ϲ� ���� ����
					u.setString(player, (byte)3, "NORMAL");
				else if(slot == 4)//���� ���� ����
					u.setString(player, (byte)3, "FLAT");
				else if(slot == 6)//���� ���̿� ���� ����
					u.setString(player, (byte)3, "LARGE_BIOMES");
				
				player.sendMessage("��6[���� ����] : ���ο� ���� �̸��� �ۼ� �� �ּ���!");
			}
		}
	}
}
