package customitem.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import util.GuiUtil;
import util.YamlLoader;

public class UseableItemTypeSelectGui extends GuiUtil{

	private String uniqueCode = "��0��0��3��0��4��r";
	
	public void useableItemTypeSelectGui(Player player)
	{
		Inventory inv = Bukkit.createInventory(null, 27, uniqueCode + "��0�Ҹ� ������ Ÿ��");

		removeFlagStack("��f��l[��ȯ��]", 340,0,1,Arrays.asList("��7Ư�� ��ġ�� �ż��� �̵��� �� �ִ�","��7��ȯ���� �����մϴ�."), 0, inv);
		removeFlagStack("��f��l[�ֹ���]", 339,0,1,Arrays.asList("��7Ư���� ����� ���","��7�ֹ����� �����մϴ�."), 1, inv);
		removeFlagStack("��f��l[��ų ��]", 403,0,1,Arrays.asList("��7Ư�� ��ų�� ��� �� �ִ�","��7��ų ���� �����մϴ�.","","��c[���� �ý����� '������'���� �մϴ�.]"), 2, inv);
		removeFlagStack("��f��l[����, ����]", 297,0,1,Arrays.asList("��7���������� ����� ������","��7���� �� ���� ���� �����մϴ�."), 3, inv);
		removeFlagStack("��f��l[��]", 381,0,1,Arrays.asList("��7������ �ɷ��� �÷��ִ�","��7�ź��� ���� �����մϴ�."), 4, inv);
		removeFlagStack("��f��l[����]", 145,0,1,Arrays.asList("��7����� ���õ� Ȥ��","��7�ִ� �������� �÷��ݴϴ�."), 5, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 18, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 26, inv);
		player.openInventory(inv);
	}

