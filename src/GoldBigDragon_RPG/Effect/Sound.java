package GoldBigDragon_RPG.Effect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Sound
{
    //�ش� �÷��̾�� �Ҹ��� �����ϴ� �޼ҵ�//
	public void SP(Player player, org.bukkit.Sound sound, float volume,float pitch)
	{
    	if(Bukkit.getOfflinePlayer(player.getName()).isOnline() == true)
		player.playSound(player.getLocation(), sound ,volume, pitch);
	}
	
    //�ش� �÷��̾�� �ش� ��ġ���� �Ҹ��� �����ϴ� �޼ҵ�//
	public void SPL(Player player, Location loc, org.bukkit.Sound sound, float volume,float pitch)
	{
    	if(Bukkit.getOfflinePlayer(player.getName()).isOnline() == true)
		player.playSound(loc, sound ,volume, pitch);
	}
	
	//�ش� ��ġ�� ���带 �����ϴ� �޼ҵ�//
	public void SL(Location loc, org.bukkit.Sound sound, float volume,float pitch)
	{
		World world = loc.getWorld();
		world.playSound(loc, sound ,volume, pitch);
	}
}
