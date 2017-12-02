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
			new admin.OPboxGui().opBoxGuiInventoryClick(event);
		else if(subjectCode.equals("01"))//������ �ɼ�
			new admin.OPboxGui().opBoxGuiSettingInventoryClick(event);
		else if(subjectCode.equals("02"))//������ ��������
			new admin.OPboxGui().opBoxGuiBroadCastClick(event);
		else if(subjectCode.equals("03"))//������ ���� ����
			new admin.OPboxGui().opBoxGuiStatChangeClick(event);
		else if(subjectCode.equals("04"))//������ ȭ�� ����
			new admin.OPboxGui().opBoxGuiMoneyClick(event);
		else if(subjectCode.equals("05"))//������ ��� ����
			new admin.OPboxGui().opBoxGuiDeathClick(event);
		else if(subjectCode.equals("06") || subjectCode.equals("07"))//������ ��Ȱ ����
			new admin.OPboxGui().opBoxGuiRescueOrReviveClick(event);
		else if(subjectCode.equals("08"))//������ ���̵�
			new admin.OPboxGui().opBoxGuideInventoryClick(event);
		else if(subjectCode.equals("09"))//�̺�Ʈ ����
			new admin.EventGui().eventGuiInventoryclick(event);
		else if(subjectCode.equals("0a") || subjectCode.equals("0b"))//�̺�Ʈ ������ ����
			new admin.EventGui().allPlayerGiveEventGuiClick(event, subjectCode);
		else if(subjectCode.equals("0c"))//���� ����
			new admin.GambleGui().gambleMainGuiClick(event);
		else if(subjectCode.equals("0d"))//���� ��ǰ ���
			new admin.GambleGui().gamblePresentGuiClick(event);
		else if(subjectCode.equals("0e"))//���� ��ǰ ����
			new admin.GambleGui().gambleDetailViewPackageGuiClick(event);
		else if(subjectCode.equals("0f"))//���� ��� ���
			new admin.GambleGui().slotMachineMainGUIClick(event);
		else if(subjectCode.equals("10"))//���� ��� ����
			new admin.GambleGui().slotMachineDetailGuiClick(event);
		else if(subjectCode.equals("11"))//���� ��� ����
			new admin.GambleGui().slotMachineCoinGuiClick(event);
		else if(subjectCode.equals("12"))//���� �ӽ�
			new admin.GambleGui().slotMachinePlayGuiClick(event);
		else if(subjectCode.equals("13"))//���� �ӽ� (ȸ�� ȭ��)
			event.setCancelled(true);
		else if(subjectCode.equals("14"))//�׺���̼� ���
			new admin.NavigationGui().navigationListGuiClick(event);
		else if(subjectCode.equals("15"))//�׺���̼� ����
			new admin.NavigationGui().navigationOptionGuiClick(event);
		else if(subjectCode.equals("16"))//�׺���̼� ���
			new admin.NavigationGui().useNavigationGuiClick(event);
		else if(subjectCode.equals("17"))//�ʽ��� �ɼ� ����
			new admin.NewBieGui().newBieGuiMainInventoryClick(event);
		else if(subjectCode.equals("18") || subjectCode.equals("1a"))//�ʽ��� ���� �� �ʽ��� ���̵�
			new admin.NewBieGui().newbieSupportItemGuiInventoryClick(event, subjectCode);
		else if(subjectCode.equals("19"))//�ʽ��� �⺻ ����Ʈ
			new admin.NewBieGui().newbieQuestGuiInventoryClick(event);
		else if(subjectCode.equals("1b"))//���� ���� ����
			new admin.WorldCreateGui().worldCreateGuiClick(event);
		else if(subjectCode.equals("1c"))//������ ���
			new admin.UpgradeGui().upgradeRecipeGuiClick(event);
		else if(subjectCode.equals("1d"))//������ ����
			new admin.UpgradeGui().upgradeRecipeSettingGuiClick(event);
	}
	
	public void closeRouting(InventoryCloseEvent event, String subjectCode)
	{
		if(subjectCode.equals("06") || subjectCode.equals("07"))//��Ȱ ������ ����
			new admin.OPboxGui().opBoxGuiRescueOrReviveClose(event, subjectCode);
		else if(subjectCode.equals("0e"))//���� ��ǰ ����
			new admin.GambleGui().gambleDetailViewPackageGuiClose(event);
		else if(subjectCode.equals("11"))//���� ���� ����
			new admin.GambleGui().slotMachineCoinGuiClose(event);
		else if(subjectCode.equals("18")||subjectCode.equals("1a"))//�ʽ��� ���� �� �ʽ��� ���̵�
			new admin.NewBieGui().newbieInventoryClose(event, subjectCode);
	}
}
