package skill;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _SkillGUIManager
{
	//Skill GUI Click Unique Number = 0b
	//��ų ���� GUI�� ���� ��ȣ�� 0b�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ��ų ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//��ü ��ų ���
			new skill.OPboxSkillGui().AllSkillsGUIClick(event);
		else if(SubjectCode.equals("01"))//��ų ����
			new skill.OPboxSkillGui().IndividualSkillOptionGUIClick(event);
		else if(SubjectCode.equals("02"))//��ų ��ũ�� ����
			new skill.OPboxSkillGui().SkillRankOptionGUIClick(event);
		else if(SubjectCode.equals("03"))//������ ����
			new skill.UserSkillGui().MapleStory_MainSkillsListGUIClick(event);
		else if(SubjectCode.equals("04"))//ī�װ� ����
			new skill.UserSkillGui().Mabinogi_MainSkillsListGUIClick(event);
		else if(SubjectCode.equals("05"))//���� ��ų ���
			new skill.UserSkillGui().SkillListGUIClick(event);
		else if(SubjectCode.equals("06"))//������ ���
			new skill.UserSkillGui().AddQuickBarGUIClick(event);
		else if(SubjectCode.equals("07"))//��� �������� ����
			new otherplugins.SpellMain().ShowAllMaigcGUIClick(event);
	}
}
