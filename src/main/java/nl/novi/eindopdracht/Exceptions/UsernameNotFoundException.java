package nl.novi.eindopdracht.Exceptions;

import java.io.Serial;

public class UsernameNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("Cannot find user " + username);
    }


}
