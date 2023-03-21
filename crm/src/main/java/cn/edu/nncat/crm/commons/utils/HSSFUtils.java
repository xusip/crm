package cn.edu.nncat.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * @program: CRM
 * @description:
 * @author: xsp
 * @create: 2022-10-03 17:20
 * @version:1.0
 **/

/*
*关于Excel文件操作的工具类
* */
public class HSSFUtils {
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
