package com.example.forum.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "recruit_article")
@ApiModel("Recruit_article")
public class Recruit_article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    @NotNull(message = "文章id不能为空")
    @ApiModelProperty("文章id")
    private Integer article_id;

    @Column(name = "user_id", nullable = false)
    @NotBlank(message = "发帖用户id不能为空")
    @ApiModelProperty("发帖用户id")
    private Integer user_id;

    @Column(name = "update_date", nullable = false)
    @ApiModelProperty("发布日期")
    private Date update_date;

    @Column(name = "type", nullable = false)
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "用户名长度为6-20")
    @ApiModelProperty("密码")
    private Integer type;

    @Column(name = "studentid", nullable = false)
    @NotBlank(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String studentid;

    @Column(name = "studentname", nullable = false)
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty("名字")
    private String studentname;

    @Column(name = "email", nullable = true)
    @Email(message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;

    @Column(name = "headportrait", nullable = true)
    @ApiModelProperty("头像")
    private String headportrait;



}
