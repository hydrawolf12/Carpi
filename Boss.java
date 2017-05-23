
public class Boss extends Zombie
{
	public int health;
	public Boss(int speed, int xPos, int yPos, int hp) //initiates boss based on speed position and health
	{
		super(speed, xPos, yPos);
		health = hp;
		//reduce zomb spawn rate
	}
	public void setHealth(int a) //decrement hp by player's bossDamage
	{
	}
	public void remove() // removes boss from field, upgrades player, returns spawn rate to normal
	{
	}
	
}
