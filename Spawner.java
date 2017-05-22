
public class Spawner
{
	public static int currentSpeed;
	public static int rate;
	
	public void incrementSpeed(int a)
	{
		currentSpeed += a;
	}
	
	public void setRate(int a)
	{
		rate = a;
	}
	
	public void spawnZombie(int speed)
	{
		int a = (int)(Math.random() * 8) + 1;
		if (a == 1)
		{
		}
		else if(a == 2)
		{
			
		}
		// Zombie uhhh = new Zombie(speed, xPos, yPos)
	}

}
