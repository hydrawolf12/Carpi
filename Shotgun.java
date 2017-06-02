
public class Shotgun extends Gun
{
  public void shoot(int direction) 
  {
	  getBullets().add(new Bullet(player, direction, false, true, player.getDamage() * 2));
	  getBullets().add(new Bullet(player, direction + 1, false, true, player.getDamage() * 2));
	  getBullets().add(new Bullet(player, direction - 1, false, true, player.getDamage() * 2));
  }
}
