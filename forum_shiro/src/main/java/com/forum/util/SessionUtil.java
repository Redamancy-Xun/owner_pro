package com.forum.util;

import com.forum.common.EnumExceptionType;
import com.forum.dto.SessionData;
import com.forum.entity.User;
import com.forum.exception.MyException;
import com.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class SessionUtil {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletRequest response;

    @Autowired
    private UserMapper userMapper;

    public SessionData getSessionData(){
        String sessionId = request.getHeader("Sessionid");
        if (sessionId==null) return null;

        return getSessionDataFromDB(sessionId);
    }

    public String getSessionId(){
        return  request.getHeader("Sessionid");
    }

    private SessionData getSessionDataFromDB(String sessionId){
        SessionData sessionData;
        User user = userMapper.getUserBySessionId(sessionId);
        if (user==null) throw new MyException(EnumExceptionType.USER_NOT_EXIST);
        sessionData = new SessionData(user);
        return sessionData;
    }

    public String generateSessionId(){
        return UUID.randomUUID().toString();
    }


}
