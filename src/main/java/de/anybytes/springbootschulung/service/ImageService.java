package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.entity.Image;
import de.anybytes.springbootschulung.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public List<Image> getImages() {
        return (List<Image>) imageRepository.findAll();
    }

    public Image updateImage(Image image) {
        Image optImage = imageRepository.findById(image.getId()).orElseThrow(() -> new EntityNotFoundException(image.getId().toString()));
        return imageRepository.save(optImage);
    }

    public void createImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setType(file.getContentType());
        image.setImage(file.getBytes());
        imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
