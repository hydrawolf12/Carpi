import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.Timer;

import com.sun.webkit.dom.KeyboardEventImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener
{
	private static Timer timer;
	private static int score;
	private static int killCount;
	private static int bossKillCount;
	private static int xEnd;
	private static int yEnd;
	private Player player;
	public ArrayList<Zombie> currentZombs = new ArrayList<Zombie>();
	public ArrayList<Bullet> currentBullets = new ArrayList<Bullet>();
	public boolean[] currentInputs = new boolean[11]; 
	public Board()
	{
		 
	}
}
private class AAdapter extends KeyAdapter
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
}
