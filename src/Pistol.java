
public class Pistol extends Gun
{
  public void shoot(int direction, Board b) 
  {
	  b.getBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction, false, false, b.getPlayer().getDamage(), b));
  }
  
  public int returnType()
  {
          return 0;
  }
}
