package party;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import effect.SoundEffect;
import util.YamlLoader;

public class Party_Object
{
	private Long CreateTime = null;
	private String Title = null;
	private String Leader = null;
	private Boolean PartyLock = false;
	private String PartyPassword = null;
	private byte PartyCapacity = 2;
	private String[] PartyMember = null;
	
	public Party_Object(Long CreateTime, Player player, String PartyName)
	{
		this.Title = PartyName;
		this.CreateTime = CreateTime;
		this.Leader = player.getName();
		this.PartyMember = new String[this.PartyCapacity];
		this.PartyMember[0] = player.getName();
		
		main.Main_ServerOption.partyJoiner.put(player, CreateTime);
		main.Main_ServerOption.party.put(CreateTime, this);
	}
	
	public Party_Object(Long CreateTime, String Leader,
			String Title, Boolean PartyLock, String PartyPassword, byte PartyCapacity,
			String[] PartyMember)
	{
		this.Title = Title;
		this.CreateTime = CreateTime;
		this.Leader = Leader;
		this.PartyLock = PartyLock;
		this.PartyPassword = PartyPassword;
		this.PartyCapacity = PartyCapacity;
		this.PartyMember = new String[this.PartyCapacity];
		
		for(int count = 0; count < this.PartyCapacity; count++)
		{
			if(PartyMember[count]=="null")
				this.PartyMember[count] = null;
			else
				this.PartyMember[count] = PartyMember[count];
		}
	}
	
	public void ChangeMaxCpacity(Player player, byte Capacity)
	{
		if(player.getName().equals(this.Leader))
			if(Capacity >= getPartyMembers())
			{
				if(Capacity >= 2)
				{
					YamlLoader configYaml = new YamlLoader();
					configYaml.getConfig("config.yml");
					if(Capacity <= configYaml.getInt("Party.MaxPartyUnit"))
					{
						this.PartyCapacity = Capacity;
						String[] TempMember = this.PartyMember;
						this.PartyMember = new String[Capacity];
						for(int count = 0; count < this.PartyCapacity; count++)
							PartyMember[count] = null;
						byte a=0;
						for(int count = 0; count < TempMember.length; count++)
						{
							if(TempMember[count]!=null&&TempMember[count]!="null")
							{
								PartyMember[a] = TempMember[count];
								a++;
							}
						}
						PartyBroadCastMessage("��a[��Ƽ] : �ִ� ��Ƽ�� ���� ��e��l"+Capacity+"���a���� ����Ǿ����ϴ�!",null);
						PartyBroadCastSound(Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.0F,null);
					}
					else
						Message(player, (byte)3);
				}
				else
					Message(player, (byte)10);
			}
			else
				Message(player, (byte)2);
		else
			Message(player, (byte)1);
	}

	public void ChangeLock(Player player)
	{
		if(player.getName().equals(this.Leader))
		{
			if(this.PartyLock == false)
			{
				this.PartyLock = true;
				PartyBroadCastMessage("��c[��Ƽ] : ���̻� ��Ƽ ������ ���� �ʽ��ϴ�!",null);
				PartyBroadCastSound(Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F,null);
			}
			else
			{
				this.PartyLock = false;
				PartyBroadCastMessage("��a[��Ƽ] : ��Ƽ ������ ���� �մϴ�!",null);
				PartyBroadCastSound(Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F,null);
			}
		}
		else
			Message(player, (byte)1);
	}
	
	public void SetPassword(Player player, String Message)
	{
		if(player.getName().equals(this.Leader))
		{
			this.PartyPassword = Message;
			PartyBroadCastMessage("��e[��Ƽ] : ��ȣ�� ���� �����Ǿ����ϴ�!",null);
			PartyBroadCastSound(Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F,null);
		}
		else
			Message(player, (byte)1);
	}
	
	public void JoinParty(Player player)
	{
		if(main.Main_ServerOption.partyJoiner.containsKey(player) == false)
			if(this.PartyCapacity > getPartyMembers())
				if(this.PartyLock == false)
					if(this.PartyPassword == null)
					{
						for(int count = 0; count < this.PartyCapacity; count++)
							if(this.PartyMember[count] == null)
							{
								if(player.isOnline())
								{
									player.sendMessage("��a[��Ƽ] : ��Ƽ�� ���� �Ͽ����ϴ�!");
									SoundEffect.SP(player, Sound.BLOCK_WOODEN_DOOR_OPEN, 1.1F, 1.0F);
								}
								this.PartyMember[count] = player.getName();
								main.Main_ServerOption.partyJoiner.put(player, this.CreateTime);
								PartyBroadCastMessage("��a[��Ƽ] : ��e��l"+player.getName()+"��a�Բ��� ��Ƽ�� �����ϼ̽��ϴ�!",player);
								PartyBroadCastSound(Sound.BLOCK_WOODEN_DOOR_OPEN, 1.1F, 1.0F,player);
								return;
							}
					}
					else
					{
						
					}
				else
					Message(player, (byte)5);
			else
				Message(player, (byte)4);
		else
			Message(player, (byte)6);
	}

