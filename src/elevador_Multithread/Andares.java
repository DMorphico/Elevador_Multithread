package elevador_Multithread;

import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Andares extends JPanel{
	ImageIcon sprite;
	int posY, numPassageiro;
	Random random;
	Predio predio;
	Passageiros[] passageiros;
	
	public Andares(int _posY, Predio _predio, Semaphore _semaphore) {
		this.posY = _posY;
		sprite = new ImageIcon("Imagens/Floor.png");
		this.predio = _predio;
		this.posY = _posY * sprite.getIconHeight();
		
		random = new Random();
		numPassageiro = random.nextInt(6);
		passageiros = new Passageiros[numPassageiro];
		for(int i = 0; i < passageiros.length; i++) {
			passageiros[i] = new Passageiros(predio, this, 145 - (i*20), _semaphore);
			}
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getLargura() {
		return sprite.getIconWidth();
	}
	
	public int getAltura() {
		return sprite.getIconHeight();
	}
	
	public Passageiros[] getPassageiro() {
		return passageiros;
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(predio, g, 0, posY);
	}
}
