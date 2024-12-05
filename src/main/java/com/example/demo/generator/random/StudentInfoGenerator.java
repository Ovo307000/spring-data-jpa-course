package com.example.demo.generator.random;

import com.example.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 学生信息生成器类，用于生成随机的学生信息
 */
@Slf4j
public class StudentInfoGenerator
{
    // 邮件域名常量
    private static final String EMAIL_DOMAIN = "@example.com";

    // 年龄范围常量
    private static final int BASE_AGE = 18;

    // 年龄变化范围常量
    private static final int AGE_VARIANCE = 50;

    // 常用英文名字列表
    private static final List<String> FIRST_NAMES = List.of("Alice",
            "Bob",
            "Charlie",
            "David",
            "Eve",
            "Frank",
            "Grace",
            "Heidi",
            "Ivan",
            "Judy",
            "Kevin",
            "Linda",
            "Michael",
            "Nancy",
            "Oliver",
            "Pamela",
            "Quentin",
            "Rachel",
            "Steve",
            "Tina",
            "Ursula",
            "Victor",
            "Wendy",
            "Xavier",
            "Yvonne",
            "Zack");

    // 常用英文姓氏列表
    private static final List<String> LAST_NAMES = List.of("Smith",
            "Johnson",
            "Williams",
            "Jones",
            "Brown",
            "Davis",
            "Miller",
            "Wilson",
            "Moore",
            "Taylor",
            "Anderson",
            "Thomas",
            "Jackson",
            "White",
            "Harris",
            "Martin",
            "Thompson",
            "Garcia",
            "Martinez",
            "Robinson",
            "Clark",
            "Rodriguez",
            "Lewis",
            "Lee",
            "Walker",
            "Hall",
            "Allen");

    // 生成唯一全名的迭代器
    private static final Iterator<FullName> uniqueNameIterator = generateUniqueNames().iterator();

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
                            final FullName fullName = getNextFullName();
                            return new Student(fullName.getFirstName(),
                                    fullName.getLastName(),
                                    generateEmail(fullName),
                                    generateAge());
                        })
                        .collect(Collectors.toList());
    }

    /**
     * 获取下一个唯一全名
     *
     * @return 唯一全名对象
     */
    private static FullName getNextFullName()
    {
        if (uniqueNameIterator.hasNext())
        {
            return uniqueNameIterator.next();
        }

        throw new RuntimeException("全名组合已用尽！");
    }

    /**
     * 生成电子邮件地址
     *
     * @param fullName 全名对象
     * @return 生成的电子邮件地址
     */
    private static String generateEmail(final FullName fullName)
    {
        return fullName.getFirstName()
                       .toLowerCase() +
               "." +
               fullName.getLastName()
                       .toLowerCase() +
               EMAIL_DOMAIN;
    }

    /**
     * 生成一个随机年龄
     *
     * @return 随机生成的年龄，作为整数返回
     */
    private static int generateAge()
    {
        return ThreadLocalRandom.current()
                                .nextInt(BASE_AGE, BASE_AGE + AGE_VARIANCE + 1);
    }

    /**
     * 提前生成所有可能的唯一全名组合，并随机打乱顺序
     *
     * @return 打乱顺序的全名集合
     */
    private static List<FullName> generateUniqueNames()
    {
        final List<FullName> allNames = new ArrayList<>();

        for (final String firstName : FIRST_NAMES)
        {
            for (final String lastName : LAST_NAMES)
            {
                allNames.add(new FullName(firstName, lastName));
            }
        }

        log.debug("所有可能的全名组合共 {} 个", allNames.size());
        Collections.shuffle(allNames);

        return allNames;
    }

    /**
     * FullName 类表示一个人的全名，包括名和姓。
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class FullName
    {
        private String firstName; // 名
        private String lastName;  // 姓
    }
}
