package nl.novi.eindopdracht.LoginAndSecurity.Service;


import nl.novi.eindopdracht.LoginAndSecurity.Dto.UserDto;
import nl.novi.eindopdracht.LoginAndSecurity.Model.Authority;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;
import nl.novi.eindopdracht.LoginAndSecurity.Repository.UserRepository;
import nl.novi.eindopdracht.LoginAndSecurity.Utils.RandomStringGenerator;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public UserDto updateUser(String username, UserDto newUser) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            User user1 = user.get();
            if (newUser.getName() != null) {
                user1.setName(newUser.getName());
            }
            if (newUser.getUsername() != null) {
                user1.setUsername(newUser.getUsername());
            }
//            if (newUser.getUsername() != null) {
//                user1.setUsername(newUser.getUsername());
//            }
            if (newUser.getPassword() != null) {
                user1.setPassword(passwordEncoder.encode(newUser.password));
            }
            if (newUser.getEmail() != null) {
                user1.setEmail(newUser.getEmail());
            }
            User updatedUser = userRepository.save(user1);
            return fromUser(updatedUser);
        } else {
            throw new UsernameNotFoundException("Gebruiker kan niet bewerkt worden.");
        }
    }

//        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
//        User user = userRepository.findById(username).get();
//        user.setName();
//        user.setPassword(passwordEncoder.encode(newUser.password));
//        userRepository.save(user);
//    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException((username));
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException((username));
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.name = user.getName();
        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
