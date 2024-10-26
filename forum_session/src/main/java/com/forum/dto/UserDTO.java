package com.forum.dto;

import com.forum.entity.Admin;
import com.forum.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private Integer id;
    private String username;
    //0为普通用户，1为管理员
    private int type;

    public UserDTO(User user){
        id = user.getId();
        username = user.getUsername();
        type = 0;
    }

    public UserDTO(Admin admin){
        id = admin.getAdmin_id();
        username = admin.getUsername();
        type = 1;
    }

}
