package elevador_Multithread;

public class App {

	public static void main(String[] args) {
		Predio predio = new Predio(10);
		new Janela(predio);
	}
}
