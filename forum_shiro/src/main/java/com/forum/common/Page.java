package com.forum.common;

import com.forum.controller.response.ShowArticleResponse;
import com.forum.entity.RecruitArticle;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page<T> {
    //当前页
    private Integer pageNum = 1;
    //每页显示的总条数
    private Integer pageSize = 10;
    //总条数
    private Long total;
    //总页数
    private int pages;
    //分页结果
    private List<T> items;

    public Page(PageInfo<T> pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();
        this.items = pageInfo.getList();
    }

    public Page(PageParam pageParam, Long total, int pages, List<T> list) {
        this.pageNum = pageParam.getPageNum();
        this.pageSize = pageParam.getPageSize();
        this.total = total;
        this.pages = pages;
        this.items = list;
    }
}
