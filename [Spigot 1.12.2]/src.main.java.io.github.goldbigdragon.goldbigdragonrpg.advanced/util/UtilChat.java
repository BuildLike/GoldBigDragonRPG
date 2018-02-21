package util;

import org.bukkit.entity.Player;

import effect.SoundEffect;

public class UtilChat
{
	public boolean isIntMinMax(String message,Player player, int min, int max)
	{
		try
		{
			if(message.split(" ").length <= 1 && Integer.parseInt(message) >= min&& Integer.parseInt(message) <= max)
				return true;
			else
			{
				player.sendMessage("��c[SYSTEM] : �ּ� ��e"+min+"��c, �ִ� ��e"+max+"��c ������ ���ڸ� �Է��ϼ���!");
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
		}
		catch(NumberFormatException e)
		{
			player.sendMessage("��c[SYSTEM] : ���� ������ ��(����)�� �Է��ϼ���. (��e"+min+"��c ~ ��e"+max+"��c)");
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
		return false;
	}
	
	public byte askOX(String message,Player player)
	{
		if(message.split(" ").length <= 1)
		{
			if(message.equals("x")||message.equals("X")||message.equals("�ƴϿ�"))
				return 0;
			else if(message.equals("o")||message.equals("O")||message.equals("��"))
				return 1;
			else
			{
				player.sendMessage("��c[SYSTEM] : O Ȥ�� X�� �Է� �� �ּ���!");
				SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
			}
			
		}
		else
		{
			player.sendMessage("��c[SYSTEM] : O Ȥ�� X�� �Է� �� �ּ���!");
			SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
		}
		return -1;
	}
}
