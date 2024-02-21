package com.forum.dto;

import com.forum.common.EnumExceptionType;
import com.forum.entity.Admin;
import com.forum.entity.User;
import com.forum.exception.MyException;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("SessionData 会话实体")
public class SessionData  implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("头像")
    private String headportrait;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String studentname;

    @ApiModelProperty("学号")
    private String studentid;

    @ApiModelProperty("会话id")
    private String sessionId;

    @ApiModelProperty("角色 0用户 1管理员")
    private Integer role;

    public SessionData(User user){
        if (user == null)throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        id=user.getId();
        headportrait=user.getHeadportrait();
        username=user.getUsername();
        studentname=user.getStudentname();
        studentid=user.getStudentid();
        sessionId=user.getSessionId();
        role=0;
    }

    public SessionData(Admin admin){
        if (admin == null)throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        id=admin.getAdmin_id();
        headportrait="admin.jpg";
        username=admin.getUsername();
        studentname="admin";
        studentid="00000000000";
        sessionId=admin.getSessionId();
        role=1;
    }
}
