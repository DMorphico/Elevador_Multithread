package elevador_Multithread;

import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class Elevador extends Thread{
	int andar_inicial;
	int posX = 165, posY;
	Predio predio;
	ImageIcon sprite;
	Andares[] andares;
	private Semaphore semaforo;
	
	
	public Elevador (Andares[] andares, Predio predio) {
		semaforo = new Semaphore(1);
		this.andares = andares;
		this.predio = predio;
		sprite = new ImageIcon("Imagens/Elevador.png");
		posY = andares[0].getPosY() - 25;
	}
	
	public void Abrirporta() {
		
	}
	public void Fecharporta(Passageiros[] andar) {
		
	}	
	public void Visitar_andar(Andares andarDesejado) {
		System.out.println(andarDesejado.getPosY());
		while(posY < andarDesejado.getPosY()-25) {
			posY++;
		}
		andares[0].getPassageiro()[0].moverY(posY);
		System.out.println(posY);
		this.predio.Redesenhar();
	}
	
	public int getPosX() {
		return posX;
	}
	
	public Andares setAndar(int andar){
		return andares[andar];
	}
	
	private void andarInicial() {
		
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(predio, g, posX, posY);
	}
}

