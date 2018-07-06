package event;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.*;

import area.gui.AreaBlockItemSettingGui;
import customitem.CustomItemAPI;
import effect.SoundEffect;
import net.citizensnpcs.api.CitizensAPI;
import user.UserDataObject;
import util.YamlLoader;

public class EventInteract
{
	//�� ��Ŭ/��Ŭ �� ��//
	public void PlayerInteract(PlayerInteractEvent event)
	{
		if(event.getHand()==EquipmentSlot.HAND)
		{
			if(new corpse.CorpseAPI().deathCapture(event.getPlayer(),false))
				return;
			if(event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK)
				ClickTrigger(event);
			if(event.getPlayer().getInventory().getItemInMainHand()!=null&&event.getPlayer().isOp())
				AreaChecker(event);

			if(event.getPlayer().isOp())
				OPwork(event);

			if(event.getPlayer().getWorld().getName().equals("Dungeon"))
			{
				new dungeon.DungeonMain().dungeonInteract(event);
				return;
			}

			if(event.getAction()==Action.RIGHT_CLICK_BLOCK&&event.getClickedBlock()!=null)
			{
				short id = (short) event.getClickedBlock().getTypeId();
				if(id==54||id==58||id==61||id==84||id==116||id==120||id==130||id==145||id==146
					||id==321||id==355||id==389||id==416||id==23||id==25||id==84||id==69||id==84
					||id==46||id==77||id==84||id==96||id==107||id==143||id==151||id==154||id==158
					||id==167||id==84||(id>=183&&id<=187)||id==324||id==330||id==356||id==404||(id>=427&&id<=431)
					||id==138)
				{
					area.AreaAPI A = new area.AreaAPI();
					String[] Area = A.getAreaName(event.getClickedBlock());
					if(Area != null)
					{
						if(A.getAreaOption(Area[0], (char) 7) == false && event.getPlayer().isOp() == false)
						{
							event.setCancelled(true);
							SoundEffect.playSound(event.getPlayer(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
							event.getPlayer().sendMessage("��c[SYSTEM] : ��e"+ Area[1] + "��c ������ �ִ� ����� �� �� �������ϴ�!");
							return;
						}
					}
				}
				if(event.getItem()!=null)
				if(event.getItem().getTypeId()>=325&&event.getItem().getTypeId()<=327)
				{
					area.AreaAPI A = new area.AreaAPI();
					String[] Area = A.getAreaName(event.getClickedBlock());
					if(Area != null)
					{
						if(A.getAreaOption(Area[0], (char) 7) == false && event.getPlayer().isOp() == false)
						{
							event.setCancelled(true);
							SoundEffect.playSound(event.getPlayer(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
							event.getPlayer().sendMessage("��c[SYSTEM] : ��e"+ Area[1] + "��c ���������� �絿�̸� ����� �������ϴ�!");
							return;
						}
					}
				}
			}
			return;
		}
	}
	
	//NPC �� ����
	public void PlayerInteractEntity (PlayerInteractEntityEvent event)
	{
		if(event.getHand()==EquipmentSlot.HAND)
		{
			Entity target = event.getRightClicked();
			Player player = event.getPlayer();

			if(target.getType() == EntityType.PLAYER)
			{
				if(main.MainServerOption.PlayerList.get(player.getUniqueId().toString()).isOption_SeeInventory())
				{
					Player t = (Player)target;
					if(t.isOnline())
					{
						user.EquipGui EGUI = new user.EquipGui();
						EGUI.EquipWatchGUI(player, t);
						return;
					}
				}
			}
		    if(player.isOp())
		    {
		    	String type = new UserDataObject().getType(player);
		    	if(type!=null && new UserDataObject().getType(player).equals("Quest"))
	    			new quest.QuestInteractEvent().EntityInteract(event, type);
		    }

			String[] area = new area.AreaAPI().getAreaName(target);
			if(area != null)
			{
				if( ! new area.AreaAPI().getAreaOption(area[0], (char) 7) && ! event.getPlayer().isOp())
				{
					if(target.getCustomName() == null || CitizensAPI.getNPCRegistry().getNPC(target) == null)
					{
						event.setCancelled(true);
						SoundEffect.playSound(event.getPlayer(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
						event.getPlayer().sendMessage("��c[SYSTEM] : ��e"+ area[1] + "��c ������ �ִ� ��ƼƼ�� �� �� �������ϴ�!");
						return;
					}
				}
			}
		    return;
		}
	}
	
	public void ClickTrigger(PlayerInteractEvent event)
	{
		if(event.getPlayer().getInventory().getItemInMainHand()!=null)
			ItemUse(event);
		if(event.getClickedBlock()!=null)
			SlotMachine(event);
	}
	
	private void OPwork(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		UserDataObject u = new UserDataObject();
		if(u.getType(player) != null)
		{
			if(u.getType(player).equals("Quest"))
				new quest.QuestInteractEvent().BlockInteract(event);
			else if(u.getType(player).equals("Area"))
			    OPwork_Area(event);
			else if(u.getType(player).equals("Gamble"))
			    OPwork_Gamble(event);
		}
		return;
	}
	
	private void ItemUse(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if(player.getInventory().getItemInMainHand()!=null)
		if(player.getInventory().getItemInMainHand().hasItemMeta())
		{
			if(player.getInventory().getItemInMainHand().getItemMeta().hasLore())
			{
				int itemID = player.getInventory().getItemInMainHand().getData().getItemTypeId();
				if(itemID == 383 && event.getItem().getData().getData() == 0 && event.getAction() == Action.RIGHT_CLICK_BLOCK)
				{
	    			if(main.MainServerOption.MonsterList.containsKey(ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName())))
	    			{
	    				if(player.isOp())
	    					new monster.MonsterSpawn().SpawnMob(event.getClickedBlock().getLocation(), ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName()), (byte)-1, null, (char) -1, false);
	    				else
	    				{
	    					SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
	    			    	player.sendMessage("��c[SYSTEM] : ���� ���� ������ �����ϴ�!");
	    				}
	    			}
					else
					{
						SoundEffect.playSound(player, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0F, 1.7F);
				    	player.sendMessage("��c[SYSTEM] : �ش� �̸��� ���Ͱ� �������� �ʽ��ϴ�!");
					}
			    	return;
				}
				//���� ���
				else if(itemID == 395 || itemID == 358)
				{
					Object[] lore = player.getInventory().getItemInMainHand().getItemMeta().getLore().toArray();
					for(int counter = 0; counter < lore.length; counter ++)
						if(lore[counter].toString().contains("����"))
						{
							UserDataObject u = new UserDataObject();
							//������ �̹��� �ִ� �۾�
							if(player.isOp())
							{
								u.setType(player, "Map");
								u.setString(player, (byte)1, ChatColor.stripColor(lore[counter].toString()).split(" : ")[1]);
								main.MainServerOption.Mapping = true;
								return;
							}
						}
				}
				String LoreString = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).toString();
				if(LoreString.contains("[��ȯ��]")||LoreString.contains("[�ֹ���]")||
				   LoreString.contains("[��ų��]")||LoreString.contains("[�Һ�]")||
				   LoreString.contains("[��]")||LoreString.contains("[����]"))
				{
					event.setCancelled(true);
					if(LoreString.contains("[�Һ�]"))
						new CustomItemAPI().useAbleItemUse(player, "�Һ�");
					else if(LoreString.contains("[��ȯ��]"))
						new CustomItemAPI().useAbleItemUse(player, "��ȯ��");
					else if(LoreString.contains("[�ֹ���]"))
						new CustomItemAPI().useAbleItemUse(player, "�ֹ���");
					else if(LoreString.contains("[��ų��]"))
						new CustomItemAPI().useAbleItemUse(player, "��ų��");
					else if(LoreString.contains("[��]"))
						new CustomItemAPI().useAbleItemUse(player, "��");
					else if(LoreString.contains("[����]"))
						new CustomItemAPI().useAbleItemUse(player, "����");
					return;
				}
			}
		}
		return;
	}
	
	private void SlotMachine(PlayerInteractEvent event)
	{
		Block block = event.getClickedBlock();
	  	YamlLoader gambleYaml = new YamlLoader();
		gambleYaml.getConfig("ETC/SlotMachine.yml");
		String BlockLocation = block.getLocation().getWorld().getName()+"_"+(int)block.getLocation().getX()+","+(short)block.getLocation().getY()+","+(int)block.getLocation().getZ();
		if(gambleYaml.contains(BlockLocation))
		{
			event.setCancelled(true);
			new admin.GambleGui().slotMachinePlayGui(event.getPlayer(), BlockLocation);
		}
		return;
	}
	
	
	private void OPwork_Area(PlayerInteractEvent event)
	{
		event.setCancelled(true);
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
	  	YamlLoader areaYaml = new YamlLoader();
		areaYaml.getConfig("Area/AreaList.yml");
		
		UserDataObject u = new UserDataObject();

		String AreaName = u.getString(player, (byte)2);
		if(event.getAction()==Action.LEFT_CLICK_BLOCK)
		{
			if(u.getString(player, (byte)3).equals("ANBI"))
			{
				String BlockData = block.getTypeId()+":"+block.getData();
				ItemStack item = new MaterialData(block.getTypeId(), (byte) block.getData()).toItemStack(1);
				areaYaml.set(AreaName+".Mining."+BlockData+".100",item);
				areaYaml.saveConfig();
				SoundEffect.playSound(player, Sound.ENTITY_HORSE_SADDLE, 1.0F, 1.8F);
				new AreaBlockItemSettingGui().areaBlockItemSettingGui(player, AreaName, BlockData);
		    	u.clearAll(player);
			}
		}
		else if(event.getAction()==Action.RIGHT_CLICK_BLOCK)
		{
			if(u.getString(player, (byte)3).equals("MLS"))//MonsterLocationSetting
			{
				String count = u.getString(player, (byte)1);
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".loc.world", block.getLocation().getWorld().getName());
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".loc.x", (int)block.getLocation().getX());
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".loc.y", (short)block.getLocation().getY()+1);
				areaYaml.set(AreaName+".MonsterSpawnRule."+count+".loc.z", (int)block.getLocation().getZ());
				areaYaml.saveConfig();
				SoundEffect.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F);
		    	u.clearAll(player);
				u.setType(player, "Area");
				u.setString(player, (byte)1, count);
				u.setString(player, (byte)2, "AMSC");
				u.setString(player, (byte)3, AreaName);
				player.sendMessage("��a[����] : �� ���� �� ���� �� ���� �ұ��?");
				player.sendMessage("��e(�ּ� 1���� ~ �ִ� 100����)");
			}
		}
		return;
	}
	
	private void OPwork_Gamble(PlayerInteractEvent event)
	{
		event.setCancelled(true);
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
	  	YamlLoader gambleYaml = new YamlLoader();
		gambleYaml.getConfig("ETC/SlotMachine.yml");
		
		UserDataObject u = new UserDataObject();

		String AreaName = u.getString(player, (byte)2);
		if(event.getAction()==Action.LEFT_CLICK_BLOCK)
		{
			/*
			if(u.getString(player, (byte)3).equals("ANBI"))
			{
				String BlockData = block.getTypeId()+":"+block.getData();
				ItemStack item = new MaterialData(block.getTypeId(), (byte) block.getData()).toItemStack(1);
				AreaConfig.set(AreaName+".Mining."+BlockData,item);
				AreaConfig.saveConfig();
				GoldBigDragon_RPG.GUI.AreaGUI AGUI = new GoldBigDragon_RPG.GUI.AreaGUI();
				new GoldBigDragon_RPG.Effect.Sound().SP(player, Sound.HORSE_SADDLE, 1.0F, 1.8F);
				AGUI.AreaBlockItemSettingGUI(player, AreaName, BlockData);
		    	u.clearAll(player);
			}
			*/
		}
		else if(event.getAction()==Action.RIGHT_CLICK_BLOCK)
		{
			if(u.getString(player, (byte)0).equals("NSM"))//NewSlotMachine
			{
				String Name = block.getLocation().getWorld().getName()+"_"+(int)block.getLocation().getX()+","+(short)block.getLocation().getY()+","+(int)block.getLocation().getZ();
				if(gambleYaml.contains(Name))
				{
					SoundEffect.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
					player.sendMessage("��c[����] : �ش� ��Ͽ��� �̹� �ٸ� ���� ��Ⱑ ��ġ�Ǿ� �ֽ��ϴ�!");
					return;
				}
				gambleYaml.set(Name+".0", "null");
				gambleYaml.set(Name+".1", "null");
				gambleYaml.set(Name+".2", "null");
				gambleYaml.set(Name+".3", "null");
				gambleYaml.set(Name+".4", "null");
				gambleYaml.set(Name+".5", "null");
				gambleYaml.set(Name+".6", "null");
				gambleYaml.set(Name+".8", "null");
				gambleYaml.set(Name+".9", "null");
				gambleYaml.set(Name+".10", "null");
				gambleYaml.set(Name+".11", "null");
				gambleYaml.set(Name+".12", "null");
				gambleYaml.set(Name+".13", "null");
				gambleYaml.set(Name+".14", "null");
				gambleYaml.set(Name+".15", "null");
				gambleYaml.saveConfig();
				SoundEffect.playSound(player, Sound.ENTITY_IRONGOLEM_DEATH, 1.0F, 1.8F);
		    	u.clearAll(player);
				player.sendMessage("��a[����] : ��谡 ��ġ �Ǿ����ϴ�!");
				new admin.GambleGui().slotMachineDetailGUI(player, Name);
			}
		}
		return;
	}
	
	private void AreaChecker(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
	  	YamlLoader configYaml = new YamlLoader();
		configYaml.getConfig("config.yml");
		if(player.getInventory().getItemInMainHand().getTypeId() == configYaml.getInt("Server.AreaSettingWand"))
		{
			event.setCancelled(true);
			if(event.getAction()==Action.LEFT_CLICK_BLOCK)
			{
				main.MainServerOption.catchedLocation1.put(player, block.getLocation());
				player.sendMessage("��e[SYSTEM] : ù ��° ���� ���� �Ϸ�! (" + block.getLocation().getBlockX() + ","
						+block.getLocation().getBlockY()+","+block.getLocation().getBlockZ()+")");
				return;
			}
			else if(event.getAction()==Action.RIGHT_CLICK_BLOCK)
			{
				main.MainServerOption.catchedLocation2.put(player, event.getClickedBlock().getLocation());
				player.sendMessage("��e[SYSTEM] : �� ��° ���� ���� �Ϸ�! (" + event.getClickedBlock().getLocation().getBlockX() + ","
						+event.getClickedBlock().getLocation().getBlockY()+","+event.getClickedBlock().getLocation().getBlockZ()+")");
				return;
			}
		}
		return;
	}
	
	public void PlayerGetItem(PlayerPickupItemEvent event)
	{
	  	if(main.MainServerOption.PlayerList.get(event.getPlayer().getUniqueId().toString()).isAlert_ItemPickUp())
	  	{
			String ItemName = null;
	  		if(event.getItem().getItemStack().hasItemMeta()&&event.getItem().getItemStack().getItemMeta().hasDisplayName())
	  			ItemName = event.getItem().getItemStack().getItemMeta().getDisplayName();
	  		else
				ItemName = setItemDefaultName((short) event.getItem().getItemStack().getTypeId(),event.getItem().getItemStack().getData().getData());
	  		new effect.SendPacket().sendActionBar(event.getPlayer(), "��7��l(��l"+ItemName+" ��7��l"+event.getItem().getItemStack().getAmount()+"��)", false);
	  	}
	  	return;
	}

	public String setItemDefaultName(int itemCode, int itemData)
	{
		String name = "�������� ���� ������";
		switch (itemCode)
		{
			case 1:
				switch(itemData)
				{
				case 0:
					return "��7��l��";
				case 1:
					return "��c��lȭ����";
				case 2:
					return "��c��l�ε巯�� ȭ����";
				case 3:
					return "��f��l���Ͼ�";
				case 4:
					return "��f��l�ε巯�� ���Ͼ�";
				case 5:
					return "��7��l�Ȼ��";
				case 6:
					return "��7��l�ε巯�� �Ȼ��";
				}
				break;
			case 2: return "��a��l�ܵ� ���";
			case 3:
				switch(itemData)
				{
				case 0:
					return "��6��l��";
				case 1:
					return "��6��l��ģ ��";
				case 2:
					return "��6��lȸ����";
				}
				break;
			case 4: return "��7��l���൹";
			case 5:
				switch(itemData)
				{
				case 0:
					return "��6��l������ ����";
				case 1:
					return "��0��l�����񳪹� ����";
				case 2:
					return "��f��l���۳��� ����";
				case 3:
					return "��6��l���� ���� ����";
				case 4:
					return "��6��l��ī�þƳ��� ����";
				case 5:
					return "��0��l£�� ������ ����";
				}
				break;
			case 6:
				switch(itemData)
				{
				case 0:
					return "��6��l������ ����";
				case 1:
					return "��0��l�����񳪹� ����";
				case 2:
					return "��f��l���۳��� ����";
				case 3:
					return "��6��l���� ���� ����";
				case 4:
					return "��6��l��ī�þƳ��� ����";
				case 5:
					return "��0��l£�� ������ ����";
				}
				break;
			case 7: return "��0��l��ݾ�";
			case 8: return "��9��l��";
			case 9: return "��9��l�帣�� �ʴ� ��";
			case 10: return "��c��l���";
			case 11: return "��c��l�帣�� �ʴ� ���";
			case 12:
				switch(itemData)
				{
				case 0:
					return "��e��l��";
				case 1:
					return "��c��l���� ��";
				}
				break;
			case 13: return "��7��l�ڰ�";
			case 14: return "��e��l�ݱ���";
			case 15: return "��7��lö����";
			case 16: return "��0��l��ź ����";
			case 17:
				switch(itemData%4)
				{
				case 0:
					return "��6��l������";
				case 1:
					return "��0��l�����񳪹�";
				case 2:
					return "��f��l���۳���";
				case 3:
					return "��6��l���� ����";
				}
				break;
			case 18:
				switch(itemData)
				{
				case 0:
					return "��a��l������ ��";
				case 1:
					return "��a��l�����񳪹� ��";
				case 2:
					return "��a��l���۳��� ��";
				case 3:
					return "��a��l���� ���� ��";
				}
				break;
			case 19:
				switch(itemData)
				{
				case 0:
					return "��e��l������";
				case 1:
					return "��9��l���� ������";
				}
				break;
			case 20: return "��f��l����";
			case 21: return "��9��lû�ݼ� ����";
			case 22: return "��9��lû�ݼ� ���";
			case 23: return "��7��l�߻��";
			case 24:
				switch(itemData)
				{
				case 0:
					return "��e��l���";
				case 1:
					return "��e��l������ ���";
				case 2:
					return "��e��l�ε巯�� ���";
				}
				break;
			case 25: return "��6��l��Ʈ ���";
			case 26: return "��c��l���丷 ħ��";
			case 27: return "��e��l�Ŀ� ����";
			case 28: return "��7��l������ ����";
			case 29: return "��a��l������ �ǽ���";
			case 30: return "��f��l�Ź���";
			case 31:
				switch(itemData)
				{
				case 1:
					return "��a��l�ܵ�";
				case 2:
					return "��a��l��縮";
				}
				break;
			case 32: return "��a��l���� ����";
			case 33: return "��6��l�ǽ���";
			case 34: return "��6��l�ǽ��� �Ӹ�";
			case 35:
				switch(itemData)
				{
				case 0:
					return "��f��l�� ����";
				case 1:
					return "��6��l��Ȳ�� ����";
				case 2:
					return "��d��l��ȫ�� ����";
				case 3:
					return "��b��l�ϴû� ����";
				case 4:
					return "��e��l����� ����";
				case 5:
					return "��a��l���λ� ����";
				case 6:
					return "��d��l��ȫ�� ����";
				case 7:
					return "��8��lȸ�� ����";
				case 8:
					return "��7��l���� ȸ�� ����";
				case 9:
					return "��9��lû�ϻ� ����";
				case 10:
					return "��5��l����� ����";
				case 11:
					return "��1��l�Ķ��� ����";
				case 12:
					return "��6��l���� ����";
				case 13:
					return "��2��l�ʷϻ� ����";
				case 14:
					return "��4��l������ ����";
				case 15:
					return "��0��l������ ����";
				}
				break;
			case 37: return "��e��l�ε鷹";
			case 38:
				switch(itemData)
				{
				case 0:
					return "��c��l��ͺ�";
				case 1:
					return "��b��l�Ķ� ����";
				case 2:
					return "��d��l�Ĳ�";
				case 3:
					return "��f��lǪ�� �����";
				case 4:
					return "��c��l������ ƫ��";
				case 5:
					return "��6��l��Ȳ�� ƫ��";
				case 6:
					return "��f��l�Ͼ�� ƫ��";
				case 7:
					return "��d��l��ȫ�� ƫ��";
				case 8:
					return "��f��l������";
				}
				break;
			case 39: return "��6��l���� ����";
			case 40: return "��c��l���� ����";
			case 41: return "��e��l�� ���";
			case 42: return "��f��lö ���";
			case 43: return "��7��l������ �� �� ���";
			case 44:
				switch(itemData%7)
				{
				case 0:
					return "��7��l�� �� ���";
				case 1:
					return "��e��l��� �� ���";
				case 3:
					return "��7��l���൹ �� ���";
				case 4:
					return "��c��l���� �� ���";
				case 5:
					return "��7��l���� ���� �� ���";
				case 6:
					return "��4��l�״� ���� �� ���";
				case 7:
					return "��f��l���� �� ���";
				}
				break;
			case 45: return "��c��l����";
			case 46: return "��4��lTNT";
			case 47: return "��6��lå��";
			case 48: return "��a��l�̳� �� ��";
			case 49: return "��0��l��伮";
			case 50: return "��e��lȶ��";
			case 51: return "��c��l��";
			case 52: return "��7��l���� ������";
			case 53: return "��6��l������ ���";
			case 54: return "��6��l����";
			case 55: return "��c��l���彺��";
			case 56: return "��b��l���̾Ƹ�� ����";
			case 57: return "��b��l���̾Ƹ�� ���";
			case 58: return "��6��l�۾���";
			case 59: return "��a��l���۹�";
			case 60: return "��6��l�� ���";
			case 61: return "��7��lȭ��";
			case 62: return "��e��l��Ÿ�� ȭ��";
			case 63: return "��6��lǥ����";
			case 64: return "��6��l��¦ ��";
			case 65: return "��6��l��ٸ�";
			case 66: return "��7��l����";
			case 67: return "��7��l���� ���";
			case 68: return "��6��l�پ��ִ� ǥ����";
			case 69: return "��7��l����";
			case 70: return "��7��l�� ������";
			case 71: return "��f��lö ��¦ ��";
			case 72: return "��6��l���� ������";
			case 73: return "��c��l���彺�� ����";
			case 74: return "��c��l������ ���彺�� ����";
			case 75: return "��7��l�� ���� ���彺�� ȶ��";
			case 76: return "��c��l���彺�� ȶ��";
			case 77: return "��7��l�� ��ư";
			case 78: return "��f��l��";
			case 79: return "��b��l����";
			case 80: return "��f��l�� ���";
			case 81: return "��2��l������";
			case 82: return "��7��l����";
			case 83: return "��a��l���� ����";
			case 84: return "��6��l��ũ �ڽ�";
			case 85: return "��6��l������ ��Ÿ��";
			case 86: return "��6��lȣ��";
			case 87: return "��d��l�״���";
			case 88: return "��6��l�ҿ� ����";
			case 89: return "��e��l�߱���";
			case 90: return "��5��l�״� ��Ż";
			case 91: return "��e��l�� �� ����";
			case 92: return "��f��l����ũ ���";
			case 93: return "��7��l�� ���� ���彺�� �߰��";
			case 94: return "��c��l���彺�� �߰��";
			case 95:
				switch(itemData)
				{
				case 0:
					return "��f��l�Ͼ�� ������ ����";
				case 1:
					return "��6��l��Ȳ�� ������ ����";
				case 2:
					return "��d��l��ȫ�� ������ ����";
				case 3:
					return "��b��l�ϴû� ������ ����";
				case 4:
					return "��e��l����� ������ ����";
				case 5:
					return "��a��l���λ� ������ ����";
				case 6:
					return "��d��l��ȫ�� ������ ����";
				case 7:
					return "��8��lȸ�� ������ ����";
				case 8:
					return "��7��l���� ȸ�� ������ ����";
				case 9:
					return "��9��lû�ϻ� ������ ����";
				case 10:
					return "��5��l����� ������ ����";
				case 11:
					return "��1��l�Ķ��� ������ ����";
				case 12:
					return "��6��l���� ������ ����";
				case 13:
					return "��2��l�ʷϻ� ������ ����";
				case 14:
					return "��4��l������ ������ ����";
				case 15:
					return "��0��l������ ������ ����";
				}
				break;
			case 96: return "��6��l�ٶ���";
			case 97:
				switch(itemData)
				{
				case 0:
					return "��7��l�� ���� ��";
				case 1:
					return "��7��l���൹ ���� ��";
				case 2:
					return "��7��l���� ���� ���� ��";
				case 3:
					return "��7��l�̳� �� ���� ���� ���� ��";
				case 4:
					return "��7��l�� �� ���� ���� ���� ��";
				case 5:
					return "��7��l������ ���� ���� ���� ��";
				}
				break;
			case 98:
				switch(itemData)
				{
				case 0:
					return "��7��l���� ����";
				case 1:
					return "��7��l�̳� �� ���� ����";
				case 2:
					return "��7��l�� �� ���� ����";
				case 3:
					return "��7��l������ ���� ����";
				}
				break;
			case 99: return "��6��l���� ���� ���� �� ���";
			case 100: return "��c��l���� ���� ���� �� ���";
			case 101: return "��7��löâ";
			case 102: return "��f��l������";
			case 103: return "��a��l���� ���";
			case 104: return "��a��lȣ�� ����";
			case 105: return "��a��l���� ����";
			case 106: return "��2��l����";
			case 107: return "��6��l������ ��Ÿ�� ��";
			case 108: return "��c��l���� ���";
			case 109: return "��7��l���� ���� ���";
			case 110: return "��d��l�ջ�ü";
			case 111: return "��2��l������";
			case 112: return "��5��l�״� ����";
			case 113: return "��5��l�״� ���� ��Ÿ��";
			case 114: return "��5��l�״� ���� ���";
			case 115: return "��d��l�״� ��Ʈ";
			case 116: return "��b��l��æƮ ���̺�";
			case 117: return "��e��l������";
			case 118: return "��7��l������";
			case 119: return "��0��l�������� ��Ż";
			case 120: return "��e��l���� ��Ż";
			case 121: return "��e��l���� ����";
			case 122: return "��0��l�巡�� ��";
			case 123: return "��6��l���彺�� ����";
			case 124: return "��e��l������ ���彺�� ����";
			case 125: return "��6��l������ ���� �� ���";
			case 126:
				switch(itemData%6)
				{
				case 0:
					return "��6��l������ �� ���";
				case 1:
					return "��0��l�����񳪹� �� ���";
				case 2:
					return "��f��l���۳��� �� ���";
				case 3:
					return "��6��l���� ���� �� ���";
				case 4:
					return "��6��l��ī�þƳ��� �� ���";
				case 5:
					return "��0��l£�� ������ �� ���";
				}
				break;
			case 127: return "��6��l���ھ� ����";
			case 128: return "��e��l��� ���";
			case 129: return "��a��l���޶��� ����";
			case 130: return "��0��l���� ����";
			case 131: return "��7��lö�� �� ����";
			case 132: return "��7��lö�� �� ���� ��";
			case 133: return "��a��l���޶��� ���";
			case 134: return "��6��l������ ���� ���";
			case 135: return "��6��l���� ���� ���";
			case 136: return "��6��l���� ���� ���";
			case 137: return "��c��lĿ��l��6��l�ǡ�l��e��l���l��2��l ���l��9��l��";
			case 138: return "��b��l��ȣ��";
			case 139: return "��7��l���൹ ����";
			case 140: return "��d��lȭ��";
			case 141: return "��a��l���۹�";
			case 142: return "��a��l���۹�";
			case 143: return "��6��l���� ��ư";
			case 144: return "��f��l�̽��׸� �ذ�";
			case 145:
				switch(itemData)
				{
				case 0:
					return "��7��l���";
				case 1:
					return "��7��l�ణ �ջ�� ���";
				case 2:
					return "��7��l�ɰ��ϰ� �ջ�� ���";
				}
				break;
			case 146: return "��c��l�� ����";
			case 147: return "��6��l���� ������(����)";
			case 148: return "��f��l���� ������(����)";
			case 149: return "��c��l���彺�� �񱳱�";
			case 150: return "��e��l������ ���彺�� �񱳱�";
			case 151: return "��c��l�޺� ������";
			case 152: return "��c��l���彺�� ���";
			case 153: return "��f��l�״� ���� ����";
			case 154: return "��7��l�򶧱�";
			case 155:
				switch(itemData)
				{
				case 0:
					return "��f��l���� ���";
				case 1:
					return "��f��l������ ���� ���";
				default:
					return "��f��l���� ��� ���";
				}
			case 156: return "��f��l���� ���";
			case 157: return "��7��lȰ��ȭ ����";
			case 158: return "��7��l���ޱ�";
			case 159:
				switch(itemData)
				{
				case 0:
					return "��f��l��� ������ ����";
				case 1:
					return "��6��l��Ȳ�� ������ ����";
				case 2:
					return "��d��l��ȫ�� ������ ����";
				case 3:
					return "��b��l�ϴû� ������ ����";
				case 4:
					return "��e��l����� ������ ����";
				case 5:
					return "��a��l���λ� ������ ����";
				case 6:
					return "��d��l��ȫ�� ������ ����";
				case 7:
					return "��8��lȸ�� ������ ����";
				case 8:
					return "��7��l���� ȸ�� ������ ����";
				case 9:
					return "��9��lû�ϻ� ������ ����";
				case 10:
					return "��5��l����� ������ ����";
				case 11:
					return "��1��l�Ķ��� ������ ����";
				case 12:
					return "��6��l���� ������ ����";
				case 13:
					return "��2��l�ʷϻ� ������ ����";
				case 14:
					return "��4��l������ ������ ����";
				case 15:
					return "��0��l������ ������ ����";
				}
				break;
			case 160:
				switch(itemData)
				{
				case 0:
					return "��f��l�Ͼ�� ������ ������";
				case 1:
					return "��6��l��Ȳ�� ������ ������";
				case 2:
					return "��d��l��ȫ�� ������ ������";
				case 3:
					return "��b��l�ϴû� ������ ������";
				case 4:
					return "��e��l����� ������ ������";
				case 5:
					return "��a��l���λ� ������ ������";
				case 6:
					return "��d��l��ȫ�� ������ ������";
				case 7:
					return "��8��lȸ�� ������ ������";
				case 8:
					return "��7��l���� ȸ�� ������ ������";
				case 9:
					return "��9��lû�ϻ� ������ ������";
				case 10:
					return "��5��l����� ������ ������";
				case 11:
					return "��1��l�Ķ��� ������ ������";
				case 12:
					return "��6��l���� ������ ������";
				case 13:
					return "��2��l�ʷϻ� ������ ������";
				case 14:
					return "��4��l������ ������ ������";
				case 15:
					return "��0��l������ ������ ������";
				}
				break;
			case 161:
				switch(itemData)
				{
				case 0:
					return "��a��l��ī�þ� ��";
				case 1:
					return "��a��l£�� ������ ��";
				}
				break;
			case 162:
				switch(itemData%2)
				{
				case 0:
					return "��6��l��ī�þ� ����";
				case 1:
					return "��0��l£�� ������";
				}
				break;
			case 163: return "��6��l��ī�þ� ���� ���";
			case 164: return "��6��l£�� ������ ���";
			case 165: return "��a��l������ ���";
			case 166: return "��f��l�ٸ�����Ʈ(������)";
			case 167: return "��f��lö �ٶ���";
			case 168:
				switch(itemData)
				{
				case 0:
					return "��b��l�������";
				case 1:
					return "��b��l������� ����";
				case 2:
					return "��9��l��ο� ������� ����";
				}
				break;
			case 169: return "��b��l�ٴ� ����";
			case 170: return "��6��l���� ����";
			case 171:
				switch(itemData)
				{
				case 0:
					return "��f��l��� ��ź��";
				case 1:
					return "��6��l��Ȳ�� ��ź��";
				case 2:
					return "��d��l��ȫ�� ��ź��";
				case 3:
					return "��b��l�ϴû� ��ź��";
				case 4:
					return "��e��l����� ��ź��";
				case 5:
					return "��a��l���λ� ��ź��";
				case 6:
					return "��d��l��ȫ�� ��ź��";
				case 7:
					return "��8��lȸ�� ��ź��";
				case 8:
					return "��7��l���� ȸ�� ��ź��";
				case 9:
					return "��9��lû�ϻ� ��ź��";
				case 10:
					return "��5��l����� ��ź��";
				case 11:
					return "��1��l�Ķ��� ��ź��";
				case 12:
					return "��6��l���� ��ź��";
				case 13:
					return "��2��l�ʷϻ� ��ź��";
				case 14:
					return "��4��l������ ��ź��";
				case 15:
					return "��0��l������ ��ź��";
				}
				break;
			case 172: return "��7��l���� ����";
			case 173: return "��0��l��ź ���";
			case 174: return ChatColor.MAGIC+"��l�ܴ��� ����";
			case 175:
				switch(itemData)
				{
				case 0:
					return "��e��l�عٶ��";
				case 1:
					return "��d��l���϶�";
				case 2:
					return "��2��lū �ܵ�";
				case 3:
					return "��b��lū ��縮";
				case 4:
					return "��c��l��� ����";
				case 5:
					return "��d��l���";
				}
				break;
			case 176: return "��f��l������(�Ʒ� ���)";
			case 177: return "��f��l������(�� ���)";
			case 178: return "��7��l�� ���� �޺� ������";
			case 179:
				switch(itemData)
				{
				case 0:
					return "��c��l���� ���";
				case 1:
					return "��c��l������ ���� ���";
				case 2:
					return "��c��l�ε巯�� ���� ���";
				}
				break;
			case 180: return "��c��l���� ��� ���";
			case 181: return "��c��l������ ���� ��� �� ���";
			case 182: return "��c��l���� ��� �� ���";
			case 183: return "��6��l������ ���� ��Ÿ�� ��";
			case 184: return "��6��l���� ���� ��Ÿ�� ��";
			case 185: return "��6��l���� ���� ��Ÿ�� ��";
			case 186: return "��6��l£�� ������ ��Ÿ�� ��";
			case 187: return "��6��l��ī�þ� ���� ��Ÿ�� ��";
			case 188: return "��6��l������ ���� ��Ÿ��";
			case 189: return "��6��l���� ���� ��Ÿ��";
			case 190: return "��6��l���� ���� ��Ÿ��";
			case 191: return "��6��l£�� ������ ��Ÿ��";
			case 192: return "��6��l��ī�þ� ���� ��Ÿ��";
			case 193: return "��6��l������ ���� ��¦ ��";
			case 194: return "��6��l���� ���� ��¦ ��";
			case 195: return "��6��l���� ���� ��¦ ��";
			case 196: return "��6��l��ī�þ� ���� ��¦ ��";
			case 197: return "��6��l£�� ������ ��¦ ��";
			case 198: return "��5��l���� ����";
			case 199: return "��5��l�ķ� ����";
			case 200: return "��5��l�ķ�ȭ";
			case 201: return "��5��l���� ���";
			case 202: return "��5��l���� ���";
			case 203: return "��5��l���� ���";
			case 204: return "��5��l���� ��ģ �� ���";
			case 205: return "��5��l���� �� ���";
			case 206: return "��f��l���� ���� ����";
			case 207: return "��e��l������ ���";
			case 208: return "��6��l�ܵ� ��";
			case 209: return "��8��l���� ����Ʈ";
			case 210: return "��5��l�ݺ� Ŀ�ǵ� ���";
			case 211: return "��a��lü�� Ŀ�ǵ� ���";
			case 212: return "��b��l�ܴ��� ����";
			case 213: return "��c��l���׸� ���";
			case 214: return "��4��l�״� ��Ʈ ���";
			case 215: return "��4��l���� �״� ����";
			case 216: return "��f��l�� ���";
			case 217: return "��2��l�� ������";
			case 218: return "��7��l���� ���";
			case 219: return "��7��l�Ͼ�� ��Ŀ ����";
			case 220: return "��6��l��Ȳ�� ��Ŀ ����";
			case 221: return "��d��l��ȫ�� ��Ŀ ����";
			case 222: return "��b��l�ϴû� ��Ŀ ����";
			case 223: return "��e��l����� ��Ŀ ����";
			case 224: return "��a��l���λ� ��Ŀ ����";
			case 225: return "��d��l��ȫ�� ��Ŀ ����";
			case 226: return "��8��lȸ�� ��Ŀ ����";
			case 227: return "��7��l���� ȸ�� ��Ŀ ����";
			case 228: return "��9��lû�ϻ� ��Ŀ ����";
			case 229: return "��5��l����� ��Ŀ ����";
			case 230: return "��1��l�Ķ��� ��Ŀ ����";
			case 231: return "��6��l���� ��Ŀ ����";
			case 232: return "��2��l�ʷϻ� ��Ŀ ����";
			case 233: return "��4��l������ ��Ŀ ����";
			case 234: return "��0��l������ ��Ŀ ����";
			case 235: return "��7��l�Ͼ�� �׶���Ÿ";
			case 236: return "��6��l��Ȳ�� �׶���Ÿ";
			case 237: return "��d��l��ȫ�� �׶���Ÿ";
			case 238: return "��b��l�ϴû� �׶���Ÿ";
			case 239: return "��e��l����� �׶���Ÿ";
			case 240: return "��a��l���λ� �׶���Ÿ";
			case 241: return "��d��l��ȫ�� �׶���Ÿ";
			case 242: return "��8��lȸ�� �׶���Ÿ";
			case 243: return "��7��l���� ȸ�� �׶���Ÿ";
			case 244: return "��9��lû�ϻ� �׶���Ÿ";
			case 245: return "��5��l����� �׶���Ÿ";
			case 246: return "��1��l�Ķ��� �׶���Ÿ";
			case 247: return "��6��l���� �׶���Ÿ";
			case 248: return "��2��l�ʷϻ� �׶���Ÿ";
			case 249: return "��4��l������ �׶���Ÿ";
			case 250: return "��0��l������ �׶���Ÿ";
			case 251:
			{
				switch(itemData)
				{
					case 0: return "��7��l�Ͼ�� ��ũ��Ʈ";
					case 1: return "��6��l��Ȳ�� ��ũ��Ʈ";
					case 2: return "��d��l��ȫ�� ��ũ��Ʈ";
					case 3: return "��b��l�ϴû� ��ũ��Ʈ";
					case 4: return "��e��l����� ��ũ��Ʈ";
					case 5: return "��a��l���λ� ��ũ��Ʈ";
					case 6: return "��d��l��ȫ�� ��ũ��Ʈ";
					case 7: return "��8��lȸ�� ��ũ��Ʈ";
					case 8: return "��7��l���� ȸ�� ��ũ��Ʈ";
					case 9: return "��9��lû�ϻ� ��ũ��Ʈ";
					case 10: return "��5��l����� ��ũ��Ʈ";
					case 11: return "��1��l�Ķ��� ��ũ��Ʈ";
					case 12: return "��6��l���� ��ũ��Ʈ";
					case 13: return "��2��l�ʷϻ� ��ũ��Ʈ";
					case 14: return "��4��l������ ��ũ��Ʈ";
					case 15: return "��0��l������ ��ũ��Ʈ";
				}
			}break;
			case 252:
			{
				switch(itemData)
				{
					case 0: return "��7��l�Ͼ�� ��ũ��Ʈ ����";
					case 1: return "��6��l��Ȳ�� ��ũ��Ʈ ����";
					case 2: return "��d��l��ȫ�� ��ũ��Ʈ ����";
					case 3: return "��b��l�ϴû� ��ũ��Ʈ ����";
					case 4: return "��e��l����� ��ũ��Ʈ ����";
					case 5: return "��a��l���λ� ��ũ��Ʈ ����";
					case 6: return "��d��l��ȫ�� ��ũ��Ʈ ����";
					case 7: return "��8��lȸ�� ��ũ��Ʈ ����";
					case 8: return "��7��l���� ȸ�� ��ũ��Ʈ ����";
					case 9: return "��9��lû�ϻ� ��ũ��Ʈ ����";
					case 10: return "��5��l����� ��ũ��Ʈ ����";
					case 11: return "��1��l�Ķ��� ��ũ��Ʈ ����";
					case 12: return "��6��l���� ��ũ��Ʈ ����";
					case 13: return "��2��l�ʷϻ� ��ũ��Ʈ ����";
					case 14: return "��4��l������ ��ũ��Ʈ ����";
					case 15: return "��0��l������ ��ũ��Ʈ ����";
				}
			}break;

			case 255: return "��8��l������ ���";
			case 256: return "��f��lö ��";
			case 257: return "��f��lö ���";
			case 258: return "��f��lö ����";
			case 259: return "��f��l������";
			case 260: return "��c��l���";
			case 261: return "��6��lȰ";
			case 262: return "��6��lȭ��";
			case 263:
				switch(itemData)
				{
				case 0:
					return "��0��l��ź";
				case 1:
					return "��0��l��ź";
				}
				break;
			case 264: return "��b��l���̾Ƹ��";
			case 265: return "��f��lö��";
			case 266: return "��e��l�ݱ�";
			case 267: return "��f��lö ��";
			case 268: return "��6��l���� ��";
			case 269: return "��6��l���� ��";
			case 270: return "��6��l���� ���";
			case 271: return "��6��l���� ����";
			case 272: return "��7��l�� ��";
			case 273: return "��7��l�� ��";
			case 274: return "��7��l�� ���";
			case 275: return "��7��l�� ����";
			case 276: return "��b��l���̾Ƹ�� ��";
			case 277: return "��b��l���̾Ƹ�� ��";
			case 278: return "��b��l���̾Ƹ�� ���";
			case 279: return "��b��l���̾Ƹ�� ����";
			case 280: return "��6��l�����";
			case 281: return "��6��l�׸�";
			case 282: return "��d��l���� ��Ʃ";
			case 283: return "��e��l�� ��";
			case 284: return "��e��l�� ��";
			case 285: return "��e��l�� ���";
			case 286: return "��e��l�� ����";
			case 287: return "��f��l��";
			case 288: return "��f��l����";
			case 289: return "��7��lȭ��";
			case 290: return "��6��l���� ����";
			case 291: return "��7��l�� ����";
			case 292: return "��f��lö ����";
			case 293: return "��b��l���̾Ƹ�� ����";
			case 294: return "��e��l�� ����";
			case 295: return "��a��l����";
			case 296: return "��6��l��";
			case 297: return "��6��l��";
			case 298: return "��6��l���� ����";
			case 299: return "��6��l���� Ʃ��";
			case 300: return "��6��l���� ����";
			case 301: return "��6��l���� ��ȭ";
			case 302: return "��f��l�罽 ����";
			case 303: return "��f��l�罽 ����";
			case 304: return "��f��l�罽 ���뽺";
			case 305: return "��f��l�罽 ����";
			case 306: return "��f��lö ����";
			case 307: return "��f��lö ����";
			case 308: return "��f��lö ���뽺";
			case 309: return "��f��lö ����";
			case 310: return "��b��l���̾Ƹ�� ����";
			case 311: return "��b��l���̾Ƹ�� ����";
			case 312: return "��b��l���̾Ƹ�� ���뽺";
			case 313: return "��b��l���̾Ƹ�� ����";
			case 314: return "��e��l�� ����";
			case 315: return "��e��l�� ����";
			case 316: return "��e��l�� ���뽺";
			case 317: return "��e��l�� ����";
			case 318: return "��0��l�ν˵�";
			case 319: return "��d��l������ ���� �������";
			case 320: return "��6��l���� �������";
			case 321: return "��7��l�׸�";
			case 322:
				switch(itemData)
				{
				case 0:
					return "��e��lȲ�ݻ��";
				case 1:
					return "��5��l��æƮ�� Ȳ�ݻ��";
				}
				break;
			case 323: return "��6��lǥ����";
			case 324: return "��6��l���� ��";
			case 325: return "��7��l�絿��";
			case 326: return "��9��l�� �絿��";
			case 327: return "��c��l��� �絿��";
			case 328: return "��7��l����īƮ";
			case 329: return "��6��l����";
			case 330: return "��f��lö ��";
			case 331: return "��c��l���彺��";
			case 332: return "��f��l������";
			case 333: return "��6��l��Ʈ";
			case 334: return "��6��l����";
			case 335: return "��f��l����";
			case 336: return "��c��l����";
			case 337: return "��7��l����";
			case 338: return "��a��l��������";
			case 339: return "��f��l����";
			case 340: return "��6��lå";
			case 341: return "��a��l������ ��";
			case 342: return "��6��l���� ����īƮ";
			case 343: return "��7��lȭ�� ����īƮ";
			case 344: return "��f��l�ް�";
			case 345: return "��7��l��ħ��";
			case 346: return "��6��l���˴�";
			case 347: return "��e��l�ð�";
			case 348: return "��e��l�߱��� ����";
			case 349:
				switch(itemData)
				{
				case 0:
					return "��b��l�� ����";
				case 1:
					return "��c��l�� ����";
				case 2:
					return "��6��l�򵿰���";
				case 3:
					return "��e��l����";
				}
				break;
			case 350:
				switch(itemData)
				{
				case 0:
					return "��7��l���� ����";
				case 1:
					return "��c��l���� ����";
				}
				break;
			case 351:
				switch(itemData)
				{
				case 0:
					return "��0��l�Թ�";
				case 1:
					return "��c��l���� ��� ����";
				case 2:
					return "��2��l�ʷ� ������ ����";
				case 3:
					return "��6��l���ھ� ��";
				case 4:
					return "��1��lû�ݼ�";
				case 5:
					return "��5��l����� ����";
				case 6:
					return "��9��lû�ϻ� ����";
				case 7:
					return "��7��l���� ȸ�� ����";
				case 8:
					return "��8��lȸ�� ����";
				case 9:
					return "��d��l��ȫ�� ����";
				case 10:
					return "��a��l���λ� ����";
				case 11:
					return "��e��l��� �ε鷹 ����";
				case 12:
					return "��b��l�ϴû� ����";
				case 13:
					return "��d��l��ȫ�� ����";
				case 14:
					return "��6��l��Ȳ�� ����";
				case 15:
					return "��f��l�İ���";
				}
				break;
			case 352: return "��f��l��";
			case 353: return "��f��l����";
			case 354: return "��f��l����ũ";
			case 355: return "��c��lħ��";
			case 356: return "��c��l���彺�� �߰��";
			case 357: return "��6��l��Ű";
			case 358: return "��e��l����";
			case 359: return "��f��l����";
			case 360: return "��a��l����";
			case 361: return "��f��lȣ�ھ�";
			case 362: return "��0��l���ھ�";
			case 363: return "��d��l������ ���� �Ұ��";
			case 364: return "��6��l������ũ";
			case 365: return "��d��l������ ���� �߰��";
			case 366: return "��6��l���� �߰��";
			case 367: return "��d��l���� ���";
			case 368: return "��2��l���� ����";
			case 369: return "��e��l������ ����";
			case 370: return "��f��l����Ʈ�� ����";
			case 371: return "��e��l�� ����";
			case 372: return "��c��l�״� ��Ʈ";
			case 373: return "��9��l����";
			case 374: return "��f��l������";
			case 375: return "��4��l�Ź� ��";
			case 376: return "��d��l��ȿ�� �Ź� ��";
			case 377: return "��e��l������ ����";
			case 378: return "��e��l���׸� ũ��";
			case 379: return "��e��l������";
			case 380: return "��7��l������";
			case 381: return "��2��l������ ��";
			case 382: return "��e��l��¦�̴� ����";
			case 383: return "��7��l���� ���� ����";
			case 384: return "��a��l����ġ ��";
			case 385: return "��0��lȭ����";
			case 386: return "��f��lå�� ����";
			case 388: return "��a��l���޶���";
			case 389: return "��6��l������ ����";
			case 390: return "��d��lȭ��";
			case 391: return "��6��l���";
			case 392: return "��6��l����";
			case 393: return "��6��l���� ����";
			case 394: return "��a��l���� �� ����";
			case 395: return "��f��l�� ����";
			case 396: return "��e��lȲ�� ���";
			case 397: return "��f��l�Ӹ�";
			case 398: return "��6��l��� ���˴�";
			case 399: return "��b��l�״��� ��";
			case 400: return "��6��lȣ�� ����";
			case 401: return "��f��l����";
			case 402: return "��7��l�Ҳɳ��� ź��";
			case 403: return "��e��l������ �ο��� å";
			case 404: return "��c��l���彺�� �񱳱�";
			case 405: return "��5��l�״� ����";
			case 406: return "��f��l�״� ����";
			case 407: return "��4��lTNT ����īƮ";
			case 408: return "��7��l�򶧱� ����īƮ";
			case 409: return "��b��l������� ����";
			case 410: return "��b��l������� ����";
			case 411: return "��d��l������ ���� �䳢���";
			case 412: return "��6��l���� �䳢���";
			case 413: return "��6��l�䳢 ��Ʃ";
			case 414: return "��6��l�䳢 ��";
			case 415: return "��6��l�䳢 ����";
			case 416: return "��f��l���� ��ġ��";
			case 417: return "��f��lö �� ����";
			case 418: return "��e��l�� �� ����";
			case 419: return "��b��l���̾Ƹ�� �� ����";
			case 420: return "��6��l��";
			case 421: return "��6��l�̸�ǥ";
			case 423: return "��d��l������ ���� ����";
			case 424: return "��6��l���� ����";
			case 425: return "��f��l������";
			case 426: return "��d��l���� ����";
			case 427: return "��6��l������ ���� ��";
			case 428: return "��6��l���� ���� ��";
			case 429: return "��6��l���� ���� ��";
			case 430: return "��6��l��ī�þ� ���� ��";
			case 431: return "��6��l£�� ������ ��";
			case 432: return "��d��l�ķŰ�";
			case 433: return "��d��lƢ�� �ķŰ�";
			case 434: return "��c��l������";
			case 435: return "��f��l������ ����";
			case 436: return "��c��l������ ����";
			case 437: return "��d��l�巡���� ����";
			case 438: return "��f��l��ô�� ����";
			case 439: return "��e��l�б� ȭ��";
			case 440: return "��f��l���� �ٸ� ȭ��";
			case 441: return "��f��l�ܷ��� ����";
			case 442: return "��f��l����";
			case 443: return "��f��l�ѳ���";
			case 444: return "��6��l�����񳪹� ��Ʈ";
			case 445: return "��6��l���۳��� ��Ʈ";
			case 446: return "��6��l���۳��� ��Ʈ";
			case 447: return "��6��l��ī�þƳ��� ��Ʈ";
			case 448: return "��6��l£�� ������ ��Ʈ";
			case 449: return "��e��l�һ��� ����";
			case 450: return "��d��l��Ŀ ������";
			case 452: return "��f��lö ����";
			case 2256: return "��7��l[����] [�� �� : ��l��e��l13��l��7��l]";
			case 2257: return "��7��l[����] [�� �� : ��l��a��lcat��l��7��l]";
			case 2258: return "��7��l[����] [�� �� : ��l��c��lblocks��l��7��l]";
			case 2259: return "��7��l[����] [�� �� : ��l��4��lchirp��l��7��l]";
			case 2260: return "��7��l[����] [�� �� : ��l��a��lfar��l��7��l]";
			case 2261: return "��7��l[����] [�� �� : ��l"+ChatColor.MAGIC+"��lmall��l��7��l]";
			case 2262: return "��7��l[����] [�� �� : ��l��5��lmellohi��l��7��l]";
			case 2263: return "��7��l[����] [�� �� : ��l��0��lstal��l��7��l]";
			case 2264: return "��7��l[����] [�� �� : ��l��f��lstrad��l��7��l]";
			case 2265: return "��7��l[����] [�� �� : ��l��2��lward��l��7��l]";
			case 2266: return "��7��l[����] [�� �� : ��l��0��l11��l��7��l]";
			case 2267: return "��7��l[����] [�� �� : ��l��b��lwait��l��7��l]";
		}
		return name;
	}
}