package GBD_RPG.Main_Event;

import org.bukkit.event.inventory.InventoryCloseEvent;

public class Main_InventoryClose
{
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryCode)
	{
		String UniqueCode = InventoryCode.charAt(1) + "" + InventoryCode.charAt(2);
		String SubjectCode = InventoryCode.charAt(3) + "" + InventoryCode.charAt(4);
		if(UniqueCode.compareTo("00")==0)//User ��Ű�� ���� GUI Click�� ������.
		    new GBD_RPG.User._UserGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("01")==0)//Admin ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Admin._AdminGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("02")==0)//Area ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Area._AreaGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("05")==0)//Quest ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Quest._QuestGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("07")==0)//NPC ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.NPC._NPCGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("08")==0)//Monster ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Monster._MonsterGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("0a")==0)//Dungeon ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Dungeon._DungeonGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("0d")==0)//Structure ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Structure._StructureGUIManager().CloseRouting(event, SubjectCode);
		else if(UniqueCode.compareTo("0e")==0)//Making ��Ű�� ���� GUI Close�� ������.
		    new GBD_RPG.Making._MakingGUIManager().CloseRouting(event, SubjectCode);
	}
}
