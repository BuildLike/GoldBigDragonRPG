package admin;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import effect.SoundEffect;
import user.UserDataObject;
import util.UtilGui;
import util.YamlLoader;



public class EventGui extends UtilGui
{
	public void eventGuiMain (Player player)
	{
	  	YamlLoader configYaml = new YamlLoader();
	  	configYaml.getConfig("config.yml");
		String uniqueCode = "��0��0��1��0��9��r";
		
		Inventory inv = Bukkit.createInventory(null, 45, uniqueCode + "��0�̺�Ʈ ����");
		
		if(configYaml.getInt("Event.Multiple_Level_Up_SkillPoint") == 1)
		{removeFlagStack("��f��l��ų ����Ʈ �߰� ȹ��", 340,0,1,Arrays.asList("��c[�� Ȱ��ȭ]","��7���� ���� ��� ��ų ����Ʈ : " + configYaml.getInt("Server.Level_Up_SkillPoint")), 10, inv);}
		else
		{removeFlagStack("��f��l��ų ����Ʈ �߰� ȹ��", 403,0,configYaml.getInt("Event.Multiple_Level_Up_SkillPoint"),Arrays.asList("��a[Ȱ��ȭ]","��e"+configYaml.getInt("Event.Multiple_Level_Up_SkillPoint") +"��7��","��7���� ���� ��� ��ų ����Ʈ : " + (configYaml.getInt("Server.Level_Up_SkillPoint") * configYaml.getInt("Event.Multiple_Level_Up_SkillPoint"))), 10, inv);}	

		if(configYaml.getInt("Event.Multiple_Level_Up_StatPoint") == 1)
		{removeFlagStack("��f��l���� ����Ʈ �߰� ȹ��", 351,15,1,Arrays.asList("��c[�� Ȱ��ȭ]","��7���� ���� ��� ���� ����Ʈ : " + configYaml.getInt("Server.Level_Up_StatPoint")), 11, inv);}
		else
		{removeFlagStack("��f��l���� ����Ʈ �߰� ȹ��", 399,0,configYaml.getInt("Event.Multiple_Level_Up_StatPoint"),Arrays.asList("��a[Ȱ��ȭ]","��e"+configYaml.getInt("Event.Multiple_Level_Up_StatPoint") +"��7��","��7���� ���� ��� ���� ����Ʈ : " + (configYaml.getInt("Server.Level_Up_StatPoint")*configYaml.getInt("Event.Multiple_Level_Up_StatPoint"))), 11, inv);}	

		if(configYaml.getInt("Event.Multiple_EXP_Get") == 1)
		{removeFlagStack("��f��l����ġ �߰� ȹ��", 374,0,1,Arrays.asList("��c[�� Ȱ��ȭ]","��7����ġ ȹ��� : " + configYaml.getInt("Event.Multiple_EXP_Get")+"��"), 12, inv);}
		else
		{removeFlagStack("��f��l����ġ �߰� ȹ��", 384,0,configYaml.getInt("Event.Multiple_EXP_Get"),Arrays.asList("��a[Ȱ��ȭ]","��7����ġ ȹ��� : ��e"+configYaml.getInt("Event.Multiple_EXP_Get") +"��7��"), 12, inv);}	
		
		if(configYaml.getInt("Event.DropChance") == 1)
		{removeFlagStack("��f��l��ӷ� ���", 265,0,1,Arrays.asList("��c[�� Ȱ��ȭ]","��7��ӷ� : " + configYaml.getInt("Event.DropChance")+"��"), 13, inv);}
		else
		{removeFlagStack("��f��l��ӷ� ���", 266,0,configYaml.getInt("Event.DropChance"),Arrays.asList("��a[Ȱ��ȭ]","��7��ӷ� : ��e"+configYaml.getInt("Event.DropChance") +"��7��"), 13, inv);}	

		if(configYaml.getInt("Event.Multiple_Proficiency_Get") == 1)
		{removeFlagStack("��f��l���õ� ���", 145,0,1,Arrays.asList("��c[�� Ȱ��ȭ]","��7���õ� : " + configYaml.getInt("Event.Multiple_Proficiency_Get")+"��"), 14, inv);}
		else
		{removeFlagStack("��f��l���õ� ���", 145,2,configYaml.getInt("Event.Multiple_Proficiency_Get"),Arrays.asList("��a[Ȱ��ȭ]","��7���õ� : ��e"+configYaml.getInt("Event.Multiple_Proficiency_Get") +"��7��"), 14, inv);}	
		
		removeFlagStack("��f��l��ü �ֱ�", 54,0,1,Arrays.asList("��7���� ������ ��� ��������","��7�������� �����մϴ�."), 28, inv);
		removeFlagStack("��f��l���� �ֱ�", 130,0,1,Arrays.asList("��7���� ������ ������ ��,","��7�� ������Ը� ������","��7�������� �����մϴ�."), 30, inv);

		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7�۾� ������ �޴��� ���ư��ϴ�."), 36, inv);

		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		
		player.openInventory(inv);
	}

