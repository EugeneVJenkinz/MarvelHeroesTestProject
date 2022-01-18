package com.eugenevdovin.marvelheroestestproject.wrapper;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

public class CharacterWrapper {
    private CharacterEntity characterEntity;
    private String name;
    private List<String> comics;

    public CharacterWrapper(CharacterEntity characterEntity) {
        this.characterEntity = characterEntity;
        this.comics = new ArrayList<>();
        this.name = characterEntity.getName();
        characterEntity.getComics().forEach(comicEntity -> comics.add(comicEntity.getName()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getComics() {
        return comics;
    }

    public void setComics(List<String> comics) {
        this.comics = comics;
    }
}
