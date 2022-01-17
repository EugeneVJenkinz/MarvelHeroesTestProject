package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.service.CharacterService;
import com.eugenevdovin.marvelheroestestproject.service.ComicService;
import com.eugenevdovin.marvelheroestestproject.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/public")
public class RESTController {
    @Autowired
    CharacterService characterService;
    @Autowired
    ComicService comicService;
    @Autowired
    PictureService pictureService;

    @GetMapping("/characters/{characterId}")
    public CharacterEntity getCharacter(@PathVariable int id) {
        CharacterEntity character = characterService.getCharacter(id);
        if (character == null) System.out.println("Character not found");//Сделать полноценную обработку исключений
        return character;
    }

    @GetMapping("/characters")
    public List<CharacterEntity> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @PostMapping("/characters")
    public CharacterEntity addNewCharacter(@RequestBody CharacterEntity characterEntity) {
        characterService.saveCharacter(characterEntity);
        return characterEntity;
    }
}
