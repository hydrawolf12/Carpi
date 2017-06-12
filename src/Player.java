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
	private Board board;

	public Player(Board playBoard)//public Player(int health, int speed, int x, int y, int w, int h, int topL, int topR, int width, int height) //constructs player hitbox + spawns in center of map
	{
		super(3, 2, playBoard.getxEnd() / 2, playBoard.getyEnd() / 2,  playBoard.getxEnd() / 2 - 25, playBoard.getyEnd() / 2 - 25, 50, 50);
		board = playBoard;
		int x = playBoard.getxEnd() / 2;
		int y = playBoard.getyEnd() / 2;
		damage = 1;
		fireRate = 1;
		isInvin = false;
		canShoot = true;
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
	public int getFireRate()
	{
		return fireRate;
	}
	public void move() //moves player
	{
		boolean w,a,s,d;
		w = board.getInputs(0);
		a = board.getInputs(1);
		s = board.getInputs(2);
		d = board.getInputs(3);
		boolean canUP = this.getYPos() > 1;
		boolean canDOWN = this.getYPos() < board.getyEnd() - 1;
		boolean canLEFT = this.getXPos() > 1;
		boolean canRIGHT =  this.getXPos() < board.getxEnd() - 1;
		if (w && a && canUP && canLEFT)
		{
			this.setYPos(-1);
			this.setXPos(-1);
			// direction = 135;
		}
		else if(a && s && canLEFT && canDOWN)
		{
			this.setYPos(1);
			this.setXPos(-1);
			// direction = 225;
		}
		else if(s && d && canDOWN && canRIGHT)
		{
			this.setYPos(1);
			this.setXPos(1);
			// direction = 315;
		}
		else if(w && d && canUP && canRIGHT)
		{
			this.setYPos(-1);
			this.setXPos(1);
			// direction = 2;
		}
		else if(w && canUP)
		{
			this.setYPos(-1);
			// direction = 1;
		}
		else if(a && canLEFT)
		{
			this.setXPos(-1);
			// direction = 180;
		}
		else if(s && canDOWN)
		{
			this.setYPos(-1);
			//  direction = 270;
		}
		else if(d && canRIGHT)
		{
			this.setXPos(1);
			// direction = 3;
		}
		this.setHitbox(new Rectangle(this.getXPos() - 25, this.getYPos() - 25, 50 , 50));
		for (Zombie z: board.getZombies())
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
		
		up = board.getInputs(4);
		left = board.getInputs(5);
		down = board.getInputs(6);
		right = board.getInputs(7);
		if (up && left)
		{
			this.setDirection(135);
		}
		else if(left && down)
		{
			this.setDirection(225);
		}
		else if(down && right)
		{
			this.setDirection(315);
		}
		else if(up && right)
		{
			this.setDirection(45);
		}
		else if(up)
		{
			this.setDirection(90);
		}
		else if(left)
		{
			this.setDirection(180);
		}
		else if(down)
		{
			this.setDirection(270);
		}
		else if(right)
		{
			this.setDirection(0);
		}
		currentWep.shoot(this.getDirection(), this);
		canShoot = false;
		Timer shootTimer = new Timer(3000, action);
		shootTimer.setRepeats(false);
		shootTimer.start();
	}
	
	public boolean checkHP() //checks hp ends game if < 0
	{	
		if (this.getHealth() == 0)
			board.endGame();
	}
	
	public void setWeapon() //what variable type is the keyboard input?
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
			}
			else if(two)
			{
				currentWep = new Shotgun();
			}
			else if(three)
			{
				currentWep = new Sniper();
			}	
		}
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
