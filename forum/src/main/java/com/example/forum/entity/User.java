package com.example.forum.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@ApiModel("User 用户")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private Integer id;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 20, message = "用户名长度为6-20")
    @ApiModelProperty("用户名")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "用户名长度为6-20")
    @ApiModelProperty("密码")
    private String password;

    @Column(name = "studentid", nullable = false)
    @NotBlank(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String studentid;

    @Column(name = "studentname", nullable = false)
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty("名字")
    private String studentname;

    @Column(name = "birthday", nullable = true)
    @ApiModelProperty("生日")
    private String birthday;

    @Column(name = "email", nullable = true)
    @Email(message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;

    @Column(name = "headportrait", nullable = true)
    @ApiModelProperty("头像")
    private String headportrait;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String studentid, String studentname,
                String birthday, String email, String headportrait) {
        this.username = username;
        this.password = password;
        this.studentid = studentid;
        this.studentname = studentname;
        this.birthday = birthday;
        this.email = email;
        this.headportrait = headportrait;
    }

}
