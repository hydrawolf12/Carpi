
public class Sniper extends Gun
{
  //Generates a single piercing bullet
  public void shoot(int direction, Board b) 
  {
	  b.getCurrentBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction, true, false, b.getPlayer().getDamage() * 3, b));
  }
  
  public int returnType()
  {
          return 2;
  }
}
