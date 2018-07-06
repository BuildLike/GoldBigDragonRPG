package area.gui;

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
			new AreaListGui().areaListGuiClick(event);
		else if(subjectCode.equals("01"))//���� ����
			new AreaSettingGui().areaSettingClick(event);
		else if(subjectCode.equals("02"))//���� ���� ���� �� ����
			new AreaMonsterSpawnSettingGui().areaMonsterSpawnSettingClick(event);
		else if(subjectCode.equals("03"))//���� ��ü ���� ����
			new AreaMonsterSettingGui().areaMonsterSettingGuiClick(event);
		else if(subjectCode.equals("04"))//���� ���� ���� ����
			new AreaFishSettingGui().areaFishSettingClick(event);
		else if(subjectCode.equals("05"))//���� Ư��ǰ ���
			new AreaBlockSettingGui().areaBlockSettingClick(event);
		else if(subjectCode.equals("06"))//���� Ư��ǰ ����
			new AreaBlockItemSettingGui().areaBlockItemSettingClick(event);
		else if(subjectCode.equals("07"))//���� ���� ����
			new AreaMonsterListGui().areaMonsterListGuiClick(event);
		else if(subjectCode.equals("08"))//���� Ư�� ���� ����
			new AreaSpawnSpecialMonsterListGui().areaSpawnSpecialMonsterListGuiClick(event);
		else if(subjectCode.equals("09"))//���� ����� ����
			new area.gui.AreaMusicSettingGui().areaMusicSettingGuiClick(event);
	}
	
	public void closeRouting(InventoryCloseEvent event, String subjectCode)
	{
		if(subjectCode.equals("04"))//���� ���� ���� ����
			new AreaFishSettingGui().areafishingSettingClose(event);
		else if(subjectCode.equals("06"))//���� Ư��ǰ ����
			new AreaBlockItemSettingGui().areaBlockItemSettingClose(event);
	}
}
