
public class Sniper extends Gun
{
  public void shoot(int direction, Board b) 
  {
	  b.getBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYos(), direction, false, true, b.getPlayer().getDamage() * 3, b));
  }
  
  public int returnType()
  {
          return 2;
  }
}
