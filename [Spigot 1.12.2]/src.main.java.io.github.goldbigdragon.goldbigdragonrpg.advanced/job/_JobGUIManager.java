package job;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _JobGUIManager
{
	//Job GUI Click Unique Number = 06
	//���� ���� GUI�� ���� ��ȣ�� 06�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.equals("00"))//���� �ý��� ����
			new job.JobGUI().ChooseSystemGUIClick(event);
		else if(SubjectCode.equals("01"))//������ ���丮 ���� ��ü ���� ���
			new job.JobGUI().MapleStory_ChooseJobClick(event);
		else if(SubjectCode.equals("02"))//������ ���丮 ���� ��ü ���� ����
			new job.JobGUI().MapleStory_JobSettingClick(event);
		else if(SubjectCode.equals("03"))//������ ��ü ī�װ� ���
			new job.JobGUI().Mabinogi_ChooseCategoryClick(event);
		else if(SubjectCode.equals("04"))//������ ��ų ����
			new job.JobGUI().Mabinogi_SkillSettingClick(event);
		else if(SubjectCode.equals("05"))//��ϵ� ��ų ����
			new job.JobGUI().AddedSkillsListGUIClick(event);
	}
}
