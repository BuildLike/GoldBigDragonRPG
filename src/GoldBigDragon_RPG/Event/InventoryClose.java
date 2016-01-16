package GoldBigDragon_RPG.Event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;


import GoldBigDragon_RPG.Main.Main;
import GoldBigDragon_RPG.Main.UserDataObject;

public class InventoryClose
{
	public void InventoryCloseRouter(InventoryCloseEvent event)
	{
		UserDataObject u = new UserDataObject();
		Player player = (Player) event.getPlayer();
		String InventoryName = event.getInventory().getTitle();
		if(InventoryName.contains("��ü")&&InventoryName.contains("��ų")&&InventoryName.contains("���")&&InventoryName.contains(" : ")&&
			((u.getType(player)=="Job"&&u.getString(player, (byte)2) != null&&u.getString(player, (byte)3) != null)||
			u.getType(player)=="Skill"&&u.getString(player, (byte)1)==null&&u.getString(player, (byte)2)==null
			&&u.getString(player, (byte)3)==null&&u.getString(player, (byte)4)==null))
			u.clearAll(player);
		if(InventoryName.contains("��ü") &&InventoryName.contains("����")==false&&InventoryName.contains("[MapleStory]")==false&&InventoryName.contains("[Mabinogi]")==false
				&&InventoryName.contains("��ų")==false&&InventoryName.contains("����")==false)
		{
			boolean ChooseQuestGUI = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			if(ChooseQuestGUI==true)
				u.setString(player, (byte)1, null);
		}
		else if(InventoryName.contains("����") == true)
		{InventoryClose_Monster(event, InventoryName);return;	}
		else if(InventoryName.contains("����")||InventoryName.contains("������"))
		{InventoryClose_Area(event, InventoryName);return;}
		else if(InventoryName.contains("NPC")== true)
		{InventoryClose_NPC(event, InventoryName);return;}
		else if(ChatColor.stripColor(InventoryName).compareTo("��ȯ")==0)
		{new GoldBigDragon_RPG.GUI.EquipGUI().InventoryClose_ExchangeGUI(event);return;}
		else if(InventoryName.contains("�ʽ���")== true)
		{InventoryClose_NewBie(event, InventoryName);return;}
		if(player.isOp())
			if(player.isOp()==false)
				u.UserDataInit(player);
			else
				if(u.getType(player) == "Quest")
				{
					InventoryClose_Quest(event, InventoryName, player);
					return;
				}
		return;
	}

	private void InventoryClose_Quest(InventoryCloseEvent event, String InventoryName, Player player)
	{
		UserDataObject u = new UserDataObject();
		if(u.getString(player, (byte)1)!=null)
		{
			if(u.getString(player, (byte)1)!=null
				&&u.getString(player, (byte)2)!=null
				&&u.getString(player, (byte)3)!=null)
			{
				GoldBigDragon_RPG.GUI.QuestGUI QGUI = new GoldBigDragon_RPG.GUI.QuestGUI();
				if(InventoryName.contains("��ƾ�"))
				{
					QGUI.ItemAddInvnetoryClose(event);
    		    	u.setBoolean(player, (byte)1, false);
				}
				else if(InventoryName.contains("����"))
				{
					QGUI.PresentAddInvnetoryClose(event);
				}
			}
			else if(InventoryName.contains("��ü"))
			{
				u.clearAll(player);
			}
			return;
		}
		return;
	}

	private void InventoryClose_Monster(InventoryCloseEvent event, String InventoryName)
	{
	    GoldBigDragon_RPG.ETC.Monster MC = new GoldBigDragon_RPG.ETC.Monster();
		if(InventoryName.contains("���") == true)
			MC.InventorySetting(event);
		return;
	}
	
	private void InventoryClose_Area(InventoryCloseEvent event, String InventoryName)
	{
	    GoldBigDragon_RPG.ETC.Area A = new GoldBigDragon_RPG.ETC.Area();
		 if(InventoryName.contains(ChatColor.stripColor("������")) == true)
			A.BlockItemSettingInventoryClose(event);
		 else if(InventoryName.contains(ChatColor.stripColor("���")) == true)
			A.FishingSettingInventoryClose(event);
		return;
	}
	
	private void InventoryClose_NPC(InventoryCloseEvent event, String InventoryName)
	{
	    GoldBigDragon_RPG.ETC.NPC NP = new GoldBigDragon_RPG.ETC.NPC();
		 if(InventoryName.contains(ChatColor.stripColor("��")) == true)
			 NP.InventoryClose_NPC(event);
		return;
	}

	private void InventoryClose_NewBie(InventoryCloseEvent event,String InventoryName)
	{
	    GoldBigDragon_RPG.GUI.NewBieGUI NGUI = new GoldBigDragon_RPG.GUI.NewBieGUI();
		 if(InventoryName.contains(ChatColor.stripColor("���̵�"))
			 ||InventoryName.contains(ChatColor.stripColor("����")))
			 NGUI.InventoryClose_NewBie(event);
		return;
	}
}