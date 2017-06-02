
public class Pistol extends Gun
{
  public void shoot(int direction) 
  {
	  getBullets().add(new Bullet(player, direction, false, false, player.getDamage()));
  }
}
