package event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import user.UserDataObject;
import user.UserObject;
import util.YamlLoader;



public class EventPlayerJoin implements Listener
{
	@EventHandler
	private void PlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		String playerUUID = player.getUniqueId().toString();
		new UserObject(player);
	  	YamlLoader config = new YamlLoader();
		config.getConfig("config.yml");
	  	if(config.isExit("Skill/PlayerData/" + playerUUID+".yml") == false)
	  		new skill.SkillConfig().CreateNewPlayerSkill(player);
	  	else
	  		new job.JobMain().PlayerFixAllSkillAndJobYML(player);

	  	if(player.getLocation().getWorld().getName().equals("Dungeon"))
		{
			new util.UtilPlayer().teleportToCurrentArea(player, true);
			new dungeon.DungeonMain().EraseAllDungeonKey(player, false);
			main.MainServerOption.PlayerList.get(event.getPlayer().getUniqueId().toString()).setDungeon_Enter(null);
			main.MainServerOption.PlayerList.get(event.getPlayer().getUniqueId().toString()).setDungeon_UTC(-1);
		}
		if(new corpse.CorpseMain().deathCapture(player,true))
			new corpse.CorpseMain().createCorpse(player);
		
    	new main.MainServerOption().MagicSpellCatch();
    	new main.MainServerOption().CitizensCatch();

		new UserDataObject().UserDataInit(player);
		
		if(player.isOp() == true)
			new effect.SendPacket().sendTitleSubTitle(player,"\'��e/���ǹڽ�\'", "\'��eGoldBigDragonAdvanced ���̵� �� ���� ������ �����մϴ�.\'", (byte)1,(byte)10, (byte)1);
		else
		{
			if(config.getInt("Event.DropChance")>=2||config.getInt("Event.Multiple_EXP_Get")>=2||config.getInt("Event.Multiple_Level_Up_StatPoint")>=2||config.getInt("Event.Multiple_Level_Up_SkillPoint")>=2)
			{
				String alert = "[";
				if(config.getInt("Event.DropChance")>=2)
					alert =alert+ "��ӷ� ���� "+config.getInt("Event.DropChance")+"��";
				if(config.getInt("Event.DropChance")>=2)
					alert = alert+", ";
				if(config.getInt("Event.Multiple_EXP_Get")>=2)
					alert = alert + "����ġ " + config.getInt("Event.Multiple_EXP_Get")+"�� ȹ��";
				if(config.getInt("Event.Multiple_EXP_Get")>=2)
					alert = alert+", ";
				if(config.getInt("Event.Multiple_Level_Up_StatPoint")>=2)
					alert = alert +"���� ����Ʈ "+config.getInt("Event.Multiple_Level_Up_StatPoint")+"�� ȹ��";
				if(config.getInt("Event.Multiple_Level_Up_StatPoint")>=2)
					alert = alert+", ";
				if(config.getInt("Event.Multiple_Level_Up_SkillPoint")>=2)
					alert = alert +"��ų ����Ʈ " +config.getInt("Event.Multiple_Level_Up_SkillPoint")+"�� ȹ��";
				alert = alert+"]";
				new effect.SendPacket().sendTitleSubTitle(player, "\'���� �̺�Ʈ�� �������Դϴ�.\'", "\'"+alert+"\'", (byte)1, (byte)10, (byte)1);
			}
		}
	  	if(config.isExit("Quest/PlayerData/" + playerUUID+".yml") == false)
	  	{
	  	    new quest.QuestConfig().CreateNewPlayerConfig(player);

		  	YamlLoader newbieYaml = new YamlLoader();
			newbieYaml.getConfig("ETC/NewBie.yml");
			for(int count = 0; count < newbieYaml.getConfigurationSection("SupportItem").getKeys(false).toArray().length;count++)
				if(newbieYaml.getItemStack("SupportItem."+count) != null)
					player.getInventory().addItem(newbieYaml.getItemStack("SupportItem."+count));
			player.teleport(new Location(Bukkit.getWorld(newbieYaml.getString("TelePort.World")), newbieYaml.getInt("TelePort.X"), newbieYaml.getInt("TelePort.Y"), newbieYaml.getInt("TelePort.Z"), newbieYaml.getInt("TelePort.Yaw"), newbieYaml.getInt("TelePort.Pitch")));
	  	}
		new util.ETC().UpdatePlayerHPMP(event.getPlayer());
    	new user.EquipGui().FriendJoinQuitMessage(player, true);

		if(config.getString("Server.JoinMessage") != null)
			event.setJoinMessage(config.getString("Server.JoinMessage").replace("%player%",event.getPlayer().getName()));
		else
			event.setJoinMessage(null);
	}
}
