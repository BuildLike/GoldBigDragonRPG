package npc;

import org.bukkit.entity.Player;

import util.YamlLoader;




public class NpcConfig
{
    public void PlayerNPCconfig(Player player, String NPCuuid)
	{
	  	YamlLoader npcScriptYaml = new YamlLoader();
    	if(npcScriptYaml.isExit("NPC/PlayerData/"+player.getUniqueId()+".yml")==false)
    	{
    		npcScriptYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
    	  	npcScriptYaml.set(NPCuuid+".love", 0);
    	  	npcScriptYaml.set(NPCuuid+".Career", 0);
    	  	npcScriptYaml.saveConfig();
    	}
		npcScriptYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
    	if(npcScriptYaml.contains(NPCuuid) == false)
    	{
    		npcScriptYaml.getConfig("NPC/PlayerData/"+player.getUniqueId()+".yml");
    	  	npcScriptYaml.set(NPCuuid+".love", 0);
    	  	npcScriptYaml.set(NPCuuid+".Career", 0);
    	  	npcScriptYaml.saveConfig();
    	}
	}
    public void NPCNPCconfig(String NPCuuid)
	{
	  	YamlLoader npcScriptYaml = new YamlLoader();
    	if(npcScriptYaml.isExit("NPC/NPCData/"+NPCuuid+".yml")==false)
    	{
    		npcScriptYaml.getConfig("NPC/NPCData/"+NPCuuid+".yml");
    	  	npcScriptYaml.set("NPCuuid", "NPC's uuid");
    	  	npcScriptYaml.set("KoreaLanguage(UTF-8)->JavaEntityLanguage", "http://itpro.cz/juniconv/");
    	  	npcScriptYaml.set("NatureTalk.1.love", 0);
    	  	npcScriptYaml.set("NatureTalk.1.Script", "��f�ȳ�, ��e%player%?");
    	  	npcScriptYaml.set("NatureTalk.2.love", 0);
    	  	npcScriptYaml.set("NatureTalk.2.Script", "��f��� �����%enter%��f�̷��� ����.");
    	  	npcScriptYaml.set("NatureTalk.3.love", 0);
    	  	npcScriptYaml.set("NatureTalk.3.Script", "��1������ ��4�̷��� ��f���� �� �־�!");
    	  	npcScriptYaml.set("NearByNEWS.1.love", 0);
    	  	npcScriptYaml.set("NearByNEWS.1.Script", "��f���������� ���� ���̾Ƹ�带 �� �� ������...");
    	  	npcScriptYaml.set("NearByNEWS.2.love", 0);
    	  	npcScriptYaml.set("NearByNEWS.2.Script", "��f�����ϴ°� ����.%enter%��f���� ��4���κ� ��f�� �� ��ó�� �־��ŵ�...");
    	  	npcScriptYaml.set("NearByNEWS.3.love", 0);
    	  	npcScriptYaml.set("NearByNEWS.3.Script", "��f��...");
    	  	npcScriptYaml.set("AboutSkills.1.love", 0);
    	  	npcScriptYaml.set("AboutSkills.1.giveSkill", "null");
    	  	npcScriptYaml.set("AboutSkills.1.AlreadyGetScript", "null");
    	  	npcScriptYaml.set("AboutSkills.1.Script", "��f���� ��eä�� ��ų��f�� ����!%enter%��f��? �ʵ� �ִٰ�?");
    	  	npcScriptYaml.set("AboutSkills.2.love", 0);
    	  	npcScriptYaml.set("AboutSkills.2.giveSkill", "null");
    	  	npcScriptYaml.set("AboutSkills.2.AlreadyGetScript", "null");
    	  	npcScriptYaml.set("AboutSkills.2.Script", "��f�޸���� ���� �ǰ����� ������%enter%��f�������� ����.");
    	  	npcScriptYaml.set("AboutSkills.3.love", 0);
    	  	npcScriptYaml.set("AboutSkills.3.giveSkill", "null");
    	  	npcScriptYaml.set("AboutSkills.3.AlreadyGetScript", "null");
    	  	npcScriptYaml.set("AboutSkills.3.Script", "��f�ʿ��� ������ �ٸ���%enter%��f����� ���°� ������...");

    	  	npcScriptYaml.createSection("Shop.Sell");
    	  	npcScriptYaml.createSection("Shop.Buy");
    	  	npcScriptYaml.set("Quest.0", null);
    	  	
    	  	npcScriptYaml.saveConfig();
    	}
	}
}
