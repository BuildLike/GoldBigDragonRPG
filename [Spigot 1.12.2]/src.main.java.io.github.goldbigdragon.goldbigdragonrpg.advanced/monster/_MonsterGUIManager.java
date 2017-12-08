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
			new monster.MonsterGui().MonsterListGUIClick(event);
		else if(SubjectCode.equals("01"))//���� ����
			new monster.MonsterGui().MonsterOptionSettingGUIClick(event);
		else if(SubjectCode.equals("02"))//���� ���� ����
			new monster.MonsterGui().MonsterPotionGUIClick(event);
		else if(SubjectCode.equals("03"))//���� ��� ����
			new monster.MonsterGui().ArmorGUIClick(event);
		else if(SubjectCode.equals("0b"))//���� Ÿ�� ����
			new monster.MonsterGui().MonsterTypeGUIClick(event);
		
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("03"))//��� ����â
			new monster.MonsterGui().ArmorGUIClose(event);
	}
}