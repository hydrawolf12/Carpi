import java.awt.Rectangle;

public abstract class Entity
{
	private int health, speed, xPos, yPos, direction;
	private Rectangle hitbox;
	
	public Entity(int hp, int sp, int x, int y, int hbW, int hbH, int topX, int topY)
	{
		health = hp;
		speed = sp;
		xPos = x;
		yPos = y;
		direction = 1;
		
		hitbox = new Rectangle(topX, topY, hbW, hbH);
	}
	
	public int getHealth()
	{
		return health;
	}
	public int getSpeed()
	{
		return speed;
	}
	public int getxPos()
	{
		return xPos;
	}
	public int getyPos()
	{
		return yPos;
	}
	public int getDirection()
	{
		return direction;
	}
	
	public abstract void move();
}
