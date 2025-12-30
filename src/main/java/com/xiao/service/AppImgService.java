package com.xiao.service;

import com.xiao.dao.AppImg;
import com.xiao.http.req.AppImgSaveReq;

public interface AppImgService {

    /**
     * Create or update app image for given appId.
     */
    AppImg save(AppImgSaveReq req);

    AppImg getByAppId(String appId);

    void deleteByAppId(String appId);
}
