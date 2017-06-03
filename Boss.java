import java.awt.Rectangle;

public class Boss extends Zombie
{
	public Boss(int health, int speed, int x, int y, int w, int h, int topX, int topY, int width, int height, Player mccree) //initiates boss based on speed position and health
	{
		super(health, speed, x, y, w, h, topX, topY, width, height mccree);
	}
	
	public void remove(int pos, Player mccree) // removes boss from field, upgrades player, returns spawn rate to normal
	{
		currentZombs.remove(pos);
		Spawner.currentHealth++;
		Spawner.currentSpeed++;
		bossKillCount++;
		if (bossKillCount % 3 == 0)
			mccree.updateHP(1); // int a subject to change
		else if (bossKillCount % 3 == 2)
		{
			mccree.updateDamage(5);
		}
		else if (bossKillCount % 3 == 1)
		{
			mccree.updateFireRate(2);
		}
	}
	
}
