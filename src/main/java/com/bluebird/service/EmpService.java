package com.bluebird.service;

import com.bluebird.po.emp;
import com.bluebird.po.pageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    pageBean selectRow(Integer page, Integer pageSize, String name,
                       Short gender, LocalDate begin, LocalDate end);

    pageBean empRows(Integer page, Integer pageSize);


    void deleteIds(List<Integer> ids);

    void addEmp(emp emp);

    emp getByEmpId(Integer id);

    void updateById(emp emp);

    emp login(emp emp);
}
