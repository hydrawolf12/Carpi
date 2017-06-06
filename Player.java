import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Entity
{
	private Gun currentWep;
	private boolean canShoot;
	private boolean isInvin;
	private int damage, fireRate;

	public Player()//public Player(int health, int speed, int x, int y, int w, int h, int topL, int topR, int width, int height) //constructs player hitbox + spawns in center of map
	{
		x = getxEnd() / 2;
		y = getyEnd() / 2;
		super(3, 2, x , y, x - 25, y - 25, 50, 50);
		damage = 1;
		fireRate = 1;
		isInvin = false;
		canShoot = true;
		currentWep = new Pistol(damage, fireRate);
	}
	public boolean getShoot()
	{
		return canShoot;
	}
	public boolean getInvin()
	{
		return isInvin;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getFireRate()
	{
		return fireRate;
	}
	public void move() //moves player
	{
		boolean w,a,s,d;
		w = currentInputs[0];
		a = currentInputs[1];
		s = currentInputs[2];
		d = currentInputs[3];
		canUP = yPos > 1;
		canDOWN = yPos < getyEnd() - 1;
		canLEFT = xPos > 1;
		canRIGHT =  xPos < getxEnd() - 1;
		if (w && a && canUP && canLEFT)
		{
			yPos--;
			xPos--;
			// direction = 8;
		}
		else if(a && s && canLEFT && canDOWN)
		{
			yPos++;
			xPos--;
			// direction = 6;
		}
		else if(s && d && canDOWN && canRIGHT)
		{
			yPos++;
			xPos++;
			// direction = 4;
		}
		else if(w && d && canUP && canRIGHT)
		{
			yPos--;
			xPos++;
			// direction = 2;
		}
		else if(w && canUP)
		{
			yPos--;
			// direction = 1;
		}
		else if(a && canLEFT)
		{
			xPos--;
			// direction = 7;
		}
		else if(s && canDOWN)
		{
			yPos++;
			//  direction = 5;
		}
		else if(d && canRIGHT)
		{
			xPos++;
			// direction = 3;
		}
		hitbox = new Rectangle(x - 25, y - 25, 50 , 50);
		for (Zombie z: currentZombs)
		{
			z.calcAng();
		}
	}
	
	public void shoot() //spawns bullet
	{
		ActionListener action = new ActionListener()
		{   
		    @Override
		    public void actionPerformed(ActionEvent event)
		    {
		    	canShoot = true;
		    }
		};
		boolean up, left, down, right;
		
		up = currentInputs[4];
		left = currentInputs[5];
		down = currentInputs[6];
		right = currentInputs[7];
		if (up && left)
		{
			direction = 8;
		}
		else if(left && down)
		{
			direction = 6;
		}
		else if(down && right)
		{
			direction = 4;
		}
		else if(up && right)
		{
			direction = 2;
		}
		else if(up)
		{
			direction = 1;
		}
		else if(left)
		{
			direction = 7;
		}
		else if(down)
		{
			direction = 5;
		}
		else if(right)
		{
			direction = 3;
		}
		currentWep.shoot(direction, this);
		canShoot = false;
		Timer shootTimer = new Timer(3000, action);
		shootTimer.setRepeats(false);
		shootTimer.start();
	}
	
	public boolean checkHP() //checks hp ends game if < 0
	{	
		if (health == 0)
			endGame();
	}
	
	public void setWeapon() //what variable type is the keyboard input?
	{	
		if (canShoot)
		{
			boolean one, two , three;
			one = currentInputs[8];
			two = currentInputs[9];
			three = currentInputs[10];
			if (one)
			{
				currentWep = new Pistol(damage, fireRate);
			}
			else if(two)
			{
				currentWep = new Shotgun(damage, fireRate);
			}
			else if(three)
			{
				currentWep = new Sniper(damage, fireRate);
			}	
		}
	}
	
	public void updateHP(int a) //updates hp by a
	{
		health += a;
	}
	
	public void updateDamage(int a) // updates damage and firerate by a
	{
		damage+= a;
	}
	public void updateFireRate(int a)
	{
		fireRate += a;
	}
	
	public void toggleInvin()
	{
		ActionListener action = new ActionListener()
		{   
		    @Override
		    public void actionPerformed(ActionEvent event)
		    {
		    	isInvin = false;
		    }
		};
		isInvin = true;
		Timer invTimer = new Timer(2000, action);
		invTimer.setRepeats(false);
		invTimer.start();
	}
	public int returnType()
	{
		return currentWep.returnType();
	}

}
