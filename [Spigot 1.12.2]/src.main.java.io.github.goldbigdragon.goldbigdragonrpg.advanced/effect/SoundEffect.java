package effect;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SoundEffect
{
    //�ش� �÷��̾�� �Ҹ��� �����ϴ� �޼ҵ�//
	public static void SP(Player player, org.bukkit.Sound sound, float volume,float pitch)
	{
    	if(player.isOnline() == true)
    		player.playSound(player.getLocation(), sound ,volume, pitch);
	}
	
    //�ش� �÷��̾�� �ش� ��ġ���� �Ҹ��� �����ϴ� �޼ҵ�//
	public static void SPL(Player player, Location loc, org.bukkit.Sound sound, float volume,float pitch)
	{
    	if(player.isOnline() == true)
		player.playSound(loc, sound ,volume, pitch);
	}
	
	//�ش� ��ġ�� ���带 �����ϴ� �޼ҵ�//
	public static void SL(Location loc, org.bukkit.Sound sound, float volume,float pitch)
	{
		World world = loc.getWorld();
		world.playSound(loc, sound ,volume, pitch);
	}

}
