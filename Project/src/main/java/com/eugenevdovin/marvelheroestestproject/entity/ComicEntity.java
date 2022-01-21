package com.eugenevdovin.marvelheroestestproject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comics")
public class ComicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comic_id")
    private int id;
    @Column(name = "comic_name")
    private String name;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "characters_comics",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<CharacterEntity> characters;
    @OneToOne(mappedBy = "comicEntity", cascade = CascadeType.ALL)
    private PictureEntity picture;

    public ComicEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacterEntity> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterEntity> characters) {
        this.characters = characters;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ComicEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addCharacterToComic(CharacterEntity characterEntity) {
        if (characters == null) characters = new ArrayList<>();
        characters.add(characterEntity);
    }

    public boolean hasPicture() {
        if (picture == null) return false;
        else return true;
    }
}
