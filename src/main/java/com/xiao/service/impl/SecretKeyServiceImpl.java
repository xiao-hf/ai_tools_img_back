package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiao.dao.SecretKey;
import com.xiao.exception.BusinessException;
import com.xiao.mapper.SecretKeyMapper;
import com.xiao.service.SecretKeyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SecretKeyServiceImpl implements SecretKeyService {

    @Resource
    private SecretKeyMapper secretKeyMapper;

    @Override
    public boolean verify(String credential) {
        SecretKey secret = secretKeyMapper.selectOne(
            Wrappers.<SecretKey>lambdaQuery().last("limit 1")
        );
        if (secret == null || secret.getValue() == null) {
            throw new BusinessException("密钥未配置");
        }
        return secret.getValue().equals(credential);
    }
}
