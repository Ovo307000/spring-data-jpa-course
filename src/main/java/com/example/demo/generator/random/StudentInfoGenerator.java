package com.example.demo.generator.random;

import com.example.demo.entity.Student;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 学生信息生成器类，用于生成随机的学生信息
 */
public class StudentInfoGenerator
{
    // 邮件域名常量
    private static final String       EMAIL_DOMAIN = "@example.com";
    // 常用英文名字列表
    private static final List<String> FIRST_NAMES  = List.of("Alice",
            "Bob",
            "Charlie",
            "David",
            "Eve",
            "Frank",
            "Grace",
            "Heidi",
            "Ivan",
            "Judy");
    // 常用英文姓氏列表
    private static final List<String> LAST_NAMES   = List.of("Smith",
            "Johnson",
            "Williams",
            "Jones",
            "Brown",
            "Davis",
            "Miller",
            "Wilson",
            "Moore",
            "Taylor");

    /**
     * 生成指定数量的学生信息列表
     *
     * @param studentCount 需要生成的学生数量
     * @return 学生信息列表
     */
    public static List<Student> generateStudent(final int studentCount)
    {
        return IntStream.range(0, studentCount)
                        .mapToObj(index ->
                        {
                            final String firstName = generateFirstName();
                            final String lastName  = generateLastName();

                            return new Student(firstName, lastName, generateEmail(firstName, lastName), generateAge());

                        })
                        .collect(Collectors.toList());
    }

    /**
     * 随机生成英文名字
     *
     * @return 随机生成的英文名字
     */
    private static String generateFirstName()
    {
        return FIRST_NAMES.get((int) (Math.random() * FIRST_NAMES.size()));
    }

    /**
     * 随机生成英文姓氏
     *
     * @return 随机生成的英文姓氏
     */
    private static String generateLastName()
    {
        return LAST_NAMES.get((int) (Math.random() * LAST_NAMES.size()));
    }

    /**
     * 根据名字和姓氏生成电子邮件地址
     *
     * @param firstName 名字
     * @param lastName 姓氏
     * @return 生成的电子邮件地址
     */
    private static String generateEmail(final String firstName, final String lastName)
    {
        return firstName + lastName + EMAIL_DOMAIN;
    }

    /**
     * 随机生成年龄，范围在18到67岁之间
     *
     * @return 随机生成的年龄
     */
    private static Integer generateAge()
    {
        return (int) (Math.random() * 50) + 18;
    }
}
