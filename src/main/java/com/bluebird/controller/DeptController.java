package com.bluebird.controller;

import com.bluebird.anno.Log;
import com.bluebird.po.Result;
import com.bluebird.po.dept;
import com.bluebird.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("prototype")
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    //private static Logger log= LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)

    @GetMapping
    public Result selectAll() {
        List<dept> deptList = deptService.selectAll();
        log.info("查询所有员工");
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id) throws Exception {
        log.info("根据Id删除部门:{}", id);
        deptService.deleteByDeptId(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addDept(@RequestBody dept dept) {
        log.info("新增部门:{}", dept);
        deptService.addDept(dept);
        return Result.success();
    }

    @Log
    @PutMapping
    public Result updateDept(@RequestBody dept dept) {
        log.info("修改部门:", dept);
        deptService.updateDept(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("查询单个部门:{}", id);
        dept dept = deptService.selectByDeptId(id);
        return Result.success(dept);
    }

}
