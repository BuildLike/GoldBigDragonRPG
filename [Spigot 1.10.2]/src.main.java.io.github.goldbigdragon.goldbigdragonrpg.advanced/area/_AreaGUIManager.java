package area;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _AreaGUIManager
{
	//Area GUI Click Unique Number = 02
	//���� ���� GUI�� ���� ��ȣ�� 02�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//��ü ���� ���
			new area.Area_GUI().AreaListGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//���� ����
			new area.Area_GUI().AreaSettingGUIInventoryclick(event);
		else if(SubjectCode.compareTo("02")==0)//���� ���� ���� �� ����
			new area.Area_GUI().AreaAddMonsterSpawnRuleGUIClick(event);
		else if(SubjectCode.compareTo("03")==0)//���� ��ü ���� ����
			new area.Area_GUI().AreaMonsterSettingGUIClick(event);
		else if(SubjectCode.compareTo("04")==0)//���� ���� ���� ����
			new area.Area_GUI().AreaFishSettingGUIClick(event);
		else if(SubjectCode.compareTo("05")==0)//���� Ư��ǰ ���
			new area.Area_GUI().AreaBlockSettingGUIClick(event);
		else if(SubjectCode.compareTo("06")==0)//���� Ư��ǰ ����
			new area.Area_GUI().AreaBlockItemSettingGUIClick(event);
		else if(SubjectCode.compareTo("07")==0)//���� ���� ����
			new area.Area_GUI().AreaAddMonsterListGUIClick(event);
		else if(SubjectCode.compareTo("08")==0)//���� Ư�� ���� ����
			new area.Area_GUI().AreaSpawnSpecialMonsterListGUIClick(event);
		else if(SubjectCode.compareTo("09")==0)//���� ����� ����
			new area.Area_GUI().AreaMusicSettingGUIClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("04")==0)//���� ���� ���� ����
			new area.Area_GUI().FishingSettingInventoryClose(event);
		else if(SubjectCode.compareTo("06")==0)//���� Ư��ǰ ����
			new area.Area_GUI().BlockItemSettingInventoryClose(event);
	}
}
