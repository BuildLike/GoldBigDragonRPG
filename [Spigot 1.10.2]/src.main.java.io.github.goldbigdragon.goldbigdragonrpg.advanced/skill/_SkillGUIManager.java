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
		if(SubjectCode.compareTo("00")==0)//��ü ��ų ���
			new skill.OPboxSkill_GUI().AllSkillsGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//��ų ����
			new skill.OPboxSkill_GUI().IndividualSkillOptionGUIClick(event);
		else if(SubjectCode.compareTo("02")==0)//��ų ��ũ�� ����
			new skill.OPboxSkill_GUI().SkillRankOptionGUIClick(event);
		else if(SubjectCode.compareTo("03")==0)//������ ����
			new skill.UserSkill_GUI().MapleStory_MainSkillsListGUIClick(event);
		else if(SubjectCode.compareTo("04")==0)//ī�װ� ����
			new skill.UserSkill_GUI().Mabinogi_MainSkillsListGUIClick(event);
		else if(SubjectCode.compareTo("05")==0)//���� ��ų ���
			new skill.UserSkill_GUI().SkillListGUIClick(event);
		else if(SubjectCode.compareTo("06")==0)//������ ���
			new skill.UserSkill_GUI().AddQuickBarGUIClick(event);
		else if(SubjectCode.compareTo("07")==0)//��� �������� ����
			new otherplugins.SpellMain().ShowAllMaigcGUIClick(event);
	}
}
