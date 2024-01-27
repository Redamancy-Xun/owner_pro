package com.example.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import module.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private int id;
    private String username;
    private int type;

    public UserDTO(User user){
        id = Integer.parseInt(user.getId()+"");
        username = user.getUser_name();
        type = 0;
    }

}