	public void useableItemTypeSelectClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 26)
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 18)
				new UseableItemListGui().useableItemListGui(player, 0);
			else
			{
				String type = "";
				String lore = "";
				String displayName = "";
				short id = 267;
				short data = 0;
				
				if(slot == 0)
				{
					type = "��f[��ȯ��]";
					lore = "��f��ü�� �ս� ���� ������ ������%enter%��f������ �̵��� �� �ִ� �ź���%enter%��f��ȯ �ֹ����̴�.";
					displayName = "��f0�� ��ȯ �ֹ���";
					id = 340;
				}
				else if(slot == 1)
				{
					type = "��6[�ֹ���]";
					lore = "��f���� ��ų ����Ʈ��%enter%��f5��ŭ ��½��� �ش�.";
					displayName ="��f��ų ����Ʈ 5 ���� �ֹ���";
					id = 339;
				}
				else if(slot == 2)
				{
					type = "��5[��ų��]";
					lore = "��f���� �ƹ��͵� �������� ����%enter%��f�� ������ ��ų ���̴�.%enter% %enter%��f(�ƹ��͵� ���� �� ���� �� ����.)";
					displayName = "��f�� ��ų ��";
					id = 403;
				}
				else if(slot == 3)
				{
					type = "��d[�Һ�]";
					lore = "��f�� ���Կ� ����, ���޽�%enter%��f����� ���, �������%enter%��f10 ġ���� �ִ� �����̴�.";
					displayName = "��f�û��� ����";
					id = 373;
					data = 8261;
				}
				else if(slot == 4)
				{
					type = "��9[��]";
					lore = "��f������ ����� ���̴�.%enter%��f������ �뷱���� �÷��ִ� �� �ϴ�.";
					displayName ="��f��� ��";
					id = 351;
					data = 10;
				}
				else if(slot == 5)
				{
					type = "��e[����]";
					lore = "��f�ն� ���� ���� ����̴�.%enter%��f���õ��� �÷��� �� ����.";
					displayName ="��6���õ� ����� ���";
					id = 145;
					data = 2;
				}

			  	YamlLoader useableItemYaml = new YamlLoader();
				useableItemYaml.getConfig("Item/Consume.yml");
				
				int itemCounter = useableItemYaml.getKeys().size();
				useableItemYaml.set(itemCounter+".ShowType","��f[���]");
				useableItemYaml.set(itemCounter+".ID",id);
				useableItemYaml.set(itemCounter+".Data",data);
				useableItemYaml.set(itemCounter+".DisplayName",displayName);
				useableItemYaml.set(itemCounter+".Lore",lore);
				useableItemYaml.set(itemCounter+".Type",type);
				useableItemYaml.set(itemCounter+".Grade","��f[�Ϲ�]");
				
				if(slot == 0)
				{
					useableItemYaml.set(itemCounter+".World",player.getLocation().getWorld().getName().toString());
					useableItemYaml.set(itemCounter+".X",0);
					useableItemYaml.set(itemCounter+".Y",0);
					useableItemYaml.set(itemCounter+".Z",0);
					useableItemYaml.set(itemCounter+".Pitch",0);
					useableItemYaml.set(itemCounter+".Yaw",0);
				}
				else if(slot == 1)
				{
					useableItemYaml.set(itemCounter+".DEF",0);
					useableItemYaml.set(itemCounter+".Protect",0);
					useableItemYaml.set(itemCounter+".MaDEF",0);
					useableItemYaml.set(itemCounter+".MaProtect",0);
					useableItemYaml.set(itemCounter+".STR",0);
					useableItemYaml.set(itemCounter+".DEX",0);
					useableItemYaml.set(itemCounter+".INT",0);
					useableItemYaml.set(itemCounter+".WILL",0);
					useableItemYaml.set(itemCounter+".LUK",0);
					useableItemYaml.set(itemCounter+".Balance",0);
					useableItemYaml.set(itemCounter+".Critical",0);
					useableItemYaml.set(itemCounter+".HP",0);
					useableItemYaml.set(itemCounter+".MP",0);
					useableItemYaml.set(itemCounter+".SkillPoint",5);
					useableItemYaml.set(itemCounter+".StatPoint",0);
				}
				else if(slot == 2)
					useableItemYaml.set(itemCounter+".Skill","null");
				else if(slot == 3)
				{
					useableItemYaml.set(itemCounter+".HP",10);
					useableItemYaml.set(itemCounter+".MP",0);
					useableItemYaml.set(itemCounter+".Saturation",0);
					useableItemYaml.set(itemCounter+".Rebirth",false);
				}
				else if(slot == 4)
				{
					useableItemYaml.set(itemCounter+".MinDamage",0);
					useableItemYaml.set(itemCounter+".MaxDamage",0);
					useableItemYaml.set(itemCounter+".MinMaDamage",0);
					useableItemYaml.set(itemCounter+".MaxMaDamage",0);
					useableItemYaml.set(itemCounter+".DEF",0);
					useableItemYaml.set(itemCounter+".Protect",0);
					useableItemYaml.set(itemCounter+".MaDEF",0);
					useableItemYaml.set(itemCounter+".MaProtect",0);
					useableItemYaml.set(itemCounter+".Durability",0);
					useableItemYaml.set(itemCounter+".MaxDurability",0);
					useableItemYaml.set(itemCounter+".STR",0);
					useableItemYaml.set(itemCounter+".DEX",0);
					useableItemYaml.set(itemCounter+".INT",0);
					useableItemYaml.set(itemCounter+".WILL",0);
					useableItemYaml.set(itemCounter+".LUK",0);
					useableItemYaml.set(itemCounter+".Balance",10);
					useableItemYaml.set(itemCounter+".Critical",0);
					useableItemYaml.set(itemCounter+".HP",0);
					useableItemYaml.set(itemCounter+".MP",0);
				}
				else if(slot == 5)
				{
					useableItemYaml.set(itemCounter+".MaxDurability",0);
					useableItemYaml.set(itemCounter+".Proficiency", 100);
				}
				useableItemYaml.saveConfig();
				new UseableItemMakingGui().useableItemMakingGui(player,itemCounter);
			}
		}
	}
}
