import java.awt.EventQueue;
import javax.swing.JFrame;
public class CarpiGame extends JFrame 
{	
	public CarpiGame () 
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
	}

}
