package com.eugenevdovin.marvelheroestestproject.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private int id;
    @Column(name = "character_name")
    private String name;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "characters_comics",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    private List<ComicEntity> comics;
    @OneToOne(mappedBy = "characterEntity", cascade = CascadeType.ALL)
    private PictureEntity picture;

    public CharacterEntity() {
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

    public List<ComicEntity> getComics() {
        return comics;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "CharacterEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addComicToCharacter(ComicEntity comicEntity) {
        if (comics == null) comics = new ArrayList<>();
        comics.add(comicEntity);
    }

    public boolean hasPicture() {
        if (picture == null) return false;
        else return true;
    }
}
