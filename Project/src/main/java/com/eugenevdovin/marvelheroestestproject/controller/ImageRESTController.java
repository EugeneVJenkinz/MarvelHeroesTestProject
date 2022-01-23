package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.service.CharacterService;
import com.eugenevdovin.marvelheroestestproject.service.ComicService;
import com.eugenevdovin.marvelheroestestproject.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/public")
public class ImageRESTController {
    @Autowired
    PictureService pictureService;
    @Autowired
    CharacterService characterService;
    @Autowired
    ComicService comicService;

    @PostMapping("/uploadImage")
    public ResponseEntity<Object> fileUploadDB
            (@RequestParam MultipartFile file,
             @RequestParam Integer id,
             @RequestParam String entityType) throws IOException {
        return pictureService.uploadPicture(file, id, entityType);
    }

    @GetMapping(value = "/showImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPictureAsResponseEntity(@RequestParam Integer id, @RequestParam String entityType) {
        return pictureService.getPictureAsResponseEntity(id, entityType);
    }
}
