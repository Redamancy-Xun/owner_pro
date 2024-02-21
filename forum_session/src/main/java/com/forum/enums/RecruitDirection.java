package com.forum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitDirection {

    FRONT_END("前端"),
    BACK_END("后端"),
    PROJECT_MANAGER("项目经理"),
    UI_DESIGNER("UI设计师"),
    OTHER("其他需求方向");

    private final String name;

}