	public void QuitParty(Player player)
	{
		main.Main_ServerOption.partyJoiner.remove(player);
		if(player.isOnline())
		{
			player.sendMessage("��c[��Ƽ] : ��Ƽ�� Ż���Ͽ����ϴ�!");
			SoundEffect.SP(player, Sound.BLOCK_WOODEN_DOOR_CLOSE, 1.1F, 1.0F);
		}
		if(getPartyMembers() == 1)
		{
			main.Main_ServerOption.party.remove(this.CreateTime);
			return;
		}
		for(int count = 0; count < this.PartyCapacity; count++)
			if(this.PartyMember[count] == player.getName()||this.PartyMember[count].equals(player.getName()))
			{
				PartyBroadCastMessage("��c[��Ƽ] : ��e��l"+player.getName()+"��c�Բ��� ��Ƽ�� Ż���ϼ̽��ϴ�!",player);
				PartyBroadCastSound(Sound.BLOCK_WOODEN_DOOR_CLOSE, 1.1F, 1.0F,player);
				for(int counter = count; counter < this.PartyCapacity-1; counter++)
					this.PartyMember[counter] = this.PartyMember[counter+1];
				this.PartyMember[this.PartyMember.length-1] = null;
				break;
			}
		if(player.getName().equals(this.Leader))
		{
			for(int count = 0; count < this.PartyCapacity; count++)
				if(this.PartyMember[count] != null)
				{
					ChangeLeader(Bukkit.getServer().getPlayer(this.PartyMember[count]));
					break;
				}
		}
	}
	
	public void QuitPartyOfflinePlayer(String playerName)
	{
		if(getPartyMembers() == 1)
		{
			main.Main_ServerOption.party.remove(this.CreateTime);
			return;
		}
		for(int count = 0; count < this.PartyCapacity; count++)
			if(this.PartyMember[count]!=null)
				if(this.PartyMember[count].equals(playerName))
				{
					PartyBroadCastMessage("��c[��Ƽ] : ��e��l"+playerName+"��c�Բ��� ��Ƽ�� Ż���ϼ̽��ϴ�!",null);
					PartyBroadCastSound(Sound.BLOCK_WOODEN_DOOR_CLOSE, 1.1F, 1.0F,null);
					for(int counter = count; counter < this.PartyCapacity-1; counter++)
						this.PartyMember[counter] = this.PartyMember[counter+1];
					this.PartyMember[this.PartyMember.length-1] = null;
					break;
				}
		if(playerName.equals(this.Leader))
		{
			for(int count = 0; count < this.PartyCapacity; count++)
				if(this.PartyMember[count] != null)
				{
					ChangeLeader(Bukkit.getServer().getPlayer(this.PartyMember[count]));
					break;
				}
		}
	}
	
	public void ChangeLeader(Player player)
	{
		this.Leader = player.getName();
		PartyBroadCastMessage("��e[��Ƽ] : ��e��l"+player.getName()+"��e�Բ��� ��Ƽ ������ �Ǽ̽��ϴ�!",null);
		PartyBroadCastSound(Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.5F,null);
	}
	
	public void ChangeLeader(Player player,String target)
	{
		if(player.getName().equals(this.Leader))
		{
			for(int count = 0; count < getMember().length; count ++)
			{	if(getMember()[count].getName() == target ||getMember()[count].getName().equals(target))
				{
					if(Bukkit.getServer().getOfflinePlayer(target).isOnline())
						if(this.Leader != target &&!this.Leader.equals(target))
							ChangeLeader(Bukkit.getPlayer(target));
						else
							Message(player, (byte)9);
					else
						Message(player, (byte)8);
					return;
				}
			}
			Message(player, (byte)11);
		}
		else
			Message(player, (byte)1);
	}

	public void ChangeTitle(Player player, String Message)
	{
		if(player.getName().equals(this.Leader))
		{
			this.Title = Message;
			PartyBroadCastMessage("��e[��Ƽ] : ��Ƽ ������ ����Ǿ����ϴ�!",null);
			PartyBroadCastSound(Sound.ENTITY_ITEM_PICKUP, 1.0F, 1.8F,null);
		}
		else
			Message(player, (byte)1);
	}
	
