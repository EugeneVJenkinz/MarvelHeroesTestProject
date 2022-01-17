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
    @OneToMany(mappedBy = "characterEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PictureEntity> pictures;

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

    public void setComics(List<ComicEntity> comics) {
        this.comics = comics;
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

    public void addPictureToCharacter(PictureEntity pictureEntity) {
        if (pictures == null) pictures = new ArrayList<>();
        pictures.add(pictureEntity);
    }
}
