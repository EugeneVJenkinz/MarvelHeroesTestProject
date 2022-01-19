package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class CharacterRepository implements PagingAndSortingRepository<CharacterEntity, Integer> {
    @Autowired
    private EntityManager entityManager;

    //Not working yet
    @Override
    public Iterable<CharacterEntity> findAll(Sort sort) {
        return null;
    }
    //Not working yet
    @Override
    public Page<CharacterEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CharacterEntity> S save(S entity) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public <S extends CharacterEntity> Iterable<S> saveAll(Iterable<S> entities) {
        Session session = entityManager.unwrap(Session.class);
        entities.forEach(entity -> session.saveOrUpdate(entity));
        return entities;
    }

    @Override
    public Optional<CharacterEntity> findById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        CharacterEntity entity = session.get(CharacterEntity.class, integer);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean existsById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        CharacterEntity entity = session.get(CharacterEntity.class, integer);
        if (entity == null) return false;
        else return true;
    }

    @Override
    public Iterable<CharacterEntity> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM CharacterEntity", CharacterEntity.class);
        return query.getResultList();
    }

    @Override
    public Iterable<CharacterEntity> findAllById(Iterable<Integer> integers) {
        Session session = entityManager.unwrap(Session.class);
        List<CharacterEntity> entityList = new LinkedList<>();
        integers.forEach(integer -> {
            CharacterEntity entity = session.get(CharacterEntity.class, integer);
            if (entity != null) entityList.add(entity);
        });
        return entityList;
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);
        return (Long) session.createQuery("SELECT COUNT(*) from CharacterEntity").uniqueResult();
    }

    @Override
    public void deleteById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(session.get(CharacterEntity.class, integer));
    }

    @Override
    public void delete(CharacterEntity entity) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        Session session = entityManager.unwrap(Session.class);
        integers.forEach(integer -> session.delete(session.get(CharacterEntity.class, integer)));
    }

    @Override
    public void deleteAll(Iterable<? extends CharacterEntity> entities) {
        Session session = entityManager.unwrap(Session.class);
        entities.forEach(entity -> session.delete(entity));
    }

    @Override
    public void deleteAll() {
        Session session = entityManager.unwrap(Session.class);
        session.createQuery("DELETE FROM CharacterEntity").executeUpdate();
    }
}
