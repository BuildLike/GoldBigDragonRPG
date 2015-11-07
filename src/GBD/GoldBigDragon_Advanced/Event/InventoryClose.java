package GBD.GoldBigDragon_Advanced.Event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;


import GBD.GoldBigDragon_Advanced.Main.Main;
import GBD.GoldBigDragon_Advanced.Main.UserDataObject;

public class InventoryClose
{
	public void InventoryCloseRouter(InventoryCloseEvent event)
	{
		Player player = (Player) event.getPlayer();
		String InventoryName = event.getInventory().getTitle();
		if(InventoryName.contains("��ü")&&InventoryName.contains("��ų")&&InventoryName.contains("���")&&InventoryName.contains(" : ")&&
			((Main.UserData.get(player).getType()=="Job"&&Main.UserData.get(player).getString((byte)2) != null&&Main.UserData.get(player).getString((byte)3) != null)||
			Main.UserData.get(player).getType()=="Skill"&&Main.UserData.get(player).getString((byte)1)==null&&Main.UserData.get(player).getString((byte)2)==null
			&&Main.UserData.get(player).getString((byte)3)==null&&Main.UserData.get(player).getString((byte)4)==null))
			Main.UserData.get(player).clearAll();
		if(InventoryName.contains("��ü") &&InventoryName.contains("����")==false&&InventoryName.contains("[MapleStory]")==false&&InventoryName.contains("[Mabinogi]")==false
				&&InventoryName.contains("��ų")==false&&InventoryName.contains("����")==false)
		{
			boolean ChooseQuestGUI = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			if(ChooseQuestGUI==true)
				Main.UserData.get(player).setString((byte)1, null);
		}
		else if(InventoryName.contains("����") == true)
		{InventoryClose_Monster(event, InventoryName);return;	}
		else if(InventoryName.contains("����")||InventoryName.contains("�����"))
		{InventoryClose_Area(event, InventoryName);return;}
		else if(InventoryName.contains("NPC")== true)
		{InventoryClose_NPC(event, InventoryName);return;}
		else if(InventoryName.contains("�ʽ���")== true)
		{InventoryClose_NewBie(event, InventoryName);return;}
		if(player.isOp())
			if(Main.UserData.containsKey(player)==false)
				Main.UserData.put(player, new UserDataObject(player));
			else
				if(Main.UserData.get(player).getType() == "Quest")
				{InventoryClose_Quest(event, InventoryName, player);return;	}
			
	}

	private void InventoryClose_Quest(InventoryCloseEvent event, String InventoryName, Player player)
	{
		if(Main.UserData.get(player).getString((byte)1)!=null)
		{
			if(Main.UserData.get(player).getString((byte)1)!=null
				&&Main.UserData.get(player).getString((byte)2)!=null
				&&Main.UserData.get(player).getString((byte)3)!=null)
			{
				GBD.GoldBigDragon_Advanced.GUI.QuestGUI QGUI = new GBD.GoldBigDragon_Advanced.GUI.QuestGUI();
				if(InventoryName.contains("��ƾ�"))
				{
					QGUI.ItemAddInvnetoryClose(event);
    		    	Main.UserData.get(player).setBoolean((byte)1, false);
				}
				else if(InventoryName.contains("����"))
				{
					QGUI.PresentAddInvnetoryClose(event);
				}
			}
			else if(InventoryName.contains("��ü"))
			{
				Main.UserData.get(player).clearAll();
			}
			return;
		}
	}

	private void InventoryClose_Monster(InventoryCloseEvent event, String InventoryName)
	{
	    GBD.GoldBigDragon_Advanced.ETC.Monster MC = new GBD.GoldBigDragon_Advanced.ETC.Monster();
		if(InventoryName.contains("���") == true)
			MC.InventorySetting(event);
	}
	
	private void InventoryClose_Area(InventoryCloseEvent event, String InventoryName)
	{
	    GBD.GoldBigDragon_Advanced.ETC.Area A = new GBD.GoldBigDragon_Advanced.ETC.Area();
		 if(InventoryName.contains(ChatColor.stripColor("�����")) == true)
			A.BlockItemSettingInventoryClose(event);
		 else if(InventoryName.contains(ChatColor.stripColor("���")) == true)
			A.FishingSettingInventoryClose(event);
	}
	
	private void InventoryClose_NPC(InventoryCloseEvent event, String InventoryName)
	{
	    GBD.GoldBigDragon_Advanced.ETC.NPC NP = new GBD.GoldBigDragon_Advanced.ETC.NPC();
		 if(InventoryName.contains(ChatColor.stripColor("��")) == true)
			 NP.InventoryClose_NPC(event);
	}

	private void InventoryClose_NewBie(InventoryCloseEvent event,String InventoryName)
	{
	    GBD.GoldBigDragon_Advanced.GUI.NewBieGUI NGUI = new GBD.GoldBigDragon_Advanced.GUI.NewBieGUI();
		 if(InventoryName.contains(ChatColor.stripColor("���̵�"))
			 ||InventoryName.contains(ChatColor.stripColor("����")))
			 NGUI.InventoryClose_NewBie(event);
		return;
	}
	
}
