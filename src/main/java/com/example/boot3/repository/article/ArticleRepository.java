package com.example.boot3.repository.article;

import com.example.boot3.entity.ArticleEntity;

public interface ArticleRepository {
    void save(ArticleEntity article);
    ArticleEntity findById(Long id);
    ArticleEntity findByTitle(String title);
    void delete(ArticleEntity article);
}
