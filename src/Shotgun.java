
public class Shotgun extends Gun
{
  public void shoot(int direction, Board b) 
  {
          b.getBullets().add(new Bullet(b.getPlayer.getxPos(), b.getPlayer.getyPos(), direction, false, true, b.getPlayer.getDamage() * 2, b));
	  b.getBullets().add(new Bullet(b.getPlayer.getxPos(), b.getPlayer.getyPos(), direction + 1, false, true, b.getPlayer.getDamage() * 2, b));
	  b.getBullets().add(new Bullet(b.getPlayer.getxPos(), b.getPlayer.getyPos(), direction - 1, false, true, b.getPlayer.getDamage() * 2, b));
  }
}
