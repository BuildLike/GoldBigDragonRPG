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
			new job.Job_GUI().ChooseSystemGUIClick(event);
		else if(SubjectCode.equals("01"))//������ ���丮 ���� ��ü ���� ���
			new job.Job_GUI().MapleStory_ChooseJobClick(event);
		else if(SubjectCode.equals("02"))//������ ���丮 ���� ��ü ���� ����
			new job.Job_GUI().MapleStory_JobSettingClick(event);
		else if(SubjectCode.equals("03"))//������ ��ü ī�װ� ���
			new job.Job_GUI().Mabinogi_ChooseCategoryClick(event);
		else if(SubjectCode.equals("04"))//������ ��ų ����
			new job.Job_GUI().Mabinogi_SkillSettingClick(event);
		else if(SubjectCode.equals("05"))//��ϵ� ��ų ����
			new job.Job_GUI().AddedSkillsListGUIClick(event);
	}
}
