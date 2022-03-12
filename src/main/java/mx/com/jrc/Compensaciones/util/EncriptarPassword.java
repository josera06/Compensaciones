package mx.com.jrc.Compensaciones.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
@Slf4j
public class EncriptarPassword {

    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static String generaPassword(int length){
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
        } catch (NoSuchAlgorithmException e){
            log.info("Error encriptando password: " + e.getMessage());
        }
        return password;
    }
}
