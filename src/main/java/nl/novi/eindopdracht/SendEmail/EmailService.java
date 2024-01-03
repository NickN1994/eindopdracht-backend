package nl.novi.eindopdracht.SendEmail;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sendTo;

    public void sendEmail (EmailDto emailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(sendTo);
        mailMessage.setText("Naam: " + emailDto.getName() + "\n" +
                            "Telefoonnummer: " + emailDto.getPhoneNumber() + "\n" +
                            "Email: " + emailDto.getEmailSender() + "\n" +
                            "Bericht: " + emailDto.getMessage());

        javaMailSender.send(mailMessage);
    }

}
