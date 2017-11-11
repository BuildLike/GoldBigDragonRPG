package admin;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _AdminGUIManager
{
	//Admin GUI Click Unique Number = 01
	//���� ���� GUI�� ���� ��ȣ�� 01�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void clickRouting(InventoryClickEvent event, String subjectCode)
	{
		if(subjectCode.equals("00"))//���ǹڽ�
			new admin.OPbox_GUI().opBoxGuiInventoryClick(event);
		else if(subjectCode.equals("01"))//������ �ɼ�
			new admin.OPbox_GUI().opBoxGuiSettingInventoryClick(event);
		else if(subjectCode.equals("02"))//������ ��������
			new admin.OPbox_GUI().opBoxGuiBroadCastClick(event);
		else if(subjectCode.equals("03"))//������ ���� ����
			new admin.OPbox_GUI().opBoxGuiStatChangeClick(event);
		else if(subjectCode.equals("04"))//������ ȭ�� ����
			new admin.OPbox_GUI().opBoxGuiMoneyClick(event);
		else if(subjectCode.equals("05"))//������ ��� ����
			new admin.OPbox_GUI().opBoxGuiDeathClick(event);
		else if(subjectCode.equals("06") || subjectCode.equals("07"))//������ ��Ȱ ����
			new admin.OPbox_GUI().opBoxGuiRescueOrReviveClick(event);
		else if(subjectCode.equals("08"))//������ ���̵�
			new admin.OPbox_GUI().opBoxGuideInventoryClick(event);
		else if(subjectCode.equals("09"))//�̺�Ʈ ����
			new admin.Event_GUI().eventGuiInventoryclick(event);
		else if(subjectCode.equals("0a") || subjectCode.equals("0b"))//�̺�Ʈ ������ ����
			new admin.Event_GUI().allPlayerGiveEventGuiClick(event, subjectCode);
		else if(subjectCode.equals("0c"))//���� ����
			new admin.Gamble_GUI().gambleMainGuiClick(event);
		else if(subjectCode.equals("0d"))//���� ��ǰ ���
			new admin.Gamble_GUI().gamblePresentGuiClick(event);
		else if(subjectCode.equals("0e"))//���� ��ǰ ����
			new admin.Gamble_GUI().gambleDetailViewPackageGuiClick(event);
		else if(subjectCode.equals("0f"))//���� ��� ���
			new admin.Gamble_GUI().slotMachineMainGUIClick(event);
		else if(subjectCode.equals("10"))//���� ��� ����
			new admin.Gamble_GUI().slotMachineDetailGuiClick(event);
		else if(subjectCode.equals("11"))//���� ��� ����
			new admin.Gamble_GUI().slotMachineCoinGuiClick(event);
		else if(subjectCode.equals("12"))//���� �ӽ�
			new admin.Gamble_GUI().slotMachinePlayGuiClick(event);
		else if(subjectCode.equals("13"))//���� �ӽ� (ȸ�� ȭ��)
			event.setCancelled(true);
		else if(subjectCode.equals("14"))//�׺���̼� ���
			new admin.Navigation_GUI().navigationListGuiClick(event);
		else if(subjectCode.equals("15"))//�׺���̼� ����
			new admin.Navigation_GUI().navigationOptionGuiClick(event);
		else if(subjectCode.equals("16"))//�׺���̼� ���
			new admin.Navigation_GUI().useNavigationGuiClick(event);
		else if(subjectCode.equals("17"))//�ʽ��� �ɼ� ����
			new admin.NewBie_GUI().newBieGuiMainInventoryClick(event);
		else if(subjectCode.equals("18") || subjectCode.equals("1a"))//�ʽ��� ���� �� �ʽ��� ���̵�
			new admin.NewBie_GUI().newbieSupportItemGuiInventoryClick(event, subjectCode);
		else if(subjectCode.equals("19"))//�ʽ��� �⺻ ����Ʈ
			new admin.NewBie_GUI().newbieQuestGuiInventoryClick(event);
		else if(subjectCode.equals("1b"))//���� ���� ����
			new admin.WorldCreate_GUI().worldCreateGuiClick(event);
		else if(subjectCode.equals("1c"))//������ ���
			new admin.Upgrade_GUI().upgradeRecipeGuiClick(event);
		else if(subjectCode.equals("1d"))//������ ����
			new admin.Upgrade_GUI().upgradeRecipeSettingGuiClick(event);
	}
	
	public void closeRouting(InventoryCloseEvent event, String subjectCode)
	{
		if(subjectCode.equals("06") || subjectCode.equals("07"))//��Ȱ ������ ����
			new admin.OPbox_GUI().opBoxGuiRescueOrReviveClose(event, subjectCode);
		else if(subjectCode.equals("0e"))//���� ��ǰ ����
			new admin.Gamble_GUI().gambleDetailViewPackageGuiClose(event);
		else if(subjectCode.equals("11"))//���� ���� ����
			new admin.Gamble_GUI().slotMachineCoinGuiClose(event);
		else if(subjectCode.equals("18")||subjectCode.equals("1a"))//�ʽ��� ���� �� �ʽ��� ���̵�
			new admin.NewBie_GUI().newbieInventoryClose(event, subjectCode);
	}
}
