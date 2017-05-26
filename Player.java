public class Player extends Entity
{
	private Gun currentWep;
	public boolean canShoot;
	public boolean isInvin;
	private int damage, fireRate;

	public Player() //constructs player hitbox + spawns in center of map
	{
	}
	public void move(boolean[] inputs) //moves player
	{
		boolean w,a,s,d;
		w = inputs[0];
		a = inputs[1];
		s = inputs[2];
		d = inputs[3];
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
	}
	
	public void shoot(boolean[] inputs) //spawns bullet
	{
		boolean up, left, down, right;
		up = inputs[4];
		left = inputs[5];
		down = inputs[6];
		right = inputs[7];
		if (up && left)
		{
			direction = 8;
			currentWep.shoot(direction);
		}
		else if(left && down)
		{
			direction = 6;
			currentWep.shoot(direction);
		}
		else if(down && right)
		{
			direction = 4;
			currentWep.shoot(direction);
		}
		else if(up && right)
		{
			direction = 2;
			currentWep.shoot(direction);
		}
		else if(up)
		{
			direction = 1;
			currentWep.shoot(direction);
		}
		else if(left)
		{
			direction = 7;
			currentWep.shoot(direction);
		}
		else if(down)
		{
			direction = 5;
			currentWep.shoot(direction);
		}
		else if(right)
		{
			direction = 3;
			currentWep.shoot(direction);
		}
	}
	
	public boolean checkHP() //checks hp ends game if < 0
	{	
		if (health == 0)
			endGame();
	}
	
	public void setWeapon(boolean[] inputs) //what variable type is the keyboard input?
	{	
		boolean one, two , three;
		one = inputs[8];
		two = inputs[9];
		three = inputs[10];
		if (one)
		{
			currentWep = new Pistol(int damage, int fireRate);
		}
		else if(two)
		{
			currentWep = new Shotgun(int damage, int fireRate);
		}
		else if(three)
		{
			currentWep = new Sniper(int damage, int fireRate);
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
		isInvin = !isInvin;
	}
	

}
