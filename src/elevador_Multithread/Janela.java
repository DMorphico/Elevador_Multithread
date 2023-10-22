package elevador_Multithread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Janela extends JFrame implements ActionListener {
    JButton startButton;
    JPanel UI;
    Predio predio;

    public Janela(Predio _predio) {
    	this.predio = _predio;
    	janelaConfig();
    }

    private JPanel configUI() {
        UI = new JPanel();
        UI.setLayout(new BorderLayout());

        startButton = new JButton("Começar");
        startButton.addActionListener(this);
        UI.add(startButton);
        return UI;
    }

    private void janelaConfig() {
        setLayout(new BorderLayout());
        setTitle("Elevador");
        setSize(new Dimension(300, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(predio, BorderLayout.CENTER);
        add(configUI(), BorderLayout.SOUTH);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
        	predio.start();
            UI.remove(startButton);
        }
    }
}