package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

/**
 * This class represents the Student entity, mapping to the "student" table in the database.
 * The Lombok annotations are used to reduce boilerplate code.
 * - @Getter and @Setter: Automatically generate getter and setter methods for all fields.
 * - @ToString: Automatically generate the toString method.
 * - @NoArgsConstructor and @AllArgsConstructor: Automatically generate a no-argument constructor and a constructor with all arguments.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "student",
       uniqueConstraints = {
               // This is a unique constraint for the email column
               // which means that the email column must be unique across all records in the student table.
               @UniqueConstraint(name = "student_email_unique",
                                 columnNames = "email")
       })
public class Student
{
    /**
     * The primary key of the student entity, using a sequence generator for automatic id generation.
     * - @Id: Marks this field as the primary key.
     * - @SequenceGenerator and @GeneratedValue: Define the id generation strategy using a database sequence.
     * - @Column: Defines the column details in the database, with updatable=false meaning this column cannot be updated.
     */
    @Id
    @SequenceGenerator(name = "student_sequence",
                       sequenceName = "student_sequence",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "student_sequence")
    @Column(name = "id",
            nullable = false,
            updatable = false)
    private Long id;

    /**
     * The first name of the student, a required field, stored as TEXT type in the database.
     */
    @Column(name = "first_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstName;

    /**
     * The last name of the student, a required field, stored as TEXT type in the database.
     */
    @Column(name = "last_name",
            nullable = false,
            // This is a column definition for the last_name column
            // Containing the TEXT data type meaning that the column can store any text data.
            columnDefinition = "TEXT")
    private String lastName;

    /**
     * The email of the student, a required field, stored as TEXT type in the database.
     * Due to the unique constraint defined at the table level, the email must be unique.
     */
    @Column(name = "email",
            nullable = false,
            columnDefinition = "TEXT")
    private String email;

    /**
     * The age of the student, a required field.
     */
    @Column(name = "age",
            nullable = false)
    private Integer age;

    public Student(final String firstName, final String lastName, final String email, final Integer age)
    {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.age       = age;
    }
}
