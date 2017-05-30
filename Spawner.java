public class Spawner
{
	public static int currentSpeed;
	public static int currentHealth;
	public static int rate;
	
	public void incrementSpeed(int a)
	{
		currentSpeed += a;
	}
	
	public void incrementHealth(int a)
	{
		currentHealth += a;
	}
	
	public void setRate(int a)
	{
		rate = a;
	}
	
	public void spawnBoss(Player mccree)
	{
		Boss baby = new Boss(currentHealth * 5, currentSpeed * 3, 175, 175, mccree);
		baby.calcAng();
		currentZombs.add(baby);
		
	}
	public void spawnZombie(Player mccree)
	{
		int x,y;
		int a = (int)(Math.random() * 8) + 1;
		if (a == 1)
		{
			x = 43;
			y = 0;
		}
		else if(a == 2)
		{
			x = 307;
			y = 0;
		}
		else if(a == 3)
		{
			x = 350;
			y = 43;
		}
		else if(a == 4)
		{
			x = 350;
			y = 307;
		}
		else if(a == 5)
		{
			x = 307;
			y = 350;
		}
		else if(a == 6)
		{
			x = 43;
			y = 350;
		}
		else if(a == 7)
		{
			x = 0;
			y = 307;
		}
		else if(a == 8)
		{
			x = 0;
			y = 43;
		}
		Zombie zimbabwe = new Zombie(currentHealth, currentSpeed, x, y, mccree);
		zimbabwe.calcAng();
		currentZombs.add(zimbabwe);
		
	}

}
