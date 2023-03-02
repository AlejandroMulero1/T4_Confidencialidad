import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Introduzca el mensaje");
        Scanner sc=new Scanner(System.in);
        String msg= sc.nextLine();
       String msgCifrado= cifrar(msg);
        System.out.println(msgCifrado);
       descifrar(msgCifrado);
    }

    public static String cifrar(String msg) throws Exception {

        PublicKey clavePublica = KeysManager.getClavePublica();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clavePublica);

        byte[] mensaje = msg.getBytes(StandardCharsets.UTF_8);
        // Se cifra el mensaje
        byte[] mensajeCifrado = cipher.doFinal(mensaje);

        // Lo imprimimos por pantalla en Base64
        return Base64.getEncoder().encodeToString(mensajeCifrado);
    }

    public static void descifrar(String msg) throws Exception {
        // Tomamos la clave privada
        PrivateKey clavePrivada = KeysManager.getClavePrivada();

        Cipher cipher = Cipher.getInstance("RSA");

        // Desciframos con la clave privada
        cipher.init(Cipher.DECRYPT_MODE, clavePrivada);

        byte[] mensajeCifrado = Base64.getDecoder().decode(msg);

        // Se obtiene el mensaje descifrado
        byte[] mensaje = cipher.doFinal(mensajeCifrado);

        // Lo imprimimos por pantalla en Base64
        System.out.println(new String(mensaje));
    }
}