	public void allPlayerGiveEventGui(Player player, boolean isGiveToAll)
	{
		String uniqueCode = "��1��0��1��0��a��r";
		Inventory inv = Bukkit.createInventory(null, 45, uniqueCode + "��0�̺�Ʈ ��ü ����");
		if(!isGiveToAll)
			inv = Bukkit.createInventory(null, 45, "��1��0��1��0��b��r��0�̺�Ʈ ���� ����");
		for(int count = 0; count <10;count++)
			removeFlagStack("��e[ ���� �� ������ ]", 160, 4, 1, null, count, inv);
		removeFlagStack("��e[ ���� �� ������ ]", 160, 4, 1, null, 17, inv);
		removeFlagStack("��e[ ���� �� ������ ]", 160, 4, 1, null, 18, inv);
		for(int count = 26; count <36;count++)
			removeFlagStack("��e[ ���� �� ������ ]", 160, 4, 1, null, count, inv);

		for(int count = 36; count <44;count++)
			removeFlagStack("��e ", 160, 8, 1, null, count, inv);

		if(isGiveToAll)
			removeFlagStack("��f��l[���� ����!]", 54,0,1,Arrays.asList("��7��� �׵θ� ���� ��������","��7��� �÷��̾�� �����մϴ�!"), 40, inv);
		else
			removeFlagStack("��f��l[���� ����!]", 130,0,1,Arrays.asList("��7��� �׵θ� ���� ��������","��7���� ������ �𸨴ϴ�!"), 40, inv);
			
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7�̺�Ʈ �޴��� ���ư��ϴ�."), 36, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		player.openInventory(inv);
	}
	
	
	//���� GUIâ ���� �������� ������ ��, �ش� �����ܿ� ����� �ִ� �޼ҵ�1   -���� GUI, ���ǹڽ�, Ŀ���� ����GUI-//
	public void eventGuiInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 44)//�ݱ�
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 36)//���� ���
				new OPboxGui().opBoxGuiMain(player,(byte) 1);
			else if(slot == 28)//��ü �ֱ�
				allPlayerGiveEventGui(player,true);
			else if(slot == 30)//���� �ֱ�
				allPlayerGiveEventGui(player,false);
			else
			{
				UserDataObject u = new UserDataObject();
				if(slot == 10)
				{
					player.sendMessage("��a[�̺�Ʈ] : ��ų ����Ʈ ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "SKP");
				}
				else if(slot == 11)
				{
					player.sendMessage("��a[�̺�Ʈ] : ���� ����Ʈ ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "STP");
				}
				else if(slot == 12)
				{
					player.sendMessage("��a[�̺�Ʈ] : ����ġ ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "EXP");
				}
				else if(slot == 13)
				{
					player.sendMessage("��a[�̺�Ʈ] : ��ӷ� ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "DROP");
				}
				else if(slot == 14)
				{
					player.sendMessage("��a[�̺�Ʈ] : ���õ� ��·��� �� ��� �ϽǷ���?");
					u.setString(player, (byte)1, "Proficiency");
				}
				player.sendMessage("��e(1 ~ 10) (1�� ��� �̺�Ʈ ����)");
				u.setType(player, "Event");
				player.closeInventory();
			}
		}
		return;
	}

	public void allPlayerGiveEventGuiClick(InventoryClickEvent event, String subjectCode)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		if(!event.getClickedInventory().getTitle().equals("container.inventory"))
		{
			if((slot >=0 && slot <= 9)||slot == 17||slot == 18||slot >= 26)
				event.setCancelled(true);
			if(slot == 36)//���� ȭ��
			{
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				eventGuiMain(player);
			}
			else if(slot == 40)//���� ����
			{
				event.EventInteract interact = new event.EventInteract();
				if(subjectCode.equals("0b"))//���� ����
				{
					boolean itemExit = false;
	  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
	  		    	Player[] a = new Player[playerlist.size()];
	  		    	playerlist.toArray(a);
	  		    	int luckyGuy = new util.UtilNumber().RandomNum(0, a.length-1);
					for(int count = 10; count < 17;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							itemExit = true;
							ItemStack item = event.getInventory().getItem(count);
							new util.UtilPlayer().giveItemForce(a[luckyGuy], item);
						}
					}
					for(int count = 19; count < 26;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							itemExit = true;
							ItemStack item = event.getInventory().getItem(count);
							new util.UtilPlayer().giveItemForce(a[luckyGuy], item);
						}
					}
					if(itemExit)
					{
						SoundEffect.playSound(a[luckyGuy], Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.9F);
						Bukkit.broadcastMessage("��e[�̺�Ʈ] : ��6��l"+a[luckyGuy].getName()+"��e�Բ��� ���� ������ ���޿� ��÷ �Ǽ̽��ϴ�!");
						eventGuiMain(player);
					}
				}
				else
				{
					boolean itemExit = false;
					for(int count = 10; count < 17;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							itemExit = true;
							ItemStack item = event.getInventory().getItem(count);
			  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
			  		    	Player[] a = new Player[playerlist.size()];
			  		    	playerlist.toArray(a);
			  	  			for(int counter = 0; counter<a.length;counter++)
			  	  			{
								new util.UtilPlayer().giveItemForce(a[counter], item);
			  	  				if(item.hasItemMeta())
			  	  				{
			  	  					if(item.getItemMeta().hasDisplayName())
			  	  						a[counter].sendMessage("��e[�̺�Ʈ] : "+item.getItemMeta().getDisplayName()+"��e �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				}
			  	  				else
		  	  						a[counter].sendMessage("��e[�̺�Ʈ] : "+interact.setItemDefaultName(item.getTypeId(), item.getData().getData())+"��e �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				SoundEffect.playSound(a[counter], Sound.ENTITY_ITEM_PICKUP, 0.7F, 1.8F);
			  	  			}
						}
					}
					for(int count = 19; count < 26;count++)
					{
						if(event.getInventory().getItem(count) != null)
						{
							itemExit = true;
							ItemStack item = event.getInventory().getItem(count);
			  		    	Collection<? extends Player> playerlist = Bukkit.getServer().getOnlinePlayers();
			  		    	Player[] a = new Player[playerlist.size()];
			  		    	playerlist.toArray(a);
			  	  			for(int counter = 0; counter<a.length;counter++)
			  	  			{
								new util.UtilPlayer().giveItemForce(a[counter], item);
			  	  				if(item.hasItemMeta())
			  	  				{
			  	  					if(item.getItemMeta().hasDisplayName())
			  	  						a[counter].sendMessage("��e[�̺�Ʈ] : "+item.getItemMeta().getDisplayName()+"��e �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				}
			  	  				else
		  	  						a[counter].sendMessage("��e[�̺�Ʈ] : "+interact.setItemDefaultName(item.getTypeId(), item.getData().getData())+"��e �������� "+item.getAmount()+"�� ���� �޾ҽ��ϴ�!");
			  	  				SoundEffect.playSound(a[counter], Sound.ENTITY_ITEM_PICKUP, 0.7F, 1.8F);	
			  	  			}
						}
					}
					if(itemExit)
						eventGuiMain(player);
				}
			}
			else if(slot == 44)//������
			{
				SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
		}
	}
}
