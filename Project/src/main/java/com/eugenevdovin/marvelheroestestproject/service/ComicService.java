package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

import java.util.List;

public interface ComicService {
    List<ComicEntity> getAllComics();
    ComicEntity getComic(int id);
    void saveComic(ComicEntity comicEntity);
    void deleteComic(int id);
    List<ComicEntity> getAllComicsForCharacter(int characterId);
}
