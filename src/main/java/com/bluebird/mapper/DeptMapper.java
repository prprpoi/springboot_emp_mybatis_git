package com.bluebird.mapper;

import com.bluebird.po.dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    public List<dept> selectAll();

    @Delete("delete from dept where id=#{id};")
    public void deleteByDeptId(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime});")
    public void addDept(dept dept);

    @Update("update dept set name=#{name},create_time=#{createTime},update_time=#{updateTime} where id=#{id};")
    void updateDept(dept dept);

    @Select("select * from dept where id=#{id}")
    public dept selectByDeptId(Integer id);
}
