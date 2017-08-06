package admin;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;



public class Event_GUI extends Util_GUI
{
	public void EventGUI_Main (Player player)
	{
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
		String UniqueCode = "��0��0��1��0��9��r";
		
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0�̺�Ʈ ����");
		
		if(configYaml.getInt("Event.Multiple_Level_Up_SkillPoint") == 1)
		{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��ų ����Ʈ �߰� ȹ��", 340,0,1,Arrays.asList(ChatColor.RED + "[�� Ȱ��ȭ]",ChatColor.GRAY + "���� ���� ��� ��ų ����Ʈ : " + configYaml.getInt("Server.Level_Up_SkillPoint")), 10, inv);}
		else
		{{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��ų ����Ʈ �߰� ȹ��", 403,0,configYaml.getInt("Event.Multiple_Level_Up_SkillPoint"),Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.YELLOW +""+configYaml.getInt("Event.Multiple_Level_Up_SkillPoint") +ChatColor.GRAY +"��",ChatColor.GRAY + "���� ���� ��� ��ų ����Ʈ : " + (configYaml.getInt("Server.Level_Up_SkillPoint") * configYaml.getInt("Event.Multiple_Level_Up_SkillPoint"))), 10, inv);}	}

		if(configYaml.getInt("Event.Multiple_Level_Up_StatPoint") == 1)
		{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "���� ����Ʈ �߰� ȹ��", 351,15,1,Arrays.asList(ChatColor.RED + "[�� Ȱ��ȭ]",ChatColor.GRAY + "���� ���� ��� ���� ����Ʈ : " + configYaml.getInt("Server.Level_Up_StatPoint")), 11, inv);}
		else
		{{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "���� ����Ʈ �߰� ȹ��", 399,0,configYaml.getInt("Event.Multiple_Level_Up_StatPoint"),Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.YELLOW +""+configYaml.getInt("Event.Multiple_Level_Up_StatPoint") +ChatColor.GRAY +"��",ChatColor.GRAY + "���� ���� ��� ���� ����Ʈ : " + (configYaml.getInt("Server.Level_Up_StatPoint")*configYaml.getInt("Event.Multiple_Level_Up_StatPoint"))), 11, inv);}	}

		if(configYaml.getInt("Event.Multiple_EXP_Get") == 1)
		{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "����ġ �߰� ȹ��", 374,0,1,Arrays.asList(ChatColor.RED + "[�� Ȱ��ȭ]",ChatColor.GRAY + "����ġ ȹ��� : " + configYaml.getInt("Event.Multiple_EXP_Get")+"��"), 12, inv);}
		else
		{{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "����ġ �߰� ȹ��", 384,0,configYaml.getInt("Event.Multiple_EXP_Get"),Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY + "����ġ ȹ��� : "+ChatColor.YELLOW +""+configYaml.getInt("Event.Multiple_EXP_Get") +ChatColor.GRAY +"��"), 12, inv);}	}
		
		if(configYaml.getInt("Event.DropChance") == 1)
		{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��ӷ� ���", 265,0,1,Arrays.asList(ChatColor.RED + "[�� Ȱ��ȭ]",ChatColor.GRAY + "��ӷ� : " + configYaml.getInt("Event.DropChance")+"��"), 13, inv);}
		else
		{{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��ӷ� ���", 266,0,configYaml.getInt("Event.DropChance"),Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY + "��ӷ� : "+ChatColor.YELLOW +""+configYaml.getInt("Event.DropChance") +ChatColor.GRAY +"��"), 13, inv);}	}

		if(configYaml.getInt("Event.Multiple_Proficiency_Get") == 1)
		{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "���õ� ���", 145,0,1,Arrays.asList(ChatColor.RED + "[�� Ȱ��ȭ]",ChatColor.GRAY + "���õ� : " + configYaml.getInt("Event.Multiple_Proficiency_Get")+"��"), 14, inv);}
		else
		{{Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "���õ� ���", 145,2,configYaml.getInt("Event.Multiple_Proficiency_Get"),Arrays.asList(ChatColor.GREEN + "[Ȱ��ȭ]",ChatColor.GRAY + "���õ� : "+ChatColor.YELLOW +""+configYaml.getInt("Event.Multiple_Proficiency_Get") +ChatColor.GRAY +"��"), 14, inv);}	}
		
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��ü �ֱ�", 54,0,1,Arrays.asList(ChatColor.GRAY + "���� ������ ��� ��������",ChatColor.GRAY+"�������� �����մϴ�."), 28, inv);
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "���� �ֱ�", 130,0,1,Arrays.asList(ChatColor.GRAY + "���� ������ ������ ��,",ChatColor.GRAY+"�� ������Ը� ������",ChatColor.GRAY+"�������� �����մϴ�."), 30, inv);

		
		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "�۾� ������ �޴��� ���ư��ϴ�."), 36, inv);

		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		
		player.openInventory(inv);
	}

	public void AllPlayerGiveEventGUI(Player player, boolean All)
	{
		String UniqueCode = "��1��0��1��0��a��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0�̺�Ʈ ��ü ����");
		if(All==false)
			inv = Bukkit.createInventory(null, 45, "��1��0��1��0��b��r" + "��0�̺�Ʈ ���� ����");
		for(int count = 0; count <10;count++)
			Stack2(ChatColor.YELLOW+"[ ���� �� ������ ]", 160, 4, 1, null, count, inv);
		Stack2(ChatColor.YELLOW+"[ ���� �� ������ ]", 160, 4, 1, null, 17, inv);
		Stack2(ChatColor.YELLOW+"[ ���� �� ������ ]", 160, 4, 1, null, 18, inv);
		for(int count = 26; count <36;count++)
			Stack2(ChatColor.YELLOW+"[ ���� �� ������ ]", 160, 4, 1, null, count, inv);

		for(int count = 36; count <44;count++)
			Stack2(ChatColor.YELLOW+" ", 160, 8, 1, null, count, inv);

		if(All)
			Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "[���� ����!]", 54,0,1,Arrays.asList(ChatColor.GRAY + "��� �׵θ� ���� ��������",ChatColor.GRAY+"��� �÷��̾�� �����մϴ�!"), 40, inv);
		else
			Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "[���� ����!]", 130,0,1,Arrays.asList(ChatColor.GRAY + "��� �׵θ� ���� ��������",ChatColor.GRAY+"���� ������ �𸨴ϴ�!"), 40, inv);
			
		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "�̺�Ʈ �޴��� ���ư��ϴ�."), 36, inv);
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		player.openInventory(inv);
	}
	
	
	//���� GUIâ ���� �������� ������ ��, �ش� �����ܿ� ����� �ִ� �޼ҵ�1   -���� GUI, ���ǹڽ�, Ŀ���� ����GUI-//
	public void EventGUIInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		
		
		if(slot == 44)//�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 36)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player,(byte) 1);
			else if(slot == 28)//��ü �ֱ�
				AllPlayerGiveEventGUI(player,true);
			else if(slot == 30)//���� �ֱ�
				AllPlayerGiveEventGUI(player,false);
			else
			{
				UserData_Object u = new UserData_Object();
				if(slot == 10)
				{
					player.sendMessage(ChatColor.GREEN+"[�̺�Ʈ] : ��ų ����Ʈ ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "SKP");
				}
				else if(slot == 11)
				{
					player.sendMessage(ChatColor.GREEN+"[�̺�Ʈ] : ���� ����Ʈ ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "STP");
				}
				else if(slot == 12)
				{
					player.sendMessage(ChatColor.GREEN+"[�̺�Ʈ] : ����ġ ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "EXP");
				}
				else if(slot == 13)
				{
					player.sendMessage(ChatColor.GREEN+"[�̺�Ʈ] : ��ӷ� ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "DROP");
				}
				else if(slot == 14)
				{
					player.sendMessage(ChatColor.GREEN+"[�̺�Ʈ] : ���õ� ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "Proficiency");
				}
				player.sendMessage(ChatColor.YELLOW+"(1 ~ 10) (1�� ��� �̺�Ʈ ����)");
				u.setType(player, "Event");
				player.closeInventory();
			}
		}
		return;
	}

	public void AllPlayerGiveEventGUIclick(InventoryClickEvent event, String SubjectCode)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		

		if(event.getClickedInventory().getTitle().compareTo("container.inventory") != 0)
		{
			if((slot >=0 && slot <= 9)||slot == 17||slot == 18||slot >= 26)
				event.setCancelled(true);
			if(slot == 36)//���� ȭ��
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				EventGUI_Main(player);
			}
			else if(slot == 40)//���� ����
			{
				event.Main_Interact IT = new event.Main_Interact();
				if(SubjectCode.compareTo("0b")==0)//���� ����
				{
					boolean ItemExit = false;
	  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
	  		    	Player[] a = new Player[playerlist.size()];
	  		    	playerlist.toArray(a);
	  		    	short LuckyGuy = (short) new util.Util_Number().RandomNum(0, a.length-1);
					for(int count = 10; count < 17;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							ItemExit = true;
							ItemStack item = event.getInventory().getItem(count);
							new util.Util_Player().giveItemForce(a[LuckyGuy], item);
						}
					}
					for(int count = 19; count < 26;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							ItemExit = true;
							ItemStack item = event.getInventory().getItem(count);
							new util.Util_Player().giveItemForce(a[LuckyGuy], item);
						}
					}
					if(ItemExit)
					{
						SoundEffect.SP(a[LuckyGuy], Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.9F);
						Bukkit.broadcastMessage(ChatColor.YELLOW+"[�̺�Ʈ] : "+ChatColor.GOLD+""+ChatColor.BOLD+a[LuckyGuy].getName()+ChatColor.YELLOW+"�Բ��� ���� ������ ���޿� ��÷ �Ǽ̽��ϴ�!");
						EventGUI_Main(player);
					}
				}
				else
				{
					boolean ItemExit = false;
					for(int count = 10; count < 17;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							ItemExit = true;
							ItemStack item = event.getInventory().getItem(count);
			  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
			  		    	Player[] a = new Player[playerlist.size()];
			  		    	playerlist.toArray(a);
			  	  			for(int counter = 0; counter<a.length;counter++)
			  	  			{
								new util.Util_Player().giveItemForce(a[counter], item);
			  	  				if(item.hasItemMeta())
			  	  				{
			  	  					if(item.getItemMeta().hasDisplayName())
			  	  						a[counter].sendMessage(ChatColor.YELLOW+"[�̺�Ʈ] : "+item.getItemMeta().getDisplayName()+ChatColor.YELLOW+" �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				}
			  	  				else
		  	  						a[counter].sendMessage(ChatColor.YELLOW+"[�̺�Ʈ] : "+IT.SetItemDefaultName((short) item.getTypeId(), item.getData().getData())+ChatColor.YELLOW+" �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				SoundEffect.SP(a[counter], Sound.ENTITY_ITEM_PICKUP, 0.7F, 1.8F);
			  	  			}
						}
					}
					for(int count = 19; count < 26;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							ItemExit = true;
							ItemStack item = event.getInventory().getItem(count);
			  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
			  		    	Player[] a = new Player[playerlist.size()];
			  		    	playerlist.toArray(a);
			  	  			for(int counter = 0; counter<a.length;counter++)
			  	  			{
								new util.Util_Player().giveItemForce(a[counter], item);
			  	  				if(item.hasItemMeta())
			  	  				{
			  	  					if(item.getItemMeta().hasDisplayName())
			  	  						a[counter].sendMessage(ChatColor.YELLOW+"[�̺�Ʈ] : "+item.getItemMeta().getDisplayName()+ChatColor.YELLOW+" �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				}
			  	  				else
		  	  						a[counter].sendMessage(ChatColor.YELLOW+"[�̺�Ʈ] : "+IT.SetItemDefaultName((short) item.getTypeId(), item.getData().getData())+ChatColor.YELLOW+" �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				SoundEffect.SP(a[counter], Sound.ENTITY_ITEM_PICKUP, 0.7F, 1.8F);	
			  	  			}
						}
					}
					if(ItemExit)
						EventGUI_Main(player);
				}
			}
			else if(slot == 44)//������
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
		}
	}
}
