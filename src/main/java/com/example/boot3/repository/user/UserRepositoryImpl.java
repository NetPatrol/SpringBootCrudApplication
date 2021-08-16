package com.example.boot3.repository.user;

import com.example.boot3.model.User;
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
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u join fetch u.roles", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.createQuery("select u from User u join fetch u.roles where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User findByLogin(String login) {
        return em.createQuery("select u from User u join fetch u.roles where u.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

}
