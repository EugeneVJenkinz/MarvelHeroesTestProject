package com.eugenevdovin.marvelheroestestproject.dao;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

import java.util.List;

public interface CharacterEntityDAO {
    List<CharacterEntity> getAllCharacters();
    CharacterEntity getCharacter(int id);
    void saveCharacter(CharacterEntity characterEntity);
    void deleteCharacter(int id);
    List<ComicEntity> getAllComicsForCharacter();
}
