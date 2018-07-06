package area.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class AreaSettingGui extends GuiUtil {

	private String uniqueCode = "��0��0��2��0��1��r";

	public void areaSettingGui (Player player, String areaName)
	{
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		Inventory inv = Bukkit.createInventory(null, 45, uniqueCode + "��0���� ����");

		if(!areaYaml.getBoolean(areaName+".BlockUse"))
			removeFlagStack("��f��l[��� ���]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 9, inv);
		else
			removeFlagStack("��f��l[��� ���]", 116,0,1,Arrays.asList("","��a��l[   ���   ]",""), 9, inv);

		if(!areaYaml.getBoolean(areaName+".BlockPlace"))
			removeFlagStack("��f��l[��� ��ġ]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 10, inv);
		else
			removeFlagStack("��f��l[��� ��ġ]", 2,0,1,Arrays.asList("","��a��l[   ���   ]",""), 10, inv);

		if(!areaYaml.getBoolean(areaName+".BlockBreak"))
			removeFlagStack("��f��l[��� �ı�]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 11, inv);
		else
			removeFlagStack("��f��l[��� �ı�]", 278,0,1,Arrays.asList("","��a��l[   ���   ]",""), 11, inv);

		if(!areaYaml.getBoolean(areaName+".PVP"))
			removeFlagStack("��f��l[   PVP   ]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 12, inv);
		else
			removeFlagStack("��f��l[   PVP   ]", 267,0,1,Arrays.asList("","��a��l[   ���   ]",""), 12, inv);

		if(!areaYaml.getBoolean(areaName+".MobSpawn"))
			removeFlagStack("��f��l[���� ����]", 166,0,1,Arrays.asList("","��c��l[   �ź�   ]",""), 13, inv);
		else
			removeFlagStack("��f��l[���� ����]", 52,0,1,Arrays.asList("","��a��l[   ���   ]",""), 13, inv);

		if(!areaYaml.getBoolean(areaName+".Alert"))
			removeFlagStack("��f��l[���� �޽���]", 166,0,1,Arrays.asList("","��c��l[   ����   ]",""), 14, inv);
		else
			removeFlagStack("��f��l[���� �޽���]", 340,0,1,Arrays.asList("","��a��l[   ����   ]",""), 14, inv);

		if(!areaYaml.getBoolean(areaName+".SpawnPoint"))
			removeFlagStack("��f��l[������ ���]", 166,0,1,Arrays.asList("","��c��l[   �Ұ�   ]",""), 15, inv);
		else
			removeFlagStack("��f��l[������ ���]", 397,3,1,Arrays.asList("","��a��l[   ����   ]",""), 15, inv);

		if(!areaYaml.getBoolean(areaName+".Music"))
			removeFlagStack("��f��l[����� ���]", 166,0,1,Arrays.asList("","��c��l[   ����   ]",""), 16, inv);
		else
			removeFlagStack("��f��l[����� ���]", 84,0,1,Arrays.asList("","��a��l[   ���   ]",""), 16, inv);

		if(areaYaml.getInt(areaName+".RegenBlock")==0)
			removeFlagStack("��f��l[��� ����]", 166,0,1,Arrays.asList("","��c��l[   ����   ]",""), 28, inv);
		else
			removeFlagStack("��f��l[��� ����]", 103,0,1,Arrays.asList("","��a��l[   Ȱ��   ]","","��3"+areaYaml.getInt(areaName+".RegenBlock")+" �� ���� ����","","��c[�÷��̾ ���� ĵ ��ϸ� ���� �˴ϴ�]",""), 28, inv);

		removeFlagStack("��f��l[Ư��ǰ ����]", 15,0,1,Arrays.asList("","��7���� �������� ����� ĳ��","��7������ �������� ������","��7���� �մϴ�.","","��e[Ŭ���� Ư��ǰ ����]"), 19, inv);
		removeFlagStack("��f��l[���� ������]", 346,0,1,Arrays.asList("","��7���� �������� ���ø� �Ͽ�","��7���� �� �ִ� ������ Ȯ������","��7�����մϴ�.","��e[Ŭ���� ���� ������ ����]"), 20, inv);
		removeFlagStack("��f��l[�켱���� ����]", 384,0,1,Arrays.asList("","��7�������� ���� ��ĥ ���","��7�켱 ������ �� ���� ������","��7����˴ϴ�.","��7�� �Ӽ��� �̿��Ͽ� ������ �����,","��7���� ������ ���� ���� ��","��7������ ���� �� �ֽ��ϴ�.","��9[   ���� �켱 ����   ]","��f "+areaYaml.getInt(areaName+".Priority"),"","��e[Ŭ���� �켱 ���� ����]"), 21, inv);
		removeFlagStack("��f��l[���� ����]", 383,0,1,Arrays.asList("","��7���� �������� �ڿ�������","��7�����Ǵ� ���� ��ſ�","��7Ŀ���� ���ͷ� �����մϴ�.","","��e[Ŭ���� Ŀ���� ���� ����]","��c[���� ���� ������ ��Ȱ��]"), 22, inv);
		removeFlagStack("��f��l[���� ���� ����]", 52,0,1,Arrays.asList("","��7���� ������ Ư�� ������","��7Ư�� �ð����� ���͸�","��7���� �մϴ�.","","��e[Ŭ���� ���� ���� ����]"), 31, inv);
		removeFlagStack("��f��l[�޽��� ����]", 386,0,1,Arrays.asList("","��7���� ���� �޽����� �����մϴ�.","","��e[Ŭ���� ���� �޽��� ����]"), 23, inv);
		removeFlagStack("��f��l[�߽��� ����]", 138,0,1,Arrays.asList("","��7���� ��ȯ, �ֱ� �湮 ��ġ����","��7������ ���� ���� ��������","��7�ڷ���Ʈ �Ǵ� �̺�Ʈ�� �߻��� ���","��7���� ��ġ�� �߽����� �˴ϴ�.","","��3[  ���� �߽���  ]","��3"+areaYaml.getString(areaName+".World")+" : "+areaYaml.getInt(areaName+".SpawnLocation.X")+","+areaYaml.getInt(areaName+".SpawnLocation.Y")+","+areaYaml.getInt(areaName+".SpawnLocation.Z"),"","��e[Ŭ���� ���� ��ġ�� ����]"), 24, inv);
		
		if(areaYaml.getInt(areaName+".Restrict.MinNowLevel")+areaYaml.getInt(areaName+".Restrict.MinNowLevel")+
			areaYaml.getInt(areaName+".Restrict.MinRealLevel")+areaYaml.getInt(areaName+".Restrict.MaxRealLevel")==0)
			removeFlagStack("��a��l[���� ���� ���� ����]", 166,0,1,Arrays.asList("","��7������ ���� ���� ������ �����ϴ�.",""), 34, inv);
		else
			removeFlagStack("��c��l[���� ���� ����]", 399,0,1,Arrays.asList("","��7������ ���� ���� ������ �ֽ��ϴ�.",""
			,"��3[  �ּ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MinNowLevel")
			,"��3[  �ִ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MaxNowLevel")
			,"��7 �� ������ �ý����� ��� �߰� ���� ��"
			,"��3[  �ּ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MinRealLevel")
			,"��3[  �ִ� ���� ����  ]", "  ��3"+areaYaml.getInt(areaName+".Restrict.MaxRealLevel"),""), 34, inv);
		String lore = "";
		short tracknumber = (short) areaYaml.getInt(areaName+".BGM");
		lore = " %enter%��7���� ����� �׸� ����%enter%��7��� ��ų �� �ֽ��ϴ�.%enter% %enter%��9[Ŭ���� ��Ʈ��� ���� ����]%enter% %enter%��3[Ʈ��] ��9"+ tracknumber+"%enter%"
		+"��3[����] ��9"+ new otherplugins.NoteBlockApiMain().getTitle(tracknumber)+"%enter%"
		+"��3[����] ��9"+new otherplugins.NoteBlockApiMain().getAuthor(tracknumber)+"%enter%��3[����] ";
		
		String description = new otherplugins.NoteBlockApiMain().getDescription(areaYaml.getInt(areaName+".BGM"));
		String lore2="";
		short a = 0;
		for(int count = 0; count <description.toCharArray().length; count++)
		{
			lore2 = lore2+"��9"+description.toCharArray()[count];
			a++;
			if(a >= 15)
			{
				a = 0;
				lore2 = lore2+"%enter%      ";
			}
		}
		lore = lore + lore2;
		
		removeFlagStack("��f��l[���� �����]", 2263,0,1,Arrays.asList(lore.split("%enter%")), 25, inv);
		
		removeFlagStack("��f��l���� �̵�", 368,0,1,Arrays.asList("��7���� �������� ������ �̵��մϴ�."), 40, inv);
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ������� ���ư��ϴ�."), 36, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7���� â�� �ݽ��ϴ�.","��0"+areaName), 44, inv);
		
		player.openInventory(inv);
		return;
	}
	
	public void areaSettingClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		if(slot == 44)//â�ݱ�
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
		  	YamlLoader areaYaml = new YamlLoader();
			areaYaml.getConfig("Area/AreaList.yml");
			String areaName = ChatColor.stripColor(event.getInventory().getItem(44).getItemMeta().getLore().get(1));
			if(slot == 36)//���� ȭ��
				new AreaListGui().areaListGui(player,(short) 0);
			else if(slot >= 9 && slot <= 16)
			{
				if(slot == 9)//��� ���
				{
					if(areaYaml.getBoolean(areaName+".BlockUse") == false)
						areaYaml.set(areaName+".BlockUse", true);
					else
						areaYaml.set(areaName+".BlockUse", false);
				}
				else if(slot == 10)//��� ��ġ
				{
					if(areaYaml.getBoolean(areaName+".BlockPlace") == false)
						areaYaml.set(areaName+".BlockPlace", true);
					else
						areaYaml.set(areaName+".BlockPlace", false);
				}
				else if(slot == 11)//��� �ı�
				{
					if(areaYaml.getBoolean(areaName+".BlockBreak") == false)
						areaYaml.set(areaName+".BlockBreak", true);
					else
						areaYaml.set(areaName+".BlockBreak", false);
				}
				else if(slot == 12)//PVP
				{
					if(areaYaml.getBoolean(areaName+".PVP") == false)
						areaYaml.set(areaName+".PVP", true);
					else
						areaYaml.set(areaName+".PVP", false);
				}
				else if(slot == 13)//���� ����
				{
					if(areaYaml.getBoolean(areaName+".MobSpawn") == false)
						areaYaml.set(areaName+".MobSpawn", true);
					else
						areaYaml.set(areaName+".MobSpawn", false);
				}
				else if(slot == 14)//���� �޽���
				{
					if(areaYaml.getBoolean(areaName+".Alert") == false)
						areaYaml.set(areaName+".Alert", true);
					else
						areaYaml.set(areaName+".Alert", false);
				}
				else if(slot == 15)//������ ���
				{
					if(areaYaml.getBoolean(areaName+".SpawnPoint") == false)
						areaYaml.set(areaName+".SpawnPoint", true);
					else
						areaYaml.set(areaName+".SpawnPoint", false);
				}
				else if(slot == 16)//����� ���
				{
					if(areaYaml.getBoolean(areaName+".Music") == false)
						areaYaml.set(areaName+".Music", true);
					else
						areaYaml.set(areaName+".Music", false);
				}
				areaYaml.saveConfig();
				areaSettingGui(player, areaName);
			}
			else if(slot == 21)//�켱 ���� ����
			{
				UserDataObject u = new UserDataObject();
				player.closeInventory();
				u.setType(player, "Area");
				u.setString(player, (byte)2, "Priority");
				u.setString(player, (byte)3, areaName);
				player.sendMessage("��a[����] : ��e"+areaName+"��a ������ �켱 ������ �Է��ϼ���!");
				player.sendMessage("��7(�ּ� 0 ~ �ִ� 100)");
			}
			else if(slot == 23)//�޽��� ����
			{
				SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.sendMessage("��6/���� "+areaName+" �̸� <���ڿ�>��e\n - "+areaName+" ������ �˸� �޽����� ���� �̸��� ���մϴ�.");
				player.sendMessage("��6/���� "+areaName+" ���� <���ڿ�>��e\n - "+areaName+" ������ �˸� �޽����� ���� �ΰ� ������ ���մϴ�.");
				player.sendMessage("��6%player%��f - �÷��̾� ��Ī�ϱ� -");
				player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
				"��3&3 ��4&4 ��5&5 " +
						"��6&6 ��7&7 ��8&8 " +
				"��9&9 ��a&a ��b&b ��c&c " +
						"��d&d ��e&e ��f&f");
				player.closeInventory();
			}
			else if(slot == 24)//�߽��� ����
			{
				areaYaml.set(areaName+".World", player.getLocation().getWorld().getName());
				areaYaml.set(areaName+".SpawnLocation.X", player.getLocation().getX());
				areaYaml.set(areaName+".SpawnLocation.Y", player.getLocation().getY());
				areaYaml.set(areaName+".SpawnLocation.Z", player.getLocation().getZ());
				areaYaml.set(areaName+".SpawnLocation.Pitch", player.getLocation().getPitch());
				areaYaml.set(areaName+".SpawnLocation.Yaw", player.getLocation().getYaw());
				areaYaml.saveConfig();
				areaSettingGui(player, areaName);
			}
			else if(slot == 25)//���� ����� ����
			{
				if(new otherplugins.NoteBlockApiMain().SoundList(player,true))
					new AreaMusicSettingGui().areaMusicSettingGui(player, 0, areaName);
				else
					SoundEffect.playSound(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.9F);
			}
			else if(slot == 28)//��� ����
			{
				if(areaYaml.getInt(areaName+".RegenBlock")==0)
				{
					player.closeInventory();
					UserDataObject u = new UserDataObject();
					areaYaml.set(areaName+".RegenBlock", 1);
					areaYaml.saveConfig();
					u.setType(player, "Area");
					u.setString(player, (byte)2, "ARR");
					u.setString(player, (byte)3, areaName);
					player.sendMessage("��a[����] : ��e"+areaName+"��a ������ ��� ���� �ӵ��� �����ϼ���!");
					player.sendMessage("��7(�ּ� 1�� ~ �ִ� 3600��(1�ð�))");
				}
				else
				{
					areaYaml.set(areaName+".RegenBlock", 0);
					areaYaml.saveConfig();
					areaSettingGui(player, areaName);
				}
			}
			else if(slot == 19)//Ư��ǰ ����
				new AreaBlockSettingGui().areaBlockSettingGui(player, 0, areaName);
			else if(slot == 20)//���� ������
				new AreaFishSettingGui().areaFishSettingGui(player, areaName);
			else if(slot == 22)//���� ����
				new AreaMonsterSettingGui().areaMonsterSettingGui(player, 0, areaName);
			else if(slot == 31)//���� ���� ��
				new AreaMonsterSpawnSettingGui().areaMonsterSpawnSettingGui(player, 0, areaName);
			else if(slot == 34)//���� ����
			{
				UserDataObject u = new UserDataObject();
				player.closeInventory();
				u.setType(player, "Area");
				u.setString(player, (byte)2, "MinNLR");
				u.setString(player, (byte)3, areaName);
				player.sendMessage("��a[����] : ��e"+areaName+"��a ������ ���忡 �ʿ��� �ּ� ������ �Է� �ϼ���!��7 (0 �Է½� ���� ����)");
			}
			else if(slot == 40)//���� �̵�
			{
				player.closeInventory();
				player.teleport(new Location(Bukkit.getWorld(areaYaml.getString(areaName+".World")),areaYaml.getInt(areaName+".SpawnLocation.X"), areaYaml.getInt(areaName+".SpawnLocation.Y"),areaYaml.getInt(areaName+".SpawnLocation.Z"),areaYaml.getInt(areaName+".SpawnLocation.Yaw"),areaYaml.getInt(areaName+".SpawnLocation.Pitch")));
			}
		}
		return;
	}
}
