package customitem;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _ItemGUIManager
{
	//Item GUI Click Unique Number = 03
	//������ ���� GUI�� ���� ��ȣ�� 03�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ������ ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//������ ���
			new customitem.CustomItemGui().itemListInventoryclick(event);
		else if(SubjectCode.equals("01"))//������ ����
			new customitem.CustomItemGui().newItemGuiClick(event);
		else if(SubjectCode.equals("02"))//������ ���� ����
			new customitem.CustomItemGui().JobGUIClick(event);
		else if(SubjectCode.equals("03"))//�Ҹ� ������ ���
			new customitem.UseableItemGui().useableItemListGuiClick(event);
		else if(SubjectCode.equals("04"))//�Ҹ� ������ Ÿ�� ����
			new customitem.UseableItemGui().chooseUseableItemTypeGuiClick(event);
		else if(SubjectCode.equals("05"))//�Ҹ� ������ ����
			new customitem.UseableItemGui().newUseableItemGuiClick(event);
		else if(SubjectCode.equals("06"))//��ų�� ��ų ���
			new customitem.UseableItemGui().selectSkillGuiClick(event);
	}
}