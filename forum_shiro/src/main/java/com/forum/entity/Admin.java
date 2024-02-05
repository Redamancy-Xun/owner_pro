package com.forum.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "admin")
@ApiModel("Admin 管理员")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    @NotNull(message = "管理员id不能为空")
    @ApiModelProperty("管理员id")
    private Integer admin_id;

    @Column(name = "username", nullable = false)
    @NotNull(message = "管理员用户名不能为空")
    @ApiModelProperty("管理员用户名")
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull(message = "管理员密码不能为空")
    @ApiModelProperty("密码")
    private String password;
}
