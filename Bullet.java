import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet 
{ 
  private int damage;
  private boolean isPierce;
  private boolean isBuckshot;
  private int xPos;
  private int yPos;
  private int direction;
  private Rectangle hitbox;

  public Bullet(int x, int y, int d, boolean p, boolean b, int dam)
  {
      if (d == 1)
		  yPos = yPos - 2;
	  else if (d == 2)
	  {
		  xPos = xPos + 2;
		  yPos = yPos - 2;
	  }
	  else if (d == 3)
		  xPos = xPos + 2;
	  else if (d == 4)
	  {
		  yPos = yPos + 2;
		  xPos = xPos + 2;
	  }
	  else if (d == 5)
		  yPos = yPos + 2;
	  else if (d == 6)
	  {
		  xPos = xPos - 2;
		  yPos = yPos + 2;
	  }
	  else if (d == 7)
		  xPos = xPos - 2;
	  else if (d == 8)
	  {
		  yPos = yPos - 2;
		  xPos = xPos - 2;
	  }
      isBuckshot = b;
      isPierce = p;
      damage = dam;
      direction = d;
      hitbox = new Rectangle(xPos - 1, yPos - 1, 2, 2);
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
	  
	  hitbox = new Rectangle(xPos - 1, yPos - 1, 2, 2);
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
