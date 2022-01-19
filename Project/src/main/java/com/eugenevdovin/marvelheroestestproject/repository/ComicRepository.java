package com.eugenevdovin.marvelheroestestproject.repository;

import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
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
public class ComicRepository implements PagingAndSortingRepository<ComicEntity, Integer> {
    @Autowired
    EntityManager entityManager;

    @Override
    public Iterable<ComicEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ComicEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ComicEntity> S save(S entity) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public <S extends ComicEntity> Iterable<S> saveAll(Iterable<S> entities) {
        Session session = entityManager.unwrap(Session.class);
        entities.forEach(entity -> session.saveOrUpdate(entity));
        return entities;
    }

    @Override
    public Optional<ComicEntity> findById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        ComicEntity entity = session.get(ComicEntity.class, integer);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean existsById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        ComicEntity entity = session.get(ComicEntity.class, integer);
        if (entity == null) return false;
        else return true;
    }

    @Override
    public Iterable<ComicEntity> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM ComicEntity", ComicEntity.class);
        return query.getResultList();
    }

    @Override
    public Iterable<ComicEntity> findAllById(Iterable<Integer> integers) {
        Session session = entityManager.unwrap(Session.class);
        List<ComicEntity> entityList = new LinkedList<>();
        integers.forEach(integer -> {
            ComicEntity entity = session.get(ComicEntity.class, integer);
            if (entity != null) entityList.add(entity);
        });
        return entityList;
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);
        return (Long) session.createQuery("SELECT COUNT(*) from ComicEntity").uniqueResult();
    }

    @Override
    public void deleteById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(session.get(ComicEntity.class, integer));
    }

    @Override
    public void delete(ComicEntity entity) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        Session session = entityManager.unwrap(Session.class);
        integers.forEach(integer -> session.delete(session.get(ComicEntity.class, integer)));
    }

    @Override
    public void deleteAll(Iterable<? extends ComicEntity> entities) {
        Session session = entityManager.unwrap(Session.class);
        entities.forEach(entity -> session.delete(entity));
    }

    @Override
    public void deleteAll() {
        Session session = entityManager.unwrap(Session.class);
        session.createQuery("DELETE FROM ComicEntity").executeUpdate();
    }
}
