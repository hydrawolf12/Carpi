
public class Shotgun extends Gun
{
  public void shoot(int direction, Board b) 
  {
          b.getBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction, false, true, b.getPlayer().getDamage() * 2, b));
	  b.getBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction + 1, false, true, b.getPlayer().getDamage() * 2, b));
	  b.getBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction - 1, false, true, b.getPlayer().getDamage() * 2, b));
  }
  
  public int returnType()
  {
          return 1;
  }
}
