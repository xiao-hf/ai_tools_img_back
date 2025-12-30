package com.xiao.service;

import com.xiao.common.dto.ArticleDto;
import com.xiao.http.req.ArticleCreateReq;
import com.xiao.http.req.ArticleUpdateReq;

import java.util.List;

public interface ArticleService {

    ArticleDto create(ArticleCreateReq req);

    ArticleDto update(Long id, ArticleUpdateReq req);

    ArticleDto detail(Long id);

    List<ArticleDto> listByAppId(String appId);

    void delete(Long id);
}
