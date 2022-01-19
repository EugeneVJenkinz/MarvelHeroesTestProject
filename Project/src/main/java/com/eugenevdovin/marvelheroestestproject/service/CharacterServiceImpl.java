package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.repository.CharacterRepository;
import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;

    @Override
    @Transactional
    public List<CharacterEntity> getAllCharacters() {
        return (List<CharacterEntity>) characterRepository.findAll();
    }

    @Override
    @Transactional
    public CharacterEntity getCharacter(int id) {
        return characterRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void saveCharacter(CharacterEntity characterEntity) {
        characterRepository.save(characterEntity);
    }

    @Override
    @Transactional
    public List<CharacterEntity> getAllCharactersFromComic(int comicId) {
        ComicEntity comic = comicRepository.findById(comicId).get();
        return comic.getCharacters();
    }
}
