package com.fastfeet.Services;

import com.fastfeet.Services.Exception.ObjectNotFound;
import com.fastfeet.Services.util.ImageManager;
import com.fastfeet.repositories.DeliverymanRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class ImageService {

    @Autowired
    private DeliverymanRepository deliverymanRepository;

    public String saveAvatar(MultipartFile image, String name) {
        try {
            if (name == null) name = ImageManager.generateName(Objects.requireNonNull(image.getOriginalFilename())) + ".jpg";
            String source = new File("./images").getCanonicalPath() + "/" + name;
            var inputImage = ImageManager.formatImageBufferImage(image, 400);
            ImageIO.write(inputImage, "jpg", new File(source));
            return name;
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }

    @SneakyThrows
    public String updateAvatar(MultipartFile image, String id) {
        var user = deliverymanRepository.findById(id);
        if (user.isPresent()) {
            user.get().setAvatar(saveAvatar(image, user.get().getAvatar()));
            return deliverymanRepository.save(user.get()).getAvatar();
        } else {
            throw new ObjectNotFound("Usuário não encontrado");
        }
    }

    public ByteArrayResource getAvatar(String name) {
        try {
            String path = new File("./images").getCanonicalPath() + "/" + name;
            return new ByteArrayResource(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new ObjectNotFound("Imagem não encontrada");
        }
    }
}
