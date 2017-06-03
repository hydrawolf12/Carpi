import java.util.ArrayList;

public class Zombie extends Entity
{

	private Player player;
	private ArrayList<Zombie> currentZombs;
	
	public Zombie(int health, int speed, int x, int y, int w, int h, int topX, int topY, int width, int height Player mccree, ArrayList<Zombie> zombieList) //public Zombie(int hp, int sp, int x, int y, Player mccree)// creates zombie speed and position
	{
		super(health, speed, x, y, w, h, topX, topY, width, height);
		player = mccree;
		currentZombs = zombieList;
	}
	
	public void move()// moves every tick
	{
		canUP = yPos > 1;
		canDOWN = yPos < frameHeight - 1;
		canLEFT = xPos > 1;
		canRIGHT =  xPos < frameWidth - 1;
		
		if (direction == 1 && canUP)
			  yPos--;
		else if (direction == 2 && canUP && canRIGHT)
		{
			  yPos--;
			  xPos++;
		}
		else if (direction == 3 && canRIGHT)
			  xPos++;
		else if (direction == 4 && canDOWN && canRIGHT)
		{
			  yPos++;
			  xPos++;
		}
		else if (direction == 5 && canDOWN)
			  yPos--;
		else if (direction == 6 && canDOWN && canLEFT)
		{
			  yPos++;
			  xPos--;
		}
		else if (direction == 7 && canLEFT)
			  xPos--;
		else if (direction == 8 && canUP && canLEFT)
		{
			  yPos--;
			  xPos--;
		}
		this.calcAng();
	}
	public void calcAng()// called when player moves
	{
		double angle = (double)Math.toDegrees(Math.atan2(player.yPos - yPos, player.xPos - xPos));

	    if(angle < 0)
	    {
	        angle += 360;
	    }
	    
	    if (angle < 45)
	    {
	    	direction = 1;
	    }
	    else if(angle < 90)
	    {
	    	direction = 2;
	    }
	    else if(angle < 135)
	    {
	    	direction = 3;
	    }
	    else if(angle < 180)
	    {
	    	direction = 4;
	    }
	    else if(angle < 225)
	    {
	    	direction = 5;
	    }
	    else if(angle < 270)
	    {
	    	direction = 6;
	    }
	    else if(angle < 315)
	    {
	    	direction = 7;
	    }
	    else if(angle < 360)
	    {
	    	direction = 8;
	    }
	}
	public void collisionDetect()
	{
		for (Zombie z: currentZombs)
		{
			if(z.hitbox.intersects(player.hitbox))
			{
				if(!player.isInvin)
				{
					player.toggleInvin();
					z.attack();
				}
			}
		}
	}
	public void attack()//attacks if hitboxes overlap w/ player
	{
		player.health--;
		player.checkHP();
	}
	public void takeDamage(int a, int pos)
	{
		health -= a;
		if (health <= 0)
		{
			currentZombs.remove(pos);
			killCount++;
		}
	}
	//public boolean checkHP()
	//{
	//	if (health <= 0)
	//		return true;
	//	else
	//		return false;
	//}
	//public void remove(int pos)// removes zombie from ArrayList when collides w/ bullet
	//{
	//	currentZombs.remove(pos);
	//	killCount++;
	//}
	
}
