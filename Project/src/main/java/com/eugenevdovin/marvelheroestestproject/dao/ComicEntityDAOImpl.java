package com.eugenevdovin.marvelheroestestproject.dao;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComicEntityDAOImpl implements ComicEntityDAO {
    //Not working yet
    @Override
    public List<ComicEntity> getAllComics() {
        return null;
    }
    //Not working yet
    @Override
    public ComicEntity getComic(int id) {
        return null;
    }
    //Not working yet
    @Override
    public void saveComic(ComicEntity comicEntity) {

    }
    //Not working yet
    @Override
    public void deleteComic(int id) {

    }
    //Not working yet
    @Override
    public List<CharacterEntity> getAllCharactersInComic() {
        return null;
    }
}
