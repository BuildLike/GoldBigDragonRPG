package effect;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer;

import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutAnimation;
import net.minecraft.server.v1_12_R1.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutGameStateChange;
import net.minecraft.server.v1_12_R1.PacketPlayOutHeldItemSlot;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutOpenWindow;

public class SendPacket
{
	public void sendTitle(Player player, String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime)
	{
		player.sendTitle(title, subtitle, fadeInTime*20, showTime*20, fadeOutTime*20);
	}

	public void sendTitleAll(String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime)
	{
		Object[] players = Bukkit.getOnlinePlayers().toArray();
		for(int count = 0; count < players.length; count++)
			((Player)players[count]).sendTitle(title, subtitle, fadeInTime*20, showTime*20, fadeOutTime*20);
	}
	
	public void sendActionBar(Player p, String msg, boolean isAllPlayer)
	{
		IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \""  +msg+  "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,  ChatMessageType.a((byte)2));
		if(isAllPlayer)
			((CraftServer) p.getServer()).getHandle().sendAll(ppoc);
		else
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoc);
	}

	public void switchHotbarSlot(Player p, int slot)
	{
		PacketPlayOutHeldItemSlot ppoc = new PacketPlayOutHeldItemSlot(slot);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoc);
	}

	public void testPacket(Player p)
	{
		/*
			minecraft:chest
			minecraft:crafting_table
			minecraft:furnace
			minecraft:dispenser
			minecraft:enchanting_table
			minecraft:brewing_stand
			minecraft:villager
			minecraft:beacon
			minecraft:anvil
			minecraft:hopper
			minecraft:dropper
			EntityHorse
		 */
		Packet slotChange = new PacketPlayOutOpenWindow(1, "PLAYER",IChatBaseComponent.ChatSerializer.a("�κ��丮"), 63, 0);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(slotChange);
	}
	
	public void SpawnHallucinations(Player player, Player Hallucination)
	{
		PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(((CraftPlayer)Hallucination).getHandle());
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(spawn);
	}
	
	public void playerAnimation(Player player, byte Type)
	{
		/*
		Type
		0 = ������ ��� ���
		1 = �Ѵ� ���� ���
		2 = ?
		3 = �޼� ��� ���
		4 = ũ��Ƽ�� ����Ʈ
		5 = ��æƮ Ÿ�� ����Ʈ
		 */
		CraftPlayer cp = ((CraftPlayer)player);
		PacketPlayOutAnimation packet = new PacketPlayOutAnimation(cp.getHandle(), Type);
		cp.getHandle().playerConnection.sendPacket(packet);
	}
	
	public void playerGameStateChange(Player player, byte type1, float type2)
	{
		/*
		type1
		0 = ħ�� ���ư���
		1 = ?
		2 = ?
		3 = ���Ӹ�� �����ϴ� �� ó�� ���̰� �ϱ�
			type2
			0 = �����̹���ô
			1 = ũ������Ƽ����ô
			2 = ����ó��ô
			3 = ��������ô
			
		4 = ���� ���� �̵� ����
			type2
			0 = ���� ���� �ҷ������� �߰� �ϱ�
			0�ʰ����� 2 �̸����� = ���� ũ����
		5 = ���̵� ��Ŷ
			type2
			0 ü���� GUI ����
			101 = WASD�� �̵� �� �� �ֽ��ϴ�. ���콺�� ���� ���ñ� ����
			102 = Space�� �����ϼ���
			103 = E�� ���� �κ��丮�� �� �� �ֽ��ϴ�.
		7 = ��ũ�� ȭ�� ���� ����
			type2
			0 world�� �Ϲ� ���
			0.2 ���� įį
			0.3 �� įį
			1 ���ڻ�
			. (type2���� ���� �� �ٲ�)
			..
		10 = ���� ����� ȿ��
		 */
		PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(type1, type2);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
	

	public void sendCustomPacketOnlinePlayers(Player player, byte value)
	{
		/*
		0�� �Ϲ�
		1�� ��Ÿ�� ȿ��
		32�� ���� ȿ��
		64�� Glowȿ��
		96�� ���� Glowȿ��
		128�� ��Ʈ�� ���� ȿ��
		*/
		
		PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
		packet.getIntegers().write(0, player.getEntityId());
		WrappedDataWatcher watcher = new WrappedDataWatcher();
		Serializer serializer = Registry.get(Byte.class);
		watcher.setEntity(player);
		watcher.setObject(0, serializer, value);
		packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
		
		Iterator<Player> targetList = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
		try
		{	
			while(targetList.hasNext())
				ProtocolLibrary.getProtocolManager().sendServerPacket(targetList.next(), packet);
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}
	
	public void customPacket(Player player, ArrayList<LivingEntity> target, byte value)
	{
		/*
			0�� �Ϲ�
			1�� ��Ÿ�� ȿ��
			32�� ���� ȿ��
			64�� Glowȿ��
			96�� ���� Glowȿ��
			128�� ��Ʈ�� ���� ȿ��
		 */
		PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
		for(int count = 0; count < target.size(); count++)
		{
			packet.getIntegers().write(0, target.get(count).getEntityId());
			WrappedDataWatcher watcher = new WrappedDataWatcher();
			Serializer serializer = Registry.get(Byte.class);
			watcher.setEntity(target.get(count));
			watcher.setObject(0, serializer, value);
			packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
			try {
				ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void blockBreakAnimation(Player player, Location loc, int damage)
	{
		//damage 1 : ���� ����, 9 : ���� ���� �μ��� ȿ��
		Block b = loc.getBlock();
		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(b.getX(), b.getY(), b.getZ()), damage);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		((CraftServer) player.getServer()).getHandle().sendAll(packet);
	}
}
