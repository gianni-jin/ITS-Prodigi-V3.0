package com.giannijin.ITS.Prodigi.v3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "tbl_teacher")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "teacherId")
public class Teacher {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long teacherId;

    @Column(name = "first_name")
    @NotBlank(message = "Teacher's first name cannot be empty")
    @Getter @Setter private String firstName;

    @Column (name = "last_name")
    @Getter @Setter private String lastName;

    @Column (name = "module")
    @Getter @Setter private String module;

    @Column (name = "residence_address")
    @Getter @Setter private String residenceAddress;


    @Column (name = "gender")
    @Getter @Setter private String gender;



    @Column (name = "birth_date")
    @Getter @Setter private Date birthDate;


    @Column (name = "salary")
    @Getter @Setter private Integer salary;

    @Column (name = "phone_number")
    @Getter @Setter private Long phoneNumber;

    @Column (name = "email")
    @Getter @Setter private String email;

    @Column (name = "start_school_year")
    @Getter @Setter private Integer startYear;

    @Column (name = "end_school_year")
    @Getter @Setter private Integer endYear;


    @Column (name = "saving_date")
    @Getter @Setter private Date date;


    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Getter @Setter private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Getter @Setter private Timestamp updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_studentClass",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_class_id"))
    private Set<StudentClass> studentClasses;
}
