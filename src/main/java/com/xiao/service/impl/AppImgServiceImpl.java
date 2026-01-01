package com.xiao.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiao.dao.AppImg;
import com.xiao.exception.BusinessException;
import com.xiao.http.req.AppImgSaveReq;
import com.xiao.http.req.AppImgUpdateReq;
import com.xiao.mapper.AppImgMapper;
import com.xiao.service.AppImgService;
import com.xiao.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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
        boolean isNew = false;
        AppImg exist = appImgMapper.selectOne(
            Wrappers.<AppImg>lambdaQuery().eq(AppImg::getAppId, req.getAppId()).last("limit 1")
        );
        if (exist == null) {
            isNew = true;
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
        if (isNew) {
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

    @Override
    public AppImg detail(Long id) {
        AppImg appImg = appImgMapper.selectById(id);
        if (appImg == null) {
            throw new BusinessException("小程序图片不存在");
        }
        return appImg;
    }

    @Override
    public List<AppImg> list(String appId, String name) {
        return appImgMapper.selectList(
            Wrappers.<AppImg>lambdaQuery()
                .eq(StringUtils.hasText(appId), AppImg::getAppId, appId)
                .like(StringUtils.hasText(name), AppImg::getName, name)
                .orderByDesc(AppImg::getUpdateTime)
                .orderByDesc(AppImg::getId)
        );
    }

    @Override
    public AppImg updateById(Long id, AppImgUpdateReq req) {
        AppImg exist = appImgMapper.selectById(id);
        if (exist == null) {
            throw new BusinessException("小程序图片不存在");
        }
        boolean changed = false;
        if (StringUtils.hasText(req.getAppId())) {
            exist.setAppId(req.getAppId());
            changed = true;
        }
        if (StringUtils.hasText(req.getName())) {
            exist.setName(req.getName());
            changed = true;
        }
        if (StringUtils.hasText(req.getWxImg())) {
            if (StringUtils.hasText(exist.getWxImg()) && !exist.getWxImg().equals(req.getWxImg())) {
                fileService.deleteByUrl(exist.getWxImg());
            }
            exist.setWxImg(req.getWxImg());
            changed = true;
        }
        if (!changed) {
            throw new BusinessException("未提供需要更新的字段");
        }
        exist.setUpdateTime(new Date());
        appImgMapper.updateById(exist);
        return exist;
    }

    @Override
    public AppImg updateByAppId(String appId, AppImgUpdateReq req) {
        AppImg exist = getByAppId(appId);
        if (exist == null) {
            throw new BusinessException("小程序图片不存在");
        }
        boolean changed = false;
        if (StringUtils.hasText(req.getName())) {
            exist.setName(req.getName());
            changed = true;
        }
        if (StringUtils.hasText(req.getWxImg())) {
            if (StringUtils.hasText(exist.getWxImg()) && !exist.getWxImg().equals(req.getWxImg())) {
                fileService.deleteByUrl(exist.getWxImg());
            }
            exist.setWxImg(req.getWxImg());
            changed = true;
        }
        if (!changed) {
            throw new BusinessException("未提供需要更新的字段");
        }
        exist.setUpdateTime(new Date());
        appImgMapper.updateById(exist);
        return exist;
    }

    @Override
    public void delete(Long id) {
        AppImg exist = appImgMapper.selectById(id);
        if (exist == null) {
            throw new BusinessException("小程序图片不存在");
        }
        fileService.deleteByUrl(exist.getWxImg());
        appImgMapper.deleteById(id);
    }
}
