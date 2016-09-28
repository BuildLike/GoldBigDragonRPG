package GBD_RPG.Area;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import GBD_RPG.Util.YamlController;
import GBD_RPG.Util.YamlManager;

public class Area_Command
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) talker;
		if(player.isOp() == false)
		{
			talker.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			s.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("���"))
			{
				GBD_RPG.Area.Area_GUI AGUI = new GBD_RPG.Area.Area_GUI();
				s.SP(player, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				AGUI.AreaListGUI(player, (short) 0);
				return;
			}
			else
			{
			  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				YamlManager AreaList = YC.getNewConfig("Area/AreaList.yml");
				
				if(AreaList.contains(args[0]))
				{
					s.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
					GBD_RPG.Area.Area_GUI AGUI = new GBD_RPG.Area.Area_GUI();
					AGUI.AreaGUI_Main(player, args[0]);
				}
				else
				{
					s.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					player.sendMessage(ChatColor.RED + "[SYSTEM] : �ش� �̸��� ������ �����ϴ�!");
				}
				return;
			}
		}
		if(args.length == 2)
		{
			GBD_RPG.Area.Area_Main A = new GBD_RPG.Area.Area_Main();
			switch(args[1])
			{
			case "����" :
				if(GBD_RPG.Main_Main.Main_ServerOption.catchedLocation1.containsKey(player)==true && GBD_RPG.Main_Main.Main_ServerOption.catchedLocation2.containsKey(player) ==true)
				{
					A.CreateNewArea(player, GBD_RPG.Main_Main.Main_ServerOption.catchedLocation1.get(player), GBD_RPG.Main_Main.Main_ServerOption.catchedLocation2.get(player), args[0]);
					return;
				}
				else
				{
					GBD_RPG.Main_Event.Main_Interact IT = new GBD_RPG.Main_Event.Main_Interact();
				  	YamlController YC = new YamlController(GBD_RPG.Main_Main.Main_Main.plugin);
				  	YamlManager Config = YC.getNewConfig("config.yml");
					player.sendMessage(ChatColor.RED + "[SYSTEM] : ���� " + IT.SetItemDefaultName((short) Config.getInt("Server.AreaSettingWand"),(byte)0) +ChatColor.RED+" �������� �տ� �� ä�� ����� ��/�� Ŭ���Ͽ� ������ ������ �ּ���!");
					s.SP((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
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
			GBD_RPG.Area.Area_Main A = new GBD_RPG.Area.Area_Main();
			String SB = "";
			for(byte a =2; a<= ((args.length)-1);a++)
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
