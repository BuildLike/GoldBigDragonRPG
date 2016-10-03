package GBD_RPG.Monster;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _MonsterGUIManager
{
	//Monster GUI Click Unique Number = 08
	//���� ���� GUI�� ���� ��ȣ�� 08�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//���� ���
			new GBD_RPG.Monster.Monster_GUI().MonsterListGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//���� ����
			new GBD_RPG.Monster.Monster_GUI().MonsterOptionSettingGUIClick(event);
		else if(SubjectCode.compareTo("02")==0)//���� ���� ����
			new GBD_RPG.Monster.Monster_GUI().MonsterPotionGUIClick(event);
		else if(SubjectCode.compareTo("03")==0)//���� ��� ����
			new GBD_RPG.Monster.Monster_GUI().ArmorGUIClick(event);
		
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("03")==0)//��� ����â
			new GBD_RPG.Monster.Monster_GUI().ArmorGUIClose(event);
	}
}
