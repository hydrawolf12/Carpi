import java.awt.Rectangle;

public class Boss extends Zombie
{
	public Boss(int health, int speed, int x, int y, Player mccree) //initiates boss based on speed position and health
	{
		super(health, speed, x, y, x - 25, y - 25, 50, 50, mccree);
	}
	
	public void remove(int pos) // removes boss from field, upgrades player, returns spawn rate to normal
	{
		currentZombs.remove(pos);
		Spawner.incrementHealth(2);
		Spawner.incrementSpeed(1);
		Spawner.incrementRate(1);
		bossKillCount++;
		if (bossKillCount % 3 == 0)
			mccree.updateHP(1); // int a subject to change
		else if (bossKillCount % 3 == 2)
		{
			mccree.updateDamage(2);
		}
		else if (bossKillCount % 3 == 1)
		{
			mccree.updateFireRate(2);
		}
	}
	
}
