package com.forum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitType {

    EAI("双创"),
    OUTSOURCING("外包"),
    HOMEWORK("大作业"),
    SCHOOL_PROJECT("校级项目"),
    CLUB_PROJECT("社团项目"),
    OTHER_TYPE("其他类型");

    private final String name;
}
