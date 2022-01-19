package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.PictureEntity;

public interface PictureEntityDAO {
    PictureEntity getPicture(int id);
    void savePicture(PictureEntity pictureEntity);
    void deletePicture(int id);
}
