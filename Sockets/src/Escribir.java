import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.jasypt.util.text.AES256TextEncryptor;

public class Escribir extends Thread{
	
	private DataOutputStream out;
	private Scanner kb = new Scanner(System.in);
	private String key;
	
	public Escribir(DataOutputStream out, String key) {
		this.out = out;
		this.key = key;
	}
	
	@Override
	public void run() {
		while (true) {
			String mensaje = kb.nextLine();
			try {
				this.out.writeUTF(cifrar(mensaje));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected String cifrar(String text) {
        try {
        	AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
		    aesEncryptor.setPassword(key);
		    String myEncryptedPassword = aesEncryptor.encrypt(text);
            return myEncryptedPassword;
        } catch (Exception e) {
       	 	System.err.println("Error al cifrar");
       	    return null;
        }
	}
}
