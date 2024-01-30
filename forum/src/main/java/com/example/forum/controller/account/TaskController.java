package com.example.forum.controller.account;

import com.example.forum.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @PostMapping("/task")
    public Result task(@RequestParam("id") Integer id) {
        return Result.success("success", id);
    }
}
