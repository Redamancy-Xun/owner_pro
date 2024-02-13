package com.forum.controller.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.forum.controller.request.UpdateArticleMessageRequest;
import com.forum.entity.RecruitArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("ShowArticleResponse 帖子信息返回")
public class ShowArticleResponse {

    @ApiModelProperty("招募帖子id")
    private Integer article_id;

    @ApiModelProperty("发帖者id")
    private Integer id;

    @ApiModelProperty("发布日期")
    private Date update_date;

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

    @ApiModelProperty("完成状态")
    private Integer finish;

    @ApiModelProperty("置顶状态")
    private Integer top;

    public ShowArticleResponse(RecruitArticle article) {
        this.article_id = article.getArticle_id();
        if (article.getAdmin_id() != 0)
            this.id = article.getAdmin_id();
        else
            this.id = article.getUser_id();
        this.update_date = article.getUpdate_date();

        this.type = JSON.parseObject(article.getType(), new TypeReference<List<String>>(){});
        this.direction = JSON.parseObject(article.getDirection(), new TypeReference<List<String>>(){});
        this.tag = JSON.parseObject(article.getTag(), new TypeReference<List<String>>(){});

        this.content = article.getContent();
        this.start_time = article.getStart_time();
        this.end_time = article.getEnd_time();
        this.contact = article.getContact();
        this.finish = article.getFinish();
        this.top = article.getTop();
    }

    public ShowArticleResponse(UpdateArticleMessageRequest article, Integer id, Integer top) {
        this.article_id = article.getArticle_id();
        this.id = id;
        this.update_date = article.getUpdate_date();

        this.type = JSON.parseObject(article.getType(), new TypeReference<List<String>>(){});
        this.direction = JSON.parseObject(article.getDirection(), new TypeReference<List<String>>(){});
        this.tag = JSON.parseObject(article.getTag(), new TypeReference<List<String>>(){});

        this.content = article.getContent();
        this.start_time = article.getStart_time();
        this.end_time = article.getEnd_time();
        this.contact = article.getContact();
        this.finish = article.getFinish();
        this.top = top;
    }
}
