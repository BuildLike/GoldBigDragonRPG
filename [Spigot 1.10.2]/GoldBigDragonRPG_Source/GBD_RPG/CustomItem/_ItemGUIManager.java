package GBD_RPG.CustomItem;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _ItemGUIManager
{
	//Item GUI Click Unique Number = 03
	//������ ���� GUI�� ���� ��ȣ�� 03�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ������ ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//������ ���
			new GBD_RPG.CustomItem.CustomItem_GUI().ItemListInventoryclick(event);
		else if(SubjectCode.compareTo("01")==0)//������ ����
			new GBD_RPG.CustomItem.CustomItem_GUI().NewItemGUIclick(event);
		else if(SubjectCode.compareTo("02")==0)//������ ���� ����
			new GBD_RPG.CustomItem.CustomItem_GUI().JobGUIClick(event);
		else if(SubjectCode.compareTo("03")==0)//�Ҹ� ������ ���
			new GBD_RPG.CustomItem.UseableItem_GUI().UseableItemListGUIClick(event);
		else if(SubjectCode.compareTo("04")==0)//�Ҹ� ������ Ÿ�� ����
			new GBD_RPG.CustomItem.UseableItem_GUI().ChooseUseableItemTypeGUIClick(event);
		else if(SubjectCode.compareTo("05")==0)//�Ҹ� ������ ����
			new GBD_RPG.CustomItem.UseableItem_GUI().NewUseableItemGUIclick(event);
		else if(SubjectCode.compareTo("06")==0)//��ų�� ��ų ���
			new GBD_RPG.CustomItem.UseableItem_GUI().SelectSkillGUIClick(event);
	}
}
