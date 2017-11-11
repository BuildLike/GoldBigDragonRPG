package dungeon;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class _DungeonGUIManager
{
	//Dungeon GUI Click Unique Number = 0a
	//���� ���� GUI�� ���� ��ȣ�� 0a�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ������ ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//���� ���
			new dungeon.Dungeon_GUI().DungeonListMainGUIClick(event);
		else if(SubjectCode.equals("01"))//���� ����
			new dungeon.Dungeon_GUI().DungeonSetUpGUIClick(event);
		else if(SubjectCode.equals("02"))//���� ����
			new dungeon.Dungeon_GUI().DungeonChestRewardClick(event);
		else if(SubjectCode.equals("03"))//���� ���� ����
			new dungeon.Dungeon_GUI().DungeonMonsterGUIMainClick(event);
		else if(SubjectCode.equals("04"))//���� ���� Ÿ�� ����
			new dungeon.Dungeon_GUI().DungeonMonsterChooseMainClick(event);
		else if(SubjectCode.equals("05"))//���� �Ϲ� ����
			new dungeon.Dungeon_GUI().DungeonSelectNormalMonsterChooseClick(event);
		else if(SubjectCode.equals("06"))//���� Ŀ���� ����
			new dungeon.Dungeon_GUI().DungeonSelectCustomMonsterChooseClick(event);
		else if(SubjectCode.equals("07"))//���� ��� ����
			new dungeon.Dungeon_GUI().DungeonMusicSettingGUIClick(event);
		else if(SubjectCode.equals("08"))//���� ������ ����
			new dungeon.Dungeon_GUI().EnterCardSetUpGUIClick(event);
		else if(SubjectCode.equals("09"))//���� ������ ����
			new dungeon.Dungeon_GUI().EnterCardDungeonSettingGUIClick(event);
		else if(SubjectCode.equals("0a"))//���� ���� ���
			new dungeon.Dungeon_GUI().AltarShapeListGUIClick(event);
		else if(SubjectCode.equals("0b"))//���� ���� ����
			new dungeon.Dungeon_GUI().AltarSettingGUIClick(event);
		else if(SubjectCode.equals("0c"))//���� ���� ����
			new dungeon.Dungeon_GUI().AltarDungeonSettingGUIClick(event);
		else if(SubjectCode.equals("0d"))//���� ���ܿ� ��ϵ� ������ ���
			new dungeon.Dungeon_GUI().AltarEnterCardSettingGUIClick(event);
		else if(SubjectCode.equals("0e"))//������ ������ ���
			new dungeon.Dungeon_GUI().AltarEnterCardListGUIClick(event);
		else if(SubjectCode.equals("0f"))//���� ���� ���� ȭ��
			new dungeon.Dungeon_GUI().AltarUseGUIClick(event);
		else if(SubjectCode.equals("10"))//���� �ܷ� ȭ��
			new dungeon.Dungeon_GUI().DungeonEXITClick(event);
		
	}

	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("02"))//���� ����
			new dungeon.Dungeon_GUI().DungeonChestRewardClose(event);
		else if(SubjectCode.equals("0f"))//���� ���� ���� ȭ��
			new dungeon.Dungeon_GUI().AltarUSEGuiClose(event);
	}
}
