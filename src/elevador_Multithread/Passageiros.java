package elevador_Multithread;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Passageiros {
	Andares andarInicial, andarDesejado;
	int posX, posY;
	boolean estado;
	ImageIcon sprite;
	Predio predio;
	Random r = new Random();
	
	public Passageiros(Predio _predio, Andares _andarInicial, int posX){
		sprite = new ImageIcon("Imagens/Trabalhador.png");
		this.predio = _predio;
		this.andarInicial = _andarInicial;
		this.posX = posX;
		this.posY = andarInicial.getPosY() - 15;
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
		System.out.println(y);
		this.posY = y;
		predio.Redesenhar();
		moverX(-20);
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(predio, g, posX, posY);
	}
	
	public void start() {
		moverX(predio.getElevador().getPosX() + 10);
	}
}
