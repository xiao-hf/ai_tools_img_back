package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiao.dao.LikeArticle;
import com.xiao.mapper.LikeArticleMapper;
import com.xiao.service.LikeArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LikeArticleServiceImpl implements LikeArticleService {

    @Resource
    private LikeArticleMapper likeArticleMapper;

    @Override
    public LikeArticle like(Long userId, Long articleId) {
        LikeArticle exist = likeArticleMapper.selectOne(
            Wrappers.<LikeArticle>lambdaQuery()
                .eq(LikeArticle::getUserId, userId)
                .eq(LikeArticle::getArticleId, articleId)
                .last("limit 1")
        );
        if (exist != null) {
            return exist;
        }
        LikeArticle record = new LikeArticle();
        record.setUserId(userId);
        record.setArticleId(articleId);
        record.setCreateTime(new Date());
        likeArticleMapper.insert(record);
        return record;
    }

    @Override
    public void unlike(Long userId, Long articleId) {
        likeArticleMapper.delete(
            Wrappers.<LikeArticle>lambdaQuery()
                .eq(LikeArticle::getUserId, userId)
                .eq(LikeArticle::getArticleId, articleId)
        );
    }

    @Override
    public List<LikeArticle> listByUser(Long userId) {
        return likeArticleMapper.selectList(
            Wrappers.<LikeArticle>lambdaQuery().eq(LikeArticle::getUserId, userId)
        );
    }

    @Override
    public List<LikeArticle> listByArticle(Long articleId) {
        return likeArticleMapper.selectList(
            Wrappers.<LikeArticle>lambdaQuery().eq(LikeArticle::getArticleId, articleId)
        );
    }
}
