package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.dao.Article;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    int updateBatch(@Param("list") List<Article> list);

    int batchInsert(@Param("list") List<Article> list);

    int batchInsertSelectiveUseDefaultForNull(@Param("list") List<Article> list);

    int insertOnDuplicateUpdate(Article record);

    int insertOnDuplicateUpdateSelective(Article record);
}