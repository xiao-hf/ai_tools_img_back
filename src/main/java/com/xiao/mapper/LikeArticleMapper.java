package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.LikeArticle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeArticleMapper extends BaseMapper<LikeArticle> {
    int updateBatch(@Param("list") List<LikeArticle> list);

    int batchInsert(@Param("list") List<LikeArticle> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<LikeArticle> list);

    int insertOnDuplicateUpdate(LikeArticle record);

    int insertOnDuplicateUpdateSelective(LikeArticle record);
}