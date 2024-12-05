package com.example.demo.generator.random;

import com.example.demo.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentInfoGeneratorTest
{
    @Test
    void generateStudent_ValidCount_ShouldGenerateCorrectNumberOfStudents()
    {
        final int           studentCount = 10;
        final List<Student> students     = StudentInfoGenerator.generateStudent(studentCount);
        assertNotNull(students, "生成的学生列表不应为null");
        assertEquals(studentCount, students.size(), "学生数量应与请求的数量匹配");
    }

    @Test
    void generateStudent_InvalidCount_ShouldNotGenerateStudents()
    {
        final int           studentCount = -1;
        final List<Student> students     = StudentInfoGenerator.generateStudent(studentCount);
        assertNotNull(students, "生成的学生列表不应为null，即使请求了无效的数量");
        assertTrue(students.isEmpty(), "学生列表不应包含任何学生");
    }

    @Test
    void generateStudent_UniqueNames_ShouldGenerateUniqueNames()
    {
        final int           studentCount = 5;
        final List<Student> students     = StudentInfoGenerator.generateStudent(studentCount);

        final Set<String> uniqueNames = students.stream()
                                                .map(s -> s.getFirstName() + " " + s.getLastName())
                                                .collect(Collectors.toSet());

        assertEquals(studentCount, uniqueNames.size(), "生成的学生姓名应唯一");
    }

    @Test
    void generateStudent_AgeRange_ShouldGenerateAgesWithinRange()
    {
        final int           studentCount = 5;
        final List<Student> students     = StudentInfoGenerator.generateStudent(studentCount);
        final int           minAge       = 18;
        final int           maxAge       = 68;

        for (final Student student : students)
        {
            final int age = student.getAge();
            assertTrue(age >= minAge && age <= maxAge, "生成的年龄应在指定范围内: " + age);
        }
    }
}
