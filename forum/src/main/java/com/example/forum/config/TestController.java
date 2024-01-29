package module.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import module.dto.UserDTO;

@RestController
public class TestController {

    @GetMapping("/userTest")
    public UserDTO userTest(){
        UserDTO principal = (UserDTO) SecurityUtils.getSubject().getPrincipal();
        return principal;
    }

    @GetMapping("/anonTest")
    public String anonTest(){
        return "this is anon";
    }

    @GetMapping("/exception")
    public void exception() {
        throw new NullPointerException("asd");
    }


}
