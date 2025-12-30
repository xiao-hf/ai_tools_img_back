package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.RolePermission;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    int updateBatch(@Param("list") List<RolePermission> list);

    int batchInsert(@Param("list") List<RolePermission> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<RolePermission> list);

    int insertOnDuplicateUpdate(RolePermission record);

    int insertOnDuplicateUpdateSelective(RolePermission record);
}