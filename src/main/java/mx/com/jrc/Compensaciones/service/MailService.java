package mx.com.jrc.Compensaciones.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class MailService {
    private final static String MyEMAIL = "compensaciones208@gmail.com";

    @Autowired
    private AmazonSimpleEmailServiceClient client;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendAwsSesMail(String myDestination, String mySubject, String myBody){
        Destination destination = new Destination()
                .withToAddresses(myDestination);

        Message message = new Message()
                .withSubject(new Content(mySubject))
                .withBody(new Body(new Content(myBody)));

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withSource(MyEMAIL)
                .withDestination(destination)
                .withMessage(message);

        client.sendEmail(emailRequest);
    }

}
