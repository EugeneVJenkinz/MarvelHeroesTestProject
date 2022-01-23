package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.PictureEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    ResponseEntity<Object> uploadPicture(MultipartFile file, Integer id, String entityType) throws IOException;
    ResponseEntity<byte[]> getPictureAsResponseEntity(Integer id, String entityType);
}
