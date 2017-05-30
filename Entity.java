import java.awt.Rectangle;

public abstract class Entity
{
	public int health, speed, xPos, yPos, direction;
	public Rectangle hitbox;
	
	public Entity(int hp, int sp, int x, int y, int hbW, int hbH, int topLeft, int topRight)
	{
		health = hp;
		speed = sp;
		xPos = x;
		yPos = y;
		direction = 1;
		hitbox = new Rectangle(hbW, hbH, topLeft, topRight);
	}
	public abstract void move();
}
