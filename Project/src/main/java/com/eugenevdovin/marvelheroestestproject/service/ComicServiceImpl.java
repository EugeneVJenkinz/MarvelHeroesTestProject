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
public class ComicServiceImpl implements ComicService {
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    ComicRepository comicRepository;

    @Override
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
    public List<ComicEntity> getAllComicsForCharacter(int characterId) {
        CharacterEntity character = characterRepository.findById(characterId).get();
        return character.getComics();
    }

    @Override
    public List<ComicEntity> getAllComicsForCharacter(Integer pageNo, Integer pageSize, String sortBy, Integer characterId) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        CharacterEntity characterEntity = characterRepository.findById(characterId).get();
        Page<ComicEntity> pagedResult = comicRepository.findAllByCharacters(characterEntity, paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ComicEntity> getAllComics(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<ComicEntity> pagedResult = comicRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ComicEntity> getComicListFilteredByNameContains(Integer pageNo, Integer pageSize, String sortBy, String name) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<ComicEntity> pagedResult = comicRepository.findAllByNameContains(name, paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public boolean existsById(Integer id) {
        return comicRepository.existsById(id);
    }
}
