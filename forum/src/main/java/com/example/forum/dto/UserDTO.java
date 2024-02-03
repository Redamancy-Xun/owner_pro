package com.example.forum.dto;

import com.example.forum.entity.Admin;
import com.example.forum.entity.User;
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
    private int type;//0为普通用户，1为管理员

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
