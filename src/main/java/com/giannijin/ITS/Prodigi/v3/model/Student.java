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


@Setter
@Getter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentId")

@Entity
@Table(name = "tbl_student")
@NoArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column (name = "first_name")
    @NotBlank(message = "Student's first name cannot be empty")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "residence_address")
    private String residenceAddress;

    @Column (name = "gender")
    private String gender;

    @Column (name = "birth_date")
    private Date birthDate;


    @Column (name = "payment_status")
    private String paymentStatus;

    @Column (name = "program")
    private String program;



    @Column (name = "start_school_year")
    private Integer startYear;

    @Column (name = "end_school_year")
    private Integer endYear;


    @Column (name = "saving_date")
    private Date date;



    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;


    @Column (name = "email")
    private String email;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "studentClassId")
    private StudentClass studentClass;

}
