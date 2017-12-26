package warp;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import effect.SoundEffect;

public class WarpCommand
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
		Player player = (Player) talker;
		if(args.length >= 1)
		{
			warp.WarpMain TP = new warp.WarpMain();
			switch(args[0])
			{
			case "���" :
				{
					SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 0.8F, 1.0F);
					new warp.WarpGui().warpListGUI(player, 0);
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
		player.sendMessage("��e������������������������[�ڷ���Ʈ ��ɾ�]������������������������");
		player.sendMessage("��6/���� ��ϡ�e - ���� ���� ���� ����� ���ϴ�.");
		player.sendMessage("��6/���� <���� �̸�>��e - �ش� �̸����� ��ϵ� �������� �̵��մϴ�.");
		player.sendMessage("��6/���� ��� <���� �̸�>��e - ���� ��ġ�� ���� �������� ����մϴ�.");
		player.sendMessage("��6/���� ���� <���� �̸�>��e - �ش� ���� ������ �����մϴ�.");
		player.sendMessage("��6/���� ���� <���� �̸�>��e - �Ϲ� ������ �̵� ����/�Ұ��� �ϵ��� �����մϴ�.");
		player.sendMessage("��e����������������������������������������������������������������");
	}
}
