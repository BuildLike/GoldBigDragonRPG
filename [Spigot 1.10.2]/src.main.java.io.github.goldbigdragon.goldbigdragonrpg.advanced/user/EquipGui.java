package user;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import effect.SoundEffect;
import servertick.ServerTickMain;
import util.UtilGui;
import util.YamlLoader;

public class EquipGui extends UtilGui
{
	public void EquipWatchGUI(Player player, Player target)
	{
		String UniqueCode = "��0��0��0��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��� ����");
	  	if(main.MainServerOption.PlayerList.get(target.getUniqueId().toString()).isOption_EquipLook())
	  	{
			Stack("��3[    ���    ]", 160,7,1,null, 0, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 1, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 2, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 3, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 4, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 9, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 18, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 27, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 36, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 45, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 46, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 47, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 48, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 49, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 40, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 31, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 22, inv);
			Stack("��3[    ���    ]", 160,7,1,null, 13, inv);
			
			
	  		if(target.getInventory().getHelmet() == null)
	  			Stack2("��f��l������", 302,0,1,null, 11, inv);
	  		else
	  			ItemStackStack(target.getInventory().getHelmet(), 11, inv);
	  		if(target.getInventory().getChestplate() == null)
	  			Stack2("��f��l������", 303,0,01,null, 20, inv);
	  		else
	  			ItemStackStack(target.getInventory().getChestplate(), 20, inv);
	  		if(target.getInventory().getLeggings() == null)
	  			Stack2("��f��l������", 304,0,1,null, 29, inv);
	  		else
	  			ItemStackStack(target.getInventory().getLeggings(), 29, inv);
	  		if(target.getInventory().getBoots() == null)
	  			Stack2("��f��l������", 305,0,1,null, 38, inv);
	  		else
	  			ItemStackStack(target.getInventory().getBoots(), 38, inv);
	  		if(target.getInventory().getItemInMainHand() == null)
	  			Stack2("��f��l�Ǽ�", 336,0,1,null, 28, inv);
	  		else
	  			if(target.getInventory().getItemInMainHand().getType() == Material.AIR)
		  			Stack2("��f��l�Ǽ�", 336,0,1,null, 28, inv);
	  			else
	  				ItemStackStack(target.getInventory().getItemInMainHand(), 28, inv);
	  		if(target.getInventory().getItemInOffHand() == null)
	  			Stack2("��f��l�Ǽ�", 336,0,1,null, 30, inv);
	  		else
	  			if(target.getInventory().getItemInOffHand().getType() == Material.AIR)
		  			Stack2("��f��l�Ǽ�", 336,0,1,null, 30, inv);
	  			else
	  				ItemStackStack(target.getInventory().getItemInOffHand(), 30, inv);
	  	}
	  	else
	  	{
			Stack("��c[    �����    ]", 160,14,1,null, 0, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 1, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 2, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 3, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 4, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 9, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 18, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 27, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 36, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 45, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 46, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 47, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 48, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 49, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 40, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 31, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 22, inv);
			Stack("��c[    �����    ]", 160,14,1,null, 13, inv);
			
	  		Stack2("��f��l�˼�����", 302,0,1,null, 11, inv);
  			Stack2("��f��l�˼�����", 303,0,1,null, 20, inv);
  			Stack2("��f��l�˼�����", 304,0,1,null, 29, inv);
  			Stack2("��f��l�˼�����", 305,0,1,null, 38, inv);
  			Stack2("��f��l�˼�����", 336,0,1,null, 28, inv);
	  	}

		Stack("��3[    �ൿ    ]", 160,9,1,null, 5, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 6, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 7, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 8, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 14, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 17, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 23, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 26, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 32, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 35, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 41, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 44, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 50, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 51, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 52, inv);
		Stack("��3[    �ൿ    ]", 160,9,1,null, 53, inv);
		
