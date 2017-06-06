import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet extends Entity
{ 
  private int damage;
  boolean isPierce;
  boolean isBuckshot;

  
  public Bullet(int x, int y, int d, boolean p, boolean b, int dam)
  {
          if (direction == 1)
		  Entity(0, 0, x, y - 2, 2, 2, x - 1, y - 1);
	  else if (direction == 2)
	  {
		  Entity(0, 0, x + 2, y - 2, 2, 2, x - 1, y - 1);
	  }
	  else if (direction == 3)
		  Entity(0, 0, x + 2, y, 2, 2, x - 1, y - 1);
	  else if (direction == 4)
	  {
		  Entity(0, 0, x + 2, y + 2, 2, 2, x - 1, y - 1);
	  }
	  else if (direction == 5)
		  Entity(0, 0, x, y + 2, 2, 2, x - 1, y - 1);
	  else if (direction == 6)
	  {
		  Entity(0, 0, x - 2, y + 2, 2, 2, x - 1, y - 1);
	  }
	  else if (direction == 7)
		   Entity(0, 0, x - 2, y, 2, 2, x - 1, y - 1);
	  else if (direction == 8)
	  {
		  Entity(0, 0, x - 2, y - 2, 2, 2, x - 1, y - 1);
	  }
      isBuckshot = b;
      isPierce = p;
      damage = dam;
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
		    	this.remove();
		    }
		};
      Timer t = new Timer(3000, action);
      t.setRepeats(false);
      t.start();
  }
   
  public void move()
  {
	  if (direction == 1)
		  yPos--;
	  else if (direction == 2)
	  {
		  yPos--;
		  xPos++;
	  }
	  else if (direction == 3)
		  xPos++;
	  else if (direction == 4)
	  {
		  yPos++;
		  xPos++;
	  }
	  else if (direction == 5)
		  yPos++;
	  else if (direction == 6)
	  {
		  yPos++;
		  xPos--;
	  }
	  else if (direction == 7)
		  xPos--;
	  else if (direction == 8)
	  {
		  yPos--;
		  xPos--;
	  }
	  
	  hitbox = new Rectangle(xpos - 1, ypos - 1, 2, 2);
  }
  
  public void collisionDetect()
  {
	  for (int i = 0; i < currentZombs.size(); i++)
	  {
		  if(currentZombs.get(i).getRectangle.intersects(this.hitbox))
		  {
			 currentZombs.takeDamage(this.damage, i);
			 if(isPierce == false)
			 {
			    this.remove();
			    break;
			 }
			 if(xpos >= getxEnd() || ypos >= getyEnd() || xpos <= 0 || ypos <= 0)
			 {
				 this.remove();
			 }
		  }
	  }
   }
  
   public void remove()
   {
	  for (int i = 0; i < currentBullets.size(); i++)
	  {
		  if (this.equals(currentBullets.get(i)))
		  {
			  currentBullets.remove(i);
			  break;
		  }
	  }
   }
}

