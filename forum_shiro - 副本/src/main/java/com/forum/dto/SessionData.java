package com.xiaowu.dto;

import com.xiaowu.common.CommonErrorCode;
import com.xiaowu.entity.User;
import com.xiaowu.util.AssertUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * session缓存实体
 * @author yan on 2020-02-27
 */
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

    @ApiModelProperty("avatar")
    private String avatar;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("status")
    private String status;

    @ApiModelProperty("bannedBefore")
    private Date bannedBefore;

    @ApiModelProperty("gender")
    private Integer gender;

    @ApiModelProperty("major")
    private String major;

    @ApiModelProperty("grade")
    private String grade;

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

