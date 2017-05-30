imimport java.awt.Rectangle;

public class Bullet 
{
  private int direction; 
  private Rectangle hitbox; 
  private int xpos;
  private int ypos;
  boolean isPierce;
  boolean isBuckshot;
  
  public Bullet(Player character, int d, boolean p, boolean b)
  {
	  xpos = character.getxpos();
	  ypos = character.getypos();
	  direction =  d;//key input
      hitbox = new Rectangle(xpos, ypos, 2, 2); //numbers will change
      isBuckshot = b;
      isPierce = p;
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
			 currentZombs.remove();
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
