package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.entity.PictureEntity;

public interface PictureService {
    PictureEntity getPicture(int id);
    void savePicture(PictureEntity pictureEntity);
}
