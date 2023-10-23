package elevador_Multithread;

import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class Passageiros extends Thread{
	Andares andarInicial, andarDesejado;
	int posX, posY;
	boolean estado;
	ImageIcon sprite;
	Predio predio;
	Random r = new Random();
	Semaphore semaphore;
	
	public Passageiros(Predio _predio, Andares _andarInicial, int posX, Semaphore _semaphore){
		sprite = new ImageIcon("Imagens/Trabalhador.png");
		this.predio = _predio;
		this.andarInicial = _andarInicial;
		this.posX = posX;
		this.posY = andarInicial.getPosY() - 15;
		this.semaphore = _semaphore;
	}

	public void run() {
		
	}
	
	public Andares setAndar() {
		andarDesejado = andarInicial;
		do {
		    int randomIndex = r.nextInt(predio.getAndares().length);
		    andarDesejado = predio.getAndares()[randomIndex];
		} while (andarDesejado == andarInicial);
		
		return andarDesejado;
	}
	
	private void moverX(int x) {
		if(x < 0) {
			while(this.posX > x) {
				this.posX -= 1;
				predio.Redesenhar();
			}
		}else {
			while(this.posX < x) {
				this.posX += 1;
				predio.Redesenhar();
			}
		}
		
	}
	
	
	public void moverY(int y) {
		
		if(posY < y) {
			while(posY < y) {
				posY++;
				predio.Redesenhar();	
			}
		}
		moverX(-20);
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(predio, g, posX, posY);
	}
	
	public void start() {
		moverX(predio.getElevador().getPosX() + 10);
	}
}
