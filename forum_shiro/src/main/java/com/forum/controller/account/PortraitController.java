package com.forum.controller.account;

import com.forum.common.EnumExceptionType;
import com.forum.common.Result;
import com.forum.dto.UserDTO;
import com.forum.exception.MyException;
import com.forum.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@Api("头像上传接口")
public class PortraitController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/portrait",produces = "application/json")
    public Result uploadPortrait(MultipartFile multipartFile) {

        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();

        if (principal==null) throw new MyException(EnumExceptionType.LOGIN_INVALID);
        Integer id = principal.getId();
        if (multipartFile == null) throw new MyException(EnumExceptionType.EMPTY_FILE);
        if (id == null) throw new  MyException(EnumExceptionType.USER_NOT_EXIST);

        return Result.success("更新成功", userService.uploadPortrait(multipartFile, id));
    }
}
