package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.service.ComicService;
import com.eugenevdovin.marvelheroestestproject.service.RelationService;
import com.eugenevdovin.marvelheroestestproject.wrapper.ComicWrapper;
import com.eugenevdovin.marvelheroestestproject.wrapper.WrapExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/public")
public class ComicRESTController {
    @Autowired
    ComicService comicService;
    @Autowired
    RelationService relationService;

    //Допилить проверку на совпадения по именам + Сделать полноценную обработку исключений с выбросом HTTP ошибок
    //Внедрить загрузку/выгрузку изображений
    //!Постраничная загрузка - paging and sorting
    //!Отдельные методы с маппингами sort by name, sort by id - paging and sorting
    //Swagger
    //Docker

    @GetMapping("/comics")
    public List<ComicWrapper> getAllComics() {
        return WrapExecutor.getComicWrapperList(comicService.getAllComics());
    }

    @GetMapping("/comics/{comicId}")
    public ComicWrapper getComic(@PathVariable int comicId) {
        ComicEntity comicEntity = comicService.getComic(comicId);
        if (comicEntity == null) System.out.println("Character not found");
        return WrapExecutor.getComicWrapper(comicEntity);
    }

    @GetMapping("/characters/{characterId}/comics")
    public List<ComicWrapper> getAllComicsForCharacter(@PathVariable int characterId) {
        List<ComicWrapper> comicWrapperList = new ArrayList<>();
        comicService.getAllComicsForCharacter(characterId).forEach(comicEntity
                -> comicWrapperList.add(new ComicWrapper(comicEntity)));
        return comicWrapperList;
    }

    @PostMapping("/comics")
    public ComicEntity addNewComic(@RequestBody ComicEntity comicEntity) {
        comicService.saveComic(comicEntity);
        return comicEntity;
    }

    @PutMapping ("/comics/{comicId}/characters/{characterId}")
    public void addCharacterToComic(@PathVariable int comicId, @PathVariable int characterId) {
        relationService.addCharacterToComic(comicId, characterId);
    }

    @DeleteMapping("/comics/{comicId}")
    public void deleteComicById(@PathVariable int comicId) {
        comicService.deleteComic(comicId);
    }


}
