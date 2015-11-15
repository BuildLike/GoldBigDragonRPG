package GBD.GoldBigDragon_Advanced.Command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends HelpMessage
{
	public void onCommand(CommandSender talker, Command command, String string, String[] args)
	{
		Player player = (Player) talker;
		if(args.length >= 1)
		{
			GBD.GoldBigDragon_Advanced.ETC.Teleport TP = new GBD.GoldBigDragon_Advanced.ETC.Teleport();
			switch(args[0])
			{
			case "���" :
				{
					new GBD.GoldBigDragon_Advanced.Effect.Sound().SP(player, Sound.ITEM_PICKUP, 0.8F, 1.0F);
					new GBD.GoldBigDragon_Advanced.GUI.WarpGUI().WarpListGUI(player, 0);
				}
				return;
			case "���" : 
				{
					if(args.length >= 2)TP.CreateNewTeleportSpot(player, args[1]); else HelpMessager(player, 5);
				}
				return;
			case "����" :
				{
					if(args.length >= 2)
						TP.RemoveTeleportList(player, args[1]);
					else
						HelpMessager(player, 5);
				}
				return;
			case "����" :
				{
					if(args.length >= 2)
						TP.setTeleportPermission(player, args[1]);
					else
						HelpMessager(player, 5);
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
			HelpMessager(player, 5);
		}
	}
}
