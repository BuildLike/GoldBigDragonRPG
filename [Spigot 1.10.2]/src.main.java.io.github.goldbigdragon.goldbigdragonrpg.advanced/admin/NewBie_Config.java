package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;
import util.YamlLoader;



public class NewBie_Config
{
    public void CreateNewConfig()
	{
	  	YamlLoader newbieYaml = new YamlLoader();
    	newbieYaml.getConfig("ETC/NewBie.yml");
	  	newbieYaml.set("TelePort.World", Bukkit.getWorlds().get(0).getSpawnLocation().getWorld().getName().toString());
	  	newbieYaml.set("TelePort.X", Bukkit.getWorlds().get(0).getSpawnLocation().getX());
	  	newbieYaml.set("TelePort.Y", Bukkit.getWorlds().get(0).getSpawnLocation().getY());
	  	newbieYaml.set("TelePort.Z", Bukkit.getWorlds().get(0).getSpawnLocation().getZ());
	  	newbieYaml.set("TelePort.Pitch", Bukkit.getWorlds().get(0).getSpawnLocation().getPitch());
	  	newbieYaml.set("TelePort.Yaw", Bukkit.getWorlds().get(0).getSpawnLocation().getYaw());

		ItemStack Icon = new MaterialData(340, (byte) 0).toItemStack(1);
		ItemMeta Icon_Meta = Icon.getItemMeta();
		Icon_Meta.setDisplayName("��e��l[���� ���̵�]");
		Icon_Meta.setLore(Arrays.asList("��9��l[�Ϲ� ����]",
				"��e��l/����",
				ChatColor.WHITE+" �ڽ��� ������ Ȯ���մϴ�.",
				"��e��l/��ų",
				ChatColor.WHITE+" �ڽ��� ��ų�� Ȯ���մϴ�.",
				"��e��l/�ɼ�",
				ChatColor.WHITE+" ���� �ɼ��� �����մϴ�.",
				"��e��l/����Ʈ",
				ChatColor.WHITE+" ���� �������� ����Ʈ�� Ȯ���մϴ�.",
				"��e��l/��",
				ChatColor.WHITE+" ���� �������� ���� Ȯ���մϴ�.",
				"��e��l/��Ÿ",
				ChatColor.WHITE+" ��Ÿ �ý����� Ȯ���մϴ�.",
				"��e��l/��Ƽ",
				ChatColor.WHITE+" ��Ƽ�� ���� ����� �����մϴ�.",
				"",
				ChatColor.GOLD+""+ChatColor.BOLD+"[��� ����/��ȯ/ģ�� �߰�]",
				ChatColor.GRAY+" �÷��̾� �� Ŭ��",
				""
				));
		Icon.setItemMeta(Icon_Meta);
	  	newbieYaml.set("SupportItem.0", Icon);

	  	Icon = new MaterialData(340, (byte) 0).toItemStack(1);
	  	Icon_Meta = Icon.getItemMeta();
	  	Icon_Meta.setDisplayName("��e��l[���� ���̵�]");
	  	Icon_Meta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"[���۷�����]",
				"��e��l/���ǹڽ�",
				ChatColor.WHITE+" ���� ���� ����â�� ���ϴ�.",
				"��e��l/����",
				ChatColor.WHITE+" ������ �����մϴ�.",
				"��e��l/������",
				ChatColor.WHITE+" ���� �տ� �� �����ۿ� ����",
				ChatColor.WHITE+" NBT ������ �մϴ�.",
				"��e��l/����",
				ChatColor.WHITE+" ���� ���� ��ɾ ���ϴ�.",
				"��e��l/��ƼƼ���� [1~10000]",
				ChatColor.WHITE+" �ڽ��� �������� �ش� ���� ����",
				ChatColor.WHITE+" ��� ��ƼƼ�� �����մϴ�.",
				"��e��l/Ÿ���߰� [���ο� ������ Ÿ��]",
				ChatColor.WHITE+" Ŀ���� ������ Ÿ���� �߰��մϴ�.",
				""
				));
	  	Icon.setItemMeta(Icon_Meta);
	  	newbieYaml.set("SupportItem.1", Icon);
	  	
	  	newbieYaml.set("SupportMoney", 1000);
	  	newbieYaml.set("FirstQuest", "null");

	  	Icon = new MaterialData(340, (byte) 0).toItemStack(1);
	  	Icon_Meta = Icon.getItemMeta();
	  	Icon_Meta.setDisplayName(ChatColor.YELLOW +""+ ChatColor.BOLD + "���� �ý���");
	  	Icon_Meta.setLore(Arrays.asList(ChatColor.GRAY+ "�÷����ο��� 5���� ������ �ֽ��ϴ�.",ChatColor.RED +"["+main.Main_ServerOption.STR+"]",ChatColor.GRAY+""+main.Main_ServerOption.STR+"�� �÷��̾���",ChatColor.GRAY+"������ �������� �����մϴ�.",ChatColor.GREEN +  "["+main.Main_ServerOption.DEX+"]",ChatColor.GRAY+""+main.Main_ServerOption.DEX+"�� �÷��̾��� �뷱�� ��",ChatColor.GRAY+"���� �������� ���� ǰ��,",ChatColor.GRAY+"���Ÿ� �������� �����մϴ�.",ChatColor.BLUE+"["+main.Main_ServerOption.INT+"]",ChatColor.GRAY+""+main.Main_ServerOption.INT+"�� ������� �� ������ȣ,",ChatColor.GRAY+"���� ���ݷ¿� �����մϴ�.",ChatColor.WHITE+"["+main.Main_ServerOption.WILL+"]",ChatColor.GRAY + ""+main.Main_ServerOption.WILL+"�� �÷��̾���",ChatColor.GRAY + "ũ��Ƽ�ÿ� �����մϴ�.",ChatColor.YELLOW + "["+main.Main_ServerOption.LUK+"]",ChatColor.GRAY + ""+main.Main_ServerOption.LUK+"�� ũ��Ƽ�� ��",ChatColor.GRAY +"��Ű �ǴϽ�, ��Ű ���ʽ� ��",ChatColor.GRAY +"���� 'Ȯ��'�� �����մϴ�."));
	  	Icon.setItemMeta(Icon_Meta);
	  	newbieYaml.set("Guide.0", Icon);

		Icon = new MaterialData(340, (byte) 0).toItemStack(1);
		Icon_Meta = Icon.getItemMeta();
		Icon_Meta.setDisplayName(ChatColor.YELLOW +""+ ChatColor.BOLD + "����Ű �ý���");
		Icon_Meta.setLore(Arrays.asList(ChatColor.WHITE+ "/��ų"+ChatColor.GRAY+" ��ɾ ���Ͽ�",ChatColor.GRAY+"���� �ڽ��� �˰� �ִ� ��ų��",ChatColor.GRAY+"������ �� ������, Ŭ���� ����",ChatColor.GRAY+"�ش� ��ų�� �ֹٿ� ��� ��Ŵ���� ��",ChatColor.GRAY+"����Ű�� ���� ���� ��ų �����",ChatColor.GRAY+"�����մϴ�.",ChatColor.GRAY+"���� ���� ���� Ư�� �����۵���",ChatColor.GRAY+"����Ű�� ���� ������ �����",ChatColor.GRAY+"�����ϰ� �Ǿ� �ֽ��ϴ�."));
		Icon.setItemMeta(Icon_Meta);
	  	newbieYaml.set("Guide.1", Icon);
	  	newbieYaml.saveConfig();
	  	return;
	}
}
