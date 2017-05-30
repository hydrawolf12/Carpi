
public class Sniper extends Gun
{
  public void shoot(int direction) 
  {
	  currentBullets.add(new Bullet(player, direction, true, false));
  }
}
