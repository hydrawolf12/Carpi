import java.awt.Rectangle;

public abstract class Entity
{
	public int health, speed, xPos, yPos, direction;
	public Rectangle hitbox;

	public abstract void move(boolean[] inputs);
}
