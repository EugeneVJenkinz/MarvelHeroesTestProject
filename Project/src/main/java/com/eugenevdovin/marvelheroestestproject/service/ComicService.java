package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ComicService {
    ComicEntity getComic(int id);
    void saveComic(ComicEntity comicEntity);
    ResponseEntity<Object> deleteComic(int comicId);
    List<ComicEntity> getAllComicsForCharacter(Integer pageNo, Integer pageSize, String sortBy, Integer characterId);
    List<ComicEntity> getAllComics(Integer pageNo, Integer pageSize, String sortBy);
    List<ComicEntity> getComicListFilteredByNameContains(Integer pageNo, Integer pageSize, String sortBy, String name);
    boolean existsById(Integer id);
}

