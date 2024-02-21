package com.forum.util;

import com.forum.exception.MyException;
import com.forum.dto.SessionData;
import com.forum.entity.User;
import com.forum.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;


/**
 * @author yannis
 * @version 2020/8/1 18:38
 */
@Component
public class SessionUtils {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public String getUserId(){
        return Optional
                .ofNullable(getSessionData())
                .orElse(new SessionData())
                .getId();
    }

    public String getSessionId(String userId)throws CommonException{
        Object obj = redisUtil.get(userId);
        if(!(obj instanceof String)){
            throw new CommonException(CommonErrorCode.SESSION_NOT_FOUND);
        }

        return (String) obj;
    }

    public SessionData getSessionData() throws CommonException {
        String key = request.getHeader("session");
        if(key == null) throw new CommonException(CommonErrorCode.NEED_SESSION_ID);
        if(redisUtil.isExpire(key)){
            redisUtil.del(key);
            throw new CommonException(CommonErrorCode.LOGIN_HAS_OVERDUE);
        }

        return (SessionData) redisUtil.get(key);

    }

    public void setSessionId(String sessionId){
        response.setHeader("session",sessionId);
    }

    public String generateSessionId() {
        String sessionId = UUID.randomUUID().toString();
        response.setHeader("session", sessionId);
        return sessionId;
    }

    public void ChangeContentType(){
        response.setHeader("Content-Type","application/json");
    }

    public void invalidate(){
        request.removeAttribute(CommonConstants.SESSION);
        String userId = getUserId();
        redisUtil.del(userId);
        redisUtil.del(getSessionId(userId));
    }

    public void refreshData(User user) throws CommonException {
        String userId = getUserId();
        if(user == null){
            user = userMapper.selectById(userId);
        }
        SessionData sessionData = new SessionData(user);
        String sessionId = getSessionId(getUserId());
        AssertUtil.notNull(sessionId,CommonErrorCode.SESSION_NOT_FOUND);

        redisUtil.del(sessionId);
        redisUtil.set(sessionId,sessionData,86400);
        redisUtil.del(userId);
        redisUtil.set(userId,sessionId,86400);
    }
}
