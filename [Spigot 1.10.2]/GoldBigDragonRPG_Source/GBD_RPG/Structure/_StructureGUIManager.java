package GBD_RPG.Structure;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import GBD_RPG.User.UserData_Object;

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
			if(!(Striped.compareTo("���� ������")==0
			))
				event.setCancelled(true);
		}
		if(InventoryName.compareTo("������")==0)
			new Struct_PostBox().PostBoxMainGUIClick(event);
		else if(InventoryName.compareTo("���� ������")==0)
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
		else if(InventoryName.compareTo("�Ǹ��� �������� ������")==0)
			new Struct_TradeBoard().SelectSellItemGUIClick(event);
		else if(InventoryName.compareTo("������ �������� ������")==0)
			new Struct_TradeBoard().SelectBuyItemGUIClick(event);
		else if(InventoryName.contains("�Ϲ� ������"))
			new Struct_TradeBoard().SelectNormalItemGUIClick(event);
		else if(InventoryName.compareTo("�ް���� �������� ������")==0)
			new Struct_TradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(InventoryName.compareTo("���� �� �������� ������")==0)
			new Struct_TradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(InventoryName.compareTo("��ں�")==0)
			new Struct_CampFire().CampFireGUIClick(event);
	}
	
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryName)
	{
		UserData_Object u = new UserData_Object();
		Player player = (Player)event.getPlayer();
		
		if(InventoryName.compareTo("���� ������")==0)
			new Struct_PostBox().ItemPutterGUIClose(event);
		else if(InventoryName.compareTo("�Ǹ��� �������� ������")==0||InventoryName.compareTo("������ �������� ������")==0)
		{
			if(u.getItemStack((Player)event.getPlayer())==null)
				u.clearAll(player);
		}
		else if(InventoryName.contains("�Ϲ� ������"))
			u.clearAll(player);
	}
	
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//��ü ��ü ���
			new GBD_RPG.Structure.Structure_GUI().StructureListGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//��ü Ÿ�� ����
			new GBD_RPG.Structure.Structure_GUI().SelectStructureTypeGUIClick(event);
		else if(SubjectCode.compareTo("02")==0)//��ü ���� ����
			new GBD_RPG.Structure.Structure_GUI().SelectStructureDirectionGUIClick(event);
		else if(SubjectCode.compareTo("03")==0)//������ ����
			new GBD_RPG.Structure.Struct_PostBox().PostBoxMainGUIClick(event);
		else if(SubjectCode.compareTo("04")==0)//������ ������ ���
			new GBD_RPG.Structure.Struct_PostBox().ItemPutterGUIClick(event);
		else if(SubjectCode.compareTo("05")==0)//�Խ��� ���
			new GBD_RPG.Structure.Struct_Board().BoardMainGUIClick(event);
		else if(SubjectCode.compareTo("06")==0)//�Խ��� ����
			new GBD_RPG.Structure.Struct_Board().BoardSettingGUIClick(event);
		else if(SubjectCode.compareTo("07")==0)//�ŷ� �Խ��� ���
			new GBD_RPG.Structure.Struct_TradeBoard().TradeBoardMainGUIClick(event);
		else if(SubjectCode.compareTo("08")==0)//�ŷ� �Խ��� ����
			new GBD_RPG.Structure.Struct_TradeBoard().TradeBoardSettingGUIClick(event);
		else if(SubjectCode.compareTo("09")==0)//�ŷ� �Խ��� �޴�
			new GBD_RPG.Structure.Struct_TradeBoard().SelectTradeTypeGUIClick(event);
		else if(SubjectCode.compareTo("0a")==0)//�Ǹ��� ������ ����
			new GBD_RPG.Structure.Struct_TradeBoard().SelectSellItemGUIClick(event);
		else if(SubjectCode.compareTo("0b")==0)//������ ������ ����
			new GBD_RPG.Structure.Struct_TradeBoard().SelectBuyItemGUIClick(event);
		else if(SubjectCode.compareTo("0c")==0)//��ȯ�� ���� ���� ������ ����
			new GBD_RPG.Structure.Struct_TradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(SubjectCode.compareTo("0d")==0)//��ȯ�� ���� �� ������ ����
			new GBD_RPG.Structure.Struct_TradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(SubjectCode.compareTo("0e")==0)//�Ϲ� ������ ���
			new GBD_RPG.Structure.Struct_TradeBoard().SelectNormalItemGUIClick(event);
		else if(SubjectCode.compareTo("0f")==0)//��ں�
			new GBD_RPG.Structure.Struct_CampFire().CampFireGUIClick(event);
	}
	
	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("04")==0)//������ ������ ���
			new GBD_RPG.Structure.Struct_PostBox().ItemPutterGUIClose(event);
	}
}
