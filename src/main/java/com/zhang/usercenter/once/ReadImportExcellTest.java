package com.zhang.usercenter.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;

public class ReadImportExcellTest {

    public static void main(String[] args) {
        String fileName = "F:\\IdeaProjects\\JAVA_WEB\\user-center\\src\\main\\resources\\excell\\tryfile.xlsx";
        synchronousRead(fileName);
    }



    // 通过监听器读取
    public static void readByListener(String fileName) {
        EasyExcel.read(fileName, ExcellData.class, new TableListener()).sheet().doRead();
    }



    // 同步读取数据
    public static List<ExcellData> synchronousRead(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<ExcellData> list = EasyExcel.read(fileName).head(ExcellData.class).sheet().doReadSync();
        System.out.println(list.toString());
        return list;
    }

}
