package GBD_RPG.Party;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import GBD_RPG.User.Equip_GUI;
import GBD_RPG.Util.Util_GUI;

public final class Party_GUI extends Util_GUI
{
	public void PartyGUI_Main(Player player)
	{
		String UniqueCode = "��0��0��4��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 45, UniqueCode + "��0��Ƽ");
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��Ƽ ���", 340,0,1,Arrays.asList(ChatColor.GRAY + "���� ������ ��Ƽ ����� ���ϴ�."), 12, inv);
		if(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.containsKey(player)==false)
		{
			Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��Ƽ ����", 323,0,1,Arrays.asList(ChatColor.GRAY + "���ο� ��Ƽ�� �����մϴ�."), 10, inv);
			Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��Ƽ ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "�����Ǿ� �ִ� ��Ƽ�� �����մϴ�."), 12, inv);
		}
		else
		{
			Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��Ƽ ����", 397,3,1,Arrays.asList(ChatColor.GRAY + "���� ��Ƽ�� ������ �˾ƺ��ϴ�.",ChatColor.GRAY+"������ ���, ��Ƽ �����",ChatColor.GRAY+"���� ��ų ���� �ֽ��ϴ�."), 10, inv);
			Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "��Ƽ Ż��", 52,0,1,Arrays.asList(ChatColor.GRAY + "��Ƽ���� Ż���մϴ�."), 14, inv);
			if(GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).getLeader().equalsIgnoreCase(player.getName()) == true)
			{
				Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "���� ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "��Ƽ�� ������ �����մϴ�."), 28, inv);
				Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "�ο� ����", 386,0,1,Arrays.asList(ChatColor.GRAY + "���� �ο��� �����մϴ�."), 30, inv);
				if(GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).getLock() == false) Stack2(ChatColor.BLUE +""+ ChatColor.BOLD + "��Ƽ ����", 54,0,1,Arrays.asList(ChatColor.GRAY + "��Ƽ ���� ��û�� �޽��ϴ�."), 34, inv);
				else Stack2(ChatColor.RED +""+ ChatColor.BOLD + "��Ƽ ���", 130,0,1,Arrays.asList(ChatColor.GRAY + "��Ƽ ���� ��û�� ���� �ʽ��ϴ�."), 34, inv);
			}
		}
		Stack2(ChatColor.WHITE  + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "��Ÿ â���� ���ư��ϴ�."), 36, inv);
		Stack2(ChatColor.WHITE +""+ ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "�۾� ������ â�� �ݽ��ϴ�."), 44, inv);
		player.openInventory(inv);
	}
	
	public void PartyListGUI(Player player, short page)
	{
		String UniqueCode = "��0��0��4��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��Ƽ ��� : " + (page+1));

		Object[] a = GBD_RPG.Main_Main.Main_ServerOption.Party.keySet().toArray();
		if(GBD_RPG.Main_Main.Main_ServerOption.Party.size() <= 0)
		{
			new GBD_RPG.Effect.Effect_Sound().SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			player.sendMessage(ChatColor.RED + "[��Ƽ] : ������ ��Ƽ�� �����ϴ�!");
			player.sendMessage(ChatColor.GOLD + "/��Ƽ ���� <�̸�>");
			return;
		}
		else
		{
			byte loc=0;
			for(int count = page*45; count < GBD_RPG.Main_Main.Main_ServerOption.Party.size();count++)
			{
				if(count > GBD_RPG.Main_Main.Main_ServerOption.Party.size() || loc >= 45) break;
				if(GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getLock()==false)
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getTitle(), 54,0,1,Arrays.asList(ChatColor.GRAY + "��Ƽ ���� �ð� : "+ChatColor.DARK_GRAY+a[count],"",ChatColor.AQUA + "��Ƽ�� : "+ChatColor.GRAY+GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getLeader(),ChatColor.AQUA + "�ο� : "+ChatColor.GRAY + GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getPartyMembers()+"/"+GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getCapacity()), loc, inv);
				else
					Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getTitle(), 130,0,1,Arrays.asList(ChatColor.GRAY + "��Ƽ ���� �ð� : "+ChatColor.DARK_GRAY+a[count],"",ChatColor.AQUA + "��Ƽ�� : "+ChatColor.GRAY+GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getLeader(),ChatColor.AQUA + "�ο� : "+ChatColor.GRAY + GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getPartyMembers()+"/"+GBD_RPG.Main_Main.Main_ServerOption.Party.get(a[count]).getCapacity(),"",ChatColor.RED + "[���]",ChatColor.RED + "��Ƽ ������ �Ͻ÷���",ChatColor.RED +"��Ƽ�忡�� �����ϼ���!"), loc, inv);
				loc++;
			}
		}
		
		if(a.length-(page*44)>45)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	public void PartyMemberInformationGUI(Player player, short page, long PartyCreateTime, boolean isLeaderChange)
	{
		Inventory inv = null;
		String UniqueCode = "��0��0��4��0��2��r";
		if(isLeaderChange == false)
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��Ƽ ��� : " + (page+1));
		else
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��Ƽ ���� ��ü : " + (page+1));
			
		Player[] Member = GBD_RPG.Main_Main.Main_ServerOption.Party.get(PartyCreateTime).getMember();
			byte loc=0;
			for(int count = page*45; count < Member.length;count++)
			{
				ItemStack ph = null;
				if(count > Member.length || loc >= 45) break;
				Damageable pl = Member[count];
				if(player.getName().equals(GBD_RPG.Main_Main.Main_ServerOption.Party.get(PartyCreateTime).getLeader()))
				{
					if(isLeaderChange == false)
					{
						if(!Member[count].getName().equals(GBD_RPG.Main_Main.Main_ServerOption.Party.get(PartyCreateTime).getLeader()))
							ph = getPlayerSkull(ChatColor.WHITE+""+ChatColor.BOLD+Member[count].getName(), 1, Arrays.asList("",ChatColor.GRAY +""+ChatColor.BOLD+ "[   ����   ]",ChatColor.RED+""+ChatColor.BOLD+(int)pl.getHealth()+ChatColor.GRAY +""+ChatColor.BOLD+ " / "+ChatColor.RED+""+ChatColor.BOLD+(int)pl.getMaxHealth(),
									"",ChatColor.GRAY+""+ChatColor.BOLD+"[   ��ġ   ]",ChatColor.DARK_AQUA+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"",ChatColor.WHITE +"" + ChatColor.BOLD+ "[   �Ϲ� ���   ]","",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[ �� Ŭ���� ��� ���� ]",ChatColor.RED +"" + ChatColor.BOLD+ "[Shift + �� Ŭ���� ����]"), Member[count].getName());
						else
							ph = getPlayerSkull(ChatColor.YELLOW+""+ChatColor.BOLD+Member[count].getName(), 1, Arrays.asList("",ChatColor.GRAY +""+ChatColor.BOLD+ "[   ����   ]",ChatColor.RED+""+ChatColor.BOLD+(int)pl.getHealth()+ChatColor.GRAY +""+ChatColor.BOLD+ " / "+ChatColor.RED+""+ChatColor.BOLD+(int)pl.getMaxHealth(),
									"",ChatColor.GRAY+""+ChatColor.BOLD+"[   ��ġ   ]",ChatColor.DARK_AQUA+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[   ��Ƽ ����   ]","",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[ �� Ŭ���� ��� ���� ]"), Member[count].getName());
					}
					else
					{
						if(!Member[count].getName().equals(GBD_RPG.Main_Main.Main_ServerOption.Party.get(PartyCreateTime).getLeader()))
							ph = getPlayerSkull(ChatColor.WHITE+""+ChatColor.BOLD+Member[count].getName(), 1, Arrays.asList("",ChatColor.GRAY +""+ChatColor.BOLD+ "[   ����   ]",ChatColor.RED+""+ChatColor.BOLD+(int)pl.getHealth()+ChatColor.GRAY +""+ChatColor.BOLD+ " / "+ChatColor.RED+""+ChatColor.BOLD+(int)pl.getMaxHealth(),
									"",ChatColor.GRAY+""+ChatColor.BOLD+"[   ��ġ   ]",ChatColor.DARK_AQUA+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"",ChatColor.WHITE +"" + ChatColor.BOLD+ "[   �Ϲ� ���   ]","",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[ �� Ŭ���� ���� ���� ]"), Member[count].getName());
						else
							ph = getPlayerSkull(ChatColor.YELLOW+""+ChatColor.BOLD+Member[count].getName(), 1, Arrays.asList("",ChatColor.GRAY +""+ChatColor.BOLD+ "[   ����   ]",ChatColor.RED+""+ChatColor.BOLD+(int)pl.getHealth()+ChatColor.GRAY +""+ChatColor.BOLD+ " / "+ChatColor.RED+""+ChatColor.BOLD+(int)pl.getMaxHealth(),
									"",ChatColor.GRAY+""+ChatColor.BOLD+"[   ��ġ   ]",ChatColor.DARK_AQUA+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[   ��Ƽ ����   ]","",ChatColor.RED +"" + ChatColor.BOLD+ "[ ���� ���� �Ұ��� ]"), Member[count].getName());
					}
				}
				else
				{
					if(!Member[count].getName().equals(GBD_RPG.Main_Main.Main_ServerOption.Party.get(PartyCreateTime).getLeader()))
						ph = getPlayerSkull(ChatColor.WHITE+""+ChatColor.BOLD+Member[count].getName(), 1, Arrays.asList("",ChatColor.GRAY +""+ChatColor.BOLD+ "[   ����   ]",ChatColor.RED+""+ChatColor.BOLD+(int)pl.getHealth()+ChatColor.GRAY +""+ChatColor.BOLD+ " / "+ChatColor.RED+""+ChatColor.BOLD+(int)pl.getMaxHealth(),
								"",ChatColor.GRAY+""+ChatColor.BOLD+"[   ��ġ   ]",ChatColor.DARK_AQUA+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"",ChatColor.WHITE +"" + ChatColor.BOLD+ "[   �Ϲ� ���   ]","",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[ �� Ŭ���� ��� ���� ]"), Member[count].getName());
					else
						ph = getPlayerSkull(ChatColor.YELLOW+""+ChatColor.BOLD+Member[count].getName(), 1, Arrays.asList("",ChatColor.GRAY +""+ChatColor.BOLD+ "[   ����   ]",ChatColor.RED+""+ChatColor.BOLD+(int)pl.getHealth()+ChatColor.GRAY +""+ChatColor.BOLD+ " / "+ChatColor.RED+""+ChatColor.BOLD+(int)pl.getMaxHealth(),
								"",ChatColor.GRAY+""+ChatColor.BOLD+"[   ��ġ   ]",ChatColor.DARK_AQUA+pl.getLocation().getWorld().getName()+" "+(int)pl.getLocation().getX()+","+(int)pl.getLocation().getY()+","+(int)pl.getLocation().getZ(),"",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[   ��Ƽ ����   ]","",ChatColor.YELLOW +"" + ChatColor.BOLD+ "[ �� Ŭ���� ��� ���� ]"), Member[count].getName());
				}

				ItemStackStack(ph, loc, inv);
				loc++;
			}
		if(Member.length-(page*44)>45)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ������", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� �������� �̵� �մϴ�."), 48, inv);
		
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "���� ���", 323,0,1,Arrays.asList(ChatColor.GRAY + "���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2(ChatColor.WHITE + "" + ChatColor.BOLD + "�ݱ�", 324,0,1,Arrays.asList(ChatColor.GRAY + "â�� �ݽ��ϴ�."), 53, inv);
		player.openInventory(inv);
	}
	
	
	public void PartyGUI_MainClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
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
				new GBD_RPG.User.ETC_GUI().ETCGUI_Main(player);
			else if(slot == 10)//��Ƽ ���� / ��Ƽ ����
			{
				if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).compareTo("��Ƽ ����")==0)
				{
					player.closeInventory();
					player.sendMessage(ChatColor.GOLD + "/��Ƽ ���� <�̸�>");
				}
				else
					PartyMemberInformationGUI(player, (short) 0, GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player),false);
			}
			else if(slot == 12)//��Ƽ ��� / ��Ƽ ����
				PartyListGUI(player, (short) 0);
			else if(slot == 14)//��Ƽ Ż��
			{
				player.closeInventory();
				GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).QuitParty(player);
			}
			else if(slot == 28)//���� ����
				PartyMemberInformationGUI(player, (short) 0, GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player),true);
			else if(slot == 30)//�ο� ����
			{
				player.closeInventory();
				player.sendMessage(ChatColor.GOLD + "/��Ƽ �ο� [�ο� ��]");
			}
			else if(slot == 34)//�ο� ����
			{
				GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).ChangeLock(player);
				PartyGUI_Main(player);
			}
		}
	}
	
	public void PartyListGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		
		if(slot == 53)//�ݱ�
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				PartyGUI_Main(player);
			else if(slot == 48)//���� ������
				PartyListGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				PartyListGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				if(GBD_RPG.Main_Main.Main_ServerOption.Party.get(Long.parseLong(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" : ")[1]))).getLock())
				{
				  s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		    	  player.sendMessage(ChatColor.RED + "[��Ƽ] : �ش� ��Ƽ�� ��� �����Դϴ�! �������� �����ϼ���!");
				}
				else
				{
					GBD_RPG.Main_Main.Main_ServerOption.Party.get(Long.parseLong(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" : ")[1]))).JoinParty(player);
					PartyGUI_Main(player);
				}
			}
		}
	}

	public void PartyMemberInformationGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		if(slot == 53)//�ݱ�
		{
			s.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			s.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			boolean isLeaderChange = event.getInventory().getTitle().contains("��ü");
			if(slot == 45)//���� ���
				PartyGUI_Main(player);
			else if(slot == 48)//���� ������
				PartyMemberInformationGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2),GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player),isLeaderChange);
			else if(slot == 50)//���� ������
				PartyMemberInformationGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]),GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player),isLeaderChange);
			else
			{
				if(event.isLeftClick())
				{
					if(isLeaderChange == false)
						new Equip_GUI().EquipWatchGUI(player, Bukkit.getServer().getPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
					else
						GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).ChangeLeader(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				}
				else if(event.isRightClick()&&event.isShiftClick())
				{
					if(isLeaderChange == false)
					{
						GBD_RPG.Main_Main.Main_ServerOption.Party.get(GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player)).KickPartyMember(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
						PartyMemberInformationGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1),GBD_RPG.Main_Main.Main_ServerOption.PartyJoiner.get(player), isLeaderChange);
					}
				}
			}
		}
	}
}
