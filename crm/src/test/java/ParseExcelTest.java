import cn.edu.nncat.crm.commons.utils.HSSFUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-09-28 21:02
 * @version:1.0
 **/

/*
* 使用Apache-poi解析Excel文件
* */
public class ParseExcelTest {
    public static void main(String[] args) throws Exception{
        InputStream is = new FileInputStream("D:\\IDEA2019\\代码文件IDEA\\CRM\\crm\\src\\serverDir\\activityList.xls");
        HSSFWorkbook wb = new HSSFWorkbook(is);
        HSSFSheet sheet=wb.getSheetAt(0);
        HSSFRow row=null;
        HSSFCell cell=null;
        for (int i = 0; i <=sheet.getLastRowNum() ; i++) {
            row=sheet.getRow(i);
            for (int j = 0; j <row.getLastCellNum() ; j++) {
                cell=row.getCell(j);
                System.out.print(HSSFUtils.getCellValueForStr(cell)+" ");
            }
            System.out.println("");
        }

    }

    /**
     * 从指定的HSSFCell对象中获取列的值
     */
    public static String getCellValueForStr(HSSFCell cell){
        String ret = "";
        if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            ret = cell.getStringCellValue();
        }else if (cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            ret=cell.getNumericCellValue()+"";
        }else if (cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
           ret=cell.getBooleanCellValue()+"";
        }else if (cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
            ret=cell.getCellFormula();
        }else {
            ret="";
        }
        return ret;
    }
}
