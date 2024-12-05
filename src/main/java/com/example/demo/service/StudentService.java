package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.generator.random.StudentInfoGenerator;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生服务类，负责处理与学生相关的业务逻辑
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService
{
    // 定义学生数量常量
    private static final int               STUDENT_COUNT = 10;
    // 注入学生仓库接口，用于操作学生数据
    private final        StudentRepository studentRepository;

    /**
     * 初始化学生数据
     * 该方法生成指定数量的学生信息，并保存到数据库中
     * 主要用于系统初始化或者测试数据准备
     */
    public void initializeStudents()
    {
        // 记录初始化学生的调试信息
        log.debug("Initializing students");
        // 生成学生信息
        final List<Student> students = StudentInfoGenerator.generateStudent(STUDENT_COUNT);

        // 记录保存学生信息的调试信息
        log.debug("Saving {} students: {}", STUDENT_COUNT, students);
        // 将生成的学生信息保存到数据库中
        this.studentRepository.saveAll(students);
    }
}
