package GBD_RPG.Quest;

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
		if(SubjectCode.compareTo("00")==0)//���� ����Ʈ ���� ��� GUI
			new GBD_RPG.Quest.Quest_GUI().MyQuestListGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//��ü ����Ʈ ���� ��� GUI
			new GBD_RPG.Quest.Quest_GUI().AllOfQuestListGUIClick(event);
		else if(SubjectCode.compareTo("02")==0)//����Ʈ �帧�� GUI
			new GBD_RPG.Quest.Quest_GUI().FixQuestGUIClick(event);
		else if(SubjectCode.compareTo("03")==0)//����Ʈ ������Ʈ �߰� GUI
			new GBD_RPG.Quest.Quest_GUI().SelectObjectPageClick(event);
		else if(SubjectCode.compareTo("04")==0)//��ũ��Ʈ GUI
			new GBD_RPG.Quest.Quest_GUI().QuestScriptTypeGUIClick(event);
		else if(SubjectCode.compareTo("05")==0)//����Ʈ �ɼ� GUI
			new GBD_RPG.Quest.Quest_GUI().QuestOptionGUIClick(event);
		else if(SubjectCode.compareTo("06")==0)//���� �ؾ� �� ������ ��� GUI
			new GBD_RPG.Quest.Quest_GUI().GetterItemSetingGUIClick(event);
		else if(SubjectCode.compareTo("07")==0)//������ �� ������ ��� GUI
			new GBD_RPG.Quest.Quest_GUI().PresentItemSettingGUIClick(event);
		else if(SubjectCode.compareTo("08")==0)//����, ��ƾ� �� ������, ��� �ؾ� �� ����, ä�� �ؾ� �� ��� ��� GUI
			new GBD_RPG.Quest.Quest_GUI().ShowNeedGUIClick(event);
		else if(SubjectCode.compareTo("09")==0)//����� ��� �� ������ ���� GUI
			new GBD_RPG.Quest.Quest_GUI().KeepGoingClick(event);
		else if(SubjectCode.compareTo("0a")==0)//����Ʈ �׺���̼� GUI
			new GBD_RPG.Quest.Quest_GUI().Quest_NavigationListGUIClick(event);
		else if(SubjectCode.compareTo("0b")==0)//���� ������ ����Ʈ ���� GUI
			new GBD_RPG.Quest.Quest_GUI().Quest_OPChoiceClick(event);
		else if(SubjectCode.compareTo("0c")==0)//���� ������ ����Ʈ ���� GUI
			new GBD_RPG.Quest.Quest_GUI().Quest_UserChoiceClick(event);
	}

	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("06")==0)//���� �ؾ� �� ������ ��� GUI
			new GBD_RPG.Quest.Quest_GUI().GetterItemSetingGUIClose(event);
		else if(SubjectCode.compareTo("07")==0)//������ �� ������ ��� GUI
			new GBD_RPG.Quest.Quest_GUI().PresentItemSettingGUIClose(event);
	}
}
