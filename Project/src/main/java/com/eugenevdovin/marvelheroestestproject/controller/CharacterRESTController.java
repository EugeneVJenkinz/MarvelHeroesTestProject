package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
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
public class CharacterRESTController {
    @Autowired
    CharacterService characterService;
    @Autowired
    RelationService relationService;
    @Autowired
    ComicService comicService;

    @GetMapping("/characters")
    public ResponseEntity<Object> getAllCharacters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<CharacterEntity> list = characterService.getAllCharacters(pageNo, pageSize, sortBy);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(WrapExecutor.getCharacterWrapperList(list), HttpStatus.OK)
                : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/characters/filterByName")
    public ResponseEntity<Object> getAllFilteredCharacters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam String filterValue) {
        List<CharacterEntity> list = characterService.getCharacterListFilteredByNameContains(pageNo, pageSize, sortBy, filterValue);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(WrapExecutor.getCharacterWrapperList(list), HttpStatus.OK)
                : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/characters/{characterId}")
    public ResponseEntity<Object> getCharacter(@PathVariable int characterId) {
        if (!characterService.existsById(characterId)) return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        return new ResponseEntity<>(WrapExecutor.getCharacterWrapper(characterEntity), HttpStatus.OK);
    }

    @GetMapping("/comics/{comicId}/characters")
    public ResponseEntity<Object> getAllCharactersInComic(
            @PathVariable int comicId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        if (!comicService.existsById(comicId)) return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        List<CharacterEntity> list = characterService.getAllCharactersFromComic(pageNo, pageSize, sortBy, comicId);
        if (list.isEmpty())
            return new ResponseEntity<>("This comics has no characters added yet", HttpStatus.OK);
        else
            return new ResponseEntity<>(WrapExecutor.getCharacterWrapperList(list), HttpStatus.OK);
    }

    @PostMapping("/characters")
    public ResponseEntity<Object> addNewCharacter(@RequestBody CharacterEntity characterEntity) {
        characterService.saveCharacter(characterEntity);
        return new ResponseEntity<>(characterEntity, HttpStatus.OK);
    }

    @PutMapping ("/characters/{characterId}/comics/{comicId}")
    public ResponseEntity<Object> addComicForCharacter(@PathVariable int characterId, @PathVariable int comicId) {
        if (!characterService.existsById(characterId))
            return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
        if (!comicService.existsById(comicId))
            return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        relationService.addComicForCharacter(characterId, comicId);
        if (characterService.getCharacter(characterId).getComics().contains(comicService.getComic(comicId)))
            return new ResponseEntity<>("Comic added for character successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/characters/{characterId}")
    public ResponseEntity<Object> deleteCharacterById(@PathVariable int characterId) {
        if (!characterService.existsById(characterId))
            return new ResponseEntity<>("Character not found", HttpStatus.NOT_FOUND);
        characterService.deleteCharacter(characterId);
        if (!characterService.existsById(characterId))
            return new ResponseEntity<>("Character was successfully deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
