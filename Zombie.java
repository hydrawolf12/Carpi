import java.util.ArrayList;

public class Zombie extends Entity
{

	private Player player;
	private ArrayList<Zombie> currentZombs;
	
	public Zombie(int health, int speed, int x, int y, int w, int h, int topL, int topR, Player mccree, ArrayList<Zombie> zombieList) //public Zombie(int hp, int sp, int x, int y, Player mccree)// creates zombie speed and position
	{
		super(health, speed, x, y, w, h, topL, topR);
		player = mccree;
		currentZombs = zombieList;
	}
	
	public void move()// moves every tick
	{
		
		if (direction == 1)
			  yPos--;
		else if (direction == 2)
		{
			  yPos--;
			  xPos++;
		}
		else if (direction == 3)
			  xPos++;
		else if (direction == 4)
		{
			  yPos++;
			  xPos++;
		}
		else if (direction == 5)
			  yPos--;
		else if (direction == 6)
		{
			  yPos++;
			  xPos--;
		}
		else if (direction == 7)
			  xPos--;
		else if (direction == 8)
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
