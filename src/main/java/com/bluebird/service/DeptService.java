package com.bluebird.service;

import com.bluebird.po.dept;

import java.util.List;

public interface DeptService {
    public dept selectByDeptId(Integer id);

    public List<dept> selectAll();

    public void deleteByDeptId(Integer id) throws Exception;

    public void addDept(dept dept);

    public void updateDept(dept dept);
}
