package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserDataObject;
import util.GuiUtil;

public class WorldCreateGui extends GuiUtil
{
	public void worldCreateGuiMain(Player player)
	{
		String uniqueCode = "��0��0��1��1��b��r";
		Inventory inv = Bukkit.createInventory(null, 36, uniqueCode + "��0���� ����");

		UserDataObject u = new UserDataObject();
		String type = u.getString(player, 3);
		String biome = u.getString(player, 4);
		boolean createStructure = u.getBoolean(player, (byte)0);
		
		if(type.equals("FLAT"))
			stack("��f��l������ ����", 2,0,1,Arrays.asList("��7������ ������ ���� ������ �����մϴ�.", "", "��8�Ϲ� ����", " ��7��l������ ����", "��8���� ���̿�", ""), 11, inv);
		else if(type.equals("LARGE_BIOMES"))
			stack("��f��l���� ���̿�", 175,4,1,Arrays.asList("��7���̿��� ���� ������ �����մϴ�.", "", "��8�Ϲ� ����", "��8������ ����", " ��7��l���� ���̿�", ""), 11, inv);
		else
			stack("��f��l�Ϲ� ����", 6,0,1,Arrays.asList("��7�Ϲ����� ������ �����մϴ�.", "", " ��7��l�Ϲ� ����", "��8������ ����", "��8���� ���̿�", ""), 11, inv);

		if(biome.equals("NETHER"))
		{
			stack("��c��l�״�", 87,0,1,Arrays.asList("��7�״� ���带 �����մϴ�.", "", "��8�Ϲ� ����", " ��7��l�״� ����", "��8���� ����", ""), 13, inv);
			inv.setItem(11, null);
		}
		else if(biome.equals("THE_END"))
		{
			stack("��e��l����", 121,0,1,Arrays.asList("��7���� ���带 �����մϴ�.", "", "��8�Ϲ� ����", "��8�״� ����", " ��7��l���� ����", ""), 13, inv);
			inv.setItem(11, null);
		}
		else
			stack("��a��l�Ϲ�", 2,0,1,Arrays.asList("��7�Ϲ� ���带 �����մϴ�.", "", " ��7��l�Ϲ� ����", "��8�״� ����", "��8���� ����", ""), 13, inv);
		
		if(createStructure)
			stack("��9������ ����", 98,0,1,Arrays.asList("��7���忡 �´� �������� �����մϴ�.", ""), 15, inv);
		else
			stack("��c������ �̻���", 166,0,1,Arrays.asList("��7�������� �������� �ʽ��ϴ�.", ""), 15, inv);

		stack("��f��l���� ����", 2,0,1,Arrays.asList("��7Ŭ�� �� ���� �̸��� �����ϴ�."), 31, inv);
		
		stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 27, inv);
		stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 35, inv);
		player.openInventory(inv);
	}
	
	public void worldCreateGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 35)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 27)//���� ���
				new OPboxGui().opBoxGuiMain(player, (byte) 2);
			else
			{
				UserDataObject u = new UserDataObject();
				if(slot == 11)
				{
					String type = u.getString(player, 3);
					if(type.equals("FLAT"))
						u.setString(player, 3, "LARGE_BIOMES");
					else if(type.equals("LARGE_BIOMES"))
						u.setString(player, 3, "NORMAL");
					else
						u.setString(player, 3, "FLAT");
					worldCreateGuiMain(player);
				}
				else if(slot == 13)
				{
					String biome = u.getString(player, 4);
					if(biome.equals("NETHER"))
						u.setString(player, 4, "THE_END");
					else if(biome.equals("THE_END"))
						u.setString(player, 4, "NORMAL");
					else
						u.setString(player, 4, "NETHER");
					worldCreateGuiMain(player);
				}
				else if(slot == 15)
				{
					boolean createStructure = u.getBoolean(player, (byte)0);
					u.setBoolean(player, (byte)0, ! createStructure);
					worldCreateGuiMain(player);
				}
				else if(slot == 31)
				{
					u.setType(player, "WorldCreater");
					u.setString(player, (byte)2, "WorldCreate");
					player.closeInventory();
					player.sendMessage("��6[���� ����] : ���ο� ���� �̸��� �ۼ� �� �ּ���!");
				}
			}
		}
	}
}
