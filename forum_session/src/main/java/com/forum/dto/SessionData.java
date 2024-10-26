package com.forum.dto;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("SessionData")
public class SessionData implements Serializable {

    /**
     * {@link User}
     */
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("role")
    private Integer role;

    @ApiModelProperty("name")
    private String name;

    public SessionData(User user) {
        AssertUtil.notNull(user, CommonErrorCode.USER_NOT_EXIST);
        this.id = user.getId();
        this.avatar = user.getAvatar();
        this.bannedBefore = user.getBannedBefore();
        this.createTime = user.getCreateTime();
        this.gender = user.getGender();
        this.major = user.getMajor();
        this.name = user.getName();
        this.role = user.getRole();
        this.status = user.getStatus();
    }
}

