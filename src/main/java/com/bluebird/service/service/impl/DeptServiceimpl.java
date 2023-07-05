package com.bluebird.service.service.impl;

import com.bluebird.Aop.MyLog;
import com.bluebird.mapper.DeptLogMapper;
import com.bluebird.mapper.DeptMapper;
import com.bluebird.mapper.EmpMapper;
import com.bluebird.po.DeptLog;
import com.bluebird.po.dept;
import com.bluebird.service.DeptLogService;
import com.bluebird.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceimpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @MyLog
    @Override
    public dept selectByDeptId(Integer id) {
        dept dept = deptMapper.selectByDeptId(id);
        return dept;
    }

    @Override
    public List<dept> selectAll() {
        return deptMapper.selectAll();
    }

    //@Transactional(rollbackFor = Exception.class)
    @Transactional
    @Override
    public void deleteByDeptId(Integer id) throws Exception {

        try {
            deptMapper.deleteByDeptId(id);
            //int i=1/0;
            /*if(true){
                throw new Exception("出错了....");
            }*/
            empMapper.deleteEmpByDeptId(id);


        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作本次删除的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void addDept(dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public void updateDept(dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
