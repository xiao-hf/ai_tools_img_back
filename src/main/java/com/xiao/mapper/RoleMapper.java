package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.Role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    int updateBatch(@Param("list") List<Role> list);

    int batchInsert(@Param("list") List<Role> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Role> list);

    int insertOnDuplicateUpdate(Role record);

    int insertOnDuplicateUpdateSelective(Role record);
}