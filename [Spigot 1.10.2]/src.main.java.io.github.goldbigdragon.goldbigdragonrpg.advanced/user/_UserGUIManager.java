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
		if(SubjectCode.compareTo("00")==0)//����
			new user.Stats_GUI().StatusInventoryclick(event);
		if(SubjectCode.compareTo("01")==0)//�ɼ�
			new user.Option_GUI().optionInventoryclick(event);
		if(SubjectCode.compareTo("02")==0)//��Ÿ
			new user.ETC_GUI().ETCInventoryclick(event);
		if(SubjectCode.compareTo("03")==0)//���̵�
			new user.ETC_GUI().GuideInventoryclick(event);
		if(SubjectCode.compareTo("04")==0)//ģ��
			new user.ETC_GUI().FriendsGUIclick(event);
		if(SubjectCode.compareTo("05")==0)//ģ�� ��û ���
			new user.ETC_GUI().WaittingFriendsGUIclick(event);
		if(SubjectCode.compareTo("06")==0)//��� ����
			new user.Equip_GUI().EquipSeeInventoryclick(event);
		if(SubjectCode.compareTo("07")==0)//��ȯ
		{
			new user.Equip_GUI().ExchangeInventoryclick(event);
			new user.Equip_GUI().ExchangeGUIclick(event);
		}
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("07")==0)//��ȯ
			new user.Equip_GUI().ExchangeGUI_Close(event);
	}
}
