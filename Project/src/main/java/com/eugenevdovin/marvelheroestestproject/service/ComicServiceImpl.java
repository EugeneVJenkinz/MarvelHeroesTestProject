package com.eugenevdovin.marvelheroestestproject.service;

import com.eugenevdovin.marvelheroestestproject.dao.ComicEntityDAO;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    ComicEntityDAO comicEntityDAO;

    @Override
    public List<ComicEntity> getAllComics() {
        return comicEntityDAO.getAllComics();
    }

    @Override
    public ComicEntity getComic(int id) {
        return comicEntityDAO.getComic(id);
    }
}
