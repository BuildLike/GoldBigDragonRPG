package structure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import user.UserData_Object;

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
			new Struct_PostBox().PostBoxMainGUIClick(event);
		else if(InventoryName.equals("���� ������"))
			new Struct_PostBox().ItemPutterGUIClick(event);
		else if(InventoryName.contains("�Խ���"))
		{
			if(InventoryName.contains("�ŷ�"))
			{
				if(InventoryName.contains("�޴�"))
					new Struct_TradeBoard().SelectTradeTypeGUIClick(event);
				else if(InventoryName.contains("����"))
					new Struct_TradeBoard().TradeBoardSettingGUIClick(event);
				else
					new Struct_TradeBoard().TradeBoardMainGUIClick(event);
			}
			else
			{
				if(InventoryName.contains("����"))
					new Struct_Board().BoardSettingGUIClick(event);
				else
					new Struct_Board().BoardMainGUIClick(event);
			}
		}
		else if(InventoryName.equals("�Ǹ��� �������� ������"))
			new Struct_TradeBoard().SelectSellItemGUIClick(event);
		else if(InventoryName.equals("������ �������� ������"))
			new Struct_TradeBoard().SelectBuyItemGUIClick(event);
		else if(InventoryName.contains("�Ϲ� ������"))
			new Struct_TradeBoard().SelectNormalItemGUIClick(event);
		else if(InventoryName.equals("�ް���� �������� ������"))
			new Struct_TradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(InventoryName.equals("���� �� �������� ������"))
			new Struct_TradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(InventoryName.equals("��ں�"))
			new Struct_CampFire().CampFireGUIClick(event);
	}
	
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryName)
	{
		UserData_Object u = new UserData_Object();
		Player player = (Player)event.getPlayer();
		
		if(InventoryName.equals("���� ������"))
			new Struct_PostBox().ItemPutterGUIClose(event);
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
			new structure.Structure_GUI().StructureListGUIClick(event);
		else if(SubjectCode.equals("01"))//��ü Ÿ�� ����
			new structure.Structure_GUI().SelectStructureTypeGUIClick(event);
		else if(SubjectCode.equals("02"))//��ü ���� ����
			new structure.Structure_GUI().SelectStructureDirectionGUIClick(event);
		else if(SubjectCode.equals("03"))//������ ����
			new structure.Struct_PostBox().PostBoxMainGUIClick(event);
		else if(SubjectCode.equals("04"))//������ ������ ���
			new structure.Struct_PostBox().ItemPutterGUIClick(event);
		else if(SubjectCode.equals("05"))//�Խ��� ���
			new structure.Struct_Board().BoardMainGUIClick(event);
		else if(SubjectCode.equals("06"))//�Խ��� ����
			new structure.Struct_Board().BoardSettingGUIClick(event);
		else if(SubjectCode.equals("07"))//�ŷ� �Խ��� ���
			new structure.Struct_TradeBoard().TradeBoardMainGUIClick(event);
		else if(SubjectCode.equals("08"))//�ŷ� �Խ��� ����
			new structure.Struct_TradeBoard().TradeBoardSettingGUIClick(event);
		else if(SubjectCode.equals("09"))//�ŷ� �Խ��� �޴�
			new structure.Struct_TradeBoard().SelectTradeTypeGUIClick(event);
		else if(SubjectCode.equals("0a"))//�Ǹ��� ������ ����
			new structure.Struct_TradeBoard().SelectSellItemGUIClick(event);
		else if(SubjectCode.equals("0b"))//������ ������ ����
			new structure.Struct_TradeBoard().SelectBuyItemGUIClick(event);
		else if(SubjectCode.equals("0c"))//��ȯ�� ���� ���� ������ ����
			new structure.Struct_TradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(SubjectCode.equals("0d"))//��ȯ�� ���� �� ������ ����
			new structure.Struct_TradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(SubjectCode.equals("0e"))//�Ϲ� ������ ���
			new structure.Struct_TradeBoard().SelectNormalItemGUIClick(event);
		else if(SubjectCode.equals("0f"))//��ں�
			new structure.Struct_CampFire().CampFireGUIClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("04"))//������ ������ ���
			new structure.Struct_PostBox().ItemPutterGUIClose(event);
	}
}
