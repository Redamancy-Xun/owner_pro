package com.forum.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("UpdateUserRequest 修改用户信息")
public class UpdateUserRequest {


    @ApiModelProperty("用户名")
    private String username;


    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("生日")
    private Date birthday;

}
