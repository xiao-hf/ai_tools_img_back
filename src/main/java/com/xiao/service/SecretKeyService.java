package com.xiao.service;

public interface SecretKeyService {

    /**
     * 校验凭证是否正确。
     * @param credential 待校验的凭证
     * @return true / false
     */
    boolean verify(String credential);
}
