package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 学生仓库接口，继承自JpaRepository以获得对学生实体的数据库操作功能
 * 该接口主要用于处理与学生相关的数据库操作，如查询、插入、更新和删除
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    /**
     * 根据学生的名和姓查找学生
     *
     * @param firstName 学生的名
     * @param lastName 学生的姓
     * @return 如果找到匹配的学生，则返回包含学生对象的Optional，否则返回Optional.empty()
     */
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * 根据邮箱地址查找学生信息
     * 此方法使用Optional封装返回结果，以优雅地处理可能的空值情况
     *
     * @param email 学生的邮箱地址，用于查询
     * @return Optional<Student> 包含学生信息的Optional对象，如果未找到对应学生，则返回空Optional
     */
    Optional<Student> findByEmail(String email);
}

