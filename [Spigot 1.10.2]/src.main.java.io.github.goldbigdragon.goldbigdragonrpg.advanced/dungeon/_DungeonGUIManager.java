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
		if(SubjectCode.compareTo("00")==0)//���� ���
			new dungeon.Dungeon_GUI().DungeonListMainGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//���� ����
			new dungeon.Dungeon_GUI().DungeonSetUpGUIClick(event);
		else if(SubjectCode.compareTo("02")==0)//���� ����
			new dungeon.Dungeon_GUI().DungeonChestRewardClick(event);
		else if(SubjectCode.compareTo("03")==0)//���� ���� ����
			new dungeon.Dungeon_GUI().DungeonMonsterGUIMainClick(event);
		else if(SubjectCode.compareTo("04")==0)//���� ���� Ÿ�� ����
			new dungeon.Dungeon_GUI().DungeonMonsterChooseMainClick(event);
		else if(SubjectCode.compareTo("05")==0)//���� �Ϲ� ����
			new dungeon.Dungeon_GUI().DungeonSelectNormalMonsterChooseClick(event);
		else if(SubjectCode.compareTo("06")==0)//���� Ŀ���� ����
			new dungeon.Dungeon_GUI().DungeonSelectCustomMonsterChooseClick(event);
		else if(SubjectCode.compareTo("07")==0)//���� ��� ����
			new dungeon.Dungeon_GUI().DungeonMusicSettingGUIClick(event);
		else if(SubjectCode.compareTo("08")==0)//���� ������ ����
			new dungeon.Dungeon_GUI().EnterCardSetUpGUIClick(event);
		else if(SubjectCode.compareTo("09")==0)//���� ������ ����
			new dungeon.Dungeon_GUI().EnterCardDungeonSettingGUIClick(event);
		else if(SubjectCode.compareTo("0a")==0)//���� ���� ���
			new dungeon.Dungeon_GUI().AltarShapeListGUIClick(event);
		else if(SubjectCode.compareTo("0b")==0)//���� ���� ����
			new dungeon.Dungeon_GUI().AltarSettingGUIClick(event);
		else if(SubjectCode.compareTo("0c")==0)//���� ���� ����
			new dungeon.Dungeon_GUI().AltarDungeonSettingGUIClick(event);
		else if(SubjectCode.compareTo("0d")==0)//���� ���ܿ� ��ϵ� ������ ���
			new dungeon.Dungeon_GUI().AltarEnterCardSettingGUIClick(event);
		else if(SubjectCode.compareTo("0e")==0)//������ ������ ���
			new dungeon.Dungeon_GUI().AltarEnterCardListGUIClick(event);
		else if(SubjectCode.compareTo("0f")==0)//���� ���� ���� ȭ��
			new dungeon.Dungeon_GUI().AltarUseGUIClick(event);
		else if(SubjectCode.compareTo("10")==0)//���� �ܷ� ȭ��
			new dungeon.Dungeon_GUI().DungeonEXITClick(event);
		
	}

	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("02")==0)//���� ����
			new dungeon.Dungeon_GUI().DungeonChestRewardClose(event);
		else if(SubjectCode.compareTo("0f")==0)//���� ���� ���� ȭ��
			new dungeon.Dungeon_GUI().AltarUSEGuiClose(event);
	}
}
