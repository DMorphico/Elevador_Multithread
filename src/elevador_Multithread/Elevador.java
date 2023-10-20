package elevador_Multithread;

import java.util.concurrent.Semaphore;

public class Elevador extends Thread{
	int andar_inicial ;
	private Semaphore semaforo;
	
	
	public Elevador (int _andarInicial, Passageiros[] _andar1, Passageiros[] _andar2, Passageiros[] _andar3, Passageiros[] _andar4, Passageiros[] _andar5) {
		semaforo = new Semaphore(1);
	}
	
	public void Abrirporta() {
		
	}
	public void Fecharporta(Passageiros[] andar) {
		
	}	
	public void Visitar_andar() {
		
	}	
}

