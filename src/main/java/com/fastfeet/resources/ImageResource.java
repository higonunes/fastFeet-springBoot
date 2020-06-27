package com.fastfeet.resources;

import com.fastfeet.GlobalVariables;
import com.fastfeet.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageResource {


    @Autowired
    private ImageService imageService;

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        ByteArrayResource img = imageService.getAvatar(imageName);

        return ResponseEntity.ok()
                             .contentLength(img.contentLength())
                             .contentType(MediaType.IMAGE_JPEG)
                             .body(img);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<String> updateImage(@PathVariable String userId, MultipartFile file) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON
                )
                .body("{ \"avatarURL\": \""+ GlobalVariables.baseURL+ "/image/" + imageService.updateAvatar(file, userId) + "\"}");
    }

}
