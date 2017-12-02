package area;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import effect.SoundEffect;
import util.YamlLoader;

public class AreaCommand
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
	    
		Player player = (Player) talker;
		if(!player.isOp())
		{
			talker.sendMessage("��c[SYSTEM] : �ش� ��ɾ �����ϱ� ���ؼ��� ������ ������ �ʿ��մϴ�!");
			SoundEffect.SP((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("���"))
			{
				area.AreaGui areaGui = new area.AreaGui();
				SoundEffect.SP(player, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				areaGui.areaListGui(player, (short) 0);
				return;
			}
			else
			{
			  	YamlLoader areaYaml = new YamlLoader();
				areaYaml.getConfig("Area/AreaList.yml");
				
				if(areaYaml.contains(args[0]))
				{
					SoundEffect.SP(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
					area.AreaGui areaGui = new area.AreaGui();
					areaGui.areaSettingGui(player, args[0]);
				}
				else
				{
					SoundEffect.SP(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					player.sendMessage("��c[SYSTEM] : �ش� �̸��� ������ �����ϴ�!");
				}
				return;
			}
		}
		if(args.length == 2)
		{
			area.AreaMain areaMain = new area.AreaMain();
			switch(args[1])
			{
			case "����" :
				if(main.MainServerOption.catchedLocation1.containsKey(player)&&main.MainServerOption.catchedLocation2.containsKey(player))
				{
					areaMain.CreateNewArea(player, main.MainServerOption.catchedLocation1.get(player), main.MainServerOption.catchedLocation2.get(player), args[0]);
					return;
				}
				else
				{
					event.EventInteract interact = new event.EventInteract();
				  	YamlLoader configYaml = new YamlLoader();
				  	configYaml.getConfig("config.yml");
					player.sendMessage("��c[SYSTEM] : ���� " + interact.SetItemDefaultName((short) configYaml.getInt("Server.AreaSettingWand"),(byte)0) +"��c �������� �տ� �� ä�� ����� ��/�� Ŭ���Ͽ� ������ ������ �ּ���!");
					SoundEffect.SP((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				}
				return;
			case "����" :
				areaMain.RemoveArea(player, args[0]);
				return;
			}
		}
		if(args.length <= 2)
		{
			helpMessage(player);
			return;
		}
		else
		{
			area.AreaMain areaMain = new area.AreaMain();
			StringBuilder sb = new StringBuilder();
			for(int a =2; a<= ((args.length)-1);a++)
			{
				sb.append(args[a]);
				sb.append(" ");
			}
			
			switch(args[1])
			{
			case "�̸�" :
				areaMain.OptionSetting(player, args[0],(char) 0, sb.toString());
				return;
			case "����" :
				areaMain.OptionSetting(player, args[0],(char) 1, sb.toString());
				return;
			}
		}
	}
	
	private void helpMessage(Player player)
	{
		player.sendMessage("��e������������������������[���� ���� ��ɾ�]������������������������");
		player.sendMessage("��6/���� ��ϡ�e - ���� ����� Ȯ���մϴ�.");
		player.sendMessage("��6/���� <���� �̸�>��e - �ش� ���� ����â�� ���ϴ�.");
		player.sendMessage("��6/���� <���� �̸�> ������e - �ش� �̸��� ���� ������ �����մϴ�.");
		player.sendMessage("��6/���� <���� �̸�> ������e - �ش� �̸��� ���� ������ �����մϴ�.");
		player.sendMessage("��6/���� <���� �̸�> �̸� <���ڿ�>��e - �ش� ������ �˸� �޽����� ���� �̸��� ���մϴ�.");
		player.sendMessage("��6/���� <���� �̸�> ���� <���ڿ�>��e - �ش� ������ �˸� �޽����� ���� �ΰ� ������ ���մϴ�.");
		player.sendMessage("��e����������������������������������������������������������������");
	}
}
