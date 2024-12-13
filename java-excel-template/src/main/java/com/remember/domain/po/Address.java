package com.remember.domain.po;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Address {
    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("省")
    private String province;

    @ExcelProperty("市")
    private String city;

    @ExcelProperty("县/区")
    private String town;

    @ExcelProperty("手机")
    private String mobile;

    @ExcelProperty("详细地址")
    private String street;

    @ExcelProperty("联系人")
    private String contact;
}

