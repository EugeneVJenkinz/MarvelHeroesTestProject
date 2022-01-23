package com.eugenevdovin.marvelheroestestproject.service;

import org.springframework.http.ResponseEntity;

public interface RelationService {
    ResponseEntity<Object> addRelation(int comicId, int characterId);
}
