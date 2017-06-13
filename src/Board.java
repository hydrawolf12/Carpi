import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	private Timer timer = new Timer(5, this);
	private int score;
	private boolean inGame;
	private static int killCount;
	private int xEnd;
	private int yEnd;
	private Player player;
	private int count;
	private Spawner spawner;
	private ActionListener updateScore;
	private ActionListener spawnZomb;
	private Timer scoreT;
	private Timer spawnZ;
	private ArrayList<Zombie> currentZombs;
	private ArrayList<Bullet> currentBullets;
	private boolean[] currentInputs; 
	public BufferedImage c1pistol, c1shotgun, c1sniper, c2pistol, c2shotgun, c2sniper, 
	c3pistol, c3shotgun, c3sniper, c4pistol, c4shotgun, c4sniper, c5pistol, c5shotgun, 
	c5sniper, c6pistol, c6shotgun, c6sniper, c7pistol, c7shotgun, c7sniper, c8pistol, 
	c8shotgun, c8sniper, z1, z2, z3, z4, z5, z6, z7, z8, bz1, bz2, bz3, bz4, bz5, bz6, 
	bz7, bz8, b, background;
	
	public Board()
	{
		score = 0;
		killCount = 0;
		xEnd = 1000;
		yEnd = 900;
		count = 0;
		player = new Player(this);
		spawner = new Spawner(this);
		currentZombs = new ArrayList<Zombie>();
		currentBullets = new ArrayList<Bullet>();
		currentInputs = new boolean[11];
		inGame = true;
		updateScore = new ActionListener()
		{   
		    @Override
		    public void actionPerformed(ActionEvent event)
		    {
		    	Graphics g = new Graphics();
		    	g.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		    	g.drawString("Score: " + String.valueOf(score), 700, 950);
		    	score++;
		    }
		};
		spawnZomb = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				if(killCount >= 100)
				{
					
					getZombies().add(spawner.spawnBoss());
					killCount = 0;
				}
				else
				{
					getZombies().add(spawner.spawnZombie()); 
				}
			}
		};
		scoreT = new Timer(1000, updateScore);
		spawnZ = new Timer(spawner.returnRate(), spawnZomb);
		try 
		{
		    cpistol = ImageIO.read(new File("pixelArt//cpistol.png"));
		    cshotgun = ImageIO.read(new File("pixelArt//cshotgun.png"));
		    csniper = ImageIO.read(new File("pixelArt//csniper.png"));
		    z = ImageIO.read(new File("pixelArt//z.png"));
		    bz = ImageIO.read(new File("pixelArt//bz.png"));
		    b = ImageIO.read(new File("b.png"));
		    background = ImageIO.read(new File("pixelArt//background.png"));
		} catch (IOException e) 
		{
		}
		addKeyListener(new AAdapter());
		scoreT.start();
		spawnZ.start();
		timer.start(); 
	}
	/*int i = 0;
	public void  paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		g.setColor(new Color(216, 174, 119));
		g.fillRect(0, 0, 1000, 900);
		
		//BufferedImage currentCarpi = cpistol;
		AffineTransform at1 = AffineTransform.getTranslateInstance(475, 425);
		at1.rotate(Math.toRadians(i++/10), 25, 25);
		at1.scale(2, 2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(cpistol, at1, null);
		AffineTransform at2 = AffineTransform.getTranslateInstance(350, 425);
		at2.rotate(Math.toRadians(i++/10), 25, 25);
		at2.scale(2, 2);
		g2d.drawImage(cshotgun, at2, null);
		AffineTransform at3 = AffineTransform.getTranslateInstance(600, 425);
		at3.rotate(Math.toRadians(i++/10), 25, 25);
		at3.scale(2, 2);
		g2d.drawImage(csniper, at3, null);
		AffineTransform at4 = AffineTransform.getTranslateInstance(600, 550);
		at4.rotate(Math.toRadians(i++/10), 25, 25);
		at4.scale(2, 2);
		g2d.drawImage(z, at4, null);
		AffineTransform at5 = AffineTransform.getTranslateInstance(350, 550);
		at5.rotate(Math.toRadians(i++/10), 25, 25);
		at5.scale(2, 2);
		g2d.drawImage(bz, at5, null);
		repaint();
		
		//g.setColor(Color.RED);
		//g.fillRect(475, 425, 50, 50);
		
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		g.drawString("Health: 420", 200, 950);
	}*/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.drawImage(background, 0, 0, null);
		AffineTransform at = AffineTransform.getTranslateInstance(player.getXPos() - 25, player.getYPos() - 25);
		at.rotate(Math.toRadians(player.getDirection()), 25, 25);
		Graphics2D g2d = (Graphics2D) g;
		if(player.returnType() == 0)
			g2d.drawImage(cpistol, at, null);
		else if(player.returnType() == 1)
			g2d.drawImage(cshotgun, at, null);
		else
			g2d.drawImage(csniper, at, null);
			
		for(int i = 0; i < currentZombs.size(); i++)
		{
			int x = currentZombs.get(i).returnRadius();
			AffineTransform at1 = AffineTransform.getTranslateInstance(currentZombs.get(i).getXPos() - x, currentZombs.get(i).getYPos() - x);
			at1.rotate(Math.toRadians(currentZombs.getDirection()), x, x);
			Graphics2D g2d = (Graphics2D) g;
			if(x == 25)
				g2d.drawImage(z, at1, null);
			else
				g2d.drawImage(bz, at1, null);
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
			for(Zombie z : currentZombs)
			{
				z.move();
			}
			for(Bullet bullet : currentBullets)
			{
				bullet.move();
			}
		}
		/*if(Boss.returnIsBossDefeated)  Either do this or somehow do this in the Boss remove method;
		{
			spawnZ.setDelay(Spawner.returnRate()); //ASK IF I MAKE A SPAWNER VARIABLE IN BOSS AND SET IT EQUAL TO THIS WILL IT WORK IT UPDATE IF I CHANGE IT IN THE BOSS METHOD.
		}*/
		repaint();	
	}
	public Player getPlayer()
	{
		return player;
	}
	public ArrayList<Bullet> getBullets()
	{
	   return currentBullets;
	}
	public ArrayList<Zombie> getZombies()
	{
		return currentZombs;
	}
	public int getxEnd()
	{
	   return xEnd;
	}
	
	public int getyEnd()
	{
	   return yEnd;
	}
	public Timer returnZombieSpawner()
	{
		return spawnZ;
	}
	public static int returnKillCount()
	{
		return killCount;
	}
	public static void setKillCount(int k)
	{
		killCount += k;
	}
	public void addZomb(Zombie z)
	{
		currentZombs.add(z);
	}
	public void removeZomb(Zombie zomb)
	{
		currentZombs.remove(zomb);
	}
	public boolean getInputs(int x)
	{
		return currentInputs[x];
	}
	public void Endgame()
	{
		//idk what to Its gonna display graphics and stuff.  so tommy thats ur call;
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
			if(key == KeyEvent.VK_D)
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
			if(key == KeyEvent.VK_D)
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
