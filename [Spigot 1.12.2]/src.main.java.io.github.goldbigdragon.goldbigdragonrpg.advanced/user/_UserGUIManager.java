package user;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _UserGUIManager
{
	//User GUI Click Unique Number = 00
	//���� ���� GUI�� ���� ��ȣ�� 00�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//����
			new user.StatsGui().StatusInventoryclick(event);
		if(SubjectCode.equals("01"))//�ɼ�
			new user.OptionGui().optionInventoryclick(event);
		if(SubjectCode.equals("02"))//��Ÿ
			new user.EtcGui().ETCInventoryclick(event);
		if(SubjectCode.equals("03"))//���̵�
			new user.EtcGui().GuideInventoryclick(event);
		if(SubjectCode.equals("04"))//ģ��
			new user.EtcGui().FriendsGUIclick(event);
		if(SubjectCode.equals("05"))//ģ�� ��û ���
			new user.EtcGui().WaittingFriendsGUIclick(event);
		if(SubjectCode.equals("06"))//��� ����
			new user.EquipGui().EquipSeeInventoryclick(event);
		if(SubjectCode.equals("07"))//��ȯ
		{
			new user.EquipGui().ExchangeInventoryclick(event);
			new user.EquipGui().ExchangeGUIclick(event);
		}
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("07"))//��ȯ
			new user.EquipGui().ExchangeGUI_Close(event);
	}
}
