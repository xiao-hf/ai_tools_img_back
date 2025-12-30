package com.xiao.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiao.dao.AppImg;
import com.xiao.http.req.AppImgSaveReq;
import com.xiao.mapper.AppImgMapper;
import com.xiao.service.AppImgService;
import com.xiao.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class AppImgServiceImpl implements AppImgService {

    @Resource
    private AppImgMapper appImgMapper;

    @Resource
    private FileService fileService;

    @Override
    public AppImg save(AppImgSaveReq req) {
        Date now = new Date();
        AppImg exist = appImgMapper.selectOne(
            Wrappers.<AppImg>lambdaQuery().eq(AppImg::getAppId, req.getAppId()).last("limit 1")
        );
        if (exist == null) {
            exist = new AppImg();
            exist.setId(IdUtil.getSnowflakeNextId());
            exist.setAppId(req.getAppId());
            exist.setCreateTime(now);
        } else if (StringUtils.hasText(exist.getWxImg()) && !exist.getWxImg().equals(req.getWxImg())) {
            fileService.deleteByUrl(exist.getWxImg());
        }
        exist.setName(req.getName());
        exist.setWxImg(req.getWxImg());
        exist.setUpdateTime(now);
        if (exist.getId() == null || appImgMapper.selectById(exist.getId()) == null) {
            appImgMapper.insert(exist);
        } else {
            appImgMapper.updateById(exist);
        }
        return exist;
    }

    @Override
    public AppImg getByAppId(String appId) {
        return appImgMapper.selectOne(
            Wrappers.<AppImg>lambdaQuery().eq(AppImg::getAppId, appId).last("limit 1")
        );
    }

    @Override
    public void deleteByAppId(String appId) {
        AppImg exist = getByAppId(appId);
        if (exist != null) {
            fileService.deleteByUrl(exist.getWxImg());
            appImgMapper.deleteById(exist.getId());
        }
    }
}
