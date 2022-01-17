package com.eugenevdovin.marvelheroestestproject.dao;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import com.eugenevdovin.marvelheroestestproject.entity.PictureEntity;

public interface PictureEntityDAO {
    PictureEntity getPicture(int id);
    void savePicture(PictureEntity pictureEntity);
    void deletePicture(int id);
}
