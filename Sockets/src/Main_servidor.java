import java.io.IOException;

public class Main_servidor {

	public static void main(String[] args) throws IOException {
		Servidor server = new Servidor();
		server.esperarConexion();
	}
}
