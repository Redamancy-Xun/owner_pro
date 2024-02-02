package com.example.forum.common;

//Google的Gson库，用于JSON的序列化和反序列化。
import com.google.gson.Gson;
//Lombok提供的SLF4J日志注解。
import lombok.extern.slf4j.Slf4j;
//Apache Shiro，一个Java安全框架。
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
//Spring Web的注解，用于处理Web请求和响应。
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//自定义类和异常。
import com.example.forum.dto.UserDTO;
import com.example.forum.exception.MyException;

//import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//@Slf4j: 这是Lombok库提供的注解，用于简化日志的声明。它会自动生成一个名为log的字段，并提供了log的方法。
//这样你就可以直接在类中使用log来记录日志，而不需要手动声明和初始化一个Logger对象。
//使用SLF4J记录错误信息，如用户ID、请求参数和异常堆栈跟踪。这有助于在出现问题时进行调试和诊断。
@Slf4j
//@RestControllerAdvice: 这是Spring框架提供的注解，用于定义一个全局的异常处理器，这个处理器可以处理所有的REST Controller中抛出的异常。
@RestControllerAdvice
public class GlobalExceptionHandler {

    //使用 @Resource(name = "json") 注入一个 Gson 实例，这样我们就可以将其用于将Java对象转换为JSON格式。
    //@Resource(name = "json")
    @Autowired
    //Gson是一个常用的JSON处理库，可以将Java对象转换为JSON字符串，或者将JSON字符串转换为Java对象。
    Gson gson;

    //使用 @ExceptionHandler(value = Exception.class) 注解来声明一个方法，该方法处理所有的Exception类型的异常。
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest request, Exception e) {

        //从请求中获取 Subject，如果用户已认证，则获取其主信息并将其记录到日志中。
        //在这个处理器中，首先获取当前的安全主题（如果存在的话），然后从主题中获取当前登录的用户信息。如果用户存在，则记录用户的ID。
        Subject subject = SecurityUtils.getSubject();
        if(subject != null) {
            UserDTO principal = (UserDTO) subject.getPrincipal();
            if(principal != null){
                log.error("用户：" + principal.getId());
            }
        }

        //创建一个 Map 来存储请求的参数。遍历请求的参数并将它们放入Map中，同时确保不包含空或空的字符串值。
        //从HTTP请求中获取所有的参数，并将它们存储在一个Map中。然后，如果某个参数的值为空或为空字符串，则从Map中删除该参数。
        Map<String, String> res = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //如果字段的值为空，判断若值为空，则删除这个字段
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        log.error("参数：" + gson.toJson(res));

        //使用 PrintWriter 和 StringWriter 来获取异常的堆栈跟踪信息，并将其记录到日志中。
        //使用 PrintWriter 将异常的堆栈跟踪信息写入到一个字符串中，然后使用日志框架将其记录到日志文件。
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        log.error("异常：" + sw);

        /*上面代码是一个全局异常处理器，用于处理Web应用中的异常，并将相关信息（如用户信息、请求参数和异常堆栈跟踪）记录到日志中。
         *下面的代码是根据抛出的异常类型进行不同的处理：*/

        //如果异常是MyException类型，则记录相应的错误信息并返回一个包含异常类型的Result对象。
        if(e instanceof MyException) {
            log.error("RRE异常：" + ((MyException) e).getEnumExceptionType().getCodeMessage());
            return Result.result(((MyException) e).getEnumExceptionType());
        }

        //如果异常是UnauthenticatedException类型，则记录登录无效的错误信息并返回一个包含登录无效的Result对象。
        if(e instanceof UnauthenticatedException){
            Result result = Result.result(EnumExceptionType.LOGIN_INVALID);
            log.error("RRE异常：" + EnumExceptionType.LOGIN_INVALID.getCodeMessage());
            return result;
        }

        return Result.fail(e.getMessage(), null);
    }

    @ExceptionHandler(value = MyException.class)
    public Result MyExceptionHandler(MyException e){
        return Result.result(e.getEnumExceptionType(),null);
    }

}