package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.AiIcon;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AiIconMapper extends BaseMapper<AiIcon> {
    int updateBatch(@Param("list") List<AiIcon> list);

    int batchInsert(@Param("list") List<AiIcon> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<AiIcon> list);

    int insertOnDuplicateUpdate(AiIcon record);

    int insertOnDuplicateUpdateSelective(AiIcon record);
}