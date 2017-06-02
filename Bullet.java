import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet extends Entity
{ 
  private int damage;
  boolean isPierce;
  boolean isBuckshot;

  
  public Bullet(int x,int y, int d, boolean p, boolean b, int dam)
  {
      xPos = x;
      yPos = y;
      direction =  d;//key input
      hitbox = new Rectangle(xpos, ypos, 2, 2); //numbers will change
      isBuckshot = b;
      isPierce = p;
      damage = dam;
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
		  ypos--;
	  else if (direction == 2)
	  {
		  ypos--;
		  xpos++;
	  }
	  else if (direction == 3)
		  xpos++;
	  else if (direction == 4)
	  {
		  ypos++;
		  xpos++;
	  }
	  else if (direction == 5)
		  ypos--;
	  else if (direction == 6)
	  {
		  ypos++;
		  xpos--;
	  }
	  else if (direction == 7)
		  xpos--;
	  else if (direction == 8)
	  {
		  ypos--;
		  xpos--;
	  }
  }
  
  public void collisionDetect()
  {
	  for (int i = 0; i < currentZombs.size(); i++)
	  {
		  if(currentZombs.get(i).getRectangle.intersects(this.hitbox))
		  {
			 currentZombs.takeDamage(this.damage);
			 if(isPierce == false)
			 {
			    this.remove();
			    break;
			 }
			 if(xpos >= boardEnd || ypos >= boardEnd || xpos <= 0 || ypos <= 0)
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

