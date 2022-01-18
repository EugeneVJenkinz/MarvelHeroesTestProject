package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.dao.CharacterEntityDAO;
import com.eugenevdovin.marvelheroestestproject.dao.ComicEntityDAO;
import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicEntityDAO comicEntityDAO;
    @Autowired
    CharacterEntityDAO characterEntityDAO;

    @Override
    @Transactional
    public List<ComicEntity> getAllComics() {
        return comicEntityDAO.getAllComics();
    }

    @Override
    @Transactional
    public ComicEntity getComic(int id) {
        return comicEntityDAO.getComic(id);
    }

    @Override
    @Transactional
    public void saveComic(ComicEntity comicEntity) {
        comicEntityDAO.saveComic(comicEntity);
    }

    @Override
    @Transactional
    public void deleteComic(int id) {
        comicEntityDAO.deleteComic(comicEntityDAO.getComic(id));
    }

    @Override
    @Transactional
    public List<ComicEntity> getAllComicsForCharacter(int characterId) {
        CharacterEntity character = characterEntityDAO.getCharacter(characterId);
        return character.getComics();
    }
}
