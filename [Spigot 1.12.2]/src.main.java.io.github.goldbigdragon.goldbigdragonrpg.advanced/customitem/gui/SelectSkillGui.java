package customitem.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import effect.SoundEffect;
import util.GuiUtil;
import util.YamlLoader;

public class SelectSkillGui extends GuiUtil{

	public void selectSkillGui(Player player, int page, int itemNumber)
	{
	  	YamlLoader skillYaml = new YamlLoader();
		skillYaml.getConfig("Skill/SkillList.yml");

		String uniqueCode = "��0��0��3��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, uniqueCode + "��0��� ���� ��ų ��� : " + (page+1));

		String[] skillList= skillYaml.getKeys().toArray(new String[0]);
		
		byte loc=0;
		for(int count = page*45; count < skillList.length;count++)
		{
			short jobLevel= (short) skillYaml.getConfigurationSection(skillList[count].toString()+".SkillRank").getKeys(false).size();
			if(count > skillList.length || loc >= 45) break;

		  	YamlLoader jobYaml = new YamlLoader();
			jobYaml.getConfig("Skill/JobList.yml");
			if(jobYaml.contains("Mabinogi.Added."+skillList[count])==true)
			{
				removeFlagStack("��f��l" + skillList[count],  skillYaml.getInt(skillList[count].toString()+".ID"),skillYaml.getInt(skillList[count].toString()+".DATA"),skillYaml.getInt(skillList[count].toString()+".Amount"),Arrays.asList("��3�ִ� ��ų ���� : ��f"+jobLevel,"","��e[�� Ŭ���� ��ų ���]"), loc, inv);
				loc++;	
			}
		}
		
		if(skillList.length-(page*44)>45)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
		removeFlagStack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		removeFlagStack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		removeFlagStack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+itemNumber), 53, inv);
		player.openInventory(inv);
	}
	
	public void selectSkillGuiClick(InventoryClickEvent event)
	{
		
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		if(slot == 53)//������
		{
			SoundEffect.playSound(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			int number = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1)));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			if(slot == 45)//���� ���
				new UseableItemMakingGui().useableItemMakingGui(player, number);
			else if(slot == 48)//���� ������
				selectSkillGui(player, page-1, number);
			else if(slot == 50)//���� ������
				selectSkillGui(player, page, number);
			else
			{
			  	YamlLoader useableItemYaml = new YamlLoader();
				useableItemYaml.getConfig("Item/Consume.yml");
				useableItemYaml.set(number+".Skill", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				useableItemYaml.saveConfig();
				new UseableItemMakingGui().useableItemMakingGui(player, number);
			}
		}
	}
	
}
