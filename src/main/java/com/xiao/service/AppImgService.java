package com.xiao.service;

import com.xiao.dao.AppImg;
import com.xiao.http.req.AppImgSaveReq;
import com.xiao.http.req.AppImgUpdateReq;

import java.util.List;

public interface AppImgService {

    /**
     * Create or update app image for given appId.
     */
    AppImg save(AppImgSaveReq req);

    AppImg getByAppId(String appId);

    void deleteByAppId(String appId);

    /**
     * Detail by id.
     */
    AppImg detail(Long id);

    /**
     * List with optional filters.
     */
    List<AppImg> list(String appId, String name);

    /**
     * Update by id (partial).
     */
    AppImg updateById(Long id, AppImgUpdateReq req);

    /**
     * Update by appId (partial).
     */
    AppImg updateByAppId(String appId, AppImgUpdateReq req);

    /**
     * Delete by id.
     */
    void delete(Long id);
}
