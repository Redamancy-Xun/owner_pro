package com.forum.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("CreateArticleRequest 发布帖子")
public class CreateArticleRequest {

    @ApiModelProperty("招募帖子id")
    private Integer article_id;

    @ApiModelProperty("招募任务类型")
    private List<String> type;

    @ApiModelProperty("需求方向")
    private List<String> direction;

    @ApiModelProperty("技术栈")
    private List<String> tag;

    @ApiModelProperty("详情内容")
    private String content;

    @ApiModelProperty("任务开始时间")
    private String start_time;

    @ApiModelProperty("任务结束时间")
    private String end_time;

    @ApiModelProperty("联系方式")
    private String contact;
}
