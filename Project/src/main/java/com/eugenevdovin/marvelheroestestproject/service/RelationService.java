package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;

public interface RelationService {
    void addComicForCharacter(int characterId, int comicId);
    void addCharacterToComic(int comicId, int characterId);
}
