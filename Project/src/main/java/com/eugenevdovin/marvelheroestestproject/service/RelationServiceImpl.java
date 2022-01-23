package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    ComicService comicService;
    @Autowired
    CharacterService characterService;

    @Transactional
    public ResponseEntity<Object> addRelation(int comicId, int characterId) {
        if (!characterService.existsById(characterId))
            return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
        if (!comicService.existsById(comicId))
            return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        ComicEntity comicEntity = comicService.getComic(comicId);
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        if (characterEntity.getComics().contains(comicEntity)) return new ResponseEntity<>("That character and that comic already linked", HttpStatus.BAD_REQUEST);
        else {
            comicEntity.addCharacterToComic(characterEntity);
            return new ResponseEntity<>("Comic added for character successfully", HttpStatus.OK);
        }
    }
}
