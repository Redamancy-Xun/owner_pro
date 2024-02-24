package com.forum.controller.response;

import com.forum.entity.RecruitArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("DownloadArticleResponse 帖子信息返回")
public class DownloadArticleResponse {

    @ApiModelProperty("招募帖子id")
    private Integer article_id;

    @ApiModelProperty("发帖者id")
    private Integer id;

    @ApiModelProperty("发布日期")
    private Date update_date;

    @ApiModelProperty("招募任务类型")
    private String type;

    @ApiModelProperty("需求方向")
    private String direction;

    @ApiModelProperty("技术栈")
    private String tag;

    @ApiModelProperty("详情内容")
    private String content;

    @ApiModelProperty("任务开始时间")
    private String start_time;

    @ApiModelProperty("任务结束时间")
    private String end_time;

    @ApiModelProperty("联系方式")
    private String contact;

    @ApiModelProperty("完成状态")
    private String finish;

    @ApiModelProperty("置顶状态")
    private String top;

    public DownloadArticleResponse(RecruitArticle article) {
        this.article_id = article.getArticle_id();
        if (article.getAdmin_id() != 0)
            this.id = article.getAdmin_id();
        else
            this.id = article.getUser_id();

        this.update_date = article.getUpdate_date();
        this.type =article.getType();
        this.direction = article.getDirection();
        this.tag = article.getTag();
        this.content = article.getContent();
        this.start_time = article.getStart_time();
        this.end_time = article.getEnd_time();
        this.contact = article.getContact();
        this.finish = article.getFinish() == 0 ? "未完成" : "已完成";
        this.top = article.getTop() == 0 ? "未置顶" : "已置顶";
    }

    public Object[]  toList(){
            return new Object[]{this.article_id, this.id,this.update_date,this.type,this.type,this.direction,this.tag,this.content,this.start_time,this.end_time,this.contact,this.finish,this.top};

        }
}