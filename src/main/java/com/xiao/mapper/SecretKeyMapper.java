package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.SecretKey;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SecretKeyMapper extends BaseMapper<SecretKey> {
    int updateBatch(@Param("list") List<SecretKey> list);

    int batchInsert(@Param("list") List<SecretKey> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<SecretKey> list);

    int insertOnDuplicateUpdate(SecretKey record);

    int insertOnDuplicateUpdateSelective(SecretKey record);
}