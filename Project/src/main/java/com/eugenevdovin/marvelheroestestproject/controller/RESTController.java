package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.wrapper.CharacterWrapper;
import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.service.CharacterService;
import com.eugenevdovin.marvelheroestestproject.service.ComicService;
import com.eugenevdovin.marvelheroestestproject.service.PictureService;
import com.eugenevdovin.marvelheroestestproject.service.RelationService;
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

    //Вопросы: как добавить связь между комиксами и персонажами в REST?
    //Проверить/до(пере)работать метод сервиса по выгрузке информации на основе свзяей
    //Допилить проверку на совпадения по именам
    //Сделать полноценную обработку исключений с выбросом HTTP ошибок
    //Внедрить загрузку/выгрузку изображений

    @GetMapping("/characters/{characterId}")
    public CharacterWrapper getCharacter(@PathVariable int characterId) {
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        CharacterWrapper characterWrapper = new CharacterWrapper(characterEntity);
        if (characterEntity == null) System.out.println("Character not found");
        return characterWrapper;
    }

    @GetMapping("/characters")
    public List<CharacterWrapper> getAllCharacters() {
        List<CharacterWrapper> characterWrapperList = new ArrayList<>();
        characterService.getAllCharacters().forEach(characterEntity -> {
            CharacterWrapper characterWrapper = new CharacterWrapper(characterEntity);
            characterWrapperList.add(characterWrapper);
        });
        return characterWrapperList;
    }

    @PostMapping("/characters")
    public CharacterEntity addNewCharacter(@RequestBody CharacterEntity characterEntity) {
        characterService.saveCharacter(characterEntity);
        return characterEntity;
    }

    @GetMapping("/comics/{comicId}")
    public ComicEntity getComic(@PathVariable int comicId) {
        ComicEntity comic = comicService.getComic(comicId);
        if (comic == null) System.out.println("Character not found");//Сделать полноценную обработку исключений
        return comic;
    }

    @GetMapping("/comics")
    public List<ComicEntity> getAllComics() {
        return comicService.getAllComics();
    }

    @PostMapping("/comics")
    public ComicEntity addNewCharacter(@RequestBody ComicEntity comicEntity) {
        comicService.saveComic(comicEntity);
        return comicEntity;
    }

    @DeleteMapping("/comics/{comicId}")
    public void deleteComicById(@PathVariable int comicId) {
        comicService.deleteComic(comicId);
    }

    @GetMapping("/characters/{characterId}/comics")
    public List<ComicEntity> getAllComicsForCharacter(@PathVariable int characterId) {
        return comicService.getAllComicsForCharacter(characterId);
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
