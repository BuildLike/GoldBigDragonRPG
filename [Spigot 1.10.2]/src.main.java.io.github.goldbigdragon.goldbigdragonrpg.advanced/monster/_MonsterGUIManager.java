package monster;

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
		if(SubjectCode.equals("00"))//���� ���
			new monster.Monster_GUI().MonsterListGUIClick(event);
		else if(SubjectCode.equals("01"))//���� ����
			new monster.Monster_GUI().MonsterOptionSettingGUIClick(event);
		else if(SubjectCode.equals("02"))//���� ���� ����
			new monster.Monster_GUI().MonsterPotionGUIClick(event);
		else if(SubjectCode.equals("03"))//���� ��� ����
			new monster.Monster_GUI().ArmorGUIClick(event);
		else if(SubjectCode.equals("0b"))//���� Ÿ�� ����
			new monster.Monster_GUI().MonsterTypeGUIClick(event);
		
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("03"))//��� ����â
			new monster.Monster_GUI().ArmorGUIClose(event);
	}
}
