package com.giannijin.ITS.Prodigi.v3.repository;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Student> findByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);


    @Query("SELECT s FROM Student s WHERE s.program = :program")
    List<Student> findByProgram(@Param("program") String program);


    @Query("SELECT COUNT(s) FROM Student s")
    Integer findTotalStudents();

    @Query("SELECT s FROM Student s WHERE s.startYear = :startYear AND s.endYear = :endYear")
    List<Student> findByStartYearAndEndYear(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);


    @Query("SELECT s FROM Student s WHERE s.startYear = :year OR s.endYear = :year")
    List<Student> findByStartYearOrEndYear(@Param("year") Integer year);

    @Query("SELECT s.program, COUNT(s) FROM Student s GROUP BY s.program")
    List<Object[]> findTotalStudentsForEachProgram();

}
