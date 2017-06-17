import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Bullet
{
	private int damage;
	private boolean isPierce;
	private boolean isBuckshot;
	private int xPos;
	private int yPos;
	private int direction;
	private Rectangle hitbox;
	private int counter = 0;
	private Board b;

	public Bullet(int x, int y, int d, boolean p, boolean bu, int dam, Board bd)
	{
		b = bd;
		yPos = y;
		xPos = x;
		if (d == 0)
			yPos = yPos - 2;
		else if (d == 45)
		{
			xPos = xPos + 2;
			yPos = yPos - 2;
		}
		else if (d == 90)
			xPos = xPos + 2;
		else if (d == 135)
		{
			yPos = yPos + 2;
			xPos = xPos + 2;
		}
		else if (d == 180)
			yPos = yPos + 2;
		else if (d == 225)
		{
			xPos = xPos - 2;
			yPos = yPos + 2;
		}
		else if (d == 270)
			xPos = xPos - 2;
		else if (d == 315)
		{
			yPos = yPos - 2;
			xPos = xPos - 2;
		}
		isBuckshot = bu;
		isPierce = p;
		damage = dam;
		hitbox = new Rectangle(xPos - 1, yPos - 1, 2, 2);
		direction = d;
	}

	/*public void startTimer()
	{
		ActionListener action = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				remove();
			}
		};
		Timer t = new Timer(3000, action);
		t.setRepeats(false);
		t.start();
	}*/

	public void move(double delta)
	{
		counter+= delta;
		if (direction == 90)
			xPos++;
		else if (direction == 45)
		{
			yPos--;
			xPos++;
		}
		else if (direction == 0)
			yPos--;
		else if (direction == 315)
		{
			yPos--;
			xPos--;
		}
		else if (direction == 270)
			xPos--;
		else if (direction == 225)
		{
			yPos++;
			xPos--;
		}
		else if (direction == 180)
			yPos++;
		else if (direction == 135)
		{
			yPos++;
			xPos++;
		}
		if(isBuckshot && counter >= 3) {
			this.remove();
			counter = 0;
		}
		if(xPos >= b.getxEnd() || yPos >= b.getyEnd() || xPos <= 0 || yPos <= 0)
		{
			this.remove();
		}
		else
		{
			hitbox = new Rectangle(xPos - 1, yPos - 1, 2, 2);
		}
	}

	public void collisionDetect()
	{
		ArrayList<Zombie> currentZombs = b.getCurrentZombs();
		for (int i = 0; i < currentZombs.size(); i++)
		{
			if(currentZombs.get(i).getHitbox().intersects(this.hitbox))
			{
				if(currentZombs.get(i).takeDamage(this.damage, i) == true) {
					if(currentZombs.get(i) instanceof Boss)
					{
						Spawner.incrementRate(1);
					}
					i--;
				}

				if(isPierce == false)
				{
					this.remove();
					break;
				}
			}
		}
	}

	public void remove()
	{
		ArrayList<Bullet> currentBullets = b.getCurrentBullets();
		int index = currentBullets.indexOf(this);
		currentBullets.remove(index);
	}

	public Bullet getBullet()
	{
		return this;
	}
	public int getXPos()
	{
		return xPos;
	}

	public int getYPos()
	{
		return yPos;
	}
}
