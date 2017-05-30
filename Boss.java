import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Entity
{
	private Gun currentWep;
	public boolean canShoot;
	public boolean isInvin;
	public int damage, fireRate;

	public Player(int health, int speed, int x, int y, int w, int h, int topL, int topR) //constructs player hitbox + spawns in center of map
	{
		super(health, speed, x , y , w, h, topL, topR); //x and y should be 174 topL and topR should be 154
		damage = 1;
		fireRate = 1;
		isInvin = false;
		canShoot = true;
		currentWep = new Pistol(damage, fireRate);
	}
	public void move() //moves player
	{
		boolean w,a,s,d;
		w = currentInputs[0];
		a = currentInputs[1];
		s = currentInputs[2];
		d = currentInputs[3];
		if (w && a)
		{
			yPos--;
			xPos--;
			direction = 8;
		}
		else if(a && s)
		{
			yPos++;
			xPos--;
			direction = 6;
		}
		else if(s && d)
		{
			yPos++;
			xPos++;
			direction = 4;
		}
		else if(w && d)
		{
			yPos--;
			xPos++;
			direction = 2;
		}
		else if(w)
		{
			yPos--;
			direction = 1;
		}
		else if(a)
		{
			xPos--;
			direction = 7;
		}
		else if(s)
		{
			yPos++;
			direction = 5;
		}
		else if(d)
		{
			xPos++;
			direction = 3;
		}
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
		currentWep.shoot(direction);
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
	
	public void updateDamageFireRate(int a) // updates damage and firerate by a
	{
		damage+= a;
		fireRate+= a;
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
	

}
