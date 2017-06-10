import java.util.ArrayList;

public class Zombie extends Entity
{

	// private Player player;
	private Board board;
	// private static ArrayList<Zombie> currentZombs = Board.returnCurrentZombs();
	private static int killCount = Board.returnKillCount();
	
	public Zombie(int health, int speed, int x, int y, Board playBoard) //public Zombie(int hp, int sp, int x, int y, Player mccree)// creates zombie speed and position
	{
		super(health, speed, x, y, x - 25, y - 25, 50, 50);
		board = playBoard;
		// currentZombs = zombieList;
	}
	
	
	public void move()// moves every tick
	{
		boolean canUP = this.getyPos() > 1;
		boolean canDOWN = this.getyPos() < board.getyEnd() - 1;
		boolean canLEFT = this.xPos() > 1;
		boolean canRIGHT =  this.getxPos() < board.getxEnd() - 1;
		
		if (direction == 90 && canUP)
			  yPos--;
		else if (direction == 45 && canUP && canRIGHT)
		{
			  yPos--;
			  xPos++;
		}
		else if (direction == 0 && canRIGHT)
			  xPos++;
		else if (direction == 315 && canDOWN && canRIGHT)
		{
			  yPos++;
			  xPos++;
		}
		else if (direction == 270 && canDOWN)
			  yPos--;
		else if (direction == 225 && canDOWN && canLEFT)
		{
			  yPos++;
			  xPos--;
		}
		else if (direction == 180 && canLEFT)
			  xPos--;
		else if (direction == 135 && canUP && canLEFT)
		{
			  yPos--;
			  xPos--;
		}
			hitbox = new Rectangle(x - 25, y - 25, 50, 50);
			this.calcAng();
	}
	public void calcAng()// called when player moves
	{
		Player player = board.getPlayer();
		double angle = (double)Math.toDegrees(Math.atan2(player.getYpos - yPos, player.getXpos - xPos));

	    if(angle < 0)
	    {
	        angle += 360;
	    }
	    
	    if (angle < 45)
	    {
	    	direction = 0;
	    }
	    else if(angle < 90)
	    {
	    	direction = 45;
	    }
	    else if(angle < 135)
	    {
	    	direction = 90;
	    }
	    else if(angle < 180)
	    {
	    	direction = 135;
	    }
	    else if(angle < 225)
	    {
	    	direction = 180;
	    }
	    else if(angle < 270)
	    {
	    	direction = 225;
	    }
	    else if(angle < 315)
	    {
	    	direction = 270;
	    }
	    else if(angle < 360)
	    {
	    	direction = 315;
	    }
	}
	public void collisionDetect()
	{
		for (Zombie z: currentZombs)
		{
			if(z.hitbox.intersects(player.getHitbox()))
			{
				if(!player.getInvin())
				{
					player.toggleInvin();
					z.attack();
				}
			}
		}
	}
	public void attack()//attacks if hitboxes overlap w/ player
	{
		player.updateHP(-1);
		player.checkHP();
	}
	public boolean takeDamage(int a, int pos)
	{
		health -= a;
		if (health <= 0)
		{
			return true;
		} 
		return false;
	}
	//public boolean checkHP()
	//{
	//	if (health <= 0)
	//		return true;
	//	else
	//		return false;
	//}
	/*public void remove(int pos)// removes zombie from ArrayList when collides w/ bullet
	{
		currentZombs.remove(pos);
		Board.setKillCount(1);
	} */
	
}
