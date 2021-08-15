package com.example.boot3.repository.role;

import com.example.boot3.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final EntityManager em;
    @Autowired
    public RoleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Role role) {
        em.persist(role);
    }

    @Override
    public List<Role> findAll() {
        return em.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role findById(Long id) {
        return em.createQuery("select r from Role r where r.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
