package com.bluebird.service.service.impl;

import com.bluebird.mapper.EmpMapper;
import com.bluebird.po.emp;
import com.bluebird.po.pageBean;
import com.bluebird.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceimpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public pageBean empRows(Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行查询
        List<emp> empList = empMapper.selectAll();
        Page<emp> p = (Page<emp>) empList;
        //封装PageBean对象
        pageBean pageBean = new pageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void deleteIds(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void addEmp(emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public emp getByEmpId(Integer id) {
        return empMapper.getByEmpId(id);
    }

    @Override
    public void updateById(emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

    }

    @Override
    public emp login(emp emp) {
        return empMapper.login(emp);
    }

    @Override
    public pageBean selectRow(Integer page, Integer pageSize, String name,
                              Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<emp> listRow = empMapper.selectRow(name, gender, begin, end);
        Page<emp> p = (Page<emp>) listRow;
        pageBean pageBean = new pageBean(p.getTotal(), p.getResult());
        return pageBean;

    }

    /*@Override
    public pageBean empRows(Integer page, Integer pageSize) {
        //获取总记录数
        Long count = empMapper.count();
        //获取分页查询的结果
        Integer start=(page-1)*pageSize;
        List<emp> empList = empMapper.empRows(start, pageSize);
        //将分页查询的结果封装到pageBean对象
        pageBean pageBean = new pageBean(count,empList);
        return pageBean;
    }*/


}
