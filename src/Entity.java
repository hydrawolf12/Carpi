import java.awt.Rectangle;

public abstract class Entity
{
	private int health, speed, xPos, yPos, direction;
	private Rectangle hitbox;
	
	//initializes Entity object based on health, speed, x pos, y pos, hitbox origin corner, and hitbox height / width
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
	public void setHitbox(Rectangle ricky)
	{
		hitbox = ricky;
	}
	public int getHealth()
	{
		return health;
	}
	public void setHealth(int a)
	{
		health += a;
	}
	public int getSpeed()
	{
		return speed;
	}
	public int getXPos()
	{
		return xPos;
	}
	public int getYPos()
	{
		return yPos;
	}
	public void setXPos(int a)
	{
		xPos += a;
	}
	public void setYPos(int a)
	{
		yPos += a;
	}
	public int getDirection()
	{
		return direction;
	}
	public void setDirection(int x)
	{
		direction = x;
	}
	
	//abstract method move for all Entity objects to implement
	public abstract void move();
}
