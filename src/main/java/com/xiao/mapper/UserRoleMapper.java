package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.UserRole;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    int updateBatch(@Param("list") List<UserRole> list);

    int batchInsert(@Param("list") List<UserRole> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<UserRole> list);

    int insertOnDuplicateUpdate(UserRole record);

    int insertOnDuplicateUpdateSelective(UserRole record);
}