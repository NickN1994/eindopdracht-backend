package nl.novi.eindopdracht.SendEmail;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;


    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail (@Valid @RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto);
        return "Bericht succesvol verstuurd";
    }

}