	public void KickPartyMember(Player player, String target)
	{
		if(player.getName()==this.Leader||player.getName().equals(this.Leader))
		{
			if(player.getName().equals(target)==false)
				if(Bukkit.getServer().getOfflinePlayer(target).isOnline())
					for(int count = 0; count < this.PartyCapacity; count++)
					{
						if(this.PartyMember[count].equals(target))
						{
							QuitParty((Player) Bukkit.getServer().getPlayer(target));
							return;
						}
					}
				else
					Message(player, (byte)8);
			else
				Message(player, (byte)7);
		}
		else
			Message(player, (byte)1);
	}
	
	public String getLeader()
	{
		return this.Leader;
	}
	
	public Player[] getMember()
	{
		Player[] p = new Player[this.PartyCapacity];
		byte a = 0;
		for(int count = 0; count < this.PartyCapacity; count++)
			if(this.PartyMember[count] != null)
				if(Bukkit.getServer().getOfflinePlayer(this.PartyMember[count]).isOnline())
				{
					p[a] = Bukkit.getServer().getPlayer(this.PartyMember[count]);
					a++;
				}
				else
					this.PartyMember[count] = null;
		Player[] pp = new Player[a];
		for(int count = 0; count < pp.length; count++)
			pp[count] = p[count];
		
		return pp;
	}
	
	public boolean getLock()
	{
		return this.PartyLock;
	}

	public String getTitle()
	{
		return this.Title;
	}

	public int getCapacity()
	{
		return this.PartyCapacity;
	}

	public String getPassword()
	{
		return this.PartyPassword;
	}
	
	public void getPartyInformation()
	{
		
	}
		
	public int getPartyMembers()
	{
		int Members = 0;
		for(int count = 0; count < this.PartyCapacity; count++)
			if(this.PartyMember[count] != null)
				if(Bukkit.getServer().getOfflinePlayer(this.PartyMember[count]).isOnline())
					Members = Members+1;
		return Members;
	}
	
	public void PartyBroadCastMessage(String Message, Player noAlertMember)
	{
		Player[] p = getMember();
		
		for(int count = 0; count < p.length; count++)
		{
			if(p[count] != null && p[count] != noAlertMember)
				if(Bukkit.getServer().getOfflinePlayer(p[count].getName()).isOnline())
					p[count].sendMessage(Message);
		}
	}

	public void PartyBroadCastSound(Sound s, float volume, float pitch, Player noAlertMember)
	{
		Player[] p = getMember();
		for(int count = 0; count < p.length; count++)
			if(p[count] != null && p[count] != noAlertMember)
				if(Bukkit.getServer().getOfflinePlayer(p[count].getName()).isOnline())
					SoundEffect.SP(p[count], s, volume, pitch);
	}
	
	public void Message(Player player, byte num)
	{
		SoundEffect.SP(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.8F);
		switch(num)
		{
		case 1:
			player.sendMessage("��c[��Ƽ] : ����� ��Ƽ ������ �ƴմϴ�!");
			return;
		case 2:
			player.sendMessage("��c[��Ƽ] : �ִ� �ο����� ���� ��Ƽ �ο� �� ���� ������ �� �����ϴ�!");
			return;
		case 3:
			{
				YamlLoader configYaml = new YamlLoader();
				configYaml.getConfig("config.yml");
				player.sendMessage("��c[��Ƽ] : �ִ� ��Ƽ �ο� �� : ��e��l"+configYaml.getInt("Party.MaxPartyUnit")+"��");
			}
			return;
		case 4:
			player.sendMessage("��c[��Ƽ] : ��Ƽ �ο��� �� á���ϴ�!");
			return;
		case 5:
			player.sendMessage("��c[��Ƽ] : �ش� ��Ƽ�� ���̻� ��Ƽ���� �������� �ʽ��ϴ�!");
			return;
		case 6:
			player.sendMessage("��c[��Ƽ] : ����� �̹� �ٸ� ��Ƽ�� ���� ���Դϴ�!");
			return;
		case 7:
			player.sendMessage("��c[��Ƽ] : �ڱ� �ڽ��� ���� ��ų �� �����ϴ�!");
			return;
		case 8:
			player.sendMessage("��c[��Ƽ] : �ش� �÷��̾�� ��Ƽ���� �ƴմϴ�!");
			return;
		case 9:
			player.sendMessage("��c[��Ƽ] : ����� �̹� ��Ƽ �����Դϴ�!");
			return;
		case 10:
			player.sendMessage("��c[��Ƽ] : ��Ƽ �ο��� �ּ� 2�� �̻��̾�� �մϴ�!");
			return;
		case 11:
			player.sendMessage("��c[��Ƽ] : �ش� �÷��̾�� ��Ƽ���� �ƴմϴ�!");
			return;
		}
	}
}
