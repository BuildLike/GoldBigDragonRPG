package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;
import util.YamlLoader;



public class NewBieConfig
{
    public void createNewConfig()
	{
	  	YamlLoader newbieYaml = new YamlLoader();
    	newbieYaml.getConfig("ETC/NewBie.yml");
	  	newbieYaml.set("TelePort.World", Bukkit.getWorlds().get(0).getSpawnLocation().getWorld().getName().toString());
	  	newbieYaml.set("TelePort.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
	  	newbieYaml.set("TelePort.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
	  	newbieYaml.set("TelePort.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
	  	newbieYaml.set("TelePort.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
	  	newbieYaml.set("TelePort.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());

		ItemStack icon = new MaterialData(340, (byte) 0).toItemStack(1);
		ItemMeta iconMeta = icon.getItemMeta();
		iconMeta.setDisplayName("��e��l[���� ���̵�]");
		iconMeta.setLore(Arrays.asList("��9��l[�Ϲ� ����]",
				"��e��l/����",
				"��f �ڽ��� ������ Ȯ���մϴ�.",
				"��e��l/��ų",
				"��f �ڽ��� ��ų�� Ȯ���մϴ�.",
				"��e��l/�ɼ�",
				"��f ���� �ɼ��� �����մϴ�.",
				"��e��l/����Ʈ",
				"��f ���� �������� ����Ʈ�� Ȯ���մϴ�.",
				"��e��l/��",
				"��f ���� �������� ���� Ȯ���մϴ�.",
				"��e��l/��Ÿ",
				"��f ��Ÿ �ý����� Ȯ���մϴ�.",
				"��e��l/��Ƽ",
				"��f ��Ƽ�� ���� ����� �����մϴ�.",
				"",
				"��6��l[��� ����/��ȯ/ģ�� �߰�]",
				"��7 �÷��̾� �� Ŭ��",
				""
				));
		icon.setItemMeta(iconMeta);
	  	newbieYaml.set("SupportItem.0", icon);

	  	icon = new MaterialData(340, (byte) 0).toItemStack(1);
	  	iconMeta = icon.getItemMeta();
	  	iconMeta.setDisplayName("��e��l[���� ���̵�]");
	  	iconMeta.setLore(Arrays.asList("��d��l[���۷�����]",
				"��e��l/���ǹڽ�",
				"��f ���� ���� ����â�� ���ϴ�.",
				"��e��l/����",
				"��f ������ �����մϴ�.",
				"��e��l/������",
				"��f ���� �տ� �� �����ۿ� ����",
				"��f NBT ������ �մϴ�.",
				"��e��l/����",
				"��f ���� ���� ��ɾ ���ϴ�.",
				"��e��l/��ƼƼ���� [1~10000]",
				"��f �ڽ��� �������� �ش� ���� ����",
				"��f ��� ��ƼƼ�� �����մϴ�.",
				"��e��l/Ÿ���߰� [���ο� ������ Ÿ��]",
				"��f Ŀ���� ������ Ÿ���� �߰��մϴ�.",
				""
				));
	  	icon.setItemMeta(iconMeta);
	  	newbieYaml.set("SupportItem.1", icon);
	  	
	  	newbieYaml.set("SupportMoney", 1000);
	  	newbieYaml.set("FirstQuest", "null");

	  	icon = new MaterialData(340, (byte) 0).toItemStack(1);
	  	iconMeta = icon.getItemMeta();
	  	iconMeta.setDisplayName("��e��l���� �ý���");
	  	iconMeta.setLore(Arrays.asList("��7�÷����ο��� 5���� ������ �ֽ��ϴ�.","��c["+main.MainServerOption.statSTR+"]","��7"+main.MainServerOption.statSTR+"�� �÷��̾���","��7������ �������� �����մϴ�.",ChatColor.GREEN + "["+main.MainServerOption.statDEX+"]","��7"+main.MainServerOption.statDEX+"�� �÷��̾��� �뷱�� ��","��7���� �������� ���� ǰ��,","��7���Ÿ� �������� �����մϴ�.","��9["+main.MainServerOption.statINT+"]","��7"+main.MainServerOption.statINT+"�� ������� �� ������ȣ,","��7���� ���ݷ¿� �����մϴ�.","��f["+main.MainServerOption.statWILL+"]","��7"+main.MainServerOption.statWILL+"�� �÷��̾���","��7ũ��Ƽ�ÿ� �����մϴ�.","��e["+main.MainServerOption.statLUK+"]","��7"+main.MainServerOption.statLUK+"�� ũ��Ƽ�� ��","��7��Ű �ǴϽ�, ��Ű ���ʽ� ��","��7���� 'Ȯ��'�� �����մϴ�."));
	  	icon.setItemMeta(iconMeta);
	  	newbieYaml.set("Guide.0", icon);

		icon = new MaterialData(340, (byte) 0).toItemStack(1);
		iconMeta = icon.getItemMeta();
		iconMeta.setDisplayName("��e��l����Ű �ý���");
		iconMeta.setLore(Arrays.asList("��f/��ų��7 ��ɾ ���Ͽ�","��7���� �ڽ��� �˰� �ִ� ��ų��","��7������ �� ������, Ŭ���� ����","��7�ش� ��ų�� �ֹٿ� ��� ��Ŵ���� ��","��7����Ű�� ���� ���� ��ų �����","��7�����մϴ�.","��7���� ���� ���� Ư�� �����۵���","��7����Ű�� ���� ������ �����","��7�����ϰ� �Ǿ� �ֽ��ϴ�."));
		icon.setItemMeta(iconMeta);
	  	newbieYaml.set("Guide.1", icon);
	  	newbieYaml.saveConfig();
	  	return;
	}
}
