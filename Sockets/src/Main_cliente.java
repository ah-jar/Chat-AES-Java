import java.io.IOException;
import java.net.UnknownHostException;

public class Main_cliente {
	
	private static Cliente cliente;

	public static void main(String[] args) throws UnknownHostException, IOException {
		cliente = new Cliente("192.168.0.38", 1234);
		cliente.run();
	}
}
