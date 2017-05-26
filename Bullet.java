import java.awt.Rectangle;

public class Bullet 
{
  private int direction; 
  private Rectangle hitbox; 
  private int xpos;
  private int ypos;
  boolean isPierce;
  
  public Bullet(Player character, int d, boolean p)
  {
      xpos = character.getxpos();
      ypos = character.getypos();
      direction =  d;//key input
      hitbox = new Rectangle(xpos, ypos, 2, 2); //numbers will change
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
			 currentZombs.get(i).takeDamage(player.damage); //currentZombs.remove(i);
			 if(isPierce == false)
			 {
			    currentBullets.remove(); // must find index somehow
			    break;
			 }
		  }
	  }
  }
}
