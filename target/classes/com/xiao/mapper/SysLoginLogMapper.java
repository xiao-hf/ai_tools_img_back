package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.SysLoginLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
    int updateBatch(@Param("list") List<SysLoginLog> list);

    int batchInsert(@Param("list") List<SysLoginLog> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<SysLoginLog> list);

    int insertOnDuplicateUpdate(SysLoginLog record);

    int insertOnDuplicateUpdateSelective(SysLoginLog record);
}