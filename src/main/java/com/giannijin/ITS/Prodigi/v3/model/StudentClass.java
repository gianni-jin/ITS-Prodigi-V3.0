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
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentClassId")

@Entity
@Table(name = "tbl_class")
@NoArgsConstructor

public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_CLASS_ID")
    private Long studentClassId;


    @Column (name = "name")
    @NotBlank(message = "The name of the class cannot be empty")
    private String name;
    @Column (name = "description")
    private String description;


    @Column (name = "start_school_year")
    private Integer startYear;
    @Column (name = "number_students")
    private Integer numStudents;

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

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "studentClass", fetch = FetchType.LAZY)
    private List<Student> studentList;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "program_id" )
    @Getter @Setter private Program program;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "studentClasses")
    private Set<Teacher> teachers;

}
