import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-23 21:18
 * @version:1.0
 **/

/*
 * 使用Apache-poi生成Excel文件
 * */
public class poi {
    public static void main(String[] args) {
        //创建HSSFWorkbook对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("学生列表");
        HSSFRow row= sheet.createRow(0);
        HSSFCell cell=row.createCell(0);
        cell.setCellValue("学号");
        cell=row.createCell(1);
        cell.setCellValue("姓名");
        cell=row.createCell(2);
        cell.setCellValue("年龄");

        //生成HSSFCellStyle设置样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        //使用sheet创建10个HSSFRow对象，对应sheet里的10行
        for(int i=1;i<=10;i++){
            row=sheet.createRow(i);
            cell=row.createCell(0);
            cell.setCellValue(100+i);
            cell=row.createCell(1);
            cell.setCellValue("NAME"+i);
            cell=row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(20+i);
        }

        //调用工具函数生成Excel文件
        FileOutputStream os= null;
        try {
            os = new FileOutputStream("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\studentList.xls");
            wb.write(os);
            os.close();
            wb.close();
            System.out.println("======Create OK=======");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
