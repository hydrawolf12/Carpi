import java.awt.EventQueue;
import javax.swing.JFrame;
public class CarpiGame extends JFrame 
{	
	/*public CarpiGame () Dis some Trash b0i
	{
		initGame();
	}
	public void initGame() 
	{
		add(new Board());
		setTitle("Carpi Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350,350);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				CarpiGame ex = new CarpiGame();
				ex.setVisible(true);
			}
		});
	}*/
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame("Cowboy Carpi");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Board b = new Board();
		f.add(b);
		f.setSize(1000, 1000);
		f.setVisible(true);
		
	}
}
