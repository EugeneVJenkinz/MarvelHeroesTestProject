package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComicRepository extends PagingAndSortingRepository<ComicEntity, Integer> {

}
