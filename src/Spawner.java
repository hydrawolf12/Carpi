public class Spawner
{
	private static int currentSpeed;
	private static int currentHealth;
	private static double rate;
	private Board board;
	private double zombieT;
	// private static Player mccree;
	
	public Spawner(Board a)
	{
		currentSpeed = 1;
		currentHealth = 1;
		rate = 2;
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
	
	public static void incrementRate(double a)
	{
		rate *= a;
	}
	
	public static double returnRate() //IN MILLISECONDS, NOT SECONDS.  SO MULTIPLE WHATEVER IT CURRENT IT IS BY 1000.
	{
		return rate;
	}
	public Boss spawnBoss()
	{
		Boss baby = new Boss(currentHealth * 5, currentSpeed, board.getxEnd() / 2, 30, 70, 70, board);
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
		Zombie zimbabwe = new Zombie(currentHealth, currentSpeed, x, y, 45, 45, board);
		zimbabwe.calcAng();
		return zimbabwe;
		
	}
	public void spawn(double delta) {
		zombieT += delta;
		if (zombieT >= rate) {
			System.out.println("spawn has been called");
			System.out.println("The killCount is " + board.returnKillCount());
			if (board.returnKillCount() >= 30) {
				board.getCurrentZombs().add(spawnBoss());
				board.setKillCount(0);
				System.out.println("A zombie boss has spawned");
			} else {
				board.getCurrentZombs().add(spawnZombie());
				System.out.println("A zombie has spawned");
			}
			zombieT = 0;
		}
	}
}
