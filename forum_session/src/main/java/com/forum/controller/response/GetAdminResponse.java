package com.forum.controller.response;

import com.forum.entity.Admin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("GetAdminrResponse 管理员信息返回")
public class GetAdminResponse implements Serializable {

    @ApiModelProperty("管理员id")
    private Integer admin_id;

    @ApiModelProperty("管理员用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("类别")
    private Integer type;

    public GetAdminResponse(Admin admin, Integer type){
        this.admin_id = admin.getAdmin_id();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.type = type;
    }
}
