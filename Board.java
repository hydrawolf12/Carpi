import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel implements ActionListener
{
	private static Timer timer;
	private static int score;
	private static int killCount;
	private static int bossKillCount;
	private static int xEnd;
	private static int yEnd;
	private Player player;
	private boolean
	private ArrayList<Zombie> currentZombs = new ArrayList<Zombie>();
	private ArrayList<Bullet> currentBullets = new ArrayList<Bullet>();
	private boolean[] currentInputs = new boolean[11]; 
	public BufferedImage c1pistol, c1shotgun, c1sniper, c2pistol, c2shotgun, c2sniper, 
	c3pistol, c3shotgun, c3sniper, c4pistol, c4shotgun, c4sniper, c5pistol, c5shotgun, 
	c5sniper, c6pistol, c6shotgun, c6sniper, c7pistol, c7shotgun, c7sniper, c8pistol, 
	c8shotgun, c8sniper, z1, z2, z3, z4, z5, z6, z7, z8, bz1, bz2, bz3, bz4, bz5, bz6, 
	bz7, bz8, b, background;
	
	public Board()
	{
		ActionListener updateScore = new ActionListener()
		{   
		    @Override
		    public void actionPerformed(ActionEvent event)
		    {
		    	score++;
		    }
		};
		timer score = new Timer(1000, updateScore)
		timer.start();
		try 
		{
		    c1pistol = ImageIO.read(new File("c1pistol.jpg"));
		    c1shotgun = ImageIO.read(new File("c1shotgun.jpg"));
		    c1sniper = ImageIO.read(new File("c1sniper.jpg"));
		    c2pistol = ImageIO.read(new File("c2pistol.jpg"));
		    c2shotgun = ImageIO.read(new File("c2shotgun.jpg"));
		    c2sniper = ImageIO.read(new File("c2sniper.jpg"));
		    c3pistol = ImageIO.read(new File("c3pistol.jpg"));
		    c3shotgun = ImageIO.read(new File("c3shotgun.jpg"));
		    c3sniper = ImageIO.read(new File("c3sniper.jpg"));
		    c4pistol = ImageIO.read(new File("c4pistol.jpg"));
		    c4shotgun = ImageIO.read(new File("c4shotgun.jpg"));
		    c4sniper = ImageIO.read(new File("c4sniper.jpg"));
		    c5pistol = ImageIO.read(new File("c5pistol.jpg"));
		    c5shotgun = ImageIO.read(new File("c5shotgun.jpg"));
		    c5sniper = ImageIO.read(new File("c5sniper.jpg"));
		    c6pistol = ImageIO.read(new File("c6pistol.jpg"));
		    c6shotgun = ImageIO.read(new File("c6shotgun.jpg"));
		    c6sniper = ImageIO.read(new File("c6sniper.jpg"));
		    c7pistol = ImageIO.read(new File("c7pistol.jpg"));
		    c7shotgun = ImageIO.read(new File("c7shotgun.jpg"));
		    c7sniper = ImageIO.read(new File("c7sniper.jpg"));
		    c8pistol = ImageIO.read(new File("c8pistol.jpg"));
		    c8shotgun = ImageIO.read(new File("c8shotgun.jpg"));
		    c8sniper = ImageIO.read(new File("c8sniper.jpg"));
		    z1 = ImageIO.read(new File("z1.jpg"));
		    z2 = ImageIO.read(new File("z2.jpg"));
		    z3 = ImageIO.read(new File("z3.jpg"));
		    z4 = ImageIO.read(new File("z4.jpg"));
		    z5 = ImageIO.read(new File("z5.jpg"));
		    z6 = ImageIO.read(new File("z6.jpg"));
		    z7 = ImageIO.read(new File("z7.jpg"));
		    z8 = ImageIO.read(new File("z8.jpg"));
		    bz1 = ImageIO.read(new File("bz1.jpg"));
		    bz2 = ImageIO.read(new File("bz2.jpg"));
		    bz3 = ImageIO.read(new File("bz3.jpg"));
		    bz4 = ImageIO.read(new File("bz4.jpg"));
		    bz5 = ImageIO.read(new File("bz5.jpg"));
		    bz6 = ImageIO.read(new File("bz6.jpg"));
		    bz7 = ImageIO.read(new File("bz7.jpg"));
		    bz8 = ImageIO.read(new File("bz8.jpg"));
		    b = ImageIO.read(new File("b.jpg"));
		    background = ImageIO.read(new File("background.jpg"));
		} catch (IOException e) 
		{
		} 
	}
	/*public void  paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		g.setColor(new Color(219, 143, 43));
		g.fillRect(0, 0, 1000, 850);
		
		g.setColor(Color.RED);
		g.fillRect(475, 400, 50, 50);
		
		g.setColor(Color.WHITE);
		g.drawString("Health: 420", 200, 950);
		g.drawString("Score: 420", 700, 950);
	}*/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		g.drawImage(currentCarpi, player.getXPos(), player.getYPos(), null);
		for(int i = 0; i < currentZombs.size(); i++)
		{
			if(Zombie.getDirection() == 1)
				g.drawImage(z1, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection == 2)
				g.drawImage(z2, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection() == 3)
				g.drawImage(z3, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection() == 4)
				g.drawImage(z4, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection() == 5)
				g.drawImage(z5, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection() == 6)
				g.drawImage(z6, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection() == 7)
				g.drawImage(z7, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
			else if(Zombie.getDirection() == 8)
				g.drawImage(z8, currentZombs.get(i).getXPos(), currentZombs.get(i).getYPos(), null);
		}
		for(int i = 0; i < currentBullets.size(); i++)
		{
			g.drawImage(b, currentBullets.get(i).getXPos(), currentBullets.get(i).getYPos(), null);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(inGame)
		{
			player.move();
			for(int i = 0; i < currentZombs.size(); i++)
			{
				currentZombs.get(i).move();
			}
			for(int i = 0; i < currentBullets.size(); i++)
			{
				currentBullets.get(i).move();
			}
		}
		repaint();	
		
	}
	public void getBullets()
	{
	   return currentBullets;
	}
	
	public static int getxEnd()
	{
	   return xEnd;
	}
	
	public static int getyEnd()
	{
	   return yEnd;
	}
	private class AAdapter extends KeyAdapter //deals with keyboard inputs
	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_W)
			{
				currentInputs[0] = true;
			}
			if(key == KeyEvent.VK_A)
			{
				currentInputs[1] = true;
			}
			if(key == KeyEvent.VK_S)
			{
				currentInputs[2] = true;
			}
			if(key = KeyEvent.VK_D)
			{
				currentInputs[3] = true;
			}
			if(key == KeyEvent.VK_UP)
			{
				currentInputs[4] = true;
			}
			if(key == KeyEvent.VK_LEFT)
			{
				currentInputs[5] = true;
			}
			if(key == KeyEvent.VK_DOWN)
			{
				currentInputs[6] = true;
			}
			if(key == KeyEvent.VK_RIGHT)
			{
				currentInputs[7] = true;
			}
			if(key == KeyEvent.VK_1)
			{
				currentInputs[8] = true;
			}
			if(key == KeyEvent.VK_2)
			{
				currentInputs[9] = true;
			}
			if(key == KeyEvent.VK_3)
			{
				currentInputs[10] = true;
			}
		}
		public void keyReleased(KeyEvent e)
		{
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_W)
			{
				currentInputs[0] = false;
			}
			if(key == KeyEvent.VK_A)
			{
				currentInputs[1] = false;
			}
			if(key == KeyEvent.VK_S)
			{
				currentInputs[2] = false;
			}
			if(key = KeyEvent.VK_D)
			{
				currentInputs[3] = false;
			}
			if(key == KeyEvent.VK_UP)
			{
				currentInputs[4] = false;
			}
			if(key == KeyEvent.VK_LEFT)
			{
				currentInputs[5] = false;
			}
			if(key == KeyEvent.VK_DOWN)
			{
				currentInputs[6] = false;
			}
			if(key == KeyEvent.VK_RIGHT)
			{
				currentInputs[7] = false;
			}
			if(key == KeyEvent.VK_1)
			{
				currentInputs[8] = false;
			}
			if(key == KeyEvent.VK_2)
			{
				currentInputs[9] = false;
			}
			if(key == KeyEvent.VK_3)
			{
				currentInputs[10] = false;
			}
		}
	}
}
