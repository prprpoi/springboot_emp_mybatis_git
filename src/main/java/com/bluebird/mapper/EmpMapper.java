package com.bluebird.mapper;

import com.bluebird.po.emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //查询总记录数
    /*@Select("select count(*) from emp;")
    public Long count();
    //分页查询，获取总记录数
    @Select("select * from emp limit #{start},#{pageSize};")
    public List<emp> empRows(Integer start, Integer pageSize);*/

    @Select("select * from emp")
    public List<emp> selectAll();


    public List<emp> selectRow(String name, Short gender, LocalDate begin, LocalDate end);


    void delete(List<Integer> ids);

    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    void addEmp(emp emp);

    @Select("select * from emp where id=#{id};")
    emp getByEmpId(Integer id);

    //更新员工
    void updateById(emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    emp login(emp emp);

    @Delete("delete from emp where dept_id=#{deptId};")
    public void deleteEmpByDeptId(Integer deptId);

}
