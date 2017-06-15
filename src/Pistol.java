
public class Pistol extends Gun
{
  private int type;
  public Pistol()
  {
     type = 0;
  }
  public void shoot(int direction, Board b) 
  {
	  b.getBullets().add(new Bullet(b.getPlayer().getXPos(), b.getPlayer().getYPos(), direction, false, false, b.getPlayer().getDamage(), b));
  }
  
  public int returnType()
  {
          return type;
  }
}
