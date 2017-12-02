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
			new dungeon.DungeonGui().DungeonListMainGUIClick(event);
		else if(SubjectCode.equals("01"))//���� ����
			new dungeon.DungeonGui().DungeonSetUpGUIClick(event);
		else if(SubjectCode.equals("02"))//���� ����
			new dungeon.DungeonGui().DungeonChestRewardClick(event);
		else if(SubjectCode.equals("03"))//���� ���� ����
			new dungeon.DungeonGui().DungeonMonsterGUIMainClick(event);
		else if(SubjectCode.equals("04"))//���� ���� Ÿ�� ����
			new dungeon.DungeonGui().DungeonMonsterChooseMainClick(event);
		else if(SubjectCode.equals("05"))//���� �Ϲ� ����
			new dungeon.DungeonGui().DungeonSelectNormalMonsterChooseClick(event);
		else if(SubjectCode.equals("06"))//���� Ŀ���� ����
			new dungeon.DungeonGui().DungeonSelectCustomMonsterChooseClick(event);
		else if(SubjectCode.equals("07"))//���� ��� ����
			new dungeon.DungeonGui().DungeonMusicSettingGUIClick(event);
		else if(SubjectCode.equals("08"))//���� ������ ����
			new dungeon.DungeonGui().EnterCardSetUpGUIClick(event);
		else if(SubjectCode.equals("09"))//���� ������ ����
			new dungeon.DungeonGui().EnterCardDungeonSettingGUIClick(event);
		else if(SubjectCode.equals("0a"))//���� ���� ���
			new dungeon.DungeonGui().AltarShapeListGUIClick(event);
		else if(SubjectCode.equals("0b"))//���� ���� ����
			new dungeon.DungeonGui().AltarSettingGUIClick(event);
		else if(SubjectCode.equals("0c"))//���� ���� ����
			new dungeon.DungeonGui().AltarDungeonSettingGUIClick(event);
		else if(SubjectCode.equals("0d"))//���� ���ܿ� ��ϵ� ������ ���
			new dungeon.DungeonGui().AltarEnterCardSettingGUIClick(event);
		else if(SubjectCode.equals("0e"))//������ ������ ���
			new dungeon.DungeonGui().AltarEnterCardListGUIClick(event);
		else if(SubjectCode.equals("0f"))//���� ���� ���� ȭ��
			new dungeon.DungeonGui().AltarUseGUIClick(event);
		else if(SubjectCode.equals("10"))//���� �ܷ� ȭ��
			new dungeon.DungeonGui().DungeonEXITClick(event);
		
	}

	public void CloseRouting(InventoryCloseEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("02"))//���� ����
			new dungeon.DungeonGui().DungeonChestRewardClose(event);
		else if(SubjectCode.equals("0f"))//���� ���� ���� ȭ��
			new dungeon.DungeonGui().AltarUSEGuiClose(event);
	}
}
