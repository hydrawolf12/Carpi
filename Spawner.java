public class Spawner
{
	private static int currentSpeed;
	private static int currentHealth;
	private static int rate;
	private Board board;
	// private static Player mccree;
	
	public Spawner(Board a)
	{
		currentSpeed = 1;
		currentHealth = 1;
		rate = 1;
		// mccree = player;
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
	public Boss spawnBoss()
	{
		Boss baby = new Boss(currentHealth * 5, currentSpeed * 3, board.getxEnd() / 2, 30, board);
		baby.calcAng();
		//board.addZomb(baby);
		return baby;
		
	}
	public Zombie spawnZombie()
	{
		int x = 0;
		int y = 0;
		int frameWidth = board.getxEnd();
		int frameHeight = board.getyEnd();
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
		Zombie zimbabwe = new Zombie(currentHealth, currentSpeed, x, y, board);
		zimbabwe.calcAng();
		return zimbabwe;
		
	}

}
