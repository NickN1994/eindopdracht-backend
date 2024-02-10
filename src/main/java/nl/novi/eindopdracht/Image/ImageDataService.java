package nl.novi.eindopdracht.Image;


import nl.novi.eindopdracht.Exceptions.UsernameNotFoundException;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;
import nl.novi.eindopdracht.LoginAndSecurity.Repository.UserRepository;
import nl.novi.eindopdracht.LoginAndSecurity.Utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;
    private final UserRepository userRepository;


    public ImageDataService(ImageDataRepository imageDataRepository, UserRepository userRepository) {
        this.imageDataRepository = imageDataRepository;
        this.userRepository = userRepository;
    }

//    public String uploadImage(MultipartFile multipartFile, String username) throws IOException {
//        Optional<User> user = userRepository.findById(username);
//        User user1 = user.get();
//        // dit moet eigenlijk in if statement
//
//        ImageData imageData = new ImageData();
//        imageData.setName(multipartFile.getName());
//        imageData.setType(multipartFile.getContentType());
//        imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));
//        imageData.setUser(user1);
//
//        ImageData savedImage = imageDataRepository.save(imageData);
//        user1.setImage(imageData);
//        userRepository.save(user1);
//
//        return savedImage.getName();
//    }

    public String uploadImage(MultipartFile multipartFile, String username) throws IOException {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            ImageData imageData = new ImageData();
            imageData.setName(multipartFile.getOriginalFilename());
            imageData.setType(multipartFile.getContentType());
            imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));
            imageData.setUser(user);

            ImageData savedImage = imageDataRepository.save(imageData);
            user.setImage(imageData); // Zorg ervoor dat User klasse een setImage methode heeft
            userRepository.save(user);

            return savedImage.getName();
        } else {
            throw new UsernameNotFoundException("Gebruiker niet gevonden met gebruikersnaam: " + username);
        }
    }


    public byte[] downloadImage (String username) throws IOException {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            ImageData imageData = user.getImageData(); // Zorg ervoor dat deze methode of veld bestaat en correct is geïmplementeerd in User.
            if (imageData != null) {
                return ImageUtil.decompressImage(imageData.getImageData());
            }
        }
        throw new IllegalArgumentException("Gebruiker niet gevonden of geen afbeelding beschikbaar voor gebruiker: " + username);
    }

}
