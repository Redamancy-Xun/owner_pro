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
@ApiModel("ShowArticleRequest 展示帖子")
public class ShowArticleRequest {

    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("页面大小")
    private Integer pageSize;

    @ApiModelProperty("招募任务类型")
    private List<String> type;

    @ApiModelProperty("需求方向")
    private List<String> direction;

    @ApiModelProperty("技术栈")
    private List<String> tag;

}
