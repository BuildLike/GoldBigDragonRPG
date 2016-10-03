package GBD_RPG.Admin;

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

import GBD_RPG.User.UserData_Object;
import GBD_RPG.Util.Util_GUI;
import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Gamble_GUI extends Util_GUI
{
	public void GambleMainGUI(Player player)
	{
		String UniqueCode = "��0��0��1��0��c��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0���� ����");

		Stack2(ChatColor.WHITE + "��ǰ ����", 54,0,1,Arrays.asList(ChatColor.GRAY + "��ǰ ��Ű���� ����ų�",ChatColor.GRAY + "����/Ȯ�� �մϴ�."), 10, inv);
		
		Stack2(ChatColor.WHITE + "���� ����", 137,0,1,Arrays.asList(ChatColor.GRAY + "���� ���ӿ� ���� ������ �մϴ�."), 12, inv);
		Stack2(ChatColor.WHITE + "���� ���", 137,0,1,Arrays.asList(ChatColor.GRAY + "���� ��Ͽ� ���� ������ �մϴ�.", ChatColor.RED+""+ChatColor.BOLD+"[������Ʈ�� �ʿ��մϴ�!]"), 14, inv);
		
		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "�۾� ������ �޴��� ���ư��ϴ�."), 36, inv);
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		player.openInventory(inv);
	}
	
	public void GambleMainGUI_Click(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();

		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 44)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 36)//���� ���
				new OPbox_GUI().OPBoxGUI_Main(player, (byte) 3);
			else if(slot == 10)//��ǰ ����
				GamblePresentGUI(player, (short)0, (byte)0, (short)-1, null);
			else if(slot == 12)//���� �ӽ�
				SlotMachine_MainGUI(player,0);
		}
	}
	
	
	public void GamblePresentGUI(Player player, short page, byte isChoose, short DetailChoose, String DeDetailChoose)
	{
		String UniqueCode = "��0��0��1��0��d��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��ǰ ��� : "+(page+1));

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager PresentList  = YC.getNewConfig("Item/GamblePresent.yml");
		
		Object[] a = PresentList.getConfigurationSection("").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String PackageName = a[count].toString();
			String Grade = PresentList.getString(a[count].toString()+".Grade");
			byte PresentAmount = (byte) PresentList.getConfigurationSection(a[count].toString()+".Present").getKeys(false).size();
			
			if(count > a.length || loc >= 45) break;

			Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+PackageName, 54,0,1,Arrays.asList(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"��� : "+Grade, ChatColor.DARK_AQUA+""+ChatColor.BOLD+"��ϵ� ������ �� : " +ChatColor.WHITE+ PresentAmount+"��","",ChatColor.YELLOW+"[�� Ŭ���� ��Ű�� Ȯ��]",ChatColor.RED+"[Shift + �� Ŭ���� ����]",""), loc, inv);
			
			loc++;
		}
		
		if(a.length-(page*44)>45)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		if(isChoose==1)
			Stack2(ChatColor.RED + "" + ChatColor.BOLD + "��", 166,0,1,Arrays.asList(ChatColor.GRAY + "�ƹ��͵� ���� �ʽ��ϴ�.",ChatColor.BLACK+DeDetailChoose), 49, inv);
		else
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�� ��ǰ", 130,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ��ǰ�� �����մϴ�.",ChatColor.BLACK+DeDetailChoose), 49, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+""+isChoose), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�.",ChatColor.BLACK+""+DetailChoose), 53, inv);
		player.openInventory(inv);
	}
	
	public void GamblePresentGUI_Click(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);

			short page =  (short) (Short.parseShort(ChatColor.stripColor(event.getInventory().getTitle().split(" : ")[1]))-1);
			byte isChoose = Byte.parseByte(ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1)));
			short DetailChoose = Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			String DeDetailChoose = ChatColor.stripColor(event.getInventory().getItem(49).getItemMeta().getLore().get(1));
			
			if(slot == 45)//���� ���
			{
				if(isChoose==0)
					GambleMainGUI(player);
				else if(isChoose==1)//���� �ӽ��� ��ǰ ����
					SlotMachine_DetailGUI(player, DeDetailChoose);
			}
			else if(slot == 48)//���� ������
				GamblePresentGUI(player, (short) (page-1),isChoose,DetailChoose,DeDetailChoose);
			else if(slot == 49)//�� ��Ű��
			{
				if(isChoose==1)//���� �ӽ��� ��ǰ ����
				{
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
					GambleConfig.set(DeDetailChoose+"."+DetailChoose, "null");
					GambleConfig.saveConfig();
					SlotMachine_DetailGUI(player, DeDetailChoose);
					return;
				}
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Gamble");
				u.setString(player, (byte)0, "NP");
				player.sendMessage(ChatColor.GREEN + "[����] : ��ǰ �̸��� ������ �ּ���!");
			}
			else if(slot == 50)//���� ������
				GamblePresentGUI(player, (short) (page+1),isChoose,DetailChoose,DeDetailChoose);
			else
			{
				if(isChoose==0)
				{
					if(event.isLeftClick() == true&&event.isShiftClick()==false)
						GambleDetailViewPackageGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					else if(event.isRightClick()==true&&event.isShiftClick()==true)
					{
					  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
						YamlManager PresentList = YC.getNewConfig("Item/GamblePresent.yml");
						PresentList.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						PresentList.saveConfig();
						GamblePresentGUI(player, page,isChoose,DetailChoose,DeDetailChoose);
					}
				}
				else if(isChoose==1)//���� �ӽ��� ��ǰ ����
				{
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
					GambleConfig.set(DeDetailChoose+"."+DetailChoose, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					GambleConfig.saveConfig();
					SlotMachine_DetailGUI(player, DeDetailChoose);
				}
			}
		}
	}
	
	
	public void GambleDetailViewPackageGUI(Player player, String Package)
	{
		String UniqueCode = "��1��0��1��0��e��r";
		Inventory inv = Bukkit.createInventory(null, 36, UniqueCode + "��0���� ��ǰ ����");

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager PresentList  = YC.getNewConfig("Item/GamblePresent.yml");
		
		byte loc=0;
		for(byte count = 0; count < 27;count++)
		{
			ItemStack item = PresentList.getItemStack(Package+".Present."+count);
			if(item!=null)
			{
				ItemStackStack(item, loc, inv);
				loc++;
			}
		}

		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� [��ǰ �ֱ�] ����", 160,8,1,null, 28, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� [��ǰ �ֱ�] ����", 160,8,1,null, 29, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� [��ǰ �ֱ�] ����", 160,8,1,null, 30, inv);
		String Grade = PresentList.getString(Package+".Grade");
		Stack2(ChatColor.DARK_AQUA + "[    ��� ����    ]", 266,0,1,Arrays.asList(ChatColor.WHITE+"��Ű���� �����",ChatColor.WHITE+"�����մϴ�.","",ChatColor.WHITE+"[    ���� ���    ]","       "+Grade,""), 31, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� [��ǰ �ֱ�] ����", 160,8,1,null, 32, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� [��ǰ �ֱ�] ����", 160,8,1,null, 33, inv);
		Stack(ChatColor.WHITE + "" + ChatColor.BOLD + "���� [��ǰ �ֱ�] ����", 160,8,1,null, 34, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+Package), 27, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 35, inv);
		player.openInventory(inv);
	}

	public void GambleDetailViewPackageGUI_Click(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot >= 27)
			event.setCancelled(true);
		if(slot == 35)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 27)//���� ���
				GamblePresentGUI(player,(byte)0,(byte)0,(short)0,null);
			else if(slot == 31)//��� ����
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager PresentList  = YC.getNewConfig("Item/GamblePresent.yml");
				String Package = ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
				String Grade = PresentList.getString(Package+".Grade");
				String MaximumGrade = ChatColor.DARK_RED+""+ChatColor.BOLD+"["+ChatColor.GOLD+""+ChatColor.BOLD+"��"+ChatColor.DARK_GREEN+""+ChatColor.BOLD+"��"+ChatColor.DARK_BLUE+""+ChatColor.BOLD+"]";
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(Grade.compareTo(ChatColor.WHITE+"[�Ϲ�]")==0)
					PresentList.set(Package+".Grade",ChatColor.GREEN+"[���]");
				else if(Grade.compareTo(ChatColor.GREEN+"[���]")==0)
					PresentList.set(Package+".Grade",ChatColor.BLUE+"[����]");
				else if(Grade.compareTo(ChatColor.BLUE+"[����]")==0)
					PresentList.set(Package+".Grade",ChatColor.YELLOW+"[����]");
				else if(Grade.compareTo(ChatColor.YELLOW+"[����]")==0)
					PresentList.set(Package+".Grade",ChatColor.DARK_PURPLE+"[����]");
				else if(Grade.compareTo(ChatColor.DARK_PURPLE+"[����]")==0)
					PresentList.set(Package+".Grade",ChatColor.GOLD+"[����]");
				else if(Grade.compareTo(ChatColor.GOLD+"[����]")==0)
					PresentList.set(Package+".Grade",MaximumGrade);
				else if(Grade.compareTo(MaximumGrade)==0)
					PresentList.set(Package+".Grade",ChatColor.GRAY+"[�ϱ�]");
				else if(Grade.compareTo(ChatColor.GRAY+"[�ϱ�]")==0)
					PresentList.set(Package+".Grade",ChatColor.WHITE+"[�Ϲ�]");
				else
					PresentList.set(Package+".Grade",ChatColor.WHITE+"[�Ϲ�]");
				PresentList.saveConfig();
				Grade = PresentList.getString(Package+".Grade");
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
				PresentList.removeKey(Package+".Present");
				PresentList.saveConfig();
				for(byte count=0; count<itemcount;count++)
					PresentList.set(Package+".Present."+count,item[count]);
				PresentList.saveConfig();
				GambleDetailViewPackageGUI(player, Package);
			}
		}
	}
	
	public void GambleDetailViewPackageGUI_Close(InventoryCloseEvent event)
	{
		String Package = ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager PresentList  = YC.getNewConfig("Item/GamblePresent.yml");
		ItemStack item[] = new ItemStack[27];
		byte itemcount=0;
		for(byte count=0; count < 27; count++)
		{
			if(event.getInventory().getItem(count)!=null)
			{
				item[itemcount] = event.getInventory().getItem(count);
				itemcount++;
			}
		}
		PresentList.removeKey(Package+".Present");
		PresentList.createSection(Package+".Present");
		PresentList.saveConfig();
		for(byte count=0; count<itemcount;count++)
			PresentList.set(Package+".Present."+count,item[count]);
		PresentList.saveConfig();
	}
	
	
	public void SlotMachine_MainGUI(Player player, int page)
	{
		String UniqueCode = "��0��0��1��0��f��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ��� ��� : "+(page+1));

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager SlotMachineList  = YC.getNewConfig("ETC/SlotMachine.yml");
		
		Object[] a = SlotMachineList.getConfigurationSection("").getKeys(false).toArray();
		//����_��ǥ
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String location = a[count].toString().split("_")[1];
			String world = a[count].toString().split("_")[0];
			
			if(count > a.length || loc >= 45) break;

			Stack2(ChatColor.BLACK+""+ChatColor.BOLD+a[count].toString(), 137,0,1,Arrays.asList(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"���� : "+ChatColor.WHITE+""+ChatColor.BOLD+world, ChatColor.DARK_AQUA+""+ChatColor.BOLD+"��ǥ : " +ChatColor.WHITE+ ""+ChatColor.BOLD+location,"",ChatColor.YELLOW+"[�� Ŭ���� ��� ����]",ChatColor.RED+"[Shift + �� Ŭ���� ����]",""), loc, inv);
			
			loc++;
		}
		
		if(a.length-(page*44)>45)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);

		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�� ���", 130,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ��踦 ��ġ�մϴ�."), 49, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}	

	public void SlotMachine_MainGUI_Click(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);

		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 53)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//�������
				GambleMainGUI(player);
			else if(slot == 48)//���� ������
				SlotMachine_MainGUI(player, page-1);
			else if(slot == 49)//�� ���� �ӽ�
			{
				UserData_Object u = new UserData_Object();
				player.closeInventory();
				u.setType(player, "Gamble");
				u.setString(player, (byte)0, "NSM");
				player.sendMessage(ChatColor.GREEN + "[����] : ���� �ӽ��� ��� �� ����� ��Ŭ�� �� �ּ���!");
			}
			else if(slot == 50)//���� ������
				SlotMachine_MainGUI(player, page+1);
			else
			{
				if(event.isLeftClick() == true&&event.isShiftClick()==false)
					SlotMachine_DetailGUI(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				else if(event.isRightClick()==true&&event.isShiftClick()==true)
				{
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager PresentList = YC.getNewConfig("ETC/SlotMachine.yml");
					PresentList.removeKey(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					PresentList.saveConfig();
					SlotMachine_MainGUI(player, page);
				}
			}
		}
	}
	
	
	public void SlotMachine_DetailGUI(Player player, String MachineNumber)
	{
		String UniqueCode = "��0��0��1��1��0��r";
		Inventory inv = Bukkit.createInventory(null, 36, UniqueCode + "��0���� ��� ���� ");

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
		
		String Setter = GambleConfig.getString(MachineNumber+".0");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.GRAY+""+ChatColor.BOLD+"[��� �ٸ� ���]", 351,15,1,Arrays.asList("",ChatColor.WHITE+"�� "+ChatColor.YELLOW+"�� "+ChatColor.DARK_AQUA+"��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� �ٸ� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 0, inv);
		Setter = GambleConfig.getString(MachineNumber+".1");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"[Ʈ���� �ھ�]", 263,0,3,Arrays.asList("",ChatColor.DARK_GRAY+"�� �� ��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� ��ź�� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 1, inv);
		Setter = GambleConfig.getString(MachineNumber+".2");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[Ʈ���� ���̾�]", 265,0,3,Arrays.asList("",ChatColor.WHITE+"�� �� ��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� ö���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 2, inv);
		Setter = GambleConfig.getString(MachineNumber+".3");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+"[Ʈ���� ���]", 266,0,3,Arrays.asList("",ChatColor.YELLOW+"�� �� ��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� �ݱ��� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 3, inv);
		Setter = GambleConfig.getString(MachineNumber+".4");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"[Ʈ���� ���̾Ƹ��]", 264,0,3,Arrays.asList("",ChatColor.DARK_AQUA+"�� �� ��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� ���̾��� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 4, inv);
		Setter = GambleConfig.getString(MachineNumber+".5");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.GREEN+""+ChatColor.BOLD+"[Ʈ���� ���޶���]", 388,0,3,Arrays.asList("",ChatColor.GREEN+"�� �� ��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� ���޶����� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 5, inv);
		Setter = GambleConfig.getString(MachineNumber+".6");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.BLUE+""+ChatColor.BOLD+"[Ʈ���� �״� ��Ÿ]", 399,0,3,Arrays.asList("",ChatColor.BLUE+"�� �� ��",ChatColor.GRAY+"���� �ӽ��� ���� �����",ChatColor.GRAY+"��� �״� ���� ����Դϴ�.",ChatColor.GRAY+"��÷�� ��ǰ�� ��÷�ڸ�",ChatColor.GRAY+"���� ��ü�� �˷� �ݴϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 6, inv);
		Setter = GambleConfig.getString(MachineNumber+".9");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.BLUE+""+ChatColor.BOLD+"[�̱� �״� ��Ÿ]", 399,0,1,Arrays.asList("",ChatColor.BLUE+"�� "+ChatColor.DARK_GRAY+"�� ��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"�״� ���� 1�� ���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 9, inv);
		Setter = GambleConfig.getString(MachineNumber+".10");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"[���� �ھ�]", 263,0,2,Arrays.asList("",ChatColor.DARK_GRAY+"�� �� "+ChatColor.DARK_GRAY+"��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"��ź�� 2���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 10, inv);
		Setter = GambleConfig.getString(MachineNumber+".11");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.WHITE+""+ChatColor.BOLD+"[���� ���̾�]", 265,0,2,Arrays.asList("",ChatColor.WHITE+"�� �� "+ChatColor.DARK_GRAY+"��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"ö���� 2���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 11, inv);
		Setter = GambleConfig.getString(MachineNumber+".12");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+"[���� ���]", 266,0,2,Arrays.asList("",ChatColor.YELLOW+"�� �� "+ChatColor.DARK_GRAY+"��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"�ݱ��� 2���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 12, inv);
		Setter = GambleConfig.getString(MachineNumber+".13");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"[���� ���̾Ƹ��]", 264,0,2,Arrays.asList("",ChatColor.DARK_AQUA+"�� �� "+ChatColor.DARK_GRAY+"��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"��� ���̾ư� 2���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 13, inv);
		Setter = GambleConfig.getString(MachineNumber+".14");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.GREEN+""+ChatColor.BOLD+"[���� ���޶���]", 388,0,2,Arrays.asList("",ChatColor.GREEN+"�� �� "+ChatColor.DARK_GRAY+"��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"���޶��尡 2���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 14, inv);
		Setter = GambleConfig.getString(MachineNumber+".15");
		if(Setter.compareTo("null")==0)Setter="����";
		Stack2(ChatColor.BLUE+""+ChatColor.BOLD+"[���� �״� ��Ÿ]", 399,0,2,Arrays.asList("",ChatColor.BLUE+"�� �� "+ChatColor.DARK_GRAY+"��",ChatColor.GRAY+"���� �ӽ��� ���� ���",ChatColor.GRAY+"�״� ���� 2���� ����Դϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ��ǰ ����]","",ChatColor.GRAY+"[���� ��ǰ]",ChatColor.GRAY+Setter), 15, inv);

		Stack2(ChatColor.DARK_GREEN+""+ChatColor.BOLD+"[���� ���� ����]", 341,0,1,Arrays.asList("",ChatColor.GRAY+"���� �ӽ� 1ȸ �̿��� ����",ChatColor.GRAY+"�ʿ��� ������ �����մϴ�.","",ChatColor.YELLOW+"[�� Ŭ���� ���� ����]",""), 8, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+MachineNumber), 27, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 35, inv);
		player.openInventory(inv);
	}
	
	public void SlotMachine_DetailGUI_Click(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		String MachineNumber =  ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
		int slot = event.getSlot();
		
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();

		if(slot == 35)//������
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot <= 6 || (slot >= 9 && slot <= 15))//�� Ȯ���� ���� ����
				GamblePresentGUI(player, (byte)0, (byte)1, (short)event.getSlot(),MachineNumber);
			else if(slot == 8)//���� ����
				SlotMachineCoinGUI(player, MachineNumber);
			else if(slot == 27)//���� ���
				SlotMachine_MainGUI(player,0);
		}
	}

	
	public void SlotMachineCoinGUI(Player player, String MachineNumber)
	{
		String UniqueCode = "��1��0��1��1��1��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0���� ��� ����");

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
		
		if(GambleConfig.contains(MachineNumber+".8"))
			ItemStackStack(GambleConfig.getItemStack(MachineNumber+".8"), 4, inv);
			
		Stack(ChatColor.LIGHT_PURPLE + "[���� �ֱ�]����", 166,0,1,null, 1, inv);
		Stack(ChatColor.LIGHT_PURPLE + "[���� �ֱ�]����", 166,0,1,null, 2, inv);
		Stack(ChatColor.LIGHT_PURPLE + "[���� �ֱ�]����", 166,0,1,null, 3, inv);
		Stack(ChatColor.LIGHT_PURPLE + "����[���� �ֱ�]", 166,0,1,null, 5, inv);
		Stack(ChatColor.LIGHT_PURPLE + "����[���� �ֱ�]", 166,0,1,null, 6, inv);
		Stack(ChatColor.LIGHT_PURPLE + "����[���� �ֱ�]", 166,0,1,null, 7, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�.",ChatColor.BLACK+MachineNumber), 0, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 8, inv);
		player.openInventory(inv);
	}
	
	public void SlotMachineCoinGUI_Click(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		String MachineNumber = ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(1));
		
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();

		if(event.getClickedInventory().getTitle().compareTo("container.inventory") != 0)
		{
			if(slot!=4)
				event.setCancelled(true);
			if(slot == 0)
			{
				s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				SlotMachine_DetailGUI(player, MachineNumber);
			}
			else if(slot == 8)
			{
				s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
		}
	}

	public void SlotMachineCoinGUI_Close(InventoryCloseEvent event)
	{
		String MachineNumber = ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(1));

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
		
		if(event.getInventory().getItem(4)!=null)
			GambleConfig.set(MachineNumber+".8", event.getInventory().getItem(4));
		else
			GambleConfig.set(MachineNumber+".8", null);
		GambleConfig.saveConfig();
	}

	
	public void SlotMachine_PlayGUI(Player player, String MachineNumber)
	{
		String UniqueCode = "��0��0��1��1��2��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0���� �ӽ�");

	  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
		YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
		
		if(GambleConfig.contains(MachineNumber+".8"))
			ItemStackStack(GambleConfig.getItemStack(MachineNumber+".8"), 16, inv);
		else
			Stack2(ChatColor.RED+""+ChatColor.BOLD+"[��� ���� ��]", 166,0,1,Arrays.asList("",ChatColor.GRAY+"���� �ӽ� �̿뿡",ChatColor.GRAY+"������ ���ĵ��",ChatColor.GRAY+"����� �˼��մϴ�."), 16, inv);
		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+"[����� �����̽ÿ�!]", 76,0,1,Arrays.asList("",ChatColor.GRAY+"���� �ӽſ� ������ �ְ�",ChatColor.GRAY+"������ �����ϴ�!","",ChatColor.GREEN+"[���� �������� �����Դϴ�.]"), 15, inv);

		for(byte count=0; count<5; count++)
			Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), count, inv);
		for(byte count=5; count<9; count++)
			Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(""), count, inv);

		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), 9, inv);
		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), 13, inv);
		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(""), 14, inv);
		Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(ChatColor.BLACK+MachineNumber), 17, inv);
		
		for(byte count=18; count<23; count++)
			Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), count, inv);
		for(byte count=23; count<27; count++)
			Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(""), count, inv);
		
		for(byte count=1;count<4;count++)
		{
			byte randomnum = (byte) new GBD_RPG.Util.Util_Number().RandomNum(0, 5);
			short ItemID=263;
			switch(randomnum)
			{
			case 0:
				ItemID = 263;
				break;
			case 1:
				ItemID = 265;
				break;
			case 2:
				ItemID = 266;
				break;
			case 3:
				ItemID = 264;
				break;
			case 4:
				ItemID = 388;
				break;
			case 5:
				ItemID = 399;
				break;
			}
			Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+""+count+" ��° ����", ItemID,0,1,Arrays.asList(""), count+9, inv);
			
		}
		player.openInventory(inv);
	}

	public void SlotMachine_PlayGUI_Click(InventoryClickEvent event)
	{
		if(event.getSlot()==15)
		{
			GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
			ItemStack Coin = event.getInventory().getItem(16);
			Player player = (Player) event.getWhoClicked();
			if(event.getCurrentItem().getTypeId()==69)
				return;
			for(byte countta=0;countta < player.getInventory().getSize(); countta++)
			{
				if(player.getInventory().getItem(countta)!=null)
				{
					if(player.getInventory().getItem(countta).isSimilar(Coin)==true)
					{
						if(player.getInventory().getItem(countta).getAmount()>=Coin.getAmount())
						{
							ItemStack ii = player.getInventory().getItem(countta);
							if(ii.getAmount()==Coin.getAmount())
								player.getInventory().setItem(countta, null);
							else
							{
								ii.setAmount(ii.getAmount()-Coin.getAmount());
								player.getInventory().setItem(countta, ii);
							}
							player.updateInventory();
							if(event.getInventory().getItem(16).getTypeId()==166 && event.getInventory().getItem(16).hasItemMeta()==true)
								if(event.getInventory().getItem(16).getItemMeta().getDisplayName().compareTo(ChatColor.RED+""+ChatColor.BOLD+"[��� ���� ��]")==0)
								{
									s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
									player.sendMessage(ChatColor.RED+"[���� �ӽ�] : ���� ���� ���� ���Դϴ�! �����ڿ��� �����ϼ���!");
									return;
								}
							
							if(GBD_RPG.ServerTick.ServerTick_Main.PlayerTaskList.containsKey(player.getName())==true)
							{
								s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage(ChatColor.RED+"[���� �ӽ�] : �̹� ������� �۾��� �ֽ��ϴ�! ������ �� ���� ������!");
								return;
							}
							
							s.SP(player, Sound.BLOCK_CHEST_CLOSE, 1.0F, 0.5F);
							s.SP(player, Sound.BLOCK_CHEST_OPEN, 1.0F, 0.5F);
							
							ItemStack Icon = new MaterialData(69, (byte) 0).toItemStack(1);
							ItemMeta Icon_Meta = Icon.getItemMeta();
							Icon_Meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"[������ ���� �� ����!]");
							Icon_Meta.setLore(Arrays.asList("",ChatColor.GRAY+"����� ��ٸ�����!"));
							Icon.setItemMeta(Icon_Meta);
							
							event.getInventory().setItem(15, Icon);
							short ItemID[] = new short[3];
							for(byte count=0;count<3;count++)
							{
								byte randomnum = (byte) new GBD_RPG.Util.Util_Number().RandomNum(0, 5);
								ItemID[count]=263;
								switch(randomnum)
								{
								case 0:
									ItemID[count] = 263;
									break;
								case 1:
									ItemID[count] = 265;
									break;
								case 2:
									ItemID[count] = 266;
									break;
								case 3:
									ItemID[count] = 264;
									break;
								case 4:
									ItemID[count] = 388;
									break;
								case 5:
									ItemID[count] = 399;
									break;
								}
							}
							Long UTC = GBD_RPG.ServerTick.ServerTick_Main.nowUTC+5;
							for(;;)
							{
								if(GBD_RPG.ServerTick.ServerTick_Main.Schedule.containsKey(UTC))
									UTC=UTC+1;
								else
									break;
							}
							
							GBD_RPG.ServerTick.ServerTick_Object OBJECT = new GBD_RPG.ServerTick.ServerTick_Object(UTC, "G_SM");
							OBJECT.setString((byte)0, player.getName());
							OBJECT.setString((byte)1, ChatColor.stripColor(event.getInventory().getItem(17).getItemMeta().getLore().get(0)));

							OBJECT.setInt((byte)0, ItemID[0]);
							OBJECT.setInt((byte)1, ItemID[1]);
							OBJECT.setInt((byte)2, ItemID[2]);
							OBJECT.setMaxCount(20);
							GBD_RPG.ServerTick.ServerTick_Main.Schedule.put(UTC, OBJECT);
							GBD_RPG.ServerTick.ServerTick_Main.PlayerTaskList.put(player.getName(), "G_SM");
							return;
						}
					}
				}
			}
			s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
			player.sendMessage(ChatColor.RED+"[���� �ӽ�] : ������ �����մϴ�!");
			return;
		}
	}

	public void SlotMachine_RollingGUI(String player, short[] itemID, boolean fin, String MachineNumber)
	{
		String UniqueCode = "��1��0��1��1��3��r";
		if(Bukkit.getServer().getPlayer(player)!=null)
		{
			if(Bukkit.getServer().getPlayer(player).isOnline())
			{
				Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0���� �ӽ�");
				if(fin)
				{
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
					YamlManager GambleConfig =YC.getNewConfig("ETC/SlotMachine.yml");
					
					if(GambleConfig.contains(MachineNumber+".8"))
						ItemStackStack(GambleConfig.getItemStack(MachineNumber+".8"), 16, inv);
					else
						Stack2(ChatColor.RED+""+ChatColor.BOLD+"[��� ���� ��]", 166,0,1,Arrays.asList("",ChatColor.GRAY+"���� �ӽ� �̿뿡",ChatColor.GRAY+"������ ���ĵ��",ChatColor.GRAY+"����� �˼��մϴ�."), 16, inv);
					Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+"[����� �����̽ÿ�!]", 76,0,1,Arrays.asList("",ChatColor.GRAY+"���� �ӽſ� ������ �ְ�",ChatColor.GRAY+"������ �����ϴ�!","",ChatColor.GREEN+"[���� �������� �����Դϴ�.]"), 15, inv);
				}
				else
				{
					Stack2(ChatColor.RED+""+ChatColor.BOLD+"[������ ���� ���� �� ����!]", 166,0,1,Arrays.asList("",ChatColor.GRAY+"������ �������� ��ٸ���!"), 16, inv);
					Stack2(ChatColor.RED+""+ChatColor.BOLD+"[������ ���� �� ����!]", 69,0,1,Arrays.asList("",ChatColor.GRAY+"����� ��ٸ�����!"), 15, inv);
				}
				for(byte count=0; count<5; count++)
					Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), count, inv);
				for(byte count=5; count<9; count++)
					Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(""), count, inv);

				Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), 9, inv);
				Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), 13, inv);
				Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(""), 14, inv);
				Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(ChatColor.BLACK+MachineNumber), 17, inv);
				
				for(byte count=18; count<23; count++)
					Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,4,1,Arrays.asList(""), count, inv);
				for(byte count=23; count<27; count++)
					Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+" ", 160,11,1,Arrays.asList(""), count, inv);
				
				for(byte count=0;count<3;count++)
					Stack2(ChatColor.YELLOW+""+ChatColor.BOLD+""+(count+1)+" ��° ����", itemID[count],0,1,Arrays.asList(""), count+10, inv);
				Bukkit.getServer().getPlayer(player).openInventory(inv);
				GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
				s.SP(Bukkit.getServer().getPlayer(player), Sound.BLOCK_STONE_STEP, 1.0F, 1.0F);
			}
		}
	}
}
