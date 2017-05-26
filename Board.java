import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.image.BufferStrategy;

public class Board extends JPanel implements ActionListener
{
	private static Timer timer;
	private static int score;
	private static int killCount;
	private static int bossKillCount;
	private Player player;
	public ArrayList<Zombie> currentZombs = new ArrayList<Zombie>();
	public ArrayList<Bullet> currentBullets = new ArrayList<Bullet>();
	public Board()
	{
		 
	}

}
