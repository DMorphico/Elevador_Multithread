package elevador_Multithread;

import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class Passageiros extends Thread{
	Andares andarAtual, andarDesejado;
	int posX, posY;
	boolean ativo = true, elemRemovido = false;
	ImageIcon sprite;
	Predio predio;
	Random r = new Random();
	Semaphore semaphore, indexSemaphore;
	int index;
	
	public Passageiros(Predio _predio, Andares _andarInicial, int posX, Semaphore _semaphore, int index){
		sprite = new ImageIcon("Imagens/Trabalhador.png");
		this.index =index ;
		this.predio = _predio;
		this.andarAtual = _andarInicial;
		this.posX = posX;
		this.posY = andarAtual.getPosY() - 15;
		this.semaphore = _semaphore;
		this.indexSemaphore = _semaphore;
	}
	
	public void run() {
		this.andarDesejado = setAndar();
		getAndarAtual(andarAtual);
		
		while(ativo) {
			if(!elemRemovido) {
				attPassageirosIndex();
			}
			if(andarAtual.getPassageiro()[index] == andarAtual.getPassageiro()[0]) {
				chamarElevador();
				esperar(1000);
				entrarElevador();
				esperar(1000);
				predio.getElevador().VisitarAndar(this.andarDesejado);
				moverY();
				sairElevador();
				esperar(1000);
				andarAtual.removePassageiro(andarAtual.getPassageiro());
			}
		}
	}
	
	
    public void attPassageirosIndex() {
        elemRemovido = true;
    	try {
            indexSemaphore.acquire();
            if (index > 0) {
                index--;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            elemRemovido = false;
            indexSemaphore.release();   
        }
    }
	
	private void esperar(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void chamarElevador() {
		try {
			semaphore.acquire();
			predio.getElevador().VisitarAndar(andarAtual);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void entrarElevador() {
		moverX(predio.getElevador().getPosX() + 5);
	}

	public Andares setAndar() {
		andarDesejado = andarAtual;
		do {
		    int randomIndex = r.nextInt(predio.getAndares().length);
		    andarDesejado = predio.getAndares()[randomIndex];
		} while (andarDesejado == andarAtual);
		
		return andarDesejado;
	}
	
	public Andares getAndarAtual(Andares andarAtual) {
		return andarAtual;
	}
	
	private void moverX(int x) {
		if(x < 0) {
			while(this.posX > x) {
				predio.Redesenhar();
				esperar(10);
				this.posX -= 1;
			}
		}else {
			while(this.posX < x) {
				predio.Redesenhar();
				esperar(10);
				this.posX += 1;
			}
		}
	}
	
	
	private void sairElevador() {
		while(this.posX > -20) {
			predio.Redesenhar();
			esperar(10);
			this.posX--;
		}
		semaphore.release();
		ativo = false;
	}
	
	
	public void moverY() {
		int elevadorPosY = predio.getElevador().getPosY();
		while(this.posY != elevadorPosY) {
			if(this.posY <= elevadorPosY) {
				this.posY++;
			}else if(this.posY >= elevadorPosY){
				this.posY--;
			}
		}
	}
	
	public void draw(Graphics g) {
		sprite.paintIcon(predio, g, posX, posY);
	}
	
}
