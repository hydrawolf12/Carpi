import java.awt.Rectangle;

public abstract class Entity
{
	private int health, speed, xPos, yPos, direction;
	private Rectangle hitbox;
	
	public Entity(int hp, int sp, int x, int y, int topX, int topY, int hbW, int hbH)
	{
		health = hp;
		speed = sp;
		xPos = x;
		yPos = y;
		direction = 0;
		
		hitbox = new Rectangle(topX, topY, hbW, hbH);
	}
	
	public Rectangle getHitbox()
	{
		return hitbox;
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
	public void setxPos(int a)
	{
		xPos += a;
	}
	public void setyPos(int a)
	{
		yPos += a;
	}
	public int getDirection()
	{
		return direction;
	}
	
	public abstract void move();
}
