package quest;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _QuestGUIManager
{
	//Quest GUI Click Unique Number = 05
	//����Ʈ ���� GUI�� ���� ��ȣ�� 05�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ����Ʈ ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//���� ����Ʈ ���� ��� GUI
			new quest.QuestGui().MyQuestListGUIClick(event);
		else if(SubjectCode.equals("01"))//��ü ����Ʈ ���� ��� GUI
			new quest.QuestGui().AllOfQuestListGUIClick(event);
		else if(SubjectCode.equals("02"))//����Ʈ �帧�� GUI
			new quest.QuestGui().FixQuestGUIClick(event);
		else if(SubjectCode.equals("03"))//����Ʈ ������Ʈ �߰� GUI
			new quest.QuestGui().SelectObjectPageClick(event);
		else if(SubjectCode.equals("04"))//��ũ��Ʈ GUI
			new quest.QuestGui().QuestScriptTypeGUIClick(event);
		else if(SubjectCode.equals("05"))//����Ʈ �ɼ� GUI
			new quest.QuestGui().QuestOptionGUIClick(event);
		else if(SubjectCode.equals("06"))//���� �ؾ� �� ������ ��� GUI
			new quest.QuestGui().GetterItemSetingGUIClick(event);
		else if(SubjectCode.equals("07"))//������ �� ������ ��� GUI
			new quest.QuestGui().PresentItemSettingGUIClick(event);
		else if(SubjectCode.equals("08"))//����, ��ƾ� �� ������, ��� �ؾ� �� ����, ä�� �ؾ� �� ��� ��� GUI
			new quest.QuestGui().ShowNeedGUIClick(event);
		else if(SubjectCode.equals("09"))//����� ��� �� ������ ���� GUI
			new quest.QuestGui().KeepGoingClick(event);
		else if(SubjectCode.equals("0a"))//����Ʈ �׺���̼� GUI
			new quest.QuestGui().Quest_NavigationListGUIClick(event);
		else if(SubjectCode.equals("0b"))//���� ������ ����Ʈ ���� GUI
			new quest.QuestGui().Quest_OPChoiceClick(event);
		else if(SubjectCode.equals("0c"))//���� ������ ����Ʈ ���� GUI
			new quest.QuestGui().Quest_UserChoiceClick(event);
	}

	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("06"))//���� �ؾ� �� ������ ��� GUI
			new quest.QuestGui().GetterItemSetingGUIClose(event);
		else if(SubjectCode.equals("07"))//������ �� ������ ��� GUI
			new quest.QuestGui().PresentItemSettingGUIClose(event);
	}
}
