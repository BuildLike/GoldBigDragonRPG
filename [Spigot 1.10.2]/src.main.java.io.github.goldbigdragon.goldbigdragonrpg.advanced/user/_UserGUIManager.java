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
			new user.Stats_GUI().StatusInventoryclick(event);
		if(SubjectCode.equals("01"))//�ɼ�
			new user.Option_GUI().optionInventoryclick(event);
		if(SubjectCode.equals("02"))//��Ÿ
			new user.ETC_GUI().ETCInventoryclick(event);
		if(SubjectCode.equals("03"))//���̵�
			new user.ETC_GUI().GuideInventoryclick(event);
		if(SubjectCode.equals("04"))//ģ��
			new user.ETC_GUI().FriendsGUIclick(event);
		if(SubjectCode.equals("05"))//ģ�� ��û ���
			new user.ETC_GUI().WaittingFriendsGUIclick(event);
		if(SubjectCode.equals("06"))//��� ����
			new user.Equip_GUI().EquipSeeInventoryclick(event);
		if(SubjectCode.equals("07"))//��ȯ
		{
			new user.Equip_GUI().ExchangeInventoryclick(event);
			new user.Equip_GUI().ExchangeGUIclick(event);
		}
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("07"))//��ȯ
			new user.Equip_GUI().ExchangeGUI_Close(event);
	}
}
