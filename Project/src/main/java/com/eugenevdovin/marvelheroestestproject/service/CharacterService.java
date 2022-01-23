package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;

import java.util.List;

public interface CharacterService {
    CharacterEntity getCharacter(int id);
    void saveCharacter(CharacterEntity characterEntity);
    List<CharacterEntity> getAllCharactersFromComic(Integer pageNo, Integer pageSize, String sortBy, Integer comicId);
    List<CharacterEntity> getAllCharacters(Integer pageNo, Integer pageSize, String sortBy);
    List<CharacterEntity> getCharacterListFilteredByNameContains(Integer pageNo, Integer pageSize, String sortBy, String name);
    void deleteCharacter(int characterId);
    boolean existsById(int characterId);
}
