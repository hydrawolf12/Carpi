import java.awt.Rectangle;

public class Boss extends Zombie
{
	public Boss(int health, int speed, int x, int y, int w, int h, int topL, int topR, Player mccree) //initiates boss based on speed position and health
	{
		super(health, speed, x, y, w, h, topL, topR, mccree);
	}
	
	public void remove(int pos, Player mccree) // removes boss from field, upgrades player, returns spawn rate to normal
	{
		currentZombs.remove(pos);
		Spawner.currentHealth++;
		Spawner.currentSpeed++;
		bossKillCount++;
		if (bossKillCount % 2 == 0)
			mccree.health ++;
		else if (bossKillCount % 2 == 1)
		{
			mccree.damage++;
			mccree.fireRate++;
		}
	}
	
}
