package com.eugenevdovin.marvelheroestestproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int id;
    @Column(name = "picture_array")
    private byte[] imageBytes;
    @OneToOne
    @JoinColumn(name = "character_id")
    CharacterEntity characterEntity;
    @OneToOne
    @JoinColumn(name = "comic_id")
    ComicEntity comicEntity;

    public PictureEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public void setCharacterOrComic(Object entity) {
        if (entity.getClass().getName().equals("CharacterEntity")) this.characterEntity = (CharacterEntity) entity;
        else if (entity.getClass().getName().equals("ComicEntity")) this.comicEntity = (ComicEntity) entity;
    }

    public CharacterEntity getCharacterEntity() {
        return characterEntity;
    }

    public void setCharacterEntity(CharacterEntity characterEntity) {
        this.characterEntity = characterEntity;
    }

    public ComicEntity getComicEntity() {
        return comicEntity;
    }

    public void setComicEntity(ComicEntity comicEntity) {
        this.comicEntity = comicEntity;
    }
}
