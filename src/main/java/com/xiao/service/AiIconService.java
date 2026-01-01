package com.xiao.service;

import com.xiao.dao.AiIcon;
import com.xiao.http.req.AiIconCreateReq;
import com.xiao.http.req.AiIconUpdateReq;

import java.util.List;

public interface AiIconService {

    AiIcon create(AiIconCreateReq req);

    AiIcon create(String name, String img);

    /**
     * 通过名称前缀递减匹配，返回首个匹配的图片 URL；若无匹配则返回 null
     */
    String findImgByCascadePrefix(String name);

    AiIcon update(Long id, AiIconUpdateReq req);

    AiIcon detail(Long id);

    List<AiIcon> list(String name);

    void delete(Long id);
}
