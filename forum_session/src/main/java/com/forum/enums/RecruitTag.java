package com.forum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitTag {

    JAVA("Java"),
    PYTHON("Python"),
    C("C/C++"),
    GO("Go"),
    RUBY("Ruby"),
    OTHER_BACKEND_LANGUAGE("其他后端语言"),

    SPRING("Spring"),
    DJANGO("Django"),
    RUBY_ON_RAILS("Ruby on Rails"),
    OTHER_WEB_STRUCTURE("其他Web框架"),

    RDS("关系型数据库"),
    NOSQL("非关系型数据库"),
    IN_MEMORY_DATABASE("内存数据库"),

    JAVASCRIPT("JavaScript"),
    HTML("HTML"),
    CSS("CSS"),
    OTHER_FRONTEND_LANGUAGE("其他前端语言"),

    REACT("React"),
    ANGULAR("Angular"),
    VUE_JS("Vue.js"),
    OTHER_FRONTEND_STRUCTURE("其他前端框架"),

    AJAX("AJAX"),
    OTHER_CALL("其他与服务器通信方式"),

    GITEE("Gitee"),
    GITHUB("Github"),
    OTHER_GIT("其他云git库"),
    ;

    private final String name;
}
