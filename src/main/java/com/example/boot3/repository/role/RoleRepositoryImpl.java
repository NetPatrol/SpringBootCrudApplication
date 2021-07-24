package com.example.boot3.repository.role;

import com.example.boot3.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final EntityManager em;
    @Autowired
    public RoleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role findById(Long id) {
        return em.createQuery("select r from Role r join fetch r.users where r.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
