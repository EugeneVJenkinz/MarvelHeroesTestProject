package com.eugenevdovin.marvelheroestestproject.dao;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ComicEntityDAOImpl implements ComicEntityDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ComicEntity> getAllComics() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM ComicEntity", ComicEntity.class).getResultList();
    }

    @Override
    public ComicEntity getComic(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(ComicEntity.class, id);
    }

    @Override
    public void saveComic(ComicEntity comicEntity) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(comicEntity);
    }

    @Override
    public void deleteComic(ComicEntity comicEntity) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(comicEntity);

    }
    //Not working yet
    @Override
    public List<CharacterEntity> getAllCharactersInComic() {
        Session session = entityManager.unwrap(Session.class);
        return null;
    }

}
