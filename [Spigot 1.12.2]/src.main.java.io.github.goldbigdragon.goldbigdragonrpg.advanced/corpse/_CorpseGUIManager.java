package corpse;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _CorpseGUIManager
{
	//Corpse GUI Click Unique Number = 09
	//��ü ���� GUI�� ���� ��ȣ�� 09�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ��ü ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//��Ȱ ��� ���� â
			new corpse.CorpseGui().reviveSelectClick(event);
	}
}
