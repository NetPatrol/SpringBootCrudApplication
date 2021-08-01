package com.example.boot3.repository.user;

import com.example.boot3.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final EntityManager em;
    @Autowired
    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(UserEntity user) {
        em.persist(em.contains(user) ? user : em.merge(user));
    }

    @Override
    public List<UserEntity> findAll() {
        return em.createQuery("select u from UserEntity u join fetch u.roles", UserEntity.class)
                .getResultList();
    }

    @Override
    public UserEntity findById(Long id) {
        return em.createQuery("select u from UserEntity u join fetch u.roles where u.id = :id", UserEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public UserEntity findByLogin(String login) {
        return em.createQuery("select u from UserEntity u join fetch u.roles where u.login = :login", UserEntity.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public void delete(UserEntity user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

}
