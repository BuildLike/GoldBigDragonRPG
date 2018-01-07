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
			SoundEffect.playSound((Player)talker, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			return;
		}
		if(args.length > 0)
		{
			if(args.length == 1)
			{
				if(args[0].equals("���"))
				{
					area.AreaGui areaGui = new area.AreaGui();
					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
					areaGui.areaListGui(player, (short) 0);
					return;
				}
				else
				{
				  	YamlLoader areaYaml = new YamlLoader();
					areaYaml.getConfig("Area/AreaList.yml");
					
					if(areaYaml.contains(args[0]))
					{
						SoundEffect.playSound(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
						area.AreaGui areaGui = new area.AreaGui();
						areaGui.areaSettingGui(player, args[0]);
					}
					else
					{
						SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
						player.sendMessage("��c[SYSTEM] : �ش� �̸��� ������ �����ϴ�!");
					}
					return;
				}
			}
			else if(args.length == 2)
			{
				for(int count = 0; count < 10; count++)
				{
					if(args[0].contains("."))
						args[0] = args[0].replace('.', '_');
					if(args[0].contains(":"))
						args[0] = args[0].replace(':', '_');
					if(args[0].contains("["))
						args[0] = args[0].replace('[', '_');
					if(args[0].contains("]"))
						args[0] = args[0].replace(']', '_');
					if(args[0].contains("\\"))
						args[0] = args[0].replace('\\', '_');
					if(args[0].contains("-"))
						args[0] = args[0].replace('-', '_');
				}
				area.AreaMain areaMain = new area.AreaMain();
				if(args[1].equals("����"))
				{
					if(main.MainServerOption.catchedLocation1.containsKey(player)&&main.MainServerOption.catchedLocation2.containsKey(player))
					{
						areaMain.CreateNewArea(player, main.MainServerOption.catchedLocation1.get(player), main.MainServerOption.catchedLocation2.get(player), args[0]);
					}
					else
					{
						event.EventInteract interact = new event.EventInteract();
					  	YamlLoader configYaml = new YamlLoader();
					  	configYaml.getConfig("config.yml");
						player.sendMessage("��c[SYSTEM] : ���� " + interact.setItemDefaultName(configYaml.getInt("Server.AreaSettingWand"), 0) +"��c �������� �տ� �� ä�� ����� ��/�� Ŭ���Ͽ� ������ ������ �ּ���!");
						SoundEffect.playSound((Player)player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
					}
				}
				else if(args[1].equals("����"))
					areaMain.RemoveArea(player, args[0]);
			}
			else if(args.length <= 2)
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
				if(args[1].equals("�̸�"))
					areaMain.OptionSetting(player, args[0],(char) 0, sb.toString());
				else if(args[1].equals("����"))
					areaMain.OptionSetting(player, args[0],(char) 1, sb.toString());
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
