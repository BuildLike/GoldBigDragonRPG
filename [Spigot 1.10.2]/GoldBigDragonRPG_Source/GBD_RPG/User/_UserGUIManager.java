package GBD_RPG.User;

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
			new GBD_RPG.User.Stats_GUI().StatusInventoryclick(event);
		if(SubjectCode.compareTo("01")==0)//�ɼ�
			new GBD_RPG.User.Option_GUI().optionInventoryclick(event);
		if(SubjectCode.compareTo("02")==0)//��Ÿ
			new GBD_RPG.User.ETC_GUI().ETCInventoryclick(event);
		if(SubjectCode.compareTo("03")==0)//���̵�
			new GBD_RPG.User.ETC_GUI().GuideInventoryclick(event);
		if(SubjectCode.compareTo("04")==0)//ģ��
			new GBD_RPG.User.ETC_GUI().FriendsGUIclick(event);
		if(SubjectCode.compareTo("05")==0)//ģ�� ��û ���
			new GBD_RPG.User.ETC_GUI().WaittingFriendsGUIclick(event);
		if(SubjectCode.compareTo("06")==0)//��� ����
			new GBD_RPG.User.Equip_GUI().EquipSeeInventoryclick(event);
		if(SubjectCode.compareTo("07")==0)//��ȯ
		{
			new GBD_RPG.User.Equip_GUI().ExchangeInventoryclick(event);
			new GBD_RPG.User.Equip_GUI().ExchangeGUIclick(event);
		}
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("07")==0)//��ȯ
			new GBD_RPG.User.Equip_GUI().ExchangeGUI_Close(event);
	}
}
