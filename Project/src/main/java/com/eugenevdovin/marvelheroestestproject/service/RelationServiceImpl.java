package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    ComicService comicService;
    @Autowired
    CharacterService characterService;

    @Override
    @Transactional
    public CharacterEntity addComicForCharacter(int characterId, int comicId) {
        ComicEntity comicEntity = comicService.getComic(comicId);
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        characterEntity.addComicToCharacter(comicEntity);
        return characterEntity;
    }

    @Override
    @Transactional
    public ComicEntity addCharacterForComic(int comicId, int characterId) {
        ComicEntity comicEntity = comicService.getComic(comicId);
        CharacterEntity characterEntity = characterService.getCharacter(characterId);
        comicEntity.addCharacterToComic(characterEntity);
        return comicEntity;
    }
}
