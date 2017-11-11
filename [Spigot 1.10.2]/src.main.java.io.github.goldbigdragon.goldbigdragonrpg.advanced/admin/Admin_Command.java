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
		if(player.isOp())
		{
			if(string.equals("�׽�Ʈ")||string.equals("gbdtest"))
			{
				player.sendMessage("�׽�Ʈ1");
			}
			else if(string.equals("�׽�Ʈ2")||string.equals("gbdtest2"))
			{
				player.sendMessage("�׽�Ʈ2");
			}
			else if(string.equals("���ǹڽ�")||string.equals("opbox"))
			{
				new UserData_Object().clearAll(player);
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_HORSE_ARMOR, 0.8F, 1.8F);
				new admin.OPbox_GUI().opBoxGuiMain(player,(byte) 1);
			}
			else if(string.equals("Ÿ���߰�")||string.equals("gbdaddtype"))
			{
				if(args.length!=1)
				{
					player.sendMessage("��c[SYSTEM] : /Ÿ���߰� [���ο� ������ Ÿ��]");
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.7F);
				}
				else
				{
				  	YamlLoader target = new YamlLoader();
				  	target.getConfig("Item/CustomType.yml");
			  		target.set("["+args[0]+"]", 0);
			  		target.saveConfig();
			  		player.sendMessage("��a[SYSTEM] : ���ο� ������ Ÿ�� �߰� �Ϸ�!  ��f"+args[0]);
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.7F);
				}
			}
			else if(string.equals("��ƼƼ����")||string.equals("gbdremoveentity"))
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage("��c[SYSTEM] : /��ƼƼ���� [1~10000]");
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
			    player.sendMessage("��a[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"������ ��ƼƼ�� �����Ͽ����ϴ�!");
			}
			else if(string.equals("����������")||string.equals("gbdremoveitem"))
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage("��c[SYSTEM] : /���������� [1~10000]");
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
			    player.sendMessage("��a[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"���� �������� �����Ͽ����ϴ�!");
			}
			else if(string.equals("����ö��")||string.equals("gbdforceremove"))
			{
				if(args.length != 1 ||Integer.parseInt(args[0]) > 10000)
				{
					player.sendMessage("��c[SYSTEM] : /����ö�� [1~10000]");
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
			    player.sendMessage("��a[SYSTEM] : �ݰ� "+args[0]+"��� �̳��� �ִ� "+amount+"������ ��ƼƼ�� ���� ö���Ͽ����ϴ�!");
			}
			else if(string.equals("�����ʱ�ȭ��")||string.equals("gbdbacktothenewbie"))
			{
				ItemStack icon = new MaterialData(340, (byte) 0).toItemStack(1);
				ItemMeta iconMeta = icon.getItemMeta();
				iconMeta.setDisplayName("��2��3��4��3��3��l[���� �ʱ�ȭ �ֹ���]");
				iconMeta.setLore(Arrays.asList("��a[�ֹ���]",""));
				icon.setItemMeta(iconMeta);
				if(args.length==1)
				{
	  				if(Bukkit.getServer().getPlayer(args[0]) != null)
	  				{
	  					Player target = Bukkit.getServer().getPlayer(args[0]);
		  				if(target.isOnline())
		  					new util.Util_Player().giveItemForce(target, icon);
		  				else
		  				{
		  					player.sendMessage("��c[SYSTEM] : �ش� �÷��̾�� �������� �ƴմϴ�!");
		  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  				}
	  				}
				}
				else
  					new util.Util_Player().giveItemForce(player, icon);
			}
			else if(string.equals("����")||string.equals("giveexp")||string.equals("����ġ�ֱ�"))
			{
				if(args.length==2)
				{
  					Player target = Bukkit.getServer().getPlayer(args[0]);
	  				if(target.isOnline())
	  				{
	  					int exp = 0;
	  					try
	  					{
	  						exp = Integer.parseInt(args[1]);
	  					}
	  					catch(NumberFormatException e)
	  					{
	  						player.sendMessage("��c[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���!");
		  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		  					return;
	  					}
	  					main.Main_ServerOption.PlayerList.get(target.getUniqueId().toString()).addStat_MoneyAndEXP(0, exp, true);
	  					player.sendMessage("��a[SYSTEM] : " + args[0] + "�Կ��� ����ġ " + exp + "�� �����Ͽ����ϴ�!");
	  				}
	  				else
	  				{
	  					player.sendMessage("��c[SYSTEM] : �ش� �÷��̾�� �������� �ƴմϴ�!");
	  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	  				}
				}
				else
				{
  					player.sendMessage("��c[SYSTEM] : /���� [�г���] [����ġ]");
  					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				}
			}
		}
		else
		{
			player.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
	}
}
