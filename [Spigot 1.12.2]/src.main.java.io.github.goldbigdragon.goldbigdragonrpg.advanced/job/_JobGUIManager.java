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
			new job.JobGUI().chooseSystemGUIClick(event);
		else if(SubjectCode.equals("01"))//������ ���丮 ���� ��ü ���� ���
			new job.JobGUI().mapleStory_ChooseJobClick(event);
		else if(SubjectCode.equals("02"))//������ ���丮 ���� ��ü ���� ����
			new job.JobGUI().mapleStory_JobSettingClick(event);
		else if(SubjectCode.equals("03"))//������ ��ü ī�װ� ���
			new job.JobGUI().mabinogi_ChooseCategoryClick(event);
		else if(SubjectCode.equals("04"))//������ ��ų ����
			new job.JobGUI().mabinogi_SkillSettingClick(event);
		else if(SubjectCode.equals("05"))//��ϵ� ��ų ����
			new job.JobGUI().addedSkillsListGUIClick(event);
	}
}
