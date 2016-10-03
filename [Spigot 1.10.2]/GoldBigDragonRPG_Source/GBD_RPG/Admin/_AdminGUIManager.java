package GBD_RPG.Admin;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _AdminGUIManager
{
	//Admin GUI Click Unique Number = 01
	//���� ���� GUI�� ���� ��ȣ�� 01�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//���ǹڽ�
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUIInventoryclick(event);
		else if(SubjectCode.compareTo("01")==0)//������ �ɼ�
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_SettingInventoryClick(event);
		else if(SubjectCode.compareTo("02")==0)//������ ��������
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_BroadCastClick(event);
		else if(SubjectCode.compareTo("03")==0)//������ ���� ����
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_StatChangeClick(event);
		else if(SubjectCode.compareTo("04")==0)//������ ȭ�� ����
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_MoneyClick(event);
		else if(SubjectCode.compareTo("05")==0)//������ ��� ����
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_DeathClick(event);
		else if(SubjectCode.compareTo("06")==0 || SubjectCode.compareTo("07")==0)//������ ��Ȱ ����
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_RescueOrReviveClick(event);
		else if(SubjectCode.compareTo("08")==0)//������ ���̵�
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGuideInventoryclick(event);
		else if(SubjectCode.compareTo("09")==0)//�̺�Ʈ ����
			new GBD_RPG.Admin.Event_GUI().EventGUIInventoryclick(event);
		else if(SubjectCode.compareTo("0a")==0 || SubjectCode.compareTo("0b")==0)//�̺�Ʈ ������ ����
			new GBD_RPG.Admin.Event_GUI().AllPlayerGiveEventGUIclick(event, SubjectCode);
		else if(SubjectCode.compareTo("0c")==0)//���� ����
			new GBD_RPG.Admin.Gamble_GUI().GambleMainGUI_Click(event);
		else if(SubjectCode.compareTo("0d")==0)//���� ��ǰ ���
			new GBD_RPG.Admin.Gamble_GUI().GamblePresentGUI_Click(event);
		else if(SubjectCode.compareTo("0e")==0)//���� ��ǰ ����
			new GBD_RPG.Admin.Gamble_GUI().GambleDetailViewPackageGUI_Click(event);
		else if(SubjectCode.compareTo("0f")==0)//���� ��� ���
			new GBD_RPG.Admin.Gamble_GUI().SlotMachine_MainGUI_Click(event);
		else if(SubjectCode.compareTo("10")==0)//���� ��� ����
			new GBD_RPG.Admin.Gamble_GUI().SlotMachine_DetailGUI_Click(event);
		else if(SubjectCode.compareTo("11")==0)//���� ��� ����
			new GBD_RPG.Admin.Gamble_GUI().SlotMachineCoinGUI_Click(event);
		else if(SubjectCode.compareTo("12")==0)//���� �ӽ�
			new GBD_RPG.Admin.Gamble_GUI().SlotMachine_PlayGUI_Click(event);
		else if(SubjectCode.compareTo("13")==0)//���� �ӽ� (ȸ�� ȭ��)
			event.setCancelled(true);
		else if(SubjectCode.compareTo("14")==0)//�׺���̼� ���
			new GBD_RPG.Admin.Navigation_GUI().NavigationListGUIClick(event);
		else if(SubjectCode.compareTo("15")==0)//�׺���̼� ����
			new GBD_RPG.Admin.Navigation_GUI().NavigationOptionGUIClick(event);
		else if(SubjectCode.compareTo("16")==0)//�׺���̼� ���
			new GBD_RPG.Admin.Navigation_GUI().UseNavigationGUIClick(event);
		else if(SubjectCode.compareTo("17")==0)//�ʽ��� �ɼ� ����
			new GBD_RPG.Admin.NewBie_GUI().NewBieGUIMainInventoryclick(event);
		else if(SubjectCode.compareTo("18")==0 || SubjectCode.compareTo("1a")==0)//�ʽ��� ���� �� �ʽ��� ���̵�
			new GBD_RPG.Admin.NewBie_GUI().NewBieSupportItemGUIInventoryclick(event, SubjectCode);
		else if(SubjectCode.compareTo("19")==0)//�ʽ��� �⺻ ����Ʈ
			new GBD_RPG.Admin.NewBie_GUI().NewBieQuestGUIInventoryclick(event);
		else if(SubjectCode.compareTo("1b")==0)//���� ���� ����
			new GBD_RPG.Admin.WorldCreate_GUI().WorldCreateGUIClick(event);
		else if(SubjectCode.compareTo("1c")==0)//������ ���
			new GBD_RPG.Admin.Upgrade_GUI().UpgradeRecipeGUIClick(event);
		else if(SubjectCode.compareTo("1d")==0)//������ ����
			new GBD_RPG.Admin.Upgrade_GUI().UpgradeRecipeSettingGUIClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("06")==0 || SubjectCode.compareTo("07")==0)//��Ȱ ������ ����
			new GBD_RPG.Admin.OPbox_GUI().OPBoxGUI_RescueOrReviveClose(event, SubjectCode);
		else if(SubjectCode.compareTo("0e")==0)//���� ��ǰ ����
			new GBD_RPG.Admin.Gamble_GUI().GambleDetailViewPackageGUI_Close(event);
		else if(SubjectCode.compareTo("11")==0)//���� ���� ����
			new GBD_RPG.Admin.Gamble_GUI().SlotMachineCoinGUI_Close(event);
		else if(SubjectCode.compareTo("18")==0||SubjectCode.compareTo("1a")==0)//�ʽ��� ���� �� �ʽ��� ���̵�
			new GBD_RPG.Admin.NewBie_GUI().InventoryClose_NewBie(event, SubjectCode);
	}
}
