package mx.com.jrc.Compensaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body) throws UnsupportedEncodingException {
        SimpleMailMessage message=new SimpleMailMessage();
        String remitente = "compensaciones208@gmail.com";
        String  copia = "desarrollo208@gmail.com";

        message.setFrom(String.valueOf(new InternetAddress(remitente,"SNTSS Sección 37 - Trámites y avisos")));
        message.setTo(toEmail);
        message.setBcc(copia);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail sent successfully...");

    }

}
