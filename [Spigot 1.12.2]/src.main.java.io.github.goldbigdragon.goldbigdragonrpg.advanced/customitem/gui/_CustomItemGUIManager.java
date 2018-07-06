package customitem.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _CustomItemGUIManager
{
	//Item GUI Click Unique Number = 03
	//������ ���� GUI�� ���� ��ȣ�� 03�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ������ ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void clickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//������ ���
			new EquipItemListGui().itemListClick(event);
		else if(SubjectCode.equals("01"))//������ ����
			new EquipItemForgingGui().itemForgingClick(event);
		else if(SubjectCode.equals("02"))//������ ���� ����
			new RestrictJobSelectGui().restrictJobSelectClick(event);
		else if(SubjectCode.equals("03"))//�Ҹ� ������ ���
			new UseableItemListGui().useableItemListClick(event);
		else if(SubjectCode.equals("04"))//�Ҹ� ������ Ÿ�� ����
			new UseableItemTypeSelectGui().useableItemTypeSelectClick(event);
		else if(SubjectCode.equals("05"))//�Ҹ� ������ ����
			new UseableItemMakingGui().useableItemMakingClick(event);
		else if(SubjectCode.equals("06"))//��ų�� ��ų ���
			new SelectSkillGui().selectSkillGuiClick(event);
		else if(SubjectCode.equals("07"))//���� ���
			new ApplyToolGui().applyToolClick(event);
	}

	public void closeRouting(InventoryCloseEvent event, String subjectCode)
	{
		if(subjectCode.equals("07"))//���� ���
			new ApplyToolGui().applyToolClose(event);
	}
}