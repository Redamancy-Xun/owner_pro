package com.forum.util;

import com.forum.dto.Excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ExcelUtil {

    @Autowired
    HttpServletResponse response;

    public boolean exportRecruitArticle(Excel excel) throws IOException {

        //创建工作簿、创建表单
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("sheet1");

        //创建标题行
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeight((short) (25 * 35)); //设置高度
        for(int i=0;i<excel.getRowName().size();i++){
            HSSFCell cell = rowTitle.createCell(i);
            cell.setCellValue(excel.getRowName().get(i));
        }

        //写入数据
        for (int i=0;i<excel.getDataList().size();i++){
            Object[] objects = excel.getDataList().get(i);
            HSSFRow row =sheet.createRow(i+1);

            row.setHeight((short) (25 * 20));//设置高度

            for (int j=0;j<objects.length;j++){
                HSSFCell cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);
                if (!"".equals(objects[j])){
                    cell.setCellValue(objects[j].toString());
                } else {
                    cell.setCellValue("未获取到内容");
                }

            }

        }

        try {
            //发送结果
            String fileName = excel.getFileName()+".xls";
            String headStr = "attachment; filename=\"" + fileName + "\"";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            OutputStream out = response.getOutputStream();
            workbook.write(out);

            out.close();
            return true;
        }catch (IOException e){
            return false;
        }

    }
}
