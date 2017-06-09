public class Spawner
{
	private static int currentSpeed;
	private static int currentHealth;
	private static int rate;
	private Board board;
	private static Player mccree;
	
	public Spawner(Board a)
	{
		currentSpeed = 1;
		currentHealth = 1;
		rate = 1;
		mccree = player;
		board = a;
	}
	public static void incrementSpeed(int a)
	{
		currentSpeed += a;
	}
	
	public static void incrementHealth(int a)
	{
		currentHealth += a;
	}
	
	public static void incrementRate(int a)
	{
		rate += a;
	}
	
	public static void returnRate() //IN MILLISECONDS, NOT SECONDS.  SO MULTIPLE WHATEVER IT CURRENT IT IS BY 1000.
	{
		return rate;
	}
	public void spawnBoss()
	{
		Boss baby = new Boss(currentHealth * 5, currentSpeed * 3, getxEnd() / 2 + 25, 25, 50, 50, getxEnd / 2, 0, mccree);
		baby.calcAng();
		Board.addZomb(baby);
		
	}
	public void spawnZombie()
	{
		int x,y;
		int a = (int)(Math.random() * 8) + 1;
		if (a == 1)
		{
			x = frameWidth / 8;
			y = 1;
		}
		else if(a == 2)
		{
			x = frameWidth - frameWidth / 8;
			y = 1;
		}
		else if(a == 3)
		{
			x = frameWidth;
			y = frameHeight / 8;
		}
		else if(a == 4)
		{
			x = frameWidth;
			y = frameHeight - frameHeight / 8;
		}
		else if(a == 5)
		{
			x = frameWidth - frameWidth / 8;
			y = frameHeight;
		}
		else if(a == 6)
		{
			x = frameWidth / 8;
			y = frameHeight;
		}
		else if(a == 7)
		{
			x = 1;
			y = frameHeight - frameHeight / 8;
		}
		else if(a == 8)
		{
			x = 1;
			y = frameHeight / 8;
		}
		Zombie zimbabwe = new Zombie(currentHealth, currentSpeed, x, y, x - 25,y - 25, 50, 50, mccree);
		zimbabwe.calcAng();
		Board.addZomb(zimbabwe);
		
	}

}
