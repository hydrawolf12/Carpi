import java.awt.Rectangle;

public class Boss extends Zombie
{
	private int bossKillCount = 0;
	// private boolean isBossDefeated;
	private Timer spawn = Board.returnZombieSpawner();
	public Boss(int health, int speed, int x, int y, Player mccree) //initiates boss based on speed position and health
	{
		super(health, speed, x, y, x - 25, y - 25, 50, 50, mccree);
		// isBossDefeated = false;
	}
	
	
	/*public static boolean returnIsBossDefeated()
	{
		return isBossDefeated;
	}
	*/
	public void remove(int pos) // removes boss from field, upgrades player, returns spawn rate to normal
	{
		currentZombs.remove(pos);
		//isBossDefeated = true;
		Spawner.incrementHealth(2);
		Spawner.incrementSpeed(1);
		Spawner.incrementRate(1);
		bossKillCount++;
		spawn.setDelay(Spawner.returnRate());
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
