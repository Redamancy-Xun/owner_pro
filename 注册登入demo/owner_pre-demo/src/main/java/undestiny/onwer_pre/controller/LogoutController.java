package undestiny.onwer_pre.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session){
        //检查是否登录（session是否存在）
        if (session.getAttribute("user") == null)
            return null;
        //注销session（在服务器里删除该session）
        session.invalidate();
        return "Logout successfully";
    }
}
