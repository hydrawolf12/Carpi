import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Bullet
{
    private int damage;
    private boolean isPierce;
    private boolean isBuckshot;
    private int xPos;
    private int yPos;
    private int direction;
    private Rectangle hitbox;
    private double counter = 0.0;
    private Board b;
    //Creates a bullet based on the type of gun used and the direction of the player
    public Bullet(int x, int y, int d, boolean p, boolean bu, int dam, Board bd)
    {
        b = bd;
        yPos = y;
        xPos = x;
        if (d == 0 || d == 360)
            yPos = yPos - 4;
        else if (d == 45)
        {
            xPos = xPos + 4;
            yPos = yPos - 4;
        }
        else if (d == 90)
            xPos = xPos + 4;
        else if (d == 135)
        {
            yPos = yPos + 4;
            xPos = xPos + 4;
        }
        else if (d == 180)
            yPos = yPos + 4;
        else if (d == 225)
        {
            xPos = xPos - 4;
            yPos = yPos + 4;
        }
        else if (d == 270)
            xPos = xPos - 4;
        else if (d == 315 || d == -45)
        {
            yPos = yPos - 4;
            xPos = xPos - 4;
        }
        isBuckshot = bu;
        isPierce = p;
        damage = dam;
        hitbox = new Rectangle(xPos - 1, yPos - 1, 3, 3);
        direction = d;
    }
    //Moves the bullet and removes it if it is out of bounds, or if it is a shotgun bullet removes it after 0.35 seconds
    public void move(double delta)
    {
        counter += delta;
        if (direction == 90)
            xPos += 10;
        else if (direction == 45)
        {
            yPos -= 5;
            xPos += 5;
        }
        else if (direction == 0 || direction == 360)
            yPos -= 10;
        else if (direction == 315 || direction == -45)
        {
            yPos -= 5;
            xPos -= 5;
        }
        else if (direction == 270)
            xPos -= 10;
        else if (direction == 225)
        {
            yPos += 5;
            xPos -= 5;
        }
        else if (direction == 180)
            yPos += 10;
        else if (direction == 135)
        {
            yPos += 5;
            xPos += 5;
        }
        if(xPos >= b.getxEnd() || yPos >= b.getyEnd() || xPos <= 0 || yPos <= 0)
        {
            this.remove();
        }
        else
        {
            hitbox = new Rectangle(xPos - 1, yPos - 1, 3, 3);
        }
        if(isBuckshot && counter >= 0.35)
        {
            this.remove();
        }
    }
    //Determines if a bullet and a zombie's hitboxes collide and removes the zombie if it has no more health
    public void collisionDetect()
    {
        for (int i = 0; i < b.getCurrentZombs().size(); i++)
        {
            if(b.getCurrentZombs().get(i).getHitbox().intersects(this.hitbox))
            {
                if(b.getCurrentZombs().get(i).takeDamage(this.damage) == true)
                {
                    b.setKillCount(b.returnKillCount() + 1);
                    b.getCurrentZombs().remove(i);
                    i--;
                }

                if(isPierce == false) //Non Sniper Bullets are removed after a collison
                {
                    this.remove();
                    break;
                }
            }
        }
    }
    //Removes a bullet from the game
    public void remove()
    {
        ArrayList<Bullet> currentBullets = b.getCurrentBullets();
        int index = currentBullets.indexOf(this);
        currentBullets.remove(index);
    }

    public Bullet getBullet()
    {
        return this;
    }
    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }
}
