package nl.novi.eindopdracht.Image;


import nl.novi.eindopdracht.Courses.Game.Dto.InformationOutputDto;
import nl.novi.eindopdracht.LoginAndSecurity.Model.User;
import nl.novi.eindopdracht.LoginAndSecurity.Repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageDataService imageDataService;

    private final UserRepository userRepository;

    private final ImageDataRepository imageDataRepository;

    public ImageController(ImageDataService imageDataService, UserRepository userRepository, ImageDataRepository imageDataRepository) {
        this.imageDataService = imageDataService;
        this.userRepository = userRepository;
        this.imageDataRepository = imageDataRepository;
    }


    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile multipartFile, @RequestParam String username) throws IOException {
        String image = imageDataService.uploadImage(multipartFile, username);
        return ResponseEntity.ok("file has been uploaded" + image);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> downloadImage (@PathVariable("username") String username) throws IOException {
        byte[] image = imageDataService.downloadImage(username);
        Optional<User> user = userRepository.findById(username);
        Optional<ImageData> dbImageData = imageDataRepository.findById(user.get().getImageData().getId());
        MediaType mediaType = MediaType.valueOf(dbImageData.get().getType());
        return  ResponseEntity.ok().contentType(mediaType).body(image);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteImage (@PathVariable("username") String username) throws IOException {

        imageDataService.deleteImage(username);

        return ResponseEntity.noContent().build();

    }

}
