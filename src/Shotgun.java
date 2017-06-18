
public class Shotgun extends Gun
{
  //Generates 3 non-piercing bullets that disappear after a limited amount of time
  public void shoot(int direction, Board b) 
  {
      b.getCurrentBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction, false, true, b.getPlayer().getDamage() * 2, b));
	  b.getCurrentBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction + 45, false, true, b.getPlayer().getDamage() * 2, b));
	  b.getCurrentBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction - 45, false, true, b.getPlayer().getDamage() * 2, b));
  }
  
  public int returnType()
  {
          return 1;
  }
}
