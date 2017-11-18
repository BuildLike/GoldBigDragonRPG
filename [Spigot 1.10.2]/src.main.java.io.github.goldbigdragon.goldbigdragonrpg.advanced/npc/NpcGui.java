package npc;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import effect.PottionBuff;
import effect.SoundEffect;
import main.MainServerOption;
import user.UserDataObject;
import util.UtilGui;
import util.UtilNumber;
import util.YamlLoader;



public class NpcGui extends UtilGui
{
	public void MainGUI(Player player, String NPCname, boolean isOP)
	{
		String UniqueCode = "��0��0��7��0��0��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0[NPC] "+ChatColor.stripColor(NPCname));
		UserDataObject u = new UserDataObject();
		Stack2("��f��l��ȭ�� �Ѵ�", 340,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7��ȭ�� �մϴ�."), 10, inv);
		Stack2("��f��l�ŷ��� �Ѵ�", 371,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7�ŷ��� ��û�մϴ�."), 12, inv);
		Stack2("��f��l����Ʈ", 386,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7���� ���� ������","��7����ϴ�."), 14, inv);

		YamlLoader playerNPCYaml = new YamlLoader();
    	if(playerNPCYaml.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
    	{
    		playerNPCYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
    		playerNPCYaml.set(u.getNPCuuid(player)+".love", 0);
    		playerNPCYaml.set(u.getNPCuuid(player)+".Career", 0);
    		playerNPCYaml.saveConfig();
    	}
    	else
    		playerNPCYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
		Stack2("��f��l�����ϱ�", 54,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7�ڽ��� ������ �ִ�","��7�������� �����մϴ�.","��7(NPC���� ȣ���� ���)","",
				"��d[���� ȣ����]","��c��l �� ��f��l"+playerNPCYaml.getInt(u.getNPCuuid(player)+".love") + " / 1000"), 16, inv);
		Stack2("��f��l������", 324,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7��ȭ�� �����մϴ�.","��0"+ u.getNPCuuid(player)), 26, inv);
		if(player.isOp())
			Stack2("��f��lGUI �� Ȱ��ȭ", 166,0,1,Arrays.asList("��7�� NPC�� GoldBigDragonRPG��","��7NPC GUI ȭ���� ������� �ʰ� �մϴ�.",""), 8, inv);

		YamlLoader NPCscript = new YamlLoader();
		NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player)  +".yml");
		if(NPCscript.contains("Job") == false)
		{
		  	NPCscript.set("Job.Type", "null");
			NPCscript.saveConfig();
		}
		if(NPCscript.contains("NatureTalk") == false)
		{
    	  	NPCscript.set("NPCuuid", "NPC's uuid");
    	  	NPCscript.set("KoreaLanguage(UTF-8)->JavaEntityLanguage", "http://itpro.cz/juniconv/");
    	  	NPCscript.set("NatureTalk.1.love", 0);
    	  	NPCscript.set("NatureTalk.1.Script", "��f�ȳ�, ��e%player%?");
    	  	NPCscript.set("NatureTalk.2.love", 0);
    	  	NPCscript.set("NatureTalk.2.Script", "��f��� �����%enter%��f�̷��� ����.");
    	  	NPCscript.set("NatureTalk.3.love", 0);
    	  	NPCscript.set("NatureTalk.3.Script", "��1������ ��4�̷��� ��f���� �� �־�!");
    	  	NPCscript.saveConfig();
		}
		if(NPCscript.contains("NearByNEWS") == false)
		{
    	  	NPCscript.set("NearByNEWS.1.love", 0);
    	  	NPCscript.set("NearByNEWS.1.Script", "��f���������� ���� ���̾Ƹ�带 �� �� ������...");
    	  	NPCscript.set("NearByNEWS.2.love", 0);
    	  	NPCscript.set("NearByNEWS.2.Script", "��f�����ϴ°� ����.%enter%��f���� ��4���κ� ��f�� �� ��ó�� �־��ŵ�...");
    	  	NPCscript.set("NearByNEWS.3.love", 0);
    	  	NPCscript.set("NearByNEWS.3.Script", "��f��...");
    	  	NPCscript.saveConfig();
		}
		if(NPCscript.contains("AboutSkills") == false)
		{
    	  	NPCscript.set("AboutSkills.1.love", 0);
    	  	NPCscript.set("AboutSkills.1.giveSkill", "null");
    	  	NPCscript.set("AboutSkills.1.AlreadyGetScript", "null");
    	  	NPCscript.set("AboutSkills.1.Script", "��f���� ��eä�� ��ų��f�� ����!%enter%��f��? �ʵ� �ִٰ�?");
    	  	NPCscript.set("AboutSkills.2.love", 0);
    	  	NPCscript.set("AboutSkills.2.giveSkill", "null");
    	  	NPCscript.set("AboutSkills.2.AlreadyGetScript", "null");
    	  	NPCscript.set("AboutSkills.2.Script", "��f�޸���� ���� �ǰ����� ������%enter%��f�������� ����.");
    	  	NPCscript.set("AboutSkills.3.love", 0);
    	  	NPCscript.set("AboutSkills.3.giveSkill", "null");
    	  	NPCscript.set("AboutSkills.3.AlreadyGetScript", "null");
    	  	NPCscript.set("AboutSkills.3.Script", "��f�ʿ��� ������ �ٸ���%enter%��f����� ���°� ������...");
    	  	NPCscript.saveConfig();
		}
		if(NPCscript.contains("Shop.Sell") == false)
		{
		  	NPCscript.createSection("Shop.Sell");
    	  	NPCscript.saveConfig();
		}
		if(NPCscript.contains("Shop.Buy") == false)
		{
		  	NPCscript.createSection("Shop.Buy");
    	  	NPCscript.saveConfig();
		}
		if(NPCscript.contains("Quest") == false)
		{
		  	NPCscript.createSection("Quest");
    	  	NPCscript.saveConfig();
		}
		switch(NPCscript.getString("Job.Type"))
		{
		case "BlackSmith":
				Stack2("��6��l��������", 145,0,1,Arrays.asList("��7�տ� �� ���� �� ����, ����","��7������ Ȯ���� ���ݿ� ���� �� �ݴϴ�.","","��3���� ������ : ��f" +NPCscript.getInt("Job.FixRate")+"��3 %","��a������ 10 �� ���� : ��e"+NPCscript.getInt("Job.10PointFixDeal")+" "+ChatColor.GREEN+MainServerOption.money,"","��e[�� Ŭ���� ���� ����ȭ�� �̵�]","��c[�Ϲ� ������ ���� ���н�]","��c - ���� ������ ��� ���� ��� �Ҹ�","��c[Ŀ���� ������ ���� ���н�]","��c - �ִ� ������ ����"), 4, inv);
			break;
		case "Shaman":
			Stack2("��6��l�ּ���",381,0,1,Arrays.asList("��7�÷��̾�� ������ ���� ȿ����","��7���� �ݾ��� �ް� �ο��� �ݴϴ�.","��b���� ������ : ��f" + NPCscript.getInt("Job.GoodRate") + "��3%","��b���� �ð� : ��f" + NPCscript.getInt("Job.BuffTime")+"��3 ��","��a��ä ��� : ��f"+NPCscript.getInt("Job.Deal")+ChatColor.GREEN+MainServerOption.money,"��e[�� Ŭ���� �� ġ��]","��c[���� ���н�]","��c - ������ ������� ��� �Ҹ�","��c - ���� �ð����� ���� ȿ��"), 4, inv);
		break;
		case "Healer":
			Stack2("��6��l����", 373,8261,1,Arrays.asList("��7������ ���� �ݾ��� �ް�","��7�÷��̾��� ����� ȸ�� ��","��7���� ���� ȿ���� ������ �ݴϴ�.","��aġ�� ��� : ��f"+NPCscript.getInt("Job.Deal")+ChatColor.GREEN+MainServerOption.money,"��e[�� Ŭ���� ġ�� �ޱ�]"), 4, inv);
		break;
		case "Master":
			YamlLoader JobList = new YamlLoader();
			JobList.getConfig("Skill/JobList.yml");
			boolean isExitJob = false;
			Object[] Job = JobList.getConfigurationSection("MapleStory").getKeys(false).toArray();
			for(int count = 0; count < Job.length; count++)
			{
				Object[] a = JobList.getConfigurationSection("MapleStory."+Job[count].toString()).getKeys(false).toArray();
				for(int counter=0;counter<a.length;counter++)
				{
					if(a[counter].toString().equalsIgnoreCase(NPCscript.getString("Job.Job"))==true)
						isExitJob = true;
				}
			}
			if(isExitJob == false)
			{
				NPCscript.removeKey("Job");
				NPCscript.set("Job.Type","null");
				NPCscript.saveConfig();
			}
			for(int count = 0; count < Job.length; count++)
			{
				String[] a = JobList.getConfigurationSection("MapleStory."+Job[count].toString()).getKeys(false).toArray(new String[0]);
				for(int counter=0;counter<a.length;counter++)
				{
					if(a[counter].equals(NPCscript.getString("Job.Job")))
					{
						YamlLoader PlayerJob = new YamlLoader();
						PlayerJob.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
						short ID = (short) JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".IconID");
						byte DATA = (byte) JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".IconData");
						byte Amount = (byte) JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".IconAmount");
						int NeedLV = JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".NeedLV");
						int NeedSTR = JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".NeedSTR");
						int NeedDEX = JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".NeedDEX");
						int NeedINT = JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".NeedINT");
						int NeedWILL = JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".NeedWILL");
						int NeedLUK = JobList.getInt("MapleStory."+Job[count].toString()+"."+a[counter]+".NeedLUK");
						String PrevJob = JobList.getString("MapleStory."+Job[count].toString()+"."+a[counter]+".PrevJob");

						String lore = "%enter%��9��l   ["+NPCscript.getString("Job.Job")+" ����]   %enter%��7%enter%��7     [���� ����]     %enter%%enter%";

						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level()<NeedLV)
							lore = lore + "��c�ʿ� ���� : "+NeedLV+"%enter%";
						else
							lore = lore + "��b�ʿ� ���� : "+NeedLV+"%enter%";
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR()<NeedSTR)
							lore = lore + "��c�ʿ� "+MainServerOption.statSTR+" : "+NeedSTR+"%enter%";
						else
							lore = lore + "��b�ʿ� "+MainServerOption.statSTR+" : "+NeedSTR+"%enter%";
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX()<NeedDEX)
							lore = lore + "��c�ʿ� "+MainServerOption.statDEX+" : "+NeedDEX+"%enter%";
						else
							lore = lore + "��b�ʿ� "+MainServerOption.statDEX+" : "+NeedDEX+"%enter%";
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT()<NeedINT)
							lore = lore + "��c�ʿ� "+MainServerOption.statINT+" : "+NeedINT+"%enter%";
						else
							lore = lore + "��b�ʿ� "+MainServerOption.statINT+" : "+NeedINT+"%enter%";
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL()<NeedWILL)
							lore = lore + "��c�ʿ� "+MainServerOption.statWILL+" : "+NeedWILL+"%enter%";
						else
							lore = lore + "��b�ʿ� "+MainServerOption.statWILL+" : "+NeedWILL+"%enter%";
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK()<NeedLUK)
							lore = lore + "��c�ʿ� "+MainServerOption.statLUK+" : "+NeedLUK+"%enter%";
						else
							lore = lore + "��b�ʿ� "+MainServerOption.statLUK+" : "+NeedLUK+"%enter%";
						if(PrevJob.equalsIgnoreCase("null")==false)
						{
							if(PlayerJob.getString("Job.Type").equalsIgnoreCase(PrevJob)==false)
								lore = lore + "��c���� ���� : "+PrevJob+"%enter%";
							else
								lore = lore + "��b���� ���� : "+PrevJob+"%enter%";
						}
						
						lore = lore + "%enter%��e[�� Ŭ���� ����]";
						
						String[] scriptA = lore.split("%enter%");
						for(int county = 0; county < scriptA.length; county++)
							scriptA[county] =  scriptA[county];
						
						Stack2("��6��l���� ����", ID,DATA,Amount,Arrays.asList(scriptA), 4, inv);
					}
				}
			}
			break;
		case "Warper":
			Stack2("��6��l���� �̵�����", 368,0,1,Arrays.asList("��7���� �̵������ ���� �ݾ��� �ް�","��7�÷��̾ Ư�� �������� �̵����� �ݴϴ�.","��a��ϵ� ���� : ��f"+NPCscript.getConfigurationSection("Job.WarpList").getKeys(false).size()+"��a ����","","��e[�� Ŭ���� ���� ��� ����]"), 4, inv);
		break;
		case "Upgrader":
			Stack2("��6��l���� ����", 417,0,1,Arrays.asList("��7���� ������ ���� �ݾ��� �ް�","��7���� ���θ��� �˰� �ִ�","��7���� �����Ǹ� �����Ͽ�","��7���� �տ� �� ���⸦","��7���� ���� �ݴϴ�.","��a�̿� ������ ������ : ��f"+NPCscript.getConfigurationSection("Job.UpgradeRecipe").getKeys(false).size()+"��a ����","","��e[�� Ŭ���� ���� ������ ����]"), 4, inv);
		break;
		case "Rune":
			Stack2("��6��l�� ������", 351,10,1,Arrays.asList("��7�� ������� ���� �ݾ��� �ް�","��7������ �ִ� ���� �����Ͽ�","��7���� �տ� �� ���⿡","��7����ִ� �� ������ �ִٸ�","��7���� ���� ���� �ݴϴ�.","","��a�� ���� ������ : ��f" +NPCscript.getInt("Job.SuccessRate")+"��3%","��a�� ���� ���� : ��e"+NPCscript.getInt("Job.Deal")+ChatColor.GREEN+MainServerOption.money,"","��e[�� Ŭ���� �� ����]"), 4, inv);
		break;
		}
		
		if(isOP == true)
		{
			switch(NPCscript.getString("Job.Type"))
			{
			case "null":
				Stack2("��6��l���� ����", 403,0,1,Arrays.asList("��7�� NPC���Դ� ���� ������ �����ϴ�!","��7������ ������ �پ��� ����� �����մϴ�.","","��e[Ŭ���� ���� ����]"), 4, inv);
				break;
			case "BlackSmith":
					Stack2("��6��l��������", 145,0,1,Arrays.asList("��7�÷��̾��� ���� �� ����, ����","��7������ Ȯ���� ���ݿ� ���� �� �ݴϴ�.","","��3���� ������ : ��f" +NPCscript.getInt("Job.FixRate")+"��3 %","��a������ 10 �� ���� : ��e"+NPCscript.getInt("Job.10PointFixDeal")+" "+MainServerOption.money,"","��c[�Ϲ� ������ ���� ���н�]","��c - ���� ������ ��� ���� "+MainServerOption.money+"��c �Ҹ�","��c[Ŀ���� ������ ���� ���н�]","��c - �ִ� ������ ����","","��e[�� Ŭ���� ���� ����ȭ�� �̵�]","��c[�� Ŭ���� ���� ����]"), 4, inv);
				break;
			case "Shaman":
				Stack2("��6��l�ּ���",381,0,1,Arrays.asList("��7�÷��̾�� ������ ���� ȿ����","��7���� �ݾ��� �ް� �ο��� �ݴϴ�.","","��b���� ������ : ��f" + NPCscript.getInt("Job.GoodRate") + "��3%","��b���� �ð� : ��f" + NPCscript.getInt("Job.BuffTime")+"��3 ��","��a��ä ��� : ��f"+NPCscript.getInt("Job.Deal")+ChatColor.GREEN+MainServerOption.money,"��c[���� ���н�]","��c - ������ ������� ��� �Ҹ�","��c - ���� �ð����� ���� ȿ��","","��e[�� Ŭ���� �� ġ��]","��c[�� Ŭ���� ���� ����]"), 4, inv);
			break;
			case "Healer":
				Stack2("��6��l����", 373,8261,1,Arrays.asList("��7������ ���� �ݾ��� �ް�","��7�÷��̾��� ����� ȸ�� ��","��7���� ���� ȿ���� ������ �ݴϴ�.","��aġ�� ��� : ��f"+NPCscript.getInt("Job.Deal")+ChatColor.GREEN+MainServerOption.money,"","��e[�� Ŭ���� ġ�� �ޱ�]","��c[�� Ŭ���� ���� ����]"), 4, inv);
			break;
			case "Master":
				YamlLoader JobList = new YamlLoader();
				JobList.getConfig("Skill/JobList.yml");
				String[] Job = JobList.getConfigurationSection("MapleStory").getKeys(false).toArray(new String[0]);
				String PrevJob = null;
				String lore = null;
				for(int count = 0; count < Job.length; count++)
				{
					String[] a = JobList.getConfigurationSection("MapleStory."+Job[count]).getKeys(false).toArray(new String[0]);
					for(int counter=0;counter<a.length;counter++)
					{
						if(a[counter].toString().equalsIgnoreCase(NPCscript.getString("Job.Job"))==true)
						{
							YamlLoader PlayerJob = new YamlLoader();
							PlayerJob.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
							short ID = (short) JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".IconID");
							byte DATA = (byte) JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".IconData");
							byte Amount = (byte) JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".IconAmount");
							int NeedLV = JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".NeedLV");
							int NeedSTR = JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".NeedSTR");
							int NeedDEX = JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".NeedDEX");
							int NeedINT = JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".NeedINT");
							int NeedWILL = JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".NeedWILL");
							int NeedLUK = JobList.getInt("MapleStory."+Job[count]+"."+a[counter]+".NeedLUK");
							PrevJob = JobList.getString("MapleStory."+Job[count]+"."+a[counter]+".PrevJob");						
							lore = "%enter%��9��l   ["+NPCscript.getString("Job.Job")+" ����]   %enter%��7%enter%��7     [���� ����]     %enter%%enter%";
							
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level()<NeedLV)
								lore = lore + "��c�ʿ� ���� : "+NeedLV+"%enter%";
							else
								lore = lore + "��b�ʿ� ���� : "+NeedLV+"%enter%";
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR()<NeedSTR)
								lore = lore + "��c�ʿ� "+MainServerOption.statSTR+" : "+NeedSTR+"%enter%";
							else
								lore = lore + "��b�ʿ� "+MainServerOption.statSTR+" : "+NeedSTR+"%enter%";
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX()<NeedDEX)
								lore = lore + "��c�ʿ� "+MainServerOption.statDEX+" : "+NeedDEX+"%enter%";
							else
								lore = lore + "��b�ʿ� "+MainServerOption.statDEX+" : "+NeedDEX+"%enter%";
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT()<NeedINT)
								lore = lore + "��c�ʿ� "+MainServerOption.statINT+" : "+NeedINT+"%enter%";
							else
								lore = lore + "��b�ʿ� "+MainServerOption.statINT+" : "+NeedINT+"%enter%";
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL()<NeedWILL)
								lore = lore + "��c�ʿ� "+MainServerOption.statWILL+" : "+NeedWILL+"%enter%";
							else
								lore = lore + "��b�ʿ� "+MainServerOption.statWILL+" : "+NeedWILL+"%enter%";
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK()<NeedLUK)
								lore = lore + "��c�ʿ� "+MainServerOption.statLUK+" : "+NeedLUK+"%enter%";
							else
								lore = lore + "��b�ʿ� "+MainServerOption.statLUK+" : "+NeedLUK+"%enter%";

							if(PrevJob.equalsIgnoreCase("null")==false)
							{
								if(PlayerJob.getString("Job.Type").equalsIgnoreCase(PrevJob)==false)
									lore = lore + "��c���� ���� : "+PrevJob+"%enter%";
								else
									lore = lore + "��b���� ���� : "+PrevJob+"%enter%";
							}
							
							lore = lore + "%enter%��e[�� Ŭ���� ����]%enter%��c[�� Ŭ���� ���� ����]";
							
							String[] scriptA = lore.split("%enter%");
							for(int county = 0; county < scriptA.length; county++)
								scriptA[county] =  scriptA[county];
							
							Stack2("��6��l���� ����", ID,DATA,Amount,Arrays.asList(scriptA), 4, inv);
						}
					}
				}
				break;
			case "Warper":
				Stack2("��6��l���� �̵�����", 368,0,1,Arrays.asList("��7���� �̵������ ���� �ݾ��� �ް�","��7�÷��̾ Ư�� �������� �̵����� �ݴϴ�.","��a��ϵ� ���� : ��f"+NPCscript.getConfigurationSection("Job.WarpList").getKeys(false).size()+"��a ����","","��e[�� Ŭ���� ���� ��� ����]","��c[�� Ŭ���� ���� ����]"), 4, inv);
			break;
			case "Upgrader":
				Stack2("��6��l���� ����", 417,0,1,Arrays.asList("��7���� ������ ���� �ݾ��� �ް�","��7���� ���θ��� �˰� �ִ�","��7���� �����Ǹ� �����Ͽ�","��7���� �տ� �� ���⸦","��7���� ���� �ݴϴ�.","��a�̿� ������ ������ : ��f"+NPCscript.getConfigurationSection("Job.UpgradeRecipe").getKeys(false).size()+"��a ����","","��e[�� Ŭ���� ���� ������ ����]","��c[�� Ŭ���� ���� ����]"), 4, inv);
			break;
			case "Rune":
				Stack2("��6��l�� ������", 351,10,1,Arrays.asList("��7�� ������� ���� �ݾ��� �ް�","��7������ �ִ� ���� �����Ͽ�","��7���� �տ� �� ���⿡","��7����ִ� �� ������ �ִٸ�","��7���� ���� ���� �ݴϴ�.","","��a�� ���� ������ : ��f" +NPCscript.getInt("Job.SuccessRate")+"��3%","��a�� ���� ���� : ��e"+NPCscript.getInt("Job.Deal")+" "+MainServerOption.money,"","��e[�� Ŭ���� �� ����]","��c[�� Ŭ���� ���� ����]"), 4, inv);
			break;
			}
			Stack2("��6��l��ȭ ����", 403,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7�� ��ȭ�� �� ��","��7�÷��̾�� ���� ��縦","��7�߰�/����/���� �մϴ�."), 19, inv);
			Stack2("��6��l�ŷ� ����", 403,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7��","��7�ŷ� ǰ���� ��Ŭ����","��7�ش� �������� ���� ������,","��7�ŷ� ǰ���� ��Ŭ����","��7�ش� �������� ����/�Ǹ� �����˴ϴ�.","","��f[����/�Ǹ��� ������ ��� ��ɾ�]","��6��l/���� [����/�Ǹ�] [����]"), 21, inv);
			Stack2("��6��l����Ʈ ����", 403,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7��","��7����Ʈ ������ �����մϴ�."), 23, inv);
			Stack2("��6��l���� ����", 403,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7���� �÷��̾","��7�ִ� ������ ���� ����ϴ�","��7ȣ���� ��� ��ġ�� �����մϴ�."), 25, inv);
			if(NPCscript.getBoolean("Sale.Enable"))
				Stack2("��6��l���� ����", 38,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7���� ģ�е��� ���� ���","��7���� �Ǹ� ��ǰ�� ���� �� �ݴϴ�.","","��aȣ���� "+NPCscript.getInt("Sale.Minlove") + "�̻�� " +NPCscript.getInt("Sale.discount")+"% ����","", "��c[Shift + �� Ŭ���� ����]"), 7, inv);
			else
				Stack2("��6��l���� ����", 38,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7���� ģ�е��� ���� ���","��7���� �Ǹ� ��ǰ�� ���� �� �ݴϴ�.","","��e[�� Ŭ���� ����]"), 7, inv);
				
		}
		
		player.openInventory(inv);
	}
	
	public void ShopGUI(Player player, String NPCname, short page, boolean Buy, boolean isEditMode)
	{
		UserDataObject u = new UserDataObject();
		String UniqueCode = "��0��0��7��0��1��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0[NPC] "+ChatColor.stripColor(NPCname));
		
		YamlLoader NPCscript = new YamlLoader();
	  	if(NPCscript.isExit("NPC/NPCData/"+ u.getNPCuuid(player)  +".yml") == false)
	  	{
	  		npc.NpcConfig NPCC = new npc.NpcConfig();
	  		NPCC.NPCNPCconfig(u.getNPCuuid(player));
	  	}
		NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");
		ItemStack item;
		ItemMeta IM = null;
		short a = 0;
		if(Buy == true)
		{
			a = (short) NPCscript.getConfigurationSection("Shop.Sell").getKeys(false).size();
			
			if(isEditMode == false)
				Stack2("��b     [����]     ", 160,11,1,null, 0, inv);
			else
				Stack2("��b     [����]     ", 160,11,1,Arrays.asList("��0-1"), 0, inv);
				
			Stack2("��b     [����]     ", 160,11,1,null, 1, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 2, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 3, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 4, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 5, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 6, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 7, inv);
			Stack2("��b     [����]     ", 160,11,1,Arrays.asList("��0"+page), 8, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 9, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 18, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 17, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 26, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 27, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 36, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 35, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 36, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 37, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 38, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 39, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 40, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 41, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 42, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 43, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 44, inv);
			Stack2("��c   [��ǰ �Ǹ�]   ", 160,14,1,null, 49, inv);
			byte loc=0;
			boolean Sale = NPCscript.getBoolean("Sale.Enable");
			int discount = 0;
			if(Sale)
			{
				YamlLoader PlayerNPC = new YamlLoader();
			  	PlayerNPC = null;
		    	if(PlayerNPC.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
		    	{
		    		PlayerNPC.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
		    		PlayerNPC.set(u.getNPCuuid(player)+".love", 0);
		    		PlayerNPC.set(u.getNPCuuid(player)+".Career", 0);
		    		PlayerNPC.saveConfig();
		    	}
		    	else
		    		PlayerNPC.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
		    	if(PlayerNPC.getInt(u.getNPCuuid(player)+".love") >= NPCscript.getInt("Sale.Minlove"))
					discount = NPCscript.getInt("Sale.discount");
		    	else
		    		Sale = false;
			}
			for(int count = page*21; count < a; count++)
			{
				if(count > a || loc >= 21) break;
				item = NPCscript.getItemStack("Shop.Sell."+count + ".item");
				IM = item.getItemMeta();
				long price = NPCscript.getLong("Shop.Sell."+count+".price");
				if(Sale)
					price = price - ((price/100) * discount);
				if(item.hasItemMeta() == true)
				{
					if(item.getItemMeta().hasLore() == true)
					{
						String[] lore = new String[IM.getLore().size()+3];
						for(int counter=0; counter < lore.length-3;counter++)
							lore[counter] = IM.getLore().get(counter);
						lore[lore.length-3] = "";

						if(isEditMode == false)
						{
							if(price >= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money())
								lore[lore.length-2] = "��c[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							else
								lore[lore.length-2] = "��b[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[lore.length-1] = "��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]";
						}
						else
						{
							lore[lore.length-2] = "��f[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[lore.length-1] = "��0"+ count;
						}
						IM.setLore(Arrays.asList(lore));
					}
					else
					{
						String[] lore = new String[3];
						lore[0] = "";
						if(isEditMode == false)
						{
							if(price >= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money())
								lore[1] = "��c[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							else
								lore[1] = "��b[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[2] = "��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]";
						}
						else
						{
							lore[1] = "��f[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[2] = "��0"+ count;
						}
						IM.setLore(Arrays.asList(lore));
					}
				}
				else
				{
					if(isEditMode == false)
					{
						if(price >= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money())
						{
							List<String> l = Arrays.asList("","��c[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]","��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]");
							IM.setLore(l);
						}
						else
						{
							List<String> l = Arrays.asList("","��b[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]","��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]");
							IM.setLore(l);
						}
					}
					else
					{
						List<String> l = Arrays.asList("","��f[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]","��0"+ count);
						IM.setLore(l);
					}
				}
				item.setItemMeta(IM);
				if(loc >=0 && loc <= 6)
					inv.setItem(loc+10, item);
				if(loc >=7 && loc <= 13)
					inv.setItem(loc+12, item);
				if(loc >=14 && loc <= 21)
					inv.setItem(loc+14, item);
					
				loc++;
			}
			if(a-(page*20)>21)
				Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�.", "��7������ : " + (page+2)), 50, inv);
				if(page!=0)
				Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�.", "��7������ : " + (page)), 48, inv);
			
		}
		else
		{
			a = (short) NPCscript.getConfigurationSection("Shop.Buy").getKeys(false).toArray().length;

			if(isEditMode == false)
				Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 0, inv);
			else
				Stack2("��c     [�Ǹ�]     ", 160,14,1,Arrays.asList("��0-1"), 0, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 1, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 2, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 3, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 4, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 5, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 6, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 7, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,Arrays.asList("��0"+page), 8, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 9, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 18, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 17, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 26, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 27, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 36, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 35, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 36, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 37, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 38, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 39, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 40, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 41, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 42, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 43, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 44, inv);
			Stack2("��b   [��ǰ ����]   ", 160,11,1,null, 49, inv);
			byte loc=0;
			for(int count = page*21; count < a; count++)
			{
				if(count > a || loc >= 21) break;
				item = NPCscript.getItemStack("Shop.Buy."+count + ".item");
				IM = item.getItemMeta();
				long price = NPCscript.getLong("Shop.Buy."+count+".price");
				
				if(item.hasItemMeta() == true)
				{
					if(item.getItemMeta().hasLore() == true)
					{
						String[] lore = new String[IM.getLore().size()+3];
						for(int counter=0; counter < lore.length-3;counter++)
							lore[counter] = IM.getLore().get(counter);
						lore[lore.length-3] = "";

						if(isEditMode == false)
						{
							lore[lore.length-2] = "��b[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[lore.length-1] = "��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]";
						}
						else
						{
							lore[lore.length-2] = "��f[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[lore.length-1] = "��0"+ count;
						}
						IM.setLore(Arrays.asList(lore));
					}
					else
					{
						String[] lore = new String[3];
						lore[0] = "";
						
						if(isEditMode == false)
						{
							lore[1] = "��b[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[2] = "��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]";
						}
						else
						{
							lore[1] = "��f[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]";
							lore[2] = "��0"+ count;
						}
						IM.setLore(Arrays.asList(lore));
					}
				}
				else
				{
					List<String> l =null;
					if(isEditMode == false)
						l = Arrays.asList("","��b[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]","��f[������ : " + main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() + " "+ChatColor.stripColor(MainServerOption.money)+"]");
					else
						l = Arrays.asList("","��f[���� : " + price + " "+ChatColor.stripColor(MainServerOption.money)+"]","��0"+ count);
					IM.setLore(l);
				}
				item.setItemMeta(IM);
				if(loc >=0 && loc <= 6)
					inv.setItem(loc+10, item);
				if(loc >=7 && loc <= 13)
					inv.setItem(loc+12, item);
				if(loc >=14 && loc <= 21)
					inv.setItem(loc+14, item);
					
				loc++;
			}
			if(a-(page*20)>21)
				Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�.", "��7������ : " + (page+2)), 50, inv);
				if(page!=0)
				Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�.", "��7������ : " + (page)), 48, inv);
		}

	
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ u.getNPCuuid(player)), 53, inv);

		player.openInventory(inv);
	}

	public void TalkGUI(Player player, String NPCname, String[] strings, char TalkType)
	{
		UserDataObject u = new UserDataObject();
		String UniqueCode = "��0��0��7��0��2��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0[NPC] "+ChatColor.stripColor(NPCname));

		Stack2("��f��l�������� ��ȭ", 340,0,1,Arrays.asList("��7����� �̾߱� �Ÿ��� ����ϴ�."), 2, inv);
		Stack2("��f��l��ó�� �ҹ�", 340,0,1,Arrays.asList("��7�ֱ� �鸮�� �ҹ��� ���� ����ϴ�."), 4, inv);
		Stack2("��f��l��ų�� ���Ͽ�", 340,0,1,Arrays.asList("��7��ų�� ���Ͽ� ����ϴ�."), 6, inv);
		if(TalkType != -1)
		if(strings != null)
			Stack2("��e��l "+NPCname, 386,0,1,Arrays.asList(new npc.NpcMain().getScript(player, TalkType)),(int) TalkType, inv);
		Stack2("��f��l���� �޴�", 323,0,1,Arrays.asList("��7���� �޴��� ���ư��ϴ�."), 0, inv);
		Stack2("��f��l������", 324,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7��ȭ�� �����մϴ�.","��0"+ u.getNPCuuid(player)), 8, inv);
		
		player.openInventory(inv);
	}
	
	public void QuestAddGUI(Player player, short page)
	{
		UserDataObject u = new UserDataObject();
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

	  	if(QuestList.isExit("NPC/NPCData/"+ u.getNPCuuid(player) +".yml") == false)
	  	{
	  		npc.NpcConfig NPCC = new npc.NpcConfig();
	  		NPCC.NPCNPCconfig(u.getNPCuuid(player));
	  	}
		YamlLoader NPCscript = new YamlLoader();
		NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");

		String UniqueCode = "��0��0��7��0��3��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0��� ���� ����Ʈ ��� : " + (page+1));

		Object[] a= QuestList.getKeys().toArray();
		Object[] c =  NPCscript.getConfigurationSection("Quest").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String QuestName = a[count].toString();
			Set<String> QuestFlow= QuestList.getConfigurationSection(QuestName+".FlowChart").getKeys(false);

			boolean isExit =false;
			for(int counter = 0; counter < c.length; counter ++)
			{
				if(NPCscript.getString("Quest."+counter).equals(QuestName))
				{
					isExit = true;
					break;
				}
			}
			
				
			if(count > a.length || loc >= 45) break;

			String[] Temp = new String[12];
			Temp[0] = "��f����Ʈ ���� ��� : "+QuestFlow.size()+"��";
			int lorecount = 2;
			if(QuestList.getInt(QuestName + ".Need.LV")!=0)
			{
				Temp[lorecount] = "��c���� ���� : " + QuestList.getInt(QuestName + ".Need.LV") + " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Need.Love")!=0)
			{
				Temp[lorecount] = "��cģ�е� ���� : " + QuestList.getInt(QuestName + ".Need.Love") + " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Need.STR")!=0)
			{
				Temp[lorecount] = "��c"+MainServerOption.statSTR+" ���� : " + QuestList.getInt(QuestName + ".Need.STR") + " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Need.DEX")!=0)
			{
				Temp[lorecount] = "��c"+MainServerOption.statDEX+" ���� : " + QuestList.getInt(QuestName + ".Need.DEX") + " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Need.INT")!=0)
			{
				Temp[lorecount] = "��c"+MainServerOption.statINT+" ���� : " + QuestList.getInt(QuestName + ".Need.INT") + " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Need.WILL")!=0)
			{
				Temp[lorecount] = "��c"+MainServerOption.statWILL+" ���� : " + QuestList.getInt(QuestName + ".Need.WILL") + " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Need.LUK")!=0)
			{
				Temp[lorecount] = "��c"+MainServerOption.statLUK+" ���� : " + QuestList.getInt(QuestName + ".Need.LUK" )+ " �̻�";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Server.Limit")==-1)
			{
				Temp[lorecount] = "��c[����Ʈ �����ο� ����]";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Server.Limit")==0)
			{
				Temp[lorecount] = "��a[����Ʈ ���࿡ ���� ����]";
				lorecount = lorecount+1;
			}
			if(QuestList.getInt(QuestName + ".Server.Limit")>0)
			{
				Temp[lorecount] = "��3����Ʈ ���� ���� �ο� : " + QuestList.getInt(QuestName + ".Server.Limit")+ " �� ����";
				lorecount = lorecount+1;
			}
			if(QuestList.getString(QuestName + ".Need.PrevQuest") != null && QuestList.getString(QuestName + ".Need.PrevQuest").equalsIgnoreCase("null") == false)
			{
				if(QuestList.contains(QuestList.getString(QuestName + ".Need.PrevQuest")) == false)
				{
					QuestList.set(QuestName + ".Need.PrevQuest","null");
					QuestList.saveConfig();
				}
				else
				{
					Temp[lorecount] = "��c�ʼ� �Ϸ� ����Ʈ : " + QuestList.getString(QuestName + ".Need.PrevQuest");
					lorecount = lorecount+1;
				}
			}
			
			Temp[lorecount] = "";
			lorecount = lorecount+1;
			if(isExit == true)
				Temp[lorecount] = "��a[��� �� ����Ʈ]";
			else
				Temp[lorecount] = "��c[��� ���� ���� ����Ʈ]";
			lorecount = lorecount+1;
			
			String[] lore = new String[lorecount];
			for(int counter = 0; counter < lorecount; counter++)
				lore[counter] = Temp[counter];
			
			String QuestType = QuestList.getString(QuestName + ".Type");
			switch(QuestType)
			{
			case "N" :
				lore[1] = "��3����Ʈ Ÿ�� : �Ϲ� ����Ʈ";
				Stack2("��f��l" + QuestName, 340,0,1,Arrays.asList(lore), loc, inv);
				break;
			case "R" :
				lore[1] ="��3����Ʈ Ÿ�� : �ݺ� ����Ʈ";
				Stack2("��f��l" + QuestName, 386,0,1,Arrays.asList(lore), loc, inv);
				break;
			case "D" :
				lore[1] ="��3����Ʈ Ÿ�� : ���� ����Ʈ";
				Stack2("��f��l" + QuestName, 403,0,1,Arrays.asList(lore), loc, inv);
				break;
			case "W" :
				lore[1] ="��3����Ʈ Ÿ�� : ���� ����Ʈ";
				Stack2("��f��l" + QuestName, 403,0,7,Arrays.asList(lore), loc, inv);
				break;
			case "M" :
				lore[1] ="��3����Ʈ Ÿ�� : �Ѵ� ����Ʈ";
				Stack2("��f��l" + QuestName, 403,0,31,Arrays.asList(lore), loc, inv);
				break;
			}
			loc++;
		}
		
		if(a.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		//Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ u.getNPCuuid(player)), 53, inv);
		player.openInventory(inv);
	}
	
	public void QuestListGUI(Player player, short page)
	{
		UserDataObject u = new UserDataObject();
		YamlLoader QuestList = new YamlLoader();
		QuestList.getConfig("Quest/QuestList.yml");

	  	if(QuestList.isExit("NPC/NPCData/"+ u.getNPCuuid(player) +".yml") == false)
	  	{
	  		npc.NpcConfig NPCC = new npc.NpcConfig();
	  		NPCC.NPCNPCconfig(u.getNPCuuid(player));
	  	}
		YamlLoader NPCscript = new YamlLoader();
		NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");

		String UniqueCode = "��0��0��7��0��4��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0���� ������ ����Ʈ ��� : " + (page+1));

		Object[] a = NPCscript.getConfigurationSection("Quest").getKeys(false).toArray();

		YamlLoader PlayerQuestList = new YamlLoader();
		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
		
    	if(PlayerQuestList.isExit("Quest/PlayerData/"+player.getUniqueId()+".yml")==false)
    	{
    		PlayerQuestList.set("PlayerName", player.getName());
    		PlayerQuestList.set("PlayerUUID", player.getUniqueId().toString());
    		PlayerQuestList.createSection("Started");
    		PlayerQuestList.createSection("Ended");
    		PlayerQuestList.saveConfig();
    	}

		PlayerQuestList.getConfig("Quest/PlayerData/"+player.getUniqueId()+".yml");
    	
		Set<String> PlayerHas = PlayerQuestList.getConfigurationSection("Started").getKeys(false);
		Set<String> PlayerFinished = PlayerQuestList.getConfigurationSection("Ended").getKeys(false);

		YamlLoader PlayerNPC = new YamlLoader();
    	if(PlayerNPC.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
    	{
    		PlayerNPC.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
    		PlayerNPC.set(u.getNPCuuid(player)+".love", 0);
    		PlayerNPC.set(u.getNPCuuid(player)+".Career", 0);
    		PlayerNPC.saveConfig();
    	}

		PlayerNPC.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
		
		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			String QuestName = NPCscript.getString("Quest."+a[count]);
			if(count > a.length || loc >= 45) break;

			if(QuestList.contains(QuestName) == true)
			{
				if(QuestList.getString(QuestName + ".Need.PrevQuest") != null && QuestList.getString(QuestName + ".Need.PrevQuest").equalsIgnoreCase("null") == false)
				{
					if(QuestList.contains(QuestList.getString(QuestName + ".Need.PrevQuest")) == false)
					{
						QuestList.set(QuestName + ".Need.PrevQuest","null");
						QuestList.saveConfig();
					}
				}
				boolean gogogo = false;
				if(QuestList.getString(QuestName + ".Need.PrevQuest") == null || QuestList.getString(QuestName + ".Need.PrevQuest").equalsIgnoreCase("null") == true)
				{
					gogogo = true;
				}
				if(QuestList.getString(QuestName + ".Need.PrevQuest") != null && QuestList.getString(QuestName + ".Need.PrevQuest").equalsIgnoreCase("null") == false)
				{
					if(PlayerQuestList.contains("Ended."+QuestList.getString(QuestName + ".Need.PrevQuest")) == true)
						gogogo = true;
				}
				else
				{
					gogogo = true;
				}
				if(gogogo==true)
				{
					String[] Temp = new String[12];
					Temp[0]="";
					byte lorecount = 1;
					if(QuestList.getInt(QuestName + ".Need.LV")!=0)
					{
						YamlLoader Config = new YamlLoader();
					  	Config.getConfig("config.yml");
						if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == true)
						{
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel() >= QuestList.getInt(QuestName + ".Need.LV"))
								Temp[lorecount] = "��a���� ���� : " + QuestList.getInt(QuestName + ".Need.LV") + " �̻�";
							else
								Temp[lorecount] = "��c���� ���� : " + QuestList.getInt(QuestName + ".Need.LV") + " �̻�";
						}
						else
						{
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level() >= QuestList.getInt(QuestName + ".Need.LV"))
								Temp[lorecount] = "��a���� ���� : " + QuestList.getInt(QuestName + ".Need.LV") + " �̻�";
							else
								Temp[lorecount] = "��c���� ���� : " + QuestList.getInt(QuestName + ".Need.LV") + " �̻�";
						}
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Need.Love")!=0)
					{
						if(PlayerNPC.getInt(u.getNPCuuid(player)+".love") >= QuestList.getInt(QuestName + ".Need.Love"))
							Temp[lorecount] = "��aȣ���� ���� : " + QuestList.getInt(QuestName + ".Need.Love") + " �̻�";
						else
							Temp[lorecount] = "��cȣ���� ���� : " + QuestList.getInt(QuestName + ".Need.Love") + " �̻�";
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Need.STR")!=0)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR() >= QuestList.getInt(QuestName + ".Need.STR"))
							Temp[lorecount] = "��a"+MainServerOption.statSTR+" ���� : " + QuestList.getInt(QuestName + ".Need.STR") + " �̻�";
						else
							Temp[lorecount] = "��c"+MainServerOption.statSTR+" ���� : " + QuestList.getInt(QuestName + ".Need.STR") + " �̻�";
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Need.DEX")!=0)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX() >= QuestList.getInt(QuestName + ".Need.DEX"))
							Temp[lorecount] = "��a"+MainServerOption.statDEX+" ���� : " + QuestList.getInt(QuestName + ".Need.DEX") + " �̻�";
						else
							Temp[lorecount] = "��c"+MainServerOption.statDEX+" ���� : " + QuestList.getInt(QuestName + ".Need.DEX") + " �̻�";
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Need.INT")!=0)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT() >= QuestList.getInt(QuestName + ".Need.INT"))
							Temp[lorecount] = "��a"+MainServerOption.statINT+" ���� : " + QuestList.getInt(QuestName + ".Need.INT") + " �̻�";
						else
							Temp[lorecount] = "��c"+MainServerOption.statINT+" ���� : " + QuestList.getInt(QuestName + ".Need.INT") + " �̻�";
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Need.WILL")!=0)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL() >= QuestList.getInt(QuestName + ".Need.WILL"))
							Temp[lorecount] = "��a"+MainServerOption.statWILL+" ���� : " + QuestList.getInt(QuestName + ".Need.WILL") + " �̻�";
						else
							Temp[lorecount] = "��c"+MainServerOption.statWILL+" ���� : " + QuestList.getInt(QuestName + ".Need.WILL") + " �̻�";
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Need.LUK")!=0)
					{
						if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK() >= QuestList.getInt(QuestName + ".Need.LUK"))
							Temp[lorecount] = "��a"+MainServerOption.statLUK+" ���� : " + QuestList.getInt(QuestName + ".Need.LUK" )+ " �̻�";
						else
							Temp[lorecount] = "��c"+MainServerOption.statLUK+" ���� : " + QuestList.getInt(QuestName + ".Need.LUK" )+ " �̻�";
						lorecount++;
					}
					if(QuestList.getInt(QuestName + ".Server.Limit")!=0)
					{
						if(QuestList.getInt(QuestName + ".Server.Limit")==-1)
							Temp[lorecount] = "��c���̻� ����Ʈ�� ������ �� �����ϴ�.";
						else
							Temp[lorecount] = "��a����Ʈ ���� ���� �ο� : " + QuestList.getInt(QuestName + ".Server.Limit")+ " �� ����";
						lorecount++;
					}
					
					Temp[lorecount] = "";
					lorecount++;
					
					String[] lore = new String[lorecount];
					for(int counter = 0; counter < lorecount; counter++)
						lore[counter] = Temp[counter];
					
					
					switch(QuestList.getString(QuestName+".Type"))
					{
					case "N" :
						if(PlayerHas.toString().contains(QuestName) == false && PlayerFinished.toString().contains(QuestName) == false)
						{
							lore[0] ="��f[�Ϲ� ����Ʈ]";
							Stack2("��f��l" + QuestName, 340,0,1,Arrays.asList(lore), loc, inv);
							loc++;
						}
						break;
					case "R" :
						if(PlayerHas.toString().contains(QuestName) == false)
						{
							lore[0] ="��f[�ݺ� ����Ʈ]";
							Stack2("��f��l" + QuestName, 386,0,1,Arrays.asList(lore), loc, inv);
							loc++;
						}
						break;
					case "D" :
						if(PlayerHas.toString().contains(QuestName) == false)
						{
							if(PlayerFinished.contains(QuestName) == true)
							{
								util.ETC ETC = new util.ETC();
								Object e[] = PlayerFinished.toArray();
								for(int counter=0; counter < e.length; counter++)
								{
									if(e[counter].toString().equalsIgnoreCase(QuestName))
									{
										Long ClearTime = PlayerQuestList.getLong("Ended."+e[counter]+".ClearTime") + (86400000) ;
										if(ClearTime < ETC.getNowUTC())
										{
											lore[0] ="��f[���� ����Ʈ]";
											Stack2("��f��l" + QuestName, 403,0,1,Arrays.asList(lore), loc, inv);
											loc++;
										}
										else
										{
											ClearTime = PlayerQuestList.getLong("Ended."+e[counter]+".ClearTime") + (86400000) - ETC.getNowUTC() ;
											ClearTime = ClearTime/1000;
											lore[0] ="��f[���� ����Ʈ]";
											String[] timelore = new String[lore.length+3];
											for(int counter2=0;counter2 < lore.length;counter2++)
												timelore[counter2] = lore[counter2];
											byte hour = 0;
											byte min = 0;
											
											hour = (byte) (ClearTime/3600);
											ClearTime = ClearTime-(3600*hour);
											
											min = (byte)(ClearTime/60);
											ClearTime = ClearTime-(60*min);
											
											timelore[lore.length]="";
											timelore[lore.length+1] = "��c[����Ʈ ��� �ð�]";
											if(hour>0)
												timelore[lore.length+2] = "��c"+hour+" �ð� " ;
											if(min>0)
												timelore[lore.length+2] =timelore[lore.length+2] + min+" �� " ;
											if(ClearTime>0)
												timelore[lore.length+2] =timelore[lore.length+2] + ClearTime+" �� " ;
											
											Stack2("��f��l" + QuestName, 403,0,1,Arrays.asList(timelore), loc, inv);
											loc++;
										}
									}
								}
							}
							else
							{
								lore[0] ="��f[���� ����Ʈ]";
								Stack2("��f��l" + QuestName, 403,0,1,Arrays.asList(lore), loc, inv);
								loc++;
							}
						}
						break;
					case "W" :
						if(PlayerHas.toString().contains(QuestName) == false)
						{
							if(PlayerFinished.contains(QuestName) == true)
							{
								util.ETC ETC = new util.ETC();
								Object e[] = PlayerFinished.toArray();
								for(int counter=0; counter < e.length; counter++)
								{
									if(e[counter].toString().equalsIgnoreCase(QuestName))
									{
										Long ClearTime = PlayerQuestList.getLong("Ended."+e[counter]+".ClearTime") + (604800000) ;
										if(ClearTime < ETC.getNowUTC())
										{
											lore[0] ="��f[�ְ� ����Ʈ]";
											Stack2("��f��l" + QuestName, 403,0,7,Arrays.asList(lore), loc, inv);
											loc++;
										}
										else
										{
											ClearTime = PlayerQuestList.getLong("Ended."+e[counter]+".ClearTime") + (604800000) - ETC.getNowUTC() ;
											ClearTime = ClearTime/1000;
											lore[0] ="��f[�ְ� ����Ʈ]";
											String[] timelore = new String[lore.length+3];
											for(int counter2=0;counter2 < lore.length;counter2++)
												timelore[counter2] = lore[counter2];
											byte day = 0;
											byte hour = 0;
											byte min = 0;
											
											day = (byte) (ClearTime/86400);
											ClearTime = ClearTime-(86400*day);
											
											hour = (byte) (ClearTime/3600);
											ClearTime = ClearTime-(3600*hour);
											
											min = (byte)(ClearTime/60);
											ClearTime = ClearTime-(60*min);
											
											timelore[lore.length]="";
											timelore[lore.length+1] = "��c[����Ʈ ��� �ð�]";
											if(hour>0)
												timelore[lore.length+2] = "��c"+day+" �� " ;
											if(hour>0)
												timelore[lore.length+2] = timelore[lore.length+2] +""+hour+" �ð� " ;
											if(min>0)
												timelore[lore.length+2] =timelore[lore.length+2] + min+" �� " ;
											if(ClearTime>0)
												timelore[lore.length+2] =timelore[lore.length+2] + ClearTime+" �� " ;
											
											Stack2("��f��l" + QuestName, 403,0,7,Arrays.asList(timelore), loc, inv);
											loc++;
										}
									}
								}
							}
							else
							{
								lore[0] ="��f[�ְ� ����Ʈ]";
								Stack2("��f��l" + QuestName, 403,0,7,Arrays.asList(lore), loc, inv);
								loc++;
							}
						}
						break;
					case "M" :
						if(PlayerHas.toString().contains(QuestName) == false)
						{
							if(PlayerFinished.contains(QuestName) == true)
							{
								util.ETC ETC = new util.ETC();
								Object e[] = PlayerFinished.toArray();
								for(int counter=0; counter < e.length; counter++)
								{
									if(e[counter].toString().equalsIgnoreCase(QuestName))
									{
										Long ClearTime = (PlayerQuestList.getLong("Ended."+e[counter]+".ClearTime") + Long.parseLong("2678400000")) ;
										if(ClearTime < ETC.getNowUTC())
										{
											lore[0] ="��f[���� ����Ʈ]";
											Stack2("��f��l" + QuestName, 403,0,31,Arrays.asList(lore), loc, inv);
											loc++;
										}
										else
										{
											ClearTime = PlayerQuestList.getLong("Ended."+e[counter]+".ClearTime") + Long.parseLong("2678400000") - ETC.getNowUTC() ;
											ClearTime = ClearTime/1000;
											lore[0] ="��f[���� ����Ʈ]";
											String[] timelore = new String[lore.length+3];
											for(int counter2=0;counter2 < lore.length;counter2++)
												timelore[counter2] = lore[counter2];
											byte day = 0;
											byte hour = 0;
											byte min = 0;
											
											day = (byte) (ClearTime/86400);
											ClearTime = ClearTime-(86400*day);
											
											hour = (byte) (ClearTime/3600);
											ClearTime = ClearTime-(3600*hour);
											
											min = (byte)(ClearTime/60);
											ClearTime = ClearTime-(60*min);
											
											timelore[lore.length]="";
											timelore[lore.length+1] = "��c[����Ʈ ��� �ð�]";
											if(hour>0)
												timelore[lore.length+2] = "��c"+day+" �� " ;
											if(hour>0)
												timelore[lore.length+2] = timelore[lore.length+2] +""+hour+" �ð� " ;
											if(min>0)
												timelore[lore.length+2] =timelore[lore.length+2] + min+" �� " ;
											if(ClearTime>0)
												timelore[lore.length+2] =timelore[lore.length+2] + ClearTime+" �� " ;
											
											Stack2("��f��l" + QuestName, 403,0,31,Arrays.asList(timelore), loc, inv);
											loc++;
										}
									}
								}
							}
							else
							{
								lore[0] ="��f[���� ����Ʈ]";
								Stack2("��f��l" + QuestName, 403,0,31,Arrays.asList(lore), loc, inv);
								loc++;
							}
						}
						break;
					}
				}
			}
			else
			{
				NPCscript.removeKey("Quest."+a[count]);
				NPCscript.saveConfig();
			}
		}
		
		if(a.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		//Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+ u.getNPCuuid(player)), 53, inv);
		player.openInventory(inv);
	}
	
	public void NPCjobGUI(Player player,String NPCname)
	{
		UserDataObject u = new UserDataObject();
		String UniqueCode = "��0��0��7��0��5��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0NPC ���� ����");

		Stack2("��f��l���� ����", 397,3,1,Arrays.asList("��7NPC�� ������ ���۴ϴ�."), 1, inv);
		Stack2("��f��l��������", 145,0,1,Arrays.asList("��7����, ����, �� ���","��7�ݼ����� ���۵� ������ ��Ĩ�ϴ�."), 2, inv);
		Stack2("��f��l�ּ���", 116,0,1,Arrays.asList("��7�÷��̾�� ���� ������ �̴ϴ�."), 3, inv);
		Stack2("��f��l����", 373,8261,1,Arrays.asList("��7�÷��̾ ������ ġ���� �ݴϴ�."), 4, inv);
		Stack2("��f��l���� ����", 314,0,1,Arrays.asList("��7�÷��̾ ���� ���ǿ� ������ ���","��7�÷��̾ Ư�� �������� ���� �����ݴϴ�.","","��c�� ����� ���� �ý�����","��c������ ���丮�� ��츸 ��� �����մϴ�."), 5, inv);
		Stack2("��f��l���� �̵�����", 368,0,1,Arrays.asList("��7Ư�� ��ġ�� �ڷ���Ʈ �����ݴϴ�."), 6, inv);
		Stack2("��f��l���� ����", 417,0,1,Arrays.asList("��7�������� ���� �� �ݴϴ�."), 7, inv);
		Stack2("��f��l�� ������", 351,10,1,Arrays.asList("��7�����ۿ� ���� ���� �����ݴϴ�."), 10, inv);

		Stack2("��f��l���� �޴�", 323,0,1,Arrays.asList("��7���� �޴��� ���ư��ϴ�.","��0"+ NPCname), 18, inv);
		Stack2("��f��l������", 324,0,1,Arrays.asList("��e"+ChatColor.stripColor(NPCname)+"��7����","��7��ȭ�� �����մϴ�.","��0"+ u.getNPCuuid(player)), 26, inv);
		
		player.openInventory(inv);
	}
	
	public void WarpMainGUI(Player player, int page,String NPCname)
	{
		UserDataObject u = new UserDataObject();
		YamlLoader NPCConfig = new YamlLoader();
		NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
		String UniqueCode = "��0��0��7��0��6��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC ���� ���� ��� : " + (page+1));
		Object[] WarpList= NPCConfig.getConfigurationSection("Job.WarpList").getKeys(false).toArray();
		YamlLoader AreaConfig = new YamlLoader();
		AreaConfig.getConfig("Area/AreaList.yml");

		Object[] AreaList = null;
		boolean isExit = false;
		for(int c = 0; c < 5; c++)
		{
			AreaList = AreaConfig.getKeys().toArray();
			for(int count = 0; count < WarpList.length;count++)
			{
				isExit = false;
				for(int counter = 0; counter < AreaList.length; counter++)
				{
					if(AreaList[counter].equals(NPCConfig.getString("Job.WarpList."+count+".Area"))==true)
					{
						isExit = true;
						break;
					}
				}
				if(isExit == false)
				{
					short Acount =  (short) (WarpList.length-1);
					for(int counter = count;counter <Acount;counter++)
						NPCConfig.set("Job.WarpList."+counter, NPCConfig.get("Job.WarpList."+(counter+1)));
					NPCConfig.removeKey("Job.WarpList."+Acount);
				}
			}
			NPCConfig.saveConfig();
		}
		WarpList= NPCConfig.getConfigurationSection("Job.WarpList").getKeys(false).toArray();
		byte loc=0;
		for(int count = page*45; count < WarpList.length;count++)
		{
			if(count > WarpList.length || loc >= 45) break;

			if(player.isOp() == true)
			Stack2("��f��l" + NPCConfig.getString("Job.WarpList."+count+".DisplayName"), 368,0,1,Arrays.asList("",
					"��e���� ��� : ��f" +NPCConfig.getInt("Job.WarpList."+count+".Cost")+" "+MainServerOption.money
					,"","��e[�� Ŭ���� �ش� �������� �̵�]","��c[Shift + ��Ŭ���� ���� ����]"), loc, inv);
			else
				Stack2("��f��l" + NPCConfig.getString("Job.WarpList."+count+".DisplayName"), 368,0,1,Arrays.asList("",
						"��e���� ��� : ��f" +NPCConfig.getInt("Job.WarpList."+count+".Cost") +" "+MainServerOption.money
						,"","��e[�� Ŭ���� �ش� �������� �̵�]"), loc, inv);
			loc++;
		}
		
		if(WarpList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		if(player.isOp() == true)
			Stack2("��f��l�� ����", 381,0,1,Arrays.asList("��7���ο� ���� ������ �����մϴ�.","","��e[������ ������ ������ ��� �����մϴ�.]"), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}
	
	public void WarperGUI(Player player, short page, String NPCname)
	{
		YamlLoader AreaConfig = new YamlLoader();
		AreaConfig.getConfig("Area/AreaList.yml");

		String UniqueCode = "��0��0��7��0��7��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC ���� ��� : " + (page+1));

		Object[] AreaList= AreaConfig.getConfigurationSection("").getKeys(false).toArray();
		
		byte loc=0;
		for(int count = page*45; count < AreaList.length;count++)
		{
			String AreaName = AreaList[count].toString();
			
			if(count > AreaList.length || loc >= 45) break;
			String world = AreaConfig.getString(AreaName+".World");
			int MinXLoc = AreaConfig.getInt(AreaName+".X.Min");
			short MinYLoc = (short) AreaConfig.getInt(AreaName+".Y.Min");
			int MinZLoc = AreaConfig.getInt(AreaName+".Z.Min");
			int MaxXLoc = AreaConfig.getInt(AreaName+".X.Max");
			short MaxYLoc = (short) AreaConfig.getInt(AreaName+".Y.Max");
			int MaxZLoc = AreaConfig.getInt(AreaName+".Z.Max");
			Stack2("��f��l" + AreaName, 395,0,1,Arrays.asList(
					"��3���� : "+world,"��3X ���� : "+MinXLoc+" ~ " + MaxXLoc
					,"��3Y ���� : "+MinYLoc+" ~ " + MaxYLoc
					,"��3Z ���� : "+MinZLoc+" ~ " + MaxZLoc
					,"","��e[�� Ŭ���� ���� �߰�]"), loc, inv);
			
			loc++;
		}
		
		if(AreaList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}
	
	public void UpgraderGUI(Player player, short page, String NPCname)
	{
		UserDataObject u = new UserDataObject();
		YamlLoader NPCConfig = new YamlLoader();
		NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
		YamlLoader UpgradeRecipe = new YamlLoader();
		UpgradeRecipe.getConfig("Item/Upgrade.yml");

		String UniqueCode = "��0��0��7��0��8��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC ���� ���� : " + (page+1));

		Object[] UpgradeAbleList = NPCConfig.getConfigurationSection("Job.UpgradeRecipe").getKeys(false).toArray();
		long playerMoney = main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money();
		byte loc=0;
		for(int count = page*45; count < UpgradeAbleList.length;count++)
		{
			String RecipeName = UpgradeAbleList[count].toString();
			
			String Lore=null;
			if(UpgradeRecipe.getString(RecipeName+".Only").equals("null"))
				Lore = "��f[��� ���]%enter%%enter%";
			else
				Lore = UpgradeRecipe.getString(RecipeName+".Only")+"%enter%%enter%";

			if(UpgradeRecipe.getInt(RecipeName+".MaxDurability") > 0)
				Lore = Lore+"��3 �� �ִ� ������ : "+UpgradeRecipe.getInt(RecipeName+".MaxDurability")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MaxDurability") < 0)
				Lore = Lore+"��c �� �ִ� ������ : "+UpgradeRecipe.getInt(RecipeName+".MaxDurability")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".MinDamage") > 0)
				Lore = Lore+"��3 �� �ּ� "+MainServerOption.damage+" : "+UpgradeRecipe.getInt(RecipeName+".MinDamage")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MinDamage") < 0)
				Lore = Lore+"��c �� �ּ� "+MainServerOption.damage+" : "+UpgradeRecipe.getInt(RecipeName+".MinDamage")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".MaxDamage") > 0)
				Lore = Lore+"��3 �� �ִ� "+MainServerOption.damage+" : "+UpgradeRecipe.getInt(RecipeName+".MaxDamage")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MaxDamage") < 0)
				Lore = Lore+"��c �� �ִ� "+MainServerOption.damage+" : "+UpgradeRecipe.getInt(RecipeName+".MaxDamage")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".MinMaDamage") > 0)
				Lore = Lore+"��3 �� �ּ� "+MainServerOption.magicDamage+" : "+UpgradeRecipe.getInt(RecipeName+".MinMaDamage")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MinMaDamage") < 0)
				Lore = Lore+"��c �� �ּ� "+MainServerOption.magicDamage+" : "+UpgradeRecipe.getInt(RecipeName+".MinMaDamage")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".MaxMaDamage") > 0)
				Lore = Lore+"��3 �� �ִ� "+MainServerOption.magicDamage+" : "+UpgradeRecipe.getInt(RecipeName+".MaxMaDamage")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MaxMaDamage") < 0)
				Lore = Lore+"��c �� �ִ� "+MainServerOption.magicDamage+" : "+UpgradeRecipe.getInt(RecipeName+".MaxMaDamage")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".DEF") > 0)
				Lore = Lore+"��3 �� ��� : "+UpgradeRecipe.getInt(RecipeName+".DEF")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".DEF") < 0)
				Lore = Lore+"��c �� ��� : "+UpgradeRecipe.getInt(RecipeName+".DEF")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".Protect") > 0)
				Lore = Lore+"��3 �� ��ȣ : "+UpgradeRecipe.getInt(RecipeName+".Protect")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".Protect") < 0)
				Lore = Lore+"��c �� ��ȣ : "+UpgradeRecipe.getInt(RecipeName+".Protect")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".MaDEF") > 0)
				Lore = Lore+"��3 �� ���� ��� : "+UpgradeRecipe.getInt(RecipeName+".MaDEF")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MaDEF") < 0)
				Lore = Lore+"��c �� ���� ��� : "+UpgradeRecipe.getInt(RecipeName+".MaDEF")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".MaProtect") > 0)
				Lore = Lore+"��3 �� ���� ��ȣ : "+UpgradeRecipe.getInt(RecipeName+".MaProtect")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".MaProtect") < 0)
				Lore = Lore+"��c �� ���� ��ȣ : "+UpgradeRecipe.getInt(RecipeName+".MaProtect")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".Balance") > 0)
				Lore = Lore+"��3 �� �뷱�� : "+UpgradeRecipe.getInt(RecipeName+".Balance")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".Balance") < 0)
				Lore = Lore+"��c �� �뷱�� : "+UpgradeRecipe.getInt(RecipeName+".Balance")+"%enter%";
			if(UpgradeRecipe.getInt(RecipeName+".Critical") > 0)
				Lore = Lore+"��3 �� ũ��Ƽ�� : "+UpgradeRecipe.getInt(RecipeName+".Critical")+"%enter%";
			else if(UpgradeRecipe.getInt(RecipeName+".Critical") < 0)
				Lore = Lore+"��c �� ũ��Ƽ�� : "+UpgradeRecipe.getInt(RecipeName+".Critical")+"%enter%";
			
			Lore = Lore+"%enter%"+UpgradeRecipe.getString(RecipeName+".Lore")+"%enter%%enter%";

			Lore = Lore+"��e �� ���� Ƚ�� : ��f"+UpgradeRecipe.getInt(RecipeName+".UpgradeAbleLevel")+"��e ȸ° ���� ����%enter%";
			Lore = Lore+"��e �� �ʿ� ���õ� : ��f"+UpgradeRecipe.getInt(RecipeName+".DecreaseProficiency")+"%enter% ";

			Lore = Lore+"%enter%��c��l���� ��� : "+NPCConfig.getInt("Job.UpgradeRecipe."+RecipeName)+" "+MainServerOption.money+"%enter% " ;

			if(playerMoney < NPCConfig.getInt("Job.UpgradeRecipe."+RecipeName+".Cost"))
				Lore = Lore+"��c��l���� �ݾ� : " +playerMoney+" "+MainServerOption.money+"%enter%";
			else
				Lore = Lore+"��b��l���� �ݾ� : " +playerMoney+" "+MainServerOption.money+"%enter%";
				
			if(player.isOp() == true)
				Lore = Lore+"%enter%��c[Shift + �� Ŭ���� ������ ����]%enter% ";
			
			String[] scriptA = Lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			
			
			if(count > UpgradeAbleList.length || loc >= 45) break;
			Stack2("��f��l" + RecipeName, 395,0,1,Arrays.asList(scriptA), loc, inv);
			
			loc++;
		}
		
		if(UpgradeAbleList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		if(player.isOp() == true)
			Stack2("��f��l������ �߰�", 386,0,1,Arrays.asList("��7���� ���� ������ ���ο�","��7���� �����Ǹ� �˰� �մϴ�."), 49, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}
	
	public void SelectUpgradeRecipeGUI(Player player, short page, String NPCname)
	{
		YamlLoader RecipeList = new YamlLoader();
		RecipeList.getConfig("Item/Upgrade.yml");

		String UniqueCode = "��0��0��7��0��9��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC ������ �߰� : " + (page+1));

		Object[] a= RecipeList.getKeys().toArray();

		byte loc=0;
		for(int count = page*45; count < a.length;count++)
		{
			if(count > a.length || loc >= 45) break;
			String ItemName =a[count].toString();
			String Lore=null;
			if(RecipeList.getString(ItemName+".Only").equals("null"))
				Lore = "��f[��� ���]%enter%%enter%";
			else
				Lore = RecipeList.getString(ItemName+".Only")+"%enter%%enter%";

			if(RecipeList.getInt(ItemName+".MaxDurability") > 0)
				Lore = Lore+"��3 �� �ִ� ������ : "+RecipeList.getInt(ItemName+".MaxDurability")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MaxDurability") < 0)
				Lore = Lore+"��c �� �ִ� ������ : "+RecipeList.getInt(ItemName+".MaxDurability")+"%enter%";
			if(RecipeList.getInt(ItemName+".MinDamage") > 0)
				Lore = Lore+"��3 �� �ּ� "+MainServerOption.damage+" : "+RecipeList.getInt(ItemName+".MinDamage")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MinDamage") < 0)
				Lore = Lore+"��c �� �ּ� "+MainServerOption.damage+" : "+RecipeList.getInt(ItemName+".MinDamage")+"%enter%";
			if(RecipeList.getInt(ItemName+".MaxDamage") > 0)
				Lore = Lore+"��3 �� �ִ� "+MainServerOption.damage+" : "+RecipeList.getInt(ItemName+".MaxDamage")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MaxDamage") < 0)
				Lore = Lore+"��c �� �ִ� "+MainServerOption.damage+" : "+RecipeList.getInt(ItemName+".MaxDamage")+"%enter%";
			if(RecipeList.getInt(ItemName+".MinMaDamage") > 0)
				Lore = Lore+"��3 �� �ּ� "+MainServerOption.magicDamage+" : "+RecipeList.getInt(ItemName+".MinMaDamage")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MinMaDamage") < 0)
				Lore = Lore+"��c �� �ּ� "+MainServerOption.magicDamage+" : "+RecipeList.getInt(ItemName+".MinMaDamage")+"%enter%";
			if(RecipeList.getInt(ItemName+".MaxMaDamage") > 0)
				Lore = Lore+"��3 �� �ִ� "+MainServerOption.magicDamage+" : "+RecipeList.getInt(ItemName+".MaxMaDamage")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MaxMaDamage") < 0)
				Lore = Lore+"��c �� �ִ� "+MainServerOption.magicDamage+" : "+RecipeList.getInt(ItemName+".MaxMaDamage")+"%enter%";
			if(RecipeList.getInt(ItemName+".DEF") > 0)
				Lore = Lore+"��3 �� ��� : "+RecipeList.getInt(ItemName+".DEF")+"%enter%";
			else if(RecipeList.getInt(ItemName+".DEF") < 0)
				Lore = Lore+"��c �� ��� : "+RecipeList.getInt(ItemName+".DEF")+"%enter%";
			if(RecipeList.getInt(ItemName+".Protect") > 0)
				Lore = Lore+"��3 �� ��ȣ : "+RecipeList.getInt(ItemName+".Protect")+"%enter%";
			else if(RecipeList.getInt(ItemName+".Protect") < 0)
				Lore = Lore+"��c �� ��ȣ : "+RecipeList.getInt(ItemName+".Protect")+"%enter%";
			if(RecipeList.getInt(ItemName+".MaDEF") > 0)
				Lore = Lore+"��3 �� ���� ��� : "+RecipeList.getInt(ItemName+".MaDEF")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MaDEF") < 0)
				Lore = Lore+"��c �� ���� ��� : "+RecipeList.getInt(ItemName+".MaDEF")+"%enter%";
			if(RecipeList.getInt(ItemName+".MaProtect") > 0)
				Lore = Lore+"��3 �� ���� ��ȣ : "+RecipeList.getInt(ItemName+".MaProtect")+"%enter%";
			else if(RecipeList.getInt(ItemName+".MaProtect") < 0)
				Lore = Lore+"��c �� ���� ��ȣ : "+RecipeList.getInt(ItemName+".MaProtect")+"%enter%";
			if(RecipeList.getInt(ItemName+".Balance") > 0)
				Lore = Lore+"��3 �� �뷱�� : "+RecipeList.getInt(ItemName+".Balance")+"%enter%";
			else if(RecipeList.getInt(ItemName+".Balance") < 0)
				Lore = Lore+"��c �� �뷱�� : "+RecipeList.getInt(ItemName+".Balance")+"%enter%";
			if(RecipeList.getInt(ItemName+".Critical") > 0)
				Lore = Lore+"��3 �� ũ��Ƽ�� : "+RecipeList.getInt(ItemName+".Critical")+"%enter%";
			else if(RecipeList.getInt(ItemName+".Critical") < 0)
				Lore = Lore+"��c �� ũ��Ƽ�� : "+RecipeList.getInt(ItemName+".Critical")+"%enter%";
			
			Lore = Lore+"%enter%"+RecipeList.getString(ItemName+".Lore")+"%enter%%enter%";

			Lore = Lore+"��e �� ���� Ƚ�� : ��f"+RecipeList.getInt(ItemName+".UpgradeAbleLevel")+"��e ȸ° ���� ����%enter%";
			Lore = Lore+"��e �� �ʿ� ���õ� : ��f"+RecipeList.getInt(ItemName+".DecreaseProficiency")+"%enter% ";

			Lore = Lore+"%enter%��e[�� Ŭ���� ������ ���]%enter%";

			String[] scriptA = Lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			
			Stack("��f"+ItemName, 395, 0, 1,Arrays.asList(scriptA), loc, inv);
			loc++;
		}
		
		if(a.length-(page*44)>45)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}
	
	public void RuneEquipGUI(Player player, String NPCname)
	{
		UserDataObject u = new UserDataObject();
		String UniqueCode = "��1��0��7��0��a��r";
		Inventory inv = Bukkit.createInventory(null, 27, UniqueCode + "��0NPC �� ����");

		YamlLoader NPCscript = new YamlLoader();
		NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player)  +".yml");
		Stack2("��9", 160,7,1,null, 0, inv);
		Stack2("��9", 160,7,1,null, 1, inv);
		Stack2("��9", 160,7,1,null, 2, inv);
		Stack2("��9", 160,7,1,null, 9, inv);
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 10, inv);
		Stack2("��9", 160,7,1,null,11, inv);
		Stack2("��9", 160,7,1,null,18, inv);
		Stack2("��9", 160,7,1,null,19, inv);
		Stack2("��9", 160,7,1,null,20, inv);
		
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null, 3, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null, 4, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null, 5, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null, 12, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null,14, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null,21, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null,22, inv);
		Stack2("��9��l[���� �÷� ��������]", 160,11,1,null,23, inv);
		
		Stack2("��9", 160,7,1,null, 6, inv);
		Stack2("��9", 160,7,1,null, 7, inv);
		Stack2("��9", 160,7,1,null, 8, inv);
		Stack2("��9", 160,7,1,null, 15, inv);
		Stack2("��b��l[     �� ����     ]", 145,0,1,Arrays.asList("","��a�� ���� ������ : ��f" +NPCscript.getInt("Job.SuccessRate")+"��a%","��a�� ���� ���� : ��e"+NPCscript.getInt("Job.Deal")+MainServerOption.money), 16, inv);
		Stack2("��9", 160,7,1,null,17, inv);
		Stack2("��9", 160,7,1,null,24, inv);
		Stack2("��9", 160,7,1,null,25, inv);
		Stack2("��9", 160,7,1,Arrays.asList("��0"+NPCname),26, inv);
		player.openInventory(inv);
	}
	
	public void NPCTalkGUI(Player player, short page, String NPCname,String TalkType)
	{
		UserDataObject u = new UserDataObject();
		YamlLoader NPCConfig = new YamlLoader();
		NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");

		String UniqueCode = "��0��0��7��0��b��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC �Ϲ� ��� : " + (page+1));
		switch(TalkType)
		{
		case "NT"://NatureTalk
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC [�������� ��ȭ] ��� : " + (page+1));
			break;
		case "NN"://NearbyNews
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC [��ó�� �ҹ�] ��� : " + (page+1));
			break;
		case "AS"://AboutSkill
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC [��ų�� ���Ͽ�] ��� : " + (page+1));
			break;
		}
		Object[] TalkList = NPCConfig.getConfigurationSection("NatureTalk").getKeys(false).toArray();
		switch(TalkType)
		{
		case "NT"://NatureTalk
			TalkList = NPCConfig.getConfigurationSection("NatureTalk").getKeys(false).toArray();
			break;
		case "NN"://NearbyNews
			TalkList = NPCConfig.getConfigurationSection("NearByNEWS").getKeys(false).toArray();
			break;
		case "AS"://AboutSkill
			TalkList = NPCConfig.getConfigurationSection("AboutSkills").getKeys(false).toArray();
			break;
		}
		int loc=0;
		for(int count = page*45; count < TalkList.length;count++)
		{
			if(count > TalkList.length || loc >= 45) break;
			String Lore = "";
			switch(TalkType)
			{
			case "NT"://NatureTalk
				Lore = "%enter%��d[�ʿ� ȣ����]%enter%��f"+NPCConfig.getInt("NatureTalk."+(count+1)+".love")+" ~ "+NPCConfig.getInt("NatureTalk."+(count+1)+".loveMax")+"%enter%%enter%��e[��ϵ� ���]%enter%��f"+NPCConfig.getString("NatureTalk."+(count+1)+".Script");
				break;
			case "NN"://NearbyNews
				Lore = "%enter%��d[�ʿ� ȣ����]%enter%��f"+NPCConfig.getInt("NearByNEWS."+(count+1)+".love")+" ~ "+NPCConfig.getInt("NearByNEWS."+(count+1)+".loveMax")+"%enter%%enter%��e[��ϵ� ���]%enter%��f"+NPCConfig.getString("NearByNEWS."+(count+1)+".Script");
				break;
			case "AS"://AboutSkill
				Lore = "%enter%��d[�ʿ� ȣ����]%enter%��f"+NPCConfig.getInt("AboutSkills."+(count+1)+".love")+" ~ "+NPCConfig.getInt("AboutSkills."+(count+1)+".loveMax")+"%enter%%enter%��e[��ų ���� ���]%enter%��f"+NPCConfig.getString("AboutSkills."+(count+1)+".Script")+"%enter%%enter%��e[���� ���� ���]%enter%��f"+NPCConfig.getString("AboutSkills."+(count+1)+".AlreadyGetScript")+"%enter%%enter%��e[��� �� �ִ� ��ų]%enter%��f"+NPCConfig.getString("AboutSkills."+(count+1)+".giveSkill")+"%enter%��c[���� �ý����� ������ �� ��츸 ���� �մϴ�.]";
				break;
			}
			
			String[] scriptA = Lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];
			
			Stack2("��f��l"+ (count+1), 386,0,1,Arrays.asList(scriptA), loc, inv);
			
			loc=loc+1;
		}
		
		if(TalkList.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		Stack2("��f��l[��� �߰�]", 403,0,1,Arrays.asList("��7��縦 �߰� ��ŵ�ϴ�."), 49, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		switch(TalkType)
		{
		case "NT"://NatureTalk
			Stack2("��f��l[��ó�� �ҹ�]", 340,0,1,Arrays.asList("��7�ٸ� ������ ��縦 �����մϴ�."), 46, inv);
			Stack2("��f��l[��ų�� ���Ͽ�]", 340,0,1,Arrays.asList("��7�ٸ� ������ ��縦 �����մϴ�."), 52, inv);
			break;
		case "NN"://NearbyNews
			Stack2("��f��l[��ų�� ���Ͽ�]", 340,0,1,Arrays.asList("��7�ٸ� ������ ��縦 �����մϴ�."), 46, inv);
			Stack2("��f��l[�������� ��ȭ]", 340,0,1,Arrays.asList("��7�ٸ� ������ ��縦 �����մϴ�."), 52, inv);
			break;
		case "AS"://AboutSkill
			Stack2("��f��l[�������� ��ȭ]", 340,0,1,Arrays.asList("��7�ٸ� ������ ��縦 �����մϴ�."), 46, inv);
			Stack2("��f��l[��ó�� �ҹ�]", 340,0,1,Arrays.asList("��7�ٸ� ������ ��縦 �����մϴ�."), 52, inv);
			break;
		}
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+TalkType), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}
	
	public void TalkSettingGUI(Player player,String NPCname, String TalkType, short TalkNumber)
	{
		UserDataObject u = new UserDataObject();
		YamlLoader NPCConfig = new YamlLoader();
		NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");

		String UniqueCode = "��0��0��7��0��c��r";
		Inventory inv = Bukkit.createInventory(null, 36, UniqueCode + "��0NPC ��� ����");
		String Lore = "";
		switch(TalkType)
		{
		case "NT"://NatureTalk
			Lore = "%enter%��d[�ʿ� ȣ����]%enter%��f"+NPCConfig.getInt("NatureTalk."+TalkNumber+".love")+" ~ "+NPCConfig.getInt("NatureTalk."+TalkNumber+".loveMax")+"%enter%%enter%��e[��ϵ� ���]%enter%��f"+NPCConfig.getString("NatureTalk."+TalkNumber+".Script");
			break;
		case "NN"://NearbyNews
			Lore = "%enter%��d[�ʿ� ȣ����]%enter%��f"+NPCConfig.getInt("NearByNEWS."+TalkNumber+".love")+" ~ "+NPCConfig.getInt("NearByNEWS."+TalkNumber+".loveMax")+"%enter%%enter%��e[��ϵ� ���]%enter%��f"+NPCConfig.getString("NearByNEWS."+TalkNumber+".Script");
			break;
		case "AS"://AboutSkill
			Lore = "%enter%��d[�ʿ� ȣ����]%enter%��f"+NPCConfig.getInt("AboutSkills."+TalkNumber+".love")+" ~ "+NPCConfig.getInt("AboutSkills."+TalkNumber+".loveMax")+"%enter%%enter%��e[��ų ���� ���]%enter%��f"+NPCConfig.getString("AboutSkills."+TalkNumber+".Script")+"%enter%%enter%��e[���� ���� ���]%enter%��f"+NPCConfig.getString("AboutSkills."+TalkNumber+".AlreadyGetScript")+"%enter%%enter%��e[��� �� �ִ� ��ų]%enter%��f"+NPCConfig.getString("AboutSkills."+TalkNumber+".giveSkill")+"%enter%��c[���� �ý����� ������ �� ��츸 ���� �մϴ�.]";
			break;
		}
			
			String[] scriptA = Lore.split("%enter%");
			for(int counter = 0; counter < scriptA.length; counter++)
				scriptA[counter] =  " "+scriptA[counter];

			Stack2("��b[     ���     ]", 160,3,1,null, 0, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 1, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 2, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 9, inv);
			Stack2("��f��l"+ TalkNumber, 386,0,1,Arrays.asList(scriptA), 10, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 11, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 18, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 19, inv);
			Stack2("��b[     ���     ]", 160,3,1,null, 20, inv);
			

			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 3, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 4, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 5, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 6, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 7, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 8, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 12, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 17, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 21, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 22, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 23, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 24, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 25, inv);
			Stack2("��7[     �ɼ�     ]", 160,7,1,null, 26, inv);
			

			switch(TalkType)
			{
			case "NT"://NatureTalk
			case "NN"://NearbyNews
				Stack2("��d[     ȣ����     ]", 38,0,1,null, 13, inv);
				Stack2("��f[     �� ��     ]", 386,0,1,null, 14, inv);
				break;
			case "AS"://AboutSkill
				Stack2("��d[     ȣ����     ]", 38,0,1,null, 13, inv);
				Stack2("��f[     �� �� 1     ]", 386,0,1,null, 14, inv);
				Stack2("��3[     ��ų     ]", 403,0,1,null, 15, inv);
				Stack2("��f[     �� �� 2     ]", 386,0,1,null, 16, inv);
				break;
			}
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+TalkType), 27, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 35, inv);
		player.openInventory(inv);
	}
	
	public void AddAbleSkillsGUI(Player player, short page, String NPCname,int TalkNumber)
	{
		YamlLoader SkillList = new YamlLoader();
		SkillList.getConfig("Skill/JobList.yml");
		YamlLoader RealSkills = new YamlLoader();
		RealSkills.getConfig("Skill/SkillList.yml");

		String UniqueCode = "��0��0��7��0��d��r";
		Inventory inv = Bukkit.createInventory(null, 54, UniqueCode + "��0NPC ����ĥ ��ų ���� : " + (page+1));
		Object[] Skills = SkillList.getConfigurationSection("Mabinogi.Added").getKeys(false).toArray();

		byte loc=0;
		for(int count = page*45; count < Skills.length;count++)
		{
			if(count > Skills.length || loc >= 45) break;
			if(RealSkills.contains(Skills[count].toString())==true)
			{
				Stack2("��f��l"+ Skills[count], RealSkills.getInt(Skills[count].toString()+".ID"),RealSkills.getInt(Skills[count].toString()+".DATA"),RealSkills.getInt(Skills[count].toString()+".Amount"),Arrays.asList("","��a[�ִ� ��ũ]","��f"+RealSkills.getConfigurationSection(Skills[count].toString()+".SkillRank").getKeys(false).size()), loc, inv);
				loc++;
			}
		}
		
		if(Skills.length-(page*44)>45)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 50, inv);
		if(page!=0)
			Stack2("��f��l���� ������", 323,0,1,Arrays.asList("��7���� �������� �̵� �մϴ�."), 48, inv);

		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+TalkNumber), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}

	public void ItemBuy(Player player, ItemStack item, int value, String NPCname, boolean isItemBuy, int count)
	{
		Inventory inv = null;
		String itemName = null;
		String UniqueCode = "��0��0��7��0��e��r";
		if(item.hasItemMeta()&&item.getItemMeta().hasDisplayName())
			itemName = item.getItemMeta().getDisplayName();
		else
			itemName = new event.EventInteract().SetItemDefaultName((short)item.getTypeId(), item.getData().getData());
		if(isItemBuy)
		{
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0[NPC]��9��l ��ǰ ����");
			Stack2("��b     [����]     ", 160,11,1,Arrays.asList("��0"+value), 0, inv);
			Stack2("��b     [����]     ", 160,11,1,Arrays.asList("��0"+count), 1, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 2, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 3, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 4, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 5, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 6, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 7, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 8, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 9, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 18, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 17, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 26, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 27, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 36, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 35, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 36, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 37, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 38, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 39, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 40, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 41, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 42, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 43, inv);
			Stack2("��b     [����]     ", 160,11,1,null, 44, inv);
			Stack2("��b��l[��ǰ ����]", 54,0,1,Arrays.asList(itemName+"��f��l��"+item.getAmount()+"��f ��������","��f�� ��e"+ChatColor.BOLD+count +"��f �� �����մϴ�."
					,"","��f���� : ��e"+ value*count + " ��6"+ MainServerOption.money, "��f������ : ��f"+ChatColor.BOLD+MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()+" "+MainServerOption.money), 49, inv);
		}
		else
		{
			inv = Bukkit.createInventory(null, 54, UniqueCode + "��0[NPC]��c��l ��ǰ �Ǹ�");
			Stack2("��c     [�Ǹ�]     ", 160,14,1,Arrays.asList("��0"+value), 0, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,Arrays.asList("��0"+count), 1, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 2, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 3, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 4, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 5, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 6, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 7, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 8, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 9, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 18, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 17, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 26, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 27, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 36, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 35, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 36, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 37, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 38, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 39, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 40, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 41, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 42, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 43, inv);
			Stack2("��c     [�Ǹ�]     ", 160,14,1,null, 44, inv);
			Stack2("��c��l[��ǰ �Ǹ�]", 54,0,1,Arrays.asList(itemName+"��f��l��"+item.getAmount()+"��f ��������","��f�� ��e"+ChatColor.BOLD+count +"��f �� �Ǹ��մϴ�."
					,"","��f���� : ��e"+ value*count + " ��6"+ MainServerOption.money, "��f������ : ��f"+ChatColor.BOLD+MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money()+" "+MainServerOption.money), 49, inv);
		}

		Stack2("��c��l     [���� - 64]     ", 374,0,64,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 19, inv);
		Stack2("��c��l     [���� - 10]     ", 374,0,10,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 20, inv);
		Stack2("��c��l     [���� - 1]     ", 374,0,1,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 21, inv);

		Stack2("��c��l     [�ּ���]     ", 325,0,1,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 13, inv);
		ItemStackStack(item, 22, inv);
		Stack2("��b��l     [�ִ���]     ", 326,0,1,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 31, inv);
		Stack2("��b��l     [���� + 1]     ", 373,0,1,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 23, inv);
		Stack2("��b��l     [���� + 10]     ", 373,0,10,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 24, inv);
		Stack2("��b��l     [���� + 64]     ", 373,0,64,Arrays.asList("","��f���� �� ��e"+ChatColor.BOLD+count +"��f ��"), 25, inv);
		
		Stack2("��f��l���� ���", 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 45, inv);
		Stack2("��f��l�ݱ�", 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 53, inv);
		player.openInventory(inv);
	}
	
	public void ItemFix(Player player, String NPCname, int successRate, int value)
	{
		String UniqueCode = "��0��0��7��0��f��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0[NPC]��0��l ������ ��� ���� �ϼ���.");
		Stack2("��f��l���� ��ϡ�0��e"+ChatColor.RED, 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+successRate,"��0"+value), 0, inv);
		Stack2("��f��l�ݱ��0��e"+ChatColor.RED, 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 8, inv);

		Stack2("��0��e"+ChatColor.RED, 265,0,1,null, 1, inv);
		Stack2("��0��e"+ChatColor.RED, 265,0,1,null, 2, inv);
		Stack2("��0��e"+ChatColor.RED, 265,0,1,null, 3, inv);
		Stack2("��6��l[��� ����]��0��e"+ChatColor.RED, 145,0,1,Arrays.asList("��7�κ��丮 �� �������� ���� ���","��7������ Ȯ���� ���ݿ� ���� �� �ݴϴ�.","","��3���� ������ : ��f" +successRate+"��3 %","��a������ 10 �� ���� : ��e"+value+" "+ChatColor.GREEN+MainServerOption.money,"","��c[�Ϲ� ������ ���� ���н�]","��c - ���� ������ ��� ���� ��� �Ҹ�","��c[Ŀ���� ������ ���� ���н�]","��c - �ִ� ������ ����"), 4, inv);
		Stack2("��0��e"+ChatColor.RED, 265,0,1,null, 5, inv);
		Stack2("��0��e"+ChatColor.RED, 265,0,1,null, 6, inv);
		Stack2("��0��e"+ChatColor.RED, 265,0,1,null, 7, inv);
		player.openInventory(inv);
	}
	
	public void PresentSettingGUI(Player player, String NPCname)
	{
		String UniqueCode = "��0��0��7��1��0��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0[NPC] ���� ���� ������ ���");
		Stack2("��f��l���� ��ϡ�0��e"+ChatColor.RED, 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�."), 0, inv);
		Stack2("��f��l�ݱ��0��e"+ChatColor.RED, 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 8, inv);

		YamlLoader NPCConfig = new YamlLoader();
		NPCConfig.getConfig("NPC/NPCData/"+new UserDataObject().getNPCuuid(player)+".yml");
		
		Stack2("��f��Ÿ ������", 138,0,1,Arrays.asList("��7������ ������ ���� �ٸ�","��7�������� �־��� ����","��7ȣ���� ��·��� �����մϴ�.","","��aȣ���� : " + NPCConfig.getInt("Present.1.love")), 1, inv);

		for(int count = 2; count < 8; count++)
		{
			if(NPCConfig.getItemStack("Present."+count+".item") == null)
				Stack2("��c[�������� ���� ����]", 166,0,1,Arrays.asList("��e��l[Ŭ���� ���� ����]"), count, inv);
			else
			{
				ItemStack item = NPCConfig.getItemStack("Present."+count+".item");
				ItemMeta im = item.getItemMeta();
				if(im.hasLore() == false)
					im.setLore(Arrays.asList("","��aȣ���� : " + NPCConfig.getInt("Present."+count+".love")));
				else
				{
					List<String> lore = item.getItemMeta().getLore();
					lore.add(lore.size(), "");
					lore.add(lore.size(),"��aȣ���� : " + NPCConfig.getInt("Present."+count+".love"));
					im.setLore(lore);
				}
				item.setItemMeta(im);
				ItemStackStack(item, count, inv);
			}
		}
		player.openInventory(inv);
	}

	public void PresentGiveGUI(Player player, String NPCname, boolean isSettingMode, int number)
	{
		String UniqueCode = "��1��0��7��1��1��r";
		Inventory inv = Bukkit.createInventory(null, 9, UniqueCode + "��0[NPC] ���� �������� �÷� �ּ���");
		if(isSettingMode)
		{
			Stack2("��f��l���� ��ϡ�0��e"+ChatColor.RED, 389,0,1,Arrays.asList("��7�� ���������� �����մϴ�.","��0"+isSettingMode), 0, inv);

			YamlLoader NPCConfig = new YamlLoader();
			NPCConfig.getConfig("NPC/NPCData/"+new UserDataObject().getNPCuuid(player)+".yml");
			ItemStack item = NPCConfig.getItemStack("Present."+number+".item");
			if(item != null)
				ItemStackStack(item, 4, inv);
			Stack2("��f��l�ݱ��0��e"+ChatColor.RED, 324,0,1,Arrays.asList("��7â�� �ݽ��ϴ�.","��0"+NPCname), 8, inv);
		}
		else
		{
			Stack2("��f��l���� ��ϡ�0��e"+ChatColor.RED, 323,0,1,Arrays.asList("��7���� ȭ������ ���ư��ϴ�.","��0"+isSettingMode), 0, inv);
			Stack2("��f��l���� �ֱ��0��e"+ChatColor.RED, 54,0,1,Arrays.asList("��7�÷��� �������� �����մϴ�.","��0"+NPCname), 8, inv);
		}
		Stack2("��0��e"+ChatColor.RED, 160,5,1,Arrays.asList("��0"+number), 1, inv);
		Stack2("��0��e"+ChatColor.RED, 160,5,1,null, 2, inv);
		Stack2("��0��e"+ChatColor.RED, 160,5,1,null, 3, inv);
		Stack2("��0��e"+ChatColor.RED, 160,5,1,null, 5, inv);
		Stack2("��0��e"+ChatColor.RED, 160,5,1,null, 6, inv);
		Stack2("��0��e"+ChatColor.RED, 160,5,1,null, 7, inv);
		player.openInventory(inv);
	}

	
	
	
	public void QuestAddGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.8F);
			if(slot == 48)//���� ������
				QuestAddGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				QuestAddGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				YamlLoader NPCscript = new YamlLoader();
				UserDataObject u = new UserDataObject();
				NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");
				
				String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				Set<String> NPChasQuest = NPCscript.getConfigurationSection("Quest").getKeys(false);
				Object[] a = NPChasQuest.toArray();
				
				boolean isExit=false;
				for(int count = 0; count < a.length;count++)
				{
					if(NPCscript.getString("Quest."+a[count]).equalsIgnoreCase(QuestName) == true)
					{
						isExit = true;
						NPCscript.removeKey("Quest."+count);
					}
					if(isExit == true)
					{
						if(count < a.length-1)
						NPCscript.set("Quest."+count, NPCscript.getString("Quest."+(count+1)));
					}
				}
				if(isExit == true)
				{
					NPCscript.removeKey("Quest."+ (a.length-1));
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 0.8F);
					player.sendMessage("��c[SYSTEM] : ����Ʈ ���� �Ϸ�!");
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.8F);
					player.sendMessage("��a[SYSTEM] : ����Ʈ ��� �Ϸ�!");
					NPCscript.set("Quest."+a.length, QuestName);
				}
				NPCscript.saveConfig();
				QuestAddGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1));
			}
		}
	}
	
	public void QuestListGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if(slot == 48)//���� ������
				QuestListGUI(player,(short) (Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-2));
			else if(slot == 50)//���� ������
				QuestListGUI(player,(short) Integer.parseInt(event.getInventory().getTitle().split(" : ")[1]));
			else
			{
				String QuestName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				YamlLoader PlayerQuest = new YamlLoader();
				PlayerQuest.getConfig("Quest/PlayerData/"+ player.getUniqueId().toString() +".yml");
				YamlLoader QuestList = new YamlLoader();
				QuestList.getConfig("Quest/QuestList.yml");
				YamlLoader Config = new YamlLoader();
				Config.getConfig("config.yml");
				for(int counter = 0; counter < event.getCurrentItem().getItemMeta().getLore().size();counter ++)
				{
					if(event.getCurrentItem().getItemMeta().getLore().get(counter).contains("���") == true)
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
						player.sendMessage("��c[����Ʈ] : ������ ���̻� ����Ʈ�� ������ �� �����ϴ�!");
						return;
					}
				}
				if(QuestList.getInt(QuestName+".Server.Limit") == -1)
				{
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
					player.sendMessage("��c[����Ʈ] : ���̻� �� ����Ʈ�� ���� �� �� �����ϴ�!");
					return;
				}
				else
				{
					int NeedLevel = QuestList.getInt(QuestName+".Need.LV");
					int NeedLove = QuestList.getInt(QuestName+".Need.Love");
					int NeedSTR = QuestList.getInt(QuestName+".Need.STR");
					int NeedDEX = QuestList.getInt(QuestName+".Need.DEX");
					int NeedINT = QuestList.getInt(QuestName+".Need.INT");
					int NeedWILL = QuestList.getInt(QuestName+".Need.WILL");
					int NeedLUK = QuestList.getInt(QuestName+".Need.LUK");
					String PrevQuest = QuestList.getString(QuestName+".Need.PrevQuest");
					
					int PLV = 0;
					if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
						PLV = main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level();
					else
						PLV = main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_RealLevel();
					if(NeedLevel <= PLV)
					{
						if(NeedSTR <= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR()&&
						NeedDEX <= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX()&&
						NeedINT <= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT()&&
						NeedWILL <= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL()&&
						NeedLUK <= main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK())
						{
							UserDataObject u = new UserDataObject();
							YamlLoader PlayerNPC = new YamlLoader();
							PlayerNPC.getConfig("NPC/PlayerData/" + player.getUniqueId()+".yml");
							if(NeedLove <= PlayerNPC.getInt(u.getNPCuuid(player)+".love"))
							{
								if(PrevQuest.equalsIgnoreCase("null")||PlayerQuest.contains("Ended."+PrevQuest) == true)
								{
									if(QuestList.getInt(QuestName+".Server.Limit") != 0)
									{
										if(QuestList.getInt(QuestName+".Server.Limit") == 1|| QuestList.getInt(QuestName+".Server.Limit") < -1)
										{
											QuestList.set(QuestName+".Server.Limit", -1);
										}
										else
										{
											QuestList.set(QuestName+".Server.Limit", QuestList.getInt(QuestName+".Server.Limit")-1);
										}
										QuestList.saveConfig();
									}
									player.closeInventory();
									SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
									String message = Config.getString("Quest.AcceptMessage").replace("%QuestName%", QuestName);
									player.sendMessage(message);

									PlayerQuest.set("Started."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())+".Flow", 0);
									PlayerQuest.set("Started."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())+".Type", QuestList.getString(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())+".FlowChart."+0+".Type"));
									
									PlayerQuest.saveConfig();
									
									new quest.QuestGui().QuestRouter(player, ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
								}
								else
								{
									SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
									player.sendMessage("��c[����Ʈ] : ���� ����Ʈ�� �������� �ʾ� ����Ʈ�� ������ �� �����ϴ�!");
									return;
								}
							}
							else
							{
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
								player.sendMessage("��c[����Ʈ] : ȣ������ �����Ͽ� ����Ʈ�� ������ �� �����ϴ�!");
								return;
							}
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
							player.sendMessage("��c[����Ʈ] : ������ �����Ͽ� ����Ʈ�� ������ �� �����ϴ�!");
							return;
						}
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 0.8F);
						player.sendMessage("��c[����Ʈ] : ���� ������ ������ �ƴմϴ�!");
						return;
					}
				}
			}
		}
	}
	
	public void MainGUIClick(InventoryClickEvent event, String NPCname)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(event.getClickedInventory().getType()!=InventoryType.PLAYER)
		{
			if(slot == 26)//������
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
			else if(slot == 8 && player.isOp())//GUI �� Ȱ��ȭ
			{
				SoundEffect.SP(player, Sound.ENTITY_VILLAGER_HURT, 0.8F, 1.0F);
				YamlLoader DNPC = new YamlLoader();
				DNPC.getConfig("NPC/DistrictNPC.yml");

				UserDataObject u = new UserDataObject();
				DNPC.set(u.getNPCuuid(player).toString(), true);
				DNPC.saveConfig();
				player.closeInventory();
				player.sendMessage("��e[NPC] : �ش� NPC�� GUIȭ���� ������� �ʰ� �Ǿ����ϴ�!");
				player.sendMessage("��6/gui����f ��ɾ� �Է� ��, NPC Ŭ����, �ٽ� Ȱ��ȭ �˴ϴ�.");
			}
			else if(slot == 4)//���� ������
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				String Case = (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				if(Case.equals("��������") || Case.equals("�� ������") || Case.equals("�ּ���") ||
						Case.equals("����") || Case.equals("���� ����") || Case.equals("���� �̵�����") ||
						Case.equals("���� ����"))
				{
					if(event.getClick().isLeftClick() == true)
					{
						YamlLoader NPCscript = new YamlLoader();
						UserDataObject u = new UserDataObject();
					  	if(NPCscript.isExit("NPC/NPCData/"+ u.getNPCuuid(player) +".yml") == false)
					  	{
					  		npc.NpcConfig NPCC = new npc.NpcConfig();
					  		NPCC.NPCNPCconfig(u.getNPCuuid(player));
					  	}
						NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");
						util.UtilNumber n = new util.UtilNumber();

						SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
						if(Case.equals("�� ������"))
							RuneEquipGUI(player, NPCname);
						else if(Case.equals("���� ����"))
							UpgraderGUI(player, (short) 0,NPCname);
						else if(Case.equals("���� �̵�����"))
							WarpMainGUI(player, 0,NPCname);
						else if(Case.equals("���� ����"))
							UpgraderGUI(player, (short) 0,NPCname);
						else if(Case.equals("��������"))
							ItemFix(player, NPCname, Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(3).split(" ")[3])), Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(4).split(" ")[5])));
						else if(Case.equals("�ּ���"))
						{
							util.ETC ETC = new util.ETC();
							YamlLoader Config = new YamlLoader();
							Config.getConfig("config.yml");

							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_BuffCoolTime()+(Config.getInt("NPC.Shaman.BuffCoolTime")*1000) > ETC.getNowUTC())
							{
								SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[SYSTEM] : ��f"+((main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getETC_BuffCoolTime()+(Config.getInt("NPC.Shaman.BuffCoolTime")*1000) -ETC.getNowUTC())/1000)+"��c�� �� �̿� �����մϴ�!");
								return;
							}
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() < NPCscript.getInt("Job.Deal"))
							{
								SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[SYSTEM] : ��ä ����� �����մϴ�!");
								return;
							}
							else
							{
								main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).setETC_BuffCoolTime(ETC.getNowUTC());
								if(n.RandomNum(0, 100) <= NPCscript.getInt("Job.GoodRate"))
								{
									switch(n.RandomNum(1, 8))
									{
									case 1:
										SoundEffect.SP(player,Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
										player.sendMessage("��f��l[��������] �غ�� ���� �߰����� ���� �ܴ��ϰ� ������...");
										PottionBuff.givePotionEffect(player,PotionEffectType.DAMAGE_RESISTANCE, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 2:
										SoundEffect.SP(player,Sound.BLOCK_GRAVEL_HIT, 1.5F, 1.0F);
										player.sendMessage("��f��l[�������] ������ ���� ���� �ϰ� ū ���� ������ ��������...");
										PottionBuff.givePotionEffect(player,PotionEffectType.FAST_DIGGING, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 3:
										SoundEffect.SP(player,Sound.BLOCK_FIRE_EXTINGUISH, 1.5F, 1.0F);
										player.sendMessage("��f��l[٥�κ��] ���� ��վ� ���ٸ� ���̻� ���� �η��� ��������...");
										PottionBuff.givePotionEffect(player,PotionEffectType.FIRE_RESISTANCE, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 4:
										SoundEffect.SP(player,Sound.ENTITY_PLAYER_LEVELUP, 1.5F, 0.8F);
										player.sendMessage("��f��l[����̪�] ���� ������ ��� ǳ��ο�� �� ���� ����� �ƴ��Ѱ�...");
										PottionBuff.givePotionEffect(player,PotionEffectType.HEAL, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										PottionBuff.givePotionEffect(player,PotionEffectType.SATURATION, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										PottionBuff.givePotionEffect(player,PotionEffectType.REGENERATION, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										PottionBuff.givePotionEffect(player,PotionEffectType.HEALTH_BOOST, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 5:
										SoundEffect.SP(player,Sound.ENTITY_MINECART_INSIDE, 1.0F, 1.0F);
										player.sendMessage("��f��l[�������] �� ���� �̸� ���� ���ƴٴϱ� �����ϴ�, ������ ���� ��ϸ�...");
										PottionBuff.givePotionEffect(player,PotionEffectType.SPEED, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										PottionBuff.givePotionEffect(player,PotionEffectType.JUMP, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 6:
										SoundEffect.SP(player,Sound.BLOCK_ANVIL_LAND, 1.0F, 1.8F);
										player.sendMessage("��f��l[Ӥ������] ��鸮�� �ʴ� �ų��� ���� ���� �����, ö�ٰ��� "+MainServerOption.statWILL+"�� ���� ���� �ٽ��� �߸���...");
										PottionBuff.givePotionEffect(player,PotionEffectType.INCREASE_DAMAGE, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 7:
										SoundEffect.SP(player,Sound.ENTITY_ENDERDRAGON_GROWL, 1.0F, 1.0F);
										player.sendMessage("��f��l[ˬ������] �� ���� ���� �޺��̿�, �� �߱� ��� ���� ���̴�...");
										PottionBuff.givePotionEffect(player,PotionEffectType.NIGHT_VISION, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									case 8:
										SoundEffect.SP(player,Sound.BLOCK_WATER_AMBIENT, 1.5F, 1.0F);
										player.sendMessage("��f��l[٥����] �ſ︸ġ ���� ������ ���� ���⸦ �����ϸ���...");
										PottionBuff.givePotionEffect(player,PotionEffectType.WATER_BREATHING, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")));
										break;
									}
								}
								else
								{
									switch(n.RandomNum(1, 8))
									{
									case 1:
										SoundEffect.SP(player,Sound.ENTITY_BLAZE_AMBIENT, 1.0F, 1.0F);
										player.sendMessage("��c��l[��������] �Ÿ��� ���� ���� ������ �ѳ� ���̷� �ٶ󺸸���...");
										PottionBuff.givePotionEffect(player,PotionEffectType.WEAKNESS, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 2:
										SoundEffect.SP(player,Sound.AMBIENT_CAVE, 1.0F, 1.0F);
										player.sendMessage("��c��l[��������] ��ġ �յ� ������ �ʰŴ�...");
										PottionBuff.givePotionEffect(player,PotionEffectType.BLINDNESS, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 3:
										SoundEffect.SP(player,Sound.ENTITY_ENDERDRAGON_GROWL, 0.8F,0.5F);
										player.sendMessage("��c��l[�������] ���� ���� �� ���� �����̶�...");
										PottionBuff.givePotionEffect(player,PotionEffectType.CONFUSION, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 4:
										SoundEffect.SP(player,Sound.ENTITY_ZOMBIE_DEATH, 0.8F,0.5F);
										player.sendMessage("��c��l[ӯ������] �� ����� ��ġ �ƱͿ� ���� �ӻ��ϴ�...");
										PottionBuff.givePotionEffect(player,PotionEffectType.HUNGER, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 5:
										SoundEffect.SP(player,Sound.ENTITY_ZOMBIE_HORSE_DEATH, 0.8F,0.5F);
										player.sendMessage("��c��l[�������] ��Ʈ���� �Ⱑ �� ��ü�� ���� ���� ������ ���·ӱⰡ ¦�� ������...");
										PottionBuff.givePotionEffect(player,PotionEffectType.POISON, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 6:
										SoundEffect.SP(player,Sound.ENTITY_ITEM_BREAK, 0.8F,0.5F);
										player.sendMessage("��c��l[����گ��] �������� ����� �Ͽ���, �ð��� ��ٷ� ���� ��������...");
										PottionBuff.givePotionEffect(player,PotionEffectType.SLOW_DIGGING, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 7:
										SoundEffect.SP(player,Sound.ENTITY_SPIDER_AMBIENT, 0.8F,0.5F);
										player.sendMessage("��c��l[ʹ����ͪ] ���� ������ ������ �پ�� ������...");
										PottionBuff.givePotionEffect(player,PotionEffectType.SLOW, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									case 8:
										SoundEffect.SP(player,Sound.ENTITY_WITHER_HURT, 0.8F,0.5F);
										player.sendMessage("��c��l[������] ���� ���� ���� ��ư��� ���밡 ���±���...");
										PottionBuff.givePotionEffect(player,PotionEffectType.WITHER, NPCscript.getInt("Job.BuffTime"), n.RandomNum(1,  NPCscript.getInt("Job.BuffMaxStrog")+1));
										break;
									}
								}
						  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * NPCscript.getInt("Job.Deal"), 0, false);
							}
							NPCscript.set("Job.Type","Shaman");
							NPCscript.set("Job.GoodRate",50);
							NPCscript.set("Job.BuffMaxStrog",2);
							NPCscript.set("Job.BuffTime",60);
							NPCscript.set("Job.Deal",500);
						}
						else if(Case.equals("����"))
						{
							Damageable getouter = (Damageable)player;
							int a = (int)getouter.getHealth();
							if(player.getHealthScale()== a&&(player.hasPotionEffect(PotionEffectType.BLINDNESS)||
									player.hasPotionEffect(PotionEffectType.CONFUSION)||player.hasPotionEffect(PotionEffectType.HARM)||
									player.hasPotionEffect(PotionEffectType.HUNGER)||player.hasPotionEffect(PotionEffectType.POISON)||
									player.hasPotionEffect(PotionEffectType.SLOW)||player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)||
									player.hasPotionEffect(PotionEffectType.WEAKNESS)||player.hasPotionEffect(PotionEffectType.WITHER))==false)
							{
								SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��3[SYSTEM] : ����� ġ����� �ʿ䰡 �����ϴ�!");
								return;
							}
							if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() < NPCscript.getInt("Job.Deal"))
							{
								SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[SYSTEM] : ġ�� ����� �����մϴ�!");
							}
							else
							{
								SoundEffect.SP(player,Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.5F);
								Damageable p = player;
								p.setHealth(p.getMaxHealth());
								player.removePotionEffect(PotionEffectType.BLINDNESS);
								player.removePotionEffect(PotionEffectType.CONFUSION);
								player.removePotionEffect(PotionEffectType.HARM);
								player.removePotionEffect(PotionEffectType.HUNGER);
								player.removePotionEffect(PotionEffectType.POISON);
								player.removePotionEffect(PotionEffectType.SLOW);
								player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
								player.removePotionEffect(PotionEffectType.WEAKNESS);
								player.removePotionEffect(PotionEffectType.WITHER);
						  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * NPCscript.getInt("Job.Deal"), 0, false);
								player.sendMessage("��3[SYSTEM] : ����� ������ ġ��Ǿ����ϴ�!");
							}
						}
						else if(Case.equals("���� ����"))
						{
							YamlLoader JobList = new YamlLoader();
							JobList.getConfig("Skill/JobList.yml");
							YamlLoader PlayerJob = new YamlLoader();
							Object[] Job = JobList.getConfigurationSection("MapleStory").getKeys(false).toArray();
							for(int count = 0; count < Job.length; count++)
							{
								Object[] q = JobList.getConfigurationSection("MapleStory."+Job[count].toString()).getKeys(false).toArray();
								for(int counter=0;counter<q.length;counter++)
								{
									if(q[counter].toString().equals(NPCscript.getString("Job.Job")))
									{
										PlayerJob.getConfig("Skill/PlayerData/"+player.getUniqueId().toString()+".yml");
										int NeedLV = JobList.getInt("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".NeedLV");
										int NeedSTR = JobList.getInt("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".NeedSTR");
										int NeedDEX = JobList.getInt("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".NeedDEX");
										int NeedINT = JobList.getInt("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".NeedINT");
										int NeedWILL = JobList.getInt("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".NeedWILL");
										int NeedLUK = JobList.getInt("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".NeedLUK");
										String PrevJob = JobList.getString("MapleStory."+Job[count].toString()+"."+q[counter].toString()+".PrevJob");
										
										if((main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Level()>=NeedLV)&&
										(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_STR()>=NeedSTR)&&
										(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_DEX()>=NeedDEX)&&
										(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_INT()>=NeedINT)&&
										(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_WILL()>=NeedWILL)&&
										(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_LUK()>=NeedLUK))
										{
											if(!PrevJob.equals("null"))
											{
												if(!PlayerJob.getString("Job.Type").equals(PrevJob))
												{
													player.sendMessage("��c[����] : ����� �������δ� ���� �� �� ���� ����Դϴ�.");
													SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
													return;	
												}
											}
											if(!NPCscript.getString("Job.Job").equals(PlayerJob.getString("Job.Type")))
											{
												//�÷��̾� ������
												PlayerJob.set("Job.Root", Job[count].toString());
												PlayerJob.set("Job.Type", NPCscript.getString("Job.Job"));
												PlayerJob.createSection("MapleStory."+NPCscript.getString("Job.Job")+".Skill");
												PlayerJob.saveConfig();
												MainServerOption.PlayerList.get(player.getUniqueId().toString()).setPlayerRootJob(Job[count].toString());
												job.JobMain J = new job.JobMain();
												J.FixPlayerJobList(player);
												player.closeInventory();
												Bukkit.broadcastMessage("��a��l[��e��l"+player.getName()+"��a��l�Բ��� ��e��l"+NPCscript.getString("Job.Job")+"��a��l �±޿� ���� �ϼ̽��ϴ�!]");
											}
											else
											{
												player.sendMessage("��c[����] : ���� �������� ���� �� �� �����ϴ�!");
												SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
											}
										}
										else
										{
											player.sendMessage("��c[����] : ����� ������ ���� ��ǿ� ���� �ʽ��ϴ�.");
											SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
										}
									}
								}
							}
						}
					}
					else if(event.getClick().isRightClick()&& player.isOp())
					{
						SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
						NPCjobGUI(player,NPCname);
					}
				}
				else//���� ����
					NPCjobGUI(player,NPCname);
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(slot == 7 && player.isOp())//���� ����
				{
					UserDataObject u = new UserDataObject();
					YamlLoader NPCConfig = new YamlLoader();
					NPCConfig.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");
					if(event.isShiftClick()&&event.isRightClick())
					{
						NPCConfig.set("Sale.Enable", false);
						NPCConfig.saveConfig();
						SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 0.8F, 1.0F);
						MainGUI(player, NPCname, player.isOp());
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
						u.setType(player, "NPC");
						u.setString(player, (byte)2,NPCname);
						u.setString(player, (byte)3,u.getNPCuuid(player));
						u.setString(player, (byte)4,"SaleSetting1");
						player.sendMessage("��3[NPC] : ������ ���� �� �ּ� ȣ������ �Է� �� �ּ���! (-1000 ~ 1000 ���� ��)");
						player.closeInventory();
					}
				}
				else if(slot==10)//��ȭ�� �Ѵ�
					TalkGUI(player,NPCname,null,(char)-1);
				else if(slot==12)//�ŷ��� �Ѵ�
					ShopGUI(player,NPCname,(short) 0,true,false);
				else if(slot == 14)//����Ʈ
					QuestListGUI(player, (short) 0);
				else if(slot == 16)//�����ϱ�
					PresentGiveGUI(player, NPCname, false, -1);
				else if(player.isOp())
				{
					if(slot == 19)//��ȭ ����
						NPCTalkGUI(player, (short) 0, NPCname,"NT");
					else if(slot == 21)//�ŷ� ����
						ShopGUI(player,NPCname,(short) 0,true,true);
					else if(slot == 23)//����Ʈ ����
						QuestAddGUI(player, (short) 0);
					else if(slot == 25)//���� ����
						PresentSettingGUI(player, NPCname);
				}
			}	
		}
		return;
	}

	public void TalkGUIClick(InventoryClickEvent event, String NPCname)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		if(slot > 0 && slot < 8)
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			TalkGUI(player,NPCname, new npc.NpcMain().getScript(player,(char)-1),(char)slot);
		}
		else
		{
			if(slot == 0)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				MainGUI(player,NPCname,player.isOp());
			}
			else
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
		}
	}
	
	public void ShopGUIClick(InventoryClickEvent event, String NPCname)
	{
		
		Player player = (Player) event.getWhoClicked();
		if(event.getClickedInventory().getType() == InventoryType.PLAYER)
			return;
		switch(event.getSlot())
		{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 17:
			case 18:
			case 26:
			case 27:
			case 35:
			case 36:
			case 37:
			case 38:
			case 39:
			case 40:
			case 41:
			case 42:
			case 43:
			case 44:
				return;
			case 45:
				MainGUI(player,NPCname,player.isOp());
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				break;
			case 48:
			{
				int showingPage = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(1).split("������ : ")[1]));
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(event.getInventory().getItem(0).getItemMeta().hasLore() == false)
				{
					if(event.getInventory().getItem(0).getData().getData() ==(byte)14)
						ShopGUI(player, NPCname, (short) (showingPage-1) , false,false);
					else
						ShopGUI(player, NPCname, (short) (showingPage-1), true,false);
				}
				else
				{
					if(event.getInventory().getItem(0).getData().getData() ==(byte)14)
						ShopGUI(player, NPCname, (short) (showingPage-1) , false,true);
					else
						ShopGUI(player, NPCname, (short) (showingPage-1), true,true);
				}
				break;
			}
			case 49:
				SoundEffect.SP(player, Sound.BLOCK_CHEST_OPEN, 0.8F, 1.0F);
				if(event.getInventory().getItem(0).getItemMeta().hasLore() == false)
				{
					if(event.getCurrentItem().getData().getData() ==(byte)14)
						ShopGUI(player, NPCname, (short) 0, false,false);
					else
						ShopGUI(player, NPCname, (short) 0, true,false);
				}
				else
				{
					if(event.getCurrentItem().getData().getData() ==(byte)14)
						ShopGUI(player, NPCname, (short) 0, false,true);
					else
						ShopGUI(player, NPCname, (short) 0, true,true);
				}
				break;
			case 50:
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				int showingPage2 = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(1).split("������ : ")[1]));
				if(event.getInventory().getItem(0).getItemMeta().hasLore() == false)
				{
					if(event.getInventory().getItem(0).getData().getData() ==(byte)14)
						ShopGUI(player, NPCname, (short) (showingPage2-1), false,false);
					else
						ShopGUI(player, NPCname, (short) (showingPage2-1), true,false);
				}
				else
				{
					if(event.getInventory().getItem(0).getData().getData() ==(byte)14)
						ShopGUI(player, NPCname, (short) (showingPage2-1), false,true);
					else
						ShopGUI(player, NPCname, (short) (showingPage2-1), true,true);
				}
				break;
			case 53:
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
				break;
			default:
			{
				ItemStack item = event.getCurrentItem();
				boolean isBuy = true;
				if(event.getInventory().getItem(0).getData().getData() == (byte)14)
					isBuy=false;
				if(event.getInventory().getItem(0).getItemMeta().hasLore())
				{
					if(event.getClick().isRightClick() == true)
					{
						UserDataObject u = new UserDataObject();
						String Type=null;
						if(isBuy == true)
							Type = "Sell";
						else
							Type = "Buy";
						
						byte num = Byte.parseByte(ChatColor.stripColor(item.getItemMeta().getLore().get(item.getItemMeta().getLore().size()-1)));
						YamlLoader NPCscript = new YamlLoader();
						NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");

						Set<String> a = NPCscript.getConfigurationSection("Shop."+Type).getKeys(false);
						
						for(int count = 0; count < a.size(); count++)
						{
							if(count == num)
							{
								for(int counter =count; counter < a.size()-1; counter++)
								{
									NPCscript.set("Shop."+Type+"."+counter + ".item", NPCscript.getItemStack("Shop."+Type+"."+(counter+1) + ".item"));
									NPCscript.set("Shop."+Type+"."+counter + ".price", NPCscript.getLong("Shop."+Type+"."+(counter+1) + ".price"));
								}
								NPCscript.removeKey("Shop."+Type+"."+(a.size()-1) + ".item");
								NPCscript.removeKey("Shop."+Type+"."+(a.size()-1) + ".price");
								NPCscript.removeKey("Shop."+Type+"."+(a.size()-1));
								NPCscript.saveConfig();
							}
						}
					}
					else
					{
						if(item.getItemMeta().getLore().size() < 4)
						{
							ItemMeta Icon_Meta = item.getItemMeta();
							Icon_Meta.setLore(null);
							item.setItemMeta(Icon_Meta);
							player.getInventory().addItem(item);
						}
						else
						{
							ItemMeta Icon_Meta = item.getItemMeta();
							String[] l = new String[item.getItemMeta().getLore().size()-3];
							for(int count =0;count <l.length;count++)
								l[count] = (item.getItemMeta().getLore().get(count));
							Icon_Meta.setLore(Arrays.asList(l));
							item.setItemMeta(Icon_Meta);
							player.getInventory().addItem(item);
						}
					}
					SoundEffect.SP(player, org.bukkit.Sound.BLOCK_LAVA_POP, 2.0F, 1.7F);
					int showingPage3 = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getLore().get(0)));
					if(isBuy == true)
						ShopGUI(player, NPCname, (short) showingPage3, true,true);
					else
						ShopGUI(player, NPCname, (short) showingPage3, false,true);
				}
				else if(item.hasItemMeta())
				{
					int value = Integer.parseInt(item.getItemMeta().getLore().get(item.getItemMeta().getLore().size()-2).split(" ")[2]);
					if(item.getItemMeta().getLore().size() < 4)
					{
						ItemMeta Icon_Meta = item.getItemMeta();
						Icon_Meta.setLore(null);
						item.setItemMeta(Icon_Meta);
					}
					else
					{
						ItemMeta Icon_Meta = item.getItemMeta();
						String[] l = new String[item.getItemMeta().getLore().size()-3];
						for(int count =0;count <l.length;count++)
							l[count] = (item.getItemMeta().getLore().get(count));
						Icon_Meta.setLore(Arrays.asList(l));
						item.setItemMeta(Icon_Meta);
					}
					SoundEffect.SP(player, Sound.BLOCK_IRON_TRAPDOOR_OPEN, 0.8F, 0.5F);
					if(event.getInventory().getItem(0).getData().getData() == (byte)14)
						ItemBuy(player, item, value, NPCname, isBuy, 0);
					else
						ItemBuy(player, item, value, NPCname, isBuy, 0);
				}
			}
		}
	}
	
	public void NPCjobGUIClick(InventoryClickEvent event, String NPCname)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 26)//�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.8F);
			UserDataObject u = new UserDataObject();
			YamlLoader NPCscript = new YamlLoader();
			NPCscript.getConfig("NPC/NPCData/"+ u.getNPCuuid(player) +".yml");
			if(slot == 18)
				MainGUI(player,NPCname,player.isOp());
			else
			{
				NPCscript.removeKey("Job");
				if(slot == 1)//���� ����
				{
					NPCscript.set("Job.Type","null");
					NPCscript.saveConfig();
					MainGUI(player,NPCname,player.isOp());
				}
				else if(slot == 6)//���� �̵�����
				{
					NPCscript.set("Job.Type","Warper");
					NPCscript.createSection("Job.WarpList");
					NPCscript.saveConfig();
					WarpMainGUI(player, 0,NPCname);
				}
				else if(slot == 7)//���� ����
				{
					NPCscript.set("Job.Type","Upgrader");
					NPCscript.createSection("Job.UpgradeRecipe");
					NPCscript.saveConfig();
					MainGUI(player, NPCname, player.isOp());
				}
				else
				{
					player.closeInventory();
					u.setType(player, "NPC");
					u.setString(player, (byte)2,NPCname);
					u.setString(player, (byte)3,u.getNPCuuid(player));
					if(slot == 2)//��������
					{
						NPCscript.set("Job.Type","BlackSmith");
						NPCscript.set("Job.FixRate",50);
						NPCscript.set("Job.10PointFixDeal",500);
						NPCscript.saveConfig();
						u.setString(player, (byte)4,"FixRate");
						player.sendMessage("��3[NPC] : �� NPC�� ���� �������� �Է� �� �ּ���! (1~100 ���� ��)");
					}
					else if(slot == 3)//�ּ���
					{
						NPCscript.set("Job.Type","Shaman");
						NPCscript.set("Job.GoodRate",50);
						NPCscript.set("Job.BuffMaxStrog",2);
						NPCscript.set("Job.BuffTime",60);
						NPCscript.set("Job.Deal",500);
						NPCscript.saveConfig();
						u.setString(player, (byte)4,"GoodRate");
						player.sendMessage("��3[NPC] : �� NPC�� ���� �������� �Է� �� �ּ���! (1~100 ���� ��)");
					}
					else if(slot == 4)//����
					{
						NPCscript.set("Job.Type","Healer");
						NPCscript.set("Job.Deal",500);
						NPCscript.saveConfig();
						u.setString(player, (byte)4,"Deal");
						player.sendMessage("��3[NPC] : �� NPC�� ġ�� ����� �Է� �� �ּ���!");
					}
					else if(slot == 5)//���� ����
					{
						YamlLoader Config = new YamlLoader();
						Config.getConfig("config.yml");
						if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == false)
						{
							YamlLoader JobList = new YamlLoader();
							JobList.getConfig("Skill/JobList.yml");
							Object[] Job = JobList.getConfigurationSection("MapleStory").getKeys(false).toArray();
							if(Job.length == 1)
							{
								u.clearAll(player);
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[NPC] : ���� ������ ������ �����ϴ�! ��e/���ǹڽ���c ��ɾ ����Ͽ� �������� ����ʽÿ�!");
								return;
							}
							u.setString(player, (byte)4,"NPCJL");

							player.sendMessage("��d[NPC] : �� NPC�� � �������� ���� ���� �ִ� �����ΰ���?");
							for(int count = 0; count < Job.length; count++)
							{
								Object[] a = JobList.getConfigurationSection("MapleStory."+Job[count].toString()).getKeys(false).toArray();
								for(int counter=0;counter<a.length;counter++)
								{
									if(a[counter].toString().equalsIgnoreCase(Config.getString("Server.DefaultJob"))==false)
										player.sendMessage("��d "+Job[count].toString()+" �� ��e"+ a[counter].toString());
								}
							}
						}
						else
						{
							u.clearAll(player);
							SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[NPC] : ���� ����� ����Ͻ÷����e /���ǹڽ���c ���� ���� �ý����� '������ ���丮'�� ������ �ֽñ� �ٶ��ϴ�.");
						}
					}
					else if(slot == 10)//�� ������
					{
						NPCscript.set("Job.Type","Rune");
						NPCscript.set("Job.SuccessRate",50);
						NPCscript.set("Job.Deal",5000);
						NPCscript.saveConfig();
						u.setString(player, (byte)4,"SuccessRate");
						player.sendMessage("��3[NPC] : �� NPC�� �� ���� �������� �Է� �� �ּ���! (1~100 ���� ��)");
					}
				}
			}
		}
	}
	
	public void WarpMainGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		

		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			if(slot == 45)//���� �������
				MainGUI(player, NPCname, player.isOp());
			else if(slot == 48)//���� ������
				WarpMainGUI(player,page-1,NPCname);
			else if(slot == 50)//���� ������
				WarpMainGUI(player,page+1,NPCname);
			else if(slot == 49)//�� ����
				WarperGUI(player, (short) 0, NPCname);
			else
			{
				if(event.isLeftClick()==true&&event.isShiftClick()==false)
				{
					util.ETC ETC = new util.ETC();
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_AttackTime()+15000 >= ETC.getSec())
					{
						player.sendMessage("��c[�̵� �Ұ�] : ��e"+((main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_AttackTime()+15000 - ETC.getSec())/1000)+"��c �� �Ŀ� �̵� �����մϴ�!");
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						return;
					}
					UserDataObject u = new UserDataObject();
					YamlLoader NPCConfig = new YamlLoader();
					NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
					if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() >= NPCConfig.getInt("Job.WarpList."+((page*45)+event.getSlot())+".Cost"))
					{
				  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * NPCConfig.getInt("Job.WarpList."+((page*45)+event.getSlot())+".Cost"), 0, false);
						String AreaName = NPCConfig.getString("Job.WarpList."+((page*45)+event.getSlot())+".Area");
						YamlLoader AreaConfig = new YamlLoader();
						AreaConfig.getConfig("Area/AreaList.yml");
						SoundEffect.SL(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
						player.teleport(new Location(Bukkit.getWorld(AreaConfig.getString(AreaName+".World")), AreaConfig.getInt(AreaName+".SpawnLocation.X"), AreaConfig.getInt(AreaName+".SpawnLocation.Y"), AreaConfig.getInt(AreaName+".SpawnLocation.Z")));
						SoundEffect.SL(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[����] : �ڷ���Ʈ ����� �����մϴ�!");
					}
				}
				else if(event.isRightClick()==true&&event.isShiftClick()==true&&player.isOp()==true)
				{
					UserDataObject u = new UserDataObject();
					YamlLoader NPCConfig = new YamlLoader();
					NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
					int number = ((page*45)+event.getSlot());
					int Acount =  NPCConfig.getConfigurationSection("Job.WarpList").getKeys(false).toArray().length-1;

					for(int counter = number;counter <Acount;counter++)
						NPCConfig.set("Job.WarpList."+counter, NPCConfig.get("Job.WarpList."+(counter+1)));
					NPCConfig.removeKey("Job.WarpList."+Acount);
					NPCConfig.saveConfig();
					WarpMainGUI(player, page, NPCname);
				}
			}
		}
	}

	public void WarperGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();

		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			int page =  Integer.parseInt(event.getInventory().getTitle().split(" : ")[1])-1;
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			
			if(slot == 45)//���� �������
				WarpMainGUI(player, 0, NPCname);
			else if(slot == 48)//���� ������
				WarperGUI(player, (short) (page-1), NPCname);
			else if(slot == 50)//���� ������
				WarperGUI(player, (short) (page+1), NPCname);
			else
			{
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
				int number = NPCConfig.getConfigurationSection("Job.WarpList").getKeys(false).size();
				NPCConfig.set("Job.WarpList."+number+".Area",ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				NPCConfig.set("Job.WarpList."+number+".DisplayName","��������");
				NPCConfig.set("Job.WarpList."+number+".Cost",1000);
				NPCConfig.saveConfig();
				u.setType(player, "NPC");
				u.setString(player, (byte)4,"WDN");
				u.setString(player, (byte)3,u.getNPCuuid(player));
				u.setString(player, (byte)2,NPCname);
				u.setInt(player, (byte)4, number);
				player.sendMessage("��3[NPC] : �ش� ���� ������ �̸��� ������ �ּ���!");
				player.closeInventory();
			}
		}
	}

	public void UpgraderGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		
		if(slot == 53)
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.8F);
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));

			if(slot == 45)//���� �������
				MainGUI(player, NPCname,player.isOp());
			else if(slot == 48)//���� ������
				UpgraderGUI(player, (short) (page-1), NPCname);
			else if(slot == 49)//�� ������
			{
				if(player.isOp())
					SelectUpgradeRecipeGUI(player, (short) 0,NPCname);
			}
			else if(slot == 50)//���� ������
				UpgraderGUI(player, (short) (page+1), NPCname);
			else
			{
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");

				if(event.isLeftClick() == true && event.isShiftClick()==false)
				{
					if(player.getInventory().getItemInMainHand() != null)
					{
						if(player.getInventory().getItemInMainHand().hasItemMeta() == true)
						{
							if(player.getInventory().getItemInMainHand().getItemMeta().hasLore()==true)
							{
								List<String> Lore = event.getCurrentItem().getItemMeta().getLore();
								int Cost = 0;
								int NeedProficiency = 100;
								int UpgradeLevel = 0;
								for(int counter = 0; counter < Lore.size();counter++)
								{
									if(Lore.get(counter).contains("���") == true)
									{
										if(Lore.get(counter).contains(" : ") == true)
										{
											String RawString = ChatColor.stripColor(Lore.get(counter)).split(" : ")[1];
											Cost = Integer.parseInt(RawString.split(" ")[0]);
										}
									}
									else if(Lore.get(counter).contains("���õ�") == true)
									{
										if(Lore.get(counter).contains(" : ") == true)
											NeedProficiency = Integer.parseInt(ChatColor.stripColor(Lore.get(counter)).split(" : ")[1]);
									}
									else if(Lore.get(counter).contains("Ƚ��") == true)
									{
										if(Lore.get(counter).contains(" : ") == true)
										{
											String RawString = ChatColor.stripColor(Lore.get(counter)).split(" : ")[1];
											UpgradeLevel = Integer.parseInt(RawString.split(" ")[0]);
										}
									}
								}
								float playerProficiency=0.0F;
								int playerNowUpgradeLevel = -1;
								int playerMaxUpgradeLevel = -1;
								String PlayerWeaponType = "null";

								Lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
								for(int counter = 0; counter < Lore.size();counter++)
								{
									if(Lore.get(counter).length()!=0)
									{
										if(Lore.get(counter).contains("���õ�") == true)
										{
											if(Lore.get(counter).contains(" : ") == true)
											{
												String RawString = ChatColor.stripColor(Lore.get(counter)).split(" : ")[1];
												playerProficiency = Float.parseFloat(RawString.split("%")[0]);
											}
										}
										else if(Lore.get(counter).contains("����") == true)
										{
											if(Lore.get(counter).contains(" : ") == true)
											{
												playerNowUpgradeLevel = Integer.parseInt(ChatColor.stripColor(Lore.get(counter)).split(" : ")[1].split(" / ")[0]);
												playerMaxUpgradeLevel =  Integer.parseInt(ChatColor.stripColor(Lore.get(counter)).split(" : ")[1].split(" / ")[1]);
											}
										}
										else if(ChatColor.stripColor(Lore.get(counter)).charAt(0)=='['&&Lore.get(counter).contains("]") == true)
										{
											PlayerWeaponType = "";
											if(Lore.get(counter).contains("[����") == true)
												PlayerWeaponType="[���� ����]";
											else if(Lore.get(counter).contains("[�Ѽ�") == true)
												PlayerWeaponType="[�Ѽ� ��]";
											else if(Lore.get(counter).contains("[���") == true)
												PlayerWeaponType="[��� ��]";
											else if(Lore.get(counter).contains("[����]") == true)
												PlayerWeaponType="[����]";
											else if(Lore.get(counter).contains("[��]") == true)
												PlayerWeaponType="[��]";
											else if(Lore.get(counter).contains("[���Ÿ�") == true)
												PlayerWeaponType="[���Ÿ� ����]";
											else if(Lore.get(counter).contains("[Ȱ]") == true)
												PlayerWeaponType="[Ȱ]";
											else if(Lore.get(counter).contains("[����]") == true)
												PlayerWeaponType="[����]";
											else if(Lore.get(counter).contains("[����") == true)
												PlayerWeaponType="[���� ����]";
											else if(Lore.get(counter).contains("[����]") == true)
												PlayerWeaponType="[����]";
											else if(Lore.get(counter).contains("[������]") == true)
												PlayerWeaponType="[������]";
											else if(Lore.get(counter).contains("[��]") == true)
												PlayerWeaponType="[��]";
											else if(Lore.get(counter).contains("[��Ÿ]") == true)
												PlayerWeaponType="[��Ÿ]";
											else
											{
												String LoreLine = ChatColor.stripColor(Lore.get(counter));
												for(int count = 0; count < LoreLine.length(); count++)
												{
													if(LoreLine.charAt(count)!=']')
														PlayerWeaponType = PlayerWeaponType+LoreLine.charAt(count);
													else
														break;
												}
												PlayerWeaponType = PlayerWeaponType+"]";
											}
										}	
									}
								}
						  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * NPCConfig.getInt("Job.WarpList."+((page*45)+event.getSlot())+".Cost"), 0, false);
								if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() >=  Cost)
								{
									String RecipeName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
									YamlLoader UpgradeRecipe = new YamlLoader();
									UpgradeRecipe.getConfig("Item/Upgrade.yml");
									String Type = ChatColor.stripColor(UpgradeRecipe.getString(RecipeName+".Only"));
									if(Type.equals(PlayerWeaponType)||
											((Type.equals("[���� ����]")&&PlayerWeaponType.equals("[�Ѽ� ��]"))||(Type.equals("[���� ����]")&&PlayerWeaponType.equals("[��� ��]"))||
											(Type.equals("[���� ����]")&&PlayerWeaponType.equals("[����]"))||(Type.equals("[���Ÿ� ����]")&&PlayerWeaponType.equals("[Ȱ]"))||
											(Type.equals("[���Ÿ� ����]")&&PlayerWeaponType.equals("[����]"))||(Type.equals("[���� ����]")&&PlayerWeaponType.equals("[����]"))||
											(Type.equals("[���� ����]")&&PlayerWeaponType.equals("[������]"))))
									{
										if(playerMaxUpgradeLevel!=-1&&playerNowUpgradeLevel!=-1&&UpgradeLevel==playerNowUpgradeLevel)
										{
											if(!(playerMaxUpgradeLevel==playerNowUpgradeLevel))
											{
												if(playerProficiency >= NeedProficiency)
												{
											  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * Cost, 0, false);
													ItemStack item = player.getInventory().getItemInMainHand();
													if(UpgradeRecipe.getInt(RecipeName+".MaxDurability")!=0)
														item = Calculator(item, "Durability", UpgradeRecipe.getInt(RecipeName+".MaxDurability"));
													if(UpgradeRecipe.getInt(RecipeName+".MinDamage")!=0)
														item = Calculator(item, "MinDamage", UpgradeRecipe.getInt(RecipeName+".MinDamage"));
													if(UpgradeRecipe.getInt(RecipeName+".MaxDamage")!=0)
														item = Calculator(item, "MaxDamage", UpgradeRecipe.getInt(RecipeName+".MaxDamage"));
													if(UpgradeRecipe.getInt(RecipeName+".MinMaDamage")!=0)
														item = Calculator(item, "MinMaDamage", UpgradeRecipe.getInt(RecipeName+".MinMaDamage"));
													if(UpgradeRecipe.getInt(RecipeName+".MaxMaDamage")!=0)
														item = Calculator(item, "MaxMaDamage", UpgradeRecipe.getInt(RecipeName+".MaxMaDamage"));
													if(UpgradeRecipe.getInt(RecipeName+".DEF")!=0)
														item = Calculator(item, "DEF", UpgradeRecipe.getInt(RecipeName+".DEF"));
													if(UpgradeRecipe.getInt(RecipeName+".Protect")!=0)
														item = Calculator(item, "Protect", UpgradeRecipe.getInt(RecipeName+".Protect"));
													if(UpgradeRecipe.getInt(RecipeName+".MaDEF")!=0)
														item = Calculator(item, "MaDEF", UpgradeRecipe.getInt(RecipeName+".MaDEF"));
													if(UpgradeRecipe.getInt(RecipeName+".MaProtect")!=0)
														item = Calculator(item, "MaProtect", UpgradeRecipe.getInt(RecipeName+".MaProtect"));
													if(UpgradeRecipe.getInt(RecipeName+".Critical")!=0)
														item = Calculator(item, "Critical", UpgradeRecipe.getInt(RecipeName+".Critical"));
													if(UpgradeRecipe.getInt(RecipeName+".Balance")!=0)
														item = Calculator(item, "Balance", UpgradeRecipe.getInt(RecipeName+".Balance"));
													if(UpgradeRecipe.getInt(RecipeName+".DecreaseProficiency")!=0)
														item = Calculator(item, "Proficiency", UpgradeRecipe.getInt(RecipeName+".DecreaseProficiency"));
													item = Calculator(item, "UpgradeLevelIncrease", 1);
													//���� ����
													
													player.getInventory().setItemInMainHand(item);
													player.closeInventory();
													SoundEffect.SP(player, Sound.BLOCK_ANVIL_USE, 1.0F, 0.8F);
													player.sendMessage("��3[����] : ������ �Ϸ�Ǿ����ϴ�!");
												}
												else
												{
													SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
													player.sendMessage("��c[����] : ���õ��� �����մϴ�!");
												}
											}
											else
											{
												SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
												player.sendMessage("��c[����] : ���̻� ������ �� �����ϴ�!");
											}
										}
										else
										{
											SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
											player.sendMessage("��c[����] : ���� ������ ���� �ʽ��ϴ�!");
										}
									}
									else
									{
										SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
										player.sendMessage("��c[����] : ���� ������ ���� Ÿ���� �ƴմϴ�!");
									}
								}
								else
								{
									SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
									player.sendMessage("��c[����] : ���� ����� �����մϴ�!");
								}
							}
							else
							{
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								player.sendMessage("��c[����] : ���� �տ� ��� �ִ� ��������  ������ �Ұ��� �մϴ�!");
							}
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							player.sendMessage("��c[����] : ���� �տ� ��� �ִ� ��������  ������ �Ұ��� �մϴ�!");
						}
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[����] : �������� �տ� �����ϰ� �־�� �մϴ�!");
					}
				}
				else if(event.isRightClick()==true && event.isShiftClick()==true&&player.isOp()==true)
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.2F, 1.0F);
					NPCConfig.removeKey("Job.UpgradeRecipe."+ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
					NPCConfig.saveConfig();
					UpgraderGUI(player, page, NPCname);
				}
			}
		}
	}

	public void SelectUpgradeRecipeGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.8F);
			if(slot == 45)//���� ���
				UpgraderGUI(player, (short) 0, NPCname);
			else if(slot == 48)//���� ������
				SelectUpgradeRecipeGUI(player, (short) (page-1),NPCname);
			else if(slot == 50)//���� ������
				SelectUpgradeRecipeGUI(player, (short) (page+1),NPCname);
			else
			{
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
				String RecipeName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				NPCConfig.set("Job.UpgradeRecipe."+RecipeName, 2147483647);
				player.sendMessage("[NPC] : ������ �������� ���� ����� �Է� �� �ּ���!");
				player.sendMessage("��a(��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
				u.setType(player, "NPC");
				u.setString(player, (byte)4,"NUC");
				u.setString(player, (byte)6,RecipeName);
				u.setString(player, (byte)8,NPCname);
				player.closeInventory();
			}
		}
	}
	
	public ItemStack Calculator(ItemStack item, String WeaponOption, int PlusOption)
	{
		String Option = "null";
		switch(WeaponOption)
		{
		case "Durability":
			Option = "������";
			break;
		case "MinDamage":
			Option = "�Ҵ�";
			break;
		case "MaxDamage":
			Option = "�ƴ�";
			break;
		case "MinMaDamage":
			Option = "���Ҵ�";
			break;
		case "MaxMaDamage":
			Option = "���ƴ�";
			break;
		case "DEF":
			Option = "���";
			break;
		case "Protect":
			Option = "��ȣ";
			break;
		case "MaDEF":
			Option = "���� ���";
			break;
		case "MaProtect":
			Option = "���� ��ȣ";
			break;
		case "Critical":
			Option = "ũ��Ƽ��";
			break;
		case "Balance":
			Option = "�뷱��";
			break;
		case "Proficiency":
			Option = "���õ�";
			break;
		case "UpgradeLevelIncrease":
			Option = "����";
			break;
		case "NowDura":
			Option = "���系����";
			break;
		case "HP":
			Option = "�����";
			break;
		case "MP":
			Option = "����";
			break;
		case "STR":
			Option = ""+MainServerOption.statSTR+"";
			break;
		case "DEX":
			Option = ""+MainServerOption.statDEX+"";
			break;
		case "INT":
			Option = ""+MainServerOption.statINT+"";
			break;
		case "WILL":
			Option = ""+MainServerOption.statWILL+"";
			break;
		case "LUK":
			Option = ""+MainServerOption.statLUK+"";
			break;
		}
		ItemMeta IM = item.getItemMeta();
		List<String> Lore = item.getItemMeta().getLore();
		for(int count = 0; count < Lore.size(); count++)
		{
			String SliceOfLore = Lore.get(count);
			if(SliceOfLore.contains(Option) == true||(SliceOfLore.contains(MainServerOption.damage)&&!(SliceOfLore.contains("����"))&&(Option.equals("�Ҵ�")))||
					(SliceOfLore.contains(MainServerOption.damage)&&!(SliceOfLore.contains("����"))&&(Option.equals("�ƴ�")))||(SliceOfLore.contains(MainServerOption.magicDamage)&&(Option.equals("���Ҵ�")))||
					(SliceOfLore.contains(MainServerOption.magicDamage)&&(Option.equals("���ƴ�")))
					||(Option.equals("���系����")&&SliceOfLore.contains("������")))
			{
				String WillBeLore=" ";
				if(SliceOfLore.contains(" : ") == true)
				{
					switch(Option)
					{
					case "���系����":
						if(SliceOfLore.contains("������")&&!SliceOfLore.contains(MainServerOption.damage))
						{
							int low = Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[0]));
							int max = Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[1]));
							if((low+PlusOption)  <= max)
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+(low+ PlusOption) + " / "+ max;
							else
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+max + " / "+ max;
						}
						break;
					case "������":
						if(SliceOfLore.contains("������")&&!SliceOfLore.contains(MainServerOption.damage))
						{
							if(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[0])) <= (Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[1])) + PlusOption))
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[0])) + " / "+ (Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[1])) + PlusOption);
							else if((Integer.parseInt(SliceOfLore.split(" : ")[1].split(" / ")[1]) + PlusOption) != 0)
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[1])) + PlusOption) + " / "+ (Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[1])) + PlusOption);
							else
								WillBeLore = SliceOfLore.split(" : ")[0] + "��f : 0 / 0";
						}
						break;
					case "�Ҵ�":
						if(SliceOfLore.contains(MainServerOption.damage))
						{
							if(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0])) + PlusOption > 0)
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0]))+ PlusOption)+ " ~ "+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1]));
							else
								WillBeLore = SliceOfLore.split(" : ")[0] +"��f : 0 ~ "+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1]));
						}
						break;
					case "���Ҵ�":
						if(SliceOfLore.contains(MainServerOption.magicDamage))
						{
							if(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0])) + PlusOption > 0)
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0]))+ PlusOption)+ " ~ "+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1]));
							else
								WillBeLore = SliceOfLore.split(" : ")[0] +"��f : 0 ~ "+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1]));
						}
						break;
					case "�ƴ�":
						if(SliceOfLore.contains(MainServerOption.damage))
						{
							if(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1])) + PlusOption > 0)
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0])) + " ~ "+ (Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1])) + PlusOption);
							else
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0])) + " ~ 0";
						}
						break;
					case "���ƴ�":
						if(SliceOfLore.contains(MainServerOption.magicDamage))
						{
							if(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1])) + PlusOption > 0)
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0])) + " ~ "+ (Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[1])) + PlusOption);
							else
								WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" ~ ")[0])) + " ~ 0";
						}
						break;
					case "���õ�":
						DecimalFormat format = new DecimalFormat(".##");
						String str = "0";
						if((Float.parseFloat(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split("%")[0]))-PlusOption) > 0)
							str = format.format((Float.parseFloat(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split("%")[0]))-PlusOption));
						else
							str = "0.0";
						WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+str +"%��f";
						break;
					case "����":
						WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[0]))+ 1) + " / "+ (Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1].split(" / ")[1])));
						break;
					default:
						if(!SliceOfLore.contains("������"))
							WillBeLore = SliceOfLore.split(" : ")[0] + " : ��f"+(Integer.parseInt(ChatColor.stripColor(SliceOfLore.split(" : ")[1])) + PlusOption);
						break;
					}
					Lore.set(count, WillBeLore);
					IM.setLore(Lore);
					item.setItemMeta(IM);
					return item;
				}
			}
		}
		switch(Option)
		{
		case "�Ҵ�":
			Lore.add(2, "��f "+MainServerOption.damage+" : " + PlusOption + " ~ 0");
			break;
		case "���Ҵ�":
			Lore.add(3, "��f "+MainServerOption.magicDamage+" : " + PlusOption + " ~ 0");
			break;
		case "�ƴ�":
			Lore.add(2, "��f "+MainServerOption.damage+" : 0 ~ "+PlusOption);
			break;
		case "���ƴ�":
			Lore.add(3, "��f "+MainServerOption.magicDamage+" : 0 ~ "+PlusOption);
			break;
		default:
			Lore.add(4, " ��f"+ Option + " : " + PlusOption);
			break;
		}
		IM.setLore(Lore);
		item.setItemMeta(IM);
		return item;
	}
	
	public void RuneEquipGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		

		if(!event.getClickedInventory().getTitle().equals("container.inventory"))
		{
			if(slot != 13)
				event.setCancelled(true);
			if(slot == 10)//���� �������
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 0.8F);
				String NPCname = ChatColor.stripColor(event.getInventory().getItem(26).getItemMeta().getLore().get(0));
				MainGUI(player, NPCname, player.isOp());
			}
			else if(slot == 16)//�� ����
			{
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
				int SuccessRate = NPCConfig.getInt("Job.SuccessRate");
				boolean Success = false;
				if(event.getInventory().getItem(13)!=null)
				{
					if(event.getInventory().getItem(13).hasItemMeta()==true)
					{
						if(event.getInventory().getItem(13).getItemMeta().hasLore()==true)
						{
							if(event.getInventory().getItem(13).getItemMeta().getLore().toString().contains("[��]"))
							{
								if(player.getInventory().getItemInMainHand()!=null)
								{
									if(player.getInventory().getItemInMainHand().hasItemMeta()==true)
									{
										if(player.getInventory().getItemInMainHand().getItemMeta().hasLore()==true)
										{
											int Pay = NPCConfig.getInt("Job.Deal");
											if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() >= Pay)
											{
												ItemStack item = player.getInventory().getItemInMainHand();
												int MinDamage = 0;
												int MaxDamage = 0;
												int MinMaDamage = 0;
												int MaxMaDamage = 0;
												int DEF = 0;
												int Protect = 0;
												int MaDEF = 0;
												int MaProtect = 0;
												int Balance = 0;
												int Critical = 0;
												int NowDura = 0;
												int MaxDura = 0;
												int HP = 0;
												int MP = 0;
												int STR = 0;
												int DEX = 0;
												int INT = 0;
												int WILL = 0;
												int LUK = 0;
												for(int count = 0; count < event.getInventory().getItem(13).getItemMeta().getLore().size();count++)
												{
													if(event.getInventory().getItem(13).getItemMeta().getLore().get(count).contains(" : "))
													{
														String Rune = event.getInventory().getItem(13).getItemMeta().getLore().get(count);
														if(Rune.contains("���ݷ�"))
														{
															if(Rune.contains("����"))
															{
																if(Rune.contains("�ּ�"))
																	MinMaDamage = Integer.parseInt(Rune.split(" : ")[1]);
																else
																	MaxMaDamage = Integer.parseInt(Rune.split(" : ")[1]);
															}
															else
															{
																if(Rune.contains("�ּ�"))
																	MinDamage = Integer.parseInt(Rune.split(" : ")[1]);
																else
																	MaxDamage = Integer.parseInt(Rune.split(" : ")[1]);
															}
														}
														else if(Rune.contains("���"))
														{
															if(Rune.contains("����"))
																MaDEF = Integer.parseInt(Rune.split(" : ")[1]);
															else
																DEF = Integer.parseInt(Rune.split(" : ")[1]);
														}
														else if(Rune.contains("��ȣ"))
														{
															if(Rune.contains("����"))
																MaProtect = Integer.parseInt(Rune.split(" : ")[1]);
															else
																Protect = Integer.parseInt(Rune.split(" : ")[1]);
														}
														else if(Rune.contains("������"))
														{
															if(Rune.contains("����"))
																NowDura = Integer.parseInt(Rune.split(" : ")[1]);
															else
																MaxDura = Integer.parseInt(Rune.split(" : ")[1]);
														}
														else if(Rune.contains("�뷱��"))
															Balance = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains("ũ��Ƽ��"))
															Critical = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains("�����"))
															HP = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains("����"))
															MP = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains(""+MainServerOption.statSTR+""))
															STR = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains(""+MainServerOption.statDEX+""))
															DEX = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains(""+MainServerOption.statINT+""))
															INT = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains(""+MainServerOption.statWILL+""))
															WILL = Integer.parseInt(Rune.split(" : ")[1]);
														else if(Rune.contains(""+MainServerOption.statLUK+""))
															LUK = Integer.parseInt(Rune.split(" : ")[1]);
													}
												}
												
												for(int count = 0; count < item.getItemMeta().getLore().size();count++)
												{
													if(item.getItemMeta().getLore().get(count).contains("��") == true)
													{
														if(item.getItemMeta().getLore().get(count).contains(" : ") == true)
														{
															if(item.getItemMeta().getLore().get(count).contains("��") == true)
															{
																String Lore = item.getItemMeta().getLore().get(count).split(" : ")[1];
																int EmptyCircle = 0;
																String Circle = "";
																for(int k = 0; k < Lore.split(" ").length;k++)
																{
																	if(Lore.split(" ")[k].contains("��")==true)
																	{
																		EmptyCircle = k;
																		break;
																	}
																}
																UtilNumber n = new UtilNumber();
																if(n.RandomNum(1, 100)<= SuccessRate)
																{
																	for(int k = 0; k < Lore.split(" ").length;k++)
																	{
																		if(k != EmptyCircle)
																			Circle = Circle+Lore.split(" ")[k]+" ";
																		else
																			Circle = Circle+"��9�� ";
																	}
																	Lore =  item.getItemMeta().getLore().get(count).split(" : ")[0] + " : " + Circle;
																	player.sendMessage("��9[�� ����] : �����ۿ� ���� �����Ͽ����ϴ�!");
																	SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 0.5F);
																	Success = true;
																}
																else
																{
																	for(int k = 0; k < Lore.split(" ").length;k++)
																	{
																		if(k != EmptyCircle)
																			Circle = Circle+Lore.split(" ")[k]+" ";
																		else
																			Circle = Circle+"��c�� ";
																	}
																	Lore =  item.getItemMeta().getLore().get(count).split(" : ")[0] + " : " + Circle;
																	player.sendMessage("��c[�� ����] : �� ���� ����!!!");
																	player.sendMessage("��c[�������� �� ������ �ı��Ǿ����ϴ�!]");
																	SoundEffect.SP(player, Sound.BLOCK_ANVIL_BREAK, 1.0F, 1.1F);
																}
																if(event.getInventory().getItem(13).getAmount()==1)
																	event.getInventory().setItem(13, new ItemStack(0));
																else
																	event.getInventory().getItem(13).setAmount(event.getInventory().getItem(13).getAmount()-1);
														  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * Pay, 0, false);

																ItemStack ii = item;
																ItemMeta IM = item.getItemMeta();
																String LL = "";
																for(int k = 0; k < item.getItemMeta().getLore().size();k++)
																{
																	if(item.getItemMeta().getLore().get(k).contains("��") == true&&item.getItemMeta().getLore().get(k).contains(" : ") == true)
																	{
																		if(k < item.getItemMeta().getLore().size()-1)
																			LL = LL+Lore+"%enter%";
																		else
																			LL = LL+Lore;
																	}
																	else
																		LL = LL+item.getItemMeta().getLore().get(k) + "%enter%";
																}
																String[] scriptA = LL.split("%enter%");
																IM.setLore(Arrays.asList(scriptA));
																ii.setItemMeta(IM);
																player.getInventory().setItemInMainHand(ii);

																if(Success == true)
																{
																	ii = player.getInventory().getItemInMainHand();
																	if(MaxDura!=0)
																		ii = Calculator(ii, "Durability", MaxDura);
																	if(MinDamage!=0)
																		ii = Calculator(ii, "MinDamage", MinDamage);
																	if(MaxDamage!=0)
																		ii = Calculator(ii, "MaxDamage", MaxDamage);
																	if(MinMaDamage!=0)
																		ii = Calculator(ii, "MinMaDamage", MinMaDamage);
																	if(MaxMaDamage!=0)
																		ii = Calculator(ii, "MaxMaDamage", MaxMaDamage);
																	if(DEF!=0)
																		ii = Calculator(ii, "DEF", DEF);
																	if(Protect!=0)
																		ii = Calculator(ii, "Protect", Protect);
																	if(MaDEF!=0)
																		ii = Calculator(ii, "MaDEF", MaDEF);
																	if(MaProtect!=0)
																		ii = Calculator(ii, "MaProtect", MaProtect);
																	if(Critical!=0)
																		ii = Calculator(ii, "Critical", Critical);
																	if(Balance!=0)
																		ii = Calculator(ii, "Balance", Balance);
																	if(HP!=0)
																		ii = Calculator(ii, "HP", HP);
																	if(MP!=0)
																		ii = Calculator(ii, "MP", MP);
																	if(STR!=0)
																		ii = Calculator(ii, "STR", STR);
																	if(DEX!=0)
																		ii = Calculator(ii, "DEX", DEX);
																	if(INT!=0)
																		ii = Calculator(ii, "INT", INT);
																	if(WILL!=0)
																		ii = Calculator(ii, "WILL", WILL);
																	if(LUK!=0)
																		ii = Calculator(ii, "LUK", LUK);
																	if(NowDura!=0)
																		ii = Calculator(ii, "NowDura", NowDura);
																}
																return;
															}
														}
													}
												}
												player.sendMessage("��c[�� ����] : ���� ���� ��ų �� �ִ� ���� ������ �����ϴ�!");
												SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
											}
											else
											{
												player.sendMessage("��c[�� ����] : �������� �����մϴ�!");
												SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
											}
										}
										else
										{
											player.sendMessage("��c[�� ����] : ���� ������ų �� ���� ������ �Դϴ�!");
											SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
										}
										
									}
									else
									{
										player.sendMessage("��c[�� ����] : ���� ������ų �� ���� ������ �Դϴ�!");
										SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
									}
								}
								else
								{
									player.sendMessage("��c[�� ����] : �տ� �������� �����ϰ� �־�� �մϴ�!");
									SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
								}
							}
							else
							{
								player.sendMessage("��c[�� ����] : ���� �ø� �������� ���� �ƴմϴ�!");
								SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
							}
						}
						else
						{
							player.sendMessage("��c[�� ����] : ���� �ø� �������� ���� �ƴմϴ�!");
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						}
					}
					else
					{
						player.sendMessage("��c[�� ����] : ���� �ø� �������� ���� �ƴմϴ�!");
						SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					}
				}
				else
				{
					player.sendMessage("��c[�� ����] : ���� ��ų ���� �÷� �ּ���!");
					SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
				}
			}
		}
	}

	public void TalkGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		
		if(slot == 53)//�ݱ�
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.8F);
			player.closeInventory();
		}
		else
		{
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			String TalkType = ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			if(slot == 45)//���� ���
				MainGUI(player, NPCname, player.isOp());
			else if(slot == 46)//��ȭ Ÿ�� ����
			{
				if(TalkType.equals("NT"))
					NPCTalkGUI(player, (short) 0, NPCname, "NN");
				else if(TalkType.equals("NN"))
					NPCTalkGUI(player, (short) 0, NPCname, "AS");
				else
					NPCTalkGUI(player, (short) 0, NPCname, "NT");
			}
			else if(slot == 48)//���� ���
				NPCTalkGUI(player, (short) (page-1), NPCname, TalkType);
			else if(slot == 49)//��� �߰�
			{
				short number = 1;
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
				if(TalkType.equals("NT"))
				{
					number = (short) (NPCConfig.getConfigurationSection("NatureTalk").getKeys(false).toArray().length+1);
					NPCConfig.set("NatureTalk."+number+".love", 0);
					NPCConfig.set("NatureTalk."+number+".Script", "��f��� ����");
				}
				else if(TalkType.equals("NN"))
				{
					number = (short) (NPCConfig.getConfigurationSection("NearByNEWS").getKeys(false).toArray().length+1);
					NPCConfig.set("NearByNEWS."+number+".love", 0);
					NPCConfig.set("NearByNEWS."+number+".Script", "��f��� ����");
				}
				else
				{
					number = (short) (NPCConfig.getConfigurationSection("AboutSkills").getKeys(false).toArray().length+1);
					NPCConfig.set("AboutSkills."+number+".love", 0);
					NPCConfig.set("AboutSkills."+number+".giveSkill", "null");
					NPCConfig.set("AboutSkills."+number+".Script", "��f��� ����");
					NPCConfig.set("AboutSkills."+number+".AlreadyGetScript", "��� ����");
				}
				NPCConfig.saveConfig();
				NPCTalkGUI(player, page, NPCname, TalkType);
			}
			else if(slot == 50)//���� ���
				NPCTalkGUI(player, (short) (page+1), NPCname, TalkType);
			else if(slot == 52)//Ÿ�� ����
			{
				if(TalkType.equals("NT"))
					NPCTalkGUI(player, (short) 0, NPCname, "AS");
				else if(TalkType.equals("NN"))
					NPCTalkGUI(player, (short) 0, NPCname, "NT");
				else
					NPCTalkGUI(player, (short) 0, NPCname, "NN");
			}
			else
			{
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
				short Number = Short.parseShort(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				if(event.isRightClick()&&event.isShiftClick())
				{
					SoundEffect.SP(player, Sound.BLOCK_LAVA_POP, 1.0F, 1.0F);
					Short Acount = 0;
					switch(TalkType)
					{
					case "NT"://NatureTalk
						Acount =  (short) NPCConfig.getConfigurationSection("NatureTalk").getKeys(false).toArray().length;
						for(int counter = Number;counter <Acount;counter++)
						{
							NPCConfig.set("NatureTalk."+counter+".love", NPCConfig.getInt("NatureTalk."+(counter+1)+".love"));
							NPCConfig.set("NatureTalk."+counter+".Script", NPCConfig.getString("NatureTalk."+(counter+1)+".Script"));
						}
						NPCConfig.removeKey("NatureTalk."+Acount);
						NPCConfig.saveConfig();
						NPCTalkGUI(player, page, NPCname, TalkType);
						break;
					case "NN"://NearbyNews
						Acount =  (short) NPCConfig.getConfigurationSection("NearByNEWS").getKeys(false).toArray().length;
						for(int counter = Number;counter <Acount;counter++)
						{
							NPCConfig.set("NearByNEWS."+counter+".love", NPCConfig.getInt("NearByNEWS."+(counter+1)+".love"));
							NPCConfig.set("NearByNEWS."+counter+".Script", NPCConfig.getString("NearByNEWS."+(counter+1)+".Script"));
						}
						NPCConfig.removeKey("NearByNEWS."+Acount);
						NPCConfig.saveConfig();
						NPCTalkGUI(player, page, NPCname, TalkType);
						break;
					case "AS"://AboutSkill
						Acount =  (short) NPCConfig.getConfigurationSection("AboutSkills").getKeys(false).toArray().length;
						for(int counter = Number;counter <Acount;counter++)
						{
							NPCConfig.set("AboutSkills."+counter+".love", NPCConfig.getInt("AboutSkills."+(counter+1)+".love"));
							NPCConfig.set("AboutSkills."+counter+".giveSkill", NPCConfig.getInt("AboutSkills."+(counter+1)+".giveSkill"));
							NPCConfig.set("AboutSkills."+counter+".Script", NPCConfig.getString("AboutSkills."+(counter+1)+".Script"));
							NPCConfig.set("AboutSkills."+counter+".AlreadyGetScript", NPCConfig.getString("AboutSkills."+(counter+1)+".AlreadyGetScript"));
						}
						NPCConfig.removeKey("AboutSkills."+Acount);
						NPCConfig.saveConfig();
						NPCTalkGUI(player, page, NPCname, TalkType);
						break;
					}
				}
				else
					TalkSettingGUI(player, NPCname, TalkType, Number);
			}
		}
	}
	
	public void TalkSettingGUIClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 35)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String TalkType = ChatColor.stripColor(event.getInventory().getItem(27).getItemMeta().getLore().get(1));
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(35).getItemMeta().getLore().get(1));
			short number =  Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(10).getItemMeta().getDisplayName()));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			
			if(slot == 27)
				NPCTalkGUI(player,(short) (number/45), NPCname, TalkType);
			else if(slot == 15)//��ų
			{
				YamlLoader Config = new YamlLoader();
				Config.getConfig("config.yml");
				if(Config.getBoolean("Server.Like_The_Mabinogi_Online_Stat_System") == true)
				{
					YamlLoader SkillList = new YamlLoader();
					SkillList.getConfig("Skill/JobList.yml");
					if(SkillList.getConfigurationSection("Mabinogi").getKeys(false).toArray().length >= 0)
					{
						if(SkillList.getConfigurationSection("Mabinogi.Added").getKeys(false).toArray().length >= 0)
						{
							AddAbleSkillsGUI(player, (short) 0, NPCname,number);
							return;
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
							player.sendMessage("��c[SYSTEM] : ��� ������ ��ų�� �����ϴ�!");
						}
					}
					else
					{
						SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
						player.sendMessage("��c[SYSTEM] : ��� ������ ��ų�� �����ϴ�!");
					}
				}
				else
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
					player.sendMessage("��c[SYSTEM] : ���� �ý����� ������ ������ �ƴմϴ�!");
				}
			}
			else if(slot==13||slot==14||slot==16)
			{
				player.closeInventory();
				UserDataObject u = new UserDataObject();
				u.setType(player, "NPC");
				u.setString(player, (byte)2,NPCname);
				u.setString(player, (byte)3,u.getNPCuuid(player));
				u.setString(player, (byte)5,TalkType);
				u.setString(player, (byte)6,number+"");
				if(slot == 13)//ȣ����
				{
					player.sendMessage("��3[���] : �� ��縦 ���� ���ؼ��� �ּ� ���� ȣ������ �ʿ��Ѱ���?");
					player.sendMessage("��a(��e0��a ~ ��e"+Integer.MAX_VALUE+"��a)");
					u.setString(player, (byte)4,"NPC_TNL");
				}
				else
				{

					if(slot == 14)//���1
					{
						player.sendMessage("��3[���] : NPC�� �� ��縦 �Է��� �ּ���!");
						u.setString(player, (byte)4,"NPC_TS");
					}
					else if(slot == 16)//���2
					{
						player.sendMessage("��3[���] : �÷��̾�� ��ų�� ������ ��, NPC�� �� ��縦 �Է��� �ּ���!");
						u.setString(player, (byte)4,"NPC_TS2");
					}
					player.sendMessage("��6%enter%��f - ���� ��� ���� -");
					player.sendMessage("��6%player%��f - �÷��̾� ��Ī�ϱ� -");
					player.sendMessage("��f��l&l ��0&0 ��1&1 ��2&2 "+
					"��3&3 ��4&4 ��5&5 " +
							"��6&6 ��7&7 ��8&8 " +
					"��9&9 ��a&a ��b&b ��c&c " +
							"��d&d ��e&e ��f&f");
				}
			}
		}
	}
	
	public void AddAbleSkillsGUIClick(InventoryClickEvent event)
	{
		int slot = event.getSlot();
		Player player = (Player) event.getWhoClicked();
		
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 1.0F, 1.8F);
			player.closeInventory();
		}
		else
		{
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			String TalkType = "AS";
			short number =  Short.parseShort(ChatColor.stripColor(event.getInventory().getItem(45).getItemMeta().getLore().get(1)));
			short page =  (short) (Short.parseShort(event.getInventory().getTitle().split(" : ")[1])-1);
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
			
			if(slot == 45)//���� �޴�
				TalkSettingGUI(player, NPCname, TalkType, number);
			else if(slot == 48)//���� ������
				AddAbleSkillsGUI(player, (short) (page-1), NPCname, number);
			else if(slot == 50)//���� ������
				AddAbleSkillsGUI(player, (short) (page+1), NPCname, number);
			else
			{
				UserDataObject u = new UserDataObject();
				YamlLoader NPCConfig = new YamlLoader();
				NPCConfig.getConfig("NPC/NPCData/"+u.getNPCuuid(player)+".yml");
				NPCConfig.set("AboutSkills."+number+".giveSkill", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
				NPCConfig.saveConfig();
				TalkSettingGUI(player, NPCname, TalkType,number);
			}
		}
	}

	public void ItemBuyGuiClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		
		if(slot == 53)//������
		{
			SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
			player.closeInventory();
		}
		else
		{
			boolean isBuy = false;
			isBuy = event.getInventory().getName().contains("����");
			String NPCname = ChatColor.stripColor(event.getInventory().getItem(53).getItemMeta().getLore().get(1));
			SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
			if(slot == 45)//���� ���
				ShopGUI(player, NPCname, (short)0, isBuy, false);
			else
			{
				ItemStack item = event.getInventory().getItem(22);
				int value = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(0)));
				int count = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(1).getItemMeta().getLore().get(0)));

				if(isBuy)
				{
					if(slot == 49 && count != 0)
					{
						if(MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() >= value*count)
						{
							for(int counter = 0; counter < count; counter++)
							{
								if(new util.UtilPlayer().giveItem(player, item)==false)
								{
									SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.8F);
									player.sendMessage("��c[���� ����] : �κ��丮�� �����Ͽ� "+(count-counter)+"���� �������� ���Ͽ����ϴ�!");
									MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_Money(MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() - (counter*value));
									return;
								}
							}
							SoundEffect.SP(player, Sound.ENTITY_SHULKER_OPEN, 0.8F, 1.0F);
							MainServerOption.PlayerList.get(player.getUniqueId().toString()).setStat_Money(MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() - (count*value));
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.8F);
							player.sendMessage("��c[���� ����] : "+MainServerOption.money+"��c�� �����Ͽ� ������ �� �����ϴ�!");
						}
					}
					else if(slot == 31)
					{
						SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_DIAMOND, 0.8F, 0.5F);
						count = (int) (MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() / value);
					}
					else if(slot == 13)
					{
						SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.6F, 1.8F);
						count = 1;
					}
					else if(slot>=19&&slot<=21)
					{
						if(slot==19)
						{
							SoundEffect.SP(player, Sound.ENTITY_SHULKER_CLOSE, 0.8F, 1.6F);
							if(count-64 > 0)
								count -= 64;
							else
								count = 1;
						}
						else if(slot==20)
						{
							SoundEffect.SP(player, Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 0.8F, 1.4F);
							if(count-10 > 0)
								count -= 10;
							else
								count = 1;
						}
						else if(slot==21)
						{
							SoundEffect.SP(player, Sound.ENTITY_ITEMFRAME_ROTATE_ITEM, 0.8F, 1.4F);
							if(count-1 > 0)
								count -= 1;
						}
					}
					else if(slot >= 23 && slot <= 25)
					{
						int TempCount = count;
						if(slot == 23)
						{
							SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_IRON, 0.8F, 1.8F);
							TempCount += 1;
						}
						else if(slot == 24)
						{
							SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.8F, 1.2F);
							TempCount += 10;
						}
						else if(slot == 25)
						{
							SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_GOLD, 0.8F, 0.5F);
							TempCount += 64;
						}
						if(value * TempCount <= MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money())
							count = TempCount;
						else if(slot != 23)
							count = (int) (MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money() / value);
					}
					ItemBuy(player, item, value, NPCname, isBuy, count);
				}
				else
				{
					int ItemHave = new util.UtilPlayer().getItemAmount(player, item);

					if(slot == 49 && count != 0)
					{
						if(new util.UtilPlayer().deleteItem(player, item, count)==false)
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.8F);
							player.sendMessage("��c[�Ǹ� ����] : ��ǰ�� �����Ͽ� �Ǹ����� ���Ͽ����ϴ�!");
							return;
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 1.0F);
							MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(value*count, 0, false);
						}
					}
					
					if(slot == 31)
					{
						SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_DIAMOND, 0.8F, 0.5F);
						count = ItemHave;
					}
					else if(slot == 13)
					{
						SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.6F, 1.8F);
						if(ItemHave > 0)
							count = 1;
						else
							count = 0;
					}
					else if(slot>=19&&slot<=21)
					{
						if(slot==19)
						{
							SoundEffect.SP(player, Sound.ENTITY_SHULKER_CLOSE, 0.8F, 1.6F);
							if(count-64 > 0)
								count -= 64;
							else
								count = 1;
						}
						else if(slot==20)
						{
							SoundEffect.SP(player, Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 0.8F, 1.4F);
							if(count-10 > 0)
								count -= 10;
							else
								count = 1;
						}
						else if(slot==21)
						{
							SoundEffect.SP(player, Sound.ENTITY_ITEMFRAME_ROTATE_ITEM, 0.8F, 1.4F);
							if(count-1 > 0)
								count -= 1;
						}
					}
					else if(slot >= 23 && slot <= 25)
					{
						int TempCount = count;
						if(slot == 23)
						{
							SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_IRON, 0.8F, 1.8F);
							TempCount += 1;
						}
						else if(slot == 24)
						{
							SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.8F, 1.2F);
							TempCount += 10;
						}
						else if(slot == 25)
						{
							SoundEffect.SP(player, Sound.ITEM_ARMOR_EQUIP_GOLD, 0.8F, 0.5F);
							TempCount += 64;
						}
						if(TempCount <= ItemHave)
							count = TempCount;
						else
							count = ItemHave;
					}
					ItemBuy(player, item, value, NPCname, isBuy, count);
				}
			}
		}
	}

	public void ItemFixGuiClick(InventoryClickEvent event)
	{
		
		Player player = (Player) event.getWhoClicked();

		if(event.getClickedInventory().getTitle().equals("container.inventory"))
		{
			ItemStack clickedItem = event.getCurrentItem();
			short ItemID = (short) clickedItem.getTypeId();
			boolean HasCustomDurability = false;
			if(clickedItem.hasItemMeta()==true)
			{
				if(clickedItem.getItemMeta().hasLore()==true)
				{
					for(int count = 0; count < clickedItem.getItemMeta().getLore().size(); count++)
					{
						if(clickedItem.getItemMeta().getLore().get(count).contains("������") == true)
						{
							HasCustomDurability= true;
							break;
						}
					}
				}
			}
			if((ItemID>=256&&ItemID<=259)||(ItemID==261)||(ItemID>=267&&ItemID<=279)||(ItemID>=283&&ItemID<=286)||(ItemID>=290&&ItemID<=294)
					||(ItemID>=298&&ItemID<=317)||ItemID==346||ItemID==359||ItemID==398||ItemID==442||ItemID==443||HasCustomDurability==true)
			{
			  	long playerMoney = main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).getStat_Money();
			  	long FixPrice = Long.parseLong(ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(2)));
			  	byte FixRate = (byte)Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(1)));
				if(HasCustomDurability == false)
				{
					short nowDurability = (short) (clickedItem.getDurability());
					
					if(nowDurability == 0)
					{
						SoundEffect.SP(player,Sound.BLOCK_ANVIL_LAND, 0.8F, 1.6F);
						player.sendMessage("��3[����] : �ش� ����� �������� �ʿ䰡 �����ϴ�.");
						return;
					}
					
					int point10need = (nowDurability/10);
					int point1need = (nowDurability%10);

					int point10success = 0;
					int point1success = 0;
					
					if(playerMoney < point10need*FixPrice ||playerMoney < ((point1success*FixPrice)/10))
					{
						SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[SYSTEM] : ���� ����� �����մϴ�!");
						return;
					}
					
					for(int count = 0; count < point10need;count++)
					{
						if(new Random().nextInt(101) <= FixRate)
							point10success = (point10success+1);
					}
					for(int count = 0; count < point1need;count++)
					{
						if(new Random().nextInt(101) <= FixRate)
							point1success = (point1success+1);
					}
					if(point10success==0 && point1success==0)
					{
						player.sendMessage("��c[����] : ���� ���� ����!");
						SoundEffect.SP(player,Sound.BLOCK_ANVIL_BREAK, 1.2F, 1.0F);
						return;
					}
					SoundEffect.SP(player,Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
					if(point10success == point10need && point1success ==point1need)
						player.sendMessage("��3[����] : ���� �뼺��!");
					if(point10success != point10need || point1success !=point1need)
						player.sendMessage("��e[����] : ��f"+((point10success*10)+point1success)+"��e ����Ʈ ���� ����, ��f"+((point10need-(point10success))*10+(point1need-point1success))+"��e ����Ʈ ���� ���� ");

					clickedItem.setDurability((short) (clickedItem.getDurability()-((point10success*10)+point1success)));
			  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * ((point10need*FixPrice)+((point1need*FixPrice)/10)), 0, false);
				}
				else//Ŀ���� ������ ���� ���
				{
					int Maxdurability = 0;
					int nowDurability =0;
					for(int count = 0; count < clickedItem.getItemMeta().getLore().size();count++)
					{
						if(clickedItem.getItemMeta().getLore().get(count).contains("������") == true)
						{
							Maxdurability = Integer.parseInt(ChatColor.stripColor(clickedItem.getItemMeta().getLore().get(count)).split(" : ")[1].split(" / ")[1]);
							nowDurability = Integer.parseInt(ChatColor.stripColor(clickedItem.getItemMeta().getLore().get(count)).split(" : ")[1].split(" / ")[0]);
							break;
						}
					}
					
					if(nowDurability == Maxdurability)
					{
						SoundEffect.SP(player,Sound.BLOCK_ANVIL_LAND, 0.8F, 1.6F);
						player.sendMessage("��3[����] : �ش� ����� �������� �ʿ䰡 �����ϴ�.");
						return;
					}
					
					int point10need = (Maxdurability-nowDurability)/10;
					int point1need = (Maxdurability-nowDurability)%10;

					int point10success = 0;
					int point1success = 0;
					
					if(playerMoney < point10need*FixPrice ||playerMoney < ((point1success*FixPrice)/10))
					{
						SoundEffect.SP(player,Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
						player.sendMessage("��c[SYSTEM] : ���� ����� �����մϴ�!");
						return;
					}
					
					for(int count = 0; count < point10need;count++)
					{
						if(new Random().nextInt(101) <= FixRate)
							point10success = (point10success+1);
					}
					for(int count = 0; count < point1need;count++)
					{
						if(new Random().nextInt(101) <= FixRate)
							point1success = (point1success+1);
					}
					if(point10success==0 && point1success==0)
					{
						player.sendMessage("��c[����] : ���� ���� ����!");
						SoundEffect.SP(player,Sound.BLOCK_ANVIL_BREAK, 1.2F, 1.0F);
					}
					SoundEffect.SP(player,Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
					if(point10success == point10need && point1success ==point1need)
						player.sendMessage("��3[����] : ���� �뼺��!");
					if((point10success != point10need || point1success !=point1need)&&(point10success!=0 || point1success!=0))
						player.sendMessage("��e[����] : ��f"+((point10success*10)+point1success)+"��e ����Ʈ ���� ����, ��f"+((point10need-(point10success))*10+(point1need-point1success))+"��e ����Ʈ ���� ���� ");

					if(clickedItem.hasItemMeta() == true)
					{
						if(clickedItem.getItemMeta().hasLore() == true)
						{
							for(int count = 0; count < clickedItem.getItemMeta().getLore().size(); count++)
							{
								ItemMeta Meta = clickedItem.getItemMeta();
								if(Meta.getLore().get(count).contains("������") == true)
								{
									String[] Lore = ChatColor.stripColor(Meta.getLore().get(count)).split(" : ");
									String[] SubLore = Lore[1].split(" / ");
									List<String> PLore = Meta.getLore();
									PLore.set(count,"��f"+ Lore[0] + " : "+(Integer.parseInt(SubLore[0])+((point10success*10)+point1success))+" / "+(Integer.parseInt(SubLore[1])-(((point10need-point10success)*10)+(point1need-point1success))));
									Meta.setLore(PLore);
									clickedItem.setItemMeta(Meta);
								}
							}
						}
					}
			  		main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).addStat_MoneyAndEXP(-1 * ((point10need*FixPrice)+((point1need*FixPrice)/10)), 0, false);
				}
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.8F, 1.0F);
				player.sendMessage("��c[SYSTEM] : ���� �� �� ���� �����Դϴ�!");
			}
		}
		else
		{
			int slot = event.getSlot();
			if(slot == 8)
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(slot == 0)
				{
					String NPCname = ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getLore().get(1));
					MainGUI(player, NPCname, player.isOp());
				}
			}
		}
	}
	
	public void PresentGuiClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		
		
		String NPCname = ChatColor.stripColor(event.getInventory().getItem(8).getItemMeta().getLore().get(1)); 
		if(ChatColor.stripColor(event.getInventory().getName()).equals("[NPC] ���� �������� �÷� �ּ���"))
		{
			int number = Integer.parseInt(ChatColor.stripColor(event.getInventory().getItem(1).getItemMeta().getLore().get(0)));
			boolean isSettingMode = Boolean.parseBoolean(ChatColor.stripColor(event.getInventory().getItem(0).getItemMeta().getLore().get(1)));

			if(event.getSlot()==4)
			{
				event.setCancelled(false);
				return;
			}
			String DisplayName = null;
			if(event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName())
			{
				DisplayName = event.getCurrentItem().getItemMeta().getDisplayName();
				byte size = (byte) DisplayName.length();
				if(size >= 6 && DisplayName.charAt(size-1)=='c'&&DisplayName.charAt(size-2)=='��'&&DisplayName.charAt(size-3)=='e'&&DisplayName.charAt(size-4)=='��'&& DisplayName.charAt(size-5)=='0'&&DisplayName.charAt(size-6)=='��')
				{
					event.setCancelled(true);
					YamlLoader NPCConfig = new YamlLoader();
					NPCConfig.getConfig("NPC/NPCData/"+new UserDataObject().getNPCuuid(player)+".yml");
					if(event.getSlot()==0)//���� ����
					{
						if(isSettingMode)
						{
							ItemStack item = NPCConfig.getItemStack("Present."+number+".item");
							if(event.getInventory().getItem(4) != null)
								NPCConfig.set("Present."+number+".item", event.getInventory().getItem(4));
							else
							{
								NPCConfig.set("Present."+number+".item", null);
								NPCConfig.saveConfig();
								SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
								PresentSettingGUI(player, NPCname);
								return;
							}
							NPCConfig.set("Present."+number+".love", 0);
							NPCConfig.saveConfig();
							SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
							UserDataObject u = new UserDataObject();
							u.setType(player, "NPC");
							u.setString(player, (byte)2,NPCname);
							u.setString(player, (byte)3,u.getNPCuuid(player));
							u.setString(player, (byte)4,"PresentLove");
							u.setInt(player, (byte)0, number);
							player.sendMessage("��3[NPC] : �ش� �������� �� �� ����ϴ� ȣ���� ��ġ�� �Է� �� �ּ���! (-1000 ~ 1000 ���� ��)");
							player.closeInventory();
						}
						else
						{
							SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
							MainGUI(player, NPCname, player.isOp());
						}
					}
					else if(event.getSlot() == 8)//�ݴ�(���� �ֱ�)
					{
						if(isSettingMode)
						{
							SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
							player.closeInventory();
							return;
						}
						YamlLoader PlayerNPC = new YamlLoader();
				    	if(PlayerNPC.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
				    	{
							UserDataObject u = new UserDataObject();
				    		PlayerNPC.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
				    		PlayerNPC.set(u.getNPCuuid(player)+".love", 0);
				    		PlayerNPC.set(u.getNPCuuid(player)+".Career", 0);
				    		PlayerNPC.saveConfig();
				    	}
				    	else
				    		PlayerNPC.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
						if(event.getInventory().getItem(4) != null)
						{
					    	ItemStack Present = new ItemStack(event.getInventory().getItem(4));
					    	int Amount = Present.getAmount();
					    	Present.setAmount(1);

							UserDataObject u = new UserDataObject();
							for(int count = 2; count < 8; count++)
							{
								if(NPCConfig.getItemStack("Present."+count+".item") != null)
								{
									ItemStack LoveItem = new ItemStack(NPCConfig.getItemStack("Present."+count+".item"));
									int LoveItemAmount = LoveItem.getAmount();
									LoveItem.setAmount(1);
									if(LoveItem.equals(Present))
									{
										event.getInventory().setItem(4, null);
										int LoveValue = NPCConfig.getInt("Present."+count+".love");
										int love = 0;
										if(LoveValue >= 0)
										{
											if(Amount / LoveItemAmount == 0)
												love = 1;
											else
												love = LoveValue * (Amount/LoveItemAmount);
										}
										else
											love = LoveValue;
										if(PlayerNPC.getInt(u.getNPCuuid(player)+".love") + love >= 1000)
											PlayerNPC.set(u.getNPCuuid(player)+".love", 1000);
										else if(PlayerNPC.getInt(u.getNPCuuid(player)+".love") + love <= -1000)
											PlayerNPC.set(u.getNPCuuid(player)+".love", -1000);
										else
											PlayerNPC.set(u.getNPCuuid(player)+".love", PlayerNPC.getInt(u.getNPCuuid(player)+".love") + love);
										PlayerNPC.saveConfig();
										if(love >= 0)
										{
											SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
											player.sendMessage("��a[SYSTEM] : ��e"+NPCname+"��a�� ȣ������ ��e"+love+"��a ����Ͽ����ϴ�!");
										}
										else
										{
											SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.8F);
											player.sendMessage("��c[SYSTEM] : ��e"+NPCname+"��c�� ȣ������ ��e"+(love*-1)+"��c �϶� �Ͽ����ϴ�!");
										}
										return;
									}
								}
							}
							if(NPCConfig.getInt("Present.1.love")!=0)
							{
								event.getInventory().setItem(4, null);
								int LoveValue = NPCConfig.getInt("Present.1.love");
								int love = 0;
								if(LoveValue >= 0)
									love = LoveValue * Amount;
								else
									love = LoveValue;
								if(PlayerNPC.getInt(u.getNPCuuid(player)+".love") + love >= 1000)
									PlayerNPC.set(u.getNPCuuid(player)+".love", 1000);
								else if(PlayerNPC.getInt(u.getNPCuuid(player)+".love") + love <= -1000)
									PlayerNPC.set(u.getNPCuuid(player)+".love", -1000);
								else
									PlayerNPC.set(u.getNPCuuid(player)+".love", PlayerNPC.getInt(u.getNPCuuid(player)+".love") + love);
								PlayerNPC.saveConfig();
								if(love >= 0)
								{
									SoundEffect.SP(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.8F);
									player.sendMessage("��a[SYSTEM] : ��e"+NPCname+"��a�� ȣ������ ��e"+love+"��a ����Ͽ����ϴ�!");
								}
								else
								{
									SoundEffect.SP(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.8F);
									player.sendMessage("��c[SYSTEM] : ��e"+NPCname+"��c�� ȣ������ ��e"+(love*-1)+"��c �϶� �Ͽ����ϴ�!");
								}
								return;
							}
							else
							{
								SoundEffect.SP(player, Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 1.0F, 1.8F);
								player.sendMessage("��e[SYSTEM] : ��6"+NPCname+"��e�� ������ ����Ͽ���.");
							}
						}
						else
							SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					}
					else
						SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					return;
				}
			}
		}
		else//[NPC] ���� ���� ������ ���
		{
			if(event.getSlot()==0)
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				MainGUI(player, NPCname, player.isOp());
			}
			else if(event.getSlot() == 8)
			{
				SoundEffect.SP(player, Sound.BLOCK_PISTON_CONTRACT, 0.8F, 1.8F);
				player.closeInventory();
			}
			else
			{
				SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
				if(event.getSlot()==1)
				{
					UserDataObject u = new UserDataObject();
					u.setType(player, "NPC");
					u.setString(player, (byte)2,NPCname);
					u.setString(player, (byte)3,u.getNPCuuid(player));
					u.setString(player, (byte)4,"PresentLove");
					u.setInt(player, (byte)0, 1);
					player.sendMessage("��3[NPC] : �ƹ� �������̳� �� �� ����ϴ� ȣ���� ��ġ�� �Է� �� �ּ���! (-1000 ~ 1000 ���� ��)");
					player.closeInventory();
				}
				else
					PresentGiveGUI(player, NPCname, true, event.getSlot());
			}
		}
		return;
	}
	

	
	public void RuneEquipGUIClose(InventoryCloseEvent event)
	{
		if(event.getInventory().getSize() > 9)
			if(event.getInventory().getItem(13)!=null)
				event.getPlayer().getInventory().addItem(event.getInventory().getItem(13));
		return;
	}
	
	public void PresentInventoryClose(InventoryCloseEvent event)
	{
		if(event.getInventory().getItem(4)!=null)
			event.getPlayer().getInventory().addItem(event.getInventory().getItem(4));
		return;
	}
}