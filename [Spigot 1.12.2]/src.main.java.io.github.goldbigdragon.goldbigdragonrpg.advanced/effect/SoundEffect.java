package effect;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SoundEffect
{
    //�ش� �÷��̾�� �Ҹ��� �����ϴ� �޼ҵ�//
	public static void playSound(Player player, org.bukkit.Sound sound, float volume,float pitch)
	{
    	if(player.isOnline())
    		player.playSound(player.getLocation(), sound ,volume, pitch);
	}
	
    //�ش� �÷��̾�� �ش� ��ġ���� �Ҹ��� �����ϴ� �޼ҵ�//
	public static void playSound(Player player, Location loc, org.bukkit.Sound sound, float volume,float pitch)
	{
    	if(player.isOnline())
    		player.playSound(loc, sound ,volume, pitch);
	}
	
	//�ش� ��ġ�� ���带 �����ϴ� �޼ҵ�//
	public static void playSoundLocation(Location loc, org.bukkit.Sound sound, float volume,float pitch)
	{
		World world = loc.getWorld();
		world.playSound(loc, sound ,volume, pitch);
	}

}
