package undestiny.onwer_pre.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import undestiny.onwer_pre.service.UserServiceImpl;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class UpdateController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/updateUsernameById/{username}/{password}/{birthday}/{email}/{headportrait}/{id}")
    public String updateUsernameById(@PathVariable("username")String username,
                                   @PathVariable("password")String password,
                                   @PathVariable("birthday")String birthday,
                                   @PathVariable("email")String email,
                                   @PathVariable("headportrait")String headportrait,
                                   @PathVariable("id")Integer id,
                                   HttpSession session){

        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            return null;

        try {
            int count = userService.updateUsernameById(username, password, birthday, email, headportrait, id);
            log.info("count=" + count);
        } catch (RuntimeException e) {
            return "Can't find this ID";
        }
        return "Update successfully";
    }
}
