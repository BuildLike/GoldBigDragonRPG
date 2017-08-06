package admin;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import effect.SoundEffect;
import user.UserData_Object;
import util.YamlLoader;

public class Admin_Command
{
	public void onCommand(Player player, String[] args, String string)
	{
		if(player.isOp() == true)
		{
			if(string.compareTo("�׽�Ʈ")==0||string.compareTo("gbdtest")==0)
			{
				player.sendMessage("�׽�Ʈ1");
			}
			else if(string.compareTo("�׽�Ʈ2")==0||string.compareTo("gbdtest2")==0)
			{
				player.sendMessage("�׽�Ʈ2");
			}
			else if(string.compareTo("���ǹڽ�")==0||string.compareTo("opbox")==0)
			{
				new UserData_Object().clearAll(player);
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				new admin.OPbox_GUI().OPBoxGUI_Main(player,(byte) 1);
			}
			else if(string.compareTo("Ÿ���߰�")==0||string.compareTo("gbdaddtype")==0)
			{
				if(args.length!=1)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /Ÿ���߰� [���ο� ������ Ÿ��]");
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.7F);
				}
				else
				{
				  	YamlLoader Target = new YamlLoader();
				  	Target.getConfig("Item/CustomType.yml");
			  		Target.set("["+args[0]+"]", 0);
			  		Target.saveConfig();
			  		player.sendMessage(ChatColor.GREEN+"[SYSTEM] : ���ο� ������ Ÿ�� �߰� �Ϸ�!  " + ChatColor.WHITE+args[0]);
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.7F);
				}
			}
			else if(string.compareTo("��ƼƼ����")==0||string.compareTo("gbdremoveentity")==0)
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /��ƼƼ���� [1~10000]");
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
			    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
			    short amount = 0;
			    for(int count = 0; count < entities.size(); count++)
			    {
			    	if(entities.get(count).getType() != EntityType.PLAYER &&entities.get(count).getType() != EntityType.ITEM_FRAME&&entities.get(count).getType()!= EntityType.ARMOR_STAND)
			    	{
			    		entities.get(count).remove();
			    		amount = (short) (amount+1);
			    	}
			    }
			    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"������ ��ƼƼ�� �����Ͽ����ϴ�!");
			}
			else if(string.compareTo("����������")==0||string.compareTo("gbdremoveitem")==0)
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /���������� [1~10000]");
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
			    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
			    short amount = 0;
			    for(int count = 0; count < entities.size(); count++)
			    {
			    	if(entities.get(count).getType() == EntityType.DROPPED_ITEM)
			    	{
			    		entities.get(count).remove();
			    		amount = (short) (amount+1);
			    	}
			    }
			    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"���� �������� �����Ͽ����ϴ�!");
			}
			else if(string.compareTo("����ö��")==0||string.compareTo("gbdforceremove")==0)
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage(ChatColor.RED + "[SYSTEM] : /����ö�� [1~10000]");
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					return;
				}
			    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(args[0]), Integer.parseInt(args[0]), Integer.parseInt(args[0]));
			    short amount = 0;
			    for(int count = 0; count < entities.size(); count++)
			    {
			    	if(entities.get(count).getType() != EntityType.PLAYER)
			    	{
			    		entities.get(count).remove();
			    		amount = (short) (amount+1);
			    	}
			    }
			    player.sendMessage(ChatColor.GREEN + "[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"������ ��ƼƼ�� ���� ö���Ͽ����ϴ�!");
			}
			else if(string.compareTo("�����ʱ�ȭ��")==0||string.compareTo("gbdbacktothenewbie")==0)
			{
				ItemStack Icon = new MaterialData(340, (byte) 0).toItemStack(1);
				ItemMeta Icon_Meta = Icon.getItemMeta();
				Icon_Meta.setDisplayName("��2��3��4��3��3��l[���� �ʱ�ȭ �ֹ���]");
				Icon_Meta.setLore(Arrays.asList("��a[�ֹ���]",""));
				Icon.setItemMeta(Icon_Meta);
				if(args.length==1)
				{
	  				if(Bukkit.getServer().getPlayer(args[0]) != null)
	  				{
	  					Player target = Bukkit.getServer().getPlayer(args[0]);
		  				if(target.isOnline())
		  					new util.Util_Player().giveItemForce(target, Icon);
		  				else
		  				{
		  					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �÷��̾�� �������� �ƴմϴ�!");
		  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  				}
	  				}
				}
				else
  					new util.Util_Player().giveItemForce(player, Icon);
			}
			else if(string.compareTo("����")==0||string.compareTo("giveexp")==0||string.compareTo("����ġ�ֱ�")==0)
			{
				if(args.length==2)
				{
  					Player target = Bukkit.getServer().getPlayer(args[0]);
	  				if(target.isOnline())
	  				{
	  					int EXP = 0;
	  					try
	  					{
	  						EXP = Integer.parseInt(args[1]);
	  					}
	  					catch(NumberFormatException e)
	  					{
	  						player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���!");
		  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  					return;
	  					}
	  					main.Main_ServerOption.PlayerList.get(target.getUniqueId().toString()).addStat_MoneyAndEXP(0, EXP, true);
	  					player.sendMessage(ChatColor.GREEN+"[SYSTEM] : " + args[0] + "�Կ��� ����ġ " + EXP + "�� �����Ͽ����ϴ�!");
	  				}
	  				else
	  				{
	  					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �÷��̾�� �������� �ƴմϴ�!");
	  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  				}
				}
				else
				{
  					player.sendMessage(ChatColor.RED + "[SYSTEM] : /���� [�г���] [����ġ]");
  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				}
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
	}
}
