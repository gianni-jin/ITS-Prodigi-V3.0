package com.giannijin.ITS.Prodigi.v3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "programId")

@Entity
@Table(name = "tbl_program")
@NoArgsConstructor

public class Program {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "PROGRAM_ID")
        @Setter @Getter private Long programId;

        @Column (name = "program_name")
        @NotBlank(message = "Program's name cannot be empty")
        @Setter @Getter private String name;


        @Column (name = "address")
        @Setter @Getter private String address;


        @Column (name = "location")
        @Setter @Getter private String location;


        @Column(name = "description", length = 100000)
        @Setter @Getter private String description;



        @Column (name = "program_code")
        @Setter @Getter private Integer code;



        @Column (name = "saving_date")
        @Setter @Getter private Date date;



        @Column(name="created_at", nullable = false, updatable = false)
        @CreationTimestamp
        @Setter @Getter private Timestamp createdAt;

        @Column(name = "updated_at")
        @UpdateTimestamp
        @Setter @Getter private Timestamp updatedAt;


        @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
        @Setter @Getter private List<StudentClass> studentClasses;

        @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
        @Setter @Getter private List<Subject> subjects;


}

