package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.repository.CharacterRepository;
import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;

    @Override
    public CharacterEntity getCharacter(int id) {
        return characterRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void saveCharacter(CharacterEntity characterEntity) {
        characterRepository.save(characterEntity);
    }

    @Override
    public List<CharacterEntity> getAllCharactersFromComic(Integer pageNo, Integer pageSize, String sortBy, Integer comicId) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        ComicEntity comic = comicRepository.findById(comicId).get();
        Page<CharacterEntity> pagedResult = characterRepository.findAllByComics(comic, paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<CharacterEntity> getAllCharacters(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<CharacterEntity> pagedResult = characterRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<CharacterEntity> getCharacterListFilteredByNameContains(Integer pageNo, Integer pageSize, String sortBy, String name) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<CharacterEntity> pagedResult = characterRepository.findAllByNameContains(name, paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void deleteCharacter(int characterId) {
        characterRepository.deleteById(characterId);
    }

    @Override
    public boolean existsById(int characterId) {
        return characterRepository.existsById(characterId);
    }
}
