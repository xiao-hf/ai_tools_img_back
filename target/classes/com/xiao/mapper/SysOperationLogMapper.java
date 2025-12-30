package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.SysOperationLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysOperationLogMapper extends BaseMapper<SysOperationLog> {
    int updateBatch(@Param("list") List<SysOperationLog> list);

    int batchInsert(@Param("list") List<SysOperationLog> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<SysOperationLog> list);

    int insertOnDuplicateUpdate(SysOperationLog record);

    int insertOnDuplicateUpdateSelective(SysOperationLog record);
}