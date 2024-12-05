package com.example.demo;

import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application 类是 Spring Boot 应用的入口点。
 * 使用 @SpringBootApplication 注解来标记这是一个 Spring Boot 应用。
 * 该类负责启动应用以及配置初始化任务。
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Application
{
    /**
     * 主函数，用于启动 Spring Boot 应用。
     * @param args 命令行参数，通常未使用。
     */
    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 配置一个 CommandLineRunner Bean，用于在应用启动后执行初始化逻辑。
     * @param studentService 学生服务接口，用于操作学生相关数据。
     * @return 返回一个 CommandLineRunner 实例，用于执行初始化学生数据的操作。
     */
    @Bean
    public CommandLineRunner commandLineRunner(final StudentService studentService)
    {
        return notUsed -> studentService.initializeStudents();
    }
}
