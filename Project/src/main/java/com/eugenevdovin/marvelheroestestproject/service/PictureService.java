package com.eugenevdovin.marvelheroestestproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {
    ResponseEntity<Object> uploadPicture(MultipartFile file, Integer id, String entityType) throws IOException;
    ResponseEntity<byte[]> getPictureAsResponseEntity(Integer id, String entityType);
}
