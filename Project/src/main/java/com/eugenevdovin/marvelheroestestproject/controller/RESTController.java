package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.wrapper.CharacterWrapper;
import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.service.CharacterService;
import com.eugenevdovin.marvelheroestestproject.service.ComicService;
import com.eugenevdovin.marvelheroestestproject.service.PictureService;
import com.eugenevdovin.marvelheroestestproject.service.RelationService;
import com.eugenevdovin.marvelheroestestproject.wrapper.ComicWrapper;
import com.eugenevdovin.marvelheroestestproject.wrapper.WrapExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    RelationService relationService;

    //Допилить проверку на совпадения по именам + Сделать полноценную обработку исключений с выбросом HTTP ошибок
    //Внедрить загрузку/выгрузку изображений
    //Постраничная загрузка
    //Swagger
    //Docker

    @GetMapping("/characters/{characterId}")
    public CharacterWrapper getCharacter(@PathVariable int characterId) {
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        if (characterEntity == null) System.out.println("Character not found");
        return WrapExecutor.getCharacterWrapper(characterEntity);
    }

    @GetMapping("/characters")
    public List<CharacterWrapper> getAllCharacters() {
        return WrapExecutor.getCharacterWrapperList(characterService.getAllCharacters());
    }

    @PostMapping("/characters")
    public CharacterEntity addNewCharacter(@RequestBody CharacterEntity characterEntity) {
        characterService.saveCharacter(characterEntity);
        return characterEntity;
    }

    @GetMapping("/comics/{comicId}")
    public ComicWrapper getComic(@PathVariable int comicId) {
        ComicEntity comicEntity = comicService.getComic(comicId);
        if (comicEntity == null) System.out.println("Character not found");
        return WrapExecutor.getComicWrapper(comicEntity);
    }

    @GetMapping("/comics")
    public List<ComicWrapper> getAllComics() {
        return WrapExecutor.getComicWrapperList(comicService.getAllComics());
    }

    @PostMapping("/comics")
    public ComicEntity addNewComic(@RequestBody ComicEntity comicEntity) {
        comicService.saveComic(comicEntity);
        return comicEntity;
    }

    @DeleteMapping("/comics/{comicId}")
    public void deleteComicById(@PathVariable int comicId) {
        comicService.deleteComic(comicId);
    }

    @GetMapping("/characters/{characterId}/comics")
    public List<ComicWrapper> getAllComicsForCharacter(@PathVariable int characterId) {
        List<ComicWrapper> comicWrapperList = new ArrayList<>();
        comicService.getAllComicsForCharacter(characterId).forEach(comicEntity
                -> comicWrapperList.add(new ComicWrapper(comicEntity)));
        return comicWrapperList;
    }

    @GetMapping("/comics/{comicId}/characters")
    public List<CharacterWrapper> getAllCharactersInComic(@PathVariable int comicId) {
        List<CharacterWrapper> characterWrapperList = new ArrayList<>();
        characterService.getAllCharactersFromComic(comicId).forEach(characterEntity
                -> characterWrapperList.add(new CharacterWrapper(characterEntity)));
        return characterWrapperList;
    }

    @PutMapping ("/characters/{characterId}/comics/{comicId}")
    public void addComicForCharacter(@PathVariable int characterId, @PathVariable int comicId) {
        relationService.addComicForCharacter(characterId, comicId);
    }

    @PutMapping ("/comics/{comicId}/characters/{characterId}")
    public void addCharacterToComic(@PathVariable int comicId, @PathVariable int characterId) {
        relationService.addCharacterToComic(comicId, characterId);
    }
}
