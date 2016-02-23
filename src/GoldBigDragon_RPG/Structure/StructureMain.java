package GoldBigDragon_RPG.Structure;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import GoldBigDragon_RPG.Main.UserDataObject;
import GoldBigDragon_RPG.ServerTick.ServerTickMain;

public class StructureMain
{
	/*
	Ŀ�ǵ� ��� �߾ӿ� �� ����
	/summon ArmorStand ~-0.18 ~-0.348 ~-0.28 {ShowArms:1,Invisible:1,NoBasePlate:1,NoGravity:1,Equipment:[{id:stone,Count:1},{},{},{},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[346f,44f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}
	
	�� �ٷ� ���� �� ����
	/summon ArmorStand ~-0.18 ~0.008 ~-0.28 {ShowArms:1,Invisible:1,NoBasePlate:1,NoGravity:1,Equipment:[{id:stone,Count:1},{},{},{},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[346f,44f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}
	�Ƹ� ���ĵ��� �տ� ����� ����� �Ÿ� ���� 0.34����.
	
	Ŀ�ǵ� ��� �߾ӿ� �� �ִ� ���� ����
	/summon ArmorStand ~-0.14 ~0.07 ~0.12 {ShowArms:1,Invisible:1,NoBasePlate:1,NoGravity:1,Equipment:[{id:stick,Count:1},{},{},{},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[350f,90f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}
	
	�� �ٷ� ���� ���� ����
	/summon ArmorStand ~-0.14 ~0.896 ~0.12 {ShowArms:1,Invisible:1,NoBasePlate:1,NoGravity:1,Equipment:[{id:stick,Count:1},{},{},{},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[350f,90f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}
	�Ƹ� ���ĵ��� �տ� ����� ����� �Ÿ� ���� ���� 0.826��.
	
	����� ���� ��� ����
	/summon ArmorStand ~0.08 ~0.268 ~-0.216 {ShowArms:1,Invisible:1,NoBasePlate:1,NoGravity:1,Equipment:[{id:planks,Count:1},{},{},{},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[346f,44f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}
	
	������ ������
	/summon ArmorStand ~0.3 ~1.63 ~1.08 {ShowArms:1,Invisible:1,Invulnerable:1,NoBasePlate:1,NoGravity:1,Rotation:[90f,0.0f],Equipment:[{id:item_frame,Count:1},{},{},{},{}],Pose:{Body:[0f,0f,0f],LeftArm:[0f,0f,0f],RightArm:[0f,270f,270f],LeftLeg:[0f,0f,0f],RightLeg:[0f,0f,0f],Head:[0f,0f,0f]}}
	
	 */
	public void CreateSturcture(Player player, String StructureCode, int StructureID, int Direction)
	{
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();
		if(ServerTickMain.ServerTask.compareTo("null")!=0)
		{
			s.SP(player, Sound.ORB_PICKUP, 1.0F, 1.8F);
			player.sendMessage(ChatColor.RED+"[Server] : ���� ������ "+ChatColor.YELLOW+ServerTickMain.ServerTask+ChatColor.RED+" �۾� ���Դϴ�.");
			return;
		}
		ServerTickMain.ServerTask="[������ ��ġ]";
		Long UTC = ServerTickMain.nowUTC;
		
		GoldBigDragon_RPG.ServerTick.ServerTickScheduleObject STSO = new GoldBigDragon_RPG.ServerTick.ServerTickScheduleObject(UTC, "C_S");
		STSO.setCount(0);//���� ���� �ܰ�
		
		Location PlayerLoc = player.getLocation();
		Location CMLock = new Location(PlayerLoc.getWorld(), PlayerLoc.getX()-1, PlayerLoc.getY()-2, PlayerLoc.getZ()-1);
		Location RSLock = new Location(PlayerLoc.getWorld(), PlayerLoc.getX(), PlayerLoc.getY()-3, PlayerLoc.getZ());
		
		switch(StructureID)
		{
			case 0://������
				STSO.setString((byte)0, "PB");//��ġ�� ������ �̸� ���� PostBox
				break;
			case 1://�Խ���
				STSO.setString((byte)0, "B");//��ġ�� ������ �̸� ���� Board
				break;
			case 2://�ŷ� �Խ���
				STSO.setString((byte)0, "TB");//��ġ�� ������ �̸� ���� TradeBoard
				break;
			case 3://ķ�� ���̾�
				STSO.setString((byte)0, "CF");//��ġ�� ������ �̸� ���� CampFire
				break;
		}
		STSO.setString((byte)1, player.getLocation().getWorld().getName());//�÷��̾��� ���� �̸� ����
		STSO.setString((byte)2, StructureCode);//������ �ڵ� ����
		STSO.setString((byte)3, Direction+"");//������ ���� ����
		
		STSO.setInt((byte)0, (int) CMLock.getX());//Ŀ�ǵ� ���X ��ġ����
		STSO.setInt((byte)1, (int) CMLock.getY());//Ŀ�ǵ� ���Y ��ġ����
		STSO.setInt((byte)2, (int) CMLock.getZ());//Ŀ�ǵ� ���Z ��ġ����
		STSO.setInt((byte)3, (int) 0);//���� �ؾ� �� �� 0�̸� ���彺�� ��� ����, 1�̸� ����

		STSO.setInt((byte)4, (int) CMLock.getBlock().getTypeId());//Ŀ�ǵ� ��� ��ġ�� ���� ��� ID
		STSO.setInt((byte)5, (int) CMLock.getBlock().getData());//Ŀ�ǵ� ��� ��ġ�� ���� ��� DATA
		STSO.setInt((byte)6, (int) RSLock.getBlock().getTypeId());//���� ���� ��� ��ġ�� ���� ��� ID
		STSO.setInt((byte)7, (int) RSLock.getBlock().getData());//���� ���� ��� ��ġ�� ���� ��� DATA
		player.sendMessage(ChatColor.YELLOW+"[SYSTEM] : ������ ��ġ�� ���� ���� ���, ���� �������� Ŀ�ǵ� ��� ����� Ȱ��ȭ ���� �ּ���!");
		GoldBigDragon_RPG.ServerTick.ServerTickMain.Schedule.put(GoldBigDragon_RPG.ServerTick.ServerTickMain.nowUTC, STSO);
	}
	
