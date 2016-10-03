package GBD_RPG.NPC;

import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _NPCGUIManager
{
	//NPC GUI Click Unique Number = 07
	//NPC ���� GUI�� ���� ��ȣ�� 07�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� NPC ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//NPC ���� GUI
			new GBD_RPG.NPC.NPC_GUI().MainGUIClick(event, ChatColor.stripColor(event.getInventory().getName()).split("C] ")[1]);
		else if(SubjectCode.compareTo("01")==0)//NPC ���� ��� GUI
			new GBD_RPG.NPC.NPC_GUI().ShopGUIClick(event, ChatColor.stripColor(event.getInventory().getName()).split("C] ")[1]);
		else if(SubjectCode.compareTo("02")==0)//NPC ��ȭ GUI
			new GBD_RPG.NPC.NPC_GUI().TalkGUIClick(event, ChatColor.stripColor(event.getInventory().getName()).split("C] ")[1]);
		else if(SubjectCode.compareTo("03")==0)//NPC ��� ����Ʈ ��� GUI
			new GBD_RPG.NPC.NPC_GUI().QuestAddGUIClick(event);
		else if(SubjectCode.compareTo("04")==0)//���� ���� ����Ʈ ��� GUI
			new GBD_RPG.NPC.NPC_GUI().QuestListGUIClick(event);
		else if(SubjectCode.compareTo("05")==0)//NPC ���� ���� GUI
			new GBD_RPG.NPC.NPC_GUI().NPCjobGUIClick(event, ChatColor.stripColor(event.getInventory().getItem(18).getItemMeta().getLore().get(1)));
		else if(SubjectCode.compareTo("06")==0)//NPC ���� ��� GUI
			new GBD_RPG.NPC.NPC_GUI().WarpMainGUIClick(event);
		else if(SubjectCode.compareTo("07")==0)//NPC ���� ��� GUI
			new GBD_RPG.NPC.NPC_GUI().WarperGUIClick(event);
		else if(SubjectCode.compareTo("08")==0)//NPC ���� ��� GUI
			new GBD_RPG.NPC.NPC_GUI().UpgraderGUIClick(event);
		else if(SubjectCode.compareTo("09")==0)//NPC ������ ��� GUI
			new GBD_RPG.NPC.NPC_GUI().SelectUpgradeRecipeGUIClick(event);
		else if(SubjectCode.compareTo("0a")==0)//NPC �� ������ GUI
			new GBD_RPG.NPC.NPC_GUI().RuneEquipGUIClick(event);
		else if(SubjectCode.compareTo("0b")==0)//NPC ��ȭ ��� GUI
			new GBD_RPG.NPC.NPC_GUI().TalkGUIClick(event);
		else if(SubjectCode.compareTo("0c")==0)//NPC ��ȭ ���� GUI
			new GBD_RPG.NPC.NPC_GUI().TalkSettingGUIClick(event);
		else if(SubjectCode.compareTo("0d")==0)//NPC ����ĥ ��ų ���� GUI
			new GBD_RPG.NPC.NPC_GUI().AddAbleSkillsGUIClick(event);
		else if(SubjectCode.compareTo("0e")==0)//NPC ��ǰ ���� ���� GUI
			new GBD_RPG.NPC.NPC_GUI().ItemBuyGuiClick(event);
		else if(SubjectCode.compareTo("0f")==0)//NPC ���� GUI
			new GBD_RPG.NPC.NPC_GUI().ItemFixGuiClick(event);
		else if(SubjectCode.compareTo("10")==0)//NPC ���� ������ ��� GUI
			new GBD_RPG.NPC.NPC_GUI().PresentGuiClick(event);
		else if(SubjectCode.compareTo("11")==0)//NPC ���� ������ ���� GUI
			new GBD_RPG.NPC.NPC_GUI().PresentGuiClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("0a")==0)//NPC �� ������ GUI
			new GBD_RPG.NPC.NPC_GUI().RuneEquipGUIClose(event);
		else if(SubjectCode.compareTo("11")==0)//NPC ���� ������ ���� GUI
			new GBD_RPG.NPC.NPC_GUI().PresentInventoryClose(event);
	}
}
