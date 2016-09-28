package GBD_RPG.Admin;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import GBD_RPG.Main_Main.Main_ServerOption;
import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Admin_Command
{
	public void onCommand(Player player, String[] args, String string)
	{
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();

		if(player.isOp() == true)
		{
			if(string.compareTo("�׽�Ʈ")==0)
			{
				player.sendMessage(""+Main_ServerOption.PlayerList.get(player.getUniqueId().toString()).getPlayerRootJob());
			}
			else if(string.compareTo("�׽�Ʈ2")==0)
			{
				//������ ����
			}
			else if(string.compareTo("���ǹڽ�")==0)
			{
				new UserData_Object().clearAll(player);
				s.SP(player, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_Main(player,(byte) 1);
			}
			else if(string.compareTo("Ÿ���߰�")==0)
			{
				if(args.length!=1)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /Ÿ���߰� [���ο� ������ Ÿ��]");
					s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.7F);
				}
				else
				{
				  	YamlManager Target = YC.getNewConfig("Item/CustomType.yml");
			  		Target.set("["+args[0]+"]", 0);
			  		Target.saveConfig();
			  		player.sendMessage(ChatColor.GREEN+"[SYSTEM] : ���ο� ������ Ÿ�� �߰� �Ϸ�!  " + ChatColor.WHITE+args[0]);
					s.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.7F);
				}
			}
			else if(string.compareTo("��ƼƼ����")==0)
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /��ƼƼ���� [1~10000]");
					s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
			    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
			    short amount = 0;
			    for(short count = 0; count < entities.size(); count++)
			    {
			    	if(entities.get(count).getType() != EntityType.PLAYER &&entities.get(count).getType() != EntityType.ITEM_FRAME&&entities.get(count).getType()!= EntityType.ARMOR_STAND)
			    	{
			    		entities.get(count).remove();
			    		amount = (short) (amount+1);
			    	}
			    }
			    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"������ ��ƼƼ�� �����Ͽ����ϴ�!");
			}
			else if(string.compareTo("����������")==0)
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /���������� [1~10000]");
					s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
			    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
			    short amount = 0;
			    for(short count = 0; count < entities.size(); count++)
			    {
			    	if(entities.get(count).getType() == EntityType.DROPPED_ITEM)
			    	{
			    		entities.get(count).remove();
			    		amount = (short) (amount+1);
			    	}
			    }
			    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"���� �������� �����Ͽ����ϴ�!");
			}
			else if(string.compareTo("����ö��")==0)
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /����ö�� [1~10000]");
					s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
			    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
			    short amount = 0;
			    for(short count = 0; count < entities.size(); count++)
			    {
			    	if(entities.get(count).getType() != EntityType.PLAYER)
			    	{
			    		entities.get(count).remove();
			    		amount = (short) (amount+1);
			    	}
			    }
			    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"������ ��ƼƼ�� ���� ö���Ͽ����ϴ�!");
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
	}
}
