package event;

import org.bukkit.event.inventory.InventoryCloseEvent;

public class Main_InventoryClose
{
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryCode)
	{
		String UniqueCode = InventoryCode.charAt(1) + "" + InventoryCode.charAt(2);
		String SubjectCode = InventoryCode.charAt(3) + "" + InventoryCode.charAt(4);
		if(UniqueCode.compareTo("00")==0)//User ��Ű�� ���� GUI Click�� ������.
		    new user._UserGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("01")==0)//Admin ��Ű�� ���� GUI Close�� ������.
		    new admin._AdminGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("02")==0)//Area ��Ű�� ���� GUI Close�� ������.
		    new area._AreaGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("05")==0)//Quest ��Ű�� ���� GUI Close�� ������.
		    new quest._QuestGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("07")==0)//NPC ��Ű�� ���� GUI Close�� ������.
		    new npc._NPCGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("08")==0)//Monster ��Ű�� ���� GUI Close�� ������.
		    new monster._MonsterGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("0a")==0)//Dungeon ��Ű�� ���� GUI Close�� ������.
		    new dungeon._DungeonGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("0d")==0)//Structure ��Ű�� ���� GUI Close�� ������.
		    new structure._StructureGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("0e")==0)//Making ��Ű�� ���� GUI Close�� ������.
		    new making._MakingGUIManager().CloseRouting(event, SubjectCode);
	}
}
