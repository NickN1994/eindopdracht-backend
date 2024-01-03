package nl.novi.eindopdracht.SendEmail;

import jakarta.validation.constraints.NotBlank;

public class EmailDto {


    private final String name;
    private final String phoneNumber;
    private final String emailSender;
    private final String message;

    public EmailDto(String name, String phoneNumber, String emailSender, String message) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailSender = emailSender;
        this.message = message;
    }
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailSender() {
        return emailSender;
    }


    public String getMessage() {
        return message;
    }

}
