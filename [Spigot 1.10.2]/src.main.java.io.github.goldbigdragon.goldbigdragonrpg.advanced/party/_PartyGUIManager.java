package party;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _PartyGUIManager
{
	//Party GUI Click Unique Number = 04
	//��Ƽ ���� GUI�� ���� ��ȣ�� 04�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ��Ƽ ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//��Ƽ ���� GUI
			new party.Party_GUI().PartyGUI_MainClick(event);
		else if(SubjectCode.equals("01"))//��Ƽ ��� GUI
			new party.Party_GUI().PartyListGUIClick(event);
		else if(SubjectCode.equals("02"))//��Ƽ ��� ��� GUI
			new party.Party_GUI().PartyMemberInformationGUIClick(event);
	}
}
