package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.service.CharacterService;
import com.eugenevdovin.marvelheroestestproject.service.RelationService;
import com.eugenevdovin.marvelheroestestproject.wrapper.CharacterWrapper;
import com.eugenevdovin.marvelheroestestproject.wrapper.WrapExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/public")
public class CharacterRESTController {
    @Autowired
    CharacterService characterService;
    @Autowired
    RelationService relationService;

    @GetMapping("/characters")
    public ResponseEntity<?> getAllCharacters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<CharacterEntity> list = characterService.getAllCharacters(pageNo, pageSize, sortBy);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(WrapExecutor.getCharacterWrapperList(list), HttpStatus.OK)
                : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/characters/filter")
    public ResponseEntity<?> getAllFilteredCharacters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam String filterValue)
    {
        List<CharacterEntity> list = characterService.getListFilteredByNameContains(pageNo, pageSize, sortBy, filterValue);
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(WrapExecutor.getCharacterWrapperList(list), HttpStatus.OK)
                : ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/characters/{characterId}")
    public CharacterWrapper getCharacter(@PathVariable int characterId) {
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        return WrapExecutor.getCharacterWrapper(characterEntity);
    }

    @GetMapping("/comics/{comicId}/characters")
    public List<CharacterWrapper> getAllCharactersInComic(@PathVariable int comicId) {
        List<CharacterWrapper> characterWrapperList = new ArrayList<>();
        characterService.getAllCharactersFromComic(comicId).forEach(characterEntity
                -> characterWrapperList.add(new CharacterWrapper(characterEntity)));
        return characterWrapperList;
    }

    @PostMapping("/characters")
    public CharacterEntity addNewCharacter(@RequestBody CharacterEntity characterEntity) {
        characterService.saveCharacter(characterEntity);
        return characterEntity;
    }

    @PutMapping ("/characters/{characterId}/comics/{comicId}")
    public void addComicForCharacter(@PathVariable int characterId, @PathVariable int comicId) {
        relationService.addComicForCharacter(characterId, comicId);
    }
}
