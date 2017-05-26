import java.awt.Rectangle;

public abstract class Entity
{
	public int health, speed, xPos, yPos, direction;
	public Rectangle hitbox;

	public void setDirection(int a)
	{
		direction = a;
	}

	public abstract void move();
}
