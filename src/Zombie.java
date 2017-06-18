import java.awt.Rectangle;
public class Zombie extends Entity
{

	// private Player player;
	private Board board;
	private int widtha, heighta;
	// private static ArrayList<Zombie> currentZombs = Board.returnCurrentZombs();
	//private static int killCount = Board.returnKillCount();

	public Zombie(int health, int speed, int x, int y, int width, int height, Board playBoard) //public Zombie(int hp, int sp, int x, int y, Player mccree)// creates zombie speed and position
	{
		super(health, speed, x, y, x - width/2, y - height/2, width, height);
		widtha = width;
		heighta = height;
		board = playBoard;
		// currentZombs = zombieList;
	}
	public Board returnBoard()
	{
		return board;
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

		if (this.getDirection() == 0 && canUP)
			this.setYPos(-2);
		else if (this.getDirection() == 45 && canUP && canRIGHT)
		{
			this.setYPos(-1);
			this.setXPos(1);
		}
		else if (this.getDirection() == 90 && canRIGHT)
			this.setXPos(2);
		else if (this.getDirection() == 135 && canDOWN && canRIGHT)
		{
			this.setYPos(1);
			this.setXPos(1);
		}
		else if (this.getDirection() == 180 && canDOWN)
			this.setYPos(2);
		else if (this.getDirection() == 225 && canDOWN && canLEFT)
		{
			this.setYPos(1);
			this.setXPos(-1);
		}
		else if (this.getDirection() == 270 && canLEFT)
			this.setXPos(-2);
		else if (this.getDirection() == 315 && canUP && canLEFT)
		{
			this.setYPos(-1);
			this.setXPos(-1);
		}
		this.setHitbox(new Rectangle(this.getXPos() - widtha / 2, this.getYPos() - heighta / 2, widtha, heighta));
		this.calcAng();
	}
	public void calcAng()// called when player moves
	{
		Player player = board.getPlayer();
		double angle = (double)Math.toDegrees(Math.atan2(player.getYPos() - this.getYPos(), player.getXPos() - this.getXPos()));
		int xDiff = this.getXPos() - player.getXPos();
		int yDiff = this.getYPos() - player.getYPos();
		
	    if(angle < 0)
	    {
	        angle += 360;
	    }
	    
	    if (xDiff == 0 && yDiff < 0) // above
	    {
	    	this.setDirection(180);
	    }
	    else if(xDiff == 0 && yDiff > 0) // below
	    {
	    	this.setDirection(0);
	    }
	    else if(yDiff == 0 && xDiff < 0) // left
	    {
	    	this.setDirection(90);
	    }
	    else if(yDiff == 0 && xDiff > 0) // right
	    {
	    	this.setDirection(270);
	    }
	    else
	    {
	    	/*if (xDiff < 0 && yDiff < 0) // top left
	    	{
	    		angle += 90;
	    	}
	    	if(xDiff > 0 && yDiff < 0) // top right
	    	{
	    		angle += 90;
	    	}
	    	if(xDiff > 0 && yDiff > 0) //bottom right
	    	{
	    		angle += 90;
	    	}*/
	    	angle += 90;
	    	angle = angle % 360;
	    
	        
	    	if (angle > 22.5 && angle <= 67.5)
	    	{
	    		this.setDirection(45);
	    	}
	    	else if(angle > 67.5 && angle <= 112.5)
	    	{
	    		this.setDirection(90);
	    	}
	    	else if(angle > 112.5 && angle <= 157.5)
	    	{
	    		this.setDirection(135);
	    	}
	    	else if(angle > 157.5 && angle <= 202.5)
	    	{
	    		this.setDirection(180);
	    	}
	    	else if(angle > 202.5 && angle <= 247.5)
	    	{
	    		this.setDirection(225);
	    	}
	    	else if(angle > 247.2 && angle <= 292.5)
	    	{
	    		this.setDirection(270);
	    	}
	    	else if(angle > 292.5 && angle <= 337.5)
	    	{
	    		this.setDirection(315);
	    	}
	    	else if (angle > 337.5 && angle <= 22.5)
	    	{
	    		this.setDirection(0);
	    	}
	   }
	}
	public void collisionDetect(double delta)
	{
		for (Zombie z: board.getCurrentZombs())
		{
			if(z.getHitbox().intersects(board.getPlayer().getHitbox()))
			{
				if(!board.getPlayer().getInvin())
				{
					board.getPlayer().changeInvin();
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
	public boolean takeDamage(int a)
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


	public int returnRadius() {
		// TODO Auto-generated method stub
		return 25;
	}

}
