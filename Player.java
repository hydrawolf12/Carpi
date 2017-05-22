
public class Player extends Character
{
	private Gun currentWep;
	public boolean canShoot;
	public boolean isInvin;
	private int bossDamage;

	public Player() //constructs player hitbox + spawns in center of map
	{
	}
	public void move() //moves player
	{	
	}
	
	public void shoot() //spawns bullet
	{	
	}
	
	public boolean checkHP() //checks hp ends game if < 0
	{	
	}
	
	public void setWeapon(int input) //what variable type is the keyboard input?
	{	
	}
	
	public void updateHP(int a) //updates hp by a
	{	
	}
	
	public void updateDamageFireRate() // updates damage and firerate
	{	
	}
	
	public void toggleInvin()
	{
		isInvin = !isInvin;
	}
	

}
