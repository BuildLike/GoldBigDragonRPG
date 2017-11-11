package admin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import effect.SoundEffect;
import user.UserData_Object;
import util.Util_GUI;
import util.YamlLoader;



public class Gamble_GUI extends Util_GUI
{
	public void gambleMainGui(Player player)
	{
		String uniqueCode = "��0��0��1��0��c��r";
		Inventory inv = Bukkit.createInventory(null, 45, uniqueCode + "��0���� ����");

		Stack2("��f��ǰ ����", 54,0,1,Arrays.asList("��7��ǰ ��Ű���� ����ų�","��7����/Ȯ�� �մϴ�."), 10, inv);
		
		Stack2("��f���� ����", 137,0,1,Arrays.asList("��7���� ���ӿ� ���� ������ �մϴ�."), 12, inv);
		Stack2("��f���� ���", 137,0,1,Arrays.asList("��7���� ��Ͽ� ���� ������ �մϴ�.", "��c��l[������Ʈ�� �ʿ��մϴ�!]"), 14, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7�۾� ������ �޴��� ���ư��ϴ�."), 36, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		player.openInventory(inv);
	}
	
	public void gambleMainGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 44)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 36)//���� ���
				new OPbox_GUI().opBoxGuiMain(player, (byte) 3);
			else if(slot == 10)//��ǰ ����
				gamblePresentGui(player, (short)0, (byte)0, (short)-1, null);
			else if(slot == 12)//���� �ӽ�
				slotMachineMainGui(player,0);
		}
	}
	
	
	public void gamblePresentGui(Player player, short page, byte isChoose, short detailChoose, String deDetailChoose)
	{
		String uniqueCode = "��0��0��1��0��d��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��ǰ ��� : "+(page+1));

	  	YamlLoader presentList = new YamlLoader();
	  	presentList.getConfig("Item/GamblePresent.yml");
		
		String[] itemList = presentList.getConfigurationSection("").getKeys(false).toArray(new String[0]);
		
		int loc = 0;
		int presentAmount = 0;
		String packageName = null;
		String grade = null;
		for(int count = page*45; count < itemList.length;count++)
		{
			packageName = itemList[count];
			grade = presentList.getString(itemList[count]+".Grade");
			presentAmount = presentList.getConfigurationSection(itemList[count]+".Present").getKeys(false).size();
			if(count > itemList.length || loc >= 45) break;
			Stack2("��e��l"+packageName, 54,0,1,Arrays.asList("��9��l��� : "+grade, "��9��l��ϵ� ������ �� : ��f"+ presentAmount+"��","","��e[�� Ŭ���� ��Ű�� Ȯ��]","��c[Shift + �� Ŭ���� ����]",""), loc, inv);
			loc++;
		}
		
		if(itemList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		if(isChoose==1)
			Stack2("��c��l��", 166,0,1,Arrays.asList("��7�ƹ��͵� ���� �ʽ��ϴ�.","��0"+deDetailChoose), 49, inv);
		else
			Stack2("��f��l�� ��ǰ", 130,0,1,Arrays.asList("��7���ο� ��ǰ�� �����մϴ�.","��0"+deDetailChoose), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+isChoose), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+detailChoose), 53, inv);
		player.openInventory(inv);
	}
	
	public void gamblePresentGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);

			short page =  (short) (Short.parseShort(ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]))-1);
			byte isChoose = Byte.parseByte(ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1)));
			short DetailChoose = Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			String DeDetailChoose = ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(1));
			
			if(slot == 45)//���� ���
			{
				if(isChoose==0)
					gambleMainGui(player);
				else if(isChoose==1)//���� �ӽ��� ��ǰ ����
					slotMachineDetailGUI(player, DeDetailChoose);
			}
			else if(slot == 48)//���� ������
				gamblePresentGui(player, (short) (page-1),isChoose,DetailChoose,DeDetailChoose);
			else if(slot == 49)//�� ��Ű��
			{
				if(isChoose==1)//���� �ӽ��� ��ǰ ����
				{
				  	YamlLoader gambleYaml = new YamlLoader();
				  	gambleYaml.getConfig("ETC/SlotMachine.yml");
					gambleYaml.set(DeDetailChoose+"."+DetailChoose, "null");
					gambleYaml.saveConfig();
					slotMachineDetailGUI(player, DeDetailChoose);
					return;
				}
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Gamble");
				u.setString(player, (byte)0, "NP");
				player.sendMessage("��a[����] : ��ǰ �̸��� ������ �ּ���!");
			}
			else if(slot == 50)//���� ������
				gamblePresentGui(player, (short) (page+1),isChoose,DetailChoose,DeDetailChoose);
			else
			{
				if(isChoose==0)
				{
					if(event.isLeftClick() == true&&event.isShiftClick()==false)
						gambleDetailViewPackageGui(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					else if(event.isRightClick()==true&&event.isShiftClick()==true)
					{
					  	YamlLoader presentYaml = new YamlLoader();
					  	presentYaml.getConfig("Item/GamblePresent.yml");
						presentYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						presentYaml.saveConfig();
						gamblePresentGui(player, page,isChoose,DetailChoose,DeDetailChoose);
					}
				}
				else if(isChoose==1)//���� �ӽ��� ��ǰ ����
				{
				  	YamlLoader gambleYaml = new YamlLoader();
				  	gambleYaml.getConfig("ETC/SlotMachine.yml");
					gambleYaml.set(DeDetailChoose+"."+DetailChoose, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					gambleYaml.saveConfig();
					slotMachineDetailGUI(player, DeDetailChoose);
				}
			}
		}
	}
	
	public void gambleDetailViewPackageGui(Player player, String packageName)
	{
		String uniqueCode = "��1��0��1��0��e��r";
		Inventory inv = Bukkit.createInventory(null, 36, uniqueCode + "��0���� ��ǰ ����");

	  	YamlLoader presentYaml = new YamlLoader();
	  	presentYaml.getConfig("Item/GamblePresent.yml");
		
		byte loc=0;
		for(int count = 0; count < 27;count++)
		{
			ItemStack item = presentYaml.getItemStack(packageName+".Present."+count);
			if(item!=null)
			{
				ItemStackStack(item, loc, inv);
				loc++;
			}
		}

		Stack("��f��l���� [��ǰ �ֱ�] ����", 160,8,1,null, 28, inv);
		Stack("��f��l���� [��ǰ �ֱ�] ����", 160,8,1,null, 29, inv);
		Stack("��f��l���� [��ǰ �ֱ�] ����", 160,8,1,null, 30, inv);
		String Grade = presentYaml.getString(packageName+".Grade");
		Stack2("��3[    ��� ����    ]", 266,0,1,Arrays.asList("��f��Ű���� �����","��f�����մϴ�.","","��f[    ���� ���    ]","       "+Grade,""), 31, inv);
		Stack("��f��l���� [��ǰ �ֱ�] ����", 160,8,1,null, 32, inv);
		Stack("��f��l���� [��ǰ �ֱ�] ����", 160,8,1,null, 33, inv);
		Stack("��f��l���� [��ǰ �ֱ�] ����", 160,8,1,null, 34, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+packageName), 27, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 35, inv);
		player.openInventory(inv);
	}

	public void gambleDetailViewPackageGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot >= 27)
			event.setCancelled(true);
		if(slot == 35)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 27)//���� ���
				gamblePresentGui(player,(byte)0,(byte)0,(short)0,null);
			else if(slot == 31)//��� ����
			{
			  	YamlLoader presentYaml = new YamlLoader();
			  	presentYaml.getConfig("Item/GamblePresent.yml");
				String packageName = ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
				String grade = presentYaml.getString(packageName+".Grade");
				String maximumGrade = "��4��l[��6��l�ʡ�2��l����1��l]";
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(grade.equals("��f[�Ϲ�]"))
					presentYaml.set(packageName+".Grade","��a[���]");
				else if(grade.equals("��a[���]"))
					presentYaml.set(packageName+".Grade","��9[����]");
				else if(grade.equals("��9[����]"))
					presentYaml.set(packageName+".Grade","��e[����]");
				else if(grade.equals("��e[����]"))
					presentYaml.set(packageName+".Grade","��5[����]");
				else if(grade.equals("��5[����]"))
					presentYaml.set(packageName+".Grade","��6[����]");
				else if(grade.equals("��6[����]"))
					presentYaml.set(packageName+".Grade",maximumGrade);
				else if(grade.equals(maximumGrade))
					presentYaml.set(packageName+".Grade","��7[�ϱ�]");
				else if(grade.equals("��7[�ϱ�]"))
					presentYaml.set(packageName+".Grade","��f[�Ϲ�]");
				else
					presentYaml.set(packageName+".Grade","��f[�Ϲ�]");
				presentYaml.saveConfig();
				grade = presentYaml.getString(packageName+".Grade");
				ItemStack item[] = new ItemStack[27];
				int itemcount=0;
				for(int count=0; count < 27; count++)
				{
					if(event.getInventory().getItem(count)!=null)
					{
						item[itemcount] = event.getInventory().getItem(count);
						itemcount++;
					}
				}
				presentYaml.removeKey(packageName+".Present");
				presentYaml.saveConfig();
				for(int count=0; count<itemcount;count++)
					presentYaml.set(packageName+".Present."+count,item[count]);
				presentYaml.saveConfig();
				gambleDetailViewPackageGui(player, packageName);
			}
		}
	}
	
	public void gambleDetailViewPackageGuiClose(InventoryCloseEvent event)
	{
		String packageName = ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
	  	YamlLoader presentYaml = new YamlLoader();
	  	presentYaml.getConfig("Item/GamblePresent.yml");
		ItemStack item[] = new ItemStack[27];
		byte itemcount=0;
		for(int count=0; count < 27; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				item[itemcount] = event.getInventory().getItem(count);
				itemcount++;
			}
		}
		presentYaml.removeKey(packageName+".Present");
		presentYaml.createSection(packageName+".Present");
		presentYaml.saveConfig();
		for(int count=0; count<itemcount;count++)
			presentYaml.set(packageName+".Present."+count,item[count]);
		presentYaml.saveConfig();
	}
	
	
	public void slotMachineMainGui(Player player, int page)
	{
		String uniqueCode = "��0��0��1��0��f��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0���� ��� ��� : "+(page+1));

	  	YamlLoader slotmachineYaml = new YamlLoader();
	  	slotmachineYaml.getConfig("ETC/SlotMachine.yml");
		
		String[] slotMachineList = slotmachineYaml.getConfigurationSection("").getKeys(false).toArray(new String[0]);
		//����_��ǥ
		byte loc=0;
		String location = null;
		String world = null;
		for(int count = page*45; count < slotMachineList.length;count++)
		{
			location = slotMachineList[count].split("_")[1];
			world = slotMachineList[count].split("_")[0];
			if(count > slotMachineList.length || loc >= 45) break;

			Stack2("��0��l"+slotMachineList[count].toString(), 137,0,1,Arrays.asList("��9��l���� : ��f��l"+world, "��9��l��ǥ : ��f��l"+location,"","��e[�� Ŭ���� ��� ����]","��c[Shift + �� Ŭ���� ����]",""), loc, inv);
			
			loc++;
		}
		
		if(slotMachineList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l�� ���", 130,0,1,Arrays.asList("��7���ο� ��踦 ��ġ�մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}	

	public void slotMachineMainGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//�������
				gambleMainGui(player);
			else if(slot == 48)//���� ������
				slotMachineMainGui(player, page-1);
			else if(slot == 49)//�� ���� �ӽ�
			{
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Gamble");
				u.setString(player, (byte)0, "NSM");
				player.sendMessage("��a[����] : ���� �ӽ��� ��� �� ����� ��Ŭ�� �� �ּ���!");
			}
			else if(slot == 50)//���� ������
				slotMachineMainGui(player, page+1);
			else
			{
				if(event.isLeftClick() == true&&event.isShiftClick()==false)
					slotMachineDetailGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isRightClick()==true&&event.isShiftClick()==true)
				{
				  	YamlLoader presentYaml = new YamlLoader();
				  	presentYaml.getConfig("ETC/SlotMachine.yml");
					presentYaml.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					presentYaml.saveConfig();
					slotMachineMainGui(player, page);
				}
			}
		}
	}
	
	
	public void slotMachineDetailGUI(Player player, String machineNumber)
	{
		String uniqueCode = "��0��0��1��1��0��r";
		Inventory inv = Bukkit.createInventory(null, 36, uniqueCode + "��0���� ��� ���� ");

	  	YamlLoader gambleYaml = new YamlLoader();
	  	gambleYaml.getConfig("ETC/SlotMachine.yml");
		
		String setter = gambleYaml.getString(machineNumber+".0");
		if(setter.equals("null"))setter="����";
		Stack2("��7��l[��� �ٸ� ���]", 351,15,1,Arrays.asList("","��f�� ��e�� ��3��","��7���� �ӽ��� ���� �����","��7��� �ٸ� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 0, inv);
		setter = gambleYaml.getString(machineNumber+".1");
		if(setter.equals("null"))setter="����";
		Stack2("��8��l[Ʈ���� �ھ�]", 263,0,3,Arrays.asList("","��8�� �� ��","��7���� �ӽ��� ���� �����","��7��� ��ź�� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 1, inv);
		setter = gambleYaml.getString(machineNumber+".2");
		if(setter.equals("null"))setter="����";
		Stack2("��f��l[Ʈ���� ���̾�]", 265,0,3,Arrays.asList("","��f�� �� ��","��7���� �ӽ��� ���� �����","��7��� ö���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 2, inv);
		setter = gambleYaml.getString(machineNumber+".3");
		if(setter.equals("null"))setter="����";
		Stack2("��e��l[Ʈ���� ���]", 266,0,3,Arrays.asList("","��e�� �� ��","��7���� �ӽ��� ���� �����","��7��� �ݱ��� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 3, inv);
		setter = gambleYaml.getString(machineNumber+".4");
		if(setter.equals("null"))setter="����";
		Stack2("��9��l[Ʈ���� ���̾Ƹ��]", 264,0,3,Arrays.asList("","��3�� �� ��","��7���� �ӽ��� ���� �����","��7��� ���̾��� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 4, inv);
		setter = gambleYaml.getString(machineNumber+".5");
		if(setter.equals("null"))setter="����";
		Stack2("��a��l[Ʈ���� ���޶���]", 388,0,3,Arrays.asList("","��a�� �� ��","��7���� �ӽ��� ���� �����","��7��� ���޶����� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 5, inv);
		setter = gambleYaml.getString(machineNumber+".6");
		if(setter.equals("null"))setter="����";
		Stack2("��9��l[Ʈ���� �״� ��Ÿ]", 399,0,3,Arrays.asList("","��9�� �� ��","��7���� �ӽ��� ���� �����","��7��� �״� ���� ����Դϴ�.","��7��÷�� ��ǰ�� ��÷�ڸ�","��7���� ��ü�� �˷� �ݴϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 6, inv);
		setter = gambleYaml.getString(machineNumber+".9");
		if(setter.equals("null"))setter="����";
		Stack2("��9��l[�̱� �״� ��Ÿ]", 399,0,1,Arrays.asList("","��9�� ��8�� ��","��7���� �ӽ��� ���� ���","��7�״� ���� 1�� ���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 9, inv);
		setter = gambleYaml.getString(machineNumber+".10");
		if(setter.equals("null"))setter="����";
		Stack2("��8��l[���� �ھ�]", 263,0,2,Arrays.asList("","��8�� �� ��8��","��7���� �ӽ��� ���� ���","��7��ź�� 2���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 10, inv);
		setter = gambleYaml.getString(machineNumber+".11");
		if(setter.equals("null"))setter="����";
		Stack2("��f��l[���� ���̾�]", 265,0,2,Arrays.asList("","��f�� �� ��8��","��7���� �ӽ��� ���� ���","��7ö���� 2���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 11, inv);
		setter = gambleYaml.getString(machineNumber+".12");
		if(setter.equals("null"))setter="����";
		Stack2("��e��l[���� ���]", 266,0,2,Arrays.asList("","��e�� �� ��8��","��7���� �ӽ��� ���� ���","��7�ݱ��� 2���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 12, inv);
		setter = gambleYaml.getString(machineNumber+".13");
		if(setter.equals("null"))setter="����";
		Stack2("��9��l[���� ���̾Ƹ��]", 264,0,2,Arrays.asList("","��3�� �� ��8��","��7���� �ӽ��� ���� ���","��7��� ���̾ư� 2���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 13, inv);
		setter = gambleYaml.getString(machineNumber+".14");
		if(setter.equals("null"))setter="����";
		Stack2("��a��l[���� ���޶���]", 388,0,2,Arrays.asList("","��a�� �� ��8��","��7���� �ӽ��� ���� ���","��7���޶��尡 2���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 14, inv);
		setter = gambleYaml.getString(machineNumber+".15");
		if(setter.equals("null"))setter="����";
		Stack2("��9��l[���� �״� ��Ÿ]", 399,0,2,Arrays.asList("","��9�� �� ��8��","��7���� �ӽ��� ���� ���","��7�״� ���� 2���� ����Դϴ�.","","��e[�� Ŭ���� ��ǰ ����]","","��7[���� ��ǰ]","��8"+setter), 15, inv);

		Stack2("��2��l[���� ���� ����]", 341,0,1,Arrays.asList("","��7���� �ӽ� 1ȸ �̿��� ����","��7�ʿ��� ������ �����մϴ�.","","��e[�� Ŭ���� ���� ����]",""), 8, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+machineNumber), 27, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 35, inv);
		player.openInventory(inv);
	}
	
	public void slotMachineDetailGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		String machineNumber =  ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
		int slot = event.getSlot();

		if(slot == 35)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot <= 6 || (slot >= 9 && slot <= 15))//�� Ȯ���� ���� ����
				gamblePresentGui(player, (byte)0, (byte)1, (short)event.getSlot(),machineNumber);
			else if(slot == 8)//���� ����
				slotMachineCoinGui(player, machineNumber);
			else if(slot == 27)//���� ���
				slotMachineMainGui(player,0);
		}
	}

	
	public void slotMachineCoinGui(Player player, String machineNumber)
	{
		String uniqueCode = "��1��0��1��1��1��r";
		Inventory inv = Bukkit.createInventory(null, 9, uniqueCode + "��0���� ��� ����");

	  	YamlLoader gambleYaml = new YamlLoader();
	  	gambleYaml.getConfig("ETC/SlotMachine.yml");
		
		if(gambleYaml.contains(machineNumber+".8"))
			ItemStackStack(gambleYaml.getItemStack(machineNumber+".8"), 4, inv);
			
		Stack("��d[���� �ֱ�]����", 166,0,1,null, 1, inv);
		Stack("��d[���� �ֱ�]����", 166,0,1,null, 2, inv);
		Stack("��d[���� �ֱ�]����", 166,0,1,null, 3, inv);
		Stack("��d����[���� �ֱ�]", 166,0,1,null, 5, inv);
		Stack("��d����[���� �ֱ�]", 166,0,1,null, 6, inv);
		Stack("��d����[���� �ֱ�]", 166,0,1,null, 7, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+machineNumber), 0, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void slotMachineCoinGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String machineNumber = ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(1));

		if(!event.getClickedInventory().getTitle().equals("container.inventory"))
		{
			if(slot!=4)
				event.setCancelled(true);
			if(slot == 0)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				slotMachineDetailGUI(player, machineNumber);
			}
			else if(slot == 8)
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
		}
	}

	public void slotMachineCoinGuiClose(InventoryCloseEvent event)
	{
		String machineNumber = ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(1));

	  	YamlLoader gambleYaml = new YamlLoader();
	  	gambleYaml.getConfig("ETC/SlotMachine.yml");
		
		if(event.getInventory().getItem(4)!=null)
			gambleYaml.set(machineNumber+".8", event.getInventory().getItem(4));
		else
			gambleYaml.set(machineNumber+".8", null);
		gambleYaml.saveConfig();
	}

	
	public void slotMachinePlayGui(Player player, String machineNumber)
	{
		String uniqueCode = "��0��0��1��1��2��r";
		Inventory inv = Bukkit.createInventory(null, 27, uniqueCode + "��0���� �ӽ�");

	  	YamlLoader gambleYaml = new YamlLoader();
	  	gambleYaml.getConfig("ETC/SlotMachine.yml");
		
		if(gambleYaml.contains(machineNumber+".8"))
			ItemStackStack(gambleYaml.getItemStack(machineNumber+".8"), 16, inv);
		else
			Stack2("��c��l[��� ���� ��]", 166,0,1,Arrays.asList("","��7���� �ӽ� �̿뿡","��7������ ���ĵ��","��7����� �˼��մϴ�."), 16, inv);
		Stack2("��e��l[����� �����̽ÿ�!]", 76,0,1,Arrays.asList("","��7���� �ӽſ� ������ �ְ�","��7������ �����ϴ�!","","��a[���� �������� �����Դϴ�.]"), 15, inv);

		for(int count=0; count<5; count++)
			Stack2("��e��l ", 160,4,1,Arrays.asList(""), count, inv);
		for(int count=5; count<9; count++)
			Stack2("��e��l ", 160,11,1,Arrays.asList(""), count, inv);

		Stack2("��e��l ", 160,4,1,Arrays.asList(""), 9, inv);
		Stack2("��e��l ", 160,4,1,Arrays.asList(""), 13, inv);
		Stack2("��e��l ", 160,11,1,Arrays.asList(""), 14, inv);
		Stack2("��e��l ", 160,11,1,Arrays.asList("��0"+machineNumber), 17, inv);
		
		for(int count=18; count<23; count++)
			Stack2("��e��l ", 160,4,1,Arrays.asList(""), count, inv);
		for(int count=23; count<27; count++)
			Stack2("��e��l ", 160,11,1,Arrays.asList(""), count, inv);
		
		for(int count=1;count<4;count++)
		{
			byte randomnum = (byte) new util.Util_Number().RandomNum(0, 5);
			short itemID=263;
			if(randomnum == 0)
				itemID = 263;
			else if(randomnum == 1)
				itemID = 265;
			else if(randomnum == 2)
				itemID = 266;
			else if(randomnum == 3)
				itemID = 264;
			else if(randomnum == 4)
				itemID = 388;
			else if(randomnum == 5)
				itemID = 399;
			Stack2("��e��l"+count+" ��° ����", itemID,0,1,Arrays.asList(""), count+9, inv);
			
		}
		player.openInventory(inv);
	}

	public void slotMachinePlayGuiClick(InventoryClickEvent event)
	{
		if(event.getSlot()==15)
		{
			ItemStack coin = event.getInventory().getItem(16);
			Player player = (Player) event.getWhoClicked();
			if(event.getCurrentItem().getTypeId()==69)
				return;
			ItemStack coinItem = null;
			for(int countta=0;countta < player.getInventory().getSize(); countta++)
			{
				coinItem = player.getInventory().getItem(countta);
				if(coinItem!=null&&coinItem.isSimilar(coin)&&coinItem.getAmount()>=coin.getAmount())
				{
					if(coinItem.getAmount()==coin.getAmount())
						player.getInventory().setItem(countta, null);
					else
					{
						coinItem.setAmount(coinItem.getAmount()-coin.getAmount());
						player.getInventory().setItem(countta, coinItem);
					}
					player.updateInventory();
					if(event.getInventory().getItem(16).getTypeId()==166 && event.getInventory().getItem(16).hasItemMeta()==true)
						if(event.getInventory().getItem(16).getItemMeta().getDisplayName().equals("��c��l[��� ���� ��]"))
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[���� �ӽ�] : ���� ���� ���� ���Դϴ�! �����ڿ��� �����ϼ���!");
							return;
						}
					
					if(servertick.ServerTick_Main.PlayerTaskList.containsKey(player.getName())==true)
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[���� �ӽ�] : �̹� ������� �۾��� �ֽ��ϴ�! ������ �� ���� ������!");
						return;
					}
					
					SoundEffect.SP(player, Sound.BLOCK_CHEST_CLOSE, 1.0F, 0.5F);
					SoundEffect.SP(player, Sound.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
					
					ItemStack icon = new MaterialData(69, (byte) 0).toItemStack(1);
					ItemMeta iconMeta = icon.getItemMeta();
					iconMeta.setDisplayName("��c��l[������ ���� �� ����!]");
					iconMeta.setLore(Arrays.asList("","��7����� ��ٸ�����!"));
					icon.setItemMeta(iconMeta);
					
					event.getInventory().setItem(15, icon);
					short itemId[] = new short[3];
					for(int count=0;count<3;count++)
					{
						int randomnum = (byte) new util.Util_Number().RandomNum(0, 5);
						itemId[count]=263;
						if(randomnum == 0)
							itemId[count] = 263;
						else if(randomnum == 1)
							itemId[count] = 265;
						else if(randomnum == 2)
							itemId[count] = 266;
						else if(randomnum == 3)
							itemId[count] = 264;
						else if(randomnum == 4)
							itemId[count] = 388;
						else if(randomnum == 5)
							itemId[count] = 399;
					}
					Long utc = servertick.ServerTick_Main.nowUTC+5;
					for(;;)
					{
						if(servertick.ServerTick_Main.Schedule.containsKey(utc))
							utc=utc+1;
						else
							break;
					}
					
					servertick.ServerTick_Object tickObject = new servertick.ServerTick_Object(utc, "G_SM");
					tickObject.setString((byte)0, player.getName());
					tickObject.setString((byte)1, ChatColor.stripColor(event.getInventory().getItem(17).getItemMeta().getLore().get(0)));

					tickObject.setInt((byte)0, itemId[0]);
					tickObject.setInt((byte)1, itemId[1]);
					tickObject.setInt((byte)2, itemId[2]);
					tickObject.setMaxCount(20);
					servertick.ServerTick_Main.Schedule.put(utc, tickObject);
					servertick.ServerTick_Main.PlayerTaskList.put(player.getName(), "G_SM");
					return;
				}
			}
			SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
			player.sendMessage("��c[���� �ӽ�] : ������ �����մϴ�!");
			return;
		}
	}

	public void slotMachineRollingGui(String player, short[] itemID, boolean fin, String machineNumber)
	{
		String uniqueCode = "��1��0��1��1��3��r";
		if(Bukkit.getServer().getPlayer(player)!=null && Bukkit.getServer().getPlayer(player).isOnline())
		{
			Inventory inv = Bukkit.createInventory(null, 27, uniqueCode + "��0���� �ӽ�");
			if(fin)
			{
			  	YamlLoader gambleYaml = new YamlLoader();
			  	gambleYaml.getConfig("ETC/SlotMachine.yml");
				
				if(gambleYaml.contains(machineNumber+".8"))
					ItemStackStack(gambleYaml.getItemStack(machineNumber+".8"), 16, inv);
				else
					Stack2("��c��l[��� ���� ��]", 166,0,1,Arrays.asList("","��7���� �ӽ� �̿뿡","��7������ ���ĵ��","��7����� �˼��մϴ�."), 16, inv);
				Stack2("��e��l[����� �����̽ÿ�!]", 76,0,1,Arrays.asList("","��7���� �ӽſ� ������ �ְ�","��7������ �����ϴ�!","","��a[���� �������� �����Դϴ�.]"), 15, inv);
			}
			else
			{
				Stack2("��c��l[������ ���� ���� �� ����!]", 166,0,1,Arrays.asList("","��7������ �������� ��ٸ���!"), 16, inv);
				Stack2("��c��l[������ ���� �� ����!]", 69,0,1,Arrays.asList("","��7����� ��ٸ�����!"), 15, inv);
			}
			for(int count=0; count<5; count++)
				Stack2("��e��l ", 160,4,1,Arrays.asList(""), count, inv);
			for(int count=5; count<9; count++)
				Stack2("��e��l ", 160,11,1,Arrays.asList(""), count, inv);

			Stack2("��e��l ", 160,4,1,Arrays.asList(""), 9, inv);
			Stack2("��e��l ", 160,4,1,Arrays.asList(""), 13, inv);
			Stack2("��e��l ", 160,11,1,Arrays.asList(""), 14, inv);
			Stack2("��e��l ", 160,11,1,Arrays.asList("��0"+machineNumber), 17, inv);
			
			for(int count=18; count<23; count++)
				Stack2("��e��l ", 160,4,1,Arrays.asList(""), count, inv);
			for(int count=23; count<27; count++)
				Stack2("��e��l ", 160,11,1,Arrays.asList(""), count, inv);
			
			for(int count=0;count<3;count++)
				Stack2("��e��l"+(count+1)+" ��° ����", itemID[count],0,1,Arrays.asList(""), count+10, inv);
			Bukkit.getServer().getPlayer(player).openInventory(inv);
			
			SoundEffect.SP(Bukkit.getServer().getPlayer(player), Sound.BLOCK_STONE_STEP, 1.0F, 1.0F);
		}
	}
}
