package GBD_RPG.Job;

import org.bukkit.event.inventory.InventoryClickEvent;

public class _JobGUIManager
{
	//Job GUI Click Unique Number = 06
	//���� ���� GUI�� ���� ��ȣ�� 06�Դϴ�.
	
	//If you want add this system, just Put it in for Main_InventoryClcik!
	//����� ���� ���� GUI ����� �ְ������, �׳� Main_InventoryClick Ŭ���� �ȿ� ��������!
	
	public void ClickRouting(InventoryClickEvent event, String SubjectCode)
	{
		if(SubjectCode.compareTo("00")==0)//���� �ý��� ����
			new GBD_RPG.Job.Job_GUI().ChooseSystemGUIClick(event);
		else if(SubjectCode.compareTo("01")==0)//������ ���丮 ���� ��ü ���� ���
			new GBD_RPG.Job.Job_GUI().MapleStory_ChooseJobClick(event);
		else if(SubjectCode.compareTo("02")==0)//������ ���丮 ���� ��ü ���� ����
			new GBD_RPG.Job.Job_GUI().MapleStory_JobSettingClick(event);
		else if(SubjectCode.compareTo("03")==0)//������ ��ü ī�װ� ���
			new GBD_RPG.Job.Job_GUI().Mabinogi_ChooseCategoryClick(event);
		else if(SubjectCode.compareTo("04")==0)//������ ��ų ����
			new GBD_RPG.Job.Job_GUI().Mabinogi_SkillSettingClick(event);
		else if(SubjectCode.compareTo("05")==0)//��ϵ� ��ų ����
			new GBD_RPG.Job.Job_GUI().AddedSkillsListGUIClick(event);
	}
}
