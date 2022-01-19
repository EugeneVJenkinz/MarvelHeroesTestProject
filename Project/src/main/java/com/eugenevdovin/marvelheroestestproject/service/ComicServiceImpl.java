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
public class ComicServiceImpl implements ComicService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;

    @Override
    @Transactional
    public List<ComicEntity> getAllComics() {
        return (List<ComicEntity>) comicRepository.findAll();
    }

    @Override
    @Transactional
    public ComicEntity getComic(int id) {
        return comicRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void saveComic(ComicEntity comicEntity) {
        comicRepository.save(comicEntity);
    }

    @Override
    @Transactional
    public void deleteComic(int id) {
        comicRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ComicEntity> getAllComicsForCharacter(int characterId) {
        CharacterEntity character = characterRepository.findById(characterId).get();
        return character.getComics();
    }
}
