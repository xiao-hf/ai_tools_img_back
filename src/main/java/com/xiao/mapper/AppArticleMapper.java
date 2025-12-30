package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.AppArticle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppArticleMapper extends BaseMapper<AppArticle> {
    int updateBatch(@Param("list") List<AppArticle> list);

    int batchInsert(@Param("list") List<AppArticle> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<AppArticle> list);

    int insertOnDuplicateUpdate(AppArticle record);

    int insertOnDuplicateUpdateSelective(AppArticle record);
}