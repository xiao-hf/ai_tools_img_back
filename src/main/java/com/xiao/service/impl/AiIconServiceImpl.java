package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiao.dao.AiIcon;
import com.xiao.exception.BusinessException;
import com.xiao.http.req.AiIconCreateReq;
import com.xiao.http.req.AiIconUpdateReq;
import com.xiao.mapper.AiIconMapper;
import com.xiao.service.AiIconService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AiIconServiceImpl implements AiIconService {

    @Resource
    private AiIconMapper aiIconMapper;

    @Override
    public AiIcon create(AiIconCreateReq req) {
        AiIcon icon = new AiIcon();
        icon.setName(req.getName());
        icon.setImg(req.getImg());
        aiIconMapper.insert(icon);
        return icon;
    }

    @Override
    public AiIcon create(String name, String img) {
        if (!StringUtils.hasText(name)) {
            throw new BusinessException("名称不能为空");
        }
        if (!StringUtils.hasText(img)) {
            throw new BusinessException("图片地址不能为空");
        }
        AiIconCreateReq req = new AiIconCreateReq();
        req.setName(name);
        req.setImg(img);
        return create(req);
    }

    @Override
    public String findImgByCascadePrefix(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        String trimmed = name.trim();
        for (int len = trimmed.length(); len >= 1; len--) {
            String prefix = trimmed.substring(0, len);
            AiIcon icon = aiIconMapper.selectOne(
                Wrappers.<AiIcon>lambdaQuery()
                    .likeRight(AiIcon::getName, prefix)
                    .orderByAsc(AiIcon::getId)
                    .last("limit 1")
            );
            if (icon != null && StringUtils.hasText(icon.getImg())) {
                return icon.getImg();
            }
        }
        return null;
    }

    @Override
    public AiIcon update(Long id, AiIconUpdateReq req) {
        AiIcon exist = aiIconMapper.selectById(id);
        if (exist == null) {
            throw new BusinessException("AI图标不存在");
        }
        boolean changed = false;
        if (StringUtils.hasText(req.getName())) {
            exist.setName(req.getName());
            changed = true;
        }
        if (StringUtils.hasText(req.getImg())) {
            exist.setImg(req.getImg());
            changed = true;
        }
        if (!changed) {
            throw new BusinessException("未提供需要更新的字段");
        }
        aiIconMapper.updateById(exist);
        return exist;
    }

    @Override
    public AiIcon detail(Long id) {
        AiIcon icon = aiIconMapper.selectById(id);
        if (icon == null) {
            throw new BusinessException("AI图标不存在");
        }
        return icon;
    }

    @Override
    public List<AiIcon> list(String name) {
        return aiIconMapper.selectList(
            Wrappers.<AiIcon>lambdaQuery()
                .like(StringUtils.hasText(name), AiIcon::getName, name)
                .orderByDesc(AiIcon::getId)
        );
    }

    @Override
    public void delete(Long id) {
        int rows = aiIconMapper.deleteById(id);
        if (rows == 0) {
            throw new BusinessException("AI图标不存在");
        }
    }
}
