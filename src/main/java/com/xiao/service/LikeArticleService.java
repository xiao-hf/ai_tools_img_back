package com.xiao.service;

import com.xiao.dao.LikeArticle;

import java.util.List;

public interface LikeArticleService {

    /**
     * 点赞（若已存在则直接返回）
     */
    LikeArticle like(Long userId, Long articleId);

    /**
     * 取消点赞（幂等）
     */
    void unlike(Long userId, Long articleId);

    List<LikeArticle> listByUser(Long userId);

    List<LikeArticle> listByArticle(Long articleId);
}
