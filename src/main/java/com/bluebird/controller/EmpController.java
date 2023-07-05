package com.bluebird.controller;

import com.bluebird.anno.Log;
import com.bluebird.po.Result;
import com.bluebird.po.emp;
import com.bluebird.po.pageBean;
import com.bluebird.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    //@GetMapping
    public Result selectRows(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("分页查询,参数:{},{}", page, pageSize);
        pageBean pagebean = empService.empRows(page, pageSize);
        return Result.success(pagebean);
    }

    @GetMapping
    public Result selectRow(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "5") Integer pageSize,
                            String name, Short gender,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("执行分页查询,参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        pageBean pageBean = empService.selectRow(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result deleteIds(@PathVariable List<Integer> ids) {
        log.info("删除多个员工:ids{}", ids);
        empService.deleteIds(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addEmp(@RequestBody emp emp) {
        log.info("添加员工，{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getByEmpId(@PathVariable Integer id) {
        log.info("根据id查询员工方法:{}", id);
        emp emp = empService.getByEmpId(id);
        return Result.success(emp);
    }


    @Log
    @PutMapping
    public Result updateById(@RequestBody emp emp) {
        log.info("更新员工:{}", emp);
        empService.updateById(emp);
        return Result.success();
    }

}
