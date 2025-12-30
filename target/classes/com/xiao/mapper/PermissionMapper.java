package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.Permission;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    int updateBatch(@Param("list") List<Permission> list);

    int batchInsert(@Param("list") List<Permission> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Permission> list);

    int insertOnDuplicateUpdate(Permission record);

    int insertOnDuplicateUpdateSelective(Permission record);
}