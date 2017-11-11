package event;

import org.bukkit.event.inventory.InventoryCloseEvent;

public class Main_InventoryClose
{
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryCode)
	{
		String UniqueCode = InventoryCode.charAt(1) + "" + InventoryCode.charAt(2);
		String SubjectCode = InventoryCode.charAt(3) + "" + InventoryCode.charAt(4);
		if(UniqueCode.equals("00"))//User ��Ű�� ���� GUI Click�� ������.
		    new user._UserGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.equals("01"))//Admin ��Ű�� ���� GUI Close�� ������.
		    new admin._AdminGUIManager().closeRouting(event, SubjectCode);
		else if(UniqueCode.equals("02"))//Area ��Ű�� ���� GUI Close�� ������.
		    new area._AreaGUIManager().closeRouting(event, SubjectCode);
		else if(UniqueCode.equals("05"))//Quest ��Ű�� ���� GUI Close�� ������.
		    new quest._QuestGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.equals("07"))//NPC ��Ű�� ���� GUI Close�� ������.
		    new npc._NPCGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.equals("08"))//Monster ��Ű�� ���� GUI Close�� ������.
		    new monster._MonsterGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.equals("0a"))//Dungeon ��Ű�� ���� GUI Close�� ������.
		    new dungeon._DungeonGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.equals("0d"))//Structure ��Ű�� ���� GUI Close�� ������.
		    new structure._StructureGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.equals("0e"))//Making ��Ű�� ���� GUI Close�� ������.
		    new making._MakingGUIManager().CloseRouting(event, SubjectCode);
	}
}
