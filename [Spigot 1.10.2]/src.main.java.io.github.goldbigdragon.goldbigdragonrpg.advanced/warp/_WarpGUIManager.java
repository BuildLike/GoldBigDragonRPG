package warp;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _WarpGUIManager
{
	//Warp GUI Click Unique Number = 0c
	//���� ���� GUI�� ���� ��ȣ�� 0c�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//���� ���GUI
			new warp.Warp_GUI().WarpListGUIInventoryclick(event);
	}
}
