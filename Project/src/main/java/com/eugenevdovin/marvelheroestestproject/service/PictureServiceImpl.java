package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.repository.CharacterRepository;
import com.eugenevdovin.marvelheroestestproject.repository.ComicRepository;
import com.eugenevdovin.marvelheroestestproject.entity.PictureEntity;
import com.eugenevdovin.marvelheroestestproject.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureRepository pictureRepository;
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;

    @Override
    public PictureEntity getPicture(int id) {
        return pictureRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void savePicture(PictureEntity pictureEntity) {
        pictureRepository.save(pictureEntity);
    }

    @Override
    public PictureEntity getPictureByComicId(int comicId) {
        return comicRepository.findById(comicId).get().getPicture();
    }

    @Override
    public PictureEntity getPicturesByCharacterId(int characterId) {
        return characterRepository.findById(characterId).get().getPicture();
    }

    @Override
    @Transactional
    public ResponseEntity<Object> uploadPicture(MultipartFile file, Integer id, String entityType) throws IOException {
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            PictureEntity newPictureEntity = new PictureEntity();
            newPictureEntity.setImageBytes(file.getBytes());
            if (entityType.equals("character")) {
                CharacterEntity characterEntity = characterRepository.findById(id).get();
                if (characterEntity.hasPicture()) pictureRepository.delete(characterEntity.getPicture());
                newPictureEntity.setCharacterEntity(characterEntity);
                characterEntity.setPicture(newPictureEntity);
                pictureRepository.save(newPictureEntity);
                return new ResponseEntity<>("The image uploaded to database successfully", HttpStatus.OK);
            }
            else if (entityType.equals("comic")) {
                ComicEntity comicEntity = comicRepository.findById(id).get();
                if (comicEntity.hasPicture()) pictureRepository.delete(comicEntity.getPicture());
                newPictureEntity.setComicEntity(comicEntity);
                comicEntity.setPicture(newPictureEntity);
                pictureRepository.save(newPictureEntity);
                return new ResponseEntity<>("The image uploaded to database successfully", HttpStatus.OK);
            }
        }
        else return new ResponseEntity<>("Error, only .jpg and .png files supported", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<byte[]> getPictureAsResponseEntity(Integer id, String entityType) {
        if (entityType.equals("character")) {
            PictureEntity pictureEntity = this.getPicturesByCharacterId(id);
            return new ResponseEntity<>(pictureEntity.getImageBytes(), HttpStatus.OK);
        }
        else if (entityType.equals("comic")) {
            PictureEntity pictureEntity = this.getPicturesByCharacterId(id);
            return new ResponseEntity<>(pictureEntity.getImageBytes(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
