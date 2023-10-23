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
	Andares andarInicial;
	private Semaphore semaforo;
	
	
	public Elevador (Andares[] andares, Predio predio, Semaphore _semaforo) {
		semaforo = _semaforo;
		this.andares = andares;
		this.predio = predio;
		sprite = new ImageIcon("Imagens/Elevador.png");
		posY = andarInicial().getPosY() - 25;
	}
	
	public void Abrirporta() {
		try {
			semaforo.acquire();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		semaforo.release();
	}
	
	public void Fecharporta(Passageiros[] andar) {
		
	}	
	public void Visitar_andar(Andares andarDesejado) {
		int andarIndex = getAndarIndex();
		if (andarIndex >= 0) {
			while(posY < andarDesejado.getPosY()-25) {
				posY++;
			}
		    andares[andarIndex].getPassageiro()[0].moverY(posY);
		}
		System.out.println(posY);
		this.predio.Redesenhar();
	}
	
	public int getPosX() {
		return posX;
	}
	
	public Andares setAndar(int andar){
		return andares[andar];
	}
	
	private Andares andarInicial() {
		for(Andares andares : this.andares) {
			if(andares.getPassageiro().length != 0) {
				return andares;
			}
		}
		return null;
	}
	
	public int getAndarIndex() {
	    int elevadorPosY = predio.getElevador().posY;
	    
	    for (int i = 0; i < andares.length; i++) {
	        if (elevadorPosY == andares[i].getPosY() - 25) {
	            return i; 
	        }
	    }
	    return -1;
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(predio, g, posX, posY);
	}
}