	  	Stack2("��f��l��ȯ ��û", 388,0,1,Arrays.asList(
	  			"��7��ȯ ��û�� �մϴ�.","��7������ ���� �������� ������","��7������ �����Ǿ� �ֽ��ϴ�.","","��e[    ���    ]","��f��l"+target.getName()), 15, inv);
		Stack2("��f��lģ�� �߰�", 397,3,1,Arrays.asList(
				"��7ģ�� �߰� ��û�� �մϴ�.","","��e[    ���    ]","��f��l"+target.getName()), 16, inv);
		player.openInventory(inv);
	}
	
	public void ExChangeGUI(Player player, Player target, ItemStack[] SideItems, boolean MyReady,boolean TargetReady)
	{
		String UniqueCode = "��0��0��0��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��ȯ");

		for(int count = 0; count < 5; count++)
			Stack("��7��l[   ĭ����   ]", 160,15,1,null, 4+(count*9), inv);

		Stack("��f��l[ ��ȯ ��� ]", 166,0,1,null, 49, inv);
		
		ItemStackStack(getPlayerSkull("��a��l"+player.getName(), 1, null, player.getName()), 0, inv);
		ItemStackStack(getPlayerSkull("��a��l"+target.getName(), 1, null, target.getName()), 8, inv);

		for(int count = 1; count < 4; count++)
			Stack("��9��l[   �ڽ�   ]", 160,11,1,null, count, inv);
		for(int count = 5; count < 8; count++)
			Stack("��c��l[   ���   ]", 160,14,1,null, count, inv);

		for(int count = 0; count < 4; count++)
			Stack("��9��l[   �ڽ�   ]", 160,11,1,null, 9+(count*9), inv);
		for(int count = 0; count < 4; count++)
			Stack("��c��l[   ���   ]", 160,14,1,null, 17+(count*9), inv);
		
		for(int count = 46; count < 49; count++)
			Stack("��9��l[   �ڽ�   ]", 160,11,1,null, count, inv);
		for(int count = 50; count < 53; count++)
			Stack("��c��l[   ���   ]", 160,14,1,null, count, inv);

		for(int count = 0; count < 4; count ++)
			for(int count2 = 10; count2 < 13; count2 ++)
				Stack("��7��l[�ƹ��͵� �÷����� ����]", 160,8,1,null, count2+(count*9), inv);
		
		for(int count = 0; count < 4; count ++)
			for(int count2 = 14; count2 < 17; count2 ++)
				Stack("��7��l[�ƹ��͵� �÷����� ����]", 160,8,1,null, count2+(count*9), inv);
		
		if(SideItems != null)
		{
			if(SideItems.length != 0)
			{
				int SIC = 0;
				for(int count = 0; count < 3; count ++)
				{
					for(int count2 = 10; count2 < 13; count2 ++)
					{
						if(SideItems[SIC] != null)
							ItemStackStack(SideItems[SIC], count2+(count*9), inv);
						SIC = SIC+1;
					}
				}
				for(int count = 0; count < 3; count ++)
				{
					for(int count2 = 14; count2 < 17; count2 ++)
					{
						if(SideItems[SIC] != null)
							ItemStackStack(SideItems[SIC], count2+(count*9), inv);
						SIC = SIC+1;
					}
				}
			}
		}
		
		if(MyReady)
			Stack("��9��l[ �غ� �Ϸ� ]", 35,11,1,null, 45, inv);
		else
			Stack("��c��l[  �����  ]", 35,14,1,null, 45, inv);

		if(TargetReady)
			Stack("��9��l[ �غ� �Ϸ� ]", 35,11,1,null, 53, inv);
		else
			Stack("��c��l[  �����  ]", 35,14,1,null, 53, inv);
		
		player.openInventory(inv);
	}
	
	
	
	
	public void EquipSeeInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		Player target = (Player) Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getInventory().getItem(16).getItemMeta().getLore().get(3)));

		int slot = event.getSlot();
		
		if(slot == 15)
			AddExchangeTarget(player, target);
		else if(slot == 16)
			SetFriends(player, target);
	}

	public void ExchangeInventoryclick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		Player target = Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getDisplayName()));
		

		if((event.getAction() == InventoryAction.PICKUP_ALL||
		event.getAction() == InventoryAction.PICKUP_HALF||
		event.getAction() == InventoryAction.PICKUP_ONE||
		event.getAction() == InventoryAction.PICKUP_SOME)&&
		!ChatColor.stripColor(event.getClickedInventory().getName()).equals("��ȯ"))
		{
			ItemStack mySideSlot[] = new ItemStack[12];
			byte MSS = 0;
			for(int count = 0; count < 4; count ++)
			{
				for(int count2 = 10; count2 < 13; count2 ++)
				{
					if(event.getInventory().getItem(count2+(count*9)) != null)
						mySideSlot[MSS] = event.getInventory().getItem(count2+(count*9));
					MSS++;
				}
			}
			byte emptySlot = -1;
			for(int count = 0;count < 12; count ++)
			{
				if(mySideSlot[count]!=null)
				{
					if(mySideSlot[count].hasItemMeta())
					{
						if(mySideSlot[count].getItemMeta().hasLore()==false&&
							mySideSlot[count].getItemMeta().hasDisplayName())
						{
							if(mySideSlot[count].getItemMeta().getDisplayName().equals("��7��l[�ƹ��͵� �÷����� ����]"))
							{emptySlot = (byte) count; break;}
						}
					}
				}
			}
			if(emptySlot != -1)
			{
				SoundEffect.SP(player, Sound.BLOCK_STONE_STEP, 1.0F, 1.0F);
				SoundEffect.SP(target, Sound.BLOCK_STONE_STEP, 1.0F, 1.0F);
				switch(emptySlot)
				{
				case 0 :
					player.getOpenInventory().setItem(10, event.getCurrentItem());
					target.getOpenInventory().setItem(14, event.getCurrentItem());
					break;
				case 1 :
					player.getOpenInventory().setItem(11, event.getCurrentItem());
					target.getOpenInventory().setItem(15, event.getCurrentItem());
					break;
				case 2 :
					player.getOpenInventory().setItem(12, event.getCurrentItem());
					target.getOpenInventory().setItem(16, event.getCurrentItem());
					break;
				case 3 :
					player.getOpenInventory().setItem(19, event.getCurrentItem());
					target.getOpenInventory().setItem(23, event.getCurrentItem());
					break;
				case 4 :
					player.getOpenInventory().setItem(20, event.getCurrentItem());
					target.getOpenInventory().setItem(24, event.getCurrentItem());
					break;
				case 5 :
					player.getOpenInventory().setItem(21, event.getCurrentItem());
					target.getOpenInventory().setItem(25, event.getCurrentItem());
					break;
				case 6 :
					player.getOpenInventory().setItem(28, event.getCurrentItem());
					target.getOpenInventory().setItem(32, event.getCurrentItem());
					break;
				case 7 :
					player.getOpenInventory().setItem(29, event.getCurrentItem());
					target.getOpenInventory().setItem(33, event.getCurrentItem());
					break;
				case 8 :
					player.getOpenInventory().setItem(30, event.getCurrentItem());
					target.getOpenInventory().setItem(34, event.getCurrentItem());
					break;
				case 9 :
					player.getOpenInventory().setItem(37, event.getCurrentItem());
					target.getOpenInventory().setItem(41, event.getCurrentItem());
					break;
				case 10 :
					player.getOpenInventory().setItem(38, event.getCurrentItem());
					target.getOpenInventory().setItem(42, event.getCurrentItem());
					break;
				case 11 :
					player.getOpenInventory().setItem(39, event.getCurrentItem());
					target.getOpenInventory().setItem(43, event.getCurrentItem());
					break;
				}
				event.getClickedInventory().setItem(event.getSlot(), new ItemStack(0));

				ItemStack Icon = new MaterialData(35, (byte) 14).toItemStack();
				Icon.setAmount(1);
				ItemMeta Icon_Meta = Icon.getItemMeta();
				Icon_Meta.setLore(null);
				Icon_Meta.setDisplayName("��c��l[  �����  ]");
				Icon.setItemMeta(Icon_Meta);
				
				target.getOpenInventory().setItem(45, Icon);
				event.getInventory().setItem(45, Icon);
				
				target.getOpenInventory().setItem(53, Icon);
				event.getInventory().setItem(53, Icon);
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[��ȯ] : �� �̻� ������ �ø� �� �����ϴ�!");
			}
		}
		else if((event.getAction() == InventoryAction.PICKUP_ALL||
				event.getAction() == InventoryAction.PICKUP_HALF||
				event.getAction() == InventoryAction.PICKUP_ONE||
				event.getAction() == InventoryAction.PICKUP_SOME)&&
				ChatColor.stripColor(event.getClickedInventory().getName()).equals("��ȯ"))
		{
			if((event.getSlot()>=10&&event.getSlot()<=12)||
			(event.getSlot()>=19&&event.getSlot()<=21)||
			(event.getSlot()>=28&&event.getSlot()<=30)||
			(event.getSlot()>=37&&event.getSlot()<=39))
			{
				if(event.getCurrentItem()!=null)
				{
					if(event.getCurrentItem().hasItemMeta())
					{
						if(event.getCurrentItem().getItemMeta().hasLore()==false&&
								event.getCurrentItem().getItemMeta().hasDisplayName())
						{
							if(event.getCurrentItem().getItemMeta().getDisplayName().equals("��7��l[�ƹ��͵� �÷����� ����]"))
							{return;}
						}
					}
				}
				ItemStack Icon = new MaterialData(160, (byte) 8).toItemStack();
				Icon.setAmount(1);
				ItemMeta Icon_Meta = Icon.getItemMeta();
				Icon_Meta.setLore(null);
				Icon_Meta.setDisplayName("��7��l[�ƹ��͵� �÷����� ����]");
				Icon.setItemMeta(Icon_Meta);
				player.getInventory().addItem(event.getCurrentItem());
				target.getOpenInventory().setItem(event.getSlot()+4, Icon);
				event.getClickedInventory().setItem(event.getSlot(), Icon);
				

				Icon = new MaterialData(35, (byte) 14).toItemStack();
				Icon.setAmount(1);
				Icon_Meta = Icon.getItemMeta();
				Icon_Meta.setLore(null);
				Icon_Meta.setDisplayName("��c��l[  �����  ]");
				Icon.setItemMeta(Icon_Meta);
				
				target.getOpenInventory().setItem(45, Icon);
				event.getClickedInventory().setItem(45, Icon);
				
				target.getOpenInventory().setItem(53, Icon);
				event.getClickedInventory().setItem(53, Icon);
				SoundEffect.SP(player, Sound.BLOCK_CLOTH_STEP, 1.0F, 1.0F);
				SoundEffect.SP(target, Sound.BLOCK_CLOTH_STEP, 1.0F, 1.0F);
			}
		}
	}
	
	public void ExchangeGUIclick(InventoryClickEvent event)
	{
		Player player = Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getDisplayName()));
		Player target = Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getDisplayName()));
		
		if((event.getSlot() >= 0 && event.getSlot() <= 9)||
		(event.getSlot() >= 13 && event.getSlot() <= 18)||
		(event.getSlot() >= 22 && event.getSlot() <= 27)||
		(event.getSlot() >= 31 && event.getSlot() <= 36)||
		(event.getSlot() >= 40 && event.getSlot() <= 53))
			event.setCancelled(true);
		
		switch(event.getSlot())
		{
			case 45:
			{
				if(event.getInventory().getItem(45).getData().getData()==14)
				{
					if(event.getInventory().getItem(53).getData().getData()==11)
					{
						byte needSlot = 0;
						byte mySlot = 0;
						
						for(int count = 0; count < 4; count ++)
							for(int count2 = 10; count2 < 13; count2 ++)
							{
								ItemStack item = target.getOpenInventory().getItem(count2+(count*9));
								if(item != null)
								{
									if(item.getTypeId()==160&&item.getData().getData()==8)
										if(item.hasItemMeta())
											if(item.getItemMeta().hasDisplayName())
											{
												if(!ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("[�ƹ��͵� �÷����� ����]"))
													needSlot++;
											}
											else
												needSlot++;
										else
											needSlot++;
									else
										needSlot++;
								}
							}
						
						for(int count = 0; count < 36;count++)
							if(player.getInventory().getItem(count)==null)
								mySlot++;
						if(mySlot < needSlot)
						{
							player.sendMessage("��c[��ȯ] : ����� �κ��丮 ������ �����մϴ�!");
							target.sendMessage("��c[��ȯ] : ������ �κ��丮 ������ �����մϴ�!");
							return;
						}
						
						needSlot=0;
						mySlot=0;

						for(int count = 0; count < 4; count ++)
							for(int count2 = 10; count2 < 13; count2 ++)
							{
								ItemStack item = event.getInventory().getItem(count2+(count*9));
								if(item != null)
								{
									if(item.getTypeId()==160&&item.getData().getData()==8)
										if(item.hasItemMeta())
											if(item.getItemMeta().hasDisplayName())
											{
												if(!ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals("[�ƹ��͵� �÷����� ����]"))
													needSlot++;
											}
											else
												needSlot++;
										else
											needSlot++;
									else
										needSlot++;
								}
							}

						for(int count = 0; count < 36;count++)
							if(target.getInventory().getItem(count)==null)
								mySlot++;

						if(mySlot < needSlot)
						{
							player.sendMessage("��c[��ȯ] : ������ �κ��丮 ������ �����մϴ�!");
							target.sendMessage("��c[��ȯ] : ����� �κ��丮 ������ �����մϴ�!");
							return;
						}
						
						for(int count = 0; count < 4; count ++)
						{
							for(int count2 = 10; count2 < 13; count2 ++)
							{
								if(event.getInventory().getItem(count2+(count*9))!=null)
								{
									if(event.getInventory().getItem(count2+(count*9)).hasItemMeta())
									{
										if(event.getInventory().getItem(count2+(count*9)).getItemMeta().hasLore()==false&&
												event.getInventory().getItem(count2+(count*9)).getItemMeta().hasDisplayName())
										{
											if(!event.getInventory().getItem(count2+(count*9)).getItemMeta().getDisplayName().equals("��7��l[�ƹ��͵� �÷����� ����]"))
												target.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
										}
										else
											target.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
									}
									else
										target.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
								}
								else
									target.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
							}
						}
	
						for(int count = 0; count < 4; count ++)
						{
							for(int count2 = 10; count2 < 13; count2 ++)
							{
								if(target.getOpenInventory().getItem(count2+(count*9))!=null)
								{
									if(target.getOpenInventory().getItem(count2+(count*9)).hasItemMeta())
									{
										if(target.getOpenInventory().getItem(count2+(count*9)).getItemMeta().hasLore()==false&&
												target.getOpenInventory().getItem(count2+(count*9)).getItemMeta().hasDisplayName())
										{
											if(!target.getOpenInventory().getItem(count2+(count*9)).getItemMeta().getDisplayName().equals("��7��l[�ƹ��͵� �÷����� ����]"))
												player.getInventory().addItem(target.getOpenInventory().getItem(count2+(count*9)));
										}
										else
											player.getInventory().addItem(target.getOpenInventory().getItem(count2+(count*9)));
									}
									else
										player.getInventory().addItem(target.getOpenInventory().getItem(count2+(count*9)));
								}
								else
									player.getInventory().addItem(target.getOpenInventory().getItem(count2+(count*9)));
							}
						}
						target.getOpenInventory().setItem(0, new ItemStack(2));
						event.getInventory().setItem(0, new ItemStack(2));
						target.closeInventory();
						player.closeInventory();
					}
					else
					{
						ItemStack Icon = new MaterialData(35, (byte) 11).toItemStack();
						Icon.setAmount(1);
						ItemMeta Icon_Meta = Icon.getItemMeta();
						Icon_Meta.setDisplayName("��9��l[ �غ� �Ϸ� ]");
						Icon.setItemMeta(Icon_Meta);
						event.getInventory().setItem(45, Icon);
						target.getOpenInventory().setItem(53, Icon);
						SoundEffect.SP(player, Sound.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
						SoundEffect.SP(target, Sound.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
					}
				}
				else
				{
					ItemStack Icon = new MaterialData(35, (byte) 14).toItemStack();
					Icon.setAmount(1);
					ItemMeta Icon_Meta = Icon.getItemMeta();
					Icon_Meta.setDisplayName("��c��l[  �����  ]");
					Icon.setItemMeta(Icon_Meta);
					event.getInventory().setItem(45, Icon);
					target.getOpenInventory().setItem(53, Icon);
					SoundEffect.SP(player, Sound.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
					SoundEffect.SP(target, Sound.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
				}
			}
			return;
			case 49:
			{
				event.getInventory().setItem(0, new ItemStack(138,1));
				for(int count = 0; count < 4; count ++)
				{
					for(int count2 = 10; count2 < 13; count2 ++)
					{
						if(event.getInventory().getItem(count2+(count*9))!=null)
						{
							if(event.getInventory().getItem(count2+(count*9)).hasItemMeta())
							{
								if(event.getInventory().getItem(count2+(count*9)).getItemMeta().hasLore()==false&&
										event.getInventory().getItem(count2+(count*9)).getItemMeta().hasDisplayName())
								{
									if(!event.getInventory().getItem(count2+(count*9)).getItemMeta().getDisplayName().equals("��7��l[�ƹ��͵� �÷����� ����]"))
										player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
								}
								else
									player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
							}
							else
								player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
						}
						else
							player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
					}
				}
				target.closeInventory();
				player.closeInventory();
			}
			return;
		}
		return;
	}
	
	
	public void ExchangeGUI_Close(InventoryCloseEvent event)
	{
		
		if(event.getInventory().getItem(0).getTypeId() == 138)
		{
			SoundEffect.SP((Player)event.getPlayer(), Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.8F);
			event.getPlayer().sendMessage("��c[��ȯ] : ��ȯ�� ����Ͽ����ϴ�!");
		}
		else if(event.getInventory().getItem(0).getTypeId()== 397)
		{
			Player target = Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getDisplayName()));
			Player player = (Player) event.getPlayer();
			SoundEffect.SP((Player)event.getPlayer(), Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.8F);
			event.getPlayer().sendMessage("��c[��ȯ] : ��ȯ�� ��ҵǾ����ϴ�!");
			for(int count = 0; count < 4; count ++)
			{
				for(int count2 = 10; count2 < 13; count2 ++)
				{
					if(event.getInventory().getItem(count2+(count*9))!=null)
					{
						if(event.getInventory().getItem(count2+(count*9)).hasItemMeta())
						{
							if(event.getInventory().getItem(count2+(count*9)).getItemMeta().hasLore()==false&&
									event.getInventory().getItem(count2+(count*9)).getItemMeta().hasDisplayName())
							{
								if(!event.getInventory().getItem(count2+(count*9)).getItemMeta().getDisplayName().equals("��7��l[�ƹ��͵� �÷����� ����]"))
									player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
							}
							else
								player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
						}
						else
							player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
					}
					else
						player.getInventory().addItem(event.getInventory().getItem(count2+(count*9)));
				}
			}
			event.getInventory().setItem(0, new ItemStack(166,1));
			target.closeInventory();
		}
		else if(event.getInventory().getItem(0).getTypeId()== 2)
		{
			SoundEffect.SP((Player)event.getPlayer(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
			event.getPlayer().sendMessage("��a[��ȯ] : ��ȯ�� ����Ǿ����ϴ�!");
		}
	}
	
	
	
	public void SetFriends(Player player, Player target)
	{
	  	YamlLoader FriendsList = new YamlLoader();
		FriendsList.getConfig("Friend/"+player.getUniqueId().toString()+".yml");
		if(FriendsList.contains("Name")==false)
		{
			FriendsList.set("Name", player.getName());
			FriendsList.createSection("Friends");
			FriendsList.createSection("Waitting");
			FriendsList.saveConfig();
		}
	  	YamlLoader SideFriendsList = new YamlLoader();
		SideFriendsList.getConfig("Friend/"+target.getUniqueId().toString()+".yml");
		if(SideFriendsList.contains("Name")==false)
		{
			SideFriendsList.set("Name", target.getName());
			SideFriendsList.createSection("Friends");
			SideFriendsList.createSection("Waitting");
			SideFriendsList.saveConfig();
		}

		
		Long AddTime = new util.ETC().getSec();
		Object[] Friend = FriendsList.getConfigurationSection("Waitting").getKeys(false).toArray();
		Object[] SideFriend = SideFriendsList.getConfigurationSection("Waitting").getKeys(false).toArray();

		for(int counter= 0; counter <SideFriend.length;counter++)
		{
			if(SideFriend[counter].toString().equals(player.getName()))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[ģ��] : �̹� ���濡�� ģ�� ��û�� �� �����Դϴ�!");
				return;
			}
		}
		
		for(int counter= 0; counter < Friend.length;counter++)
		{
			if(Friend[counter].toString().equals(target.getName()))
			{
				SideFriendsList.removeKey("Waitting."+player.getName());
				SideFriendsList.set("Friends."+player.getName(),AddTime);
				SideFriendsList.saveConfig();
				FriendsList.removeKey("Waitting."+target.getName());
				FriendsList.set("Friends."+target.getName(),AddTime);
				FriendsList.saveConfig();
				SoundEffect.SP(player, Sound.BLOCK_LAVA_POP,1.0F, 1.2F);
				player.sendMessage("��b[ģ��] : ��e"+target.getName()+"��b�Բ��� ģ���� ��ϵǾ����ϴ�!");
				if(target.isOnline())
				{
					SoundEffect.SP(target, Sound.BLOCK_LAVA_POP,1.0F, 1.2F);
					target.sendMessage("��b[ģ��] : ��e"+player.getName()+"��b�Բ��� ģ���� ��ϵǾ����ϴ�!");
				}
				return;
			}
		}
		Friend = FriendsList.getConfigurationSection("Friends").getKeys(false).toArray();
		for(int counter= 0; counter < Friend.length;counter++)
		{
			if(Friend[counter].toString().equals(target.getName()))
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				player.sendMessage("��c[ģ��] : �̹� ģ���� ��ϵ� �÷��̾� �Դϴ�!");
				return;
			}
		}
		SideFriendsList.removeKey("Friends."+player.getName());
		SideFriendsList.set("Waitting."+player.getName(), AddTime);
		SideFriendsList.saveConfig();
		SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
		player.sendMessage("��e[ģ��] : ���濡�� ģ�� ��û�� �Ͽ����ϴ�!");
		if(target.isOnline())
		{
			SoundEffect.SP(target, Sound.ENTITY_WOLF_AMBIENT,1.0F, 1.0F);
			target.sendMessage("��e[ģ��] : ��f"+player.getName()+"��e�Բ��� ģ�� ��û�� �ϼ̽��ϴ�!");
			target.sendMessage("��6/ģ����f ��ɾ �Է��Ͽ� ģ�� ��û�� Ȯ�� �� �ּ���!");
		}
	}
	
	public void FriendJoinQuitMessage(Player player, boolean isJoinMessage)
	{
	  	YamlLoader FriendsList = new YamlLoader();
		FriendsList.getConfig("Friend/"+player.getUniqueId().toString()+".yml");
		if(FriendsList.contains("Name")==false)
		{
			FriendsList.set("Name", player.getName());
			FriendsList.createSection("Friends");
			FriendsList.createSection("Waitting");
			FriendsList.saveConfig();
			return;
		}
		Player target = null;
	  	YamlLoader SideFriendsList = new YamlLoader();
		
		Object[] Friend = FriendsList.getConfigurationSection("Friends").getKeys(false).toArray();
		for(int counter= 0; counter < Friend.length;counter++)
		{
			target = Bukkit.getServer().getPlayer(Friend[counter].toString());
			if(target!=null)
			if(target.isOnline())
			{
				SideFriendsList.getConfig("Friend/"+target.getUniqueId().toString()+".yml");
				if(SideFriendsList.contains("Name"))
				{
					if(SideFriendsList.contains("Friends."+player.getName()))
					{
						if(isJoinMessage)
						{
							SoundEffect.SP(target, Sound.BLOCK_WOODEN_DOOR_OPEN, 0.5F, 1.0F);
							target.sendMessage("��a��l[����] ��f��l"+player.getName());
						}
						else
						{
							SoundEffect.SP(target, Sound.BLOCK_WOODEN_DOOR_CLOSE, 0.5F, 1.0F);
							target.sendMessage("��c��l[����] ��7��l"+player.getName());
						}
						break;
					}
				}
			}
		}
	}

	public void AddExchangeTarget(Player player, Player target)
	{
		
		if(ServerTickMain.PlayerTaskList.containsKey(target.getName())==true)
		{
			SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
			player.sendMessage("��c[��ȯ] : �ش� �÷��̾�� ���� �ٸ� ��û�� ó���ϰ� �ֽ��ϴ�.");
			player.sendMessage("��7(��� �� �ٽ� �õ� �� ������.)");
			return;
		}
		if(ServerTickMain.PlayerTaskList.containsKey(player.getName())==true)
		{
			SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
			player.sendMessage("��c[��ȯ] : ����� ���� ��û���� ���� ó���ؾ� �մϴ�.");
			return;
		}
		long UTC = servertick.ServerTickMain.nowUTC-1;
		ServerTickMain.PlayerTaskList.put(player.getName(), ""+UTC);
		ServerTickMain.PlayerTaskList.put(player.getName(), ""+UTC);
		player.closeInventory();
		servertick.ServerTickObject STSO = new servertick.ServerTickObject(UTC, "P_EC");
		STSO.setCount(0);//Ƚ �� �ʱ�ȭ
		STSO.setMaxCount(10);//��ȯ ��û �ִ� ��� �ð�
		STSO.setString((byte)0, player.getName());//��ȯ ��û �� �÷��̾� �̸� ����(�÷��̾ ������������ Ȯ���ؾ� �ϹǷ�)
		STSO.setString((byte)1, target.getName());//��ȯ ��û ���� �÷��̾� �̸� ����(�÷��̾ ������������ Ȯ���ؾ� �ϹǷ�)
		servertick.ServerTickMain.Schedule.put(UTC, STSO);
		SoundEffect.SP(player, Sound.BLOCK_NOTE_PLING, 1.0F, 1.0F);
		player.sendMessage("��e[��ȯ] : ���濡�� ��ȯ ��û�� �Ͽ����ϴ�!");
		target.sendMessage("��e[��ȯ] : ��f"+ChatColor.BOLD+player.getName()+"��a �Բ��� ��ȯ ��û�� �Ͽ����ϴ�!");
		target.sendMessage("��6/������f ����� ��û�� �����մϴ�.");
		target.sendMessage("��6/������f ����� ��û�� �����մϴ�.");
	}

}
