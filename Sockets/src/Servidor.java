import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.jasypt.util.text.AES256TextEncryptor;

public class Servidor {
	
	private ServerSocket serverSocket;
	private final int PORT = 1234;
	private DataInputStream in;
	private DataOutputStream out;
	private Scanner kb = new Scanner(System.in);
	private String key;
	
	public Servidor() throws IOException {
		this.serverSocket = new ServerSocket(PORT, 5);
	}
	
	public void esperarConexion() throws IOException {
		introducirPass();
		
		System.out.println("Esperando conexiones...");
		Socket socket = serverSocket.accept();
		System.out.println("Cliente conectado\nIP: " + socket.getLocalAddress().toString().substring(1) + 
				"\nPort: " + socket.getLocalPort() + "\n");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		Escribir escribir = new Escribir(out, key);
		escribir.start();
		while (true) {
			try {
				String mensaje = in.readUTF();
				descifrar(mensaje);
			}
			catch (Exception e) {
				break;
			}
		}
		System.out.println("Cliente desconectado");
	}
	
	protected void introducirPass() {
		System.out.print("Introduce una pass para cifrar: ");
		key = kb.next();
	}
	
	protected void descifrar(String text) {
		try {
			AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
		    aesEncryptor.setPassword(key);
		    String myEncryptedPassword = aesEncryptor.decrypt(text);
		    System.out.println(myEncryptedPassword);
		} catch (Exception e) {
			System.err.println("Error al descrifrar");
		}
	}
}
	
