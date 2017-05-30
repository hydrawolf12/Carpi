
public class Pistol extends Gun
{
  public void shoot(int direction) 
  {
	  currentBullets.add(new Bullet(player, direction, false, false));
  }
}
