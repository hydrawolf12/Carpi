
public class Pistol extends Gun
{
  public void shoot(int direction, Board b) 
  {
	  getBullets().add(new Bullet(b.getPlayer.getxPos(), b.getPlayer.getyPos(), direction, false, false, player.getDamage(), b));
  }
}
