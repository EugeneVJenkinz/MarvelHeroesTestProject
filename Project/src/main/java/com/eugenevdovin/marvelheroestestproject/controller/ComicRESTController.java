package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.service.CharacterService;
import com.eugenevdovin.marvelheroestestproject.service.ComicService;
import com.eugenevdovin.marvelheroestestproject.service.RelationService;
import com.eugenevdovin.marvelheroestestproject.wrapper.WrapExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/public")
public class ComicRESTController {
    @Autowired
    ComicService comicService;
    @Autowired
    RelationService relationService;
    @Autowired
    CharacterService characterService;

    @GetMapping("/comics")
    public ResponseEntity<Object> getAllComics(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<ComicEntity> list = comicService.getAllComics(pageNo, pageSize, sortBy);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(WrapExecutor.getComicWrapperList(list), HttpStatus.OK)
                : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/comics/filterByName")
    public ResponseEntity<Object> getAllFilteredComics(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam String filterValue) {
        List<ComicEntity> list = comicService.getComicListFilteredByNameContains(pageNo, pageSize, sortBy, filterValue);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(WrapExecutor.getComicWrapperList(list), HttpStatus.OK)
                : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/comics/{comicId}")
    public ResponseEntity<Object> getComic(@PathVariable int comicId) {
        if (!comicService.existsById(comicId)) return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        ComicEntity comicEntity = comicService.getComic(comicId);
        return new ResponseEntity<>(WrapExecutor.getComicWrapper(comicEntity), HttpStatus.OK);
    }

    @GetMapping("/characters/{characterId}/comics")
    public ResponseEntity<Object> getAllComicsForCharacter(
            @PathVariable int characterId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        if (!characterService.existsById(characterId)) return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
        List<ComicEntity> list = comicService.getAllComicsForCharacter(pageNo, pageSize, sortBy, characterId);
        if (list.isEmpty())
            return new ResponseEntity<>("This character has no comics added yet", HttpStatus.OK);
        else
            return new ResponseEntity<>(WrapExecutor.getComicWrapperList(list), HttpStatus.OK);
    }

    @PostMapping("/comics")
    public ResponseEntity<Object> addNewComic(@RequestBody ComicEntity comicEntity) {
        comicService.saveComic(comicEntity);
        return new ResponseEntity<>(comicEntity, HttpStatus.OK);
    }

    @PutMapping ("/comics/{comicId}/characters/{characterId}")
    public ResponseEntity<Object> addCharacterInComic(@PathVariable int characterId, @PathVariable int comicId) {
        if (!characterService.existsById(characterId))
            return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
        if (!comicService.existsById(comicId))
            return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        relationService.addCharacterToComic(comicId, characterId);
        if (comicService.getComic(comicId).getCharacters().contains(characterService.getCharacter(characterId)))
            return new ResponseEntity<>("Character added in comic successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/comics/{comicId}")
    public ResponseEntity<Object> deleteComicById(@PathVariable int comicId) {
        if (!comicService.existsById(comicId))
            return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        comicService.deleteComic(comicId);
        if (!comicService.existsById(comicId))
            return new ResponseEntity<>("Comic was successfully deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
