package com.forum.controller.response;

import com.forum.dto.SessionData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("LoginResponse 登录响应")
public class LoginResponse {

    @ApiModelProperty("会话信息")
    private SessionData sessionData;

    @ApiModelProperty("会话id")
    private String sessionId;
}
