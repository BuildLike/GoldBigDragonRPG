package GBD.GoldBigDragon_Advanced.Util;

import java.util.Random;
//�ڹ� ���� ���̺귯�� ��, ���� �Լ��� ȣ����.

public class Number
{
    public boolean isNumeric(String str)
    //�����ΰ��� �˾Ƴ� �ִ� �޼ҵ�
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
	
	public Random random = new Random();
	//���� �Լ��� ȣ���Ͽ� random�̶� �� �̸����� �����ϴ� �ܶ�
	
	
	//�ּ� ~ �ִ� �� ��, ������ ���� �����ϴ� �޼ҵ�//
	public int RandomNum(int min, int max)
    {
		if(min<=max)
		return random.nextInt((int) (max-min+1))+min;
		else
		return random.nextInt((int) (min-max+1))+max;
    }

    public boolean RandomPercent(double percent)
	{
		if (Math.random() <= percent)
		return true;
		return false;
	}
}
