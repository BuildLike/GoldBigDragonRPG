package GBD_RPG.Corpse;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _CorpseGUIManager
{
	//Corpse GUI Click Unique Number = 09
	//��ü ���� GUI�� ���� ��ȣ�� 09�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ��ü ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//��Ȱ ��� ���� â
			new GBD_RPG.Corpse.Corpse_GUI().ReviveSelectClick(event);
	}
}
