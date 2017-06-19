import java.awt.Rectangle;
import javax.swing.Timer;

public class Boss extends Zombie
{
	private static int bossKillCount = 0;
	
	 //initializes boss based on speed, position, hitbox size and health
	public Boss(int health, int speed, int x, int y, int width, int height, Board playBoard)
	{
		super(health, speed, x, y, width, height, playBoard);
	}
	
	//decrements Boss health by int a and returns true if the Bossis killed. Increments zombies' health, speed,
	//and spawn rate and player's health, damage, and firerate in alternating order every 3 boss kills.
	public boolean takeDamage(int a)
	{
		this.setHealth(-a);
		if (this.getHealth() <= 0)
		{
			Player mccree = this.returnBoard().getPlayer();
			bossKillCount++;
			if (bossKillCount % 3 == 0)
			{
				Spawner.incrementHealth(1);
				mccree.setHealth(1); // int a subject to change
			}
			else if (bossKillCount % 3 == 2)
			{
				mccree.updateDamage(1);
				Spawner.incrementSpeed(1);
			}
			else if (bossKillCount % 3 == 1)
			{
				Spawner.incrementHealth(1);
				mccree.updateFireRate(0.75);
				Spawner.incrementRate(0.75);
			}
			return true;
		} 
		return false;
	}
	
	//returns the radius of the Boss zombie object
	public int returnRadius() 
	{
		return 35;
	}
}
