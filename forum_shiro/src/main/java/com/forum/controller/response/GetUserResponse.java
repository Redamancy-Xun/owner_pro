package com.forum.controller.response;

import com.forum.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("GetUserResponse 用户信息返回")
public class GetUserResponse implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("学生学号")
    private String studentid;

    @ApiModelProperty("学生姓名")
    private String studentname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String headportrait;

    @ApiModelProperty("类别")
    private Integer type;

    public GetUserResponse(User user, Integer type){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.birthday = user.getBirthday();
        this.studentid = user.getStudentid();
        this.studentname = user.getStudentname();
        this.email = user.getEmail();
        this.headportrait = user.getHeadportrait();
        this.type = type;
    }
}
