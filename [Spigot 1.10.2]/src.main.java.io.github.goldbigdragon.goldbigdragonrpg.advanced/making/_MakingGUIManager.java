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
		if(SubjectCode.equals("00"))//��ü ���� ���
			new area.AreaGui().areaListGuiClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("04"))//���� ���� ���� ����
			new area.AreaGui().fishingSettingInventoryClose(event);
	}
}
