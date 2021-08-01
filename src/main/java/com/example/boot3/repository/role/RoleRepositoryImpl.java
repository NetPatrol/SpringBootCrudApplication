package com.example.boot3.repository.role;

import com.example.boot3.entity.RoleEntity;
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
    public RoleEntity findById(Long id) {
        return em.createQuery("select r from RoleEntity r where r.id = :id", RoleEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
