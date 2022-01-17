package com.eugenevdovin.marvelheroestestproject.dao;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CharacterEntityDAOImpl implements CharacterEntityDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CharacterEntity> getAllCharacters() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM CharacterEntity", CharacterEntity.class);
        return query.getResultList();
    }
    //Not working yet
    @Override
    public CharacterEntity getCharacter(int id) {
        Session session = entityManager.unwrap(Session.class);
        return null;
    }

    @Override
    public void saveCharacter(CharacterEntity characterEntity) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(characterEntity);
    }
    //Not working yet
    @Override
    public void deleteCharacter(int id) {
        Session session = entityManager.unwrap(Session.class);
    }
    //Not working yet
    @Override
    public List<ComicEntity> getAllComicsForCharacter() {
        Session session = entityManager.unwrap(Session.class);
        return null;
    }
}