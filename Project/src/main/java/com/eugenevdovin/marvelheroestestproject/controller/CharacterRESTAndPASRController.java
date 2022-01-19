package com.eugenevdovin.marvelheroestestproject.controller;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.service.CharacterPASService;
import com.eugenevdovin.marvelheroestestproject.wrapper.CharacterWrapper;
import com.eugenevdovin.marvelheroestestproject.wrapper.WrapExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/public/PAS")
public class CharacterRESTAndPASRController {
    @Autowired
    CharacterPASService service;

    @GetMapping("/")
    public ResponseEntity<List<CharacterWrapper>> getAllCharacters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy)
    {
        List<CharacterEntity> list = service.getAllCharacters(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(WrapExecutor.getCharacterWrapperList(list), new HttpHeaders(), HttpStatus.OK);
    }
}
