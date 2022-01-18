package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

public interface RelationService {
    CharacterEntity addComicForCharacter(int characterId, int comicId);
    ComicEntity addCharacterForComic(int comicId, int characterId);
}
