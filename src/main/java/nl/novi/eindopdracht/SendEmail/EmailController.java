package nl.novi.eindopdracht.SendEmail;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<String> sendEmail (@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return ResponseEntity.badRequest().body("Validatiefout: " + bindingResult.getAllErrors());
        } else {
            emailService.sendEmail(emailDto);
            return ResponseEntity.ok("Bericht succesvol verstuurd");
        }
    }
}
