package com.eugenevdovin.marvelheroestestproject.wrapper;


import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;

import java.util.ArrayList;
import java.util.List;

public class ComicWrapper {
    private ComicEntity comicEntity;
    private String name;
    private List<String> characters;

    public ComicWrapper(ComicEntity comicEntity) {
        this.comicEntity = comicEntity;
        this.characters = new ArrayList<>();
        this.name = comicEntity.getName();
        comicEntity.getCharacters().forEach(characterEntity -> characters.add(characterEntity.getName()));
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
