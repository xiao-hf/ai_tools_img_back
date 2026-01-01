package com.xiao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiao.common.dto.ArticleDto;
import com.xiao.dao.AppArticle;
import com.xiao.dao.Article;
import com.xiao.exception.BusinessException;
import com.xiao.http.req.ArticleCreateReq;
import com.xiao.http.req.ArticleUpdateReq;
import com.xiao.http.req.ArticleSimpleCreateReq;
import com.xiao.mapper.AppArticleMapper;
import com.xiao.mapper.ArticleMapper;
import com.xiao.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private AppArticleMapper appArticleMapper;

    @Override
    public ArticleDto create(ArticleCreateReq req) {
        if (!StringUtils.hasText(req.getAppId())) {
            throw new BusinessException("appId不能为空");
        }
        if (!StringUtils.hasText(req.getTitle())) {
            throw new BusinessException("标题不能为空");
        }
        validateImages(req.getImgs(), req.getLocations());
        if (req.getImgs() == null || req.getImgs().isEmpty()) {
            throw new BusinessException("至少上传一张图片");
        }
        Article article = new Article();
        article.setTitleImg(req.getImgs().get(0));
        article.setImgs(JSON.toJSONString(req.getImgs()));
        article.setLocations(JSON.toJSONString(req.getLocations()));
        article.setContent(req.getContent());
        article.setTitle(req.getTitle());
        article.setCreateTime(new Date());
        articleMapper.insert(article);

        AppArticle relation = new AppArticle();
        relation.setAppId(req.getAppId());
        relation.setArticleId(article.getId());
        appArticleMapper.insert(relation);
        return toDto(article, req.getAppId());
    }

    @Override
    public ArticleDto createWithoutApp(ArticleSimpleCreateReq req) {
        if (req.getImgs() == null || req.getLocations() == null) {
            throw new BusinessException("图片和位置列表不能为空");
        }
        if (req.getImgs().size() != req.getLocations().size()) {
            throw new BusinessException("图片数量和位置数量不一致");
        }
        if (!StringUtils.hasText(req.getTitle())) {
            throw new BusinessException("标题不能为空");
        }
        if (!StringUtils.hasText(req.getContent())) {
            throw new BusinessException("内容不能为空");
        }
        Article article = new Article();
        article.setTitleImg(req.getImgs().isEmpty() ? null : req.getImgs().get(0));
        article.setImgs(JSON.toJSONString(req.getImgs()));
        article.setLocations(JSON.toJSONString(req.getLocations()));
        article.setContent(req.getContent());
        article.setTitle(req.getTitle());
        article.setCreateTime(new Date());
        articleMapper.insert(article);
        return toDto(article, null);
    }

    @Override
    public ArticleDto update(Long id, ArticleUpdateReq req) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        if (req.getImgs() != null || req.getLocations() != null) {
            validateImages(req.getImgs(), req.getLocations());
            article.setImgs(JSON.toJSONString(req.getImgs()));
            article.setLocations(JSON.toJSONString(req.getLocations()));
            if (req.getImgs() != null && !req.getImgs().isEmpty()) {
                article.setTitleImg(req.getImgs().get(0));
            }
        }
        if (StringUtils.hasText(req.getContent())) {
            article.setContent(req.getContent());
        }
        if (StringUtils.hasText(req.getTitle())) {
            article.setTitle(req.getTitle());
        }
        articleMapper.updateById(article);

        String appId = updateRelation(id, req.getAppId());
        return toDto(article, appId);
    }

    @Override
    public ArticleDto detail(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        String appId = findAppIdByArticleId(id);
        return toDto(article, appId);
    }

    @Override
    public List<ArticleDto> listByAppId(String appId) {
        List<AppArticle> relations;
        if (StringUtils.hasText(appId)) {
            relations = appArticleMapper.selectList(
                Wrappers.<AppArticle>lambdaQuery().eq(AppArticle::getAppId, appId)
            );
        } else {
            relations = appArticleMapper.selectList(null);
        }
        if (relations.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> articleIds = relations.stream()
            .map(AppArticle::getArticleId)
            .filter(Objects::nonNull)
            .toList();
        if (articleIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Article> articles = articleMapper.selectBatchIds(articleIds);
        Map<Long, String> relationMap = relations.stream()
            .collect(Collectors.toMap(AppArticle::getArticleId, AppArticle::getAppId, (a, b) -> a));
        return articles.stream()
            .map(item -> toDto(item, relationMap.get(item.getId())))
            .toList();
    }

    @Override
    public void delete(Long id) {
        articleMapper.deleteById(id);
        appArticleMapper.delete(
            Wrappers.<AppArticle>lambdaQuery().eq(AppArticle::getArticleId, id)
        );
    }

    private void validateImages(List<String> imgs, List<Integer> locations) {
        if (imgs == null || locations == null) {
            throw new BusinessException("图片和位置列表不能为空");
        }
        if (imgs.size() != locations.size()) {
            throw new BusinessException("图片数量和位置数量不一致");
        }
    }

    private String updateRelation(Long articleId, String appId) {
        AppArticle relation = appArticleMapper.selectOne(
            Wrappers.<AppArticle>lambdaQuery().eq(AppArticle::getArticleId, articleId).last("limit 1")
        );
        if (!StringUtils.hasText(appId)) {
            return relation == null ? null : relation.getAppId();
        }
        if (relation == null) {
            relation = new AppArticle();
            relation.setArticleId(articleId);
            relation.setAppId(appId);
            appArticleMapper.insert(relation);
        } else {
            relation.setAppId(appId);
            appArticleMapper.updateById(relation);
        }
        return appId;
    }

    private String findAppIdByArticleId(Long articleId) {
        AppArticle relation = appArticleMapper.selectOne(
            Wrappers.<AppArticle>lambdaQuery().eq(AppArticle::getArticleId, articleId).last("limit 1")
        );
        return relation == null ? null : relation.getAppId();
    }

    private ArticleDto toDto(Article article, String appId) {
        ArticleDto dto = new ArticleDto();
        BeanUtil.copyProperties(article, dto);
        dto.setAppId(appId);
        // 将存储的 JSON 字符串转换回集合
        dto.setImgs(JSON.parseArray(article.getImgs(), String.class));
        dto.setLocations(JSON.parseArray(article.getLocations(), Integer.class));
        dto.setTitleImg(article.getTitleImg());
        return dto;
    }
}
