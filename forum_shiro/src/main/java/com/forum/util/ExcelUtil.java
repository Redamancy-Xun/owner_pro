package com.forum.util;

import com.forum.dto.Excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Component
public class ExcelUtil {

    @Autowired
    HttpServletResponse response;

    public boolean exportRecruitArticle(Excel excel) throws IOException {

        //创建工作簿、创建表单
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(excel.getSheetName());

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

            for (int j=0; j<objects.length; j++) {
                HSSFCell cell = row.createCell(j, CellType.STRING);
                if (objects[j] != null && !"".equals(objects[j].toString())) {
                    cell.setCellValue(objects[j].toString());
                } else {
                    cell.setCellValue("未获取到内容");
                }
            }

        }

        //通过 HttpServletResponse 将生成的 Excel 文件发送给客户端进行下载
        try {
            //发送结果
            String fileName = excel.getFileName()+".xls";
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            String headStr = "attachment; filename=\"" + encodedFileName + "\"";
            //设置 Content-Type 为 “APPLICATION/OCTET-STREAM”，告诉浏览器这是一个二进制流文件，需要下载保存
            response.setContentType("APPLICATION/OCTET-STREAM");
            //设置 Content-Disposition 头部，指示浏览器如何处理响应的内容，提示浏览器以附件形式下载文件，并指定下载时的文件名
            response.setHeader("Content-Disposition", headStr);
            //获取 HttpServletResponse 的输出流，并将 Excel 文件内容写入到输出流中，客户端浏览器就能获取到完整的 Excel 文件内容
            OutputStream out = response.getOutputStream();
            workbook.write(out);

            //关闭输出流
            out.close();
            return true;
        } catch (IOException e){
            return false;
        }

    }
}
