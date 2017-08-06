package area;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import effect.SoundEffect;
import util.YamlLoader;



public class Area_Command
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    
		Player player = (Player) talker;
		if(player.isOp() == false)
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("���"))
			{
				area.Area_GUI AGUI = new area.Area_GUI();
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				AGUI.AreaListGUI(player, (short) 0);
				return;
			}
			else
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				
				if(areaYaml.contains(args[0]))
				{
					SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
					area.Area_GUI AGUI = new area.Area_GUI();
					AGUI.AreaSettingGUI(player, args[0]);
				}
				else
				{
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �̸��� ������ �����ϴ�!");
				}
				return;
			}
		}
		if(args.length == 2)
		{
			area.Area_Main A = new area.Area_Main();
			switch(args[1])
			{
			case "����" :
				if(main.Main_ServerOption.catchedLocation1.containsKey(player)==true && main.Main_ServerOption.catchedLocation2.containsKey(player) ==true)
				{
					A.CreateNewArea(player, main.Main_ServerOption.catchedLocation1.get(player), main.Main_ServerOption.catchedLocation2.get(player), args[0]);
					return;
				}
				else
				{
					event.Main_Interact IT = new event.Main_Interact();
				  	YamlLoader configYaml = new YamlLoader();
				  	configYaml.getConfig("config.yml");
					player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� " + IT.SetItemDefaultName((short) configYaml.getInt("Server.AreaSettingWand"),(byte)0) +ChatColor.RED+" �������� �տ� �� ä�� ����� ��/�� Ŭ���Ͽ� ������ ������ �ּ���!");
					SoundEffect.SP((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				}
				return;
			case "����" :
				A.RemoveArea(player, args[0]);
				return;
			}
		}
		if(args.length <= 2)
		{
			HelpMessage(player);
			return;
		}
		else
		{
			area.Area_Main A = new area.Area_Main();
			String SB = "";
			for(int a =2; a<= ((args.length)-1);a++)
				SB = SB+args[a]+" ";
			switch(args[1])
			{
			case "�̸�" :
				A.OptionSetting(player, args[0],(char) 0, SB);
				return;
			case "����" :
				A.OptionSetting(player, args[0],(char) 1, SB);
				return;
			}
		}
	}
	
	private void HelpMessage(Player player)
	{
		player.sendMessage(ChatColor.YELLOW+"������������������������[���� ���� ��ɾ�]������������������������");
		player.sendMessage(ChatColor.GOLD + "/���� ���" + ChatColor.YELLOW + " - ���� ����� Ȯ���մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� <���� �̸�>" + ChatColor.YELLOW + " - �ش� ���� ����â�� ���ϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� <���� �̸�> ����" + ChatColor.YELLOW + " - �ش� �̸��� ���� ������ �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� <���� �̸�> ����" + ChatColor.YELLOW + " - �ش� �̸��� ���� ������ �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� <���� �̸�> �̸� <���ڿ�>" + ChatColor.YELLOW + " - �ش� ������ �˸� �޽����� ���� �̸��� ���մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� <���� �̸�> ���� <���ڿ�>" + ChatColor.YELLOW + " - �ش� ������ �˸� �޽����� ���� �ΰ� ������ ���մϴ�.");
		player.sendMessage(ChatColor.YELLOW+"����������������������������������������������������������������");
	}
}
