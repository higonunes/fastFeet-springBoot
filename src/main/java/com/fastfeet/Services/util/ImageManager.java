package com.fastfeet.Services.util;

import javassist.scopedpool.ScopedClassPoolRepository;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public class ImageManager {

    public static BufferedImage formatImageBufferImage(MultipartFile img, Integer size) throws IOException {
        var result = getJpgImageFromFile(img);
        result = cropSquare(result);
        return resize(result,size);
    }

    public static String generateName(String name) {
        byte[] array = new byte[0];
        try {
            array = java.security.MessageDigest.getInstance("MD5").digest(name.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
       return sb.toString();
    }

    private static BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) throws IOException {
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
        if(!"png".equals(ext) && !"jpg".equals(ext)) {
            throw new IOException("Somente imagens PNG e JPG sao permitidas");
        }

        try {
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
            if("png".equals(ext)) {
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw new IOException("Erro ao ler arquivo");
        }
    }

    private static BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.white, null);
        return jpgImage;
    }

    private static BufferedImage cropSquare(BufferedImage sourceImg) {
        int min = (Math.min(sourceImg.getHeight(), sourceImg.getWidth()));
        return Scalr.crop(
                sourceImg,
                (sourceImg.getWidth()/2) - (min/2),
                (sourceImg.getHeight()/2) - (min/2),
                min,
                min
        );
    }

    private static BufferedImage resize(BufferedImage source, int size) {
        return Scalr.resize(source, Scalr.Method.ULTRA_QUALITY, size);
    }

    public static InputStream getInputStream(BufferedImage img, String extension) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new IOException("Erro ao ler arquivo");
        }


    }
}
