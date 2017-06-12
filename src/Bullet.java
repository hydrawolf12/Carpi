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
  private Board b;

  public Bullet(int x, int y, int d, boolean p, boolean bu, int dam, Board bd)
  {
	  b = bd;
          if (d == 90)
		  yPos = yPos - 2;
	  else if (d == 45)
	  {
		  xPos = xPos + 2;
		  yPos = yPos - 2;
	  }
	  else if (d == 0)
		  xPos = xPos + 2;
	  else if (d == 315)
	  {
		  yPos = yPos + 2;
		  xPos = xPos + 2;
	  }
	  else if (d == 270)
		  yPos = yPos + 2;
	  else if (d == 225)
	  {
		  xPos = xPos - 2;
		  yPos = yPos + 2;
	  }
	  else if (d == 180)
		  xPos = xPos - 2;
	  else if (d == 135)
	  {
		  yPos = yPos - 2;
		  xPos = xPos - 2;
	  }
      isBuckshot = bu;
      isPierce = p;
      damage = dam;
      hitbox = new Rectangle(xPos - 1, yPos - 1, 2, 2);
      direction = d;
      if(isBuckshot == true)
      {
    	  startTimer();
      }
   }
   
  public void startTimer()
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
  }
   
  public void move()
  {
	  if (direction == 90)
		  yPos--;
	  else if (direction == 45)
	  {
		  yPos--;
		  xPos++;
	  }
	  else if (direction == 0)
		  xPos++;
	  else if (direction == 315)
	  {
		  yPos++;
		  xPos++;
	  }
	  else if (direction == 270)
		  yPos++;
	  else if (direction == 225)
	  {
		  yPos++;
		  xPos--;
	  }
	  else if (direction == 180)
		  xPos--;
	  else if (direction == 135)
	  {
		  yPos--;
		  xPos--;
	  }
	  
	  if(xpos >= b.getxEnd() || ypos >= b.getyEnd() || xpos <= 0 || ypos <= 0)
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
      ArrayList<Zombie> currentZombs = b.returnList();
	  for (int i = 0; i < currentZombs.size(); i++)
	  {
		  if(currentZombs.get(i).getHitbox().intersects(this.hitbox))
		  {
			 if(currentZombs.takeDamage(this.damage, i) == true)
			 {
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
      ArrayList<Bullet> currentBullets = t.returnList2(); 
      int index = currentBullets.indexOf(this);
      currentBullets.remove(index);
   }
	
   public Bullet getBullet()
   {
       return this;
   }
}
