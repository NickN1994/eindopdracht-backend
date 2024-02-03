package nl.novi.eindopdracht.Image;


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

    public String uploadImage(MultipartFile multipartFile, String username) throws IOException {
        Optional<User> user = userRepository.findById(username);
        User user1 = user.get();
        // dit moet eigenlijk in if statement

        ImageData imageData = new ImageData();
        imageData.setName(multipartFile.getName());
        imageData.setType(multipartFile.getContentType());
        imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));
        imageData.setUser(user1);

        ImageData savedImage = imageDataRepository.save(imageData);
        user1.setImage(imageData);
        userRepository.save(user1);

        return savedImage.getName();
    }

    public byte[] downloadImage (String username) throws IOException {
        Optional<User> user = userRepository.findById(username);
        User user1 = new User();
        ImageData imageData = user1.getImageData();
        return ImageUtil.decompressImage(imageData.getImageData());
    }

}
