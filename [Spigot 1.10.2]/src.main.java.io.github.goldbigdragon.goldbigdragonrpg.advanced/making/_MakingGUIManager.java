package making;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _MakingGUIManager
{
	//Making GUI Click Unique Number = 0e
	//���� ���� GUI�� ���� ��ȣ�� 0e�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//��ü ���� ���
			new area.Area_GUI().AreaListGUIClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("04")==0)//���� ���� ���� ����
			new area.Area_GUI().FishingSettingInventoryClose(event);
	}
}
