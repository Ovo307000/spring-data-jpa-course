package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 学生实体的仓库接口，用于执行学生数据的CRUD操作
 * 继承自JpaRepository，自动获得数据访问能力
 *
 * @param <Student> 指定实体类型为Student
 * @param <Long> 指定主键类型为Long
 */
public interface StudentRepository extends JpaRepository<Student, Long>
{
    // 此接口没有定义额外的方法，完全依赖于JpaRepository提供的方法进行数据操作
}
