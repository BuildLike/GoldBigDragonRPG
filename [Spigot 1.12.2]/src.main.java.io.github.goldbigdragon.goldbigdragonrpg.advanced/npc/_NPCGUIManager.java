package npc;

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
		if(SubjectCode.equals("00"))//NPC ���� GUI
			new npc.NpcGui().MainGUIClick(event, ChatColor.stripColor(event.getInventory().getName()).split("C] ")[1]);
		else if(SubjectCode.equals("01"))//NPC ���� ��� GUI
			new npc.NpcGui().ShopGUIClick(event, ChatColor.stripColor(event.getInventory().getName()).split("C] ")[1]);
		else if(SubjectCode.equals("02"))//NPC ��ȭ GUI
			new npc.NpcGui().TalkGUIClick(event, ChatColor.stripColor(event.getInventory().getName()).split("C] ")[1]);
		else if(SubjectCode.equals("03"))//NPC ��� ����Ʈ ��� GUI
			new npc.NpcGui().QuestAddGUIClick(event);
		else if(SubjectCode.equals("04"))//���� ���� ����Ʈ ��� GUI
			new npc.NpcGui().QuestListGUIClick(event);
		else if(SubjectCode.equals("05"))//NPC ���� ���� GUI
			new npc.NpcGui().NPCjobGUIClick(event, ChatColor.stripColor(event.getInventory().getItem(18).getItemMeta().getLore().get(1)));
		else if(SubjectCode.equals("06"))//NPC ���� ��� GUI
			new npc.NpcGui().WarpMainGUIClick(event);
		else if(SubjectCode.equals("07"))//NPC ���� ��� GUI
			new npc.NpcGui().WarperGUIClick(event);
		else if(SubjectCode.equals("08"))//NPC ���� ��� GUI
			new npc.NpcGui().UpgraderGUIClick(event);
		else if(SubjectCode.equals("09"))//NPC ������ ��� GUI
			new npc.NpcGui().SelectUpgradeRecipeGUIClick(event);
		else if(SubjectCode.equals("0a"))//NPC �� ������ GUI
			new npc.NpcGui().RuneEquipGUIClick(event);
		else if(SubjectCode.equals("0b"))//NPC ��ȭ ��� GUI
			new npc.NpcGui().TalkGUIClick(event);
		else if(SubjectCode.equals("0c"))//NPC ��ȭ ���� GUI
			new npc.NpcGui().TalkSettingGUIClick(event);
		else if(SubjectCode.equals("0d"))//NPC ����ĥ ��ų ���� GUI
			new npc.NpcGui().AddAbleSkillsGUIClick(event);
		else if(SubjectCode.equals("0e"))//NPC ��ǰ ���� ���� GUI
			new npc.NpcGui().ItemBuyGuiClick(event);
		else if(SubjectCode.equals("0f"))//NPC ���� GUI
			new npc.NpcGui().ItemFixGuiClick(event);
		else if(SubjectCode.equals("10"))//NPC ���� ������ ��� GUI
			new npc.NpcGui().PresentGuiClick(event);
		else if(SubjectCode.equals("11"))//NPC ���� ������ ���� GUI
			new npc.NpcGui().PresentGuiClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("0a"))//NPC �� ������ GUI
			new npc.NpcGui().RuneEquipGUIClose(event);
		else if(SubjectCode.equals("11"))//NPC ���� ������ ���� GUI
			new npc.NpcGui().PresentInventoryClose(event);
	}
}
