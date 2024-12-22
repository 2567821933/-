package com.zhang.usercenter.once;


import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
public class ExcellData {

    @ExcelProperty(index = 0)
    private Integer id;

    @ExcelProperty(index = 1)
    private String username;
}
