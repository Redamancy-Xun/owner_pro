package undestiny.onwer_pre.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import undestiny.onwer_pre.entity.User;
import undestiny.onwer_pre.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/deleteUserById/{id}")
    public String deleteUserById(@PathVariable("id")Integer id,
                                 HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            return null;

        try {
            int count = userService.deleteUserById(id);
            log.info("count=" + count);
        } catch (RuntimeException e) {
            return "Can't find this ID";
        }
        return "Delete successfully";
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id")Integer id,
                            HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            return null;

        return userService.getUserById(id);
    }

    @GetMapping("/getUserByUsername/{username}")
    public List<User> getUserByUsername(@PathVariable("username")String username,
                                        HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            return null;

        return userService.getUserListByUsername(username);
    }

    @GetMapping("/getUserListOrderly/{order_by_sql}")
    public List<User> getUserListOrderly(@PathVariable("order_by_sql")String order_by_sql,
                                         HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            return null;

        return userService.getUserListOrderly(order_by_sql);
    }
}