	public String getCMD(String StructureName, int LineNumber, String StructureCode, String Direction)
	{
		if(StructureName.compareTo("PB")==0)
			return new Structure_PostBox().CreatePostBox(LineNumber,StructureCode,Integer.parseInt(Direction));
		else if(StructureName.compareTo("B")==0)
			return new Structure_Board().CreateBoard(LineNumber,StructureCode,Integer.parseInt(Direction));
		else if(StructureName.compareTo("TB")==0)
			return new Structure_TradeBoard().CreateTradeBoard(LineNumber,StructureCode,Integer.parseInt(Direction));
		else if(StructureName.compareTo("CF")==0)
			return new Structure_CampFire().CreateCampFire(LineNumber,StructureCode,Integer.parseInt(Direction));

		return "null";
	}
	
	
	
	
	public void StructureUse(Player player, String StructureName)
	{
		GoldBigDragon_RPG.Effect.Sound s = new GoldBigDragon_RPG.Effect.Sound();

		String Structrue = ChatColor.stripColor(StructureName);
		if(Structrue.compareTo("[������]")==0)
		{
			s.SP(player, Sound.CHEST_OPEN, 0.8F, 1.0F);
			new Structure_PostBox().PostBoxMainGUI(player, 0);
		}
		else if(Structrue.compareTo("[�Խ���]")==0)
		{
			s.SP(player, Sound.ZOMBIE_WOOD, 0.5F, 1.8F);
			new Structure_Board().BoardMainGUI(player,StructureName, 0);
		}
		else if(Structrue.compareTo("[�ŷ� �Խ���]")==0)
		{
			s.SP(player, Sound.VILLAGER_IDLE, 1.0F, 1.0F);
			new Structure_TradeBoard().TradeBoardMainGUI(player, 0, 0);
		}
		else if(Structrue.compareTo("[��ں�]")==0)
		{
			s.SP(player, Sound.VILLAGER_IDLE, 1.0F, 1.0F);
			new Structure_TradeBoard().TradeBoardMainGUI(player, 0, 0);
		}
	}
	
	public void InventoryClickRouter(InventoryClickEvent event, String InventoryName)
	{
		String Striped = ChatColor.stripColor(event.getInventory().getName().toString());
		if(event.getInventory().getType()==InventoryType.CHEST)
		{
			if(!(Striped.compareTo("���� ������")==0
			))
				event.setCancelled(true);
		}
		if(InventoryName.compareTo("������")==0)
			new Structure_PostBox().PostBoxMainGUIClick(event);
		else if(InventoryName.compareTo("���� ������")==0)
			new Structure_PostBox().ItemPutterGUIClick(event);
		else if(InventoryName.contains("�Խ���"))
		{
			if(InventoryName.contains("�ŷ�"))
			{
				if(InventoryName.contains("�޴�"))
					new Structure_TradeBoard().SelectTradeTypeGUIClick(event);
				else if(InventoryName.contains("����"))
					new Structure_TradeBoard().TradeBoardSettingGUIClick(event);
				else
					new Structure_TradeBoard().TradeBoardMainGUIClick(event);
			}
			else
			{
				if(InventoryName.contains("����"))
					new Structure_Board().BoardSettingGUIClick(event);
				else
					new Structure_Board().BoardMainGUIClick(event);
			}
		}
		else if(InventoryName.compareTo("�Ǹ��� �������� ������")==0)
			new Structure_TradeBoard().SelectSellItemGUIClick(event);
		else if(InventoryName.compareTo("������ �������� ������")==0)
			new Structure_TradeBoard().SelectBuyItemGUIClick(event);
		else if(InventoryName.contains("�Ϲ� ������"))
			new Structure_TradeBoard().SelectNormalItemGUIClick(event);
		else if(InventoryName.compareTo("�ް���� �������� ������")==0)
			new Structure_TradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(InventoryName.compareTo("���� �� �������� ������")==0)
			new Structure_TradeBoard().SelectExchangeItem_MyGUIClick(event);
	}
	
	
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryName)
	{
		UserDataObject u = new UserDataObject();
		Player player = (Player)event.getPlayer();
		
		if(InventoryName.compareTo("���� ������")==0)
			new Structure_PostBox().ItemPutterGUIClose(event);
		else if(InventoryName.compareTo("�Ǹ��� �������� ������")==0||InventoryName.compareTo("������ �������� ������")==0)
		{
			if(u.getItemStack((Player)event.getPlayer())==null)
				u.clearAll(player);
		}
		else if(InventoryName.contains("�Ϲ� ������"))
			u.clearAll(player);
	}
}