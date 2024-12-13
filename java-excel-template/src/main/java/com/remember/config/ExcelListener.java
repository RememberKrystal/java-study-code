package com.remember.config;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.remember.domain.po.Address;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<Address> {
    private final List<Address> dataList = new ArrayList<>();

    @Override
    public void invoke(Address address, AnalysisContext context) {
        System.out.println("读取到一条数据：" + address);
        dataList.add(address);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据解析完成，共读取到：" + dataList.size() + "条数据");
    }

    public List<Address> getDataList() {
        return dataList;
    }
}

