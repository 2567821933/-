package com.zhang.usercenter.once;

import java.util.List;

/**
 * 导入excel到数据库
 */
public class ImportXingQiuUser {
    public static void main(String[] args) {
        String fileName = "F:\\IdeaProjects\\JAVA_WEB\\user-center\\src\\main\\resources\\excell\\tryfile.xlsx";
        List<ExcellData> list = ReadImportExcellTest.synchronousRead(fileName);
    }
}
