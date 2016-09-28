package GBD_RPG.Structure;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import GBD_RPG.Util.Util_GUI;

public class Struct_CampFire extends Util_GUI
{

	public void CampFireMainGUI(Player player, String BoardCode)
	{
		BoardCode = BoardCode.replace("��", "&");
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED +""+ChatColor.BOLD +""+ "��ں�");
		Stack2(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"�� ����", 326, 0, 1, Arrays.asList(ChatColor.WHITE+"��ں��� ���� ���ϴ�."), 3, inv);
		Stack2(ChatColor.RED+""+ChatColor.BOLD+"�� ���Ǳ�", 259, 0, 1, Arrays.asList(ChatColor.WHITE+"��ںҿ� ���� ���̴ϴ�.","",ChatColor.YELLOW+"[����� 10�� �ʿ�]",ChatColor.BLACK+BoardCode), 5, inv);
		player.openInventory(inv);
		return;
	}
	
	public String CreateCampFire(int LineNumber, String StructureCode, byte Direction)
	{
		if(LineNumber <= 36)
			return "/summon ArmorStand ~0 ~0.36 ~0 {CustomName:\""+StructureCode+"\",Invisible:1,ShowArms:1,NoBasePlate:1,NoGravity:1,Rotation:["+(LineNumber*10)+"f,0.0f],HandItems:[{id:stick,Count:1},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[40f,90f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}";
		else if(LineNumber == 37)
			return "/summon ArmorStand ~0 ~0.56 ~0 {CustomName:\""+StructureCode+"\",Invisible:1,ShowArms:1,NoBasePlate:1,NoGravity:1}";
		else if(LineNumber == 38)
			return "/summon ArmorStand ~0 ~0.56 ~0 {CustomName:\""+StructureCode+"\",CustomNameVisible:1,Invisible:1,ShowArms:1,NoBasePlate:1,NoGravity:1}";
		else
			return "null";
	}
	
	public void CampFireGUIClick(InventoryClickEvent event)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		Player player = (Player) event.getWhoClicked();
		String CampFireName = ChatColor.stripColor(event.getInventory().getItem(5).getItemMeta().getLore().get(3)).replace("&", "��");
		
		if(event.getSlot()==3||event.getSlot()==5)
		{
			if(event.getSlot()==5)
			{
				ItemStack item = new MaterialData(280, (byte) 0).toItemStack(1);
				if(new GBD_RPG.Util.Util_Player().deleteItem(player, item, 10)==false)
				{
					s.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage(ChatColor.RED+"[SYSTEM] : ���� ���Ǳ� ���� �ʿ��� ����� ������ ���ڶ��ϴ�!");
					return;
				}
				else
					s.SP(player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.8F);
			}
			else
				s.SP(player, Sound.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
				
			Object[] e = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 6, 6, 6).toArray();
			for(int count = 0; count < e.length; count++)
				if(((Entity)e[count]).getType()==EntityType.ARMOR_STAND)
					if(((Entity)e[count]).getCustomName()!=null)
						if(((Entity)e[count]).getCustomName().compareTo(CampFireName)==0)
							{
								if(event.getSlot()==3)
									((Entity)e[count]).setFireTicks(0);
								if(event.getSlot()==5)
									((Entity)e[count]).setFireTicks(25565);
							}
		}
	}
}
