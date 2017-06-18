import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
    private int score;
    private boolean inGame;
    private static int killCount;
    private int xEnd;
    private int yEnd;
    private long lastFpsTime;
    private int fps;
    private Thread string;
    private double zombieT;
    private Player player;
    private double scoreT;
    private Spawner spawner;
    private double shootT;
    private ArrayList<Zombie> currentZombs;
    private ArrayList<Bullet> currentBullets;
    private boolean[] currentInputs;
    public BufferedImage cpistol, cshotgun, csniper, z, bz, b, background;
    private Image dbImage;
    private Graphics dbg;
    public Board() {
        addKeyListener(new AAdapter());
        score = 0;
        killCount = 0;
        fps = 0;
        xEnd = 1000;
        yEnd = 900;
        lastFpsTime = 0;
        scoreT = 0;
        shootT = 0;
        player = new Player(this);
        spawner = new Spawner(this);
        currentZombs = new ArrayList<Zombie>();
        currentBullets = new ArrayList<Bullet>();
        currentInputs = new boolean[11];
        inGame = true;
        try {
            cpistol = ImageIO.read(new File("pixelArt//cpistol.png"));
            cshotgun = ImageIO.read(new File("pixelArt//cshotgun.png"));
            csniper = ImageIO.read(new File("pixelArt//csniper.png"));
            z = ImageIO.read(new File("pixelArt//z.png"));
            bz = ImageIO.read(new File("pixelArt//bz.png"));
            b = ImageIO.read(new File("pixelArt//b.png"));
            background = ImageIO.read(new File("pixelArt//background.png"));
        } catch (IOException e) {
        }

        setFocusable(true);
        requestFocus();
    }
    public void addNotify() {
        super.addNotify();
        if(string == null)
        {
            string = new Thread(this);
            string.start();
        }
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
    public void paint(Graphics g)
    {
        dbImage = createImage(1000, 900);
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
        if(inGame)
        {
	        g.setColor(Color.BLACK);
	        g.fillRect(0, 900, 1000, 100);
	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Times New Roman", Font.PLAIN, 34));
	        g.drawString("Health: " + player.getHealth(), 200, 950);
	        g.drawString("Score: " + score, 700, 950);
        }
        else
        {
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 900, 1000, 100);
        }
    } 

    public void paintComponent(Graphics g)
    {
        int j = 0, temp;
        if(inGame)
        {	
        	super.paintComponent(g);
        	g.drawImage(background, 0, 0, null);
        	AffineTransform at = AffineTransform.getTranslateInstance(player.getXPos() - 25, player.getYPos() - 25);
        	at.rotate(Math.toRadians(player.getDirection()), 25, 25);
        	Graphics2D g2d = (Graphics2D) g;
        	if (player.returnType() == 0)
        		g2d.drawImage(cpistol, at, null);
        	else if (player.returnType() == 1)
        		g2d.drawImage(cshotgun, at, null);
        	else	
        		g2d.drawImage(csniper, at, null);

        	for (int i = 0; i < currentZombs.size(); i++) {
        		int x = currentZombs.get(i).returnRadius();
        		AffineTransform at1 = AffineTransform.getTranslateInstance(currentZombs.get(i).getXPos() - x,
        				currentZombs.get(i).getYPos() - x);
        		at1.rotate(Math.toRadians(currentZombs.get(i).getDirection()), x, x);
        		Graphics2D g2d1 = (Graphics2D) g;
        		if (x == 25)
        			g2d1.drawImage(z, at1, null);
        		else
        			g2d1.drawImage(bz, at1, null);
        	}
        	while(j < currentBullets.size())
        	{
        		temp = currentBullets.size();
        		g.drawImage(b, currentBullets.get(j).getXPos(), currentBullets.get(j).getYPos(), null);
        		if(temp == currentBullets.size())
        		{
        			j++;
        		}
        	}
        }
        else
        {
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, 1000, 1000);
        	g.setColor(Color.WHITE);
        	g.setFont(new Font("Times New Roman", Font.PLAIN, 80));
        	g.drawString("Final Score: " + score, 200, 400);
        	g.drawString("GAME OVER", 300, 600);
        }
    }

    public void run() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long NANOSECONDS_FRAME = 1000000000 / TARGET_FPS;
        while (inGame) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / 1000000000D;
            lastFpsTime += updateLength;
            fps++;
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }
            updateGame(delta);
            long elapsed = System.nanoTime() - now;
            long sleepTime = (NANOSECONDS_FRAME - elapsed) / 1000000L;
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                }
            }
            repaint();
        }
    }

    public void updateGame(double delta) {
        scoreT += delta;
        shootT += delta;
        if (scoreT >= 1) {
            score++;
            System.out.println(score);
            scoreT = 0;
        }
        if(shootT >= player.getFireRate()){
            player.setCanShoot(true);
            shootT = 0;
        }
        spawner.spawn(delta);
        player.toggleInvin(delta);
        move(delta);
        player.shoot(delta);
        detect(delta);
        player.setWeapon(delta);
    }

    public void move(double delta) {
        int i = 0, temp;
        player.move();
        for (Zombie z : currentZombs) {
            z.move();
        }
        while(i < currentBullets.size())
        {
            temp = currentBullets.size();
            currentBullets.get(i).move(delta);
            if(temp == currentBullets.size())
            {
                i++;
            }
        }
    }

    public void detect(double delta) {
        int i = 0, temp;
        for (Zombie z : currentZombs) {
            z.collisionDetect(delta);
        }
        while(i < currentBullets.size())
        {
            temp = currentBullets.size();
            currentBullets.get(i).collisionDetect();
            if(temp == currentBullets.size())
            {
                i++;
            }
        }
    }
    public ArrayList<Zombie> getCurrentZombs() {
        return currentZombs;
    }

    public ArrayList<Bullet> getCurrentBullets() {
        return currentBullets;
    }

    public Player getPlayer() {
        return player;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public int returnKillCount() {
        return killCount;
    }

    public void setKillCount(int k) {
        killCount = k;
    }

    public boolean getInputs(int x) {
        return currentInputs[x];
    }

    public void endGame()
    {
    	inGame = false;
    }

    private class AAdapter extends KeyAdapter // deals with keyboard inputs
    {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W) {
                currentInputs[0] = true;
            }
            if (key == KeyEvent.VK_A) {
                currentInputs[1] = true;
            }
            if (key == KeyEvent.VK_S) {
                currentInputs[2] = true;
            }
            if (key == KeyEvent.VK_D) {
                currentInputs[3] = true;
            }
            if (key == KeyEvent.VK_UP) {
                currentInputs[4] = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                currentInputs[5] = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                currentInputs[6] = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                currentInputs[7] = true;
            }
            if (key == KeyEvent.VK_1) {
                currentInputs[8] = true;
            }
            if (key == KeyEvent.VK_2) {
                currentInputs[9] = true;
            }
            if (key == KeyEvent.VK_3) {
                currentInputs[10] = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W) {
                currentInputs[0] = false;
            }
            if (key == KeyEvent.VK_A) {
                currentInputs[1] = false;
            }
            if (key == KeyEvent.VK_S) {
                currentInputs[2] = false;
            }
            if (key == KeyEvent.VK_D) {
                currentInputs[3] = false;
            }
            if (key == KeyEvent.VK_UP) {
                currentInputs[4] = false;
            }
            if (key == KeyEvent.VK_LEFT) {
                currentInputs[5] = false;
            }
            if (key == KeyEvent.VK_DOWN) {
                currentInputs[6] = false;
            }
            if (key == KeyEvent.VK_RIGHT) {
                currentInputs[7] = false;
            }
            if (key == KeyEvent.VK_1) {
                currentInputs[8] = false;
            }
            if (key == KeyEvent.VK_2) {
                currentInputs[9] = false;
            }
            if (key == KeyEvent.VK_3) {
                currentInputs[10] = false;
            }
        }
    }
}
