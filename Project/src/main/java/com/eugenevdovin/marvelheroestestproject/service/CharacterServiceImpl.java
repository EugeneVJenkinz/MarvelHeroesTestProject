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
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterEntityDAO characterEntityDAO;
    @Autowired
    ComicEntityDAO comicEntityDAO;

    @Override
    @Transactional
    public List<CharacterEntity> getAllCharacters() {
        return characterEntityDAO.getAllCharacters();
    }

    @Override
    @Transactional
    public CharacterEntity getCharacter(int id) {
        return characterEntityDAO.getCharacter(id);
    }

    @Override
    @Transactional
    public void saveCharacter(CharacterEntity characterEntity) {
        characterEntityDAO.saveCharacter(characterEntity);
    }

    @Override
    @Transactional
    public List<CharacterEntity> getAllCharactersFromComic(int comicId) {
        ComicEntity comic = comicEntityDAO.getComic(comicId);
        return comic.getCharacters();
    }
}
