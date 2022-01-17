package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.dao.CharacterEntityDAO;
import com.eugenevdovin.marvelheroestestproject.dao.CharacterEntityDAOImpl;
import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterEntityDAO characterEntityDAO;

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
}
