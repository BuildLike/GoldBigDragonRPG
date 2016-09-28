package GBD_RPG.Structure;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import GBD_RPG.Dungeon.Schematic;
import GBD_RPG.ServerTick.ServerTick_Main;
import GBD_RPG.User.UserData_Object;

public class Structure_Main
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
	public void CreateSturcture(Player player, String StructureCode, short StructureID, int Direction)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();
		ServerTick_Main.ServerTask="[������ ��ġ]";
		Long UTC = ServerTick_Main.nowUTC;
		
		GBD_RPG.ServerTick.ServerTick_Object STSO = new GBD_RPG.ServerTick.ServerTick_Object(UTC, "C_S");
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
				
				
			case 101://�̳� ����
				STSO.setString((byte)0, "A_M");//��ġ�� ������ �̸� ���� Altar_Mossy
				break;
			case 102://����ף
				STSO.setString((byte)0, "A_GoldBigDragon");//��ġ�� ������ �̸� ���� Altar_GoldBigDragon
				break;
			case 103://���� ����
				STSO.setString((byte)0, "A_SH");//��ġ�� ������ �̸� ���� Altar_StoneHenge
				break;
			case 104://�غδ�
				STSO.setString((byte)0, "A_AB");//��ġ�� ������ �̸� ���� Altar_AnatomicalBoard
				break;
			case 145://�׽�Ʈ�� ����
				STSO.setString((byte)0, "A_TEST");//��ġ�� ������ �̸� ���� Altar_TEST
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
		GBD_RPG.ServerTick.ServerTick_Main.Schedule.put(GBD_RPG.ServerTick.ServerTick_Main.nowUTC, STSO);
	}
	
	public String getCMD(String StructureName, int LineNumber, String StructureCode, String Direction)
	{
		if(StructureName.compareTo("PB")==0)
			return new Struct_PostBox().CreatePostBox(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("B")==0)
			return new Struct_Board().CreateBoard(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("TB")==0)
			return new Struct_TradeBoard().CreateTradeBoard(LineNumber,StructureCode,(byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("CF")==0)
			return new Struct_CampFire().CreateCampFire(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));

		else if(StructureName.compareTo("A_M")==0)
			return new Struct_Altar().CreateMossyAltar(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("A_GoldBigDragon")==0)
			return new Struct_Altar().CreateGoldBigDragon1(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("A_SH")==0)
			return new Struct_Altar().CreateStoneHenge(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("A_AB")==0)
			return new Struct_Altar().CreateAtonomicBoard(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		else if(StructureName.compareTo("A_TEST")==0)
			return new Struct_Altar().CreateTestAltar(LineNumber,StructureCode, (byte) Byte.parseByte(Direction));
		return "null";
	}
	
	public static void pasteSchematic(Location loc, Schematic schematic)
    {
		byte[] blocks = schematic.getBlocks();
		byte[] blockData = schematic.getData();
 
        short length = schematic.getLenght();
        short width = schematic.getWidth();
        short height = schematic.getHeight();
 
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    Block block = new Location(loc.getWorld(), x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();
                    block.setTypeIdAndData(blocks[index], (byte) blockData[index], true);
                }
            }
        }
    }
	
	public void StructureUse(Player player, String StructureName)
	{
		GBD_RPG.Effect.Effect_Sound s = new GBD_RPG.Effect.Effect_Sound();

		String Structrue = ChatColor.stripColor(StructureName);
		if(Structrue.compareTo("[������]")==0)
		{
			s.SP(player, Sound.BLOCK_CHEST_OPEN, 0.8F, 1.0F);
			new Struct_PostBox().PostBoxMainGUI(player, (byte) 0);
		}
		else if(Structrue.compareTo("[�Խ���]")==0)
		{
			s.SP(player, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 0.5F, 1.8F);
			new Struct_Board().BoardMainGUI(player,StructureName, (byte) 0);
		}
		else if(Structrue.compareTo("[�ŷ� �Խ���]")==0)
		{
			s.SP(player, Sound.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
			new Struct_TradeBoard().TradeBoardMainGUI(player, (byte)0, (byte)0);
		}
		else if(Structrue.compareTo("[��ں�]")==0)
		{
			s.SP(player, Sound.BLOCK_FIRE_AMBIENT, 2.0F, 1.0F);
			new Struct_CampFire().CampFireMainGUI(player, StructureName);
		}
		else if(Structrue.compareTo("[����]")==0)
		{
			s.SP(player, Sound.AMBIENT_CAVE, 1.2F, 1.2F);
			new GBD_RPG.Dungeon.Dungeon_GUI().AltarUseGUI(player, StructureName);
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
			new Struct_PostBox().PostBoxMainGUIClick(event);
		else if(InventoryName.compareTo("���� ������")==0)
			new Struct_PostBox().ItemPutterGUIClick(event);
		else if(InventoryName.contains("�Խ���"))
		{
			if(InventoryName.contains("�ŷ�"))
			{
				if(InventoryName.contains("�޴�"))
					new Struct_TradeBoard().SelectTradeTypeGUIClick(event);
				else if(InventoryName.contains("����"))
					new Struct_TradeBoard().TradeBoardSettingGUIClick(event);
				else
					new Struct_TradeBoard().TradeBoardMainGUIClick(event);
			}
			else
			{
				if(InventoryName.contains("����"))
					new Struct_Board().BoardSettingGUIClick(event);
				else
					new Struct_Board().BoardMainGUIClick(event);
			}
		}
		else if(InventoryName.compareTo("�Ǹ��� �������� ������")==0)
			new Struct_TradeBoard().SelectSellItemGUIClick(event);
		else if(InventoryName.compareTo("������ �������� ������")==0)
			new Struct_TradeBoard().SelectBuyItemGUIClick(event);
		else if(InventoryName.contains("�Ϲ� ������"))
			new Struct_TradeBoard().SelectNormalItemGUIClick(event);
		else if(InventoryName.compareTo("�ް���� �������� ������")==0)
			new Struct_TradeBoard().SelectExchangeItem_YouGUIClick(event);
		else if(InventoryName.compareTo("���� �� �������� ������")==0)
			new Struct_TradeBoard().SelectExchangeItem_MyGUIClick(event);
		else if(InventoryName.compareTo("��ں�")==0)
			new Struct_CampFire().CampFireGUIClick(event);
	}
	
	
	public void InventoryCloseRouter(InventoryCloseEvent event, String InventoryName)
	{
		UserData_Object u = new UserData_Object();
		Player player = (Player)event.getPlayer();
		
		if(InventoryName.compareTo("���� ������")==0)
			new Struct_PostBox().ItemPutterGUIClose(event);
		else if(InventoryName.compareTo("�Ǹ��� �������� ������")==0||InventoryName.compareTo("������ �������� ������")==0)
		{
			if(u.getItemStack((Player)event.getPlayer())==null)
				u.clearAll(player);
		}
		else if(InventoryName.contains("�Ϲ� ������"))
			u.clearAll(player);
	}
}