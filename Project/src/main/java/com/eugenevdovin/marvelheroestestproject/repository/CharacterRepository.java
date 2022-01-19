package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Integer> {

}
