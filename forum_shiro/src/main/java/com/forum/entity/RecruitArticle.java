package com.forum.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "recruit_article")
@ApiModel("Recruit_article")
public class RecruitArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    @NotNull(message = "招募帖子id不能为空")
    @ApiModelProperty("招募帖子id")
    private Integer article_id;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "发帖用户id不能为空")
    @ApiModelProperty("发帖用户id")
    private Integer user_id;

    @Column(name = "update_date", nullable = false)
    @NotNull(message = "发布日期不能为空")
    @ApiModelProperty("发布日期")
    private Date update_date;

    @Column(name = "type", nullable = false)
    @NotNull(message = "招募任务类型不能为空")
    @ApiModelProperty("招募任务类型")
    private Integer type;

    @Column(name = "direction", nullable = false)
    @NotNull(message = "需求方向不能为空")
    @ApiModelProperty("需求方向")
    private Integer direction;

    @Column(name = "tag", nullable = false)
    @NotNull(message = "技术栈不能为空")
    @ApiModelProperty("技术栈")
    private String tag;

    @Column(name = "content", nullable = false)
    @NotNull(message = "详情内容不能为空")
    @ApiModelProperty("详情内容")
    private String content;

    @Column(name = "start_time", nullable = false)
    @NotNull(message = "任务开始时间不能为空")
    @ApiModelProperty("任务开始时间")
    private String start_time;

    @Column(name = "end_time", nullable = false)
    @NotNull(message = "任务结束时间不能为空")
    @ApiModelProperty("任务结束时间")
    private String end_time;

    @Column(name = "contact", nullable = false)
    @NotNull(message = "联系方式不能为空")
    @ApiModelProperty("联系方式")
    private String contact;

    @Column(name = "finish", nullable = false)
    @NotNull(message = "完成状态不能为空")
    @ApiModelProperty("完成状态")
    private Integer finish;

    @Column(name = "top", nullable = false)
    @NotNull(message = "置顶状态不能为空")
    @ApiModelProperty("置顶状态")
    private Integer top;

    public RecruitArticle(Integer user_id, Date update_date, Integer type, Integer direction,
                          String tag, String content, String start_time, String end_time,
                          String contact, Integer finish, Integer top) {
        this.user_id = user_id;
        this.update_date = update_date;
        this.type = type;
        this.direction = direction;
        this.tag = tag;
        this.content = content;
        this.start_time = start_time;
        this.end_time = end_time;
        this.contact = contact;
        this.finish = finish;
        this.top = top;
    }
}
