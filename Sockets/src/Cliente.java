import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import org.jasypt.util.text.AES256TextEncryptor;

public class Cliente {

    private String key, ip;
    private int port;
    
    public Cliente(String ip, int port) {
    	this.ip = ip;
    	this.port = port;
    }

    public void run() throws IOException {
        Scanner kb = new Scanner(System.in);
        System.out.print("Introduce la key: ");
        key = kb.nextLine();

        try {
            DataInputStream in;
            DataOutputStream out;
            Socket sc = new Socket(ip, port);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            System.out.println("- - - Conexion establecida - - - ");
            
            Escribir escribir = new Escribir(out, key);
            escribir.start();
            
            while (true) {
                String mensaje = in.readUTF();
                System.out.println(descifrar(mensaje));
            }
        } catch (Exception e) {
            System.out.println("Conexión perdida");
        }
    }

    protected String descifrar(String text) {
        AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword(key);
        String myEncryptedPassword = aesEncryptor.decrypt(text);
        return myEncryptedPassword;
    }

}

