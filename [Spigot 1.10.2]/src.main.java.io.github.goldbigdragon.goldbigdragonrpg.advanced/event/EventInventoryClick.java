package event;

import org.bukkit.event.inventory.InventoryClickEvent;

public class EventInventoryClick
{
	public void InventoryClickRouter(InventoryClickEvent event, String InventoryCode)
	{
		String UniqueCode = InventoryCode.charAt(1) + "" + InventoryCode.charAt(2);
		String SubjectCode = InventoryCode.charAt(3) + "" + InventoryCode.charAt(4);
		if(UniqueCode.equals("00"))//User ��Ű�� ���� GUI Click�� ������.
		    new user._UserGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("01"))//Admin ��Ű�� ���� GUI Click�� ������.
		    new admin._AdminGUIManager().clickRouting(event, SubjectCode);
		else if(UniqueCode.equals("02"))//Area ��Ű�� ���� GUI Click�� ������.
		    new area._AreaGUIManager().clickRouting(event, SubjectCode);
		else if(UniqueCode.equals("03"))//CustomItem ��Ű�� ���� GUI Click�� ������.
		    new customitem._ItemGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("04"))//Party ��Ű�� ���� GUI Click�� ������.
		    new party._PartyGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("05"))//Quest ��Ű�� ���� GUI Click�� ������.
		    new quest._QuestGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("06"))//Job ��Ű�� ���� GUI Click�� ������.
		    new job._JobGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("07"))//NPC ��Ű�� ���� GUI Click�� ������.
		    new npc._NPCGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("08"))//Monster ��Ű�� ���� GUI Click�� ������.
		    new monster._MonsterGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("09"))//Corpse ��Ű�� ���� GUI Click�� ������.
		    new corpse._CorpseGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("0a"))//Dungeon ��Ű�� ���� GUI Click�� ������.
		    new dungeon._DungeonGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("0b"))//Skill ��Ű�� ���� GUI Click�� ������.
		    new skill._SkillGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("0c"))//Warp ��Ű�� ���� GUI Click�� ������.
		    new warp._WarpGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("0d"))//Structure ��Ű�� ���� GUI Click�� ������.
		    new structure._StructureGUIManager().ClickRouting(event, SubjectCode);
		else if(UniqueCode.equals("0e"))//Making ��Ű�� ���� GUI Click�� ������.
		    new making._MakingGUIManager().ClickRouting(event, SubjectCode);
	}
}