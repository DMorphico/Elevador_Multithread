package elevador_Multithread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Janela extends JFrame implements ActionListener{
	JButton startButton;
	JPanel UI;
	
	public Janela() {
		janelaConfig();
	}
	
	private void janelaConfig() {
		setLayout(new BorderLayout());
		setName("Elevador");
		setSize(new Dimension(800,800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
