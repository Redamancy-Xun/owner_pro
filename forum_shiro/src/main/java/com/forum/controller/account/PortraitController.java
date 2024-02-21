package com.forum.controller.account;

import com.forum.common.EnumExceptionType;
import com.forum.common.Result;
import com.forum.dto.SessionData;
import com.forum.exception.MyException;
import com.forum.service.UserService;
import com.forum.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    SessionUtil sessionUtil;

//    @RequiresRoles("online")
    @PostMapping(value = "/portrait", produces = "application/json")
    @ApiOperation("上传头像")
    public Result uploadPortrait(MultipartFile multipartFile) {

        SessionData sessionData = sessionUtil.getSessionData();
        String sessionId = sessionData.getSessionId();
        if (userService.getStatus(sessionData.getId())==0) throw new MyException(EnumExceptionType.LOGIN_INVALID);
        if (multipartFile == null) throw new MyException(EnumExceptionType.EMPTY_FILE);
        if (sessionData.getId() == null) throw new  MyException(EnumExceptionType.USER_NOT_EXIST);

        return Result.success("更新成功", userService.uploadPortrait(multipartFile, sessionData.getId()));
    }
}
