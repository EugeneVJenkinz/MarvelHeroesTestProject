package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComicRepository extends PagingAndSortingRepository<ComicEntity, Integer> {
    Page<ComicEntity> findAllByNameContains(String name, Pageable pageable);
    Page<ComicEntity> findAllByCharacters(CharacterEntity characterEntity, Pageable pageable);
}
