
public class Pistol extends Gun
{
  public void shoot(int direction, Player player) 
  {
	  getBullets().add(new Bullet(player.getxPos(), player.getyPos(), direction, false, false, player.getDamage()));
  }
}