import java.awt.Rectangle;

public class Boss extends Zombie
{
	private int bossKillCount = 0;
	// private boolean isBossDefeated;
	private Timer spawn;
	private Board board;
	public Boss(int health, int speed, int x, int y, Board playBoard) //initiates boss based on speed position and health
	{
		super(health, speed, x, y, playBoard);
		// isBossDefeated = false;'
		spawn = playBoard.returnZombieSpawner();
		board = playBoard;
	}
	
	
	/*public static boolean returnIsBossDefeated()
	{
		return isBossDefeated;
	}
	*/
	/*public void remove(int pos) // removes boss from field, upgrades player, returns spawn rate to normal
	{
		Player mccree = board.getPlayer();
		// currentZombs.remove(pos);
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
	} */
	public int returnWidth()
	{
		return 35;
	}
	public boolean takeDamage(int a, int pos)
	{
		this.setHealth(a);
		if (this.getHealth() <= 0)
		{
			Player mccree = board.getPlayer();
			Spawner.incrementHealth(2);
			Spawner.incrementSpeed(1);
			Spawner.incrementRate(1);
			bossKillCount++;
			spawn.setDelay(Spawner.returnRate());
			if (bossKillCount % 3 == 0)
				mccree.setHealth(1); // int a subject to change
			else if (bossKillCount % 3 == 2)
			{
				mccree.updateDamage(2);
			}
			else if (bossKillCount % 3 == 1)
			{
				mccree.updateFireRate(2);
			}
			return true;
		} 
		return false;
	}
	
}
