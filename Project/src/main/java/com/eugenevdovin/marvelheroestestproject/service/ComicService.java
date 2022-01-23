package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

import java.util.List;

public interface ComicService {
    ComicEntity getComic(int id);
    void saveComic(ComicEntity comicEntity);
    void deleteComic(int id);
    List<ComicEntity> getAllComicsForCharacter(int characterId);
    List<ComicEntity> getAllComicsForCharacter(Integer pageNo, Integer pageSize, String sortBy, Integer characterId);
    List<ComicEntity> getAllComics(Integer pageNo, Integer pageSize, String sortBy);
    List<ComicEntity> getComicListFilteredByNameContains(Integer pageNo, Integer pageSize, String sortBy, String name);
    boolean existsById(Integer id);
}

