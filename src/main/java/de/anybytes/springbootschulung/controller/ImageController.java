package de.anybytes.springbootschulung.controller;

import de.anybytes.springbootschulung.aspect.TrackExecutionTime;
import de.anybytes.springbootschulung.entity.Image;
import de.anybytes.springbootschulung.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}/picture")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> getPicture(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType())).body(image.getImage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageService.getImages());
    }

    @PostMapping("/save")
    public ResponseEntity<Void> createImage(@RequestParam("image") MultipartFile file) throws IOException {
        imageService.createImage(file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}
