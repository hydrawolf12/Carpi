
public class Sniper extends Gun
{
  public void shoot(int direction, Board b) 
  {
	  b.getBullets().add(new Bullet(b.getPlayer().getxPos(), b.getPlayer().getyPos(), direction, false, true, b.getPlayer().getDamage() * 3, b));
  }
}
