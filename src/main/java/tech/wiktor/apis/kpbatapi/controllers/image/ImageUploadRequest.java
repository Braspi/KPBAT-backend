package tech.wiktor.apis.kpbatapi.controllers.image;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
public class ImageUploadRequest {
    @NotEmpty(message = "Please select file")
    private MultipartFile file;
}
