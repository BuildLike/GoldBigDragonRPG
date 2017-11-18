package area;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _AreaGUIManager
{
	//Area GUI Click Unique Number = 02
	//���� ���� GUI�� ���� ��ȣ�� 02�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void clickRouting(InventoryClickEvent event, String subjectCode)
	{
		if(subjectCode.equals("00"))//��ü ���� ���
			new area.AreaGui().areaListGuiClick(event);
		else if(subjectCode.equals("01"))//���� ����
			new area.AreaGui().areaSettingGuiInventoryClick(event);
		else if(subjectCode.equals("02"))//���� ���� ���� �� ����
			new area.AreaGui().areaAddMonsterSpawnRuleGuiClick(event);
		else if(subjectCode.equals("03"))//���� ��ü ���� ����
			new area.AreaGui().areaMonsterSettingGuiClick(event);
		else if(subjectCode.equals("04"))//���� ���� ���� ����
			new area.AreaGui().areaFishSettingGuiClick(event);
		else if(subjectCode.equals("05"))//���� Ư��ǰ ���
			new area.AreaGui().areaBlockSettingGuiClick(event);
		else if(subjectCode.equals("06"))//���� Ư��ǰ ����
			new area.AreaGui().areaBlockItemSettingGuiClick(event);
		else if(subjectCode.equals("07"))//���� ���� ����
			new area.AreaGui().areaAddMonsterListGuiClick(event);
		else if(subjectCode.equals("08"))//���� Ư�� ���� ����
			new area.AreaGui().areaSpawnSpecialMonsterListGuiClick(event);
		else if(subjectCode.equals("09"))//���� ����� ����
			new area.AreaGui().areaMusicSettingGuiClick(event);
	}
	
	public void closeRouting(InventoryCloseEvent event, String subjectCode)
	{
		if(subjectCode.equals("04"))//���� ���� ���� ����
			new area.AreaGui().fishingSettingInventoryClose(event);
		else if(subjectCode.equals("06"))//���� Ư��ǰ ����
			new area.AreaGui().blockItemSettingInventoryClose(event);
	}
}
