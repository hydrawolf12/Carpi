import java.awt.EventQueue;
import javax.swing.JFrame;
public class CarpiGame extends JFrame 
{	
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
