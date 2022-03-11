package mx.com.jrc.Compensaciones.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class EncriptarPassword {

    public static String getPassword(int longitud){
        return encriptarPassword(generaPassword(longitud));
    }

    private static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private static String generaPassword(int length){
        String[] symbols = {"0", "1", "-", "*", "%", "$", "a", "b", "c"};
        String password = "";
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int indexRandom = random.nextInt ( symbols.length );
                sb.append( symbols[indexRandom] );
            }
            password = sb.toString();
            System.out.println(password);
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
        return password;
    }
}
