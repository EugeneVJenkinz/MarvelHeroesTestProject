package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.repository.PictureEntityDAO;
import com.eugenevdovin.marvelheroestestproject.entity.PictureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureEntityDAO pictureEntityDAO;

    @Override
    public PictureEntity getPicture(int id) {
        return pictureEntityDAO.getPicture(id);
    }

    @Override
    @Transactional
    public void savePicture(PictureEntity pictureEntity) {
        pictureEntityDAO.savePicture(pictureEntity);
    }
}
