package structure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import user.UserDataObject;

public class _StructureGUIManager
{
	//Structure GUI Click Unique Number = 0d
	//��ü ���� GUI�� ���� ��ȣ�� 0d�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ��ü ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void InventoryClickRouter(InventoryClickEvent event, String InventoryName)
	{
		String Striped = ChatColor.stripColor(event.getInventory().getName().toString());
		if(event.getInventory().getType()==InventoryType.CHEST)
		{
			if(!(Striped.equals("���� ������")
			))
				event.setCancelled(true);
		}
		if(InventoryName.equals("������"))
			new StructPostBox().PostBoxMainGUIClick(event);
		else if(InventoryName.equals("���� ������"))
			new StructPostBox().ItemPutterGUIClick(event);
		else if(InventoryName.contains("�Խ���"))
		{
			if(InventoryName.contains("�ŷ�"))
			{
				if(InventoryName.contains("�޴�"))
					new StructTradeBoard().SelectTradeTypeGUIClick(event);
				else if(InventoryName.contains("����"))
					new StructTradeBoard().TradeBoardSettingGUIClick(event);
				else
					new StructTradeBoard().TradeBoardMainGUIClick(event);
			}
			else
			{
				if(InventoryName.contains("����"))
					new StructBoard().BoardSettingGUIClick(event);
				else
					new StructBoard().BoardMainGUIClick(event);
			}
		}
		else if(InventoryName.equals("�Ǹ��� �������� ������"))
			new StructTradeBoard().SelectSellItemGUIClick(event);
		else if(InventoryName.equals("������ �������� ������"))
			new StructTradeBoard().SelectBuyItemGUIClick(event);
		else if(InventoryName.contains("�Ϲ� ������"))
			new StructTradeBoard().SelectNormalItemGUIClick(event);
		else if(InventoryName.equals("�ް���� �������� ������"))
			new StructTradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(InventoryName.equals("���� �� �������� ������"))
			new StructTradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(InventoryName.equals("��ں�"))
			new StructCampFire().CampFireGUIClick(event);
	}
	
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryName)
	{
		UserDataObject u = new UserDataObject();
		Player player = (Player)event.getPlayer();
		
		if(InventoryName.equals("���� ������"))
			new StructPostBox().ItemPutterGUIClose(event);
		else if(InventoryName.equals("�Ǹ��� �������� ������")||InventoryName.equals("������ �������� ������"))
		{
			if(u.getItemStack((Player)event.getPlayer())==null)
				u.clearAll(player);
		}
		else if(InventoryName.contains("�Ϲ� ������"))
			u.clearAll(player);
	}
	
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//��ü ��ü ���
			new structure.StructureGui().StructureListGUIClick(event);
		else if(SubjectCode.equals("01"))//��ü Ÿ�� ����
			new structure.StructureGui().SelectStructureTypeGUIClick(event);
		else if(SubjectCode.equals("02"))//��ü ���� ����
			new structure.StructureGui().SelectStructureDirectionGUIClick(event);
		else if(SubjectCode.equals("03"))//������ ����
			new structure.StructPostBox().PostBoxMainGUIClick(event);
		else if(SubjectCode.equals("04"))//������ ������ ���
			new structure.StructPostBox().ItemPutterGUIClick(event);
		else if(SubjectCode.equals("05"))//�Խ��� ���
			new structure.StructBoard().BoardMainGUIClick(event);
		else if(SubjectCode.equals("06"))//�Խ��� ����
			new structure.StructBoard().BoardSettingGUIClick(event);
		else if(SubjectCode.equals("07"))//�ŷ� �Խ��� ���
			new structure.StructTradeBoard().TradeBoardMainGUIClick(event);
		else if(SubjectCode.equals("08"))//�ŷ� �Խ��� ����
			new structure.StructTradeBoard().TradeBoardSettingGUIClick(event);
		else if(SubjectCode.equals("09"))//�ŷ� �Խ��� �޴�
			new structure.StructTradeBoard().SelectTradeTypeGUIClick(event);
		else if(SubjectCode.equals("0a"))//�Ǹ��� ������ ����
			new structure.StructTradeBoard().SelectSellItemGUIClick(event);
		else if(SubjectCode.equals("0b"))//������ ������ ����
			new structure.StructTradeBoard().SelectBuyItemGUIClick(event);
		else if(SubjectCode.equals("0c"))//��ȯ�� ���� ���� ������ ����
			new structure.StructTradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(SubjectCode.equals("0d"))//��ȯ�� ���� �� ������ ����
			new structure.StructTradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(SubjectCode.equals("0e"))//�Ϲ� ������ ���
			new structure.StructTradeBoard().SelectNormalItemGUIClick(event);
		else if(SubjectCode.equals("0f"))//��ں�
			new structure.StructCampFire().CampFireGUIClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("04"))//������ ������ ���
			new structure.StructPostBox().ItemPutterGUIClose(event);
	}
}
