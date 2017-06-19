import java.awt.Rectangle;
public class Zombie extends Entity
{
	private Board board;
	private int widtha, heighta;
	private boolean isHit = false;

	//initializes zombie object based on health, speed, position, and hitbox size
	public Zombie(int health, int speed, int x, int y, int width, int height, Board playBoard)
	{
		super(health, speed, x, y, x - width/2, y - height/2, width, height);
		widtha = width;
		heighta = height;
		board = playBoard;
	}
	public Board returnBoard()
	{
		return board;
	}
	public int returnWidth()
	{
		return 25;
	}
	
	//moves zombie object based on the angle calculated in calcAng() if it is not at the border of the map
	//redraws hitbox at new positions and calls calcAng() again
	public void move()// moves every tick
	{
		boolean canUP = this.getYPos() > 25;
		boolean canDOWN = this.getYPos() < board.getyEnd() - 25;
		boolean canLEFT = this.getXPos() > 25;
		boolean canRIGHT =  this.getXPos() < board.getxEnd() - 25;
		int distance = this.getSpeed();
		
		if (this.getDirection() == 0 && canUP)
			this.setYPos(distance * -1);
		else if (this.getDirection() == 45 && canUP && canRIGHT)
		{
			this.setYPos(distance * -1);
			this.setXPos(distance);
		}
		else if (this.getDirection() == 90 && canRIGHT)
			this.setXPos(distance * 2);
		else if (this.getDirection() == 135 && canDOWN && canRIGHT)
		{
			this.setYPos(distance);
			this.setXPos(distance);
		}
		else if (this.getDirection() == 180 && canDOWN)
			this.setYPos(distance * 2);
		else if (this.getDirection() == 225 && canDOWN && canLEFT)
		{
			this.setYPos(distance);
			this.setXPos(distance * -1);
		}
		else if (this.getDirection() == 270 && canLEFT)
			this.setXPos(distance * -2);
		else if (this.getDirection() == 315 && canUP && canLEFT)
		{
			this.setYPos(distance * -1);
			this.setXPos(distance * -1);
		}
		this.setHitbox(new Rectangle(this.getXPos() - widtha / 2, this.getYPos() - heighta / 2, widtha, heighta));
		this.calcAng();
	}
	
	//called whenever a Zombie or Player object moves
	//uses the difference in x and y pos of zombie/player to calculate the degree angle between the two objects
	//and sets direction depending on angle range
	public void calcAng()
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
	
	//checks if any zombies in the ArrayList are colliding with a player hitbox
	//toggles player's invincibility and calls zombie attack() method
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
	
	//decrements player health by 1 and checks if player has died
	public void attack()
	{
		board.getPlayer().setHealth(-1);
		board.getPlayer().checkHP();
		System.out.println("The player health is: " + board.getPlayer().getHealth());
	}
	
	//decrements zombie health by 1
	//returns true if the zombie has died so it can be removed from the ArrayList
	public boolean takeDamage(int a)
	{
		this.setHealth(-1);
		if (this.getHealth() <= 0)
		{
			return true;
		}
		return false;
	}
	
	public void setHit(boolean b)
	{
		isHit = b;
 	}
	

	
	public int returnRadius()
	{
		return 25;
	}

}
