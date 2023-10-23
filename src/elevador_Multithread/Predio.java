package elevador_Multithread;

import java.awt.Graphics;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;

public class Predio extends JPanel{

	Andares[] andares;
	Elevador elevador;
	Passageiros[] passageiros;
	Semaphore semaphore;

	public Predio(int numAndares) {
		semaphore = new Semaphore(1);
		andares = new Andares[numAndares];
		for(int i = 0; i < andares.length; i++) {
			andares[i] = new Andares(i + 50 + (i*5), this, semaphore);
		}
		elevador = new Elevador(andares, this, semaphore);
	}
	
	public Andares[] getAndares() {
		return andares;
	}
	
	public int getAltura() {
		return(andares.length + 1) * andares[0].getAltura();
	}
	
	public Elevador getElevador() {
		return elevador;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i < andares.length; i++) {
			andares[i].draw(g);
		}
		
		elevador.draw(g);
		
		for(int i = 0; i < andares.length; i++) {
			for(int j = 0; j < andares[i].getPassageiro().length ; j++) {
				andares[i].getPassageiro()[j].draw(g);
			}
		}
		
	}
	
	public void Redesenhar() {
		this.revalidate();
		this.repaint();
	}

	public void start() {
		andares[elevador.getAndarIndex()].getPassageiro()[0].start();
		elevador.Visitar_andar(andares[elevador.getAndarIndex()].getPassageiro()[0].setAndar());
		loop();
	}
	
	public void loop() {
		
	}
}
