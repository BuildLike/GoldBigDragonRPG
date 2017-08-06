package warp;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import effect.SoundEffect;

public class Warp_Command
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
		Player player = (Player) talker;
		if(args.length >= 1)
		{
			warp.Warp_Main TP = new warp.Warp_Main();
			switch(args[0])
			{
			case "���" :
				{
					SoundEffect.SP(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					new warp.Warp_GUI().WarpListGUI(player, 0);
				}
				return;
			case "���" : 
				{
					if(args.length >= 2)
						TP.CreateNewTeleportSpot(player, args[1]);
					else
						HelpMessage(player);
				}
				return;
			case "����" :
				{
					if(args.length >= 2)
						TP.RemoveTeleportList(player, args[1]);
					else
						HelpMessage(player);
				}
				return;
			case "����" :
				{
					if(args.length >= 2)
						TP.setTeleportPermission(player, args[1]);
					else
						HelpMessage(player);
				}
				return;
			default :
				{
					TP.TeleportUser(player, args[0]);
				}
				return;
			}
		}
		else
		{
			HelpMessage(player);
		}
	}
	
	private void HelpMessage(Player player)
	{
		player.sendMessage(ChatColor.YELLOW+"������������������������[�ڷ���Ʈ ��ɾ�]������������������������");
		player.sendMessage(ChatColor.GOLD + "/���� ���" + ChatColor.YELLOW + " - ���� ���� ���� ����� ���ϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� <���� �̸�>" + ChatColor.YELLOW + " - �ش� �̸����� ��ϵ� �������� �̵��մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� ��� <���� �̸�>" + ChatColor.YELLOW + " - ���� ��ġ�� ���� �������� ����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� ���� <���� �̸�>" + ChatColor.YELLOW + " - �ش� ���� ������ �����մϴ�.");
		player.sendMessage(ChatColor.GOLD + "/���� ���� <���� �̸�>" + ChatColor.YELLOW + " - �Ϲ� ������ �̵� ����/�Ұ��� �ϵ��� �����մϴ�.");
		player.sendMessage(ChatColor.YELLOW+"����������������������������������������������������������������");
	}
}
