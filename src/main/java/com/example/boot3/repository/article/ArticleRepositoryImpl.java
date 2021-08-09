package com.example.boot3.repository.article;

import com.example.boot3.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final EntityManager em;
    @Autowired
    public ArticleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(ArticleEntity article) {
        em.persist(em.contains(article) ? article : em.merge(article));
    }

    @Override
    public ArticleEntity findById(Long id) {
        return em.createQuery("select a from ArticleEntity a where a.id = :id", ArticleEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public ArticleEntity findByTitle(String title) {
        return em.createQuery("select a from ArticleEntity a where a.title = :title", ArticleEntity.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public void delete(ArticleEntity article) {
        em.remove(em.contains(article) ? article : em.merge(article));
    }
}
