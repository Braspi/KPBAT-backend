package tech.wiktor.apis.kpbatapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tech.wiktor.apis.kpbatapi.controllers.image.ImageResponse;
import tech.wiktor.apis.kpbatapi.controllers.image.ImageUploadRequest;
import tech.wiktor.apis.kpbatapi.controllers.image.ImageUploadResponse;
import tech.wiktor.apis.kpbatapi.models.Image;
import tech.wiktor.apis.kpbatapi.repositories.ImageRepository;
import tech.wiktor.apis.kpbatapi.utils.ImageUtility;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> upload(@Valid @NotNull @RequestParam("image") MultipartFile file) throws IOException {
        this.imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes()))
                .build());

        return new ResponseEntity<>(new ImageUploadResponse("Image uploaded successfully: " + file.getOriginalFilename()), HttpStatus.OK);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<?> getImageById(@PathVariable("imageId") Long imageId){
        Image image = this.imageRepository.findById(imageId).get();
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType())).body(ImageUtility.decompressImage(image.getImage()));
    }

    @GetMapping()
    public ResponseEntity<?> findImages(@RequestParam(value = "categoryId", required = false) Long categoryId){
        if(categoryId == null){
            List<Image> images = this.imageRepository.findAll();
            List<ImageResponse> response = new ArrayList<>();
            return new ResponseEntity<>(this.imageRepository.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(categoryId, HttpStatus.OK);
    }
}
