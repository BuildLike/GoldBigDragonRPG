package monster.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import user.UserDataObject;
import util.GuiUtil;
import util.YamlLoader;

public class MonsterPotionGui extends GuiUtil{
	
	private String uniqueCode = "��0��0��8��0��2��r";

	public void monsterPotionGui(Player player, String monsterName)
	{
		YamlLoader monsterListYaml = new YamlLoader();
		monsterListYaml.getConfig("Monster/MonsterList.yml");
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ����");
		
		removeFlagStack("��3[  ���  ]", 373,8193,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.Regenerate")), 10, inv);
		removeFlagStack("��3[  ��  ]", 373,8196,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.Poison")), 11, inv);
		removeFlagStack("��3[  �ż�  ]", 373,8194,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.Speed")), 12, inv);
		removeFlagStack("��3[  ����  ]", 373,8234,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.Slow")), 13, inv);
		removeFlagStack("��3[  ��  ]", 373,8201,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.Strength")), 14, inv);
		removeFlagStack("��3[  ������  ]", 373,8232,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.Weak")), 15, inv);
		removeFlagStack("��3[  ����  ]", 373,8267,1,Arrays.asList("��f[  ���� ��  ]","��e "+monsterListYaml.getInt(monsterName+".Potion.JumpBoost")), 16, inv);

		if(monsterListYaml.getInt(monsterName+".Potion.FireRegistance")!=0)
			removeFlagStack("��3[  ȭ�� ����  ]", 373,8227,1,Arrays.asList("��a[  ���� ����  ]"), 19, inv);
		else
			removeFlagStack("��3[  ȭ�� ����  ]", 166,0,1,Arrays.asList("��c[  ���� ������  ]"), 19, inv);
		if(monsterListYaml.getInt(monsterName+".Potion.WaterBreath")!=0)
			removeFlagStack("��3[  ���� ȣȩ  ]", 373,8237,1,Arrays.asList("��a[  ���� ����  ]"), 20, inv);
		else
			removeFlagStack("��3[  ���� ȣȩ  ]", 166,0,1,Arrays.asList("��c[  ���� ������  ]"), 20, inv);
		if(monsterListYaml.getInt(monsterName+".Potion.Invisible")!=0)
			removeFlagStack("��3[  ����  ]", 373,8238,1,Arrays.asList("��a[  ���� ����  ]"), 21, inv);
		else
			removeFlagStack("��3[  ����  ]", 166,0,1,Arrays.asList("��c[  ���� ������  ]"), 21, inv);
			

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+monsterName), 53, inv);
		player.openInventory(inv);
	}


	public void monsterPotionClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String monsterName = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				new MonsterOptionSettingGui().monsterOptionSettingGui(player, monsterName);
			else if(slot >= 10 && slot <= 16)
			{
				UserDataObject u = new UserDataObject();
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				player.closeInventory();
				u.setType(player, "Monster");
				u.setString(player, (byte)1, "Potion");
				u.setString(player, (byte)3, monsterName);
				if(slot == 10)
				{
					player.sendMessage("��a[����] : ������ ��� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Regenerate");
				}
				else if(slot == 11)
				{
					player.sendMessage("��a[����] : ������ �� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Poision");
				}
				else if(slot == 12)
				{
					player.sendMessage("��a[����] : ������ �ż� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Speed");
				}
				else if(slot == 13)
				{
					player.sendMessage("��a[����] : ������ ���� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Slow");
				}
				else if(slot == 14)
				{
					player.sendMessage("��a[����] : ������ �� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Strength");
				}
				else if(slot == 15)
				{
					player.sendMessage("��a[����] : ������ ������ ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Weak");
				}
				else if(slot == 16)
				{
					player.sendMessage("��a[����] : ������ ���� ȿ���� �� ���� �����Ͻǰǰ���?");
					player.sendMessage("��e(0 ~ 100)");
					u.setString(player, (byte)2, "Jump");
				}
			}
			else if(slot >= 19)
			{
				YamlLoader monsterListYaml = new YamlLoader();
				monsterListYaml.getConfig("Monster/MonsterList.yml");
				if(slot == 19)//ȭ�� ����
				{
					if(monsterListYaml.getInt(monsterName+".Potion.FireRegistance")==0)
						monsterListYaml.set(monsterName+".Potion.FireRegistance", 1);
					else
						monsterListYaml.set(monsterName+".Potion.FireRegistance", 0);
				}
				else if(slot == 20)//���� ȣȩ
				{
					if(monsterListYaml.getInt(monsterName+".Potion.WaterBreath")==0)
						monsterListYaml.set(monsterName+".Potion.WaterBreath", 1);
					else
						monsterListYaml.set(monsterName+".Potion.WaterBreath", 0);
				}
				else if(slot == 21)//����
				{
					if(monsterListYaml.getInt(monsterName+".Potion.Invisible")==0)
						monsterListYaml.set(monsterName+".Potion.Invisible", 1);
					else
						monsterListYaml.set(monsterName+".Potion.Invisible", 0);
				}
				monsterListYaml.saveConfig();
				SoundEffect.playSound(player, Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
				monsterPotionGui(player, monsterName);
			}
		}
	}

}
