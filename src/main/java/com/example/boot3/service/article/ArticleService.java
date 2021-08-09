package com.example.boot3.service.article;

import com.example.boot3.entity.ArticleEntity;
import com.example.boot3.entity.UserEntity;

public interface ArticleService {
    void save(ArticleEntity article, UserEntity user);
    void save(ArticleEntity article);
    ArticleEntity findById(Long id);
    ArticleEntity findByTitle(String title);
}
