package com.giannijin.ITS.Prodigi.v3.repository;

import com.giannijin.ITS.Prodigi.v3.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.giannijin.ITS.Prodigi.v3.model.Student; // Add this line

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p WHERE p.name = :name")
    List<Program> findByName(@Param("name") String name);

    @Query("SELECT p FROM Program p WHERE p.location = :location")
    List<Program> findByLocation(@Param("location") String location);

    @Query("SELECT COUNT(p) FROM Program p")
    Integer findTotalPrograms();


    @Query("SELECT p FROM Program p WHERE p.description = :description")
    List<Program> findByDescription(@Param("description") String description);

    @Query("SELECT p.location, COUNT(p) FROM Program p GROUP BY p.location")
    List<Object[]> findTotalProgramsForEachLocation();

    @Query("SELECT s FROM Program p JOIN p.studentClasses sc JOIN sc.studentList s WHERE p.programId = :programId")
    List<Student> findAllStudentsByProgramId(@Param("programId") Long programId);

}