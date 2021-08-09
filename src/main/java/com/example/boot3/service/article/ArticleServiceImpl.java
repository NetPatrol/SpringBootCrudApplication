package com.example.boot3.service.article;

import com.example.boot3.entity.ArticleEntity;
import com.example.boot3.entity.UserEntity;
import com.example.boot3.repository.article.ArticleRepository;
import com.example.boot3.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {


    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(ArticleEntity article) {
        articleRepository.save(article);
    }

    @Override
    public void save(ArticleEntity article, UserEntity user) {
        UserEntity userEntity = userRepository.findByLogin(user.getLogin());
        ArticleEntity a = new ArticleEntity();
        a.setTitle(article.getTitle());
        a.setArticle(article.getArticle());
        a.setDate(article.getDate());
        a.setUser(userEntity);
        save(a);
    }

    @Override
    public ArticleEntity findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public ArticleEntity findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
