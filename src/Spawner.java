public class Spawner
{
	private static int currentSpeed;
	private static int currentHealth;
	private static double rate;
	private Board board;
	private double zombieT;
	
	//initializes Spawner object and sets base speed, health, and spawnrate for zombie objects
	public Spawner(Board a)
	{
		currentSpeed = 1;
		currentHealth = 1;
		rate = 3;
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
	
	public static void incrementRate(double a)
	{
		rate *= a;
	}
	
	public static double returnRate()
	{
		return rate;
	}
	
	//initializes a Boss object with 5 times the health of normal zombies, at the top of the screen, and with hitbox size 70x70
	//calls calcAng() method and returns Boss object to be added to ArrayList
	public Boss spawnBoss()
	{
		Boss baby = new Boss(currentHealth * 5, currentSpeed, board.getxEnd() / 2, 30, 70, 70, board);
		baby.calcAng();
		return baby;
	}
	
	//initializes Zombie at a randomly selected position
	//calls calcAng method and returns Zombie object to be added to ArrayList
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
		Zombie zimbabwe = new Zombie(currentHealth, currentSpeed, x, y, 50, 50, board);
		zimbabwe.calcAng();
		return zimbabwe;
	}
	
	//increments timer by delta value and spawns a zombie if needed
	//spawns a Boss if the killCount of zombies is >= 30 and resets the killCount as well as timer
	public void spawn(double delta)
	{
		zombieT += delta;
		if (zombieT >= rate)
		{
			System.out.println("spawn has been called");
			System.out.println("The killCount is " + board.returnKillCount());
			if (board.returnKillCount() >= 30)
			{
				board.getCurrentZombs().add(spawnBoss());
				board.setKillCount(0);
				System.out.println("A zombie boss has spawned");
			}
			else
			{
				board.getCurrentZombs().add(spawnZombie());
				System.out.println("A zombie has spawned");
			}
			zombieT = 0;
		}
	}
}
