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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> deleteComic(int comicId) {
        if (!this.existsById(comicId))
            return new ResponseEntity<>("Comic not found", HttpStatus.NOT_FOUND);
        comicRepository.deleteById(comicId);
        if (!this.existsById(comicId))
            return new ResponseEntity<>("Comic was successfully deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
