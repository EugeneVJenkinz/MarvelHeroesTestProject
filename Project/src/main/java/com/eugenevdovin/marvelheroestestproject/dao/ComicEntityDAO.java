package com.eugenevdovin.marvelheroestestproject.dao;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

import java.util.List;

public interface ComicEntityDAO {
    List<ComicEntity> getAllComics();
    ComicEntity getComic(int id);
    void saveComic(ComicEntity comicEntity);
    void deleteComic(ComicEntity comicEntity);
    List<CharacterEntity> getAllCharactersInComic();
}
