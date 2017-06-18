import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Entity
{
	private Gun currentWep;
	private boolean canShoot;
	private boolean isInvin;
	private int damage; 
	private double fireRate;
	private double baseRate;
	private Board board;
	private double shootT;
	private double invinT;

	//spawns player object in center of board and initializes health, speed, damage, fire rate, invincibility, ability to shoot, shoot timer, and constructs a pistol object
	public Player(Board playBoard)
	{
		super(3, 3, playBoard.getxEnd() / 2, playBoard.getyEnd() / 2,  playBoard.getxEnd() / 2 - 23, playBoard.getyEnd() / 2 - 23, 46, 46);
		board = playBoard;
		damage = 1;
		baseRate = 1;
		fireRate = 1;
		isInvin = false;
		canShoot = false;
		shootT = 0;
		currentWep = new Pistol();//this isn't a thing
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
	public double getFireRate()
	{
		return fireRate;
	}
	
	//moves player based on boolean array of inputs representing WASD movement keys if the player is not at the map's border
	//calls all zombie calcAng() methods and redraws player hitbox at new position
	public void move() //moves player
	{
		boolean w,a,s,d;
		w = board.getInputs(0);
		a = board.getInputs(1);
		s = board.getInputs(2);
		d = board.getInputs(3);
		boolean canUP = this.getYPos() > 25;
		boolean canDOWN = this.getYPos() < board.getyEnd() - 25;
		boolean canLEFT = this.getXPos() > 25;
		boolean canRIGHT =  this.getXPos() < board.getxEnd() - 25;
		if (w && a && canUP && canLEFT)
		{
			this.setYPos(-3);
			this.setXPos(-3);
			// direction = 135;
		}
		else if(a && s && canLEFT && canDOWN)
		{
			this.setYPos(3);
			this.setXPos(-3);
			// direction = 225;
		}
		else if(s && d && canDOWN && canRIGHT)
		{
			this.setYPos(3);
			this.setXPos(3);
			// direction = 315;
		}
		else if(w && d && canUP && canRIGHT)
		{
			this.setYPos(-3);
			this.setXPos(3);
			// direction = 2;
		}
		else if(w && canUP)
		{
			this.setYPos(-6);
			// direction = 1;
		}
		else if(a && canLEFT)
		{
			this.setXPos(-6);
			// direction = 180;
		}
		else if(s && canDOWN)
		{
			this.setYPos(6);
			//  direction = 270;
		}
		else if(d && canRIGHT)
		{
			this.setXPos(6);
			// direction = 3;
		}
		this.setHitbox(new Rectangle(this.getXPos() - 23, this.getYPos() - 23, 46, 46));
		for (Zombie z: board.getCurrentZombs())
		{
			z.calcAng();
		}
	}
	
	//spawns bullet in one of eight directions depending on boolean array representing ARROW KEYS if canShoot is true
	//increments the shoot timer by a double value and checks if the time since the last shot is greater than the fireRate
	//sets canShoot to false and resets timer
	public void shoot(double delta)
    	{
       		shootT += delta;
       		boolean up, left, down, right;
        	up = board.getInputs(4);
        	left = board.getInputs(5);
        	down = board.getInputs(6);
        	right = board.getInputs(7);
        	if(shootT >= fireRate)
        	{
            		canShoot = true;
        	}
       		 if (up == left && left == right && right == down && up == false)
        	{
            		return;
        	}
        	if (up && left)
		{
            		this.setDirection(315);
        	} 
		else if (left && down)
		{
            		this.setDirection(225);
		} 
		else if (down && right) 
		{
            		this.setDirection(135);
        	} 
		else if (up && right) 
		{
            		this.setDirection(45);
        	} 
		else if (up) 
		{
            		this.setDirection(0);
        	} 
		else if (left) 
		{
            		this.setDirection(270);
        	} 
		else if (down) 
		{
            		this.setDirection(180);
        	} 
		else if (right) 
		{
            		this.setDirection(90);
        	}
       		if(canShoot)
		{
        		canShoot = false;
        		currentWep.shoot(this.getDirection(), board);
        		System.out.println("The number of bullets" + board.getCurrentBullets().size());
        		shootT = 0;
        	}
   	}
	
	//checks hp ends game if <= 0
	public void checkHP() 
	{	
		if (this.getHealth() <= 0)
			board.endGame();
	}
	
	//constructs and assigns new weapon based on boolean array of inputs representing numbers 1 - 3
	//pistol for 1, shotgun for 2, sniper for 3 and changes fireRate depending on weapon
	public void setWeapon(double delta)
	{
		if (canShoot)
		{
			boolean one, two , three;
			one = board.getInputs(8);
			two = board.getInputs(9);
			three = board.getInputs(10);
			if (one)
			{
				currentWep = new Pistol();
				fireRate = baseRate;
			}
			else if(two)
			{
				currentWep = new Shotgun();
				fireRate = baseRate * 2;
			}
			else if(three)
			{
				currentWep = new Sniper();
				fireRate = baseRate * 3;
			}	
		}
	}
	
	public void updateDamage(int a) // updates damage and firerate by a
	{
		damage += a;
	}
	public void updateFireRate(double a)
	{
		baseRate *= a;
	}
	
	//disables invincibility if it has been longer than 1 second and resets timer
	public void toggleInvin(double delta)
	{
		invinT +=delta;
	    	if(invinT >= 1 && isInvin)
	    	{
	       	 	isInvin = false;
	       		invinT = 0;
        	}
	}
	
	//switches invincibility value and resets timer
	public void changeInvin()
	{
		isInvin = !isInvin;
		invinT = 0;
    	}
	public int returnType()
	{
		return currentWep.returnType();
	}
	public void setCanShoot(boolean canShoot)
	{
		this.canShoot = canShoot;
	}
}
