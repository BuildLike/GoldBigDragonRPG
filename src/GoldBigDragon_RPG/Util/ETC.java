package GoldBigDragon_RPG.Util;

import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import GoldBigDragon_RPG.Main.Main;

//�ڹ� ���� ���̺귯�� ��, ��¥ �Լ��� ȣ����.

public class ETC
{
	public Date date = new Date();
	//��¥ �Լ��� ȣ���Ͽ� date�� �� �̸����� �����ϴ� �ܶ�
	
	public long getNowUTC()
	{
		return date.UTC(date.getYear(),date.getMonth(),date.getDate(),date.getHours(),date.getMinutes(),date.getSeconds());
	}
	
    public long getSec()
    {
    	Calendar Calender = Calendar.getInstance();
    	return Calender.getTimeInMillis();
    }
    
    //���� �ʸ� ��Ÿ���� �޼ҵ�//
    public int getSecond()
    {
    	return date.getSeconds();
    }
    
    //���� ���� ��Ÿ���� �޼ҵ�//
    public int getMin()
    {
    	return date.getMinutes();
    }
    
    //���� �ð��� ��Ÿ���� �޼ҵ�//
    public int getHour()
    {
    	return date.getHours();
    }
    
    //���� ������ ��Ÿ���� �޼ҵ�//
    public int getDay()
    {
    	return date.getDate();
    }
    
    //���� ���� ��Ÿ���� �޼ҵ�//
    public int getMonth()
    {
    	return date.getMonth()+1;
    }

    //���� �⵵�� ��Ÿ���� �޼ҵ�//
    public int getYear()
    {
    	return date.getYear()+1900;
    }
    
    //��/��/�� ���� �����ִ� �޼ҵ�//
    public String getToday()
    {
    	return getYear() + "." + getMonth() + "." + getDay();
    }

    //������ ������ ����� ���� ���ϴ� �޼ҵ�//
    public String Today()
	{
		Calendar Calender = Calendar.getInstance();
		//���� �⵵, ��, ��
		int year = Calender.get ( Calendar.YEAR );
		int month = Calender.get ( Calendar.MONTH ) + 1 ;
		int date = Calender.get ( Calendar.DATE ) ;
		//���� �ð�(��,��,��)
		int hour = Calender.get ( Calendar.HOUR_OF_DAY ) ;
		int zellerMonth;
		int zellerYear;
		String Today = null;
		if(month < 3) { // ������ 3���� ������
		    
		    zellerMonth = month + 12; // �� + 12
		    zellerYear = year - 1; // �� - 1
		}

		else {
			zellerMonth = month;
			zellerYear = year;
		}
		   
		int computation = date + (26 * (zellerMonth + 1)) / 10 + zellerYear + 
		                  zellerYear / 4 + 6 * (zellerYear / 100) +
		                  zellerYear / 400;
		int dayOfWeek = computation % 7;
		
		
		 switch(dayOfWeek) // 0~6���� ��~�ݿ��Ϸ� ǥ��
		    {
		     
		      case 0:
		    	  Today = "�����";
		          break;
		      case 1:
		    	  Today = "�Ͽ���";
		          break;
		      case 2:
		    	  Today = "������";
		          break;
		      case 3:
		    	  Today = "ȭ����";
		          break;
		      case 4:
		    	  Today = "������";
		          break;
		      case 5:
		    	  Today = "����� ";
		          break;
		      case 6:
		    	  Today = "�ݿ���";
		          break;
		    }   
		 return Today;
		    
	}
	
    public void UpdatePlayerHPMP(Player player)
    {
		if(Bukkit.getPluginManager().isPluginEnabled("MagicSpells") == true
		&&Main.MagicSpellsCatched==true)
		{
			OtherPlugins.SpellMain MS = new OtherPlugins.SpellMain();
			MS.setPlayerMaxAndNowMana(player);
		}
		GoldBigDragon_RPG.Event.Damage d = new GoldBigDragon_RPG.Event.Damage();
		YamlController Main_YC = GoldBigDragon_RPG.Main.Main.YC_2;
		YamlManager PlayerStats  = Main_YC.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");
		Damageable p = player;
		
		int BonusHealth = d.getPlayerEquipmentStat(player, "�����")[0];
		int MaxHealth = PlayerStats.getInt("Stat.MAXHP")+BonusHealth;
		if(MaxHealth > 0)
			p.setMaxHealth(MaxHealth);
		return;
    }
    
    public void SlotChangedUpdatePlayerHPMP(Player player, ItemStack newSlot)
    {
		if(Main.MagicSpellsCatched == true)
		{
			OtherPlugins.SpellMain MS = new OtherPlugins.SpellMain();
			MS.setSlotChangePlayerMaxAndNowMana(player,newSlot);
		}
		GoldBigDragon_RPG.Event.Damage d = new GoldBigDragon_RPG.Event.Damage();
		YamlController Main_YC = GoldBigDragon_RPG.Main.Main.YC_2;
		YamlManager PlayerStats  = Main_YC.getNewConfig("Stats/"+player.getUniqueId().toString()+".yml");
		Damageable p = player;
		
		int BonusHealth = d.getSlotChangedPlayerEquipmentStat(player, "�����",newSlot)[0];
		int MaxHealth = PlayerStats.getInt("Stat.MAXHP")+BonusHealth;

		if(MaxHealth > 0)
			p.setMaxHealth(MaxHealth);
		return;
    }
}
