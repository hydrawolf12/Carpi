
public class Sniper extends Gun
{
  public void shoot(int direction, Player player) 
  {
	  getBullets().add(new Bullet(player.getxPos(), player.getyPos(), direction, false, true, player.getDamage() * 3));
  }
}
