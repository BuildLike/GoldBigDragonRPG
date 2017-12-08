package party;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import effect.SoundEffect;
import user.EquipGui;
import util.UtilGui;

public final class PartyGUI extends UtilGui
{
	public void PartyGUI_Main(Player player)
	{
		String UniqueCode = "��0��0��4��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0��Ƽ");
		removeFlagStack("��f��l��Ƽ ���", 340,0,1,Arrays.asList("��7���� ������ ��Ƽ ����� ���ϴ�."), 12, inv);
		if(main.MainServerOption.partyJoiner.containsKey(player)==false)
		{
			removeFlagStack("��f��l��Ƽ ����", 323,0,1,Arrays.asList("��7���ο� ��Ƽ�� �����մϴ�."), 10, inv);
			removeFlagStack("��f��l��Ƽ ����", 386,0,1,Arrays.asList("��7�����Ǿ� �ִ� ��Ƽ�� �����մϴ�."), 12, inv);
		}
		else
		{
			removeFlagStack("��f��l��Ƽ ����", 397,3,1,Arrays.asList("��7���� ��Ƽ�� ������ �˾ƺ��ϴ�.","��7������ ���, ��Ƽ �����","��7���� ��ų ���� �ֽ��ϴ�."), 10, inv);
			removeFlagStack("��f��l��Ƽ Ż��", 52,0,1,Arrays.asList("��7��Ƽ���� Ż���մϴ�."), 14, inv);
			if(main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).getLeader().equalsIgnoreCase(player.getName()) == true)
			{
				removeFlagStack("��f��l���� ����", 386,0,1,Arrays.asList("��7��Ƽ�� ������ �����մϴ�."), 28, inv);
				removeFlagStack("��f��l�ο� ����", 386,0,1,Arrays.asList("��7���� �ο��� �����մϴ�."), 30, inv);
				if(main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).getLock() == false) removeFlagStack("��9��l��Ƽ ����", 54,0,1,Arrays.asList("��7��Ƽ ���� ��û�� �޽��ϴ�."), 34, inv);
				else removeFlagStack("��c��l��Ƽ ���", 130,0,1,Arrays.asList("��7��Ƽ ���� ��û�� ���� �ʽ��ϴ�."), 34, inv);
			}
		}
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7��Ÿ â���� ���ư��ϴ�."), 36, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		player.openInventory(inv);
	}
	
	public void PartyListGUI(Player player, short page)
	{
		String UniqueCode = "��0��0��4��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��Ƽ ��� : " + (page+1));

		Object[] a = main.MainServerOption.party.keySet().toArray();
		if(main.MainServerOption.party.size() <= 0)
		{
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage("��c[��Ƽ] : ������ ��Ƽ�� �����ϴ�!");
			player.sendMessage("��6/��Ƽ ���� <�̸�>");
			return;
		}
		else
		{
			byte loc=0;
			for(int count = page*45; count < main.MainServerOption.party.size();count++)
			{
				if(count > main.MainServerOption.party.size() || loc >= 45) break;
				if(main.MainServerOption.party.get(a[count]).getLock()==false)
					removeFlagStack("��f��l" + main.MainServerOption.party.get(a[count]).getTitle(), 54,0,1,Arrays.asList("��7��Ƽ ���� �ð� : "+ChatColor.DARK_GRAY+a[count],"","��b��Ƽ�� : ��8"+main.MainServerOption.party.get(a[count]).getLeader(),"��b�ο� : ��8"+ main.MainServerOption.party.get(a[count]).getPartyMembers()+"/"+main.MainServerOption.party.get(a[count]).getCapacity()), loc, inv);
				else
					removeFlagStack("��f��l" + main.MainServerOption.party.get(a[count]).getTitle(), 130,0,1,Arrays.asList("��7��Ƽ ���� �ð� : "+ChatColor.DARK_GRAY+a[count],"","��b��Ƽ�� : ��8"+main.MainServerOption.party.get(a[count]).getLeader(),"��b�ο� : ��8"+ main.MainServerOption.party.get(a[count]).getPartyMembers()+"/"+main.MainServerOption.party.get(a[count]).getCapacity(),"","��c[���]","��c��Ƽ ������ �Ͻ÷���","��c��Ƽ�忡�� �����ϼ���!"), loc, inv);
				loc++;
			}
		}
		
		if(a.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void PartyMemberInformationGUI(Player player, short page, long PartyCreateTime, boolean isLeaderChange)
	{
		Inventory inv = null;
		String UniqueCode = "��0��0��4��0��2��r";
		if(!isLeaderChange)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��Ƽ ��� : " + (page+1));
		else
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��Ƽ ���� ��ü : " + (page+1));
			
		Player[] Member = main.MainServerOption.party.get(PartyCreateTime).getMember();
			byte loc=0;
			for(int count = page*45; count < Member.length;count++)
			{
				ItemStack ph = null;
				if(count > Member.length || loc >= 45) break;
				Damageable pl = Member[count];
				if(player.getName().equals(main.MainServerOption.party.get(PartyCreateTime).getLeader()))
				{
					if(!isLeaderChange)
					{
						if(!Member[count].getName().equals(main.MainServerOption.party.get(PartyCreateTime).getLeader()))
							ph = getPlayerSkull("��f��l"+Member[count].getName(), 1, Arrays.asList("","��7��l[   ����   ]","��c��l"+(int)pl.getHealth()+"��7��l / ��c��l"+(int)pl.getMaxHealth(),
									"","��7��l[   ��ġ   ]","��3"+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"","��f��l[   �Ϲ� ���   ]","","��e��l[ �� Ŭ���� ��� ���� ]","��c��l[Shift + �� Ŭ���� ����]"), Member[count].getName());
						else
							ph = getPlayerSkull("��e��l"+Member[count].getName(), 1, Arrays.asList("","��7��l[   ����   ]","��c��l"+(int)pl.getHealth()+"��7��l / ��c��l"+(int)pl.getMaxHealth(),
									"","��7��l[   ��ġ   ]","��3"+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"","��e��l[   ��Ƽ ����   ]","","��e��l[ �� Ŭ���� ��� ���� ]"), Member[count].getName());
					}
					else
					{
						if(!Member[count].getName().equals(main.MainServerOption.party.get(PartyCreateTime).getLeader()))
							ph = getPlayerSkull("��f��l"+Member[count].getName(), 1, Arrays.asList("","��7��l[   ����   ]","��c��l"+(int)pl.getHealth()+"��7��l / ��c��l"+(int)pl.getMaxHealth(),
									"","��7��l[   ��ġ   ]","��3"+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"","��f��l[   �Ϲ� ���   ]","","��e��l[ �� Ŭ���� ���� ���� ]"), Member[count].getName());
						else
							ph = getPlayerSkull("��e��l"+Member[count].getName(), 1, Arrays.asList("","��7��l[   ����   ]","��c��l"+(int)pl.getHealth()+"��7��l / ��c��l"+(int)pl.getMaxHealth(),
									"","��7��l[   ��ġ   ]","��3"+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"","��e��l[   ��Ƽ ����   ]","","��c��l[ ���� ���� �Ұ��� ]"), Member[count].getName());
					}
				}
				else
				{
					if(!Member[count].getName().equals(main.MainServerOption.party.get(PartyCreateTime).getLeader()))
						ph = getPlayerSkull("��f��l"+Member[count].getName(), 1, Arrays.asList("","��7��l[   ����   ]","��c��l"+(int)pl.getHealth()+"��7��l / ��c��l"+(int)pl.getMaxHealth(),
								"","��7��l[   ��ġ   ]","��3"+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"","��f��l[   �Ϲ� ���   ]","","��e��l[ �� Ŭ���� ��� ���� ]"), Member[count].getName());
					else
						ph = getPlayerSkull("��e��l"+Member[count].getName(), 1, Arrays.asList("","��7��l[   ����   ]","��c��l"+(int)pl.getHealth()+"��7��l / ��c��l"+(int)pl.getMaxHealth(),
								"","��7��l[   ��ġ   ]","��3"+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"","��e��l[   ��Ƽ ����   ]","","��e��l[ �� Ŭ���� ��� ���� ]"), Member[count].getName());
				}

				stackItem(ph, loc, inv);
				loc++;
			}
		if(Member.length-(page*44)>45)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);
		
		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void PartyGUI_MainClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		

		if(slot == 44)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 36)//���� ���
				new user.EtcGui().ETCGUI_Main(player);
			else if(slot == 10)//��Ƽ ���� / ��Ƽ ����
			{
				if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("��Ƽ ����"))
				{
					player.closeInventory();
					player.sendMessage("��6/��Ƽ ���� <�̸�>");
				}
				else
					PartyMemberInformationGUI(player, (short) 0, main.MainServerOption.partyJoiner.get(player),false);
			}
			else if(slot == 12)//��Ƽ ��� / ��Ƽ ����
				PartyListGUI(player, (short) 0);
			else if(slot == 14)//��Ƽ Ż��
			{
				player.closeInventory();
				main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).QuitParty(player);
			}
			else if(slot == 28)//���� ����
				PartyMemberInformationGUI(player, (short) 0, main.MainServerOption.partyJoiner.get(player),true);
			else if(slot == 30)//�ο� ����
			{
				player.closeInventory();
				player.sendMessage("��6/��Ƽ �ο� [�ο� ��]");
			}
			else if(slot == 34)//�ο� ����
			{
				main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).ChangeLock(player);
				PartyGUI_Main(player);
			}
		}
	}
	
	public void PartyListGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		
		if(slot == 53)//�ݱ�
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				PartyGUI_Main(player);
			else if(slot == 48)//���� ������
				PartyListGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				PartyListGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				if(main.MainServerOption.party.get(Long.parseLong(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" : ")[1]))).getLock())
				{
				  SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		    	  player.sendMessage("��c[��Ƽ] : �ش� ��Ƽ�� ��� �����Դϴ�! �������� �����ϼ���!");
				}
				else
				{
					main.MainServerOption.party.get(Long.parseLong(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" : ")[1]))).JoinParty(player);
					PartyGUI_Main(player);
				}
			}
		}
	}

	public void PartyMemberInformationGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//�ݱ�
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			boolean isLeaderChange = event.getInventory().getTitle().contains("��ü");
			if(slot == 45)//���� ���
				PartyGUI_Main(player);
			else if(slot == 48)//���� ������
				PartyMemberInformationGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2),main.MainServerOption.partyJoiner.get(player),isLeaderChange);
			else if(slot == 50)//���� ������
				PartyMemberInformationGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]),main.MainServerOption.partyJoiner.get(player),isLeaderChange);
			else
			{
				if(event.isLeftClick())
				{
					if(!isLeaderChange)
						new EquipGui().EquipWatchGUI(player, Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
					else
						main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).ChangeLeader(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
				else if(event.isRightClick()&&event.isShiftClick())
				{
					if(!isLeaderChange)
					{
						main.MainServerOption.party.get(main.MainServerOption.partyJoiner.get(player)).KickPartyMember(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						PartyMemberInformationGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1),main.MainServerOption.partyJoiner.get(player), isLeaderChange);
					}
				}
			}
		}
	}
}
