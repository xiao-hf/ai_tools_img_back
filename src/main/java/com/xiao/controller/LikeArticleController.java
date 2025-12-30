package com.xiao.controller;

import com.xiao.common.AjaxResult;
import com.xiao.dao.LikeArticle;
import com.xiao.http.req.LikeArticleReq;
import com.xiao.service.LikeArticleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/like-article")
@Validated
public class LikeArticleController {

    @Resource
    private LikeArticleService likeArticleService;

    @Operation(summary = "点赞文章（userId + articleId）")
    @PostMapping
    public AjaxResult<LikeArticle> like(@RequestBody @Valid LikeArticleReq req) {
        return AjaxResult.success(likeArticleService.like(req.getUserId(), req.getArticleId()));
    }

    @Operation(summary = "取消点赞（userId + articleId）")
    @DeleteMapping
    public AjaxResult<String> unlike(@RequestBody @Valid LikeArticleReq req) {
        likeArticleService.unlike(req.getUserId(), req.getArticleId());
        return AjaxResult.success("取消成功");
    }

    @Operation(summary = "按用户查询点赞列表")
    @GetMapping("/by-user")
    public AjaxResult<List<LikeArticle>> listByUser(@RequestParam @NotNull(message = "userId不能为空") Long userId) {
        return AjaxResult.success(likeArticleService.listByUser(userId));
    }

    @Operation(summary = "按文章查询点赞列表")
    @GetMapping("/by-article")
    public AjaxResult<List<LikeArticle>> listByArticle(@RequestParam @NotNull(message = "articleId不能为空") Long articleId) {
        return AjaxResult.success(likeArticleService.listByArticle(articleId));
    }
}
