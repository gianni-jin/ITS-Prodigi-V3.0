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

@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "subjectId")
@Table(name = "tbl_subject")
@NoArgsConstructor
public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    @Getter @Setter private Long subjectId;




    @Column (name = "name")
    @NotBlank(message = "Student's first name cannot be empty")
    @Getter @Setter private String name;

    @Column (name = "full_name")
    @Getter @Setter private String fullName;



    @Column (name = "description")
    @Getter @Setter private String description;

    @Column (name = "start_school_year")
    @Getter @Setter private Integer startYear;

    @Column (name = "end_school_year")
    @Getter @Setter private Integer endYear;


    @Column(name = "saving_date")
    @Getter @Setter private Date date;



    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @Getter @Setter private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Getter @Setter private Timestamp updatedAt;


    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Getter @Setter private List<Teacher> teacherList;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @Getter @Setter private Program program;
}
