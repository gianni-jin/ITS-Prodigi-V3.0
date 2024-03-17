package com.giannijin.ITS.Prodigi.v3.repository;

import com.giannijin.ITS.Prodigi.v3.model.Subject;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByName(String name);

    @Query("SELECT t FROM Teacher t JOIN t.subjects s WHERE s.name = :subjectName")
    List<Teacher> findTeachersBySubject(@Param("subjectName") String subjectName);
    @Query("SELECT s FROM Subject s WHERE s.program = :program")
    List<Subject> findByProgram(@Param("program") String program);

    @Query("SELECT s FROM Subject s WHERE s.startYear = :startYear")
    List<Subject> findByStartYear(@Param("startYear") Integer startYear);

    @Query("SELECT s.program, COUNT(s) FROM Subject s GROUP BY s.program")
    List<Object[]> findTotalSubjectsForEachProgram();


}
