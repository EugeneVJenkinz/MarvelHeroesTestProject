package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Integer> {
    Page<CharacterEntity> findAllByNameContains(String name, Pageable pageable);
    Page<CharacterEntity> findAllByComics(ComicEntity comicEntity, Pageable pageable);
}
