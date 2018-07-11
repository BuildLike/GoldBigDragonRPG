package monster;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _MonsterGUIManager
{
	//Monster GUI Click Unique Number = 08
	//���� ���� GUI�� ���� ��ȣ�� 08�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String subjectCode)
	{
		if(subjectCode.equals("00"))//���� ���
			new monster.gui.MonsterListGui().monsterListClick(event);
		else if(subjectCode.equals("01"))//���� ����
			new monster.gui.MonsterOptionSettingGui().monsterOptionSettingGUIClick(event);
		else if(subjectCode.equals("02"))//���� ���� ����
			new monster.gui.MonsterPotionGui().monsterPotionClick(event);
		else if(subjectCode.equals("03"))//���� ��� ����
			new monster.gui.MonsterEquipGui().monsterEquipClick(event);
		else if(subjectCode.equals("0b"))//���� Ÿ�� ����
			new monster.gui.MonsterTypeGui().monsterTypeClick(event);
		
	}
	
	public void CloseRouting(InventoryCloseEvent event, String subjectCode)
	{
		if(subjectCode.equals("03"))//��� ����â
			new monster.gui.MonsterEquipGui().monsterEquipClose(event);
	}
}