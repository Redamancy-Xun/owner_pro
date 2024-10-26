package com.forum.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("Excel 数据对象")
public class Excel {

    private String fileName;    //文件名

    private String sheetName;      //表名

    private List<String> rowName;   //列名

    private List<Object[]> dataList;    //数据内容

    public Excel(List<Object[]> responses){
        List<String> nameList = new ArrayList<>();

        nameList.add("帖子id");
        nameList.add("发帖用户id");
        nameList.add("发布日期");
        nameList.add("招募类型");
        nameList.add("需求方向");
        nameList.add("技术栈");
        nameList.add("详情内容");
        nameList.add("开始时间");
        nameList.add("结束时间");
        nameList.add("联系方式");
        nameList.add("完成状态");
        nameList.add("置顶状态");

        this.fileName = "博远招募帖子";
        this.sheetName = "博远招募帖子";
        this.rowName = nameList;

        this.dataList = responses;
    }
}
