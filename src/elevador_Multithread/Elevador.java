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
	Andares andarAtual, andarDesejado;
	private Semaphore semaforo;
	boolean portaAberta = false;
	//int andarIndex = getAndarIndex();
	
	public Elevador (Andares[] andares, Predio predio, Semaphore _semaforo) {
		semaforo = _semaforo;
		this.andares = andares;
		this.predio = predio;
		sprite = new ImageIcon("Imagens/Elevador.png");
		posY = andarInicial().getPosY() - 25;
		andarAtual = andarInicial();
		
	}
	public void run() {
		while(true) {
			VisitarAndar(andarDesejado);

		}
	}
	
	private void esperar() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Abrirporta() {
		portaAberta = true;
	}
	
	public void Fecharporta() {
		portaAberta = false;
	}	
	
	public void VisitarAndar(Andares _andarDesejado) {
	    while(posY != _andarDesejado.getPosY() - 25) {
	    	if(_andarDesejado.getPosY()- 25 > posY) {
	    		posY++;
	    	}else {
	    		posY--;
	    	}
	    }
	    
	    esperar();
	    andarDesejado = _andarDesejado;
	}
	
	public void setAndar(Andares andares) {
		this.andarDesejado = andares;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
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
	public boolean isPortaAberta() {
		return portaAberta;
	}
}

