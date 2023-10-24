package elevador_Multithread;

public class App {

	public static void main(String[] args) {
		Predio predio = new Predio(7);
		new Janela(predio);
	}
}
