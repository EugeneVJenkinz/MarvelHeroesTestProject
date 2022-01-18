package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

import java.util.List;

public interface CharacterService {
    List<CharacterEntity> getAllCharacters();
    CharacterEntity getCharacter(int id);
    void saveCharacter(CharacterEntity characterEntity);
    List<CharacterEntity> getAllCharactersFromComic(int comicId);
}
