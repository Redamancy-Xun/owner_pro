package com.forum.common;

import com.forum.exception.MyException;
import com.google.gson.Gson;
import com.forum.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource(name = "json")
    Gson gson;

    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest request, Exception e) {

        Subject subject = SecurityUtils.getSubject();
        if(subject != null) {
            UserDTO principal = (UserDTO) subject.getPrincipal();
            if(principal != null){
                log.error("用户：" + principal.getId());
            }
        }

        Map<String, String> res = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //如果字段的值为空，判断若值为空，则删除这个字段>
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        log.error("参数：" + gson.toJson(res));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        log.error("异常：" + sw.toString());

        if(e instanceof MyException) {
            log.error("自定义异常：" + ((MyException) e).getEnumExceptionType().getCodeMessage());
            return Result.result(((MyException) e).getEnumExceptionType());
        }

        if(e instanceof UnauthenticatedException){
            Result result = Result.result(EnumExceptionType.LOGIN_INVALID);
            log.error("自定义异常：" + EnumExceptionType.LOGIN_INVALID.getCodeMessage());
            return result;
        }

        if(e instanceof RuntimeException) {
            //...
        }

        return Result.fail(e.getMessage(), null);
    }
}