package com.remember.service;

import com.remember.domain.po.Person;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface ExcelService {
    void importExcel(InputStream inputStream) throws Exception;
    void exportExcel(OutputStream outputStream, List<Person> data) throws Exception;
}
