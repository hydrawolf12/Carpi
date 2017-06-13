import java.awt.Rectangle;
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
	
	public int returnWidth()
	{
		return 25;
	}
	public void move()// moves every tick
	{
		boolean canUP = this.getYPos() > 1;
		boolean canDOWN = this.getYPos() < board.getyEnd() - 1;
		boolean canLEFT = this.getXPos() > 1;
		boolean canRIGHT =  this.getXPos() < board.getxEnd() - 1;
		
		if (this.getDirection() == 90 && canUP)
			  this.setYPos(-1);
		else if (this.getDirection() == 45 && canUP && canRIGHT)
		{
			  this.setYPos(-1);
			  this.setXPos(1);
		}
		else if (this.getDirection() == 0 && canRIGHT)
			  this.setXPos(1);
		else if (this.getDirection() == 315 && canDOWN && canRIGHT)
		{
			  this.setYPos(1);
			  this.setXPos(1);
		}
		else if (this.getDirection() == 270 && canDOWN)
			  this.setYPos(1);
		else if (this.getDirection() == 225 && canDOWN && canLEFT)
		{
			  this.setYPos(1);
			  this.setXPos(-1);
		}
		else if (this.getDirection() == 180 && canLEFT)
			  this.setXPos(-1);
		else if (this.getDirection() == 135 && canUP && canLEFT)
		{
			  this.setYPos(-1);
			  this.setXPos(-1);
		}
			this.setHitbox(new Rectangle(this.getXPos() - 25, this.getYPos() - 25, 50, 50));
			this.calcAng();
	}
	public void calcAng()// called when player moves
	{
		Player player = board.getPlayer();
		double angle = (double)Math.toDegrees(Math.atan2(player.getYPos() - this.getYPos(), player.getXPos() - this.getXPos()));

	    if(angle < 0)
	    {
	        angle += 360;
	    }
	    
	    if (angle < 45)
	    {
	    	this.setDirection(0);
	    }
	    else if(angle < 90)
	    {
	    	this.setDirection(45);
	    }
	    else if(angle < 135)
	    {
	    	this.setDirection(90);
	    }
	    else if(angle < 180)
	    {
	    	this.setDirection(135);
	    }
	    else if(angle < 225)
	    {
	    	this.setDirection(180);
	    }
	    else if(angle < 270)
	    {
	    	this.setDirection(225);
	    }
	    else if(angle < 315)
	    {
	    	this.setDirection(270);
	    }
	    else if(angle < 360)
	    {
	    	this.setDirection(315);
	    }
	}
	public void collisionDetect()
	{
		for (Zombie z: board.getZombies())
		{
			if(z.getHitbox().intersects(board.getPlayer().getHitbox()))
			{
				if(!board.getPlayer().getInvin())
				{
					board.getPlayer().toggleInvin();
					z.attack();
				}
			}
		}
	}
	public void attack()//attacks if hitboxes overlap w/ player
	{
		board.getPlayer().setHealth(-1);
		board.getPlayer().checkHP();
	}
	public boolean takeDamage(int a, int pos)
	{
		this.setHealth(-1);
		if (this.getHealth() <= 0)
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
