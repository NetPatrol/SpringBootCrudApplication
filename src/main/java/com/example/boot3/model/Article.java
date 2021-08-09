package com.example.boot3.model;

import com.example.boot3.entity.ArticleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Article {
    private Long id;
    private String title;
    private String article;
    private String date;

    public static Article toModel(ArticleEntity entity) {
        Article model = new Article();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setArticle(entity.getArticle());
        model.setDate(entity.getDate());
        return model;
    }

}
