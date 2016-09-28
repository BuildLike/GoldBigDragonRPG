package GBD_RPG.Main_Event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class Main_InventoryClick
{
	public void InventoryClickRouter(InventoryClickEvent event, String InventoryName)
	{
		if(event.getClickedInventory() == null)
			return;
		if(event.getClickedInventory().getTitle().equalsIgnoreCase("container.inventory") == true)
		{
			if(InventoryName.compareTo("[NPC] ������ ��� ���� �ϼ���.")==0)
				new GBD_RPG.NPC.NPC_GUI().ItemFixGuiClick(event);
			return;
		}
		if(InventoryName.compareTo("��ȯ")==0)
		{new GBD_RPG.User.Equip_GUI().ExchangeGUIclick(event);return;}
		if (event.getCurrentItem() == null||event.getCurrentItem().getType() == Material.AIR||event.getCurrentItem().getAmount() == 0)
		{return;}
		
		if(InventoryName.compareTo("����")==0)
		{
		    if(event.getClickedInventory().getType() != InventoryType.CHEST)
		    {
		    	event.setCancelled(true);
		    	return;
		    }
		    new GBD_RPG.User.Stats_GUI().StatusInventoryclick(event); 
			return;
		}
		else if(InventoryName.compareTo("���� ��� ����")==0)
		{
			new GBD_RPG.Monster.Monster_Spawn().ArmorGUIClick(event);return;
	    }
		else if(InventoryName.compareTo("��� ����")==0)
	    {
		    new GBD_RPG.User.Equip_GUI().optionInventoryclick(event);return;
	    }
		else if(InventoryName.compareTo("�ɼ�")==0)
	    {
			new GBD_RPG.User.Option_GUI().optionInventoryclick(event);return;
	    }
		else if(InventoryName.compareTo("�ش� ����� ĳ�� ���� ������")==0)
	    {
		    new GBD_RPG.Area.Area_GUI().AreaBlockItemSettingGUIClick(event);return;
	    }
		else if(InventoryName.compareTo("���� ������")==0||InventoryName.compareTo("��Ȱ ������")==0)
	    {
		    new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_RescueOrReviveClick(event);return;
	    }
		else if(InventoryName.contains("��Ȱ"))
	    {
			new GBD_RPG.Corpse.Corpse_GUI().ReviveSelectClick(event);return;
	    }
		else if(InventoryName.compareTo("���� �ӽ�")==0)
	    {
			new GBD_RPG.Admin.Gamble_GUI().SlotMachine_PlayGUI_Click(event);return;
	    }
	    GBD_RPG.User.ETC_GUI EGUI = new GBD_RPG.User.ETC_GUI();
	    GBD_RPG.Quest.Quest_GUI QGUI = new GBD_RPG.Quest.Quest_GUI();
	    GBD_RPG.Job.Job_GUI JGUI = new GBD_RPG.Job.Job_GUI();
	    GBD_RPG.Skill.OPboxSkill_GUI SKGUI = new GBD_RPG.Skill.OPboxSkill_GUI();
	    GBD_RPG.Skill.UserSkill_GUI PSKGUI = new GBD_RPG.Skill.UserSkill_GUI();
		switch(InventoryName)
		{
			case "������ ���":
				PSKGUI.AddQuickBarGUIClick(event);
			break;
			case "�ý��� ����":
				JGUI.ChooseSystemGUIClick(event);
			break;
			case "��� ��� �Ͻðڽ��ϱ�?":
				QGUI.KeepGoingClick(event);
				break;
			case "���� ���":
			case "ä�� �ؾ� �� ��� ���":
			case "��� �ؾ� �� ���� ���":
				QGUI.ShowItemGUIInventoryClick(event); return;
			case "��Ÿ" : 
				EGUI.ETCInventoryclick(event);return;
			case "���̵�" : 
				EGUI.ETCInventoryclick(event); return;
			case "������Ʈ �߰�":
				QGUI.ObjectAddInventoryClick(event);return;
			default :
				if(InventoryName.contains("NPC"))
				{IC_NPC(event, InventoryName);return;}
				else if(InventoryName.contains("��Ƽ"))
				{new GBD_RPG.Party.Party_GUI().PartyGUIClickRouter(event, InventoryName);; return;}
				else if(InventoryName.contains("������"))
				{IC_Item(event, InventoryName);return;}
				else if(InventoryName.contains("����Ʈ"))
				{new GBD_RPG.Quest.Quest_GUI().QuestGUIClickRouter(event, InventoryName);;}
				else if(InventoryName.contains("��ϵ�"))
				{JGUI.AddedSkillsListGUIClick(event);return;}
				else if(InventoryName.contains("[MapleStory]"))
				{IC_MapleStory(event, InventoryName);return;}
				else if(InventoryName.contains("[Mabinogi]"))
				{IC_Mabinogi(event, InventoryName);return;}
				else if(InventoryName.contains("��ų"))
				{IC_Skill(event, InventoryName);return;}
				else if(InventoryName.contains("��ũ"))
				{SKGUI.SkillRankOptionGUIClick(event);return;}
				else if(InventoryName.contains("������"))
				{PSKGUI.MapleStory_MainSkillsListGUIClick(event);return;}
				else if(InventoryName.contains("ī�װ�"))
				{PSKGUI.Mabinogi_MainSkillsListGUIClick(event);return;}
				else if(InventoryName.contains("������"))
				{IC_OP(event, InventoryName);return;}
				else if(InventoryName.contains("�̺�Ʈ"))
				{IC_Event(event, InventoryName);return;}
				else if(InventoryName.contains("����"))
				{IC_Area(event, InventoryName);return;}
				else if(InventoryName.contains("������"))
				{IC_Upgrade(event, InventoryName);return;}
				else if(InventoryName.contains("�ʽ���"))
				{IC_NewBie(event, InventoryName);return;}
				else if(InventoryName.contains("����"))
				{IC_Monster(event, InventoryName);return;}
				else if(InventoryName.contains("����"))
				{IC_World(event, InventoryName);return;}
				else if(InventoryName.contains("����"))
				{IC_Warp(event, InventoryName);return;}
				else if(InventoryName.contains("��������"))
				{new OtherPlugins.SpellMain().ShowAllMaigcGUIClick(event);return;}
				else if(InventoryName.contains("ģ��"))
				{IC_Friend(event, InventoryName);return;}
				else if(InventoryName.contains("�׺�"))
				{IC_Navi(event, InventoryName);return;}
				else if(InventoryName.contains("����"))
				{IC_Gamble(event, InventoryName);return;}
				else if(InventoryName.contains("��ü"))
				{IC_Structure(event, InventoryName);return;}
				return;
		}
		return;
	}
	
	private void IC_NPC(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.NPC.NPC_GUI NPGUI = new GBD_RPG.NPC.NPC_GUI();
    	if(InventoryName.compareTo("NPC ���� ����")==0)
    		NPGUI.NPCJobClick(event, ChatColor.stripColor(event.getInventory().getItem(18).getItemMeta().getLore().get(1)));
    	else if(InventoryName.contains("NPC"))
	    {
        	if(InventoryName.contains("[NPC]"))
				NPGUI.NPCclickMain(event, InventoryName.split("C] ")[1]);	
        	else if(InventoryName.contains("����"))
			{
    			if(InventoryName.contains("����"))
    				NPGUI.WarpMainGUIClick(event);
    			else if(InventoryName.contains("���"))
    				NPGUI.WarperGUIClick(event);
			}
        	else if(InventoryName.contains("����"))
        	{
    			if(InventoryName.contains("����"))
    				NPGUI.UpgraderGUIClick(event);
    			else
    				NPGUI.SelectUpgradeRecipeGUIClick(event);
    				
        	}
        	else if(InventoryName.contains("����ĥ"))
				NPGUI.AddAbleSkillsGUIClick(event);
        	else if(InventoryName.contains("��"))
				NPGUI.RuneEquipGUIClick(event);
        	else if(InventoryName.contains("���"))
        	{
        		if(InventoryName.contains("����"))
            		NPGUI.TalkSettingGUIClick(event);
        		else
        			NPGUI.TalkGUIClick(event);
        	}
	    }
		return;
	}

	private void IC_MapleStory(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Job.Job_GUI JGUI = new GBD_RPG.Job.Job_GUI();

		if(InventoryName.contains("��ü"))
			JGUI.MapleStory_ChooseJobClick(event);
		else if(InventoryName.contains("����"))
			JGUI.MapleStory_JobSettingClick(event);
		return;
	}

	private void IC_Mabinogi(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Job.Job_GUI JGUI = new GBD_RPG.Job.Job_GUI();

		if(InventoryName.contains("��ü"))
			JGUI.Mabinogi_ChooseCategoryClick(event);
		else if(InventoryName.contains("����"))
			JGUI.MapleStory_JobSettingClick(event);
		else if(InventoryName.contains("����"))
			JGUI.Mabinogi_SkillSettingClick(event);
		return;
	}

	private void IC_Skill(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Skill.OPboxSkill_GUI SKGUI = new GBD_RPG.Skill.OPboxSkill_GUI();
	    GBD_RPG.Skill.UserSkill_GUI PSKGUI = new GBD_RPG.Skill.UserSkill_GUI();

		if(InventoryName.contains("��ü"))
			SKGUI.AllSkillsGUIClick(event);
		else if(InventoryName.contains("����"))
			SKGUI.IndividualSkillOptionGUIClick(event);
		else if(InventoryName.contains("����"))
			PSKGUI.SkillListGUIClick(event);
	    else if(InventoryName.contains("����"))
	    {
	    	GBD_RPG.CustomItem.UseableItem_GUI UIGUI = new GBD_RPG.CustomItem.UseableItem_GUI();
	    	UIGUI.SelectSkillGUIClick(event);
	    }
		return;
	}

	private void IC_OP(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.OPbox_GUI OPGUI = new GBD_RPG.Admin.OPbox_GUI();

	    if(InventoryName.contains("���̵�"))
	    	OPGUI.OPBoxGuideInventoryclick(event);
	    else if(InventoryName.contains("����"))
	    	OPGUI.OPBoxGUIInventoryclick(event);
	    else if(InventoryName.contains("�ɼ�"))
	    	OPGUI.OPBoxGUI_SettingInventoryClick(event);
	    else if(InventoryName.contains("��������"))
	    	OPGUI.OPBoxGUI_BroadCastClick(event);
	    else if(InventoryName.contains("����"))
	    	OPGUI.OPBoxGUI_StatChangeClick(event);
	    else if(InventoryName.contains("ȭ��"))
	    	OPGUI.OPBoxGUI_MoneyClick(event);
	    else if(InventoryName.contains("���"))
	    	OPGUI.OPBoxGUI_DeathClick(event);
	    return;
	}

	private void IC_Item(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Quest.Quest_GUI QGUI = new GBD_RPG.Quest.Quest_GUI();
	    GBD_RPG.CustomItem.CustomItem_GUI IGUI = new GBD_RPG.CustomItem.CustomItem_GUI();

	    if(InventoryName.contains("�Ҹ�")==true)
	    {
	    	GBD_RPG.CustomItem.UseableItem_GUI UIGUI = new GBD_RPG.CustomItem.UseableItem_GUI();
			if(InventoryName.contains("���"))
		    	UIGUI.UseableItemListGUIClick(event);
			else if(InventoryName.contains("Ÿ��"))
		    	UIGUI.ChooseUseableItemTypeGUIClick(event);
			else if(InventoryName.contains("��"))
		    	UIGUI.NewUseableItemGUIclick(event);
	    }
	    else
	    {
		    if(InventoryName.compareTo("��ƾ� �� ������ ���")==0||InventoryName.compareTo("������ ������ ���")==0)
		    {
				if(event.getSlot() == 8)
					event.getWhoClicked().closeInventory();
		    }
		    else  if(InventoryName.compareTo("���� ������ ���")==0)
				QGUI.SettingPresentClick(event);
		    else  if(InventoryName.contains("��"))
				IGUI.NewItemGUIclick(event);
		    else  if(InventoryName.compareTo("��ƾ� �� ������ ���")==0)
				QGUI.ShowItemGUIInventoryClick(event);
			else if(InventoryName.contains("���"))
				IGUI.ItemListInventoryclick(event);
			else if(InventoryName.contains("����"))
				new GBD_RPG.CustomItem.CustomItem_GUI().JobGUIClick(event);
	    }
		return;
	}

	private void IC_Area(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Area.Area_GUI AGUI = new GBD_RPG.Area.Area_GUI();
	    if(InventoryName.contains("����"))
			AGUI.AreaGUIInventoryclick(event);
	    else if(InventoryName.contains("��ü"))
	    	AGUI.AreaListGUIClick(event);
	    else if(InventoryName.contains("����"))
	    {
		    if(InventoryName.contains("��ü"))
		    	AGUI.AreaMonsterSettingGUIClick(event);
		    else if(InventoryName.contains("����"))
		    	AGUI.AreaAddMonsterListGUIClick(event);
		    else if(InventoryName.contains("����"))
		    	AGUI.AreaAddMonsterSpawnRuleGUIClick(event);
		    else if(InventoryName.contains("Ư��"))
		    	AGUI.AreaSpawnSpecialMonsterListGUIClick(event);
	    }
	    else if(InventoryName.contains("Ư��ǰ"))
	    	AGUI.AreaBlockSettingGUIClick(event);
	    else if(InventoryName.contains("���"))
	    	AGUI.AreaFishSettingGUIClick(event);
	    else if(InventoryName.contains("�����"))
	    	AGUI.AreaMusicSettingGUIClick(event);
		return;
	}

	private void IC_Upgrade(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.Upgrade_GUI UGUI = new GBD_RPG.Admin.Upgrade_GUI();
	    if(InventoryName.contains("���"))
	    	UGUI.UpgradeRecipeGUIClick(event);
	    else if(InventoryName.contains("����"))
	    	UGUI.UpgradeRecipeSettingGUIClick(event);
		return;
	}	
	
	private void IC_NewBie(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.NewBie_GUI NGUI = new GBD_RPG.Admin.NewBie_GUI();
	    if(InventoryName.contains("�ɼ�"))
	    	NGUI.NewBieGUIMainInventoryclick(event);
	    else if(InventoryName.contains("����")||InventoryName.contains("���̵�"))
	    	NGUI.NewBieSupportItemGUIInventoryclick(event);
	    else if(InventoryName.contains("�⺻��"))
	    	NGUI.NewBieQuestGUIInventoryclick(event);
		return;
	}
	
	private void IC_Monster(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Monster.Monster_GUI MGUI = new GBD_RPG.Monster.Monster_GUI();
	    if(InventoryName.contains("���"))
	    	MGUI.MonsterListGUIClick(event);
	    else if(InventoryName.contains("����"))
	    	MGUI.MonsterOptionSettingGUIClick(event);
	    else if(InventoryName.contains("����"))
	    	MGUI.MonsterPotionGUIClick(event);
		return;
	}	
	
	private void IC_World(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.WorldCreate_GUI WGUI = new GBD_RPG.Admin.WorldCreate_GUI();
	    if(InventoryName.contains("����"))
	    	WGUI.WorldCreateGUIClick(event);
		return;
	}	

	private void IC_Warp(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Warp.Warp_GUI WGUI = new GBD_RPG.Warp.Warp_GUI();
	    if(InventoryName.contains("���"))
	    	WGUI.WarpListGUIInventoryclick(event);
		return;
	}
	
	private void IC_Event(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.Event_GUI EGUI = new GBD_RPG.Admin.Event_GUI();
	    if(InventoryName.contains("����"))
	    	EGUI.AllPlayerGiveEventGUIclick(event);
		else if(InventoryName.contains("����"))
			EGUI.EventGUIInventoryclick(event);
		return;
	}		

	private void IC_Friend(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.User.ETC_GUI EGUI = new GBD_RPG.User.ETC_GUI();
	    if(InventoryName.contains("���"))
	    	EGUI.FriendsGUIclick(event);
	    if(InventoryName.contains("��û"))
	    	EGUI.WaittingFriendsGUIclick(event);
		return;
	}

	private void IC_Navi(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.Navigation_GUI NGUI = new GBD_RPG.Admin.Navigation_GUI();
	    if(InventoryName.contains("���"))
	    	NGUI.NavigationListGUIClick(event);
	    else if(InventoryName.contains("����"))
	    	NGUI.NavigationOptionGUIClick(event);
	    else if(InventoryName.contains("���"))
	    	NGUI.UseNavigationGUIClick(event);
		return;
	}

	private void IC_Gamble(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Admin.Gamble_GUI GGUI = new GBD_RPG.Admin.Gamble_GUI();
	    if(InventoryName.contains("����"))
	    	GGUI.GambleMainGUI_Click(event);
	    else if(InventoryName.contains("��ǰ"))
	    {
	    	if(InventoryName.contains("���"))
	    		GGUI.GamblePresentGUI_Click(event);
	    	else if(InventoryName.contains("����"))
	    		GGUI.GambleDetailViewPackageGUI_Click(event);
	    }
	    else if(InventoryName.contains("���"))
	    {
	    	if(InventoryName.contains("���"))
	    		GGUI.SlotMachine_MainGUI_Click(event);
	    	else if(InventoryName.contains("����"))
	    		GGUI.SlotMachine_DetailGUI_Click(event);
	    	else if(InventoryName.contains("����"))
	    		GGUI.SlotMachineCoinGUI_Click(event);
	    }
		return;
	}
	
	private void IC_Structure(InventoryClickEvent event, String InventoryName)
	{
	    GBD_RPG.Structure.Structure_GUI SGUI = new GBD_RPG.Structure.Structure_GUI();
	    if(InventoryName.contains("��ü"))
	    	SGUI.StructureListGUIClick(event);
	    else if(InventoryName.contains("Ÿ��"))
	    	SGUI.SelectStructureTypeGUIClick(event);
	    else if(InventoryName.contains("����"))
	    	SGUI.SelectStructureDirectionGUIClick(event);
		return;
	}
}